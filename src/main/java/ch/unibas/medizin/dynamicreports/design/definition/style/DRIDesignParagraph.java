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
package ch.unibas.medizin.dynamicreports.design.definition.style;

import ch.unibas.medizin.dynamicreports.report.constant.LineSpacing;

import java.io.Serializable;
import java.util.List;

/**
 * <p>DRIDesignParagraph interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface DRIDesignParagraph extends Serializable {

    /**
     * <p>getLineSpacing.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.LineSpacing} object.
     */
    LineSpacing getLineSpacing();

    /**
     * <p>getLineSpacingSize.</p>
     *
     * @return a {@link java.lang.Float} object.
     */
    Float getLineSpacingSize();

    /**
     * <p>getFirstLineIndent.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    Integer getFirstLineIndent();

    /**
     * <p>getLeftIndent.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    Integer getLeftIndent();

    /**
     * <p>getRightIndent.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    Integer getRightIndent();

    /**
     * <p>getSpacingBefore.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    Integer getSpacingBefore();

    /**
     * <p>getSpacingAfter.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    Integer getSpacingAfter();

    /**
     * <p>getTabStopWidth.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    Integer getTabStopWidth();

    /**
     * <p>getTabStops.</p>
     *
     * @return a {@link java.util.List} object.
     */
    List<DRIDesignTabStop> getTabStops();
}
