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
package net.sf.dynamicreports.report.builder.chart;

import net.sf.dynamicreports.report.base.chart.plot.DRBarPlot;
import net.sf.dynamicreports.report.constant.ChartType;
import net.sf.dynamicreports.report.constant.Constants;

/**
 * <p>XyBarChartBuilder class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class XyBarChartBuilder extends AbstractXyChartBuilder<XyBarChartBuilder, DRBarPlot> {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for XyBarChartBuilder.</p>
     */
    protected XyBarChartBuilder() {
        super(ChartType.XYBAR);
    }

    /**
     * <p>setShowLabels.</p>
     *
     * @param showLabels a {@link java.lang.Boolean} object.
     * @return a {@link net.sf.dynamicreports.report.builder.chart.XyBarChartBuilder} object.
     */
    public XyBarChartBuilder setShowLabels(Boolean showLabels) {
        getPlot().setShowLabels(showLabels);
        return this;
    }

    /**
     * <p>setShowValues.</p>
     *
     * @param showValues a {@link java.lang.Boolean} object.
     * @return a {@link net.sf.dynamicreports.report.builder.chart.XyBarChartBuilder} object.
     */
    public XyBarChartBuilder setShowValues(Boolean showValues) {
        getPlot().setShowValues(showValues);
        return this;
    }

    /**
     * <p>setValuePattern.</p>
     *
     * @param valuePattern a {@link java.lang.String} object.
     * @return a {@link net.sf.dynamicreports.report.builder.chart.XyBarChartBuilder} object.
     */
    public XyBarChartBuilder setValuePattern(String valuePattern) {
        getPlot().setValuePattern(valuePattern);
        return this;
    }

    /*public XyBarChartBuilder setPercentValuePattern(String percentValuePattern) {
        getPlot().setPercentValuePattern(percentValuePattern);
        return this;
    }*/

    /*public XyBarChartBuilder setShowTickLabels(boolean showTickLabels) {
        getPlot().setShowTickLabels(showTickLabels);
        return this;
    }

    public XyBarChartBuilder setShowTickMarks(boolean showTickMarks) {
        getPlot().setShowTickMarks(showTickMarks);
        return this;
    }*/
}
