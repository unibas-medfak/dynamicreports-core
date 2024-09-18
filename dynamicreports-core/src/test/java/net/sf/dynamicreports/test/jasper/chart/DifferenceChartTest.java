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

import java.awt.Color;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.Axis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYDifferenceRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.junit.jupiter.api.Assertions;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.TimePeriod;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.definition.chart.DRIChartCustomizer;
import net.sf.dynamicreports.test.jasper.AbstractJasperChartTest;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * Difference chart tests.
 *
 * @author Ricardo Mariaca
 */
public class DifferenceChartTest extends AbstractJasperChartTest implements Serializable {
  private static final long serialVersionUID = 1L;

  @Override
  protected void configureReport(JasperReportBuilder rb) {
    TextColumnBuilder<Date> column1;
    TextColumnBuilder<Timestamp> column2;
    TextColumnBuilder<Integer> column3;
    TextColumnBuilder<Integer> column4;

    rb.setLocale(Locale.ENGLISH)
        .columns(column1 = col.column("Column1", "field1", Date.class),
            column2 = col.column("Column2", "field2", Timestamp.class),
            column3 = col.column("Column3", "field3", Integer.class),
            column4 = col.column("Column4", "field4", Integer.class))
        .summary(
            cht.differenceChart().customizers(new ChartCustomizer()).setTimePeriod(column1)
                .series(cht.serie(column3), cht.serie(column4)).setTimePeriodType(TimePeriod.DAY)
                .setShowShapes(false).setPositiveColor(Color.BLUE).setNegativeColor(Color.MAGENTA),
            cht.differenceChart().setTimePeriod(column1).series(cht.serie(column3))
                .setTimeAxisFormat(cht.axisFormat().setLabel("time").setLabelColor(Color.BLUE)
                    .setLabelFont(ARIMO_BOLD).setTickLabelFont(ARIMO_ITALIC)
                    .setTickLabelColor(Color.CYAN).setLineColor(Color.LIGHT_GRAY)
                    .setVerticalTickLabels(true)),
            cht.differenceChart().setTimePeriod(column2).series(cht.serie(column3))
                .setValueAxisFormat(cht.axisFormat().setLabel("value").setLabelColor(Color.BLUE)
                    .setLabelFont(ARIMO_BOLD).setTickLabelFont(ARIMO_ITALIC)
                    .setTickLabelColor(Color.CYAN).setTickLabelMask("#,##0.00")
                    .setLineColor(Color.LIGHT_GRAY).setRangeMinValueExpression(1)
                    .setRangeMaxValueExpression(15).setVerticalTickLabels(true)));
  }

  @Override
  public void test() {
    super.test();

    numberOfPagesTest(1);

    JFreeChart chart = getChart("summary.chart1", 0);
    final XYItemRenderer renderer = chart.getXYPlot().getRenderer();
     Assertions.assertEquals( XYDifferenceRenderer.class, renderer.getClass(),"renderer");
     Assertions.assertFalse( ((XYDifferenceRenderer) renderer).getShapesVisible(),"show shapes");
     Assertions.assertEquals( Color.BLUE,        ((XYDifferenceRenderer) renderer).getPositivePaint(),"positive paint");
     Assertions.assertEquals( Color.MAGENTA,        ((XYDifferenceRenderer) renderer).getNegativePaint(),"negative paint");

    chart = getChart("summary.chart2", 0);
    Axis axis = chart.getXYPlot().getDomainAxis();
     Assertions.assertEquals( "time", axis.getLabel(),"category label");
     Assertions.assertEquals( Color.BLUE, axis.getLabelPaint(),"category label color");
     Assertions.assertEquals( ARIMO_BOLD_AWT, axis.getLabelFont(),"category label font");
     Assertions.assertEquals( Color.CYAN, axis.getTickLabelPaint(),"tick label color");
     Assertions.assertEquals( ARIMO_ITALIC_AWT, axis.getTickLabelFont(),"tick label font");
     Assertions.assertEquals( Color.LIGHT_GRAY, axis.getAxisLinePaint(),"line color");
     Assertions.assertTrue( ((ValueAxis) axis).isVerticalTickLabels(),"vertical tick labels");

    chart = getChart("summary.chart3", 0);
    axis = chart.getXYPlot().getRangeAxis();
     Assertions.assertEquals( "value", axis.getLabel(),"value label");
     Assertions.assertEquals( Color.BLUE, axis.getLabelPaint(),"value label color");
     Assertions.assertEquals( ARIMO_BOLD_AWT, axis.getLabelFont(),"value label font");
     Assertions.assertEquals( Color.CYAN, axis.getTickLabelPaint(),"tick label color");
     Assertions.assertEquals( ARIMO_ITALIC_AWT, axis.getTickLabelFont(),"tick label font");
    Assertions.assertEquals("tick label mask", "10.00",
        ((NumberAxis) axis).getNumberFormatOverride().format(10));
    //  Assertions.assertEquals( Color.LIGHT_GRAY, axis.getAxisLinePaint(),"line color");
     Assertions.assertEquals( 1d, ((ValueAxis) axis).getLowerBound(), 0,"range min value");
     Assertions.assertEquals( 15d, ((ValueAxis) axis).getUpperBound(), 0,"range max value");
     Assertions.assertTrue( ((ValueAxis) axis).isVerticalTickLabels(),"vertical tick labels");
  }

  @Override
  protected JRDataSource createDataSource() {
    final DRDataSource dataSource = new DRDataSource("field1", "field2", "field3", "field4");
    final Calendar c = Calendar.getInstance();
    c.setTime(new Date());
    for (int i = 0; i < 4; i++) {
      dataSource.add(c.getTime(), new Timestamp(c.getTimeInMillis()), i + 5, 7 - i);
      c.add(Calendar.DAY_OF_MONTH, 1);
    }
    return dataSource;
  }

  private class ChartCustomizer implements DRIChartCustomizer, Serializable {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    @Override
    public void customize(JFreeChart chart, ReportParameters reportParameters) {
      final XYPlot xyPlot = chart.getXYPlot();
       Assertions.assertEquals( XYDifferenceRenderer.class, xyPlot.getRenderer().getClass(),"renderer");
    }
  }
}
