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

import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.CategoryDataset;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.FieldBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.chart.BarChartBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.chart.LineChartBuilder;
import ch.unibas.medizin.dynamicreports.report.datasource.DRDataSource;
import ch.unibas.medizin.dynamicreports.test.jasper.AbstractJasperChartTest;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * Reproduces https://github.com/unibas-medfak/dynamicreports-core/issues/149:
 * a {@link ch.unibas.medizin.dynamicreports.report.builder.chart.MultiAxisChartBuilder} combining
 * a bar chart and a line chart doubles all values except the first key value.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MultiAxisChartBarLineTest extends AbstractJasperChartTest implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Override
    protected void configureReport(JasperReportBuilder rb) {
        final FieldBuilder<String> category = field("category", type.stringType());
        final FieldBuilder<Integer> valueBar = field("valueBar", type.integerType());
        final FieldBuilder<Integer> valueLine = field("valueLine", type.integerType());

        final BarChartBuilder barChart = cht.barChart().setCategory(category).series(cht.serie(valueBar).setLabel("valueBar"));
        final LineChartBuilder lineChart = cht.lineChart().setCategory(category).series(cht.serie(valueLine).setLabel("valueLine"));

        rb.summary(cht.multiAxisChart(barChart, lineChart));
    }

    @Override
    @Test
    public void test() {
        super.test();

        numberOfPagesTest(1);

        final JFreeChart chart = getChart("summary.chart1", 0);
        final CategoryPlot plot = chart.getCategoryPlot();

        final CategoryDataset barDataset = plot.getDataset(0);
        Assertions.assertEquals(1d, barDataset.getValue("valueBar", "A"), "bar value A");
        Assertions.assertEquals(2d, barDataset.getValue("valueBar", "B"), "bar value B");
        Assertions.assertEquals(3d, barDataset.getValue("valueBar", "C"), "bar value C");

        final CategoryDataset lineDataset = plot.getDataset(1);
        Assertions.assertEquals(2d, lineDataset.getValue("valueLine", "A"), "line value A");
        Assertions.assertEquals(3d, lineDataset.getValue("valueLine", "B"), "line value B");
        Assertions.assertEquals(4d, lineDataset.getValue("valueLine", "C"), "line value C");
    }

    @Override
    protected JRDataSource createDataSource() {
        final DRDataSource dataSource = new DRDataSource("category", "valueBar", "valueLine");
        dataSource.add("A", 1, 2);
        dataSource.add("B", 2, 3);
        dataSource.add("C", 3, 4);
        return dataSource;
    }
}
