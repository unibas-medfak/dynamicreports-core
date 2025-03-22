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

import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.cmp;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder;
import ch.unibas.medizin.dynamicreports.report.base.expression.AbstractSimpleExpression;
import ch.unibas.medizin.dynamicreports.report.definition.ReportParameters;
import ch.unibas.medizin.dynamicreports.report.exception.DRException;
import ch.unibas.medizin.dynamicreports.test.jasper.AbstractJasperValueTest;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

/**
 * @author Ricardo Mariaca
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ParametersTest extends AbstractJasperValueTest implements Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    protected void configureReport(JasperReportBuilder rb) {
        rb.title(cmp.text(new TitleExpression())).addParameter("title", String.class);
    }

    @Override
    @Test
    public void test() {
        super.test();

        numberOfPagesTest(1);

        elementValueTest("title.textField1", "");

        try {
            JasperReport jasperReport = getJasperReport();
            JasperPrint jasperPrint = getJasperPrint();
            getReportBuilder().setParameter("title", "1");
            build();
            elementValueTest("title.textField1", "1");
            Assertions.assertSame(jasperReport, getJasperReport());
            Assertions.assertNotSame(jasperPrint, getJasperPrint());

            jasperReport = getJasperReport();
            jasperPrint = getJasperPrint();
            getReportBuilder().setParameter("title", "2");
            build();
            elementValueTest("title.textField1", "2");
            Assertions.assertSame(jasperReport, getJasperReport());
            Assertions.assertNotSame(jasperPrint, getJasperPrint());

            jasperReport = getJasperReport();
            jasperPrint = getJasperPrint();
            final Map<String, Object> parameters = new HashMap<>();
            parameters.put("title", "3");
            getReportBuilder().setParameters(parameters);
            build();
            elementValueTest("title.textField1", "3");
            Assertions.assertSame(jasperReport, getJasperReport());
            Assertions.assertNotSame(jasperPrint, getJasperPrint());
        } catch (final DRException e) {
            e.printStackTrace();
            Assertions.fail(e.getMessage());
        }
    }

    private static class TitleExpression extends AbstractSimpleExpression<String> {
        private static final long serialVersionUID = 1L;

        @Override
        public String evaluate(ReportParameters reportParameters) {
            return reportParameters.getValue("title");
        }

    }
}
