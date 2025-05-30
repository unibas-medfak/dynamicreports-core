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
package ch.unibas.medizin.dynamicreports.design.base.expression;

import ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignComplexExpression;
import ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignExpression;
import ch.unibas.medizin.dynamicreports.report.ReportUtils;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Abstract AbstractDesignComplexExpression class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public abstract class AbstractDesignComplexExpression implements DRIDesignComplexExpression {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private final String name;
    private List<DRIDesignExpression> expressions;

    /**
     * <p>Constructor for AbstractDesignComplexExpression.</p>
     */
    protected AbstractDesignComplexExpression() {
        this(ReportUtils.generateUniqueName("complexExpression"));
    }

    /**
     * <p>Constructor for AbstractDesignComplexExpression.</p>
     *
     * @param name a {@link java.lang.String} object.
     */
    protected AbstractDesignComplexExpression(String name) {
        this.name = name;
        this.expressions = new ArrayList<>();
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return name;
    }

    /**
     * <p>addExpression.</p>
     *
     * @param expression a {@link ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignExpression} object.
     */
    public void addExpression(DRIDesignExpression expression) {
        this.expressions.add(expression);
    }

    /** {@inheritDoc} */
    @Override
    public List<DRIDesignExpression> getExpressions() {
        return expressions;
    }

    /**
     * <p>Setter for the field <code>expressions</code>.</p>
     *
     * @param expressions a {@link java.util.List} object.
     */
    public void setExpressions(List<DRIDesignExpression> expressions) {
        this.expressions = expressions;
    }

    /** {@inheritDoc} */
    @Override
    public String getParameterName() {
        return null;
    }
}
