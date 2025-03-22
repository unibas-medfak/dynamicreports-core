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

import ch.unibas.medizin.dynamicreports.design.definition.DRIDesignDataset;
import ch.unibas.medizin.dynamicreports.design.definition.DRIDesignField;
import ch.unibas.medizin.dynamicreports.design.definition.DRIDesignParameter;
import ch.unibas.medizin.dynamicreports.design.definition.DRIDesignReport;
import ch.unibas.medizin.dynamicreports.design.definition.DRIDesignSort;
import ch.unibas.medizin.dynamicreports.design.definition.DRIDesignTemplateDesign;
import ch.unibas.medizin.dynamicreports.design.definition.DRIDesignVariable;
import ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignComplexExpression;
import ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignExpression;
import ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignJasperExpression;
import ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignSimpleExpression;
import ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignSystemExpression;
import ch.unibas.medizin.dynamicreports.design.definition.style.DRIDesignStyle;
import ch.unibas.medizin.dynamicreports.design.transformation.AbstractExpressionTransform;
import ch.unibas.medizin.dynamicreports.design.transformation.BandTransform;
import ch.unibas.medizin.dynamicreports.design.transformation.ChartTransform;
import ch.unibas.medizin.dynamicreports.design.transformation.ColumnGridTransform;
import ch.unibas.medizin.dynamicreports.design.transformation.ColumnTransform;
import ch.unibas.medizin.dynamicreports.design.transformation.ComponentTransform;
import ch.unibas.medizin.dynamicreports.design.transformation.CrosstabTransform;
import ch.unibas.medizin.dynamicreports.design.transformation.DatasetTransform;
import ch.unibas.medizin.dynamicreports.design.transformation.DesignTransformAccessor;
import ch.unibas.medizin.dynamicreports.design.transformation.GroupTransform;
import ch.unibas.medizin.dynamicreports.design.transformation.MainDatasetExpressionTransform;
import ch.unibas.medizin.dynamicreports.design.transformation.PageTransform;
import ch.unibas.medizin.dynamicreports.design.transformation.ReportTransform;
import ch.unibas.medizin.dynamicreports.design.transformation.StyleTransform;
import ch.unibas.medizin.dynamicreports.design.transformation.SubtotalTransform;
import ch.unibas.medizin.dynamicreports.design.transformation.TableOfContentsTransform;
import ch.unibas.medizin.dynamicreports.design.transformation.TemplateTransform;
import ch.unibas.medizin.dynamicreports.jasper.base.tableofcontents.JasperTocHeading;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import ch.unibas.medizin.dynamicreports.report.constant.Orientation;
import ch.unibas.medizin.dynamicreports.report.constant.RunDirection;
import ch.unibas.medizin.dynamicreports.report.constant.WhenNoDataType;
import ch.unibas.medizin.dynamicreports.report.constant.WhenResourceMissingType;
import ch.unibas.medizin.dynamicreports.report.definition.DRIDataset;
import ch.unibas.medizin.dynamicreports.report.definition.DRIReport;
import ch.unibas.medizin.dynamicreports.report.definition.DRIScriptlet;
import ch.unibas.medizin.dynamicreports.report.definition.DRITableOfContentsCustomizer;
import ch.unibas.medizin.dynamicreports.report.exception.DRException;

