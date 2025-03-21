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

import ch.unibas.medizin.dynamicreports.report.constant.ComponentDimensionType;
import ch.unibas.medizin.dynamicreports.report.constant.Position;
import ch.unibas.medizin.dynamicreports.report.constant.SubtotalPosition;
import ch.unibas.medizin.dynamicreports.report.definition.column.DRIColumn;
import ch.unibas.medizin.dynamicreports.report.definition.component.DRITextField;
import ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression;
import ch.unibas.medizin.dynamicreports.report.definition.style.DRIReportStyle;

/**
 * <p>DRISubtotal interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface DRISubtotal<T> extends DRIExpression<T>, DRIValue<T> {

    /**
     * <p>getShowInColumn.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.column.DRIColumn} object.
     */
    public DRIColumn<?> getShowInColumn();

    /**
     * <p>getValueField.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.component.DRITextField} object.
     */
    public DRITextField<T> getValueField();

    /**
     * <p>getLabelExpression.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression} object.
     */
    public DRIExpression<?> getLabelExpression();

    /**
     * <p>getLabelStyle.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.style.DRIReportStyle} object.
     */
    public DRIReportStyle getLabelStyle();

    /**
     * <p>getLabelPosition.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.Position} object.
     */
    public Position getLabelPosition();

    /**
     * <p>getLabelWidth.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getLabelWidth();

    /**
     * <p>getLabelWidthType.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.ComponentDimensionType} object.
     */
    public ComponentDimensionType getLabelWidthType();

    /**
     * <p>getPosition.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.SubtotalPosition} object.
     */
    public SubtotalPosition getPosition();

    /**
     * <p>getGroup.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.DRIGroup} object.
     */
    public DRIGroup getGroup();
}
