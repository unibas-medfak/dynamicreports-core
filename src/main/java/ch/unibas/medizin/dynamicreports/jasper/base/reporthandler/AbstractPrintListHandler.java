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
package ch.unibas.medizin.dynamicreports.jasper.base.reporthandler;

import ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder;
import ch.unibas.medizin.dynamicreports.jasper.definition.JasperReportHandler;
import net.sf.jasperreports.engine.JasperPrint;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * <p>Abstract AbstractPrintListHandler class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public abstract class AbstractPrintListHandler implements JasperReportHandler {
    private static final Logger log = LogManager.getLogger();

    private boolean continuousPageNumbering;
    private int pageNumber;

    /**
     * <p>Constructor for AbstractPrintListHandler.</p>
     */
    public AbstractPrintListHandler() {
        continuousPageNumbering = false;
        pageNumber = 1;
    }

    /** {@inheritDoc} */
    @Override
    public void concatenate(JasperReportBuilder... jasperReportBuilders) {
        for (JasperReportBuilder jasperReportBuilder : jasperReportBuilders) {
            try {
                if (continuousPageNumbering) {
                    jasperReportBuilder.setStartPageNumber(pageNumber);
                } else {
                    jasperReportBuilder.setStartPageNumber(null);
                }
                JasperPrint jasperPrint = jasperReportBuilder.toJasperPrint();
                add(jasperPrint);
                pageNumber += jasperPrint.getPages().size();
                jasperReportBuilder.rebuild();
            } catch (final Exception e) {
                log.error(e.getMessage(), e);
            }
        }
    }

    /**
     * <p>add.</p>
     *
     * @param jasperPrint a {@link net.sf.jasperreports.engine.JasperPrint} object.
     */
    protected abstract void add(JasperPrint jasperPrint);

    /** {@inheritDoc} */
    @Override
    public void setContinuousPageNumbering(boolean continuousPageNumbering) {
        this.continuousPageNumbering = continuousPageNumbering;
    }

}
