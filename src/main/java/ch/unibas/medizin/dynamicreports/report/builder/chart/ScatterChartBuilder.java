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

import ch.unibas.medizin.dynamicreports.report.base.chart.plot.DRLinePlot;
import ch.unibas.medizin.dynamicreports.report.constant.ChartType;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;

import java.io.Serial;

/**
 * <p>ScatterChartBuilder class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class ScatterChartBuilder extends AbstractXyChartBuilder<ScatterChartBuilder, DRLinePlot> {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for ScatterChartBuilder.</p>
     */
    protected ScatterChartBuilder() {
        super(ChartType.SCATTER);
    }

    /**
     * <p>setShowShapes.</p>
     *
     * @param showShapes a {@link java.lang.Boolean} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.chart.ScatterChartBuilder} object.
     */
    public ScatterChartBuilder setShowShapes(Boolean showShapes) {
        getPlot().setShowShapes(showShapes);
        return this;
    }

    /**
     * <p>setShowLines.</p>
     *
     * @param showLines a {@link java.lang.Boolean} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.chart.ScatterChartBuilder} object.
     */
    public ScatterChartBuilder setShowLines(Boolean showLines) {
        getPlot().setShowLines(showLines);
        return this;
    }

    /**
     * <p>setShowValues.</p>
     *
     * @param showValues a {@link java.lang.Boolean} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.chart.ScatterChartBuilder} object.
     */
    public ScatterChartBuilder setShowValues(Boolean showValues) {
        getPlot().setShowValues(showValues);
        return this;
    }

    /**
     * <p>setValuePattern.</p>
     *
     * @param valuePattern a {@link java.lang.String} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.chart.ScatterChartBuilder} object.
     */
    public ScatterChartBuilder setValuePattern(String valuePattern) {
        getPlot().setValuePattern(valuePattern);
        return this;
    }

}
