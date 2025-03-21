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
import ch.unibas.medizin.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JasperPrint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>JasperReportBuilderHandler class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class JasperReportBuilderHandler implements JasperReportHandler {
    private final List<JasperReportBuilder> jasperReportBuilders;
    private boolean continuousPageNumbering;

    /**
     * <p>Constructor for JasperReportBuilderHandler.</p>
     */
    public JasperReportBuilderHandler() {
        jasperReportBuilders = new ArrayList<>();
        continuousPageNumbering = false;
    }

    /** {@inheritDoc} */
    @Override
    public void concatenate(JasperReportBuilder... jasperReportBuilders) {
        this.jasperReportBuilders.addAll(Arrays.asList(jasperReportBuilders));
    }

    /** {@inheritDoc} */
    @Override
    public void setContinuousPageNumbering(boolean continuousPageNumbering) {
        this.continuousPageNumbering = continuousPageNumbering;
    }

    /** {@inheritDoc} */
    @Override
    public List<JasperPrint> getPrintList() throws DRException {
        List<JasperPrint> printList = new ArrayList<>();
        int pageNumber = 1;
        for (JasperReportBuilder jasperReportBuilder : jasperReportBuilders) {
            if (continuousPageNumbering) {
                jasperReportBuilder.setStartPageNumber(pageNumber);
            } else {
                jasperReportBuilder.setStartPageNumber(null);
            }
            JasperPrint jasperPrint = jasperReportBuilder.toJasperPrint();
            printList.add(jasperPrint);
            pageNumber += jasperPrint.getPages().size();
        }
        return printList;
    }

}
