/*
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2018 Ricardo Mariaca and the Dynamic Reports Contributors
 *
 * This file is part of DynamicReports.
 *
 * DynamicReports is free software: you can redistribute it and/or modify it under the terms of the
 * GNU Lesser General Public License as published by the Free Software Foundation, either version 3
 * of the License, or (at your option) any later version.
 *
 * DynamicReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with
 * DynamicReports. If not, see <http://www.gnu.org/licenses/>.
 */

package net.sf.dynamicreports.test.base;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.InputStream;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import net.sf.dynamicreports.report.base.datatype.DRDataType;
import net.sf.dynamicreports.report.base.style.DRFont;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.defaults.Default;
import net.sf.dynamicreports.report.defaults.DefaultBinder;
import net.sf.dynamicreports.report.defaults.xml.XmlDynamicReports;

/**
 * Defaults tests.
 *
 * @author Ricardo Mariaca
 */
public class DefaultsTest {

  private Default load() {
    final InputStream is = DefaultsTest.class.getResourceAsStream("dynamicreports-defaults.xml");
    try {
      final Unmarshaller unmarshaller = JAXBContext.newInstance(XmlDynamicReports.class).createUnmarshaller();
      final JAXBElement<XmlDynamicReports> root = unmarshaller.unmarshal(new StreamSource(is), XmlDynamicReports.class);
      return DefaultBinder.bind(root.getValue());
    } catch (final JAXBException e) {
      e.printStackTrace();
      Assertions.fail(e.getMessage());
    }
    return null;
  }

  @Test
  public void test() {
    DefaultBinder.bind(null);
    final Default defaults = load();

    final DRFont font = defaults.getFont();
    assertEquals("Arimo", font.getFontName(), "Font name");
    assertEquals(Integer.valueOf(15), font.getFontSize(), "Font size");
    assertEquals("Arimo", font.getPdfFontName(), "Pdf font name");
    assertEquals("Identity-H", font.getPdfEncoding(), "Pdf encoding");
    assertTrue(font.getPdfEmbedded(), "Pdf embedded");

    testDataType("BigDecimal", defaults.getBigDecimalType(), "#,###.00#", HorizontalTextAlignment.LEFT);
    testDataType("BigInteger", defaults.getBigIntegerType(), "#,###", HorizontalTextAlignment.LEFT);
    testDataType("Byte", defaults.getByteType(), "#,###", HorizontalTextAlignment.LEFT);
    testDataType("Double", defaults.getDoubleType(), "#,###.#", HorizontalTextAlignment.LEFT);
    testDataType("Float", defaults.getFloatType(), "#,###.#", HorizontalTextAlignment.LEFT);
    testDataType("Integer", defaults.getIntegerType(), "#,###", HorizontalTextAlignment.LEFT);
    testDataType("Long", defaults.getLongType(), "#,###", HorizontalTextAlignment.LEFT);
    testDataType("Short", defaults.getShortType(), "#,###", HorizontalTextAlignment.LEFT);
    testDataType("Date", defaults.getDateType(), "MM.dd.yyyy", HorizontalTextAlignment.LEFT);
    testDataType("DateYearToMonth", defaults.getDateYearToMonthType(), "MM.yyyy", HorizontalTextAlignment.LEFT);
    testDataType("DateYearToHour", defaults.getDateYearToHourType(), "MM.dd.yyyy h a", HorizontalTextAlignment.LEFT);
    testDataType("DateYearToMinute", defaults.getDateYearToMinuteType(), "MM.dd.yyyy h:mm a",
        HorizontalTextAlignment.LEFT);
    testDataType("DateYearToSecond", defaults.getDateYearToSecondType(), "MM.dd.yyyy h:mm:ss a",
        HorizontalTextAlignment.LEFT);
    testDataType("DateYearToFraction", defaults.getDateYearToFractionType(), "MM.dd.yyyy h:mm:ss,SSS a",
        HorizontalTextAlignment.LEFT);
    testDataType("DateYear", defaults.getDateYearType(), "yy", HorizontalTextAlignment.LEFT);
    testDataType("DateMonth", defaults.getDateMonthType(), "MM", HorizontalTextAlignment.LEFT);
    testDataType("DateDay", defaults.getDateDayType(), "d", HorizontalTextAlignment.LEFT);
    testDataType("TimeHourToMinute", defaults.getTimeHourToMinuteType(), "hh:mm a", HorizontalTextAlignment.LEFT);
    testDataType("TimeHourToSecond", defaults.getTimeHourToSecondType(), "hh:mm:ss a", HorizontalTextAlignment.LEFT);
    testDataType("TimeHourToFraction", defaults.getTimeHourToFractionType(), "hh:mm:ss,SSS a",
        HorizontalTextAlignment.LEFT);
    testDataType("Percentage", defaults.getPercentageType(), "#,###.00%", HorizontalTextAlignment.LEFT);
    testDataType("Boolean", defaults.getBooleanType(), null, HorizontalTextAlignment.RIGHT);
    testDataType("Character", defaults.getCharacterType(), null, HorizontalTextAlignment.RIGHT);
    testDataType("String", defaults.getStringType(), null, HorizontalTextAlignment.RIGHT);
  }

  private void testDataType(String name, DRDataType<?, ?> dataType, String pattern,
      HorizontalTextAlignment horizontalTextAlignment) {
    assertEquals(pattern, dataType.getPattern(), name + " pattern");
    assertEquals(horizontalTextAlignment, dataType.getHorizontalTextAlignment(), name + " horizontal alignment");
  }
}
