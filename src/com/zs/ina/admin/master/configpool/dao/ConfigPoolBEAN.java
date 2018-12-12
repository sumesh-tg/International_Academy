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
package com.zs.ina.admin.master.configpool.dao;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author 100035
 */
public class ConfigPoolBEAN {
    private final StringProperty id = new SimpleStringProperty();
    private final StringProperty loginId = new SimpleStringProperty();
    private final StringProperty timeLimit = new SimpleStringProperty();
    private final StringProperty enquiryLimit = new SimpleStringProperty();
    private final StringProperty enquiryDuration = new SimpleStringProperty();
    private final StringProperty flagLimit = new SimpleStringProperty();
    private final StringProperty days = new SimpleStringProperty();
    private final StringProperty hours = new SimpleStringProperty();
    private final StringProperty minutes = new SimpleStringProperty();
    private final StringProperty tblTimeLimit = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getTblTimeLimit() {
        return tblTimeLimit.get();
    }

    /**
     *
     * @param value
     */
    public void setTblTimeLimit(String value) {
        tblTimeLimit.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty tblTimeLimitProperty() {
        return tblTimeLimit;
    }
    
    /**
     *
     * @return
     */
    public String getMinutes() {
        return minutes.get();
    }

    /**
     *
     * @param value
     */
    public void setMinutes(String value) {
        minutes.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty minutesProperty() {
        return minutes;
    }

    /**
     *
     * @return
     */
    public String getHours() {
        return hours.get();
    }

    /**
     *
     * @param value
     */
    public void setHours(String value) {
        hours.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty hoursProperty() {
        return hours;
    }
    
    /**
     *
     * @return
     */
    public String getDays() {
        return days.get();
    }

    /**
     *
     * @param value
     */
    public void setDays(String value) {
        days.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty daysProperty() {
        return days;
    }
    
    
    /*========= additional purpose===========*/
    private final StringProperty branch = new SimpleStringProperty();
    private final StringProperty fullname = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getFullname() {
        return fullname.get();
    }

    /**
     *
     * @param value
     */
    public void setFullname(String value) {
        fullname.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty fullnameProperty() {
        return fullname;
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
    public String getFlagLimit() {
        return flagLimit.get();
    }

    /**
     *
     * @param value
     */
    public void setFlagLimit(String value) {
        flagLimit.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty flagLimitProperty() {
        return flagLimit;
    }
    
    
    private final StringProperty username = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getUsername() {
        return username.get();
    }

    /**
     *
     * @param value
     */
    public void setUsername(String value) {
        username.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty usernameProperty() {
        return username;
    }
    
    /**
     *
     * @return
     */
    public String getEnquiryDuration() {
        return enquiryDuration.get();
    }

    /**
     *
     * @param value
     */
    public void setEnquiryDuration(String value) {
        enquiryDuration.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty enquiryDurationProperty() {
        return enquiryDuration;
    }
    
    /**
     *
     * @return
     */
    public String getEnquiryLimit() {
        return enquiryLimit.get();
    }

    /**
     *
     * @param value
     */
    public void setEnquiryLimit(String value) {
        enquiryLimit.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty enquiryLimitProperty() {
        return enquiryLimit;
    }

    /**
     *
     * @return
     */
    public String getTimeLimit() {
        return timeLimit.get();
    }

    /**
     *
     * @param value
     */
    public void setTimeLimit(String value) {
        timeLimit.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty timeLimitProperty() {
        return timeLimit;
    }

    /**
     *
     * @return
     */
    public String getLoginId() {
        return loginId.get();
    }

    /**
     *
     * @param value
     */
    public void setLoginId(String value) {
        loginId.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty loginIdProperty() {
        return loginId;
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
