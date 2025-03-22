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
package ch.unibas.medizin.dynamicreports.jasper.base.export;

import ch.unibas.medizin.dynamicreports.jasper.definition.export.JasperIXlsxExporter;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;

import java.io.Serial;

/**
 * <p>JasperXlsxExporter class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class JasperXlsxExporter extends AbstractJasperExcelExporter implements JasperIXlsxExporter {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private String macroTemplate;

    /** {@inheritDoc} */
    @Override
    public String getMacroTemplate() {
        return macroTemplate;
    }

    /**
     * <p>Setter for the field <code>macroTemplate</code>.</p>
     *
     * @param macroTemplate a {@link java.lang.String} object.
     */
    public void setMacroTemplate(String macroTemplate) {
        this.macroTemplate = macroTemplate;
    }

}
