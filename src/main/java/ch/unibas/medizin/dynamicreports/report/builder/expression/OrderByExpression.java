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
package ch.unibas.medizin.dynamicreports.report.builder.expression;

import ch.unibas.medizin.dynamicreports.report.base.expression.AbstractSimpleExpression;
import ch.unibas.medizin.dynamicreports.report.builder.crosstab.CrosstabMeasureBuilder;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import ch.unibas.medizin.dynamicreports.report.definition.ReportParameters;

/**
 * <p>OrderByExpression class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class OrderByExpression extends AbstractSimpleExpression<Comparable<?>> {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private CrosstabMeasureBuilder<? extends Comparable<?>> measure;

    /**
     * <p>Constructor for OrderByExpression.</p>
     *
     * @param measure a {@link ch.unibas.medizin.dynamicreports.report.builder.crosstab.CrosstabMeasureBuilder} object.
     */
    public OrderByExpression(CrosstabMeasureBuilder<? extends Comparable<?>> measure) {
        this.measure = measure;
    }

    /** {@inheritDoc} */
    @Override
    public Comparable<?> evaluate(ReportParameters reportParameters) {
        return reportParameters.getValue(measure);
    }
}
