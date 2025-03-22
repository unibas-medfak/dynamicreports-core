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
package ch.unibas.medizin.dynamicreports.test.design.report;

import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.cht;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.cmp;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.col;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.ctab;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.grp;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.margin;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.sbt;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.stl;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.template;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Locale;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ch.unibas.medizin.dynamicreports.design.base.DRDesignGroup;
import ch.unibas.medizin.dynamicreports.design.base.DRDesignReport;
import ch.unibas.medizin.dynamicreports.design.base.chart.DRDesignChart;
import ch.unibas.medizin.dynamicreports.design.base.chart.plot.AbstractDesignBasePlot;
import ch.unibas.medizin.dynamicreports.design.base.component.DRDesignComponent;
import ch.unibas.medizin.dynamicreports.design.base.component.DRDesignImage;
import ch.unibas.medizin.dynamicreports.design.base.component.DRDesignList;
import ch.unibas.medizin.dynamicreports.design.base.component.DRDesignTextField;
import ch.unibas.medizin.dynamicreports.design.base.crosstab.DRDesignCrosstab;
import ch.unibas.medizin.dynamicreports.design.definition.component.DRIDesignList;
import ch.unibas.medizin.dynamicreports.design.definition.style.DRIDesignStyle;
import ch.unibas.medizin.dynamicreports.report.builder.ReportBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.column.TextColumnBuilder;
import ch.unibas.medizin.dynamicreports.report.constant.Calculation;
import ch.unibas.medizin.dynamicreports.report.constant.GroupHeaderLayout;
import ch.unibas.medizin.dynamicreports.report.constant.Language;
import ch.unibas.medizin.dynamicreports.report.constant.Orientation;
import ch.unibas.medizin.dynamicreports.report.constant.PageOrientation;
import ch.unibas.medizin.dynamicreports.report.constant.PageType;
import ch.unibas.medizin.dynamicreports.report.constant.RunDirection;
import ch.unibas.medizin.dynamicreports.report.constant.SplitType;
import ch.unibas.medizin.dynamicreports.report.constant.WhenNoDataType;
import ch.unibas.medizin.dynamicreports.report.constant.WhenResourceMissingType;
import ch.unibas.medizin.dynamicreports.report.exception.DRException;
import ch.unibas.medizin.dynamicreports.test.design.DesignReportBuilder;

/**
 * @author Ricardo Mariaca
 */
public class ReportTemplateTest {

