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
package ch.unibas.medizin.dynamicreports.design.definition;

import ch.unibas.medizin.dynamicreports.design.constant.ResetType;
import ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignExpression;
import ch.unibas.medizin.dynamicreports.report.constant.Calculation;

/**
 * <p>DRIDesignVariable interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface DRIDesignVariable extends DRIDesignExpression {

    /**
     * <p>getValueExpression.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignExpression} object.
     */
    DRIDesignExpression getValueExpression();

    /**
     * <p>getInitialValueExpression.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignExpression} object.
     */
    DRIDesignExpression getInitialValueExpression();

    /**
     * <p>getCalculation.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.Calculation} object.
     */
    Calculation getCalculation();

    /**
     * <p>getResetType.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.constant.ResetType} object.
     */
    ResetType getResetType();

    /**
     * <p>getResetGroup.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.definition.DRIDesignGroup} object.
     */
    DRIDesignGroup getResetGroup();
}
