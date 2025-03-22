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
package ch.unibas.medizin.dynamicreports.jasper.definition.export;

import ch.unibas.medizin.dynamicreports.jasper.constant.PdfPermission;
import ch.unibas.medizin.dynamicreports.jasper.constant.PdfVersion;
import ch.unibas.medizin.dynamicreports.report.constant.PdfPrintScaling;
import ch.unibas.medizin.dynamicreports.report.constant.PdfaConformance;

import java.util.List;

/**
 * <p>JasperIPdfExporter interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface JasperIPdfExporter extends JasperIExporter {

    /**
     * <p>getCreatingBatchModeBookmarks.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean getCreatingBatchModeBookmarks();

    /**
     * <p>getCompressed.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean getCompressed();

    /**
     * <p>getEncrypted.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean getEncrypted();

    /**
     * <p>getBitKey128.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean getBitKey128();

    /**
     * <p>getUserPassword.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    String getUserPassword();

    /**
     * <p>getOwnerPassword.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    String getOwnerPassword();

    /**
     * <p>getPermissions.</p>
     *
     * @return a {@link java.util.List} object.
     */
    List<PdfPermission> getPermissions();

    /**
     * <p>getPdfVersion.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.jasper.constant.PdfVersion} object.
     */
    PdfVersion getPdfVersion();

    /**
     * <p>getMetadataTitle.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    String getMetadataTitle();

    /**
     * <p>getMetadataAuthor.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    String getMetadataAuthor();

    /**
     * <p>getMetadataSubject.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    String getMetadataSubject();

    /**
     * <p>getMetadataKeyWords.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    String getMetadataKeyWords();

    /**
     * <p>getMetadataCreator.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    String getMetadataCreator();

    /**
     * <p>getForceSvgShapes.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean getForceSvgShapes();

    /**
     * <p>getPdfJavaScript.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    String getPdfJavaScript();

    /**
     * <p>getTagged.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean getTagged();

    /**
     * <p>getTagLanguage.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    String getTagLanguage();

    /**
     * <p>getCollapseMissingBookmarkLevels.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean getCollapseMissingBookmarkLevels();

    /**
     * <p>getSizePageToContent.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean getSizePageToContent();

    /**
     * <p>getIgnoreHyperLink.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean getIgnoreHyperLink();

    /**
     * <p>getForceLineBreakPolicy.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean getForceLineBreakPolicy();

    /**
     * <p>getPrintScaling.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.PdfPrintScaling} object.
     */
    PdfPrintScaling getPrintScaling();

    /**
     * <p>getPdfaConformance.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.PdfaConformance} object.
     */
    PdfaConformance getPdfaConformance();

    /**
     * <p>getIccProfilePath.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    String getIccProfilePath();

    /**
     * <p>getAllowedPermissionsHint.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    String getAllowedPermissionsHint();

    /**
     * <p>getDeniedPermissionsHint.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    String getDeniedPermissionsHint();

    /**
     * <p>getDisplayMetadataTitle.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean getDisplayMetadataTitle();
}
