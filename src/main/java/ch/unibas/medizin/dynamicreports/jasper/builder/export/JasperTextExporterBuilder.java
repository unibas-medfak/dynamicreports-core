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
package ch.unibas.medizin.dynamicreports.jasper.builder.export;

import ch.unibas.medizin.dynamicreports.jasper.base.export.JasperTextExporter;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;

import java.io.Serial;

/**
 * <p>JasperTextExporterBuilder class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class JasperTextExporterBuilder extends AbstractJasperExporterBuilder<JasperTextExporterBuilder, JasperTextExporter> {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for JasperTextExporterBuilder.</p>
     */
    protected JasperTextExporterBuilder() {
        super(new JasperTextExporter());
    }

    /**
     * <p>setCharacterWidth.</p>
     *
     * @param characterWidth a {@link java.lang.Float} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.export.JasperTextExporterBuilder} object.
     */
    public JasperTextExporterBuilder setCharacterWidth(Float characterWidth) {
        this.getObject().setCharacterWidth(characterWidth);
        return this;
    }

    /**
     * <p>setCharacterHeight.</p>
     *
     * @param characterHeight a {@link java.lang.Float} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.export.JasperTextExporterBuilder} object.
     */
    public JasperTextExporterBuilder setCharacterHeight(Float characterHeight) {
        this.getObject().setCharacterHeight(characterHeight);
        return this;
    }

    /**
     * <p>setPageWidthInChars.</p>
     *
     * @param pageWidth a {@link java.lang.Integer} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.export.JasperTextExporterBuilder} object.
     */
    public JasperTextExporterBuilder setPageWidthInChars(Integer pageWidth) {
        this.getObject().setPageWidthInChars(pageWidth);
        return this;
    }

    /**
     * <p>setPageHeightInChars.</p>
     *
     * @param pageHeight a {@link java.lang.Integer} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.export.JasperTextExporterBuilder} object.
     */
    public JasperTextExporterBuilder setPageHeightInChars(Integer pageHeight) {
        this.getObject().setPageHeightInChars(pageHeight);
        return this;
    }

    /**
     * <p>setPageSeparator.</p>
     *
     * @param pageSeparator a {@link java.lang.String} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.export.JasperTextExporterBuilder} object.
     */
    public JasperTextExporterBuilder setPageSeparator(String pageSeparator) {
        this.getObject().setPageSeparator(pageSeparator);
        return this;
    }

    /**
     * <p>setLineSeparator.</p>
     *
     * @param lineSeparator a {@link java.lang.String} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.export.JasperTextExporterBuilder} object.
     */
    public JasperTextExporterBuilder setLineSeparator(String lineSeparator) {
        this.getObject().setLineSeparator(lineSeparator);
        return this;
    }
}
