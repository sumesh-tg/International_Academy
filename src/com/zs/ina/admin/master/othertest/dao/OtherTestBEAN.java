/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.admin.master.othertest.dao;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author user
 */
public class OtherTestBEAN {

    private final StringProperty otherTest = new SimpleStringProperty();
    private final StringProperty id = new SimpleStringProperty();

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

    /**
     *
     * @return
     */
    public String getOtherTest() {
        return otherTest.get();
    }

    /**
     *
     * @param value
     */
    public void setOtherTest(String value) {
        otherTest.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty otherTestProperty() {
        return otherTest;
    }

    @Override
    public String toString() {
        return "OtherTestBEAN{" + "otherTest=" + otherTest + ", id=" + id + '}';
    }

}
