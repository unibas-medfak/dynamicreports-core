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
package ch.unibas.medizin.dynamicreports.design.transformation.chartcustomizer;

import java.io.Serial;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.apache.commons.lang3.StringUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.chart.ui.TextAnchor;

import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import ch.unibas.medizin.dynamicreports.report.definition.ReportParameters;
import ch.unibas.medizin.dynamicreports.report.definition.chart.DRIChartCustomizer;

/**
 * <p>ShowValuesCustomizer class.</p>
 *
 * @author Ricardo Mariaca
 *
 */
public class ShowValuesCustomizer implements DRIChartCustomizer, Serializable {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private final String valuePattern;
    private final boolean customRangeMaxValue;

    /**
     * <p>Constructor for ShowValuesCustomizer.</p>
     *
     * @param valuePattern        a {@link java.lang.String} object.
     * @param customRangeMaxValue a boolean.
     */
    public ShowValuesCustomizer(String valuePattern, boolean customRangeMaxValue) {
        this.valuePattern = valuePattern;
        this.customRangeMaxValue = customRangeMaxValue;
    }

    /** {@inheritDoc} */
    @Override
    public void customize(JFreeChart chart, ReportParameters reportParameters) {
        if (chart.getPlot() instanceof CategoryPlot) {
            final CategoryItemRenderer renderer = chart.getCategoryPlot().getRenderer();
            if (StringUtils.isBlank(valuePattern)) {
                renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
            } else {
                renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator(StandardCategoryItemLabelGenerator.DEFAULT_LABEL_FORMAT_STRING, new DecimalFormat(valuePattern)));
            }
            renderer.setDefaultItemLabelsVisible(Boolean.TRUE);
            if (!customRangeMaxValue) {
                chart.getCategoryPlot().getRangeAxis().zoomRange(0, 1.1);
            }
            if (renderer.getClass().equals(BarRenderer.class)) {
                ((BarRenderer) renderer).setItemLabelInsets(new RectangleInsets(10D, 10D, 10D, 10D));
                renderer.setDefaultPositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));
            }
        } else if (chart.getPlot() instanceof XYPlot) {
            final XYItemRenderer renderer = chart.getXYPlot().getRenderer();
            if (StringUtils.isBlank(valuePattern)) {
                renderer.setDefaultItemLabelGenerator(new StandardXYItemLabelGenerator());
            } else {
                renderer.setDefaultItemLabelGenerator(
                    new StandardXYItemLabelGenerator(StandardXYItemLabelGenerator.DEFAULT_ITEM_LABEL_FORMAT, NumberFormat.getNumberInstance(), new DecimalFormat(valuePattern)));
            }
            renderer.setDefaultItemLabelsVisible(Boolean.TRUE);
            chart.getXYPlot().getRangeAxis().zoomRange(0, 1.1);
        }
    }
}
