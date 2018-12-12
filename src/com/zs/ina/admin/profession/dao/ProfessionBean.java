/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.admin.profession.dao;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author zoft
 */
public class ProfessionBean {
    private final StringProperty profession = new SimpleStringProperty();
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
    public ProfessionBean() {
    }

    /**
     *
     * @return
     */
    public String getProfession() {
        return profession.get();
    }

    /**
     *
     * @param value
     */
    public void setProfession(String value) {
        profession.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty professionProperty() {
        return profession;
    }
    
}
