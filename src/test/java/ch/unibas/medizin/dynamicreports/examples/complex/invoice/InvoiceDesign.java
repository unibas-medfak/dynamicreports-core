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
package ch.unibas.medizin.dynamicreports.examples.complex.invoice;

import ch.unibas.medizin.dynamicreports.examples.Templates;
import ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder;
import ch.unibas.medizin.dynamicreports.report.base.expression.AbstractSimpleExpression;
import ch.unibas.medizin.dynamicreports.report.builder.column.TextColumnBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.component.ComponentBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.component.HorizontalListBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.style.StyleBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.subtotal.AggregationSubtotalBuilder;
import ch.unibas.medizin.dynamicreports.report.constant.HorizontalTextAlignment;
import ch.unibas.medizin.dynamicreports.report.definition.ReportParameters;
import ch.unibas.medizin.dynamicreports.report.exception.DRException;

import java.io.Serial;
import java.math.BigDecimal;

import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.cmp;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.col;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.exp;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.grid;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.report;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.sbt;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.stl;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.type;

/**
 * <p>InvoiceDesign class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class InvoiceDesign {
    private final InvoiceData data = new InvoiceData();
    private AggregationSubtotalBuilder<BigDecimal> totalSum;

    /**
     * <p>main.</p>
     *
     * @param args an array of {@link java.lang.String} objects.
     */
    public static void main(String[] args) {
        InvoiceDesign design = new InvoiceDesign();
        try {
            JasperReportBuilder report = design.build();
            report.show();
        } catch (DRException e) {
            e.printStackTrace();
        }
    }

    /**
     * <p>build.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder} object.
     */
    public JasperReportBuilder build() {
        JasperReportBuilder report = report();

        // init styles
        StyleBuilder columnStyle = stl.style(Templates.columnStyle).setBorder(stl.pen1Point());
        StyleBuilder subtotalStyle = stl.style(columnStyle).bold();
        StyleBuilder shippingStyle = stl.style(Templates.boldStyle).setHorizontalTextAlignment(HorizontalTextAlignment.RIGHT);

        // init columns
        TextColumnBuilder<Integer> rowNumberColumn = col.reportRowNumberColumn().setFixedColumns(2).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER);
        TextColumnBuilder<String> descriptionColumn = col.column("Description", "description", type.stringType()).setFixedWidth(250);
        TextColumnBuilder<Integer> quantityColumn = col.column("Quantity", "quantity", type.integerType()).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER);
        TextColumnBuilder<BigDecimal> unitPriceColumn = col.column("Unit Price", "unitprice", Templates.currencyType);
        TextColumnBuilder<String> taxColumn = col.column("Tax", exp.text("20%")).setFixedColumns(3);
        // price = unitPrice * quantity
        TextColumnBuilder<BigDecimal> priceColumn = unitPriceColumn.multiply(quantityColumn).setTitle("Price").setDataType(Templates.currencyType);
        // vat = price * tax
        TextColumnBuilder<BigDecimal> vatColumn = priceColumn.multiply(data.getInvoice().getTax()).setTitle("VAT").setDataType(Templates.currencyType);
        // total = price + vat
        TextColumnBuilder<BigDecimal> totalColumn = priceColumn.add(vatColumn).setTitle("Total Price").setDataType(Templates.currencyType).setRows(2).setStyle(subtotalStyle);
        // init subtotals
        totalSum = sbt.sum(totalColumn).setLabel("Total:").setLabelStyle(Templates.boldStyle);

        // configure report
        report.setTemplate(Templates.reportTemplate)
              .setColumnStyle(columnStyle)
              .setSubtotalStyle(subtotalStyle)
              // columns
              .columns(rowNumberColumn, descriptionColumn, quantityColumn, unitPriceColumn, totalColumn, priceColumn, taxColumn, vatColumn)
              .columnGrid(rowNumberColumn, descriptionColumn, quantityColumn, unitPriceColumn, grid.horizontalColumnGridList().add(totalColumn).newRow().add(priceColumn, taxColumn, vatColumn))
              // subtotals
              .subtotalsAtSummary(totalSum, sbt.sum(priceColumn), sbt.sum(vatColumn))
              // band components
              .title(Templates.createTitleComponent("Invoice No.: " + data.getInvoice().getId()), cmp.horizontalList()
                                                                                                     .setStyle(stl.style(10))
                                                                                                     .setGap(50)
                                                                                                     .add(cmp.hListCell(createCustomerComponent("Bill To", data.getInvoice().getBillTo()))
                                                                                                             .heightFixedOnTop(),
                                                                                                          cmp.hListCell(createCustomerComponent("Ship To", data.getInvoice().getShipTo()))
                                                                                                             .heightFixedOnTop()), cmp.verticalGap(10))
              .pageFooter(Templates.footerComponent)
              .summary(cmp.text(data.getInvoice().getShipping()).setValueFormatter(Templates.createCurrencyValueFormatter("Shipping:")).setStyle(shippingStyle),
                       cmp.horizontalList(cmp.text("Payment terms: 30 days").setStyle(Templates.bold12CenteredStyle), cmp.text(new TotalPaymentExpression()).setStyle(Templates.bold12CenteredStyle)),
                       cmp.verticalGap(30), cmp.text("Thank you for your business").setStyle(Templates.bold12CenteredStyle))
              .setDataSource(data.createDataSource());

        return report;
    }

    private ComponentBuilder<?, ?> createCustomerComponent(String label, Customer customer) {
        HorizontalListBuilder list = cmp.horizontalList().setBaseStyle(stl.style().setTopBorder(stl.pen1Point()).setLeftPadding(10));
        addCustomerAttribute(list, "Name", customer.getName());
        addCustomerAttribute(list, "Address", customer.getAddress());
        addCustomerAttribute(list, "City", customer.getCity());
        addCustomerAttribute(list, "Email", customer.getEmail());
        return cmp.verticalList(cmp.text(label).setStyle(Templates.boldStyle), list);
    }

    private void addCustomerAttribute(HorizontalListBuilder list, String label, String value) {
        if (value != null) {
            list.add(cmp.text(label + ":").setFixedColumns(8).setStyle(Templates.boldStyle), cmp.text(value)).newRow();
        }
    }

    private class TotalPaymentExpression extends AbstractSimpleExpression<String> {
        @Serial
        private static final long serialVersionUID = 1L;

        @Override
        public String evaluate(ReportParameters reportParameters) {
            BigDecimal total = reportParameters.getValue(totalSum);
            BigDecimal shipping = total.add(data.getInvoice().getShipping());
            return "Total payment: " + Templates.currencyType.valueToString(shipping, reportParameters.getLocale());
        }
    }
}
