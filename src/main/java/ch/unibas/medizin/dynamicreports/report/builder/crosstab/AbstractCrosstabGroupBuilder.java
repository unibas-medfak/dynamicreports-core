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
package ch.unibas.medizin.dynamicreports.report.builder.crosstab;

import java.io.Serial;
import java.util.Comparator;

import org.apache.commons.lang3.Validate;

import ch.unibas.medizin.dynamicreports.report.base.column.DRValueColumn;
import ch.unibas.medizin.dynamicreports.report.base.crosstab.DRCrosstabGroup;
import ch.unibas.medizin.dynamicreports.report.builder.AbstractBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.FieldBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.HyperLinkBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.column.ValueColumnBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.expression.Expressions;
import ch.unibas.medizin.dynamicreports.report.builder.style.ReportStyleBuilder;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import ch.unibas.medizin.dynamicreports.report.constant.CrosstabTotalPosition;
import ch.unibas.medizin.dynamicreports.report.constant.HorizontalTextAlignment;
import ch.unibas.medizin.dynamicreports.report.constant.OrderType;
import ch.unibas.medizin.dynamicreports.report.constant.TextAdjust;
import ch.unibas.medizin.dynamicreports.report.definition.DRIField;
import ch.unibas.medizin.dynamicreports.report.definition.DRIValue;
import ch.unibas.medizin.dynamicreports.report.definition.datatype.DRIDataType;
import ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression;
import ch.unibas.medizin.dynamicreports.report.definition.expression.DRIPropertyExpression;
import ch.unibas.medizin.dynamicreports.report.definition.expression.DRIValueFormatter;

/**
 * <p>Abstract AbstractCrosstabGroupBuilder class.</p>
 *
 * @author Ricardo Mariaca, Jan Moxter
 * 
 */
public abstract class AbstractCrosstabGroupBuilder<T extends AbstractCrosstabGroupBuilder<T, U, V>, U extends DRCrosstabGroup<V>, V> extends AbstractBuilder<T, U> implements DRIValue<V> {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for AbstractCrosstabGroupBuilder.</p>
     *
     * @param column        a {@link ch.unibas.medizin.dynamicreports.report.builder.column.ValueColumnBuilder} object.
     * @param crosstabGroup a U object.
     */
    protected AbstractCrosstabGroupBuilder(final ValueColumnBuilder<?, V> column, final U crosstabGroup) {
        super(crosstabGroup);
        Validate.notNull(column, "column must not be null");
        final DRValueColumn<V> col = column.getColumn();
        getObject().setExpression(col);
        getObject().setDataType(col.getComponent().getDataType());
        getObject().setHeaderPattern(col.getComponent().getPattern());
    }

    /**
     * <p>Constructor for AbstractCrosstabGroupBuilder.</p>
     *
     * @param field         a {@link ch.unibas.medizin.dynamicreports.report.builder.FieldBuilder} object.
     * @param crosstabGroup a U object.
     */
    protected AbstractCrosstabGroupBuilder(final FieldBuilder<V> field, final U crosstabGroup) {
        super(crosstabGroup);
        Validate.notNull(field, "field must not be null");
        getObject().setExpression(field.getField());
        getObject().setDataType(field.getField().getDataType());
    }

    /**
     * <p>Constructor for AbstractCrosstabGroupBuilder.</p>
     *
     * @param expression    a {@link ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression} object.
     * @param crosstabGroup a U object.
     */
    protected AbstractCrosstabGroupBuilder(final DRIExpression<V> expression, final U crosstabGroup) {
        super(crosstabGroup);
        getObject().setExpression(expression);
        if (expression instanceof DRIField) {
            getObject().setDataType(((DRIField<V>) expression).getDataType());
        }
    }

    /**
     * <p>setHeaderPattern.</p>
     *
     * @param pattern a {@link java.lang.String} object.
     * @return a T object.
     */
    public T setHeaderPattern(final String pattern) {
        getObject().setHeaderPattern(pattern);
        return (T) this;
    }

