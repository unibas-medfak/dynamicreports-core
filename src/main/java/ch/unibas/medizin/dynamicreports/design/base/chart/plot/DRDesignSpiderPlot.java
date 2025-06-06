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
package ch.unibas.medizin.dynamicreports.design.base.chart.plot;

import ch.unibas.medizin.dynamicreports.design.definition.chart.plot.DRIDesignSpiderPlot;
import ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignExpression;
import ch.unibas.medizin.dynamicreports.design.definition.style.DRIDesignFont;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import ch.unibas.medizin.dynamicreports.report.constant.SpiderRotation;
import ch.unibas.medizin.dynamicreports.report.constant.TableOrder;

import java.awt.Color;
import java.io.Serial;

/**
 * <p>DRDesignSpiderPlot class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DRDesignSpiderPlot implements DRIDesignSpiderPlot {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private DRIDesignExpression maxValueExpression;
    private SpiderRotation rotation;
    private TableOrder tableOrder;
    private Boolean webFilled;
    private Double startAngle;
    private Double headPercent;
    private Double interiorGap;
    private Color axisLineColor;
    private Float axisLineWidth;
    private DRIDesignFont labelFont;
    private Double labelGap;
    private Color labelColor;

    /** {@inheritDoc} */
    @Override
    public DRIDesignExpression getMaxValueExpression() {
        return maxValueExpression;
    }

    /**
     * <p>Setter for the field <code>maxValueExpression</code>.</p>
     *
     * @param maxValueExpression a {@link ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignExpression} object.
     */
    public void setMaxValueExpression(DRIDesignExpression maxValueExpression) {
        this.maxValueExpression = maxValueExpression;
    }

    /** {@inheritDoc} */
    @Override
    public SpiderRotation getRotation() {
        return rotation;
    }

    /**
     * <p>Setter for the field <code>rotation</code>.</p>
     *
     * @param rotation a {@link ch.unibas.medizin.dynamicreports.report.constant.SpiderRotation} object.
     */
    public void setRotation(SpiderRotation rotation) {
        this.rotation = rotation;
    }

    /** {@inheritDoc} */
    @Override
    public TableOrder getTableOrder() {
        return tableOrder;
    }

    /**
     * <p>Setter for the field <code>tableOrder</code>.</p>
     *
     * @param tableOrder a {@link ch.unibas.medizin.dynamicreports.report.constant.TableOrder} object.
     */
    public void setTableOrder(TableOrder tableOrder) {
        this.tableOrder = tableOrder;
    }

    /** {@inheritDoc} */
    @Override
    public Boolean getWebFilled() {
        return webFilled;
    }

    /**
     * <p>Setter for the field <code>webFilled</code>.</p>
     *
     * @param webFilled a {@link java.lang.Boolean} object.
     */
    public void setWebFilled(Boolean webFilled) {
        this.webFilled = webFilled;
    }

    /** {@inheritDoc} */
    @Override
    public Double getStartAngle() {
        return startAngle;
    }

    /**
     * <p>Setter for the field <code>startAngle</code>.</p>
     *
     * @param startAngle a {@link java.lang.Double} object.
     */
    public void setStartAngle(Double startAngle) {
        this.startAngle = startAngle;
    }

    /** {@inheritDoc} */
    @Override
    public Double getHeadPercent() {
        return headPercent;
    }

    /**
     * <p>Setter for the field <code>headPercent</code>.</p>
     *
     * @param headPercent a {@link java.lang.Double} object.
     */
    public void setHeadPercent(Double headPercent) {
        this.headPercent = headPercent;
    }

    /** {@inheritDoc} */
    @Override
    public Double getInteriorGap() {
        return interiorGap;
    }

    /**
     * <p>Setter for the field <code>interiorGap</code>.</p>
     *
     * @param interiorGap a {@link java.lang.Double} object.
     */
    public void setInteriorGap(Double interiorGap) {
        this.interiorGap = interiorGap;
    }

    /** {@inheritDoc} */
    @Override
    public Color getAxisLineColor() {
        return axisLineColor;
    }

    /**
     * <p>Setter for the field <code>axisLineColor</code>.</p>
     *
     * @param axisLineColor a {@link java.awt.Color} object.
     */
    public void setAxisLineColor(Color axisLineColor) {
        this.axisLineColor = axisLineColor;
    }

    /** {@inheritDoc} */
    @Override
    public Float getAxisLineWidth() {
        return axisLineWidth;
    }

    /**
     * <p>Setter for the field <code>axisLineWidth</code>.</p>
     *
     * @param axisLineWidth a {@link java.lang.Float} object.
     */
    public void setAxisLineWidth(Float axisLineWidth) {
        this.axisLineWidth = axisLineWidth;
    }

    /** {@inheritDoc} */
    @Override
    public DRIDesignFont getLabelFont() {
        return labelFont;
    }

    /**
     * <p>Setter for the field <code>labelFont</code>.</p>
     *
     * @param labelFont a {@link ch.unibas.medizin.dynamicreports.design.definition.style.DRIDesignFont} object.
     */
    public void setLabelFont(DRIDesignFont labelFont) {
        this.labelFont = labelFont;
    }

    /** {@inheritDoc} */
    @Override
    public Double getLabelGap() {
        return labelGap;
    }

    /**
     * <p>Setter for the field <code>labelGap</code>.</p>
     *
     * @param labelGap a {@link java.lang.Double} object.
     */
    public void setLabelGap(Double labelGap) {
        this.labelGap = labelGap;
    }

    /** {@inheritDoc} */
    @Override
    public Color getLabelColor() {
        return labelColor;
    }

    /**
     * <p>Setter for the field <code>labelColor</code>.</p>
     *
     * @param labelColor a {@link java.awt.Color} object.
     */
    public void setLabelColor(Color labelColor) {
        this.labelColor = labelColor;
    }
}
