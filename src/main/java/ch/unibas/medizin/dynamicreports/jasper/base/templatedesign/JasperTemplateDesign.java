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
package ch.unibas.medizin.dynamicreports.jasper.base.templatedesign;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.Validate;

import ch.unibas.medizin.dynamicreports.jasper.transformation.ConstantTransform;
import ch.unibas.medizin.dynamicreports.report.base.DRField;
import ch.unibas.medizin.dynamicreports.report.base.DRMargin;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import ch.unibas.medizin.dynamicreports.report.constant.PageOrientation;
import ch.unibas.medizin.dynamicreports.report.constant.WhenNoDataType;
import ch.unibas.medizin.dynamicreports.report.constant.WhenResourceMissingType;
import ch.unibas.medizin.dynamicreports.report.definition.DRIField;
import ch.unibas.medizin.dynamicreports.report.definition.DRIMargin;
import ch.unibas.medizin.dynamicreports.report.definition.DRITemplateDesign;
import ch.unibas.medizin.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRBand;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.engine.xml.JRXmlWriter;

/**
 * <p>JasperTemplateDesign class.</p>
 *
 * @author Ricardo Mariaca
 *
 */
public class JasperTemplateDesign implements DRITemplateDesign<JasperDesign> {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private JasperDesign jasperDesign;
    private List<DRIField<?>> fields;
    private DRMargin margin;
    private transient ByteArrayOutputStream templateDesign;

    /**
     * <p>Constructor for JasperTemplateDesign.</p>
     *
     * @param jasperDesign a {@link net.sf.jasperreports.engine.design.JasperDesign} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    public JasperTemplateDesign(JasperDesign jasperDesign) throws DRException {
        init(jasperDesign);
    }

    /**
     * <p>Constructor for JasperTemplateDesign.</p>
     *
     * @param file a {@link java.io.File} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    public JasperTemplateDesign(File file) throws DRException {
        Validate.notNull(file, "file must not be null");
        try {
            init(JRXmlLoader.load(file));
        } catch (final JRException e) {
            throw new DRException(e);
        }
    }

    /**
     * <p>Constructor for JasperTemplateDesign.</p>
     *
     * @param fileName a {@link java.lang.String} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    public JasperTemplateDesign(String fileName) throws DRException {
        Validate.notNull(fileName, "fileName must not be null");
        try {
            init(JRXmlLoader.load(fileName));
        } catch (final JRException e) {
            throw new DRException(e);
        }
    }

    /**
     * <p>Constructor for JasperTemplateDesign.</p>
     *
     * @param inputStream a {@link java.io.InputStream} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    public JasperTemplateDesign(InputStream inputStream) throws DRException {
        Validate.notNull(inputStream, "inputStream must not be null");
        try {
            init(JRXmlLoader.load(inputStream));
        } catch (final JRException e) {
            throw new DRException(e);
        }
    }

    /**
     * <p>Constructor for JasperTemplateDesign.</p>
     *
     * @param url a {@link java.net.URL} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    public JasperTemplateDesign(URL url) throws DRException {
        Validate.notNull(url, "url must not be null");
        try {
            init(JRXmlLoader.load(url.openStream()));
        } catch (final JRException | IOException e) {
            throw new DRException(e);
        }
    }

    private void init(JasperDesign jasperDesign) throws DRException {
        Validate.notNull(jasperDesign, "jasperDesign must not be null");
        this.jasperDesign = jasperDesign;

        this.fields = new ArrayList<>();
        for (final JRField jrField : jasperDesign.getFields()) {
            final DRField<?> field = new DRField<>(jrField.getName(), jrField.getValueClass());
            fields.add(field);
        }

        this.margin = new DRMargin();
        margin.setTop(jasperDesign.getTopMargin());
        margin.setLeft(jasperDesign.getLeftMargin());
        margin.setBottom(jasperDesign.getBottomMargin());
        margin.setRight(jasperDesign.getRightMargin());
    }

    /** {@inheritDoc} */
    @Override
    public String getReportName() {
        return jasperDesign.getName();
    }

