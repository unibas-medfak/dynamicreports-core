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

import ch.unibas.medizin.dynamicreports.report.ReportUtils;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import ch.unibas.medizin.dynamicreports.report.definition.expression.DRIJasperExpression;
import org.apache.commons.lang3.Validate;

import java.io.Serial;

/**
 * <p>JasperExpression class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class JasperExpression<T> implements DRIJasperExpression<T> {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private final String name;
    private final String expression;
    private final Class<? super T> valueClass;

    /**
     * <p>Constructor for JasperExpression.</p>
     *
     * @param expression a {@link java.lang.String} object.
     * @param valueClass a {@link java.lang.Class} object.
     */
    protected JasperExpression(String expression, Class<? super T> valueClass) {
        Validate.notNull(expression, "expression must not be null");
        Validate.notNull(valueClass, "valueClass must not be null");
        this.expression = expression;
        this.valueClass = valueClass;
        this.name = ReportUtils.generateUniqueName("jasperExpression");
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return name;
    }

    /** {@inheritDoc} */
    @Override
    public String getExpression() {
        return expression;
    }

    /** {@inheritDoc} */
    @Override
    public Class<? super T> getValueClass() {
        return valueClass;
    }
}
