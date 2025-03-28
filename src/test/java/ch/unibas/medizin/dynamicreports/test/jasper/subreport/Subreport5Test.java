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
package ch.unibas.medizin.dynamicreports.test.jasper.subreport;

import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.cmp;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.col;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.field;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.report;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.type;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.column.TextColumnBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.component.Components;
import ch.unibas.medizin.dynamicreports.report.builder.component.SubreportBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.expression.AbstractComplexExpression;
import ch.unibas.medizin.dynamicreports.report.datasource.DRDataSource;
import ch.unibas.medizin.dynamicreports.report.definition.ReportParameters;
import ch.unibas.medizin.dynamicreports.test.jasper.AbstractJasperValueTest;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Subreport5Test extends AbstractJasperValueTest implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final TextColumnBuilder<String> column1 = col.column("field1", type.stringType());

    @Override
    protected void configureReport(JasperReportBuilder rb) {
        final SubreportBuilder subreport = Components.subreport(new SubreportExpression()).setDataSource(new SubreportDataSourceExpression());

        rb.detail(subreport);
    }

    @Override
    @Test
    public void test() {
        super.test();

        numberOfPagesTest(1);

        elementCountTest("title.textField1", 3);
        elementValueTest("title.textField1", "Subreport text1", "Subreport text2", "Subreport text3");

        columnDetailCountTest(column1, 6);
        columnDetailValueTest(column1, "text1a", "text1b", "text2a", "text2b", "text3a", "text3b");
    }

    @Override
    protected JRDataSource createDataSource() {
        final DRDataSource dataSource = new DRDataSource("field1");
        dataSource.add("text1");
        dataSource.add("text2");
        dataSource.add("text3");
        return dataSource;
    }

    private class SubreportExpression extends AbstractComplexExpression<JasperReportBuilder> {
        @Serial
        private static final long serialVersionUID = 1L;

        public SubreportExpression() {
            addExpression(field("field1", String.class));
        }

        @Override
        public JasperReportBuilder evaluate(List<?> values, ReportParameters reportParameters) {
            final JasperReportBuilder report = report();
            report.title(cmp.text("Subreport " + values.getFirst())).columns(column1);

            return report;
        }
    }

    private static class SubreportDataSourceExpression extends AbstractComplexExpression<JRDataSource> {
        @Serial
        private static final long serialVersionUID = 1L;

        public SubreportDataSourceExpression() {
            addExpression(field("field1", String.class));
        }

        @Override
        public JRDataSource evaluate(List<?> values, ReportParameters reportParameters) {
            final DRDataSource dataSource = new DRDataSource("field1");
            dataSource.add(values.getFirst() + "a");
            dataSource.add(values.getFirst() + "b");
            return dataSource;
        }
    }
}
