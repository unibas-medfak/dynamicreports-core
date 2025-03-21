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
package ch.unibas.medizin.dynamicreports.test.jasper.templatedesign;

import ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.column.TextColumnBuilder;
import ch.unibas.medizin.dynamicreports.report.datasource.DRDataSource;
import ch.unibas.medizin.dynamicreports.report.exception.DRException;
import ch.unibas.medizin.dynamicreports.test.jasper.AbstractJasperPositionTest;
import net.sf.jasperreports.engine.JRDataSource;

import java.io.InputStream;
import java.io.Serial;
import java.io.Serializable;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.col;

/**
 * @author Ricardo Mariaca
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TemplateDesignPosition1Test extends AbstractJasperPositionTest implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private TextColumnBuilder<String> column1;
    private TextColumnBuilder<Integer> column2;

    @Override
    protected void configureReport(JasperReportBuilder rb) throws DRException {
        InputStream is = TemplateDesignPosition1Test.class.getResourceAsStream("templatedesign1.jrxml");
        rb.setTemplateDesign(is).columns(column1 = col.column("Column1", "field1", String.class), column2 = col.column("Column2", "field2", Integer.class));
    }

    @Override
    @Test
    public void test() {
        super.test();

        numberOfPagesTest(1);

        elementPositionTest("columnHeader.list1", 0, 20, 134, 555, 16);
        columnTitlePositionTest(column1, 0, 0, 0, 277, 16);
        columnTitlePositionTest(column2, 0, 277, 0, 278, 16);
        elementPositionTest("detail.list1", 0, 20, 171, 555, 16);
        columnDetailPositionTest(column1, 0, 0, 0, 277, 16);
        columnDetailPositionTest(column2, 0, 277, 0, 278, 16);
        elementPositionTest("detail.list1", 1, 20, 208, 555, 16);
        columnDetailPositionTest(column1, 1, 0, 0, 277, 16);
        columnDetailPositionTest(column2, 1, 277, 0, 278, 16);

        elementPositionTest("templateDesign.title1", 0, 146, 48, 100, 20);
        elementPositionTest("templateDesign.pageHeader", 0, 146, 112, 100, 20);
        elementPositionTest("templateDesign.pageFooter", 0, 146, 785, 100, 20);
        elementPositionTest("templateDesign.detail", 0, 146, 150, 100, 20);
    }

    @Override
    protected JRDataSource createDataSource() {
        DRDataSource dataSource = new DRDataSource("field1", "field2");
        for (int i = 0; i < 2; i++) {
            dataSource.add("row" + i, i);
        }
        return dataSource;
    }
}
