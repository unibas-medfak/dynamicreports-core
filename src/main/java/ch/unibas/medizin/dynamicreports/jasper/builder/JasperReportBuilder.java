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
package ch.unibas.medizin.dynamicreports.jasper.builder;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.Validate;

import ch.unibas.medizin.dynamicreports.design.base.DRDesignReport;
import ch.unibas.medizin.dynamicreports.design.definition.DRIDesignReport;
import ch.unibas.medizin.dynamicreports.jasper.base.JasperReportDesign;
import ch.unibas.medizin.dynamicreports.jasper.base.export.AbstractJasperExporter;
import ch.unibas.medizin.dynamicreports.jasper.base.export.JasperImageExporter;
import ch.unibas.medizin.dynamicreports.jasper.base.tableofcontents.JasperTocReport;
import ch.unibas.medizin.dynamicreports.jasper.base.templatedesign.JasperEmptyTemplateDesign;
import ch.unibas.medizin.dynamicreports.jasper.base.templatedesign.JasperTemplateDesign;
import ch.unibas.medizin.dynamicreports.jasper.builder.export.AbstractJasperExporterBuilder;
import ch.unibas.medizin.dynamicreports.jasper.builder.export.Exporters;
import ch.unibas.medizin.dynamicreports.jasper.builder.export.JasperCsvExporterBuilder;
import ch.unibas.medizin.dynamicreports.jasper.builder.export.JasperDocxExporterBuilder;
import ch.unibas.medizin.dynamicreports.jasper.builder.export.JasperHtmlExporterBuilder;
import ch.unibas.medizin.dynamicreports.jasper.builder.export.JasperImageExporterBuilder;
import ch.unibas.medizin.dynamicreports.jasper.builder.export.JasperJsonExporterBuilder;
import ch.unibas.medizin.dynamicreports.jasper.builder.export.JasperOdsExporterBuilder;
import ch.unibas.medizin.dynamicreports.jasper.builder.export.JasperOdtExporterBuilder;
import ch.unibas.medizin.dynamicreports.jasper.builder.export.JasperPdfExporterBuilder;
import ch.unibas.medizin.dynamicreports.jasper.builder.export.JasperPptxExporterBuilder;
import ch.unibas.medizin.dynamicreports.jasper.builder.export.JasperRtfExporterBuilder;
import ch.unibas.medizin.dynamicreports.jasper.builder.export.JasperTextExporterBuilder;
import ch.unibas.medizin.dynamicreports.jasper.builder.export.JasperXlsxExporterBuilder;
import ch.unibas.medizin.dynamicreports.jasper.builder.export.JasperXmlExporterBuilder;
import ch.unibas.medizin.dynamicreports.jasper.constant.ImageType;
import ch.unibas.medizin.dynamicreports.jasper.exception.JasperDesignException;
import ch.unibas.medizin.dynamicreports.jasper.transformation.ExporterTransform;
import ch.unibas.medizin.dynamicreports.jasper.transformation.JasperTransform;
import ch.unibas.medizin.dynamicreports.report.builder.DynamicReports;
import ch.unibas.medizin.dynamicreports.report.builder.QueryBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.ReportBuilder;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import ch.unibas.medizin.dynamicreports.report.constant.QueryLanguage;
import ch.unibas.medizin.dynamicreports.report.definition.DRITemplateDesign;
import ch.unibas.medizin.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JRRewindableDataSource;
import net.sf.jasperreports.engine.JRVirtualizer;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRGraphics2DExporter;
import net.sf.jasperreports.engine.xml.JRXmlWriter;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleGraphics2DExporterConfiguration;
import net.sf.jasperreports.export.SimpleGraphics2DExporterOutput;
import net.sf.jasperreports.export.SimpleGraphics2DReportConfiguration;
import net.sf.jasperreports.view.JasperDesignViewer;
import net.sf.jasperreports.view.JasperViewer;

/**
 * The most used report builder for creating reports. It allows constructing and customizing the whole report content. A report consists of bands, columns, subtotals, groups, and other parts. Each
 * part is created and configured using a particular builder method, and it's passed to the report builder instance.
 *
 * @author Ricardo Mariaca
 *
 */
public class JasperReportBuilder extends ReportBuilder<JasperReportBuilder> {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private JasperReportDesign reportDesign;
    private JasperDesign jasperDesign;
    private JasperReport jasperReport;
    private JasperPrint jasperPrint;
    private transient JRDataSource dataSource;
    private transient Connection connection;
    private transient JRVirtualizer virtualizer;
    private Integer startPageNumber;
    private Map<String, Object> parameters;

