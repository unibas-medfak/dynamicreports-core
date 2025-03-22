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
package ch.unibas.medizin.dynamicreports.design.definition.style;

import ch.unibas.medizin.dynamicreports.report.constant.HorizontalImageAlignment;
import ch.unibas.medizin.dynamicreports.report.constant.HorizontalTextAlignment;
import ch.unibas.medizin.dynamicreports.report.constant.ImageScale;
import ch.unibas.medizin.dynamicreports.report.constant.Markup;
import ch.unibas.medizin.dynamicreports.report.constant.Rotation;
import ch.unibas.medizin.dynamicreports.report.constant.VerticalImageAlignment;
import ch.unibas.medizin.dynamicreports.report.constant.VerticalTextAlignment;

import java.awt.Color;
import java.io.Serializable;

/**
 * <p>DRIDesignBaseStyle interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface DRIDesignBaseStyle extends Serializable {

    /**
     * <p>getForegroundColor.</p>
     *
     * @return a {@link java.awt.Color} object.
     */
    Color getForegroundColor();

    /**
     * <p>getBackgroundColor.</p>
     *
     * @return a {@link java.awt.Color} object.
     */
    Color getBackgroundColor();

    /**
     * <p>getRadius.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    Integer getRadius();

    /**
     * <p>getImageScale.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.ImageScale} object.
     */
    ImageScale getImageScale();

    /**
     * <p>getHorizontalTextAlignment.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.HorizontalTextAlignment} object.
     */
    HorizontalTextAlignment getHorizontalTextAlignment();

    /**
     * <p>getVerticalTextAlignment.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.VerticalTextAlignment} object.
     */
    VerticalTextAlignment getVerticalTextAlignment();

    /**
     * <p>getHorizontalImageAlignment.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.HorizontalImageAlignment} object.
     */
    HorizontalImageAlignment getHorizontalImageAlignment();

    /**
     * <p>getVerticalImageAlignment.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.VerticalImageAlignment} object.
     */
    VerticalImageAlignment getVerticalImageAlignment();

    /**
     * <p>getBorder.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.definition.style.DRIDesignBorder} object.
     */
    DRIDesignBorder getBorder();

    /**
     * <p>getPadding.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.definition.style.DRIDesignPadding} object.
     */
    DRIDesignPadding getPadding();

    /**
     * <p>getFont.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.definition.style.DRIDesignFont} object.
     */
    DRIDesignFont getFont();

    /**
     * <p>getRotation.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.Rotation} object.
     */
    Rotation getRotation();

    /**
     * <p>getPattern.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    String getPattern();

    /**
     * <p>getMarkup.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.Markup} object.
     */
    Markup getMarkup();

    /**
     * <p>getParagraph.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.definition.style.DRIDesignParagraph} object.
     */
    DRIDesignParagraph getParagraph();

    /**
     * <p>getLinePen.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.definition.style.DRIDesignPen} object.
     */
    DRIDesignPen getLinePen();

}
