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
package ch.unibas.medizin.dynamicreports.test.jasper.dataset;

import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.ctab;

import java.io.Serial;
import java.io.Serializable;
import java.util.Locale;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder;
import ch.unibas.medizin.dynamicreports.report.base.expression.AbstractSimpleExpression;
import ch.unibas.medizin.dynamicreports.report.builder.crosstab.CrosstabBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.crosstab.CrosstabColumnGroupBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.crosstab.CrosstabMeasureBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.crosstab.CrosstabRowGroupBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.crosstab.CrosstabVariableBuilder;
import ch.unibas.medizin.dynamicreports.report.constant.Calculation;
import ch.unibas.medizin.dynamicreports.report.datasource.DRDataSource;
import ch.unibas.medizin.dynamicreports.report.definition.ReportParameters;
import ch.unibas.medizin.dynamicreports.test.jasper.AbstractJasperCrosstabValueTest;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CrosstabDataset3Test extends AbstractJasperCrosstabValueTest implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private CrosstabRowGroupBuilder<String> rowGroup;
    private CrosstabColumnGroupBuilder<String> columnGroup;
    private CrosstabVariableBuilder<Integer> variable1;
    private CrosstabMeasureBuilder<Integer> measure1;
    private CrosstabMeasureBuilder<Double> measure2;
    private CrosstabMeasureBuilder<Double> measure3;

    @Override
    protected void configureReport(JasperReportBuilder rb) {
        variable1 = ctab.variable("field4", Integer.class, Calculation.SUM);
        measure1 = ctab.measure("field3", Integer.class, Calculation.SUM);
        measure2 = ctab.measure(new MeasureExpression1(), Calculation.SUM);
        measure3 = ctab.measure(new MeasureExpression2());

        final CrosstabBuilder crosstab = ctab.crosstab()
                                       .setDataSource(createCrosstabDataSource())
                                       .rowGroups(rowGroup = ctab.rowGroup("field1", String.class))
                                       .columnGroups(columnGroup = ctab.columnGroup("field2", String.class))
                                       .variables(variable1)
                                       .measures(measure1, measure2, measure3);

        rb.setLocale(Locale.ENGLISH).addParameter("parameter", "parameter_value").title(crosstab);
    }

    @Override
    @Test
    public void test() {
        super.test();

        numberOfPagesTest(1);

        setCrosstabBand("title");

        // column group
        crosstabGroupHeaderCountTest(columnGroup, 2);
        crosstabGroupHeaderValueTest(columnGroup, "c", "d");
        crosstabGroupTotalHeaderCountTest(columnGroup, 1);
        crosstabGroupTotalHeaderValueTest(columnGroup, "Total");

        // row group
        crosstabGroupHeaderCountTest(rowGroup, 2);
        crosstabGroupHeaderValueTest(rowGroup, "a", "b");
        crosstabGroupTotalHeaderCountTest(rowGroup, 1);
        crosstabGroupTotalHeaderValueTest(rowGroup, "Total");

        // measure1
        crosstabCellCountTest(measure1, null, null, 4);
        crosstabCellValueTest(measure1, null, null, "15", "11", "7", "3");
        crosstabCellCountTest(measure1, null, columnGroup, 2);
        crosstabCellValueTest(measure1, null, columnGroup, "26", "10");
        crosstabCellCountTest(measure1, rowGroup, null, 2);
        crosstabCellValueTest(measure1, rowGroup, null, "22", "14");
        crosstabCellCountTest(measure1, rowGroup, columnGroup, 1);
        crosstabCellValueTest(measure1, rowGroup, columnGroup, "36");

        // measure2
        crosstabCellCountTest(measure2, null, null, 4);
        crosstabCellValueTest(measure2, null, null, "15.0", "11.0", "7.0", "3.0");
        crosstabCellCountTest(measure2, null, columnGroup, 2);
        crosstabCellValueTest(measure2, null, columnGroup, "26.0", "10.0");
        crosstabCellCountTest(measure2, rowGroup, null, 2);
        crosstabCellValueTest(measure2, rowGroup, null, "22.0", "14.0");
        crosstabCellCountTest(measure2, rowGroup, columnGroup, 1);
        crosstabCellValueTest(measure2, rowGroup, columnGroup, "36.0");

        // measure3
        crosstabCellCountTest(measure3, null, null, 4);
        crosstabCellValueTest(measure3, null, null, "7.5", "5.5", "3.5", "1.5");
        crosstabCellCountTest(measure3, null, columnGroup, 2);
        crosstabCellValueTest(measure3, null, columnGroup, "6.5", "2.5");
        crosstabCellCountTest(measure3, rowGroup, null, 2);
        crosstabCellValueTest(measure3, rowGroup, null, "5.5", "3.5");
        crosstabCellCountTest(measure3, rowGroup, columnGroup, 1);
        crosstabCellValueTest(measure3, rowGroup, columnGroup, "4.5");
    }

    private JRDataSource createCrosstabDataSource() {
        final DRDataSource dataSource = new DRDataSource("field1", "field2", "field3", "field4");
        dataSource.add("a", "c", 8, 1);
        dataSource.add("a", "c", 7, 1);
        dataSource.add("a", "d", 6, 1);
        dataSource.add("a", "d", 5, 1);
        dataSource.add("b", "c", 4, 1);
        dataSource.add("b", "c", 3, 1);
        dataSource.add("b", "d", 2, 1);
        dataSource.add("b", "d", 1, 1);
        return dataSource;
    }

    private static class MeasureExpression1 extends AbstractSimpleExpression<Double> {
        @Serial
        private static final long serialVersionUID = 1L;

        @Override
        public Double evaluate(ReportParameters reportParameters) {
            Assertions.assertNotNull(reportParameters.getMasterParameters());
            try {
                reportParameters.getValue("parameter");
                Assertions.fail("parameter is not null");
            } catch (final Exception ignored) {
            }
            Assertions.assertEquals("parameter_value", reportParameters.getMasterParameters().getValue("parameter"));
            final Integer value1 = reportParameters.getValue("field3");
            final Integer value2 = reportParameters.getValue("field4");
            return value1.doubleValue() / value2.doubleValue();
        }
    }

    private class MeasureExpression2 extends AbstractSimpleExpression<Double> {
        @Serial
        private static final long serialVersionUID = 1L;

        @Override
        public Double evaluate(ReportParameters reportParameters) {
            Assertions.assertNotNull(reportParameters.getMasterParameters());
            try {
                reportParameters.getValue("parameter");
                Assertions.fail("parameter is not null");
            } catch (final Exception ignored) {
            }
            Assertions.assertEquals("parameter_value", reportParameters.getMasterParameters().getValue("parameter"));
            final Integer value1 = reportParameters.getValue(measure1);
            final Integer value2 = reportParameters.getValue(variable1);
            return value1.doubleValue() / value2.doubleValue();
        }
    }
}
