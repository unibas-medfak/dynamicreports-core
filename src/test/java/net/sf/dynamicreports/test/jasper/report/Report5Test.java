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
package net.sf.dynamicreports.test.jasper.report;

import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;
import static net.sf.dynamicreports.report.builder.DynamicReports.exp;
import static net.sf.dynamicreports.report.builder.DynamicReports.report;

import java.util.HashMap;
import java.util.ListResourceBundle;
import java.util.Locale;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.builder.component.SubreportBuilder;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.test.jasper.AbstractJasperValueTest;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperPrint;

/**
 * @author Ricardo Mariaca
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Report5Test extends AbstractJasperValueTest {

    @Override
    protected void configureReport(JasperReportBuilder rb) {
        final SubreportBuilder detailSubreport = cmp.subreport(detailSubreport()).setParameters(new SubreportParametersExpression());

        rb.title(cmp.text(exp.jasperSyntax("$R{bundleKey1}", String.class))).detail(detailSubreport).setLocale(Locale.ENGLISH).setResourceBundle(new ResourceBundle());
    }

    @Override
    @Test
    public void test() {
        super.test();

        numberOfPagesTest(1);

        elementCountTest("title.textField1", 3);
        elementValueTest("title.textField1", "bundleValue1", "bundleValue1", "bundleValue1");

        final JasperPrint jasperPrint = getJasperPrint();
        Assertions.assertEquals(Locale.ENGLISH.getLanguage(), jasperPrint.getLocaleCode());
    }

    @Override
    protected boolean serializableTest() {
        return false;
    }

    @Override
    protected JRDataSource createDataSource() {
        return new JREmptyDataSource(2);
    }

    private JasperReportBuilder detailSubreport() {
        final JasperReportBuilder report = report();
        report.title(cmp.text(exp.jasperSyntax("$R{bundleKey1}", String.class)));
        return report;
    }

    private class SubreportParametersExpression extends AbstractSimpleExpression<Map<String, Object>> {
        private static final long serialVersionUID = 1L;

        @Override
        public Map<String, Object> evaluate(ReportParameters reportParameters) {
            final Map<String, Object> parameters = new HashMap<>();
            parameters.put(JRParameter.REPORT_LOCALE, reportParameters.getParameterValue(JRParameter.REPORT_LOCALE));
            parameters.put(JRParameter.REPORT_RESOURCE_BUNDLE, reportParameters.getParameterValue(JRParameter.REPORT_RESOURCE_BUNDLE));
            return parameters;
        }
    }

    private class ResourceBundle extends ListResourceBundle {

        @Override
        protected Object[][] getContents() {
            return new Object[][] {{"bundleKey1", "bundleValue1"}};
        }
    }
}
