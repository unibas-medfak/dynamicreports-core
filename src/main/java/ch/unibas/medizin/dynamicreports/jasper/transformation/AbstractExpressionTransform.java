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
package ch.unibas.medizin.dynamicreports.jasper.transformation;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import ch.unibas.medizin.dynamicreports.design.constant.ResetType;
import ch.unibas.medizin.dynamicreports.design.definition.DRIDesignField;
import ch.unibas.medizin.dynamicreports.design.definition.DRIDesignGroup;
import ch.unibas.medizin.dynamicreports.design.definition.DRIDesignSort;
import ch.unibas.medizin.dynamicreports.design.definition.DRIDesignVariable;
import ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignComplexExpression;
import ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignExpression;
import ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignJasperExpression;
import ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignParameterExpression;
import ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignPropertyExpression;
import ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignSimpleExpression;
import ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignSystemExpression;
import ch.unibas.medizin.dynamicreports.jasper.base.JasperCustomValues;
import ch.unibas.medizin.dynamicreports.jasper.constant.ValueType;
import ch.unibas.medizin.dynamicreports.jasper.exception.JasperDesignException;
import ch.unibas.medizin.dynamicreports.report.constant.SystemExpression;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRGenericElementParameter;
import net.sf.jasperreports.engine.JRGroup;
import net.sf.jasperreports.engine.JRPropertyExpression;
import net.sf.jasperreports.engine.JRVariable;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignGenericElementParameter;
import net.sf.jasperreports.engine.design.JRDesignPropertyExpression;
import net.sf.jasperreports.engine.design.JRDesignSortField;
import net.sf.jasperreports.engine.design.JRDesignVariable;
import net.sf.jasperreports.engine.type.SortFieldTypeEnum;

/**
 * <p>Abstract AbstractExpressionTransform class.</p>
 *
 * @author Ricardo Mariaca
 *
 */
public abstract class AbstractExpressionTransform {
    private static final String VALUE = "$P'{'{0}'}'.getValue(\"{1}\")";
    private static final String FIELD_VALUE = "$F'{'{0}'}'";
    private static final String VARIABLE_VALUE = "$V'{'{0}'}'";
    private static final String PARAMETER_VALUE = "$P'{'{0}'}'";
    private static final String COMPLEX_VALUE = "$P'{'{0}'}'.getValue(\"{1}\", new Object[]'{'{2}'}')";

    private final Map<String, JRDesignExpression> expressions;

    /**
     * <p>Constructor for AbstractExpressionTransform.</p>
     */
    public AbstractExpressionTransform() {
        this.expressions = new HashMap<>();
    }

    /**
     * <p>transform.</p>
     */
    public void transform() {
        for (final DRIDesignField field : getFields()) {
            addField(field);
        }
        for (final DRIDesignSystemExpression expression : getSystemExpressions()) {
            addSystemExpression(expression);
        }
        for (final DRIDesignJasperExpression expression : getJasperExpressions()) {
            addJasperExpression(expression);
        }
        for (final DRIDesignSimpleExpression expression : getSimpleExpressions()) {
            addSimpleExpression(expression);
        }
        for (final DRIDesignComplexExpression complexExpression : getComplexExpressions()) {
            addComplexExpression(complexExpression);
        }
        for (final DRIDesignVariable variable : getVariables()) {
            addVariable(variable);
        }
        for (final DRIDesignSort sort : getSorts()) {
            addSort(sort);
        }
    }

    private void addSystemExpression(DRIDesignSystemExpression systemExpression) {
        if (systemExpression == null) {
            return;
        }
        getCustomValues().addValueType(systemExpression.getName(), ValueType.SYSTEM_EXPRESSION);
        addExpression(systemExpression);
    }

    private void addJasperExpression(DRIDesignJasperExpression jasperExpression) {
        if (jasperExpression == null) {
            return;
        }
        addExpression(jasperExpression);
    }

    /**
     * <p>addSimpleExpression.</p>
     *
     * @param simpleExpression a {@link ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignSimpleExpression} object.
     */
    protected void addSimpleExpression(DRIDesignSimpleExpression simpleExpression) {
        if (simpleExpression == null) {
            return;
        }
        getCustomValues().addSimpleExpression(simpleExpression);
        addExpression(simpleExpression);
    }

    private void addField(DRIDesignField field) {
        try {
            if (!field.isExternal()) {
                addField(field(field));
            }
            getCustomValues().addValueType(field.getName(), ValueType.FIELD);
            addExpression(field);
        } catch (final JRException e) {
            throw new JasperDesignException("Registration failed for field \"" + field.getName() + "\"", e);
        }
    }

    private void addVariable(DRIDesignVariable variable) {
        try {
            addVariable(variable(variable));
            getCustomValues().addValueType(variable.getName(), ValueType.VARIABLE);
            addExpression(variable);
        } catch (final JRException e) {
            throw new JasperDesignException("Registration failed for variable \"" + variable.getName() + "\"", e);
        }
    }

