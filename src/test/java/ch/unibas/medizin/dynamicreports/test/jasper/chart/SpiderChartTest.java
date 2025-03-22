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
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.field;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.stl;

import java.awt.BasicStroke;
import java.awt.Color;
import java.io.Serial;
import java.io.Serializable;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.SpiderWebPlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.util.Rotation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.FieldBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.column.TextColumnBuilder;
import ch.unibas.medizin.dynamicreports.report.constant.SpiderRotation;
import ch.unibas.medizin.dynamicreports.report.constant.TableOrder;
import ch.unibas.medizin.dynamicreports.report.datasource.DRDataSource;
import ch.unibas.medizin.dynamicreports.test.jasper.AbstractJasperChartTest;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRPrintFrame;
import net.sf.jasperreports.engine.JRPrintImage;

/**
 * @author Ricardo Mariaca
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SpiderChartTest extends AbstractJasperChartTest implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Override
    protected void configureReport(final JasperReportBuilder rb) {
        final FieldBuilder<String> column1 = field("field1", String.class);
        final TextColumnBuilder<Integer> column2 = col.column("Column2", "field2", Integer.class);

        rb.summary(cmp.horizontalList(cht.spiderChart()
                                         .setCategory(column1)
                                         .series(cht.serie(column2))
                                         .setMaxValue(10d)
                                         .setRotation(SpiderRotation.ANTICLOCKWISE)
                                         .setTableOrder(TableOrder.BY_COLUMN)
                                         .setWebFilled(false)
                                         .setStartAngle(20d)
                                         .setHeadPercent(30d)
                                         .setInteriorGap(0.15d)
                                         .setAxisLineColor(Color.RED)
                                         .setAxisLineWidth(2f)
                                         .setLabelColor(Color.BLUE)
                                         .setLabelGap(2d)
                                         .setLabelFont(stl.font().bold()), cht.spiderChart().setCategory(column1).series(cht.serie(column2)).setTitle("title").setSubtitle("subtitle")));
    }

    @Override
    @Test
    public void test() {
        super.test();

        numberOfPagesTest(1);

        final JRPrintFrame printFrame = (JRPrintFrame) getElementAt("summary.list1", 0);

        JRPrintImage image = (JRPrintImage) printFrame.getElements().getFirst();
        JFreeChart chart = getChart(image);
        SpiderWebPlot plot = (SpiderWebPlot) chart.getPlot();
        Assertions.assertEquals( Double.valueOf(10), Double.valueOf(plot.getMaxValue()),"max value");
        Assertions.assertEquals( Rotation.ANTICLOCKWISE, plot.getDirection(),"rotation");
        Assertions.assertEquals( org.jfree.chart.util.TableOrder.BY_COLUMN, plot.getDataExtractOrder(),"table order");
        Assertions.assertFalse(plot.isWebFilled(), "web filled");
        Assertions.assertEquals( Double.valueOf(20), Double.valueOf(plot.getStartAngle()),"start angle");
        Assertions.assertEquals( Double.valueOf(30), Double.valueOf(plot.getHeadPercent()),"head percent");
        Assertions.assertEquals( Double.valueOf(0.15), Double.valueOf(plot.getInteriorGap()),"interior gap");
        Assertions.assertEquals( Color.RED, plot.getAxisLinePaint(),"axis line color");
        Assertions.assertEquals( Float.valueOf(2), Float.valueOf(((BasicStroke) plot.getAxisLineStroke()).getLineWidth()),"interior gap");
        Assertions.assertEquals( Color.BLUE, plot.getLabelPaint(),"label color");
        Assertions.assertEquals( Double.valueOf(2), Double.valueOf(plot.getAxisLabelGap()),"label gap");
        Assertions.assertTrue(plot.getLabelFont().isBold(), "label font");

        image = (JRPrintImage) printFrame.getElements().get(1);
        chart = getChart(image);
        Assertions.assertEquals( "title", chart.getTitle().getText(),"title");
        Assertions.assertEquals( "subtitle", ((TextTitle) chart.getSubtitle(1)).getText(),"subtitle");
    }

    @Override
    protected JRDataSource createDataSource() {
        final DRDataSource dataSource = new DRDataSource("field1", "field2");
        for (int i = 0; i < 4; i++) {
            dataSource.add("value" + (i + 1), i + 1);
            dataSource.add("value" + (i + 1), i + 1);
        }
        return dataSource;
    }
}
