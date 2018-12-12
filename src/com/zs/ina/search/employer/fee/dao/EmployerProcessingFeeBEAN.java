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
package com.zs.ina.search.employer.fee.dao;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class EmployerProcessingFeeBEAN {

    private final StringProperty processingFeeId = new SimpleStringProperty();
    private final StringProperty employerId = new SimpleStringProperty();
    private final StringProperty processingFee = new SimpleStringProperty();
    private final StringProperty currency = new SimpleStringProperty();
    private final StringProperty amount = new SimpleStringProperty();

    public String getAmount() {
        return amount.get();
    }

    public void setAmount(String value) {
        amount.set(value);
    }

    public StringProperty amountProperty() {
        return amount;
    }

    public String getCurrency() {
        return currency.get();
    }

    public void setCurrency(String value) {
        currency.set(value);
    }

    public StringProperty currencyProperty() {
        return currency;
    }

    public String getProcessingFee() {
        return processingFee.get();
    }

    public void setProcessingFee(String value) {
        processingFee.set(value);
    }

    public StringProperty processingFeeProperty() {
        return processingFee;
    }

    public String getEmployerId() {
        return employerId.get();
    }

    public void setEmployerId(String value) {
        employerId.set(value);
    }

    public StringProperty employerIdProperty() {
        return employerId;
    }

    public String getProcessingFeeId() {
        return processingFeeId.get();
    }

    public void setProcessingFeeId(String value) {
        processingFeeId.set(value);
    }

    public StringProperty processingFeeIdProperty() {
        return processingFeeId;
    }

    @Override
    public String toString() {
        return "EmployerProcessingFeeBEAN{" + "processingFeeId=" + processingFeeId + ", employerId=" + employerId + ", processingFee=" + processingFee + ", currency=" + currency + ", amount=" + amount + '}';
    }

}
