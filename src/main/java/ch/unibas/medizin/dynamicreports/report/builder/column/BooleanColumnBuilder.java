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
package ch.unibas.medizin.dynamicreports.report.builder.column;

import ch.unibas.medizin.dynamicreports.report.base.column.DRBooleanColumn;
import ch.unibas.medizin.dynamicreports.report.base.component.DRBooleanField;
import ch.unibas.medizin.dynamicreports.report.builder.FieldBuilder;
import ch.unibas.medizin.dynamicreports.report.constant.BooleanComponentType;
import ch.unibas.medizin.dynamicreports.report.constant.ComponentDimensionType;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import ch.unibas.medizin.dynamicreports.report.constant.HorizontalImageAlignment;
import ch.unibas.medizin.dynamicreports.report.constant.HorizontalTextAlignment;
import ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression;
import org.apache.commons.lang3.Validate;

import java.io.Serial;

/**
 * It shows a boolean value either as a text or as an image.
 *
 * @author Ricardo Mariaca
 * 
 */
public class BooleanColumnBuilder extends ColumnBuilder<BooleanColumnBuilder, DRBooleanColumn> {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for BooleanColumnBuilder.</p>
     *
     * @param field a {@link ch.unibas.medizin.dynamicreports.report.builder.FieldBuilder} object.
     */
    protected BooleanColumnBuilder(FieldBuilder<Boolean> field) {
        super(new DRBooleanColumn(new DRBooleanField()));
        Validate.notNull(field, "field must not be null");
        getComponent().setValueExpression(field.getField());
    }

    /**
     * <p>Constructor for BooleanColumnBuilder.</p>
     *
     * @param valueExpression a {@link ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression} object.
     */
    protected BooleanColumnBuilder(DRIExpression<Boolean> valueExpression) {
        super(new DRBooleanColumn(new DRBooleanField()));
        getComponent().setValueExpression(valueExpression);
    }

    /**
     * Sets the preferred width of a column.
     *
     * @param width the column preferred width >= 0
     * @return a column builder
     * @throws java.lang.IllegalArgumentException if <code>width</code> is &lt; 0
     * @see ch.unibas.medizin.dynamicreports.report.builder.Units
     */
    public BooleanColumnBuilder setWidth(Integer width) {
        getComponent().setWidth(width);
        return this;
    }

    /**
     * Sets the fixed width of a column.
     *
     * @param width the column fixed width >= 0
     * @return a column builder
     * @throws java.lang.IllegalArgumentException if <code>width</code> is &lt; 0
     * @see ch.unibas.medizin.dynamicreports.report.builder.Units
     */
    public BooleanColumnBuilder setFixedWidth(Integer width) {
        getComponent().setWidth(width);
        getComponent().setWidthType(ComponentDimensionType.FIXED);
        return this;
    }

    /**
     * Sets the minimum width of a column.
     *
     * @param width the column minimum width >= 0
     * @return a column builder
     * @throws java.lang.IllegalArgumentException if <code>width</code> is &lt; 0
     * @see ch.unibas.medizin.dynamicreports.report.builder.Units
     */
    public BooleanColumnBuilder setMinWidth(Integer width) {
        getComponent().setWidth(width);
        getComponent().setWidthType(ComponentDimensionType.EXPAND);
        return this;
    }

    /**
     * Sets the preferred height of a column.
     *
     * @param height the column preferred height >= 0
     * @return a column builder
     * @throws java.lang.IllegalArgumentException if <code>height</code> is &lt; 0
     * @see ch.unibas.medizin.dynamicreports.report.builder.Units
     */
    public BooleanColumnBuilder setHeight(Integer height) {
        getComponent().setHeight(height);
        return this;
    }

    /**
     * Sets the fixed height of a column.
     *
     * @param height the column fixed height >= 0
     * @return a column builder
     * @throws java.lang.IllegalArgumentException if <code>height</code> is &lt; 0
     * @see ch.unibas.medizin.dynamicreports.report.builder.Units
     */
    public BooleanColumnBuilder setFixedHeight(Integer height) {
        getComponent().setHeight(height);
        getComponent().setHeightType(ComponentDimensionType.FIXED);
        return this;
    }

    /**
     * Sets the minimum height of a column.
     *
     * @param height the column minimum height >= 0
     * @return a column builder
     * @throws java.lang.IllegalArgumentException if <code>height</code> is &lt; 0
     * @see ch.unibas.medizin.dynamicreports.report.builder.Units
     */
    public BooleanColumnBuilder setMinHeight(Integer height) {
        getComponent().setHeight(height);
        getComponent().setHeightType(ComponentDimensionType.EXPAND);
        return this;
    }

    /**
     * Sets the boolean presentation type.<br/>
     * <i>BooleanComponentType.TEXT_*</i> - shows a text value<br/>
     * <i>BooleanComponentType.IMAGE_*</i> - shows an image
     *
     * @param booleanComponentType the component type
     * @return a column builder
     */
    public BooleanColumnBuilder setComponentType(BooleanComponentType booleanComponentType) {
        getComponent().setComponentType(booleanComponentType);
        return this;
    }

    /**
     * <p>setEmptyWhenNullValue.</p>
     *
     * @param emptyWhenNullValue a {@link java.lang.Boolean} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.column.BooleanColumnBuilder} object.
     */
    public BooleanColumnBuilder setEmptyWhenNullValue(Boolean emptyWhenNullValue) {
        getComponent().setEmptyWhenNullValue(emptyWhenNullValue);
        return this;
    }

    /**
     * Sets the boolean image dimension. Has effect only when the boolean value is presented as an image.
     *
     * @param width  the image width
     * @param height the image height
     * @return a column builder
     */
    public BooleanColumnBuilder setImageDimension(Integer width, Integer height) {
        getComponent().setImageWidth(width);
        getComponent().setImageHeight(height);
        return this;
    }

    /**
     * Sets the boolean image width. Has effect only when the boolean value is presented as an image.
     *
     * @param width the image width
     * @return a column builder
     */
    public BooleanColumnBuilder setImageWidth(Integer width) {
        getComponent().setImageWidth(width);
        return this;
    }

    /**
     * Sets the boolean image height. Has effect only when the boolean value is presented as an image.
     *
     * @param height the image height
     * @return a column builder
     */
    public BooleanColumnBuilder setImageHeight(Integer height) {
        getComponent().setImageHeight(height);
        return this;
    }

    /**
     * Sets the column value horizontal image alignment.
     *
     * @param horizontalImageAlignment a {@link ch.unibas.medizin.dynamicreports.report.constant.HorizontalImageAlignment} object.
     * @return a column builder
     */
    public BooleanColumnBuilder setHorizontalImageAlignment(HorizontalImageAlignment horizontalImageAlignment) {
        getComponent().setHorizontalImageAlignment(horizontalImageAlignment);
        return this;
    }

    /**
     * Sets the column value horizontal text alignment.
     *
     * @param horizontalTextAlignment a {@link ch.unibas.medizin.dynamicreports.report.constant.HorizontalTextAlignment} object.
     * @return a column builder
     */
    public BooleanColumnBuilder setHorizontalTextAlignment(HorizontalTextAlignment horizontalTextAlignment) {
        getComponent().setHorizontalTextAlignment(horizontalTextAlignment);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    protected DRBooleanField getComponent() {
        return (DRBooleanField) getObject().getComponent();
    }
}
