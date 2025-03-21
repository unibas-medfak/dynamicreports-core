/*
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2018 Ricardo Mariaca and the Dynamic Reports Contributors
 *
 * This file is part of DynamicReports.
 *
 * DynamicReports is free software: you can redistribute it and/or modify it under the terms of the
 * GNU Lesser General Public License as published by the Free Software Foundation, either version 3
 * of the License, or (at your option) any later version.
 *
 * DynamicReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with
 * DynamicReports. If not, see <http://www.gnu.org/licenses/>.
 */

package net.sf.dynamicreports.jasper.base;

import net.sf.dynamicreports.design.definition.expression.DRIDesignComplexExpression;
import net.sf.dynamicreports.design.definition.expression.DRIDesignSimpleExpression;
import net.sf.dynamicreports.jasper.base.tableofcontents.JasperTocHeading;
import net.sf.dynamicreports.jasper.constant.ValueType;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.DRICustomValues;
import net.sf.dynamicreports.report.definition.chart.DRIChartCustomizer;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static java.lang.Boolean.TRUE;
import static net.sf.dynamicreports.jasper.base.JasperScriptletManager.USE_THREAD_SAFE_SCRIPLET_MANAGER_PROPERTY_KEY;

/**
 * <p>
 * JasperCustomValues class.
 * </p>
 *
 * @author edwin.njeru
 * 
 */
public class JasperCustomValues implements DRICustomValues {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private Map<String, ValueType> valueTypes;
    private Map<String, DRIDesignSimpleExpression> simpleExpressions;
    private Map<String, DRIDesignComplexExpression> complexExpressions;
    private Map<String, List<DRIChartCustomizer>> chartCustomizers;
    private Map<String, Object> systemValues;
    private Integer startPageNumber;
    private Map<String, JasperTocHeading> tocHeadings;
    private Integer subreportWidth;
    private transient JasperScriptletManager scriptletManager;

    /**
     * <p>
     * Constructor for JasperCustomValues.
     * </p>
     *
     * @param properties a {@link java.util.Properties} object.
     */
    public JasperCustomValues(Properties properties) {
        init(properties);
    }

    private void init(Properties properties) {
        valueTypes = new HashMap<>();
        simpleExpressions = new HashMap<>();
        complexExpressions = new HashMap<>();
        chartCustomizers = new HashMap<>();
        systemValues = new HashMap<>();
        scriptletManager = createScriptletManager(properties);
    }

    private JasperScriptletManager createScriptletManager(Properties properties) {
        String useThreadSafeScriptletManagerPropertyValue = properties.getProperty(USE_THREAD_SAFE_SCRIPLET_MANAGER_PROPERTY_KEY);
        if (TRUE.toString().equalsIgnoreCase(useThreadSafeScriptletManagerPropertyValue)) {
            return new ThreadSafeJasperScriptletManager();
        }
        return new DefaultJasperScriptletManager();
    }

    /**
     * <p>
     * addSimpleExpression.
     * </p>
     *
     * @param simpleExpression a {@link net.sf.dynamicreports.design.definition.expression.DRIDesignSimpleExpression} object.
     */
    public void addSimpleExpression(DRIDesignSimpleExpression simpleExpression) {
        simpleExpressions.put(simpleExpression.getName(), simpleExpression);
        addValueType(simpleExpression.getName(), ValueType.SIMPLE_EXPRESSION);
    }

    /**
     * <p>
     * addComplexExpression.
     * </p>
     *
     * @param complexExpression a {@link net.sf.dynamicreports.design.definition.expression.DRIDesignComplexExpression} object.
     */
    public void addComplexExpression(DRIDesignComplexExpression complexExpression) {
        complexExpressions.put(complexExpression.getName(), complexExpression);
        addValueType(complexExpression.getName(), ValueType.COMPLEX_EXPRESSION);
    }

    /**
     * <p>
     * addChartCustomizers.
     * </p>
     *
     * @param name             a {@link java.lang.String} object.
     * @param chartCustomizers a {@link java.util.List} object.
     */
    public void addChartCustomizers(String name, List<DRIChartCustomizer> chartCustomizers) {
        this.chartCustomizers.put(name, chartCustomizers);
    }

    /**
     * <p>
     * addValueType.
     * </p>
     *
     * @param name      a {@link java.lang.String} object.
     * @param valueType a {@link net.sf.dynamicreports.jasper.constant.ValueType} object.
     */
    public void addValueType(String name, ValueType valueType) {
        /*
         * if (valueTypes.containsKey(name)) { throw new JasperDesignException("Duplicate value name \""
         * + name + "\""); }
         */
        valueTypes.put(name, valueType);
    }

    /**
     * <p>
     * getValueType.
     * </p>
     *
     * @param name a {@link java.lang.String} object.
     * @return a {@link net.sf.dynamicreports.jasper.constant.ValueType} object.
     */
    protected ValueType getValueType(String name) {
        return valueTypes.get(name);
    }

