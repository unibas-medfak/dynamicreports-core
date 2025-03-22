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
package ch.unibas.medizin.dynamicreports.report.base.expression;

import ch.unibas.medizin.dynamicreports.report.ReportUtils;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import ch.unibas.medizin.dynamicreports.report.definition.expression.DRIValueFormatter;
import org.apache.commons.lang3.Validate;

/**
 * <p>Abstract AbstractValueFormatter class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public abstract class AbstractValueFormatter<T, U> implements DRIValueFormatter<T, U> {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private final String name;

    /**
     * <p>Constructor for AbstractValueFormatter.</p>
     */
    protected AbstractValueFormatter() {
        this.name = ReportUtils.generateUniqueName("valueFormatter");
    }

    /**
     * <p>Constructor for AbstractValueFormatter.</p>
     *
     * @param name a {@link java.lang.String} object.
     */
    protected AbstractValueFormatter(String name) {
        Validate.notEmpty(name, "name must not be empty");
        this.name = name;
    }

    /**
     * <p>Getter for the field <code>name</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getName() {
        return name;
    }

    /** {@inheritDoc} */
    @Override
    @SuppressWarnings("unchecked")
    public Class<T> getValueClass() {
        return (Class<T>) ReportUtils.getGenericClass(this, 0);
    }
}
