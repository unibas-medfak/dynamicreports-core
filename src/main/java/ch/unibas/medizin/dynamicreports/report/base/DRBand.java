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
package ch.unibas.medizin.dynamicreports.report.base;

import ch.unibas.medizin.dynamicreports.report.base.component.DRComponent;
import ch.unibas.medizin.dynamicreports.report.base.component.DRList;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import ch.unibas.medizin.dynamicreports.report.constant.ListType;
import ch.unibas.medizin.dynamicreports.report.constant.SplitType;
import ch.unibas.medizin.dynamicreports.report.definition.DRIBand;
import ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression;

import java.io.Serial;

/**
 * <p>DRBand class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DRBand implements DRIBand {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private SplitType splitType;
    private final DRList list;
    private DRIExpression<Boolean> printWhenExpression;

    /**
     * <p>Constructor for DRBand.</p>
     */
    public DRBand() {
        this.list = new DRList(ListType.VERTICAL);
    }

    /** {@inheritDoc} */
    @Override
    public SplitType getSplitType() {
        return splitType;
    }

    /**
     * <p>Setter for the field <code>splitType</code>.</p>
     *
     * @param splitType a {@link ch.unibas.medizin.dynamicreports.report.constant.SplitType} object.
     */
    public void setSplitType(SplitType splitType) {
        this.splitType = splitType;
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
    public DRIExpression<Boolean> getPrintWhenExpression() {
        return printWhenExpression;
    }

    /**
     * <p>Setter for the field <code>printWhenExpression</code>.</p>
     *
     * @param printWhenExpression a {@link ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression} object.
     */
    public void setPrintWhenExpression(DRIExpression<Boolean> printWhenExpression) {
        this.printWhenExpression = printWhenExpression;
    }
}
