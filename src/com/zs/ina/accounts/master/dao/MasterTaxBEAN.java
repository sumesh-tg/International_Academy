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
public class MasterTaxBEAN {

    private final StringProperty taxId = new SimpleStringProperty();
    private final StringProperty taxName = new SimpleStringProperty();
    private final StringProperty rate = new SimpleStringProperty();
    private final StringProperty isCompoundTax = new SimpleStringProperty();
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

    public String getIsCompoundTax() {
        return isCompoundTax.get();
    }

    public void setIsCompoundTax(String value) {
        isCompoundTax.set(value);
    }

    public StringProperty isCompoundTaxProperty() {
        return isCompoundTax;
    }

    public String getRate() {
        return rate.get();
    }

    public void setRate(String value) {
        rate.set(value);
    }

    public StringProperty rateProperty() {
        return rate;
    }

    public String getTaxName() {
        return taxName.get();
    }

    public void setTaxName(String value) {
        taxName.set(value);
    }

    public StringProperty taxNameProperty() {
        return taxName;
    }

    public String getTaxId() {
        return taxId.get();
    }

    public void setTaxId(String value) {
        taxId.set(value);
    }

    public StringProperty taxIdProperty() {
        return taxId;
    }

    @Override
    public String toString() {
        return "MasterTaxBEAN{" + "taxId=" + taxId + ", taxName=" + taxName + ", rate=" + rate + '}';
    }
}
