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
package ch.unibas.medizin.dynamicreports.test.jasper.tableofcontents;

import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.col;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.margin;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.type;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.column.TextColumnBuilder;
import ch.unibas.medizin.dynamicreports.report.constant.PageOrientation;
import ch.unibas.medizin.dynamicreports.report.constant.PageType;
import ch.unibas.medizin.dynamicreports.report.datasource.DRDataSource;
import ch.unibas.medizin.dynamicreports.test.jasper.AbstractJasperPositionTest;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TableOfContentsPosition3Test extends AbstractJasperPositionTest {

    @Override
    protected void configureReport(JasperReportBuilder rb) {
        TextColumnBuilder<String> column1;
        rb.setPageFormat(PageType.A6, PageOrientation.LANDSCAPE)
          .setPageMargin(margin(10).setLeft(30))
          .tableOfContents()
          .columns(column1 = col.column("Column1", "field1", type.stringType()), col.column("Column2", "field2", type.stringType()))
          .groupBy(column1);
    }

    @Override
    @Test
    public void test() {
        super.test();

        numberOfPagesTest(5);

        elementPositionTest("title.textField1", 0, 30, 10, 381, 19);

        for (int i = 0; i < 14; i++) {
            elementPositionTest("detail.list2", i, 30, 49 + 16 * i, 381, 16);
            elementPositionTest("detail.textField1", i, 0, 0, 179, 16);
            elementPositionTest("detail.textField2", i, 179, 0, 180, 16);
            elementPositionTest("detail.textField3", i, 359, 0, 22, 16);
        }
        for (int i = 14; i < 20; i++) {
            elementPositionTest("detail.list2", i, 30, 10 + 16 * (i - 14), 381, 16);
            elementPositionTest("detail.textField1", i, 0, 0, 179, 16);
            elementPositionTest("detail.textField2", i, 179, 0, 180, 16);
            elementPositionTest("detail.textField3", i, 359, 0, 22, 16);
        }

        containsElement("title.textField1", 0);
    }

    @Override
    protected JRDataSource createDataSource() {
        final DRDataSource dataSource = new DRDataSource("field1", "field2");
        for (int i = 0; i < 20; i++) {
            dataSource.add("value" + i, "text");
        }
        return dataSource;
    }
}
