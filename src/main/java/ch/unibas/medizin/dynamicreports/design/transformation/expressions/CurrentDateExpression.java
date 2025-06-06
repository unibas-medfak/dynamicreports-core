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

import ch.unibas.medizin.dynamicreports.report.builder.datatype.DataTypes;
import ch.unibas.medizin.dynamicreports.report.builder.expression.AbstractComplexExpression;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import ch.unibas.medizin.dynamicreports.report.definition.ReportParameters;
import ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression;

import java.io.Serial;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * <p>CurrentDateExpression class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class CurrentDateExpression extends AbstractComplexExpression<String> {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private final String datePattern;

    /**
     * <p>Constructor for CurrentDateExpression.</p>
     *
     * @param currentDateExpression a {@link ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression} object.
     * @param datePattern           a {@link java.lang.String} object.
     */
    public CurrentDateExpression(DRIExpression<String> currentDateExpression, String datePattern) {
        this.datePattern = datePattern;
        addExpression(currentDateExpression);
    }

    /** {@inheritDoc} */
    @Override
    public String evaluate(List<?> values, ReportParameters reportParameters) {
        String pattern = (String) values.getFirst();
        Locale locale = reportParameters.getLocale();
        MessageFormat format = new MessageFormat(pattern, locale);
        String date;
        if (datePattern == null) {
            date = DataTypes.dateType().valueToString(new Date(), locale);
        } else {
            date = new SimpleDateFormat(datePattern, locale).format(new Date());
        }
        String result = format.format(new Object[] {date});
        return result;
    }
}
