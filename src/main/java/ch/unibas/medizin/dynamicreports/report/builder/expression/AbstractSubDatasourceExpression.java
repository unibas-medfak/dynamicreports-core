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

import ch.unibas.medizin.dynamicreports.report.ReportUtils;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import ch.unibas.medizin.dynamicreports.report.definition.ReportParameters;
import ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression;
import net.sf.jasperreports.engine.JRDataSource;
import org.apache.commons.lang3.Validate;

import java.io.Serial;
import java.util.List;

/**
 * <p>Abstract AbstractSubDatasourceExpression class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public abstract class AbstractSubDatasourceExpression<T> extends AbstractComplexExpression<JRDataSource> {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for AbstractSubDatasourceExpression.</p>
     *
     * @param fieldName a {@link java.lang.String} object.
     */
    protected AbstractSubDatasourceExpression(String fieldName) {
        Validate.notNull(fieldName, "fieldName must not be null");
        addExpression(fieldName, getSubDatasourceDataClass());
    }

    /**
     * <p>Constructor for AbstractSubDatasourceExpression.</p>
     *
     * @param expression a {@link ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression} object.
     */
    protected AbstractSubDatasourceExpression(DRIExpression<? extends T> expression) {
        addExpression(expression);
    }

    /** {@inheritDoc} */
    @Override
    public JRDataSource evaluate(List<?> values, ReportParameters reportParameters) {
        return createSubDatasource((T) values.getFirst());
    }

    /** {@inheritDoc} */
    @Override
    public Class<? super JRDataSource> getValueClass() {
        return JRDataSource.class;
    }

    /**
     * <p>getSubDatasourceDataClass.</p>
     *
     * @return a {@link java.lang.Class} object.
     */
    protected Class<T> getSubDatasourceDataClass() {
        return (Class<T>) ReportUtils.getGenericClass(this, 0);
    }

    /**
     * <p>createSubDatasource.</p>
     *
     * @param data a T object.
     * @return a {@link net.sf.jasperreports.engine.JRDataSource} object.
     */
    protected abstract JRDataSource createSubDatasource(T data);
}