    /**
     * <p>Constructor for JasperReportBuilder.</p>
     */
    public JasperReportBuilder() {
        setTemplateDesign(new JasperEmptyTemplateDesign());
    }

    /**
     * <p>Setter for the field <code>startPageNumber</code>.</p>
     *
     * @param startPageNumber a {@link java.lang.Integer} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder setStartPageNumber(Integer startPageNumber) throws DRException {
        if (Objects.equals(this.startPageNumber, startPageNumber)) {
            return this;
        }
        this.startPageNumber = startPageNumber;
        rebuild();
        return this;
    }

    /**
     * Sets a data source object. Creates a new JRBeanCollectionDataSource data source object.
     *
     * @param collection - the collection values
     * @return a report builder
     */
    public JasperReportBuilder setDataSource(Collection<?> collection) {
        return setDataSource(new JRBeanCollectionDataSource(collection));
    }

    /**
     * Sets a database data source. In this type of data source, data are retrieved from a database.
     *
     * @param resultSet - the resultSet object
     * @return a report builder
     */
    public JasperReportBuilder setDataSource(ResultSet resultSet) {
        return setDataSource(new JRResultSetDataSource(resultSet));
    }

    /**
     * Sets a database data source. In this type of data source, data are retrieved from a database.
     *
     * @param sql        - the sql query
     * @param connection - the database connection
     * @return a report builder
     */
    public JasperReportBuilder setDataSource(String sql, Connection connection) {
        Validate.notNull(sql, "sql must not be null");
        return setDataSource(DynamicReports.query(sql, QueryLanguage.SQL), connection);
    }

    /**
     * Sets a database data source. In this type of data source, data are retrieved from a database.
     *
     * @param query      - the query definition
     * @param connection - the database connection
     * @return a report builder
     */
    public JasperReportBuilder setDataSource(QueryBuilder query, Connection connection) {
        Validate.notNull(query, "query must not be null");
        Validate.notNull(connection, "connection must not be null");
        getObject().setQuery(query.build());
        this.connection = connection;
        dataSource = null;
        return this;
    }

