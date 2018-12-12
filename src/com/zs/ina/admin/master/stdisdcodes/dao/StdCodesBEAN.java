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
package com.zs.ina.admin.master.stdisdcodes.dao;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class StdCodesBEAN {

    private final StringProperty place = new SimpleStringProperty();
    private final StringProperty code = new SimpleStringProperty();
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
    public String getCode() {
        return code.get();
    }

    /**
     *
     * @param value
     */
    public void setCode(String value) {
        code.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty codeProperty() {
        return code;
    }

    /**
     *
     * @return
     */
    public String getPlace() {
        return place.get();
    }

    /**
     *
     * @param value
     */
    public void setPlace(String value) {
        place.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty placeProperty() {
        return place;
    }

    @Override
    public String toString() {
        return "StdCodesBEAN{" + "place=" + place + ", code=" + code + ", id=" + id + '}';
    }
    
}
