/*
 * Copyright ZoftSolutions(C) 2016 SUMESH T.G <ZoftSolutions>
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
 *  Developed by ZoftSolutions (2015) Company.
 */
package com.zs.ina.admin.master.locations.dao;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class LocationsPOJO {

    private final StringProperty id = new SimpleStringProperty();
    private final StringProperty country = new SimpleStringProperty();
    private final StringProperty location = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getLocation() {
        return location.get();
    }

    /**
     *
     * @param value
     */
    public void setLocation(String value) {
        location.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty locationProperty() {
        return location;
    }

    /**
     *
     * @return
     */
    public String getCountry() {
        return country.get();
    }

    /**
     *
     * @param value
     */
    public void setCountry(String value) {
        country.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty countryProperty() {
        return country;
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
        return "LocationsPOJO{" + "id=" + id + ", country=" + country + ", location=" + location + '}';
    }
    
    
    
}
