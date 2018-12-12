/*
 * Copyright (C) 2016 100035
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
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package com.zs.ina.admin.master.locations.country.dao;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author 100035
 */
public class CountryBEAN {
    private final StringProperty id = new SimpleStringProperty();
    private final StringProperty country = new SimpleStringProperty();
    private final StringProperty state = new SimpleStringProperty();
    private final StringProperty district = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getDistrict() {
        return district.get();
    }

    /**
     *
     * @param value
     */
    public void setDistrict(String value) {
        district.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty districtProperty() {
        return district;
    }

    /**
     *
     * @return
     */
    public String getState() {
        return state.get();
    }

    /**
     *
     * @param value
     */
    public void setState(String value) {
        state.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty stateProperty() {
        return state;
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
    
}
