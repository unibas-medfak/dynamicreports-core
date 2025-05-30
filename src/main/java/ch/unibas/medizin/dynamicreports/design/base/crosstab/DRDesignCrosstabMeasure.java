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
package ch.unibas.medizin.dynamicreports.design.base.crosstab;

import ch.unibas.medizin.dynamicreports.design.definition.crosstab.DRIDesignCrosstabMeasure;
import ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignExpression;
import ch.unibas.medizin.dynamicreports.report.ReportUtils;
import ch.unibas.medizin.dynamicreports.report.constant.Calculation;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import ch.unibas.medizin.dynamicreports.report.constant.CrosstabPercentageType;

import java.io.Serial;

/**
 * <p>DRDesignCrosstabMeasure class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DRDesignCrosstabMeasure implements DRIDesignCrosstabMeasure {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private String name;
    private DRIDesignExpression valueExpression;
    private Calculation calculation;
    private CrosstabPercentageType percentageType;

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return name;
    }

    /**
     * <p>Setter for the field <code>name</code>.</p>
     *
     * @param name a {@link java.lang.String} object.
     */
    public void setName(String name) {
        this.name = name;
    }

    /** {@inheritDoc} */
    @Override
    public DRIDesignExpression getValueExpression() {
        return valueExpression;
    }

    /**
     * <p>Setter for the field <code>valueExpression</code>.</p>
     *
     * @param valueExpression a {@link ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignExpression} object.
     */
    public void setValueExpression(DRIDesignExpression valueExpression) {
        this.valueExpression = valueExpression;
    }

    /** {@inheritDoc} */
    @Override
    public Calculation getCalculation() {
        return calculation;
    }

    /**
     * <p>Setter for the field <code>calculation</code>.</p>
     *
     * @param calculation a {@link ch.unibas.medizin.dynamicreports.report.constant.Calculation} object.
     */
    public void setCalculation(Calculation calculation) {
        this.calculation = calculation;
    }

    /** {@inheritDoc} */
    @Override
    public CrosstabPercentageType getPercentageType() {
        return percentageType;
    }

    /**
     * <p>Setter for the field <code>percentageType</code>.</p>
     *
     * @param percentageType a {@link ch.unibas.medizin.dynamicreports.report.constant.CrosstabPercentageType} object.
     */
    public void setPercentageType(CrosstabPercentageType percentageType) {
        this.percentageType = percentageType;
    }

    /** {@inheritDoc} */
    @Override
    public Class<?> getValueClass() {
        if (percentageType != null && percentageType.equals(CrosstabPercentageType.GRAND_TOTAL) && !calculation.equals(Calculation.COUNT) && !calculation.equals(Calculation.DISTINCT_COUNT)) {
            return Double.class;
        }
        return ReportUtils.getVariableValueClass(calculation, valueExpression.getValueClass());
    }
}
