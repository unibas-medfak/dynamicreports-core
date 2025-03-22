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
import ch.unibas.medizin.dynamicreports.report.constant.HorizontalCellComponentAlignment;
import ch.unibas.medizin.dynamicreports.report.constant.ListType;
import ch.unibas.medizin.dynamicreports.report.constant.VerticalCellComponentAlignment;
import ch.unibas.medizin.dynamicreports.report.definition.grid.DRIColumnGridComponent;
import ch.unibas.medizin.dynamicreports.report.definition.grid.DRIColumnGridList;
import org.apache.commons.lang3.Validate;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>DRColumnGridList class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DRColumnGridList implements DRIColumnGridList {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private final List<DRColumnGridListCell> listCells;
    private ListType type;
    private int gap;

    /**
     * <p>Constructor for DRColumnGridList.</p>
     */
    public DRColumnGridList() {
        this(ListType.HORIZONTAL);
    }

    /**
     * <p>Constructor for DRColumnGridList.</p>
     *
     * @param type a {@link ch.unibas.medizin.dynamicreports.report.constant.ListType} object.
     */
    public DRColumnGridList(ListType type) {
        setType(type);
        this.listCells = new ArrayList<>();
    }

    /** {@inheritDoc} */
    @Override
    public List<DRColumnGridListCell> getListCells() {
        return listCells;
    }

    /**
     * <p>addComponent.</p>
     *
     * @param component a {@link ch.unibas.medizin.dynamicreports.report.definition.grid.DRIColumnGridComponent} object.
     */
    public void addComponent(DRIColumnGridComponent component) {
        listCells.add(new DRColumnGridListCell(component));
    }

    /**
     * <p>addCell.</p>
     *
     * @param cell a {@link ch.unibas.medizin.dynamicreports.report.base.grid.DRColumnGridListCell} object.
     */
    public void addCell(DRColumnGridListCell cell) {
        Validate.notNull(cell, "cell must not be null");
        listCells.add(cell);
    }

    /**
     * <p>addComponent.</p>
     *
     * @param horizontalAlignment a {@link ch.unibas.medizin.dynamicreports.report.constant.HorizontalCellComponentAlignment} object.
     * @param verticalAlignment   a {@link ch.unibas.medizin.dynamicreports.report.constant.VerticalCellComponentAlignment} object.
     * @param component           a {@link ch.unibas.medizin.dynamicreports.report.definition.grid.DRIColumnGridComponent} object.
     */
    public void addComponent(HorizontalCellComponentAlignment horizontalAlignment, VerticalCellComponentAlignment verticalAlignment, DRIColumnGridComponent component) {
        listCells.add(new DRColumnGridListCell(horizontalAlignment, verticalAlignment, component));
    }

    /** {@inheritDoc} */
    @Override
    public ListType getType() {
        return type;
    }

    /**
     * <p>Setter for the field <code>type</code>.</p>
     *
     * @param type a {@link ch.unibas.medizin.dynamicreports.report.constant.ListType} object.
     */
    public void setType(ListType type) {
        Validate.notNull(type, "type must not be null");
        this.type = type;
    }

    /** {@inheritDoc} */
    @Override
    public int getGap() {
        return gap;
    }

    /**
     * <p>Setter for the field <code>gap</code>.</p>
     *
     * @param gap a int.
     */
    public void setGap(int gap) {
        Validate.notNull(gap < 0, "gap must be >= 0");
        this.gap = gap;
    }
}
