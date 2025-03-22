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

import ch.unibas.medizin.dynamicreports.report.base.expression.AbstractSimpleExpression;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import ch.unibas.medizin.dynamicreports.report.definition.DRIValue;
import ch.unibas.medizin.dynamicreports.report.definition.ReportParameters;
import org.apache.commons.lang3.Validate;

/**
 * <p>UnEqualExpression class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class UnEqualExpression extends AbstractSimpleExpression<Boolean> {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private final DRIValue<?> value;
    private final Object[] values;

    /**
     * <p>Constructor for UnEqualExpression.</p>
     *
     * @param value a {@link ch.unibas.medizin.dynamicreports.report.definition.DRIValue} object.
     * @param values a T object.
     * @param <T> a T object.
     */
    @SafeVarargs
    public <T> UnEqualExpression(DRIValue<T> value, T... values) {
        Validate.notNull(value, "value must not be null");
        Validate.noNullElements(values, "values must not contains null value");
        this.value = value;
        this.values = values;
    }

    /** {@inheritDoc} */
    @Override
    public Boolean evaluate(ReportParameters reportParameters) {
        Object actualValue = reportParameters.getValue(value);
        for (Object value : values) {
            if (value.equals(actualValue)) {
                return false;
            }
        }
        return true;
    }

    /** {@inheritDoc} */
    @Override
    public Class<Boolean> getValueClass() {
        return Boolean.class;
    }
}