    /**
     * <p>
     * getSimpleExpression.
     * </p>
     *
     * @param name a {@link java.lang.String} object.
     * @return a {@link net.sf.dynamicreports.design.definition.expression.DRIDesignSimpleExpression} object.
     */
    protected DRIDesignSimpleExpression getSimpleExpression(String name) {
        return simpleExpressions.get(name);
    }

    /**
     * <p>
     * getComplexExpression.
     * </p>
     *
     * @param name a {@link java.lang.String} object.
     * @return a {@link net.sf.dynamicreports.design.definition.expression.DRIDesignComplexExpression} object.
     */
    protected DRIDesignComplexExpression getComplexExpression(String name) {
        return complexExpressions.get(name);
    }

    /**
     * <p>
     * Getter for the field <code>chartCustomizers</code>.
     * </p>
     *
     * @param name a {@link java.lang.String} object.
     * @return a {@link java.util.List} object.
     */
    protected List<DRIChartCustomizer> getChartCustomizers(String name) {
        return chartCustomizers.get(name);
    }

    /**
     * <p>
     * isEmpty.
     * </p>
     *
     * @return a boolean.
     */
    public boolean isEmpty() {
        if (!simpleExpressions.isEmpty()) {
            return false;
        }
        if (!complexExpressions.isEmpty()) {
            return false;
        }
        if (!chartCustomizers.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * <p>
     * getValue.
     * </p>
     *
     * @param valueName a {@link java.lang.String} object.
     * @return a {@link java.lang.Object} object.
     */
    public Object getValue(String valueName) {
        return scriptletManager.getJasperScriptlet().getValue(valueName);

    }

    /**
     * <p>
     * getValue.
     * </p>
     *
     * @param name   a {@link java.lang.String} object.
     * @param values an array of {@link java.lang.Object} objects.
     * @return a {@link java.lang.Object} object.
     */
    public Object getValue(String name, Object[] values) {
        return scriptletManager.getJasperScriptlet().getValue(name, values);
    }

    /** {@inheritDoc} */
    @Override
    public void setSystemValue(String name, Object value) {
        systemValues.put(name, value);
    }

    /**
     * <p>
     * getSystemValue.
     * </p>
     *
     * @param name a {@link java.lang.String} object.
     * @return a {@link java.lang.Object} object.
     */
    protected Object getSystemValue(String name) {
        return systemValues.get(name);
    }

    /**
     * <p>
     * Getter for the field <code>jasperScriptlet</code>.
     * </p>
     *
     * @return a {@link net.sf.dynamicreports.jasper.base.JasperScriptlet} object.
     */
    protected JasperScriptlet getJasperScriptlet() {
        return scriptletManager.getJasperScriptlet();
    }

    /**
     * <p>
     * Setter for the field <code>jasperScriptlet</code>.
     * </p>
     *
     * @param jasperScriptlet a {@link net.sf.dynamicreports.jasper.base.JasperScriptlet} object.
     */
    protected void setJasperScriptlet(JasperScriptlet jasperScriptlet) {
        scriptletManager.setJasperScriptlet(jasperScriptlet);
    }

    /**
     * <p>
     * Getter for the field <code>startPageNumber</code>.
     * </p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getStartPageNumber() {
        return startPageNumber;
    }

    /**
     * <p>
     * Setter for the field <code>startPageNumber</code>.
     * </p>
     *
     * @param startPageNumber a {@link java.lang.Integer} object.
     */
    public void setStartPageNumber(Integer startPageNumber) {
        this.startPageNumber = startPageNumber;
    }

    /** {@inheritDoc} */
    @Override
    public void addTocHeading(int level, String id, String text, Object customValue) {
        JasperTocHeading heading = new JasperTocHeading();
        heading.setLevel(level);
        heading.setText(text);
        heading.setReference(id);
        heading.setCustomValue(customValue);
        tocHeadings.put(id, heading);
    }

    /** {@inheritDoc} */
    @Override
    public Map<String, JasperTocHeading> getTocHeadings() {
        return tocHeadings;
    }

    /** {@inheritDoc} */
    @Override
    public void setTocHeadings(Map<String, JasperTocHeading> tocHeadings) {
        this.tocHeadings = tocHeadings;
    }

    /**
     * <p>
     * Getter for the field <code>subreportWidth</code>.
     * </p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getSubreportWidth() {
        return subreportWidth;
    }

    /** {@inheritDoc} */
    @Override
    public void setSubreportWidth(Integer subreportWidth) {
        this.subreportWidth = subreportWidth;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (String name : valueTypes.keySet()) {
            result.append(valueTypes.get(name).name() + ":" + name);
            result.append(", ");
        }
        return "{" + StringUtils.removeEnd(result.toString(), ", ") + "}";
    }

    /**
     * Getter for testing purposes.
     *
     * @return the {@link JasperScriptletManager}
     */
    JasperScriptletManager getScriptletManager() {
        return scriptletManager;
    }


}
