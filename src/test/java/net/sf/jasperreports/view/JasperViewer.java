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
package net.sf.jasperreports.view;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReportsContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Test double that <em>shadows</em> the real {@code net.sf.jasperreports.view.JasperViewer} from
 * jasperreports.jar on the test classpath.
 *
 * <p>Every DynamicReports example renders its report by calling {@code JasperReportBuilder.show()},
 * which delegates to {@code JasperViewer.viewReport(JasperPrint)} and opens a Swing window. Because
 * Maven Surefire places {@code target/test-classes} ahead of dependency jars on the classpath, this
 * class is loaded instead of the library one. Rather than displaying a window, it captures the
 * {@link JasperPrint} so the baseline test can render and compare it headlessly &mdash; without
 * modifying any of the example classes.</p>
 *
 * <p>Only the {@code viewReport} overloads actually reachable from {@code show()} are implemented;
 * all of them simply record the print. Capture is thread-safe, but the baseline test runs examples
 * sequentially and drains between them.</p>
 *
 * @author Martin Imobersteg
 */
public final class JasperViewer {

    private static final List<JasperPrint> CAPTURED = Collections.synchronizedList(new ArrayList<>());

    private JasperViewer() {
    }

    /** Removes and returns everything captured so far, leaving the buffer empty. */
    public static List<JasperPrint> drainCaptured() {
        synchronized (CAPTURED) {
            final List<JasperPrint> copy = new ArrayList<>(CAPTURED);
            CAPTURED.clear();
            return copy;
        }
    }

    /** Discards anything captured so far. */
    public static void clearCaptured() {
        CAPTURED.clear();
    }

    public static void viewReport(JasperPrint jasperPrint) {
        CAPTURED.add(jasperPrint);
    }

    public static void viewReport(JasperPrint jasperPrint, boolean exitOnClose) {
        CAPTURED.add(jasperPrint);
    }

    public static void viewReport(JasperPrint jasperPrint, boolean exitOnClose, Locale locale) {
        CAPTURED.add(jasperPrint);
    }

    public static void viewReport(JasperReportsContext jasperReportsContext, JasperPrint jasperPrint, boolean exitOnClose) {
        CAPTURED.add(jasperPrint);
    }

    public static void viewReport(JasperReportsContext jasperReportsContext, JasperPrint jasperPrint, boolean exitOnClose, Locale locale, ResourceBundle resBundle) {
        CAPTURED.add(jasperPrint);
    }
}
