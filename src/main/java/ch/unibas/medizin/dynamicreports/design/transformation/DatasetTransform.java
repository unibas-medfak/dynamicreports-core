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

import ch.unibas.medizin.dynamicreports.design.base.DRDesignDataset;
import ch.unibas.medizin.dynamicreports.design.definition.DRIDesignDataset;
import ch.unibas.medizin.dynamicreports.design.exception.DRDesignReportException;
import ch.unibas.medizin.dynamicreports.jasper.base.JasperScriptlet;
import ch.unibas.medizin.dynamicreports.report.definition.DRIDataset;
import ch.unibas.medizin.dynamicreports.report.exception.DRException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>DatasetTransform class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DatasetTransform {
    private final DesignTransformAccessor accessor;
    private final Map<String, DRIDesignDataset> datasets;
    private final Map<DRIDataset, DRDesignDataset> designDatasets;

    /**
     * <p>Constructor for DatasetTransform.</p>
     *
     * @param accessor a {@link ch.unibas.medizin.dynamicreports.design.transformation.DesignTransformAccessor} object.
     */
    public DatasetTransform(DesignTransformAccessor accessor) {
        this.accessor = accessor;
        datasets = new HashMap<>();
        designDatasets = new HashMap<>();
    }

    /**
     * <p>transform.</p>
     *
     * @param dataset a {@link ch.unibas.medizin.dynamicreports.report.definition.DRIDataset} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.design.base.DRDesignDataset} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    public DRDesignDataset transform(DRIDataset dataset) throws DRException {
        if (dataset == null) {
            return null;
        }
        if (designDatasets.containsKey(dataset)) {
            return designDatasets.get(dataset);
        }

        DatasetExpressionTransform datasetExpressionTransform = new DatasetExpressionTransform(accessor, dataset);
        datasetExpressionTransform.transform();
        DRDesignDataset designDataset = new DRDesignDataset(datasetExpressionTransform);
        if (dataset.getQuery() != null) {
            designDataset.setQuery(accessor.getReportTransform().query(dataset.getQuery()));
        }
        designDataset.setConnectionExpression(accessor.getExpressionTransform().transformExpression(dataset.getConnectionExpression()));
        designDataset.setDataSourceExpression(accessor.getExpressionTransform().transformExpression(dataset.getDataSourceExpression()));
        designDataset.setFilterExpression(datasetExpressionTransform.transformExpression(dataset.getFilterExpression(), JasperScriptlet.SCRIPTLET_NAME));

        addDataset(dataset, designDataset);

        return designDataset;
    }

    /**
     * <p>getDatasetExpressionTransform.</p>
     *
     * @param dataset a {@link ch.unibas.medizin.dynamicreports.report.definition.DRIDataset} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.design.transformation.DatasetExpressionTransform} object.
     */
    public DatasetExpressionTransform getDatasetExpressionTransform(DRIDataset dataset) {
        return designDatasets.get(dataset).getDatasetExpressionTransform();
    }

    /**
     * <p>getDesignDataset.</p>
     *
     * @param dataset a {@link ch.unibas.medizin.dynamicreports.report.definition.DRIDataset} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.design.base.DRDesignDataset} object.
     */
    protected DRDesignDataset getDesignDataset(DRIDataset dataset) {
        return designDatasets.get(dataset);
    }

    private void addDataset(DRIDataset dataset, DRDesignDataset designDataset) {
        if (datasets.containsKey(designDataset.getName())) {
            throw new DRDesignReportException("Duplicate declaration of dataset \"" + designDataset.getName() + "\"");
        }
        datasets.put(designDataset.getName(), designDataset);
        designDatasets.put(dataset, designDataset);
    }

    /**
     * <p>Getter for the field <code>datasets</code>.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<DRIDesignDataset> getDatasets() {
        return datasets.values();
    }
}
