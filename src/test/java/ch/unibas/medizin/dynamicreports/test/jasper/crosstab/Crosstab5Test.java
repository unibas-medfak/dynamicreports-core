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
package ch.unibas.medizin.dynamicreports.test.jasper.crosstab;

import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.ctab;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.exp;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.field;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.type;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.FieldBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.crosstab.CrosstabBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.crosstab.CrosstabColumnGroupBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.crosstab.CrosstabMeasureBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.crosstab.CrosstabRowGroupBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.crosstab.CrosstabVariableBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.expression.AbstractComplexExpression;
import ch.unibas.medizin.dynamicreports.report.constant.Calculation;
import ch.unibas.medizin.dynamicreports.report.constant.CrosstabPercentageType;
import ch.unibas.medizin.dynamicreports.report.datasource.DRDataSource;
import ch.unibas.medizin.dynamicreports.report.definition.ReportParameters;
import ch.unibas.medizin.dynamicreports.test.jasper.AbstractJasperCrosstabValueTest;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Crosstab5Test extends AbstractJasperCrosstabValueTest implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private CrosstabRowGroupBuilder<String> rowGroup;
    private CrosstabColumnGroupBuilder<String> columnGroup;
    private CrosstabVariableBuilder<Integer> variable1;
    private CrosstabMeasureBuilder<Integer> measure1;
    private CrosstabMeasureBuilder<?> measure2;
    private CrosstabMeasureBuilder<BigDecimal> measure3;
    private CrosstabMeasureBuilder<BigDecimal> measure4;
    private CrosstabMeasureBuilder<BigDecimal> measure5;

    @Override
    protected void configureReport(JasperReportBuilder rb) {
        rowGroup = ctab.rowGroup("field1", String.class);
        columnGroup = ctab.columnGroup("field2", String.class);

        final FieldBuilder<Integer> field = field("field3", Integer.class);
        variable1 = ctab.variable(field, Calculation.SUM);
        measure1 = ctab.measure(field, Calculation.SUM);
        measure2 = ctab.measure(field, Calculation.SUM);
        measure2.setPercentageType(CrosstabPercentageType.GRAND_TOTAL);
        measure3 = ctab.measure(new PercentageExpression1());
        measure3.setDataType(type.doubleType());
        final String expression = "$V{" + measure1.getName() + "}.doubleValue()/$V{" + measure1.getName() + "_" + rowGroup.getName() + "_" + columnGroup.getName() + "_ALL}.doubleValue() * 100";
        measure4 = ctab.measure(exp.jasperSyntax(expression, Double.class));
        measure4.setDataType(type.doubleType());
        measure5 = ctab.measure(new PercentageExpression2());
        measure5.setDataType(type.doubleType());

        final CrosstabBuilder crosstab = ctab.crosstab().rowGroups(rowGroup).columnGroups(columnGroup).variables(variable1).measures(measure1, measure2, measure3, measure4, measure5);

        rb.setLocale(Locale.ENGLISH).summary(crosstab);
    }

    @Override
    @Test
    public void test() {
        super.test();

        numberOfPagesTest(1);

        setCrosstabBand("summary");

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
        crosstabCellValueTest(measure1, null, null, "3", "7", "11", "15");
        crosstabCellCountTest(measure1, null, columnGroup, 2);
        crosstabCellValueTest(measure1, null, columnGroup, "10", "26");
        crosstabCellCountTest(measure1, rowGroup, null, 2);
        crosstabCellValueTest(measure1, rowGroup, null, "14", "22");
        crosstabCellCountTest(measure1, rowGroup, columnGroup, 1);
        crosstabCellValueTest(measure1, rowGroup, columnGroup, "36");

        // measure2
        crosstabCellCountTest(measure2, null, null, 4);
        crosstabCellValueTest(measure2, null, null, "8.3", "19.4", "30.6", "41.7");
        crosstabCellCountTest(measure2, null, columnGroup, 2);
        crosstabCellValueTest(measure2, null, columnGroup, "27.8", "72.2");
        crosstabCellCountTest(measure2, rowGroup, null, 2);
        crosstabCellValueTest(measure2, rowGroup, null, "38.9", "61.1");
        crosstabCellCountTest(measure2, rowGroup, columnGroup, 1);
        crosstabCellValueTest(measure2, rowGroup, columnGroup, "100");

        // measure3
        crosstabCellCountTest(measure3, null, null, 4);
        crosstabCellValueTest(measure3, null, null, "8.3", "19.4", "30.6", "41.7");
        crosstabCellCountTest(measure3, null, columnGroup, 2);
        crosstabCellValueTest(measure3, null, columnGroup, "27.8", "72.2");
        crosstabCellCountTest(measure3, rowGroup, null, 2);
        crosstabCellValueTest(measure3, rowGroup, null, "38.9", "61.1");
        crosstabCellCountTest(measure3, rowGroup, columnGroup, 1);
        crosstabCellValueTest(measure3, rowGroup, columnGroup, "100");

        // measure4
        crosstabCellCountTest(measure4, null, null, 4);
        crosstabCellValueTest(measure4, null, null, "8.3", "19.4", "30.6", "41.7");
        crosstabCellCountTest(measure4, null, columnGroup, 2);
        crosstabCellValueTest(measure4, null, columnGroup, "27.8", "72.2");
        crosstabCellCountTest(measure4, rowGroup, null, 2);
        crosstabCellValueTest(measure4, rowGroup, null, "38.9", "61.1");
        crosstabCellCountTest(measure4, rowGroup, columnGroup, 1);
        crosstabCellValueTest(measure4, rowGroup, columnGroup, "100");

        // measure5
        crosstabCellCountTest(measure5, null, null, 4);
        crosstabCellValueTest(measure5, null, null, "21.4", "31.8", "78.6", "68.2");
        crosstabCellCountTest(measure5, null, columnGroup, 2);
        crosstabCellValueTest(measure5, null, columnGroup, "27.8", "72.2");
        crosstabCellCountTest(measure5, rowGroup, null, 2);
        crosstabCellValueTest(measure5, rowGroup, null, "100", "100");
        crosstabCellCountTest(measure5, rowGroup, columnGroup, 1);
        crosstabCellValueTest(measure5, rowGroup, columnGroup, "100");
    }

    @Override
    protected JRDataSource createDataSource() {
        final DRDataSource dataSource = new DRDataSource("field1", "field2", "field3");
        dataSource.add("a", "c", 1);
        dataSource.add("a", "c", 2);
        dataSource.add("a", "d", 3);
        dataSource.add("a", "d", 4);
        dataSource.add("b", "c", 5);
        dataSource.add("b", "c", 6);
        dataSource.add("b", "d", 7);
        dataSource.add("b", "d", 8);
        return dataSource;
    }

    private class PercentageExpression1 extends AbstractComplexExpression<BigDecimal> {
        @Serial
        private static final long serialVersionUID = 1L;

        private PercentageExpression1() {
            addExpression(exp.crosstabValue(measure1));
            addExpression(exp.crosstabValue(measure1, rowGroup, columnGroup));
        }

        @Override
        public BigDecimal evaluate(List<?> values, ReportParameters reportParameters) {
            final Integer value1 = (Integer) values.get(0);
            final Integer value2 = (Integer) values.get(1);
            return new BigDecimal(value1).divide(new BigDecimal(value2), 3, RoundingMode.HALF_UP).multiply(new BigDecimal(100));
        }
    }

    private class PercentageExpression2 extends AbstractComplexExpression<BigDecimal> {
        @Serial
        private static final long serialVersionUID = 1L;

        private PercentageExpression2() {
            addExpression(exp.crosstabValue(variable1));
            addExpression(exp.crosstabValue(measure1, rowGroup));
            addExpression(exp.jasperSyntax("$V{" + measure1.getName() + "_" + rowGroup.getName() + "_ALL}", Integer.class));
        }

        @Override
        public BigDecimal evaluate(List<?> values, ReportParameters reportParameters) {
            final Integer value1 = (Integer) values.get(0);
            final Integer value2 = (Integer) values.get(1);
            final Integer measure1Value = reportParameters.getValue(measure1);
            Assertions.assertEquals(value1, measure1Value,"measure value");
            Assertions.assertEquals(value2, values.get(2),"measure value");
            return new BigDecimal(value1).divide(new BigDecimal(value2), 3, RoundingMode.HALF_UP).multiply(new BigDecimal(100));
        }
    }
}
