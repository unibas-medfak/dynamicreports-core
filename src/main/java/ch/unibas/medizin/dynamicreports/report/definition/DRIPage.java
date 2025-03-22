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
package ch.unibas.medizin.dynamicreports.report.definition;

import ch.unibas.medizin.dynamicreports.report.constant.PageOrientation;

import java.io.Serializable;

/**
 * <p>DRIPage interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface DRIPage extends Serializable {

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
     * <p>getOrientation.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.PageOrientation} object.
     */
    PageOrientation getOrientation();

    /**
     * <p>getMargin.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.DRIMargin} object.
     */
    DRIMargin getMargin();

    /**
     * <p>getColumnsPerPage.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    Integer getColumnsPerPage();

    /**
     * <p>getColumnSpace.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    Integer getColumnSpace();

    /**
     * <p>getIgnorePageWidth.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean getIgnorePageWidth();
}
