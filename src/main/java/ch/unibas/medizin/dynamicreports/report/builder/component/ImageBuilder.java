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

import ch.unibas.medizin.dynamicreports.report.base.component.DRImage;
import ch.unibas.medizin.dynamicreports.report.builder.expression.Expressions;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import ch.unibas.medizin.dynamicreports.report.constant.HorizontalImageAlignment;
import ch.unibas.medizin.dynamicreports.report.constant.ImageScale;
import ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression;

import java.awt.Image;
import java.io.InputStream;
import java.io.Serial;
import java.net.URL;

/**
 * <p>ImageBuilder class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class ImageBuilder extends HyperLinkComponentBuilder<ImageBuilder, DRImage> {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for ImageBuilder.</p>
     */
    protected ImageBuilder() {
        super(new DRImage());
    }

    /**
     * <p>setImage.</p>
     *
     * @param imageExpression a {@link ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.component.ImageBuilder} object.
     */
    public ImageBuilder setImage(DRIExpression<?> imageExpression) {
        getObject().setImageExpression(imageExpression);
        return this;
    }

    /**
     * <p>setImage.</p>
     *
     * @param imagePath a {@link java.lang.String} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.component.ImageBuilder} object.
     */
    public ImageBuilder setImage(String imagePath) {
        getObject().setImageExpression(Expressions.text(imagePath));
        getObject().setUsingCache(true);
        return this;
    }

    /**
     * <p>setImage.</p>
     *
     * @param image a {@link java.awt.Image} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.component.ImageBuilder} object.
     */
    public ImageBuilder setImage(Image image) {
        getObject().setImageExpression(Expressions.image(image));
        getObject().setUsingCache(true);
        return this;
    }

    /**
     * <p>setImage.</p>
     *
     * @param imageInputStream a {@link java.io.InputStream} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.component.ImageBuilder} object.
     */
    public ImageBuilder setImage(InputStream imageInputStream) {
        getObject().setImageExpression(Expressions.inputStream(imageInputStream));
        getObject().setUsingCache(true);
        return this;
    }

    /**
     * <p>setImage.</p>
     *
     * @param imageUrl a {@link java.net.URL} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.component.ImageBuilder} object.
     */
    public ImageBuilder setImage(URL imageUrl) {
        getObject().setImageExpression(Expressions.url(imageUrl));
        getObject().setUsingCache(true);
        return this;
    }

    /**
     * <p>setImageScale.</p>
     *
     * @param imageScale a {@link ch.unibas.medizin.dynamicreports.report.constant.ImageScale} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.component.ImageBuilder} object.
     */
    public ImageBuilder setImageScale(ImageScale imageScale) {
        getObject().setImageScale(imageScale);
        return this;
    }

    /**
     * <p>setUsingCache.</p>
     *
     * @param usingCache a {@link java.lang.Boolean} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.component.ImageBuilder} object.
     */
    public ImageBuilder setUsingCache(Boolean usingCache) {
        getObject().setUsingCache(usingCache);
        return this;
    }

    /**
     * <p>setLazy.</p>
     *
     * @param lazy a {@link java.lang.Boolean} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.component.ImageBuilder} object.
     */
    public ImageBuilder setLazy(Boolean lazy) {
        getObject().setLazy(lazy);
        return this;
    }

    /**
     * <p>setHorizontalImageAlignment.</p>
     *
     * @param horizontalImageAlignment a {@link ch.unibas.medizin.dynamicreports.report.constant.HorizontalImageAlignment} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.component.ImageBuilder} object.
     */
    public ImageBuilder setHorizontalImageAlignment(HorizontalImageAlignment horizontalImageAlignment) {
        getObject().setHorizontalImageAlignment(horizontalImageAlignment);
        return this;
    }
}
