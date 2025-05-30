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
package ch.unibas.medizin.dynamicreports.test.jasper.component;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder;
import ch.unibas.medizin.dynamicreports.report.constant.WhenNoDataType;
import ch.unibas.medizin.dynamicreports.test.jasper.AbstractJasperValueTest;

import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.cmp;

/**
 * @author Ricardo Mariaca
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PageNumber1Test extends AbstractJasperValueTest {

    @Override
    protected void configureReport(JasperReportBuilder rb) {
        rb.setWhenNoDataType(WhenNoDataType.ALL_SECTIONS_NO_DETAIL).pageFooter(cmp.pageNumber(), cmp.totalPages(), cmp.pageXslashY(), cmp.pageXofY());
    }

    @Override
    @Test
    public void test() {
        super.test();

        numberOfPagesTest(1);
        elementCountTest("pageFooter.textField1", 1);
        elementValueTest("pageFooter.textField1", "1");

        elementCountTest("pageFooter.textField2", 1);
        elementValueTest("pageFooter.textField2", "1");

        elementCountTest("pageFooter.textField3", 1);
        elementValueTest("pageFooter.textField3", "1");

        elementCountTest("pageFooter.textField4", 1);
        elementValueTest("pageFooter.textField4", "/1");

        elementCountTest("pageFooter.textField5", 1);
        elementValueTest("pageFooter.textField5", "1");

        elementCountTest("pageFooter.textField6", 1);
        elementValueTest("pageFooter.textField6", " of 1");
    }
}
