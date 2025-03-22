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
package ch.unibas.medizin.dynamicreports.test.jasper.column;

import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.col;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.type;

import java.io.Serial;
import java.io.Serializable;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.column.TextColumnBuilder;
import ch.unibas.medizin.dynamicreports.report.datasource.DRDataSource;
import ch.unibas.medizin.dynamicreports.test.jasper.AbstractJasperValueTest;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Column4Test extends AbstractJasperValueTest implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private TextColumnBuilder<String> column1;
    private TextColumnBuilder<Integer> column2;

    @Override
    protected void configureReport(JasperReportBuilder rb) {
        rb.columns(column1 = col.column("Column1", "field1", type.stringType()).setPrintRepeatedDetailValues(false).setPrintInFirstWholeBand(true),
                   column2 = col.column("Column2", "field2", type.integerType()));
    }

    @Override
    @Test
    public void test() {
        super.test();

        numberOfPagesTest(2);
        // column1
        columnDetailCountTest(column1, 3);
        columnDetailValueTest(column1, "test1", "test2", "test2");
        columnTitleCountTest(column1, 2);
        columnTitleValueTest(column1, "Column1", "Column1");
        // column2
        columnDetailCountTest(column2, 60);
        columnDetailValueTest(column2, 0, "0");
        columnTitleCountTest(column2, 2);
        columnTitleValueTest(column2, "Column2", "Column2");
    }

    @Override
    protected JRDataSource createDataSource() {
        final DRDataSource dataSource = new DRDataSource("field1", "field2");
        for (int i = 0; i < 30; i++) {
            dataSource.add("test1", i);
        }
        for (int i = 0; i < 30; i++) {
            dataSource.add("test2", i);
        }
        return dataSource;
    }
}
