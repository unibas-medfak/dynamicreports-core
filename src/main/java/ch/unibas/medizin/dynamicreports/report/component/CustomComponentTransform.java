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
package ch.unibas.medizin.dynamicreports.report.component;

import ch.unibas.medizin.dynamicreports.design.base.DRDesignGroup;
import ch.unibas.medizin.dynamicreports.design.constant.ResetType;
import ch.unibas.medizin.dynamicreports.design.definition.component.DRIDesignComponent;
import ch.unibas.medizin.dynamicreports.design.transformation.DesignTransformAccessor;
import ch.unibas.medizin.dynamicreports.jasper.transformation.JasperTransformAccessor;
import ch.unibas.medizin.dynamicreports.report.definition.component.DRIComponent;
import ch.unibas.medizin.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRComponentElement;

/**
 * <p>CustomComponentTransform interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface CustomComponentTransform<T extends DRIComponent, U extends DRIDesignComponent> {

    /**
     * <p>isTransform.</p>
     *
     * @param component a {@link java.lang.Object} object.
     * @return a boolean.
     */
    boolean isTransform(Object component);

    /**
     * <p>designComponent.</p>
     *
     * @param accessor   a {@link ch.unibas.medizin.dynamicreports.design.transformation.DesignTransformAccessor} object.
     * @param component  a T object.
     * @param resetType  a {@link ch.unibas.medizin.dynamicreports.design.constant.ResetType} object.
     * @param resetGroup a {@link ch.unibas.medizin.dynamicreports.design.base.DRDesignGroup} object.
     * @return a U object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    U designComponent(DesignTransformAccessor accessor, T component, ResetType resetType, DRDesignGroup resetGroup) throws DRException;

    /**
     * <p>jasperComponent.</p>
     *
     * @param accessor  a {@link ch.unibas.medizin.dynamicreports.jasper.transformation.JasperTransformAccessor} object.
     * @param component a U object.
     * @return a {@link net.sf.jasperreports.engine.JRComponentElement} object.
     */
    JRComponentElement jasperComponent(JasperTransformAccessor accessor, U component);

}
