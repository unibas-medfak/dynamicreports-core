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

import ch.unibas.medizin.dynamicreports.design.base.DRDesignGroup;
import ch.unibas.medizin.dynamicreports.design.base.DRDesignHyperLink;
import ch.unibas.medizin.dynamicreports.design.base.DRDesignTableOfContentsHeading;
import ch.unibas.medizin.dynamicreports.design.base.DRDesignVariable;
import ch.unibas.medizin.dynamicreports.design.base.chart.DRDesignChart;
import ch.unibas.medizin.dynamicreports.design.base.component.DRDesignBreak;
import ch.unibas.medizin.dynamicreports.design.base.component.DRDesignComponent;
import ch.unibas.medizin.dynamicreports.design.base.component.DRDesignEllipse;
import ch.unibas.medizin.dynamicreports.design.base.component.DRDesignFiller;
import ch.unibas.medizin.dynamicreports.design.base.component.DRDesignGenericElement;
import ch.unibas.medizin.dynamicreports.design.base.component.DRDesignHyperlinkComponent;
import ch.unibas.medizin.dynamicreports.design.base.component.DRDesignImage;
import ch.unibas.medizin.dynamicreports.design.base.component.DRDesignLine;
import ch.unibas.medizin.dynamicreports.design.base.component.DRDesignList;
import ch.unibas.medizin.dynamicreports.design.base.component.DRDesignRectangle;
import ch.unibas.medizin.dynamicreports.design.base.component.DRDesignSubreport;
import ch.unibas.medizin.dynamicreports.design.base.component.DRDesignTextField;
import ch.unibas.medizin.dynamicreports.design.base.crosstab.DRDesignCrosstab;
import ch.unibas.medizin.dynamicreports.design.base.style.DRDesignStyle;
import ch.unibas.medizin.dynamicreports.design.constant.DefaultStyleType;
import ch.unibas.medizin.dynamicreports.design.constant.EvaluationTime;
import ch.unibas.medizin.dynamicreports.design.constant.ResetType;
import ch.unibas.medizin.dynamicreports.design.definition.DRIDesignField;
import ch.unibas.medizin.dynamicreports.design.definition.DRIDesignVariable;
import ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignComplexExpression;
import ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignExpression;
import ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignJasperExpression;
import ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignSimpleExpression;
import ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignSystemExpression;
import ch.unibas.medizin.dynamicreports.design.exception.DRDesignReportException;
import ch.unibas.medizin.dynamicreports.design.transformation.expressions.BooleanImageExpression;
import ch.unibas.medizin.dynamicreports.design.transformation.expressions.BooleanTextValueFormatter;
import ch.unibas.medizin.dynamicreports.design.transformation.expressions.CurrentDateExpression;
import ch.unibas.medizin.dynamicreports.design.transformation.expressions.MultiPageListDataSourceExpression;
import ch.unibas.medizin.dynamicreports.design.transformation.expressions.MultiPageListSubreportExpression;
import ch.unibas.medizin.dynamicreports.design.transformation.expressions.PageNumberExpression;
import ch.unibas.medizin.dynamicreports.design.transformation.expressions.PageXofYNumberExpression;
import ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder;
import ch.unibas.medizin.dynamicreports.report.base.DRGroup;
import ch.unibas.medizin.dynamicreports.report.base.DRHyperLink;
import ch.unibas.medizin.dynamicreports.report.base.component.DRHyperLinkComponent;
import ch.unibas.medizin.dynamicreports.report.base.component.DRImage;
import ch.unibas.medizin.dynamicreports.report.base.component.DRList;
import ch.unibas.medizin.dynamicreports.report.base.component.DRTextField;
import ch.unibas.medizin.dynamicreports.report.base.style.DRPen;
import ch.unibas.medizin.dynamicreports.report.base.style.DRStyle;
import ch.unibas.medizin.dynamicreports.report.builder.DynamicReports;
import ch.unibas.medizin.dynamicreports.report.builder.component.Components;
import ch.unibas.medizin.dynamicreports.report.builder.datatype.DataTypes;
import ch.unibas.medizin.dynamicreports.report.builder.expression.Expressions;
import ch.unibas.medizin.dynamicreports.report.component.CustomComponentTransform;
import ch.unibas.medizin.dynamicreports.report.component.CustomComponents;
import ch.unibas.medizin.dynamicreports.report.component.DRICustomComponent;
import ch.unibas.medizin.dynamicreports.report.constant.BooleanComponentType;
import ch.unibas.medizin.dynamicreports.report.constant.ComponentDimensionType;
import ch.unibas.medizin.dynamicreports.report.constant.Evaluation;
import ch.unibas.medizin.dynamicreports.report.constant.HorizontalCellComponentAlignment;
import ch.unibas.medizin.dynamicreports.report.constant.HorizontalTextAlignment;
import ch.unibas.medizin.dynamicreports.report.constant.ImageScale;
import ch.unibas.medizin.dynamicreports.report.constant.VerticalCellComponentAlignment;
import ch.unibas.medizin.dynamicreports.report.definition.DRIGroup;
import ch.unibas.medizin.dynamicreports.report.definition.DRIHyperLink;
import ch.unibas.medizin.dynamicreports.report.definition.chart.DRIChart;
import ch.unibas.medizin.dynamicreports.report.definition.component.DRIBooleanField;
import ch.unibas.medizin.dynamicreports.report.definition.component.DRIBreak;
import ch.unibas.medizin.dynamicreports.report.definition.component.DRIComponent;
import ch.unibas.medizin.dynamicreports.report.definition.component.DRICurrentDate;
import ch.unibas.medizin.dynamicreports.report.definition.component.DRIDimensionComponent;
import ch.unibas.medizin.dynamicreports.report.definition.component.DRIEllipse;
import ch.unibas.medizin.dynamicreports.report.definition.component.DRIFiller;
import ch.unibas.medizin.dynamicreports.report.definition.component.DRIFormatField;
import ch.unibas.medizin.dynamicreports.report.definition.component.DRIGenericElement;
import ch.unibas.medizin.dynamicreports.report.definition.component.DRIHyperLinkComponent;
import ch.unibas.medizin.dynamicreports.report.definition.component.DRIImage;
import ch.unibas.medizin.dynamicreports.report.definition.component.DRILine;
import ch.unibas.medizin.dynamicreports.report.definition.component.DRIList;
import ch.unibas.medizin.dynamicreports.report.definition.component.DRIListCell;
import ch.unibas.medizin.dynamicreports.report.definition.component.DRIMultiPageList;
import ch.unibas.medizin.dynamicreports.report.definition.component.DRIPageNumber;
import ch.unibas.medizin.dynamicreports.report.definition.component.DRIPageXofY;
import ch.unibas.medizin.dynamicreports.report.definition.component.DRIRectangle;
import ch.unibas.medizin.dynamicreports.report.definition.component.DRISubreport;
import ch.unibas.medizin.dynamicreports.report.definition.component.DRITextField;
import ch.unibas.medizin.dynamicreports.report.definition.component.DRITotalPages;
import ch.unibas.medizin.dynamicreports.report.definition.component.DRIXyList;
import ch.unibas.medizin.dynamicreports.report.definition.component.DRIXyListCell;
import ch.unibas.medizin.dynamicreports.report.definition.crosstab.DRICrosstab;
import ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression;
import ch.unibas.medizin.dynamicreports.report.definition.expression.DRIParameterExpression;
import ch.unibas.medizin.dynamicreports.report.definition.expression.DRIPropertyExpression;
import ch.unibas.medizin.dynamicreports.report.definition.style.DRIReportStyle;
import ch.unibas.medizin.dynamicreports.report.exception.DRException;

