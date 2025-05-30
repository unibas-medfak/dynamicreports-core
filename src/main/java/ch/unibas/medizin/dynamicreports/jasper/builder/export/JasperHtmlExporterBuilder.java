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
package ch.unibas.medizin.dynamicreports.jasper.builder.export;

import ch.unibas.medizin.dynamicreports.jasper.base.export.JasperHtmlExporter;
import ch.unibas.medizin.dynamicreports.jasper.constant.BorderCollapse;
import ch.unibas.medizin.dynamicreports.jasper.constant.SizeUnit;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;

import java.io.Serial;

/**
 * <p>JasperHtmlExporterBuilder class.</p>
 *
 * @author Ricardo Mariaca
 *
 */
public class JasperHtmlExporterBuilder extends AbstractJasperExporterBuilder<JasperHtmlExporterBuilder, JasperHtmlExporter> {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for JasperHtmlExporterBuilder.</p>
     */
    protected JasperHtmlExporterBuilder() {
        super(new JasperHtmlExporter());
    }

    /**
     * <p>setOutputImagesToDir.</p>
     *
     * @param outputImagesToDir a {@link java.lang.Boolean} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.export.JasperHtmlExporterBuilder} object.
     */
    public JasperHtmlExporterBuilder setOutputImagesToDir(Boolean outputImagesToDir) {
        this.getObject().setOutputImagesToDir(outputImagesToDir);
        return this;
    }

    /**
     * <p>setImagesDirName.</p>
     *
     * @param imagesDirName a {@link java.lang.String} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.export.JasperHtmlExporterBuilder} object.
     */
    public JasperHtmlExporterBuilder setImagesDirName(String imagesDirName) {
        this.getObject().setImagesDirName(imagesDirName);
        return this;
    }

    /**
     * <p>setImagesURI.</p>
     *
     * @param imagesURI a {@link java.lang.String} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.export.JasperHtmlExporterBuilder} object.
     */
    public JasperHtmlExporterBuilder setImagesURI(String imagesURI) {
        this.getObject().setImagesURI(imagesURI);
        return this;
    }

    /**
     * <p>setHtmlHeader.</p>
     *
     * @param htmlHeader a {@link java.lang.String} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.export.JasperHtmlExporterBuilder} object.
     */
    public JasperHtmlExporterBuilder setHtmlHeader(String htmlHeader) {
        this.getObject().setHtmlHeader(htmlHeader);
        return this;
    }

    /**
     * <p>setBetweenPagesHtml.</p>
     *
     * @param betweenPagesHtml a {@link java.lang.String} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.export.JasperHtmlExporterBuilder} object.
     */
    public JasperHtmlExporterBuilder setBetweenPagesHtml(String betweenPagesHtml) {
        this.getObject().setBetweenPagesHtml(betweenPagesHtml);
        return this;
    }

    /**
     * <p>setHtmlFooter.</p>
     *
     * @param htmlFooter a {@link java.lang.String} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.export.JasperHtmlExporterBuilder} object.
     */
    public JasperHtmlExporterBuilder setHtmlFooter(String htmlFooter) {
        this.getObject().setHtmlFooter(htmlFooter);
        return this;
    }

    /**
     * <p>setRemoveEmptySpaceBetweenRows.</p>
     *
     * @param removeEmptySpaceBetweenRows a {@link java.lang.Boolean} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.export.JasperHtmlExporterBuilder} object.
     */
    public JasperHtmlExporterBuilder setRemoveEmptySpaceBetweenRows(Boolean removeEmptySpaceBetweenRows) {
        this.getObject().setRemoveEmptySpaceBetweenRows(removeEmptySpaceBetweenRows);
        return this;
    }

    /**
     * <p>setWhitePageBackground.</p>
     *
     * @param whitePageBackground a {@link java.lang.Boolean} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.export.JasperHtmlExporterBuilder} object.
     */
    public JasperHtmlExporterBuilder setWhitePageBackground(Boolean whitePageBackground) {
        this.getObject().setWhitePageBackground(whitePageBackground);
        return this;
    }

    /**
     * <p>setWrapBreakWord.</p>
     *
     * @param wrapBreakWord a {@link java.lang.Boolean} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.export.JasperHtmlExporterBuilder} object.
     */
    public JasperHtmlExporterBuilder setWrapBreakWord(Boolean wrapBreakWord) {
        this.getObject().setWrapBreakWord(wrapBreakWord);
        return this;
    }

    /**
     * <p>setSizeUnit.</p>
     *
     * @param sizeUnit a {@link ch.unibas.medizin.dynamicreports.jasper.constant.SizeUnit} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.export.JasperHtmlExporterBuilder} object.
     */
    public JasperHtmlExporterBuilder setSizeUnit(SizeUnit sizeUnit) {
        this.getObject().setSizeUnit(sizeUnit);
        return this;
    }

    /**
     * <p>setIgnorePageMargins.</p>
     *
     * @param ignorePageMargins a {@link java.lang.Boolean} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.export.JasperHtmlExporterBuilder} object.
     */
    public JasperHtmlExporterBuilder setIgnorePageMargins(Boolean ignorePageMargins) {
        this.getObject().setIgnorePageMargins(ignorePageMargins);
        return this;
    }

    /**
     * <p>setBorderCollapse.</p>
     *
     * @param borderCollapse a {@link java.lang.String} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.export.JasperHtmlExporterBuilder} object.
     */
    public JasperHtmlExporterBuilder setBorderCollapse(BorderCollapse borderCollapse) {
        this.getObject().setBorderCollapse(borderCollapse);
        return this;
    }

    /**
     * <p>setAccessibleHtml.</p>
     *
     * @param accessibleHtml a {@link java.lang.Boolean} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.export.JasperHtmlExporterBuilder} object.
     */
    public JasperHtmlExporterBuilder setAccessibleHtml(Boolean accessibleHtml) {
        this.getObject().setAccessibleHtml(accessibleHtml);
        return this;
    }

    /**
     * <p>setZoomRatio.</p>
     *
     * @param zoomRatio a {@link java.lang.Float} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.export.JasperHtmlExporterBuilder} object.
     */
    public JasperHtmlExporterBuilder setZoomRatio(Float zoomRatio) {
        this.getObject().setZoomRatio(zoomRatio);
        return this;
    }

    /**
     * <p>setIgnoreHyperLink.</p>
     *
     * @param ignoreHyperLink a {@link java.lang.Boolean} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.export.JasperHtmlExporterBuilder} object.
     */
    public JasperHtmlExporterBuilder setIgnoreHyperLink(Boolean ignoreHyperLink) {
        this.getObject().setIgnoreHyperLink(ignoreHyperLink);
        return this;
    }

    /**
     * <p>setFlushOutput.</p>
     *
     * @param flushOutput a {@link java.lang.Boolean} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.export.JasperHtmlExporterBuilder} object.
     */
    public JasperHtmlExporterBuilder setFlushOutput(Boolean flushOutput) {
        this.getObject().setFlushOutput(flushOutput);
        return this;
    }
}
