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
package ch.unibas.medizin.dynamicreports.test.jasper.subtotal;

import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.col;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.sbt;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.variable;

import java.io.Serial;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder;
import ch.unibas.medizin.dynamicreports.report.base.expression.AbstractSimpleExpression;
import ch.unibas.medizin.dynamicreports.report.builder.VariableBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.column.TextColumnBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.expression.AbstractComplexExpression;
import ch.unibas.medizin.dynamicreports.report.builder.expression.PercentageExpression;
import ch.unibas.medizin.dynamicreports.report.builder.subtotal.AggregationSubtotalBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.subtotal.CustomSubtotalBuilder;
import ch.unibas.medizin.dynamicreports.report.constant.Calculation;
import ch.unibas.medizin.dynamicreports.report.constant.Evaluation;
import ch.unibas.medizin.dynamicreports.report.datasource.DRDataSource;
import ch.unibas.medizin.dynamicreports.report.definition.ReportParameters;
import ch.unibas.medizin.dynamicreports.test.jasper.AbstractJasperValueTest;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GroupCustomSubtotal1Test extends AbstractJasperValueTest implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private TextColumnBuilder<Integer> column2;
    private VariableBuilder<Integer> variable1;
    private AggregationSubtotalBuilder<Integer> subtotal1;
    private CustomSubtotalBuilder<Integer> subtotal2;
    private AggregationSubtotalBuilder<Integer> subtotal3;
    private CustomSubtotalBuilder<String> subtotal4;
    private CustomSubtotalBuilder<String> subtotal5;

    @Override
    protected void configureReport(JasperReportBuilder rb) {
        TextColumnBuilder<String> column1;

        rb.setLocale(Locale.ENGLISH)
          .columns(column1 = col.column("Column1", "field1", String.class), column2 = col.column("Column2", "field2", Integer.class))
          .groupBy(column1)
          .subtotalsAtFirstGroupFooter(subtotal1 = sbt.sum(column2), subtotal2 = sbt.customValue(new ValueExpression(), column2))
          .subtotalsAtLastGroupFooter(subtotal3 = sbt.sum(column2), subtotal4 = sbt.customValue(new ValueExpression2(), column2), subtotal5 = sbt.customValue(new ValueExpression3(), column2));
    }

    @Override
    @Test
    public void test() {
        super.test();

        numberOfPagesTest(1);
        // groupFooter
        subtotalIndexCountTest(subtotal1, 1, 2);
        subtotalIndexValueTest(subtotal1, 1, "6", "15");
        subtotalIndexCountTest(subtotal2, 2, 2);
        subtotalIndexValueTest(subtotal2, 2, "6", "15");
        subtotalIndexCountTest(subtotal3, 3, 2);
        subtotalIndexValueTest(subtotal3, 3, "6", "15");
        subtotalIndexCountTest(subtotal4, 4, 2);
        subtotalIndexValueTest(subtotal4, 4, "6 (28.57%)", "15 (71.43%)");
        subtotalIndexCountTest(subtotal5, 5, 2);
        subtotalIndexValueTest(subtotal5, 5, "value1", "value2");
    }

    @Override
    protected JRDataSource createDataSource() {
        final DRDataSource dataSource = new DRDataSource("field1", "field2");
        for (int i = 1; i <= 3; i++) {
            dataSource.add("group1", i);
        }
        for (int i = 4; i <= 6; i++) {
            dataSource.add("group2", i);
        }
        return dataSource;
    }

    private class ValueExpression extends AbstractSimpleExpression<Integer> {
        @Serial
        private static final long serialVersionUID = 1L;

        @Override
        public Integer evaluate(ReportParameters reportParameters) {
            return reportParameters.getValue(variable1);
        }
    }

    private class ValueExpression2 extends AbstractComplexExpression<String> {
        @Serial
        private static final long serialVersionUID = 1L;

        public ValueExpression2() {
            variable1 = variable(column2, Calculation.SUM);
            variable1.setResetType(Evaluation.LAST_GROUP);
            final VariableBuilder<Integer> variable2 = variable(column2, Calculation.SUM);
            variable2.setResetType(Evaluation.REPORT);
            addExpression(new PercentageExpression(variable1.getVariable(), variable2.getVariable()));
        }

        @Override
        public String evaluate(List<?> values, ReportParameters reportParameters) {
            final String percentage = new DecimalFormat("#,##0.00%", new DecimalFormatSymbols(Locale.ENGLISH)).format(values.getFirst());
            return reportParameters.getValue(variable1) + " (" + percentage + ")";
        }
    }

    private static class ValueExpression3 extends AbstractComplexExpression<String> {
        @Serial
        private static final long serialVersionUID = 1L;
        private int count = 1;

        @Override
        public String evaluate(List<?> values, ReportParameters reportParameters) {
            return "value" + count++;
        }
    }
}
