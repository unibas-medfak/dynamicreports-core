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
package ch.unibas.medizin.dynamicreports.report.builder.chart;

import ch.unibas.medizin.dynamicreports.report.base.chart.plot.DRDifferencePlot;
import ch.unibas.medizin.dynamicreports.report.constant.ChartType;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;

import java.awt.Color;
import java.io.Serial;

/**
 * <p>DifferenceChartBuilder class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DifferenceChartBuilder extends AbstractTimeSeriesChartBuilder<DifferenceChartBuilder, DRDifferencePlot> {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for DifferenceChartBuilder.</p>
     */
    protected DifferenceChartBuilder() {
        super(ChartType.DIFFERENCE);
    }

    /**
     * <p>setPositiveColor.</p>
     *
     * @param positiveColor a {@link java.awt.Color} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.chart.DifferenceChartBuilder} object.
     */
    public DifferenceChartBuilder setPositiveColor(Color positiveColor) {
        getPlot().setPositiveColor(positiveColor);
        return this;
    }

    /**
     * <p>setNegativeColor.</p>
     *
     * @param negativeColor a {@link java.awt.Color} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.chart.DifferenceChartBuilder} object.
     */
    public DifferenceChartBuilder setNegativeColor(Color negativeColor) {
        getPlot().setNegativeColor(negativeColor);
        return this;
    }

    /**
     * <p>setShowShapes.</p>
     *
     * @param showShapes a {@link java.lang.Boolean} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.chart.DifferenceChartBuilder} object.
     */
    public DifferenceChartBuilder setShowShapes(Boolean showShapes) {
        getPlot().setShowShapes(showShapes);
        return this;
    }

}