    /**
     * <p>setHeaderHorizontalTextAlignment.</p>
     *
     * @param horizontalTextAlignment a {@link ch.unibas.medizin.dynamicreports.report.constant.HorizontalTextAlignment} object.
     * @return a T object.
     */
    public T setHeaderHorizontalTextAlignment(final HorizontalTextAlignment horizontalTextAlignment) {
        getObject().setHeaderHorizontalTextAlignment(horizontalTextAlignment);
        return (T) this;
    }

    /**
     * <p>setHeaderValueFormatter.</p>
     *
     * @param valueFormatter a {@link ch.unibas.medizin.dynamicreports.report.definition.expression.DRIValueFormatter} object.
     * @return a T object.
     */
    public T setHeaderValueFormatter(final DRIValueFormatter<?, ? super V> valueFormatter) {
        getObject().setHeaderValueFormatter(valueFormatter);
        return (T) this;
    }

    /**
     * <p>setHeaderTextAdjust.</p>
     *
     * @param textAdjust a {@link ch.unibas.medizin.dynamicreports.report.constant.TextAdjust} object.
     * @return a T object.
     */
    public T setHeaderTextAdjust(final TextAdjust textAdjust) {
        getObject().setHeaderTextAdjust(textAdjust);
        return (T )this;
    }

    /**
     * Sets the crosstab group header hyperlink.
     *
     * @param hyperLink the group header hyperlink
     * @return a crosstab group builder
     */
    public T setHeaderHyperLink(final HyperLinkBuilder hyperLink) {
        if (hyperLink != null) {
            getObject().setHeaderHyperLink(hyperLink.getHyperLink());
        } else {
            getObject().setHeaderHyperLink(null);
        }
        return (T) this;
    }

    /**
     * <p>setHeaderStyle.</p>
     *
     * @param style a {@link ch.unibas.medizin.dynamicreports.report.builder.style.ReportStyleBuilder} object.
     * @return a T object.
     */
    public T setHeaderStyle(final ReportStyleBuilder style) {
        if (style != null) {
            getObject().setHeaderStyle(style.getStyle());
        } else {
            getObject().setHeaderStyle(null);
        }
        return (T) this;
    }

    /**
     * Adds a jasper property to the header group.
     *
     * @param propertyExpression the property expression
     * @return a crosstab group builder
     */
    public T addHeaderProperty(final DRIPropertyExpression propertyExpression) {
        getObject().addHeaderPropertyExpression(propertyExpression);
        return (T) this;
    }

    /**
     * Adds a jasper property to the header group.
     *
     * @param name            the property name
     * @param valueExpression the property value expression
     * @return a crosstab group builder
     */
    public T addHeaderProperty(final String name, final DRIExpression<String> valueExpression) {
        getObject().addHeaderPropertyExpression(Expressions.property(name, valueExpression));
        return (T) this;
    }

    /**
     * Adds a jasper property to the header group.
     *
     * @param name  the property name
     * @param value the property value
     * @return a crosstab group builder
     */
    public T addHeaderProperty(final String name, final String value) {
        getObject().addHeaderPropertyExpression(Expressions.property(name, value));
        return (T) this;
    }

    /**
     * <p>setShowTotal.</p>
     *
     * @param showTotal a {@link java.lang.Boolean} object.
     * @return a T object.
     */
    public T setShowTotal(final Boolean showTotal) {
        getObject().setShowTotal(showTotal);
        return (T) this;
    }

    /**
     * <p>setTotalPosition.</p>
     *
     * @param totalPosition a {@link ch.unibas.medizin.dynamicreports.report.constant.CrosstabTotalPosition} object.
     * @return a T object.
     */
    public T setTotalPosition(final CrosstabTotalPosition totalPosition) {
        getObject().setTotalPosition(totalPosition);
        return (T) this;
    }

    /**
     * <p>setTotalHeader.</p>
     *
     * @param totalHeaderExpression a {@link ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression} object.
     * @return a T object.
     */
    public T setTotalHeader(final DRIExpression<?> totalHeaderExpression) {
        getObject().setTotalHeaderExpression(totalHeaderExpression);
        return (T) this;
    }

