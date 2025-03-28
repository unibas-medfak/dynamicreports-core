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
package ch.unibas.medizin.dynamicreports.report.builder.tableofcontents;

import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.cmp;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.field;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.hyperLink;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.stl;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.type;

import java.io.Serial;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.unibas.medizin.dynamicreports.jasper.base.tableofcontents.JasperTocHeading;
import ch.unibas.medizin.dynamicreports.report.base.expression.AbstractSimpleExpression;
import ch.unibas.medizin.dynamicreports.report.builder.FieldBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.HyperLinkBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.ReportBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.component.ComponentBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.component.HorizontalListBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.component.TextFieldBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.component.VerticalListBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.expression.SystemMessageExpression;
import ch.unibas.medizin.dynamicreports.report.builder.style.ReportStyleBuilder;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import ch.unibas.medizin.dynamicreports.report.constant.HorizontalTextAlignment;
import ch.unibas.medizin.dynamicreports.report.constant.HyperLinkType;
import ch.unibas.medizin.dynamicreports.report.constant.TableOfContentsPosition;
import ch.unibas.medizin.dynamicreports.report.constant.TextAdjust;
import ch.unibas.medizin.dynamicreports.report.definition.DRITableOfContentsCustomizer;
import ch.unibas.medizin.dynamicreports.report.definition.ReportParameters;

/**
 * <p>TableOfContentsCustomizer class.</p>
 *
 * @author Ricardo Mariaca, Jan Moxter
 * 
 */
public class TableOfContentsCustomizer implements DRITableOfContentsCustomizer {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * Constant <code>dots=""</code>
     */
    protected static String dots;

    static {
        final StringBuilder dots = new StringBuilder(200);
        dots.append(".".repeat(Math.max(0, dots.capacity())));
        TableOfContentsCustomizer.dots = dots.toString();
    }

    protected ReportBuilder<?> report;
    protected List<JasperTocHeading> headingList;
    protected int headings;
    protected int levels;
    protected FieldBuilder<Integer> levelField;
    protected FieldBuilder<String> textField;
    protected FieldBuilder<String> referenceField;
    protected FieldBuilder<Integer> pageIndexField;
    protected HyperLinkBuilder referenceHyperLink;
    protected int pageIndexDigits;
    protected ReportStyleBuilder titleStyle;
    protected ReportStyleBuilder headingStyle;
    protected Map<Integer, ReportStyleBuilder> headingStyles;
    protected Integer textFixedWidth;
    protected Integer dotsFixedWidth;
    protected Integer pageIndexFixedWidth;
    protected TableOfContentsPosition position;

    /**
     * <p>Constructor for TableOfContentsCustomizer.</p>
     */
    public TableOfContentsCustomizer() {
        headingStyles = new HashMap<>();
    }

