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
package ch.unibas.medizin.dynamicreports.design.transformation;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.unibas.medizin.dynamicreports.design.base.component.DRDesignComponent;
import ch.unibas.medizin.dynamicreports.design.base.component.DRDesignTextField;
import ch.unibas.medizin.dynamicreports.design.base.style.DRDesignStyle;
import ch.unibas.medizin.dynamicreports.design.constant.DefaultStyleType;
import ch.unibas.medizin.dynamicreports.report.base.component.DRBooleanField;
import ch.unibas.medizin.dynamicreports.report.base.component.DRTextField;
import ch.unibas.medizin.dynamicreports.report.base.style.DRConditionalStyle;
import ch.unibas.medizin.dynamicreports.report.base.style.DRPadding;
import ch.unibas.medizin.dynamicreports.report.base.style.DRStyle;
import ch.unibas.medizin.dynamicreports.report.builder.expression.Expressions;
import ch.unibas.medizin.dynamicreports.report.definition.column.DRIBooleanColumn;
import ch.unibas.medizin.dynamicreports.report.definition.column.DRIColumn;
import ch.unibas.medizin.dynamicreports.report.definition.column.DRIValueColumn;
import ch.unibas.medizin.dynamicreports.report.definition.component.DRIComponent;
import ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression;
import ch.unibas.medizin.dynamicreports.report.definition.style.DRIConditionalStyle;
import ch.unibas.medizin.dynamicreports.report.definition.style.DRIReportStyle;
import ch.unibas.medizin.dynamicreports.report.definition.style.DRISimpleStyle;
import ch.unibas.medizin.dynamicreports.report.definition.style.DRIStyle;
import ch.unibas.medizin.dynamicreports.report.exception.DRException;

/**
 * <p>ColumnTransform class.</p>
 *
 * @author Ricardo Mariaca, Jan Moxter
 * 
 */
public class ColumnTransform {
    private final DesignTransformAccessor accessor;
    private Map<DRIColumn<?>, DRIComponent> columnComponents;

    /**
     * <p>Constructor for ColumnTransform.</p>
     *
     * @param accessor a {@link ch.unibas.medizin.dynamicreports.design.transformation.DesignTransformAccessor} object.
     */
    public ColumnTransform(final DesignTransformAccessor accessor) {
        this.accessor = accessor;
    }

    // columns

