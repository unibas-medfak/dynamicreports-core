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
package ch.unibas.medizin.dynamicreports.jasper.base;

import ch.unibas.medizin.dynamicreports.report.definition.DRIScriptlet;
import net.sf.jasperreports.engine.JRAbstractScriptlet;
import net.sf.jasperreports.engine.JRScriptletException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>CustomScriptlet class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class CustomScriptlet extends JRAbstractScriptlet {
    private static final Log log = LogFactory.getLog(CustomScriptlet.class);

    private final DRIScriptlet scriptlet;
    private JasperReportParameters reportParameters;

    /**
     * <p>Constructor for CustomScriptlet.</p>
     *
     * @param scriptlet a {@link ch.unibas.medizin.dynamicreports.report.definition.DRIScriptlet} object.
     */
    public CustomScriptlet(DRIScriptlet scriptlet) {
        this.scriptlet = scriptlet;
    }

    /** {@inheritDoc} */
    @Override
    public void afterColumnInit() {
        scriptlet.afterColumnInit(getReportParameters());
    }

    /** {@inheritDoc} */
    @Override
    public void afterDetailEval() {
        scriptlet.afterDetailEval(getReportParameters());
    }

    /** {@inheritDoc} */
    @Override
    public void afterGroupInit(String groupName) {
        scriptlet.afterGroupInit(groupName, getReportParameters());
    }

    /** {@inheritDoc} */
    @Override
    public void afterPageInit() {
        scriptlet.afterPageInit(getReportParameters());
    }

    /** {@inheritDoc} */
    @Override
    public void afterReportInit() {
        scriptlet.afterReportInit(getReportParameters());
    }

    /** {@inheritDoc} */
    @Override
    public void beforeColumnInit() {
        scriptlet.beforeColumnInit(getReportParameters());
    }

    /** {@inheritDoc} */
    @Override
    public void beforeDetailEval() {
        scriptlet.beforeDetailEval(getReportParameters());
    }

    /** {@inheritDoc} */
    @Override
    public void beforeGroupInit(String groupName) {
        scriptlet.beforeGroupInit(groupName, getReportParameters());
    }

    /** {@inheritDoc} */
    @Override
    public void beforePageInit() {
        scriptlet.beforePageInit(getReportParameters());
    }

    /** {@inheritDoc} */
    @Override
    public void beforeReportInit() {
        scriptlet.beforeReportInit(getReportParameters());
    }

    private JasperReportParameters getReportParameters() {
        if (reportParameters == null) {
            try {
                reportParameters = ((JasperScriptlet) getParameterValue(JasperScriptlet.SCRIPTLET_NAME)).getReportParameters();
            } catch (final JRScriptletException e) {
                log.error(e.getMessage());
            }
        }
        return reportParameters;
    }

    /**
     * <p>Getter for the field <code>scriptlet</code>.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.definition.DRIScriptlet} object.
     */
    protected DRIScriptlet getScriptlet() {
        return scriptlet;
    }
}
