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
package ch.unibas.medizin.dynamicreports.report.builder.chart;

import ch.unibas.medizin.dynamicreports.report.base.chart.dataset.DRCategoryChartSerie;
import ch.unibas.medizin.dynamicreports.report.builder.FieldBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.VariableBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.column.ValueColumnBuilder;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression;

import java.io.Serial;

/**
 * <p>CategoryChartSerieBuilder class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class CategoryChartSerieBuilder extends AbstractCategoryChartSerieBuilder<CategoryChartSerieBuilder, DRCategoryChartSerie> {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for CategoryChartSerieBuilder.</p>
     *
     * @param column a {@link ch.unibas.medizin.dynamicreports.report.builder.column.ValueColumnBuilder} object.
     */
    protected CategoryChartSerieBuilder(ValueColumnBuilder<?, ? extends Number> column) {
        super(new DRCategoryChartSerie(), column);
    }

    /**
     * <p>Constructor for CategoryChartSerieBuilder.</p>
     *
     * @param field a {@link ch.unibas.medizin.dynamicreports.report.builder.FieldBuilder} object.
     */
    protected CategoryChartSerieBuilder(FieldBuilder<? extends Number> field) {
        super(new DRCategoryChartSerie(), field);
    }

    /**
     * <p>Constructor for CategoryChartSerieBuilder.</p>
     *
     * @param valueExpression a {@link ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression} object.
     */
    protected CategoryChartSerieBuilder(DRIExpression<? extends Number> valueExpression) {
        super(new DRCategoryChartSerie(), valueExpression);
    }

    /**
     * <p>Constructor for CategoryChartSerieBuilder.</p>
     *
     * @param variable a {@link ch.unibas.medizin.dynamicreports.report.builder.VariableBuilder} object.
     */
    protected CategoryChartSerieBuilder(VariableBuilder<? extends Number> variable) {
        super(new DRCategoryChartSerie(), variable);
    }
}
