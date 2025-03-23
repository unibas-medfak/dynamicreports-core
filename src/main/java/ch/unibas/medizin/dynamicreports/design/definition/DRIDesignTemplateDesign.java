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

import ch.unibas.medizin.dynamicreports.report.exception.DRException;

import java.io.Serializable;

/**
 * <p>DRIDesignTemplateDesign interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface DRIDesignTemplateDesign extends Serializable {

    /**
     * <p>getTitleComponentsCount.</p>
     *
     * @return an int.
     */
    int getTitleComponentsCount();

    /**
     * <p>getPageHeaderComponentsCount.</p>
     *
     * @return an int.
     */
    int getPageHeaderComponentsCount();

    /**
     * <p>getPageFooterComponentsCount.</p>
     *
     * @return an int.
     */
    int getPageFooterComponentsCount();

    /**
     * <p>getColumnHeaderComponentsCount.</p>
     *
     * @return an int.
     */
    int getColumnHeaderComponentsCount();

    /**
     * <p>getColumnFooterComponentsCount.</p>
     *
     * @return an int.
     */
    int getColumnFooterComponentsCount();

    /**
     * <p>getLastPageFooterComponentsCount.</p>
     *
     * @return an int.
     */
    int getLastPageFooterComponentsCount();

    /**
     * <p>getSummaryComponentsCount.</p>
     *
     * @return an int.
     */
    int getSummaryComponentsCount();

    /**
     * <p>getNoDataComponentsCount.</p>
     *
     * @return an int.
     */
    int getNoDataComponentsCount();

    /**
     * <p>getBackgroundComponentsCount.</p>
     *
     * @return an int.
     */
    int getBackgroundComponentsCount();

    /**
     * <p>getDesign.</p>
     *
     * @return a {@link java.lang.Object} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    Object getDesign() throws DRException;
}