    /**
     * <p>addComplexExpression.</p>
     *
     * @param complexExpression a {@link ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignComplexExpression} object.
     */
    protected void addComplexExpression(DRIDesignComplexExpression complexExpression) {
        if (complexExpression == null) {
            return;
        }
        getCustomValues().addComplexExpression(complexExpression);
        addExpression(complexExpression);
    }

    private void addExpression(DRIDesignExpression expression) {
        if (expressions.containsKey(expression.getName())) {
            throw new JasperDesignException("Duplicate declaration of expression \"" + expression.getName() + "\"");
        }
        expressions.put(expression.getName(), expression(expression));
    }

    private void addSort(DRIDesignSort sort) {
        try {
            addSort(sort(sort));
        } catch (final JRException e) {
            throw new JasperDesignException("Registration failed for sort \"" + sort.getExpression().getName() + "\"", e);
        }
    }

    // field
    private JRDesignField field(DRIDesignField field) {
        final JRDesignField jrField = new JRDesignField();
        jrField.setName(field.getName());
        jrField.setValueClass(field.getValueClass());
        jrField.setDescription(field.getDescription());
        return jrField;
    }

    // variable
    private JRDesignVariable variable(DRIDesignVariable variable) {
        final JRDesignExpression expression = getExpression(variable.getValueExpression());
        final JRDesignExpression initialValueExpression = getExpression(variable.getInitialValueExpression());

        final JRDesignVariable jrVariable = new JRDesignVariable();
        jrVariable.setName(variable.getName());
        jrVariable.setExpression(expression);
        jrVariable.setInitialValueExpression(initialValueExpression);
        jrVariable.setValueClass(variable.getValueClass());
        jrVariable.setCalculation(ConstantTransform.calculation(variable.getCalculation()));
        final ResetType resetType = variable.getResetType();
        jrVariable.setResetType(ConstantTransform.variableResetType(resetType));
        if (resetType.equals(ResetType.GROUP) && variable.getResetGroup() != null) {
            jrVariable.setResetGroup(getGroup(variable.getResetGroup()).getName());
        }
        return jrVariable;
    }

    /**
     * <p>getGroup.</p>
     *
     * @param group a {@link ch.unibas.medizin.dynamicreports.design.definition.DRIDesignGroup} object.
     * @return a {@link net.sf.jasperreports.engine.JRGroup} object.
     */
    protected JRGroup getGroup(DRIDesignGroup group) {
        return null;
    }

    // simple expression
    private JRDesignExpression expression(DRIDesignExpression simpleExpression) {
        final JRDesignExpression expression = new JRDesignExpression();
        expression.setText(getExpressionText(simpleExpression));
        return expression;
    }

    private String getExpressionText(DRIDesignExpression expression) {
        switch (expression) {
            case DRIDesignField driDesignField -> {
                return toFieldValue(expression.getName());
            }
            case DRIDesignVariable driDesignVariable -> {
                return toVariableValue(expression.getName());
            }
            case DRIDesignComplexExpression complexExpression -> {
                StringBuilder values = new StringBuilder();
                for (final DRIDesignExpression valueExpression : complexExpression.getExpressions()) {
                    values.append(", ").append(getExpressionText(valueExpression));
                }
                if (!values.isEmpty()) {
                    values = new StringBuilder(values.substring(2));
                }
                final String parameterName = getExpressionParameterName(complexExpression.getParameterName());
                return MessageFormat.format(COMPLEX_VALUE, parameterName, expression.getName(), values.toString());
            }
            case DRIDesignSimpleExpression driDesignSimpleExpression -> {
                final String parameterName = getExpressionParameterName(driDesignSimpleExpression.getParameterName());
                return MessageFormat.format(VALUE, parameterName, expression.getName());
            }
            case DRIDesignSystemExpression driDesignSystemExpression -> {
                final String name = expression.getName();
                if (name.equals(SystemExpression.PAGE_NUMBER.name())) {
                    return toVariableValue(JRVariable.PAGE_NUMBER);
                } else {
                    return toVariableValue(name);
                }
            }
            // throw new JasperDesignException("System expression \"" + name + "\" not supported");
            case DRIDesignJasperExpression driDesignJasperExpression -> {
                return driDesignJasperExpression.getExpression();
            }
            default ->
                    throw new JasperDesignException("Expression " + expression.getClass().getName() + " not supported");
        }
    }

    private String getExpressionParameterName(String parameterName) {
        return Objects.requireNonNullElse(parameterName, JasperCustomValues.NAME);
    }

    private String toFieldValue(String expression) {
        return MessageFormat.format(FIELD_VALUE, expression);
    }

