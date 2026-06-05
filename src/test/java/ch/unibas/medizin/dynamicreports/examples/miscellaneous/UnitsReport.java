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
import ch.unibas.medizin.dynamicreports.report.builder.column.TextColumnBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.component.TextFieldBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.style.StyleBuilder;
import ch.unibas.medizin.dynamicreports.report.datasource.DRDataSource;
import ch.unibas.medizin.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;

import java.math.BigDecimal;
import java.util.stream.IntStream;

import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.cm;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.cmp;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.col;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.inch;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.mm;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.report;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.stl;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.type;

/**
 * <p>UnitsReport class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class UnitsReport {

    /**
     * <p>Constructor for UnitsReport.</p>
     */
    public UnitsReport() {
        build();
    }

    /**
     * <p>main.</p>
     *
     * @param args an array of {@link java.lang.String} objects.
     */
    public static void main(String[] args) {
        new UnitsReport();
    }

    private void build() {
        TextColumnBuilder<String> itemColumn = col.column("Item", "item", type.stringType()).setFixedWidth(cm(10));
        TextColumnBuilder<Integer> quantityColumn = col.column("Quantity", "quantity", type.integerType());
        TextColumnBuilder<BigDecimal> priceColumn = col.column("Unit price", "unitprice", type.bigDecimalType());

        StyleBuilder style = stl.style(Templates.bold12CenteredStyle).setBorder(stl.pen1Point());
        TextFieldBuilder<String> text1 = cmp.text("width = 120 pixels").setFixedWidth(120).setStyle(style);
        TextFieldBuilder<String> text2 = cmp.text("width = 10cm").setFixedWidth(cm(10)).setStyle(style);
        TextFieldBuilder<String> text3 = cmp.text("width = 5 inches").setFixedWidth(inch(5)).setStyle(style);
        TextFieldBuilder<String> text4 = cmp.text("width = 150mm").setFixedWidth(mm(150)).setStyle(style);

        try {
            report().setTemplate(Templates.reportTemplate)
                    .columns(itemColumn, quantityColumn, priceColumn)
                    .title(Templates.createTitleComponent("Units"), text1, text2, text3, text4, cmp.verticalGap(cm(1)))
                    .pageFooter(Templates.footerComponent)
                    .setDataSource(createDataSource())
                    .show();
        } catch (DRException e) {
            e.printStackTrace();
        }
    }

    private JRDataSource createDataSource() {
        DRDataSource dataSource = new DRDataSource("item", "quantity", "unitprice");
        IntStream.range(0, 20).forEach(i -> dataSource.add("Book", (int) (Math.random() * 10) + 1, BigDecimal.valueOf(Math.random() * 100 + 1)));
        return dataSource;
    }
}
