/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.admin.master.language.dao;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author user
 */
public class LanguageBEAN {

    private final StringProperty language = new SimpleStringProperty();
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
    public String getLanguage() {
        return language.get();
    }

    /**
     *
     * @param value
     */
    public void setLanguage(String value) {
        language.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty languageProperty() {
        return language;
    }

    @Override
    public String toString() {
        return "LanguageBEAN{" + "language=" + language + ", id=" + id + '}';
    }

}
