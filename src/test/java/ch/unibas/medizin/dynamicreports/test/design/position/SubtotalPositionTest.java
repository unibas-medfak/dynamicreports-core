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
package ch.unibas.medizin.dynamicreports.test.design.position;


import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.col;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.sbt;

import org.junit.jupiter.api.Assertions;

import ch.unibas.medizin.dynamicreports.design.base.DRDesignBand;
import ch.unibas.medizin.dynamicreports.design.base.component.DRDesignComponent;
import ch.unibas.medizin.dynamicreports.design.base.component.DRDesignList;
import ch.unibas.medizin.dynamicreports.design.base.component.DRDesignTextField;
import ch.unibas.medizin.dynamicreports.report.builder.ReportBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.column.TextColumnBuilder;
import ch.unibas.medizin.dynamicreports.report.constant.Calculation;
import ch.unibas.medizin.dynamicreports.report.constant.ListType;
import ch.unibas.medizin.dynamicreports.test.design.AbstractBandTest;

/**
 * @author Ricardo Mariaca
 */
public class SubtotalPositionTest extends AbstractBandTest {

    @Override
    public void configureReport(ReportBuilder<?> rb) {
        TextColumnBuilder<Integer> column3;

        rb.setShowColumnTitle(false)
          .columns(col.column("Column1", "field1", Integer.class), col.column("Column2", "field2", Integer.class), column3 = col.column("Column3", "field3", Integer.class))
          .subtotalsAtTitle(sbt.sum(column3))
          .subtotalsAtPageHeader(sbt.sum(column3))
          .subtotalsAtPageFooter(sbt.sum(column3))
          .subtotalsAtColumnHeader(sbt.sum(column3))
          .subtotalsAtColumnFooter(sbt.sum(column3))
          .subtotalsAtLastPageFooter(sbt.sum(column3))
          .subtotalsAtSummary(sbt.sum(column3), sbt.aggregate(column3, Calculation.AVERAGE));
    }

    @Override
    protected void titleBandTest(DRDesignBand band) {
        testBand(band);
    }

    @Override
    protected void pageHeaderBandTest(DRDesignBand band) {
        testBand(band);
    }

    @Override
    protected void pageFooterBandTest(DRDesignBand band) {
        testBand(band);
    }

    @Override
    protected void columnHeaderBandTest(DRDesignBand band) {
        testBand(band);
    }

    @Override
    protected void columnFooterBandTest(DRDesignBand band) {
        testBand(band);
    }

    @Override
    protected void detailBandTest(DRDesignBand band) {
        Assertions.assertNotNull(band);
    }

    @Override
    protected void lastPageFooterBandTest(DRDesignBand band) {
        testBand(band);
    }

    @Override
    protected void summaryBandTest(DRDesignBand band) {
        final DRDesignComponent component = band.getBandComponent();
        Assertions.assertInstanceOf(DRDesignList.class, component);
        DRDesignList list = (DRDesignList) component;
        Assertions.assertEquals(ListType.HORIZONTAL, list.getType());
        Assertions.assertEquals(1, list.getComponents().size());
        componentPositionTest(list, 0, 0, 575, 32);
        Assertions.assertInstanceOf(DRDesignList.class, list.getComponents().getFirst());

        list = (DRDesignList) list.getComponents().getFirst();
        Assertions.assertEquals(ListType.VERTICAL, list.getType());
        Assertions.assertEquals(2, list.getComponents().size());
        componentPositionTest(list, 383, 0, 192, 32);
        Assertions.assertInstanceOf(DRDesignTextField.class, list.getComponents().get(0));
        Assertions.assertInstanceOf(DRDesignTextField.class, list.getComponents().get(1));

        // column3
        componentPositionTest(list.getComponents().get(0), 383, 0, 192, 16);
        componentPositionTest(list.getComponents().get(1), 383, 16, 192, 16);
    }

    protected void testBand(DRDesignBand band) {
        final DRDesignComponent component = band.getBandComponent();
        Assertions.assertInstanceOf(DRDesignList.class, component);
        final DRDesignList list = (DRDesignList) component;
        Assertions.assertEquals(ListType.HORIZONTAL, list.getType());
        Assertions.assertEquals(1, list.getComponents().size());
        componentPositionTest(list, 0, 0, 575, 16);
        Assertions.assertInstanceOf(DRDesignTextField.class, list.getComponents().getFirst());

        // column3
        componentPositionTest(list.getComponents().getFirst(), 383, 0, 192, 16);
    }
}
