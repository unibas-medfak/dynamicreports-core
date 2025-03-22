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
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.field;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.margin;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.report;

import java.io.Serial;
import java.io.Serializable;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder;
import ch.unibas.medizin.dynamicreports.report.base.expression.AbstractSimpleExpression;
import ch.unibas.medizin.dynamicreports.report.builder.component.SubreportBuilder;
import ch.unibas.medizin.dynamicreports.report.datasource.DRDataSource;
import ch.unibas.medizin.dynamicreports.report.definition.ReportParameters;
import ch.unibas.medizin.dynamicreports.test.jasper.AbstractJasperValueTest;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Subreport3Test extends AbstractJasperValueTest implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Override
    protected void configureReport(JasperReportBuilder rb) {
        final SubreportBuilder subreport1 = cmp.subreport(subreport1());
        subreport1.setDataSource(createSubreport1DataSource());

        rb.fields(field("f1", Integer.class)).detail(subreport1);
    }

    @Override
    @Test
    public void test() {
        super.test();

        numberOfPagesTest(1);

        elementCountTest("title.textField1", 6);
        elementValueTest("title.textField1", "1 3", "1 4", "1 5", "2 3", "2 4", "2 5");
    }

    @Override
    protected JRDataSource createDataSource() {
        final DRDataSource dataSource = new DRDataSource("f1");
        dataSource.add(1);
        dataSource.add(2);
        return dataSource;
    }

    private JasperReportBuilder subreport1() {
        final SubreportBuilder subreport2 = cmp.subreport(subreport2());

        final JasperReportBuilder report = report();
        report.fields(field("f2", Integer.class)).setPageMargin(margin(0)).detail(subreport2);
        return report;
    }

    public JRDataSource createSubreport1DataSource() {
        final DRDataSource dataSource = new DRDataSource("f2");
        dataSource.add(3);
        dataSource.add(4);
        dataSource.add(5);
        return dataSource;
    }

    private JasperReportBuilder subreport2() {
        final JasperReportBuilder report = report();
        report.setPageMargin(margin(0)).title(cmp.text(new SubreportTitle()));
        return report;
    }

    private static class SubreportTitle extends AbstractSimpleExpression<String> {
        @Serial
        private static final long serialVersionUID = 1L;

        @Override
        public String evaluate(ReportParameters reportParameters) {
            String result = "";
            result += reportParameters.getMasterParameters().getMasterParameters().getValue("f1");
            result += " ";
            result += reportParameters.getMasterParameters().getValue("f2");
            return result;
        }

    }
}
