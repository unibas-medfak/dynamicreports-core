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
package ch.unibas.medizin.dynamicreports.test.jasper.chart;

import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.cht;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.col;

import java.io.Serial;
import java.io.Serializable;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.column.TextColumnBuilder;
import ch.unibas.medizin.dynamicreports.report.datasource.DRDataSource;
import ch.unibas.medizin.dynamicreports.test.jasper.AbstractJasperChartTest;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ChartData3Test extends AbstractJasperChartTest implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Override
    protected void configureReport(JasperReportBuilder rb) {
        TextColumnBuilder<String> column1;
        TextColumnBuilder<Integer> column2;
        TextColumnBuilder<Integer> column3;

        rb.columns(column1 = col.column("Column1", "field1", String.class), column2 = col.column("Column2", "field2", Integer.class), column3 = col.column("Column3", "field3", Integer.class))
          .summary(cht.lineChart().setCategory(column1).series(cht.serie(column2), cht.serie(column3)));
    }

    @Override
    @Test
    public void test() {
        super.test();

        numberOfPagesTest(1);

        final String[] categories = new String[] {"value1", "value2", "value3"};
        final String[] series = new String[] {"Column2", "Column3"};
        final Number[][] values = new Number[][] {{1d, 2d}, {null, 3d}, {3d, 4d}, {4d, null}};

        chartCountTest("summary.chart1", 1);
        chartCategoryCountTest("summary.chart1", 0, 4);
        chartSeriesCountTest("summary.chart1", 0, 2);
        chartDataTest("summary.chart1", 0, categories, series, values);
    }

    @Override
    protected JRDataSource createDataSource() {
        final DRDataSource dataSource = new DRDataSource("field1", "field2", "field3");
        dataSource.add("value1", 1, 2);
        dataSource.add("value2", null, 1);
        dataSource.add("value2", null, null);
        dataSource.add("value2", null, 2);
        dataSource.add("value3", null, 4);
        dataSource.add("value3", 3, null);
        dataSource.add("value4", 4, null);
        return dataSource;
    }
}
