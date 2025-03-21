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
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.margin;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.report;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.type;

import java.io.Serial;
import java.io.Serializable;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder;
import ch.unibas.medizin.dynamicreports.report.base.expression.AbstractSimpleExpression;
import ch.unibas.medizin.dynamicreports.report.builder.component.Components;
import ch.unibas.medizin.dynamicreports.report.builder.component.SubreportBuilder;
import ch.unibas.medizin.dynamicreports.report.datasource.DRDataSource;
import ch.unibas.medizin.dynamicreports.report.definition.ReportParameters;
import ch.unibas.medizin.dynamicreports.test.jasper.AbstractJasperPositionTest;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;

/**
 * @author Ricardo Mariaca
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SubreportPositionTest extends AbstractJasperPositionTest implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Override
    protected void configureReport(JasperReportBuilder rb) {
        final SubreportBuilder subreport = Components.subreport(new SubreportExpression()).setDataSource(new SubreportDataSourceExpression());

        rb.detail(subreport, cmp.filler().setFixedHeight(20)).summary(cmp.subreport(subreport2()));
    }

    @Override
    @Test
    public void test() {
        super.test();

        numberOfPagesTest(1);

        // elementPositionTest("detail.list1", 0, 10, 10, 575, 68);
        // elementPositionTest("detail.subreport1", 0, 10, 10, 575, 48);

        // elementPositionTest("detail.list1", 1, 10, 78, 575, 84);
        // elementPositionTest("detail.subreport1", 1, 10, 78, 575, 64);

        // elementPositionTest("detail.list1", 4, 10, 162, 575, 100);
        // elementPositionTest("detail.subreport1", 2, 10, 162, 575, 80);

        // title
        elementPositionTest("title.textField1", 0, 10, 10, 575, 16);
        elementPositionTest("title.textField1", 1, 10, 78, 575, 16);
        elementPositionTest("title.textField1", 2, 10, 162, 575, 16);

        // column title
        elementPositionTest("columnHeader.column_column1.title1", 0, 10, 26, 575, 16);

        elementPositionTest("columnHeader.list1", 0, 10, 94, 575, 16);
        elementPositionTest("columnHeader.column_column1.title1", 1, 0, 0, 287, 16);
        elementPositionTest("columnHeader.column_column2.title1", 0, 287, 0, 288, 16);

        elementPositionTest("columnHeader.list1", 1, 10, 178, 575, 16);
        elementPositionTest("columnHeader.column_column1.title1", 2, 0, 0, 191, 16);
        elementPositionTest("columnHeader.column_column2.title1", 1, 191, 0, 192, 16);
        elementPositionTest("columnHeader.column_column3.title1", 0, 383, 0, 192, 16);

        // column detail
        elementPositionTest("detail.column_column11", 0, 10, 42, 575, 16);

        elementPositionTest("detail.list1", 0, 10, 110, 575, 16);
        elementPositionTest("detail.column_column11", 1, 0, 0, 287, 16);
        elementPositionTest("detail.column_column21", 0, 287, 0, 288, 16);

        elementPositionTest("detail.list1", 1, 10, 126, 575, 16);
        elementPositionTest("detail.column_column11", 2, 0, 0, 287, 16);
        elementPositionTest("detail.column_column21", 1, 287, 0, 288, 16);

        elementPositionTest("detail.list1", 2, 10, 194, 575, 16);
        elementPositionTest("detail.column_column11", 3, 0, 0, 191, 16);
        elementPositionTest("detail.column_column21", 2, 191, 0, 192, 16);
        elementPositionTest("detail.column_column31", 0, 383, 0, 192, 16);

        elementPositionTest("detail.list1", 3, 10, 210, 575, 16);
        elementPositionTest("detail.column_column11", 4, 0, 0, 191, 16);
        elementPositionTest("detail.column_column21", 3, 191, 0, 192, 16);
        elementPositionTest("detail.column_column31", 1, 383, 0, 192, 16);

        elementPositionTest("detail.list1", 4, 10, 226, 575, 16);
        elementPositionTest("detail.column_column11", 5, 0, 0, 191, 16);
        elementPositionTest("detail.column_column21", 4, 191, 0, 192, 16);
        elementPositionTest("detail.column_column31", 2, 383, 0, 192, 16);

        // summary
        elementPositionTest("summary.textField1", 0, 20, 272, 555, 16);
    }

    @Override
    protected JRDataSource createDataSource() {
        return new JREmptyDataSource(3);
    }

    private JasperReportBuilder subreport2() {
        final JasperReportBuilder report = report();
        report.setPageMargin(margin(10));
        report.summary(cmp.text("subreport2"));
        return report;
    }

    private static class SubreportExpression extends AbstractSimpleExpression<JasperReportBuilder> {
        @Serial
        private static final long serialVersionUID = 1L;

        @Override
        public JasperReportBuilder evaluate(ReportParameters reportParameters) {
            final int masterRowNumber = reportParameters.getReportRowNumber();
            final JasperReportBuilder report = report();
            report.title(cmp.text("Subreport" + masterRowNumber));

            for (int i = 1; i <= masterRowNumber; i++) {
                report.addColumn(col.column("Column" + i, "column" + i, type.stringType()));
            }

            return report;
        }
    }

    private static class SubreportDataSourceExpression extends AbstractSimpleExpression<JRDataSource> {
        @Serial
        private static final long serialVersionUID = 1L;

        @Override
        public JRDataSource evaluate(ReportParameters reportParameters) {
            final int masterRowNumber = reportParameters.getReportRowNumber();
            final String[] columns = new String[masterRowNumber];
            for (int i = 1; i <= masterRowNumber; i++) {
                columns[i - 1] = "column" + i;
            }
            final DRDataSource dataSource = new DRDataSource(columns);

            for (int i = 1; i <= masterRowNumber; i++) {
                final Object[] values = new Object[masterRowNumber];
                for (int j = 1; j <= masterRowNumber; j++) {
                    values[j - 1] = "row" + i + "_column" + j;
                }
                dataSource.add(values);
            }

            return dataSource;
        }
    }
}
