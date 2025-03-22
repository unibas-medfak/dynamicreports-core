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

import ch.unibas.medizin.dynamicreports.report.constant.ValueLocation;
import ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression;
import ch.unibas.medizin.dynamicreports.report.definition.style.DRIFont;

import java.awt.Color;

/**
 * <p>DRIThermometerPlot interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface DRIThermometerPlot extends DRIPlot {

    /**
     * <p>getDataRangeLowExpression.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression} object.
     */
    DRIExpression<? extends Number> getDataRangeLowExpression();

    /**
     * <p>getDataRangeHighExpression.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression} object.
     */
    DRIExpression<? extends Number> getDataRangeHighExpression();

    /**
     * <p>getValueColor.</p>
     *
     * @return a {@link java.awt.Color} object.
     */
    Color getValueColor();

    /**
     * <p>getValueMask.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    String getValueMask();

    /**
     * <p>getValueFont.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.style.DRIFont} object.
     */
    DRIFont getValueFont();

    /**
     * <p>getValueLocation.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.ValueLocation} object.
     */
    ValueLocation getValueLocation();

    /**
     * <p>getMercuryColor.</p>
     *
     * @return a {@link java.awt.Color} object.
     */
    Color getMercuryColor();

    /**
     * <p>getLowDataRangeLowExpression.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression} object.
     */
    DRIExpression<? extends Number> getLowDataRangeLowExpression();

    /**
     * <p>getLowDataRangeHighExpression.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression} object.
     */
    DRIExpression<? extends Number> getLowDataRangeHighExpression();

    /**
     * <p>getMediumDataRangeLowExpression.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression} object.
     */
    DRIExpression<? extends Number> getMediumDataRangeLowExpression();

    /**
     * <p>getMediumDataRangeHighExpression.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression} object.
     */
    DRIExpression<? extends Number> getMediumDataRangeHighExpression();

    /**
     * <p>getHighDataRangeLowExpression.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression} object.
     */
    DRIExpression<? extends Number> getHighDataRangeLowExpression();

    /**
     * <p>getHighDataRangeHighExpression.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression} object.
     */
    DRIExpression<? extends Number> getHighDataRangeHighExpression();
}
