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

import ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignExpression;
import ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignPropertyExpression;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;

import java.io.Serial;

/**
 * <p>DRDesignPropertyExpression class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DRDesignPropertyExpression implements DRIDesignPropertyExpression {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private String name;
    private DRIDesignExpression valueExpression;

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
}
