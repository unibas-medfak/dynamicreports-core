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

import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.cmp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder;
import ch.unibas.medizin.dynamicreports.report.constant.PageType;
import ch.unibas.medizin.dynamicreports.report.constant.WhenNoDataType;
import ch.unibas.medizin.dynamicreports.test.jasper.AbstractJasperPositionTest;

/**
 * @author Ricardo Mariaca
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ListGapTest extends AbstractJasperPositionTest {

    @Override
    protected void configureReport(JasperReportBuilder rb) {
        rb.setPageFormat(PageType.A5).setWhenNoDataType(WhenNoDataType.ALL_SECTIONS_NO_DETAIL).title(cmp.horizontalList(cmp.text("a"), cmp.text("a")).setGap(250));
    }

    @Override
    @Test
    public void test() {
        super.test();

        numberOfPagesTest(1);

        elementPositionTest("title.list1", 0, 10, 10, 401, 16);

        elementPositionTest("title.textField1", 0, 0, 0, 75, 16);
        elementPositionTest("title.textField2", 0, 325, 0, 76, 16);
    }
}