    /**
     * <p>setTotalHeader.</p>
     *
     * @param totalHeader a {@link java.lang.String} object.
     * @return a T object.
     */
    public T setTotalHeader(final String totalHeader) {
        getObject().setTotalHeaderExpression(Expressions.text(totalHeader));
        return (T) this;
    }

    /**
     * <p>setTotalHeaderTextAdjust.</p>
     *
     * @param textAdjust a {@link ch.unibas.medizin.dynamicreports.report.constant.TextAdjust} object.
     * @return a T object.
     */
    public T setTotalHeaderTextAdjust(final TextAdjust textAdjust) {
        getObject().setTotalHeaderTextAdjust(textAdjust);
        return (T )this;
    }

    /**
     * <p>setTotalHeaderStyle.</p>
     *
     * @param style a {@link ch.unibas.medizin.dynamicreports.report.builder.style.ReportStyleBuilder} object.
     * @return a T object.
     */
    public T setTotalHeaderStyle(final ReportStyleBuilder style) {
        if (style != null) {
            getObject().setTotalHeaderStyle(style.getStyle());
        } else {
            getObject().setTotalHeaderStyle(null);
        }
        return (T) this;
    }

    /**
     * Adds a jasper property to the total header group.
     *
     * @param propertyExpression the property expression
     * @return a crosstab group builder
     */
    public T addTotalHeaderProperty(final DRIPropertyExpression propertyExpression) {
        getObject().addTotalHeaderPropertyExpression(propertyExpression);
        return (T) this;
    }

    /**
     * Adds a jasper property to the total header group.
     *
     * @param name            the property name
     * @param valueExpression the property value expression
     * @return a crosstab group builder
     */
    public T addTotalHeaderProperty(final String name, final DRIExpression<String> valueExpression) {
        getObject().addTotalHeaderPropertyExpression(Expressions.property(name, valueExpression));
        return (T) this;
    }

    /**
     * Adds a jasper property to the total header group.
     *
     * @param name  the property name
     * @param value the property value
     * @return a crosstab group builder
     */
    public T addTotalHeaderProperty(final String name, final String value) {
        getObject().addTotalHeaderPropertyExpression(Expressions.property(name, value));
        return (T) this;
    }

    /**
     * <p>setDataType.</p>
     *
     * @param dataType a {@link ch.unibas.medizin.dynamicreports.report.definition.datatype.DRIDataType} object.
     * @return a T object.
     */
    public T setDataType(final DRIDataType<? super V, V> dataType) {
        getObject().setDataType(dataType);
        return (T) this;
    }

    /**
     * <p>setOrderType.</p>
     *
     * @param orderType a {@link ch.unibas.medizin.dynamicreports.report.constant.OrderType} object.
     * @return a T object.
     */
    public T setOrderType(final OrderType orderType) {
        getObject().setOrderType(orderType);
        return (T) this;
    }

    /**
     * <p>setOrderByExpression.</p>
     *
     * @param orderByExpression a {@link ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression} object.
     * @return a T object.
     */
    public T setOrderByExpression(final DRIExpression<? extends Comparable<?>> orderByExpression) {
        return orderBy(orderByExpression);
    }

    /**
     * <p>orderBy.</p>
     *
     * @param measure a {@link ch.unibas.medizin.dynamicreports.report.builder.crosstab.CrosstabMeasureBuilder} object.
     * @return a T object.
     */
    public T orderBy(final CrosstabMeasureBuilder<? extends Comparable<?>> measure) {
        return orderBy(Expressions.orderBy(measure));
    }

    /**
     * <p>orderBy.</p>
     *
     * @param orderByExpression a {@link ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression} object.
     * @return a T object.
     */
    public T orderBy(final DRIExpression<? extends Comparable<?>> orderByExpression) {
        getObject().setOrderByExpression(orderByExpression);
        return (T) this;
    }

    /**
     * <p>setComparatorExpression.</p>
     *
     * @param comparatorExpression a {@link ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression} object.
     * @return a T object.
     */
    public T setComparatorExpression(final DRIExpression<? extends Comparator<?>> comparatorExpression) {
        getObject().setComparatorExpression(comparatorExpression);
        return (T) this;
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return getObject().getName();
    }
}
