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

import ch.unibas.medizin.dynamicreports.report.base.component.DRComponent;
import ch.unibas.medizin.dynamicreports.report.base.component.DRList;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import ch.unibas.medizin.dynamicreports.report.constant.ListType;
import ch.unibas.medizin.dynamicreports.report.definition.crosstab.DRICrosstabCellContent;
import ch.unibas.medizin.dynamicreports.report.definition.style.DRIReportStyle;

import java.io.Serial;

/**
 * <p>DRCrosstabCellContent class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DRCrosstabCellContent implements DRICrosstabCellContent {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private final DRList list;
    private DRIReportStyle style;

    /**
     * <p>Constructor for DRCrosstabCellContent.</p>
     */
    public DRCrosstabCellContent() {
        this.list = new DRList(ListType.VERTICAL);
    }

    /** {@inheritDoc} */
    @Override
    public DRList getList() {
        return list;
    }

    /**
     * <p>addComponent.</p>
     *
     * @param component a {@link ch.unibas.medizin.dynamicreports.report.base.component.DRComponent} object.
     */
    public void addComponent(DRComponent component) {
        list.addComponent(component);
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
