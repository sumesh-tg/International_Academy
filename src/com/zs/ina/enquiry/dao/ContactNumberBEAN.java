/*
 * Copyright ZoftSolutions(C) 2016 100018
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
package com.zs.ina.enquiry.dao;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author 100018
 */
public class ContactNumberBEAN {

    private final StringProperty stdIsd1 = new SimpleStringProperty();
    private final StringProperty stdIsd2 = new SimpleStringProperty();
    private final StringProperty contactNumber1 = new SimpleStringProperty();
    private final StringProperty contactNumber2 = new SimpleStringProperty();
    private final StringProperty rowId = new SimpleStringProperty();
    private final StringProperty enquiryId = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getEnquiryId() {
        return enquiryId.get();
    }

    /**
     *
     * @param value
     */
    public void setEnquiryId(String value) {
        enquiryId.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty enquiryIdProperty() {
        return enquiryId;
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

    /**
     *
     * @return
     */
    public String getContactNumber2() {
        return contactNumber2.get();
    }

    /**
     *
     * @param value
     */
    public void setContactNumber2(String value) {
        contactNumber2.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty contactNumber2Property() {
        return contactNumber2;
    }

    /**
     *
     * @return
     */
    public String getContactNumber1() {
        return contactNumber1.get();
    }

    /**
     *
     * @param value
     */
    public void setContactNumber1(String value) {
        contactNumber1.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty contactNumber1Property() {
        return contactNumber1;
    }

    /**
     *
     * @return
     */
    public String getStdIsd2() {
        return stdIsd2.get();
    }

    /**
     *
     * @param value
     */
    public void setStdIsd2(String value) {
        stdIsd2.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty stdIsd2Property() {
        return stdIsd2;
    }

    /**
     *
     * @return
     */
    public String getStdIsd1() {
        return stdIsd1.get();
    }

    /**
     *
     * @param value
     */
    public void setStdIsd1(String value) {
        stdIsd1.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty stdIsd1Property() {
        return stdIsd1;
    }

    @Override
    public String toString() {
        return "ContactNumberBEAN{" + "stdIsd1=" + stdIsd1 + ", stdIsd2=" + stdIsd2 + ", contactNumber1=" + contactNumber1 + ", contactNumber2=" + contactNumber2 + '}';
    }
    
    
}
