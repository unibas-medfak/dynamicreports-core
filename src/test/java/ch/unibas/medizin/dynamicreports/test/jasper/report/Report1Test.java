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
package ch.unibas.medizin.dynamicreports.test.jasper.report;

import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.cmp;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.col;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.exp;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.parameter;

import java.math.BigDecimal;
import java.util.ListResourceBundle;
import java.util.Locale;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder;
import ch.unibas.medizin.dynamicreports.report.base.AbstractScriptlet;
import ch.unibas.medizin.dynamicreports.report.builder.column.TextColumnBuilder;
import ch.unibas.medizin.dynamicreports.report.constant.PageOrientation;
import ch.unibas.medizin.dynamicreports.report.constant.PageType;
import ch.unibas.medizin.dynamicreports.report.constant.WhenResourceMissingType;
import ch.unibas.medizin.dynamicreports.report.datasource.DRDataSource;
import ch.unibas.medizin.dynamicreports.report.definition.ReportParameters;
import ch.unibas.medizin.dynamicreports.test.jasper.AbstractJasperValueTest;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.fonts.FontUtil;
import net.sf.jasperreports.engine.type.OrientationEnum;

/**
 * @author Ricardo Mariaca
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Report1Test extends AbstractJasperValueTest {
  private TextColumnBuilder<Integer> column1;
  private ReportScriptlet scriptlet;
  private BigDecimal parameter1;
  private BigDecimal parameter2;

  @Override
  protected void configureReport(JasperReportBuilder rb) {
    rb.columns(column1 = col.column("Column1", "field1", Integer.class))
            .title(cmp.text(exp.jasperSyntax("$R{bundleKey3}", String.class)),
                    cmp.multiPageList(cmp.text(exp.jasperSyntax("$R{bundleKey3}", String.class)),
                            cmp.text(exp.jasperSyntax("$R{bundleKey1}", String.class))))
            .setLocale(Locale.ENGLISH)
            .setResourceBundle(new ResourceBundle())
            .setWhenResourceMissingType(WhenResourceMissingType.KEY)
            .setShowColumnTitle(false)
            .setShowColumnValues(false)
            .setPageFormat(PageType.A3, PageOrientation.LANDSCAPE)
            .scriptlets(scriptlet = new ReportScriptlet())
            .parameters(parameter("parameter1", parameter1 = new BigDecimal(10)))
            .addParameter("parameter2", parameter2 = new BigDecimal(20));
  }

  @Override
  @Test
  public void test() {
    super.test();

    numberOfPagesTest(1);
    columnTitleCountTest(column1, 0);
    columnDetailCountTest(column1, 0);

    elementCountTest("title.textField1", 3);
    elementValueTest("title.textField1", "bundleKey3", "bundleKey3", "bundleValue");

    final FontUtil fontUtil = FontUtil.getInstance(DefaultJasperReportsContext.getInstance());
    Assertions.assertFalse(fontUtil.getFontFamilyNames().isEmpty(), "fonts");

    final JasperPrint jasperPrint = getJasperPrint();
    Assertions.assertEquals("Report", jasperPrint.getName());
    Assertions.assertEquals(OrientationEnum.LANDSCAPE, jasperPrint.getOrientation());
    Assertions.assertEquals(1190, jasperPrint.getPageWidth());
    Assertions.assertEquals(842, jasperPrint.getPageHeight());

    Assertions.assertEquals(50, scriptlet.count);
  }

  @Override
  protected boolean serializableTest() {
    return false;
  }

  @Override
  protected JRDataSource createDataSource() {
    final DRDataSource dataSource = new DRDataSource("field1");
    for (int i = 0; i < 50; i++) {
      dataSource.add(i);
    }
    return dataSource;
  }

  private static class ResourceBundle extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
      return new Object[][]{{"bundleKey1", "bundleValue"}, {"bundleKey2", "bundleValue {0} - {1}"}};
    }
  }

  private class ReportScriptlet extends AbstractScriptlet {
    private int count;

    @Override
    public void afterReportInit(ReportParameters reportParameters) {
      super.afterReportInit(reportParameters);
      Assertions.assertEquals(Locale.ENGLISH, reportParameters.getLocale());
      Assertions.assertEquals("bundleValue", reportParameters.getMessage("bundleKey1"));
      Assertions.assertEquals("bundleValue a - b", reportParameters.getMessage("bundleKey2", new Object[]{"a", "b"}));
      Assertions.assertEquals(parameter1, reportParameters.getValue("parameter1"));
      Assertions.assertEquals(parameter2, reportParameters.getValue("parameter2"));
      Assertions.assertEquals(this, reportParameters.getScriptlet(getName()));
    }

    @Override
    public void afterDetailEval(ReportParameters reportParameters) {
      super.afterDetailEval(reportParameters);
      count++;
    }
  }
}
