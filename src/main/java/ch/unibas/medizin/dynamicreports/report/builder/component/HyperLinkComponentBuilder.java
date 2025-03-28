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

import ch.unibas.medizin.dynamicreports.report.base.component.DRHyperLinkComponent;
import ch.unibas.medizin.dynamicreports.report.builder.HyperLinkBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.expression.Expressions;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression;

import java.io.Serial;

/**
 * <p>Abstract HyperLinkComponentBuilder class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public abstract class HyperLinkComponentBuilder<T extends HyperLinkComponentBuilder<T, U>, U extends DRHyperLinkComponent> extends DimensionComponentBuilder<T, U> {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for HyperLinkComponentBuilder.</p>
     *
     * @param component a U object.
     */
    public HyperLinkComponentBuilder(U component) {
        super(component);
    }

    /**
     * <p>setAnchorName.</p>
     *
     * @param anchorName a {@link java.lang.String} object.
     * @return a T object.
     */
    public T setAnchorName(String anchorName) {
        getObject().setAnchorNameExpression(Expressions.text(anchorName));
        return (T) this;
    }

    /**
     * <p>setAnchorName.</p>
     *
     * @param anchorNameExpression a {@link ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression} object.
     * @return a T object.
     */
    public T setAnchorName(DRIExpression<String> anchorNameExpression) {
        getObject().setAnchorNameExpression(anchorNameExpression);
        return (T) this;
    }

    /**
     * <p>setBookmarkLevel.</p>
     *
     * @param bookmarkLevel a {@link java.lang.Integer} object.
     * @return a T object.
     */
    public T setBookmarkLevel(Integer bookmarkLevel) {
        getObject().setBookmarkLevel(bookmarkLevel);
        return (T) this;
    }

    /**
     * <p>setHyperLink.</p>
     *
     * @param hyperLink a {@link ch.unibas.medizin.dynamicreports.report.builder.HyperLinkBuilder} object.
     * @return a T object.
     */
    public T setHyperLink(HyperLinkBuilder hyperLink) {
        if (hyperLink != null) {
            getObject().setHyperLink(hyperLink.getHyperLink());
        } else {
            getObject().setHyperLink(null);
        }
        return (T) this;
    }
}
