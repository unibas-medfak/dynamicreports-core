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

import ch.unibas.medizin.dynamicreports.report.constant.GroupFooterPosition;
import ch.unibas.medizin.dynamicreports.report.constant.GroupHeaderLayout;
import ch.unibas.medizin.dynamicreports.report.definition.component.DRITextField;
import ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression;
import ch.unibas.medizin.dynamicreports.report.definition.style.DRIReportStyle;

import java.io.Serializable;

/**
 * <p>DRIGroup interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface DRIGroup extends Serializable {

    /**
     * <p>getName.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    String getName();

    /**
     * <p>getValueField.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.component.DRITextField} object.
     */
    DRITextField<?> getValueField();

    /**
     * <p>getTitleExpression.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression} object.
     */
    DRIExpression<?> getTitleExpression();

    /**
     * <p>getTitleStyle.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.style.DRIReportStyle} object.
     */
    DRIReportStyle getTitleStyle();

    /**
     * <p>getTitleWidth.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    Integer getTitleWidth();

    /**
     * <p>getHeaderLayout.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.GroupHeaderLayout} object.
     */
    GroupHeaderLayout getHeaderLayout();

    /**
     * <p>getHideColumn.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean getHideColumn();

    /**
     * <p>getGroupByDataType.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean getGroupByDataType();

    /**
     * <p>getShowColumnHeaderAndFooter.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean getShowColumnHeaderAndFooter();

    /**
     * <p>getAddToTableOfContents.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean getAddToTableOfContents();

    /**
     * <p>getPrintSubtotalsWhenExpression.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression} object.
     */
    DRIExpression<Boolean> getPrintSubtotalsWhenExpression();

    /**
     * <p>getPadding.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    Integer getPadding();

    /**
     * <p>getStartInNewPage.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean getStartInNewPage();

    /**
     * <p>getStartInNewColumn.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean getStartInNewColumn();

    /**
     * <p>getReprintHeaderOnEachPage.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean getReprintHeaderOnEachPage();

    /**
     * <p>getResetPageNumber.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean getResetPageNumber();

    /**
     * <p>getMinHeightToStartNewPage.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    Integer getMinHeightToStartNewPage();

    /**
     * <p>getFooterPosition.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.GroupFooterPosition} object.
     */
    GroupFooterPosition getFooterPosition();

    /**
     * <p>getKeepTogether.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean getKeepTogether();

    /**
     * <p>getHeaderWithSubtotal.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean getHeaderWithSubtotal();

    /**
     * <p>getHeaderBand.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.DRIBand} object.
     */
    DRIBand getHeaderBand();

    /**
     * <p>getFooterBand.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.DRIBand} object.
     */
    DRIBand getFooterBand();
}
