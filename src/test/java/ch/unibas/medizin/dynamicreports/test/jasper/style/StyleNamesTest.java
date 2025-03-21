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
package ch.unibas.medizin.dynamicreports.test.jasper.style;

import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.cmp;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.report;
import static ch.unibas.medizin.dynamicreports.report.builder.DynamicReports.stl;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ch.unibas.medizin.dynamicreports.jasper.builder.JasperReportBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.style.StyleBuilder;
import ch.unibas.medizin.dynamicreports.report.builder.style.TemplateStyleBuilder;
import ch.unibas.medizin.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.design.JasperDesign;

/**
 * @author Ramunas Belkauskas
 */
public class StyleNamesTest {

    private static final String jrtxStr =

            """
                    <jasperTemplate>
                      <style name="MyBoldStyle2" bold="true"/>
                    </jasperTemplate>""";

    private static void assertContainsStyleWithName(final String name, final Iterable<JRStyle> styles) {
        Assertions.assertNotNull(name);
        Assertions.assertNotNull(styles);
        JRStyle found = null;
        for (final JRStyle style : styles) {
            if (name.equals(style.getName())) {
                found = style;
                break;
            }
        }
        Assertions.assertNotNull(found, String.format("Style with name \"%s\" not found", name));
    }

    @Test
    public void testStyleFromTemplateName() throws DRException {
        final ByteArrayInputStream is = new ByteArrayInputStream(jrtxStr.getBytes(StandardCharsets.UTF_8));
        // name defined in style template:
        final TemplateStyleBuilder templateStyle = stl.templateStyle("MyBoldStyle2");
        final JasperReportBuilder builder = report().addTemplateStyle(stl.loadStyles(is)).title(cmp.text("Some title").setStyle(templateStyle));
        final JasperDesign design = builder.toJasperDesign();
        assertContainsStyleWithName("MyBoldStyle2", design.getStylesList());
    }

    @Test
    public void testStyleName() throws DRException {
        final String styleName = "MyBoldStyle";
        // name explicitly set:
        final StyleBuilder tucne = stl.style().setName(styleName).setFont(stl.font().bold());
        final JasperReportBuilder builder = report().title(cmp.text("Some title").setStyle(tucne));
        final JasperDesign design = builder.toJasperDesign();
        assertContainsStyleWithName(styleName, design.getStylesList());
    }
}
