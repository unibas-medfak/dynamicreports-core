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
package ch.unibas.medizin.dynamicreports.report.base.style;

import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression;
import ch.unibas.medizin.dynamicreports.report.definition.style.DRIConditionalStyle;
import org.apache.commons.lang3.Validate;

import java.io.Serial;

/**
 * <p>DRConditionalStyle class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DRConditionalStyle extends DRBaseStyle implements DRIConditionalStyle {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private final DRIExpression<Boolean> conditionExpression;

    /**
     * <p>Constructor for DRConditionalStyle.</p>
     *
     * @param conditionExpression a {@link ch.unibas.medizin.dynamicreports.report.definition.expression.DRIExpression} object.
     */
    public DRConditionalStyle(DRIExpression<Boolean> conditionExpression) {
        Validate.notNull(conditionExpression, "conditionExpression must not be null");
        this.conditionExpression = conditionExpression;
    }

    /** {@inheritDoc} */
    @Override
    public DRIExpression<Boolean> getConditionExpression() {
        return conditionExpression;
    }
}