    private void configureReport(final ReportBuilder<?> rb) {
        TextColumnBuilder<Integer> column1;

        rb.columns(column1 = col.column("Column1", "field1", Integer.class), col.booleanColumn("Column2", "field2"))
          .groupBy(grp.group(column1).header(cmp.horizontalList(cmp.hListCell(cmp.text("")).widthFixed())))
          .title(cmp.horizontalList(cmp.hListCell(cmp.image("")).widthFixed().heightFixedOnTop(), cmp.hListCell(cht.barChart()).widthFixed().heightFixedOnTop(),
                                    cmp.hListCell(ctab.crosstab()
                                                                                                                               .rowGroups(ctab.rowGroup("f1", String.class))
                                                                                                                               .columnGroups(ctab.columnGroup("f2", String.class),
                                                                                                                                             ctab.columnGroup("f3", String.class))
                                                                                                                               .measures(ctab.measure("f4", "f4", Integer.class, Calculation.SUM)))
                                                                                                                .widthFixed()
                                                                                                                .heightFixedOnTop()))
          .setTemplate(template().setLocale(Locale.ENGLISH)
                                 .setShowColumnTitle(false)
                                 .setIgnorePagination(true)
                                 .setWhenNoDataType(WhenNoDataType.ALL_SECTIONS_NO_DETAIL)
                                 .setWhenResourceMissingType(WhenResourceMissingType.KEY)
                                 .setTitleOnANewPage(true)
                                 .setSummaryOnANewPage(true)
                                 .setSummaryWithPageHeaderAndFooter(true)
                                 .setFloatColumnFooter(true)
                                 .setPrintOrder(Orientation.HORIZONTAL)
                                 .setColumnDirection(RunDirection.RIGHT_TO_LEFT)
                                 .setLanguage(Language.GROOVY)

                                 .setHighlightDetailOddRows(true)
                                 .setDetailOddRowStyle(stl.simpleStyle().setBackgroundColor(Color.BLUE))
                                 .setHighlightDetailEvenRows(true)
                                 .setDetailEvenRowStyle(stl.simpleStyle().setBackgroundColor(Color.CYAN))
                                 .setDefaultFont(stl.font().setFontSize(12))
                                 .setTextStyle(stl.style().bold())

                                 .setPageFormat(PageType.A3, PageOrientation.LANDSCAPE)
                                 .setPageMargin(margin(3))
                                 .setPageColumnsPerPage(3)
                                 .setPageColumnSpace(20)

                                 .setColumnPrintRepeatedDetailValues(false)
                                 .setColumnWidth(250)

                                 .setGroupHeaderLayout(GroupHeaderLayout.TITLE_AND_VALUE)
                                 .setGroupHideColumn(false)
                                 .setGroupShowColumnHeaderAndFooter(true)
                                 .setGroupPadding(20)
                                 .setGroupStartInNewPage(true)
                                 .setGroupStartInNewColumn(true)
                                 .setGroupReprintHeaderOnEachPage(true)
                                 .setGroupHeaderWithSubtotal(true)

                                 .setTextFieldWidth(150)

                                 .setImageWidth(110)
                                 .setImageHeight(120)

                                 .setListgap(10)

                                 .setChartWidth(210)
                                 .setChartHeight(220)
                                 .chartSeriesColors(Color.BLUE)
                                 .setChartTheme("customTheme")

                                 .setCrosstabWidth(90)
                                 .setCrosstabHeight(101)
                                 .setCrosstabHighlightOddRows(true)
                                 .setCrosstabOddRowStyle(stl.simpleStyle().setBackgroundColor(Color.ORANGE))
                                 .setCrosstabHighlightEvenRows(true)
                                 .setCrosstabEvenRowStyle(stl.simpleStyle().setBackgroundColor(Color.MAGENTA))
                                 .setCrosstabGroupStyle(stl.style().setBackgroundColor(Color.RED))
                                 .setCrosstabGroupTotalStyle(stl.style().setBackgroundColor(Color.ORANGE))
                                 .setCrosstabGrandTotalStyle(stl.style().setBackgroundColor(Color.BLUE))
                                 .setCrosstabCellStyle(stl.style().setBackgroundColor(Color.CYAN))
                                 .setCrosstabMeasureTitleStyle(stl.style().setBackgroundColor(Color.YELLOW))

                                 .setBooleanColumnStyle(stl.style(stl.pen1Point()))

                                 .setDetailSplitType(SplitType.IMMEDIATE));
    }

