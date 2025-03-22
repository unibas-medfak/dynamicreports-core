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
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.report;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.stl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder;
import ch.unibas.medizin.dynamicreports.report.constant.BooleanComponentType;
import ch.unibas.medizin.dynamicreports.report.constant.HorizontalTextAlignment;
import ch.unibas.medizin.dynamicreports.report.constant.VerticalTextAlignment;
import ch.unibas.medizin.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperReport;

/**
 * @author Ricardo Mariaca
 */
public class BooleanColumn3Test {

    @Test
    public void test() {
        try {
            final JasperReportBuilder rb = createReport();
            final JRDataSource dataSource = createDataSource();

            final JasperReport report = rb.toJasperReport();
            final Map<String, Object> params = new HashMap<>(rb.getJasperParameters());

            final ByteArrayOutputStream bos = new ByteArrayOutputStream();
            JasperFillManager.fillReportToStream(report, bos, params, dataSource);

            JasperExportManager.exportReportToPdfStream(new ByteArrayInputStream(bos.toByteArray()), new ByteArrayOutputStream());

            bos.flush();
            bos.close();
        } catch (final Exception e) {
            e.printStackTrace();
            Assertions.fail(e.getMessage());
        }
    }

    private JasperReportBuilder createReport() {
        final JasperReportBuilder rb = report();
        rb.columns(col.booleanColumn("Column1", "field1")
                      .setComponentType(BooleanComponentType.IMAGE_CHECKBOX_1)
                      .setStyle(stl.style().setTextAlignment(HorizontalTextAlignment.CENTER, VerticalTextAlignment.TOP)));
        return rb;
    }

    private JRDataSource createDataSource() {
        final DRDataSource dataSource = new DRDataSource("field1");
        dataSource.add(true);
        dataSource.add(false);
        dataSource.add();
        return dataSource;
    }

}
