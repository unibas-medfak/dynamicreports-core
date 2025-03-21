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
package net.sf.dynamicreports.design.definition;

import net.sf.dynamicreports.design.definition.expression.DRIDesignExpression;
import net.sf.dynamicreports.report.constant.GroupFooterPosition;

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
    public String getName();

    /**
     * <p>getGroupExpression.</p>
     *
     * @return a {@link net.sf.dynamicreports.design.definition.expression.DRIDesignExpression} object.
     */
    public DRIDesignExpression getGroupExpression();

    /**
     * <p>getHeaderBands.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<? extends DRIDesignBand> getHeaderBands();

    /**
     * <p>getFooterBands.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<? extends DRIDesignBand> getFooterBands();

    /**
     * <p>isStartInNewPage.</p>
     *
     * @return a boolean.
     */
    public boolean isStartInNewPage();

    /**
     * <p>isStartInNewColumn.</p>
     *
     * @return a boolean.
     */
    public boolean isStartInNewColumn();

    /**
     * <p>isReprintHeaderOnEachPage.</p>
     *
     * @return a boolean.
     */
    public boolean isReprintHeaderOnEachPage();

    /**
     * <p>isResetPageNumber.</p>
     *
     * @return a boolean.
     */
    public boolean isResetPageNumber();

    /**
     * <p>getMinHeightToStartNewPage.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getMinHeightToStartNewPage();

    /**
     * <p>getFooterPosition.</p>
     *
     * @return a {@link net.sf.dynamicreports.report.constant.GroupFooterPosition} object.
     */
    public GroupFooterPosition getFooterPosition();

    /**
     * <p>isKeepTogether.</p>
     *
     * @return a boolean.
     */
    public boolean isKeepTogether();

    /**
     * <p>isHeaderWithSubtotal.</p>
     *
     * @return a boolean.
     */
    public boolean isHeaderWithSubtotal();
}
