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
package ch.unibas.medizin.dynamicreports.report.builder.column;

import ch.unibas.medizin.dynamicreports.report.base.column.DRColumn;
import ch.unibas.medizin.dynamicreports.report.base.component.DRComponent;
import ch.unibas.medizin.dynamicreports.report.base.component.DRDimensionComponent;
import ch.unibas.medizin.dynamicreports.report.builder.component.ComponentBuilder;
import ch.unibas.medizin.dynamicreports.report.constant.ComponentDimensionType;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import ch.unibas.medizin.dynamicreports.report.definition.component.DRIDimensionComponent;
import ch.unibas.medizin.dynamicreports.report.exception.DRReportException;

import java.io.Serial;

/**
 * It is used to display custom components (e.g. images or complex content) in columns.
 *
 * @author Ricardo Mariaca
 * 
 */
public class ComponentColumnBuilder extends ColumnBuilder<ComponentColumnBuilder, DRColumn<DRComponent>> {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for ComponentColumnBuilder.</p>
     *
     * @param component a {@link ch.unibas.medizin.dynamicreports.report.builder.component.ComponentBuilder} object.
     */
    protected ComponentColumnBuilder(ComponentBuilder<?, ?> component) {
        super(new DRColumn<>(component.getComponent()));
    }

    /**
     * Sets the preferred width of a column.
     *
     * @param width the column preferred width >= 0
     * @return a column builder
     * @throws java.lang.IllegalArgumentException if <code>width</code> is &lt; 0
     * @see ch.unibas.medizin.dynamicreports.report.builder.Units
     */
    public ComponentColumnBuilder setWidth(Integer width) {
        getDimensionComponent().setWidth(width);
        return this;
    }

    /**
     * Sets the fixed width of a column.
     *
     * @param width the column fixed width >= 0
     * @return a column builder
     * @throws java.lang.IllegalArgumentException if <code>width</code> is &lt; 0
     * @see ch.unibas.medizin.dynamicreports.report.builder.Units
     */
    public ComponentColumnBuilder setFixedWidth(Integer width) {
        getDimensionComponent().setWidth(width);
        getDimensionComponent().setWidthType(ComponentDimensionType.FIXED);
        return this;
    }

    /**
     * Sets the minimum width of a column.
     *
     * @param width the column minimum width >= 0
     * @return a column builder
     * @throws java.lang.IllegalArgumentException if <code>width</code> is &lt; 0
     * @see ch.unibas.medizin.dynamicreports.report.builder.Units
     */
    public ComponentColumnBuilder setMinWidth(Integer width) {
        getDimensionComponent().setWidth(width);
        getDimensionComponent().setWidthType(ComponentDimensionType.EXPAND);
        return this;
    }

    /**
     * Sets the preferred height of a column.
     *
     * @param height the column preferred height >= 0
     * @return a column builder
     * @throws java.lang.IllegalArgumentException if <code>height</code> is &lt; 0
     * @see ch.unibas.medizin.dynamicreports.report.builder.Units
     */
    public ComponentColumnBuilder setHeight(Integer height) {
        getDimensionComponent().setHeight(height);
        return this;
    }

    /**
     * Sets the fixed height of a column.
     *
     * @param height the column fixed height >= 0
     * @return a column builder
     * @throws java.lang.IllegalArgumentException if <code>height</code> is &lt; 0
     * @see ch.unibas.medizin.dynamicreports.report.builder.Units
     */
    public ComponentColumnBuilder setFixedHeight(Integer height) {
        getDimensionComponent().setHeight(height);
        getDimensionComponent().setHeightType(ComponentDimensionType.FIXED);
        return this;
    }

    /**
     * Sets the minimum height of a column.
     *
     * @param height the column minimum height >= 0
     * @return a column builder
     * @throws java.lang.IllegalArgumentException if <code>height</code> is &lt; 0
     * @see ch.unibas.medizin.dynamicreports.report.builder.Units
     */
    public ComponentColumnBuilder setMinHeight(Integer height) {
        getDimensionComponent().setHeight(height);
        getDimensionComponent().setHeightType(ComponentDimensionType.EXPAND);
        return this;
    }

    private DRDimensionComponent getDimensionComponent() {
        if (!(getObject().getComponent() instanceof DRIDimensionComponent)) {
            throw new DRReportException("Column component" + getObject().getComponent().getClass().getName() + "is not a dimension component.");
        }
        return (DRDimensionComponent) getObject().getComponent();
    }
}
