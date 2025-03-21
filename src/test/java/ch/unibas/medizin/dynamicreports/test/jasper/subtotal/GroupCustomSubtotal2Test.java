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
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.grp;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.sbt;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.column.TextColumnBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.expression.AbstractComplexExpression;
import ch.unibas.medizin.dynamicreports.report.builder.group.ColumnGroupBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.subtotal.CustomSubtotalBuilder;
import ch.unibas.medizin.dynamicreports.report.datasource.DRDataSource;
import ch.unibas.medizin.dynamicreports.report.definition.ReportParameters;
import ch.unibas.medizin.dynamicreports.test.jasper.AbstractJasperValueTest;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GroupCustomSubtotal2Test extends AbstractJasperValueTest implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private TextColumnBuilder<Integer> column2;
    private CustomSubtotalBuilder<Integer> subtotal1;
    private CustomSubtotalBuilder<Integer> subtotal2;
    private CustomSubtotalBuilder<Integer> subtotal3;
    private CustomSubtotalBuilder<Integer> subtotal4;
    private CustomSubtotalBuilder<Integer> subtotal5;
    private CustomSubtotalBuilder<Integer> subtotal6;

    @Override
    protected void configureReport(JasperReportBuilder rb) {
        final TextColumnBuilder<String> column1 = col.column("Column1", "field1", String.class);
        final ColumnGroupBuilder group = grp.group(column1);

        rb.setLocale(Locale.ENGLISH)
          .columns(column1, column2 = col.column("Column2", "field2", Integer.class))
          .groupBy(group)
          .subtotalsAtGroupHeader(group, subtotal1 = sbt.customValue(new ValueExpression(), column2))
          .subtotalsAtGroupFooter(group, subtotal2 = sbt.customValue(new ValueExpression(), column2))
          .subtotalsAtFirstGroupHeader(subtotal3 = sbt.customValue(new ValueExpression(), column2))
          .subtotalsAtFirstGroupFooter(subtotal4 = sbt.customValue(new ValueExpression(), column2))
          .subtotalsAtLastGroupHeader(subtotal5 = sbt.customValue(new ValueExpression(), column2))
          .subtotalsAtLastGroupFooter(subtotal6 = sbt.customValue(new ValueExpression(), column2));
    }

    @Override
    @Test
    public void test() {
        super.test();

        numberOfPagesTest(1);
        // groupHeader
        subtotalIndexCountTest(subtotal1, 1, 2);
        subtotalIndexValueTest(subtotal1, 1, "3", "6");
        // groupFooter
        subtotalIndexCountTest(subtotal2, 1, 2);
        subtotalIndexValueTest(subtotal2, 1, "3", "6");
        // first groupHeader
        subtotalIndexCountTest(subtotal3, 1, 2);
        subtotalIndexValueTest(subtotal3, 1, "3", "6");
        // first groupFooter
        subtotalIndexCountTest(subtotal4, 1, 2);
        subtotalIndexValueTest(subtotal4, 1, "3", "6");
        // last groupHeader
        subtotalIndexCountTest(subtotal5, 1, 2);
        subtotalIndexValueTest(subtotal5, 1, "3", "6");
        // last groupFooter
        subtotalIndexCountTest(subtotal6, 1, 2);
        subtotalIndexValueTest(subtotal6, 1, "3", "6");
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

    private class ValueExpression extends AbstractComplexExpression<Integer> {
        @Serial
        private static final long serialVersionUID = 1L;

        private ValueExpression() {
            addExpression(column2);
        }

        @Override
        public Integer evaluate(List<?> values, ReportParameters reportParameters) {
            return (Integer) values.getFirst();
        }
    }
}
