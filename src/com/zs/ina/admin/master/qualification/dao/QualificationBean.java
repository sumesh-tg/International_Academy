/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.admin.master.qualification.dao;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author zoft
 */
public class QualificationBean {
    private final StringProperty qualificationLevel = new SimpleStringProperty();
    private final StringProperty qualificationField = new SimpleStringProperty();
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
    public QualificationBean() {
    }

    /**
     *
     * @return
     */
    public String getQualificationField() {
        return qualificationField.get();
    }

    /**
     *
     * @param value
     */
    public void setQualificationField(String value) {
        qualificationField.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty qualificationFieldProperty() {
        return qualificationField;
    }

    /**
     *
     * @return
     */
    public String getQualificationLevel() {
        return qualificationLevel.get();
    }

    /**
     *
     * @param value
     */
    public void setQualificationLevel(String value) {
        qualificationLevel.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty qualificationLevelProperty() {
        return qualificationLevel;
    }
    
}
