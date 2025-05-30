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
package ch.unibas.medizin.dynamicreports.report.base.column;

import ch.unibas.medizin.dynamicreports.report.base.component.DRBooleanField;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import ch.unibas.medizin.dynamicreports.report.definition.column.DRIBooleanColumn;
import ch.unibas.medizin.dynamicreports.report.definition.component.DRIBooleanField;

import java.io.Serial;

/**
 * <p>DRBooleanColumn class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DRBooleanColumn extends DRColumn<DRIBooleanField> implements DRIBooleanColumn {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for DRBooleanColumn.</p>
     *
     * @param booleanField a {@link ch.unibas.medizin.dynamicreports.report.base.component.DRBooleanField} object.
     */
    public DRBooleanColumn(DRBooleanField booleanField) {
        super(booleanField);
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return getComponent().getValueExpression().getName();
    }

    /** {@inheritDoc} */
    @Override
    public Class<Boolean> getValueClass() {
        return Boolean.class;
    }
}
