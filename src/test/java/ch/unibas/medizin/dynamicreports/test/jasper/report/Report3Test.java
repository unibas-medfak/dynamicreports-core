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

import java.io.Serializable;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder;
import ch.unibas.medizin.dynamicreports.report.constant.WhenNoDataType;
import ch.unibas.medizin.dynamicreports.test.jasper.AbstractJasperValueTest;
import net.sf.jasperreports.engine.JasperPrint;

/**
 * @author Ricardo Mariaca
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Report3Test extends AbstractJasperValueTest implements Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    protected void configureReport(JasperReportBuilder rb) {
        rb.setReportName("report1")
          .setWhenNoDataType(WhenNoDataType.ALL_SECTIONS_NO_DETAIL)
          .setSummaryOnANewPage(true)
          .setSummaryWithPageHeaderAndFooter(true)
          .pageHeader(cmp.text("page header"))
          .pageFooter(cmp.text("page footer"))
          .summary(cmp.text("summary"));
    }

    @Override
    @Test
    public void test() {
        super.test();

        numberOfPagesTest(2);

        final JasperPrint jasperPrint = getJasperPrint();
        Assertions.assertEquals("report1", jasperPrint.getName());

        // page header
        elementCountTest("pageHeader.textField1", 2);
        elementValueTest("pageHeader.textField1", "page header", "page header");

        // page footer
        elementCountTest("pageFooter.textField1", 2);
        elementValueTest("pageFooter.textField1", "page footer", "page footer");

        // summary
        elementCountTest("summary.textField1", 1);
        elementValueTest("summary.textField1", "summary");
    }
}
