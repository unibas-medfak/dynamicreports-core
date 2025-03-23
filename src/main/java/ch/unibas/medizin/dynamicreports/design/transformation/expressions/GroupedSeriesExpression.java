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
package ch.unibas.medizin.dynamicreports.design.transformation.expressions;

import ch.unibas.medizin.dynamicreports.design.transformation.chartcustomizer.GroupedStackedBarRendererCustomizer;
import ch.unibas.medizin.dynamicreports.report.builder.expression.AbstractComplexExpression;
import ch.unibas.medizin.dynamicreports.report.builder.expression.Expressions;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import ch.unibas.medizin.dynamicreports.report.definition.ReportParameters;
import ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression;

import java.io.Serial;
import java.util.List;

/**
 * <p>GroupedSeriesExpression class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class GroupedSeriesExpression extends AbstractComplexExpression<String> {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for GroupedSeriesExpression.</p>
     *
     * @param groupExpression  a {@link ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression} object.
     * @param seriesExpression a {@link ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression} object.
     * @param labelExpression  a {@link ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression} object.
     * @param index            an int.
     */
    public GroupedSeriesExpression(DRIExpression<?> groupExpression, DRIExpression<?> seriesExpression, DRIExpression<?> labelExpression, int index) {
        if (groupExpression != null) {
            addExpression(groupExpression);
        } else {
            addExpression(Expressions.text("group"));
        }
        if (seriesExpression != null) {
            addExpression(seriesExpression);
        } else {
            if (labelExpression != null) {
                addExpression(labelExpression);
            } else {
                addExpression(Expressions.text("serie" + index));
            }
        }
    }

    /** {@inheritDoc} */
    @Override
    public String evaluate(List<?> values, ReportParameters reportParameters) {
        String group = (String) values.get(0);
        String series = (String) values.get(1);
        return group + GroupedStackedBarRendererCustomizer.GROUP_SERIES_KEY + series;
    }
}
