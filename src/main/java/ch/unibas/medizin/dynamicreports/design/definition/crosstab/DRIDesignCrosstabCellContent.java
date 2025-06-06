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
package ch.unibas.medizin.dynamicreports.design.definition.crosstab;

import ch.unibas.medizin.dynamicreports.design.definition.component.DRIDesignComponent;
import ch.unibas.medizin.dynamicreports.design.definition.style.DRIDesignStyle;

import java.io.Serializable;

/**
 * <p>DRIDesignCrosstabCellContent interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface DRIDesignCrosstabCellContent extends Serializable {

    /**
     * <p>getWidth.</p>
     *
     * @return an int.
     */
    int getWidth();

    /**
     * <p>getHeight.</p>
     *
     * @return an int.
     */
    int getHeight();

    /**
     * <p>getComponent.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.definition.component.DRIDesignComponent} object.
     */
    DRIDesignComponent getComponent();

    /**
     * <p>getStyle.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.definition.style.DRIDesignStyle} object.
     */
    DRIDesignStyle getStyle();
}
