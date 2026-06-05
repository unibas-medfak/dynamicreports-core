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
package ch.unibas.medizin.dynamicreports.examples.tableofcontents;

import ch.unibas.medizin.dynamicreports.examples.Templates;
import ch.unibas.medizin.dynamicreports.report.builder.column.TextColumnBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.component.ComponentBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.component.VerticalListBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.style.StyleBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.tableofcontents.TableOfContentsCustomizer;
import ch.unibas.medizin.dynamicreports.report.constant.HorizontalTextAlignment;
import ch.unibas.medizin.dynamicreports.report.datasource.DRDataSource;
import ch.unibas.medizin.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;

import java.awt.Color;
import java.io.Serial;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.cmp;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.col;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.report;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.stl;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.type;

/**
 * <p>CustomTableOfContentsReport class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class CustomTableOfContentsReport {

    /**
     * <p>Constructor for CustomTableOfContentsReport.</p>
     */
    public CustomTableOfContentsReport() {
        build();
    }

    /**
     * <p>main.</p>
     *
     * @param args an array of {@link java.lang.String} objects.
     */
    public static void main(String[] args) {
        new CustomTableOfContentsReport();
    }

    private void build() {
        TextColumnBuilder<String> countryColumn = col.column("Country", "country", type.stringType());
        TextColumnBuilder<String> itemColumn = col.column("Item", "item", type.stringType());

        StyleBuilder titleTocStyle = stl.style().setForegroundColor(Color.BLUE).setFontSize(18).bold().setHorizontalTextAlignment(HorizontalTextAlignment.CENTER);
        StyleBuilder headingToc0Style = stl.style(Templates.rootStyle).setFontSize(12).bold();
        StyleBuilder headingToc1Style = stl.style(Templates.rootStyle).italic();

        CustomTableOfContentsCustomizer tableOfContentsCustomizer = new CustomTableOfContentsCustomizer();
        tableOfContentsCustomizer.setTitleStyle(titleTocStyle);
        tableOfContentsCustomizer.setHeadingStyle(0, headingToc0Style);
        tableOfContentsCustomizer.setHeadingStyle(1, headingToc1Style);
        tableOfContentsCustomizer.setTextFixedWidth(100);
        tableOfContentsCustomizer.setPageIndexFixedWidth(30);

        try {
            report().setTemplate(Templates.reportTemplate)
                    .tableOfContents(tableOfContentsCustomizer)
                    .columns(countryColumn, itemColumn, col.column("Order date", "orderdate", type.dateType()), col.column("Quantity", "quantity", type.integerType()),
                             col.column("Unit price", "unitprice", type.bigDecimalType()))
                    .groupBy(countryColumn, itemColumn)
                    .title(Templates.createTitleComponent("CustomTableOfContents"))
                    .pageFooter(Templates.footerComponent)
                    .setDataSource(createDataSource())
                    .show();
        } catch (DRException e) {
            e.printStackTrace();
        }
    }

    private JRDataSource createDataSource() {
        String[] countries = new String[] {"USA", "Canada", "Mexico"};
        String[] items = new String[] {"Book", "Notebook", "PDA"};
        DRDataSource dataSource = new DRDataSource("country", "item", "orderdate", "quantity", "unitprice");
        for (String country : countries) {
            for (String item : items) {
                for (int i = 0; i < 8; i++) {
                    dataSource.add(country, item, toDate(2010, 1, (int) (Math.random() * 10) + 1), (int) (Math.random() * 10) + 1, BigDecimal.valueOf(Math.random() * 100 + 1));
                }
            }
        }
        return dataSource;
    }

    private Date toDate(int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1);
        c.set(Calendar.DAY_OF_MONTH, day);
        return c.getTime();
    }

    private static class CustomTableOfContentsCustomizer extends TableOfContentsCustomizer {
        @Serial
        private static final long serialVersionUID = 1L;

        @Override
        protected ComponentBuilder<?, ?> title() {
            VerticalListBuilder verticalList = cmp.verticalList();
            verticalList.add(cmp.line());
            verticalList.add(super.title());
            verticalList.add(cmp.line());
            return verticalList;
        }

        @Override
        protected ComponentBuilder<?, ?> headingComponent(int level) {
            if (level == 0) {
                VerticalListBuilder verticalList = cmp.verticalList();
                verticalList.add(super.headingComponent(level));
                verticalList.add(cmp.line());
                return verticalList;
            } else {
                return super.headingComponent(level);
            }
        }
    }
}
