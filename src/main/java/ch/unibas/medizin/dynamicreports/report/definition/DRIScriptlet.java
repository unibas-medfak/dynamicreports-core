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
package ch.unibas.medizin.dynamicreports.report.definition;

/**
 * <p>DRIScriptlet interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface DRIScriptlet {

    /**
     * <p>getName.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    String getName();

    /**
     * Called before the report is initialized.
     *
     * @param reportParameters a {@link ch.unibas.medizin.dynamicreports.report.definition.ReportParameters} object.
     */
    void beforeReportInit(ReportParameters reportParameters);

    /**
     * Called after the report is initialized.
     *
     * @param reportParameters a {@link ch.unibas.medizin.dynamicreports.report.definition.ReportParameters} object.
     */
    void afterReportInit(ReportParameters reportParameters);

    /**
     * Called before each page is initialized.
     *
     * @param reportParameters a {@link ch.unibas.medizin.dynamicreports.report.definition.ReportParameters} object.
     */
    void beforePageInit(ReportParameters reportParameters);

    /**
     * Called after each page is initialized.
     *
     * @param reportParameters a {@link ch.unibas.medizin.dynamicreports.report.definition.ReportParameters} object.
     */
    void afterPageInit(ReportParameters reportParameters);

    /**
     * Called before each column is initialized.
     *
     * @param reportParameters a {@link ch.unibas.medizin.dynamicreports.report.definition.ReportParameters} object.
     */
    void beforeColumnInit(ReportParameters reportParameters);

    /**
     * Called after each column is initialized.
     *
     * @param reportParameters a {@link ch.unibas.medizin.dynamicreports.report.definition.ReportParameters} object.
     */
    void afterColumnInit(ReportParameters reportParameters);

    /**
     * Called before a group is initialized.
     *
     * @param groupName        the group name
     * @param reportParameters a {@link ch.unibas.medizin.dynamicreports.report.definition.ReportParameters} object.
     */
    void beforeGroupInit(String groupName, ReportParameters reportParameters);

    /**
     * Called after a group is initialized.
     *
     * @param groupName        the group name
     * @param reportParameters a {@link ch.unibas.medizin.dynamicreports.report.definition.ReportParameters} object.
     */
    void afterGroupInit(String groupName, ReportParameters reportParameters);

    /**
     * Called before evaluating each detail.
     *
     * @param reportParameters a {@link ch.unibas.medizin.dynamicreports.report.definition.ReportParameters} object.
     */
    void beforeDetailEval(ReportParameters reportParameters);

    /**
     * Called after evaluating each detail.
     *
     * @param reportParameters a {@link ch.unibas.medizin.dynamicreports.report.definition.ReportParameters} object.
     */
    void afterDetailEval(ReportParameters reportParameters);
}
