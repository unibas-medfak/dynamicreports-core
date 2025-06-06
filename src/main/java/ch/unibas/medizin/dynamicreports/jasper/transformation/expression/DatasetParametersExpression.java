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
package ch.unibas.medizin.dynamicreports.jasper.transformation.expression;

import ch.unibas.medizin.dynamicreports.design.base.expression.AbstractDesignSimpleExpression;
import ch.unibas.medizin.dynamicreports.jasper.base.JasperReportParameters;
import ch.unibas.medizin.dynamicreports.report.ReportUtils;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import ch.unibas.medizin.dynamicreports.report.definition.ReportParameters;

import java.io.Serial;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>DatasetParametersExpression class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DatasetParametersExpression extends AbstractDesignSimpleExpression {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private final Map<String, Object> parameters;

    /**
     * <p>Constructor for DatasetParametersExpression.</p>
     *
     * @param parameters a {@link java.util.Map} object.
     */
    public DatasetParametersExpression(Map<String, Object> parameters) {
        super(ReportUtils.generateUniqueName("datasetParametersExpression"));
        this.parameters = parameters;
    }

    /** {@inheritDoc} */
    @Override
    public Object evaluate(ReportParameters reportParameters) {
        Map<String, Object> parameters = new HashMap<>(this.parameters);
        parameters.put(JasperReportParameters.MASTER_REPORT_PARAMETERS, reportParameters);
        return parameters;
    }

    /** {@inheritDoc} */
    @Override
    public Class<?> getValueClass() {
        return Map.class;
    }
}
