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
package ch.unibas.medizin.dynamicreports.examples.complex.dynamicreport;

import ch.unibas.medizin.dynamicreports.examples.Templates;
import ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.column.TextColumnBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.component.PageXofYBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.component.TextFieldBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.group.ColumnGroupBuilder;
import ch.unibas.medizin.dynamicreports.report.constant.HorizontalTextAlignment;
import ch.unibas.medizin.dynamicreports.report.definition.datatype.DRIDataType;
import ch.unibas.medizin.dynamicreports.report.exception.DRException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.cmp;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.col;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.grp;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.report;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.sbt;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.type;

/**
 * <p>DynamicReportDesign class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DynamicReportDesign {
    private final DynamicReportData data = new DynamicReportData();

    /**
     * <p>main.</p>
     *
     * @param args an array of {@link java.lang.String} objects.
     */
    public static void main(String[] args) {
        DynamicReportDesign design = new DynamicReportDesign();
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
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public JasperReportBuilder build() throws DRException {
        JasperReportBuilder report = report();

        report.setTemplate(Templates.reportTemplate).title(Templates.createTitleComponent("DynamicReport"));

        DynamicReport dynamicReport = data.getDynamicReport();
        List<DynamicColumn> columns = dynamicReport.getColumns();
        Map<String, TextColumnBuilder> drColumns = new HashMap<>();

        for (DynamicColumn column : columns) {
            TextColumnBuilder drColumn = col.column(column.getTitle(), column.getName(), (DRIDataType) type.detectType(column.getType()));
            if (column.getPattern() != null) {
                drColumn.setPattern(column.getPattern());
            }
            if (column.getHorizontalTextAlignment() != null) {
                drColumn.setHorizontalTextAlignment(column.getHorizontalTextAlignment());
            }
            drColumns.put(column.getName(), drColumn);
            report.columns(drColumn);
        }

        for (String group : dynamicReport.getGroups()) {
            ColumnGroupBuilder group2 = grp.group(drColumns.get(group));
            report.groupBy(group2);

            for (String subtotal : dynamicReport.getSubtotals()) {
                report.subtotalsAtGroupFooter(group2, sbt.sum(drColumns.get(subtotal)));
            }
        }

        for (String subtotal : dynamicReport.getSubtotals()) {
            report.subtotalsAtSummary(sbt.sum(drColumns.get(subtotal)));
        }

        if (dynamicReport.getTitle() != null) {
            TextFieldBuilder<String> title = cmp.text(dynamicReport.getTitle()).setStyle(Templates.bold12CenteredStyle).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER);
            report.addTitle(title);
        }
        if (dynamicReport.isShowPageNumber()) {
            PageXofYBuilder pageXofY = cmp.pageXofY().setStyle(Templates.boldCenteredStyle);
            report.addPageFooter(pageXofY);
        }
        report.setDataSource(data.createDataSource());

        return report;
    }
}
