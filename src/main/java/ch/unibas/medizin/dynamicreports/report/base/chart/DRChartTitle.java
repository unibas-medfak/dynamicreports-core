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
package ch.unibas.medizin.dynamicreports.report.base.chart;

import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import ch.unibas.medizin.dynamicreports.report.constant.Position;
import ch.unibas.medizin.dynamicreports.report.definition.chart.DRIChartTitle;

import java.io.Serial;

/**
 * <p>DRChartTitle class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DRChartTitle extends DRChartSubtitle implements DRIChartTitle {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private Position position;

    /** {@inheritDoc} */
    @Override
    public Position getPosition() {
        return position;
    }

    /**
     * <p>Setter for the field <code>position</code>.</p>
     *
     * @param position a {@link ch.unibas.medizin.dynamicreports.report.constant.Position} object.
     */
    public void setPosition(Position position) {
        this.position = position;
    }
}
