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

import ch.unibas.medizin.dynamicreports.report.builder.expression.AbstractComplexExpression;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import ch.unibas.medizin.dynamicreports.report.definition.ReportParameters;
import ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression;

import java.io.Serial;
import java.util.List;

/**
 * <p>TocReferenceLinkExpression class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class TocReferenceLinkExpression extends AbstractComplexExpression<String> {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private final String expressionName;
    private final boolean customId;

    /**
     * <p>Constructor for TocReferenceLinkExpression.</p>
     *
     * @param expressionName       a {@link java.lang.String} object.
     * @param anchorNameExpression a {@link ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression} object.
     */
    public TocReferenceLinkExpression(String expressionName, DRIExpression<String> anchorNameExpression) {
        this.expressionName = expressionName;
        customId = anchorNameExpression != null;
        if (anchorNameExpression != null) {
            addExpression(anchorNameExpression);
        }
    }

    /** {@inheritDoc} */
    @Override
    public String evaluate(List<?> values, ReportParameters reportParameters) {
        String id;
        if (customId) {
            id = (String) values.getFirst();
        } else {
            id = expressionName + "_" + reportParameters.getReportRowNumber();
        }
        return id;
    }
}
