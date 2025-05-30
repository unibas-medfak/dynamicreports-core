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

import ch.unibas.medizin.dynamicreports.report.base.chart.dataset.DRSeriesDataset;
import ch.unibas.medizin.dynamicreports.report.base.chart.plot.DRPiePlot;
import ch.unibas.medizin.dynamicreports.report.builder.DynamicReports;
import ch.unibas.medizin.dynamicreports.report.builder.FieldBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.HyperLinkBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.column.ValueColumnBuilder;
import ch.unibas.medizin.dynamicreports.report.constant.ChartType;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression;
import org.apache.commons.lang3.Validate;

import java.io.Serial;

/**
 * <p>Abstract AbstractPieChartBuilder class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public abstract class AbstractPieChartBuilder<T extends AbstractPieChartBuilder<T, U>, U extends DRPiePlot> extends AbstractBaseChartBuilder<T, U, DRSeriesDataset> {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for AbstractPieChartBuilder.</p>
     *
     * @param chartType a {@link ch.unibas.medizin.dynamicreports.report.constant.ChartType} object.
     */
    protected AbstractPieChartBuilder(ChartType chartType) {
        super(chartType);
    }

    // dataset

    /**
     * <p>setKey.</p>
     *
     * @param column a {@link ch.unibas.medizin.dynamicreports.report.builder.column.ValueColumnBuilder} object.
     * @return a T object.
     */
    public T setKey(ValueColumnBuilder<?, String> column) {
        Validate.notNull(column, "column must not be null");
        getDataset().setValueExpression(column.getColumn());
        return (T) this;
    }

    /**
     * <p>setKey.</p>
     *
     * @param fieldName  a {@link java.lang.String} object.
     * @param valueClass a {@link java.lang.Class} object.
     * @return a T object.
     */
    public T setKey(String fieldName, Class<String> valueClass) {
        return setKey(DynamicReports.field(fieldName, valueClass));
    }

    /**
     * <p>setKey.</p>
     *
     * @param field a {@link ch.unibas.medizin.dynamicreports.report.builder.FieldBuilder} object.
     * @return a T object.
     */
    public T setKey(FieldBuilder<String> field) {
        Validate.notNull(field, "field must not be null");
        getDataset().setValueExpression(field.build());
        return (T) this;
    }

    /**
     * <p>setKey.</p>
     *
     * @param expression a {@link ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression} object.
     * @return a T object.
     */
    public T setKey(DRIExpression<String> expression) {
        getDataset().setValueExpression(expression);
        return (T) this;
    }

    /**
     * <p>series.</p>
     *
     * @param chartSeries a {@link ch.unibas.medizin.dynamicreports.report.builder.chart.CategoryChartSerieBuilder} object.
     * @return a T object.
     */
    public T series(CategoryChartSerieBuilder... chartSeries) {
        return addSerie(chartSeries);
    }

    /**
     * <p>addSerie.</p>
     *
     * @param chartSeries a {@link ch.unibas.medizin.dynamicreports.report.builder.chart.CategoryChartSerieBuilder} object.
     * @return a T object.
     */
    public T addSerie(CategoryChartSerieBuilder... chartSeries) {
        Validate.notNull(chartSeries, "chartSeries must not be null");
        Validate.noNullElements(chartSeries, "chartSeries must not contains null chartSerie");
        for (CategoryChartSerieBuilder chartSerie : chartSeries) {
            getDataset().addSerie(chartSerie.build());
        }
        return (T) this;
    }

    /**
     * <p>setItemHyperLink.</p>
     *
     * @param itemHyperLink a {@link ch.unibas.medizin.dynamicreports.report.builder.HyperLinkBuilder} object.
     * @return a T object.
     */
    public T setItemHyperLink(HyperLinkBuilder itemHyperLink) {
        Validate.notNull(itemHyperLink, "itemHyperLink must not be null");
        getDataset().setItemHyperLink(itemHyperLink.build());
        return (T) this;
    }

    // plot

    /**
     * <p>setCircular.</p>
     *
     * @param circular a {@link java.lang.Boolean} object.
     * @return a T object.
     */
    public T setCircular(Boolean circular) {
        getPlot().setCircular(circular);
        return (T) this;
    }

    /**
     * <p>setShowLabels.</p>
     *
     * @param showLabels a {@link java.lang.Boolean} object.
     * @return a T object.
     */
    public T setShowLabels(Boolean showLabels) {
        getPlot().setShowLabels(showLabels);
        return (T) this;
    }

    /**
     * <p>setShowValues.</p>
     *
     * @param showValues a {@link java.lang.Boolean} object.
     * @return a T object.
     */
    public T setShowValues(Boolean showValues) {
        getPlot().setShowValues(showValues);
        return (T) this;
    }

    /**
     * <p>setValuePattern.</p>
     *
     * @param valuePattern a {@link java.lang.String} object.
     * @return a T object.
     */
    public T setValuePattern(String valuePattern) {
        getPlot().setValuePattern(valuePattern);
        return (T) this;
    }

    /**
     * <p>setShowPercentages.</p>
     *
     * @param showPercentages a {@link java.lang.Boolean} object.
     * @return a T object.
     */
    public T setShowPercentages(Boolean showPercentages) {
        getPlot().setShowPercentages(showPercentages);
        return (T) this;
    }

    /**
     * <p>setPercentValuePattern.</p>
     *
     * @param percentValuePattern a {@link java.lang.String} object.
     * @return a T object.
     */
    public T setPercentValuePattern(String percentValuePattern) {
        getPlot().setPercentValuePattern(percentValuePattern);
        return (T) this;
    }

    /**
     * <p>setLabelFormat.</p>
     *
     * @param labelFormat a {@link java.lang.String} object.
     * @return a T object.
     */
    public T setLabelFormat(String labelFormat) {
        getPlot().setLabelFormat(labelFormat);
        return (T) this;
    }

    /**
     * <p>setLegendLabelFormat.</p>
     *
     * @param legendLabelFormat a {@link java.lang.String} object.
     * @return a T object.
     */
    public T setLegendLabelFormat(String legendLabelFormat) {
        getPlot().setLegendLabelFormat(legendLabelFormat);
        return (T) this;
    }
}
