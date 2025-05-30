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
package ch.unibas.medizin.dynamicreports.report.base;

import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import ch.unibas.medizin.dynamicreports.report.definition.DRIQuery;
import org.apache.commons.lang3.Validate;

import java.io.Serial;

/**
 * <p>DRQuery class.</p>
 *
 * @author Ricardo Mariaca
 */
public record DRQuery(String text, String language) implements DRIQuery {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for DRQuery.</p>
     *
     * @param text     a {@link String} object.
     * @param language a {@link String} object.
     */
    public DRQuery {
        Validate.notNull(text, "text must not be null");
        Validate.notNull(language, "language must not be null");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String text() {
        return text;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String language() {
        return language;
    }
}
