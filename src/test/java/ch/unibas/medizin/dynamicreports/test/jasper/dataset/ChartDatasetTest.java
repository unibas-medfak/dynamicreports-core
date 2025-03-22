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
package ch.unibas.medizin.dynamicreports.test.jasper.dataset;

import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.cht;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.col;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.dataset;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.field;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.variable;

import java.io.Serial;
import java.io.Serializable;

import org.jfree.chart.JFreeChart;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder;
import ch.unibas.medizin.dynamicreports.report.base.expression.AbstractSimpleExpression;
import ch.unibas.medizin.dynamicreports.report.builder.DatasetBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.FieldBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.VariableBuilder;
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
public class ChartDatasetTest extends AbstractJasperChartTest implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Override
    protected void configureReport(JasperReportBuilder rb) {
        final FieldBuilder<Integer> field2 = field("field2", Integer.class);

        final DatasetBuilder dataset1 = dataset();
        dataset1.addField(field2);
        dataset1.setDataSource(createDataSource());

        final DatasetBuilder dataset2 = dataset();
        dataset2.addField("field1", String.class);
        dataset2.setDataSource(createDataSource());

        final DatasetBuilder dataset3 = dataset();
        final VariableBuilder<Integer> variable = variable("var1", field2, Calculation.SUM);
        dataset3.variables(variable);
        dataset3.setDataSource(new Datasource3Expression());

        rb.addParameter("parameter", "parameter_value")
          .title(cht.barChart()
                    .setSubDataset(dataset1)
                    .setTitle(new TitleExpression())
                    .setCategory("field1", String.class)
                    .series(cht.serie("field3", Integer.class).setLabel("f3"), cht.serie(new ValueExpression()).setLabel("exp")), cht.barChart()
                                                                                                                                     .setSubDataset(dataset2)
                                                                                                                                     .customizers(new Customizer())
                                                                                                                                     .setCategory(new CategoryExpression())
                                                                                                                                     .series(cht.serie(col.column("Column2", "field2", Integer.class)),
                                                                                                                                             cht.serie("field3", Integer.class).setLabel("f3"),
                                                                                                                                             cht.serie(new ValueExpression()).setLabel("exp")),
                 cht.barChart().setSubDataset(dataset3).setCategory("field1", String.class).series(cht.serie(variable).setLabel("exp")));
    }

    @Override
    @Test
    public void test() {
        super.test();

        numberOfPagesTest(1);

        String[] categories = new String[] {"value1", "value2", "value3", "value4"};
        String[] series = new String[] {"f3", "exp"};
        Number[][] values = new Number[][] {{4d, 6d}, {6d, 10d}, {8d, 14d}, {10d, 18d}};

        chartCountTest("title.chart1", 1);
        chartTitleTest("title.chart1", 0, "Title");
        chartCategoryCountTest("title.chart1", 0, 4);
        chartSeriesCountTest("title.chart1", 0, 2);
        chartDataTest("title.chart1", 0, categories, series, values);

        categories = new String[] {"value1_exp", "value2_exp", "value3_exp", "value4_exp"};
        series = new String[] {"Column2", "f3", "exp"};
        values = new Number[][] {{2d, 4d, 6d}, {4d, 6d, 10d}, {6d, 8d, 14d}, {8d, 10d, 18d}};

        chartCountTest("title.chart2", 1);
        chartTitleTest("title.chart2", 0, "customizer1");
        chartCategoryCountTest("title.chart2", 0, 4);
        chartSeriesCountTest("title.chart2", 0, 3);
        chartDataTest("title.chart2", 0, categories, series, values);

        categories = new String[] {"value1", "value2", "value3", "value4"};
        series = new String[] {"exp"};
        values = new Number[][] {{2}, {6}, {12}, {20}};

        chartCountTest("title.chart3", 1);
        chartTitleTest("title.chart3", 0, null);
        chartCategoryCountTest("title.chart3", 0, 4);
        chartSeriesCountTest("title.chart3", 0, 1);
        chartDataTest("title.chart3", 0, categories, series, values);
    }

    @Override
    protected JRDataSource createDataSource() {
        final DRDataSource dataSource = new DRDataSource("field1", "field2", "field3");
        for (int i = 0; i < 4; i++) {
            dataSource.add("value" + (i + 1), i + 1, i + 2);
            dataSource.add("value" + (i + 1), i + 1, i + 2);
        }
        return dataSource;
    }

    private static class ValueExpression extends AbstractSimpleExpression<Double> {
        @Serial
        private static final long serialVersionUID = 1L;

        @Override
        public Double evaluate(ReportParameters reportParameters) {
            Assertions.assertNotNull(reportParameters.getMasterParameters());
            try {
                reportParameters.getValue("parameter");
                Assertions.fail("parameter is not null");
            } catch (final Exception e) {
            }
            Assertions.assertEquals("parameter_value", reportParameters.getMasterParameters().getValue("parameter"));

            final double f1 = ((Number) reportParameters.getValue("field2")).doubleValue();
            final double f2 = ((Number) reportParameters.getValue("field3")).doubleValue();
            return f1 + f2;
        }
    }

    private static class CategoryExpression extends AbstractSimpleExpression<String> {
        @Serial
        private static final long serialVersionUID = 1L;

        @Override
        public String evaluate(ReportParameters reportParameters) {
            Assertions.assertNotNull(reportParameters.getMasterParameters());
            try {
                reportParameters.getValue("parameter");
                Assertions.fail("parameter is not null");
            } catch (final Exception e) {
            }
            Assertions.assertEquals("parameter_value", reportParameters.getMasterParameters().getValue("parameter"));
            return reportParameters.getValue("field1") + "_exp";
        }
    }

    private static class TitleExpression extends AbstractSimpleExpression<String> {
        @Serial
        private static final long serialVersionUID = 1L;

        @Override
        public String evaluate(ReportParameters reportParameters) {
            Assertions.assertNull(reportParameters.getMasterParameters());
            Assertions.assertEquals("parameter_value", reportParameters.getValue("parameter"));
            return "Title";
        }
    }

    private static class Customizer implements DRIChartCustomizer, Serializable {
        @Serial
        private static final long serialVersionUID = 1L;

        @Override
        public void customize(JFreeChart chart, ReportParameters reportParameters) {
            Assertions.assertNull(reportParameters.getMasterParameters());
            Assertions.assertEquals("parameter_value", reportParameters.getValue("parameter"));
            chart.setTitle("customizer" + reportParameters.getPageNumber());
        }
    }

    private class Datasource3Expression extends AbstractSimpleExpression<JRDataSource> {
        @Serial
        private static final long serialVersionUID = 1L;

        @Override
        public JRDataSource evaluate(ReportParameters reportParameters) {
            Assertions.assertNull(reportParameters.getMasterParameters());
            Assertions.assertEquals("parameter_value", reportParameters.getValue("parameter"));
            return createDataSource();
        }
    }
}
