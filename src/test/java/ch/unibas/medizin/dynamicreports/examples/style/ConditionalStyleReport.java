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
package ch.unibas.medizin.dynamicreports.examples.style;

import ch.unibas.medizin.dynamicreports.examples.Templates;
import ch.unibas.medizin.dynamicreports.report.base.expression.AbstractSimpleExpression;
import ch.unibas.medizin.dynamicreports.report.builder.column.TextColumnBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.style.ConditionalStyleBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.style.StyleBuilder;
import ch.unibas.medizin.dynamicreports.report.datasource.DRDataSource;
import ch.unibas.medizin.dynamicreports.report.definition.ReportParameters;
import ch.unibas.medizin.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;

import java.awt.Color;
import java.io.Serial;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.cnd;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.col;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.report;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.stl;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.type;

/**
 * <p>ConditionalStyleReport class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class ConditionalStyleReport {

    /**
     * <p>Constructor for ConditionalStyleReport.</p>
     */
    public ConditionalStyleReport() {
        build();
    }

    /**
     * <p>main.</p>
     *
     * @param args an array of {@link java.lang.String} objects.
     */
    public static void main(String[] args) {
        new ConditionalStyleReport();
    }

    private void build() {
        TextColumnBuilder<String> itemColumn = col.column("Item", "item", type.stringType());
        TextColumnBuilder<Date> orderDateColumn = col.column("Order date", "orderdate", type.dateType());
        TextColumnBuilder<Integer> quantityColumn = col.column("Quantity", "quantity", type.integerType());
        TextColumnBuilder<BigDecimal> unitPriceColumn = col.column("Unit price", "unitprice", type.bigDecimalType());

        ConditionalStyleBuilder condition1 = stl.conditionalStyle(new OrderDateConditionExpression()).setBackgroundColor(new Color(255, 210, 210));
        ConditionalStyleBuilder condition2 = stl.conditionalStyle(cnd.greater(unitPriceColumn, 20)).setBackgroundColor(new Color(210, 255, 210));

        StyleBuilder orderDateStyle = stl.style().conditionalStyles(condition1);
        orderDateColumn.setStyle(orderDateStyle);

        StyleBuilder unitPriceStyle = stl.style().conditionalStyles(condition2);
        unitPriceColumn.setStyle(unitPriceStyle);

        try {
            report().setTemplate(Templates.reportTemplate)
                    .columns(itemColumn, orderDateColumn, quantityColumn, unitPriceColumn)
                    .title(Templates.createTitleComponent("ConditionalStyle"))
                    .pageFooter(Templates.footerComponent)
                    .setDataSource(createDataSource())
                    .show();
        } catch (DRException e) {
            e.printStackTrace();
        }
    }

    private JRDataSource createDataSource() {
        DRDataSource dataSource = new DRDataSource("item", "orderdate", "quantity", "unitprice");
        dataSource.add("DVD", toDate(2010, 1, 1), 5, BigDecimal.valueOf(30));
        dataSource.add("DVD", toDate(2010, 1, 3), 1, BigDecimal.valueOf(28));
        dataSource.add("DVD", toDate(2010, 1, 19), 5, BigDecimal.valueOf(32));
        dataSource.add("Book", toDate(2010, 1, 5), 3, BigDecimal.valueOf(11));
        dataSource.add("Book", toDate(2010, 1, 11), 1, BigDecimal.valueOf(15));
        dataSource.add("Book", toDate(2010, 1, 15), 5, BigDecimal.valueOf(10));
        dataSource.add("Book", toDate(2010, 1, 20), 8, BigDecimal.valueOf(9));
        return dataSource;
    }

    private Date toDate(int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1);
        c.set(Calendar.DAY_OF_MONTH, day);
        return c.getTime();
    }

    private class OrderDateConditionExpression extends AbstractSimpleExpression<Boolean> {
        @Serial
        private static final long serialVersionUID = 1L;

        @Override
        public Boolean evaluate(ReportParameters reportParameters) {
            Date orderDate = reportParameters.getValue("orderdate");
            Integer quantity = reportParameters.getValue("quantity");
            return orderDate.after(toDate(2010, 1, 10)) && quantity > 1;
        }
    }
}
