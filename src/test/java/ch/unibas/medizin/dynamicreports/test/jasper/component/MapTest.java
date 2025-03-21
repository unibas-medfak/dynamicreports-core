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

import java.io.Serializable;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder;
import ch.unibas.medizin.dynamicreports.test.jasper.AbstractJasperChartTest;
import net.sf.jasperreports.engine.fill.JRTemplateGenericPrintElement;

/**
 * @author Ricardo Mariaca
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MapTest extends AbstractJasperChartTest implements Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    protected void configureReport(JasperReportBuilder rb) {
        rb.title(cmp.map(40.7f, -74f, 12));
    }

    @Override
    @Test
    public void test() {
        super.test();

        numberOfPagesTest(1);

        final JRTemplateGenericPrintElement map = (JRTemplateGenericPrintElement) getJasperPrint().getPages().get(0).getElements().get(0);
        Assertions.assertEquals(map.getParameterValue("latitude"), 40.7f,"latitude");
        Assertions.assertEquals(map.getParameterValue("longitude"), -74f,"longitude");
        Assertions.assertEquals(map.getParameterValue("zoom"), 12, "zoom");
    }
}
