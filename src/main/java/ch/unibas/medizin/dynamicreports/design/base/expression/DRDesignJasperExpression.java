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

import ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignJasperExpression;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import ch.unibas.medizin.dynamicreports.report.definition.expression.DRIJasperExpression;

import java.io.Serial;

/**
 * <p>DRDesignJasperExpression class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DRDesignJasperExpression implements DRIDesignJasperExpression {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private final DRIJasperExpression<?> jasperExpression;

    /**
     * <p>Constructor for DRDesignJasperExpression.</p>
     *
     * @param jasperExpression a {@link ch.unibas.medizin.dynamicreports.report.definition.expression.DRIJasperExpression} object.
     */
    public DRDesignJasperExpression(DRIJasperExpression<?> jasperExpression) {
        this.jasperExpression = jasperExpression;
    }

    /** {@inheritDoc} */
    @Override
    public String getExpression() {
        return jasperExpression.getExpression();
    }

    /** {@inheritDoc} */
    @Override
    public Class<?> getValueClass() {
        return jasperExpression.getValueClass();
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return jasperExpression.getName();
    }
}
