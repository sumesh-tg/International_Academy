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
package com.zs.ina.search.employer.branch.dao;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class EmployerBranchBEAN {

    private final StringProperty employerId = new SimpleStringProperty();
    private final StringProperty empBranchId = new SimpleStringProperty();
    private final StringProperty empBranchName = new SimpleStringProperty();
    private final StringProperty country = new SimpleStringProperty();
    private final StringProperty location = new SimpleStringProperty();
    private final StringProperty contactAddress = new SimpleStringProperty();

    public String getContactAddress() {
        return contactAddress.get();
    }

    public void setContactAddress(String value) {
        contactAddress.set(value);
    }

    public StringProperty contactAddressProperty() {
        return contactAddress;
    }

    public String getLocation() {
        return location.get();
    }

    public void setLocation(String value) {
        location.set(value);
    }

    public StringProperty locationProperty() {
        return location;
    }

    public String getCountry() {
        return country.get();
    }

    public void setCountry(String value) {
        country.set(value);
    }

    public StringProperty countryProperty() {
        return country;
    }

    public String getEmpBranchName() {
        return empBranchName.get();
    }

    public void setEmpBranchName(String value) {
        empBranchName.set(value);
    }

    public StringProperty empBranchNameProperty() {
        return empBranchName;
    }

    public String getEmpBranchId() {
        return empBranchId.get();
    }

    public void setEmpBranchId(String value) {
        empBranchId.set(value);
    }

    public StringProperty empBranchIdProperty() {
        return empBranchId;
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

    @Override
    public String toString() {
        return "EmployerBranchBEAN{" + "employerId=" + employerId + ", empBranchId=" + empBranchId + ", empBranchName=" + empBranchName + ", country=" + country + ", location=" + location + ", contactAddress=" + contactAddress + '}';
    }

}
