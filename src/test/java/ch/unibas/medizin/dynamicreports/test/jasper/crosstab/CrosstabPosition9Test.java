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
package ch.unibas.medizin.dynamicreports.test.jasper.crosstab;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.col;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.ctab;

import ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.column.TextColumnBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.crosstab.CrosstabBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.crosstab.CrosstabColumnGroupBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.crosstab.CrosstabMeasureBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.crosstab.CrosstabRowGroupBuilder;
import ch.unibas.medizin.dynamicreports.report.constant.Calculation;
import ch.unibas.medizin.dynamicreports.report.constant.PageOrientation;
import ch.unibas.medizin.dynamicreports.report.constant.PageType;
import ch.unibas.medizin.dynamicreports.report.datasource.DRDataSource;
import ch.unibas.medizin.dynamicreports.test.jasper.AbstractJasperCrosstabPositionTest;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CrosstabPosition9Test extends AbstractJasperCrosstabPositionTest {
  private CrosstabRowGroupBuilder<String> rowGroup1;
  private CrosstabRowGroupBuilder<String> rowGroup2;
  private CrosstabColumnGroupBuilder<String> columnGroup1;
  private CrosstabMeasureBuilder<Integer> measure1;

  @Override
  protected void configureReport(JasperReportBuilder rb) {
    final TextColumnBuilder<String> column1 = col.column("Column1", "field1", String.class);
    final TextColumnBuilder<String> column2 = col.column("Column2", "field2", String.class);
    final TextColumnBuilder<Integer> column3 = col.column("Column3", "field3", Integer.class);

    rowGroup1 = ctab.rowGroup(column1).setShowTotal(false);
    rowGroup2 = ctab.rowGroup(column1).setShowTotal(false).setHeaderWidth(25);
    measure1 = ctab.measure("measure1", column3, Calculation.SUM);

    final CrosstabBuilder crosstab = ctab.crosstab()
      .rowGroups(rowGroup1, rowGroup2)
      .columnGroups(columnGroup1 = ctab.columnGroup(column2))
      .measures(measure1);

    rb.setPageFormat(PageType.A4, PageOrientation.LANDSCAPE).summary(crosstab);
  }

  @Override
  @Test
  public void test() {
    super.test();

    numberOfPagesTest(1);

    setCrosstabBand("summary");

    // column group 1
    crosstabGroupHeaderPositionTest(columnGroup1, 0, 0, 0, 100, 16);

    // row group 1
    crosstabGroupHeaderPositionTest(rowGroup1, 0, 0, 0, 100, 27);
    crosstabGroupHeaderPositionTest(rowGroup2, 0, 0, 0, 25, 27);

    // measures
    crosstabCellPositionTest(measure1, null, null, 0, 0, 0, 100, 27);
  }

  @Override
  protected JRDataSource createDataSource() {
    final DRDataSource dataSource = new DRDataSource("field1", "field2", "field3");
    dataSource.add("text text", "a", 1);
    return dataSource;
  }
}
