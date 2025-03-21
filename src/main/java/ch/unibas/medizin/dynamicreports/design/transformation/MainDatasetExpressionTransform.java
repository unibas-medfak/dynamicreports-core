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
package ch.unibas.medizin.dynamicreports.design.transformation;

import ch.unibas.medizin.dynamicreports.design.base.DRDesignField;
import ch.unibas.medizin.dynamicreports.design.base.DRDesignGroup;
import ch.unibas.medizin.dynamicreports.design.constant.ResetType;
import ch.unibas.medizin.dynamicreports.design.definition.DRIDesignDataset;
import ch.unibas.medizin.dynamicreports.report.definition.DRIField;
import ch.unibas.medizin.dynamicreports.report.definition.DRIReport;
import ch.unibas.medizin.dynamicreports.report.definition.DRISort;
import ch.unibas.medizin.dynamicreports.report.definition.DRIVariable;
import ch.unibas.medizin.dynamicreports.report.exception.DRException;

import java.util.List;

/**
 * <p>MainDatasetExpressionTransform class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class MainDatasetExpressionTransform extends AbstractExpressionTransform {

    /**
     * <p>Constructor for MainDatasetExpressionTransform.</p>
     *
     * @param accessor a {@link ch.unibas.medizin.dynamicreports.design.transformation.DesignTransformAccessor} object.
     */
    public MainDatasetExpressionTransform(DesignTransformAccessor accessor) {
        super(accessor);
    }

    /** {@inheritDoc} */
    @Override
    public void transform() throws DRException {
        DRIReport report = accessor.getReport();
        List<DRIField<?>> templateDesignFields = report.getTemplateDesign().getFields();
        if (templateDesignFields != null) {
            for (DRIField<?> field : templateDesignFields) {
                DRDesignField designField = (DRDesignField) transformExpression(field);
                designField.setExternal(true);
            }
        }
        super.transform();
    }

    /** {@inheritDoc} */
    @Override
    protected ResetType getVariableResetType(DRIVariable<?> variable) {
        return ConstantTransform.variableResetType(variable.getResetType(), variable.getResetGroup(), accessor);
    }

    /** {@inheritDoc} */
    @Override
    protected DRDesignGroup getVariableResetGroup(DRIVariable<?> variable) throws DRException {
        return accessor.getGroupTransform().getGroup(ConstantTransform.variableResetGroup(variable.getName(), variable.getResetType(), variable.getResetGroup(), accessor));
    }

    /** {@inheritDoc} */
    @Override
    protected List<? extends DRIField<?>> transformFields() {
        return accessor.getReport().getFields();
    }

    /** {@inheritDoc} */
    @Override
    protected List<? extends DRIVariable<?>> transformVariables() {
        return accessor.getReport().getVariables();
    }

    /** {@inheritDoc} */
    @Override
    protected List<? extends DRISort> transformSorts() {
        return accessor.getReport().getSorts();
    }

    /** {@inheritDoc} */
    @Override
    protected DRIDesignDataset getDataset() {
        return null;
    }
}
