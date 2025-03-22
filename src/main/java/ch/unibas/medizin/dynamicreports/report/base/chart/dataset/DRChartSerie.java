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
package ch.unibas.medizin.dynamicreports.report.base.chart.dataset;

import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import ch.unibas.medizin.dynamicreports.report.definition.DRIHyperLink;
import ch.unibas.medizin.dynamicreports.report.definition.chart.dataset.DRIChartSerie;
import ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression;
import org.apache.commons.lang3.Validate;

import java.io.Serial;

/**
 * <p>Abstract DRChartSerie class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public abstract class DRChartSerie implements DRIChartSerie {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private DRIExpression<?> seriesExpression;
    private DRIHyperLink itemHyperLink;

    /** {@inheritDoc} */
    @Override
    public DRIExpression<?> getSeriesExpression() {
        return seriesExpression;
    }

    /**
     * <p>Setter for the field <code>seriesExpression</code>.</p>
     *
     * @param seriesExpression a {@link ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression} object.
     */
    public void setSeriesExpression(DRIExpression<?> seriesExpression) {
        Validate.notNull(seriesExpression, "seriesExpression must not be null");
        this.seriesExpression = seriesExpression;
    }

    /** {@inheritDoc} */
    @Override
    public DRIHyperLink getItemHyperLink() {
        return itemHyperLink;
    }

    /**
     * <p>Setter for the field <code>itemHyperLink</code>.</p>
     *
     * @param itemHyperLink a {@link ch.unibas.medizin.dynamicreports.report.definition.DRIHyperLink} object.
     */
    public void setItemHyperLink(DRIHyperLink itemHyperLink) {
        this.itemHyperLink = itemHyperLink;
    }

}
