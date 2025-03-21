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

package ch.unibas.medizin.dynamicreports.test.jasper.chart;

import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.cht;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.col;

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
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.column.TextColumnBuilder;
import ch.unibas.medizin.dynamicreports.report.constant.TimePeriod;
import ch.unibas.medizin.dynamicreports.report.datasource.DRDataSource;
import ch.unibas.medizin.dynamicreports.test.jasper.AbstractJasperChartTest;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * Time series chart tests.
 *
 * @author Ricardo Mariaca
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TimeSeriesChartTest extends AbstractJasperChartTest implements Serializable {
  private static final long serialVersionUID = 1L;

  @Override
  protected void configureReport(JasperReportBuilder rb) {
    TextColumnBuilder<Date> column1;
    TextColumnBuilder<Timestamp> column2;
    TextColumnBuilder<Integer> column3;

    rb.setLocale(Locale.ENGLISH)
        .columns(column1 = col.column("Column1", "field1", Date.class),
            column2 = col.column("Column2", "field2", Timestamp.class),
            column3 = col.column("Column3", "field3", Integer.class))
        .summary(
            cht.timeSeriesChart().setTimePeriod(column1).series(cht.serie(column3))
                .setTimePeriodType(TimePeriod.DAY).setShowShapes(false).setShowLines(false),
            cht.timeSeriesChart().setTimePeriod(column1).series(cht.serie(column3))
                .setTimeAxisFormat(cht.axisFormat().setLabel("time").setLabelColor(Color.BLUE)
                    .setLabelFont(ARIMO_BOLD).setTickLabelFont(ARIMO_ITALIC)
                    .setTickLabelColor(Color.CYAN).setLineColor(Color.LIGHT_GRAY)
                    .setVerticalTickLabels(true)),
            cht.timeSeriesChart().setTimePeriod(column2).series(cht.serie(column3))
                .setValueAxisFormat(cht.axisFormat().setLabel("value").setLabelColor(Color.BLUE)
                    .setLabelFont(ARIMO_BOLD).setTickLabelFont(ARIMO_ITALIC)
                    .setTickLabelColor(Color.CYAN).setTickLabelMask("#,##0.00")
                    .setLineColor(Color.LIGHT_GRAY).setRangeMinValueExpression(1)
                    .setRangeMaxValueExpression(15).setVerticalTickLabels(true)));
  }

  @Override
  @Test
  public void test() {
    super.test();

    numberOfPagesTest(1);

    JFreeChart chart = getChart("summary.chart1", 0);
    final XYItemRenderer renderer = chart.getXYPlot().getRenderer();
     Assertions.assertEquals( XYLineAndShapeRenderer.class, renderer.getClass(),"renderer");
     Assertions.assertFalse( ((XYLineAndShapeRenderer) renderer).getDefaultShapesVisible(),"show shapes");
     Assertions.assertFalse( ((XYLineAndShapeRenderer) renderer).getDefaultLinesVisible(),"show lines");

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
     Assertions.assertEquals( "10.00",        ((NumberAxis) axis).getNumberFormatOverride().format(10),"tick label mask");
    //  Assertions.assertEquals( Color.LIGHT_GRAY, axis.getAxisLinePaint(),"line color");
     Assertions.assertEquals( 1d, ((ValueAxis) axis).getLowerBound(), 0,"range min value");
     Assertions.assertEquals( 15d, ((ValueAxis) axis).getUpperBound(), 0,"range max value");
     Assertions.assertTrue( ((ValueAxis) axis).isVerticalTickLabels(),"vertical tick labels");
  }

  @Override
  protected JRDataSource createDataSource() {
    final DRDataSource dataSource = new DRDataSource("field1", "field2", "field3");
    final Calendar c = Calendar.getInstance();
    c.setTime(new Date());
    for (int i = 0; i < 4; i++) {
      dataSource.add(c.getTime(), new Timestamp(c.getTimeInMillis()), i + 1);
      dataSource.add(c.getTime(), new Timestamp(c.getTimeInMillis()), i + 1);
      c.add(Calendar.DAY_OF_MONTH, 1);
    }
    return dataSource;
  }
}
