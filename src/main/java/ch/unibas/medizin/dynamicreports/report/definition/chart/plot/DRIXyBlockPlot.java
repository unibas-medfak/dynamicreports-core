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
package ch.unibas.medizin.dynamicreports.report.definition.chart.plot;

import ch.unibas.medizin.dynamicreports.report.constant.RectangleAnchor;

import java.awt.Paint;
import java.util.List;

/**
 * <p>DRIXyBlockPlot interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface DRIXyBlockPlot extends DRIAxisPlot {

    /**
     * <p>getBlockWidth.</p>
     *
     * @return a {@link java.lang.Double} object.
     */
    Double getBlockWidth();

    /**
     * <p>getBlockHeight.</p>
     *
     * @return a {@link java.lang.Double} object.
     */
    Double getBlockHeight();

    /**
     * <p>getBlockAnchor.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.RectangleAnchor} object.
     */
    RectangleAnchor getBlockAnchor();

    /**
     * <p>getDefaultLowerBound.</p>
     *
     * @return a double.
     */
    double getDefaultLowerBound();

    /**
     * <p>getDefaultUpperBound.</p>
     *
     * @return a double.
     */
    double getDefaultUpperBound();

    /**
     * <p>getDefaultPaint.</p>
     *
     * @return a {@link java.awt.Paint} object.
     */
    Paint getDefaultPaint();

    /**
     * <p>getPaintScales.</p>
     *
     * @return a {@link java.util.List} object.
     */
    List<DRIPaintScale> getPaintScales();

}
