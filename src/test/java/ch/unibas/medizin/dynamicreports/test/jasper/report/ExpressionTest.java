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
package ch.unibas.medizin.dynamicreports.test.jasper.report;

import ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder;
import ch.unibas.medizin.dynamicreports.report.base.expression.AbstractSimpleExpression;
import ch.unibas.medizin.dynamicreports.report.definition.ReportParameters;
import ch.unibas.medizin.dynamicreports.test.jasper.AbstractJasperValueTest;

import java.io.Serializable;
import java.util.Locale;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.cmp;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.type;

/**
 * @author Ricardo Mariaca
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ExpressionTest extends AbstractJasperValueTest implements Serializable {
    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unchecked")
    @Override
    protected void configureReport(JasperReportBuilder rb) {
        rb.setLocale(Locale.ENGLISH).title(
                cmp.text(new Expression1()),
                cmp.text(new Expression2<>("text2")),
                cmp.text(new Expression4()).setDataType(type.integerType())
        );
    }

    @Override
    @Test
    public void test() {
        super.test();

        numberOfPagesTest(1);

        elementValueTest("title.textField1", "text1");
        elementValueTest("title.textField2", "text2");
        elementValueTest("title.textField3", "1,000");
    }

    @SuppressWarnings("rawtypes")
    private static class Expression1 extends AbstractSimpleExpression {
        private static final long serialVersionUID = 1L;

        @Override
        public Object evaluate(ReportParameters reportParameters) {
            return "text1";
        }
    }

    private static class Expression2<T> extends AbstractSimpleExpression<T> {
        private static final long serialVersionUID = 1L;

        private final T value;

        private Expression2(T value) {
            this.value = value;
        }

        @Override
        public T evaluate(ReportParameters reportParameters) {
            return value;
        }
    }

    private static class Expression3 extends AbstractSimpleExpression<Integer> {
        private static final long serialVersionUID = 1L;

        @Override
        public Integer evaluate(ReportParameters reportParameters) {
            return 1000;
        }
    }

    private class Expression4 extends Expression3 {
        private static final long serialVersionUID = 1L;
    }
}
