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
package ch.unibas.medizin.dynamicreports.report.base.grid;

import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import ch.unibas.medizin.dynamicreports.report.constant.ListType;
import ch.unibas.medizin.dynamicreports.report.definition.grid.DRIColumnGrid;
import ch.unibas.medizin.dynamicreports.report.definition.grid.DRIColumnGridComponent;

/**
 * <p>DRColumnGrid class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DRColumnGrid implements DRIColumnGrid {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private final DRColumnGridList list;

    /**
     * <p>Constructor for DRColumnGrid.</p>
     */
    public DRColumnGrid() {
        this(ListType.HORIZONTAL);
    }

    /**
     * <p>Constructor for DRColumnGrid.</p>
     *
     * @param type a {@link ch.unibas.medizin.dynamicreports.report.constant.ListType} object.
     */
    public DRColumnGrid(ListType type) {
        this.list = new DRColumnGridList(type);
    }

    /** {@inheritDoc} */
    @Override
    public DRColumnGridList getList() {
        return list;
    }

    /**
     * <p>addComponent.</p>
     *
     * @param component a {@link ch.unibas.medizin.dynamicreports.report.definition.grid.DRIColumnGridComponent} object.
     */
    public void addComponent(DRIColumnGridComponent component) {
        list.addComponent(component);
    }
}
