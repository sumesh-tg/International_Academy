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
package com.zs.ina.search.migration.dao;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class MigrationBasicBEAN {

    private final StringProperty migraProviderId = new SimpleStringProperty();
    private final StringProperty subClass = new SimpleStringProperty();
    private final StringProperty ocupationCat = new SimpleStringProperty();
    private final StringProperty country = new SimpleStringProperty();
    private final StringProperty createdDate = new SimpleStringProperty();
    private final StringProperty updatedDate = new SimpleStringProperty();
    private final StringProperty createdUser = new SimpleStringProperty();
    private final StringProperty updatedUser = new SimpleStringProperty();

    public String getCountry() {
        return country.get();
    }

    public void setCountry(String value) {
        country.set(value);
    }

    public StringProperty countryProperty() {
        return country;
    }

    public String getUpdatedUser() {
        return updatedUser.get();
    }

    public void setUpdatedUser(String value) {
        updatedUser.set(value);
    }

    public StringProperty updatedUserProperty() {
        return updatedUser;
    }

    public String getCreatedUser() {
        return createdUser.get();
    }

    public void setCreatedUser(String value) {
        createdUser.set(value);
    }

    public StringProperty createdUserProperty() {
        return createdUser;
    }

    public String getUpdatedDate() {
        return updatedDate.get();
    }

    public void setUpdatedDate(String value) {
        updatedDate.set(value);
    }

    public StringProperty updatedDateProperty() {
        return updatedDate;
    }

    public String getCreatedDate() {
        return createdDate.get();
    }

    public void setCreatedDate(String value) {
        createdDate.set(value);
    }

    public StringProperty createdDateProperty() {
        return createdDate;
    }

    public String getOcupationCat() {
        return ocupationCat.get();
    }

    public void setOcupationCat(String value) {
        ocupationCat.set(value);
    }

    public StringProperty ocupationCatProperty() {
        return ocupationCat;
    }

    public String getSubClass() {
        return subClass.get();
    }

    public void setSubClass(String value) {
        subClass.set(value);
    }

    public StringProperty subClassProperty() {
        return subClass;
    }

    public String getMigraProviderId() {
        return migraProviderId.get();
    }

    public void setMigraProviderId(String value) {
        migraProviderId.set(value);
    }

    public StringProperty migraProviderIdProperty() {
        return migraProviderId;
    }

    @Override
    public String toString() {
        return "MigrationBEAN{" + "migraProviderId=" + migraProviderId + ", subClass=" + subClass + ", ocupationCat=" + ocupationCat + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + ", createdUser=" + createdUser + ", updatedUser=" + updatedUser + '}';
    }
}