    /** {@inheritDoc} */
    @Override
    public List<DRIField<?>> getFields() {
        return fields;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isDefinedParameter(String name) {
        final JRParameter parameter = jasperDesign.getParametersMap().get(name);
        return parameter != null;
    }

    /** {@inheritDoc} */
    @Override
    public String getResourceBundleName() {
        return jasperDesign.getResourceBundle();
    }

    /** {@inheritDoc} */
    @Override
    public Boolean getIgnorePagination() {
        return jasperDesign.isIgnorePagination();
    }

    /** {@inheritDoc} */
    @Override
    public WhenNoDataType getWhenNoDataType() {
        return ConstantTransform.whenNoDataType(jasperDesign.getWhenNoDataType());
    }

    /** {@inheritDoc} */
    @Override
    public WhenResourceMissingType getWhenResourceMissingType() {
        return jasperDesign.getWhenResourceMissingType() == null ? null : ConstantTransform.whenResourceMissingType(jasperDesign.getWhenResourceMissingType());
    }

    /** {@inheritDoc} */
    @Override
    public Boolean getTitleOnANewPage() {
        return jasperDesign.isTitleNewPage();
    }

    /** {@inheritDoc} */
    @Override
    public Boolean getSummaryOnANewPage() {
        return jasperDesign.isSummaryNewPage();
    }

    /** {@inheritDoc} */
    @Override
    public Boolean getSummaryWithPageHeaderAndFooter() {
        return jasperDesign.isSummaryWithPageHeaderAndFooter();
    }

    /** {@inheritDoc} */
    @Override
    public Boolean getFloatColumnFooter() {
        return jasperDesign.isFloatColumnFooter();
    }

    /** {@inheritDoc} */
    @Override
    public Integer getPageWidth() {
        return jasperDesign.getPageWidth();
    }

    /** {@inheritDoc} */
    @Override
    public Integer getPageHeight() {
        return jasperDesign.getPageHeight();
    }

    /** {@inheritDoc} */
    @Override
    public PageOrientation getPageOrientation() {
        return jasperDesign.getOrientation() == null ? null : ConstantTransform.pageOrientation(jasperDesign.getOrientation());
    }

    /** {@inheritDoc} */
    @Override
    public DRIMargin getPageMargin() {
        return margin;
    }

    /** {@inheritDoc} */
    @Override
    public Integer getPageColumnsPerPage() {
        return jasperDesign.getColumnCount();
    }

    /** {@inheritDoc} */
    @Override
    public Integer getPageColumnSpace() {
        return jasperDesign.getColumnSpacing();
    }

    /** {@inheritDoc} */
    @Override
    public Integer getPageColumnWidth() {
        return jasperDesign.getColumnWidth();
    }

    /** {@inheritDoc} */
    @Override
    public int getTitleComponentsCount() {
        return getBandComponentsCount(jasperDesign.getTitle());
    }

    /** {@inheritDoc} */
    @Override
    public int getPageHeaderComponentsCount() {
        return getBandComponentsCount(jasperDesign.getPageHeader());
    }

    /** {@inheritDoc} */
    @Override
    public int getPageFooterComponentsCount() {
        return getBandComponentsCount(jasperDesign.getPageFooter());
    }

    /** {@inheritDoc} */
    @Override
    public int getColumnHeaderComponentsCount() {
        return getBandComponentsCount(jasperDesign.getColumnHeader());
    }

    /** {@inheritDoc} */
    @Override
    public int getColumnFooterComponentsCount() {
        return getBandComponentsCount(jasperDesign.getColumnFooter());
    }

    /** {@inheritDoc} */
    @Override
    public int getLastPageFooterComponentsCount() {
        return getBandComponentsCount(jasperDesign.getLastPageFooter());
    }

    /** {@inheritDoc} */
    @Override
    public int getSummaryComponentsCount() {
        return getBandComponentsCount(jasperDesign.getSummary());
    }

    /** {@inheritDoc} */
    @Override
    public int getNoDataComponentsCount() {
        return getBandComponentsCount(jasperDesign.getNoData());
    }

    /** {@inheritDoc} */
    @Override
    public int getBackgroundComponentsCount() {
        return getBandComponentsCount(jasperDesign.getBackground());
    }

    private int getBandComponentsCount(JRBand band) {
        if (band != null && band.getElements() != null) {
            return band.getElements().length;
        }
        return 0;
    }

    /** {@inheritDoc} */
    @Override
    public JasperDesign getDesign() throws DRException {
        try {
            if (templateDesign == null) {
                templateDesign = new ByteArrayOutputStream();
                JRXmlWriter.writeReport(jasperDesign, templateDesign, "UTF-8");
            }

            return JRXmlLoader.load(new ByteArrayInputStream(templateDesign.toByteArray()));
        } catch (final JRException e) {
            throw new DRException(e);
        }
    }
}
