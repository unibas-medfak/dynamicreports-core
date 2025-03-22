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
package ch.unibas.medizin.dynamicreports.design.definition.chart.plot;

import ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignExpression;
import ch.unibas.medizin.dynamicreports.design.definition.style.DRIDesignFont;
import ch.unibas.medizin.dynamicreports.report.constant.MeterShape;

import java.awt.Color;
import java.util.List;

/**
 * <p>DRIDesignMeterPlot interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface DRIDesignMeterPlot extends DRIDesignPlot {

    /**
     * <p>getDataRangeLowExpression.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignExpression} object.
     */
    DRIDesignExpression getDataRangeLowExpression();

    /**
     * <p>getDataRangeHighExpression.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignExpression} object.
     */
    DRIDesignExpression getDataRangeHighExpression();

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
     * @return a {@link ch.unibas.medizin.dynamicreports.design.definition.style.DRIDesignFont} object.
     */
    DRIDesignFont getValueFont();

    /**
     * <p>getShape.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.MeterShape} object.
     */
    MeterShape getShape();

    /**
     * <p>getIntervals.</p>
     *
     * @return a {@link java.util.List} object.
     */
    List<DRIDesignMeterInterval> getIntervals();

    /**
     * <p>getMeterAngle.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    Integer getMeterAngle();

    /**
     * <p>getUnits.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    String getUnits();

    /**
     * <p>getTickInterval.</p>
     *
     * @return a {@link java.lang.Double} object.
     */
    Double getTickInterval();

    /**
     * <p>getMeterBackgroundColor.</p>
     *
     * @return a {@link java.awt.Color} object.
     */
    Color getMeterBackgroundColor();

    /**
     * <p>getNeedleColor.</p>
     *
     * @return a {@link java.awt.Color} object.
     */
    Color getNeedleColor();

    /**
     * <p>getTickColor.</p>
     *
     * @return a {@link java.awt.Color} object.
     */
    Color getTickColor();

    /**
     * <p>getTickLabelFont.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.definition.style.DRIDesignFont} object.
     */
    DRIDesignFont getTickLabelFont();
}
