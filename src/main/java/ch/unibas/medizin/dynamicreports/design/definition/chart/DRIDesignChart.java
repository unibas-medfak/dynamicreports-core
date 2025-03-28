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
package ch.unibas.medizin.dynamicreports.design.definition.chart;

import ch.unibas.medizin.dynamicreports.design.constant.EvaluationTime;
import ch.unibas.medizin.dynamicreports.design.definition.DRIDesignGroup;
import ch.unibas.medizin.dynamicreports.design.definition.chart.dataset.DRIDesignChartDataset;
import ch.unibas.medizin.dynamicreports.design.definition.chart.plot.DRIDesignPlot;
import ch.unibas.medizin.dynamicreports.design.definition.component.DRIDesignHyperLinkComponent;
import ch.unibas.medizin.dynamicreports.report.constant.ChartType;
import ch.unibas.medizin.dynamicreports.report.definition.chart.DRIChartCustomizer;

import java.util.List;

/**
 * <p>DRIDesignChart interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface DRIDesignChart extends DRIDesignHyperLinkComponent {

    /**
     * <p>getChartType.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.ChartType} object.
     */
    ChartType getChartType();

    /**
     * <p>getDataset.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.definition.chart.dataset.DRIDesignChartDataset} object.
     */
    DRIDesignChartDataset getDataset();

    /**
     * <p>getPlot.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.definition.chart.plot.DRIDesignPlot} object.
     */
    DRIDesignPlot getPlot();

    /**
     * <p>getCustomizers.</p>
     *
     * @return a {@link java.util.List} object.
     */
    List<DRIChartCustomizer> getCustomizers();

    /**
     * <p>getTitle.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.definition.chart.DRIDesignChartTitle} object.
     */
    DRIDesignChartTitle getTitle();

    /**
     * <p>getSubtitle.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.definition.chart.DRIDesignChartSubtitle} object.
     */
    DRIDesignChartSubtitle getSubtitle();

    /**
     * <p>getLegend.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.definition.chart.DRIDesignChartLegend} object.
     */
    DRIDesignChartLegend getLegend();

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
     * <p>getTheme.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    String getTheme();
}
