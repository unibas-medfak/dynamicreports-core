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
import ch.unibas.medizin.dynamicreports.report.constant.WhenNoDataType;
import ch.unibas.medizin.dynamicreports.report.constant.WhenResourceMissingType;
import ch.unibas.medizin.dynamicreports.report.exception.DRException;

import java.io.Serializable;
import java.util.List;

/**
 * <p>DRITemplateDesign interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface DRITemplateDesign<T> extends Serializable {

    /**
     * <p>getReportName.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    String getReportName();

    /**
     * <p>getFields.</p>
     *
     * @return a {@link java.util.List} object.
     */
    List<DRIField<?>> getFields();

    /**
     * <p>isDefinedParameter.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @return a boolean.
     */
    boolean isDefinedParameter(String name);

    /**
     * <p>getResourceBundleName.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    String getResourceBundleName();

    /**
     * <p>getIgnorePagination.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean getIgnorePagination();

    /**
     * <p>getWhenNoDataType.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.WhenNoDataType} object.
     */
    WhenNoDataType getWhenNoDataType();

    /**
     * <p>getWhenResourceMissingType.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.WhenResourceMissingType} object.
     */
    WhenResourceMissingType getWhenResourceMissingType();

    /**
     * <p>getTitleOnANewPage.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean getTitleOnANewPage();

    /**
     * <p>getSummaryOnANewPage.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean getSummaryOnANewPage();

    /**
     * <p>getSummaryWithPageHeaderAndFooter.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean getSummaryWithPageHeaderAndFooter();

    /**
     * <p>getFloatColumnFooter.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean getFloatColumnFooter();

    /**
     * <p>getPageWidth.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    Integer getPageWidth();

    /**
     * <p>getPageHeight.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    Integer getPageHeight();

    /**
     * <p>getPageOrientation.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.PageOrientation} object.
     */
    PageOrientation getPageOrientation();

    /**
     * <p>getPageMargin.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.DRIMargin} object.
     */
    DRIMargin getPageMargin();

    /**
     * <p>getPageColumnsPerPage.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    Integer getPageColumnsPerPage();

    /**
     * <p>getPageColumnSpace.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    Integer getPageColumnSpace();

    /**
     * <p>getPageColumnWidth.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    Integer getPageColumnWidth();

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
     * @return a T object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    T getDesign() throws DRException;
}
