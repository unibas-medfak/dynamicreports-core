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
package ch.unibas.medizin.dynamicreports.report.defaults;

import ch.unibas.medizin.dynamicreports.report.defaults.xml.XmlDynamicReports;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.transform.stream.StreamSource;
import java.io.InputStream;

/**
 * <p>Defaults class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class Defaults {
    private static final Logger log = LogManager.getLogger();

    private static final Default defaults;

    static {
        defaults = DefaultBinder.bind(load());
    }

    private static XmlDynamicReports load() {
        String resource = "dynamicreports-defaults.xml";
        InputStream is = null;

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if (classLoader != null) {
            is = classLoader.getResourceAsStream(resource);
        }
        if (is == null) {
            classLoader = Defaults.class.getClassLoader();
            if (classLoader != null) {
                is = classLoader.getResourceAsStream(resource);
            }
            if (is == null) {
                is = Defaults.class.getResourceAsStream("/" + resource);
            }
        }
        if (is == null) {
            return null;
        }

        try {
            Unmarshaller unmarshaller = JAXBContext.newInstance(XmlDynamicReports.class).createUnmarshaller();
            JAXBElement<XmlDynamicReports> root = unmarshaller.unmarshal(new StreamSource(is), XmlDynamicReports.class);
            return root.getValue();
        } catch (JAXBException e) {
            log.error("Could not load dynamic reports defaults", e);
            return null;
        }
    }

    /**
     * <p>Getter for the field <code>defaults</code>.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.defaults.Default} object.
     */
    public static Default getDefaults() {
        return defaults;
    }
}
