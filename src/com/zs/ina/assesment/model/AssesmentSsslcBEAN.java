/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.zs.ina.assesment.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author user
 */
public class AssesmentSsslcBEAN {
    private final StringProperty rowId = new SimpleStringProperty();
    private final StringProperty enquiryId = new SimpleStringProperty();
    private final StringProperty sslcBoard = new SimpleStringProperty();
    private final StringProperty sslcMedium = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getSslcMedium() {
        return sslcMedium.get();
    }

    /**
     *
     * @param value
     */
    public void setSslcMedium(String value) {
        sslcMedium.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty sslcMediumProperty() {
        return sslcMedium;
    }

    /**
     *
     * @return
     */
    public String getSslcBoard() {
        return sslcBoard.get();
    }

    /**
     *
     * @param value
     */
    public void setSslcBoard(String value) {
        sslcBoard.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty sslcBoardProperty() {
        return sslcBoard;
    }

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
    
}
