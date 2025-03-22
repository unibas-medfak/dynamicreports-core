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

import ch.unibas.medizin.dynamicreports.report.base.DRTableOfContentsHeading;
import ch.unibas.medizin.dynamicreports.report.builder.AbstractBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.expression.Expressions;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression;

import java.io.Serial;

/**
 * <p>TableOfContentsHeadingBuilder class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class TableOfContentsHeadingBuilder extends AbstractBuilder<TableOfContentsHeadingBuilder, DRTableOfContentsHeading> {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for TableOfContentsHeadingBuilder.</p>
     */
    public TableOfContentsHeadingBuilder() {
        super(new DRTableOfContentsHeading());
    }

    /**
     * <p>setParentHeading.</p>
     *
     * @param parentHeading a {@link ch.unibas.medizin.dynamicreports.report.builder.tableofcontents.TableOfContentsHeadingBuilder} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.tableofcontents.TableOfContentsHeadingBuilder} object.
     */
    public TableOfContentsHeadingBuilder setParentHeading(TableOfContentsHeadingBuilder parentHeading) {
        if (parentHeading != null) {
            getObject().setParentHeading(parentHeading.build());
        } else {
            getObject().setParentHeading(null);
        }
        return this;
    }

    /**
     * <p>setLabel.</p>
     *
     * @param label a {@link java.lang.String} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.tableofcontents.TableOfContentsHeadingBuilder} object.
     */
    public TableOfContentsHeadingBuilder setLabel(String label) {
        this.getObject().setLabelExpression(Expressions.text(label));
        return this;
    }

    /**
     * <p>setLabel.</p>
     *
     * @param labelExpression a {@link ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.tableofcontents.TableOfContentsHeadingBuilder} object.
     */
    public TableOfContentsHeadingBuilder setLabel(DRIExpression<String> labelExpression) {
        this.getObject().setLabelExpression(labelExpression);
        return this;
    }

    /**
     * <p>setCustomValue.</p>
     *
     * @param customValue a {@link java.lang.Object} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.tableofcontents.TableOfContentsHeadingBuilder} object.
     */
    public TableOfContentsHeadingBuilder setCustomValue(Object customValue) {
        this.getObject().setCustomValueExpression(Expressions.value(customValue));
        return this;
    }

    /**
     * <p>setCustomValue.</p>
     *
     * @param customValueExpression a {@link ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.tableofcontents.TableOfContentsHeadingBuilder} object.
     */
    public TableOfContentsHeadingBuilder setCustomValue(DRIExpression<?> customValueExpression) {
        this.getObject().setCustomValueExpression(customValueExpression);
        return this;
    }

    /**
     * <p>getTableOfContentsHeading.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.base.DRTableOfContentsHeading} object.
     */
    public DRTableOfContentsHeading getTableOfContentsHeading() {
        return build();
    }
}
