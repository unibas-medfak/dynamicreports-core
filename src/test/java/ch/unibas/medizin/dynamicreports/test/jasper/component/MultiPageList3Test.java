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
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.report;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.type;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.column.TextColumnBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.component.MultiPageListBuilder;
import ch.unibas.medizin.dynamicreports.report.datasource.DRDataSource;
import ch.unibas.medizin.dynamicreports.test.jasper.AbstractJasperValueTest;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MultiPageList3Test extends AbstractJasperValueTest {
    private TextColumnBuilder<Integer> column1;

    @Override
    protected void configureReport(JasperReportBuilder rb) {
        column1 = col.column("Column1", "field1", type.integerType());

        final MultiPageListBuilder multiPageList = cmp.multiPageList();
        multiPageList.add(cmp.subreport(createSubreport(80)));
        multiPageList.newPage();
        multiPageList.add(cmp.subreport(createSubreport(80)));
        multiPageList.newPage();
        multiPageList.add(cmp.subreport(createSubreport(10)));
        multiPageList.newPage();
        multiPageList.add(cmp.subreport(createSubreport(10)));
        rb.title(multiPageList);
    }

    @Override
    @Test
    public void test() {
        super.test();

        numberOfPagesTest(4);

        columnDetailAtPageIndexTest(column1, 0);
        columnDetailAtPageIndexTest(column1, 1);
        columnDetailAtPageIndexTest(column1, 2);
        columnDetailAtPageIndexTest(column1, 3);
    }

    private JasperReportBuilder createSubreport(int numberOfRecords) {
        final JasperReportBuilder report = report();
        report.title(cmp.verticalGap(6)).setPageColumnsPerPage(2).columns(column1).setDataSource(createSubreportDataSource(numberOfRecords));

        return report;
    }

    protected JRDataSource createSubreportDataSource(int numberOfRecords) {
        final DRDataSource dataSource = new DRDataSource("field1");
        for (int i = 0; i < numberOfRecords; i++) {
            dataSource.add(i);
        }
        return dataSource;
    }
}
