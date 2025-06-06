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
package ch.unibas.medizin.dynamicreports.report.datasource;

import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRRewindableDataSource;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p>DRDataSource class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DRDataSource implements JRRewindableDataSource, Serializable {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private final String[] columns;
    private final List<Map<String, Object>> values;
    private Iterator<Map<String, Object>> iterator;
    private Map<String, Object> currentRecord;

    /**
     * <p>Constructor for DRDataSource.</p>
     *
     * @param columns a {@link java.lang.String} object.
     */
    public DRDataSource(String... columns) {
        this.columns = columns;
        this.values = new ArrayList<>();
    }

    /**
     * <p>add.</p>
     *
     * @param values a {@link java.lang.Object} object.
     */
    public void add(Object... values) {
        Map<String, Object> row = new HashMap<>();
        for (int i = 0; i < values.length; i++) {
            row.put(columns[i], values[i]);
        }
        this.values.add(row);
    }

    /** {@inheritDoc} */
    @Override
    public Object getFieldValue(JRField field) {
        return currentRecord.get(field.getName());
    }

    /** {@inheritDoc} */
    @Override
    public boolean next() {
        if (iterator == null) {
            this.iterator = values.iterator();
        }
        boolean hasNext = iterator.hasNext();
        if (hasNext) {
            currentRecord = iterator.next();
        }
        return hasNext;
    }

    /** {@inheritDoc} */
    @Override
    public void moveFirst() {
        iterator = null;
    }
}
