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

import org.apache.commons.lang3.Validate;

import ch.unibas.medizin.dynamicreports.report.base.crosstab.DRCrosstabCellStyle;
import ch.unibas.medizin.dynamicreports.report.base.crosstab.DRCrosstabMeasure;
import ch.unibas.medizin.dynamicreports.report.base.crosstab.DRCrosstabVariable;
import ch.unibas.medizin.dynamicreports.report.builder.AbstractBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.FieldBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.HyperLinkBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.column.ValueColumnBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.datatype.DataTypes;
import ch.unibas.medizin.dynamicreports.report.builder.expression.Expressions;
import ch.unibas.medizin.dynamicreports.report.builder.style.ReportStyleBuilder;
import ch.unibas.medizin.dynamicreports.report.constant.Calculation;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import ch.unibas.medizin.dynamicreports.report.constant.CrosstabPercentageType;
import ch.unibas.medizin.dynamicreports.report.constant.HorizontalTextAlignment;
import ch.unibas.medizin.dynamicreports.report.constant.TextAdjust;
import ch.unibas.medizin.dynamicreports.report.definition.DRICrosstabValue;
import ch.unibas.medizin.dynamicreports.report.definition.DRIField;
import ch.unibas.medizin.dynamicreports.report.definition.component.DRITextField;
import ch.unibas.medizin.dynamicreports.report.definition.datatype.DRIDataType;
import ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression;
import ch.unibas.medizin.dynamicreports.report.definition.expression.DRIPropertyExpression;
import ch.unibas.medizin.dynamicreports.report.definition.expression.DRIValueFormatter;
import ch.unibas.medizin.dynamicreports.report.exception.DRReportException;

import java.io.Serial;

/**
 * <p>CrosstabMeasureBuilder class.</p>
 *
 * @author Ricardo Mariaca, Jan Moxter
 * 
 */
public class CrosstabMeasureBuilder<T> extends AbstractBuilder<CrosstabMeasureBuilder<T>, DRCrosstabMeasure<T>> implements DRICrosstabValue<T> {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for CrosstabMeasureBuilder.</p>
     *
     * @param column      a {@link ch.unibas.medizin.dynamicreports.report.builder.column.ValueColumnBuilder} object.
     * @param calculation a {@link ch.unibas.medizin.dynamicreports.report.constant.Calculation} object.
     */
    protected CrosstabMeasureBuilder(final ValueColumnBuilder<?, ?> column, final Calculation calculation) {
        super(new DRCrosstabMeasure<>(Crosstabs.variable(column, calculation).build()));
        if (calculation.equals(Calculation.COUNT) || calculation.equals(Calculation.DISTINCT_COUNT)) {
            setDataType(DataTypes.longType());
        } else if (calculation.equals(Calculation.AVERAGE) || calculation.equals(Calculation.STANDARD_DEVIATION) || calculation.equals(Calculation.VARIANCE)) {
            setDataType(DataTypes.doubleType());
        } else {
            final DRITextField<?> columnComponent = column.getColumn().getComponent();
            setDataType(columnComponent.getDataType());
            setPattern(columnComponent.getPattern());
        }
    }

    /**
     * <p>Constructor for CrosstabMeasureBuilder.</p>
     *
     * @param field       a {@link ch.unibas.medizin.dynamicreports.report.builder.FieldBuilder} object.
     * @param calculation a {@link ch.unibas.medizin.dynamicreports.report.constant.Calculation} object.
     */
    protected CrosstabMeasureBuilder(final FieldBuilder<?> field, final Calculation calculation) {
        super(new DRCrosstabMeasure<>(Crosstabs.variable(field, calculation).build()));
        if (calculation.equals(Calculation.COUNT) || calculation.equals(Calculation.DISTINCT_COUNT)) {
            setDataType(DataTypes.longType());
        } else if (calculation.equals(Calculation.AVERAGE) || calculation.equals(Calculation.STANDARD_DEVIATION) || calculation.equals(Calculation.VARIANCE)) {
            setDataType(DataTypes.doubleType());
        } else {
            setDataType(field.getField().getDataType());
        }
    }

    /**
     * <p>Constructor for CrosstabMeasureBuilder.</p>
     *
     * @param expression  a {@link ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression} object.
     * @param calculation a {@link ch.unibas.medizin.dynamicreports.report.constant.Calculation} object.
     */
    protected CrosstabMeasureBuilder(final DRIExpression<?> expression, final Calculation calculation) {
        super(new DRCrosstabMeasure<>(Crosstabs.variable(expression, calculation).build()));
        if (calculation.equals(Calculation.COUNT) || calculation.equals(Calculation.DISTINCT_COUNT)) {
            setDataType(DataTypes.longType());
        } else if (calculation.equals(Calculation.AVERAGE) || calculation.equals(Calculation.STANDARD_DEVIATION) || calculation.equals(Calculation.VARIANCE)) {
            setDataType(DataTypes.doubleType());
        } else if (expression instanceof DRIField) {
            setDataType(((DRIField<?>) expression).getDataType());
        }
    }

