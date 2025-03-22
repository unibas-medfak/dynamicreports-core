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
package ch.unibas.medizin.dynamicreports.report.definition.crosstab;

import ch.unibas.medizin.dynamicreports.report.constant.RunDirection;
import ch.unibas.medizin.dynamicreports.report.definition.component.DRIDimensionComponent;
import ch.unibas.medizin.dynamicreports.report.definition.style.DRIReportStyle;
import ch.unibas.medizin.dynamicreports.report.definition.style.DRISimpleStyle;

import java.util.List;

/**
 * <p>DRICrosstab interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface DRICrosstab extends DRIDimensionComponent {

    /**
     * <p>getDataset.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.crosstab.DRICrosstabDataset} object.
     */
    DRICrosstabDataset getDataset();

    /**
     * <p>isRepeatColumnHeaders.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean isRepeatColumnHeaders();

    /**
     * <p>isRepeatRowHeaders.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean isRepeatRowHeaders();

    /**
     * <p>getColumnBreakOffset.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    Integer getColumnBreakOffset();

    /**
     * <p>getIgnoreWidth.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean getIgnoreWidth();

    /**
     * <p>getRunDirection.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.RunDirection} object.
     */
    RunDirection getRunDirection();

    /**
     * <p>getCellWidth.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    Integer getCellWidth();

    /**
     * <p>getCellHeight.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    Integer getCellHeight();

    /**
     * <p>getHighlightOddRows.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean getHighlightOddRows();

    /**
     * <p>getOddRowStyle.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.style.DRISimpleStyle} object.
     */
    DRISimpleStyle getOddRowStyle();

    /**
     * <p>getHighlightEvenRows.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean getHighlightEvenRows();

    /**
     * <p>getEvenRowStyle.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.style.DRISimpleStyle} object.
     */
    DRISimpleStyle getEvenRowStyle();

    /**
     * <p>getGroupStyle.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.style.DRIReportStyle} object.
     */
    DRIReportStyle getGroupStyle();

    /**
     * <p>getGroupTotalStyle.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.style.DRIReportStyle} object.
     */
    DRIReportStyle getGroupTotalStyle();

    /**
     * <p>getGrandTotalStyle.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.style.DRIReportStyle} object.
     */
    DRIReportStyle getGrandTotalStyle();

    /**
     * <p>getCellStyle.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.style.DRIReportStyle} object.
     */
    DRIReportStyle getCellStyle();

    /**
     * <p>getMeasureTitleStyle.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.style.DRIReportStyle} object.
     */
    DRIReportStyle getMeasureTitleStyle();

    /**
     * <p>getWhenNoDataCell.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.crosstab.DRICrosstabCellContent} object.
     */
    DRICrosstabCellContent getWhenNoDataCell();

    /**
     * <p>getHeaderCell.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.crosstab.DRICrosstabCellContent} object.
     */
    DRICrosstabCellContent getHeaderCell();

    /**
     * <p>getColumnGroups.</p>
     *
     * @return a {@link java.util.List} object.
     */
    List<DRICrosstabColumnGroup<?>> getColumnGroups();

    /**
     * <p>getRowGroups.</p>
     *
     * @return a {@link java.util.List} object.
     */
    List<DRICrosstabRowGroup<?>> getRowGroups();

    /**
     * <p>getVariables.</p>
     *
     * @return a {@link java.util.List} object.
     */
    List<DRICrosstabVariable<?>> getVariables();

    /**
     * <p>getMeasures.</p>
     *
     * @return a {@link java.util.List} object.
     */
    List<DRICrosstabMeasure<?>> getMeasures();
}
