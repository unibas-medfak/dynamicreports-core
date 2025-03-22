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
package ch.unibas.medizin.dynamicreports.report.definition;

import ch.unibas.medizin.dynamicreports.report.constant.BooleanComponentType;
import ch.unibas.medizin.dynamicreports.report.constant.GroupFooterPosition;
import ch.unibas.medizin.dynamicreports.report.constant.GroupHeaderLayout;
import ch.unibas.medizin.dynamicreports.report.constant.Orientation;
import ch.unibas.medizin.dynamicreports.report.constant.PageOrientation;
import ch.unibas.medizin.dynamicreports.report.constant.Position;
import ch.unibas.medizin.dynamicreports.report.constant.RunDirection;
import ch.unibas.medizin.dynamicreports.report.constant.SplitType;
import ch.unibas.medizin.dynamicreports.report.constant.WhenNoDataType;
import ch.unibas.medizin.dynamicreports.report.constant.WhenResourceMissingType;
import ch.unibas.medizin.dynamicreports.report.definition.component.DRIComponent;
import ch.unibas.medizin.dynamicreports.report.definition.style.DRIFont;
import ch.unibas.medizin.dynamicreports.report.definition.style.DRIReportStyle;
import ch.unibas.medizin.dynamicreports.report.definition.style.DRISimpleStyle;
import ch.unibas.medizin.dynamicreports.report.definition.style.DRIStyle;

import java.awt.Color;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;

