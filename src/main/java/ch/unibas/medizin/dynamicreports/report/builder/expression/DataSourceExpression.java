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
import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import ch.unibas.medizin.dynamicreports.report.definition.ReportParameters;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRRewindableDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.Serial;

/**
 * <p>DataSourceExpression class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DataSourceExpression extends AbstractSimpleExpression<JRDataSource> {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private static final Log log = LogFactory.getLog(DataSourceExpression.class);

    private final JRDataSource dataSource;
    private boolean moveFirst = false;

    /**
     * <p>Constructor for DataSourceExpression.</p>
     *
     * @param dataSource a {@link net.sf.jasperreports.engine.JRDataSource} object.
     */
    public DataSourceExpression(JRDataSource dataSource) {
        this.dataSource = dataSource;
    }

    /** {@inheritDoc} */
    @Override
    public JRDataSource evaluate(ReportParameters reportParameters) {
        if (moveFirst && dataSource != null && dataSource instanceof JRRewindableDataSource) {
            try {
                ((JRRewindableDataSource) dataSource).moveFirst();
            } catch (final JRException e) {
                log.error(e.getMessage());
            }
        }
        moveFirst = true;
        return dataSource;
    }

    /** {@inheritDoc} */
    @Override
    public Class<JRDataSource> getValueClass() {
        return JRDataSource.class;
    }
}