    private String toVariableValue(String expression) {
        return MessageFormat.format(VARIABLE_VALUE, expression);
    }

    /**
     * <p>toParameterValue.</p>
     *
     * @param expression a {@link java.lang.String} object.
     * @return a {@link java.lang.String} object.
     */
    protected String toParameterValue(String expression) {
        return MessageFormat.format(PARAMETER_VALUE, expression);
    }

    /**
     * <p>getExpression.</p>
     *
     * @param expression a {@link ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignExpression} object.
     * @return a {@link net.sf.jasperreports.engine.design.JRDesignExpression} object.
     */
    public JRDesignExpression getExpression(DRIDesignExpression expression) {
        if (expression == null) {
            return null;
        }
        if (!expressions.containsKey(expression.getName())) {
            throw new JasperDesignException("Expression \"" + expression.getName() + "\" is not registered");
        }
        return expressions.get(expression.getName());
    }

    // sort
    private JRDesignSortField sort(DRIDesignSort sort) {
        final DRIDesignExpression expression = sort.getExpression();
        String name;
        SortFieldTypeEnum type;
        if (expression instanceof DRIDesignField) {
            name = expression.getName();
            type = SortFieldTypeEnum.FIELD;
        } else if (expression instanceof DRIDesignVariable) {
            name = expression.getName();
            type = SortFieldTypeEnum.VARIABLE;
        } else {
            throw new JasperDesignException("Sort expression \"" + expression.getName() + "\" not supported");
        }

        final JRDesignSortField jrSort = new JRDesignSortField();
        jrSort.setName(name);
        jrSort.setOrder(ConstantTransform.orderType(sort.getOrderType()));
        jrSort.setType(type);
        return jrSort;
    }

    /**
     * <p>getPropertyExpression.</p>
     *
     * @param propertyExpression a {@link ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignPropertyExpression} object.
     * @return a {@link net.sf.jasperreports.engine.JRPropertyExpression} object.
     */
    protected JRPropertyExpression getPropertyExpression(DRIDesignPropertyExpression propertyExpression) {
        final JRDesignPropertyExpression jrPropertyExpression = new JRDesignPropertyExpression();
        jrPropertyExpression.setName(propertyExpression.getName());
        jrPropertyExpression.setValueExpression(getExpression(propertyExpression.getValueExpression()));
        return jrPropertyExpression;
    }

    /**
     * <p>getGenericElementParameterExpression.</p>
     *
     * @param parameterExpression a {@link ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignParameterExpression} object.
     * @return a {@link net.sf.jasperreports.engine.JRGenericElementParameter} object.
     */
    protected JRGenericElementParameter getGenericElementParameterExpression(DRIDesignParameterExpression parameterExpression) {
        final JRDesignGenericElementParameter jrParameterExpression = new JRDesignGenericElementParameter();
        jrParameterExpression.setName(parameterExpression.getName());
        jrParameterExpression.setValueExpression(getExpression(parameterExpression.getValueExpression()));
        return jrParameterExpression;
    }

    /**
     * <p>getCustomValues.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.base.JasperCustomValues} object.
     */
    protected abstract JasperCustomValues getCustomValues();

    /**
     * <p>getFields.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    protected abstract Collection<DRIDesignField> getFields();

    /**
     * <p>getVariables.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    protected abstract Collection<DRIDesignVariable> getVariables();

    /**
     * <p>getSystemExpressions.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    protected abstract Collection<DRIDesignSystemExpression> getSystemExpressions();

    /**
     * <p>getJasperExpressions.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    protected abstract Collection<DRIDesignJasperExpression> getJasperExpressions();

    /**
     * <p>getSimpleExpressions.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    protected abstract Collection<DRIDesignSimpleExpression> getSimpleExpressions();

    /**
     * <p>getComplexExpressions.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    protected abstract Collection<DRIDesignComplexExpression> getComplexExpressions();

    /**
     * <p>getSorts.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    protected abstract Collection<DRIDesignSort> getSorts();

    /**
     * <p>addField.</p>
     *
     * @param field a {@link net.sf.jasperreports.engine.design.JRDesignField} object.
     * @throws net.sf.jasperreports.engine.JRException if any.
     */
    protected abstract void addField(JRDesignField field) throws JRException;

    /**
     * <p>addVariable.</p>
     *
     * @param variable a {@link net.sf.jasperreports.engine.design.JRDesignVariable} object.
     * @throws net.sf.jasperreports.engine.JRException if any.
     */
    protected abstract void addVariable(JRDesignVariable variable) throws JRException;

    /**
     * <p>addSort.</p>
     *
     * @param sort a {@link net.sf.jasperreports.engine.design.JRDesignSortField} object.
     * @throws net.sf.jasperreports.engine.JRException if any.
     */
    protected abstract void addSort(JRDesignSortField sort) throws JRException;
}
