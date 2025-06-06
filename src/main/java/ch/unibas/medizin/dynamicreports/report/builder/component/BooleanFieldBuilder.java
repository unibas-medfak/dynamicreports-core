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
package ch.unibas.medizin.dynamicreports.report.builder.component;

import ch.unibas.medizin.dynamicreports.report.base.component.DRBooleanField;
import ch.unibas.medizin.dynamicreports.report.builder.FieldBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.expression.Expressions;
import ch.unibas.medizin.dynamicreports.report.constant.BooleanComponentType;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import ch.unibas.medizin.dynamicreports.report.constant.HorizontalImageAlignment;
import ch.unibas.medizin.dynamicreports.report.constant.HorizontalTextAlignment;
import ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression;
import org.apache.commons.lang3.Validate;

import java.io.Serial;

/**
 * <p>BooleanFieldBuilder class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class BooleanFieldBuilder extends HyperLinkComponentBuilder<BooleanFieldBuilder, DRBooleanField> {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for BooleanFieldBuilder.</p>
     */
    protected BooleanFieldBuilder() {
        super(new DRBooleanField());
    }

    /**
     * <p>setValue.</p>
     *
     * @param field a {@link ch.unibas.medizin.dynamicreports.report.builder.FieldBuilder} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.component.BooleanFieldBuilder} object.
     */
    public BooleanFieldBuilder setValue(FieldBuilder<Boolean> field) {
        Validate.notNull(field, "field must not be null");
        getObject().setValueExpression(field.getField());
        return this;
    }

    /**
     * <p>setValue.</p>
     *
     * @param valueExpression a {@link ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.component.BooleanFieldBuilder} object.
     */
    public BooleanFieldBuilder setValue(DRIExpression<Boolean> valueExpression) {
        getObject().setValueExpression(valueExpression);
        return this;
    }

    /**
     * <p>setValue.</p>
     *
     * @param value a {@link java.lang.Boolean} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.component.BooleanFieldBuilder} object.
     */
    public BooleanFieldBuilder setValue(Boolean value) {
        getObject().setValueExpression(Expressions.value(value));
        return this;
    }

    /**
     * <p>setComponentType.</p>
     *
     * @param booleanComponentType a {@link ch.unibas.medizin.dynamicreports.report.constant.BooleanComponentType} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.component.BooleanFieldBuilder} object.
     */
    public BooleanFieldBuilder setComponentType(BooleanComponentType booleanComponentType) {
        getObject().setComponentType(booleanComponentType);
        return this;
    }

    /**
     * <p>setEmptyWhenNullValue.</p>
     *
     * @param emptyWhenNullValue a {@link java.lang.Boolean} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.component.BooleanFieldBuilder} object.
     */
    public BooleanFieldBuilder setEmptyWhenNullValue(Boolean emptyWhenNullValue) {
        getObject().setEmptyWhenNullValue(emptyWhenNullValue);
        return this;
    }

    /**
     * Sets the boolean image dimension. Has effect only when the boolean value is presented as an image.
     *
     * @param width  the image width
     * @param height the image height
     * @return a column builder
     */
    public BooleanFieldBuilder setImageDimension(Integer width, Integer height) {
        getObject().setImageWidth(width);
        getObject().setImageHeight(height);
        return this;
    }

    /**
     * Sets the boolean image width. Has effect only when the boolean value is presented as an image.
     *
     * @param width the image width
     * @return a column builder
     */
    public BooleanFieldBuilder setImageWidth(Integer width) {
        getObject().setImageWidth(width);
        return this;
    }

    /**
     * Sets the boolean image height. Has effect only when the boolean value is presented as an image.
     *
     * @param height the image height
     * @return a column builder
     */
    public BooleanFieldBuilder setImageHeight(Integer height) {
        getObject().setImageHeight(height);
        return this;
    }

    /**
     * <p>setHorizontalImageAlignment.</p>
     *
     * @param horizontalImageAlignment a {@link ch.unibas.medizin.dynamicreports.report.constant.HorizontalImageAlignment} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.component.BooleanFieldBuilder} object.
     */
    public BooleanFieldBuilder setHorizontalImageAlignment(HorizontalImageAlignment horizontalImageAlignment) {
        getObject().setHorizontalImageAlignment(horizontalImageAlignment);
        return this;
    }

    /**
     * <p>setHorizontalTextAlignment.</p>
     *
     * @param horizontalTextAlignment a {@link ch.unibas.medizin.dynamicreports.report.constant.HorizontalTextAlignment} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.component.BooleanFieldBuilder} object.
     */
    public BooleanFieldBuilder setHorizontalTextAlignment(HorizontalTextAlignment horizontalTextAlignment) {
        getObject().setHorizontalTextAlignment(horizontalTextAlignment);
        return this;
    }
}
