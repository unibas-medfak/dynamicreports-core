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
package net.sf.dynamicreports.report.builder.barcode;

import net.sf.dynamicreports.report.base.barcode.DRUspsIntelligentMailBarcode;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;

/**
 * <p>UspsIntelligentMailBarcodeBuilder class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class UspsIntelligentMailBarcodeBuilder extends AbstractChecksumBarcodeBuilder<UspsIntelligentMailBarcodeBuilder, DRUspsIntelligentMailBarcode> {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for UspsIntelligentMailBarcodeBuilder.</p>
     *
     * @param code a {@link java.lang.String} object.
     */
    protected UspsIntelligentMailBarcodeBuilder(String code) {
        super(code, new DRUspsIntelligentMailBarcode());
    }

    /**
     * <p>Constructor for UspsIntelligentMailBarcodeBuilder.</p>
     *
     * @param codeExpression a {@link net.sf.dynamicreports.report.definition.expression.DRIExpression} object.
     */
    protected UspsIntelligentMailBarcodeBuilder(DRIExpression<String> codeExpression) {
        super(codeExpression, new DRUspsIntelligentMailBarcode());
    }

    /**
     * <p>setAscenderHeight.</p>
     *
     * @param ascenderHeight a {@link java.lang.Double} object.
     * @return a {@link net.sf.dynamicreports.report.builder.barcode.UspsIntelligentMailBarcodeBuilder} object.
     */
    public UspsIntelligentMailBarcodeBuilder setAscenderHeight(Double ascenderHeight) {
        getObject().setAscenderHeight(ascenderHeight);
        return this;
    }

    /**
     * <p>setIntercharGapWidth.</p>
     *
     * @param intercharGapWidth a {@link java.lang.Double} object.
     * @return a {@link net.sf.dynamicreports.report.builder.barcode.UspsIntelligentMailBarcodeBuilder} object.
     */
    public UspsIntelligentMailBarcodeBuilder setIntercharGapWidth(Double intercharGapWidth) {
        getObject().setIntercharGapWidth(intercharGapWidth);
        return this;
    }

    /**
     * <p>setTrackHeight.</p>
     *
     * @param trackHeight a {@link java.lang.Double} object.
     * @return a {@link net.sf.dynamicreports.report.builder.barcode.UspsIntelligentMailBarcodeBuilder} object.
     */
    public UspsIntelligentMailBarcodeBuilder setTrackHeight(Double trackHeight) {
        getObject().setTrackHeight(trackHeight);
        return this;
    }
}
