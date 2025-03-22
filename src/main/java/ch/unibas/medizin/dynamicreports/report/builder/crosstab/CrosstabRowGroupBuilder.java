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
package ch.unibas.medizin.dynamicreports.report.builder.crosstab;

import ch.unibas.medizin.dynamicreports.report.base.crosstab.DRCrosstabRowGroup;
import ch.unibas.medizin.dynamicreports.report.builder.FieldBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.column.ValueColumnBuilder;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression;

import java.io.Serial;

/**
 * <p>CrosstabRowGroupBuilder class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class CrosstabRowGroupBuilder<T> extends AbstractCrosstabGroupBuilder<CrosstabRowGroupBuilder<T>, DRCrosstabRowGroup<T>, T> {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for CrosstabRowGroupBuilder.</p>
     *
     * @param column a {@link ch.unibas.medizin.dynamicreports.report.builder.column.ValueColumnBuilder} object.
     */
    protected CrosstabRowGroupBuilder(ValueColumnBuilder<?, T> column) {
        super(column, new DRCrosstabRowGroup<>());
    }

    /**
     * <p>Constructor for CrosstabRowGroupBuilder.</p>
     *
     * @param field a {@link ch.unibas.medizin.dynamicreports.report.builder.FieldBuilder} object.
     */
    protected CrosstabRowGroupBuilder(FieldBuilder<T> field) {
        super(field, new DRCrosstabRowGroup<>());
    }

    /**
     * <p>Constructor for CrosstabRowGroupBuilder.</p>
     *
     * @param expression a {@link ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression} object.
     */
    protected CrosstabRowGroupBuilder(DRIExpression<T> expression) {
        super(expression, new DRCrosstabRowGroup<>());
    }

    /**
     * <p>setHeaderWidth.</p>
     *
     * @param headerWidth a {@link java.lang.Integer} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.crosstab.CrosstabRowGroupBuilder} object.
     */
    public CrosstabRowGroupBuilder<T> setHeaderWidth(Integer headerWidth) {
        getObject().setHeaderWidth(headerWidth);
        return this;
    }

    /**
     * <p>setTotalHeaderHeight.</p>
     *
     * @param totalHeaderHeight a {@link java.lang.Integer} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.crosstab.CrosstabRowGroupBuilder} object.
     */
    public CrosstabRowGroupBuilder<T> setTotalHeaderHeight(Integer totalHeaderHeight) {
        getObject().setTotalHeaderHeight(totalHeaderHeight);
        return this;
    }
}
