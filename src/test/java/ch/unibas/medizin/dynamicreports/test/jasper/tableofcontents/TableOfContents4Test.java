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
package ch.unibas.medizin.dynamicreports.test.jasper.tableofcontents;

import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.cmp;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.col;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.type;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import ch.unibas.medizin.dynamicreports.jasper.base.tableofcontents.JasperTocHeading;
import ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder;
import ch.unibas.medizin.dynamicreports.report.base.expression.AbstractSimpleExpression;
import ch.unibas.medizin.dynamicreports.report.builder.column.TextColumnBuilder;
import ch.unibas.medizin.dynamicreports.report.datasource.DRDataSource;
import ch.unibas.medizin.dynamicreports.report.definition.DRICustomValues;
import ch.unibas.medizin.dynamicreports.report.definition.ReportParameters;
import ch.unibas.medizin.dynamicreports.test.jasper.AbstractJasperValueTest;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TableOfContents4Test extends AbstractJasperValueTest implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Override
    protected void configureReport(JasperReportBuilder rb) {
        TextColumnBuilder<String> column1;

        rb.tableOfContents()
          .pageHeader(cmp.text(new PageHeaderExpression()))
          .columns(column1 = col.column("Column1", "field1", type.stringType()), col.column("Column2", "field2", type.stringType()))
          .groupBy(column1);
    }

    @Override
    @Test
    public void test() {
        super.test();

        numberOfPagesTest(4);

        elementCountTest("pageHeader.textField1", 3);
        elementValueTest("pageHeader.textField1", "value1", "value3", "value5");
    }

    @Override
    protected JRDataSource createDataSource() {
        final String[] values = new String[] {"value1", "value2", "value3", "value4", "value5", "value6"};
        final DRDataSource dataSource = new DRDataSource("field1", "field2");
        for (final String value : values) {
            for (int i = 0; i < 20; i++) {
                dataSource.add(value, "text");
            }
        }
        return dataSource;
    }

    private static class PageHeaderExpression extends AbstractSimpleExpression<String> {
        @Serial
        private static final long serialVersionUID = 1L;

        @Override
        public String evaluate(ReportParameters reportParameters) {
            final DRICustomValues customValues = reportParameters.getParameterValue(DRICustomValues.NAME);
            final Map<String, JasperTocHeading> tocHeadings = customValues.getTocHeadings();
            if (tocHeadings.isEmpty()) {
                return reportParameters.getValue("field1");
            }
            final List<JasperTocHeading> headings = new ArrayList<>(tocHeadings.values());
            final JasperTocHeading jasperTocHeading = headings.get(headings.size() - 1);
            return jasperTocHeading.getText();
        }
    }
}
