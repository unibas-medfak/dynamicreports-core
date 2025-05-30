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
package ch.unibas.medizin.dynamicreports.design.transformation;

import ch.unibas.medizin.dynamicreports.design.base.DRDesignDataset;
import ch.unibas.medizin.dynamicreports.design.base.DRDesignGroup;
import ch.unibas.medizin.dynamicreports.design.base.chart.DRDesignChart;
import ch.unibas.medizin.dynamicreports.design.base.chart.DRDesignChartLegend;
import ch.unibas.medizin.dynamicreports.design.base.chart.DRDesignChartSubtitle;
import ch.unibas.medizin.dynamicreports.design.base.chart.DRDesignChartTitle;
import ch.unibas.medizin.dynamicreports.design.base.chart.dataset.DRDesignCategoryChartSerie;
import ch.unibas.medizin.dynamicreports.design.base.chart.dataset.DRDesignCategoryDataset;
import ch.unibas.medizin.dynamicreports.design.base.chart.dataset.DRDesignChartDataset;
import ch.unibas.medizin.dynamicreports.design.base.chart.dataset.DRDesignChartSerie;
import ch.unibas.medizin.dynamicreports.design.base.chart.dataset.DRDesignGanttChartSerie;
import ch.unibas.medizin.dynamicreports.design.base.chart.dataset.DRDesignHighLowDataset;
import ch.unibas.medizin.dynamicreports.design.base.chart.dataset.DRDesignSeriesDataset;
import ch.unibas.medizin.dynamicreports.design.base.chart.dataset.DRDesignTimeSeriesDataset;
import ch.unibas.medizin.dynamicreports.design.base.chart.dataset.DRDesignValueDataset;
import ch.unibas.medizin.dynamicreports.design.base.chart.dataset.DRDesignXyChartSerie;
import ch.unibas.medizin.dynamicreports.design.base.chart.dataset.DRDesignXyzChartSerie;
import ch.unibas.medizin.dynamicreports.design.base.chart.plot.AbstractDesignBasePlot;
import ch.unibas.medizin.dynamicreports.design.base.chart.plot.DRDesignAxisFormat;
import ch.unibas.medizin.dynamicreports.design.base.chart.plot.DRDesignAxisPlot;
import ch.unibas.medizin.dynamicreports.design.base.chart.plot.DRDesignBarPlot;
import ch.unibas.medizin.dynamicreports.design.base.chart.plot.DRDesignBubblePlot;
import ch.unibas.medizin.dynamicreports.design.base.chart.plot.DRDesignCandlestickPlot;
import ch.unibas.medizin.dynamicreports.design.base.chart.plot.DRDesignChartAxis;
import ch.unibas.medizin.dynamicreports.design.base.chart.plot.DRDesignHighLowPlot;
import ch.unibas.medizin.dynamicreports.design.base.chart.plot.DRDesignLinePlot;
import ch.unibas.medizin.dynamicreports.design.base.chart.plot.DRDesignMeterInterval;
import ch.unibas.medizin.dynamicreports.design.base.chart.plot.DRDesignMeterPlot;
import ch.unibas.medizin.dynamicreports.design.base.chart.plot.DRDesignMultiAxisPlot;
import ch.unibas.medizin.dynamicreports.design.base.chart.plot.DRDesignPiePlot;
import ch.unibas.medizin.dynamicreports.design.base.chart.plot.DRDesignSpiderPlot;
import ch.unibas.medizin.dynamicreports.design.base.chart.plot.DRDesignThermometerPlot;
import ch.unibas.medizin.dynamicreports.design.constant.DefaultStyleType;
import ch.unibas.medizin.dynamicreports.design.constant.ResetType;
import ch.unibas.medizin.dynamicreports.design.definition.DRIDesignHyperLink;
import ch.unibas.medizin.dynamicreports.design.definition.DRIDesignVariable;
import ch.unibas.medizin.dynamicreports.design.definition.chart.plot.DRIDesignPlot;
import ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignExpression;
import ch.unibas.medizin.dynamicreports.design.exception.DRDesignReportException;
import ch.unibas.medizin.dynamicreports.design.transformation.chartcustomizer.DifferenceRendererCustomizer;
import ch.unibas.medizin.dynamicreports.design.transformation.chartcustomizer.GroupedStackedBarRendererCustomizer;
import ch.unibas.medizin.dynamicreports.design.transformation.chartcustomizer.LayeredBarRendererCustomizer;
import ch.unibas.medizin.dynamicreports.design.transformation.chartcustomizer.PieChartLabelFormatCustomizer;
import ch.unibas.medizin.dynamicreports.design.transformation.chartcustomizer.SeriesColorsByNameCustomizer;
import ch.unibas.medizin.dynamicreports.design.transformation.chartcustomizer.SeriesOrderCustomizer;
import ch.unibas.medizin.dynamicreports.design.transformation.chartcustomizer.ShowPercentagesCustomizer;
import ch.unibas.medizin.dynamicreports.design.transformation.chartcustomizer.ShowValuesCustomizer;
import ch.unibas.medizin.dynamicreports.design.transformation.chartcustomizer.WaterfallBarRendererCustomizer;
import ch.unibas.medizin.dynamicreports.design.transformation.chartcustomizer.XyBlockRendererCustomizer;
import ch.unibas.medizin.dynamicreports.design.transformation.chartcustomizer.XyStepRendererCustomizer;
import ch.unibas.medizin.dynamicreports.design.transformation.expressions.GroupedSeriesExpression;
import ch.unibas.medizin.dynamicreports.design.transformation.expressions.SerieValueExpression;
import ch.unibas.medizin.dynamicreports.report.ReportUtils;
import ch.unibas.medizin.dynamicreports.report.builder.expression.Expressions;
import ch.unibas.medizin.dynamicreports.report.constant.OrderType;
import ch.unibas.medizin.dynamicreports.report.definition.DRIDataset;
import ch.unibas.medizin.dynamicreports.report.definition.chart.DRIChart;
import ch.unibas.medizin.dynamicreports.report.definition.chart.DRIChartCustomizer;
import ch.unibas.medizin.dynamicreports.report.definition.chart.DRIChartLegend;
import ch.unibas.medizin.dynamicreports.report.definition.chart.DRIChartSubtitle;
import ch.unibas.medizin.dynamicreports.report.definition.chart.DRIChartTitle;
import ch.unibas.medizin.dynamicreports.report.definition.chart.dataset.DRICategoryChartSerie;
import ch.unibas.medizin.dynamicreports.report.definition.chart.dataset.DRICategoryDataset;
import ch.unibas.medizin.dynamicreports.report.definition.chart.dataset.DRIChartDataset;
import ch.unibas.medizin.dynamicreports.report.definition.chart.dataset.DRIChartSerie;
import ch.unibas.medizin.dynamicreports.report.definition.chart.dataset.DRIGanttChartSerie;
import ch.unibas.medizin.dynamicreports.report.definition.chart.dataset.DRIGroupedCategoryChartSerie;
import ch.unibas.medizin.dynamicreports.report.definition.chart.dataset.DRIHighLowDataset;
import ch.unibas.medizin.dynamicreports.report.definition.chart.dataset.DRISeriesDataset;
import ch.unibas.medizin.dynamicreports.report.definition.chart.dataset.DRITimeSeriesDataset;
import ch.unibas.medizin.dynamicreports.report.definition.chart.dataset.DRIValueDataset;
import ch.unibas.medizin.dynamicreports.report.definition.chart.dataset.DRIXyChartSerie;
import ch.unibas.medizin.dynamicreports.report.definition.chart.dataset.DRIXyzChartSerie;
import ch.unibas.medizin.dynamicreports.report.definition.chart.plot.DRIAxisFormat;
import ch.unibas.medizin.dynamicreports.report.definition.chart.plot.DRIAxisPlot;
import ch.unibas.medizin.dynamicreports.report.definition.chart.plot.DRIBarPlot;
import ch.unibas.medizin.dynamicreports.report.definition.chart.plot.DRIBasePlot;
import ch.unibas.medizin.dynamicreports.report.definition.chart.plot.DRIBubblePlot;
import ch.unibas.medizin.dynamicreports.report.definition.chart.plot.DRICandlestickPlot;
import ch.unibas.medizin.dynamicreports.report.definition.chart.plot.DRIChartAxis;
import ch.unibas.medizin.dynamicreports.report.definition.chart.plot.DRIDifferencePlot;
import ch.unibas.medizin.dynamicreports.report.definition.chart.plot.DRIGroupedStackedBarPlot;
import ch.unibas.medizin.dynamicreports.report.definition.chart.plot.DRIHighLowPlot;
import ch.unibas.medizin.dynamicreports.report.definition.chart.plot.DRILayeredBarPlot;
import ch.unibas.medizin.dynamicreports.report.definition.chart.plot.DRILinePlot;
import ch.unibas.medizin.dynamicreports.report.definition.chart.plot.DRIMeterInterval;
import ch.unibas.medizin.dynamicreports.report.definition.chart.plot.DRIMeterPlot;
import ch.unibas.medizin.dynamicreports.report.definition.chart.plot.DRIMultiAxisPlot;
import ch.unibas.medizin.dynamicreports.report.definition.chart.plot.DRIPiePlot;
import ch.unibas.medizin.dynamicreports.report.definition.chart.plot.DRIPlot;
import ch.unibas.medizin.dynamicreports.report.definition.chart.plot.DRISpiderPlot;
import ch.unibas.medizin.dynamicreports.report.definition.chart.plot.DRIThermometerPlot;
import ch.unibas.medizin.dynamicreports.report.definition.chart.plot.DRIWaterfallBarPlot;
import ch.unibas.medizin.dynamicreports.report.definition.chart.plot.DRIXyBlockPlot;
import ch.unibas.medizin.dynamicreports.report.definition.chart.plot.DRIXyStepPlot;
import ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression;
import ch.unibas.medizin.dynamicreports.report.exception.DRException;

