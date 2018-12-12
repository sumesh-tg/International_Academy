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
package com.zs.ina.admin.master.sources.dao;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author 100035
 */
public class SourceBEAN {

    private final StringProperty enquirySourceId = new SimpleStringProperty();
    private final StringProperty sourceName = new SimpleStringProperty();
    private final StringProperty sourceType = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getEnquirySourceId() {
        return enquirySourceId.get();
    }

    /**
     *
     * @param value
     */
    public void setEnquirySourceId(String value) {
        enquirySourceId.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty enquirySourceIdProperty() {
        return enquirySourceId;
    }

    /**
     *
     * @return
     */
    public String getSourceType() {
        return sourceType.get();
    }

    /**
     *
     * @param value
     */
    public void setSourceType(String value) {
        sourceType.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty sourceTypeProperty() {
        return sourceType;
    }

    /**
     *
     * @return
     */
    public String getSourceName() {
        return sourceName.get();
    }

    /**
     *
     * @param value
     */
    public void setSourceName(String value) {
        sourceName.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty sourceNameProperty() {
        return sourceName;
    }

    

}