    /**
     * <p>transform.</p>
     *
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    public void transform() throws DRException {
        columnComponents = getColumnComponents();
        final boolean showColumnTitle = accessor.getTemplateTransform().isShowColumnTitle();
        final boolean showColumnValues = accessor.getTemplateTransform().isShowColumnValues();
        final boolean showColumnTitleForGroup = accessor.getBandTransform().getColumnHeaderForGroupBand() != null;

        ColumnGrid columnTitle = null;
        if (showColumnTitle) {
            columnTitle = accessor.getColumnGridTransform().createColumnTitleGrid(accessor.getStyleTransform().getDefaultStyle(DefaultStyleType.COLUMN_TITLE));
        }
        ColumnGrid columnTitleForGroup = null;
        if (showColumnTitleForGroup) {
            columnTitleForGroup = accessor.getColumnGridTransform().createColumnTitleGrid(accessor.getStyleTransform().getDefaultStyle(DefaultStyleType.COLUMN_TITLE));
        }
        final ColumnGrid detail = accessor.getColumnGridTransform().createColumnGrid();

        for (final DRIColumn<?> column : accessor.getReport().getColumns()) {
            if (!accessor.getGroupTransform().getHideGroupColumns().contains(column)) {
                if (column.getTitleExpression() != null) {
                    if (showColumnTitle) {
                        columnTitle.addComponent(column, titleComponent(column));
                    }
                    if (showColumnTitleForGroup) {
                        columnTitleForGroup.addComponent(column, titleComponent(column));
                    }
                }
                DRDesignComponent detailComponent;
                if (column instanceof DRIValueColumn<?>) {
                    detailComponent = detailValueComponent((DRIValueColumn<?>) column);
                } else if (column instanceof DRIBooleanColumn) {
                    detailComponent = detailBooleanComponent((DRIBooleanColumn) column);
                } else {
                    detailComponent = detailComponent(column);
                }
                detail.addComponent(column, detailComponent);
            }
        }

        if (showColumnTitle && !columnTitle.isEmpty()) {
            accessor.getBandTransform().getColumnHeaderBand().addComponent(0, columnTitle.getList());
        }
        if (showColumnTitleForGroup && !columnTitleForGroup.isEmpty()) {
            accessor.getBandTransform().getColumnHeaderForGroupBand().addComponent(0, columnTitleForGroup.getList());
        }
        if (showColumnValues) {
            accessor.getBandTransform().getDetailBand().addComponent(detail.getList());
        }
    }

    private Map<DRIColumn<?>, DRIComponent> getColumnComponents() throws DRException {
        final Map<DRIColumn<?>, DRIComponent> columnComponents = new HashMap<>();
        for (final DRIColumn<?> column : accessor.getReport().getColumns()) {
            if (!accessor.getGroupTransform().getHideGroupColumns().contains(column)) {
                DRIComponent component = column.getComponent();
                if (column instanceof DRIBooleanColumn) {
                    component = createBooleanComponent((DRIBooleanColumn) column);
                }
                columnComponents.put(column, component);
            }
        }
        return columnComponents;
    }

    private DRIComponent createBooleanComponent(final DRIBooleanColumn column) {
        final DRIReportStyle booleanColumnStyle = accessor.getTemplateTransform().getBooleanColumnStyle(column);
        if (booleanColumnStyle == null) {
            return column.getComponent();
        }
        final DRBooleanField booleanField = new DRBooleanField();
        booleanField.setComponentType(column.getComponent().getComponentType());
        booleanField.setEmptyWhenNullValue(column.getComponent().getEmptyWhenNullValue());
        booleanField.setValueExpression(column.getComponent().getValueExpression());
        booleanField.setWidth(column.getComponent().getWidth());
        booleanField.setWidthType(column.getComponent().getWidthType());
        booleanField.setHeight(column.getComponent().getHeight());
        booleanField.setHeightType(column.getComponent().getHeightType());
        booleanField.setImageWidth(column.getComponent().getImageWidth());
        booleanField.setImageHeight(column.getComponent().getImageHeight());
        booleanField.setHorizontalImageAlignment(column.getComponent().getHorizontalImageAlignment());
        booleanField.setHorizontalTextAlignment(column.getComponent().getHorizontalTextAlignment());
        booleanField.setStyle(booleanColumnStyle);
        booleanField.setPrintWhenExpression(column.getComponent().getPrintWhenExpression());
        return booleanField;
    }

    // title
    private DRDesignComponent titleComponent(final DRIColumn<?> column) throws DRException {
        final DRTextField titleField = new DRTextField();
        titleField.setValueExpression(column.getTitleExpression());
        titleField.setStyle(column.getTitleStyle());
        titleField.setWidth(accessor.getTemplateTransform().getColumnWidth(column, accessor.getStyleTransform().getDefaultStyle(DefaultStyleType.COLUMN)));
        titleField.setHeight(column.getTitleHeight());
        titleField.setHeightType(column.getTitleHeightType());
        titleField.setRows(column.getTitleRows());
        titleField.setTextAdjust(column.getTitleTextAdjust());
        titleField.setPropertyExpressions(column.getTitlePropertyExpressions());
        final DRDesignTextField designTitleField = accessor.getComponentTransform().textField(titleField, DefaultStyleType.COLUMN_TITLE);
        designTitleField.setUniqueName("column_" + column.getName() + ".title");
        return designTitleField;
    }

    // detail
    private DRDesignComponent detailValueComponent(final DRIValueColumn<?> column) throws DRException {
        final DRDesignComponent detailComponent = detailComponent(column);
        ((DRDesignTextField) detailComponent).setPrintRepeatedValues(accessor.getTemplateTransform().isColumnPrintRepeatedDetailValues(column));
        return detailComponent;
    }

    private DRDesignComponent detailBooleanComponent(final DRIBooleanColumn column) throws DRException {
        final DRDesignComponent detailComponent = detailComponent(column);

        return detailComponent;
    }

    private DRDesignComponent detailComponent(final DRIColumn<?> column) throws DRException {
        final DRDesignComponent designComponent = accessor.getComponentTransform().component(getColumnComponent(column), DefaultStyleType.COLUMN, null, null);
        designComponent.setUniqueName("column_" + column.getName());

        final List<DRIConditionalStyle> rowHighlighters = new ArrayList<>(getDetailRowHighlighters());
        final DRISimpleStyle detailOddRowStyle = accessor.getTemplateTransform().getDetailOddRowStyle();
        if (detailOddRowStyle != null) {
            rowHighlighters.add(detailRowConditionalStyle(detailOddRowStyle, Expressions.printInOddRow()));
        }
        final DRISimpleStyle detailEvenRowStyle = accessor.getTemplateTransform().getDetailEvenRowStyle();
        if (detailEvenRowStyle != null) {
            rowHighlighters.add(detailRowConditionalStyle(detailEvenRowStyle, Expressions.printInEvenRow()));
        }
        if (!rowHighlighters.isEmpty()) {
            DRIReportStyle style = getColumnComponent(column).getStyle();
            if (style == null) {
                style = accessor.getTemplateTransform().getColumnStyle(column instanceof DRIValueColumn<?>);
            }
            final DRStyle newStyle = new DRStyle();
            newStyle.setParentStyle(style);
            if (!(column instanceof DRIValueColumn<?>)) {
                newStyle.setPadding(new DRPadding(0));
            }
            final List<DRIConditionalStyle> conditionalStyles = new ArrayList<>();
            if (style != null) {
                final DRIStyle stl = accessor.getStyleTransform().getStyle(style);
                conditionalStyles.addAll(stl.getConditionalStyles());
            }
            conditionalStyles.addAll(rowHighlighters);
            final Color backgroundColor = StyleResolver.getBackgroundColor(style, accessor.getStyleTransform());
            for (final DRIConditionalStyle conditionalStyle : conditionalStyles) {
                if (backgroundColor != null && conditionalStyle.getBackgroundColor() != null) {
                    final DRConditionalStyle newConditionalStyle = new DRConditionalStyle(conditionalStyle.getConditionExpression());
                    accessor.getStyleTransform().copyStyle(newConditionalStyle, conditionalStyle);
                    final Color mergedColor = StyleResolver.mergeColors(backgroundColor, conditionalStyle.getBackgroundColor(), 0.25f);
                    newConditionalStyle.setBackgroundColor(mergedColor);
                    newStyle.addConditionalStyle(newConditionalStyle);
                } else {
                    newStyle.addConditionalStyle((DRConditionalStyle) conditionalStyle);
                }
            }
            designComponent.setStyle(accessor.getStyleTransform().transformStyle(newStyle, true, DefaultStyleType.COLUMN));
        } else {
            if (designComponent.getStyle() == null && !(column instanceof DRIValueColumn<?>)) {
                final DRIReportStyle columnStyle = accessor.getTemplateTransform().getColumnStyle(false);
                DRStyle newStyle = null;
                if (columnStyle != null) {
                    newStyle = new DRStyle();
                    newStyle.setParentStyle(columnStyle);
                    newStyle.setPadding(new DRPadding(0));
                }
                final DRDesignStyle designColumnStyle = accessor.getStyleTransform().transformStyle(newStyle, false, DefaultStyleType.NONE);
                designComponent.setStyle(designColumnStyle);
            }
        }

        return designComponent;
    }

    private List<? extends DRIConditionalStyle> getDetailRowHighlighters() {
        return accessor.getReport().getDetailRowHighlighters();
    }

    private DRConditionalStyle detailRowConditionalStyle(final DRISimpleStyle style, final DRIExpression<Boolean> expression) {
        final DRConditionalStyle conditionalStyle = new DRConditionalStyle(expression);
        accessor.getStyleTransform().copyStyle(conditionalStyle, style);
        return conditionalStyle;
    }

    /**
     * <p>getColumnComponent.</p>
     *
     * @param column a {@link ch.unibas.medizin.dynamicreports.report.definition.column.DRIColumn} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.component.DRIComponent} object.
     */
    public DRIComponent getColumnComponent(final DRIColumn<?> column) {
        return columnComponents.get(column);
    }
}
