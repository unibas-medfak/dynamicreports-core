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
package ch.unibas.medizin.dynamicreports.design.base.style;

import ch.unibas.medizin.dynamicreports.design.definition.style.DRIDesignTabStop;
import ch.unibas.medizin.dynamicreports.report.constant.Constants;
import ch.unibas.medizin.dynamicreports.report.constant.TabStopAlignment;
import org.apache.commons.lang3.builder.EqualsBuilder;

import java.io.Serial;

/**
 * <p>DRDesignTabStop class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DRDesignTabStop implements DRIDesignTabStop {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private int position;
    private TabStopAlignment alignment;

    /** {@inheritDoc} */
    @Override
    public int getPosition() {
        return position;
    }

    /**
     * <p>Setter for the field <code>position</code>.</p>
     *
     * @param position an int.
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /** {@inheritDoc} */
    @Override
    public TabStopAlignment getAlignment() {
        return alignment;
    }

    /**
     * <p>Setter for the field <code>alignment</code>.</p>
     *
     * @param alignment a {@link ch.unibas.medizin.dynamicreports.report.constant.TabStopAlignment} object.
     */
    public void setAlignment(TabStopAlignment alignment) {
        this.alignment = alignment;
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }

        DRDesignTabStop o = (DRDesignTabStop) obj;
        EqualsBuilder equalsBuilder = new EqualsBuilder().append(position, o.position).append(alignment, o.alignment);
        return equalsBuilder.isEquals();
    }
}
