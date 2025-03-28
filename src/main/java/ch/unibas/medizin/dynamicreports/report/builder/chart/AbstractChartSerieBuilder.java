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

import ch.unibas.medizin.dynamicreports.report.base.chart.dataset.DRChartSerie;
import ch.unibas.medizin.dynamicreports.report.builder.AbstractBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.DynamicReports;
import ch.unibas.medizin.dynamicreports.report.builder.FieldBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.HyperLinkBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.column.ValueColumnBuilder;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression;
import org.apache.commons.lang3.Validate;

import java.io.Serial;

/**
 * <p>Abstract AbstractChartSerieBuilder class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public abstract class AbstractChartSerieBuilder<T extends AbstractChartSerieBuilder<T, U>, U extends DRChartSerie> extends AbstractBuilder<T, U> {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for AbstractChartSerieBuilder.</p>
     *
     * @param object a U object.
     */
    protected AbstractChartSerieBuilder(U object) {
        super(object);
    }

    /**
     * <p>setSeries.</p>
     *
     * @param column a {@link ch.unibas.medizin.dynamicreports.report.builder.column.ValueColumnBuilder} object.
     * @return a T object.
     */
    public T setSeries(ValueColumnBuilder<?, ?> column) {
        Validate.notNull(column, "column must not be null");
        getObject().setSeriesExpression(column.getColumn());
        return (T) this;
    }

    /**
     * <p>setSeries.</p>
     *
     * @param fieldName  a {@link java.lang.String} object.
     * @param valueClass a {@link java.lang.Class} object.
     * @return a T object.
     */
    public T setSeries(String fieldName, Class<?> valueClass) {
        return setSeries(DynamicReports.field(fieldName, valueClass));
    }

    /**
     * <p>setSeries.</p>
     *
     * @param field a {@link ch.unibas.medizin.dynamicreports.report.builder.FieldBuilder} object.
     * @return a T object.
     */
    public T setSeries(FieldBuilder<?> field) {
        Validate.notNull(field, "field must not be null");
        getObject().setSeriesExpression(field.build());
        return (T) this;
    }

    /**
     * <p>setSeries.</p>
     *
     * @param expression a {@link ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression} object.
     * @return a T object.
     */
    public T setSeries(DRIExpression<?> expression) {
        getObject().setSeriesExpression(expression);
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
        getObject().setItemHyperLink(itemHyperLink.build());
        return (T) this;
    }
}
