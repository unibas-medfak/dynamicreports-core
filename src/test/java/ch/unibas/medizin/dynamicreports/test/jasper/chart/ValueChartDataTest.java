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
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.template;

import java.io.Serial;
import java.io.Serializable;
import java.util.Locale;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.column.TextColumnBuilder;
import ch.unibas.medizin.dynamicreports.report.constant.PageOrientation;
import ch.unibas.medizin.dynamicreports.report.constant.PageType;
import ch.unibas.medizin.dynamicreports.report.datasource.DRDataSource;
import ch.unibas.medizin.dynamicreports.test.jasper.AbstractJasperChartTest;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ValueChartDataTest extends AbstractJasperChartTest implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Override
    protected void configureReport(JasperReportBuilder rb) {
        TextColumnBuilder<String> column1;
        TextColumnBuilder<Integer> column2;
        TextColumnBuilder<Integer> column3;

        Locale.setDefault(Locale.ENGLISH);

        rb.addProperty("net.sf.jasperreports.chart.pie.ignore.duplicated.key", "true")
          .setPageFormat(PageType.A2, PageOrientation.PORTRAIT)
          .setTemplate(template().setChartValuePattern("#,##0.#").setChartPercentValuePattern("#,##0.###"))
          .columns(column1 = col.column("Column1", "field1", String.class), column2 = col.column("Column2", "field2", Integer.class), column3 = col.column("Column3", "field3", Integer.class))
          .summary(
              cmp.horizontalList(cht.barChart().setShowValues(true).setShowPercentages(true).setPercentValuePattern("#,##0.##").setCategory(column1).series(cht.serie(column2), cht.serie(column3)),
                                 cht.bar3DChart().setShowValues(true).setShowPercentages(true).setCategory(column1).series(cht.serie(column2), cht.serie(column3)),
                                 cht.barChart().setShowValues(true).setValuePattern("#,##0.##").setCategory(column1).series(cht.serie(column2), cht.serie(column3)),
                                 cht.bar3DChart().setShowValues(true).setCategory(column1).series(cht.serie(column2), cht.serie(column3))),
              cmp.horizontalList(cht.pieChart().setShowValues(true).setValuePattern("#,##0.##").setShowPercentages(true).setPercentValuePattern("#,##0.##").setKey(column1).series(cht.serie(column2)),
                                 cht.pieChart().setShowValues(true).setShowPercentages(true).setKey(column1).series(cht.serie(column2))));
    }

    @Override
    @Test
    public void test() {
        super.test();

        numberOfPagesTest(1);

        final DefaultCategoryDataset categoryDataset = new DefaultCategoryDataset();
        categoryDataset.addValue(1.191, "row", "column");

        JFreeChart chart = getChart("summary.chart1", 0);
        CategoryItemRenderer renderer1 = chart.getCategoryPlot().getRenderer();
        Assertions.assertNotNull(renderer1.getDefaultItemLabelGenerator());
        Assertions.assertEquals("1.19", renderer1.getDefaultItemLabelGenerator().generateLabel(categoryDataset, 0, 0));

        chart = getChart("summary.chart2", 0);
        renderer1 = chart.getCategoryPlot().getRenderer();
        Assertions.assertNotNull(renderer1.getDefaultItemLabelGenerator());
        Assertions.assertEquals("1.191", renderer1.getDefaultItemLabelGenerator().generateLabel(categoryDataset, 0, 0));

        chart = getChart("summary.chart3", 0);
        renderer1 = chart.getCategoryPlot().getRenderer();
        Assertions.assertNotNull(renderer1.getDefaultItemLabelGenerator());
        Assertions.assertEquals("1.19", renderer1.getDefaultItemLabelGenerator().generateLabel(categoryDataset, 0, 0));

        chart = getChart("summary.chart4", 0);
        renderer1 = chart.getCategoryPlot().getRenderer();
        Assertions.assertNotNull(renderer1.getDefaultItemLabelGenerator());
        Assertions.assertEquals("1.2", renderer1.getDefaultItemLabelGenerator().generateLabel(categoryDataset, 0, 0));

        final DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("key1", 1.191);
        dataset.setValue("key2", 122);

        chart = getChart("summary.chart5", 0);
        StandardPieSectionLabelGenerator labelGenerator = (StandardPieSectionLabelGenerator) ((PiePlot) chart.getPlot()).getLabelGenerator();
        Assertions.assertEquals("key1 = 1.19 (0.97%)", labelGenerator.generateSectionLabel(dataset, "key1"));

        chart = getChart("summary.chart6", 0);
        labelGenerator = (StandardPieSectionLabelGenerator) ((PiePlot) chart.getPlot()).getLabelGenerator();
        Assertions.assertEquals("key1 = 1.2 (0.967%)", labelGenerator.generateSectionLabel(dataset, "key1"));
    }

    @Override
    protected JRDataSource createDataSource() {
        final DRDataSource dataSource = new DRDataSource("field1", "field2", "field3");
        for (int i = 0; i < 4; i++) {
            dataSource.add("value" + (i + 1), i + 1, i + 2);
            dataSource.add("value" + (i + 1), i + 1, i + 2);
        }
        dataSource.add("value3", null, null);
        return dataSource;
    }
}