import java.awt.Color;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * <p>ChartTransform class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class ChartTransform {
    private final DesignTransformAccessor accessor;

    /**
     * <p>Constructor for ChartTransform.</p>
     *
     * @param accessor a {@link ch.unibas.medizin.dynamicreports.design.transformation.DesignTransformAccessor} object.
     */
    public ChartTransform(DesignTransformAccessor accessor) {
        this.accessor = accessor;
    }

    // chart

    /**
     * <p>transform.</p>
     *
     * @param chart      a {@link ch.unibas.medizin.dynamicreports.report.definition.chart.DRIChart} object.
     * @param resetType  a {@link ch.unibas.medizin.dynamicreports.design.constant.ResetType} object.
     * @param resetGroup a {@link ch.unibas.medizin.dynamicreports.design.base.DRDesignGroup} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.design.base.chart.DRDesignChart} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    protected DRDesignChart transform(DRIChart chart, ResetType resetType, DRDesignGroup resetGroup) throws DRException {
        return transform(chart, null, resetType, resetGroup);
    }

    private DRDesignChart transform(DRIChart chart, DRIDataset subDataset, ResetType resetType, DRDesignGroup resetGroup) throws DRException {
        DRDesignChart designChart = new DRDesignChart();
        designChart.setWidth(accessor.getTemplateTransform().getChartWidth(chart));
        designChart.setHeight(accessor.getTemplateTransform().getChartHeight(chart));
        designChart.setChartType(chart.getChartType());
        designChart.setTheme(accessor.getTemplateTransform().getChartTheme(chart));
        if (!(chart.getPlot() instanceof DRIMultiAxisPlot)) {
            designChart.setDataset(dataset(chart.getDataset(), subDataset, resetType, resetGroup));
        }
        designChart.setPlot(plot(chart.getPlot(), designChart.getCustomizers(), chart.getDataset().getSubDataset(), resetType, resetGroup));
        designChart.setTitle(title(chart.getTitle()));
        designChart.setSubtitle(subtitle(chart.getSubtitle()));
        designChart.setLegend(legend(chart.getLegend()));

        if (!chart.getCustomizers().isEmpty()) {
            designChart.getCustomizers().addAll(chart.getCustomizers());
        }

        return designChart;
    }

    // plot
    private DRIDesignPlot plot(DRIPlot plot, List<DRIChartCustomizer> chartCustomizers, DRIDataset subDataset, ResetType resetType, DRDesignGroup resetGroup) throws DRException {
        DRIDesignPlot designPlot = switch (plot) {
            case DRILayeredBarPlot driLayeredBarPlot -> layeredBarPlot(driLayeredBarPlot, chartCustomizers);
            case DRIWaterfallBarPlot driWaterfallBarPlot -> waterfallBarPlot(driWaterfallBarPlot, chartCustomizers);
            case DRIGroupedStackedBarPlot driGroupedStackedBarPlot -> groupedStackedBarPlot(driGroupedStackedBarPlot, chartCustomizers);
            case DRIBarPlot driBarPlot -> barPlot(driBarPlot, chartCustomizers);
            case DRIDifferencePlot driDifferencePlot -> differencePlot(driDifferencePlot, chartCustomizers);
            case DRIXyStepPlot driXyStepPlot -> xyStepPlot(driXyStepPlot, chartCustomizers);
            case DRILinePlot driLinePlot -> linePlot(driLinePlot, chartCustomizers);
            case DRIMultiAxisPlot driMultiAxisPlot -> multiAxisPlot(driMultiAxisPlot, chartCustomizers, subDataset, resetType, resetGroup);
            case DRIPiePlot driPiePlot -> piePlot(driPiePlot, chartCustomizers);
            case DRISpiderPlot driSpiderPlot -> spiderPlot(driSpiderPlot);
            case DRIXyBlockPlot driXyBlockPlot -> xyBlockPlot(driXyBlockPlot, chartCustomizers);
            case DRIBubblePlot driBubblePlot -> bubblePlot(driBubblePlot, chartCustomizers);
            case DRICandlestickPlot driCandlestickPlot -> candlestickPlot(driCandlestickPlot, chartCustomizers);
            case DRIHighLowPlot driHighLowPlot -> highLowPlot(driHighLowPlot, chartCustomizers);
            case DRIMeterPlot driMeterPlot -> meterPlot(driMeterPlot);
            case DRIThermometerPlot driThermometerPlot -> thermometerPlot(driThermometerPlot);
            case DRIAxisPlot driAxisPlot -> axisPlot(driAxisPlot, chartCustomizers);
            default -> throw new DRDesignReportException("Chart plot " + plot.getClass().getName() + " not supported");
        };

        if (plot instanceof DRIBasePlot basePlot) {
            AbstractDesignBasePlot designBasePlot = ((AbstractDesignBasePlot) designPlot);
            designBasePlot.setOrientation(basePlot.getOrientation());
            designBasePlot.setSeriesColors(accessor.getTemplateTransform().getChartSeriesColors(basePlot));
            Map<String, Color> seriesColorsByName = basePlot.getSeriesColorsByName();
            if (!seriesColorsByName.isEmpty()) {
                chartCustomizers.add(new SeriesColorsByNameCustomizer(seriesColorsByName));
            }
        }

        return designPlot;
    }

    private DRDesignBarPlot barPlot(DRIBarPlot barPlot, List<DRIChartCustomizer> chartCustomizers) throws DRException {
        DRDesignBarPlot designBarPlot = new DRDesignBarPlot();
        axisPlot(designBarPlot, barPlot, chartCustomizers);
        designBarPlot.setShowTickMarks(barPlot.getShowTickMarks());
        designBarPlot.setShowTickLabels(barPlot.getShowTickLabels());
        designBarPlot.setShowLabels(barPlot.getShowLabels());
        return designBarPlot;
    }

    private DRDesignBarPlot groupedStackedBarPlot(DRIGroupedStackedBarPlot groupedStackedBarPlot, List<DRIChartCustomizer> chartCustomizers) throws DRException {
        GroupedStackedBarRendererCustomizer renderer = new GroupedStackedBarRendererCustomizer();
        chartCustomizers.add(renderer);
        return barPlot(groupedStackedBarPlot, chartCustomizers);
    }

    private DRDesignBarPlot layeredBarPlot(DRILayeredBarPlot layeredBarPlot, List<DRIChartCustomizer> chartCustomizers) throws DRException {
        chartCustomizers.add(new LayeredBarRendererCustomizer(layeredBarPlot.getSeriesBarWidths()));
        return barPlot(layeredBarPlot, chartCustomizers);
    }

    private DRDesignBarPlot waterfallBarPlot(DRIWaterfallBarPlot waterfallBarPlot, List<DRIChartCustomizer> chartCustomizers) throws DRException {
        WaterfallBarRendererCustomizer waterfallBarCustomizer = new WaterfallBarRendererCustomizer(waterfallBarPlot);
        chartCustomizers.add(waterfallBarCustomizer);
        return barPlot(waterfallBarPlot, chartCustomizers);
    }

    private DRDesignLinePlot linePlot(DRILinePlot linePlot, List<DRIChartCustomizer> chartCustomizers) throws DRException {
        DRDesignLinePlot designLinePlot = new DRDesignLinePlot();
        axisPlot(designLinePlot, linePlot, chartCustomizers);
        designLinePlot.setShowShapes(linePlot.getShowShapes());
        designLinePlot.setShowLines(linePlot.getShowLines());
        return designLinePlot;
    }

    private DRDesignLinePlot differencePlot(DRIDifferencePlot differencePlot, List<DRIChartCustomizer> chartCustomizers) throws DRException {
        DRDesignLinePlot designLinePlot = new DRDesignLinePlot();
        chartCustomizers.add(new DifferenceRendererCustomizer(differencePlot));
        axisPlot(designLinePlot, differencePlot, chartCustomizers);
        return designLinePlot;
    }

    private DRDesignLinePlot xyStepPlot(DRIXyStepPlot xyStepPlot, List<DRIChartCustomizer> chartCustomizers) throws DRException {
        DRDesignLinePlot designLinePlot = new DRDesignLinePlot();
        chartCustomizers.add(new XyStepRendererCustomizer(xyStepPlot.getStepPoint()));
        axisPlot(designLinePlot, xyStepPlot, chartCustomizers);
        return designLinePlot;
    }

    private DRDesignMultiAxisPlot multiAxisPlot(DRIMultiAxisPlot multiAxisPlot, List<DRIChartCustomizer> chartCustomizers, DRIDataset subDataset, ResetType resetType, DRDesignGroup resetGroup)
        throws DRException {
        DRDesignMultiAxisPlot designMultiAxisPlot = new DRDesignMultiAxisPlot();
        axisPlot(designMultiAxisPlot, multiAxisPlot, chartCustomizers);
        for (DRIChartAxis axis : multiAxisPlot.getAxes()) {
            DRDesignChartAxis designAxis = new DRDesignChartAxis();
            designAxis.setPosition(axis.getPosition());
            DRDesignChart chart = transform(axis.getChart(), subDataset, resetType, resetGroup);
            chart.setStyle(accessor.getStyleTransform().transformStyle(axis.getChart().getStyle(), false, DefaultStyleType.CHART));
            chart.setUniqueName(ReportUtils.generateUniqueName("chart"));
            designAxis.setChart(chart);
            designMultiAxisPlot.getAxes().add(designAxis);
        }
        return designMultiAxisPlot;
    }

    private DRDesignPiePlot piePlot(DRIPiePlot piePlot, List<DRIChartCustomizer> chartCustomizers) {
        DRDesignPiePlot designPiePlot = new DRDesignPiePlot();
        piePlot(designPiePlot, piePlot, chartCustomizers);
        return designPiePlot;
    }

    private void piePlot(DRDesignPiePlot designPiePlot, DRIPiePlot piePlot, List<DRIChartCustomizer> chartCustomizers) {
        designPiePlot.setCircular(piePlot.getCircular());
        designPiePlot.setLabelFormat(piePlot.getLabelFormat());
        designPiePlot.setLegendLabelFormat(piePlot.getLegendLabelFormat());
        if (piePlot.getShowLabels() != null && !piePlot.getShowLabels()) {
            chartCustomizers.add(new PieChartLabelFormatCustomizer(null, null, null));
        } else {
            String labelFormat = piePlot.getLabelFormat();
            if (labelFormat == null) {
                labelFormat = "{0}";
            }
            if (piePlot.getShowValues() != null && piePlot.getShowValues()) {
                labelFormat += " = {1}";
            }
            if (piePlot.getShowPercentages() != null && piePlot.getShowPercentages()) {
                labelFormat += " ({2})";
            }
            String valuePattern = accessor.getTemplateTransform().getChartValuePattern(piePlot);
            String percentValuePattern = accessor.getTemplateTransform().getChartPercentValuePattern(piePlot);
            chartCustomizers.add(new PieChartLabelFormatCustomizer(labelFormat, valuePattern, percentValuePattern));
        }
    }

    private DRDesignSpiderPlot spiderPlot(DRISpiderPlot spiderPlot) throws DRException {
        DRDesignSpiderPlot designSpiderPlot = new DRDesignSpiderPlot();
        designSpiderPlot.setMaxValueExpression(accessor.getExpressionTransform().transformExpression(spiderPlot.getMaxValueExpression()));
        designSpiderPlot.setRotation(spiderPlot.getRotation());
        designSpiderPlot.setTableOrder(spiderPlot.getTableOrder());
        designSpiderPlot.setWebFilled(spiderPlot.getWebFilled());
        designSpiderPlot.setStartAngle(spiderPlot.getStartAngle());
        designSpiderPlot.setHeadPercent(spiderPlot.getHeadPercent());
        designSpiderPlot.setInteriorGap(spiderPlot.getInteriorGap());
        designSpiderPlot.setAxisLineColor(spiderPlot.getAxisLineColor());
        designSpiderPlot.setAxisLineWidth(spiderPlot.getAxisLineWidth());
        designSpiderPlot.setLabelFont(accessor.getStyleTransform().transformFont(spiderPlot.getLabelFont()));
        designSpiderPlot.setLabelGap(spiderPlot.getLabelGap());
        designSpiderPlot.setLabelColor(spiderPlot.getLabelColor());
        return designSpiderPlot;
    }

    private DRDesignAxisPlot xyBlockPlot(DRIXyBlockPlot xyBlockPlot, List<DRIChartCustomizer> chartCustomizers) throws DRException {
        DRDesignBubblePlot designBubblePlot = new DRDesignBubblePlot();
        chartCustomizers.add(new XyBlockRendererCustomizer(xyBlockPlot));
        axisPlot(designBubblePlot, xyBlockPlot, chartCustomizers);
        return designBubblePlot;
    }

    private DRDesignBubblePlot bubblePlot(DRIBubblePlot bubblePlot, List<DRIChartCustomizer> chartCustomizers) throws DRException {
        DRDesignBubblePlot designBubblePlot = new DRDesignBubblePlot();
        axisPlot(designBubblePlot, bubblePlot, chartCustomizers);
        designBubblePlot.setScaleType(bubblePlot.getScaleType());
        return designBubblePlot;
    }

    private DRDesignCandlestickPlot candlestickPlot(DRICandlestickPlot candlestickPlot, List<DRIChartCustomizer> chartCustomizers) throws DRException {
        DRDesignCandlestickPlot designCandlestickPlot = new DRDesignCandlestickPlot();
        axisPlot(designCandlestickPlot, candlestickPlot, chartCustomizers);
        designCandlestickPlot.setShowVolume(candlestickPlot.getShowVolume());
        return designCandlestickPlot;
    }

    private DRDesignHighLowPlot highLowPlot(DRIHighLowPlot highLowPlot, List<DRIChartCustomizer> chartCustomizers) throws DRException {
        DRDesignHighLowPlot designHighLowPlot = new DRDesignHighLowPlot();
        axisPlot(designHighLowPlot, highLowPlot, chartCustomizers);
        designHighLowPlot.setShowOpenTicks(highLowPlot.getShowOpenTicks());
        designHighLowPlot.setShowCloseTicks(highLowPlot.getShowCloseTicks());
        return designHighLowPlot;
    }

    private DRDesignMeterPlot meterPlot(DRIMeterPlot meterPlot) throws DRException {
        DRDesignMeterPlot designMeterPlot = new DRDesignMeterPlot();
        designMeterPlot.setDataRangeLowExpression(accessor.getExpressionTransform().transformExpression(meterPlot.getDataRangeLowExpression()));
        designMeterPlot.setDataRangeHighExpression(accessor.getExpressionTransform().transformExpression(meterPlot.getDataRangeHighExpression()));
        designMeterPlot.setValueColor(meterPlot.getValueColor());
        designMeterPlot.setValueMask(meterPlot.getValueMask());
        designMeterPlot.setValueFont(accessor.getStyleTransform().transformFont(meterPlot.getValueFont()));
        designMeterPlot.setShape(meterPlot.getShape());
        for (DRIMeterInterval interval : meterPlot.getIntervals()) {
            designMeterPlot.getIntervals().add(meterInterval(interval));
        }
        designMeterPlot.setMeterAngle(meterPlot.getMeterAngle());
        designMeterPlot.setUnits(meterPlot.getUnits());
        designMeterPlot.setTickInterval(meterPlot.getTickInterval());
        designMeterPlot.setMeterBackgroundColor(meterPlot.getMeterBackgroundColor());
        designMeterPlot.setNeedleColor(meterPlot.getNeedleColor());
        designMeterPlot.setTickColor(meterPlot.getTickColor());
        designMeterPlot.setTickLabelFont(accessor.getStyleTransform().transformFont(meterPlot.getTickLabelFont()));
        return designMeterPlot;
    }

    private DRDesignThermometerPlot thermometerPlot(DRIThermometerPlot thermometerPlot) throws DRException {
        DRDesignThermometerPlot designThermometerPlot = new DRDesignThermometerPlot();
        designThermometerPlot.setDataRangeLowExpression(accessor.getExpressionTransform().transformExpression(thermometerPlot.getDataRangeLowExpression()));
        designThermometerPlot.setDataRangeHighExpression(accessor.getExpressionTransform().transformExpression(thermometerPlot.getDataRangeHighExpression()));
        designThermometerPlot.setValueColor(thermometerPlot.getValueColor());
        designThermometerPlot.setValueMask(thermometerPlot.getValueMask());
        designThermometerPlot.setValueFont(accessor.getStyleTransform().transformFont(thermometerPlot.getValueFont()));
        designThermometerPlot.setValueLocation(accessor.getTemplateTransform().getChartThermometerPlotValueLocation(thermometerPlot));
        designThermometerPlot.setMercuryColor(thermometerPlot.getMercuryColor());
        designThermometerPlot.setLowDataRangeLowExpression(accessor.getExpressionTransform().transformExpression(thermometerPlot.getLowDataRangeLowExpression()));
        designThermometerPlot.setLowDataRangeHighExpression(accessor.getExpressionTransform().transformExpression(thermometerPlot.getLowDataRangeHighExpression()));
        designThermometerPlot.setMediumDataRangeLowExpression(accessor.getExpressionTransform().transformExpression(thermometerPlot.getMediumDataRangeLowExpression()));
        designThermometerPlot.setMediumDataRangeHighExpression(accessor.getExpressionTransform().transformExpression(thermometerPlot.getMediumDataRangeHighExpression()));
        designThermometerPlot.setHighDataRangeLowExpression(accessor.getExpressionTransform().transformExpression(thermometerPlot.getHighDataRangeLowExpression()));
        designThermometerPlot.setHighDataRangeHighExpression(accessor.getExpressionTransform().transformExpression(thermometerPlot.getHighDataRangeHighExpression()));
        return designThermometerPlot;
    }

    private DRDesignMeterInterval meterInterval(DRIMeterInterval interval) throws DRException {
        DRDesignMeterInterval designInterval = new DRDesignMeterInterval();
        designInterval.setLabel(interval.getLabel());
        designInterval.setBackgroundColor(interval.getBackgroundColor());
        designInterval.setAlpha(interval.getAlpha());
        designInterval.setDataRangeLowExpression(accessor.getExpressionTransform().transformExpression(interval.getDataRangeLowExpression()));
        designInterval.setDataRangeHighExpression(accessor.getExpressionTransform().transformExpression(interval.getDataRangeHighExpression()));
        return designInterval;
    }

    private DRDesignAxisPlot axisPlot(DRIAxisPlot axisPlot, List<DRIChartCustomizer> chartCustomizers) throws DRException {
        DRDesignAxisPlot designAxisPlot = new DRDesignAxisPlot();
        axisPlot(designAxisPlot, axisPlot, chartCustomizers);
        return designAxisPlot;
    }

    private void axisPlot(DRDesignAxisPlot designAxisPlot, DRIAxisPlot axisPlot, List<DRIChartCustomizer> chartCustomizers) throws DRException {
        designAxisPlot.setXAxisFormat(axisFormat(axisPlot.getXAxisFormat()));
        designAxisPlot.setYAxisFormat(axisFormat(axisPlot.getYAxisFormat()));
        Comparator<String> seriesOrderBy = axisPlot.getSeriesOrderBy();
        OrderType seriesOrderType = axisPlot.getSeriesOrderType();
        if (seriesOrderBy != null || seriesOrderType != null) {
            chartCustomizers.add(new SeriesOrderCustomizer(seriesOrderBy, seriesOrderType));
        }
        if (axisPlot.getShowPercentages() != null && axisPlot.getShowPercentages()) {
            chartCustomizers.add(new ShowPercentagesCustomizer());
        }
        if (axisPlot.getShowValues() != null && axisPlot.getShowValues()) {
            String valuePattern;
            if (axisPlot.getShowPercentages() != null && axisPlot.getShowPercentages()) {
                valuePattern = accessor.getTemplateTransform().getChartPercentValuePattern(axisPlot);
            } else {
                valuePattern = accessor.getTemplateTransform().getChartValuePattern(axisPlot);
            }
            boolean customRangeMaxValue = false;
            if (axisPlot.getYAxisFormat() != null) {
                customRangeMaxValue = axisPlot.getYAxisFormat().getRangeMaxValueExpression() != null;
            }
            chartCustomizers.add(new ShowValuesCustomizer(valuePattern, customRangeMaxValue));
        }
    }

    // axis format
    private DRDesignAxisFormat axisFormat(DRIAxisFormat axisFormat) throws DRException {
        DRDesignAxisFormat designAxisFormat = new DRDesignAxisFormat();
        designAxisFormat.setLabelExpression(accessor.getExpressionTransform().transformExpression(axisFormat.getLabelExpression()));
        designAxisFormat.setLabelFont(accessor.getStyleTransform().transformFont(axisFormat.getLabelFont()));
        designAxisFormat.setLabelColor(axisFormat.getLabelColor());
        designAxisFormat.setTickLabelFont(accessor.getStyleTransform().transformFont(axisFormat.getTickLabelFont()));
        designAxisFormat.setTickLabelColor(axisFormat.getTickLabelColor());
        designAxisFormat.setTickLabelMask(axisFormat.getTickLabelMask());
        designAxisFormat.setVerticalTickLabels(axisFormat.getVerticalTickLabels());
        designAxisFormat.setTickLabelRotation(axisFormat.getTickLabelRotation());
        designAxisFormat.setLineColor(axisFormat.getLineColor());
        designAxisFormat.setRangeMinValueExpression(accessor.getExpressionTransform().transformExpression(axisFormat.getRangeMinValueExpression()));
        designAxisFormat.setRangeMaxValueExpression(accessor.getExpressionTransform().transformExpression(axisFormat.getRangeMaxValueExpression()));
        return designAxisFormat;
    }

    // title
    private DRDesignChartTitle title(DRIChartTitle title) throws DRException {
        DRDesignChartTitle designTitle = new DRDesignChartTitle();
        subtitle(designTitle, title);
        designTitle.setPosition(title.getPosition());
        return designTitle;
    }

    // subtitle
    private DRDesignChartSubtitle subtitle(DRIChartSubtitle subtitle) throws DRException {
        DRDesignChartSubtitle designSubtitle = new DRDesignChartSubtitle();
        subtitle(designSubtitle, subtitle);
        return designSubtitle;
    }

    private void subtitle(DRDesignChartSubtitle designSubtitle, DRIChartSubtitle subtitle) throws DRException {
        designSubtitle.setColor(subtitle.getColor());
        designSubtitle.setFont(accessor.getStyleTransform().transformFont(subtitle.getFont()));
        designSubtitle.setTitle(accessor.getExpressionTransform().transformExpression(subtitle.getTitle()));
    }

    // legend
    private DRDesignChartLegend legend(DRIChartLegend legend) {
        DRDesignChartLegend designLegend = new DRDesignChartLegend();
        designLegend.setColor(legend.getColor());
        designLegend.setBackgroundColor(legend.getBackgroundColor());
        designLegend.setShowLegend(legend.getShowLegend());
        designLegend.setFont(accessor.getStyleTransform().transformFont(legend.getFont()));
        designLegend.setPosition(legend.getPosition());
        return designLegend;
    }

    // dataset
    private DRDesignChartDataset dataset(DRIChartDataset dataset, DRIDataset subDataset, ResetType resetType, DRDesignGroup resetGroup) throws DRException {
        DRDesignDataset designSubDataset;
        if (dataset.getSubDataset() != null) {
            designSubDataset = accessor.getDatasetTransform().transform(dataset.getSubDataset());
            accessor.transformToDataset(dataset.getSubDataset());
        } else {
            designSubDataset = accessor.getDatasetTransform().transform(subDataset);
            accessor.transformToDataset(subDataset);
        }

        DRDesignChartDataset designDataset = switch (dataset) {
            case DRICategoryDataset driCategoryDataset -> categoryDataset(driCategoryDataset, resetType, resetGroup);
            case DRITimeSeriesDataset driTimeSeriesDataset -> timeSeriesDataset(driTimeSeriesDataset, resetType, resetGroup);
            case DRISeriesDataset driSeriesDataset -> seriesDataset(driSeriesDataset, resetType, resetGroup);
            case DRIHighLowDataset driHighLowDataset -> highLowDataset(driHighLowDataset);
            case DRIValueDataset driValueDataset -> valueDataset(driValueDataset);
            default -> throw new DRDesignReportException("Dataset " + dataset.getClass().getName() + " not supported");
        };

        designDataset.setSubDataset(designSubDataset);
        if (resetType != null && resetType.equals(ResetType.NONE)) {
            designDataset.setResetType(ResetType.REPORT);
        } else {
            designDataset.setResetType(resetType);
        }
        designDataset.setResetGroup(resetGroup);
        accessor.transformToMainDataset();

        return designDataset;
    }

    private void seriesDataset(DRISeriesDataset dataset, DRDesignSeriesDataset designDataset, ResetType resetType, DRDesignGroup resetGroup) throws DRException {
        DRIDesignExpression valueExpression = accessor.getExpressionTransform().transformExpression(dataset.getValueExpression());
        DRIDesignHyperLink datasetItemHyperLink = accessor.getReportTransform().hyperlink(dataset.getItemHyperLink());
        designDataset.setValueExpression(valueExpression);
        int index = 0;
        for (DRIChartSerie serie : dataset.getSeries()) {
            DRDesignChartSerie designSerie = switch (serie) {
                case DRIGroupedCategoryChartSerie driGroupedCategoryChartSerie -> groupedCategorySerie(dataset.getSubDataset(), driGroupedCategoryChartSerie, valueExpression, resetType, resetGroup, index++);
                case DRICategoryChartSerie driCategoryChartSerie -> categorySerie(dataset.getSubDataset(), driCategoryChartSerie, valueExpression, resetType, resetGroup, index++);
                case DRIXyChartSerie driXyChartSerie -> xySerie(dataset.getSubDataset(), driXyChartSerie, valueExpression, resetType, resetGroup, index++);
                case DRIXyzChartSerie driXyzChartSerie -> xyzSerie(dataset.getSubDataset(), driXyzChartSerie, valueExpression, resetType, resetGroup, index++);
                case DRIGanttChartSerie driGanttChartSerie -> ganttSerie(dataset.getSubDataset(), driGanttChartSerie, valueExpression, resetType, resetGroup, index++);
                default -> throw new DRDesignReportException("Chart serie " + serie.getClass().getName() + " not supported");
            };
            DRIDesignHyperLink itemHyperLink = accessor.getReportTransform().hyperlink(serie.getItemHyperLink());
            if (itemHyperLink == null) {
                itemHyperLink = datasetItemHyperLink;
            }
            designSerie.setItemHyperLink(itemHyperLink);

            designDataset.addSerie(designSerie);
        }
    }

    private DRDesignSeriesDataset seriesDataset(DRISeriesDataset dataset, ResetType resetType, DRDesignGroup resetGroup) throws DRException {
        DRDesignSeriesDataset designDataset = new DRDesignSeriesDataset();
        seriesDataset(dataset, designDataset, resetType, resetGroup);
        return designDataset;
    }

    private DRDesignCategoryDataset categoryDataset(DRICategoryDataset dataset, ResetType resetType, DRDesignGroup resetGroup) throws DRException {
        DRDesignCategoryDataset designDataset = new DRDesignCategoryDataset();
        seriesDataset(dataset, designDataset, resetType, resetGroup);
        designDataset.setUseSeriesAsCategory(accessor.getTemplateTransform().isChartCategoryDatasetUseSeriesAsCategory(dataset));
        return designDataset;
    }

    private DRDesignTimeSeriesDataset timeSeriesDataset(DRITimeSeriesDataset dataset, ResetType resetType, DRDesignGroup resetGroup) throws DRException {
        DRDesignTimeSeriesDataset designDataset = new DRDesignTimeSeriesDataset();
        seriesDataset(dataset, designDataset, resetType, resetGroup);
        designDataset.setTimePeriodType(accessor.getTemplateTransform().getChartTimeSeriesDatasetTimePeriodType(dataset));
        return designDataset;
    }

    private DRDesignHighLowDataset highLowDataset(DRIHighLowDataset dataset) throws DRException {
        DRDesignHighLowDataset designDataset = new DRDesignHighLowDataset();
        AbstractExpressionTransform expressionTransform = accessor.getExpressionTransform();
        designDataset.setItemHyperLink(accessor.getReportTransform().hyperlink(dataset.getItemHyperLink()));
        designDataset.setSeriesExpression(expressionTransform.transformExpression(dataset.getSeriesExpression()));
        designDataset.setDateExpression(expressionTransform.transformExpression(dataset.getDateExpression()));
        designDataset.setHighExpression(expressionTransform.transformExpression(dataset.getHighExpression()));
        designDataset.setLowExpression(expressionTransform.transformExpression(dataset.getLowExpression()));
        designDataset.setOpenExpression(expressionTransform.transformExpression(dataset.getOpenExpression()));
        designDataset.setCloseExpression(expressionTransform.transformExpression(dataset.getCloseExpression()));
        designDataset.setVolumeExpression(expressionTransform.transformExpression(dataset.getVolumeExpression()));
        return designDataset;
    }

    private DRDesignValueDataset valueDataset(DRIValueDataset dataset) throws DRException {
        DRDesignValueDataset designDataset = new DRDesignValueDataset();
        AbstractExpressionTransform expressionTransform = accessor.getExpressionTransform();
        designDataset.setValueExpression(expressionTransform.transformExpression(dataset.getValueExpression()));
        return designDataset;
    }

    // design serie
    private DRDesignCategoryChartSerie categorySerie(DRIDataset dataset, DRICategoryChartSerie serie, DRIDesignExpression valueExpression, ResetType resetType, DRDesignGroup resetGroup, int index)
        throws DRException {
        DRDesignCategoryChartSerie designSerie = new DRDesignCategoryChartSerie();

        AbstractExpressionTransform expressionTransform = accessor.getExpressionTransform();
        DRIDesignExpression seriesExpression = expressionTransform.transformExpression(serie.getSeriesExpression());
        designSerie.setSeriesExpression(seriesExpression);
        DRIDesignExpression serieValueExpression = expressionTransform.transformExpression(serie.getValueExpression());
        if (serieValueExpression instanceof DRIDesignVariable) {
            designSerie.setValueExpression(serieValueExpression);
        } else {
            if (seriesExpression == null) {
                designSerie.setValueExpression(expressionTransform.transformExpression(new SerieValueExpression(valueExpression, serieValueExpression, resetType, resetGroup, null)));
            } else {
                designSerie.setValueExpression(
                    expressionTransform.transformExpression(new SerieValueExpression(valueExpression, serieValueExpression, resetType, resetGroup, seriesExpression.getName())));
            }
        }
        DRIExpression<?> labelExpression = serie.getLabelExpression();
        if (labelExpression == null) {
            labelExpression = Expressions.text("serie" + index);
        }
        designSerie.setLabelExpression(expressionTransform.transformExpression(labelExpression));

        return designSerie;
    }

    private DRDesignCategoryChartSerie groupedCategorySerie(DRIDataset dataset, DRIGroupedCategoryChartSerie serie, DRIDesignExpression valueExpression, ResetType resetType, DRDesignGroup resetGroup,
                                                            int index) throws DRException {
        DRDesignCategoryChartSerie designSerie = new DRDesignCategoryChartSerie();

        AbstractExpressionTransform expressionTransform = accessor.getExpressionTransform();
        GroupedSeriesExpression groupedSeriesExpression = new GroupedSeriesExpression(serie.getGroupExpression(), serie.getSeriesExpression(), serie.getLabelExpression(), index);
        DRIDesignExpression seriesExpression = expressionTransform.transformExpression(groupedSeriesExpression);
        designSerie.setSeriesExpression(seriesExpression);
        DRIDesignExpression serieValueExpression = expressionTransform.transformExpression(serie.getValueExpression());
        if (serieValueExpression instanceof DRIDesignVariable) {
            designSerie.setValueExpression(serieValueExpression);
        } else {
            designSerie.setValueExpression(expressionTransform.transformExpression(new SerieValueExpression(valueExpression, serieValueExpression, resetType, resetGroup, seriesExpression.getName())));
        }
        DRIExpression<?> labelExpression = serie.getLabelExpression();
        if (labelExpression == null) {
            labelExpression = Expressions.text("serie" + index);
        }
        designSerie.setLabelExpression(expressionTransform.transformExpression(labelExpression));

        return designSerie;
    }

    // xy serie
    private DRDesignXyChartSerie xySerie(DRIDataset dataset, DRIXyChartSerie serie, DRIDesignExpression valueExpression, ResetType resetType, DRDesignGroup resetGroup, int index) throws DRException {
        DRDesignXyChartSerie designSerie = new DRDesignXyChartSerie();

        AbstractExpressionTransform expressionTransform = accessor.getExpressionTransform();
        designSerie.setSeriesExpression(expressionTransform.transformExpression(serie.getSeriesExpression()));
        designSerie.setXValueExpression(expressionTransform.transformExpression(serie.getXValueExpression()));
        designSerie.setYValueExpression(expressionTransform.transformExpression(serie.getYValueExpression()));
        DRIExpression<?> labelExpression = serie.getLabelExpression();
        if (labelExpression == null) {
            labelExpression = Expressions.text("serie" + index);
        }
        designSerie.setLabelExpression(expressionTransform.transformExpression(labelExpression));

        return designSerie;
    }

    // xyz serie
    private DRDesignXyzChartSerie xyzSerie(DRIDataset dataset, DRIXyzChartSerie serie, DRIDesignExpression valueExpression, ResetType resetType, DRDesignGroup resetGroup, int index)
        throws DRException {
        DRDesignXyzChartSerie designSerie = new DRDesignXyzChartSerie();
        AbstractExpressionTransform expressionTransform = accessor.getExpressionTransform();
        DRIExpression<?> seriesExpression = serie.getSeriesExpression();
        if (seriesExpression == null) {
            seriesExpression = Expressions.text("serie" + index);
        }
        designSerie.setSeriesExpression(expressionTransform.transformExpression(seriesExpression));
        designSerie.setXValueExpression(expressionTransform.transformExpression(serie.getXValueExpression()));
        designSerie.setYValueExpression(expressionTransform.transformExpression(serie.getYValueExpression()));
        designSerie.setZValueExpression(expressionTransform.transformExpression(serie.getZValueExpression()));
        return designSerie;
    }

    // gantt serie
    private DRDesignGanttChartSerie ganttSerie(DRIDataset dataset, DRIGanttChartSerie serie, DRIDesignExpression valueExpression, ResetType resetType, DRDesignGroup resetGroup, int index)
        throws DRException {
        DRDesignGanttChartSerie designSerie = new DRDesignGanttChartSerie();
        AbstractExpressionTransform expressionTransform = accessor.getExpressionTransform();
        designSerie.setSeriesExpression(expressionTransform.transformExpression(serie.getSeriesExpression()));
        designSerie.setStartDateExpression(expressionTransform.transformExpression(serie.getStartDateExpression()));
        designSerie.setEndDateExpression(expressionTransform.transformExpression(serie.getEndDateExpression()));
        designSerie.setPercentExpression(expressionTransform.transformExpression(serie.getPercentExpression()));
        DRIExpression<?> labelExpression = serie.getLabelExpression();
        if (labelExpression == null) {
            labelExpression = Expressions.text("serie" + index);
        }
        designSerie.setLabelExpression(expressionTransform.transformExpression(labelExpression));
        return designSerie;
    }

}
