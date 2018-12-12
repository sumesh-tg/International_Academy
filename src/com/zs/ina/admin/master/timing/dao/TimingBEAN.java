/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.admin.master.timing.dao;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author user
 */
public class TimingBEAN {

    private final StringProperty timing = new SimpleStringProperty();
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
    public String getTiming() {
        return timing.get();
    }

    /**
     *
     * @param value
     */
    public void setTiming(String value) {
        timing.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty timingProperty() {
        return timing;
    }

    @Override
    public String toString() {
        return "TimingBEAN{" + "timing=" + timing + ", id=" + id + '}';
    }

}
