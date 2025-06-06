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
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.cmp;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.col;

import java.awt.Color;
import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.LegendItem;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.CategoryDataset;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import ch.unibas.medizin.dynamicreports.design.transformation.chartcustomizer.GroupedStackedBarRendererCustomizer;
import ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.column.TextColumnBuilder;
import ch.unibas.medizin.dynamicreports.report.constant.OrderType;
import ch.unibas.medizin.dynamicreports.report.constant.PageOrientation;
import ch.unibas.medizin.dynamicreports.report.constant.PageType;
import ch.unibas.medizin.dynamicreports.report.datasource.DRDataSource;
import ch.unibas.medizin.dynamicreports.test.jasper.AbstractJasperChartTest;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ChartSeriesOrderTest extends AbstractJasperChartTest implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Map<String, Color> colors;

    @Override
    protected void configureReport(JasperReportBuilder rb) {
        TextColumnBuilder<String> column1;
        TextColumnBuilder<String> column2;
        TextColumnBuilder<Integer> column3;
        TextColumnBuilder<String> column4;

        colors = new HashMap<>();
        colors.put("a", Color.BLUE);
        colors.put("b", Color.YELLOW);
        colors.put("c", Color.GREEN);
        colors.put("d", Color.MAGENTA);

        rb.setPageFormat(PageType.A3, PageOrientation.PORTRAIT)
          .columns(column1 = col.column("Column1", "field1", String.class), column2 = col.column("Column2", "field2", String.class), column3 = col.column("Column3", "field3", Integer.class),
                   column4 = col.column("Column4", "field4", String.class))
          .summary(cmp.horizontalList(cht.barChart().seriesColorsByName(colors).setSeriesOrderType(OrderType.ASCENDING).setCategory(column1).series(cht.serie(column3).setSeries(column2)),
                                      cht.groupedStackedBarChart()
                                         .seriesColorsByName(colors)
                                         .setSeriesOrderType(OrderType.ASCENDING)
                                         .setCategory(column1)
                                         .series(cht.groupedSerie(column3).setSeries(column2).setGroup(column4))),
                   cmp.horizontalList(cht.barChart().seriesColorsByName(colors).setSeriesOrderType(OrderType.DESCENDING).setCategory(column1).series(cht.serie(column3).setSeries(column2)),
                                      cht.groupedStackedBarChart()
                                         .seriesColorsByName(colors)
                                         .setSeriesOrderType(OrderType.DESCENDING)
                                         .setCategory(column1)
                                         .series(cht.groupedSerie(column3).setSeries(column2).setGroup(column4))),
                   cmp.horizontalList(cht.barChart().seriesColorsByName(colors).setSeriesOrderBy(Arrays.asList("c", "b", "a", "d")).setCategory(column1).series(cht.serie(column3).setSeries(column2)),
                                      cht.groupedStackedBarChart()
                                         .seriesColorsByName(colors)
                                         .setSeriesOrderBy(Arrays.asList("c", "b", "a", "d"))
                                         .setCategory(column1)
                                         .series(cht.groupedSerie(column3).setSeries(column2).setGroup(column4))), cmp.horizontalList(cht.barChart()
                                                                                                                                         .seriesColorsByName(colors)
                                                                                                                                         .seriesOrderBy("c", "b", "a", "d")
                                                                                                                                         .setSeriesOrderType(OrderType.DESCENDING)
                                                                                                                                         .setCategory(column1)
                                                                                                                                         .series(cht.serie(column3).setSeries(column2)),
                                                                                                                                      cht.groupedStackedBarChart()
                                                                                                                                         .seriesColorsByName(colors)
                                                                                                                                         .seriesOrderBy("c", "b", "a", "d")
                                                                                                                                         .setSeriesOrderType(OrderType.DESCENDING)
                                                                                                                                         .setCategory(column1)
                                                                                                                                         .series(cht.groupedSerie(column3)
                                                                                                                                                    .setSeries(column2)
                                                                                                                                                    .setGroup(column4))),
                   cmp.horizontalList(cht.barChart().seriesColorsByName(colors).seriesOrderBy("c", "e", "d").setCategory(column1).series(cht.serie(column3).setSeries(column2)), cht.barChart()
                                                                                                                                                                                    .seriesColorsByName(
                                                                                                                                                                                        colors)
                                                                                                                                                                                    .seriesOrderBy("g",
                                                                                                                                                                                                   "e",
                                                                                                                                                                                                   "d")
                                                                                                                                                                                    .setSeriesOrderType(
                                                                                                                                                                                        OrderType
                                                                                                                                                                                            .DESCENDING)
                                                                                                                                                                                    .setCategory(
                                                                                                                                                                                        column1)
                                                                                                                                                                                    .series(cht.serie(
                                                                                                                                                                                        column3)
                                                                                                                                                                                               .setSeries(
                                                                                                                                                                                                   column2))));
    }

    @Override
    @Test
    public void test() {
        super.test();

        numberOfPagesTest(1);

        chartCountTest("summary.chart1", 1);
        JFreeChart chart = getChart("summary.chart1", 0);
        CategoryItemRenderer renderer1 = chart.getCategoryPlot().getRenderer();
        CategoryDataset dataset1 = chart.getCategoryPlot().getDataset();
        for (int i = 0; i < dataset1.getRowCount(); i++) {
            final String key = (String) dataset1.getRowKey(i);
             Assertions.assertNotNull( colors.get(key),"null series color");
             Assertions.assertEquals( colors.get(key), renderer1.getSeriesPaint(i),"series color");
        }

        String[] categories = new String[] {"value1", "value2"};
        String[] series = new String[] {"a", "b", "c", "d"};
        Number[][] values = new Number[][] {{5d, 2d, 4d, 2d}, {3d, null, 2d, 4d}};

        chartCategoryCountTest("summary.chart1", 0, 2);
        chartSeriesCountTest("summary.chart1", 0, 4);
        chartDataTest("summary.chart1", 0, categories, series, values);

        chartCountTest("summary.chart2", 1);
        chart = getChart("summary.chart2", 0);
        CategoryItemRenderer renderer2 = chart.getCategoryPlot().getRenderer();
        CategoryDataset dataset2 = chart.getCategoryPlot().getDataset();
        for (int i = 0; i < dataset2.getRowCount(); i++) {
            String key = (String) dataset2.getRowKey(i);
            key = StringUtils.substringAfter(key, GroupedStackedBarRendererCustomizer.GROUP_SERIES_KEY);
             Assertions.assertNotNull( colors.get(key),"null series color");
             Assertions.assertEquals( colors.get(key), renderer2.getSeriesPaint(i),"series color");
        }
        for (
            int i = 0; i < chart.getCategoryPlot().getFixedLegendItems().getItemCount(); i++) {
            final LegendItem legendItem = chart.getCategoryPlot().getFixedLegendItems().get(i);
             Assertions.assertNotNull( colors.get(legendItem.getLabel()),"null series color");
             Assertions.assertEquals( colors.get(legendItem.getLabel()), legendItem.getFillPaint(),"series color");
        }

        series = new String[] {"1" + GroupedStackedBarRendererCustomizer.GROUP_SERIES_KEY + "a", "1" + GroupedStackedBarRendererCustomizer.GROUP_SERIES_KEY + "b",
            "1" + GroupedStackedBarRendererCustomizer.GROUP_SERIES_KEY + "c", "1" + GroupedStackedBarRendererCustomizer.GROUP_SERIES_KEY + "d",
            "2" + GroupedStackedBarRendererCustomizer.GROUP_SERIES_KEY + "a", "2" + GroupedStackedBarRendererCustomizer.GROUP_SERIES_KEY + "b",
            "2" + GroupedStackedBarRendererCustomizer.GROUP_SERIES_KEY + "c", "2" + GroupedStackedBarRendererCustomizer.GROUP_SERIES_KEY + "d"};
        values = new Number[][] {{1d, 2d, 3d, 0d, 4d, 0d, 1d, 2d}, {null, null, null, 0d, 3d, 0d, 2d, 4d}};
        chartCategoryCountTest("summary.chart2", 0, 2);
        chartSeriesCountTest("summary.chart2", 0, 8);
        chartDataTest("summary.chart2", 0, categories, series, values);

        chartCountTest("summary.chart3", 1);
        chart = getChart("summary.chart3", 0);
        renderer1 = chart.getCategoryPlot().getRenderer();
        dataset1 = chart.getCategoryPlot().getDataset();
        for (int i = 0; i < dataset1.getRowCount(); i++) {
            final String key = (String) dataset1.getRowKey(i);
             Assertions.assertNotNull( colors.get(key),"null series color");
             Assertions.assertEquals( colors.get(key), renderer1.getSeriesPaint(i),"series color");
        }

        categories = new String[] {"value1", "value2"};
        series = new String[] {"d", "c", "b", "a"};
        values = new Number[][] {{2d, 4d, 2d, 5d}, {4d, 2d, null, 3d}};

        chartCategoryCountTest("summary.chart3", 0, 2);
        chartSeriesCountTest("summary.chart3", 0, 4);
        chartDataTest("summary.chart3", 0, categories, series, values);

        chartCountTest("summary.chart4", 1);
        chart = getChart("summary.chart4", 0);
        renderer2 = chart.getCategoryPlot().getRenderer();
        dataset2 = chart.getCategoryPlot().getDataset();
        for (int i = 0; i < dataset2.getRowCount(); i++) {
            String key = (String) dataset2.getRowKey(i);
            key = StringUtils.substringAfter(key, GroupedStackedBarRendererCustomizer.GROUP_SERIES_KEY);
             Assertions.assertNotNull( colors.get(key),"null series color");
             Assertions.assertEquals( colors.get(key), renderer2.getSeriesPaint(i),"series color");
        }
        for (
            int i = 0; i < chart.getCategoryPlot().getFixedLegendItems().getItemCount(); i++) {
            final LegendItem legendItem = chart.getCategoryPlot().getFixedLegendItems().get(i);
             Assertions.assertNotNull( colors.get(legendItem.getLabel()),"null series color");
             Assertions.assertEquals( colors.get(legendItem.getLabel()), legendItem.getFillPaint(),"series color");
        }

        series = new String[] {"2" + GroupedStackedBarRendererCustomizer.GROUP_SERIES_KEY + "d", "2" + GroupedStackedBarRendererCustomizer.GROUP_SERIES_KEY + "c",
            "2" + GroupedStackedBarRendererCustomizer.GROUP_SERIES_KEY + "b", "2" + GroupedStackedBarRendererCustomizer.GROUP_SERIES_KEY + "a",
            "1" + GroupedStackedBarRendererCustomizer.GROUP_SERIES_KEY + "d", "1" + GroupedStackedBarRendererCustomizer.GROUP_SERIES_KEY + "c",
            "1" + GroupedStackedBarRendererCustomizer.GROUP_SERIES_KEY + "b", "1" + GroupedStackedBarRendererCustomizer.GROUP_SERIES_KEY + "a"};

        values = new Number[][] {{2d, 1d, 0d, 4d, 0d, 3d, 2d, 1d}, {4d, 2d, 0d, 3d, 0d, null, null, null}};
        chartCategoryCountTest("summary.chart4", 0, 2);
        chartSeriesCountTest("summary.chart4", 0, 8);
        chartDataTest("summary.chart4", 0, categories, series, values);

        chartCountTest("summary.chart5", 1);
        chart = getChart("summary.chart5", 0);
        renderer1 = chart.getCategoryPlot().getRenderer();
        dataset1 = chart.getCategoryPlot().getDataset();
        for (int i = 0; i < dataset1.getRowCount(); i++) {
            final String key = (String) dataset1.getRowKey(i);
             Assertions.assertNotNull( colors.get(key),"null series color");
             Assertions.assertEquals( colors.get(key), renderer1.getSeriesPaint(i),"series color");
        }

        categories = new String[] {"value1", "value2"};
        series = new String[] {"c", "b", "a", "d"};
        values = new Number[][] {{4d, 2d, 5d, 2d}, {2d, null, 3d, 4d}};

        chartCategoryCountTest("summary.chart5", 0, 2);
        chartSeriesCountTest("summary.chart5", 0, 4);
        chartDataTest("summary.chart5", 0, categories, series, values);

        chartCountTest("summary.chart6", 1);
        chart = getChart("summary.chart6", 0);
        renderer2 = chart.getCategoryPlot().getRenderer();
        dataset2 = chart.getCategoryPlot().getDataset();
        for (int i = 0; i < dataset2.getRowCount(); i++) {
            String key = (String) dataset2.getRowKey(i);
            key = StringUtils.substringAfter(key, GroupedStackedBarRendererCustomizer.GROUP_SERIES_KEY);
             Assertions.assertNotNull( colors.get(key),"null series color");
             Assertions.assertEquals( colors.get(key), renderer2.getSeriesPaint(i),"series color");
        }
        for (
            int i = 0; i < chart.getCategoryPlot().getFixedLegendItems().getItemCount(); i++) {
            final LegendItem legendItem = chart.getCategoryPlot().getFixedLegendItems().get(i);
             Assertions.assertNotNull( colors.get(legendItem.getLabel()),"null series color");
             Assertions.assertEquals( colors.get(legendItem.getLabel()), legendItem.getFillPaint(),"series color");
        }

        series = new String[] {"1" + GroupedStackedBarRendererCustomizer.GROUP_SERIES_KEY + "c", "1" + GroupedStackedBarRendererCustomizer.GROUP_SERIES_KEY + "b",
            "1" + GroupedStackedBarRendererCustomizer.GROUP_SERIES_KEY + "a", "1" + GroupedStackedBarRendererCustomizer.GROUP_SERIES_KEY + "d",
            "2" + GroupedStackedBarRendererCustomizer.GROUP_SERIES_KEY + "c", "2" + GroupedStackedBarRendererCustomizer.GROUP_SERIES_KEY + "b",
            "2" + GroupedStackedBarRendererCustomizer.GROUP_SERIES_KEY + "a", "2" + GroupedStackedBarRendererCustomizer.GROUP_SERIES_KEY + "d"};
        values = new Number[][] {{3d, 2d, 1d, 0d, 1d, 0d, 4d, 2d}, {null, null, null, 0d, 2d, 0d, 3d, 4d}};
        chartCategoryCountTest("summary.chart6", 0, 2);
        chartSeriesCountTest("summary.chart6", 0, 8);
        chartDataTest("summary.chart6", 0, categories, series, values);

        chartCountTest("summary.chart7", 1);
        chart = getChart("summary.chart7", 0);
        renderer1 = chart.getCategoryPlot().getRenderer();
        dataset1 = chart.getCategoryPlot().getDataset();
        for (int i = 0; i < dataset1.getRowCount(); i++) {
            final String key = (String) dataset1.getRowKey(i);
             Assertions.assertNotNull( colors.get(key),"null series color");
             Assertions.assertEquals( colors.get(key), renderer1.getSeriesPaint(i),"series color");
        }

        categories = new String[] {"value1", "value2"};
        series = new String[] {"d", "c", "b", "a"};
        values = new Number[][] {{2d, 5d, 2d, 4d}, {4d, 3d, null, 2d}};

        chartCategoryCountTest("summary.chart7", 0, 2);
        chartSeriesCountTest("summary.chart7", 0, 4);
        chartDataTest("summary.chart7", 0, categories, series, values);

        chartCountTest("summary.chart8", 1);
        chart = getChart("summary.chart8", 0);
        renderer2 = chart.getCategoryPlot().getRenderer();
        dataset2 = chart.getCategoryPlot().getDataset();
        for (int i = 0; i < dataset2.getRowCount(); i++) {
            String key = (String) dataset2.getRowKey(i);
            key = StringUtils.substringAfter(key, GroupedStackedBarRendererCustomizer.GROUP_SERIES_KEY);
             Assertions.assertNotNull( colors.get(key),"null series color");
             Assertions.assertEquals( colors.get(key), renderer2.getSeriesPaint(i),"series color");
        }
        for (
            int i = 0; i < chart.getCategoryPlot().getFixedLegendItems().getItemCount(); i++) {
            final LegendItem legendItem = chart.getCategoryPlot().getFixedLegendItems().get(i);
             Assertions.assertNotNull( colors.get(legendItem.getLabel()),"null series color");
             Assertions.assertEquals( colors.get(legendItem.getLabel()), legendItem.getFillPaint(),"series color");
        }

        series = new String[] {"2" + GroupedStackedBarRendererCustomizer.GROUP_SERIES_KEY + "d", "2" + GroupedStackedBarRendererCustomizer.GROUP_SERIES_KEY + "a",
            "2" + GroupedStackedBarRendererCustomizer.GROUP_SERIES_KEY + "b", "2" + GroupedStackedBarRendererCustomizer.GROUP_SERIES_KEY + "c",
            "1" + GroupedStackedBarRendererCustomizer.GROUP_SERIES_KEY + "d", "1" + GroupedStackedBarRendererCustomizer.GROUP_SERIES_KEY + "a",
            "1" + GroupedStackedBarRendererCustomizer.GROUP_SERIES_KEY + "b", "1" + GroupedStackedBarRendererCustomizer.GROUP_SERIES_KEY + "c"};

        values = new Number[][] {{2d, 4d, 0d, 1d, 0d, 1d, 2d, 3d}, {4d, 3d, 0d, 2d, 0d, null, null, null}};
        chartCategoryCountTest("summary.chart8", 0, 2);
        chartSeriesCountTest("summary.chart8", 0, 8);
        chartDataTest("summary.chart8", 0, categories, series, values);

        chartCountTest("summary.chart9", 1);
        chart = getChart("summary.chart9", 0);
        renderer1 = chart.getCategoryPlot().getRenderer();
        dataset1 = chart.getCategoryPlot().getDataset();
        for (int i = 0; i < dataset1.getRowCount(); i++) {
            final String key = (String) dataset1.getRowKey(i);
             Assertions.assertNotNull( colors.get(key),"null series color");
             Assertions.assertEquals( colors.get(key), renderer1.getSeriesPaint(i),"series color");
        }

        categories = new String[] {"value1", "value2"};
        series = new String[] {"c", "d", "a", "b"};
        values = new Number[][] {{4d, 2d, 5d, 2d}, {2d, 4d, 3d, null}};

        chartCategoryCountTest("summary.chart9", 0, 2);
        chartSeriesCountTest("summary.chart9", 0, 4);
        chartDataTest("summary.chart9", 0, categories, series, values);

        chartCountTest("summary.chart10", 1);
        chart = getChart("summary.chart10", 0);
        renderer1 = chart.getCategoryPlot().getRenderer();
        dataset1 = chart.getCategoryPlot().getDataset();
        for (int i = 0; i < dataset1.getRowCount(); i++) {
            final String key = (String) dataset1.getRowKey(i);
             Assertions.assertNotNull( colors.get(key),"null series color");
             Assertions.assertEquals( colors.get(key), renderer1.getSeriesPaint(i),"series color");
        }

        categories = new String[] {"value1", "value2"};
        series = new String[] {"c", "b", "a", "d"};
        values = new Number[][] {{4d, 2d, 5d, 2d}, {2d, null, 3d, 4d}};

        chartCategoryCountTest("summary.chart10", 0, 2);
        chartSeriesCountTest("summary.chart10", 0, 4);
        chartDataTest("summary.chart10", 0, categories, series, values);
    }

    @Override
    protected void chartDataTest(String name, int index, String[] categories, String[] series, Number[][] values) {
        final CategoryDataset dataset = getChart(name, index).getCategoryPlot().getDataset();
        for (int i = 0; i < categories.length; i++) {
            for (int j = 0; j < series.length; j++) {
                 Assertions.assertEquals( values[i][j], dataset.getValue(j, i),"chart data");
            }
        }
    }

    @Override
    protected JRDataSource createDataSource() {
        final DRDataSource dataSource = new DRDataSource("field1", "field2", "field3", "field4");
        dataSource.add("value1", "b", 2, "1");
        dataSource.add("value1", "a", 1, "1");
        dataSource.add("value1", "c", 3, "1");
        dataSource.add("value1", "c", 1, "2");
        dataSource.add("value1", "d", 2, "2");
        dataSource.add("value1", "a", 4, "2");
        dataSource.add("value2", "c", 2, "2");
        dataSource.add("value2", "d", 4, "2");
        dataSource.add("value2", "a", 3, "2");
        return dataSource;
    }
}
