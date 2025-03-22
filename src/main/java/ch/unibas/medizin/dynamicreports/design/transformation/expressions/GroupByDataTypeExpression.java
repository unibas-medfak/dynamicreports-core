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
package ch.unibas.medizin.dynamicreports.design.transformation.expressions;

import ch.unibas.medizin.dynamicreports.report.base.expression.AbstractSimpleExpression;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import ch.unibas.medizin.dynamicreports.report.definition.ReportParameters;
import ch.unibas.medizin.dynamicreports.report.definition.datatype.DRIDataType;
import ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression;

import java.io.Serial;

/**
 * <p>GroupByDataTypeExpression class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class GroupByDataTypeExpression extends AbstractSimpleExpression<String> {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;
    final DRIDataType<?, ?> dataType;
    private final DRIExpression<?> valueExpression;

    /**
     * <p>Constructor for GroupByDataTypeExpression.</p>
     *
     * @param valueExpression a {@link ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression} object.
     * @param dataType        a {@link ch.unibas.medizin.dynamicreports.report.definition.datatype.DRIDataType} object.
     */
    public GroupByDataTypeExpression(DRIExpression<?> valueExpression, DRIDataType<?, ?> dataType) {
        this.valueExpression = valueExpression;
        this.dataType = dataType;
    }

    /** {@inheritDoc} */
    @Override
    public String evaluate(ReportParameters reportParameters) {
        return dataType.valueToString(valueExpression.getName(), reportParameters);
    }
}
