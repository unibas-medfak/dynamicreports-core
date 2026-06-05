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
package ch.unibas.medizin.dynamicreports.examples.miscellaneous;

import ch.unibas.medizin.dynamicreports.examples.Templates;
import ch.unibas.medizin.dynamicreports.report.base.expression.AbstractSimpleExpression;
import ch.unibas.medizin.dynamicreports.report.builder.VariableBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.column.TextColumnBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.expression.AbstractComplexExpression;
import ch.unibas.medizin.dynamicreports.report.constant.Calculation;
import ch.unibas.medizin.dynamicreports.report.constant.Evaluation;
import ch.unibas.medizin.dynamicreports.report.datasource.DRDataSource;
import ch.unibas.medizin.dynamicreports.report.definition.ReportParameters;
import ch.unibas.medizin.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;

import java.io.Serial;
import java.math.BigDecimal;
import java.util.List;

import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.cmp;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.col;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.report;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.type;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.variable;

/**
 * <p>VariableReport class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class VariableReport {

    /**
     * <p>Constructor for VariableReport.</p>
     */
    public VariableReport() {
        build();
    }

    /**
     * <p>main.</p>
     *
     * @param args an array of {@link java.lang.String} objects.
     */
    public static void main(String[] args) {
        new VariableReport();
    }

    private void build() {
        TextColumnBuilder<String> itemColumn = col.column("Item", "item", type.stringType());
        TextColumnBuilder<Integer> quantityColumn = col.column("Quantity", "quantity", type.integerType());
        TextColumnBuilder<BigDecimal> unitPriceColumn = col.column("Unit price", "unitprice", type.bigDecimalType());

        VariableBuilder<Integer> itemCount = variable(itemColumn, Calculation.COUNT);
        VariableBuilder<Integer> quantitySum = variable("quantitySum", quantityColumn, Calculation.SUM);
        VariableBuilder<Integer> priceSum = variable(new PriceExpression(quantityColumn, unitPriceColumn), Calculation.SUM);

        try {
            report().setTemplate(Templates.reportTemplate)
                    .variables(quantitySum)
                    .columns(itemColumn, quantityColumn, unitPriceColumn)
                    .title(Templates.createTitleComponent("Variable"), cmp.horizontalList(cmp.text("Item count =").setFixedWidth(80), cmp.text(itemCount)),
                           cmp.text(new QuantitySumTextExpression()).setEvaluationTime(Evaluation.REPORT), cmp.text(new UnitPriceSumTextExpression(unitPriceColumn)),
                           cmp.horizontalList(cmp.text("SUM(quantity * unit price) =").setFixedWidth(150), cmp.text(priceSum).setPattern("#,###.00")))
                    .pageFooter(Templates.footerComponent)
                    .setDataSource(createDataSource())
                    .show();
        } catch (DRException e) {
            e.printStackTrace();
        }
    }

    private JRDataSource createDataSource() {
        DRDataSource dataSource = new DRDataSource("item", "quantity", "unitprice");
        for (int i = 0; i < 30; i++) {
            dataSource.add("Book", (int) (Math.random() * 10) + 1, BigDecimal.valueOf(Math.random() * 100 + 1));
        }
        return dataSource;
    }

    private static class QuantitySumTextExpression extends AbstractSimpleExpression<String> {
        @Serial
        private static final long serialVersionUID = 1L;

        @Override
        public String evaluate(ReportParameters reportParameters) {
            Integer quantitySum = reportParameters.getValue("quantitySum");
            return "Quantity sum = " + quantitySum;
        }
    }

    private static class UnitPriceSumTextExpression extends AbstractComplexExpression<String> {
        @Serial
        private static final long serialVersionUID = 1L;

        public UnitPriceSumTextExpression(TextColumnBuilder<BigDecimal> unitPriceColumn) {
            addExpression(variable(unitPriceColumn, Calculation.SUM));
        }

        @Override
        public String evaluate(List<?> values, ReportParameters reportParameters) {
            BigDecimal unitPriceSum = (BigDecimal) values.getFirst();
            return "Unit price sum = " + type.bigDecimalType().valueToString(unitPriceSum, reportParameters.getLocale());
        }
    }

    private static class PriceExpression extends AbstractSimpleExpression<BigDecimal> {
        @Serial
        private static final long serialVersionUID = 1L;

        private final TextColumnBuilder<Integer> quantityColumn;
        private final TextColumnBuilder<BigDecimal> unitPriceColumn;

        public PriceExpression(TextColumnBuilder<Integer> quantityColumn, TextColumnBuilder<BigDecimal> unitPriceColumn) {
            this.quantityColumn = quantityColumn;
            this.unitPriceColumn = unitPriceColumn;
        }

        @Override
        public BigDecimal evaluate(ReportParameters reportParameters) {
            Integer quantity = reportParameters.getValue(quantityColumn);
            BigDecimal unitPrice = reportParameters.getValue(unitPriceColumn);
            return unitPrice.multiply(BigDecimal.valueOf(quantity));
        }
    }
}
