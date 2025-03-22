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
package ch.unibas.medizin.dynamicreports.report.base.crosstab;

import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import ch.unibas.medizin.dynamicreports.report.definition.crosstab.DRICrosstabCellStyle;
import ch.unibas.medizin.dynamicreports.report.definition.crosstab.DRICrosstabColumnGroup;
import ch.unibas.medizin.dynamicreports.report.definition.crosstab.DRICrosstabRowGroup;
import ch.unibas.medizin.dynamicreports.report.definition.style.DRIReportStyle;

import java.io.Serial;

/**
 * <p>DRCrosstabCellStyle class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DRCrosstabCellStyle implements DRICrosstabCellStyle {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private DRICrosstabRowGroup<?> rowGroup;
    private DRICrosstabColumnGroup<?> columnGroup;
    private DRIReportStyle style;

    /**
     * <p>Constructor for DRCrosstabCellStyle.</p>
     *
     * @param style a {@link ch.unibas.medizin.dynamicreports.report.definition.style.DRIReportStyle} object.
     */
    public DRCrosstabCellStyle(DRIReportStyle style) {
        this(style, null, null);
    }

    /**
     * <p>Constructor for DRCrosstabCellStyle.</p>
     *
     * @param style       a {@link ch.unibas.medizin.dynamicreports.report.definition.style.DRIReportStyle} object.
     * @param rowGroup    a {@link ch.unibas.medizin.dynamicreports.report.definition.crosstab.DRICrosstabRowGroup} object.
     * @param columnGroup a {@link ch.unibas.medizin.dynamicreports.report.definition.crosstab.DRICrosstabColumnGroup} object.
     */
    public DRCrosstabCellStyle(DRIReportStyle style, DRICrosstabRowGroup<?> rowGroup, DRICrosstabColumnGroup<?> columnGroup) {
        this.style = style;
        this.rowGroup = rowGroup;
        this.columnGroup = columnGroup;
    }

    /** {@inheritDoc} */
    @Override
    public DRICrosstabRowGroup<?> getRowGroup() {
        return rowGroup;
    }

    /**
     * <p>Setter for the field <code>rowGroup</code>.</p>
     *
     * @param rowGroup a {@link ch.unibas.medizin.dynamicreports.report.definition.crosstab.DRICrosstabRowGroup} object.
     */
    public void setRowGroup(DRICrosstabRowGroup<?> rowGroup) {
        this.rowGroup = rowGroup;
    }

    /** {@inheritDoc} */
    @Override
    public DRICrosstabColumnGroup<?> getColumnGroup() {
        return columnGroup;
    }

    /**
     * <p>Setter for the field <code>columnGroup</code>.</p>
     *
     * @param columnGroup a {@link ch.unibas.medizin.dynamicreports.report.definition.crosstab.DRICrosstabColumnGroup} object.
     */
    public void setColumnGroup(DRICrosstabColumnGroup<?> columnGroup) {
        this.columnGroup = columnGroup;
    }

    /** {@inheritDoc} */
    @Override
    public DRIReportStyle getStyle() {
        return style;
    }

    /**
     * <p>Setter for the field <code>style</code>.</p>
     *
     * @param style a {@link ch.unibas.medizin.dynamicreports.report.definition.style.DRIReportStyle} object.
     */
    public void setStyle(DRIReportStyle style) {
        this.style = style;
    }
}
