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

import ch.unibas.medizin.dynamicreports.report.base.expression.AbstractSimpleExpression;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import ch.unibas.medizin.dynamicreports.report.definition.ReportParameters;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;

import java.io.Serial;

/**
 * <p>MultiPageListDataSourceExpression class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class MultiPageListDataSourceExpression extends AbstractSimpleExpression<JRDataSource> {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private final int count;

    /**
     * <p>Constructor for MultiPageListDataSourceExpression.</p>
     *
     * @param count an int.
     */
    public MultiPageListDataSourceExpression(int count) {
        this.count = count;
    }

    /** {@inheritDoc} */
    @Override
    public JRDataSource evaluate(ReportParameters reportParameters) {
        return new JREmptyDataSource(count);
    }

    /** {@inheritDoc} */
    @Override
    public Class<JRDataSource> getValueClass() {
        return JRDataSource.class;
    }
}
