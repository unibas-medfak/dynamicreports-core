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
package net.sf.dynamicreports.design.definition;

import net.sf.dynamicreports.design.base.component.DRDesignList;
import net.sf.dynamicreports.design.definition.component.DRIDesignComponent;
import net.sf.dynamicreports.design.definition.expression.DRIDesignExpression;
import net.sf.dynamicreports.report.constant.SplitType;

import java.io.Serializable;

/**
 * <p>DRIDesignBand interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface DRIDesignBand extends Serializable {

    /**
     * <p>getName.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getName();

    /**
     * <p>getSplitType.</p>
     *
     * @return a {@link net.sf.dynamicreports.report.constant.SplitType} object.
     */
    public SplitType getSplitType();

    /**
     * <p>getBandComponent.</p>
     *
     * @return a {@link net.sf.dynamicreports.design.definition.component.DRIDesignComponent} object.
     */
    public DRIDesignComponent getBandComponent();

    /**
     * <p>getList.</p>
     *
     * @return a {@link net.sf.dynamicreports.design.base.component.DRDesignList} object.
     */
    public DRDesignList getList();

    /**
     * <p>getHeight.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getHeight();

    /**
     * <p>getPrintWhenExpression.</p>
     *
     * @return a {@link net.sf.dynamicreports.design.definition.expression.DRIDesignExpression} object.
     */
    public DRIDesignExpression getPrintWhenExpression();
}
