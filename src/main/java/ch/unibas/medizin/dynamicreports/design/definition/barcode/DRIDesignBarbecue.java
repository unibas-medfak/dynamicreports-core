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
package ch.unibas.medizin.dynamicreports.design.definition.barcode;

import ch.unibas.medizin.dynamicreports.design.constant.EvaluationTime;
import ch.unibas.medizin.dynamicreports.design.definition.DRIDesignGroup;
import ch.unibas.medizin.dynamicreports.design.definition.component.DRIDesignComponent;
import ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignExpression;
import ch.unibas.medizin.dynamicreports.report.constant.BarbecueType;
import ch.unibas.medizin.dynamicreports.report.constant.BarcodeOrientation;

/**
 * <p>DRIDesignBarbecue interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface DRIDesignBarbecue extends DRIDesignComponent {

    /**
     * <p>getType.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.BarbecueType} object.
     */
    public BarbecueType getType();

    /**
     * <p>getCodeExpression.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignExpression} object.
     */
    public DRIDesignExpression getCodeExpression();

    /**
     * <p>getApplicationIdentifierExpression.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignExpression} object.
     */
    public DRIDesignExpression getApplicationIdentifierExpression();

    /**
     * <p>getDrawText.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getDrawText();

    /**
     * <p>getChecksumRequired.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getChecksumRequired();

    /**
     * <p>getBarWidth.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getBarWidth();

    /**
     * <p>getBarHeight.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getBarHeight();

    /**
     * <p>getOrientation.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.BarcodeOrientation} object.
     */
    public BarcodeOrientation getOrientation();

    /**
     * <p>getEvaluationTime.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.constant.EvaluationTime} object.
     */
    public EvaluationTime getEvaluationTime();

    /**
     * <p>getEvaluationGroup.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.definition.DRIDesignGroup} object.
     */
    public DRIDesignGroup getEvaluationGroup();
}
