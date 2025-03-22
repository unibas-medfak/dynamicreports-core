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
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.exp;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.variable;

import java.io.Serializable;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.VariableBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.column.TextColumnBuilder;
import ch.unibas.medizin.dynamicreports.report.constant.Calculation;
import ch.unibas.medizin.dynamicreports.report.constant.Evaluation;
import ch.unibas.medizin.dynamicreports.report.datasource.DRDataSource;
import ch.unibas.medizin.dynamicreports.test.jasper.AbstractJasperChartTest;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GroupChartTest extends AbstractJasperChartTest implements Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    protected void configureReport(JasperReportBuilder rb) {
        final TextColumnBuilder<String> column1 = col.column("Column1", "field1", String.class);
        final TextColumnBuilder<String> column2 = col.column("Column2", "field2", String.class);
        final TextColumnBuilder<Integer> column3 = col.column("Column3", "field3", Integer.class);

        final VariableBuilder<Integer> column2Variable = variable(column2, Calculation.DISTINCT_COUNT);
        column2Variable.setResetType(Evaluation.FIRST_GROUP);

        rb.columns(column1, column2, column3)
          .groupBy(column1)
          .summary(cht.barChart().setCategory(column1).series(cht.serie(exp.number(1)).setLabel("")), cht.barChart().setCategory(column1).series(cht.serie(column2Variable).setLabel("")));
    }

    @Override
    @Test
    public void test() {
        super.test();

        numberOfPagesTest(1);

        final String[] categories = new String[] {"value1", "value2"};
        final String[] series = new String[] {""};

        chartCountTest("summary.chart1", 1);
        chartCategoryCountTest("summary.chart1", 0, 2);
        chartSeriesCountTest("summary.chart1", 0, 1);
        chartDataTest("summary.chart1", 0, categories, series, new Number[][] {{3d}, {2d}});

        chartCountTest("summary.chart2", 1);
        chartCategoryCountTest("summary.chart2", 0, 2);
        chartSeriesCountTest("summary.chart2", 0, 1);
        chartDataTest("summary.chart2", 0, categories, series, new Number[][] {{2L}, {2L}});
    }

    @Override
    protected JRDataSource createDataSource() {
        final DRDataSource dataSource = new DRDataSource("field1", "field2", "field3");
        dataSource.add("value1", "value1_1", 5);
        dataSource.add("value1", "value1_1", 6);
        dataSource.add("value1", "value1_2", 7);
        dataSource.add("value2", "value2_1", 8);
        dataSource.add("value2", "value2_2", 9);
        return dataSource;
    }
}