import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * <p>DRDesignReport class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DRDesignReport implements DesignTransformAccessor, DRIDesignReport {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private DRIReport report;
    private Integer pageWidth;
    private Map<String, JasperTocHeading> tocHeadings;
    private ReportTransform reportTransform;
    private TemplateTransform templateTransform;
    private PageTransform pageTransform;
    private MainDatasetExpressionTransform mainDatasetExpressionTransform;
    private BandTransform bandTransform;
    private ComponentTransform componentTransform;
    private GroupTransform groupTransform;
    private ColumnGridTransform columnGridTransform;
    private ColumnTransform columnTransform;
    private SubtotalTransform subtotalTransform;
    private StyleTransform styleTransform;
    private ChartTransform chartTransform;
    private CrosstabTransform crosstabTransform;
    private DatasetTransform datasetTransform;
    private TableOfContentsTransform tableOfContentsTransform;
    private AbstractExpressionTransform expressionTransform;

    /**
     * <p>Constructor for DRDesignReport.</p>
     *
     * @param report a {@link ch.unibas.medizin.dynamicreports.report.definition.DRIReport} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    public DRDesignReport(DRIReport report) throws DRException {
        this(report, null, null);
    }

    /**
     * <p>Constructor for DRDesignReport.</p>
     *
     * @param report      a {@link ch.unibas.medizin.dynamicreports.report.definition.DRIReport} object.
     * @param pageWidth   a {@link java.lang.Integer} object.
     * @param tocHeadings a {@link java.util.Map} object.
     * @throws ch.unibas.medizin.dynamicreports.report.exception.DRException if any.
     */
    public DRDesignReport(DRIReport report, Integer pageWidth, Map<String, JasperTocHeading> tocHeadings) throws DRException {
        this.report = report;
        this.pageWidth = pageWidth;
        this.tocHeadings = tocHeadings;
        init();
        transform();
    }

    private void init() {
        reportTransform = new ReportTransform(this);
        templateTransform = new TemplateTransform(this);
        pageTransform = new PageTransform(this);
        mainDatasetExpressionTransform = new MainDatasetExpressionTransform(this);
        groupTransform = new GroupTransform(this);
        bandTransform = new BandTransform(this);
        componentTransform = new ComponentTransform(this);
        columnGridTransform = new ColumnGridTransform(this);
        columnTransform = new ColumnTransform(this);
        subtotalTransform = new SubtotalTransform(this);
        styleTransform = new StyleTransform(this);
        chartTransform = new ChartTransform(this);
        crosstabTransform = new CrosstabTransform(this);
        datasetTransform = new DatasetTransform(this);
        tableOfContentsTransform = new TableOfContentsTransform(this);
        transformToMainDataset();
    }

    private void transform() throws DRException {
        reportTransform.transform();
        pageTransform.transform();
        groupTransform.transform();
        mainDatasetExpressionTransform.transform();
        bandTransform.transform();
        columnGridTransform.transform();
        columnTransform.transform();
        groupTransform.transformHeaderAndFooter();
        pageTransform.transformPageWidth();
        subtotalTransform.transform();
        bandTransform.prepareBands();
        styleTransform.transformTemplateStyles();
    }

    /** {@inheritDoc} */
    @Override
    public DRIReport getReport() {
        return report;
    }

    /** {@inheritDoc} */
    @Override
    public Integer getPageWidth() {
        return pageWidth;
    }

    /** {@inheritDoc} */
    @Override
    public ReportTransform getReportTransform() {
        return reportTransform;
    }

    /** {@inheritDoc} */
    @Override
    public TemplateTransform getTemplateTransform() {
        return templateTransform;
    }

    /** {@inheritDoc} */
    @Override
    public PageTransform getPageTransform() {
        return pageTransform;
    }

    /** {@inheritDoc} */
    @Override
    public void transformToMainDataset() {
        transformToDataset(null);
    }

    /** {@inheritDoc} */
    @Override
    public void transformToDataset(DRIDataset dataset) {
        if (dataset != null) {
            expressionTransform = datasetTransform.getDatasetExpressionTransform(dataset);
        } else {
            expressionTransform = mainDatasetExpressionTransform;
        }
    }

    /** {@inheritDoc} */
    @Override
    public AbstractExpressionTransform getExpressionTransform() {
        return expressionTransform;
    }

    /** {@inheritDoc} */
    @Override
    public BandTransform getBandTransform() {
        return bandTransform;
    }

    /** {@inheritDoc} */
    @Override
    public ComponentTransform getComponentTransform() {
        return componentTransform;
    }

    /** {@inheritDoc} */
    @Override
    public GroupTransform getGroupTransform() {
        return groupTransform;
    }

    /** {@inheritDoc} */
    @Override
    public ColumnTransform getColumnTransform() {
        return columnTransform;
    }

    /** {@inheritDoc} */
    @Override
    public ColumnGridTransform getColumnGridTransform() {
        return columnGridTransform;
    }

    /** {@inheritDoc} */
    @Override
    public StyleTransform getStyleTransform() {
        return styleTransform;
    }

    /** {@inheritDoc} */
    @Override
    public ChartTransform getChartTransform() {
        return chartTransform;
    }

    /** {@inheritDoc} */
    @Override
    public CrosstabTransform getCrosstabTransform() {
        return crosstabTransform;
    }

    /** {@inheritDoc} */
    @Override
    public DatasetTransform getDatasetTransform() {
        return datasetTransform;
    }

    /** {@inheritDoc} */
    @Override
    public TableOfContentsTransform getTableOfContentsTransform() {
        return tableOfContentsTransform;
    }

    /** {@inheritDoc} */
    @Override
    public DRIDesignTemplateDesign getTemplateDesign() {
        return reportTransform.getTemplateDesign();
    }

    /** {@inheritDoc} */
    @Override
    public String getReportName() {
        return templateTransform.getReportName();
    }

    /** {@inheritDoc} */
    @Override
    public Locale getLocale() {
        return templateTransform.getLocale();
    }

    /** {@inheritDoc} */
    @Override
    public ResourceBundle getResourceBundle() {
        return report.getResourceBundle();
    }

    /** {@inheritDoc} */
    @Override
    public String getResourceBundleName() {
        return templateTransform.getResourceBundleName();
    }

    /** {@inheritDoc} */
    @Override
    public boolean isIgnorePagination() {
        return templateTransform.isIgnorePagination();
    }

    /** {@inheritDoc} */
    @Override
    public Properties getProperties() {
        return report.getProperties();
    }

    /** {@inheritDoc} */
    @Override
    public DRDesignQuery getQuery() {
        return reportTransform.getQuery();
    }

    /** {@inheritDoc} */
    @Override
    public DRDesignPage getPage() {
        return pageTransform.getPage();
    }

    /** {@inheritDoc} */
    @Override
    public WhenNoDataType getWhenNoDataType() {
        return templateTransform.getWhenNoDataType(getDetailBands().isEmpty(), getNoDataBand());
    }

    /** {@inheritDoc} */
    @Override
    public WhenResourceMissingType getWhenResourceMissingType() {
        return templateTransform.getWhenResourceMissingType();
    }

    /** {@inheritDoc} */
    @Override
    public boolean isTitleOnANewPage() {
        return templateTransform.isTitleOnANewPage();
    }

    /** {@inheritDoc} */
    @Override
    public boolean isSummaryOnANewPage() {
        return templateTransform.isSummaryOnANewPage();
    }

    /** {@inheritDoc} */
    @Override
    public boolean isSummaryWithPageHeaderAndFooter() {
        return templateTransform.isSummaryWithPageHeaderAndFooter();
    }

    /** {@inheritDoc} */
    @Override
    public boolean isFloatColumnFooter() {
        return templateTransform.isFloatColumnFooter();
    }

    /** {@inheritDoc} */
    @Override
    public Orientation getPrintOrder() {
        return templateTransform.getPrintOrder();
    }

    /** {@inheritDoc} */
    @Override
    public RunDirection getColumnDirection() {
        return templateTransform.getColumnDirection();
    }

    /** {@inheritDoc} */
    @Override
    public String getLanguage() {
        return templateTransform.getLanguage();
    }

    /** {@inheritDoc} */
    @Override
    public boolean isTableOfContents() {
        return templateTransform.isTableOfContents(tocHeadings);
    }

    /** {@inheritDoc} */
    @Override
    public Map<String, JasperTocHeading> getTableOfContentsHeadings() {
        return tocHeadings;
    }

    /** {@inheritDoc} */
    @Override
    public DRITableOfContentsCustomizer getTableOfContentsCustomizer() {
        return templateTransform.getTableOfContentsCustomizer();
    }

    /** {@inheritDoc} */
    @Override
    public DRIDesignExpression getFilterExpression() {
        return reportTransform.getFilterExpression();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<DRIDesignParameter> getParameters() {
        return reportTransform.getParameters();
    }

    /** {@inheritDoc} */
    @Override
    public Map<String, Object> getParameterValues() {
        return report.getParameterValues();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<DRIScriptlet> getScriptlets() {
        return report.getScriptlets();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<DRIDesignField> getFields() {
        return mainDatasetExpressionTransform.getFields();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<DRIDesignSystemExpression> getSystemExpressions() {
        return mainDatasetExpressionTransform.getSystemExpressions();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<DRIDesignJasperExpression> getJasperExpressions() {
        return mainDatasetExpressionTransform.getJasperExpressions();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<DRIDesignSimpleExpression> getSimpleExpressions() {
        return mainDatasetExpressionTransform.getSimpleExpressions();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<DRIDesignStyle> getStyles() {
        return styleTransform.getStyles();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<DRDesignGroup> getGroups() {
        return groupTransform.getGroups();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<DRIDesignVariable> getVariables() {
        return mainDatasetExpressionTransform.getVariables();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<DRIDesignComplexExpression> getComplexExpressions() {
        return mainDatasetExpressionTransform.getComplexExpressions();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<DRIDesignSort> getSorts() {
        return mainDatasetExpressionTransform.getSorts();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<DRIDesignDataset> getDatasets() {
        return datasetTransform.getDatasets();
    }

    /** {@inheritDoc} */
    @Override
    public DRDesignBand getTitleBand() {
        return bandTransform.getTitleBand();
    }

    /** {@inheritDoc} */
    @Override
    public DRDesignBand getPageHeaderBand() {
        return bandTransform.getPageHeaderBand();
    }

    /** {@inheritDoc} */
    @Override
    public DRDesignBand getPageFooterBand() {
        return bandTransform.getPageFooterBand();
    }

    /** {@inheritDoc} */
    @Override
    public DRDesignBand getColumnHeaderBand() {
        return bandTransform.getColumnHeaderBand();
    }

    /** {@inheritDoc} */
    @Override
    public DRDesignBand getColumnFooterBand() {
        return bandTransform.getColumnFooterBand();
    }

    /** {@inheritDoc} */
    @Override
    public List<DRDesignBand> getDetailBands() {
        return bandTransform.getDetailBands();
    }

    /** {@inheritDoc} */
    @Override
    public DRDesignBand getLastPageFooterBand() {
        return bandTransform.getLastPageFooterBand();
    }

    /** {@inheritDoc} */
    @Override
    public DRDesignBand getSummaryBand() {
        return bandTransform.getSummaryBand();
    }

    /** {@inheritDoc} */
    @Override
    public DRDesignBand getNoDataBand() {
        return bandTransform.getNoDataBand();
    }

    /** {@inheritDoc} */
    @Override
    public DRDesignBand getBackgroundBand() {
        return bandTransform.getBackgroundBand();
    }
}
