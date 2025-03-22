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
package ch.unibas.medizin.dynamicreports.report.definition.style;

import java.io.Serializable;

/**
 * <p>DRIFont interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface DRIFont extends Serializable {

    /**
     * <p>getFontName.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    String getFontName();

    /**
     * <p>getBold.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean getBold();

    /**
     * <p>getItalic.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean getItalic();

    /**
     * <p>getUnderline.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean getUnderline();

    /**
     * <p>getStrikeThrough.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean getStrikeThrough();

    /**
     * <p>getFontSize.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    Integer getFontSize();

    /**
     * <p>getPdfFontName.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    String getPdfFontName();

    /**
     * <p>getPdfEncoding.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    String getPdfEncoding();

    /**
     * <p>getPdfEmbedded.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean getPdfEmbedded();
}
