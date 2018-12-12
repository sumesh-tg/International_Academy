/*
 * Copyright ZoftSolutions(C) 2016 100018
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
 *  Developed by ZoftSolutions (2015) Company.
 */
package com.zs.ina.admin.master.counselorlimit.dao;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author 100018
 */
public class CounselorLimitPOJO {

    private final StringProperty id = new SimpleStringProperty();
    private final StringProperty login_id = new SimpleStringProperty();
    private final StringProperty enquiry_limit = new SimpleStringProperty();
    private final StringProperty branch = new SimpleStringProperty();
    private final StringProperty counselorName = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getCounselorName() {
        return counselorName.get();
    }

    /**
     *
     * @param value
     */
    public void setCounselorName(String value) {
        counselorName.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty counselorNameProperty() {
        return counselorName;
    }

    /**
     *
     * @return
     */
    public String getBranch() {
        return branch.get();
    }

    /**
     *
     * @param value
     */
    public void setBranch(String value) {
        branch.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty branchProperty() {
        return branch;
    }

    /**
     *
     * @return
     */
    public String getEnquiry_limit() {
        return enquiry_limit.get();
    }

    /**
     *
     * @param value
     */
    public void setEnquiry_limit(String value) {
        enquiry_limit.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty enquiry_limitProperty() {
        return enquiry_limit;
    }

    /**
     *
     * @return
     */
    public String getLogin_id() {
        return login_id.get();
    }

    /**
     *
     * @param value
     */
    public void setLogin_id(String value) {
        login_id.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty login_idProperty() {
        return login_id;
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

    @Override
    public String toString() {
        return "CounselorLimitPOJO{" + "id=" + id + ", login_id=" + login_id + ", enquiry_limit=" + enquiry_limit + ", branch=" + branch + ", counselorName=" + counselorName + '}';
    }
}
