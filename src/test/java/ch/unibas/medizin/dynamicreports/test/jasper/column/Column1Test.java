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
package ch.unibas.medizin.dynamicreports.test.jasper.column;

import ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder;
import ch.unibas.medizin.dynamicreports.report.base.expression.AbstractSimpleExpression;
import ch.unibas.medizin.dynamicreports.report.base.expression.AbstractValueFormatter;
import ch.unibas.medizin.dynamicreports.report.builder.column.TextColumnBuilder;
import ch.unibas.medizin.dynamicreports.report.datasource.DRDataSource;
import ch.unibas.medizin.dynamicreports.report.definition.ReportParameters;
import ch.unibas.medizin.dynamicreports.test.jasper.AbstractJasperValueTest;
import net.sf.jasperreports.engine.JRDataSource;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.col;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.field;

/**
 * @author Ricardo Mariaca
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Column1Test extends AbstractJasperValueTest implements Serializable {
    private static final long serialVersionUID = 1L;

    private TextColumnBuilder<String> column2;
    private TextColumnBuilder<Date> column3;
    private TextColumnBuilder<Double> column4;
    private TextColumnBuilder<BigDecimal> column5;
    private TextColumnBuilder<Double> column6;
    private TextColumnBuilder<Object> column7;

    private final Date date = new Date();

    @Override
    protected void configureReport(JasperReportBuilder rb) {
        rb.setLocale(Locale.ENGLISH)
          .addField("field1", Integer.class)
          .columns(column2 = col.column("Column2\nColumn2", "field2", String.class).setPrintWhenDetailOverflows(true),
                   column3 = col.column("Column3", "field3", Date.class).setPattern("dd.MM.yyyy"),
                   column4 = col.column("Column4", "field4", Double.class).setPattern("#,###.00"),
                   column5 = col.column("Column5", "field5", BigDecimal.class).setValueFormatter(new ColumnValueFormatter()),
                   column6 = col.column("Column6", "field6", Double.class).setPattern(new PatternExpression()),
                   column7 = col.column("Column7", field("field7", Date.class).build()));
    }

    @Override
    @Test
    public void test() {
        super.test();

        numberOfPagesTest(3);
        // column2
        columnDetailCountTest(column2, 110);
        columnDetailValueTest(column2, 50, "test");
        columnTitleCountTest(column2, 3);
        columnTitleValueTest(column2, "Column2\nColumn2", "Column2\nColumn2", "Column2\nColumn2");
        // column3
        columnDetailCountTest(column3, 110);
        columnDetailValueTest(column3, 50, new SimpleDateFormat("dd.MM.yyyy").format(date));
        columnTitleCountTest(column3, 3);
        columnTitleValueTest(column3, "Column3", "Column3", "Column3");
        // column4
        columnDetailCountTest(column4, 110);
        columnDetailValueTest(column4, 50, "1.00");
        columnTitleCountTest(column4, 3);
        columnTitleValueTest(column4, "Column4", "Column4", "Column4");
        // column5
        columnDetailCountTest(column5, 110);
        columnDetailValueTest(column5, 50, "value = 10");
        columnTitleCountTest(column5, 3);
        columnTitleValueTest(column5, "Column5", "Column5", "Column5");
        // column6
        columnDetailCountTest(column6, 110);
        columnDetailValueTest(column6, 0, "1.00");
        columnDetailValueTest(column6, 1, "1.000");
        columnDetailValueTest(column6, 2, "1.0000");
        columnDetailValueTest(column6, 3, "1.0");
        columnDetailValueTest(column6, 50, "1.0");
        columnTitleCountTest(column6, 3);
        columnTitleValueTest(column6, "Column6", "Column6", "Column6");
        // column7
        columnDetailCountTest(column7, 110);
        columnDetailValueTest(column7, 50, new SimpleDateFormat("MM/dd/yyyy").format(date));
        columnTitleCountTest(column7, 3);
        columnTitleValueTest(column7, "Column7", "Column7", "Column7");
    }

    @Override
    protected JRDataSource createDataSource() {
        DRDataSource dataSource = new DRDataSource("field1", "field2", "field3", "field4", "field5", "field6", "field7");
        for (int i = 0; i < 110; i++) {
            dataSource.add(1, "test", date, 1d, new BigDecimal(10), 1d, date);
        }
        return dataSource;
    }

    private static class ColumnValueFormatter extends AbstractValueFormatter<String, BigDecimal> {
        private static final long serialVersionUID = 1L;

        @Override
        public String format(BigDecimal value, ReportParameters reportParameters) {
            return "value = " + value;
        }
    }

    private static class PatternExpression extends AbstractSimpleExpression<String> {
        private static final long serialVersionUID = 1L;

        @Override
        public String evaluate(ReportParameters reportParameters) {
            StringBuilder pattern = new StringBuilder("#,###.0");
            Integer reportRowNumber = reportParameters.getReportRowNumber();
            if (reportRowNumber < 4) {
                pattern.append("0".repeat(Math.max(0, reportRowNumber)));
            }
            return pattern.toString();
        }
    }
}
