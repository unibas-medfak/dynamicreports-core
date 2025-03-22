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
package ch.unibas.medizin.dynamicreports.jasper.base;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang3.Validate;

import ch.unibas.medizin.dynamicreports.jasper.exception.JasperDesignException;
import ch.unibas.medizin.dynamicreports.report.base.style.DRBaseStyle;
import ch.unibas.medizin.dynamicreports.report.base.style.DRBorder;
import ch.unibas.medizin.dynamicreports.report.base.style.DRConditionalStyle;
import ch.unibas.medizin.dynamicreports.report.base.style.DRFont;
import ch.unibas.medizin.dynamicreports.report.base.style.DRPadding;
import ch.unibas.medizin.dynamicreports.report.base.style.DRParagraph;
import ch.unibas.medizin.dynamicreports.report.base.style.DRPen;
import ch.unibas.medizin.dynamicreports.report.base.style.DRStyle;
import ch.unibas.medizin.dynamicreports.report.base.style.DRTabStop;
import ch.unibas.medizin.dynamicreports.report.builder.expression.Expressions;
import ch.unibas.medizin.dynamicreports.report.constant.HorizontalImageAlignment;
import ch.unibas.medizin.dynamicreports.report.constant.HorizontalTextAlignment;
import ch.unibas.medizin.dynamicreports.report.constant.ImageScale;
import ch.unibas.medizin.dynamicreports.report.constant.LineSpacing;
import ch.unibas.medizin.dynamicreports.report.constant.LineStyle;
import ch.unibas.medizin.dynamicreports.report.constant.Markup;
import ch.unibas.medizin.dynamicreports.report.constant.Rotation;
import ch.unibas.medizin.dynamicreports.report.constant.TabStopAlignment;
import ch.unibas.medizin.dynamicreports.report.constant.VerticalImageAlignment;
import ch.unibas.medizin.dynamicreports.report.constant.VerticalTextAlignment;
import ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression;
import ch.unibas.medizin.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRConditionalStyle;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRLineBox;
import net.sf.jasperreports.engine.JRParagraph;
import net.sf.jasperreports.engine.JRPen;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.JRTemplate;
import net.sf.jasperreports.engine.TabStop;
import net.sf.jasperreports.engine.type.HorizontalImageAlignEnum;
import net.sf.jasperreports.engine.type.HorizontalTextAlignEnum;
import net.sf.jasperreports.engine.type.LineSpacingEnum;
import net.sf.jasperreports.engine.type.LineStyleEnum;
import net.sf.jasperreports.engine.type.RotationEnum;
import net.sf.jasperreports.engine.type.ScaleImageEnum;
import net.sf.jasperreports.engine.type.TabStopAlignEnum;
import net.sf.jasperreports.engine.type.VerticalImageAlignEnum;
import net.sf.jasperreports.engine.type.VerticalTextAlignEnum;
import net.sf.jasperreports.engine.xml.JRXmlTemplateLoader;

/**
 * <p>JasperTemplateStyleLoader class.</p>
 *
 * @author Ricardo Mariaca
 *
 */
public class JasperTemplateStyleLoader {

    /**
     * <p>loadStyles.</p>
     *
     * @param file a {@link java.io.File} object.
     * @return an array of {@link ch.unibas.medizin.dynamicreports.report.base.style.DRStyle} objects.
     */
    public static DRStyle[] loadStyles(File file) {
        Validate.notNull(file, "file must not be null");
        return loadStyles(JRXmlTemplateLoader.load(file));
    }

    /**
     * <p>loadStyles.</p>
     *
     * @param fileName a {@link java.lang.String} object.
     * @return an array of {@link ch.unibas.medizin.dynamicreports.report.base.style.DRStyle} objects.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    public static DRStyle[] loadStyles(String fileName) throws DRException {
        Validate.notNull(fileName, "fileName must not be null");
        try {
            return loadStyles(JRXmlTemplateLoader.load(fileName));
        } catch (final JRException e) {
            throw new DRException(e);
        }
    }

    /**
     * <p>loadStyles.</p>
     *
     * @param inputStream a {@link java.io.InputStream} object.
     * @return an array of {@link ch.unibas.medizin.dynamicreports.report.base.style.DRStyle} objects.
     */
    public static DRStyle[] loadStyles(InputStream inputStream) {
        Validate.notNull(inputStream, "inputStream must not be null");
        return loadStyles(JRXmlTemplateLoader.load(inputStream));
    }