/**
 * <p>ComponentTransform class.</p>
 *
 * @author Ricardo Mariaca, Jan Moxter
 * 
 */
public class ComponentTransform {
    private final DesignTransformAccessor accessor;

    /**
     * <p>Constructor for ComponentTransform.</p>
     *
     * @param accessor a {@link ch.unibas.medizin.dynamicreports.design.transformation.DesignTransformAccessor} object.
     */
    public ComponentTransform(final DesignTransformAccessor accessor) {
        this.accessor = accessor;
    }

    // component

    /**
     * <p>component.</p>
     *
     * @param component        a {@link ch.unibas.medizin.dynamicreports.report.definition.component.DRIComponent} object.
     * @param defaultStyleType a {@link ch.unibas.medizin.dynamicreports.design.constant.DefaultStyleType} object.
     * @param resetType        a {@link ch.unibas.medizin.dynamicreports.design.constant.ResetType} object.
     * @param resetGroup       a {@link ch.unibas.medizin.dynamicreports.design.base.DRDesignGroup} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.design.base.component.DRDesignComponent} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    protected DRDesignComponent component(final DRIComponent component, final DefaultStyleType defaultStyleType, final ResetType resetType, final DRDesignGroup resetGroup) throws DRException {
        if (component instanceof DRITextField<?>) {
            return textField((DRITextField<?>) component, defaultStyleType);
        }
        if (component instanceof DRIList) {
            return list((DRIList) component, defaultStyleType, resetType, resetGroup);
        }
        if (component instanceof DRIXyList) {
            return xyList((DRIXyList) component, defaultStyleType, resetType, resetGroup);
        }
        if (component instanceof DRIMultiPageList) {
            return multiPageList((DRIMultiPageList) component);
        }
        if (component instanceof DRIFiller) {
            return filler((DRIFiller) component);
        }
        if (component instanceof DRIImage) {
            return image((DRIImage) component);
        }
        if (component instanceof DRIChart) {
            return chart((DRIChart) component, resetType, resetGroup);
        }
        if (component instanceof DRISubreport) {
            return subreport((DRISubreport) component);
        }
        if (component instanceof DRIPageXofY) {
            return pageXofY((DRIPageXofY) component, defaultStyleType);
        }
        if (component instanceof DRITotalPages) {
            return totalPages((DRITotalPages) component, defaultStyleType);
        }
        if (component instanceof DRIPageNumber) {
            return pageNumber((DRIPageNumber) component, defaultStyleType);
        }
        if (component instanceof DRICurrentDate) {
            return currentDate((DRICurrentDate) component, defaultStyleType);
        }
        if (component instanceof DRILine) {
            return line((DRILine) component);
        }
        if (component instanceof DRIEllipse) {
            return ellipse((DRIEllipse) component);
        }
        if (component instanceof DRIRectangle) {
            return rectangle((DRIRectangle) component);
        }
        if (component instanceof DRIBooleanField) {
            return booleanField((DRIBooleanField) component, defaultStyleType, resetType, resetGroup);
        }
        if (component instanceof DRIBreak) {
            return breakComponent((DRIBreak) component);
        }
        if (component instanceof DRIGenericElement) {
            return genericElement((DRIGenericElement) component, resetType, resetGroup);
        }
        if (component instanceof DRICrosstab) {
            return crosstab((DRICrosstab) component, resetType, resetGroup);
        }
        if (component instanceof DRICustomComponent) {
            return customComponent((DRICustomComponent) component, resetType, resetGroup);
        }
        throw new DRDesignReportException("Component " + component.getClass().getName() + " not supported");
    }

    private void component(final DRDesignComponent designComponent, final DRIComponent component, final DRIReportStyle style, final boolean textStyle, final DefaultStyleType defaultStyleType) throws DRException {
        designComponent.setStyle(accessor.getStyleTransform().transformStyle(style, textStyle, defaultStyleType));
        designComponent.setPrintWhenExpression(accessor.getExpressionTransform().transformExpression(component.getPrintWhenExpression()));
        designComponent.setRemoveLineWhenBlank(accessor.getTemplateTransform().getRemoveLineWhenBlank(component));
        designComponent.setPositionType(accessor.getTemplateTransform().getPositionType(component));
        designComponent.setStretchType(accessor.getTemplateTransform().getStretchType(component));
        designComponent.setPrintInFirstWholeBand(accessor.getTemplateTransform().getPrintInFirstWholeBand(component));
        designComponent.setPrintWhenDetailOverflows(accessor.getTemplateTransform().getPrintWhenDetailOverflows(component));
        designComponent.setPrintWhenGroupChanges(accessor.getTemplateTransform().getPrintWhenGroupChanges(component));
        for (final DRIPropertyExpression propertyExpression : component.getPropertyExpressions()) {
            designComponent.getPropertyExpressions().add(accessor.getExpressionTransform().transformPropertyExpression(propertyExpression));
        }
        final DRDesignTableOfContentsHeading designTocHeading = accessor.getTableOfContentsTransform().componentHeading(component);
        if (designTocHeading != null) {
            designComponent.setTableOfContentsHeading(designTocHeading);
            final DRIDesignExpression anchorNameExpression = designTocHeading.getReferenceField().getAnchorNameExpression();
            final Integer bookmarkLevel = designTocHeading.getReferenceField().getBookmarkLevel();
            final DRDesignHyperLink designHyperLink = designTocHeading.getReferenceField().getHyperLink();
            if (designComponent instanceof DRDesignHyperlinkComponent) {
                ((DRDesignHyperlinkComponent) designComponent).setAnchorNameExpression(anchorNameExpression);
                ((DRDesignHyperlinkComponent) designComponent).setBookmarkLevel(bookmarkLevel);
                ((DRDesignHyperlinkComponent) designComponent).setHyperLink(designHyperLink);
            }
        }
    }

    private void hyperlink(final DRDesignHyperlinkComponent designHyperlinkComponent, final DRIHyperLinkComponent hyperlinkComponent, final DRIReportStyle style, final boolean textStyle, final DefaultStyleType defaultStyleType)
        throws DRException {
        component(designHyperlinkComponent, hyperlinkComponent, style, textStyle, defaultStyleType);

        if (hyperlinkComponent.getAnchorNameExpression() != null) {
            designHyperlinkComponent.setAnchorNameExpression(accessor.getExpressionTransform().transformExpression(hyperlinkComponent.getAnchorNameExpression()));
        }
        if (hyperlinkComponent.getBookmarkLevel() != null) {
            designHyperlinkComponent.setBookmarkLevel(hyperlinkComponent.getBookmarkLevel());
        }

        final DRIHyperLink hyperLink = hyperlinkComponent.getHyperLink();
        final DRDesignHyperLink designHyperLink = accessor.getReportTransform().hyperlink(hyperLink);
        if (designHyperLink != null) {
            designHyperlinkComponent.setHyperLink(designHyperLink);
        }
    }

    // list

    /**
     * <p>list.</p>
     *
     * @param list             a {@link ch.unibas.medizin.dynamicreports.report.definition.component.DRIList} object.
     * @param defaultStyleType a {@link ch.unibas.medizin.dynamicreports.design.constant.DefaultStyleType} object.
     * @param resetType        a {@link ch.unibas.medizin.dynamicreports.design.constant.ResetType} object.
     * @param resetGroup       a {@link ch.unibas.medizin.dynamicreports.design.base.DRDesignGroup} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.design.base.component.DRDesignList} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    protected DRDesignList list(final DRIList list, final DefaultStyleType defaultStyleType, final ResetType resetType, final DRDesignGroup resetGroup) throws DRException {
        final DRDesignList designList = new DRDesignList();
        component(designList, list, list.getStyle(), false, DefaultStyleType.NONE);
        designList.setType(list.getType());
        designList.setGap(accessor.getTemplateTransform().getListGap(list));
        designList.setWidth(accessor.getTemplateTransform().getListWidth(list));
        designList.setHeight(accessor.getTemplateTransform().getListHeight(list));
        designList.setCalculateComponents(designList.getWidth() == null && designList.getHeight() == null);
        for (final DRIListCell innerComponent : list.getListCells()) {
            final DRIComponent component = innerComponent.getComponent();
            HorizontalCellComponentAlignment horizontalAlignment = innerComponent.getHorizontalAlignment();
            VerticalCellComponentAlignment verticalAlignment = innerComponent.getVerticalAlignment();
            if (component instanceof DRIDimensionComponent dimComponent) {
                if (horizontalAlignment == null) {
                    horizontalAlignment = ConstantTransform.toHorizontalCellComponentAlignment(dimComponent.getWidthType());
                }
                if (verticalAlignment == null) {
                    verticalAlignment = ConstantTransform.toVerticalCellComponentAlignment(dimComponent.getHeightType());
                }
            }
            designList.addComponent(horizontalAlignment, verticalAlignment, component(component, defaultStyleType, resetType, resetGroup));
        }
        designList.setBackgroundComponent(listBackgroundComponent(list.getBackgroundComponent(), defaultStyleType, resetType, resetGroup));

        return designList;
    }

    /**
     * <p>xyList.</p>
     *
     * @param xyList           a {@link ch.unibas.medizin.dynamicreports.report.definition.component.DRIXyList} object.
     * @param defaultStyleType a {@link ch.unibas.medizin.dynamicreports.design.constant.DefaultStyleType} object.
     * @param resetType        a {@link ch.unibas.medizin.dynamicreports.design.constant.ResetType} object.
     * @param resetGroup       a {@link ch.unibas.medizin.dynamicreports.design.base.DRDesignGroup} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.design.base.component.DRDesignList} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    protected DRDesignList xyList(final DRIXyList xyList, final DefaultStyleType defaultStyleType, final ResetType resetType, final DRDesignGroup resetGroup) throws DRException {
        final DRDesignList designList = new DRDesignList(null);
        component(designList, xyList, xyList.getStyle(), false, DefaultStyleType.NONE);
        designList.setWidth(accessor.getTemplateTransform().getXyListWidth(xyList));
        designList.setHeight(accessor.getTemplateTransform().getXyListHeight(xyList));
        designList.setCalculateComponents(designList.getWidth() == null && designList.getHeight() == null);
        designList.setRemovable(true);
        for (final DRIXyListCell innerComponent : xyList.getXyListCells()) {
            final DRDesignComponent designComponent = component(innerComponent.getComponent(), defaultStyleType, resetType, resetGroup);
            designComponent.setX(innerComponent.getX());
            designComponent.setY(innerComponent.getY());
            designList.addComponent(HorizontalCellComponentAlignment.LEFT, VerticalCellComponentAlignment.TOP, designComponent);
        }

        return designList;
    }

    /**
     * <p>listBackgroundComponent.</p>
     *
     * @param backgroundComponent a {@link ch.unibas.medizin.dynamicreports.report.definition.component.DRIComponent} object.
     * @param defaultStyleType    a {@link ch.unibas.medizin.dynamicreports.design.constant.DefaultStyleType} object.
     * @param resetType           a {@link ch.unibas.medizin.dynamicreports.design.constant.ResetType} object.
     * @param resetGroup          a {@link ch.unibas.medizin.dynamicreports.design.base.DRDesignGroup} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.design.base.component.DRDesignComponent} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    protected DRDesignComponent listBackgroundComponent(final DRIComponent backgroundComponent, final DefaultStyleType defaultStyleType, final ResetType resetType, final DRDesignGroup resetGroup) throws DRException {
        if (backgroundComponent != null) {
            if (backgroundComponent instanceof DRIRectangle || backgroundComponent instanceof DRIImage || backgroundComponent instanceof DRITextField) {
                return component(backgroundComponent, defaultStyleType, resetType, resetGroup);
            } else {
                throw new DRDesignReportException("List background component not supported. Only rectangle, image and textfield are supported");
            }
        }
        return null;
    }

    // multipage list
    private DRDesignSubreport multiPageList(final DRIMultiPageList multiPageList) throws DRException {
        final DRDesignSubreport designSubreport = new DRDesignSubreport();
        component(designSubreport, multiPageList, multiPageList.getStyle(), false, DefaultStyleType.NONE);
        designSubreport.setWidth(accessor.getTemplateTransform().getMultiPageListWidth(multiPageList));
        designSubreport.setHeight(accessor.getTemplateTransform().getMultiPageListHeight(multiPageList));
        final JasperReportBuilder multiPageReport = DynamicReports.report();
        final MultiPageListSubreportExpression subreportExpression =
            new MultiPageListSubreportExpression(accessor.getLocale(), accessor.getResourceBundle(), accessor.getResourceBundleName(), accessor.getWhenResourceMissingType(),
                                                 multiPageList.getComponents(), accessor.getTemplateTransform().getTemplateStyles());
        multiPageReport.detail(Components.subreport(subreportExpression));
        multiPageReport.setDetailSplitType(multiPageList.getSplitType());
        final DRIDesignExpression reportExpression = accessor.getExpressionTransform().transformExpression(Expressions.value(multiPageReport));
        final DRIDesignExpression dataSourceExpression = accessor.getExpressionTransform().transformExpression(new MultiPageListDataSourceExpression(multiPageList.getComponents().size()));
        designSubreport.setReportExpression(reportExpression);
        designSubreport.setDataSourceExpression(dataSourceExpression);
        return designSubreport;
    }

    // text field

    /**
     * <p>textField.</p>
     *
     * @param textField        a {@link ch.unibas.medizin.dynamicreports.report.definition.component.DRITextField} object.
     * @param defaultStyleType a {@link ch.unibas.medizin.dynamicreports.design.constant.DefaultStyleType} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.design.base.component.DRDesignTextField} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    protected DRDesignTextField textField(final DRITextField<?> textField, final DefaultStyleType defaultStyleType) throws DRException {
        final DRDesignTextField designTextField = new DRDesignTextField();
        hyperlink(designTextField, textField, textField.getStyle(), true, defaultStyleType);
        final TemplateTransform templateTransform = accessor.getTemplateTransform();
        designTextField.setPrintRepeatedValues(templateTransform.isTextFieldPrintRepeatedValues(textField));
        designTextField.setTextAdjust(templateTransform.getTextFieldTextAdjust(textField));
        final DRDesignStyle style = designTextField.getStyle();
        designTextField.setWidth(templateTransform.getTextFieldWidth(textField, style));
        designTextField.setHeight(templateTransform.getTextFieldHeight(textField, style));
        designTextField.setPattern(templateTransform.getTextFieldPattern(textField, style));
        designTextField.setPatternExpression(accessor.getExpressionTransform().transformExpression(textField.getPatternExpression()));
        designTextField.setHorizontalTextAlignment(templateTransform.getTextFieldHorizontalTextAlignment(textField, style));
        designTextField.setValueExpression(accessor.getExpressionTransform().transformExpression(textField.getValueExpression(), templateTransform.getTextFieldValueFormatter(textField), null));
        designTextField.setMarkup(textField.getMarkup());
        if (textField.getEvaluationTime() != null) {
            designTextField.setEvaluationTime(ConstantTransform.textFieldEvaluationTime(textField.getEvaluationTime(), textField.getEvaluationGroup(), accessor));
            designTextField.setEvaluationGroup(
                accessor.getGroupTransform().getGroup(ConstantTransform.textFieldEvaluationGroup(textField.getEvaluationTime(), textField.getEvaluationGroup(), accessor)));
        } else {
            if (textField.getEvaluationGroup() != null) {
                throw new DRException("Evaluation group for textField is required only for evaluation time BEFORE_GROUP or GROUP");
            }
            final EvaluationTime evaluationTime = detectEvaluationTime(designTextField.getValueExpression());
            designTextField.setEvaluationTime(evaluationTime);
            designTextField.setEvaluationGroup(detectEvaluationGroup(evaluationTime, designTextField.getValueExpression()));
        }
        return designTextField;
    }

    // filler

    /**
     * <p>filler.</p>
     *
     * @param filler a {@link ch.unibas.medizin.dynamicreports.report.definition.component.DRIFiller} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.design.base.component.DRDesignFiller} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    protected DRDesignFiller filler(final DRIFiller filler) throws DRException {
        final DRDesignFiller designFiller = new DRDesignFiller();
        component(designFiller, filler, filler.getStyle(), false, DefaultStyleType.NONE);
        designFiller.setWidth(accessor.getTemplateTransform().getFillerWidth(filler));
        designFiller.setHeight(accessor.getTemplateTransform().getFillerHeight(filler));
        return designFiller;
    }

    // image
    private DRDesignImage image(final DRIImage image) throws DRException {
        return image(image, null, DefaultStyleType.IMAGE);
    }

    private DRDesignImage image(final DRIImage image, final Integer imageHeight, final DefaultStyleType defaultStyleType) throws DRException {
        final DRDesignImage designImage = new DRDesignImage();
        hyperlink(designImage, image, image.getStyle(), false, defaultStyleType);
        designImage.setImageScale(image.getImageScale());
        designImage.setImageExpression(accessor.getExpressionTransform().transformExpression(image.getImageExpression()));
        designImage.setUsingCache(image.getUsingCache());
        designImage.setLazy(image.getLazy());
        designImage.setHorizontalImageAlignment(image.getHorizontalImageAlignment());
        designImage.setWidth(accessor.getTemplateTransform().getImageWidth(image));
        final DRDesignStyle style = designImage.getStyle();
        designImage.setHeight(accessor.getTemplateTransform().getImageHeight(image, imageHeight, style));
        return designImage;
    }

    // chart
    private DRDesignChart chart(final DRIChart chart, final ResetType resetType, final DRDesignGroup resetGroup) throws DRException {
        final DRDesignChart designChart = accessor.getChartTransform().transform(chart, resetType, resetGroup);
        hyperlink(designChart, chart, chart.getStyle(), false, DefaultStyleType.CHART);
        designChart.setEvaluationTime(evaluationTimeFromResetType(resetType));
        designChart.setEvaluationGroup(resetGroup);
        return designChart;
    }

    // subreport
    private DRDesignSubreport subreport(final DRISubreport subreport) throws DRException {
        final DRDesignSubreport designSubreport = new DRDesignSubreport();
        component(designSubreport, subreport, subreport.getStyle(), false, DefaultStyleType.NONE);
        designSubreport.setWidth(accessor.getTemplateTransform().getSubreportWidth(subreport));
        designSubreport.setHeight(accessor.getTemplateTransform().getSubreportHeight(subreport));
        designSubreport.setReportExpression(accessor.getExpressionTransform().transformExpression(subreport.getReportExpression()));
        designSubreport.setParametersExpression(accessor.getExpressionTransform().transformExpression(subreport.getParametersExpression()));
        designSubreport.setConnectionExpression(accessor.getExpressionTransform().transformExpression(subreport.getConnectionExpression()));
        designSubreport.setDataSourceExpression(accessor.getExpressionTransform().transformExpression(subreport.getDataSourceExpression()));
        designSubreport.setRunToBottom(subreport.getRunToBottom());
        return designSubreport;
    }

    // page x of y
    private DRDesignList pageXofY(final DRIPageXofY pageXofY, final DefaultStyleType defaultStyleType) throws DRException {
        final TemplateTransform templateTransform = accessor.getTemplateTransform();
        DRIReportStyle pageXofYStyle = pageXofY.getStyle();
        if (pageXofYStyle == null) {
            pageXofYStyle = accessor.getTemplateTransform().getTextStyle();
        }
        final DRDesignStyle style = accessor.getStyleTransform().transformStyle(pageXofYStyle, true, defaultStyleType);
        final Integer height = templateTransform.getPageXofYHeight(pageXofY, style);
        final HorizontalTextAlignment horizontalTextAlignment = templateTransform.getPageXofYHorizontalTextAlignment(pageXofY, style);

        final DRStyle newStylePageX = new DRStyle();
        newStylePageX.setParentStyle(pageXofYStyle);
        newStylePageX.getPadding().setRight(0);
        final DRPen pen = new DRPen();
        pen.setLineWidth(0f);
        newStylePageX.getBorder().setRightPen(pen);
        final DRStyle newStylePageY = new DRStyle();
        newStylePageY.setParentStyle(pageXofYStyle);
        newStylePageY.getPadding().setLeft(0);
        newStylePageY.getBorder().setLeftPen(pen);

        final DRTextField<String> pageXField = new DRTextField<>();
        pageXField.setAnchorNameExpression(pageXofY.getAnchorNameExpression());
        pageXField.setBookmarkLevel(pageXofY.getBookmarkLevel());
        pageXField.setHyperLink((DRHyperLink) pageXofY.getHyperLink());
        pageXField.setPrintWhenExpression(pageXofY.getPrintWhenExpression());
        pageXField.setStyle(newStylePageX);
        pageXField.setHeight(height);
        pageXField.setHeightType(pageXofY.getHeightType());
        pageXField.setHorizontalTextAlignment(HorizontalTextAlignment.RIGHT);
        pageXField.setValueExpression(new PageXofYNumberExpression(pageXofY.getFormatExpression(), 0));

        final DRTextField<String> pageYField = new DRTextField<>();
        pageYField.setAnchorNameExpression(pageXofY.getAnchorNameExpression());
        pageYField.setBookmarkLevel(pageXofY.getBookmarkLevel());
        pageYField.setHyperLink((DRHyperLink) pageXofY.getHyperLink());
        pageYField.setPrintWhenExpression(pageXofY.getPrintWhenExpression());
        pageYField.setStyle(newStylePageY);
        pageYField.setHeight(height);
        pageYField.setHeightType(pageXofY.getHeightType());
        pageYField.setHorizontalTextAlignment(HorizontalTextAlignment.LEFT);
        pageYField.setValueExpression(new PageXofYNumberExpression(pageXofY.getFormatExpression(), 1));
        final DRIGroup pageYEvaluationGroup = accessor.getGroupTransform().getFirstResetPageNumberGroup();
        if (pageYEvaluationGroup == null) {
            pageYField.setEvaluationTime(Evaluation.REPORT);
        } else {
            pageYField.setEvaluationTime(Evaluation.GROUP);
            pageYField.setEvaluationGroup((DRGroup) pageYEvaluationGroup);
        }

        final int pageXofYWidth = templateTransform.getPageXofYWidth(pageXofY);
        int pageXWidth = StyleResolver.getFontWidth(style, 4);
        int pageYWidth = pageXofYWidth - pageXWidth;

        switch (horizontalTextAlignment) {
            case LEFT:
                if (pageYWidth <= 0) {
                    pageYWidth = 10;
                }
                pageXField.setWidth(pageXWidth);
                pageXField.setWidthType(ComponentDimensionType.FIXED);
                pageYField.setWidth(pageYWidth);
                pageYField.setWidthType(pageXofY.getWidthType());
                break;
            case RIGHT:
                pageYWidth = StyleResolver.getFontWidth(style, 6);
                pageXWidth = pageXofYWidth - pageYWidth;
                if (pageXWidth <= 0) {
                    pageXWidth = 10;
                }
                pageXField.setWidth(pageXWidth);
                pageXField.setWidthType(pageXofY.getWidthType());
                pageYField.setWidth(pageYWidth);
                pageYField.setWidthType(ComponentDimensionType.FIXED);
                break;
            default:
                pageXField.setWidth(pageXofYWidth / 2);
                pageXField.setWidthType(pageXofY.getWidthType());
                pageYField.setWidth(pageXofYWidth / 2);
                pageYField.setWidthType(pageXofY.getWidthType());
                break;
        }

        if (pageXofY.getPageXWidth() != null) {
            pageXField.setWidth(pageXofY.getPageXWidth());
        }
        if (pageXofY.getPageXWidthType() != null) {
            pageXField.setWidthType(pageXofY.getPageXWidthType());
        }
        if (pageXofY.getPageYWidth() != null) {
            pageYField.setWidth(pageXofY.getPageYWidth());
        }
        if (pageXofY.getPageYWidthType() != null) {
            pageYField.setWidthType(pageXofY.getPageYWidthType());
        }

        final DRList listPageXofY = new DRList();
        listPageXofY.addComponent(pageXField);
        listPageXofY.addComponent(pageYField);
        return list(listPageXofY, DefaultStyleType.TEXT, null, null);
    }

    // total pages
    private DRDesignTextField totalPages(final DRITotalPages totalPages, final DefaultStyleType defaultStyleType) throws DRException {
        final PageNumberExpression expression = new PageNumberExpression(totalPages.getFormatExpression());
        final DRTextField<String> totalPagesField = formatField(totalPages, expression);
        final DRIGroup pageEvaluationGroup = accessor.getGroupTransform().getFirstResetPageNumberGroup();
        if (pageEvaluationGroup == null) {
            totalPagesField.setEvaluationTime(Evaluation.REPORT);
        } else {
            totalPagesField.setEvaluationTime(Evaluation.GROUP);
            totalPagesField.setEvaluationGroup((DRGroup) pageEvaluationGroup);
        }

        return textField(totalPagesField, defaultStyleType);
    }

    // page number
    private DRDesignTextField pageNumber(final DRIPageNumber pageNumber, final DefaultStyleType defaultStyleType) throws DRException {
        final PageNumberExpression expression = new PageNumberExpression(pageNumber.getFormatExpression());
        return textField(formatField(pageNumber, expression), defaultStyleType);
    }

    // current date
    private DRDesignTextField currentDate(final DRICurrentDate currentDate, final DefaultStyleType defaultStyleType) throws DRException {
        final CurrentDateExpression expression = new CurrentDateExpression(currentDate.getFormatExpression(), currentDate.getPattern());
        return textField(formatField(currentDate, expression), defaultStyleType);
    }

    // format field
    private DRTextField<String> formatField(final DRIFormatField formatField, final DRIExpression<String> expression) throws DRException {
        final DRTextField<String> formatFieldTextField = new DRTextField<>();
        formatFieldTextField.setAnchorNameExpression(formatField.getAnchorNameExpression());
        formatFieldTextField.setBookmarkLevel(formatField.getBookmarkLevel());
        formatFieldTextField.setHyperLink((DRHyperLink) formatField.getHyperLink());
        formatFieldTextField.setPrintWhenExpression(formatField.getPrintWhenExpression());
        formatFieldTextField.setStyle(formatField.getStyle());
        formatFieldTextField.setWidth(formatField.getWidth());
        formatFieldTextField.setWidthType(formatField.getWidthType());
        formatFieldTextField.setHeight(formatField.getHeight());
        formatFieldTextField.setHeightType(formatField.getHeightType());
        formatFieldTextField.setHorizontalTextAlignment(formatField.getHorizontalTextAlignment());
        formatFieldTextField.setValueExpression(expression);
        return formatFieldTextField;
    }

    // line

    /**
     * <p>line.</p>
     *
     * @param line a {@link ch.unibas.medizin.dynamicreports.report.definition.component.DRILine} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.design.base.component.DRDesignLine} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    protected DRDesignLine line(final DRILine line) throws DRException {
        final DRDesignLine designLine = new DRDesignLine();
        component(designLine, line, line.getStyle(), false, DefaultStyleType.NONE);
        designLine.setDirection(line.getDirection());
        designLine.setPen(accessor.getStyleTransform().pen(line.getPen()));
        designLine.setWidth(accessor.getTemplateTransform().getLineWidth(line));
        designLine.setHeight(accessor.getTemplateTransform().getLineHeight(line));
        return designLine;
    }

    // ellipse

    /**
     * <p>ellipse.</p>
     *
     * @param ellipse a {@link ch.unibas.medizin.dynamicreports.report.definition.component.DRIEllipse} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.design.base.component.DRDesignEllipse} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    protected DRDesignEllipse ellipse(final DRIEllipse ellipse) throws DRException {
        final DRDesignEllipse designEllipse = new DRDesignEllipse();
        component(designEllipse, ellipse, ellipse.getStyle(), false, DefaultStyleType.NONE);
        designEllipse.setPen(accessor.getStyleTransform().pen(ellipse.getPen()));
        designEllipse.setWidth(accessor.getTemplateTransform().getEllipseWidth(ellipse));
        designEllipse.setHeight(accessor.getTemplateTransform().getEllipseHeight(ellipse));
        return designEllipse;
    }

    // rectangle

    /**
     * <p>rectangle.</p>
     *
     * @param rectangle a {@link ch.unibas.medizin.dynamicreports.report.definition.component.DRIRectangle} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.design.base.component.DRDesignRectangle} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    protected DRDesignRectangle rectangle(final DRIRectangle rectangle) throws DRException {
        final DRDesignRectangle designRectangle = new DRDesignRectangle();
        component(designRectangle, rectangle, rectangle.getStyle(), false, DefaultStyleType.NONE);
        designRectangle.setRadius(accessor.getTemplateTransform().getRectangleRadius(rectangle));
        designRectangle.setPen(accessor.getStyleTransform().pen(rectangle.getPen()));
        designRectangle.setWidth(accessor.getTemplateTransform().getRectangleWidth(rectangle));
        designRectangle.setHeight(accessor.getTemplateTransform().getRectangleHeight(rectangle));
        return designRectangle;
    }

    // boolean

    /**
     * <p>booleanField.</p>
     *
     * @param booleanField     a {@link ch.unibas.medizin.dynamicreports.report.definition.component.DRIBooleanField} object.
     * @param defaultStyleType a {@link ch.unibas.medizin.dynamicreports.design.constant.DefaultStyleType} object.
     * @param resetType        a {@link ch.unibas.medizin.dynamicreports.design.constant.ResetType} object.
     * @param resetGroup       a {@link ch.unibas.medizin.dynamicreports.design.base.DRDesignGroup} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.design.base.component.DRDesignComponent} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    protected DRDesignComponent booleanField(final DRIBooleanField booleanField, final DefaultStyleType defaultStyleType, final ResetType resetType, final DRDesignGroup resetGroup) throws DRException {
        final BooleanComponentType componentType = accessor.getTemplateTransform().getBooleanComponentType(booleanField);
        final boolean emptyWhenNullValue = accessor.getTemplateTransform().getBooleanEmptyWhenNullValue(booleanField);

        DRHyperLinkComponent component;

        switch (componentType) {
            case TEXT_TRUE_FALSE:
            case TEXT_YES_NO:
                String keyTrue;
                String keyFalse;
                if (componentType.equals(BooleanComponentType.TEXT_TRUE_FALSE)) {
                    keyTrue = "true";
                    keyFalse = "false";
                } else {
                    keyTrue = "yes";
                    keyFalse = "no";
                }
                final DRTextField<Boolean> textField = new DRTextField<>();
                textField.setValueExpression(booleanField.getValueExpression());
                textField.setDataType(DataTypes.booleanType());
                textField.setHorizontalTextAlignment(booleanField.getHorizontalTextAlignment());
                textField.setValueFormatter(new BooleanTextValueFormatter(keyTrue, keyFalse, emptyWhenNullValue));
                component = textField;
                break;
            case IMAGE_STYLE_1:
            case IMAGE_STYLE_2:
            case IMAGE_STYLE_3:
            case IMAGE_STYLE_4:
            case IMAGE_CHECKBOX_1:
            case IMAGE_CHECKBOX_2:
            case IMAGE_BALL:
                final DRImage image = new DRImage();
                image.setImageScale(ImageScale.CLIP);
                final int width = accessor.getTemplateTransform().getBooleanImageWidth(booleanField);
                final int height = accessor.getTemplateTransform().getBooleanImageHeight(booleanField);
                image.setImageExpression(new BooleanImageExpression(booleanField, emptyWhenNullValue, width, height));
                component = image;
                break;
            default:
                throw new DRDesignReportException("Boolean component type " + componentType.name() + " not supported");
        }

        component.setWidth(booleanField.getWidth());
        component.setWidthType(booleanField.getWidthType());
        component.setHeight(booleanField.getHeight());
        component.setHeightType(booleanField.getHeightType());
        component.setAnchorNameExpression(booleanField.getAnchorNameExpression());
        component.setBookmarkLevel(booleanField.getBookmarkLevel());
        component.setHyperLink((DRHyperLink) booleanField.getHyperLink());
        component.setStyle(booleanField.getStyle());
        component.setPrintWhenExpression(booleanField.getPrintWhenExpression());
        component.setPropertyExpressions(booleanField.getPropertyExpressions());

        DRDesignComponent designComponent;
        if (component instanceof DRIImage) {
            final int imageHeight = accessor.getTemplateTransform().getBooleanImageHeight(booleanField);
            designComponent = image((DRIImage) component, imageHeight, defaultStyleType);
            final TemplateTransform templateTransform = accessor.getTemplateTransform();
            ((DRDesignImage) designComponent).setHorizontalImageAlignment(templateTransform.getBooleanHorizontalImageAlignment(booleanField, designComponent.getStyle()));
        } else {
            designComponent = component(component, defaultStyleType, resetType, resetGroup);
        }
        return designComponent;
    }

    // break

    /**
     * <p>breakComponent.</p>
     *
     * @param breakComponent a {@link ch.unibas.medizin.dynamicreports.report.definition.component.DRIBreak} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.design.base.component.DRDesignBreak} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    protected DRDesignBreak breakComponent(final DRIBreak breakComponent) throws DRException {
        final DRDesignBreak designBreak = new DRDesignBreak();
        component(designBreak, breakComponent, null, false, DefaultStyleType.NONE);
        designBreak.setType(breakComponent.getType());
        designBreak.setWidth(accessor.getTemplateTransform().getBreakWidth(breakComponent));
        designBreak.setHeight(accessor.getTemplateTransform().getBreakHeight(breakComponent));
        return designBreak;
    }

    // generic element

    /**
     * <p>genericElement.</p>
     *
     * @param genericElement a {@link ch.unibas.medizin.dynamicreports.report.definition.component.DRIGenericElement} object.
     * @param resetType      a {@link ch.unibas.medizin.dynamicreports.design.constant.ResetType} object.
     * @param resetGroup     a {@link ch.unibas.medizin.dynamicreports.design.base.DRDesignGroup} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.design.base.component.DRDesignGenericElement} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    protected DRDesignGenericElement genericElement(final DRIGenericElement genericElement, final ResetType resetType, final DRDesignGroup resetGroup) throws DRException {
        final DRDesignGenericElement designGenericElement = new DRDesignGenericElement();
        component(designGenericElement, genericElement, genericElement.getStyle(), false, DefaultStyleType.NONE);
        designGenericElement.setGenericElementNamespace(genericElement.getGenericElementNamespace());
        designGenericElement.setGenericElementName(genericElement.getGenericElementName());
        designGenericElement.setEvaluationTime(evaluationTimeFromResetType(resetType));
        designGenericElement.setEvaluationGroup(resetGroup);
        designGenericElement.setWidth(accessor.getTemplateTransform().getGenericElementWidth(genericElement));
        designGenericElement.setHeight(accessor.getTemplateTransform().getGenericElementHeight(genericElement));
        for (final DRIParameterExpression parameterExpression : genericElement.getParameterExpressions()) {
            designGenericElement.getParameterExpressions().add(accessor.getExpressionTransform().transformParameterExpression(parameterExpression));
        }
        return designGenericElement;
    }

    // crosstab
    private DRDesignCrosstab crosstab(final DRICrosstab crosstab, final ResetType resetType, final DRDesignGroup resetGroup) throws DRException {
        final DRDesignCrosstab designCrosstab = accessor.getCrosstabTransform().transform(crosstab, resetType, resetGroup);
        component(designCrosstab, crosstab, crosstab.getStyle(), false, DefaultStyleType.NONE);
        return designCrosstab;
    }

    // custom component
    private DRDesignComponent customComponent(final DRICustomComponent component, final ResetType resetType, final DRDesignGroup resetGroup) throws DRException {
        final CustomComponentTransform componentTransform = CustomComponents.getComponentTransform(component);
        if (componentTransform == null) {
            throw new DRDesignReportException("Component " + component.getClass().getName() + " not supported");
        }
        final DRDesignComponent designComponent = (DRDesignComponent) componentTransform.designComponent(accessor, component, resetType, resetGroup);
        component(designComponent, component, component.getStyle(), false, DefaultStyleType.NONE);
        if (designComponent.getWidth() == null) {
            designComponent.setWidth(accessor.getTemplateTransform().getCustomComponentWidth(component));
        }
        if (designComponent.getHeight() == null) {
            designComponent.setHeight(accessor.getTemplateTransform().getCustomComponentHeight(component));
        }
        return designComponent;
    }

    /**
     * <p>detectEvaluationTime.</p>
     *
     * @param expression a {@link ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignExpression} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.design.constant.EvaluationTime} object.
     */
    protected EvaluationTime detectEvaluationTime(final DRIDesignExpression expression) {
        if (expression == null) {
            return null;
        }

        if (expression instanceof DRIDesignField || expression instanceof DRIDesignSystemExpression || expression instanceof DRIDesignSimpleExpression ||
            expression instanceof DRIDesignJasperExpression) {
            return EvaluationTime.NOW;
        }
        if (expression instanceof DRIDesignVariable) {
            return evaluationTimeFromResetType(((DRIDesignVariable) expression).getResetType());
        }
        if (expression instanceof DRIDesignComplexExpression) {
            return detectComplexExpressionEvaluationTime((DRIDesignComplexExpression) expression);
        }
        throw new DRDesignReportException("Expression " + expression.getClass().getName() + " not supported");
    }

