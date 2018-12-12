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
public class FeeBEAN {

    private final StringProperty rowId = new SimpleStringProperty();
    private final StringProperty courseId = new SimpleStringProperty();
    private final StringProperty currency = new SimpleStringProperty();
    private final StringProperty applicationFee = new SimpleStringProperty();
    private final StringProperty annualFee = new SimpleStringProperty();
    private final StringProperty semesterFee = new SimpleStringProperty();
    private final StringProperty otherFee = new SimpleStringProperty();

    public String getRowId() {
        return rowId.get();
    }

    public void setRowId(String value) {
        rowId.set(value);
    }

    public StringProperty rowIdProperty() {
        return rowId;
    }

    public String getOtherFee() {
        return otherFee.get();
    }

    public void setOtherFee(String value) {
        otherFee.set(value);
    }

    public StringProperty otherFeeProperty() {
        return otherFee;
    }

    public String getSemesterFee() {
        return semesterFee.get();
    }

    public void setSemesterFee(String value) {
        semesterFee.set(value);
    }

    public StringProperty semesterFeeProperty() {
        return semesterFee;
    }

    public String getAnnualFee() {
        return annualFee.get();
    }

    public void setAnnualFee(String value) {
        annualFee.set(value);
    }

    public StringProperty annualFeeProperty() {
        return annualFee;
    }

    public String getApplicationFee() {
        return applicationFee.get();
    }

    public void setApplicationFee(String value) {
        applicationFee.set(value);
    }

    public StringProperty applicationFeeProperty() {
        return applicationFee;
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

    public String getCourseId() {
        return courseId.get();
    }

    public void setCourseId(String value) {
        courseId.set(value);
    }

    public StringProperty courseIdProperty() {
        return courseId;
    }

}
