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
import ch.unibas.medizin.dynamicreports.report.builder.column.TextColumnBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.component.TextFieldBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.component.VerticalListBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.group.ColumnGroupBuilder;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import ch.unibas.medizin.dynamicreports.report.constant.GroupHeaderLayout;
import ch.unibas.medizin.dynamicreports.report.datasource.DRDataSource;
import ch.unibas.medizin.dynamicreports.report.definition.ReportParameters;
import ch.unibas.medizin.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;

import java.io.Serial;
import java.math.BigDecimal;
import java.util.stream.IntStream;

import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.cmp;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.col;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.grp;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.report;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.type;

/**
 * <p>PrintWhenExpressionReport class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class PrintWhenExpressionReport {

    /**
     * <p>Constructor for PrintWhenExpressionReport.</p>
     */
    public PrintWhenExpressionReport() {
        build();
    }

    /**
     * <p>main.</p>
     *
     * @param args an array of {@link java.lang.String} objects.
     */
    public static void main(String[] args) {
        new PrintWhenExpressionReport();
    }

    private void build() {
        TextColumnBuilder<String> itemColumn = col.column("Item", "item", type.stringType());

        ColumnGroupBuilder itemGroup = grp.group("itemGroup", itemColumn).setHeaderLayout(GroupHeaderLayout.EMPTY);

        TextFieldBuilder<String> groupHeader = cmp.text(new GroupHeaderExpression()).setStyle(Templates.groupStyle).setPrintWhenExpression(new PrintGroupHeaderExpression()).removeLineWhenBlank();

        VerticalListBuilder oddPageHeader =
            cmp.verticalList().add(cmp.text("Odd page header").setStyle(Templates.bold12CenteredStyle), cmp.line()).setPrintWhenExpression(new PrintInOddPageExpression()).removeLineWhenBlank();

        VerticalListBuilder evenPageHeader = cmp.verticalList()
                                                .add(cmp.line(), cmp.text("Even page header").setStyle(Templates.bold12CenteredStyle), cmp.line())
                                                .setPrintWhenExpression(new PrintInEvenPageExpression())
                                                .removeLineWhenBlank();

        try {
            report().setTemplate(Templates.reportTemplate)
                    .setPageColumnsPerPage(2)
                    .setPageColumnSpace(5)
                    .columns(itemColumn, col.column("Quantity", "quantity", type.integerType()), col.column("Unit price", "unitprice", type.bigDecimalType()))
                    .groupBy(itemGroup)
                    .title(Templates.createTitleComponent("PrintWhenExpression"))
                    .detailHeader(cmp.columnBreak().setPrintWhenExpression(new PrintGroupHeaderColumnBreakExpression()), groupHeader)
                    .pageHeader(oddPageHeader, evenPageHeader, cmp.verticalGap(10))
                    .pageFooter(Templates.footerComponent)
                    .setDataSource(createDataSource())
                    .show();
        } catch (DRException e) {
            e.printStackTrace();
        }
    }

    private JRDataSource createDataSource() {
        DRDataSource dataSource = new DRDataSource("item", "quantity", "unitprice");
        IntStream.range(0, 50).forEachOrdered(i -> dataSource.add("DVD", (int) (Math.random() * 10) + 1, BigDecimal.valueOf(Math.random() * 100 + 1)));
        IntStream.range(0, 50).forEachOrdered(i -> dataSource.add("Book", (int) (Math.random() * 10) + 1, BigDecimal.valueOf(Math.random() * 100 + 1)));
        return dataSource;
    }

    public static class GroupHeaderExpression extends AbstractSimpleExpression<String> {
        @Serial
        private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

        @Override
        public String evaluate(ReportParameters reportParameters) {
            return reportParameters.getValue("item");
        }
    }

    public static class PrintGroupHeaderExpression extends AbstractSimpleExpression<Boolean> {
        @Serial
        private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

        @Override
        public Boolean evaluate(ReportParameters reportParameters) {
            return reportParameters.getColumnRowNumber() == 1 || reportParameters.getGroupCount("itemGroup") == 1;
        }
    }

    public static class PrintGroupHeaderColumnBreakExpression extends AbstractSimpleExpression<Boolean> {
        @Serial
        private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

        @Override
        public Boolean evaluate(ReportParameters reportParameters) {
            return reportParameters.getColumnRowNumber() == 1 && reportParameters.getGroupCount("itemGroup") != 1;
        }
    }

    public static class PrintInOddPageExpression extends AbstractSimpleExpression<Boolean> {
        @Serial
        private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

        @Override
        public Boolean evaluate(ReportParameters reportParameters) {
            return reportParameters.getPageNumber().doubleValue() % 2 != 0;
        }
    }

    public static class PrintInEvenPageExpression extends AbstractSimpleExpression<Boolean> {
        @Serial
        private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

        @Override
        public Boolean evaluate(ReportParameters reportParameters) {
            return reportParameters.getPageNumber().doubleValue() % 2 == 0;
        }
    }
}