    /**
     * <p>evaluationTimeFromResetType.</p>
     *
     * @param resetType a {@link ch.unibas.medizin.dynamicreports.design.constant.ResetType} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.design.constant.EvaluationTime} object.
     */
    public EvaluationTime evaluationTimeFromResetType(final ResetType resetType) {
        if (resetType == null) {
            return null;
        }

        return switch (resetType) {
            case NONE -> EvaluationTime.NOW;
            case REPORT -> EvaluationTime.REPORT;
            case PAGE -> EvaluationTime.PAGE;
            case COLUMN -> EvaluationTime.COLUMN;
            case GROUP -> EvaluationTime.GROUP;
        };
    }

    private EvaluationTime detectComplexExpressionEvaluationTime(final DRIDesignComplexExpression complexExpression) {
        EvaluationTime evaluationTime = null;
        for (final DRIDesignExpression expression : complexExpression.getExpressions()) {
            final EvaluationTime evalTime = detectEvaluationTime(expression);
            if (evaluationTime == null) {
                evaluationTime = evalTime;
            } else if (evaluationTime != evalTime || evaluationTime.equals(EvaluationTime.GROUP)) {
                return EvaluationTime.AUTO;
            }
        }
        return evaluationTime;
    }

    private DRDesignGroup detectEvaluationGroup(final EvaluationTime evaluationTime, final DRIDesignExpression expression) {
        if (expression != null && evaluationTime != null && evaluationTime.equals(EvaluationTime.GROUP)) {
            final DRDesignGroup evaluationGroup = detectEvaluationGroup(expression);
            if (evaluationGroup == null) {
                throw new DRDesignReportException("Can not detect evaluation group");
            }
            return evaluationGroup;
        }
        return null;
    }

    private DRDesignGroup detectEvaluationGroup(final DRIDesignExpression expression) {
        if (expression instanceof DRIDesignField || expression instanceof DRIDesignSimpleExpression) {
            return null;
        }
        if (expression instanceof DRDesignVariable) {
            return ((DRDesignVariable) expression).getResetGroup();
        }
        if (expression instanceof DRIDesignComplexExpression) {
            return detectComplexExpressionEvaluationGroup((DRIDesignComplexExpression) expression);
        }
        throw new DRDesignReportException("Expression " + expression.getClass().getName() + " not supported");
    }

    private DRDesignGroup detectComplexExpressionEvaluationGroup(final DRIDesignComplexExpression complexExpression) {
        DRDesignGroup evaluationGroup = null;
        for (final DRIDesignExpression expression : complexExpression.getExpressions()) {
            final DRDesignGroup group = detectEvaluationGroup(expression);
            if (evaluationGroup == null) {
                evaluationGroup = group;
            } else if (evaluationGroup != group) {
                throw new DRDesignReportException("Can not detect evaluation group");
            }
        }
        return evaluationGroup;
    }

}
