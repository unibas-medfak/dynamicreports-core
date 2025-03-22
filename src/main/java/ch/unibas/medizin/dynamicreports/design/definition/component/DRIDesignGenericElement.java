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
package ch.unibas.medizin.dynamicreports.design.definition.component;

import ch.unibas.medizin.dynamicreports.design.constant.EvaluationTime;
import ch.unibas.medizin.dynamicreports.design.definition.DRIDesignGroup;
import ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignParameterExpression;

import java.util.List;

/**
 * <p>DRIDesignGenericElement interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface DRIDesignGenericElement extends DRIDesignComponent {

    /**
     * <p>getGenericElementNamespace.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    String getGenericElementNamespace();

    /**
     * <p>getGenericElementName.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    String getGenericElementName();

    /**
     * <p>getEvaluationTime.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.constant.EvaluationTime} object.
     */
    EvaluationTime getEvaluationTime();

    /**
     * <p>getEvaluationGroup.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.definition.DRIDesignGroup} object.
     */
    DRIDesignGroup getEvaluationGroup();

    /**
     * <p>getParameterExpressions.</p>
     *
     * @return a {@link java.util.List} object.
     */
    List<DRIDesignParameterExpression> getParameterExpressions();

}