    /**
     * <p>setTemplateDesign.</p>
     *
     * @param inputStream a {@link java.io.InputStream} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder setTemplateDesign(InputStream inputStream) throws DRException {
        return setTemplateDesign(new JasperTemplateDesign(inputStream));
    }

    /**
     * <p>setTemplateDesign.</p>
     *
     * @param file a {@link java.io.File} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder setTemplateDesign(File file) throws DRException {
        return setTemplateDesign(new JasperTemplateDesign(file));
    }

    // template design

    /**
     * <p>setTemplateDesign.</p>
     *
     * @param fileName a {@link java.lang.String} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder setTemplateDesign(String fileName) throws DRException {
        return setTemplateDesign(new JasperTemplateDesign(fileName));
    }

    /**
     * <p>setTemplateDesign.</p>
     *
     * @param jasperDesign a {@link net.sf.jasperreports.engine.design.JasperDesign} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder setTemplateDesign(JasperDesign jasperDesign) throws DRException {
        return setTemplateDesign(new JasperTemplateDesign(jasperDesign));
    }

    /**
     * <p>setTemplateDesign.</p>
     *
     * @param jasperDesignUrl a {@link java.net.URL} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder setTemplateDesign(URL jasperDesignUrl) throws DRException {
        return setTemplateDesign(new JasperTemplateDesign(jasperDesignUrl));
    }

    private JasperReportBuilder setTemplateDesign(DRITemplateDesign<JasperDesign> templateDesign) {
        getObject().setTemplateDesign(templateDesign);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public JasperReportBuilder setParameter(String name, Object value) {
        super.setParameter(name, value);
        parameters = null;
        jasperPrint = null;
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public JasperReportBuilder setParameters(Map<String, Object> parameters) {
        super.setParameters(parameters);
        this.parameters = null;
        jasperPrint = null;
        return this;
    }

    /**
     * <p>rebuild.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder rebuild() throws DRException {
        builded = false;
        reportDesign = null;
        jasperDesign = null;
        jasperReport = null;
        parameters = null;
        jasperPrint = null;
        if (dataSource != null && dataSource instanceof JRRewindableDataSource) {
            try {
                ((JRRewindableDataSource) dataSource).moveFirst();
            } catch (final JRException e) {
                throw new DRException(e);
            }
        }
        return this;
    }

    private JasperReportDesign toJasperReportDesign() throws DRException {
        if (reportDesign == null) {
            final DRIDesignReport report = new DRDesignReport(build());
            reportDesign = new JasperReportDesign(report, startPageNumber);
            final JasperTransform jasperTransform = new JasperTransform(report, reportDesign);
            jasperTransform.transform();
        }
        return reportDesign;
    }

    /**
     * <p>toJasperDesign.</p>
     *
     * @return a {@link net.sf.jasperreports.engine.design.JasperDesign} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    public JasperDesign toJasperDesign() throws DRException {
        if (jasperDesign == null) {
            jasperDesign = toJasperReportDesign().getDesign();
        }
        return jasperDesign;
    }

    /**
     * <p>toJasperReport.</p>
     *
     * @return a {@link net.sf.jasperreports.engine.JasperReport} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    public JasperReport toJasperReport() throws DRException {
        if (jasperReport == null) {
            try {
                jasperReport = JasperCompileManager.compileReport(toJasperDesign());
            } catch (final JRException e) {
                throw new DRException(e);
            }
        }
        return jasperReport;
    }

    /**
     * <p>getJasperParameters.</p>
     *
     * @return a {@link java.util.Map} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    public Map<String, Object> getJasperParameters() throws DRException {
        if (parameters == null) {
            parameters = new HashMap<>();
            final JasperReportDesign jasperReportDesign = toJasperReportDesign();
            parameters.putAll(jasperReportDesign.getParameters());
            if (getReport().getParameterValues() != null) {
                parameters.putAll(getReport().getParameterValues());
            }
        }
        return parameters;
    }

    /**
     * <p>toJasperPrint.</p>
     *
     * @return a {@link net.sf.jasperreports.engine.JasperPrint} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    public JasperPrint toJasperPrint() throws DRException {
        if (jasperPrint == null) {
            final Map<String, Object> parameters = getJasperParameters();
            if (virtualizer != null) {
                parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
            }

            try {
                if (connection != null && toJasperReport().getQuery() != null) {
                    jasperPrint = JasperFillManager.fillReport(toJasperReport(), parameters, connection);
                } else if (dataSource != null) {
                    jasperPrint = JasperFillManager.fillReport(toJasperReport(), parameters, dataSource);
                } else {
                    jasperPrint = JasperFillManager.fillReport(toJasperReport(), parameters);
                }

                if (toJasperReportDesign().isTableOfContents()) {
                    JasperTocReport.createTocReport(toJasperReportDesign(), jasperPrint, parameters);
                }
            } catch (final JRException e) {
                throw new DRException(e);
            }

        }
        return jasperPrint;
    }

    /**
     * <p>show.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder show() throws DRException {
        JasperViewer.viewReport(toJasperPrint());
        return this;
    }

    /**
     * <p>show.</p>
     *
     * @param exitOnClose a boolean.
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder show(boolean exitOnClose) throws DRException {
        JasperViewer.viewReport(toJasperPrint(), exitOnClose, null);
        return this;
    }

    /**
     * <p>showJrXml.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder showJrXml() throws DRException {
        try {
            JasperDesignViewer.viewReportDesign(toJasperDesign());
        } catch (final JRException e) {
            throw new DRException(e);
        }
        return this;
    }

    /**
     * <p>toJrXml.</p>
     *
     * @param outputStream a {@link java.io.OutputStream} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder toJrXml(OutputStream outputStream) throws DRException {
        Validate.notNull(outputStream, "outputStream must not be null");
        try {
            JRXmlWriter.writeReport(toJasperDesign(), outputStream, "UTF-8");
        } catch (final JRException e) {
            throw new DRException(e);
        }
        return this;
    }

    /**
     * <p>print.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder print() throws DRException {
        return print(true);
    }

    /**
     * <p>print.</p>
     *
     * @param withPrintDialog a boolean.
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder print(boolean withPrintDialog) throws DRException {
        try {
            JasperPrintManager.printReport(toJasperPrint(), withPrintDialog);
        } catch (final JRException e) {
            throw new DRException(e);
        }
        return this;
    }

    /**
     * <p>Setter for the field <code>virtualizer</code>.</p>
     *
     * @param virtualizer a {@link net.sf.jasperreports.engine.JRVirtualizer} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder} object.
     */
    public JasperReportBuilder setVirtualizer(JRVirtualizer virtualizer) {
        this.virtualizer = virtualizer;
        return this;
    }

