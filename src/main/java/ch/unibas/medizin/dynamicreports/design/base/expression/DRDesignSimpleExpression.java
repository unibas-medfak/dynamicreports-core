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
package ch.unibas.medizin.dynamicreports.design.base.expression;

import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import ch.unibas.medizin.dynamicreports.report.definition.ReportParameters;
import ch.unibas.medizin.dynamicreports.report.definition.expression.DRISimpleExpression;

import java.io.Serial;

/**
 * <p>DRDesignSimpleExpression class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DRDesignSimpleExpression extends AbstractDesignSimpleExpression {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private final DRISimpleExpression<?> simpleExpression;
    private final String parameterName;

    /**
     * <p>Constructor for DRDesignSimpleExpression.</p>
     *
     * @param simpleExpression a {@link ch.unibas.medizin.dynamicreports.report.definition.expression.DRISimpleExpression} object.
     * @param parameterName    a {@link java.lang.String} object.
     */
    public DRDesignSimpleExpression(DRISimpleExpression<?> simpleExpression, String parameterName) {
        this.simpleExpression = simpleExpression;
        this.parameterName = parameterName;
    }

    /** {@inheritDoc} */
    @Override
    public Object evaluate(ReportParameters reportParameters) {
        return simpleExpression.evaluate(reportParameters);
    }

    /** {@inheritDoc} */
    @Override
    public Class<?> getValueClass() {
        return simpleExpression.getValueClass();
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return simpleExpression.getName();
    }

    /** {@inheritDoc} */
    @Override
    public String getParameterName() {
        return parameterName;
    }
}
