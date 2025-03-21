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
package net.sf.dynamicreports.test.jasper.tableofcontents;

import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;
import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.grp;
import static net.sf.dynamicreports.report.builder.DynamicReports.tableOfContentsHeading;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.group.ColumnGroupBuilder;
import net.sf.dynamicreports.report.builder.tableofcontents.TableOfContentsHeadingBuilder;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.test.jasper.AbstractJasperValueTest;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRPrintText;
import net.sf.jasperreports.engine.util.JRStyledTextUtil;

/**
 * @author Ricardo Mariaca
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TableOfContents1Test extends AbstractJasperValueTest implements Serializable {
    private static final long serialVersionUID = 1L;

    private TextColumnBuilder<String> column1;
    private TextColumnBuilder<String> column2;
    private TextColumnBuilder<String> column3;
    private ColumnGroupBuilder group1;
    private ColumnGroupBuilder group2;
    private LabelExpression labelExpression;

    @Override
    protected void configureReport(JasperReportBuilder rb) {
        labelExpression = new LabelExpression();
        final TableOfContentsHeadingBuilder tocHeading = tableOfContentsHeading().setLabel(labelExpression);

        rb.tableOfContents()
          .columns(column1 = col.column("Column1", "field1", type.stringType()), column2 = col.column("Column2", "field2", type.stringType()),
                   column3 = col.column("Column3", "field3", type.stringType()))
          .groupBy(group1 = grp.group(column1).footer(cmp.text("group footer").setTableOfContentsHeading(tocHeading)), group2 = grp.group(column2));
    }

    @Override
    @Test
    public void test() {
        super.test();

        numberOfPagesTest(3);

        elementValueTest("title.textField1", "Table of contents");

        elementCountTest("detail.textField1", 6);
        int index = 0;
        for (int i = 0; i < 3; i++) {
            String anchorName = group1.getGroup().getName() + "_" + i * 24;

            elementValueTest("detail.textField1", index, "value" + (i + 1));
            JRPrintText text = (JRPrintText) getElementAt("detail.textField1", index);
            Assertions.assertEquals( anchorName, text.getHyperlinkAnchor(), "text anchor");

            JRPrintText dots = (JRPrintText) getElementAt("detail.textField2", index);
            String value = JRStyledTextUtil.getInstance(DefaultJasperReportsContext.getInstance()).getTruncatedText(dots);
            Assertions.assertTrue(StringUtils.containsOnly(value, "."), "dots");
            Assertions.assertEquals( anchorName, dots.getHyperlinkAnchor(), "dots anchor");

            JRPrintText pageIndex = (JRPrintText) getElementAt("detail.textField3", index);
            Assertions.assertEquals( anchorName, pageIndex.getHyperlinkAnchor(), "pageIndex anchor");
            index++;

            anchorName = labelExpression.getName() + "_" + (i + 1) * 24;

            elementValueTest("detail.textField1", index, "group footer" + (i + 1));
            text = (JRPrintText) getElementAt("detail.textField1", index);
            Assertions.assertEquals(anchorName, text.getHyperlinkAnchor(), "text anchor");

            dots = (JRPrintText) getElementAt("detail.textField2", index);
            value = JRStyledTextUtil.getInstance(DefaultJasperReportsContext.getInstance()).getTruncatedText(dots);
            Assertions.assertTrue(StringUtils.containsOnly(value, "."), "dots");
            Assertions.assertEquals( anchorName, dots.getHyperlinkAnchor(), "dots anchor");

            pageIndex = (JRPrintText) getElementAt("detail.textField3", index);
            Assertions.assertEquals( anchorName, pageIndex.getHyperlinkAnchor(), "pageIndex anchor");
            index++;
        }
        elementValueTest("detail.textField3", "1", "1", "1", "2", "2", "2");

        elementCountTest("detail.textField4", 9);
        for (int i = 0; i < 9; i++) {
            final String anchorName = group2.getGroup().getName() + "_" + i * 8;

            final JRPrintText text = (JRPrintText) getElementAt("detail.textField4", i);
            Assertions.assertEquals( anchorName, text.getHyperlinkAnchor(), "text anchor");

            final JRPrintText dots = (JRPrintText) getElementAt("detail.textField5", i);
            final String value = JRStyledTextUtil.getInstance(DefaultJasperReportsContext.getInstance()).getTruncatedText(dots);
            Assertions.assertTrue(StringUtils.containsOnly(value, "."), "dots");
            Assertions.assertEquals( anchorName, dots.getHyperlinkAnchor(), "dots anchor");

            final JRPrintText pageIndex = (JRPrintText) getElementAt("detail.textField6", i);
            Assertions.assertEquals( anchorName, pageIndex.getHyperlinkAnchor(), "pageIndex anchor");
        }
        elementValueTest("detail.textField4", "value1", "value2", "value3", "value1", "value2", "value3", "value1", "value2", "value3");
        elementValueTest("detail.textField6", "1", "1", "1", "1", "1", "1", "2", "2", "2");

        String name1 = "groupHeaderTitleAndValue.group_" + group1.getGroup().getName() + ".tocReference1";
        String name2 = "groupHeaderTitleAndValue.group_" + group1.getGroup().getName() + "1";
        elementCountTest(name1, 3);
        for (int i = 0; i < 3; i++) {
            final String anchorName = group1.getGroup().getName() + "_" + i * 24;

            elementValueTest(name1, i, "");
            JRPrintText reference = (JRPrintText) getElementAt(name1, i);
            Assertions.assertEquals( anchorName, reference.getAnchorName(), "reference anchorName " + name1);

            groupHeaderValueTest(group1, i, "value" + (i + 1));
            reference = (JRPrintText) getElementAt(name2, i);
            Assertions.assertEquals( anchorName, reference.getAnchorName(), "reference anchorName " + name2);
        }

        name1 = labelExpression.getName() + ".tocReference";
        name2 = "groupFooter.textField1";
        elementCountTest(name1, 3);
        for (int i = 0; i < 3; i++) {
            final String anchorName = labelExpression.getName() + "_" + (i + 1) * 24;

            elementValueTest(name1, i, "");
            JRPrintText reference = (JRPrintText) getElementAt(name1, i);
            Assertions.assertEquals( anchorName, reference.getAnchorName(), "reference anchorName " + name1);

            groupHeaderValueTest(group1, i, "value" + (i + 1));
            reference = (JRPrintText) getElementAt(name2, i);
            Assertions.assertEquals( anchorName, reference.getAnchorName(), "reference anchorName " + name2);
        }

        name1 = "groupHeaderTitleAndValue.group_" + group2.getGroup().getName() + ".tocReference1";
        name2 = "groupHeaderTitleAndValue.group_" + group2.getGroup().getName() + "1";
        elementCountTest(name1, 9);
        for (int i = 0; i < 9; i++) {
            final String anchorName = group2.getGroup().getName() + "_" + i * 8;

            elementValueTest(name1, i, "");
            JRPrintText reference = (JRPrintText) getElementAt(name1, i);
            Assertions.assertEquals( anchorName, reference.getAnchorName(), "reference anchorName " + name1);

            reference = (JRPrintText) getElementAt(name2, i);
            Assertions.assertEquals( anchorName, reference.getAnchorName(), "reference anchorName " + name2);
        }
        groupHeaderValueTest(group2, "value1", "value2", "value3", "value1", "value2", "value3", "value1", "value2", "value3");

        columnTitleCountTest(column3, 2);
        columnTitleValueTest(column3, "Column3", "Column3");
        columnDetailCountTest(column3, 72);
        columnDetailValueTest(column3, 71, "text");
    }

    @Override
    protected JRDataSource createDataSource() {
        final String[] values = new String[] {"value1", "value2", "value3"};
        final DRDataSource dataSource = new DRDataSource("field1", "field2", "field3");
        for (final String field1 : values) {
            for (final String field2 : values) {
                for (int i = 0; i < 8; i++) {
                    dataSource.add(field1, field2, "text");
                }
            }
        }
        return dataSource;
    }

    private class LabelExpression extends AbstractSimpleExpression<String> {
        private static final long serialVersionUID = 1L;

        private int index = 1;

        @Override
        public String evaluate(ReportParameters reportParameters) {
            return "group footer" + index++;
        }

    }
}
