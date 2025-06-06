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
package ch.unibas.medizin.dynamicreports.test.jasper.style;

import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.cmp;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.col;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.grp;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.sbt;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.stl;

import java.awt.Color;
import java.io.Serial;
import java.io.Serializable;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.column.TextColumnBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.group.ColumnGroupBuilder;
import ch.unibas.medizin.dynamicreports.report.constant.GroupHeaderLayout;
import ch.unibas.medizin.dynamicreports.report.datasource.DRDataSource;
import ch.unibas.medizin.dynamicreports.test.jasper.AbstractJasperStyleTest;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BandStyle3Test extends AbstractJasperStyleTest implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    final Color color1 = new Color(240, 240, 240);
    final Color color2 = new Color(230, 230, 230);
    final Color color3 = new Color(220, 220, 220);
    final Color color4 = new Color(210, 210, 210);
    final Color color5 = new Color(190, 190, 190);
    final Color color6 = new Color(180, 180, 180);
    final Color color7 = new Color(170, 170, 170);
    final Color color8 = new Color(160, 160, 160);
    final Color color9 = new Color(150, 150, 150);
    final Color color10 = new Color(140, 140, 140);
    final Color color11 = new Color(130, 130, 130);
    final Color color12 = new Color(120, 120, 120);
    final Color color13 = new Color(110, 110, 110);

    @Override
    protected void configureReport(JasperReportBuilder rb) {
        TextColumnBuilder<String> column1;
        TextColumnBuilder<Integer> column2;
        ColumnGroupBuilder group1;

        rb.setPageColumnsPerPage(2)
          .columns(column1 = col.column("Column1", "field1", String.class), column2 = col.column("Column2", "field2", Integer.class))
          .groupBy(group1 = grp.group(column1).setHeaderLayout(GroupHeaderLayout.TITLE_AND_VALUE))
          .setTitleStyle(stl.style().setBackgroundColor(color1))
          .setPageHeaderStyle(stl.style().setBackgroundColor(color2))
          .setPageFooterStyle(stl.style().setBackgroundColor(color3))
          .setColumnHeaderStyle(stl.style().setBackgroundColor(color4))
          .setColumnFooterStyle(stl.style().setBackgroundColor(color5))
          .setGroupHeaderStyle(group1, stl.style().setBackgroundColor(color6))
          .setGroupFooterStyle(group1, stl.style().setBackgroundColor(color7))
          .setDetailHeaderStyle(stl.style().setBackgroundColor(color8))
          .setDetailStyle(stl.style().setBackgroundColor(color9))
          .setDetailFooterStyle(stl.style().setBackgroundColor(color10))
          .setLastPageFooterStyle(stl.style().setBackgroundColor(color11))
          .setSummaryStyle(stl.style().setBackgroundColor(color12))
          .setBackgroundStyle(stl.style().setBackgroundColor(color13))
          .subtotalsAtGroupHeader(group1, sbt.sum(column2))
          .subtotalsAtGroupFooter(group1, sbt.sum(column2))
          .title(cmp.verticalGap(10), cmp.verticalGap(10))
          .pageHeader(cmp.verticalGap(10))
          .pageFooter(cmp.verticalGap(10))
          .columnHeader(cmp.verticalGap(10))
          .columnFooter(cmp.verticalGap(10))
          .groupHeader(group1, cmp.verticalGap(10))
          .groupFooter(group1, cmp.verticalGap(10))
          .detailHeader(cmp.verticalGap(10))
          .detail(cmp.verticalGap(10))
          .detailFooter(cmp.verticalGap(10))
          .lastPageFooter(cmp.verticalGap(10))
          .summary(cmp.verticalGap(10))
          .background(cmp.verticalGap(10));
    }

    @Override
    @Test
    public void test() {
        super.test();

        numberOfPagesTest(2);

        styleTest("title.list1", 0, null, color1, "SansSerif", null, null, null);
        styleTest("pageHeader.list1", 0, null, color2, "SansSerif", null, null, null);
        styleTest("pageFooter.list1", 0, null, color3, "SansSerif", null, null, null);
        styleTest("columnHeader.list1", 0, null, color4, "SansSerif", null, null, null);
        styleTest("columnFooter.list1", 0, null, color5, "SansSerif", null, null, null);
        styleTest("groupHeaderTitleAndValue.list1", 0, null, color6, "SansSerif", null, null, null);
        styleTest("groupHeader.list1", 0, null, color6, "SansSerif", null, null, null);
        styleTest("subtotalGroupHeader.list1", 0, null, color6, "SansSerif", null, null, null);
        styleTest("groupFooter.list1", 0, null, color7, "SansSerif", null, null, null);
        styleTest("subtotalGroupFooter.list1", 0, null, color7, "SansSerif", null, null, null);
        styleTest("detailHeader.list1", 0, null, color8, "SansSerif", null, null, null);
        styleTest("detail.list1", 0, null, color9, "SansSerif", null, null, null);
        styleTest("detailFooter.list1", 0, null, color10, "SansSerif", null, null, null);
        styleTest("lastPageFooter.list1", 0, null, color11, "SansSerif", null, null, null);
        styleTest("summary.list1", 0, null, color12, "SansSerif", null, null, null);
        styleTest("background.list1", 0, null, color13, "SansSerif", null, null, null);
    }

    @Override
    protected JRDataSource createDataSource() {
        final DRDataSource dataSource = new DRDataSource("field1", "field2");
        for (int i = 0; i < 10; i++) {
            dataSource.add("group1", i);
        }
        for (int i = 0; i < 10; i++) {
            dataSource.add("group2", i);
        }
        return dataSource;
    }
}
