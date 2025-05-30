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

import ch.unibas.medizin.dynamicreports.report.base.chart.dataset.DRChartDataset;
import ch.unibas.medizin.dynamicreports.report.base.chart.plot.DRMultiAxisPlot;
import ch.unibas.medizin.dynamicreports.report.constant.AxisPosition;
import ch.unibas.medizin.dynamicreports.report.constant.ChartType;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import org.apache.commons.lang3.Validate;

import java.io.Serial;

/**
 * <p>MultiAxisChartBuilder class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class MultiAxisChartBuilder extends AbstractBaseChartBuilder<MultiAxisChartBuilder, DRMultiAxisPlot, DRChartDataset> {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for MultiAxisChartBuilder.</p>
     */
    protected MultiAxisChartBuilder() {
        super(ChartType.MULTI_AXIS);
    }

    /**
     * <p>charts.</p>
     *
     * @param charts a {@link ch.unibas.medizin.dynamicreports.report.builder.chart.AbstractBaseChartBuilder} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.chart.MultiAxisChartBuilder} object.
     */
    public MultiAxisChartBuilder charts(AbstractBaseChartBuilder<?, ?, ?>... charts) {
        return addChart(charts);
    }

    /**
     * <p>addChart.</p>
     *
     * @param charts a {@link ch.unibas.medizin.dynamicreports.report.builder.chart.AbstractBaseChartBuilder} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.chart.MultiAxisChartBuilder} object.
     */
    public MultiAxisChartBuilder addChart(AbstractBaseChartBuilder<?, ?, ?>... charts) {
        Validate.notNull(charts, "charts must not be null");
        Validate.noNullElements(charts, "charts must not contains null chart");
        for (AbstractBaseChartBuilder<?, ?, ?> chart : charts) {
            getPlot().addChart(chart.build());
        }
        return this;
    }

    /**
     * <p>addChart.</p>
     *
     * @param chart    a {@link ch.unibas.medizin.dynamicreports.report.builder.chart.AbstractBaseChartBuilder} object.
     * @param position a {@link ch.unibas.medizin.dynamicreports.report.constant.AxisPosition} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.chart.MultiAxisChartBuilder} object.
     */
    public MultiAxisChartBuilder addChart(AbstractBaseChartBuilder<?, ?, ?> chart, AxisPosition position) {
        Validate.notNull(chart, "chart must not be null");
        getPlot().addChart(chart.build(), position);
        return this;
    }

    // plot

    /**
     * <p>setXAxisFormat.</p>
     *
     * @param xAxisFormat a {@link ch.unibas.medizin.dynamicreports.report.builder.chart.AxisFormatBuilder} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.chart.MultiAxisChartBuilder} object.
     */
    public MultiAxisChartBuilder setXAxisFormat(AxisFormatBuilder xAxisFormat) {
        Validate.notNull(xAxisFormat, "xAxisFormat must not be null");
        getPlot().setXAxisFormat(xAxisFormat.build());
        return this;
    }

    /**
     * <p>setYAxisFormat.</p>
     *
     * @param yAxisFormat a {@link ch.unibas.medizin.dynamicreports.report.builder.chart.AxisFormatBuilder} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.chart.MultiAxisChartBuilder} object.
     */
    public MultiAxisChartBuilder setYAxisFormat(AxisFormatBuilder yAxisFormat) {
        Validate.notNull(yAxisFormat, "yAxisFormat must not be null");
        getPlot().setYAxisFormat(yAxisFormat.build());
        return this;
    }
}
