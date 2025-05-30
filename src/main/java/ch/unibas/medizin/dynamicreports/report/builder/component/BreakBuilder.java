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
package ch.unibas.medizin.dynamicreports.report.builder.component;

import ch.unibas.medizin.dynamicreports.report.base.component.DRBreak;
import ch.unibas.medizin.dynamicreports.report.constant.BreakType;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;

import java.io.Serial;

/**
 * <p>BreakBuilder class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class BreakBuilder extends ComponentBuilder<BreakBuilder, DRBreak> {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for BreakBuilder.</p>
     */
    protected BreakBuilder() {
        super(new DRBreak());
    }

    /**
     * <p>setType.</p>
     *
     * @param breakType a {@link ch.unibas.medizin.dynamicreports.report.constant.BreakType} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.component.BreakBuilder} object.
     */
    public BreakBuilder setType(BreakType breakType) {
        getObject().setType(breakType);
        return this;
    }
}
