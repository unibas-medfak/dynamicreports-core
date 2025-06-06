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

import ch.unibas.medizin.dynamicreports.report.constant.Orientation;
import ch.unibas.medizin.dynamicreports.report.constant.RunDirection;
import ch.unibas.medizin.dynamicreports.report.constant.WhenNoDataType;
import ch.unibas.medizin.dynamicreports.report.constant.WhenResourceMissingType;
import ch.unibas.medizin.dynamicreports.report.definition.column.DRIColumn;
import ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression;
import ch.unibas.medizin.dynamicreports.report.definition.grid.DRIColumnGrid;
import ch.unibas.medizin.dynamicreports.report.definition.style.DRIConditionalStyle;
import ch.unibas.medizin.dynamicreports.report.definition.style.DRIFont;
import ch.unibas.medizin.dynamicreports.report.definition.style.DRIReportStyle;
import ch.unibas.medizin.dynamicreports.report.definition.style.DRISimpleStyle;
import ch.unibas.medizin.dynamicreports.report.definition.style.DRIStyle;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * <p>DRIReport interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface DRIReport extends Serializable {

    /**
     * <p>getTemplate.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.DRIReportTemplate} object.
     */
    DRIReportTemplate getTemplate();

    /**
     * <p>getTemplateStyles.</p>
     *
     * @return a {@link java.util.List} object.
     */
    List<DRIStyle> getTemplateStyles();

    /**
     * <p>getTemplateDesign.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.DRITemplateDesign} object.
     */
    DRITemplateDesign<?> getTemplateDesign();

    /**
     * <p>getReportName.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    String getReportName();

    /**
     * <p>getLocale.</p>
     *
     * @return a {@link java.util.Locale} object.
     */
    Locale getLocale();

    /**
     * <p>getResourceBundle.</p>
     *
     * @return a {@link java.util.ResourceBundle} object.
     */
    ResourceBundle getResourceBundle();

    /**
     * <p>getResourceBundleName.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    String getResourceBundleName();

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
     * <p>getColumns.</p>
     *
     * @return a {@link java.util.List} object.
     */
    List<? extends DRIColumn<?>> getColumns();

    /**
     * <p>getGroups.</p>
     *
     * @return a {@link java.util.List} object.
     */
    List<? extends DRIGroup> getGroups();

    /**
     * <p>getFields.</p>
     *
     * @return a {@link java.util.List} object.
     */
    List<? extends DRIField<?>> getFields();

    /**
     * <p>getVariables.</p>
     *
     * @return a {@link java.util.List} object.
     */
    List<? extends DRIVariable<?>> getVariables();

    /**
     * <p>getSorts.</p>
     *
     * @return a {@link java.util.List} object.
     */
    List<? extends DRISort> getSorts();

    /**
     * <p>getSubtotals.</p>
     *
     * @return a {@link java.util.List} object.
     */
    List<? extends DRISubtotal<?>> getSubtotals();

    /**
     * <p>getParameters.</p>
     *
     * @return a {@link java.util.List} object.
     */
    List<? extends DRIParameter<?>> getParameters();

    /**
     * <p>getParameterValues.</p>
     *
     * @return a {@link java.util.Map} object.
     */
    Map<String, Object> getParameterValues();

    /**
     * <p>getScriptlets.</p>
     *
     * @return a {@link java.util.List} object.
     */
    List<DRIScriptlet> getScriptlets();

    /**
     * <p>getProperties.</p>
     *
     * @return a {@link java.util.Properties} object.
     */
    Properties getProperties();

    /**
     * <p>getQuery.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.DRIQuery} object.
     */
    DRIQuery getQuery();

    /**
     * <p>getPage.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.DRIPage} object.
     */
    DRIPage getPage();

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
     * <p>getDetailRowHighlighters.</p>
     *
     * @return a {@link java.util.List} object.
     */
    List<? extends DRIConditionalStyle> getDetailRowHighlighters();

    /**
     * <p>getColumnGrid.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.grid.DRIColumnGrid} object.
     */
    DRIColumnGrid getColumnGrid();

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
     * <p>getFilterExpression.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression} object.
     */
    DRIExpression<Boolean> getFilterExpression();

    /**
     * <p>getTitleBand.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.DRIBand} object.
     */
    DRIBand getTitleBand();

    /**
     * <p>getPageHeaderBand.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.DRIBand} object.
     */
    DRIBand getPageHeaderBand();

    /**
     * <p>getPageFooterBand.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.DRIBand} object.
     */
    DRIBand getPageFooterBand();

    /**
     * <p>getColumnHeaderBand.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.DRIBand} object.
     */
    DRIBand getColumnHeaderBand();

    /**
     * <p>getColumnFooterBand.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.DRIBand} object.
     */
    DRIBand getColumnFooterBand();

    /**
     * <p>getDetailBand.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.DRIBand} object.
     */
    DRIBand getDetailBand();

    /**
     * <p>getDetailHeaderBand.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.DRIBand} object.
     */
    DRIBand getDetailHeaderBand();

    /**
     * <p>getDetailFooterBand.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.DRIBand} object.
     */
    DRIBand getDetailFooterBand();

    /**
     * <p>getLastPageFooterBand.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.DRIBand} object.
     */
    DRIBand getLastPageFooterBand();

    /**
     * <p>getSummaryBand.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.DRIBand} object.
     */
    DRIBand getSummaryBand();

    /**
     * <p>getNoDataBand.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.DRIBand} object.
     */
    DRIBand getNoDataBand();

    /**
     * <p>getBackgroundBand.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.DRIBand} object.
     */
    DRIBand getBackgroundBand();
}
