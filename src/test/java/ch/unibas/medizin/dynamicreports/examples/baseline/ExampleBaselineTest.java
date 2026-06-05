/*
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2018 Ricardo Mariaca and the Dynamic Reports Contributors
 *
 * This file is part of DynamicReports.
 *
 * DynamicReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * DynamicReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with DynamicReports. If not, see <http://www.gnu.org/licenses/>.
 */
package ch.unibas.medizin.dynamicreports.examples.baseline;

import ch.unibas.medizin.dynamicreports.examples.baseline.ReportBaselines.Comparison;
import ch.unibas.medizin.dynamicreports.report.defaults.Defaults;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.opentest4j.TestAbortedException;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Renders every example report to PNG images and compares them against committed baselines.
 *
 * <p>Each example class builds a report in its no-arg constructor and displays it through
 * {@code JasperReportBuilder.show()}. On the test classpath that call is intercepted by the shadow
 * {@link JasperViewer}, which captures the {@link JasperPrint} instead of opening a window. The test
 * then renders each page to a PNG &mdash; the comparable format &mdash; and:</p>
 *
 * <ul>
 *     <li><b>first run</b> (no baseline yet): writes the images under
 *         {@code src/test/resources/report-baselines/<example>/} and marks the case as skipped,
 *         signalling that a baseline was created and should be reviewed and committed;</li>
 *     <li><b>later runs</b>: re-renders and compares pixel by pixel against the baseline, failing on
 *         any visual difference and writing the actual image plus a red diff image under
 *         {@code target/report-baseline-failures/} for inspection.</li>
 * </ul>
 *
 * <p>Determinism: the only source of randomness across the examples is {@link Math#random()} (used
 * by 36 of them for sample data). Its shared generator is reseeded to a fixed value before each
 * example so rendered output is reproducible. This requires
 * {@code --add-opens java.base/java.lang=ALL-UNNAMED} (configured in the Surefire {@code argLine}).</p>
 *
 * <p>Tunable via system properties:</p>
 * <ul>
 *     <li>{@code report.baseline.dir} &ndash; baseline directory (default {@code src/test/resources/report-baselines})</li>
 *     <li>{@code report.baseline.zoom} &ndash; rendering zoom factor (default {@code 1.0})</li>
 *     <li>{@code report.baseline.maxPages} &ndash; max pages rendered per example (default {@code 50})</li>
 *     <li>{@code report.baseline.colorTolerance} &ndash; per-channel colour tolerance 0-255 (default {@code 0})</li>
 *     <li>{@code report.baseline.maxDiffRatio} &ndash; tolerated fraction of differing pixels (default {@code 0.0})</li>
 * </ul>
 *
 * @author Martin Imobersteg
 */
class ExampleBaselineTest {

    private static final String EXAMPLES_PACKAGE = "ch.unibas.medizin.dynamicreports.examples";

    /** Fixed seed applied to {@link Math#random()}'s generator before each example. */
    private static final long RANDOM_SEED = 20260530L;

    /** DynamicReports default font pinned for every example: the library default the baselines use. */
    private static final String DEFAULT_FONT = "SansSerif";

    /** Frozen "now" used for every example so {@code Instant.now()}/{@code Calendar.getInstance()} are reproducible. */
    private static final Instant FIXED_NOW = Instant.parse("2026-05-30T08:00:00Z");

    /** Fixed time zone so rendered dates do not depend on the machine's zone. */
    private static final TimeZone FIXED_ZONE = TimeZone.getTimeZone("UTC");

    private static final Path BASELINE_DIR = Path.of(System.getProperty("report.baseline.dir", "src/test/resources/report-baselines"));
    private static final Path FAILURE_DIR = Path.of("target/report-baseline-failures");
    private static final float ZOOM = Float.parseFloat(System.getProperty("report.baseline.zoom", "1.0"));
    private static final int MAX_PAGES = Integer.parseInt(System.getProperty("report.baseline.maxPages", "50"));
    private static final int COLOR_TOLERANCE = Integer.parseInt(System.getProperty("report.baseline.colorTolerance", "0"));
    private static final double MAX_DIFF_RATIO = Double.parseDouble(System.getProperty("report.baseline.maxDiffRatio", "0.0"));

    /**
     * Higher tolerance applied <em>only</em> to the JFreeChart-based examples (the {@code chart} and
     * {@code chartcustomization} packages). Their rasterised output carries tiny antialiasing
     * differences across platforms (&le;~1.2% of pixels on the Linux CI runner) that the bundled font
     * extension cannot remove; every other example stays pixel-exact at {@link #MAX_DIFF_RATIO}.
     */
    private static final double CHART_MAX_DIFF_RATIO = Double.parseDouble(System.getProperty("report.baseline.chartMaxDiffRatio", "0.02"));

