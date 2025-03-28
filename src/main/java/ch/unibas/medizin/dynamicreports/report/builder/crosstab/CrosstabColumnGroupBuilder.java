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

import ch.unibas.medizin.dynamicreports.report.base.crosstab.DRCrosstabColumnGroup;
import ch.unibas.medizin.dynamicreports.report.builder.FieldBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.column.ValueColumnBuilder;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression;

import java.io.Serial;

/**
 * <p>CrosstabColumnGroupBuilder class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class CrosstabColumnGroupBuilder<T> extends AbstractCrosstabGroupBuilder<CrosstabColumnGroupBuilder<T>, DRCrosstabColumnGroup<T>, T> {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for CrosstabColumnGroupBuilder.</p>
     *
     * @param column a {@link ch.unibas.medizin.dynamicreports.report.builder.column.ValueColumnBuilder} object.
     */
    protected CrosstabColumnGroupBuilder(ValueColumnBuilder<?, T> column) {
        super(column, new DRCrosstabColumnGroup<>());
    }

    /**
     * <p>Constructor for CrosstabColumnGroupBuilder.</p>
     *
     * @param field a {@link ch.unibas.medizin.dynamicreports.report.builder.FieldBuilder} object.
     */
    protected CrosstabColumnGroupBuilder(FieldBuilder<T> field) {
        super(field, new DRCrosstabColumnGroup<>());
    }

    /**
     * <p>Constructor for CrosstabColumnGroupBuilder.</p>
     *
     * @param expression a {@link ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression} object.
     */
    protected CrosstabColumnGroupBuilder(DRIExpression<T> expression) {
        super(expression, new DRCrosstabColumnGroup<>());
    }

    /**
     * <p>setHeaderHeight.</p>
     *
     * @param headerHeight a {@link java.lang.Integer} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.crosstab.CrosstabColumnGroupBuilder} object.
     */
    public CrosstabColumnGroupBuilder<T> setHeaderHeight(Integer headerHeight) {
        getObject().setHeaderHeight(headerHeight);
        return this;
    }

    /**
     * <p>setTotalHeaderWidth.</p>
     *
     * @param totalHeaderWidth a {@link java.lang.Integer} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.crosstab.CrosstabColumnGroupBuilder} object.
     */
    public CrosstabColumnGroupBuilder<T> setTotalHeaderWidth(Integer totalHeaderWidth) {
        getObject().setTotalHeaderWidth(totalHeaderWidth);
        return this;
    }
}
