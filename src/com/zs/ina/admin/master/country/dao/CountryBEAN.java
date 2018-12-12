/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.admin.master.country.dao;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author user
 */
public class CountryBEAN {

    private final StringProperty countryName = new SimpleStringProperty();
    private final StringProperty specification = new SimpleStringProperty();
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
    public String getSpecification() {
        return specification.get();
    }

    /**
     *
     * @param value
     */
    public void setSpecification(String value) {
        specification.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty specificationProperty() {
        return specification;
    }

    /**
     *
     * @return
     */
    public String getCountryName() {
        return countryName.get();
    }

    /**
     *
     * @param value
     */
    public void setCountryName(String value) {
        countryName.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty countryNameProperty() {
        return countryName;
    }

    @Override
    public String toString() {
        return "CountryBEAN{" + "countryName=" + countryName + ", specification=" + specification + ", id=" + id + '}';
    }

}
