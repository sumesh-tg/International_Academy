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
package com.zs.ina.accounts.master.dao;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class PaymentTermsBEAN {

    private final StringProperty termsId = new SimpleStringProperty();
    private final StringProperty showItAs = new SimpleStringProperty();
    private final StringProperty numberOfDays = new SimpleStringProperty();
    private final StringProperty isDelete = new SimpleStringProperty();

    public String getIsDelete() {
        return isDelete.get();
    }

    public void setIsDelete(String value) {
        isDelete.set(value);
    }

    public StringProperty isDeleteProperty() {
        return isDelete;
    }

    public String getNumberOfDays() {
        return numberOfDays.get();
    }

    public void setNumberOfDays(String value) {
        numberOfDays.set(value);
    }

    public StringProperty numberOfDaysProperty() {
        return numberOfDays;
    }

    public String getShowItAs() {
        return showItAs.get();
    }

    public void setShowItAs(String value) {
        showItAs.set(value);
    }

    public StringProperty showItAsProperty() {
        return showItAs;
    }

    public String getTermsId() {
        return termsId.get();
    }

    public void setTermsId(String value) {
        termsId.set(value);
    }

    public StringProperty termsIdProperty() {
        return termsId;
    }

    @Override
    public String toString() {
        return "PaymentTermsBEAN{" + "termsId=" + termsId + ", showItAs=" + showItAs + ", numberOfDays=" + numberOfDays + ", isDelete=" + isDelete + '}';
    }
    
}
