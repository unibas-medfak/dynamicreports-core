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
import ch.unibas.medizin.dynamicreports.report.builder.column.TextColumnBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.subtotal.AggregationSubtotalBuilder;
import ch.unibas.medizin.dynamicreports.report.datasource.DRDataSource;
import ch.unibas.medizin.dynamicreports.test.jasper.AbstractJasperPositionTest;
import net.sf.jasperreports.engine.JRDataSource;

import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.cmp;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.col;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.sbt;

/**
 * @author Ricardo Mariaca
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TextField1Test extends AbstractJasperPositionTest {
    private TextColumnBuilder<Integer> column1;
    private TextColumnBuilder<Integer> column2;
    private TextColumnBuilder<Integer> column3;
    private AggregationSubtotalBuilder<Integer> subtotal1;

    @Override
    protected void configureReport(JasperReportBuilder rb) {
        rb.columns(column1 = col.column("Column1", "field1", Integer.class), column2 = col.column("Column2", "field2", Integer.class).setColumns(20),
                   column3 = col.column("Column3", "field3", Integer.class).setRows(2))
          .subtotalsAtSummary(subtotal1 = sbt.sum(column2))
          .title(cmp.horizontalList(cmp.hListCell(cmp.text("").setColumns(10).setRows(5)).widthFixed()));
    }

    @Override
    @Test
    public void test() {
        super.test();

        numberOfPagesTest(1);

        elementPositionTest("title.textField1", 0, 10, 10, 94, 62);

        columnTitlePositionTest(column1, 0, 0, 0, 149, 16);
        columnDetailPositionTest(column1, 0, 0, 0, 149, 27);

        columnTitlePositionTest(column2, 0, 149, 0, 276, 16);
        columnDetailPositionTest(column2, 0, 149, 0, 276, 27);

        columnTitlePositionTest(column3, 0, 425, 0, 150, 16);
        columnDetailPositionTest(column3, 0, 425, 0, 150, 27);

        subtotalPositionTest(subtotal1, 0, 149, 0, 276, 16);
    }

    @Override
    protected JRDataSource createDataSource() {
        DRDataSource dataSource = new DRDataSource("field1", "field2", "field3");
        for (int i = 0; i < 10; i++) {
            dataSource.add(i, i, i);
        }
        return dataSource;
    }
}
