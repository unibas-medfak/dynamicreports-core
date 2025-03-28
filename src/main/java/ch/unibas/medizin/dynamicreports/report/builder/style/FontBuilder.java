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

import ch.unibas.medizin.dynamicreports.report.base.style.DRFont;
import ch.unibas.medizin.dynamicreports.report.builder.AbstractBuilder;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;

import java.io.Serial;

/**
 * <p>FontBuilder class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class FontBuilder extends AbstractBuilder<FontBuilder, DRFont> {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for FontBuilder.</p>
     */
    protected FontBuilder() {
        super(new DRFont());
    }

    /**
     * <p>Constructor for FontBuilder.</p>
     *
     * @param fontName a {@link java.lang.String} object.
     * @param bold     a {@link java.lang.Boolean} object.
     * @param italic   a {@link java.lang.Boolean} object.
     * @param fontSize a {@link java.lang.Integer} object.
     */
    protected FontBuilder(String fontName, Boolean bold, Boolean italic, Integer fontSize) {
        super(new DRFont(fontName, bold, italic, fontSize));
    }

    /**
     * <p>setFontName.</p>
     *
     * @param fontName a {@link java.lang.String} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.style.FontBuilder} object.
     */
    public FontBuilder setFontName(String fontName) {
        getObject().setFontName(fontName);
        return this;
    }

    /**
     * <p>setFontSize.</p>
     *
     * @param fontSize a {@link java.lang.Integer} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.style.FontBuilder} object.
     */
    public FontBuilder setFontSize(Integer fontSize) {
        getObject().setFontSize(fontSize);
        return this;
    }

    /**
     * <p>bold.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.style.FontBuilder} object.
     */
    public FontBuilder bold() {
        return setBold(true);
    }

    /**
     * <p>setBold.</p>
     *
     * @param bold a {@link java.lang.Boolean} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.style.FontBuilder} object.
     */
    public FontBuilder setBold(Boolean bold) {
        getObject().setBold(bold);
        return this;
    }

    /**
     * <p>italic.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.style.FontBuilder} object.
     */
    public FontBuilder italic() {
        return setItalic(true);
    }

    /**
     * <p>setItalic.</p>
     *
     * @param italic a {@link java.lang.Boolean} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.style.FontBuilder} object.
     */
    public FontBuilder setItalic(Boolean italic) {
        getObject().setItalic(italic);
        return this;
    }

    /**
     * <p>boldItalic.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.style.FontBuilder} object.
     */
    public FontBuilder boldItalic() {
        setBold(true);
        return setItalic(true);
    }

    /**
     * <p>strikeThrough.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.style.FontBuilder} object.
     */
    public FontBuilder strikeThrough() {
        return setStrikeThrough(true);
    }

    /**
     * <p>setStrikeThrough.</p>
     *
     * @param strikeThrough a {@link java.lang.Boolean} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.style.FontBuilder} object.
     */
    public FontBuilder setStrikeThrough(Boolean strikeThrough) {
        getObject().setStrikeThrough(strikeThrough);
        return this;
    }

    /**
     * <p>underline.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.style.FontBuilder} object.
     */
    public FontBuilder underline() {
        return setUnderline(true);
    }

    /**
     * <p>setUnderline.</p>
     *
     * @param underline a {@link java.lang.Boolean} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.style.FontBuilder} object.
     */
    public FontBuilder setUnderline(Boolean underline) {
        getObject().setUnderline(underline);
        return this;
    }

    /**
     * <p>getFont.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.base.style.DRFont} object.
     */
    public DRFont getFont() {
        return build();
    }
}
