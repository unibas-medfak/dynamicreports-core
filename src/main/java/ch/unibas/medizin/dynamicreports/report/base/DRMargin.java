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
package ch.unibas.medizin.dynamicreports.report.base;

import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import ch.unibas.medizin.dynamicreports.report.definition.DRIMargin;
import org.apache.commons.lang3.Validate;

import java.io.Serial;

/**
 * <p>DRMargin class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DRMargin implements DRIMargin {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private int top;
    private int left;
    private int bottom;
    private int right;

    /**
     * <p>Constructor for DRMargin.</p>
     */
    public DRMargin() {
    }

    /**
     * <p>Constructor for DRMargin.</p>
     *
     * @param margin an int.
     */
    public DRMargin(int margin) {
        Validate.isTrue(margin >= 0, "margin must be >= 0");
        top = margin;
        left = margin;
        bottom = margin;
        right = margin;
    }

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
        Validate.isTrue(top >= 0, "top must be >= 0");
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
        Validate.isTrue(left >= 0, "left must be >= 0");
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
        Validate.isTrue(bottom >= 0, "bottom must be >= 0");
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
        Validate.isTrue(right >= 0, "right must be >= 0");
        this.right = right;
    }
}
