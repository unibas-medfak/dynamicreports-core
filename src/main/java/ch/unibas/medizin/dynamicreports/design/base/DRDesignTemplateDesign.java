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
package ch.unibas.medizin.dynamicreports.design.base;

import ch.unibas.medizin.dynamicreports.design.definition.DRIDesignTemplateDesign;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import ch.unibas.medizin.dynamicreports.report.definition.DRITemplateDesign;
import ch.unibas.medizin.dynamicreports.report.exception.DRException;

import java.io.Serial;

/**
 * <p>DRDesignTemplateDesign class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DRDesignTemplateDesign implements DRIDesignTemplateDesign {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private final DRITemplateDesign<?> templateDesign;

    /**
     * <p>Constructor for DRDesignTemplateDesign.</p>
     *
     * @param templateDesign a {@link ch.unibas.medizin.dynamicreports.report.definition.DRITemplateDesign} object.
     */
    public DRDesignTemplateDesign(DRITemplateDesign<?> templateDesign) {
        this.templateDesign = templateDesign;
    }

    /** {@inheritDoc} */
    @Override
    public int getTitleComponentsCount() {
        return templateDesign.getTitleComponentsCount();
    }

    /** {@inheritDoc} */
    @Override
    public int getPageHeaderComponentsCount() {
        return templateDesign.getPageHeaderComponentsCount();
    }

    /** {@inheritDoc} */
    @Override
    public int getPageFooterComponentsCount() {
        return templateDesign.getPageFooterComponentsCount();
    }

    /** {@inheritDoc} */
    @Override
    public int getColumnHeaderComponentsCount() {
        return templateDesign.getColumnHeaderComponentsCount();
    }

    /** {@inheritDoc} */
    @Override
    public int getColumnFooterComponentsCount() {
        return templateDesign.getColumnFooterComponentsCount();
    }

    /** {@inheritDoc} */
    @Override
    public int getLastPageFooterComponentsCount() {
        return templateDesign.getLastPageFooterComponentsCount();
    }

    /** {@inheritDoc} */
    @Override
    public int getSummaryComponentsCount() {
        return templateDesign.getSummaryComponentsCount();
    }

    /** {@inheritDoc} */
    @Override
    public int getNoDataComponentsCount() {
        return templateDesign.getNoDataComponentsCount();
    }

    /** {@inheritDoc} */
    @Override
    public int getBackgroundComponentsCount() {
        return templateDesign.getBackgroundComponentsCount();
    }

    /** {@inheritDoc} */
    @Override
    public Object getDesign() throws DRException {
        return templateDesign.getDesign();
    }
}
