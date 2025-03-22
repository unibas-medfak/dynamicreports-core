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
package ch.unibas.medizin.dynamicreports.design.definition;

import ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignComplexExpression;
import ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignExpression;
import ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignJasperExpression;
import ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignSimpleExpression;
import ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignSystemExpression;
import ch.unibas.medizin.dynamicreports.design.definition.style.DRIDesignStyle;
import ch.unibas.medizin.dynamicreports.jasper.base.tableofcontents.JasperTocHeading;
import ch.unibas.medizin.dynamicreports.report.constant.Orientation;
import ch.unibas.medizin.dynamicreports.report.constant.RunDirection;
import ch.unibas.medizin.dynamicreports.report.constant.WhenNoDataType;
import ch.unibas.medizin.dynamicreports.report.constant.WhenResourceMissingType;
import ch.unibas.medizin.dynamicreports.report.definition.DRIScriptlet;
import ch.unibas.medizin.dynamicreports.report.definition.DRITableOfContentsCustomizer;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * <p>DRIDesignReport interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface DRIDesignReport extends Serializable {

    /**
     * <p>getTemplateDesign.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.definition.DRIDesignTemplateDesign} object.
     */
    DRIDesignTemplateDesign getTemplateDesign();

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
     * <p>isIgnorePagination.</p>
     *
     * @return a boolean.
     */
    boolean isIgnorePagination();

    /**
     * <p>getProperties.</p>
     *
     * @return a {@link java.util.Properties} object.
     */
    Properties getProperties();

    /**
     * <p>getQuery.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.definition.DRIDesignQuery} object.
     */
    DRIDesignQuery getQuery();

    /**
     * <p>getPage.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.definition.DRIDesignPage} object.
     */
    DRIDesignPage getPage();

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
     * <p>isTitleOnANewPage.</p>
     *
     * @return a boolean.
     */
    boolean isTitleOnANewPage();

    /**
     * <p>isSummaryOnANewPage.</p>
     *
     * @return a boolean.
     */
    boolean isSummaryOnANewPage();

    /**
     * <p>isSummaryWithPageHeaderAndFooter.</p>
     *
     * @return a boolean.
     */
    boolean isSummaryWithPageHeaderAndFooter();

    /**
     * <p>isFloatColumnFooter.</p>
     *
     * @return a boolean.
     */
    boolean isFloatColumnFooter();

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
     * <p>isTableOfContents.</p>
     *
     * @return a boolean.
     */
    boolean isTableOfContents();

    /**
     * <p>getTableOfContentsHeadings.</p>
     *
     * @return a {@link java.util.Map} object.
     */
    Map<String, JasperTocHeading> getTableOfContentsHeadings();

    /**
     * <p>getTableOfContentsCustomizer.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.DRITableOfContentsCustomizer} object.
     */
    DRITableOfContentsCustomizer getTableOfContentsCustomizer();

    /**
     * <p>getFilterExpression.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignExpression} object.
     */
    DRIDesignExpression getFilterExpression();

    /**
     * <p>getParameters.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    Collection<DRIDesignParameter> getParameters();

    /**
     * <p>getParameterValues.</p>
     *
     * @return a {@link java.util.Map} object.
     */
    Map<String, Object> getParameterValues();

    /**
     * <p>getScriptlets.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    Collection<DRIScriptlet> getScriptlets();

    /**
     * <p>getFields.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    Collection<DRIDesignField> getFields();

    /**
     * <p>getSystemExpressions.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    Collection<DRIDesignSystemExpression> getSystemExpressions();

    /**
     * <p>getJasperExpressions.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    Collection<DRIDesignJasperExpression> getJasperExpressions();

    /**
     * <p>getSimpleExpressions.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    Collection<DRIDesignSimpleExpression> getSimpleExpressions();

    /**
     * <p>getStyles.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    Collection<DRIDesignStyle> getStyles();

    /**
     * <p>getGroups.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    Collection<? extends DRIDesignGroup> getGroups();

    /**
     * <p>getVariables.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    Collection<DRIDesignVariable> getVariables();

    /**
     * <p>getComplexExpressions.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    Collection<DRIDesignComplexExpression> getComplexExpressions();

    /**
     * <p>getSorts.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    Collection<DRIDesignSort> getSorts();

    /**
     * <p>getDatasets.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    Collection<DRIDesignDataset> getDatasets();

    /**
     * <p>getTitleBand.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.definition.DRIDesignBand} object.
     */
    DRIDesignBand getTitleBand();

    /**
     * <p>getPageHeaderBand.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.definition.DRIDesignBand} object.
     */
    DRIDesignBand getPageHeaderBand();

    /**
     * <p>getPageFooterBand.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.definition.DRIDesignBand} object.
     */
    DRIDesignBand getPageFooterBand();

    /**
     * <p>getColumnHeaderBand.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.definition.DRIDesignBand} object.
     */
    DRIDesignBand getColumnHeaderBand();

    /**
     * <p>getColumnFooterBand.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.definition.DRIDesignBand} object.
     */
    DRIDesignBand getColumnFooterBand();

    /**
     * <p>getDetailBands.</p>
     *
     * @return a {@link java.util.List} object.
     */
    List<? extends DRIDesignBand> getDetailBands();

    /**
     * <p>getLastPageFooterBand.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.definition.DRIDesignBand} object.
     */
    DRIDesignBand getLastPageFooterBand();

    /**
     * <p>getSummaryBand.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.definition.DRIDesignBand} object.
     */
    DRIDesignBand getSummaryBand();

    /**
     * <p>getNoDataBand.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.definition.DRIDesignBand} object.
     */
    DRIDesignBand getNoDataBand();

    /**
     * <p>getBackgroundBand.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.definition.DRIDesignBand} object.
     */
    DRIDesignBand getBackgroundBand();
}
