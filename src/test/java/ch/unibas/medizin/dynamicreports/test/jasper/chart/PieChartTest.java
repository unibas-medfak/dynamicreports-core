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

import java.io.Serializable;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.Plot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.column.TextColumnBuilder;
import ch.unibas.medizin.dynamicreports.report.datasource.DRDataSource;
import ch.unibas.medizin.dynamicreports.test.jasper.AbstractJasperChartTest;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PieChartTest extends AbstractJasperChartTest implements Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    protected void configureReport(JasperReportBuilder rb) {
        TextColumnBuilder<String> column1;
        TextColumnBuilder<Integer> column2;

        rb.addProperty("net.sf.jasperreports.chart.pie.ignore.duplicated.key", "true")
          .columns(column1 = col.column("Column1", "field1", String.class), column2 = col.column("Column2", "field2", Integer.class))
          .summary(cht.pieChart().setKey(column1).series(cht.serie(column2)).setCircular(false).setLabelFormat("label {0}").setLegendLabelFormat("legend label {0}"),
                   cht.pieChart().setKey(column1).series(cht.serie(column2)).setShowLabels(false));
    }

    @Override
    @Test
    public void test() {
        super.test();

        numberOfPagesTest(1);

        JFreeChart chart = getChart("summary.chart1", 0);
        Plot plot = chart.getPlot();
         Assertions.assertEquals( PiePlot.class, plot.getClass(),"plot");
        Assertions.assertFalse(((PiePlot) plot).isCircular(), "circular");
         Assertions.assertEquals( "label {0}", ((StandardPieSectionLabelGenerator) ((PiePlot) plot).getLabelGenerator()).getLabelFormat(),"label format");
         Assertions.assertEquals( "legend label {0}", ((StandardPieSectionLabelGenerator) ((PiePlot) plot).getLegendLabelGenerator()).getLabelFormat(),"legend label format");

        chart = getChart("summary.chart2", 0);
        plot = chart.getPlot();
        Assertions.assertNull(((PiePlot) plot).getLabelGenerator(), "label format");
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
