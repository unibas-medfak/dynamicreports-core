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

import ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder;
import ch.unibas.medizin.dynamicreports.report.base.DRBand;
import ch.unibas.medizin.dynamicreports.report.base.component.DRComponent;
import ch.unibas.medizin.dynamicreports.report.base.expression.AbstractSimpleExpression;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import ch.unibas.medizin.dynamicreports.report.constant.WhenResourceMissingType;
import ch.unibas.medizin.dynamicreports.report.definition.ReportParameters;
import ch.unibas.medizin.dynamicreports.report.definition.component.DRIComponent;
import ch.unibas.medizin.dynamicreports.report.definition.style.DRIStyle;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.report;

/**
 * <p>MultiPageListSubreportExpression class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class MultiPageListSubreportExpression extends AbstractSimpleExpression<JasperReportBuilder> {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private Locale locale;
    private ResourceBundle resourceBundle;
    private String resourceBundleName;
    private WhenResourceMissingType whenResourceMissingType;
    private List<DRIComponent> detailComponents;
    private Map<String, DRIStyle> templateStyles;

    /**
     * <p>Constructor for MultiPageListSubreportExpression.</p>
     *
     * @param locale                  a {@link java.util.Locale} object.
     * @param resourceBundle          a {@link java.util.ResourceBundle} object.
     * @param resourceBundleName      a {@link java.lang.String} object.
     * @param resourceBundleName      a {@link java.lang.String} object.
     * @param whenResourceMissingType a {@link ch.unibas.medizin.dynamicreports.report.constant.WhenResourceMissingType} object.
     * @param detailComponents        a {@link java.util.List} object.
     * @param templateStyles          a {@link java.util.Map} object.
     */
    public MultiPageListSubreportExpression(Locale locale, ResourceBundle resourceBundle, String resourceBundleName, WhenResourceMissingType whenResourceMissingType,
                                            List<DRIComponent> detailComponents, Map<String, DRIStyle> templateStyles) {
        this.locale = locale;
        this.resourceBundle = resourceBundle;
        this.resourceBundleName = resourceBundleName;
        this.whenResourceMissingType = whenResourceMissingType;
        this.detailComponents = detailComponents;
        this.templateStyles = templateStyles;
    }

    /** {@inheritDoc} */
    @Override
    public JasperReportBuilder evaluate(ReportParameters reportParameters) {
        JasperReportBuilder report = report();
        report.setLocale(locale);
        report.setResourceBundle(resourceBundle);
        report.setResourceBundle(resourceBundleName);
        report.setWhenResourceMissingType(whenResourceMissingType);
        for (DRIStyle style : templateStyles.values()) {
            report.getReport().addTemplateStyle(style);
        }
        DRBand titleBand = report.getReport().getTitleBand();
        DRComponent detailComponent = (DRComponent) detailComponents.get(reportParameters.getReportRowNumber() - 1);
        titleBand.addComponent(detailComponent);
        return report;
    }
}
