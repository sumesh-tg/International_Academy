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
package com.zs.ina.admin.master.userlogin.dao;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author 100035
 */
public class LoginBEAN {

    /* ===================== Basic Variables ================= */
    private final StringProperty loginId = new SimpleStringProperty();
    private final StringProperty username = new SimpleStringProperty();
    private final StringProperty password = new SimpleStringProperty();
    private final StringProperty firstName = new SimpleStringProperty();
    private final StringProperty lastName = new SimpleStringProperty();
    private final StringProperty roleId = new SimpleStringProperty();
    private final StringProperty role = new SimpleStringProperty();
    private final StringProperty roleText = new SimpleStringProperty();
    private final StringProperty email = new SimpleStringProperty();
    private final StringProperty phoneNo = new SimpleStringProperty();
    private final StringProperty branchId = new SimpleStringProperty();
    private final StringProperty loginDetailsId = new SimpleStringProperty();
    private final StringProperty privilageId = new SimpleStringProperty();
    private final StringProperty privilages = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getRole() {
        return role.get();
    }

    /**
     *
     * @param value
     */
    public void setRole(String value) {
        role.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty roleProperty() {
        return role;
    }

    /**
     *
     * @return
     */
    public String getRoleText() {
        return roleText.get();
    }

    /**
     *
     * @param value
     */
    public void setRoleText(String value) {
        roleText.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty roleTextProperty() {
        return roleText;
    }

    /**
     *
     * @return
     */
    public String getPrivilages() {
        return privilages.get();
    }

    /**
     *
     * @param value
     */
    public void setPrivilages(String value) {
        privilages.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty privilagesProperty() {
        return privilages;
    }

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
    public String getPrivilageId() {
        return privilageId.get();
    }

    /**
     *
     * @param value
     */
    public void setPrivilageId(String value) {
        privilageId.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty privilegeIdProperty() {
        return privilageId;
    }

    /**
     *
     * @return
     */
    public String getLoginDetailsId() {
        return loginDetailsId.get();
    }

    /**
     *
     * @param value
     */
    public void setLoginDetailsId(String value) {
        loginDetailsId.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty loginDetailsIdProperty() {
        return loginDetailsId;
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
    public String getPassword() {
        return password.get();
    }

    /**
     *
     * @param value
     */
    public void setPassword(String value) {
        password.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty passwordProperty() {
        return password;
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
    public String getPhoneNo() {
        return phoneNo.get();
    }

    /**
     *
     * @param value
     */
    public void setPhoneNo(String value) {
        phoneNo.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty phoneNoProperty() {
        return phoneNo;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email.get();
    }

    /**
     *
     * @param value
     */
    public void setEmail(String value) {
        email.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty emailProperty() {
        return email;
    }

    /**
     *
     * @return
     */
    public String getRoleId() {
        return roleId.get();
    }

    /**
     *
     * @param value
     */
    public void setRoleId(String value) {
        roleId.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty roleIdProperty() {
        return roleId;
    }

    /**
     *
     * @return
     */
    public String getLastName() {
        return lastName.get();
    }

    /**
     *
     * @param value
     */
    public void setLastName(String value) {
        lastName.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty lastNameProperty() {
        return lastName;
    }

    /**
     *
     * @return
     */
    public String getFirstName() {
        return firstName.get();
    }

    /**
     *
     * @param value
     */
    public void setFirstName(String value) {
        firstName.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty firstNameProperty() {
        return firstName;
    }

}
