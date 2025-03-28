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
package ch.unibas.medizin.dynamicreports.design.definition.crosstab;

import ch.unibas.medizin.dynamicreports.design.definition.component.DRIDesignComponent;
import ch.unibas.medizin.dynamicreports.report.constant.RunDirection;

import java.util.List;

/**
 * <p>DRIDesignCrosstab interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface DRIDesignCrosstab extends DRIDesignComponent {

    /**
     * <p>getDataset.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.definition.crosstab.DRIDesignCrosstabDataset} object.
     */
    DRIDesignCrosstabDataset getDataset();

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
     * <p>getWhenNoDataCell.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.definition.crosstab.DRIDesignCrosstabCellContent} object.
     */
    DRIDesignCrosstabCellContent getWhenNoDataCell();

    /**
     * <p>getHeaderCell.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.definition.crosstab.DRIDesignCrosstabCellContent} object.
     */
    DRIDesignCrosstabCellContent getHeaderCell();

    /**
     * <p>getColumnGroups.</p>
     *
     * @return a {@link java.util.List} object.
     */
    List<? extends DRIDesignCrosstabColumnGroup> getColumnGroups();

    /**
     * <p>getRowGroups.</p>
     *
     * @return a {@link java.util.List} object.
     */
    List<? extends DRIDesignCrosstabRowGroup> getRowGroups();

    /**
     * <p>getCells.</p>
     *
     * @return a {@link java.util.List} object.
     */
    List<? extends DRIDesignCrosstabCell> getCells();

    /**
     * <p>getMeasures.</p>
     *
     * @return a {@link java.util.List} object.
     */
    List<DRIDesignCrosstabMeasure> getMeasures();
}
