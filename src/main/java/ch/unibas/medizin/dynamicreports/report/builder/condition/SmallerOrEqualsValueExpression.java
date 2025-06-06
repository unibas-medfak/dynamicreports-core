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
package ch.unibas.medizin.dynamicreports.report.builder.condition;

import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import ch.unibas.medizin.dynamicreports.report.definition.DRIValue;

import java.io.Serial;

/**
 * <p>SmallerOrEqualsValueExpression class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class SmallerOrEqualsValueExpression<T extends Number> extends AbstractValueExpression<T> {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for SmallerOrEqualsValueExpression.</p>
     *
     * @param value  a {@link ch.unibas.medizin.dynamicreports.report.definition.DRIValue} object.
     * @param number a {@link java.lang.Number} object.
     */
    public SmallerOrEqualsValueExpression(DRIValue<T> value, Number number) {
        super(value, number);
    }

    /** {@inheritDoc} */
    @Override
    protected Boolean compare(Number actualValue, Number number) {
        return actualValue.doubleValue() <= number.doubleValue();
    }
}
