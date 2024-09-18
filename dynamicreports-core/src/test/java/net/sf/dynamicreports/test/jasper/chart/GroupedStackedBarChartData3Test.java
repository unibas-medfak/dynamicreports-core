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
package net.sf.dynamicreports.test.jasper.chart;

import static net.sf.dynamicreports.report.builder.DynamicReports.cht;
import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.grp;

import java.lang.reflect.Field;
import java.util.List;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.renderer.category.GroupedStackedBarRenderer;
import org.jfree.data.KeyToGroupMap;
import org.junit.jupiter.api.Assertions;

import net.sf.dynamicreports.design.transformation.chartcustomizer.GroupedStackedBarRendererCustomizer;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.group.ColumnGroupBuilder;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.test.jasper.AbstractJasperChartTest;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca
 */
public class GroupedStackedBarChartData3Test extends AbstractJasperChartTest {

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
    public void test() {
        super.test();

        numberOfPagesTest(1);

        final String[] categories = new String[] {"value1"};
        String[] series = new String[] {"group1" + GroupedStackedBarRendererCustomizer.GROUP_SERIES_KEY + "series1", "group2" + GroupedStackedBarRendererCustomizer.GROUP_SERIES_KEY + "series1",
            "group3" + GroupedStackedBarRendererCustomizer.GROUP_SERIES_KEY + "series1"};
        Number[][] values = new Number[][] {{1d, 2d, 3d}};
        chartCountTest("groupFooter.chart1", 2);
        chartCategoryCountTest("groupFooter.chart1", 0, 1);
        chartSeriesCountTest("groupFooter.chart1", 0, 3);
        chartDataTest("groupFooter.chart1", 0, categories, series, values);
        JFreeChart chart = getChart("groupFooter.chart1", 0);
        LegendItemCollection fixedLegendItems = chart.getCategoryPlot().getFixedLegendItems();
         Assertions.assertEquals( "series1", fixedLegendItems.get(0).getLabel(),"series name");
        testMap(chart, "group1", "group2", "group3");

        series = new String[] {"group2" + GroupedStackedBarRendererCustomizer.GROUP_SERIES_KEY + "series1", "group4" + GroupedStackedBarRendererCustomizer.GROUP_SERIES_KEY + "series1",
            "group5" + GroupedStackedBarRendererCustomizer.GROUP_SERIES_KEY + "series1", "group6" + GroupedStackedBarRendererCustomizer.GROUP_SERIES_KEY + "series1"};
        values = new Number[][] {{4d, 5d, 6d, 7d}};
        chartCategoryCountTest("groupFooter.chart1", 1, 1);
        chartSeriesCountTest("groupFooter.chart1", 1, 4);
        chartDataTest("groupFooter.chart1", 1, categories, series, values);
        chart = getChart("groupFooter.chart1", 1);
        fixedLegendItems = chart.getCategoryPlot().getFixedLegendItems();
         Assertions.assertEquals( "series1", fixedLegendItems.get(0).getLabel(),"series name");
        testMap(chart, "group2", "group4", "group5", "group6");
    }

    private void testMap(JFreeChart chart, String... groups) {
        final GroupedStackedBarRenderer renderer = (GroupedStackedBarRenderer) chart.getCategoryPlot().getRenderer();
        try {
            final Field field = renderer.getClass().getDeclaredField("seriesToGroupMap");
            field.setAccessible(true);
            final KeyToGroupMap map = (KeyToGroupMap) field.get(renderer);
             Assertions.assertEquals( groups.length, map.getGroupCount(),"map");
            final List<?> groups2 = map.getGroups();
            for (int i = 0; i < groups2.size(); i++) {
                 Assertions.assertEquals( groups[i], groups2.get(i),"map");
            }
        } catch (final Exception e) {
            e.printStackTrace();
            Assertions.fail(e.getMessage());
        }
    }

    @Override
    protected JRDataSource createDataSource() {
        final DRDataSource dataSource = new DRDataSource("field1", "field2", "field3", "field4", "field5");
        dataSource.add("a", "value1", "series1", "group1", 1);
        dataSource.add("a", "value1", "series1", "group2", 2);
        dataSource.add("a", "value1", "series1", "group3", 3);
        dataSource.add("b", "value1", "series1", "group2", 4);
        dataSource.add("b", "value1", "series1", "group4", 5);
        dataSource.add("b", "value1", "series1", "group5", 6);
        dataSource.add("b", "value1", "series1", "group6", 7);
        return dataSource;
    }
}
