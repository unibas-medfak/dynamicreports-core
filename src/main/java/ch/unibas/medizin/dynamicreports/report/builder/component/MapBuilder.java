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

import ch.unibas.medizin.dynamicreports.report.base.component.DRMap;
import ch.unibas.medizin.dynamicreports.report.builder.expression.Expressions;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression;

import java.io.Serial;

/**
 * <p>MapBuilder class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class MapBuilder extends DimensionComponentBuilder<MapBuilder, DRMap> {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for MapBuilder.</p>
     */
    protected MapBuilder() {
        super(new DRMap());
    }

    /**
     * <p>setLatitude.</p>
     *
     * @param latitude a {@link java.lang.Float} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.component.MapBuilder} object.
     */
    public MapBuilder setLatitude(Float latitude) {
        getObject().setLatitudeExpression(Expressions.value(latitude));
        return this;
    }

    /**
     * <p>setLatitude.</p>
     *
     * @param latitudeExpression a {@link ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.component.MapBuilder} object.
     */
    public MapBuilder setLatitude(DRIExpression<Float> latitudeExpression) {
        getObject().setLatitudeExpression(latitudeExpression);
        return this;
    }

    /**
     * <p>setLongitude.</p>
     *
     * @param longitude a {@link java.lang.Float} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.component.MapBuilder} object.
     */
    public MapBuilder setLongitude(Float longitude) {
        getObject().setLongitudeExpression(Expressions.value(longitude));
        return this;
    }

    /**
     * <p>setLongitude.</p>
     *
     * @param longitudeExpression a {@link ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.component.MapBuilder} object.
     */
    public MapBuilder setLongitude(DRIExpression<Float> longitudeExpression) {
        getObject().setLongitudeExpression(longitudeExpression);
        return this;
    }

    /**
     * <p>setZoom.</p>
     *
     * @param zoom a {@link java.lang.Integer} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.component.MapBuilder} object.
     */
    public MapBuilder setZoom(Integer zoom) {
        getObject().setZoomExpression(Expressions.value(zoom));
        return this;
    }

    /**
     * <p>setZoom.</p>
     *
     * @param zoomExpression a {@link ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.component.MapBuilder} object.
     */
    public MapBuilder setZoom(DRIExpression<Integer> zoomExpression) {
        getObject().setZoomExpression(zoomExpression);
        return this;
    }
}
