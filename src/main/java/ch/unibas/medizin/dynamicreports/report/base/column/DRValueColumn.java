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

import ch.unibas.medizin.dynamicreports.report.base.component.DRTextField;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import ch.unibas.medizin.dynamicreports.report.definition.column.DRIValueColumn;
import ch.unibas.medizin.dynamicreports.report.definition.component.DRITextField;

import java.io.Serial;

/**
 * <p>DRValueColumn class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DRValueColumn<T> extends DRColumn<DRITextField<T>> implements DRIValueColumn<T> {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private Boolean printRepeatedDetailValues;

    /**
     * <p>Constructor for DRValueColumn.</p>
     *
     * @param valueField a {@link ch.unibas.medizin.dynamicreports.report.base.component.DRTextField} object.
     */
    public DRValueColumn(DRTextField<T> valueField) {
        super(valueField);
    }

    /** {@inheritDoc} */
    @Override
    public Boolean getPrintRepeatedDetailValues() {
        return printRepeatedDetailValues;
    }

    /**
     * <p>Setter for the field <code>printRepeatedDetailValues</code>.</p>
     *
     * @param printRepeatedDetailValues a {@link java.lang.Boolean} object.
     */
    public void setPrintRepeatedDetailValues(Boolean printRepeatedDetailValues) {
        this.printRepeatedDetailValues = printRepeatedDetailValues;
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return getComponent().getValueExpression().getName();
    }

    /** {@inheritDoc} */
    @Override
    public Class<? super T> getValueClass() {
        return getComponent().getValueExpression().getValueClass();
    }
}
