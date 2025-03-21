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
package net.sf.dynamicreports.report.base.chart.plot;

import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.chart.plot.DRIDifferencePlot;

import java.awt.Color;

/**
 * <p>DRDifferencePlot class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DRDifferencePlot extends DRAxisPlot implements DRIDifferencePlot {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private Color positiveColor;
    private Color negativeColor;
    private Boolean showShapes;

    /** {@inheritDoc} */
    @Override
    public Color getPositiveColor() {
        return positiveColor;
    }

    /**
     * <p>Setter for the field <code>positiveColor</code>.</p>
     *
     * @param positiveColor a {@link java.awt.Color} object.
     */
    public void setPositiveColor(Color positiveColor) {
        this.positiveColor = positiveColor;
    }

    /** {@inheritDoc} */
    @Override
    public Color getNegativeColor() {
        return negativeColor;
    }

    /**
     * <p>Setter for the field <code>negativeColor</code>.</p>
     *
     * @param negativeColor a {@link java.awt.Color} object.
     */
    public void setNegativeColor(Color negativeColor) {
        this.negativeColor = negativeColor;
    }

    /** {@inheritDoc} */
    @Override
    public Boolean getShowShapes() {
        return showShapes;
    }

    /**
     * <p>Setter for the field <code>showShapes</code>.</p>
     *
     * @param showShapes a {@link java.lang.Boolean} object.
     */
    public void setShowShapes(Boolean showShapes) {
        this.showShapes = showShapes;
    }

}
