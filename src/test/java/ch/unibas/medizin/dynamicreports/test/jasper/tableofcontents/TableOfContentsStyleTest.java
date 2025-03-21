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

package ch.unibas.medizin.dynamicreports.test.jasper.tableofcontents;

import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.col;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.stl;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.tableOfContentsCustomizer;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.type;

import java.awt.Color;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.column.TextColumnBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.style.StyleBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.tableofcontents.TableOfContentsCustomizerBuilder;
import ch.unibas.medizin.dynamicreports.report.constant.HorizontalTextAlignment;
import ch.unibas.medizin.dynamicreports.report.datasource.DRDataSource;
import ch.unibas.medizin.dynamicreports.test.jasper.AbstractJasperStyleTest;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * Table of contents style tests.
 *
 * @author Ricardo Mariaca
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TableOfContentsStyleTest extends AbstractJasperStyleTest {

  @Override
  protected void configureReport(JasperReportBuilder rb) {
    final TextColumnBuilder<String> column1 = col.column("Column1", "field1", type.stringType());
    final TextColumnBuilder<String> column2 = col.column("Column2", "field2", type.stringType());

    final StyleBuilder titleTocStyle = stl.style().setForegroundColor(Color.BLUE).setFontSize(18).bold()
        .setHorizontalTextAlignment(HorizontalTextAlignment.CENTER);
    final StyleBuilder headingTocStyle = stl.style().setFontSize(12).bold();
    final StyleBuilder headingToc1Style = stl.style().italic();

    final TableOfContentsCustomizerBuilder tableOfContentsCustomizer =
        tableOfContentsCustomizer().setTitleStyle(titleTocStyle).setHeadingStyle(headingTocStyle)
            .setHeadingStyle(1, headingToc1Style);

    rb.setTableOfContents(tableOfContentsCustomizer)
        .columns(column1, column2, col.column("Column3", "field3", type.stringType()))
        .groupBy(column1, column2);
  }

  @Override
  @Test
  public void test() {
    super.test();

    numberOfPagesTest(3);

    styleTest("title.textField1", 0, Color.BLUE, null, TEST_FONT_NAME, 18f, true, null);

    for (int i = 0; i < 3; i++) {
      styleTest("detail.textField1", i, null, null, TEST_FONT_NAME, 12f, true, null);
      styleTest("detail.textField2", i, null, null, TEST_FONT_NAME, 12f, true, null);
      styleTest("detail.textField3", i, null, null, TEST_FONT_NAME, 12f, true, null);
    }

    for (int i = 0; i < 9; i++) {
      styleTest("detail.textField4", i, null, null, TEST_FONT_NAME, 10f, null, true);
      styleTest("detail.textField5", i, null, null, TEST_FONT_NAME, 10f, null, true);
      styleTest("detail.textField6", i, null, null, TEST_FONT_NAME, 10f, null, true);
    }
  }

  @Override
  protected JRDataSource createDataSource() {
    final String[] values = new String[] {"value1", "value2", "value3"};
    final DRDataSource dataSource = new DRDataSource("field1", "field2", "field3");
    for (final String field1 : values) {
      for (final String field2 : values) {
        for (int i = 0; i < 8; i++) {
          dataSource.add(field1, field2, "text");
        }
      }
    }
    return dataSource;
  }
}
