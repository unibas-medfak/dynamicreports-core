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

import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.col;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.sbt;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.stl;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.type;

import java.io.Serial;
import java.io.Serializable;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.column.TextColumnBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.style.StyleBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.subtotal.AggregationSubtotalBuilder;
import ch.unibas.medizin.dynamicreports.report.datasource.DRDataSource;
import ch.unibas.medizin.dynamicreports.test.jasper.AbstractJasperPositionTest;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CustomFontTest extends AbstractJasperPositionTest implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private TextColumnBuilder<String> column1;
    private AggregationSubtotalBuilder<String> subtotal1;

    @Override
    protected void configureReport(JasperReportBuilder rb) {
        final StyleBuilder style = stl.style().setFontName("FreeUniversal");

        rb.columns(column1 = col.column("Column1", "field1", type.stringType())).subtotalsAtColumnFooter(subtotal1 = sbt.text("text", column1).setStyle(style));
    }

    @Override
    @Test
    public void test() {
        super.test();

        numberOfPagesTest(1);

        // column1
        columnTitlePositionTest(column1, 0, 10, 10, 575, 16);
        columnDetailPositionTest(column1, 0, 10, 26, 575, 16);

        // subtotal1
        subtotalPositionTest(subtotal1, 0, 10, 818, 575, 14);
    }

    @Override
    protected JRDataSource createDataSource() {
        final DRDataSource dataSource = new DRDataSource("field1");
        dataSource.add("1");
        return dataSource;
    }
}
