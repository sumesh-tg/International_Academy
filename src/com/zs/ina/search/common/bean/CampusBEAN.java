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
package com.zs.ina.search.common.bean;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author 100035
 */
public class CampusBEAN {

    private final StringProperty rowId = new SimpleStringProperty();
    private final StringProperty collegeId = new SimpleStringProperty();
    private final StringProperty campus = new SimpleStringProperty();
    private final StringProperty country = new SimpleStringProperty();
    private final StringProperty location = new SimpleStringProperty();
    private final StringProperty address = new SimpleStringProperty();
    private final StringProperty trainingId = new SimpleStringProperty();

    public String getTrainingId() {
        return trainingId.get();
    }

    public void setTrainingId(String value) {
        trainingId.set(value);
    }

    public StringProperty trainingIdProperty() {
        return trainingId;
    }
    
    private final StringProperty accomodation = new SimpleStringProperty();

    public String getAccomodation() {
        return accomodation.get();
    }

    public void setAccomodation(String value) {
        accomodation.set(value);
    }

    public StringProperty accomodationProperty() {
        return accomodation;
    }

    public String getCollegeId() {
        return collegeId.get();
    }

    public void setCollegeId(String value) {
        collegeId.set(value);
    }

    public StringProperty collegeIdProperty() {
        return collegeId;
    }
    
   
    public String getAddress() {
        return address.get();
    }

    public void setAddress(String value) {
        address.set(value);
    }

 
    public StringProperty addressProperty() {
        return address;
    }

    public String getLocation() {
        return location.get();
    }

  
    public void setLocation(String value) {
        location.set(value);
    }

  
    public StringProperty locationProperty() {
        return location;
    }


    public String getCountry() {
        return country.get();
    }

    public void setCountry(String value) {
        country.set(value);
    }

  
    public StringProperty countryProperty() {
        return country;
    }

    public String getCampus() {
        return campus.get();
    }

    public void setCampus(String value) {
        campus.set(value);
    }

    public StringProperty campusProperty() {
        return campus;
    }

 
    public String getRowId() {
        return rowId.get();
    }

    public void setRowId(String value) {
        rowId.set(value);
    }

    public StringProperty rowIdProperty() {
        return rowId;
    }

    @Override
    public String toString() {
        return "CampusBEAN{" + "rowId=" + rowId + ", collegeId=" + collegeId + ", campus=" + campus + ", country=" + country + ", location=" + location + ", address=" + address + ", trainingId=" + trainingId + ", accomodation=" + accomodation + '}';
    }
    
}
