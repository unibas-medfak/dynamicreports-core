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
package ch.unibas.medizin.dynamicreports.test.design.report;


import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.cmp;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.col;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.grp;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.variable;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ch.unibas.medizin.dynamicreports.design.base.DRDesignReport;
import ch.unibas.medizin.dynamicreports.report.builder.ReportBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.column.TextColumnBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.group.ColumnGroupBuilder;
import ch.unibas.medizin.dynamicreports.report.constant.Calculation;
import ch.unibas.medizin.dynamicreports.report.constant.Evaluation;
import ch.unibas.medizin.dynamicreports.report.exception.DRException;
import ch.unibas.medizin.dynamicreports.test.design.DesignReportBuilder;

/**
 * @author Ricardo Mariaca
 */
public class ExceptionTest {

    @Test
    public void testComponentFixedWidth() {
        final ReportBuilder<?> rb1 = new DesignReportBuilder();
        rb1.title(cmp.text("").setFixedWidth(600));
        try {
            new DRDesignReport(rb1.getReport());
            Assertions.fail("component fixed width exception");
        } catch (final DRException ignored) {
        }

        final ReportBuilder<?> rb2 = new DesignReportBuilder();
        rb2.title(cmp.horizontalList(cmp.text("").setFixedWidth(600)));
        try {
            new DRDesignReport(rb2.getReport());
            Assertions.fail("component fixed width exception");
        } catch (final DRException ignored) {
        }

        final ReportBuilder<?> rb3 = new DesignReportBuilder();
        rb3.title(cmp.horizontalFlowList(cmp.text("").setFixedWidth(600)));
        try {
            new DRDesignReport(rb3.getReport());
            Assertions.fail("component fixed width exception");
        } catch (final DRException ignored) {
        }
    }

    @Test
    public void testComponentMinimumWidth() {
        final ReportBuilder<?> rb1 = new DesignReportBuilder();
        rb1.title(cmp.text("").setMinWidth(600));
        try {
            new DRDesignReport(rb1.getReport());
            Assertions.fail("component minimum width exception");
        } catch (final DRException ignored) {
        }

        final ReportBuilder<?> rb2 = new DesignReportBuilder();
        rb2.title(cmp.horizontalList(cmp.text("").setMinWidth(600)));
        try {
            new DRDesignReport(rb2.getReport());
            Assertions.fail("component fixed width exception");
        } catch (final DRException ignored) {
        }

        final ReportBuilder<?> rb3 = new DesignReportBuilder();
        rb3.title(cmp.horizontalFlowList(cmp.text("").setMinWidth(600)));
        try {
            new DRDesignReport(rb3.getReport());
            Assertions.fail("component fixed width exception");
        } catch (final DRException ignored) {
        }
    }

    @Test
    public void testVariable() {
        final TextColumnBuilder<Object> column = col.column("fieldName", Object.class);
        final ColumnGroupBuilder group = grp.group(column);
        final ReportBuilder<?> rb1 =
            new DesignReportBuilder().columns(column).groupBy(group).variables(variable("fieldName", Object.class, Calculation.SUM).setResetGroup(group).setResetType(Evaluation.REPORT));
        try {
            new DRDesignReport(rb1.getReport());
            Assertions.fail("variable exception");
        } catch (final DRException ignored) {
        }

        final ReportBuilder<?> rb2 = new DesignReportBuilder().columns(column).groupBy(group).variables(variable("fieldName", Object.class, Calculation.SUM).setResetType(Evaluation.GROUP));
        try {
            new DRDesignReport(rb2.getReport());
            Assertions.fail("variable exception");
        } catch (final DRException ignored) {
        }
    }
}
