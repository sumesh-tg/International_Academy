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
package com.zs.ina.notice.dao;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author 100035
 */
public class NoticeBEAN {
    private final StringProperty id = new SimpleStringProperty();
    private final StringProperty createdDate = new SimpleStringProperty();
    private final StringProperty title = new SimpleStringProperty();
    private final StringProperty description = new SimpleStringProperty();
    private final StringProperty expiredDate = new SimpleStringProperty();
    private final StringProperty branchId = new SimpleStringProperty();
    private final StringProperty fromDate = new SimpleStringProperty();
    private final StringProperty flag = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getFlag() {
        return flag.get();
    }

    /**
     *
     * @param value
     */
    public void setFlag(String value) {
        flag.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty flagProperty() {
        return flag;
    }

    /**
     *
     * @return
     */
    public String getFromDate() {
        return fromDate.get();
    }

    /**
     *
     * @param value
     */
    public void setFromDate(String value) {
        fromDate.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty fromDateProperty() {
        return fromDate;
    }

    /**
     *
     * @return
     */
    public String getBranchId() {
        return branchId.get();
    }

    /**
     *
     * @param value
     */
    public void setBranchId(String value) {
        branchId.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty branchIdProperty() {
        return branchId;
    }

    /**
     *
     * @return
     */
    public String getExpiredDate() {
        return expiredDate.get();
    }

    /**
     *
     * @param value
     */
    public void setExpiredDate(String value) {
        expiredDate.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty expiredDateProperty() {
        return expiredDate;
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return description.get();
    }

    /**
     *
     * @param value
     */
    public void setDescription(String value) {
        description.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty descriptionProperty() {
        return description;
    }

    /**
     *
     * @return
     */
    public String getTitle() {
        return title.get();
    }

    /**
     *
     * @param value
     */
    public void setTitle(String value) {
        title.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty titleProperty() {
        return title;
    }

    /**
     *
     * @return
     */
    public String getCreatedDate() {
        return createdDate.get();
    }

    /**
     *
     * @param value
     */
    public void setCreatedDate(String value) {
        createdDate.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty createdDateProperty() {
        return createdDate;
    }
    
    /**
     *
     * @return
     */
    public String getId() {
        return id.get();
    }

    /**
     *
     * @param value
     */
    public void setId(String value) {
        id.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty idProperty() {
        return id;
    }
    
    
}
