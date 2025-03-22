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
package ch.unibas.medizin.dynamicreports.jasper.definition.export;

import java.io.File;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.Writer;

/**
 * <p>JasperIExporter interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface JasperIExporter extends Serializable {

    /**
     * <p>getOutputWriter.</p>
     *
     * @return a {@link java.io.Writer} object.
     */
    Writer getOutputWriter();

    /**
     * <p>getOutputStream.</p>
     *
     * @return a {@link java.io.OutputStream} object.
     */
    OutputStream getOutputStream();

    /**
     * <p>getOutputFile.</p>
     *
     * @return a {@link java.io.File} object.
     */
    File getOutputFile();

    /**
     * <p>getOutputFileName.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    String getOutputFileName();

    /**
     * <p>getPageIndex.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    Integer getPageIndex();

    /**
     * <p>getStartPageIndex.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    Integer getStartPageIndex();

    /**
     * <p>getEndPageIndex.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    Integer getEndPageIndex();

    /**
     * <p>getCharacterEncoding.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    String getCharacterEncoding();

    /**
     * <p>getOffsetX.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    Integer getOffsetX();

    /**
     * <p>getOffsetY.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    Integer getOffsetY();

}
