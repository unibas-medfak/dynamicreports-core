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
package net.sf.dynamicreports.design.definition.chart.plot;

import net.sf.dynamicreports.design.definition.expression.DRIDesignExpression;
import net.sf.dynamicreports.design.definition.style.DRIDesignFont;

import java.awt.Color;
import java.io.Serializable;

/**
 * <p>DRIDesignAxisFormat interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface DRIDesignAxisFormat extends Serializable {

    /**
     * <p>getLabelExpression.</p>
     *
     * @return a {@link net.sf.dynamicreports.design.definition.expression.DRIDesignExpression} object.
     */
    public DRIDesignExpression getLabelExpression();

    /**
     * <p>getLabelFont.</p>
     *
     * @return a {@link net.sf.dynamicreports.design.definition.style.DRIDesignFont} object.
     */
    public DRIDesignFont getLabelFont();

    /**
     * <p>getLabelColor.</p>
     *
     * @return a {@link java.awt.Color} object.
     */
    public Color getLabelColor();

    /**
     * <p>getTickLabelFont.</p>
     *
     * @return a {@link net.sf.dynamicreports.design.definition.style.DRIDesignFont} object.
     */
    public DRIDesignFont getTickLabelFont();

    /**
     * <p>getTickLabelColor.</p>
     *
     * @return a {@link java.awt.Color} object.
     */
    public Color getTickLabelColor();

    /**
     * <p>getTickLabelMask.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getTickLabelMask();

    /**
     * <p>getVerticalTickLabels.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getVerticalTickLabels();

    /**
     * <p>getTickLabelRotation.</p>
     *
     * @return a {@link java.lang.Double} object.
     */
    public Double getTickLabelRotation();

    /**
     * <p>getLineColor.</p>
     *
     * @return a {@link java.awt.Color} object.
     */
    public Color getLineColor();

    /**
     * <p>getRangeMinValueExpression.</p>
     *
     * @return a {@link net.sf.dynamicreports.design.definition.expression.DRIDesignExpression} object.
     */
    public DRIDesignExpression getRangeMinValueExpression();

    /**
     * <p>getRangeMaxValueExpression.</p>
     *
     * @return a {@link net.sf.dynamicreports.design.definition.expression.DRIDesignExpression} object.
     */
    public DRIDesignExpression getRangeMaxValueExpression();
}
