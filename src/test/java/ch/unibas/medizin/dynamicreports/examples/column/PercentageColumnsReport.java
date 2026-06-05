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
package ch.unibas.medizin.dynamicreports.examples.column;

import ch.unibas.medizin.dynamicreports.examples.Templates;
import ch.unibas.medizin.dynamicreports.report.builder.FieldBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.column.PercentageColumnBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.column.TextColumnBuilder;
import ch.unibas.medizin.dynamicreports.report.datasource.DRDataSource;
import ch.unibas.medizin.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;

import java.math.BigDecimal;

import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.col;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.field;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.report;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.type;

/**
 * <p>PercentageColumnsReport class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class PercentageColumnsReport {

    /**
     * <p>Constructor for PercentageColumnsReport.</p>
     */
    public PercentageColumnsReport() {
        build();
    }

    /**
     * <p>main.</p>
     *
     * @param args an array of {@link java.lang.String} objects.
     */
    public static void main(String[] args) {
        new PercentageColumnsReport();
    }

    private void build() {
        try {
            FieldBuilder<BigDecimal> unitPriceField = field("unitprice", BigDecimal.class);

            TextColumnBuilder<String> itemColumn = col.column("Item", "item", type.stringType());
            TextColumnBuilder<Integer> quantityColumn = col.column("Quantity", "quantity", type.integerType());
            PercentageColumnBuilder quantityPercColumn = col.percentageColumn("Quantity [%]", quantityColumn);
            PercentageColumnBuilder unitPricePercColumn = col.percentageColumn("Unit price [%]", unitPriceField);

            report().setTemplate(Templates.reportTemplate)
                    .fields(unitPriceField)
                    .columns(itemColumn, quantityColumn, quantityPercColumn, unitPricePercColumn)
                    .title(Templates.createTitleComponent("PercentageColumns"))
                    .pageFooter(Templates.footerComponent)
                    .setDataSource(createDataSource())
                    .show();
        } catch (DRException e) {
            e.printStackTrace();
        }
    }

    private JRDataSource createDataSource() {
        DRDataSource dataSource = new DRDataSource("item", "quantity", "unitprice");
        dataSource.add("Book", 3, BigDecimal.valueOf (11));
        dataSource.add("Book", 1, BigDecimal.valueOf (15));
        dataSource.add("Book", 5, BigDecimal.valueOf (10));
        dataSource.add("Book", 8, BigDecimal.valueOf (9));
        return dataSource;
    }
}
