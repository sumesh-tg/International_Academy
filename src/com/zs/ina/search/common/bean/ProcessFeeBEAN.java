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
public class ProcessFeeBEAN {

    private final StringProperty rowId = new SimpleStringProperty();
    private final StringProperty collegeId = new SimpleStringProperty();
    private final StringProperty currency = new SimpleStringProperty();
    private final StringProperty feeType = new SimpleStringProperty();
    private final StringProperty amount = new SimpleStringProperty();
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

    public String getCollegeId() {
        return collegeId.get();
    }

    public void setCollegeId(String value) {
        collegeId.set(value);
    }

    public StringProperty collegeIdProperty() {
        return collegeId;
    }

    /**
     *
     * @return
     */
    public String getAmount() {
        return amount.get();
    }

    /**
     *
     * @param value
     */
    public void setAmount(String value) {
        amount.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty amountProperty() {
        return amount;
    }

    /**
     *
     * @return
     */
    public String getFeeType() {
        return feeType.get();
    }

    /**
     *
     * @param value
     */
    public void setFeeType(String value) {
        feeType.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty feeTypeProperty() {
        return feeType;
    }

    /**
     *
     * @return
     */
    public String getCurrency() {
        return currency.get();
    }

    /**
     *
     * @param value
     */
    public void setCurrency(String value) {
        currency.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty currencyProperty() {
        return currency;
    }

    /**
     *
     * @return
     */
    public String getRowId() {
        return rowId.get();
    }

    /**
     *
     * @param value
     */
    public void setRowId(String value) {
        rowId.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty rowIdProperty() {
        return rowId;
    }

}
