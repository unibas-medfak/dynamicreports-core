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
package ch.unibas.medizin.dynamicreports.design.base.chart.plot;

import ch.unibas.medizin.dynamicreports.design.definition.chart.plot.DRIDesignLinePlot;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;

import java.io.Serial;

/**
 * <p>DRDesignLinePlot class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DRDesignLinePlot extends DRDesignAxisPlot implements DRIDesignLinePlot {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private Boolean showShapes;
    private Boolean showLines;

    /** {@inheritDoc} */
    @Override
    public Boolean getShowShapes() {
        return showShapes;
    }

    /**
     * <p>Setter for the field <code>showShapes</code>.</p>
     *
     * @param showShapes a {@link java.lang.Boolean} object.
     */
    public void setShowShapes(Boolean showShapes) {
        this.showShapes = showShapes;
    }

    /** {@inheritDoc} */
    @Override
    public Boolean getShowLines() {
        return showLines;
    }

    /**
     * <p>Setter for the field <code>showLines</code>.</p>
     *
     * @param showLines a {@link java.lang.Boolean} object.
     */
    public void setShowLines(Boolean showLines) {
        this.showLines = showLines;
    }
}
