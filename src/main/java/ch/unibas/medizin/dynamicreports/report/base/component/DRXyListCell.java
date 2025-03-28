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
package ch.unibas.medizin.dynamicreports.report.base.component;

import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import ch.unibas.medizin.dynamicreports.report.definition.component.DRIXyListCell;
import org.apache.commons.lang3.Validate;

import java.io.Serial;

/**
 * <p>DRXyListCell class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DRXyListCell implements DRIXyListCell {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private Integer x;
    private Integer y;
    private final DRComponent component;

    /**
     * <p>Constructor for DRXyListCell.</p>
     *
     * @param x         a {@link java.lang.Integer} object.
     * @param y         a {@link java.lang.Integer} object.
     * @param component a {@link ch.unibas.medizin.dynamicreports.report.base.component.DRComponent} object.
     */
    public DRXyListCell(Integer x, Integer y, DRComponent component) {
        Validate.notNull(x, "x must not be null");
        Validate.notNull(y, "y must not be null");
        Validate.notNull(component, "component must not be null");
        this.x = x;
        this.y = y;
        this.component = component;
    }

    /** {@inheritDoc} */
    @Override
    public Integer getX() {
        return x;
    }

    /**
     * <p>Setter for the field <code>x</code>.</p>
     *
     * @param x a {@link java.lang.Integer} object.
     */
    public void setX(Integer x) {
        this.x = x;
    }

    /** {@inheritDoc} */
    @Override
    public Integer getY() {
        return y;
    }

    /**
     * <p>Setter for the field <code>y</code>.</p>
     *
     * @param y a {@link java.lang.Integer} object.
     */
    public void setY(Integer y) {
        this.y = y;
    }

    /** {@inheritDoc} */
    @Override
    public DRComponent getComponent() {
        return component;
    }
}
