/*
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2018 Ricardo Mariaca and the Dynamic Reports Contributors
 *
 * This file is part of DynamicReports.
 *
 * DynamicReports is free software: you can redistribute it and/or modify it under the terms of the
 * GNU Lesser General Public License as published by the Free Software Foundation, either version 3
 * of the License, or (at your option) any later version.
 *
 * DynamicReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with
 * DynamicReports. If not, see <http://www.gnu.org/licenses/>.
 */

package net.sf.dynamicreports.test.jasper.chart;

import static net.sf.dynamicreports.report.builder.DynamicReports.cht;
import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.exp;

import java.awt.Color;
import java.util.Locale;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.Axis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.renderer.xy.XYBubbleRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.constant.ScaleType;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.test.jasper.AbstractJasperChartTest;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * Bubble chart tests.
 *
 * @author Ricardo Mariaca
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BubbleChartTest extends AbstractJasperChartTest {

  @Override
  protected void configureReport(JasperReportBuilder rb) {
    TextColumnBuilder<Integer> column1;
    TextColumnBuilder<Integer> column2;
    TextColumnBuilder<Double> column3;

    rb.setLocale(Locale.ENGLISH)
        .columns(column1 = col.column("Column1", "field1", Integer.class),
            column2 = col.column("Column2", "field2", Integer.class),
            column3 = col.column("Column3", "field3", Double.class))
        .summary(cht.bubbleChart().setXValue(column1)
            .series(cht.xyzSerie().setYValue(column2).setZValue(column3).setSeries(exp.text("a")),
                cht.xyzSerie().setXValue(column2).setYValue(column1).setZValue(column3))
            .setScaleType(ScaleType.ON_BOTH_AXES),
            cht.bubbleChart().setXValue(column1)
                .series(cht.xyzSerie().setYValue(column2).setZValue(column3))
                .setXAxisFormat(cht.axisFormat().setLabel("category").setLabelColor(Color.BLUE)
                    .setLabelFont(ARIMO_BOLD).setTickLabelFont(ARIMO_ITALIC)
                    .setTickLabelColor(Color.CYAN).setLineColor(Color.LIGHT_GRAY)),
            cht.bubbleChart().setXValue(column1)
                .series(cht.xyzSerie().setYValue(column2).setZValue(column3))
                .setYAxisFormat(cht.axisFormat().setLabel("value").setLabelColor(Color.BLUE)
                    .setLabelFont(ARIMO_BOLD).setTickLabelFont(ARIMO_ITALIC)
                    .setTickLabelColor(Color.CYAN).setTickLabelMask("#,##0.00")
                    .setLineColor(Color.LIGHT_GRAY)));
  }

  @Override
  @Test
  public void test() {
    super.test();

    numberOfPagesTest(1);

    JFreeChart chart = getChart("summary.chart1", 0);
    final XYItemRenderer renderer = chart.getXYPlot().getRenderer();
    Assertions.assertEquals( XYBubbleRenderer.class, renderer.getClass(),"renderer");
    Assertions.assertEquals(XYBubbleRenderer.SCALE_ON_BOTH_AXES,
        ((XYBubbleRenderer) renderer).getScaleType(),"scale type");
    xyzChartDataTest(chart, 0, "a",
        new Number[][] {{1d, 2d, 0.25}, {2d, 3d, 0.5}, {3d, 4d, 0.75}, {4d, 5d, 1d}});
    xyzChartDataTest(chart, 1, "serie1",
        new Number[][] {{2d, 1d, 0.25}, {3d, 2d, 0.5}, {4d, 3d, 0.75}, {5d, 4d, 1d}});

    chart = getChart("summary.chart2", 0);
    Axis axis = chart.getXYPlot().getDomainAxis();
    Assertions.assertEquals("category", axis.getLabel(),"category label");
    Assertions.assertEquals(Color.BLUE, axis.getLabelPaint(),"category label color");
    Assertions.assertEquals(ARIMO_BOLD_AWT, axis.getLabelFont(),"category label font");
    Assertions.assertEquals(Color.CYAN, axis.getTickLabelPaint(),"tick label color");
    Assertions.assertEquals(ARIMO_ITALIC_AWT, axis.getTickLabelFont(),"tick label font");
    Assertions.assertEquals(Color.LIGHT_GRAY, axis.getAxisLinePaint(),"line color");

    chart = getChart("summary.chart3", 0);
    axis = chart.getXYPlot().getRangeAxis();
    Assertions.assertEquals("value", axis.getLabel(),"value label");
    Assertions.assertEquals(Color.BLUE, axis.getLabelPaint(),"value label color");
    Assertions.assertEquals( ARIMO_BOLD_AWT, axis.getLabelFont(),"value label font");
    Assertions.assertEquals(Color.CYAN, axis.getTickLabelPaint(), "tick label color");
    Assertions.assertEquals( ARIMO_ITALIC_AWT, axis.getTickLabelFont(),"tick label font");
    Assertions.assertEquals( "10.00",
        ((NumberAxis) axis).getNumberFormatOverride().format(10),"tick label mask");
    Assertions.assertEquals(Color.LIGHT_GRAY, axis.getAxisLinePaint(),"line color");
  }

  @Override
  protected JRDataSource createDataSource() {
    final DRDataSource dataSource = new DRDataSource("field1", "field2", "field3");
    for (int i = 0; i < 4; i++) {
      dataSource.add(i + 1, i + 2, i * 0.25 + 0.25);
    }
    return dataSource;
  }
}
