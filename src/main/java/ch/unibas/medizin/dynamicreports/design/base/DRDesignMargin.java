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

import ch.unibas.medizin.dynamicreports.design.definition.DRIDesignMargin;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;

import java.io.Serial;

/**
 * <p>DRDesignMargin class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DRDesignMargin implements DRIDesignMargin {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private int top;
    private int left;
    private int bottom;
    private int right;

    /** {@inheritDoc} */
    @Override
    public int getTop() {
        return top;
    }

    /**
     * <p>Setter for the field <code>top</code>.</p>
     *
     * @param top an int.
     */
    public void setTop(int top) {
        this.top = top;
    }

    /** {@inheritDoc} */
    @Override
    public int getLeft() {
        return left;
    }

    /**
     * <p>Setter for the field <code>left</code>.</p>
     *
     * @param left an int.
     */
    public void setLeft(int left) {
        this.left = left;
    }

    /** {@inheritDoc} */
    @Override
    public int getBottom() {
        return bottom;
    }

    /**
     * <p>Setter for the field <code>bottom</code>.</p>
     *
     * @param bottom an int.
     */
    public void setBottom(int bottom) {
        this.bottom = bottom;
    }

    /** {@inheritDoc} */
    @Override
    public int getRight() {
        return right;
    }

    /**
     * <p>Setter for the field <code>right</code>.</p>
     *
     * @param right an int.
     */
    public void setRight(int right) {
        this.right = right;
    }
}
