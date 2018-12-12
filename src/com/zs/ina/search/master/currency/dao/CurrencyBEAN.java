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
package com.zs.ina.search.master.currency.dao;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class CurrencyBEAN {

    private final StringProperty id = new SimpleStringProperty();
    private final StringProperty currencyCode = new SimpleStringProperty();
    private final StringProperty country = new SimpleStringProperty();
    private final StringProperty cdate = new SimpleStringProperty();
    private final StringProperty rate = new SimpleStringProperty();
    private final StringProperty inRupees = new SimpleStringProperty();
    private final StringProperty baseId = new SimpleStringProperty();
    private final StringProperty baseCurrencyCode = new SimpleStringProperty();
    private final StringProperty currencyName = new SimpleStringProperty();

    public String getCurrencyName() {
        return currencyName.get();
    }

    public void setCurrencyName(String value) {
        currencyName.set(value);
    }

    public StringProperty currencyNameProperty() {
        return currencyName;
    }

    public String getBaseCurrencyCode() {
        return baseCurrencyCode.get();
    }

    public void setBaseCurrencyCode(String value) {
        baseCurrencyCode.set(value);
    }

    public StringProperty baseCurrencyCodeProperty() {
        return baseCurrencyCode;
    }

    public String getBaseId() {
        return baseId.get();
    }

    public void setBaseId(String value) {
        baseId.set(value);
    }

    public StringProperty baseIdProperty() {
        return baseId;
    }

    public String getInRupees() {
        return inRupees.get();
    }

    public void setInRupees(String value) {
        inRupees.set(value);
    }

    public StringProperty inRupeesProperty() {
        return inRupees;
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


    public String getCdate() {
        return cdate.get();
    }

    public void setCdate(String value) {
        cdate.set(value);
    }

    public StringProperty cdateProperty() {
        return cdate;
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
    public String getCurrency() {
        return currencyCode.get();
    }

    /**
     *
     * @param value
     */
    public void setCurrency(String value) {
        currencyCode.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty currencyProperty() {
        return currencyCode;
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
    /* ======================== Used For Auto Completion ==================== */
    @Override
    public String toString() {
        return currencyCode.get();
    }

}
