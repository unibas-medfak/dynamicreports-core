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
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.report;

import java.io.Serializable;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder;
import ch.unibas.medizin.dynamicreports.report.base.expression.AbstractSimpleExpression;
import ch.unibas.medizin.dynamicreports.report.builder.component.Components;
import ch.unibas.medizin.dynamicreports.report.builder.component.SubreportBuilder;
import ch.unibas.medizin.dynamicreports.report.definition.ReportParameters;
import ch.unibas.medizin.dynamicreports.test.jasper.AbstractJasperValueTest;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;

/**
 * @author Ricardo Mariaca
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Subreport6Test extends AbstractJasperValueTest implements Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    protected void configureReport(JasperReportBuilder rb) {
        final SubreportBuilder subreport = Components.subreport(new Subreport1Expression());

        rb.detail(subreport);
    }

    @Override
    @Test
    public void test() {
        super.test();

        numberOfPagesTest(1);

        elementCountTest("title.textField1", 10);
        elementValueTest("title.textField1", "575", "565", "575", "555", "575", "545", "575", "535", "575", "525");
    }

    @Override
    protected JRDataSource createDataSource() {
        return new JREmptyDataSource(5);
    }

    private class Subreport1Expression extends AbstractSimpleExpression<JasperReportBuilder> {
        private static final long serialVersionUID = 1L;

        @Override
        public JasperReportBuilder evaluate(ReportParameters reportParameters) {
            final SubreportBuilder subreport = Components.subreport(new Subreport2Expression());

            final JasperReportBuilder report = report();
            report.title(cmp.text(reportParameters.getSubreportWidth()), cmp.horizontalList(cmp.horizontalGap(10 * reportParameters.getReportRowNumber()), subreport));

            return report;
        }
    }

    private static class Subreport2Expression extends AbstractSimpleExpression<JasperReportBuilder> {
        private static final long serialVersionUID = 1L;

        @Override
        public JasperReportBuilder evaluate(ReportParameters reportParameters) {
            final JasperReportBuilder report = report();
            report.title(cmp.text(reportParameters.getSubreportWidth()));

            return report;
        }
    }
}
