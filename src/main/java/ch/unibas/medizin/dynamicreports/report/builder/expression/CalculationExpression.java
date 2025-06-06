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

import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import ch.unibas.medizin.dynamicreports.report.definition.ReportParameters;
import ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression;
import org.apache.commons.lang3.Validate;

import java.io.Serial;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Ricardo Mariaca
 */
abstract class CalculationExpression extends AbstractComplexExpression<BigDecimal> {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;


    /**
     * <p>Constructor for CalculationExpression.</p>
     *
     * @param expressions a {@link ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression} object.
     */
    @SafeVarargs
    protected CalculationExpression(DRIExpression<? extends Number>... expressions) {
        Validate.notNull(expressions, "expressions must not be null");
        Validate.noNullElements(expressions, "expressions must not contains null expression");
        for (DRIExpression<? extends Number> expression : expressions) {
            addExpression(expression);
        }
    }

    /** {@inheritDoc} */
    @Override
    public BigDecimal evaluate(List<?> values, ReportParameters reportParameters) {
        BigDecimal result = null;
        for (Object value : values) {
            BigDecimal bigDecimalValue;
            if (value instanceof BigDecimal) {
                bigDecimalValue = (BigDecimal) value;
            } else {
                bigDecimalValue = BigDecimal.valueOf(((Number) value).doubleValue());
            }
            if (result == null) {
                result = bigDecimalValue;
            } else {
                result = calculate(result, bigDecimalValue);
            }
        }
        return result;
    }

    /**
     * <p>calculate.</p>
     *
     * @param value1 a {@link java.math.BigDecimal} object.
     * @param value2 a {@link java.math.BigDecimal} object.
     * @return a {@link java.math.BigDecimal} object.
     */
    protected abstract BigDecimal calculate(BigDecimal value1, BigDecimal value2);
}
