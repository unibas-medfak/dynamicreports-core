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
package ch.unibas.medizin.dynamicreports.report.builder.crosstab;

import ch.unibas.medizin.dynamicreports.report.base.crosstab.DRCrosstabVariable;
import ch.unibas.medizin.dynamicreports.report.builder.AbstractBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.FieldBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.column.ValueColumnBuilder;
import ch.unibas.medizin.dynamicreports.report.constant.Calculation;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import ch.unibas.medizin.dynamicreports.report.constant.CrosstabPercentageType;
import ch.unibas.medizin.dynamicreports.report.definition.DRICrosstabValue;
import ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression;

import java.io.Serial;

/**
 * <p>CrosstabVariableBuilder class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class CrosstabVariableBuilder<T> extends AbstractBuilder<CrosstabVariableBuilder<T>, DRCrosstabVariable<T>> implements DRICrosstabValue<T> {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for CrosstabVariableBuilder.</p>
     *
     * @param column      a {@link ch.unibas.medizin.dynamicreports.report.builder.column.ValueColumnBuilder} object.
     * @param calculation a {@link ch.unibas.medizin.dynamicreports.report.constant.Calculation} object.
     */
    protected CrosstabVariableBuilder(ValueColumnBuilder<?, ?> column, Calculation calculation) {
        super(new DRCrosstabVariable<>(column.build(), calculation));
    }

    /**
     * <p>Constructor for CrosstabVariableBuilder.</p>
     *
     * @param field       a {@link ch.unibas.medizin.dynamicreports.report.builder.FieldBuilder} object.
     * @param calculation a {@link ch.unibas.medizin.dynamicreports.report.constant.Calculation} object.
     */
    protected CrosstabVariableBuilder(FieldBuilder<?> field, Calculation calculation) {
        super(new DRCrosstabVariable<>(field.getField(), calculation));
    }

    /**
     * <p>Constructor for CrosstabVariableBuilder.</p>
     *
     * @param expression  a {@link ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression} object.
     * @param calculation a {@link ch.unibas.medizin.dynamicreports.report.constant.Calculation} object.
     */
    protected CrosstabVariableBuilder(DRIExpression<?> expression, Calculation calculation) {
        super(new DRCrosstabVariable<>(expression, calculation));
    }

    /**
     * <p>setPercentageType.</p>
     *
     * @param percentageType a {@link ch.unibas.medizin.dynamicreports.report.constant.CrosstabPercentageType} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.crosstab.CrosstabVariableBuilder} object.
     */
    public CrosstabVariableBuilder<T> setPercentageType(CrosstabPercentageType percentageType) {
        getObject().setPercentageType(percentageType);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return getObject().getName();
    }
}
