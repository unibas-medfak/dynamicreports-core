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

import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.cmp;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.col;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.column.TextColumnBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.component.MultiPageListBuilder;
import ch.unibas.medizin.dynamicreports.report.datasource.DRDataSource;
import ch.unibas.medizin.dynamicreports.test.jasper.AbstractJasperPositionTest;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MultiPageList2Test extends AbstractJasperPositionTest {
    private TextColumnBuilder<Integer> column1;

    @Override
    protected void configureReport(JasperReportBuilder rb) {
        final MultiPageListBuilder multiPageList1 = cmp.multiPageList();
        for (int i = 0; i < 3; i++) {
            final MultiPageListBuilder multiPageList2 = cmp.multiPageList();
            multiPageList1.add(multiPageList2);
            for (int j = 0; j < 50; j++) {
                multiPageList2.add(cmp.text("text"));
            }
        }
        rb.title(multiPageList1).columns(column1 = col.column("Column1", "field1", Integer.class));
    }

    @Override
    @Test
    public void test() {
        super.test();

        numberOfPagesTest(3);

        for (int i = 0; i < 51; i++) {
            elementPositionTest("title.textField1", i, 10, 10 + i * 16, 575, 16);
        }
        for (int i = 0; i < 51; i++) {
            elementPositionTest("title.textField1", 51 + i, 10, 10 + i * 16, 575, 16);
        }
        for (int i = 0; i < 48; i++) {
            elementPositionTest("title.textField1", 102 + i, 10, 10 + i * 16, 575, 16);
        }

        columnTitlePositionTest(column1, 0, 10, 778, 575, 16);
        columnDetailPositionTest(column1, 0, 10, 794, 575, 16);
        columnDetailPositionTest(column1, 1, 10, 810, 575, 16);
    }

    @Override
    protected JRDataSource createDataSource() {
        final DRDataSource dataSource = new DRDataSource("field1");
        for (int i = 0; i < 2; i++) {
            dataSource.add(i);
        }
        return dataSource;
    }
}
