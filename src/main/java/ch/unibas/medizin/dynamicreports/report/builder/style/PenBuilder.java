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
package ch.unibas.medizin.dynamicreports.report.builder.style;

import ch.unibas.medizin.dynamicreports.report.base.style.DRPen;
import ch.unibas.medizin.dynamicreports.report.builder.AbstractBuilder;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import ch.unibas.medizin.dynamicreports.report.constant.LineStyle;

import java.awt.Color;
import java.io.Serial;

/**
 * <p>PenBuilder class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class PenBuilder extends AbstractBuilder<PenBuilder, DRPen> {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for PenBuilder.</p>
     */
    protected PenBuilder() {
        super(new DRPen());
    }

    /**
     * <p>Constructor for PenBuilder.</p>
     *
     * @param lineWidth a {@link java.lang.Float} object.
     * @param lineStyle a {@link ch.unibas.medizin.dynamicreports.report.constant.LineStyle} object.
     */
    protected PenBuilder(Float lineWidth, LineStyle lineStyle) {
        super(new DRPen(lineWidth, lineStyle));
    }

    /**
     * <p>setLineWidth.</p>
     *
     * @param lineWidth a {@link java.lang.Float} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.style.PenBuilder} object.
     */
    public PenBuilder setLineWidth(Float lineWidth) {
        getObject().setLineWidth(lineWidth);
        return this;
    }

    /**
     * <p>setLineStyle.</p>
     *
     * @param lineStyle a {@link ch.unibas.medizin.dynamicreports.report.constant.LineStyle} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.style.PenBuilder} object.
     */
    public PenBuilder setLineStyle(LineStyle lineStyle) {
        getObject().setLineStyle(lineStyle);
        return this;
    }

    /**
     * <p>setLineColor.</p>
     *
     * @param lineColor a {@link java.awt.Color} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.style.PenBuilder} object.
     */
    public PenBuilder setLineColor(Color lineColor) {
        getObject().setLineColor(lineColor);
        return this;
    }

    /**
     * <p>getPen.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.base.style.DRPen} object.
     */
    public DRPen getPen() {
        return build();
    }
}
