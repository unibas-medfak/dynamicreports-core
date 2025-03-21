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
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.field;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.type;

import java.io.Serial;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.FieldBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.chart.BarChartBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.chart.LineChartBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.chart.TimeSeriesChartBuilder;
import ch.unibas.medizin.dynamicreports.report.constant.TimePeriod;
import ch.unibas.medizin.dynamicreports.report.datasource.DRDataSource;
import ch.unibas.medizin.dynamicreports.report.definition.ReportParameters;
import ch.unibas.medizin.dynamicreports.report.definition.chart.DRIChartCustomizer;
import ch.unibas.medizin.dynamicreports.test.jasper.AbstractJasperChartTest;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MultiAxisChartScriptletTest extends AbstractJasperChartTest implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Override
    protected void configureReport(JasperReportBuilder rb) {
        final FieldBuilder<Date> field1 = field("field1", type.dateType());
        final FieldBuilder<Integer> field2 = field("field2", type.integerType());
        final FieldBuilder<Integer> field3 = field("field3", type.integerType());
        final FieldBuilder<String> field4 = field("field4", type.stringType());

        final TimeSeriesChartBuilder chart1 = cht.timeSeriesChart().customizers(new Customizer1()).setTimePeriod(field1).setTimePeriodType(TimePeriod.DAY).series(cht.serie(field2).setLabel("serie1"));

        final TimeSeriesChartBuilder chart2 = cht.timeSeriesChart().setTimePeriod(field1).setTimePeriodType(TimePeriod.DAY).series(cht.serie(field3).setLabel("serie2"));

        final BarChartBuilder chart3 = cht.barChart().customizers(new Customizer3()).setCategory(field4).series(cht.serie(field2));

        final LineChartBuilder chart4 = cht.lineChart().customizers(new Customizer4()).setCategory(field4).series(cht.serie(field3));

        rb.summary(cht.multiAxisChart(chart1, chart2).customizers(new Customizer2()), cht.multiAxisChart(chart3, chart4).customizers(new Customizer5()));
    }

    @Override
    @Test
    public void test() {
        super.test();

        numberOfPagesTest(1);

    }

    @Override
    protected JRDataSource createDataSource() {
        final DRDataSource dataSource = new DRDataSource("field1", "field2", "field3", "field4");
        final Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        for (int i = 0; i < 4; i++) {
            dataSource.add(c.getTime(), i + 1, i * i, "value" + i);
            c.add(Calendar.DAY_OF_MONTH, 1);
        }
        return dataSource;
    }

    private static class Customizer1 implements DRIChartCustomizer, Serializable {
        @Serial
        private static final long serialVersionUID = 1L;

        @Override
        public void customize(JFreeChart chart, ReportParameters reportParameters) {
            final XYItemRenderer renderer = chart.getXYPlot().getRenderer();
             Assertions.assertEquals( XYLineAndShapeRenderer.class, renderer.getClass(),"renderer");
        }
    }

    private static class Customizer2 implements DRIChartCustomizer, Serializable {
        @Serial
        private static final long serialVersionUID = 1L;

        @Override
        public void customize(JFreeChart chart, ReportParameters reportParameters) {
            XYItemRenderer renderer = chart.getXYPlot().getRenderer(0);
             Assertions.assertEquals( XYLineAndShapeRenderer.class, renderer.getClass(),"renderer");
            renderer = chart.getXYPlot().getRenderer(1);
             Assertions.assertEquals( XYLineAndShapeRenderer.class, renderer.getClass(),"renderer");
        }
    }

    private static class Customizer3 implements DRIChartCustomizer, Serializable {
        @Serial
        private static final long serialVersionUID = 1L;

        @Override
        public void customize(JFreeChart chart, ReportParameters reportParameters) {
            final CategoryItemRenderer renderer = chart.getCategoryPlot().getRenderer();
             Assertions.assertEquals( BarRenderer.class, renderer.getClass(),"renderer");
        }
    }

    private static class Customizer4 implements DRIChartCustomizer, Serializable {
        @Serial
        private static final long serialVersionUID = 1L;

        @Override
        public void customize(JFreeChart chart, ReportParameters reportParameters) {
            final CategoryItemRenderer renderer = chart.getCategoryPlot().getRenderer();
             Assertions.assertEquals( LineAndShapeRenderer.class, renderer.getClass(),"renderer");
        }
    }

    private static class Customizer5 implements DRIChartCustomizer, Serializable {
        @Serial
        private static final long serialVersionUID = 1L;

        @Override
        public void customize(JFreeChart chart, ReportParameters reportParameters) {
            CategoryItemRenderer renderer = chart.getCategoryPlot().getRenderer(0);
             Assertions.assertEquals( BarRenderer.class, renderer.getClass(),"renderer");
            renderer = chart.getCategoryPlot().getRenderer(1);
             Assertions.assertEquals( LineAndShapeRenderer.class, renderer.getClass(),"renderer");
        }
    }

}
