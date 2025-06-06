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
package ch.unibas.medizin.dynamicreports.report.builder.expression;

import ch.unibas.medizin.dynamicreports.report.base.expression.AbstractSimpleExpression;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import ch.unibas.medizin.dynamicreports.report.definition.ReportParameters;
import org.apache.commons.lang3.Validate;

import java.io.Serial;

/**
 * <p>ValueExpression class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class ValueExpression<T> extends AbstractSimpleExpression<T> {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private final T value;
    private final Class<? super T> valueClass;

    /**
     * <p>Constructor for ValueExpression.</p>
     *
     * @param value a T object.
     */
    public ValueExpression(T value) {
        Validate.notNull(value, "value must not be null");
        this.value = value;
        this.valueClass = (Class<? super T>) value.getClass();
    }

    /**
     * <p>Constructor for ValueExpression.</p>
     *
     * @param value      a T object.
     * @param valueClass a {@link java.lang.Class} object.
     */
    public ValueExpression(T value, Class<? super T> valueClass) {
        Validate.notNull(valueClass, "valueClass must not be null");
        this.value = value;
        this.valueClass = valueClass;
    }

    /** {@inheritDoc} */
    @Override
    public T evaluate(ReportParameters reportParameters) {
        return value;
    }

    /** {@inheritDoc} */
    @Override
    public Class<? super T> getValueClass() {
        return valueClass;
    }
}
