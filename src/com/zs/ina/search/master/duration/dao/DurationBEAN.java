/*
 * Copyright (C) 2016 100035
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package com.zs.ina.search.master.duration.dao;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author 100035
 */
public class DurationBEAN {

    private final StringProperty durationId = new SimpleStringProperty();
    private final StringProperty duration = new SimpleStringProperty();
    private final StringProperty durationDays = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getDurationDays() {
        return durationDays.get();
    }

    /**
     *
     * @param value
     */
    public void setDurationDays(String value) {
        durationDays.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty durationDaysProperty() {
        return durationDays;
    }

    /**
     *
     * @return
     */
    public String getDuration() {
        return duration.get();
    }

    /**
     *
     * @param value
     */
    public void setDuration(String value) {
        duration.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty durationProperty() {
        return duration;
    }

    /**
     *
     * @return
     */
    public String getDurationId() {
        return durationId.get();
    }

    /**
     *
     * @param value
     */
    public void setDurationId(String value) {
        durationId.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty durationIdProperty() {
        return durationId;
    }
  
}
