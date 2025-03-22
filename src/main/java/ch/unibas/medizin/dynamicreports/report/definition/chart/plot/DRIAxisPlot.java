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

import ch.unibas.medizin.dynamicreports.report.constant.OrderType;

import java.util.Comparator;

/**
 * <p>DRIAxisPlot interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface DRIAxisPlot extends DRIBasePlot {

    /**
     * <p>getXAxisFormat.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.chart.plot.DRIAxisFormat} object.
     */
    DRIAxisFormat getXAxisFormat();

    /**
     * <p>getYAxisFormat.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.chart.plot.DRIAxisFormat} object.
     */
    DRIAxisFormat getYAxisFormat();

    /**
     * <p>getShowValues.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean getShowValues();

    /**
     * <p>getValuePattern.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    String getValuePattern();

    /**
     * <p>getShowPercentages.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean getShowPercentages();

    /**
     * <p>getPercentValuePattern.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    String getPercentValuePattern();

    /**
     * <p>getSeriesOrderBy.</p>
     *
     * @return a {@link java.util.Comparator} object.
     */
    Comparator<String> getSeriesOrderBy();

    /**
     * <p>getSeriesOrderType.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.OrderType} object.
     */
    OrderType getSeriesOrderType();

}
