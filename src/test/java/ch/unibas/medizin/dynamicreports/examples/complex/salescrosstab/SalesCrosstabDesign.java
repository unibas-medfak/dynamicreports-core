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
package ch.unibas.medizin.dynamicreports.examples.complex.salescrosstab;

import ch.unibas.medizin.dynamicreports.examples.Templates;
import ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder;
import ch.unibas.medizin.dynamicreports.report.base.expression.AbstractSimpleExpression;
import ch.unibas.medizin.dynamicreports.report.builder.crosstab.CrosstabBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.crosstab.CrosstabColumnGroupBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.crosstab.CrosstabMeasureBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.crosstab.CrosstabRowGroupBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.style.ConditionalStyleBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.style.StyleBuilder;
import ch.unibas.medizin.dynamicreports.report.constant.Calculation;
import ch.unibas.medizin.dynamicreports.report.constant.PageOrientation;
import ch.unibas.medizin.dynamicreports.report.constant.PageType;
import ch.unibas.medizin.dynamicreports.report.definition.ReportParameters;
import ch.unibas.medizin.dynamicreports.report.exception.DRException;

import java.awt.Color;
import java.io.Serial;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.cmp;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.cnd;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.ctab;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.field;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.report;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.stl;

/**
 * <p>SalesCrosstabDesign class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class SalesCrosstabDesign {
    private final SalesCrosstabData data = new SalesCrosstabData();

    /**
     * <p>main.</p>
     *
     * @param args an array of {@link java.lang.String} objects.
     */
    public static void main(String[] args) {
        SalesCrosstabDesign design = new SalesCrosstabDesign();
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

        CrosstabRowGroupBuilder<String> rowStateGroup = ctab.rowGroup("state", String.class).setHeaderWidth(80);
        CrosstabRowGroupBuilder<String> rowItemGroup = ctab.rowGroup("item", String.class).setHeaderWidth(80);

        CrosstabColumnGroupBuilder<Integer> columnYearGroup = ctab.columnGroup(new YearExpression());
        CrosstabColumnGroupBuilder<String> columnQuarterGroup = ctab.columnGroup(new QuarterExpression());

        CrosstabMeasureBuilder<Integer> quantityMeasure = ctab.measure("Quantity", "quantity", Integer.class, Calculation.SUM);
        CrosstabMeasureBuilder<BigDecimal> unitPriceMeasure = ctab.measure("Unit price", "unitprice", BigDecimal.class, Calculation.SUM);
        unitPriceMeasure.setPattern("#,###");

        rowStateGroup.orderBy(quantityMeasure);

        ConditionalStyleBuilder condition1 = stl.conditionalStyle(cnd.greater(unitPriceMeasure, 50000)).setForegroundColor(Color.GREEN);
        ConditionalStyleBuilder condition2 = stl.conditionalStyle(cnd.smaller(unitPriceMeasure, 300)).setForegroundColor(Color.RED);

        StyleBuilder unitPriceStyle = stl.style().setBorder(stl.pen1Point().setLineColor(Color.BLACK)).conditionalStyles(condition1, condition2);
        unitPriceMeasure.setStyle(unitPriceStyle);

        CrosstabBuilder crosstab = ctab.crosstab()
                                       .setCellWidth(110)
                                       .headerCell(cmp.text("State / Date").setStyle(Templates.boldCenteredStyle))
                                       .rowGroups(rowStateGroup, rowItemGroup)
                                       .columnGroups(columnYearGroup, columnQuarterGroup)
                                       .measures(quantityMeasure, unitPriceMeasure);

        report.fields(field("orderdate", Date.class))
              .setPageFormat(PageType.A3, PageOrientation.LANDSCAPE)
              .setTemplate(Templates.reportTemplate)
              .title(Templates.createTitleComponent("SalesCrosstab"))
              .summary(crosstab)
              .pageFooter(Templates.footerComponent)
              .setDataSource(data.createDataSource());

        return report;
    }

    private static class YearExpression extends AbstractSimpleExpression<Integer> {
        @Serial
        private static final long serialVersionUID = 1L;

        @Override
        public Integer evaluate(ReportParameters reportParameters) {
            Calendar c = Calendar.getInstance();
            c.setTime(reportParameters.getValue("orderdate"));
            return c.get(Calendar.YEAR);
        }
    }

    private static class QuarterExpression extends AbstractSimpleExpression<String> {
        @Serial
        private static final long serialVersionUID = 1L;

        @Override
        public String evaluate(ReportParameters reportParameters) {
            Calendar c = Calendar.getInstance();
            c.setTime(reportParameters.getValue("orderdate"));
            return "Q" + (c.get(Calendar.MONTH) / 3 + 1);
        }
    }
}
