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
package ch.unibas.medizin.dynamicreports.report.definition.component;

import ch.unibas.medizin.dynamicreports.report.constant.BooleanComponentType;
import ch.unibas.medizin.dynamicreports.report.constant.HorizontalImageAlignment;
import ch.unibas.medizin.dynamicreports.report.constant.HorizontalTextAlignment;
import ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression;

/**
 * <p>DRIBooleanField interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface DRIBooleanField extends DRIHyperLinkComponent {

    /**
     * <p>getValueExpression.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression} object.
     */
    DRIExpression<Boolean> getValueExpression();

    /**
     * <p>getComponentType.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.BooleanComponentType} object.
     */
    BooleanComponentType getComponentType();

    /**
     * <p>getEmptyWhenNullValue.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean getEmptyWhenNullValue();

    /**
     * <p>getImageWidth.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    Integer getImageWidth();

    /**
     * <p>getImageHeight.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    Integer getImageHeight();

    /**
     * <p>getHorizontalImageAlignment.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.HorizontalImageAlignment} object.
     */
    HorizontalImageAlignment getHorizontalImageAlignment();

    /**
     * <p>getHorizontalTextAlignment.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.HorizontalTextAlignment} object.
     */
    HorizontalTextAlignment getHorizontalTextAlignment();
}
