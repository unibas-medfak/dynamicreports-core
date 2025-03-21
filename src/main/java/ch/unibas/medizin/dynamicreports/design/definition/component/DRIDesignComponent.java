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
package ch.unibas.medizin.dynamicreports.design.definition.component;

import ch.unibas.medizin.dynamicreports.design.definition.DRIDesignGroup;
import ch.unibas.medizin.dynamicreports.design.definition.DRIDesignTableOfContentsHeading;
import ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignExpression;
import ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignPropertyExpression;
import ch.unibas.medizin.dynamicreports.design.definition.style.DRIDesignStyle;
import ch.unibas.medizin.dynamicreports.report.constant.ComponentPositionType;
import ch.unibas.medizin.dynamicreports.report.constant.StretchType;

import java.io.Serializable;
import java.util.List;

/**
 * <p>DRIDesignComponent interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface DRIDesignComponent extends Serializable {
    /**
     * <p>getName.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    String getName();

    /**
     * <p>getUniqueName.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    String getUniqueName();

    /**
     * <p>getStyle.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.definition.style.DRIDesignStyle} object.
     */
    DRIDesignStyle getStyle();

    /**
     * <p>getX.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    Integer getX();

    /**
     * <p>getY.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    Integer getY();

    /**
     * <p>getWidth.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    Integer getWidth();

    /**
     * <p>getHeight.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    Integer getHeight();

    /**
     * <p>getPrintWhenExpression.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignExpression} object.
     */
    DRIDesignExpression getPrintWhenExpression();

    /**
     * <p>isRemoveLineWhenBlank.</p>
     *
     * @return a boolean.
     */
    boolean isRemoveLineWhenBlank();

    /**
     * <p>getPropertyExpressions.</p>
     *
     * @return a {@link java.util.List} object.
     */
    List<DRIDesignPropertyExpression> getPropertyExpressions();

    /**
     * <p>getPositionType.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.ComponentPositionType} object.
     */
    ComponentPositionType getPositionType();

    /**
     * <p>getStretchType.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.StretchType} object.
     */
    StretchType getStretchType();

    /**
     * <p>isPrintInFirstWholeBand.</p>
     *
     * @return a boolean.
     */
    boolean isPrintInFirstWholeBand();

    /**
     * <p>isPrintWhenDetailOverflows.</p>
     *
     * @return a boolean.
     */
    boolean isPrintWhenDetailOverflows();

    /**
     * <p>getPrintWhenGroupChanges.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.definition.DRIDesignGroup} object.
     */
    DRIDesignGroup getPrintWhenGroupChanges();

    /**
     * <p>getTableOfContentsHeading.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.definition.DRIDesignTableOfContentsHeading} object.
     */
    DRIDesignTableOfContentsHeading getTableOfContentsHeading();
}