    /** Top-level packages whose examples render charts and therefore compare with {@link #CHART_MAX_DIFF_RATIO}. */
    private static final Set<String> CHART_PACKAGES = Set.of("chart", "chartcustomization");

    /**
     * Examples that never call {@code show()} but export a deterministic text file we can baseline as
     * text instead of as a rendered image. Maps the example's relative name to the file it writes
     * (relative to the working directory). The HTML export is reproducible because locale, clock and
     * {@link Math#random()} are all pinned for the run.
     */
    private static final Map<String, String> TEXT_EXPORTS = Map.of("exporter/HtmlReport", "report.html");

    @TestFactory
    Stream<DynamicTest> renderAndCompareExamples() throws Exception {
        final List<Class<?>> examples = discoverExampleClasses();
        assertTrue(!examples.isEmpty(), "no example classes discovered under " + EXAMPLES_PACKAGE + " (run 'mvn test-compile' first)");
        return examples.stream().map(example -> DynamicTest.dynamicTest(example.getName().substring(EXAMPLES_PACKAGE.length() + 1), () -> renderAndCompare(example)));
    }

    private void renderAndCompare(Class<?> example) throws Exception {
        final Path exampleDir = BASELINE_DIR.resolve(relativeName(example));
        final int existingBaselines = countExistingBaselines(exampleDir);

        final List<JasperPrint> prints;
        try {
            prints = renderExample(example);
        } catch (ReflectiveOperationException e) {
            // The example threw while building its report (e.g. a Windows-only output path or a
            // resource it cannot resolve here). If it was never baselined, skip it with the reason;
            // if a baseline exists, a previously working example now fails to render — a regression.
            final Throwable cause = unwrap(e);
            if (existingBaselines == 0) {
                throw new TestAbortedException(example.getSimpleName() + " could not be rendered, so it cannot be baselined: " + cause, cause);
            }
            throw new AssertionError(example.getSimpleName() + " has a baseline but now fails to render: " + cause, cause);
        }

        if (prints.isEmpty()) {
            final String textExport = TEXT_EXPORTS.get(relativeName(example));
            if (textExport != null) {
                baselineTextExport(example, exampleDir, Path.of(textExport));
                return;
            }
            throw new TestAbortedException(example.getSimpleName() + " produced no report through show() (e.g. it exports straight to a file); nothing to baseline");
        }

        final List<BufferedImage> actual = renderAllPages(prints);

        if (existingBaselines == 0) {
            for (int page = 0; page < actual.size(); page++) {
                ReportBaselines.write(actual.get(page), exampleDir.resolve(pageFileName(page)));
            }
            throw new TestAbortedException("baseline created for %s (%d page(s)) at %s — review and commit, then rerun to compare".formatted(example.getSimpleName(), actual.size(), exampleDir));
        }

        if (existingBaselines != actual.size()) {
            failWithActual(example, actual, "page count changed: baseline has %d page(s), now %d".formatted(existingBaselines, actual.size()));
        }

        final double maxDiffRatio = maxDiffRatioFor(example);
        final List<String> failures = new ArrayList<>();
        for (int page = 0; page < actual.size(); page++) {
            final Path baselineFile = exampleDir.resolve(pageFileName(page));
            final BufferedImage baseline = ReportBaselines.read(baselineFile);
            final Comparison comparison = ReportBaselines.compare(baseline, actual.get(page), COLOR_TOLERANCE, maxDiffRatio);
            if (!comparison.match()) {
                writeFailureArtifacts(example, page, actual.get(page), comparison.diffImage());
                failures.add("page %d: %s".formatted(page + 1, comparison.detail()));
            }
        }

        if (!failures.isEmpty()) {
            fail("%s differs from baseline:%n  %s%nActual and diff images written under %s".formatted(example.getSimpleName(), String.join("\n  ", failures), FAILURE_DIR.resolve(relativeName(example))));
        }
    }

