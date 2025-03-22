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
package ch.unibas.medizin.dynamicreports.design.definition;

import ch.unibas.medizin.dynamicreports.report.constant.PageOrientation;

import java.io.Serializable;

/**
 * <p>DRIDesignPage interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface DRIDesignPage extends Serializable {

    /**
     * <p>getWidth.</p>
     *
     * @return a int.
     */
    int getWidth();

    /**
     * <p>getHeight.</p>
     *
     * @return a int.
     */
    int getHeight();

    /**
     * <p>getOrientation.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.PageOrientation} object.
     */
    PageOrientation getOrientation();

    /**
     * <p>getMargin.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.definition.DRIDesignMargin} object.
     */
    DRIDesignMargin getMargin();

    /**
     * <p>getColumnsPerPage.</p>
     *
     * @return a int.
     */
    int getColumnsPerPage();

    /**
     * <p>getColumnSpace.</p>
     *
     * @return a int.
     */
    int getColumnSpace();

    /**
     * <p>getColumnWidth.</p>
     *
     * @return a int.
     */
    int getColumnWidth();
}
