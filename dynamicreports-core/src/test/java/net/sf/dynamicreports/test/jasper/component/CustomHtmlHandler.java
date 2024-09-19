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
package net.sf.dynamicreports.test.jasper.component;

import org.junit.jupiter.api.TestInstance;

import net.sf.jasperreports.engine.JRGenericPrintElement;
import net.sf.jasperreports.engine.export.GenericElementHtmlHandler;
import net.sf.jasperreports.engine.export.JRHtmlExporterContext;

/**
 * @author Ricardo Mariaca
 */
public class CustomHtmlHandler implements GenericElementHtmlHandler {

    @Override
    public boolean toExport(JRGenericPrintElement element) {
        return true;
    }

    @Override
    public String getHtmlFragment(JRHtmlExporterContext context, JRGenericPrintElement element) {
        String id = (String) element.getParameterValue("id");
        String data = (String) element.getParameterValue("data");
        StringBuilder script = new StringBuilder();
        script.append("<div id=\"").append(id).append("\">").append(data).append("</div>");
        return script.toString();
    }
}