    /**
     * <p>loadStyles.</p>
     *
     * @param url a {@link java.net.URL} object.
     * @return an array of {@link ch.unibas.medizin.dynamicreports.report.base.style.DRStyle} objects.
     */
    public static DRStyle[] loadStyles(URL url) {
        Validate.notNull(url, "url must not be null");
        return loadStyles(JRXmlTemplateLoader.load(url));
    }

    private static DRStyle[] loadStyles(JRTemplate template) {
        Validate.notNull(template, "template must not be null");
        final JRStyle[] jrStyles = template.getStyles();
        final DRStyle[] styles = new DRStyle[jrStyles.length];
        for (int i = 0; i < jrStyles.length; i++) {
            final JRStyle jrStyle = jrStyles[i];
            styles[i] = convertStyle(jrStyle);
        }
        // when reading from the file itself jasperreport does not build the hirachy since version 7
        Map<String, DRStyle> styleMap = Arrays.stream(styles).collect(Collectors.toMap(DRStyle::getName, Function.identity()));
        Arrays.stream(styles).forEach((style) -> {
            if (style.getStyleNameReference() != null && style.getParentStyle() == null && styleMap.containsKey(style.getStyleNameReference())) {
                style.setParentStyle(styleMap.get(style.getStyleNameReference()));
            }
        });
        return styles;
    }

    private static DRStyle convertStyle(JRStyle jrStyle) {
        final DRStyle style = new DRStyle();
        abstractStyle(jrStyle, style);
        style.setName(jrStyle.getName());
        if (jrStyle.getStyleNameReference() != null) {
            style.setStyleNameReference(jrStyle.getStyleNameReference());
        }
        for (final JRConditionalStyle jrConditionalStyle : jrStyle.getConditionalStyles()) {
            style.addConditionalStyle(conditionalStyle(jrConditionalStyle));
        }
        return style;
    }

    private static DRConditionalStyle conditionalStyle(JRConditionalStyle jrConditionalStyle) {
        final DRIExpression<Boolean> expression = Expressions.jasperSyntax(jrConditionalStyle.getConditionExpression().getText(), Boolean.class);
        final DRConditionalStyle conditionalStyle = new DRConditionalStyle(expression);
        abstractStyle(jrConditionalStyle, conditionalStyle);
        return conditionalStyle;
    }

    private static void abstractStyle(JRStyle jrStyle, DRBaseStyle style) {
        style.setForegroundColor(jrStyle.getOwnForecolor());
        style.setBackgroundColor(jrStyle.getOwnBackcolor());
        style.setRadius(jrStyle.getOwnRadius());
        style.setImageScale(imageScale(jrStyle.getOwnScaleImage()));
        style.setHorizontalImageAlignment(horizontalImageAlignment(jrStyle.getOwnHorizontalImageAlign()));
        style.setVerticalImageAlignment(verticalImageAlignment(jrStyle.getOwnVerticalImageAlign()));
        style.setHorizontalTextAlignment(horizontalTextAlignment(jrStyle.getOwnHorizontalTextAlign()));
        style.setVerticalTextAlignment(verticalTextAlignment(jrStyle.getOwnVerticalTextAlign()));
        border(jrStyle.getLineBox(), style.getBorder());
        padding(jrStyle.getLineBox(), style.getPadding());
        font(jrStyle, style.getFont());
        style.setRotation(rotation(jrStyle.getOwnRotation()));
        style.setPattern(jrStyle.getOwnPattern());
        style.setMarkup(markup(jrStyle.getOwnMarkup()));
        paragraph(jrStyle.getParagraph(), style.getParagraph());
        pen(jrStyle.getLinePen(), style.getLinePen());
    }

