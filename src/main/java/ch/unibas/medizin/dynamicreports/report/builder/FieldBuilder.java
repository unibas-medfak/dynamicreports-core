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
package ch.unibas.medizin.dynamicreports.report.builder;

import ch.unibas.medizin.dynamicreports.report.base.DRField;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import ch.unibas.medizin.dynamicreports.report.definition.DRIValue;
import ch.unibas.medizin.dynamicreports.report.definition.datatype.DRIDataType;

import java.io.Serial;

/**
 * <p>FieldBuilder class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class FieldBuilder<T> extends AbstractBuilder<FieldBuilder<T>, DRField<T>> implements DRIValue<T> {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for FieldBuilder.</p>
     *
     * @param name       a {@link java.lang.String} object.
     * @param valueClass a {@link java.lang.Class} object.
     */
    protected FieldBuilder(String name, Class<? super T> valueClass) {
        super(new DRField<>(name, valueClass));
    }

    /**
     * <p>setDataType.</p>
     *
     * @param dataType a {@link ch.unibas.medizin.dynamicreports.report.definition.datatype.DRIDataType} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.FieldBuilder} object.
     */
    public FieldBuilder<T> setDataType(DRIDataType<? super T, T> dataType) {
        getObject().setDataType(dataType);
        return this;
    }

    /**
     * <p>setDescription.</p>
     *
     * @param description a {@link java.lang.String} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.FieldBuilder} object.
     */
    public FieldBuilder<T> setDescription(String description) {
        getObject().setDescription(description);
        return this;
    }

    /**
     * <p>getField.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.base.DRField} object.
     */
    public DRField<T> getField() {
        return build();
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return getField().getName();
    }
}
