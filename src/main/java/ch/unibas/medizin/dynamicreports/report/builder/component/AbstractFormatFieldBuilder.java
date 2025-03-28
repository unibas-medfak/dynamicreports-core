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

import ch.unibas.medizin.dynamicreports.report.base.component.DRFormatField;
import ch.unibas.medizin.dynamicreports.report.builder.expression.Expressions;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import ch.unibas.medizin.dynamicreports.report.constant.HorizontalTextAlignment;
import ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression;

import java.io.Serial;

/**
 * <p>Abstract AbstractFormatFieldBuilder class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public abstract class AbstractFormatFieldBuilder<T extends AbstractFormatFieldBuilder<T, U>, U extends DRFormatField> extends HyperLinkComponentBuilder<T, U> {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for AbstractFormatFieldBuilder.</p>
     *
     * @param formatField a U object.
     */
    protected AbstractFormatFieldBuilder(U formatField) {
        super(formatField);
    }

    /**
     * <p>setFormatExpression.</p>
     *
     * @param format a {@link java.lang.String} object.
     * @return a T object.
     */
    public T setFormatExpression(String format) {
        getObject().setFormatExpression(Expressions.text(format));
        return (T) this;
    }

    /**
     * <p>setFormatExpression.</p>
     *
     * @param formatExpression a {@link ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression} object.
     * @return a T object.
     */
    public T setFormatExpression(DRIExpression<String> formatExpression) {
        getObject().setFormatExpression(formatExpression);
        return (T) this;
    }

    /**
     * <p>setHorizontalTextAlignment.</p>
     *
     * @param horizontalTextAlignment a {@link ch.unibas.medizin.dynamicreports.report.constant.HorizontalTextAlignment} object.
     * @return a T object.
     */
    public T setHorizontalTextAlignment(HorizontalTextAlignment horizontalTextAlignment) {
        getObject().setHorizontalTextAlignment(horizontalTextAlignment);
        return (T) this;
    }
}
