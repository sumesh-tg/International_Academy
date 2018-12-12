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
public class SpouseDetailsBEAN {

    private final StringProperty rowId = new SimpleStringProperty();
    private final StringProperty enquiryId = new SimpleStringProperty();
    private final StringProperty spouseName = new SimpleStringProperty();
    private final StringProperty spouseAge = new SimpleStringProperty();
    private final StringProperty plustwoStatus = new SimpleStringProperty();
    private final StringProperty sslcBoard = new SimpleStringProperty();
    private final StringProperty sslcMedium = new SimpleStringProperty();
    private final StringProperty plusTwoBoard = new SimpleStringProperty();
    private final StringProperty plusTwoField = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getPlusTwoField() {
        return plusTwoField.get();
    }

    /**
     *
     * @param value
     */
    public void setPlusTwoField(String value) {
        plusTwoField.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty plusTwoFieldProperty() {
        return plusTwoField;
    }

    /**
     *
     * @return
     */
    public String getPlusTwoBoard() {
        return plusTwoBoard.get();
    }

    /**
     *
     * @param value
     */
    public void setPlusTwoBoard(String value) {
        plusTwoBoard.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty plusTwoBoardProperty() {
        return plusTwoBoard;
    }

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
    public String getPlustwoStatus() {
        return plustwoStatus.get();
    }

    /**
     *
     * @param value
     */
    public void setPlustwoStatus(String value) {
        plustwoStatus.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty plustwoStatusProperty() {
        return plustwoStatus;
    }

    /**
     *
     * @return
     */
    public String getSpouseAge() {
        return spouseAge.get();
    }

    /**
     *
     * @param value
     */
    public void setSpouseAge(String value) {
        spouseAge.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty spouseAgeProperty() {
        return spouseAge;
    }

    /**
     *
     * @return
     */
    public String getSpouseName() {
        return spouseName.get();
    }

    /**
     *
     * @param value
     */
    public void setSpouseName(String value) {
        spouseName.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty spouseNameProperty() {
        return spouseName;
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

    @Override
    public String toString() {
        return "AssesmentSpouseDetailsBEAN{" + "rowId=" + rowId + ", enquiryId=" + enquiryId + ", spouseName=" + spouseName + ", spouseAge=" + spouseAge + ", plustwoStatus=" + plustwoStatus + ", sslcBoard=" + sslcBoard + ", sslcMedium=" + sslcMedium + ", plusTwoBoard=" + plusTwoBoard + ", plusTwoField=" + plusTwoField + '}';
    }
    
    
    
}
