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
package ch.unibas.medizin.dynamicreports.design.base;

import ch.unibas.medizin.dynamicreports.design.definition.DRIDesignSort;
import ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignExpression;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import ch.unibas.medizin.dynamicreports.report.constant.OrderType;

import java.io.Serial;

/**
 * <p>DRDesignSort class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DRDesignSort implements DRIDesignSort {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private DRIDesignExpression expression;
    private OrderType orderType;

    /** {@inheritDoc} */
    @Override
    public DRIDesignExpression getExpression() {
        return expression;
    }

    /**
     * <p>Setter for the field <code>expression</code>.</p>
     *
     * @param expression a {@link ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignExpression} object.
     */
    public void setExpression(DRIDesignExpression expression) {
        this.expression = expression;
    }

    /** {@inheritDoc} */
    @Override
    public OrderType getOrderType() {
        return orderType;
    }

    /**
     * <p>Setter for the field <code>orderType</code>.</p>
     *
     * @param orderType a {@link ch.unibas.medizin.dynamicreports.report.constant.OrderType} object.
     */
    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

}
