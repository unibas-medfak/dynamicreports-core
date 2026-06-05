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
package ch.unibas.medizin.dynamicreports.examples.chart;

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
 * <p>GroupedStackedBarChartReport class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class GroupedStackedBarChartReport {

    /**
     * <p>Constructor for GroupedStackedBarChartReport.</p>
     */
    public GroupedStackedBarChartReport() {
        build();
    }

    /**
     * <p>main.</p>
     *
     * @param args an array of {@link java.lang.String} objects.
     */
    public static void main(String[] args) {
        new GroupedStackedBarChartReport();
    }

    private void build() {
        FontBuilder boldFont = stl.fontArialBold().setFontSize(12);

        TextColumnBuilder<String> yearColumn = col.column("Year", "year", type.stringType());
        TextColumnBuilder<String> quarterColumn = col.column("Quarter", "quarter", type.stringType());
        TextColumnBuilder<Integer> stock1Column = col.column("Stock 1", "stock1", type.integerType());
        TextColumnBuilder<Integer> stock2Column = col.column("Stock 2", "stock2", type.integerType());
        TextColumnBuilder<Integer> stock3Column = col.column("Stock 3", "stock3", type.integerType());

        try {
            report().setTemplate(Templates.reportTemplate)
                    .columns(yearColumn, quarterColumn, stock1Column, stock2Column, stock3Column)
                    .title(Templates.createTitleComponent("GroupedStackedBarChart"))
                    .summary(cht.groupedStackedBarChart()
                                .setTitle("Grouped stacked bar chart")
                                .setTitleFont(boldFont)
                                .setCategory(yearColumn)
                                .series(cht.groupedSerie(stock1Column).setGroup(quarterColumn), cht.groupedSerie(stock2Column).setGroup(quarterColumn),
                                        cht.groupedSerie(stock3Column).setGroup(quarterColumn)))
                    .pageFooter(Templates.footerComponent)
                    .setDataSource(createDataSource())
                    .show();
        } catch (DRException e) {
            e.printStackTrace();
        }
    }

    private JRDataSource createDataSource() {
        DRDataSource dataSource = new DRDataSource("year", "quarter", "stock1", "stock2", "stock3");
        dataSource.add("2010", "Q1", 80, 25, 18);
        dataSource.add("2010", "Q2", 98, 78, 22);
        dataSource.add("2010", "Q3", 50, 10, 89);
        dataSource.add("2010", "Q4", 121, 40, 43);
        dataSource.add("2011", "Q1", 103, 120, 34);
        dataSource.add("2011", "Q2", 190, 95, 22);
        dataSource.add("2011", "Q3", 43, 109, 51);
        dataSource.add("2011", "Q4", 80, 88, 40);
        return dataSource;
    }
}
