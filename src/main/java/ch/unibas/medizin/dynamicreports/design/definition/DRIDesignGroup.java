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
package ch.unibas.medizin.dynamicreports.design.definition;

import ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignExpression;
import ch.unibas.medizin.dynamicreports.report.constant.GroupFooterPosition;

import java.io.Serializable;
import java.util.List;

/**
 * <p>DRIDesignGroup interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface DRIDesignGroup extends Serializable {

    /**
     * <p>getName.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    String getName();

    /**
     * <p>getGroupExpression.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.design.definition.expression.DRIDesignExpression} object.
     */
    DRIDesignExpression getGroupExpression();

    /**
     * <p>getHeaderBands.</p>
     *
     * @return a {@link java.util.List} object.
     */
    List<? extends DRIDesignBand> getHeaderBands();

    /**
     * <p>getFooterBands.</p>
     *
     * @return a {@link java.util.List} object.
     */
    List<? extends DRIDesignBand> getFooterBands();

    /**
     * <p>isStartInNewPage.</p>
     *
     * @return a boolean.
     */
    boolean isStartInNewPage();

    /**
     * <p>isStartInNewColumn.</p>
     *
     * @return a boolean.
     */
    boolean isStartInNewColumn();

    /**
     * <p>isReprintHeaderOnEachPage.</p>
     *
     * @return a boolean.
     */
    boolean isReprintHeaderOnEachPage();

    /**
     * <p>isResetPageNumber.</p>
     *
     * @return a boolean.
     */
    boolean isResetPageNumber();

    /**
     * <p>getMinHeightToStartNewPage.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    Integer getMinHeightToStartNewPage();

    /**
     * <p>getFooterPosition.</p>
     *
     * @return a {@link ch.unibas.medizin.dynamicreports.report.constant.GroupFooterPosition} object.
     */
    GroupFooterPosition getFooterPosition();

    /**
     * <p>isKeepTogether.</p>
     *
     * @return a boolean.
     */
    boolean isKeepTogether();

    /**
     * <p>isHeaderWithSubtotal.</p>
     *
     * @return a boolean.
     */
    boolean isHeaderWithSubtotal();
}
