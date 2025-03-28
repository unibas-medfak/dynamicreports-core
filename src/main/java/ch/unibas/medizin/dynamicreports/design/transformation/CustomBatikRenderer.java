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
package ch.unibas.medizin.dynamicreports.design.transformation;

import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.renderers.SimpleDataRenderer;
import net.sf.jasperreports.renderers.WrappingSvgDataToGraphics2DRenderer;

import java.awt.Dimension;
import java.awt.geom.Dimension2D;
import java.io.Serial;
import java.net.URL;

/**
 * <p>CustomBatikRenderer class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class CustomBatikRenderer extends WrappingSvgDataToGraphics2DRenderer {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private final Dimension dimension;

    /**
     * <p>Constructor for CustomBatikRenderer.</p>
     *
     * @param svgURL a {@link java.net.URL} object.
     * @param width  an int.
     * @param height an int.
     * @throws net.sf.jasperreports.engine.JRException if any.
     */
    public CustomBatikRenderer(URL svgURL, int width, int height) throws JRException {
        super(new SimpleDataRenderer(JRLoader.loadBytes(svgURL), null));
        this.dimension = new Dimension(width, height);
    }

    /** {@inheritDoc} */
    @Override
    public Dimension2D getDimension(JasperReportsContext jasperReportsContext) {
        return dimension;
    }

}
