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
package com.zs.ina.search.common.bean;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author 100035
 */
public class ContactsBEAN {

    private final StringProperty rowId = new SimpleStringProperty();
    private final StringProperty collegeId = new SimpleStringProperty();
    private final StringProperty contactName = new SimpleStringProperty();
    private final StringProperty department = new SimpleStringProperty();
    private final StringProperty stdIsd = new SimpleStringProperty();
    private final StringProperty phone = new SimpleStringProperty();
    private final StringProperty email = new SimpleStringProperty();
    private final StringProperty trainingId = new SimpleStringProperty();

    public String getTrainingId() {
        return trainingId.get();
    }

    public void setTrainingId(String value) {
        trainingId.set(value);
    }

    public StringProperty trainingIdProperty() {
        return trainingId;
    }

    public String getCollegeId() {
        return collegeId.get();
    }

    public void setCollegeId(String value) {
        collegeId.set(value);
    }

    public StringProperty collegeIdProperty() {
        return collegeId;
    }

    public String getRowId() {
        return rowId.get();
    }

    public void setRowId(String value) {
        rowId.set(value);
    }

    public StringProperty rowIdProperty() {
        return rowId;
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

    public String getPhone() {
        return phone.get();
    }

    public void setPhone(String value) {
        phone.set(value);
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public String getStdIsd() {
        return stdIsd.get();
    }

    public void setStdIsd(String value) {
        stdIsd.set(value);
    }

    public StringProperty stdIsdProperty() {
        return stdIsd;
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

    public String getContactName() {
        return contactName.get();
    }

    public void setContactName(String value) {
        contactName.set(value);
    }

    public StringProperty contactNameProperty() {
        return contactName;
    }

}
