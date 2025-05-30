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
package ch.unibas.medizin.dynamicreports.report.definition.chart.plot;

import ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression;
import ch.unibas.medizin.dynamicreports.report.definition.style.DRIFont;

import java.awt.Color;
import java.io.Serializable;

/**
 * <p>DRIAxisFormat interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface DRIAxisFormat extends Serializable {

    /**
     * <p>getLabelExpression.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression} object.
     */
    DRIExpression<String> getLabelExpression();

    /**
     * <p>getLabelFont.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.style.DRIFont} object.
     */
    DRIFont getLabelFont();

    /**
     * <p>getLabelColor.</p>
     *
     * @return a {@link java.awt.Color} object.
     */
    Color getLabelColor();

    /**
     * <p>getTickLabelFont.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.style.DRIFont} object.
     */
    DRIFont getTickLabelFont();

    /**
     * <p>getTickLabelColor.</p>
     *
     * @return a {@link java.awt.Color} object.
     */
    Color getTickLabelColor();

    /**
     * <p>getTickLabelMask.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    String getTickLabelMask();

    /**
     * <p>getVerticalTickLabels.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean getVerticalTickLabels();

    /**
     * <p>getTickLabelRotation.</p>
     *
     * @return a {@link java.lang.Double} object.
     */
    Double getTickLabelRotation();

    /**
     * <p>getLineColor.</p>
     *
     * @return a {@link java.awt.Color} object.
     */
    Color getLineColor();

    /**
     * <p>getRangeMinValueExpression.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression} object.
     */
    DRIExpression<? extends Number> getRangeMinValueExpression();

    /**
     * <p>getRangeMaxValueExpression.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression} object.
     */
    DRIExpression<? extends Number> getRangeMaxValueExpression();
}