/**
 * <p>DRIReportTemplate interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface DRIReportTemplate extends Serializable {

    /**
     * <p>getTemplateStyles.</p>
     *
     * @return a {@link java.util.List} object.
     */
    List<DRIStyle> getTemplateStyles();

    /**
     * <p>getLocale.</p>
     *
     * @return a {@link java.util.Locale} object.
     */
    Locale getLocale();

    /**
     * <p>getShowColumnTitle.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean getShowColumnTitle();

    /**
     * <p>getShowColumnValues.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean getShowColumnValues();

    /**
     * <p>getIgnorePagination.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean getIgnorePagination();

    /**
     * <p>getWhenNoDataType.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.WhenNoDataType} object.
     */
    WhenNoDataType getWhenNoDataType();

    /**
     * <p>getWhenResourceMissingType.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.WhenResourceMissingType} object.
     */
    WhenResourceMissingType getWhenResourceMissingType();

    /**
     * <p>getTitleOnANewPage.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean getTitleOnANewPage();

    /**
     * <p>getSummaryOnANewPage.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean getSummaryOnANewPage();

    /**
     * <p>getSummaryWithPageHeaderAndFooter.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean getSummaryWithPageHeaderAndFooter();

    /**
     * <p>getFloatColumnFooter.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean getFloatColumnFooter();

    /**
     * <p>getPrintOrder.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.Orientation} object.
     */
    Orientation getPrintOrder();

    /**
     * <p>getColumnDirection.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.RunDirection} object.
     */
    RunDirection getColumnDirection();

    /**
     * <p>getLanguage.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    String getLanguage();

    /**
     * <p>getUseFieldNameAsDescription.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean getUseFieldNameAsDescription();

    /**
     * <p>getHighlightDetailOddRows.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean getHighlightDetailOddRows();

    /**
     * <p>getDetailOddRowStyle.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.style.DRISimpleStyle} object.
     */
    DRISimpleStyle getDetailOddRowStyle();

    /**
     * <p>getHighlightDetailEvenRows.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean getHighlightDetailEvenRows();

    /**
     * <p>getDetailEvenRowStyle.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.style.DRISimpleStyle} object.
     */
    DRISimpleStyle getDetailEvenRowStyle();

    /**
     * <p>getDefaultFont.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.style.DRIFont} object.
     */
    DRIFont getDefaultFont();

    /**
     * <p>getTextStyle.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.style.DRIReportStyle} object.
     */
    DRIReportStyle getTextStyle();

    /**
     * <p>getColumnTitleStyle.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.style.DRIReportStyle} object.
     */
    DRIReportStyle getColumnTitleStyle();

    /**
     * <p>getColumnStyle.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.style.DRIReportStyle} object.
     */
    DRIReportStyle getColumnStyle();

    /**
     * <p>getGroupTitleStyle.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.style.DRIReportStyle} object.
     */
    DRIReportStyle getGroupTitleStyle();

    /**
     * <p>getGroupStyle.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.style.DRIReportStyle} object.
     */
    DRIReportStyle getGroupStyle();

    /**
     * <p>getSubtotalStyle.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.style.DRIReportStyle} object.
     */
    DRIReportStyle getSubtotalStyle();

    /**
     * <p>getImageStyle.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.style.DRIReportStyle} object.
     */
    DRIReportStyle getImageStyle();

    /**
     * <p>getChartStyle.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.style.DRIReportStyle} object.
     */
    DRIReportStyle getChartStyle();

    /**
     * <p>getPageWidth.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    Integer getPageWidth();

    /**
     * <p>getPageHeight.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    Integer getPageHeight();

    /**
     * <p>getPageOrientation.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.PageOrientation} object.
     */
    PageOrientation getPageOrientation();

    /**
     * <p>getPageMargin.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.DRIMargin} object.
     */
    DRIMargin getPageMargin();

    /**
     * <p>getPageColumnsPerPage.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    Integer getPageColumnsPerPage();

    /**
     * <p>getPageColumnSpace.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    Integer getPageColumnSpace();

    /**
     * <p>getIgnorePageWidth.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean getIgnorePageWidth();

    /**
     * <p>getColumnPrintRepeatedDetailValues.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean getColumnPrintRepeatedDetailValues();

    /**
     * <p>getColumnWidth.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    Integer getColumnWidth();

    /**
     * <p>getGroupHeaderLayout.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.GroupHeaderLayout} object.
     */
    GroupHeaderLayout getGroupHeaderLayout();

    /**
     * <p>getGroupHideColumn.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean getGroupHideColumn();

    /**
     * <p>getGroupShowColumnHeaderAndFooter.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean getGroupShowColumnHeaderAndFooter();

    /**
     * <p>getGroupPadding.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    Integer getGroupPadding();

    /**
     * <p>getGroupStartInNewPage.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean getGroupStartInNewPage();

    /**
     * <p>getGroupStartInNewColumn.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean getGroupStartInNewColumn();

    /**
     * <p>getGroupReprintHeaderOnEachPage.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean getGroupReprintHeaderOnEachPage();

    /**
     * <p>getGroupResetPageNumber.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean getGroupResetPageNumber();

    /**
     * <p>getGroupFooterPosition.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.GroupFooterPosition} object.
     */
    GroupFooterPosition getGroupFooterPosition();

    /**
     * <p>getGroupKeepTogether.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean getGroupKeepTogether();

    /**
     * <p>getGroupHeaderWithSubtotal.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean getGroupHeaderWithSubtotal();

    /**
     * <p>getSubtotalLabelPosition.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.Position} object.
     */
    Position getSubtotalLabelPosition();

    /**
     * <p>getTableOfContents.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean getTableOfContents();

    /**
     * <p>getTableOfContentsCustomizer.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.DRITableOfContentsCustomizer} object.
     */
    DRITableOfContentsCustomizer getTableOfContentsCustomizer();

    /**
     * <p>getTextFieldWidth.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    Integer getTextFieldWidth();

    /**
     * <p>getImageHeight.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    Integer getImageHeight();

    /**
     * <p>getImageWidth.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    Integer getImageWidth();

    /**
     * <p>getListgap.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    Integer getListgap();

    /**
     * <p>getMultiPageListHeight.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    Integer getMultiPageListHeight();

    /**
     * <p>getMultiPageListWidth.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    Integer getMultiPageListWidth();

    /**
     * <p>getChartHeight.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    Integer getChartHeight();

    /**
     * <p>getChartWidth.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    Integer getChartWidth();

    /**
     * <p>getSubreportHeight.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    Integer getSubreportHeight();

    /**
     * <p>getSubreportWidth.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    Integer getSubreportWidth();

    /**
     * <p>getCrosstabHeight.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    Integer getCrosstabHeight();

    /**
     * <p>getCrosstabWidth.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    Integer getCrosstabWidth();

    /**
     * <p>getCrosstabHighlightOddRows.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean getCrosstabHighlightOddRows();

    /**
     * <p>getCrosstabOddRowStyle.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.style.DRISimpleStyle} object.
     */
    DRISimpleStyle getCrosstabOddRowStyle();

    /**
     * <p>getCrosstabHighlightEvenRows.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean getCrosstabHighlightEvenRows();

    /**
     * <p>getCrosstabEvenRowStyle.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.style.DRISimpleStyle} object.
     */
    DRISimpleStyle getCrosstabEvenRowStyle();

    /**
     * <p>getCrosstabGroupStyle.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.style.DRIReportStyle} object.
     */
    DRIReportStyle getCrosstabGroupStyle();

    /**
     * <p>getCrosstabGroupTotalStyle.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.style.DRIReportStyle} object.
     */
    DRIReportStyle getCrosstabGroupTotalStyle();

    /**
     * <p>getCrosstabGrandTotalStyle.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.style.DRIReportStyle} object.
     */
    DRIReportStyle getCrosstabGrandTotalStyle();

    /**
     * <p>getCrosstabCellStyle.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.style.DRIReportStyle} object.
     */
    DRIReportStyle getCrosstabCellStyle();

    /**
     * <p>getCrosstabMeasureTitleStyle.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.style.DRIReportStyle} object.
     */
    DRIReportStyle getCrosstabMeasureTitleStyle();

    /**
     * <p>getChartSeriesColors.</p>
     *
     * @return a {@link java.util.List} object.
     */
    List<Color> getChartSeriesColors();

    /**
     * <p>getChartValuePattern.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    String getChartValuePattern();

    /**
     * <p>getChartPercentValuePattern.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    String getChartPercentValuePattern();

    /**
     * <p>getChartTheme.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    String getChartTheme();

    /**
     * <p>getBooleanComponentType.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.BooleanComponentType} object.
     */
    BooleanComponentType getBooleanComponentType();

    /**
     * <p>getBooleanEmptyWhenNullValue.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean getBooleanEmptyWhenNullValue();

    /**
     * <p>getBooleanImageWidth.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    Integer getBooleanImageWidth();

    /**
     * <p>getBooleanImageHeight.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    Integer getBooleanImageHeight();

    /**
     * <p>getBooleanColumnStyle.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.style.DRIReportStyle} object.
     */
    DRIReportStyle getBooleanColumnStyle();

    /**
     * <p>getDefaultSplitType.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.SplitType} object.
     */
    SplitType getDefaultSplitType();

    /**
     * <p>getTitleSplitType.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.SplitType} object.
     */
    SplitType getTitleSplitType();

    /**
     * <p>getPageHeaderSplitType.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.SplitType} object.
     */
    SplitType getPageHeaderSplitType();

    /**
     * <p>getPageFooterSplitType.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.SplitType} object.
     */
    SplitType getPageFooterSplitType();

    /**
     * <p>getColumnHeaderSplitType.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.SplitType} object.
     */
    SplitType getColumnHeaderSplitType();

    /**
     * <p>getColumnFooterSplitType.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.SplitType} object.
     */
    SplitType getColumnFooterSplitType();

    /**
     * <p>getGroupHeaderSplitType.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.SplitType} object.
     */
    SplitType getGroupHeaderSplitType();

    /**
     * <p>getGroupFooterSplitType.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.SplitType} object.
     */
    SplitType getGroupFooterSplitType();

    /**
     * <p>getDetailHeaderSplitType.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.SplitType} object.
     */
    SplitType getDetailHeaderSplitType();

    /**
     * <p>getDetailSplitType.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.SplitType} object.
     */
    SplitType getDetailSplitType();

    /**
     * <p>getDetailFooterSplitType.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.SplitType} object.
     */
    SplitType getDetailFooterSplitType();

    /**
     * <p>getLastPageFooterSplitType.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.SplitType} object.
     */
    SplitType getLastPageFooterSplitType();

    /**
     * <p>getSummarySplitType.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.SplitType} object.
     */
    SplitType getSummarySplitType();

    /**
     * <p>getNoDataSplitType.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.SplitType} object.
     */
    SplitType getNoDataSplitType();

    /**
     * <p>getBackgroundSplitType.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.SplitType} object.
     */
    SplitType getBackgroundSplitType();

    /**
     * <p>getTitleStyle.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.style.DRIReportStyle} object.
     */
    DRIReportStyle getTitleStyle();

    /**
     * <p>getPageHeaderStyle.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.style.DRIReportStyle} object.
     */
    DRIReportStyle getPageHeaderStyle();

    /**
     * <p>getPageFooterStyle.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.style.DRIReportStyle} object.
     */
    DRIReportStyle getPageFooterStyle();

    /**
     * <p>getColumnHeaderStyle.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.style.DRIReportStyle} object.
     */
    DRIReportStyle getColumnHeaderStyle();

    /**
     * <p>getColumnFooterStyle.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.style.DRIReportStyle} object.
     */
    DRIReportStyle getColumnFooterStyle();

    /**
     * <p>getGroupHeaderStyle.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.style.DRIReportStyle} object.
     */
    DRIReportStyle getGroupHeaderStyle();

    /**
     * <p>getGroupFooterStyle.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.style.DRIReportStyle} object.
     */
    DRIReportStyle getGroupFooterStyle();

    /**
     * <p>getDetailHeaderStyle.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.style.DRIReportStyle} object.
     */
    DRIReportStyle getDetailHeaderStyle();

    /**
     * <p>getDetailStyle.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.style.DRIReportStyle} object.
     */
    DRIReportStyle getDetailStyle();

    /**
     * <p>getDetailFooterStyle.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.style.DRIReportStyle} object.
     */
    DRIReportStyle getDetailFooterStyle();

    /**
     * <p>getLastPageFooterStyle.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.style.DRIReportStyle} object.
     */
    DRIReportStyle getLastPageFooterStyle();

    /**
     * <p>getSummaryStyle.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.style.DRIReportStyle} object.
     */
    DRIReportStyle getSummaryStyle();

    /**
     * <p>getNoDataStyle.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.style.DRIReportStyle} object.
     */
    DRIReportStyle getNoDataStyle();

    /**
     * <p>getBackgroundStyle.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.style.DRIReportStyle} object.
     */
    DRIReportStyle getBackgroundStyle();

    /**
     * <p>getTitleBackgroundComponent.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.component.DRIComponent} object.
     */
    DRIComponent getTitleBackgroundComponent();

    /**
     * <p>getPageHeaderBackgroundComponent.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.component.DRIComponent} object.
     */
    DRIComponent getPageHeaderBackgroundComponent();

    /**
     * <p>getPageFooterBackgroundComponent.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.component.DRIComponent} object.
     */
    DRIComponent getPageFooterBackgroundComponent();

    /**
     * <p>getColumnHeaderBackgroundComponent.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.component.DRIComponent} object.
     */
    DRIComponent getColumnHeaderBackgroundComponent();

    /**
     * <p>getColumnFooterBackgroundComponent.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.component.DRIComponent} object.
     */
    DRIComponent getColumnFooterBackgroundComponent();

    /**
     * <p>getGroupHeaderBackgroundComponent.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.component.DRIComponent} object.
     */
    DRIComponent getGroupHeaderBackgroundComponent();

    /**
     * <p>getGroupFooterBackgroundComponent.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.component.DRIComponent} object.
     */
    DRIComponent getGroupFooterBackgroundComponent();

    /**
     * <p>getDetailHeaderBackgroundComponent.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.component.DRIComponent} object.
     */
    DRIComponent getDetailHeaderBackgroundComponent();

    /**
     * <p>getDetailBackgroundComponent.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.component.DRIComponent} object.
     */
    DRIComponent getDetailBackgroundComponent();

    /**
     * <p>getDetailFooterBackgroundComponent.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.component.DRIComponent} object.
     */
    DRIComponent getDetailFooterBackgroundComponent();

    /**
     * <p>getLastPageFooterBackgroundComponent.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.component.DRIComponent} object.
     */
    DRIComponent getLastPageFooterBackgroundComponent();

    /**
     * <p>getSummaryBackgroundComponent.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.component.DRIComponent} object.
     */
    DRIComponent getSummaryBackgroundComponent();

    /**
     * <p>getNoDataBackgroundComponent.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.component.DRIComponent} object.
     */
    DRIComponent getNoDataBackgroundComponent();

    /**
     * <p>getBackgroundBackgroundComponent.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.component.DRIComponent} object.
     */
    DRIComponent getBackgroundBackgroundComponent();

}
