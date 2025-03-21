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

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.column.TextColumnBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.group.ColumnGroupBuilder;
import ch.unibas.medizin.dynamicreports.report.datasource.DRDataSource;
import ch.unibas.medizin.dynamicreports.test.jasper.AbstractJasperPositionTest;
import net.sf.jasperreports.engine.JRDataSource;

import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.col;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.grp;

/**
 * @author Ricardo Mariaca
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GroupPosition1Test extends AbstractJasperPositionTest {

    private ColumnGroupBuilder group1;
    private TextColumnBuilder<Integer> column2;

    @Override
    protected void configureReport(JasperReportBuilder rb) {
        TextColumnBuilder<String> column1;

        rb.columns(column1 = col.column("Column1", "field1", String.class), column2 = col.column("Column2", "field2", Integer.class)).groupBy(group1 = grp.group(column1));
    }

    @Override
    @Test
    public void test() {
        super.test();

        numberOfPagesTest(1);
        elementPositionTest("columnHeader.list1", 0, 10, 10, 575, 16);
        elementPositionTest("columnHeader.filler1", 0, 0, 0, 10, 16);
        elementPositionTest("detail.list1", 0, 10, 42, 575, 16);
        elementPositionTest("detail.list1", 1, 10, 58, 575, 16);

        // group1
        groupHeaderPositionTest(group1, 0, 10, 26, 575, 16);
        // column2
        columnTitlePositionTest(column2, 0, 10, 0, 565, 16);
        columnDetailPositionTest(column2, 0, 10, 0, 565, 16);
        columnDetailPositionTest(column2, 1, 10, 0, 565, 16);
    }

    @Override
    protected JRDataSource createDataSource() {
        DRDataSource dataSource = new DRDataSource("field1", "field2");
        for (int i = 0; i < 2; i++) {
            dataSource.add("group1", i);
        }
        return dataSource;
    }
}
