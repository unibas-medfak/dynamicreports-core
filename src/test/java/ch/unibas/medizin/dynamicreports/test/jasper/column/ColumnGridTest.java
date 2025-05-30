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
package ch.unibas.medizin.dynamicreports.test.jasper.column;

import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.col;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.sbt;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.column.TextColumnBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.subtotal.AggregationSubtotalBuilder;
import ch.unibas.medizin.dynamicreports.report.constant.ListType;
import ch.unibas.medizin.dynamicreports.report.datasource.DRDataSource;
import ch.unibas.medizin.dynamicreports.test.jasper.AbstractJasperPositionTest;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ColumnGridTest extends AbstractJasperPositionTest {
    private TextColumnBuilder<Integer> column1;
    private TextColumnBuilder<Integer> column2;
    private TextColumnBuilder<Integer> column3;
    private AggregationSubtotalBuilder<Integer> subtotal1;
    private AggregationSubtotalBuilder<Integer> subtotal2;
    private AggregationSubtotalBuilder<Integer> subtotal3;

    @Override
    protected void configureReport(JasperReportBuilder rb) {
        rb.columnGrid(ListType.VERTICAL)
          .columns(column1 = col.column("Column1", "field1", Integer.class), column2 = col.column("Column2", "field2", Integer.class), column3 = col.column("Column3", "field3", Integer.class))
          .subtotalsAtSummary(subtotal1 = sbt.sum(column1), subtotal2 = sbt.sum(column2), subtotal3 = sbt.sum(column3));
    }

    @Override
    @Test
    public void test() {
        super.test();

        numberOfPagesTest(1);

        // column1
        columnTitlePositionTest(column1, 0, 10, 10, 575, 16);
        columnDetailPositionTest(column1, 0, 10, 58, 575, 16);
        columnDetailPositionTest(column1, 1, 10, 106, 575, 16);
        // column2
        columnTitlePositionTest(column2, 0, 10, 26, 575, 16);
        columnDetailPositionTest(column2, 0, 10, 74, 575, 16);
        columnDetailPositionTest(column2, 1, 10, 122, 575, 16);
        // column3
        columnTitlePositionTest(column3, 0, 10, 42, 575, 16);
        columnDetailPositionTest(column3, 0, 10, 90, 575, 16);
        columnDetailPositionTest(column3, 1, 10, 138, 575, 16);
        // subtotal
        subtotalPositionTest(subtotal1, 0, 10, 154, 575, 16);
        subtotalPositionTest(subtotal2, 0, 10, 170, 575, 16);
        subtotalPositionTest(subtotal3, 0, 10, 186, 575, 16);
    }

    @Override
    protected JRDataSource createDataSource() {
        final DRDataSource dataSource = new DRDataSource("field1", "field2", "field3");
        dataSource.add(1, 2, 3);
        dataSource.add(1, 2, 3);
        return dataSource;
    }
}
