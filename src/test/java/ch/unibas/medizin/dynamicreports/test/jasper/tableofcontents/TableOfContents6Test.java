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
package ch.unibas.medizin.dynamicreports.test.jasper.tableofcontents;

import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.cmp;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.col;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.field;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.grp;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.report;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.tableOfContentsHeading;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.type;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder;
import ch.unibas.medizin.dynamicreports.report.base.component.DRTextField;
import ch.unibas.medizin.dynamicreports.report.base.expression.AbstractSimpleExpression;
import ch.unibas.medizin.dynamicreports.report.builder.column.TextColumnBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.group.CustomGroupBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.tableofcontents.TableOfContentsCustomizer;
import ch.unibas.medizin.dynamicreports.report.builder.tableofcontents.TableOfContentsHeadingBuilder;
import ch.unibas.medizin.dynamicreports.report.datasource.DRDataSource;
import ch.unibas.medizin.dynamicreports.report.definition.ReportParameters;
import ch.unibas.medizin.dynamicreports.test.jasper.AbstractJasperValueTest;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.Serial;

/**
 * @author Ricardo Mariaca
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TableOfContents6Test extends AbstractJasperValueTest {
    private TextColumnBuilder<String> column1;
    private TextColumnBuilder<String> column4;
    private CustomGroupBuilder group1;
    private CustomGroupBuilder group2;

    @Override
    protected void configureReport(JasperReportBuilder rb) {
        rb.setTableOfContents(new CustomTableOfContentsCustomizer()).title(cmp.subreport(createSubreport1())).columns(column4 = col.column("Column4", "field4", type.stringType()));
    }

    private JasperReportBuilder createSubreport1() {
        column1 = col.column("Column1", "field1", type.stringType());

        final DRTextField<String> columnComponent = (DRTextField<String>) column1.getColumn().getComponent();
        final TableOfContentsHeadingBuilder tocHeading = tableOfContentsHeading();
        tocHeading.setCustomValue(new TocCustomValueExpression());
        columnComponent.setTableOfContentsHeading(tocHeading.getTableOfContentsHeading());

        final JasperReportBuilder report = report();
        report.columns(column1, col.column("Column2", "field2", type.stringType()), col.column("Column3", "field3", type.stringType())).setDataSource(createSubreportDataSource1());

        return report;
    }

    @Override
    protected JRDataSource createDataSource() {
        final DRDataSource dataSource = new DRDataSource("field4");
        dataSource.add("detail1");
        dataSource.add("detail2");
        return dataSource;
    }

    private JRDataSource createSubreportDataSource1() {
        final DRDataSource dataSource = new DRDataSource("field1", "field2", "field3");
        dataSource.add("text1", "text1_1", "text2_1");
        dataSource.add("text2", "text1_1", "text2_1");
        dataSource.add("text3", "text1_2", "text2_2");
        dataSource.add("text4", "text1_2", "text2_2");
        return dataSource;
    }

    @Override
    @Test
    public void test() {
        super.test();

        numberOfPagesTest(2);

        elementCountTest("detail.textField1", 8);
        elementValueTest("detail.textField1", "text1", "text2", "text3", "text4", "text1", "text2", "text3", "text4");

        columnDetailCountTest(column1, 4);
        columnDetailValueTest(column1, "text1", "text2", "text3", "text4");

        columnDetailCountTest(column4, 2);
        columnDetailValueTest(column4, "detail1", "detail2");

        groupHeaderCountTest(group1, 2);
        groupHeaderValueTest(group1, "text1_1", "text1_2");

        groupHeaderCountTest(group2, 2);
        groupHeaderValueTest(group2, "text2_1", "text2_2");
    }

    @Override
    protected boolean serializableTest() {
        return false;
    }

    private class CustomTableOfContentsCustomizer extends TableOfContentsCustomizer {
        @Serial
        private static final long serialVersionUID = 1L;

        @Override
        public void customize() {
            init();

            group1 = grp.group(field("customValue.field2", String.class));
            group2 = grp.group(field("customValue.field3", String.class));

            report.title(cmp.subreport(createSubreport2(group1)), cmp.subreport(createSubreport2(group2)));
        }

        private JasperReportBuilder createSubreport2(CustomGroupBuilder group) {
            final JasperReportBuilder subreport = report();
            subreport.fields(levelField, textField, referenceField, pageIndexField).groupBy(group).detail(detailComponent()).setDataSource(new JRBeanCollectionDataSource(headingList));

            return subreport;
        }
    }

    private static class TocCustomValueExpression extends AbstractSimpleExpression<TocCustomValue> {
        @Serial
        private static final long serialVersionUID = 1L;

        @Override
        public TocCustomValue evaluate(ReportParameters reportParameters) {
            final String field2 = reportParameters.getValue("field2");
            final String field3 = reportParameters.getValue("field3");
            return new TocCustomValue(field2, field3);
        }
    }

    public static class TocCustomValue {
        private String field2;
        private String field3;

        public TocCustomValue(String field2, String field3) {
            this.field2 = field2;
            this.field3 = field3;
        }

        public String getField2() {
            return field2;
        }

        public void setField2(String field2) {
            this.field2 = field2;
        }

        public String getField3() {
            return field3;
        }

        public void setField3(String field3) {
            this.field3 = field3;
        }

    }
}