    /**
     * <p>Constructor for CrosstabMeasureBuilder.</p>
     *
     * @param expression a {@link ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression} object.
     */
    protected CrosstabMeasureBuilder(final DRIExpression<?> expression) {
        super(new DRCrosstabMeasure<>(expression));
    }

    /**
     * <p>setDataType.</p>
     *
     * @param dataType a {@link ch.unibas.medizin.dynamicreports.report.definition.datatype.DRIDataType} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.crosstab.CrosstabMeasureBuilder} object.
     */
    public CrosstabMeasureBuilder<T> setDataType(final DRIDataType dataType) {
        getObject().setDataType(dataType);
        return this;
    }

    /**
     * <p>setPercentageType.</p>
     *
     * @param percentageType a {@link ch.unibas.medizin.dynamicreports.report.constant.CrosstabPercentageType} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.crosstab.CrosstabMeasureBuilder} object.
     */
    public CrosstabMeasureBuilder<T> setPercentageType(final CrosstabPercentageType percentageType) {
        if (getObject().getExpression() instanceof DRCrosstabVariable<?> variable) {
            if (percentageType != null && percentageType.equals(CrosstabPercentageType.GRAND_TOTAL) && !variable.getCalculation().equals(Calculation.COUNT) &&
                !variable.getCalculation().equals(Calculation.DISTINCT_COUNT)) {
                setDataType(DataTypes.doubleType());
            }
            variable.setPercentageType(percentageType);
        } else {
            throw new DRReportException("Percentage is not supported in this type of measure");
        }
        return this;
    }

    /**
     * <p>setPattern.</p>
     *
     * @param pattern a {@link java.lang.String} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.crosstab.CrosstabMeasureBuilder} object.
     */
    public CrosstabMeasureBuilder<T> setPattern(final String pattern) {
        getObject().setPattern(pattern);
        return this;
    }

    /**
     * <p>setHorizontalTextAlignment.</p>
     *
     * @param horizontalTextAlignment a {@link ch.unibas.medizin.dynamicreports.report.constant.HorizontalTextAlignment} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.crosstab.CrosstabMeasureBuilder} object.
     */
    public CrosstabMeasureBuilder<T> setHorizontalTextAlignment(final HorizontalTextAlignment horizontalTextAlignment) {
        getObject().setHorizontalTextAlignment(horizontalTextAlignment);
        return this;
    }

    /**
     * <p>setValueFormatter.</p>
     *
     * @param valueFormatter a {@link ch.unibas.medizin.dynamicreports.report.definition.expression.DRIValueFormatter} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.crosstab.CrosstabMeasureBuilder} object.
     */
    public CrosstabMeasureBuilder<T> setValueFormatter(final DRIValueFormatter<?, ? super T> valueFormatter) {
        getObject().setValueFormatter(valueFormatter);
        return this;
    }

    /**
     * <p>setTotalHeaderTextAdjust.</p>
     *
     * @param textAdjust a {@link ch.unibas.medizin.dynamicreports.report.constant.TextAdjust} object.
     * @return a T object.
     */
    public CrosstabMeasureBuilder<T> setTextAdjust(final TextAdjust textAdjust) {
        getObject().setTextAdjust(textAdjust);
        return  this;
    }

    /**
     * Sets the crosstab measure hyperlink.
     *
     * @param hyperLink the measure hyperlink
     * @return a crosstab measure builder
     */
    public CrosstabMeasureBuilder<T> setHyperLink(final HyperLinkBuilder hyperLink) {
        if (hyperLink != null) {
            getObject().setHyperLink(hyperLink.getHyperLink());
        } else {
            getObject().setHyperLink(null);
        }
        return this;
    }

    /**
     * Adds a jasper property to the measure.
     *
     * @param propertyExpression the property expression
     * @return a crosstab measure builder
     */
    public CrosstabMeasureBuilder<T> addProperty(final DRIPropertyExpression propertyExpression) {
        getObject().addPropertyExpression(propertyExpression);
        return this;
    }

    /**
     * Adds a jasper property to the measure.
     *
     * @param name            the property name
     * @param valueExpression the property value expression
     * @return a crosstab measure builder
     */
    public CrosstabMeasureBuilder<T> addProperty(final String name, final DRIExpression<String> valueExpression) {
        getObject().addPropertyExpression(Expressions.property(name, valueExpression));
        return this;
    }

