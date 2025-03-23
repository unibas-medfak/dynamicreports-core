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

import ch.unibas.medizin.dynamicreports.design.base.style.DRDesignBaseStyle;
import ch.unibas.medizin.dynamicreports.design.base.style.DRDesignBorder;
import ch.unibas.medizin.dynamicreports.design.base.style.DRDesignConditionalStyle;
import ch.unibas.medizin.dynamicreports.design.base.style.DRDesignFont;
import ch.unibas.medizin.dynamicreports.design.base.style.DRDesignPadding;
import ch.unibas.medizin.dynamicreports.design.base.style.DRDesignParagraph;
import ch.unibas.medizin.dynamicreports.design.base.style.DRDesignPen;
import ch.unibas.medizin.dynamicreports.design.base.style.DRDesignStyle;
import ch.unibas.medizin.dynamicreports.design.base.style.DRDesignTabStop;
import ch.unibas.medizin.dynamicreports.design.constant.DefaultStyleType;
import ch.unibas.medizin.dynamicreports.design.definition.style.DRIDesignStyle;
import ch.unibas.medizin.dynamicreports.design.definition.style.DRIDesignTabStop;
import ch.unibas.medizin.dynamicreports.design.exception.DRDesignReportException;
import ch.unibas.medizin.dynamicreports.report.base.style.DRBaseStyle;
import ch.unibas.medizin.dynamicreports.report.base.style.DRBorder;
import ch.unibas.medizin.dynamicreports.report.base.style.DRFont;
import ch.unibas.medizin.dynamicreports.report.base.style.DRPadding;
import ch.unibas.medizin.dynamicreports.report.base.style.DRParagraph;
import ch.unibas.medizin.dynamicreports.report.base.style.DRPen;
import ch.unibas.medizin.dynamicreports.report.definition.style.DRIBaseStyle;
import ch.unibas.medizin.dynamicreports.report.definition.style.DRIBorder;
import ch.unibas.medizin.dynamicreports.report.definition.style.DRIConditionalStyle;
import ch.unibas.medizin.dynamicreports.report.definition.style.DRIFont;
import ch.unibas.medizin.dynamicreports.report.definition.style.DRIPadding;
import ch.unibas.medizin.dynamicreports.report.definition.style.DRIParagraph;
import ch.unibas.medizin.dynamicreports.report.definition.style.DRIPen;
import ch.unibas.medizin.dynamicreports.report.definition.style.DRIReportStyle;
import ch.unibas.medizin.dynamicreports.report.definition.style.DRIStyle;
import ch.unibas.medizin.dynamicreports.report.definition.style.DRITabStop;
import ch.unibas.medizin.dynamicreports.report.definition.style.DRITemplateStyle;
import ch.unibas.medizin.dynamicreports.report.exception.DRException;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>StyleTransform class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class StyleTransform {
    private final DesignTransformAccessor accessor;
    private Map<String, DRIDesignStyle> styles;
    private Map<String, DRDesignStyle> designStyles;
    private Map<String, DRIStyle> templateStyles;

    /**
     * <p>Constructor for StyleTransform.</p>
     *
     * @param accessor a {@link ch.unibas.medizin.dynamicreports.design.transformation.DesignTransformAccessor} object.
     */
    public StyleTransform(DesignTransformAccessor accessor) {
        this.accessor = accessor;
        init();
    }

    private void init() {
        styles = new LinkedHashMap<>();
        designStyles = new HashMap<>();
        templateStyles = accessor.getTemplateTransform().getTemplateStyles();
    }

    private DRDesignStyle transformStyle(DRIReportStyle style, boolean textStyle) throws DRException {
        return transformStyle(style, textStyle, DefaultStyleType.NONE);
    }

    /**
     * <p>transformStyle.</p>
     *
     * @param style            a {@link ch.unibas.medizin.dynamicreports.report.definition.style.DRIReportStyle} object.
     * @param textStyle        a boolean.
     * @param defaultStyleType a {@link ch.unibas.medizin.dynamicreports.design.constant.DefaultStyleType} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.design.base.style.DRDesignStyle} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    protected DRDesignStyle transformStyle(DRIReportStyle style, boolean textStyle, DefaultStyleType defaultStyleType) throws DRException {
        if (style == null) {
            return getDefaultStyle(defaultStyleType);
        }
        String styleName = style.hashCode() + "_" + textStyle;
        if (designStyles.containsKey(styleName)) {
            return designStyles.get(styleName);
        }

        DRDesignStyle designStyle = style(getStyle(style), textStyle, defaultStyleType);
        if (textStyle) {
            if (StyleResolver.getFontName(designStyle) == null) {
                designStyle.getFont().setFontName(accessor.getTemplateTransform().getDefaultFontName());
            }
            if (StyleResolver.getFontSize(designStyle) == null) {
                designStyle.getFont().setFontSize(accessor.getTemplateTransform().getDefaultFontSize());
            }
            if (StyleResolver.getFontBold(designStyle) == null) {
                designStyle.getFont().setBold(accessor.getTemplateTransform().getDefaultFontBold());
            }
            if (StyleResolver.getFontItalic(designStyle) == null) {
                designStyle.getFont().setItalic(accessor.getTemplateTransform().getDefaultFontItalic());
            }
            if (StyleResolver.getFontUnderline(designStyle) == null) {
                designStyle.getFont().setUnderline(accessor.getTemplateTransform().getDefaultFontUnderline());
            }
            if (StyleResolver.getFontStrikeThrough(designStyle) == null) {
                designStyle.getFont().setStrikeThrough(accessor.getTemplateTransform().getDefaultFontStrikeThrough());
            }
            if (StyleResolver.getPdfFontName(designStyle) == null) {
                designStyle.getFont().setPdfFontName(accessor.getTemplateTransform().getDefaultFontPdfFontName());
            }
            if (StyleResolver.getPdfEncoding(designStyle) == null) {
                designStyle.getFont().setPdfEncoding(accessor.getTemplateTransform().getDefaultFontPdfEncoding());
            }
            if (StyleResolver.getPdfEmbedded(designStyle) == null) {
                designStyle.getFont().setPdfEmbedded(accessor.getTemplateTransform().getDefaultFontPdfEmbedded());
            }
        }
        addStyle(styleName, designStyle);
        return designStyle;
    }

    /**
     * <p>transformFont.</p>
     *
     * @param font a {@link ch.unibas.medizin.dynamicreports.report.definition.style.DRIFont} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.design.base.style.DRDesignFont} object.
     */
    protected DRDesignFont transformFont(DRIFont font) {
        if (font == null) {
            return null;
        }

        DRDesignFont designFont = new DRDesignFont();
        designFont.setFontName(font.getFontName());
        designFont.setFontSize(font.getFontSize());
        designFont.setBold(font.getBold());
        designFont.setItalic(font.getItalic());
        designFont.setUnderline(font.getUnderline());
        designFont.setStrikeThrough(font.getStrikeThrough());
        designFont.setPdfFontName(font.getPdfFontName());
        designFont.setPdfEncoding(font.getPdfEncoding());
        designFont.setPdfEmbedded(font.getPdfEmbedded());
        return designFont;
    }

    private DRDesignStyle style(DRIStyle style, boolean textStyle, DefaultStyleType defaultStyleType) throws DRException {
        final DRDesignStyle designStyle;
        if (StringUtils.isBlank(style.getName())) {
            designStyle = new DRDesignStyle();
        } else {
            designStyle = new DRDesignStyle(style.getName());
        }
        baseStyle(designStyle, style);
        if (style.getParentStyle() != null) {
            designStyle.setParentStyle(transformStyle(style.getParentStyle(), textStyle, defaultStyleType));
        }
        for (DRIConditionalStyle conditionalStyle : style.getConditionalStyles()) {
            designStyle.addConditionalStyle(conditionalStyle(conditionalStyle));
        }
        return designStyle;
    }

    /**
     * <p>getStyle.</p>
     *
     * @param reportStyle a {@link ch.unibas.medizin.dynamicreports.report.definition.style.DRIReportStyle} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.style.DRIStyle} object.
     */
    protected DRIStyle getStyle(DRIReportStyle reportStyle) {
        switch (reportStyle) {
            case null -> {
                return null;
            }
            case DRIStyle driStyle -> {
                return driStyle;
            }
            case DRITemplateStyle driTemplateStyle -> {
                String name = driTemplateStyle.name();
                DRIStyle style = templateStyles.get(name);
                if (style == null) {
                    throw new DRDesignReportException("Template style " + name + " not found");
                }
                return style;
            }
            default -> {
            }
        }
        throw new DRDesignReportException("Style " + reportStyle.getClass().getName() + " not supported");
    }

    /**
     * <p>transformTemplateStyles.</p>
     *
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    public void transformTemplateStyles() throws DRException {
        for (DRIStyle style : templateStyles.values()) {
            if (styles.containsKey(style.getName())) {
                continue;
            }
            transformStyle(style, false);
        }
    }

    private DRDesignConditionalStyle conditionalStyle(DRIConditionalStyle conditionalStyle) throws DRException {
        DRDesignConditionalStyle designConditionalStyle = new DRDesignConditionalStyle();
        baseStyle(designConditionalStyle, conditionalStyle);
        designConditionalStyle.setConditionExpression(accessor.getExpressionTransform().transformExpression(conditionalStyle.getConditionExpression()));
        designConditionalStyle.setDataset(accessor.getExpressionTransform().getDataset());
        return designConditionalStyle;
    }

    private void baseStyle(DRDesignBaseStyle designBaseStyle, DRIBaseStyle baseStyle) {
        designBaseStyle.setForegroundColor(baseStyle.getForegroundColor());
        designBaseStyle.setBackgroundColor(baseStyle.getBackgroundColor());
        designBaseStyle.setRadius(baseStyle.getRadius());
        designBaseStyle.setImageScale(baseStyle.getImageScale());
        designBaseStyle.setHorizontalImageAlignment(baseStyle.getHorizontalImageAlignment());
        designBaseStyle.setVerticalImageAlignment(baseStyle.getVerticalImageAlignment());
        designBaseStyle.setHorizontalTextAlignment(baseStyle.getHorizontalTextAlignment());
        designBaseStyle.setVerticalTextAlignment(baseStyle.getVerticalTextAlignment());
        designBaseStyle.setBorder(border(baseStyle.getBorder()));
        designBaseStyle.setPadding(padding(baseStyle.getPadding()));
        designBaseStyle.setFont(transformFont(baseStyle.getFont()));
        designBaseStyle.setRotation(baseStyle.getRotation());
        designBaseStyle.setPattern(baseStyle.getPattern());
        designBaseStyle.setMarkup(baseStyle.getMarkup());
        designBaseStyle.setParagraph(paragraph(baseStyle.getParagraph()));
        designBaseStyle.setLinePen(pen(baseStyle.getLinePen()));
    }

    private DRDesignParagraph paragraph(DRIParagraph paragraph) {
        DRDesignParagraph designParagraph = new DRDesignParagraph();
        designParagraph.setLineSpacing(paragraph.getLineSpacing());
        designParagraph.setLineSpacingSize(paragraph.getLineSpacingSize());
        designParagraph.setFirstLineIndent(paragraph.getFirstLineIndent());
        designParagraph.setLeftIndent(paragraph.getLeftIndent());
        designParagraph.setRightIndent(paragraph.getRightIndent());
        designParagraph.setSpacingBefore(paragraph.getSpacingBefore());
        designParagraph.setSpacingAfter(paragraph.getSpacingAfter());
        designParagraph.setTabStopWidth(paragraph.getTabStopWidth());
        List<DRIDesignTabStop> designTabStops = new ArrayList<>();
        for (DRITabStop tabStop : paragraph.getTabStops()) {
            DRDesignTabStop designTabStop = new DRDesignTabStop();
            designTabStop.setPosition(tabStop.getPosition());
            designTabStop.setAlignment(tabStop.getAlignment());
            designTabStops.add(designTabStop);
        }
        designParagraph.setTabStops(designTabStops);
        return designParagraph;
    }

    private DRDesignBorder border(DRIBorder border) {
        DRDesignBorder designBorder = new DRDesignBorder();
        designBorder.setTopPen(pen(border.getTopPen()));
        designBorder.setLeftPen(pen(border.getLeftPen()));
        designBorder.setBottomPen(pen(border.getBottomPen()));
        designBorder.setRightPen(pen(border.getRightPen()));
        return designBorder;
    }

    private DRDesignPadding padding(DRIPadding padding) {
        DRDesignPadding designPadding = new DRDesignPadding();
        designPadding.setTop(padding.getTop());
        designPadding.setLeft(padding.getLeft());
        designPadding.setBottom(padding.getBottom());
        designPadding.setRight(padding.getRight());
        return designPadding;
    }

    /**
     * <p>pen.</p>
     *
     * @param pen a {@link ch.unibas.medizin.dynamicreports.report.definition.style.DRIPen} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.design.base.style.DRDesignPen} object.
     */
    protected DRDesignPen pen(DRIPen pen) {
        if (pen == null) {
            return null;
        }
        DRDesignPen designPen = new DRDesignPen();
        designPen.setLineWidth(pen.getLineWidth());
        designPen.setLineStyle(pen.getLineStyle());
        designPen.setLineColor(pen.getLineColor());
        return designPen;
    }

    /**
     * <p>getDefaultStyle.</p>
     *
     * @param defaultStyleType a {@link ch.unibas.medizin.dynamicreports.design.constant.DefaultStyleType} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.design.base.style.DRDesignStyle} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    protected DRDesignStyle getDefaultStyle(DefaultStyleType defaultStyleType) throws DRException {
        TemplateTransform templateTransform = accessor.getTemplateTransform();
        return switch (defaultStyleType) {
            case NONE -> null;
            case TEXT -> transformStyle(templateTransform.getTextStyle(), true);
            case COLUMN -> transformStyle(templateTransform.getColumnStyle(true), true);
            case COLUMN_TITLE -> transformStyle(templateTransform.getColumnTitleStyle(), true);
            case GROUP -> transformStyle(templateTransform.getGroupStyle(), true);
            case GROUP_TITLE -> transformStyle(templateTransform.getGroupTitleStyle(), true);
            case SUBTOTAL -> transformStyle(templateTransform.getSubtotalStyle(), true);
            case IMAGE -> transformStyle(templateTransform.getImageStyle(), false);
            case CHART -> transformStyle(templateTransform.getChartStyle(), false);
        };
    }

    private void addStyle(String styleName, DRDesignStyle designStyle) {
        if (designStyle == null) {
            return;
        }
        if (styles.containsKey(designStyle.getName())) {
            if (!styles.get(designStyle.getName()).equals(designStyle)) {
                throw new DRDesignReportException("Duplicate declaration of style \"" + designStyle.getName() + "\"");
            }
            return;
        }
        styles.put(designStyle.getName(), designStyle);
        designStyles.put(styleName, designStyle);
    }

    /**
     * <p>copyStyle.</p>
     *
     * @param toStyle   a {@link ch.unibas.medizin.dynamicreports.report.base.style.DRBaseStyle} object.
     * @param fromStyle a {@link ch.unibas.medizin.dynamicreports.report.definition.style.DRIBaseStyle} object.
     */
    public void copyStyle(DRBaseStyle toStyle, DRIBaseStyle fromStyle) {
        toStyle.setForegroundColor(fromStyle.getForegroundColor());
        toStyle.setBackgroundColor(fromStyle.getBackgroundColor());
        toStyle.setRadius(fromStyle.getRadius());
        toStyle.setImageScale(fromStyle.getImageScale());
        toStyle.setHorizontalImageAlignment(fromStyle.getHorizontalImageAlignment());
        toStyle.setVerticalImageAlignment(fromStyle.getVerticalImageAlignment());
        toStyle.setHorizontalTextAlignment(fromStyle.getHorizontalTextAlignment());
        toStyle.setVerticalTextAlignment(fromStyle.getVerticalTextAlignment());
        toStyle.setBorder((DRBorder) fromStyle.getBorder());
        toStyle.setPadding((DRPadding) fromStyle.getPadding());
        toStyle.setFont((DRFont) fromStyle.getFont());
        toStyle.setRotation(fromStyle.getRotation());
        toStyle.setPattern(fromStyle.getPattern());
        toStyle.setMarkup(fromStyle.getMarkup());
        toStyle.setParagraph((DRParagraph) fromStyle.getParagraph());
        toStyle.setLinePen((DRPen) fromStyle.getLinePen());
    }

    /**
     * <p>Getter for the field <code>styles</code>.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<DRIDesignStyle> getStyles() {
        return styles.values();
    }
}
