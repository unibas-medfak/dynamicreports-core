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
package ch.unibas.medizin.dynamicreports.design.transformation.chartcustomizer;

import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetChangeListener;
import org.jfree.data.general.DatasetGroup;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * <p>PercentageCategoryDataset class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class PercentageCategoryDataset implements CategoryDataset, Serializable {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    protected final CategoryDataset dataset;

    /**
     * <p>Constructor for PercentageCategoryDataset.</p>
     *
     * @param dataset a {@link org.jfree.data.category.CategoryDataset} object.
     */
    public PercentageCategoryDataset(CategoryDataset dataset) {
        this.dataset = dataset;
    }

    /** {@inheritDoc} */
    @Override
    public Comparable<?> getRowKey(int row) {
        return dataset.getRowKey(row);
    }

    /** {@inheritDoc} */
    @Override
    public int getRowIndex(Comparable key) {
        return dataset.getRowIndex(key);
    }

    /** {@inheritDoc} */
    @Override
    public List<?> getRowKeys() {
        return dataset.getRowKeys();
    }

    /** {@inheritDoc} */
    @Override
    public Comparable<?> getColumnKey(int column) {
        return dataset.getColumnKey(column);
    }

    /** {@inheritDoc} */
    @Override
    public int getColumnIndex(Comparable key) {
        return dataset.getColumnIndex(key);
    }

    /** {@inheritDoc} */
    @Override
    public List<?> getColumnKeys() {
        return dataset.getColumnKeys();
    }

    /** {@inheritDoc} */
    @Override
    public Number getValue(Comparable rowKey, Comparable columnKey) {
        return getValue(getRowIndex(rowKey), getColumnIndex(columnKey));
    }

    /** {@inheritDoc} */
    @Override
    public int getRowCount() {
        return dataset.getRowCount();
    }

    /** {@inheritDoc} */
    @Override
    public int getColumnCount() {
        return dataset.getColumnCount();
    }

    /** {@inheritDoc} */
    @Override
    public Number getValue(int row, int column) {
        double total = 0;
        for (int i = 0; i < getRowCount(); i++) {
            Number value = dataset.getValue(i, column);
            if (value != null) {
                total += value.doubleValue();
            }
        }
        Number value = dataset.getValue(row, column);
        if (value == null) {
            return 0;
        }
        double actual = value.doubleValue();
        if (total > 0) {
            return actual / total * 100;
        }
        return 0;
    }

    /** {@inheritDoc} */
    @Override
    public void addChangeListener(DatasetChangeListener listener) {
        dataset.addChangeListener(listener);
    }

    /** {@inheritDoc} */
    @Override
    public void removeChangeListener(DatasetChangeListener listener) {
        dataset.removeChangeListener(listener);
    }

    /** {@inheritDoc} */
    @Override
    public DatasetGroup getGroup() {
        return dataset.getGroup();
    }

    /** {@inheritDoc} */
    @Override
    public void setGroup(DatasetGroup group) {
        dataset.setGroup(group);
    }

}