    /**
     * Adds a jasper property to the measure.
     *
     * @param name  the property name
     * @param value the property value
     * @return a crosstab measure builder
     */
    public CrosstabMeasureBuilder<T> addProperty(final String name, final String value) {
        getObject().addPropertyExpression(Expressions.property(name, value));
        return this;
    }

    /**
     * <p>setStyle.</p>
     *
     * @param style a {@link ch.unibas.medizin.dynamicreports.report.builder.style.ReportStyleBuilder} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.crosstab.CrosstabMeasureBuilder} object.
     */
    public CrosstabMeasureBuilder<T> setStyle(final ReportStyleBuilder style) {
        Validate.notNull(style, "style must not be null");
        getObject().getStyles().add(new DRCrosstabCellStyle(style.getStyle()));
        return this;
    }

    /**
     * <p>setStyle.</p>
     *
     * @param style    a {@link ch.unibas.medizin.dynamicreports.report.builder.style.ReportStyleBuilder} object.
     * @param rowGroup a {@link ch.unibas.medizin.dynamicreports.report.builder.crosstab.CrosstabRowGroupBuilder} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.crosstab.CrosstabMeasureBuilder} object.
     */
    public CrosstabMeasureBuilder<T> setStyle(final ReportStyleBuilder style, final CrosstabRowGroupBuilder<?> rowGroup) {
        Validate.notNull(style, "style must not be null");
        Validate.notNull(rowGroup, "rowGroup must not be null");
        getObject().getStyles().add(new DRCrosstabCellStyle(style.getStyle(), rowGroup.build(), null));
        return this;
    }

    /**
     * <p>setStyle.</p>
     *
     * @param style       a {@link ch.unibas.medizin.dynamicreports.report.builder.style.ReportStyleBuilder} object.
     * @param columnGroup a {@link ch.unibas.medizin.dynamicreports.report.builder.crosstab.CrosstabColumnGroupBuilder} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.crosstab.CrosstabMeasureBuilder} object.
     */
    public CrosstabMeasureBuilder<T> setStyle(final ReportStyleBuilder style, final CrosstabColumnGroupBuilder<?> columnGroup) {
        Validate.notNull(style, "style must not be null");
        Validate.notNull(columnGroup, "columnGroup must not be null");
        getObject().getStyles().add(new DRCrosstabCellStyle(style.getStyle(), null, columnGroup.build()));
        return this;
    }

    /**
     * <p>setStyle.</p>
     *
     * @param style       a {@link ch.unibas.medizin.dynamicreports.report.builder.style.ReportStyleBuilder} object.
     * @param rowGroup    a {@link ch.unibas.medizin.dynamicreports.report.builder.crosstab.CrosstabRowGroupBuilder} object.
     * @param columnGroup a {@link ch.unibas.medizin.dynamicreports.report.builder.crosstab.CrosstabColumnGroupBuilder} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.crosstab.CrosstabMeasureBuilder} object.
     */
    public CrosstabMeasureBuilder<T> setStyle(final ReportStyleBuilder style, final CrosstabRowGroupBuilder<?> rowGroup, final CrosstabColumnGroupBuilder<?> columnGroup) {
        Validate.notNull(style, "style must not be null");
        Validate.notNull(rowGroup, "rowGroup must not be null");
        Validate.notNull(columnGroup, "columnGroup must not be null");
        getObject().getStyles().add(new DRCrosstabCellStyle(style.getStyle(), rowGroup.build(), columnGroup.build()));
        return this;
    }

    /**
     * <p>setTitle.</p>
     *
     * @param titleExpression a {@link ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.crosstab.CrosstabMeasureBuilder} object.
     */
    public CrosstabMeasureBuilder<T> setTitle(final DRIExpression<?> titleExpression) {
        getObject().setTitleExpression(titleExpression);
        return this;
    }

    /**
     * <p>setTitle.</p>
     *
     * @param title a {@link java.lang.String} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.crosstab.CrosstabMeasureBuilder} object.
     */
    public CrosstabMeasureBuilder<T> setTitle(final String title) {
        getObject().setTitleExpression(Expressions.text(title));
        return this;
    }

    /**
     * <p>setTitleStyle.</p>
     *
     * @param titleStyle a {@link ch.unibas.medizin.dynamicreports.report.builder.style.ReportStyleBuilder} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.crosstab.CrosstabMeasureBuilder} object.
     */
    public CrosstabMeasureBuilder<T> setTitleStyle(final ReportStyleBuilder titleStyle) {
        if (titleStyle != null) {
            getObject().setTitleStyle(titleStyle.getStyle());
        } else {
            getObject().setTitleStyle(null);
        }
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return build().getExpression().getName();
    }
}
