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
package ch.unibas.medizin.dynamicreports.report.base.column;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.Validate;

import ch.unibas.medizin.dynamicreports.report.ReportUtils;
import ch.unibas.medizin.dynamicreports.report.constant.ComponentDimensionType;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import ch.unibas.medizin.dynamicreports.report.constant.TextAdjust;
import ch.unibas.medizin.dynamicreports.report.definition.column.DRIColumn;
import ch.unibas.medizin.dynamicreports.report.definition.component.DRIComponent;
import ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression;
import ch.unibas.medizin.dynamicreports.report.definition.expression.DRIPropertyExpression;
import ch.unibas.medizin.dynamicreports.report.definition.style.DRIReportStyle;

/**
 * <p>DRColumn class.</p>
 *
 * @author Ricardo Mariaca, Jan Moxter
 * 
 */
public class DRColumn<T extends DRIComponent> implements DRIColumn<T> {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private final String name;
    private T component;

    private DRIExpression<?> titleExpression;
    private DRIReportStyle titleStyle;
    private Integer titleHeight;
    private ComponentDimensionType titleHeightType;
    private Integer titleRows;
    private TextAdjust titleTextAdjust;
    private List<DRIPropertyExpression> titlePropertyExpressions;

    /**
     * <p>Constructor for DRColumn.</p>
     */
    protected DRColumn() {
        this.name = ReportUtils.generateUniqueName("column");
        titlePropertyExpressions = new ArrayList<>();
    }

    /**
     * <p>Constructor for DRColumn.</p>
     *
     * @param component a T object.
     */
    public DRColumn(final T component) {
        Validate.notNull(component, "component must not be null");
        this.name = ReportUtils.generateUniqueName("column");
        this.component = component;
        titlePropertyExpressions = new ArrayList<>();
    }

    /** {@inheritDoc} */
    @Override
    public T getComponent() {
        return component;
    }

    /** {@inheritDoc} */
    @Override
    public DRIExpression<?> getTitleExpression() {
        return titleExpression;
    }

    /**
     * <p>Setter for the field <code>titleExpression</code>.</p>
     *
     * @param titleExpression a {@link ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression} object.
     */
    public void setTitleExpression(final DRIExpression<?> titleExpression) {
        this.titleExpression = titleExpression;
    }

    /** {@inheritDoc} */
    @Override
    public DRIReportStyle getTitleStyle() {
        return titleStyle;
    }

    /**
     * <p>Setter for the field <code>titleStyle</code>.</p>
     *
     * @param titleStyle a {@link ch.unibas.medizin.dynamicreports.report.definition.style.DRIReportStyle} object.
     */
    public void setTitleStyle(final DRIReportStyle titleStyle) {
        this.titleStyle = titleStyle;
    }

    /** {@inheritDoc} */
    @Override
    public Integer getTitleHeight() {
        return titleHeight;
    }

    /**
     * <p>Setter for the field <code>titleHeight</code>.</p>
     *
     * @param titleHeight a {@link java.lang.Integer} object.
     */
    public void setTitleHeight(final Integer titleHeight) {
        if (titleHeight != null) {
            Validate.isTrue(titleHeight >= 0, "titleHeight must be >= 0");
        }
        this.titleHeight = titleHeight;
    }

    /** {@inheritDoc} */
    @Override
    public ComponentDimensionType getTitleHeightType() {
        return titleHeightType;
    }

    /**
     * <p>Setter for the field <code>titleHeightType</code>.</p>
     *
     * @param titleHeightType a {@link ch.unibas.medizin.dynamicreports.report.constant.ComponentDimensionType} object.
     */
    public void setTitleHeightType(final ComponentDimensionType titleHeightType) {
        this.titleHeightType = titleHeightType;
    }

    /** {@inheritDoc} */
    @Override
    public Integer getTitleRows() {
        return titleRows;
    }

    /**
     * <p>Setter for the field <code>titleRows</code>.</p>
     *
     * @param titleRows a {@link java.lang.Integer} object.
     */
    public void setTitleRows(final Integer titleRows) {
        if (titleRows != null) {
            Validate.isTrue(titleRows >= 0, "titleRows must be >= 0");
        }
        this.titleRows = titleRows;
    }

    /** {@inheritDoc} */
    @Override
    public TextAdjust getTitleTextAdjust() {
        return titleTextAdjust;
    }

    /**
     * <p>Setter for the field <code>titleTextAdjust</code>.</p>
     *
     * @param titleTextAdjust a {@link ch.unibas.medizin.dynamicreports.report.constant.TextAdjust} object.
     */
    public void setTitleTextAdjust(final TextAdjust titleTextAdjust) {
        this.titleTextAdjust = titleTextAdjust;
    }

    /** {@inheritDoc} */
    @Override
    public List<DRIPropertyExpression> getTitlePropertyExpressions() {
        return titlePropertyExpressions;
    }

    /**
     * <p>Setter for the field <code>titlePropertyExpressions</code>.</p>
     *
     * @param titlePropertyExpressions a {@link java.util.List} object.
     */
    public void setTitlePropertyExpressions(final List<DRIPropertyExpression> titlePropertyExpressions) {
        this.titlePropertyExpressions = titlePropertyExpressions;
    }

    /**
     * <p>addTitlePropertyExpression.</p>
     *
     * @param propertyExpression a {@link ch.unibas.medizin.dynamicreports.report.definition.expression.DRIPropertyExpression} object.
     */
    public void addTitlePropertyExpression(final DRIPropertyExpression propertyExpression) {
        Validate.notNull(propertyExpression, "propertyExpression must not be null");
        this.titlePropertyExpressions.add(propertyExpression);
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return name;
    }
}
