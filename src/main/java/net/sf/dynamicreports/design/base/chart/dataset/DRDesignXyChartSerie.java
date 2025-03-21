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
package net.sf.dynamicreports.design.base.chart.dataset;

import net.sf.dynamicreports.design.definition.chart.dataset.DRIDesignXyChartSerie;
import net.sf.dynamicreports.design.definition.expression.DRIDesignExpression;
import net.sf.dynamicreports.report.constant.Constants;

/**
 * <p>DRDesignXyChartSerie class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DRDesignXyChartSerie extends DRDesignChartSerie implements DRIDesignXyChartSerie {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private DRIDesignExpression xValueExpression;
    private DRIDesignExpression yValueExpression;
    private DRIDesignExpression labelExpression;

    /** {@inheritDoc} */
    @Override
    public DRIDesignExpression getXValueExpression() {
        return xValueExpression;
    }

    /**
     * <p>Setter for the field <code>xValueExpression</code>.</p>
     *
     * @param xValueExpression a {@link net.sf.dynamicreports.design.definition.expression.DRIDesignExpression} object.
     */
    public void setXValueExpression(DRIDesignExpression xValueExpression) {
        this.xValueExpression = xValueExpression;
    }

    /** {@inheritDoc} */
    @Override
    public DRIDesignExpression getYValueExpression() {
        return yValueExpression;
    }

    /**
     * <p>Setter for the field <code>yValueExpression</code>.</p>
     *
     * @param yValueExpression a {@link net.sf.dynamicreports.design.definition.expression.DRIDesignExpression} object.
     */
    public void setYValueExpression(DRIDesignExpression yValueExpression) {
        this.yValueExpression = yValueExpression;
    }

    /** {@inheritDoc} */
    @Override
    public DRIDesignExpression getLabelExpression() {
        return labelExpression;
    }

    /**
     * <p>Setter for the field <code>labelExpression</code>.</p>
     *
     * @param labelExpression a {@link net.sf.dynamicreports.design.definition.expression.DRIDesignExpression} object.
     */
    public void setLabelExpression(DRIDesignExpression labelExpression) {
        this.labelExpression = labelExpression;
    }

}
