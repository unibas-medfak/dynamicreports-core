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

import ch.unibas.medizin.dynamicreports.jasper.base.export.JasperImageExporter;
import ch.unibas.medizin.dynamicreports.jasper.constant.ImageType;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;

import java.io.Serial;

/**
 * <p>JasperImageExporterBuilder class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class JasperImageExporterBuilder extends AbstractJasperExporterBuilder<JasperImageExporterBuilder, JasperImageExporter> {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for JasperImageExporterBuilder.</p>
     */
    protected JasperImageExporterBuilder() {
        super(new JasperImageExporter());
    }

    /**
     * <p>setPageGap.</p>
     *
     * @param pageGap a {@link java.lang.Integer} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.export.JasperImageExporterBuilder} object.
     */
    public JasperImageExporterBuilder setPageGap(Integer pageGap) {
        this.getObject().setPageGap(pageGap);
        return this;
    }

    /**
     * <p>setImageType.</p>
     *
     * @param imageType a {@link ch.unibas.medizin.dynamicreports.jasper.constant.ImageType} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.export.JasperImageExporterBuilder} object.
     */
    public JasperImageExporterBuilder setImageType(ImageType imageType) {
        this.getObject().setImageType(imageType);
        return this;
    }

    /**
     * <p>setZoomRatio.</p>
     *
     * @param zoom a {@link java.lang.Float} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.export.JasperImageExporterBuilder} object.
     */
    public JasperImageExporterBuilder setZoomRatio(Float zoom) {
        this.getObject().setZoomRatio(zoom);
        return this;
    }
}
