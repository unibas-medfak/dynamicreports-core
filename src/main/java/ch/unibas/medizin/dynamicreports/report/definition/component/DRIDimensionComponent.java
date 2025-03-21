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
package ch.unibas.medizin.dynamicreports.report.definition.component;

import ch.unibas.medizin.dynamicreports.report.constant.ComponentDimensionType;
import ch.unibas.medizin.dynamicreports.report.constant.ComponentPositionType;
import ch.unibas.medizin.dynamicreports.report.constant.StretchType;
import ch.unibas.medizin.dynamicreports.report.definition.DRIGroup;

/**
 * <p>DRIDimensionComponent interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface DRIDimensionComponent extends DRIComponent {

    /**
     * <p>getWidth.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getWidth();

    /**
     * <p>getHeight.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getHeight();

    /**
     * <p>getWidthType.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.ComponentDimensionType} object.
     */
    public ComponentDimensionType getWidthType();

    /**
     * <p>getHeightType.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.ComponentDimensionType} object.
     */
    public ComponentDimensionType getHeightType();

    /**
     * <p>getPositionType.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.ComponentPositionType} object.
     */
    public ComponentPositionType getPositionType();

    /**
     * <p>getStretchType.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.StretchType} object.
     */
    public StretchType getStretchType();

    /**
     * <p>getPrintInFirstWholeBand.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getPrintInFirstWholeBand();

    /**
     * <p>getPrintWhenDetailOverflows.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getPrintWhenDetailOverflows();

    /**
     * <p>getPrintWhenGroupChanges.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.DRIGroup} object.
     */
    public DRIGroup getPrintWhenGroupChanges();
}
