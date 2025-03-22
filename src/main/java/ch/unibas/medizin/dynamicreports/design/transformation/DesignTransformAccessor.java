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
package ch.unibas.medizin.dynamicreports.design.transformation;

import ch.unibas.medizin.dynamicreports.design.base.DRDesignPage;
import ch.unibas.medizin.dynamicreports.report.constant.WhenResourceMissingType;
import ch.unibas.medizin.dynamicreports.report.definition.DRIDataset;
import ch.unibas.medizin.dynamicreports.report.definition.DRIReport;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * <p>DesignTransformAccessor interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface DesignTransformAccessor {

    /**
     * <p>getReport.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.DRIReport} object.
     */
    DRIReport getReport();

    /**
     * <p>getPageWidth.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    Integer getPageWidth();

    /**
     * <p>getReportTransform.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.transformation.ReportTransform} object.
     */
    ReportTransform getReportTransform();

    /**
     * <p>getTemplateTransform.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.transformation.TemplateTransform} object.
     */
    TemplateTransform getTemplateTransform();

    /**
     * <p>transformToMainDataset.</p>
     */
    void transformToMainDataset();

    /**
     * <p>transformToDataset.</p>
     *
     * @param dataset a {@link ch.unibas.medizin.dynamicreports.report.definition.DRIDataset} object.
     */
    void transformToDataset(DRIDataset dataset);

    /**
     * <p>getExpressionTransform.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.transformation.AbstractExpressionTransform} object.
     */
    AbstractExpressionTransform getExpressionTransform();

    /**
     * <p>getPageTransform.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.transformation.PageTransform} object.
     */
    PageTransform getPageTransform();

    /**
     * <p>getBandTransform.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.transformation.BandTransform} object.
     */
    BandTransform getBandTransform();

    /**
     * <p>getComponentTransform.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.transformation.ComponentTransform} object.
     */
    ComponentTransform getComponentTransform();

    /**
     * <p>getGroupTransform.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.transformation.GroupTransform} object.
     */
    GroupTransform getGroupTransform();

    /**
     * <p>getColumnTransform.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.transformation.ColumnTransform} object.
     */
    ColumnTransform getColumnTransform();

    /**
     * <p>getColumnGridTransform.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.transformation.ColumnGridTransform} object.
     */
    ColumnGridTransform getColumnGridTransform();

    /**
     * <p>getStyleTransform.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.transformation.StyleTransform} object.
     */
    StyleTransform getStyleTransform();

    /**
     * <p>getChartTransform.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.transformation.ChartTransform} object.
     */
    ChartTransform getChartTransform();

    /**
     * <p>getCrosstabTransform.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.transformation.CrosstabTransform} object.
     */
    CrosstabTransform getCrosstabTransform();

    /**
     * <p>getDatasetTransform.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.transformation.DatasetTransform} object.
     */
    DatasetTransform getDatasetTransform();

    /**
     * <p>getTableOfContentsTransform.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.transformation.TableOfContentsTransform} object.
     */
    TableOfContentsTransform getTableOfContentsTransform();

    /**
     * <p>isTableOfContents.</p>
     *
     * @return a boolean.
     */
    boolean isTableOfContents();

    /**
     * <p>getPage.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.base.DRDesignPage} object.
     */
    DRDesignPage getPage();

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
     * <p>getWhenResourceMissingType.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.WhenResourceMissingType} object.
     */
    WhenResourceMissingType getWhenResourceMissingType();
}
