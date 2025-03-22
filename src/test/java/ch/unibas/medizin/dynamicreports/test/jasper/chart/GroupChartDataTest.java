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
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.grp;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.variable;

import java.io.Serial;
import java.io.Serializable;

import org.jfree.chart.JFreeChart;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder;
import ch.unibas.medizin.dynamicreports.report.base.expression.AbstractSimpleExpression;
import ch.unibas.medizin.dynamicreports.report.builder.column.TextColumnBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.group.ColumnGroupBuilder;
import ch.unibas.medizin.dynamicreports.report.constant.Calculation;
import ch.unibas.medizin.dynamicreports.report.datasource.DRDataSource;
import ch.unibas.medizin.dynamicreports.report.definition.ReportParameters;
import ch.unibas.medizin.dynamicreports.report.definition.chart.DRIChartCustomizer;
import ch.unibas.medizin.dynamicreports.test.jasper.AbstractJasperChartTest;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GroupChartDataTest extends AbstractJasperChartTest implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Override
    protected void configureReport(JasperReportBuilder rb) {
        TextColumnBuilder<String> column1, column2;
        TextColumnBuilder<Integer> column3;
        ColumnGroupBuilder group1;
        final Customizer customizer = new Customizer();

        rb.columns(column1 = col.column("Column1", "field1", String.class), column2 = col.column("Column2", "field2", String.class), column3 = col.column("Column3", "field3", Integer.class))
          .groupBy(group1 = grp.group(column1)
                               .header(cht.barChart()
                                          .setTitle(new TitleExpression())
                                          .customizers(customizer)
                                          .setCategory(column2)
                                          .series(cht.serie(column3), cht.serie("field4", Integer.class).setLabel("f4"), cht.serie(new ValueExpression()).setLabel("exp")))
                               .footer(cht.barChart()
                                          .setTitle(new TitleExpression())
                                          .customizers(customizer)
                                          .setCategory(column2)
                                          .series(cht.serie(column3), cht.serie("field4", Integer.class).setLabel("f4"), cht.serie(new ValueExpression()).setLabel("exp"))))
          .variables(variable("var1", "field4", Integer.class, Calculation.SUM).setResetGroup(group1))
          .summary(cht.barChart().setCategory(column1).series(cht.serie(column3), cht.serie("field4", Integer.class).setLabel("f4"), cht.serie(new ValueExpression()).setLabel("exp")),
                   cht.barChart().setCategory(column2).series(cht.serie(column3), cht.serie("field4", Integer.class).setLabel("f4"), cht.serie(new ValueExpression()).setLabel("exp")));
    }

    @Override
    @Test
    public void test() {
        super.test();

        numberOfPagesTest(2);

        final String[] categories1 = new String[] {"value1", "value2"};
        final String[] categories2 = new String[] {"group1", "group2"};
        final String[] series = new String[] {"Column3", "f4", "exp"};

        chartCountTest("summary.chart1", 1);
        chartTitleTest("summary.chart1", 0, null);
        chartCategoryCountTest("summary.chart1", 0, 2);
        chartSeriesCountTest("summary.chart1", 0, 3);
        chartDataTest("summary.chart1", 0, categories2, series, new Number[][] {{10d, 10d, 20d}, {10d, 26d, 36d}});

        chartCountTest("summary.chart2", 1);
        chartTitleTest("summary.chart2", 0, null);
        chartCategoryCountTest("summary.chart2", 0, 2);
        chartSeriesCountTest("summary.chart2", 0, 3);
        chartDataTest("summary.chart2", 0, categories1, series, new Number[][] {{6d, 14d, 20d}, {14d, 22d, 36d}});

        groupChartTest("Footer", categories1, series);
        groupChartTest("Header", categories1, series);
    }

    private void groupChartTest(String name, String[] categories1, String[] series) {
        chartCountTest("group" + name + ".chart1", 2);
        chartTitleTest("group" + name + ".chart1", 0, "Title sum=10 customizer1");
        chartCategoryCountTest("group" + name + ".chart1", 0, 2);
        chartSeriesCountTest("group" + name + ".chart1", 0, 3);
        chartDataTest("group" + name + ".chart1", 0, categories1, series, new Number[][] {{3d, 3d, 6d}, {7d, 7d, 14d}});

        chartTitleTest("group" + name + ".chart1", 1, "Title sum=26 customizer2");
        chartCategoryCountTest("group" + name + ".chart1", 1, 2);
        chartSeriesCountTest("group" + name + ".chart1", 1, 3);
        chartDataTest("group" + name + ".chart1", 1, categories1, series, new Number[][] {{3d, 11d, 14d}, {7d, 15d, 22d}});
    }

    @Override
    protected JRDataSource createDataSource() {
        final DRDataSource dataSource = new DRDataSource("field1", "field2", "field3", "field4");
        dataSource.add("group1", "value1", 1, 1);
        dataSource.add("group1", "value1", 2, 2);
        dataSource.add("group1", "value2", 3, 3);
        dataSource.add("group1", "value2", 4, 4);
        dataSource.add("group2", "value1", 1, 5);
        dataSource.add("group2", "value1", 2, 6);
        dataSource.add("group2", "value2", 3, 7);
        dataSource.add("group2", "value2", 4, 8);
        return dataSource;
    }

    private static class ValueExpression extends AbstractSimpleExpression<Double> {
        @Serial
        private static final long serialVersionUID = 1L;

        @Override
        public Double evaluate(ReportParameters reportParameters) {
            final double f1 = ((Number) reportParameters.getValue("field3")).doubleValue();
            final double f2 = ((Number) reportParameters.getValue("field4")).doubleValue();
            return f1 + f2;
        }
    }

    private static class TitleExpression extends AbstractSimpleExpression<String> {
        @Serial
        private static final long serialVersionUID = 1L;

        @Override
        public String evaluate(ReportParameters reportParameters) {
            return "Title sum=" + reportParameters.getValue("var1");
        }
    }

    private static class Customizer implements DRIChartCustomizer, Serializable {
        @Serial
        private static final long serialVersionUID = 1L;

        @Override
        public void customize(JFreeChart chart, ReportParameters reportParameters) {
            chart.getTitle().setText(chart.getTitle().getText() + " customizer" + reportParameters.getPageNumber());
        }
    }
}
