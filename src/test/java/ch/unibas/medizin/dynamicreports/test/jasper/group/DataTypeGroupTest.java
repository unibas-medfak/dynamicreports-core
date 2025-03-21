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
package ch.unibas.medizin.dynamicreports.test.jasper.group;

import ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.column.TextColumnBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.group.ColumnGroupBuilder;
import ch.unibas.medizin.dynamicreports.report.datasource.DRDataSource;
import ch.unibas.medizin.dynamicreports.test.jasper.AbstractJasperValueTest;
import net.sf.jasperreports.engine.JRDataSource;

import java.util.Date;
import java.util.Locale;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.col;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.grp;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.type;

/**
 * @author Ricardo Mariaca
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DataTypeGroupTest extends AbstractJasperValueTest {
    private TextColumnBuilder<Date> column1;
    private TextColumnBuilder<Date> column2;
    private ColumnGroupBuilder group1;

    @Override
    protected void configureReport(JasperReportBuilder rb) {
        rb.setLocale(Locale.ENGLISH)
          .columns(column1 = col.column("Column1", "field1", type.dateMonthType()), column2 = col.column("Column2", "field1", type.dateType()))
          .groupBy(group1 = grp.group(column1).groupByDataType());
    }

    @Override
    @Test
    public void test() {
        super.test();

        numberOfPagesTest(1);

        // column2
        columnTitleCountTest(column2, 1);
        columnTitleValueTest(column2, "Column2");
        columnDetailCountTest(column2, 2);
        columnDetailValueTest(column2, "01/01/2010", "02/01/2010");

        // group1
        groupHeaderTitleCountTest(group1, 0);
        groupHeaderCountTest(group1, 2);
        groupHeaderValueTest(group1, "January", "February");
    }

    @Override
    protected JRDataSource createDataSource() {
        DRDataSource dataSource = new DRDataSource("field1");
        for (int i = 0; i < 1; i++) {
            dataSource.add(toDate(2010, 1, 1));
            dataSource.add(toDate(2010, 2, 1));
        }
        return dataSource;
    }
}