    /**
     * Runs the example through its {@code main} method and returns whatever reports it sent to
     * {@code show()}. {@code main} is the canonical entry point every example defines, and it renders
     * the report whether the example shows from its constructor or directly from {@code main}.
     *
     * <p>Locale, time zone, the shared random generator, the clock ({@code Instant.now()} and
     * {@code Calendar.getInstance()}) and the DynamicReports default font are all pinned to fixed
     * values for the duration of the run so the example &mdash; and JasperReports while it fills the
     * report &mdash; renders reproducibly without any change to the example sources.</p>
     *
     * <p>The default font is pinned to {@value #DEFAULT_FONT} &mdash; the library's built-in default
     * and the font the committed baselines were rendered with. This neutralises any host
     * {@code dynamicreports-defaults.xml} on the test classpath (the core project ships one that sets
     * a different default font for its own tests), keeping the examples rendering against the same
     * font family that produced the baselines. The original default is restored afterwards so the
     * core tests are unaffected.</p>
     */
    private List<JasperPrint> renderExample(Class<?> example) throws Exception {
        JasperViewer.clearCaptured();
        reseedSharedRandom(RANDOM_SEED);
        final Locale previousLocale = Locale.getDefault();
        final TimeZone previousZone = TimeZone.getDefault();
        final String previousDefaultFont = Defaults.getDefaults().getFont().getFontName();
        Locale.setDefault(Locale.ENGLISH);
        TimeZone.setDefault(FIXED_ZONE);
        Defaults.getDefaults().getFont().setFontName(DEFAULT_FONT);
        try (MockedStatic<Instant> instantMock = Mockito.mockStatic(Instant.class, Mockito.CALLS_REAL_METHODS);
             MockedStatic<Calendar> calendarMock = Mockito.mockStatic(Calendar.class, Mockito.CALLS_REAL_METHODS)) {
            instantMock.when(Instant::now).thenReturn(FIXED_NOW);
            calendarMock.when(Calendar::getInstance).thenAnswer(invocation -> fixedCalendar());
            example.getDeclaredMethod("main", String[].class).invoke(null, (Object) new String[0]);
        } finally {
            Locale.setDefault(previousLocale);
            TimeZone.setDefault(previousZone);
            Defaults.getDefaults().getFont().setFontName(previousDefaultFont);
        }
        return JasperViewer.drainCaptured();
    }

    private static Throwable unwrap(Throwable t) {
        return t.getCause() != null ? t.getCause() : t;
    }

    /** A fresh {@link Calendar} pinned to {@link #FIXED_NOW}; fresh per call so callers can mutate it safely. */
    private static Calendar fixedCalendar() {
        final Calendar calendar = new GregorianCalendar(FIXED_ZONE, Locale.ENGLISH);
        calendar.setTimeInMillis(FIXED_NOW.toEpochMilli());
        return calendar;
    }

    private List<BufferedImage> renderAllPages(List<JasperPrint> prints) throws Exception {
        final List<BufferedImage> images = new ArrayList<>();
        for (JasperPrint print : prints) {
            images.addAll(ReportBaselines.renderPages(print, ZOOM, MAX_PAGES));
        }
        return images;
    }

    private void failWithActual(Class<?> example, List<BufferedImage> actual, String message) throws IOException {
        for (int page = 0; page < actual.size(); page++) {
            writeFailureArtifacts(example, page, actual.get(page), null);
        }
        fail("%s: %s (actual images written under %s)".formatted(example.getSimpleName(), message, FAILURE_DIR.resolve(relativeName(example))));
    }

    private void writeFailureArtifacts(Class<?> example, int page, BufferedImage actual, BufferedImage diff) throws IOException {
        final Path dir = FAILURE_DIR.resolve(relativeName(example));
        ReportBaselines.write(actual, dir.resolve("actual-" + pageFileName(page)));
        if (diff != null) {
            ReportBaselines.write(diff, dir.resolve("diff-" + pageFileName(page)));
        }
    }

    /**
     * Baselines the text file an export-only example wrote to the working directory. Behaves like the
     * image path: on the first run it stores the file under the baseline directory and marks the case
     * skipped; on later runs it compares the freshly written file against the stored one and fails on
     * any difference, writing the actual output under {@link #FAILURE_DIR} for inspection. The example's
     * scratch artifacts are always removed so repeated runs leave the working tree clean.
     */
    private void baselineTextExport(Class<?> example, Path exampleDir, Path produced) throws IOException {
        final String actual;
        try {
            if (!Files.isRegularFile(produced)) {
                throw new TestAbortedException(example.getSimpleName() + " did not write its export file " + produced.toAbsolutePath() + "; nothing to baseline");
            }
            actual = normalizeNewlines(Files.readString(produced, StandardCharsets.UTF_8));
        } finally {
            deleteExportArtifacts(produced);
        }

        final Path baselineFile = exampleDir.resolve(produced.getFileName().toString());
        if (!Files.isRegularFile(baselineFile)) {
            Files.createDirectories(exampleDir);
            Files.writeString(baselineFile, actual, StandardCharsets.UTF_8);
            throw new TestAbortedException("baseline created for %s (%s) at %s — review and commit, then rerun to compare".formatted(example.getSimpleName(), produced.getFileName(), baselineFile));
        }

        final String baseline = normalizeNewlines(Files.readString(baselineFile, StandardCharsets.UTF_8));
        if (!baseline.equals(actual)) {
            final Path failure = FAILURE_DIR.resolve(relativeName(example)).resolve("actual-" + produced.getFileName());
            Files.createDirectories(failure.getParent());
            Files.writeString(failure, actual, StandardCharsets.UTF_8);
            fail("%s export differs from baseline %s (actual written to %s)".formatted(example.getSimpleName(), baselineFile, failure));
        }
    }

