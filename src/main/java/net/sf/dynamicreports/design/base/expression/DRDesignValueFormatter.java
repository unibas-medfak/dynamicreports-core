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
package net.sf.dynamicreports.design.base.expression;

import net.sf.dynamicreports.design.definition.expression.DRIDesignExpression;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.definition.expression.DRIValueFormatter;

import java.util.List;

/**
 * <p>DRDesignValueFormatter class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DRDesignValueFormatter extends AbstractDesignComplexExpression {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private DRIValueFormatter<?, Object> valueFormatter;

    @SuppressWarnings("unchecked")
    /**
     * Constructor for DRDesignValueFormatter.
     *
     * @param valueFormatter a {@link net.sf.dynamicreports.report.definition.expression.DRIValueFormatter} object.
     * @param valueExpression a {@link net.sf.dynamicreports.design.definition.expression.DRIDesignExpression} object.
     */
    public DRDesignValueFormatter(DRIValueFormatter<?, ?> valueFormatter, DRIDesignExpression valueExpression) {
        this.valueFormatter = (DRIValueFormatter<?, Object>) valueFormatter;
        addExpression(valueExpression);
    }

    /** {@inheritDoc} */
    @Override
    public Object evaluate(List<?> values, ReportParameters reportParameters) {
        return valueFormatter.format(values.get(0), reportParameters);
    }

    /** {@inheritDoc} */
    @Override
    public Class<?> getValueClass() {
        return valueFormatter.getValueClass();
    }
}