    private static void paragraph(JRParagraph jrParagraph, DRParagraph paragraph) {
        paragraph.setLineSpacing(lineSpacing(jrParagraph.getOwnLineSpacing()));
        paragraph.setLineSpacingSize(jrParagraph.getOwnLineSpacingSize());
        paragraph.setFirstLineIndent(jrParagraph.getOwnFirstLineIndent());
        paragraph.setLeftIndent(jrParagraph.getOwnLeftIndent());
        paragraph.setRightIndent(jrParagraph.getOwnRightIndent());
        paragraph.setSpacingBefore(jrParagraph.getOwnSpacingBefore());
        paragraph.setSpacingAfter(jrParagraph.getOwnSpacingAfter());
        paragraph.setTabStopWidth(jrParagraph.getOwnTabStopWidth());
        if (jrParagraph.getOwnTabStops() != null) {
            for (final TabStop jrTabStop : jrParagraph.getOwnTabStops()) {
                final DRTabStop tabStop = new DRTabStop();
                tabStop.setPosition(jrTabStop.getPosition());
                tabStop.setAlignment(tabStopAlignment(jrTabStop.getAlignment()));
                paragraph.getTabStops().add(tabStop);
            }
        }
    }

    /**
     * <p>pen.</p>
     *
     * @param jrPen a {@link net.sf.jasperreports.engine.JRPen} object.
     * @param pen   a {@link ch.unibas.medizin.dynamicreports.report.base.style.DRPen} object.
     */
    protected static void pen(JRPen jrPen, DRPen pen) {
        pen.setLineColor(jrPen.getOwnLineColor());
        pen.setLineStyle(lineStyle(jrPen.getOwnLineStyle()));
        pen.setLineWidth(jrPen.getOwnLineWidth());
    }

    private static void border(JRLineBox jrLineBox, DRBorder border) {
        pen(jrLineBox.getLeftPen(), border.getLeftPen());
        pen(jrLineBox.getRightPen(), border.getRightPen());
        pen(jrLineBox.getTopPen(), border.getTopPen());
        pen(jrLineBox.getBottomPen(), border.getBottomPen());
    }

    private static void padding(JRLineBox jrLineBox, DRPadding padding) {
        padding.setLeft(jrLineBox.getOwnLeftPadding());
        padding.setRight(jrLineBox.getOwnRightPadding());
        padding.setTop(jrLineBox.getOwnTopPadding());
        padding.setBottom(jrLineBox.getOwnBottomPadding());
    }

    private static void font(JRStyle jrStyle, DRFont font) {
        font.setFontName(jrStyle.getOwnFontName());
        font.setBold(jrStyle.isOwnBold());
        font.setItalic(jrStyle.isOwnItalic());
        font.setFontSize(jrStyle.getOwnFontSize() == null ? null : jrStyle.getOwnFontSize().intValue());
        font.setStrikeThrough(jrStyle.isOwnStrikeThrough());
        font.setUnderline(jrStyle.isOwnUnderline());
        font.setPdfFontName(jrStyle.getOwnPdfFontName());
        font.setPdfEncoding(jrStyle.getOwnPdfEncoding());
        font.setPdfEmbedded(jrStyle.isOwnPdfEmbedded());
    }

    private static LineStyle lineStyle(LineStyleEnum lineStyle) {
        if (lineStyle == null) {
            return null;
        }

        return switch (lineStyle) {
            case SOLID -> LineStyle.SOLID;
            case DASHED -> LineStyle.DASHED;
            case DOTTED -> LineStyle.DOTTED;
            case DOUBLE -> LineStyle.DOUBLE;
        };
    }

    private static ImageScale imageScale(ScaleImageEnum imageScale) {
        if (imageScale == null) {
            return null;
        }

        return switch (imageScale) {
            case CLIP -> ImageScale.CLIP;
            case FILL_FRAME -> ImageScale.FILL_FRAME;
            case RETAIN_SHAPE -> ImageScale.RETAIN_SHAPE;
            case REAL_HEIGHT -> ImageScale.REAL_HEIGHT;
            case REAL_SIZE -> ImageScale.REAL_SIZE;
        };
    }