    @Test
    public void test() {
        final ReportBuilder<DesignReportBuilder> rb = new DesignReportBuilder();
        configureReport(rb);
        try {
            final DRDesignReport report = new DRDesignReport(rb.getReport());
            Assertions.assertEquals(Locale.ENGLISH, report.getLocale(), "locale");
            Assertions.assertNull( report.getColumnHeaderBand(), "show column title");
            Assertions.assertTrue(report.isIgnorePagination(), "ignore pagination");
            Assertions.assertEquals(WhenNoDataType.ALL_SECTIONS_NO_DETAIL, report.getWhenNoDataType(), "when no data type");
            Assertions.assertEquals(WhenResourceMissingType.KEY, report.getWhenResourceMissingType(), "when resource missing type");
            Assertions.assertTrue(report.isTitleOnANewPage(), "title on a new page");
            Assertions.assertTrue(report.isSummaryOnANewPage(), "summary on a new page");
            Assertions.assertTrue(report.isSummaryWithPageHeaderAndFooter(), "summary with page header and footer");
            Assertions.assertTrue(report.isFloatColumnFooter(), "float column footer");
            Assertions.assertEquals(Orientation.HORIZONTAL, report.getPrintOrder(), "print order");
            Assertions.assertEquals( RunDirection.RIGHT_TO_LEFT, report.getColumnDirection(), "column direction");
            Assertions.assertEquals( Language.GROOVY, report.getLanguage(),"language");

            final DRDesignTextField columnTextField1 = (DRDesignTextField) ((DRDesignList) report.getDetailBands().get(0).getBandComponent()).getComponents().get(0);
            DRIDesignStyle style = columnTextField1.getStyle();
            Assertions.assertEquals( Color.BLUE, style.getConditionalStyles().get(0).getBackgroundColor(),"detail odd row style");
            Assertions.assertEquals( Color.CYAN, style.getConditionalStyles().get(1).getBackgroundColor(), "detail even row style");
            Assertions.assertEquals( Integer.valueOf(12), style.getParentStyle().getFont().getFontSize(), "default font");
            Assertions.assertTrue( style.getParentStyle().getFont().getBold(), "text style bold");

            Assertions.assertEquals(1190, report.getPage().getWidth(),"page width");
            Assertions.assertEquals(842, report.getPage().getHeight(), "page height");
            Assertions.assertEquals(PageOrientation.LANDSCAPE, report.getPage().getOrientation(), "page orientation");
            Assertions.assertEquals(3, report.getPage().getMargin().getLeft(),"page margin");
            Assertions.assertEquals(3, report.getPage().getColumnsPerPage(),"page columns per page");
            Assertions.assertEquals(20, report.getPage().getColumnSpace(),"page columns spac");

            Assertions.assertFalse(columnTextField1.isPrintRepeatedValues(),"column print repeated detail values");
            Assertions.assertEquals(Integer.valueOf(180), columnTextField1.getWidth(),"column width");

            final DRDesignTextField columnTextField2 = (DRDesignTextField) ((DRDesignList) report.getDetailBands().get(0).getBandComponent()).getComponents().get(1);
            style = columnTextField2.getStyle();
            Assertions.assertEquals(Color.BLUE, style.getConditionalStyles().get(0).getBackgroundColor(), "detail odd row style");
            Assertions.assertEquals(Color.CYAN, style.getConditionalStyles().get(1).getBackgroundColor(), "detail even row style");
            Assertions.assertEquals(Float.valueOf(1), style.getParentStyle().getBorder().getTopPen().getLineWidth(), "boolean border");
            Assertions.assertEquals(Integer.valueOf(181), columnTextField2.getWidth(), "column width");

            final DRDesignGroup group = (DRDesignGroup) report.getGroups().toArray()[0];
            final DRDesignComponent textField = group.getHeaderBands().get(1).getBandComponent();
            Assertions.assertEquals(2, ((DRDesignList) group.getHeaderBands().get(0).getBandComponent()).getComponents().size(), "group header layout");
            Assertions.assertEquals("groupHeader.textField1", textField.getUniqueName(), "group header layout");
            Assertions.assertEquals(Integer.valueOf(20), columnTextField1.getX(), "group padding");
            Assertions.assertTrue(group.isStartInNewPage(), "group start in new page");
            Assertions.assertTrue( group.isStartInNewColumn(),"group start in new column");
            Assertions.assertTrue(group.isReprintHeaderOnEachPage(), "group reprint header on each page");
            Assertions.assertTrue(group.isHeaderWithSubtotal(), "group header with subtotal");

            Assertions.assertEquals(Integer.valueOf(150), textField.getWidth(), "text field width");

            final DRDesignList titleList = (DRDesignList) report.getTitleBand().getBandComponent();
            Assertions.assertEquals(10, titleList.getGap(), "list gap");

            final DRDesignComponent image = titleList.getComponents().get(0);
            Assertions.assertEquals( Integer.valueOf(110), image.getWidth(), "image width");
            Assertions.assertEquals( Integer.valueOf(120), image.getHeight(), "image height");

            final DRDesignComponent chart = titleList.getComponents().get(1);
            Assertions.assertEquals(Integer.valueOf(210), chart.getWidth(), "chart width");
            Assertions.assertEquals(Integer.valueOf(220), chart.getHeight(),"chart height");
            Assertions.assertEquals(Color.BLUE, ((AbstractDesignBasePlot) ((DRDesignChart) chart).getPlot()).getSeriesColors().get(0),"chart colors");
            Assertions.assertEquals("customTheme", ((DRDesignChart) chart).getTheme(),"chart theme");

            final DRDesignCrosstab crosstab = (DRDesignCrosstab) titleList.getComponents().get(2);
            Assertions.assertEquals(Integer.valueOf(90), crosstab.getWidth(), "crosstab width");
            Assertions.assertEquals(Integer.valueOf(101), crosstab.getHeight(), "crosstab height");
            style = crosstab.getCells().get(0).getContent().getComponent().getStyle();
            Assertions.assertEquals(new Color(63, 241, 191), style.getConditionalStyles().get(0).getBackgroundColor(), "crosstab odd row style");
            Assertions.assertEquals(new Color(63, 191, 255), style.getConditionalStyles().get(1).getBackgroundColor(), "crosstab even row style");
            Assertions.assertEquals(Color.CYAN, style.getParentStyle().getBackgroundColor(), "crosstab cell style");
            style = crosstab.getColumnGroups().get(0).getHeader().getComponent().getStyle();
            Assertions.assertEquals(Color.RED, style.getBackgroundColor(), "crosstab column header style");
            style = ((DRIDesignList) crosstab.getColumnGroups().get(0).getTotalHeader().getComponent()).getComponents().get(0).getStyle();
            Assertions.assertEquals(Color.BLUE, style.getBackgroundColor(), "crosstab column total header style");
            style = ((DRIDesignList) crosstab.getColumnGroups().get(1).getHeader().getComponent()).getComponents().get(0).getStyle();
            Assertions.assertEquals( Color.RED, style.getBackgroundColor(), "crosstab column total header style");
            style = ((DRIDesignList) crosstab.getColumnGroups().get(1).getTotalHeader().getComponent()).getComponents().get(0).getStyle();
            Assertions.assertEquals(Color.ORANGE, style.getBackgroundColor(), "crosstab column total header style");
            style = ((DRIDesignList) crosstab.getRowGroups().get(0).getHeader().getComponent()).getComponents().get(0).getStyle();
            Assertions.assertEquals(Color.RED, style.getBackgroundColor(), "crosstab row header style");
            style = crosstab.getRowGroups().get(0).getTotalHeader().getComponent().getStyle();
            Assertions.assertEquals(Color.BLUE, style.getBackgroundColor(),"crosstab row total header style");

            style = crosstab.getColumnGroups().get(0).getHeader().getStyle();
            Assertions.assertEquals(Color.RED, style.getBackgroundColor(), "crosstab column header style");
            style = crosstab.getColumnGroups().get(0).getTotalHeader().getStyle();
            Assertions.assertEquals(Color.BLUE, style.getBackgroundColor(),"crosstab column total header style");
            style = crosstab.getColumnGroups().get(1).getHeader().getStyle();
            Assertions.assertEquals(Color.RED, style.getBackgroundColor(),"crosstab column header style");
            style = crosstab.getColumnGroups().get(1).getTotalHeader().getStyle();
            Assertions.assertEquals(Color.ORANGE, style.getBackgroundColor(), "crosstab column total header style");
            style = crosstab.getRowGroups().get(0).getHeader().getStyle();
            Assertions.assertEquals(Color.RED, style.getBackgroundColor(), "crosstab row header style");
            style = crosstab.getRowGroups().get(0).getTotalHeader().getStyle();
            Assertions.assertEquals(Color.BLUE, style.getBackgroundColor(), "crosstab row total header style");

            style = ((DRIDesignList) crosstab.getColumnGroups().get(0).getTotalHeader().getComponent()).getComponents().get(1).getStyle();
            Assertions.assertEquals(Color.YELLOW, style.getBackgroundColor(), "crosstab measure title header style");
            style = ((DRIDesignList) crosstab.getColumnGroups().get(1).getHeader().getComponent()).getComponents().get(1).getStyle();
            Assertions.assertEquals(Color.YELLOW, style.getBackgroundColor(),"crosstab measure title header style");
            style = ((DRIDesignList) crosstab.getColumnGroups().get(1).getTotalHeader().getComponent()).getComponents().get(1).getStyle();
            Assertions.assertEquals(Color.YELLOW, style.getBackgroundColor(), "crosstab measure title header style");

            Assertions.assertEquals(SplitType.IMMEDIATE, report.getDetailBands().get(0).getSplitType(), "detail split type");
        } catch (final DRException e) {
            e.printStackTrace();
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    public void styleTest() {
        final ReportBuilder<?> rb = new DesignReportBuilder();
        TextColumnBuilder<Integer> column1;

        rb.columns(column1 = col.column("Column1", "field1", Integer.class))
          .groupBy(grp.group(column1).setHeaderLayout(GroupHeaderLayout.TITLE_AND_VALUE).setHideColumn(false))
          .subtotalsAtSummary(sbt.sum(column1))
          .title(cmp.image(""), cht.areaChart().setCategory("field2", String.class))
          .setTemplate(template().setColumnStyle(stl.style().setFontSize(1))
                                 .setColumnTitleStyle(stl.style().setFontSize(2))
                                 .setGroupStyle(stl.style().setFontSize(3))
                                 .setGroupTitleStyle(stl.style().setFontSize(4))
                                 .setSubtotalStyle(stl.style().setFontSize(5))
                                 .setImageStyle(stl.style().setBorder(stl.pen1Point()))
                                 .setChartStyle(stl.style().setBorder(stl.pen2Point())));
        try {
            final DRDesignReport report = new DRDesignReport(rb.getReport());

            DRDesignTextField textField = (DRDesignTextField) ((DRDesignList) report.getDetailBands().get(0).getBandComponent()).getComponents().get(0);
            Assertions.assertEquals(Integer.valueOf(1), textField.getStyle().getFont().getFontSize(), "column style");

            textField = (DRDesignTextField) ((DRDesignList) report.getColumnHeaderBand().getBandComponent()).getComponents().get(1);
            Assertions.assertEquals(Integer.valueOf(2), textField.getStyle().getFont().getFontSize(), "column title style");

            final DRDesignList groupHeaderComponent = (DRDesignList) new ArrayList<>(report.getGroups()).get(0).getHeaderBands().get(0).getBandComponent();
            textField = (DRDesignTextField) groupHeaderComponent.getComponents().get(1);
            Assertions.assertEquals(Integer.valueOf(3), textField.getStyle().getFont().getFontSize(), "group style");

            textField = (DRDesignTextField) groupHeaderComponent.getComponents().get(0);
            Assertions.assertEquals(Integer.valueOf(4), textField.getStyle().getFont().getFontSize(), "group title style");

            textField = (DRDesignTextField) ((DRDesignList) report.getSummaryBand().getBandComponent()).getComponents().get(0);
            Assertions.assertEquals(Integer.valueOf(5), textField.getStyle().getFont().getFontSize(), "subtotal style");

            final DRDesignImage image = (DRDesignImage) ((DRDesignList) report.getTitleBand().getBandComponent()).getComponents().get(0);
            Assertions.assertEquals(Float.valueOf(1), image.getStyle().getBorder().getTopPen().getLineWidth(), "image style");

            final DRDesignChart chart = (DRDesignChart) ((DRDesignList) report.getTitleBand().getBandComponent()).getComponents().get(1);
            Assertions.assertEquals(Float.valueOf(2), chart.getStyle().getBorder().getTopPen().getLineWidth(), "chart style");
        } catch (final DRException e) {
            e.printStackTrace();
            Assertions.fail(e.getMessage());
        }
    }
}
