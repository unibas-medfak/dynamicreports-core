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
package ch.unibas.medizin.dynamicreports.report.definition;

import ch.unibas.medizin.dynamicreports.jasper.base.tableofcontents.JasperTocHeading;

import java.io.Serializable;
import java.util.Map;

/**
 * <p>DRICustomValues interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface DRICustomValues extends Serializable {
    /**
     * Constant <code>NAME="CUSTOM_VALUES"</code>
     */
    String NAME = "CUSTOM_VALUES";

    /**
     * <p>setSystemValue.</p>
     *
     * @param name  a {@link java.lang.String} object.
     * @param value a {@link java.lang.Object} object.
     */
    void setSystemValue(String name, Object value);

    /**
     * <p>addTocHeading.</p>
     *
     * @param level       an int.
     * @param id          a {@link java.lang.String} object.
     * @param text        a {@link java.lang.String} object.
     * @param customValue a {@link java.lang.Object} object.
     */
    void addTocHeading(int level, String id, String text, Object customValue);

    /**
     * <p>getTocHeadings.</p>
     *
     * @return a {@link java.util.Map} object.
     */
    Map<String, JasperTocHeading> getTocHeadings();

    /**
     * <p>setTocHeadings.</p>
     *
     * @param tocHeadings a {@link java.util.Map} object.
     */
    void setTocHeadings(Map<String, JasperTocHeading> tocHeadings);

    /**
     * <p>setSubreportWidth.</p>
     *
     * @param subreportWidth a {@link java.lang.Integer} object.
     */
    void setSubreportWidth(Integer subreportWidth);
}
