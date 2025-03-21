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
package ch.unibas.medizin.dynamicreports.test.jasper.subtotal;

import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.col;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.grp;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.sbt;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.type;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.column.TextColumnBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.subtotal.AggregationSubtotalBuilder;
import ch.unibas.medizin.dynamicreports.report.constant.GroupHeaderLayout;
import ch.unibas.medizin.dynamicreports.report.datasource.DRDataSource;
import ch.unibas.medizin.dynamicreports.test.jasper.AbstractJasperPositionTest;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SubtotalPosition3Test extends AbstractJasperPositionTest {
  private AggregationSubtotalBuilder<Integer> subtotal1;
  private AggregationSubtotalBuilder<Integer> subtotal2;
  private TextColumnBuilder<String> column1;
  private TextColumnBuilder<Integer> column2;

  @Override
  protected void configureReport(JasperReportBuilder rb) {
    rb.columns(column1 = col.column("Column1", "field1", type.stringType()).setFixedWidth(540),
        column2 = col.column("Column2", "field2", type.integerType()))
      .groupBy(grp.group(column1).setHideColumn(false).setPadding(0).setHeaderLayout(GroupHeaderLayout.EMPTY))
      .subtotalsAtFirstGroupFooter(subtotal1 = sbt.sum(column2))
      .subtotalsAtSummary(subtotal2 = sbt.sum(column2));
  }

  @Override
  @Test
  public void test() {
    super.test();

    numberOfPagesTest(1);

    // columns
    columnDetailPositionTest(column1, 0, 0, 0, 540, 27);
    columnDetailPositionTest(column2, 0, 540, 0, 35, 27);

    // summary
    elementPositionTest("subtotalGroupFooter.list1", 0, 10, 91, 575, 27);
    subtotalPositionTest(subtotal1, 0, 540, 0, 35, 27);
    elementPositionTest("summary.list1", 0, 10, 118, 575, 27);
    subtotalPositionTest(subtotal2, 0, 540, 0, 35, 27);
  }

  @Override
  protected JRDataSource createDataSource() {
    final DRDataSource dataSource = new DRDataSource("field1", "field2");
    dataSource.add("text", 1000000);
    dataSource.add("text", 1000000);
    return dataSource;
  }
}
