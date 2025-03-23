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

import ch.unibas.medizin.dynamicreports.report.builder.expression.AbstractComplexExpression;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import ch.unibas.medizin.dynamicreports.report.definition.DRICustomValues;
import ch.unibas.medizin.dynamicreports.report.definition.ReportParameters;
import ch.unibas.medizin.dynamicreports.report.definition.crosstab.DRICrosstab;
import ch.unibas.medizin.dynamicreports.report.definition.crosstab.DRICrosstabMeasure;
import ch.unibas.medizin.dynamicreports.report.definition.crosstab.DRICrosstabVariable;
import ch.unibas.medizin.dynamicreports.report.definition.expression.DRIComplexExpression;
import ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression;

import java.io.Serial;
import java.util.List;

/**
 * <p>CrosstabExpression class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class CrosstabExpression<T> extends AbstractComplexExpression<T> {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private final DRIExpression<T> expression;

    /**
     * <p>Constructor for CrosstabExpression.</p>
     *
     * @param crosstab   a {@link ch.unibas.medizin.dynamicreports.report.definition.crosstab.DRICrosstab} object.
     * @param expression a {@link ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression} object.
     */
    public CrosstabExpression(DRICrosstab crosstab, DRIExpression<T> expression) {
        this.expression = expression;
        if (expression instanceof DRIComplexExpression) {
            for (DRIExpression<?> express : ((DRIComplexExpression<?>) expression).getExpressions()) {
                addExpression(express);
            }
        }
        for (DRICrosstabVariable<?> variable : crosstab.getVariables()) {
            addExpression(variable);
        }
        for (DRICrosstabMeasure<?> measure : crosstab.getMeasures()) {
            if (measure.getExpression() instanceof DRICrosstabVariable<?>) {
                addExpression(measure.getExpression());
            }
        }
    }

    /** {@inheritDoc} */
    @Override
    public T evaluate(List<?> values, ReportParameters reportParameters) {
        DRICustomValues customValues = reportParameters.getParameterValue(DRICustomValues.NAME);
        for (int i = 0; i < getExpressions().size(); i++) {
            customValues.setSystemValue(getExpressions().get(i).getName(), values.get(i));
        }
        if (expression instanceof DRIComplexExpression<?> express) {
            return (T) express.evaluate(values, reportParameters);
        } else {
            return reportParameters.getValue(expression.getName());
        }
    }

    /** {@inheritDoc} */
    @Override
    public Class getValueClass() {
        return expression.getValueClass();
    }
}
