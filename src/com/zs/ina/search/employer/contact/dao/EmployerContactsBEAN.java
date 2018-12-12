/*
 * Copyright ZoftSolutions(C) 2016 SUMESH T.G <ZoftSolutions>
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
package com.zs.ina.search.employer.contact.dao;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class EmployerContactsBEAN {

    private final StringProperty email = new SimpleStringProperty();
    private final StringProperty phoneNo = new SimpleStringProperty();
    private final StringProperty department = new SimpleStringProperty();
    private final StringProperty contactName = new SimpleStringProperty();
    private final StringProperty employerId = new SimpleStringProperty();
    private final StringProperty employerContactId = new SimpleStringProperty();
    private final StringProperty stdIsd = new SimpleStringProperty();

    public String getStdIsd() {
        return stdIsd.get();
    }

    public void setStdIsd(String value) {
        stdIsd.set(value);
    }

    public StringProperty stdIsdProperty() {
        return stdIsd;
    }

    public String getEmployerContactId() {
        return employerContactId.get();
    }

    public void setEmployerContactId(String value) {
        employerContactId.set(value);
    }

    public StringProperty employerContactIdProperty() {
        return employerContactId;
    }

    public String getEmployerId() {
        return employerId.get();
    }

    public void setEmployerId(String value) {
        employerId.set(value);
    }

    public StringProperty employerIdProperty() {
        return employerId;
    }

    public String getContactName() {
        return contactName.get();
    }

    public void setContactName(String value) {
        contactName.set(value);
    }

    public StringProperty contactNameProperty() {
        return contactName;
    }

    public String getDepartment() {
        return department.get();
    }

    public void setDepartment(String value) {
        department.set(value);
    }

    public StringProperty departmentProperty() {
        return department;
    }

    public String getPhoneNo() {
        return phoneNo.get();
    }

    public void setPhoneNo(String value) {
        phoneNo.set(value);
    }

    public StringProperty phoneNoProperty() {
        return phoneNo;
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String value) {
        email.set(value);
    }

    public StringProperty emailProperty() {
        return email;
    }

    @Override
    public String toString() {
        return "EmployerContactsBEAN{" + "email=" + email + ", phoneNo=" + phoneNo + ", department=" + department + ", contactName=" + contactName + ", employerId=" + employerId + ", employerContactId=" + employerContactId + '}';
    }

}
