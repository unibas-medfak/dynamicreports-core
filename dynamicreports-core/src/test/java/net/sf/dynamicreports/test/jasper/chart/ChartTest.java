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
import static net.sf.dynamicreports.report.builder.DynamicReports.stl;

import java.awt.Color;
import java.io.Serializable;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.ui.RectangleEdge;
import org.junit.jupiter.api.Assertions;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.constant.Orientation;
import net.sf.dynamicreports.report.constant.Position;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.test.jasper.AbstractJasperChartTest;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * Chart tests.
 *
 * @author Ricardo Mariaca
 */
public class ChartTest extends AbstractJasperChartTest implements Serializable {
  private static final long serialVersionUID = 1L;

  @Override
  protected void configureReport(JasperReportBuilder rb) {
    TextColumnBuilder<String> column1;
    TextColumnBuilder<Integer> column2;

    rb.columns(column1 = col.column("Column1", "field1", String.class),
        column2 = col.column("Column2", "field2", Integer.class))
        .summary(
            cht.barChart().setTitle("title").setTitleColor(Color.BLUE).setTitleFont(ARIMO_BOLD)
                .setTitlePosition(Position.RIGHT).setSubtitle("subtitle")
                .setSubtitleColor(Color.CYAN).setSubtitleFont(ARIMO).setLegendColor(Color.BLUE)
                .setLegendBackgroundColor(Color.LIGHT_GRAY).setLegendFont(ARIMO_ITALIC)
                .setLegendPosition(Position.LEFT).setCategory(column1).series(cht.serie(column2)),
            cht.barChart().setShowLegend(false).setOrientation(Orientation.HORIZONTAL)
                .seriesColors(Color.BLUE, Color.GREEN, Color.RED).setCategory(column1)
                .series(cht.serie(column2), cht.serie(column2).setLabel("2"),
                    cht.serie(column2).setLabel("3")),
            cht.multiAxisChart(cht.lineChart().setCategory(column1).series(cht.serie(column2)),
                cht.barChart().setCategory(column1).series(cht.serie(column2))
                    .setStyle(stl.style().setForegroundColor(Color.BLUE))));
  }

  @Override
  public void test() {
    super.test();

    numberOfPagesTest(1);

    chartCountTest("summary.chart1", 1);
    JFreeChart chart = getChart("summary.chart1", 0);

    final TextTitle title = chart.getTitle();
    Assertions.assertEquals("title", "title", title.getText());
    Assertions.assertEquals("title color", Color.BLUE, title.getPaint());
    Assertions.assertEquals("title font", ARIMO_BOLD_AWT, title.getFont());
    Assertions.assertEquals("title position", RectangleEdge.RIGHT, title.getPosition());

    final TextTitle subtitle = (TextTitle) chart.getSubtitle(1);
    Assertions.assertEquals("subtitle", "subtitle", subtitle.getText());
    Assertions.assertEquals("subtitle color", Color.CYAN, subtitle.getPaint());
    Assertions.assertEquals("subtitle font", ARIMO_AWT, subtitle.getFont());

    final LegendTitle legend = (LegendTitle) chart.getSubtitle(0);
    Assertions.assertEquals("legend color", Color.BLUE, legend.getItemPaint());
    Assertions.assertEquals("legend backgroundcolor", Color.LIGHT_GRAY, legend.getBackgroundPaint());
    Assertions.assertEquals("legend font", ARIMO_ITALIC_AWT, legend.getItemFont());
    Assertions.assertEquals("legend position", RectangleEdge.LEFT, legend.getPosition());

    chartCountTest("summary.chart2", 1);
    chart = getChart("summary.chart2", 0);
    Assertions.assertNull("legend", chart.getLegend());
    Assertions.assertEquals("plot orientation", PlotOrientation.HORIZONTAL,
        chart.getCategoryPlot().getOrientation());
    Assertions.assertEquals("plot series colors", Color.BLUE,
        chart.getPlot().getDrawingSupplier().getNextPaint());
    Assertions.assertEquals("plot series colors", Color.GREEN,
        chart.getPlot().getDrawingSupplier().getNextPaint());
    Assertions.assertEquals("plot series colors", Color.RED,
        chart.getPlot().getDrawingSupplier().getNextPaint());

    chart = getChart("summary.chart3", 0);
    Assertions.assertEquals("axis color", Color.BLACK,
        chart.getCategoryPlot().getRangeAxis(0).getAxisLinePaint());
    Assertions.assertEquals("axis color", Color.BLUE,
        chart.getCategoryPlot().getRangeAxis(1).getAxisLinePaint());
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
