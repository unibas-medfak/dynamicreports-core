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
package ch.unibas.medizin.dynamicreports.report.builder.subtotal;

import ch.unibas.medizin.dynamicreports.report.base.DRVariable;
import ch.unibas.medizin.dynamicreports.report.builder.FieldBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.column.ColumnBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.column.ValueColumnBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.datatype.DataTypes;
import ch.unibas.medizin.dynamicreports.report.constant.Calculation;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import ch.unibas.medizin.dynamicreports.report.constant.Evaluation;
import ch.unibas.medizin.dynamicreports.report.constant.SubtotalPosition;
import ch.unibas.medizin.dynamicreports.report.definition.DRIField;
import ch.unibas.medizin.dynamicreports.report.definition.DRIValue;
import ch.unibas.medizin.dynamicreports.report.definition.column.DRIValueColumn;
import ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression;
import ch.unibas.medizin.dynamicreports.report.exception.DRReportException;

import java.io.Serial;

/**
 * <p>AggregationSubtotalBuilder class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class AggregationSubtotalBuilder<T> extends SubtotalBuilder<AggregationSubtotalBuilder<T>, T> implements DRIValue<T> {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private final DRIExpression<?> expression;
    private final Calculation calculation;

    // column

    /**
     * <p>Constructor for AggregationSubtotalBuilder.</p>
     *
     * @param column      a {@link ch.unibas.medizin.dynamicreports.report.builder.column.ValueColumnBuilder} object.
     * @param calculation a {@link ch.unibas.medizin.dynamicreports.report.constant.Calculation} object.
     */
    protected AggregationSubtotalBuilder(ValueColumnBuilder<?, ?> column, Calculation calculation) {
        this(column.getColumn(), column, calculation);
    }

    // field

    /**
     * <p>Constructor for AggregationSubtotalBuilder.</p>
     *
     * @param field        a {@link ch.unibas.medizin.dynamicreports.report.builder.FieldBuilder} object.
     * @param showInColumn a {@link ch.unibas.medizin.dynamicreports.report.builder.column.ColumnBuilder} object.
     * @param calculation  a {@link ch.unibas.medizin.dynamicreports.report.constant.Calculation} object.
     */
    protected AggregationSubtotalBuilder(FieldBuilder<?> field, ColumnBuilder<?, ?> showInColumn, Calculation calculation) {
        this(field.build(), showInColumn, calculation);
    }

    // expression

    /**
     * <p>Constructor for AggregationSubtotalBuilder.</p>
     *
     * @param expression   a {@link ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression} object.
     * @param showInColumn a {@link ch.unibas.medizin.dynamicreports.report.builder.column.ColumnBuilder} object.
     * @param calculation  a {@link ch.unibas.medizin.dynamicreports.report.constant.Calculation} object.
     */
    protected AggregationSubtotalBuilder(DRIExpression<?> expression, ColumnBuilder<?, ?> showInColumn, Calculation calculation) {
        super(showInColumn);
        this.expression = expression;
        this.calculation = calculation;
        if (calculation.equals(Calculation.COUNT) || calculation.equals(Calculation.DISTINCT_COUNT)) {
            setDataType(DataTypes.longType());
        } else if (calculation.equals(Calculation.AVERAGE) || calculation.equals(Calculation.STANDARD_DEVIATION) || calculation.equals(Calculation.VARIANCE)) {
            setDataType(DataTypes.doubleType());
        } else if (expression instanceof DRIValueColumn) {
            setDataType(((DRIValueColumn<?>) expression).getComponent().getDataType());
            setPattern(((DRIValueColumn<?>) expression).getComponent().getPattern());
        } else if (expression instanceof DRIField) {
            setDataType(((DRIField<?>) expression).getDataType());
        }
    }

    private static Evaluation subtotalPositionToEvaluation(SubtotalPosition position) {
        return switch (position) {
            case PAGE_HEADER, PAGE_FOOTER -> Evaluation.PAGE;
            case COLUMN_HEADER, COLUMN_FOOTER -> Evaluation.COLUMN;
            case GROUP_HEADER, GROUP_FOOTER -> Evaluation.GROUP;
            case FIRST_GROUP_HEADER, FIRST_GROUP_FOOTER -> Evaluation.FIRST_GROUP;
            case LAST_GROUP_HEADER, LAST_GROUP_FOOTER -> Evaluation.LAST_GROUP;
            case TITLE, LAST_PAGE_FOOTER, SUMMARY -> Evaluation.REPORT;
        };
    }

    /** {@inheritDoc} */
    @Override
    protected void configure() {
        DRVariable<T> subtotalVariable = new DRVariable<>(expression, calculation);
        Evaluation resetType = subtotalPositionToEvaluation(getObject().getPosition());
        subtotalVariable.setResetType(resetType);
        subtotalVariable.setResetGroup(getObject().getGroup());
        setValueExpression(subtotalVariable);

        super.configure();
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return getSubtotal().getName();
    }
}