    /**
     * <p>toImage.</p>
     *
     * @param outputStream a {@link java.io.OutputStream} object.
     * @param imageType    a {@link ch.unibas.medizin.dynamicreports.jasper.constant.ImageType} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder toImage(OutputStream outputStream, ImageType imageType) throws DRException {
        return toImage(Exporters.imageExporter(outputStream, imageType));
    }

    /**
     * <p>toImage.</p>
     *
     * @param imageExporterBuilder a {@link ch.unibas.medizin.dynamicreports.jasper.builder.export.JasperImageExporterBuilder} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder toImage(JasperImageExporterBuilder imageExporterBuilder) throws DRException {
        Validate.notNull(imageExporterBuilder, "imageExporterBuilder must not be null");

        final JasperImageExporter imageExporter = imageExporterBuilder.build();

        final JasperPrint jasperPrint = toJasperPrint();
        Integer fromPage = null;
        Integer toPage = null;
        float zoom = 1;
        final String imageType = imageExporter.getImageType().name().toLowerCase();
        int offsetX = 0;
        int offsetY = 0;
        int pageGap = 0;

        if (imageExporter.getZoomRatio() != null) {
            zoom = imageExporter.getZoomRatio();
        }
        if (imageExporter.getOffsetX() != null) {
            offsetX = imageExporter.getOffsetX();
        }
        if (imageExporter.getOffsetY() != null) {
            offsetY = imageExporter.getOffsetY();
        }
        if (imageExporter.getPageGap() != null) {
            pageGap = imageExporter.getPageGap();
        }

        if (imageExporter.getPageIndex() != null && imageExporter.getPageIndex() >= 0) {
            fromPage = imageExporter.getPageIndex();
            toPage = imageExporter.getPageIndex() + 1;
        } else {
            if (imageExporter.getStartPageIndex() != null) {
                fromPage = imageExporter.getStartPageIndex();
            }
            if (imageExporter.getEndPageIndex() != null) {
                toPage = imageExporter.getEndPageIndex();
            }
        }
        if (fromPage == null) {
            fromPage = 0;
        }
        if (toPage == null) {
            toPage = jasperPrint.getPages().size();
        }

        final int pages = toPage - fromPage;

        final int pageWidth = (int) (jasperPrint.getPageWidth() * zoom);
        final int pageHeight = (int) (jasperPrint.getPageHeight() * zoom);
        final int width = pageWidth * pages + pages - 1 + offsetX * 2;
        final int height = (int) (jasperPrint.getPageHeight() * zoom) + offsetY * 2;
        final Image image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        int offset = offsetX;
        for (int i = 0; i < pages; i++) {
            try {
                final SimpleExporterInput exporterInput = new SimpleExporterInput(jasperPrint);
                final SimpleGraphics2DExporterOutput exporterOutput = new SimpleGraphics2DExporterOutput();
                final Image pageImage = new BufferedImage(pageWidth, pageHeight, BufferedImage.TYPE_INT_RGB);
                exporterOutput.setGraphics2D((Graphics2D) pageImage.getGraphics());
                final SimpleGraphics2DReportConfiguration reportExportConfiguration = new SimpleGraphics2DReportConfiguration();
                reportExportConfiguration.setPageIndex(fromPage);
                reportExportConfiguration.setZoomRatio(zoom);
                final SimpleGraphics2DExporterConfiguration exporterConfiguration = new SimpleGraphics2DExporterConfiguration();

                final JRGraphics2DExporter jrExporter = new JRGraphics2DExporter();
                jrExporter.setExporterInput(exporterInput);
                jrExporter.setExporterOutput(exporterOutput);
                jrExporter.setConfiguration(reportExportConfiguration);
                jrExporter.setConfiguration(exporterConfiguration);

                jrExporter.exportReport();
                image.getGraphics().drawImage(pageImage, offset, offsetY, null);
                fromPage++;
                offset += pageWidth + pageGap;
            } catch (final JRException e) {
                throw new DRException(e);
            }
        }
        try {
            if (imageExporter.getOutputStream() != null) {
                ImageIO.write((RenderedImage) image, imageType, imageExporter.getOutputStream());
            } else if (imageExporter.getOutputFileName() != null) {
                ImageIO.write((RenderedImage) image, imageType, new File(imageExporter.getOutputFileName()));
            } else if (imageExporter.getOutputFile() != null) {
                ImageIO.write((RenderedImage) image, imageType, imageExporter.getOutputFile());
            } else {
                throw new JasperDesignException("ImageExporter output not supported");
            }
        } catch (final IOException e) {
            throw new DRException(e);
        }
        return this;
    }

    // image

    /**
     * <p>toCsv.</p>
     *
     * @param outputStream a {@link java.io.OutputStream} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder toCsv(OutputStream outputStream) throws DRException {
        return toCsv(Exporters.csvExporter(outputStream));
    }

    /**
     * <p>toCsv.</p>
     *
     * @param csvExporterBuilder a {@link ch.unibas.medizin.dynamicreports.jasper.builder.export.JasperCsvExporterBuilder} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder toCsv(JasperCsvExporterBuilder csvExporterBuilder) throws DRException {
        return export(csvExporterBuilder);
    }

    // csv

    /**
     * <p>toDocx.</p>
     *
     * @param outputStream a {@link java.io.OutputStream} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder toDocx(OutputStream outputStream) throws DRException {
        return toDocx(Exporters.docxExporter(outputStream));
    }

    /**
     * <p>toDocx.</p>
     *
     * @param docxExporterBuilder a {@link ch.unibas.medizin.dynamicreports.jasper.builder.export.JasperDocxExporterBuilder} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder toDocx(JasperDocxExporterBuilder docxExporterBuilder) throws DRException {
        return export(docxExporterBuilder);
    }

    // docx

    /**
     * <p>toHtml.</p>
     *
     * @param outputStream a {@link java.io.OutputStream} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder toHtml(OutputStream outputStream) throws DRException {
        return toHtml(Exporters.htmlExporter(outputStream));
    }

    /**
     * <p>toHtml.</p>
     *
     * @param htmlExporterBuilder a {@link ch.unibas.medizin.dynamicreports.jasper.builder.export.JasperHtmlExporterBuilder} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder toHtml(JasperHtmlExporterBuilder htmlExporterBuilder) throws DRException {
        return export(htmlExporterBuilder);
    }

    // json
    /**
     * <p>toJson.</p>
     *
     * @param outputStream a {@link java.io.OutputStream} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder toJson(OutputStream outputStream) throws DRException {
        return toJson(Exporters.jsonExporter(outputStream));
    }

    /**
     * <p>toJson.</p>
     *
     * @param jsonExporterBuilder a {@link ch.unibas.medizin.dynamicreports.jasper.builder.export.JasperJsonExporterBuilder} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder toJson(JasperJsonExporterBuilder jsonExporterBuilder) throws DRException {
        return export(jsonExporterBuilder);
    }
    /**
     * <p>toOds.</p>
     *
     * @param outputStream a {@link java.io.OutputStream} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder toOds(OutputStream outputStream) throws DRException {
        return toOds(Exporters.odsExporter(outputStream));
    }

    /**
     * <p>toOds.</p>
     *
     * @param odsExporterBuilder a {@link ch.unibas.medizin.dynamicreports.jasper.builder.export.JasperOdsExporterBuilder} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder toOds(JasperOdsExporterBuilder odsExporterBuilder) throws DRException {
        return export(odsExporterBuilder);
    }

    // ods

    /**
     * <p>toOdt.</p>
     *
     * @param outputStream a {@link java.io.OutputStream} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder toOdt(OutputStream outputStream) throws DRException {
        return toOdt(Exporters.odtExporter(outputStream));
    }

    /**
     * <p>toOdt.</p>
     *
     * @param odtExporterBuilder a {@link ch.unibas.medizin.dynamicreports.jasper.builder.export.JasperOdtExporterBuilder} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder toOdt(JasperOdtExporterBuilder odtExporterBuilder) throws DRException {
        return export(odtExporterBuilder);
    }

    // odt

    /**
     * <p>toPdf.</p>
     *
     * @param outputStream a {@link java.io.OutputStream} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder toPdf(OutputStream outputStream) throws DRException {
        return toPdf(Exporters.pdfExporter(outputStream));
    }

    /**
     * <p>toPdf.</p>
     *
     * @param pdfExporterBuilder a {@link ch.unibas.medizin.dynamicreports.jasper.builder.export.JasperPdfExporterBuilder} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder toPdf(JasperPdfExporterBuilder pdfExporterBuilder) throws DRException {
        return export(pdfExporterBuilder);
    }

    // pdf

    /**
     * <p>toRtf.</p>
     *
     * @param outputStream a {@link java.io.OutputStream} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder toRtf(OutputStream outputStream) throws DRException {
        return toRtf(Exporters.rtfExporter(outputStream));
    }

    /**
     * <p>toRtf.</p>
     *
     * @param rtfExporterBuilder a {@link ch.unibas.medizin.dynamicreports.jasper.builder.export.JasperRtfExporterBuilder} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder toRtf(JasperRtfExporterBuilder rtfExporterBuilder) throws DRException {
        return export(rtfExporterBuilder);
    }

    // rtf

    /**
     * <p>toText.</p>
     *
     * @param outputStream a {@link java.io.OutputStream} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder toText(OutputStream outputStream) throws DRException {
        return toText(Exporters.textExporter(outputStream));
    }

    /**
     * <p>toText.</p>
     *
     * @param textExporterBuilder a {@link ch.unibas.medizin.dynamicreports.jasper.builder.export.JasperTextExporterBuilder} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder toText(JasperTextExporterBuilder textExporterBuilder) throws DRException {
        return export(textExporterBuilder);
    }

    // xlsx

    /**
     * <p>toXlsx.</p>
     *
     * @param outputStream a {@link java.io.OutputStream} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder toXlsx(OutputStream outputStream) throws DRException {
        return toXlsx(Exporters.xlsxExporter(outputStream));
    }

    /**
     * <p>toXlsx.</p>
     *
     * @param xlsxExporterBuilder a {@link ch.unibas.medizin.dynamicreports.jasper.builder.export.JasperXlsxExporterBuilder} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder toXlsx(JasperXlsxExporterBuilder xlsxExporterBuilder) throws DRException {
        return export(xlsxExporterBuilder);
    }

    // xml

    /**
     * <p>toXml.</p>
     *
     * @param outputStream a {@link java.io.OutputStream} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder toXml(OutputStream outputStream) throws DRException {
        return toXml(Exporters.xmlExporter(outputStream));
    }

    /**
     * <p>toXml.</p>
     *
     * @param xmlExporterBuilder a {@link ch.unibas.medizin.dynamicreports.jasper.builder.export.JasperXmlExporterBuilder} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder toXml(JasperXmlExporterBuilder xmlExporterBuilder) throws DRException {
        return export(xmlExporterBuilder);
    }

    // pptx

    /**
     * <p>toPptx.</p>
     *
     * @param outputStream a {@link java.io.OutputStream} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder toPptx(OutputStream outputStream) throws DRException {
        return toPptx(Exporters.pptxExporter(outputStream));
    }

    /**
     * <p>toPptx.</p>
     *
     * @param pptxExporterBuilder a {@link ch.unibas.medizin.dynamicreports.jasper.builder.export.JasperPptxExporterBuilder} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder toPptx(JasperPptxExporterBuilder pptxExporterBuilder) throws DRException {
        return export(pptxExporterBuilder);
    }

    /**
     * <p>export.</p>
     *
     * @param exporterBuilder a {@link ch.unibas.medizin.dynamicreports.jasper.builder.export.AbstractJasperExporterBuilder} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder export(AbstractJasperExporterBuilder<?, ? extends AbstractJasperExporter> exporterBuilder) throws DRException {
        Validate.notNull(exporterBuilder, "exporterBuilder must not be null");
        try {
            final ExporterTransform exporterTransform = new ExporterTransform(exporterBuilder.build());
            final Exporter<ExporterInput, ?, ?, ?> exporter = (Exporter<ExporterInput, ?, ?, ?>) exporterTransform.transform();
            exporter.setExporterInput(new SimpleExporterInput(toJasperPrint()));
            exporter.exportReport();
        } catch (final JRException e) {
            throw new DRException(e);
        }
        return this;
    }

    /**
     * <p>Getter for the field <code>connection</code>.</p>
     *
     * @return a {@link java.sql.Connection} object.
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * <p>Setter for the field <code>connection</code>.</p>
     *
     * @param connection a {@link java.sql.Connection} object.
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder} object.
     */
    public JasperReportBuilder setConnection(Connection connection) {
        Validate.notNull(connection, "connection must not be null");
        this.connection = connection;
        return this;
    }

    /**
     * <p>Getter for the field <code>dataSource</code>.</p>
     *
     * @return a {@link net.sf.jasperreports.engine.JRDataSource} object.
     */
    public JRDataSource getDataSource() {
        return dataSource;
    }

    /**
     * Sets a data source object.
     *
     * @param dataSource - the JRDataSource object
     * @return a report builder
     */
    public JasperReportBuilder setDataSource(JRDataSource dataSource) {
        this.dataSource = dataSource;
        connection = null;
        return this;
    }
}
