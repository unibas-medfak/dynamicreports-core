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

import ch.unibas.medizin.dynamicreports.report.base.component.DRPageXofY;
import ch.unibas.medizin.dynamicreports.report.builder.expression.SystemMessageExpression;
import ch.unibas.medizin.dynamicreports.report.constant.ComponentDimensionType;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;

import java.io.Serial;

/**
 * <p>PageXofYBuilder class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class PageXofYBuilder extends AbstractFormatFieldBuilder<PageXofYBuilder, DRPageXofY> {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for PageXofYBuilder.</p>
     */
    protected PageXofYBuilder() {
        super(new DRPageXofY());
    }

    /**
     * Sets the pageX component preferred width.
     *
     * @param width the pageX component preferred width >= 0
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.component.PageXofYBuilder} object.
     * @throws java.lang.IllegalArgumentException if <code>width</code> is &lt; 0
     * @see ch.unibas.medizin.dynamicreports.report.builder.Units
     */
    public PageXofYBuilder setPageXWidth(Integer width) {
        getObject().setPageXWidth(width);
        return this;
    }

    /**
     * Sets the pageX component fixed width.
     *
     * @param width the pageX component fixed width >= 0
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.component.PageXofYBuilder} object.
     * @throws java.lang.IllegalArgumentException if <code>width</code> is &lt; 0
     * @see ch.unibas.medizin.dynamicreports.report.builder.Units
     */
    public PageXofYBuilder setPageXFixedWidth(Integer width) {
        getObject().setPageXWidth(width);
        getObject().setPageXWidthType(ComponentDimensionType.FIXED);
        return this;
    }

    /**
     * Sets the pageX component minimum width.
     *
     * @param width the pageX component minimum width >= 0
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.component.PageXofYBuilder} object.
     * @throws java.lang.IllegalArgumentException if <code>width</code> is &lt; 0
     * @see ch.unibas.medizin.dynamicreports.report.builder.Units
     */
    public PageXofYBuilder setPageXMinWidth(Integer width) {
        getObject().setPageXWidth(width);
        getObject().setPageXWidthType(ComponentDimensionType.EXPAND);
        return this;
    }

    /**
     * Sets the pageY component preferred width.
     *
     * @param width the pageY component preferred width >= 0
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.component.PageXofYBuilder} object.
     * @throws java.lang.IllegalArgumentException if <code>width</code> is &lt; 0
     * @see ch.unibas.medizin.dynamicreports.report.builder.Units
     */
    public PageXofYBuilder setPageYWidth(Integer width) {
        getObject().setPageYWidth(width);
        return this;
    }

    /**
     * Sets the pageY component fixed width.
     *
     * @param width the pageY component fixed width >= 0
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.component.PageXofYBuilder} object.
     * @throws java.lang.IllegalArgumentException if <code>width</code> is &lt; 0
     * @see ch.unibas.medizin.dynamicreports.report.builder.Units
     */
    public PageXofYBuilder setPageYFixedWidth(Integer width) {
        getObject().setPageYWidth(width);
        getObject().setPageYWidthType(ComponentDimensionType.FIXED);
        return this;
    }

    /**
     * Sets the pageY component minimum width.
     *
     * @param width the pageY component minimum width >= 0
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.component.PageXofYBuilder} object.
     * @throws java.lang.IllegalArgumentException if <code>width</code> is &lt; 0
     * @see ch.unibas.medizin.dynamicreports.report.builder.Units
     */
    public PageXofYBuilder setPageYMinWidth(Integer width) {
        getObject().setPageYWidth(width);
        getObject().setPageYWidthType(ComponentDimensionType.EXPAND);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    protected void configure() {
        if (getObject().getFormatExpression() == null) {
            setFormatExpression(new SystemMessageExpression("page_x_of_y"));
        }
        super.configure();
    }
}
