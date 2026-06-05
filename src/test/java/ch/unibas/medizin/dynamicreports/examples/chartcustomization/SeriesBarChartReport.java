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
package ch.unibas.medizin.dynamicreports.examples.chartcustomization;

import ch.unibas.medizin.dynamicreports.examples.Templates;
import ch.unibas.medizin.dynamicreports.report.builder.column.TextColumnBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.style.FontBuilder;
import ch.unibas.medizin.dynamicreports.report.datasource.DRDataSource;
import ch.unibas.medizin.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;

import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.cht;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.col;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.report;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.stl;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.type;

/**
 * <p>SeriesBarChartReport class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class SeriesBarChartReport {

    /**
     * <p>Constructor for SeriesBarChartReport.</p>
     */
    public SeriesBarChartReport() {
        build();
    }

    /**
     * <p>main.</p>
     *
     * @param args an array of {@link java.lang.String} objects.
     */
    public static void main(String[] args) {
        new SeriesBarChartReport();
    }

    private void build() {
        FontBuilder boldFont = stl.fontArialBold().setFontSize(12);

        TextColumnBuilder<String> stateColumn = col.column("State", "state", type.stringType());
        TextColumnBuilder<String> itemColumn = col.column("Item", "item", type.stringType());
        TextColumnBuilder<Integer> quantityColumn = col.column("Quantity", "quantity", type.integerType());

        try {
            report().setTemplate(Templates.reportTemplate)
                    .columns(stateColumn, itemColumn, quantityColumn)
                    .title(Templates.createTitleComponent("SeriesBarChart"))
                    .summary(cht.barChart()
                                .setTitle("Bar chart")
                                .setTitleFont(boldFont)
                                .setCategory(stateColumn)
                                .series(cht.serie(quantityColumn).setSeries(itemColumn))
                                .setCategoryAxisFormat(cht.axisFormat().setLabel("Item")))
                    .pageFooter(Templates.footerComponent)
                    .setDataSource(createDataSource())
                    .show();
        } catch (DRException e) {
            e.printStackTrace();
        }
    }

    private JRDataSource createDataSource() {
        DRDataSource dataSource = new DRDataSource("state", "item", "quantity");
        dataSource.add("New York", "Tablet", 170);
        dataSource.add("New York", "Laptop", 100);
        dataSource.add("New York", "Smartphone", 120);
        dataSource.add("Washington", "Tablet", 110);
        dataSource.add("Washington", "Laptop", 90);
        dataSource.add("Washington", "Smartphone", 160);
        return dataSource;
    }
}
