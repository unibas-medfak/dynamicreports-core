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

import ch.unibas.medizin.dynamicreports.design.exception.DRDesignReportException;
import ch.unibas.medizin.dynamicreports.design.transformation.CustomBatikRenderer;
import ch.unibas.medizin.dynamicreports.report.ReportUtils;
import ch.unibas.medizin.dynamicreports.report.builder.expression.AbstractComplexExpression;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import ch.unibas.medizin.dynamicreports.report.definition.ReportParameters;
import ch.unibas.medizin.dynamicreports.report.definition.component.DRIBooleanField;
import ch.unibas.medizin.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.renderers.Renderable;

import java.io.Serial;
import java.util.List;

/**
 * <p>BooleanImageExpression class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class BooleanImageExpression extends AbstractComplexExpression<Renderable> {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private final Renderable imageTrue;
    private final Renderable imageFalse;
    private final boolean emptyWhenNullValue;

    /**
     * <p>Constructor for BooleanImageExpression.</p>
     *
     * @param booleanField       a {@link ch.unibas.medizin.dynamicreports.report.definition.component.DRIBooleanField} object.
     * @param emptyWhenNullValue a boolean.
     * @param width              a int.
     * @param height             a int.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    public BooleanImageExpression(DRIBooleanField booleanField, boolean emptyWhenNullValue, int width, int height) throws DRException {
        this.emptyWhenNullValue = emptyWhenNullValue;
        addExpression(booleanField.getValueExpression());
        String fileNameTrue;
        String fileNameFalse = switch (booleanField.getComponentType()) {
            case IMAGE_STYLE_1 -> {
                fileNameTrue = "boolean1_true";
                yield "boolean1_false";
            }
            case IMAGE_STYLE_2 -> {
                fileNameTrue = "boolean2_true";
                yield "boolean2_false";
            }
            case IMAGE_STYLE_3 -> {
                fileNameTrue = "boolean3_true";
                yield "boolean3_false";
            }
            case IMAGE_STYLE_4 -> {
                fileNameTrue = "boolean1_true";
                yield "boolean4_false";
            }
            case IMAGE_CHECKBOX_1 -> {
                fileNameTrue = "checkbox1_true";
                yield "checkbox_false";
            }
            case IMAGE_CHECKBOX_2 -> {
                fileNameTrue = "checkbox2_true";
                yield "checkbox_false";
            }
            case IMAGE_BALL -> {
                fileNameTrue = "ball_green";
                yield "ball_red";
            }
            default ->
                    throw new DRDesignReportException("BooleanComponentType " + booleanField.getComponentType().name() + " not supported");
        };
        try {
            imageTrue = new CustomBatikRenderer(ReportUtils.class.getResource("images/" + fileNameTrue + ".svg"), width, height);
            imageFalse = new CustomBatikRenderer(ReportUtils.class.getResource("images/" + fileNameFalse + ".svg"), width, height);
        } catch (JRException e) {
            throw new DRException(e);
        }
    }

    /** {@inheritDoc} */
    @Override
    public Renderable evaluate(List<?> values, ReportParameters reportParameters) {
        Boolean value = (Boolean) values.getFirst();
        if (emptyWhenNullValue && value == null) {
            return null;
        }
        if (value != null && value) {
            return imageTrue;
        } else {
            return imageFalse;
        }
    }
}
