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

import ch.unibas.medizin.dynamicreports.report.constant.ComponentDimensionType;
import ch.unibas.medizin.dynamicreports.report.constant.ComponentPositionType;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import ch.unibas.medizin.dynamicreports.report.constant.StretchType;
import ch.unibas.medizin.dynamicreports.report.definition.DRIGroup;
import ch.unibas.medizin.dynamicreports.report.definition.component.DRIDimensionComponent;
import org.apache.commons.lang3.Validate;

import java.io.Serial;

/**
 * <p>Abstract DRDimensionComponent class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public abstract class DRDimensionComponent extends DRComponent implements DRIDimensionComponent {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private Integer width;
    private Integer height;
    private ComponentDimensionType widthType;
    private ComponentDimensionType heightType;
    private ComponentPositionType positionType;
    private StretchType stretchType;
    private Boolean printInFirstWholeBand;
    private Boolean printWhenDetailOverflows;
    private DRIGroup printWhenGroupChanges;

    /** {@inheritDoc} */
    @Override
    public Integer getWidth() {
        return width;
    }

    /**
     * <p>Setter for the field <code>width</code>.</p>
     *
     * @param width a {@link java.lang.Integer} object.
     */
    public void setWidth(Integer width) {
        if (width != null) {
            Validate.isTrue(width >= 0, "width must be >= 0");
        }
        this.width = width;
    }

    /** {@inheritDoc} */
    @Override
    public Integer getHeight() {
        return height;
    }

    /**
     * <p>Setter for the field <code>height</code>.</p>
     *
     * @param height a {@link java.lang.Integer} object.
     */
    public void setHeight(Integer height) {
        if (height != null) {
            Validate.isTrue(height >= 0, "height must be >= 0");
        }
        this.height = height;
    }

    /** {@inheritDoc} */
    @Override
    public ComponentDimensionType getWidthType() {
        return widthType;
    }

    /**
     * <p>Setter for the field <code>widthType</code>.</p>
     *
     * @param widthType a {@link ch.unibas.medizin.dynamicreports.report.constant.ComponentDimensionType} object.
     */
    public void setWidthType(ComponentDimensionType widthType) {
        this.widthType = widthType;
    }

    /** {@inheritDoc} */
    @Override
    public ComponentDimensionType getHeightType() {
        return heightType;
    }

    /**
     * <p>Setter for the field <code>heightType</code>.</p>
     *
     * @param heightType a {@link ch.unibas.medizin.dynamicreports.report.constant.ComponentDimensionType} object.
     */
    public void setHeightType(ComponentDimensionType heightType) {
        this.heightType = heightType;
    }

    /** {@inheritDoc} */
    @Override
    public ComponentPositionType getPositionType() {
        return positionType;
    }

    /**
     * <p>Setter for the field <code>positionType</code>.</p>
     *
     * @param positionType a {@link ch.unibas.medizin.dynamicreports.report.constant.ComponentPositionType} object.
     */
    public void setPositionType(ComponentPositionType positionType) {
        this.positionType = positionType;
    }

    /** {@inheritDoc} */
    @Override
    public StretchType getStretchType() {
        return stretchType;
    }

    /**
     * <p>Setter for the field <code>stretchType</code>.</p>
     *
     * @param stretchType a {@link ch.unibas.medizin.dynamicreports.report.constant.StretchType} object.
     */
    public void setStretchType(StretchType stretchType) {
        this.stretchType = stretchType;
    }

    /** {@inheritDoc} */
    @Override
    public Boolean getPrintInFirstWholeBand() {
        return printInFirstWholeBand;
    }

    /**
     * <p>Setter for the field <code>printInFirstWholeBand</code>.</p>
     *
     * @param printInFirstWholeBand a {@link java.lang.Boolean} object.
     */
    public void setPrintInFirstWholeBand(Boolean printInFirstWholeBand) {
        this.printInFirstWholeBand = printInFirstWholeBand;
    }

    /** {@inheritDoc} */
    @Override
    public Boolean getPrintWhenDetailOverflows() {
        return printWhenDetailOverflows;
    }

    /**
     * <p>Setter for the field <code>printWhenDetailOverflows</code>.</p>
     *
     * @param printWhenDetailOverflows a {@link java.lang.Boolean} object.
     */
    public void setPrintWhenDetailOverflows(Boolean printWhenDetailOverflows) {
        this.printWhenDetailOverflows = printWhenDetailOverflows;
    }

    /** {@inheritDoc} */
    @Override
    public DRIGroup getPrintWhenGroupChanges() {
        return printWhenGroupChanges;
    }

    /**
     * <p>Setter for the field <code>printWhenGroupChanges</code>.</p>
     *
     * @param printWhenGroupChanges a {@link ch.unibas.medizin.dynamicreports.report.definition.DRIGroup} object.
     */
    public void setPrintWhenGroupChanges(DRIGroup printWhenGroupChanges) {
        this.printWhenGroupChanges = printWhenGroupChanges;
    }
}