    private static HorizontalImageAlignment horizontalImageAlignment(HorizontalImageAlignEnum horizontalImageAlignment) {
        if (horizontalImageAlignment == null) {
            return null;
        }

        return switch (horizontalImageAlignment) {
            case LEFT -> HorizontalImageAlignment.LEFT;
            case CENTER -> HorizontalImageAlignment.CENTER;
            case RIGHT -> HorizontalImageAlignment.RIGHT;
        };
    }

    private static VerticalImageAlignment verticalImageAlignment(VerticalImageAlignEnum verticalImageAlignment) {
        if (verticalImageAlignment == null) {
            return null;
        }

        return switch (verticalImageAlignment) {
            case TOP -> VerticalImageAlignment.TOP;
            case MIDDLE -> VerticalImageAlignment.MIDDLE;
            case BOTTOM -> VerticalImageAlignment.BOTTOM;
        };
    }

    private static HorizontalTextAlignment horizontalTextAlignment(HorizontalTextAlignEnum horizontalTextAlignment) {
        if (horizontalTextAlignment == null) {
            return null;
        }

        return switch (horizontalTextAlignment) {
            case LEFT -> HorizontalTextAlignment.LEFT;
            case CENTER -> HorizontalTextAlignment.CENTER;
            case RIGHT -> HorizontalTextAlignment.RIGHT;
            case JUSTIFIED -> HorizontalTextAlignment.JUSTIFIED;
        };
    }

    private static VerticalTextAlignment verticalTextAlignment(VerticalTextAlignEnum verticalTextAlignment) {
        if (verticalTextAlignment == null) {
            return null;
        }

        return switch (verticalTextAlignment) {
            case TOP -> VerticalTextAlignment.TOP;
            case MIDDLE -> VerticalTextAlignment.MIDDLE;
            case BOTTOM -> VerticalTextAlignment.BOTTOM;
            case JUSTIFIED -> VerticalTextAlignment.JUSTIFIED;
        };
    }

    private static Markup markup(String markup) {
        if (markup == null) {
            return null;
        }

        return switch (markup) {
            case "none" -> Markup.NONE;
            case "styled" -> Markup.STYLED;
            case "rtf" -> Markup.RTF;
            case "html" -> Markup.HTML;
            default -> throw new JasperDesignException("Markup " + markup + " not supported");
        };
    }

    private static LineSpacing lineSpacing(LineSpacingEnum lineSpacing) {
        if (lineSpacing == null) {
            return null;
        }

        return switch (lineSpacing) {
            case SINGLE -> LineSpacing.SINGLE;
            case ONE_AND_HALF -> LineSpacing.ONE_AND_HALF;
            case DOUBLE -> LineSpacing.DOUBLE;
            case AT_LEAST -> LineSpacing.AT_LEAST;
            case FIXED -> LineSpacing.FIXED;
            case PROPORTIONAL -> LineSpacing.PROPORTIONAL;
        };
    }

    /**
     * <p>rotation.</p>
     *
     * @param rotation a {@link net.sf.jasperreports.engine.type.RotationEnum} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.Rotation} object.
     */
    protected static Rotation rotation(RotationEnum rotation) {
        if (rotation == null) {
            return null;
        }

        return switch (rotation) {
            case NONE -> Rotation.NONE;
            case LEFT -> Rotation.LEFT;
            case RIGHT -> Rotation.RIGHT;
            case UPSIDE_DOWN -> Rotation.UPSIDE_DOWN;
        };
    }

    private static TabStopAlignment tabStopAlignment(TabStopAlignEnum alignment) {
        return switch (alignment) {
            case LEFT -> TabStopAlignment.LEFT;
            case CENTER -> TabStopAlignment.CENTER;
            case RIGHT -> TabStopAlignment.RIGHT;
        };
    }
}
