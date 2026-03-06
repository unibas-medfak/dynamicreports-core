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
package ch.unibas.medizin.dynamicreports.report.base.chart;

import ch.unibas.medizin.dynamicreports.report.base.chart.dataset.DRCategoryDataset;
import ch.unibas.medizin.dynamicreports.report.base.chart.dataset.DRChartDataset;
import ch.unibas.medizin.dynamicreports.report.base.chart.dataset.DRHighLowDataset;
import ch.unibas.medizin.dynamicreports.report.base.chart.dataset.DRSeriesDataset;
import ch.unibas.medizin.dynamicreports.report.base.chart.dataset.DRTimeSeriesDataset;
import ch.unibas.medizin.dynamicreports.report.base.chart.dataset.DRValueDataset;
import ch.unibas.medizin.dynamicreports.report.base.chart.plot.DRAxisPlot;
import ch.unibas.medizin.dynamicreports.report.base.chart.plot.DRBarPlot;
import ch.unibas.medizin.dynamicreports.report.base.chart.plot.DRBubblePlot;
import ch.unibas.medizin.dynamicreports.report.base.chart.plot.DRCandlestickPlot;
import ch.unibas.medizin.dynamicreports.report.base.chart.plot.DRDifferencePlot;
import ch.unibas.medizin.dynamicreports.report.base.chart.plot.DRGroupedStackedBarPlot;
import ch.unibas.medizin.dynamicreports.report.base.chart.plot.DRHighLowPlot;
import ch.unibas.medizin.dynamicreports.report.base.chart.plot.DRLayeredBarPlot;
import ch.unibas.medizin.dynamicreports.report.base.chart.plot.DRLinePlot;
import ch.unibas.medizin.dynamicreports.report.base.chart.plot.DRMeterPlot;
import ch.unibas.medizin.dynamicreports.report.base.chart.plot.DRMultiAxisPlot;
import ch.unibas.medizin.dynamicreports.report.base.chart.plot.DRPiePlot;
import ch.unibas.medizin.dynamicreports.report.base.chart.plot.DRSpiderPlot;
import ch.unibas.medizin.dynamicreports.report.base.chart.plot.DRThermometerPlot;
import ch.unibas.medizin.dynamicreports.report.base.chart.plot.DRWaterfallBarPlot;
import ch.unibas.medizin.dynamicreports.report.base.chart.plot.DRXyBlockPlot;
import ch.unibas.medizin.dynamicreports.report.base.chart.plot.DRXyStepPlot;
import ch.unibas.medizin.dynamicreports.report.base.component.DRHyperLinkComponent;
import ch.unibas.medizin.dynamicreports.report.constant.ChartType;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import ch.unibas.medizin.dynamicreports.report.definition.chart.DRIChart;
import ch.unibas.medizin.dynamicreports.report.definition.chart.DRIChartCustomizer;
import ch.unibas.medizin.dynamicreports.report.definition.chart.plot.DRIPlot;
import ch.unibas.medizin.dynamicreports.report.exception.DRReportException;
import org.apache.commons.lang3.Validate;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>DRChart class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DRChart extends DRHyperLinkComponent implements DRIChart {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private ChartType chartType;
    private DRChartDataset dataset;
    private DRIPlot plot;
    private List<DRIChartCustomizer> customizers;
    private DRChartTitle title;
    private DRChartSubtitle subtitle;
    private DRChartLegend legend;
    private String theme;

    /**
     * <p>Constructor for DRChart.</p>
     *
     * @param chartType a {@link ch.unibas.medizin.dynamicreports.report.constant.ChartType} object.
     */
    public DRChart(ChartType chartType) {
        setChartType(chartType);
    }

    /** {@inheritDoc} */
    @Override
    protected void init() {
        super.init();
        this.customizers = new ArrayList<>();
        this.title = new DRChartTitle();
        this.subtitle = new DRChartSubtitle();
        this.legend = new DRChartLegend();
    }

    /** {@inheritDoc} */
    @Override
    public ChartType getChartType() {
        return chartType;
    }

    private void setChartType(ChartType chartType) {
        Validate.notNull(chartType, "chartType must not be null");
        this.chartType = chartType;

        switch (chartType) {
            case AREA, STACKEDAREA -> {
                dataset = new DRCategoryDataset();
                plot = new DRAxisPlot();
            }
            case BAR, STACKEDBAR -> {
                dataset = new DRCategoryDataset();
                plot = new DRBarPlot();
            }
            case GROUPEDSTACKEDBAR -> {
                dataset = new DRCategoryDataset();
                plot = new DRGroupedStackedBarPlot();
            }
            case LAYEREDBAR -> {
                dataset = new DRCategoryDataset();
                plot = new DRLayeredBarPlot();
            }
            case WATERFALLBAR -> {
                dataset = new DRCategoryDataset();
                plot = new DRWaterfallBarPlot();
            }
            case LINE -> {
                dataset = new DRCategoryDataset();
                plot = new DRLinePlot();
            }
            case PIE -> {
                dataset = new DRSeriesDataset();
                plot = new DRPiePlot();
            }
            case TIMESERIES -> {
                dataset = new DRTimeSeriesDataset();
                plot = new DRLinePlot();
            }
            case DIFFERENCE -> {
                dataset = new DRTimeSeriesDataset();
                plot = new DRDifferencePlot();
            }
            case XYAREA -> {
                dataset = new DRSeriesDataset();
                plot = new DRAxisPlot();
            }
            case XYBAR, GANTT -> {
                dataset = new DRSeriesDataset();
                plot = new DRBarPlot();
            }
            case XYLINE, SCATTER -> {
                dataset = new DRSeriesDataset();
                plot = new DRLinePlot();
            }
            case XYSTEP -> {
                dataset = new DRSeriesDataset();
                plot = new DRXyStepPlot();
            }
            case SPIDER -> {
                dataset = new DRCategoryDataset();
                plot = new DRSpiderPlot();
            }
            case MULTI_AXIS -> {
                dataset = new DRChartDataset();
                plot = new DRMultiAxisPlot();
            }
            case XYBLOCK -> {
                dataset = new DRSeriesDataset();
                plot = new DRXyBlockPlot();
            }
            case BUBBLE -> {
                dataset = new DRSeriesDataset();
                plot = new DRBubblePlot();
            }
            case CANDLESTICK -> {
                dataset = new DRHighLowDataset();
                plot = new DRCandlestickPlot();
            }
            case HIGHLOW -> {
                dataset = new DRHighLowDataset();
                plot = new DRHighLowPlot();
            }
            case METER -> {
                dataset = new DRValueDataset();
                plot = new DRMeterPlot();
            }
            case THERMOMETER -> {
                dataset = new DRValueDataset();
                plot = new DRThermometerPlot();
            }
            default -> throw new DRReportException("Chart type not supported.");
        }
    }

    /** {@inheritDoc} */
    @Override
    public DRChartDataset getDataset() {
        return dataset;
    }

    /** {@inheritDoc} */
    @Override
    public DRIPlot getPlot() {
        return plot;
    }

    /** {@inheritDoc} */
    @Override
    public List<DRIChartCustomizer> getCustomizers() {
        return customizers;
    }

    /**
     * <p>Setter for the field <code>customizers</code>.</p>
     *
     * @param customizers a {@link java.util.List} object.
     */
    public void setCustomizers(List<DRIChartCustomizer> customizers) {
        this.customizers = customizers;
    }

    /**
     * <p>addCustomizer.</p>
     *
     * @param customizer a {@link ch.unibas.medizin.dynamicreports.report.definition.chart.DRIChartCustomizer} object.
     */
    public void addCustomizer(DRIChartCustomizer customizer) {
        this.customizers.add(customizer);
    }

    /** {@inheritDoc} */
    @Override
    public DRChartTitle getTitle() {
        return title;
    }

    /**
     * <p>Setter for the field <code>title</code>.</p>
     *
     * @param title a {@link ch.unibas.medizin.dynamicreports.report.base.chart.DRChartTitle} object.
     */
    public void setTitle(DRChartTitle title) {
        Validate.notNull(title, "title must not be null");
        this.title = title;
    }

    /** {@inheritDoc} */
    @Override
    public DRChartSubtitle getSubtitle() {
        return subtitle;
    }

    /**
     * <p>Setter for the field <code>subtitle</code>.</p>
     *
     * @param subtitle a {@link ch.unibas.medizin.dynamicreports.report.base.chart.DRChartSubtitle} object.
     */
    public void setSubtitle(DRChartSubtitle subtitle) {
        Validate.notNull(subtitle, "subtitle must not be null");
        this.subtitle = subtitle;
    }

    /** {@inheritDoc} */
    @Override
    public DRChartLegend getLegend() {
        return legend;
    }

    /**
     * <p>Setter for the field <code>legend</code>.</p>
     *
     * @param legend a {@link ch.unibas.medizin.dynamicreports.report.base.chart.DRChartLegend} object.
     */
    public void setLegend(DRChartLegend legend) {
        Validate.notNull(legend, "legend must not be null");
        this.legend = legend;
    }

    /** {@inheritDoc} */
    @Override
    public String getTheme() {
        return theme;
    }

    /**
     * <p>Setter for the field <code>theme</code>.</p>
     *
     * @param theme a {@link java.lang.String} object.
     */
    public void setTheme(String theme) {
        this.theme = theme;
    }

}
