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
package ch.unibas.medizin.dynamicreports.test.jasper.component;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder;
import ch.unibas.medizin.dynamicreports.report.datasource.DRDataSource;
import ch.unibas.medizin.dynamicreports.test.jasper.AbstractJasperValueTest;
import net.sf.jasperreports.engine.JRDataSource;

import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.cmp;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.col;

/**
 * @author Ricardo Mariaca
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PageNumber2Test extends AbstractJasperValueTest {

    @Override
    protected void configureReport(JasperReportBuilder rb) {
        rb.columns(col.column("Column1", "field1", Integer.class))
          .summaryOnANewPage()
          .summaryWithPageHeaderAndFooter()
          .summary(cmp.text("summary"))
          .pageFooter(cmp.pageNumber(), cmp.totalPages(), cmp.pageXslashY(), cmp.pageXofY());
    }

    @Override
    @Test
    public void test() {
        super.test();

        numberOfPagesTest(2);
        elementCountTest("pageFooter.textField1", 2);
        elementValueTest("pageFooter.textField1", "1", "2");

        elementCountTest("pageFooter.textField2", 2);
        elementValueTest("pageFooter.textField2", "2", "2");

        elementCountTest("pageFooter.textField3", 2);
        elementValueTest("pageFooter.textField3", "1", "2");

        elementCountTest("pageFooter.textField4", 2);
        elementValueTest("pageFooter.textField4", "/2", "/2");

        elementCountTest("pageFooter.textField5", 2);
        elementValueTest("pageFooter.textField5", "1", "2");

        elementCountTest("pageFooter.textField6", 2);
        elementValueTest("pageFooter.textField6", " of 2", " of 2");
    }

    @Override
    protected JRDataSource createDataSource() {
        DRDataSource dataSource = new DRDataSource("field1");
        for (int i = 0; i < 10; i++) {
            dataSource.add(i);
        }
        return dataSource;
    }
}
