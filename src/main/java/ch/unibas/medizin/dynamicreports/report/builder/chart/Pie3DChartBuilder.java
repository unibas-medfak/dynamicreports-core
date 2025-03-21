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

import ch.unibas.medizin.dynamicreports.report.base.chart.plot.DRPie3DPlot;
import ch.unibas.medizin.dynamicreports.report.constant.ChartType;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;

import java.io.Serial;

/**
 * <p>Pie3DChartBuilder class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class Pie3DChartBuilder extends AbstractPieChartBuilder<Pie3DChartBuilder, DRPie3DPlot> {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for Pie3DChartBuilder.</p>
     */
    protected Pie3DChartBuilder() {
        super(ChartType.PIE3D);
    }

    /**
     * <p>setDepthFactor.</p>
     *
     * @param depthFactor a {@link java.lang.Double} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.chart.Pie3DChartBuilder} object.
     */
    public Pie3DChartBuilder setDepthFactor(Double depthFactor) {
        getPlot().setDepthFactor(depthFactor);
        return this;
    }
}
