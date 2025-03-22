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

import ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder;
import ch.unibas.medizin.dynamicreports.report.base.expression.AbstractSimpleExpression;
import ch.unibas.medizin.dynamicreports.report.builder.column.TextColumnBuilder;
import ch.unibas.medizin.dynamicreports.report.constant.Calculation;
import ch.unibas.medizin.dynamicreports.report.constant.Evaluation;
import ch.unibas.medizin.dynamicreports.report.datasource.DRDataSource;
import ch.unibas.medizin.dynamicreports.report.definition.ReportParameters;
import ch.unibas.medizin.dynamicreports.test.jasper.AbstractJasperChartTest;
import net.sf.jasperreports.engine.JRDataSource;

import java.io.Serial;
import java.io.Serializable;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.cht;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.col;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.template;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.variable;

/**
 * @author Ricardo Mariaca
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BandChartDataTest extends AbstractJasperChartTest implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Override
    protected void configureReport(JasperReportBuilder rb) {
        TextColumnBuilder<String> column1;
        TextColumnBuilder<Integer> column2;

        rb.setTemplate(template().setChartHeight(100))
          .setPageColumnsPerPage(2)
          .columns(column1 = col.column("Column1", "field1", String.class), column2 = col.column("Column2", "field2", Integer.class))
          .variables(variable("var1", column2, Calculation.SUM), variable("var2", column2, Calculation.SUM).setResetType(Evaluation.PAGE),
                     variable("var3", column2, Calculation.SUM).setResetType(Evaluation.COLUMN))
          .title(cht.barChart().setTitle(new TitleExpression("var1")).setCategory(column1).series(cht.serie(column2)))
          .pageHeader(cht.barChart().setTitle(new TitleExpression("var2")).setCategory(column1).series(cht.serie(column2)))
          .pageFooter(cht.barChart().setTitle(new TitleExpression("var2")).setCategory(column1).series(cht.serie(column2)))
          .columnHeader(cht.barChart().setTitle(new TitleExpression("var3")).setCategory(column1).series(cht.serie(column2)))
          .columnFooter(cht.barChart().setTitle(new TitleExpression("var3")).setCategory(column1).series(cht.serie(column2)))
          .lastPageFooter(cht.barChart().setTitle(new TitleExpression("var2")).setCategory(column1).series(cht.serie(column2)))
          .summary(cht.barChart().setTitle(new TitleExpression("var1")).setCategory(column1).series(cht.serie(column2)));
    }

    @Test
    public void test() {
        super.test();
        numberOfPagesTest(3);

        chartTest("title", 100);
        chartTest("pageHeader", 38, 50, 12);
        chartTest("pageFooter", 38, 50);
        chartTest("columnHeader", 19, 19, 25, 25, 12);
        chartTest("columnFooter", 19, 19, 25, 25, 12);
        chartTest("lastPageFooter", 12);
        chartTest("summary", 100);
    }

    private void chartTest(String name, int... values) {
        int index = 0;
        chartCountTest(name + ".chart1", values.length);
        for (int value : values) {
            chartTitleTest(name + ".chart1", index, "Title sum=" + value);
            chartCategoryCountTest(name + ".chart1", index, 1);
            chartSeriesCountTest(name + ".chart1", index, 1);
            chartDataTest(name + ".chart1", index, new String[] {"value"}, new String[] {"Column2"}, new Number[][] {{(double) value}});
            index++;
        }
    }

    @Override
    protected JRDataSource createDataSource() {
        DRDataSource dataSource = new DRDataSource("field1", "field2");
        for (int i = 0; i < 100; i++) {
            dataSource.add("value", 1);
        }
        return dataSource;
    }

    private static class TitleExpression extends AbstractSimpleExpression<String> {
        @Serial
        private static final long serialVersionUID = 1L;

        private final String variableName;

        private TitleExpression(String variableName) {
            this.variableName = variableName;
        }

        @Override
        public String evaluate(ReportParameters reportParameters) {
            return "Title sum=" + reportParameters.getValue(variableName);
        }
    }
}
