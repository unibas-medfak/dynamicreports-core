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

import org.jfree.chart.JFreeChart;
import org.jfree.chart.LegendItemCollection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import ch.unibas.medizin.dynamicreports.design.transformation.chartcustomizer.GroupedStackedBarRendererCustomizer;
import ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.column.TextColumnBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.group.ColumnGroupBuilder;
import ch.unibas.medizin.dynamicreports.report.datasource.DRDataSource;
import ch.unibas.medizin.dynamicreports.test.jasper.AbstractJasperChartTest;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GroupedStackedBarChartData2Test extends AbstractJasperChartTest {

    @Override
    protected void configureReport(JasperReportBuilder rb) {
        final TextColumnBuilder<String> column1 = col.column("Column1", "field1", String.class);
        final TextColumnBuilder<String> column2 = col.column("Column2", "field2", String.class);
        final TextColumnBuilder<String> column3 = col.column("Column3", "field3", String.class);
        final TextColumnBuilder<String> column4 = col.column("Column4", "field4", String.class);
        final TextColumnBuilder<Integer> column5 = col.column("Column5", "field5", Integer.class);

        final ColumnGroupBuilder group = grp.group(column1).footer(cht.groupedStackedBarChart().setCategory(column2).series(cht.groupedSerie(column5).setSeries(column3).setGroup(column4)));

        rb.columns(column1, column2, column3, column4, column5).groupBy(group);
    }

    @Override
    @Test
    public void test() {
        super.test();

        numberOfPagesTest(1);

        final String[] categories = new String[] {"value1"};
        String[] series = new String[] {"group1" + GroupedStackedBarRendererCustomizer.GROUP_SERIES_KEY + "series1", "group1" + GroupedStackedBarRendererCustomizer.GROUP_SERIES_KEY + "series3",
            "group1" + GroupedStackedBarRendererCustomizer.GROUP_SERIES_KEY + "series4"};
        Number[][] values = new Number[][] {{1d, 2d, 3d}};
        chartCountTest("groupFooter.chart1", 2);
        chartCategoryCountTest("groupFooter.chart1", 0, 1);
        chartSeriesCountTest("groupFooter.chart1", 0, 3);
        chartDataTest("groupFooter.chart1", 0, categories, series, values);
        JFreeChart chart = getChart("groupFooter.chart1", 0);
        LegendItemCollection fixedLegendItems = chart.getCategoryPlot().getFixedLegendItems();
        Assertions.assertEquals( "series1", fixedLegendItems.get(0).getLabel(), "series name");
        Assertions.assertEquals( "series3", fixedLegendItems.get(1).getLabel(),"series name");
        Assertions.assertEquals( "series4", fixedLegendItems.get(2).getLabel(), "series name");

        series = new String[] {"group1" + GroupedStackedBarRendererCustomizer.GROUP_SERIES_KEY + "series1", "group1" + GroupedStackedBarRendererCustomizer.GROUP_SERIES_KEY + "series2",
            "group1" + GroupedStackedBarRendererCustomizer.GROUP_SERIES_KEY + "series3", "group1" + GroupedStackedBarRendererCustomizer.GROUP_SERIES_KEY + "series4"};
        values = new Number[][] {{4d, 5d, 6d, 7d}};
        chartCategoryCountTest("groupFooter.chart1", 1, 1);
        chartSeriesCountTest("groupFooter.chart1", 1, 4);
        chartDataTest("groupFooter.chart1", 1, categories, series, values);
        chart = getChart("groupFooter.chart1", 1);
        fixedLegendItems = chart.getCategoryPlot().getFixedLegendItems();
        Assertions.assertEquals( "series1", fixedLegendItems.get(0).getLabel(),"series name");
        Assertions.assertEquals( "series2", fixedLegendItems.get(1).getLabel(), "series name");
        Assertions.assertEquals( "series3", fixedLegendItems.get(2).getLabel(), "series name");
        Assertions.assertEquals("series4", fixedLegendItems.get(3).getLabel(), "series name");
    }

    @Override
    protected JRDataSource createDataSource() {
        final DRDataSource dataSource = new DRDataSource("field1", "field2", "field3", "field4", "field5");
        dataSource.add("a", "value1", "series1", "group1", 1);
        dataSource.add("a", "value1", "series3", "group1", 2);
        dataSource.add("a", "value1", "series4", "group1", 3);
        dataSource.add("b", "value1", "series1", "group1", 4);
        dataSource.add("b", "value1", "series2", "group1", 5);
        dataSource.add("b", "value1", "series3", "group1", 6);
        dataSource.add("b", "value1", "series4", "group1", 7);
        return dataSource;
    }
}
