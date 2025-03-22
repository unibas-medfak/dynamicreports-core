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
package ch.unibas.medizin.dynamicreports.jasper.transformation;

import ch.unibas.medizin.dynamicreports.design.definition.DRIDesignDataset;
import ch.unibas.medizin.dynamicreports.design.definition.DRIDesignReport;
import ch.unibas.medizin.dynamicreports.jasper.base.JasperCustomValues;
import ch.unibas.medizin.dynamicreports.report.definition.ReportParameters;
import net.sf.jasperreports.engine.design.JasperDesign;

import java.util.Map;

/**
 * <p>JasperTransformAccessor interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface JasperTransformAccessor {

    /**
     * <p>getReport.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.definition.DRIDesignReport} object.
     */
    DRIDesignReport getReport();

    /**
     * <p>getDesign.</p>
     *
     * @return a {@link net.sf.jasperreports.engine.design.JasperDesign} object.
     */
    JasperDesign getDesign();

    /**
     * <p>getCustomValues.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.base.JasperCustomValues} object.
     */
    JasperCustomValues getCustomValues();

    /**
     * <p>getParameters.</p>
     *
     * @return a {@link java.util.Map} object.
     */
    Map<String, Object> getParameters();

    /**
     * <p>getParameterValues.</p>
     *
     * @return a {@link java.util.Map} object.
     */
    Map<String, Object> getParameterValues();

    /**
     * <p>getStartPageNumber.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    Integer getStartPageNumber();

    /**
     * <p>getMasterReportParameters.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.ReportParameters} object.
     */
    ReportParameters getMasterReportParameters();

    /**
     * <p>getReportTransform.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.transformation.ReportTransform} object.
     */
    ReportTransform getReportTransform();

    /**
     * <p>transformToMainDataset.</p>
     */
    void transformToMainDataset();

    /**
     * <p>transformToDataset.</p>
     *
     * @param dataset a {@link ch.unibas.medizin.dynamicreports.design.definition.DRIDesignDataset} object.
     */
    void transformToDataset(DRIDesignDataset dataset);

    /**
     * <p>getExpressionTransform.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.transformation.AbstractExpressionTransform} object.
     */
    AbstractExpressionTransform getExpressionTransform();

    /**
     * <p>getExpressionTransform.</p>
     *
     * @param dataset a {@link ch.unibas.medizin.dynamicreports.design.definition.DRIDesignDataset} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.transformation.AbstractExpressionTransform} object.
     */
    AbstractExpressionTransform getExpressionTransform(DRIDesignDataset dataset);

    /**
     * <p>getGroupTransform.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.transformation.GroupTransform} object.
     */
    GroupTransform getGroupTransform();

    /**
     * <p>getComponentTransform.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.transformation.ComponentTransform} object.
     */
    ComponentTransform getComponentTransform();

    /**
     * <p>getStyleTransform.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.transformation.StyleTransform} object.
     */
    StyleTransform getStyleTransform();

    /**
     * <p>getChartTransform.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.transformation.ChartTransform} object.
     */
    ChartTransform getChartTransform();

    /**
     * <p>getCrosstabTransform.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.transformation.CrosstabTransform} object.
     */
    CrosstabTransform getCrosstabTransform();

    /**
     * <p>getDatasetTransform.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.transformation.DatasetTransform} object.
     */
    DatasetTransform getDatasetTransform();
}
