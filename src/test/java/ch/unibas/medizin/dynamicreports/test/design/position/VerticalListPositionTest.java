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


import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.cmp;

import org.junit.jupiter.api.Assertions;

import ch.unibas.medizin.dynamicreports.design.base.DRDesignBand;
import ch.unibas.medizin.dynamicreports.design.base.component.DRDesignComponent;
import ch.unibas.medizin.dynamicreports.design.base.component.DRDesignList;
import ch.unibas.medizin.dynamicreports.design.base.component.DRDesignTextField;
import ch.unibas.medizin.dynamicreports.report.builder.ReportBuilder;
import ch.unibas.medizin.dynamicreports.report.constant.ListType;
import ch.unibas.medizin.dynamicreports.test.design.AbstractBandTest;

/**
 * @author Ricardo Mariaca
 */
public class VerticalListPositionTest extends AbstractBandTest {

    @Override
    public void configureReport(ReportBuilder<?> rb) {
        rb.title(cmp.horizontalList(cmp.verticalList(cmp.vListCell(cmp.text("")), cmp.vListCell(cmp.text("")).heightFixed(), cmp.vListCell(cmp.text("")).widthFixedOnLeft().heightFixed(),
                                                     cmp.vListCell(cmp.text("")).widthFixedOnLeft(), cmp.vListCell(cmp.text("")).widthFixedOnCenter().heightFixed(),
                                                     cmp.vListCell(cmp.text("")).widthFixedOnCenter(), cmp.vListCell(cmp.text("")).widthFixedOnRight().heightFixed(),
                                                     cmp.vListCell(cmp.text("")).widthFixedOnRight()), cmp.filler().setHeight(200)));
    }

    @Override
    protected void titleBandTest(DRDesignBand band) {
        final DRDesignComponent component = band.getBandComponent();
        Assertions.assertInstanceOf(DRDesignList.class, component);
        DRDesignList list = (DRDesignList) component;
        Assertions.assertEquals(ListType.HORIZONTAL, list.getType());
        Assertions.assertEquals(1, list.getComponents().size());
        componentPositionTest(list, 0, 0, 575, 200);
        Assertions.assertInstanceOf(DRDesignList.class, list.getComponents().getFirst());

        list = (DRDesignList) list.getComponents().getFirst();
        Assertions.assertEquals(ListType.VERTICAL, list.getType());
        Assertions.assertEquals(8, list.getComponents().size());
        componentPositionTest(list, 0, 0, 575, 200);
        for (int i = 0; i < 8; i++) {
            Assertions.assertInstanceOf(DRDesignTextField.class, list.getComponents().get(i));
        }

        componentPositionTest(list.getComponents().get(0), 0, 0, 575, 34);
        componentPositionTest(list.getComponents().get(1), 0, 34, 575, 16);
        componentPositionTest(list.getComponents().get(2), 0, 50, 100, 16);
        componentPositionTest(list.getComponents().get(3), 0, 66, 100, 34);
        componentPositionTest(list.getComponents().get(4), 237, 100, 100, 16);
        componentPositionTest(list.getComponents().get(5), 237, 116, 100, 34);
        componentPositionTest(list.getComponents().get(6), 475, 150, 100, 16);
        componentPositionTest(list.getComponents().get(7), 475, 166, 100, 34);
    }
}
