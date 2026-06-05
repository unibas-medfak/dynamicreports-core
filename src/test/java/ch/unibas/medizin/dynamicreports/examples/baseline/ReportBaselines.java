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

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Renders {@link JasperPrint} pages to PNG images and compares them pixel by pixel.
 *
 * <p>PNG is the "comparable format": rendering a report page to a raster image makes a report's
 * visual output directly diff-able. Rendering is deterministic on a given machine, so a baseline
 * captured once can be compared against future runs to catch unintended visual changes.</p>
 *
 * @author Martin Imobersteg
 */
public final class ReportBaselines {

    /** Pixels that differ are painted this colour in the generated diff image. */
    private static final int DIFF_COLOR = Color.RED.getRGB();

    private ReportBaselines() {
    }

    /**
     * Renders the pages of a {@link JasperPrint} to images.
     *
     * @param jasperPrint the print to render
     * @param zoom        rendering zoom factor (1.0 = report resolution)
     * @param maxPages    upper bound on the number of pages rendered
     * @return one {@link BufferedImage} per rendered page
     */
    public static List<BufferedImage> renderPages(JasperPrint jasperPrint, float zoom, int maxPages) throws JRException {
        final int pageCount = Math.min(jasperPrint.getPages().size(), maxPages);
        final List<BufferedImage> images = new ArrayList<>(pageCount);
        for (int page = 0; page < pageCount; page++) {
            final Image image = JasperPrintManager.printPageToImage(jasperPrint, page, zoom);
            images.add(toBufferedImage(image));
        }
        return images;
    }

    /** Result of comparing an actual page image against its baseline. */
    public record Comparison(boolean match, int differingPixels, int totalPixels, String detail, BufferedImage diffImage) {
        public double differingRatio() {
            return totalPixels == 0 ? 0.0 : (double) differingPixels / totalPixels;
        }
    }

    /**
     * Compares two images.
     *
     * @param baseline       the committed baseline image
     * @param actual         the freshly rendered image
     * @param colorTolerance allowed per-channel (0-255) difference before a pixel counts as different
     * @param maxDiffRatio   fraction of differing pixels tolerated before the comparison fails
     */
    public static Comparison compare(BufferedImage baseline, BufferedImage actual, int colorTolerance, double maxDiffRatio) {
        if (baseline.getWidth() != actual.getWidth() || baseline.getHeight() != actual.getHeight()) {
            final String detail = "size changed: baseline %dx%d, actual %dx%d".formatted(baseline.getWidth(), baseline.getHeight(), actual.getWidth(), actual.getHeight());
            return new Comparison(false, -1, -1, detail, null);
        }

        final int width = baseline.getWidth();
        final int height = baseline.getHeight();
        final BufferedImage diff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        int differing = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                final int b = baseline.getRGB(x, y);
                final int a = actual.getRGB(x, y);
                if (pixelsDiffer(b, a, colorTolerance)) {
                    differing++;
                    diff.setRGB(x, y, DIFF_COLOR);
                } else {
                    diff.setRGB(x, y, dim(b));
                }
            }
        }

        final int total = width * height;
        final double ratio = total == 0 ? 0.0 : (double) differing / total;
        final boolean match = ratio <= maxDiffRatio;
        final String detail = "%d/%d pixels differ (%.4f%%)".formatted(differing, total, ratio * 100);
        return new Comparison(match, differing, total, detail, diff);
    }

    public static BufferedImage read(Path file) throws IOException {
        final BufferedImage image = ImageIO.read(file.toFile());
        if (image == null) {
            throw new IOException("not a readable image: " + file);
        }
        return image;
    }

    public static void write(BufferedImage image, Path file) throws IOException {
        Files.createDirectories(file.getParent());
        ImageIO.write(image, "png", file.toFile());
    }

    private static boolean pixelsDiffer(int rgb1, int rgb2, int tolerance) {
        if (rgb1 == rgb2) {
            return false;
        }
        if (tolerance <= 0) {
            return true;
        }
        final int dr = Math.abs(((rgb1 >> 16) & 0xFF) - ((rgb2 >> 16) & 0xFF));
        final int dg = Math.abs(((rgb1 >> 8) & 0xFF) - ((rgb2 >> 8) & 0xFF));
        final int db = Math.abs((rgb1 & 0xFF) - (rgb2 & 0xFF));
        return dr > tolerance || dg > tolerance || db > tolerance;
    }

    /** Fades a baseline pixel towards white so red diff markers stand out in the diff image. */
    private static int dim(int rgb) {
        final int r = (rgb >> 16) & 0xFF;
        final int g = (rgb >> 8) & 0xFF;
        final int b = rgb & 0xFF;
        return (mix(r) << 16) | (mix(g) << 8) | mix(b);
    }

    private static int mix(int channel) {
        return channel + (255 - channel) * 3 / 4;
    }

    private static BufferedImage toBufferedImage(Image image) {
        if (image instanceof BufferedImage buffered) {
            return buffered;
        }
        final BufferedImage buffered = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
        final Graphics2D graphics = buffered.createGraphics();
        try {
            graphics.drawImage(image, 0, 0, null);
        } finally {
            graphics.dispose();
        }
        return buffered;
    }
}
