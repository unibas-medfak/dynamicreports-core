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
package ch.unibas.medizin.dynamicreports.test.jasper;

import org.junit.jupiter.api.Assertions;

import ch.unibas.medizin.dynamicreports.report.builder.column.ColumnBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.group.GroupBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.subtotal.SubtotalBuilder;
import net.sf.jasperreports.engine.JRPrintElement;

/**
 * @author Ricardo Mariaca
 */
public abstract class AbstractJasperPositionTest extends AbstractJasperTest {

    protected void elementPositionTest(String name, int index, int x, int y, int width, int height) {
        final JRPrintElement element = getElementAt(name, index);
        Assertions.assertEquals(width, element.getWidth(), "width");
        Assertions.assertEquals(height, element.getHeight(), "height");
        Assertions.assertEquals(x, element.getX(), "x");
        Assertions.assertEquals(y, element.getY(), "y");
    }

    // column detail
    protected void columnDetailPositionTest(ColumnBuilder<?, ?> column, int index, int x, int y, int width, int height) {
        elementPositionTest(JasperTestUtils.getColumnDetailName(column), index, x, y, width, height);
    }

    // column title
    protected void columnTitlePositionTest(ColumnBuilder<?, ?> column, int index, int x, int y, int width, int height) {
        elementPositionTest(JasperTestUtils.getColumnTitleName(column), index, x, y, width, height);
    }

    // subtotal label
    protected void subtotalLabelPositionTest(SubtotalBuilder<?, ?> subtotal, int index, int x, int y, int width, int height) {
        elementPositionTest(JasperTestUtils.getSubtotalLabelName(subtotal, 1), index, x, y, width, height);
    }

    protected void subtotalLabelIndexPositionTest(SubtotalBuilder<?, ?> subtotal, int subtotalIndex, int index, int x, int y, int width, int height) {
        elementPositionTest(JasperTestUtils.getSubtotalLabelName(subtotal, subtotalIndex), index, x, y, width, height);
    }

    // subtotal
    protected void subtotalPositionTest(SubtotalBuilder<?, ?> subtotal, int index, int x, int y, int width, int height) {
        elementPositionTest(JasperTestUtils.getSubtotalName(subtotal, 1), index, x, y, width, height);
    }

    protected void subtotalIndexPositionTest(SubtotalBuilder<?, ?> subtotal, int subtotalIndex, int index, int x, int y, int width, int height) {
        elementPositionTest(JasperTestUtils.getSubtotalName(subtotal, subtotalIndex), index, x, y, width, height);
    }

    // group header title
    protected void groupHeaderTitlePositionTest(GroupBuilder<?> group, int index, int x, int y, int width, int height) {
        elementPositionTest(JasperTestUtils.getHeaderTitleGroupName(group), index, x, y, width, height);
    }

    // group header
    protected void groupHeaderPositionTest(GroupBuilder<?> group, int index, int x, int y, int width, int height) {
        elementPositionTest(JasperTestUtils.getHeaderGroupName(group), index, x, y, width, height);
    }
}
