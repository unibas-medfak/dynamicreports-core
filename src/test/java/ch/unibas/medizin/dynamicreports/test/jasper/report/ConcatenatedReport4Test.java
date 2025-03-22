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
package ch.unibas.medizin.dynamicreports.test.jasper.report;

import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.cmp;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.concatenatedReport;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.report;

import java.io.ByteArrayOutputStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ch.unibas.medizin.dynamicreports.jasper.base.reporthandler.JasperPrintListHandler;
import ch.unibas.medizin.dynamicreports.jasper.builder.JasperConcatenatedReportBuilder;
import ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder;
import ch.unibas.medizin.dynamicreports.report.exception.DRException;

/**
 * @author Ricardo Mariaca
 */
public class ConcatenatedReport4Test {
    static JasperConcatenatedReportBuilder concatenatedReport;

    @BeforeAll
    public static void init() {
        final JasperReportBuilder report1 = report().title(cmp.text("text1")).pageFooter(cmp.pageNumber());
        final JasperReportBuilder report2 = report().title(cmp.text("text2")).pageFooter(cmp.pageNumber());
        final JasperReportBuilder report3 = report().title(cmp.text("text3")).pageFooter(cmp.pageNumber());

        concatenatedReport = concatenatedReport(new JasperPrintListHandler());
        concatenatedReport.continuousPageNumbering();
        concatenatedReport.concatenate(report1, report2, report3);
    }

    @Test
    public void test() {
        try {
            new ByteArrayOutputStream();
            ByteArrayOutputStream bos;
            bos = new ByteArrayOutputStream();
            concatenatedReport.toCsv(bos);
            Assertions.assertEquals( "text1\n1\ntext2\n2\ntext3\n3\n", bos.toString(), "concatenated report ");

            concatenatedReport.setContinuousPageNumbering(false);
            bos = new ByteArrayOutputStream();
            concatenatedReport.toCsv(bos);
            Assertions.assertEquals( "text1\n1\ntext2\n2\ntext3\n3\n", bos.toString(), "concatenated report ");
        } catch (final DRException e) {
            e.printStackTrace();
            Assertions.fail(e.getMessage());
        }
    }
}
