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
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.report;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.type;

import java.io.Serializable;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder;
import ch.unibas.medizin.dynamicreports.report.base.expression.AbstractSimpleExpression;
import ch.unibas.medizin.dynamicreports.report.builder.column.TextColumnBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.component.SubreportBuilder;
import ch.unibas.medizin.dynamicreports.report.datasource.DRDataSource;
import ch.unibas.medizin.dynamicreports.report.definition.ReportParameters;
import ch.unibas.medizin.dynamicreports.test.jasper.AbstractJasperValueTest;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;

/**
 * @author Ricardo Mariaca
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Subreport2Test extends AbstractJasperValueTest implements Serializable {
    private static final long serialVersionUID = 1L;

    private TextColumnBuilder<String> column1;
    private TextColumnBuilder<String> column2;

    @Override
    protected void configureReport(JasperReportBuilder rb) {
        final SubreportBuilder detailSubreport = cmp.subreport(detailSubreport()).setDataSource(new SubreportDataSourceExpression());

        rb.title(cmp.subreport(titleSubreport())).detail(detailSubreport);
    }

    @Override
    @Test
    public void test() {
        super.test();

        numberOfPagesTest(1);

        // title subreport
        columnDetailCountTest(column1, 3);
        columnDetailValueTest(column1, "value1", "value2", "value3");

        // detail subreport
        elementCountTest("title.textField1", 3);
        elementValueTest("title.textField1", "Subreport1", "Subreport2", "Subreport3");

        columnDetailCountTest(column2, 6);
        columnDetailValueTest(column2, "1_1", "1_2", "2_1", "2_2", "3_1", "3_2");
    }

    @Override
    protected JRDataSource createDataSource() {
        return new JREmptyDataSource(3);
    }

    private JasperReportBuilder titleSubreport() {
        final JasperReportBuilder report = report();
        column1 = col.column("Column1", "field1", type.stringType());
        report.columns(column1).setDataSource(titleSubreportDataSource());
        return report;
    }

    private JasperReportBuilder detailSubreport() {
        final JasperReportBuilder report = report();
        column2 = col.column(new ValueExpression());
        report.columns(column2).title(cmp.text(new SubreportTitleExpression()));
        return report;
    }

    private JRDataSource titleSubreportDataSource() {
        final DRDataSource dataSource = new DRDataSource("field1");
        dataSource.add("value1");
        dataSource.add("value2");
        dataSource.add("value3");
        return dataSource;
    }

    private static class SubreportDataSourceExpression extends AbstractSimpleExpression<JRDataSource> {
        private static final long serialVersionUID = 1L;

        @Override
        public JRDataSource evaluate(ReportParameters reportParameters) {
            return new JREmptyDataSource(2);
        }
    }

    private static class SubreportTitleExpression extends AbstractSimpleExpression<String> {
        private static final long serialVersionUID = 1L;

        @Override
        public String evaluate(ReportParameters reportParameters) {
            return "Subreport" + reportParameters.getMasterParameters().getReportRowNumber();
        }
    }

    private static class ValueExpression extends AbstractSimpleExpression<String> {
        private static final long serialVersionUID = 1L;

        @Override
        public String evaluate(ReportParameters reportParameters) {
            return reportParameters.getMasterParameters().getReportRowNumber() + "_" + reportParameters.getReportRowNumber();
        }
    }
}
