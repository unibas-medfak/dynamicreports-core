/*
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2018 Ricardo Mariaca and the Dynamic Reports Contributors
 *
 * This file is part of DynamicReports.
 *
 * DynamicReports is free software: you can redistribute it and/or modify it under the terms of the
 * GNU Lesser General Public License as published by the Free Software Foundation, either version 3
 * of the License, or (at your option) any later version.
 *
 * DynamicReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with
 * DynamicReports. If not, see <http://www.gnu.org/licenses/>.
 */

package ch.unibas.medizin.dynamicreports.test.jasper.style;

import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.col;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.stl;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.type;

import java.awt.Color;
import java.io.Serial;
import java.io.Serializable;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.column.TextColumnBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.style.FontBuilder;
import ch.unibas.medizin.dynamicreports.report.datasource.DRDataSource;
import ch.unibas.medizin.dynamicreports.test.jasper.AbstractJasperStyleTest;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * Default font tests.
 *
 * @author Ricardo Mariaca
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DefaultFontTest extends AbstractJasperStyleTest implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  private TextColumnBuilder<String> column1;
  private TextColumnBuilder<String> column2;

  @Override
  protected void configureReport(JasperReportBuilder rb) {
    final FontBuilder defaultFont = stl.font().setFontSize(12);

    rb.setDefaultFont(defaultFont).columns(
        column1 = col.column("Column1", "field1", type.stringType()).setStyle(stl.style().bold()),
        column2 = col.column("Column2", "field2", type.stringType()));
  }

  @Override
  @Test
  public void test() {
    super.test();

    numberOfPagesTest(1);

    // column1
    columnDetailStyleTest(column1, 0, null, null, TEST_FONT_NAME, 12f, true, null);
    columnDetailStyleTest(column1, 1, null, null, TEST_FONT_NAME, 12f, true, null);
    columnDetailStyleTest(column1, 2, null, null, TEST_FONT_NAME, 12f, true, null);

    // column2
    columnDetailStyleTest(column2, 0, Color.BLACK, null, TEST_FONT_NAME, 12f, null, null);
    columnDetailStyleTest(column2, 1, Color.BLACK, null, TEST_FONT_NAME, 12f, null, null);
    columnDetailStyleTest(column2, 2, Color.BLACK, null, TEST_FONT_NAME, 12f, null, null);
  }

  @Override
  protected JRDataSource createDataSource() {
    final DRDataSource dataSource = new DRDataSource("field1", "field2", "field3");
    dataSource.add("1", "1", "1");
    dataSource.add("1", "1", "1");
    dataSource.add("1", "1", "1");
    return dataSource;
  }
}
