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
package ch.unibas.medizin.dynamicreports.report.builder.component;

import ch.unibas.medizin.dynamicreports.report.base.component.DRMultiPageList;
import ch.unibas.medizin.dynamicreports.report.base.expression.AbstractSimpleExpression;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import ch.unibas.medizin.dynamicreports.report.constant.SplitType;
import ch.unibas.medizin.dynamicreports.report.definition.ReportParameters;
import org.apache.commons.lang3.Validate;

import java.io.Serial;

/**
 * <p>MultiPageListBuilder class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class MultiPageListBuilder extends DimensionComponentBuilder<MultiPageListBuilder, DRMultiPageList> {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for MultiPageListBuilder.</p>
     */
    protected MultiPageListBuilder() {
        super(new DRMultiPageList());
    }

    /**
     * <p>add.</p>
     *
     * @param components a {@link ch.unibas.medizin.dynamicreports.report.builder.component.ComponentBuilder} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.component.MultiPageListBuilder} object.
     */
    public MultiPageListBuilder add(ComponentBuilder<?, ?>... components) {
        Validate.notNull(components, "components must not be null");
        Validate.noNullElements(components, "components must not contains null component");
        for (ComponentBuilder<?, ?> component : components) {
            getObject().addComponent(component.build());
        }
        return this;
    }

    /**
     * <p>newPage.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.component.MultiPageListBuilder} object.
     */
    public MultiPageListBuilder newPage() {
        BreakBuilder pageBreak = Components.pageBreak();
        pageBreak.setPrintWhenExpression(new PageBreakExpression());
        getObject().addComponent(pageBreak.build());
        return this;
    }

    /**
     * <p>setSplitType.</p>
     *
     * @param splitType a {@link ch.unibas.medizin.dynamicreports.report.constant.SplitType} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.report.builder.component.MultiPageListBuilder} object.
     */
    public MultiPageListBuilder setSplitType(SplitType splitType) {
        getObject().setSplitType(splitType);
        return this;
    }

    private static class PageBreakExpression extends AbstractSimpleExpression<Boolean> {
        @Serial
        private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

        @Override
        public Boolean evaluate(ReportParameters reportParameters) {
            return reportParameters.getMasterParameters().getPageRowNumber() > 0;
        }

    }
}