    private static String normalizeNewlines(String text) {
        return text.replace("\r\n", "\n");
    }

    /**
     * Removes the file an export example wrote plus the sibling {@code images} directory the HTML
     * exporter populates, so the working tree is left as it was before the run.
     */
    private static void deleteExportArtifacts(Path produced) throws IOException {
        Files.deleteIfExists(produced);
        final Path imagesDir = produced.toAbsolutePath().getParent().resolve("images");
        if (Files.isDirectory(imagesDir)) {
            try (Stream<Path> entries = Files.walk(imagesDir)) {
                entries.sorted(Comparator.reverseOrder()).forEach(path -> {
                    try {
                        Files.delete(path);
                    } catch (IOException e) {
                        throw new java.io.UncheckedIOException(e);
                    }
                });
            }
        }
    }

    private int countExistingBaselines(Path exampleDir) throws IOException {
        if (!Files.isDirectory(exampleDir)) {
            return 0;
        }
        try (Stream<Path> files = Files.list(exampleDir)) {
            return (int) files.filter(path -> path.getFileName().toString().matches("page-\\d+\\.png")).count();
        }
    }

    private static String relativeName(Class<?> example) {
        return example.getName().substring(EXAMPLES_PACKAGE.length() + 1).replace('.', '/');
    }

    /** The tolerated differing-pixel ratio for an example: relaxed for chart packages, strict otherwise. */
    private static double maxDiffRatioFor(Class<?> example) {
        final String relative = relativeName(example);
        final int slash = relative.indexOf('/');
        final String topPackage = slash < 0 ? "" : relative.substring(0, slash);
        return CHART_PACKAGES.contains(topPackage) ? CHART_MAX_DIFF_RATIO : MAX_DIFF_RATIO;
    }

    private static String pageFileName(int pageIndex) {
        return "page-%03d.png".formatted(pageIndex + 1);
    }

    /**
     * Reseeds the {@link Random} backing {@link Math#random()} so {@code Math.random()} produces a
     * reproducible sequence. Requires {@code --add-opens java.base/java.lang=ALL-UNNAMED}.
     */
    private static void reseedSharedRandom(long seed) throws ReflectiveOperationException {
        final Class<?> holder = Class.forName("java.lang.Math$RandomNumberGeneratorHolder");
        final Field field = holder.getDeclaredField("randomNumberGenerator");
        field.setAccessible(true);
        ((Random) field.get(null)).setSeed(seed);
    }

    /** Finds every runnable example: a concrete class declaring a {@code public static void main}. */
    private static List<Class<?>> discoverExampleClasses() throws Exception {
        final Path root = examplesClassRoot();
        final Path packageRoot = root.resolve(EXAMPLES_PACKAGE.replace('.', '/'));
        try (Stream<Path> files = Files.walk(packageRoot)) {
            return files.filter(path -> path.toString().endsWith(".class"))
                    .map(path -> toClassName(root, path))
                    .filter(name -> !name.contains("$"))
                    .map(ExampleBaselineTest::loadClass)
                    .filter(ExampleBaselineTest::isRunnableExample)
                    .sorted((a, b) -> a.getName().compareTo(b.getName()))
                    .collect(Collectors.toList());
        }
    }

    /** Locates {@code target/classes} via a known example class so discovery works from any working dir. */
    private static Path examplesClassRoot() throws ClassNotFoundException, URISyntaxException {
        final Class<?> anchor = Class.forName(EXAMPLES_PACKAGE + ".Templates");
        return Path.of(anchor.getProtectionDomain().getCodeSource().getLocation().toURI());
    }

    private static String toClassName(Path root, Path classFile) {
        final String relative = root.relativize(classFile).toString();
        return relative.substring(0, relative.length() - ".class".length()).replace(root.getFileSystem().getSeparator(), ".").replace('/', '.');
    }

    private static Class<?> loadClass(String name) {
        try {
            return Class.forName(name, false, ExampleBaselineTest.class.getClassLoader());
        } catch (Throwable t) {
            return null;
        }
    }

    private static boolean isRunnableExample(Class<?> type) {
        if (type == null || type.isInterface() || type.isEnum() || Modifier.isAbstract(type.getModifiers())) {
            return false;
        }
        try {
            return Modifier.isStatic(type.getDeclaredMethod("main", String[].class).getModifiers());
        } catch (NoSuchMethodException e) {
            return false;
        }
    }
}
