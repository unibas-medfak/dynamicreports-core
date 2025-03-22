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
package ch.unibas.medizin.dynamicreports.design.definition;

import ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignComplexExpression;
import ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignExpression;
import ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignJasperExpression;
import ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignSimpleExpression;
import ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignSystemExpression;

import java.io.Serializable;
import java.util.Collection;

/**
 * <p>DRIDesignDataset interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface DRIDesignDataset extends Serializable {

    /**
     * <p>getName.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    String getName();

    /**
     * <p>getFields.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    Collection<DRIDesignField> getFields();

    /**
     * <p>getVariables.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    Collection<DRIDesignVariable> getVariables();

    /**
     * <p>getSystemExpressions.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    Collection<DRIDesignSystemExpression> getSystemExpressions();

    /**
     * <p>getJasperExpressions.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    Collection<DRIDesignJasperExpression> getJasperExpressions();

    /**
     * <p>getSimpleExpressions.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    Collection<DRIDesignSimpleExpression> getSimpleExpressions();

    /**
     * <p>getComplexExpressions.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    Collection<DRIDesignComplexExpression> getComplexExpressions();

    /**
     * <p>getSorts.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    Collection<DRIDesignSort> getSorts();

    /**
     * <p>getQuery.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.definition.DRIDesignQuery} object.
     */
    DRIDesignQuery getQuery();

    /**
     * <p>getConnectionExpression.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignExpression} object.
     */
    DRIDesignExpression getConnectionExpression();

    /**
     * <p>getDataSourceExpression.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignExpression} object.
     */
    DRIDesignExpression getDataSourceExpression();

    /**
     * <p>getFilterExpression.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignExpression} object.
     */
    DRIDesignExpression getFilterExpression();
}
