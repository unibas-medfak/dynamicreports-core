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
package ch.unibas.medizin.dynamicreports.design.definition.crosstab;

import ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignExpression;
import ch.unibas.medizin.dynamicreports.report.constant.CrosstabTotalPosition;
import ch.unibas.medizin.dynamicreports.report.constant.OrderType;

import java.io.Serializable;

/**
 * <p>DRIDesignCrosstabGroup interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface DRIDesignCrosstabGroup extends Serializable {

    /**
     * <p>getName.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    String getName();

    /**
     * <p>getTotalPosition.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.CrosstabTotalPosition} object.
     */
    CrosstabTotalPosition getTotalPosition();

    /**
     * <p>getOrderType.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.OrderType} object.
     */
    OrderType getOrderType();

    /**
     * <p>getExpression.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignExpression} object.
     */
    DRIDesignExpression getExpression();

    /**
     * <p>getOrderByExpression.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignExpression} object.
     */
    DRIDesignExpression getOrderByExpression();

    /**
     * <p>getComparatorExpression.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignExpression} object.
     */
    DRIDesignExpression getComparatorExpression();

    /**
     * <p>getHeader.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.definition.crosstab.DRIDesignCrosstabCellContent} object.
     */
    DRIDesignCrosstabCellContent getHeader();

    /**
     * <p>getTotalHeader.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.definition.crosstab.DRIDesignCrosstabCellContent} object.
     */
    DRIDesignCrosstabCellContent getTotalHeader();
}