    /**
     * <p>init.</p>
     */
    protected void init() {
        levelField = field("level", type.integerType());
        textField = field("text", type.stringType());
        referenceField = field("reference", type.stringType());
        pageIndexField = field("pageIndex", type.integerType());

        referenceHyperLink = hyperLink();
        referenceHyperLink.setAnchor(new ReferenceExpression());
        referenceHyperLink.setType(HyperLinkType.LOCAL_ANCHOR);

        pageIndexDigits = String.valueOf(headings).length();

        if (titleStyle == null) {
            titleStyle = stl.style().bold().setFontSize(16).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void setReport(final ReportBuilder<?> report) {
        this.report = report;
    }

    /** {@inheritDoc} */
    @Override
    public void setHeadingList(final List<JasperTocHeading> headingList) {
        this.headingList = headingList;
    }

    /** {@inheritDoc} */
    @Override
    public void setHeadings(final int headings) {
        this.headings = headings;
    }

    /** {@inheritDoc} */
    @Override
    public void setLevels(final int levels) {
        this.levels = levels;
    }

    /** {@inheritDoc} */
    @Override
    public void customize() {
        init();

        report.title(title(), cmp.filler().setFixedHeight(20)).fields(levelField, textField, referenceField, pageIndexField).detail(detailComponent());
    }

    /**
     * <p>title.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.component.ComponentBuilder} object.
     */
    protected ComponentBuilder<?, ?> title() {
        return cmp.text(new SystemMessageExpression("table_of_contents")).setStyle(titleStyle);
    }

    /**
     * <p>detailComponent.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.component.ComponentBuilder} object.
     */
    protected ComponentBuilder<?, ?> detailComponent() {
        final VerticalListBuilder detailComponent = cmp.verticalList();
        for (int i = 0; i < levels; i++) {
            final ComponentBuilder<?, ?> headingComponent = headingComponent(i);
            headingComponent.setPrintWhenExpression(new PrintHeadingExpression(i));
            headingComponent.removeLineWhenBlank();
            detailComponent.add(headingComponent);
        }
        return detailComponent;
    }

    /**
     * <p>headingComponent.</p>
     *
     * @param level an int.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.component.ComponentBuilder} object.
     */
    protected ComponentBuilder<?, ?> headingComponent(final int level) {
        final HorizontalListBuilder headingComponent = cmp.horizontalList();

        if (level > 0) {
            headingComponent.add(cmp.filler().setFixedWidth(level * 10));
        }

        final TextFieldBuilder<String> textComponent = cmp.text(textField).setHyperLink(referenceHyperLink);
        if (textFixedWidth != null) {
            textComponent.setFixedWidth(textFixedWidth);
        }
        headingComponent.add(textComponent);

        if (level > 0) {
            headingComponent.add(cmp.filler().setFixedWidth(level * 10));
        }

        final TextFieldBuilder<String> dotsComponent = cmp.text(dots).setTextAdjust(TextAdjust.CUT_TEXT).setHyperLink(referenceHyperLink);
        if (dotsFixedWidth != null) {
            dotsComponent.setFixedWidth(dotsFixedWidth);
        }
        headingComponent.add(dotsComponent);

        final TextFieldBuilder<Integer> pageIndexComponent = cmp.text(pageIndexField).setHyperLink(referenceHyperLink);
        if (pageIndexFixedWidth != null) {
            pageIndexComponent.setFixedWidth(pageIndexFixedWidth);
        } else {
            pageIndexComponent.setFixedColumns(pageIndexDigits);
        }
        headingComponent.add(pageIndexComponent);

        ReportStyleBuilder headingStyle = headingStyles.get(level);
        if (headingStyle == null) {
            headingStyle = this.headingStyle;
        }
        if (headingStyle != null) {
            textComponent.setStyle(headingStyle);
            dotsComponent.setStyle(headingStyle);
            pageIndexComponent.setStyle(headingStyle);
        }

        return headingComponent;
    }

    /**
     * <p>Setter for the field <code>titleStyle</code>.</p>
     *
     * @param titleStyle a {@link ch.unibas.medizin.dynamicreports.report.builder.style.ReportStyleBuilder} object.
     */
    public void setTitleStyle(final ReportStyleBuilder titleStyle) {
        this.titleStyle = titleStyle;
    }

    /**
     * <p>Setter for the field <code>headingStyle</code>.</p>
     *
     * @param headingStyle a {@link ch.unibas.medizin.dynamicreports.report.builder.style.ReportStyleBuilder} object.
     */
    public void setHeadingStyle(final ReportStyleBuilder headingStyle) {
        this.headingStyle = headingStyle;
    }

    /**
     * <p>Setter for the field <code>headingStyle</code>.</p>
     *
     * @param level        an int.
     * @param headingStyle a {@link ch.unibas.medizin.dynamicreports.report.builder.style.ReportStyleBuilder} object.
     */
    public void setHeadingStyle(final int level, final ReportStyleBuilder headingStyle) {
        this.headingStyles.put(level, headingStyle);
    }

    /**
     * <p>Setter for the field <code>textFixedWidth</code>.</p>
     *
     * @param textFixedWidth a {@link java.lang.Integer} object.
     */
    public void setTextFixedWidth(final Integer textFixedWidth) {
        this.textFixedWidth = textFixedWidth;
    }

    /**
     * <p>Setter for the field <code>dotsFixedWidth</code>.</p>
     *
     * @param dotsFixedWidth a {@link java.lang.Integer} object.
     */
    public void setDotsFixedWidth(final Integer dotsFixedWidth) {
        this.dotsFixedWidth = dotsFixedWidth;
    }

    /**
     * <p>Setter for the field <code>pageIndexFixedWidth</code>.</p>
     *
     * @param pageIndexFixedWidth a {@link java.lang.Integer} object.
     */
    public void setPageIndexFixedWidth(final Integer pageIndexFixedWidth) {
        this.pageIndexFixedWidth = pageIndexFixedWidth;
    }

    /** {@inheritDoc} */
    @Override
    public TableOfContentsPosition getPosition() {
        return position;
    }

    /**
     * <p>Setter for the field <code>position</code>.</p>
     *
     * @param position a {@link ch.unibas.medizin.dynamicreports.report.constant.TableOfContentsPosition} object.
     */
    public void setPosition(final TableOfContentsPosition position) {
        this.position = position;
    }

    protected class ReferenceExpression extends AbstractSimpleExpression<String> {
        @Serial
        private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

        @Override
        public String evaluate(final ReportParameters reportParameters) {
            return reportParameters.getValue(referenceField);
        }
    }

    protected class PrintHeadingExpression extends AbstractSimpleExpression<Boolean> {
        @Serial
        private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

        private final int level;

        public PrintHeadingExpression(final int level) {
            this.level = level;
        }

        @Override
        public Boolean evaluate(final ReportParameters reportParameters) {
            return reportParameters.getValue(levelField) == level;
        }
    }
}
