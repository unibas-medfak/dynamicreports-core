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

import java.awt.Color;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.style.StyleBuilder;
import ch.unibas.medizin.dynamicreports.test.jasper.AbstractJasperStyleTest;
import net.sf.jasperreports.engine.JRStyle;

/**
 * Style tests.
 *
 * @author Ricardo Mariaca
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Style9Test extends AbstractJasperStyleTest {

  @Override
  protected void configureReport(JasperReportBuilder rb) {
    final StyleBuilder style = stl.style().bold().setForegroundColor(Color.BLUE);

    rb.title(cmp.rectangle().setStyle(style), cmp.text("text").setStyle(style));
  }

  @Override
  @Test
  public void test() {
    super.test();

    numberOfPagesTest(1);

    final JRStyle style = getElementAt("title.rectangle1", 0).getStyle();
    Assertions.assertEquals(Color.BLUE, style.getForecolor(), "foreColor");
    styleTest("title.textField1", 0, Color.BLUE, null, TEST_FONT_NAME, 10f, true, null);
  }
}
