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
package ch.unibas.medizin.dynamicreports.report.builder.datatype;

import ch.unibas.medizin.dynamicreports.report.base.datatype.AbstractDataType;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import ch.unibas.medizin.dynamicreports.report.constant.HorizontalTextAlignment;
import ch.unibas.medizin.dynamicreports.report.defaults.Defaults;
import ch.unibas.medizin.dynamicreports.report.exception.DRException;

import java.io.Serial;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * <p>DateType class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DateType extends AbstractDataType<Date, Date> {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /** {@inheritDoc} */
    @Override
    public String getPattern() {
        return Defaults.getDefaults().getDateType().getPattern();
    }

    /** {@inheritDoc} */
    @Override
    public HorizontalTextAlignment getHorizontalTextAlignment() {
        return Defaults.getDefaults().getDateType().getHorizontalTextAlignment();
    }

    /** {@inheritDoc} */
    @Override
    public String valueToString(Date value, Locale locale) {
        if (value != null) {
            return new SimpleDateFormat(getPattern(), locale).format(value);
        }
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public Date stringToValue(String value, Locale locale) throws DRException {
        if (value != null) {
            try {
                return new SimpleDateFormat(getPattern(), locale).parse(value);
            } catch (ParseException e) {
                throw new DRException("Unable to convert string value to date", e);
            }
        }
        return null;
    }
}
