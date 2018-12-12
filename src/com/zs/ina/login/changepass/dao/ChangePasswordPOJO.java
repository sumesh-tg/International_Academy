/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.login.changepass.dao;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author zoft
 */
public class ChangePasswordPOJO {
    private final StringProperty curPassword = new SimpleStringProperty();
    private final StringProperty newPass = new SimpleStringProperty();
    private final StringProperty renewPass = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getRenewPass() {
        return renewPass.get();
    }

    /**
     *
     * @param value
     */
    public void setRenewPass(String value) {
        renewPass.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty renewPassProperty() {
        return renewPass;
    }

    /**
     *
     * @return
     */
    public String getNewPass() {
        return newPass.get();
    }

    /**
     *
     * @param value
     */
    public void setNewPass(String value) {
        newPass.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty newPassProperty() {
        return newPass;
    }

    /**
     *
     * @return
     */
    public String getCurPassword() {
        return curPassword.get();
    }

    /**
     *
     * @param value
     */
    public void setCurPassword(String value) {
        curPassword.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty curPasswordProperty() {
        return curPassword;
    }
    
}
