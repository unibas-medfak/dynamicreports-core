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

import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.cmp;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.stl;

import java.io.Serial;
import java.io.Serializable;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder;
import ch.unibas.medizin.dynamicreports.report.constant.WhenNoDataType;
import ch.unibas.medizin.dynamicreports.test.jasper.AbstractJasperStyleTest;
import net.sf.jasperreports.engine.type.HorizontalTextAlignEnum;
import net.sf.jasperreports.engine.type.LineStyleEnum;

/**
 * Style tests.
 *
 * @author Ricardo Mariaca
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Style5Test extends AbstractJasperStyleTest implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Override
  protected void configureReport(JasperReportBuilder rb) {
    rb.setWhenNoDataType(WhenNoDataType.ALL_SECTIONS_NO_DETAIL)
        .setTextStyle(stl.style(stl.pen1Point()).setPadding(2)).pageFooter(cmp.pageXofY());
  }

  @Override
  @Test
  public void test() {
    super.test();

    numberOfPagesTest(1);

    // column1
    styleTest("pageFooter.textField1", 0, null, null, TEST_FONT_NAME, 10f, null, null);
    horizontalAlignmentTest("pageFooter.textField1", 0, HorizontalTextAlignEnum.RIGHT);
    paddingTest("pageFooter.textField1", 0, 2, 2, 2, 0);
    borderTest("pageFooter.textField1", 0, null, LineStyleEnum.SOLID, 1, null, LineStyleEnum.SOLID,
        1, null, LineStyleEnum.SOLID, 1, null, LineStyleEnum.SOLID, 0);

    styleTest("pageFooter.textField2", 0, null, null, TEST_FONT_NAME, 10f, null, null);
    horizontalAlignmentTest("pageFooter.textField2", 0, HorizontalTextAlignEnum.LEFT);
    paddingTest("pageFooter.textField2", 0, 2, 2, 0, 2);
    borderTest("pageFooter.textField2", 0, null, LineStyleEnum.SOLID, 1, null, LineStyleEnum.SOLID,
        1, null, LineStyleEnum.SOLID, 0, null, LineStyleEnum.SOLID, 1);
  }
}
