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

package ch.unibas.medizin.dynamicreports.test.jasper.crosstab;

import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.ctab;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.field;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.stl;

import java.awt.Color;
import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder;
import ch.unibas.medizin.dynamicreports.report.base.expression.AbstractSimpleExpression;
import ch.unibas.medizin.dynamicreports.report.builder.FieldBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.crosstab.CrosstabBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.crosstab.CrosstabColumnGroupBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.crosstab.CrosstabMeasureBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.crosstab.CrosstabRowGroupBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.style.StyleBuilder;
import ch.unibas.medizin.dynamicreports.report.constant.Calculation;
import ch.unibas.medizin.dynamicreports.report.datasource.DRDataSource;
import ch.unibas.medizin.dynamicreports.report.definition.ReportParameters;
import ch.unibas.medizin.dynamicreports.test.jasper.AbstractJasperCrosstabStyleTest;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * Crosstab style tests.
 * 
 * @author Ricardo Mariaca
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CrosstabStyle2Test extends AbstractJasperCrosstabStyleTest implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  private CrosstabRowGroupBuilder<String> rowGroup;
  private CrosstabColumnGroupBuilder<String> columnGroup;
  private CrosstabMeasureBuilder<Integer> measure1;

  @Override
  protected void configureReport(JasperReportBuilder rb) {
    FieldBuilder<String> field1 = field("field1", String.class);
    FieldBuilder<String> field2 = field("field2", String.class);

    final StyleBuilder cellStyle =
        stl.style().conditionalStyles(stl.conditionalStyle(new ConditionExpression(10, 15, 14, 36))
            .setBackgroundColor(Color.ORANGE));

    rowGroup = ctab.rowGroup(field1);
    columnGroup = ctab.columnGroup(field2);

    measure1 = ctab.measure("field3", Integer.class, Calculation.SUM);
    measure1.setStyle(cellStyle);

    CrosstabBuilder crosstab =
        ctab.crosstab().rowGroups(rowGroup).columnGroups(columnGroup).measures(measure1);

    rb.summary(crosstab);
  }

  @Override
  @Test
  public void test() {
    super.test();

    numberOfPagesTest(1);

    setCrosstabBand("summary");

    crosstabCellStyleTest(measure1, null, null, 0, null, null, TEST_FONT_NAME, 10f, null, null);
    crosstabCellStyleTest(measure1, null, null, 1, null, null, TEST_FONT_NAME, 10f, null, null);
    crosstabCellStyleTest(measure1, null, null, 2, null, null, TEST_FONT_NAME, 10f, null, null);
    crosstabCellStyleTest(measure1, null, null, 3, null, Color.ORANGE, TEST_FONT_NAME, 10f, null,
        null);

    crosstabCellStyleTest(measure1, rowGroup, null, 0, null, Color.ORANGE, TEST_FONT_NAME, 10f,
        null, null);
    crosstabCellStyleTest(measure1, rowGroup, null, 1, null, null, TEST_FONT_NAME, 10f, null, null);

    crosstabCellStyleTest(measure1, null, columnGroup, 0, null, Color.ORANGE, TEST_FONT_NAME, 10f,
        null, null);
    crosstabCellStyleTest(measure1, null, columnGroup, 1, null, null, TEST_FONT_NAME, 10f, null,
        null);

    crosstabCellStyleTest(measure1, rowGroup, columnGroup, 0, null, Color.ORANGE, TEST_FONT_NAME,
        10f, null, null);

  }

  @Override
  protected JRDataSource createDataSource() {
    DRDataSource dataSource = new DRDataSource("field1", "field2", "field3");
    dataSource.add("a", "c", 1);
    dataSource.add("a", "c", 2);
    dataSource.add("a", "d", 3);
    dataSource.add("a", "d", 4);
    dataSource.add("b", "c", 5);
    dataSource.add("b", "c", 6);
    dataSource.add("b", "d", 7);
    dataSource.add("b", "d", 8);
    return dataSource;
  }

  private class ConditionExpression extends AbstractSimpleExpression<Boolean> {
    @Serial
    private static final long serialVersionUID = 1L;

    private final List<Integer> values;

    private ConditionExpression(Integer... values) {
      this.values = Arrays.asList(values);
    }

    @Override
    public Boolean evaluate(ReportParameters reportParameters) {
      Integer value = reportParameters.getValue(measure1);
      return values.contains(value);
    }
  }
}
