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

import ch.unibas.medizin.dynamicreports.jasper.base.export.JasperDocxExporter;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;

import java.io.Serial;

/**
 * <p>JasperDocxExporterBuilder class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class JasperDocxExporterBuilder extends AbstractJasperExporterBuilder<JasperDocxExporterBuilder, JasperDocxExporter> {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for JasperDocxExporterBuilder.</p>
     */
    protected JasperDocxExporterBuilder() {
        super(new JasperDocxExporter());
    }

    /**
     * <p>setFramesAsNestedTables.</p>
     *
     * @param framesAsNestedTables a {@link java.lang.Boolean} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.export.JasperDocxExporterBuilder} object.
     */
    public JasperDocxExporterBuilder setFramesAsNestedTables(Boolean framesAsNestedTables) {
        this.getObject().setFramesAsNestedTables(framesAsNestedTables);
        return this;
    }

    /**
     * <p>setFlexibleRowHeight.</p>
     *
     * @param flexibleRowHeight a {@link java.lang.Boolean} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.export.JasperDocxExporterBuilder} object.
     */
    public JasperDocxExporterBuilder setFlexibleRowHeight(Boolean flexibleRowHeight) {
        this.getObject().setFlexibleRowHeight(flexibleRowHeight);
        return this;
    }

    /**
     * <p>setIgnoreHyperLink.</p>
     *
     * @param ignoreHyperLink a {@link java.lang.Boolean} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.export.JasperDocxExporterBuilder} object.
     */
    public JasperDocxExporterBuilder setIgnoreHyperLink(Boolean ignoreHyperLink) {
        this.getObject().setIgnoreHyperLink(ignoreHyperLink);
        return this;
    }
}
