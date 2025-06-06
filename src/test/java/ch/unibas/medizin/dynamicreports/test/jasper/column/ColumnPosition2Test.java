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

import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.col;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.grid;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.column.TextColumnBuilder;
import ch.unibas.medizin.dynamicreports.report.datasource.DRDataSource;
import ch.unibas.medizin.dynamicreports.test.jasper.AbstractJasperPositionTest;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ColumnPosition2Test extends AbstractJasperPositionTest {
  private TextColumnBuilder<Integer> column1;
  private TextColumnBuilder<String> column2;
  private TextColumnBuilder<Integer> column3;
  private TextColumnBuilder<Integer> column4;
  private TextColumnBuilder<Integer> column5;

  @Override
  protected void configureReport(JasperReportBuilder rb) {
    rb.columns(column1 = col.column("Column1", "field1", Integer.class),
        column2 = col.column("Very very very very very very long Column2 title", "field2", String.class),
        column3 = col.column("Column3", "field3", Integer.class),
        column4 = col.column("Column4", "field4", Integer.class).setWidth(50),
        column5 = col.column("Column5", "field5", Integer.class))
      .columnGrid(column1, grid.horizontalColumnGridList().add(column2, column3).newRow().add(column4, column5));
  }

  @Override
  @Test
  public void test() {
    super.test();

    numberOfPagesTest(1);
    elementPositionTest("columnHeader.list1", 0, 10, 10, 575, 43);
      elementPositionTest("columnHeader.list3", 0, 191, 0, 384, 27);
    elementPositionTest("columnHeader.list4", 0, 191, 27, 384, 16);

    elementPositionTest("detail.list1", 0, 10, 53, 575, 32);
      elementPositionTest("detail.list3", 0, 191, 0, 384, 16);
    elementPositionTest("detail.list4", 0, 191, 16, 384, 16);

    elementPositionTest("detail.list1", 1, 10, 85, 575, 43);
      elementPositionTest("detail.list3", 1, 191, 0, 384, 27);
    elementPositionTest("detail.list4", 1, 191, 27, 384, 16);

    // column1
    columnTitlePositionTest(column1, 0, 0, 0, 191, 43);
    columnDetailPositionTest(column1, 0, 0, 0, 191, 32);
    columnDetailPositionTest(column1, 1, 0, 0, 191, 43);
    // column2
    columnTitlePositionTest(column2, 0, 0, 0, 192, 27);
    columnDetailPositionTest(column2, 0, 0, 0, 192, 16);
    columnDetailPositionTest(column2, 1, 0, 0, 192, 27);
    // column3
    columnTitlePositionTest(column3, 0, 192, 0, 192, 27);
    columnDetailPositionTest(column3, 0, 192, 0, 192, 16);
    columnDetailPositionTest(column3, 1, 192, 0, 192, 27);
    // column4
    columnTitlePositionTest(column4, 0, 0, 0, 128, 16);
    columnDetailPositionTest(column4, 0, 0, 0, 128, 16);
    columnDetailPositionTest(column4, 1, 0, 0, 128, 16);
    // column5
    columnTitlePositionTest(column5, 0, 128, 0, 256, 16);
    columnDetailPositionTest(column5, 0, 128, 0, 256, 16);
    columnDetailPositionTest(column5, 1, 128, 0, 256, 16);
  }

  @Override
  protected JRDataSource createDataSource() {
    final DRDataSource dataSource = new DRDataSource("field1", "field2", "field3", "field4", "field5");
    dataSource.add(1, "2", 3, 4, 5);
    dataSource.add(1, "very very very very very very very long value 2", 3, 4, 5);
    return dataSource;
  }
}
