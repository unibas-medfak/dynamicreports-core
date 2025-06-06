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
package ch.unibas.medizin.dynamicreports.report.builder.style;

import ch.unibas.medizin.dynamicreports.report.base.style.DRStyle;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import org.apache.commons.lang3.Validate;

import java.io.Serial;

/**
 * <p>StyleBuilder class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class StyleBuilder extends BaseStyleBuilder<StyleBuilder, DRStyle> implements ReportStyleBuilder {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for StyleBuilder.</p>
     */
    protected StyleBuilder() {
        this(new DRStyle());
    }

    /**
     * <p>Constructor for StyleBuilder.</p>
     *
     * @param style a {@link ch.unibas.medizin.dynamicreports.report.base.style.DRStyle} object.
     */
    protected StyleBuilder(DRStyle style) {
        super(style);
    }

    /**
     * <p>conditionalStyles.</p>
     *
     * @param conditionalStyles a {@link ch.unibas.medizin.dynamicreports.report.builder.style.ConditionalStyleBuilder} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.style.StyleBuilder} object.
     */
    public StyleBuilder conditionalStyles(ConditionalStyleBuilder... conditionalStyles) {
        return addConditionalStyle(conditionalStyles);
    }

    /**
     * <p>addConditionalStyle.</p>
     *
     * @param conditionalStyles a {@link ch.unibas.medizin.dynamicreports.report.builder.style.ConditionalStyleBuilder} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.style.StyleBuilder} object.
     */
    public StyleBuilder addConditionalStyle(ConditionalStyleBuilder... conditionalStyles) {
        Validate.notNull(conditionalStyles, "conditionalStyles must not be null");
        Validate.noNullElements(conditionalStyles, "conditionalStyles must not contains null conditionalStyle");
        for (ConditionalStyleBuilder conditionalStyle : conditionalStyles) {
            getObject().addConditionalStyle(conditionalStyle.build());
        }
        return this;
    }

    /**
     * <p>setName.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.style.StyleBuilder} object.
     */
    public StyleBuilder setName(String name) {
        getObject().setName(name);
        return this;
    }

    /**
     * <p>setParentStyle.</p>
     *
     * @param parentStyle a {@link ch.unibas.medizin.dynamicreports.report.builder.style.ReportStyleBuilder} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.style.StyleBuilder} object.
     */
    public StyleBuilder setParentStyle(ReportStyleBuilder parentStyle) {
        if (parentStyle != null) {
            getObject().setParentStyle(parentStyle.build());
        } else {
            getObject().setParentStyle(null);
        }
        return this;
    }
}
