/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.admin.master.batchtime.dao;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author zoft
 */
public class BatchTimeBean {
    private final StringProperty batchTime = new SimpleStringProperty();
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
     */
    public BatchTimeBean() {
    }

    /**
     *
     * @return
     */
    public String getBatchTime() {
        return batchTime.get();
    }

    /**
     *
     * @param value
     */
    public void setBatchTime(String value) {
        batchTime.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty batchTimeProperty() {
        return batchTime;
    }
    
}
