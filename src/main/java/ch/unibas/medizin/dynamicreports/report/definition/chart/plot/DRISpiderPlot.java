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

import ch.unibas.medizin.dynamicreports.report.constant.SpiderRotation;
import ch.unibas.medizin.dynamicreports.report.constant.TableOrder;
import ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression;
import ch.unibas.medizin.dynamicreports.report.definition.style.DRIFont;

import java.awt.Color;

/**
 * <p>DRISpiderPlot interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface DRISpiderPlot extends DRIPlot {

    /**
     * <p>getMaxValueExpression.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression} object.
     */
    DRIExpression<Double> getMaxValueExpression();

    /**
     * <p>getRotation.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.SpiderRotation} object.
     */
    SpiderRotation getRotation();

    /**
     * <p>getTableOrder.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.TableOrder} object.
     */
    TableOrder getTableOrder();

    /**
     * <p>getWebFilled.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean getWebFilled();

    /**
     * <p>getStartAngle.</p>
     *
     * @return a {@link java.lang.Double} object.
     */
    Double getStartAngle();

    /**
     * <p>getHeadPercent.</p>
     *
     * @return a {@link java.lang.Double} object.
     */
    Double getHeadPercent();

    /**
     * <p>getInteriorGap.</p>
     *
     * @return a {@link java.lang.Double} object.
     */
    Double getInteriorGap();

    /**
     * <p>getAxisLineColor.</p>
     *
     * @return a {@link java.awt.Color} object.
     */
    Color getAxisLineColor();

    /**
     * <p>getAxisLineWidth.</p>
     *
     * @return a {@link java.lang.Float} object.
     */
    Float getAxisLineWidth();

    /**
     * <p>getLabelFont.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.style.DRIFont} object.
     */
    DRIFont getLabelFont();

    /**
     * <p>getLabelGap.</p>
     *
     * @return a {@link java.lang.Double} object.
     */
    Double getLabelGap();

    /**
     * <p>getLabelColor.</p>
     *
     * @return a {@link java.awt.Color} object.
     */
    Color getLabelColor();
}
