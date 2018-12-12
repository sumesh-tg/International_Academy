/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.enquiry.empstatus.dao;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author zoft
 */
public class EmpStatusBEAN {
    private final StringProperty id = new SimpleStringProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty position = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();
    private final StringProperty contact_number = new SimpleStringProperty();
    private final StringProperty ext_number = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getExt_number() {
        return ext_number.get();
    }

    /**
     *
     * @param value
     */
    public void setExt_number(String value) {
        ext_number.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty ext_numberProperty() {
        return ext_number;
    }

    /**
     *
     * @return
     */
    public String getContact_number() {
        return contact_number.get();
    }

    /**
     *
     * @param value
     */
    public void setContact_number(String value) {
        contact_number.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty contact_numberProperty() {
        return contact_number;
    }

    /**
     *
     * @return
     */
    public String getStatus() {
        return status.get();
    }

    /**
     *
     * @param value
     */
    public void setStatus(String value) {
        status.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty statusProperty() {
        return status;
    }

    /**
     *
     * @return
     */
    public String getPosition() {
        return position.get();
    }

    /**
     *
     * @param value
     */
    public void setPosition(String value) {
        position.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty positionProperty() {
        return position;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name.get();
    }

    /**
     *
     * @param value
     */
    public void setName(String value) {
        name.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty nameProperty() {
        return name;
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
        return "EmpStatusBEAN{" + "id=" + id + ", name=" + name + ", position=" + position + ", status=" + status + ", contact_number=" + contact_number + ", ext_number=" + ext_number + '}';
    }
    
   
}
