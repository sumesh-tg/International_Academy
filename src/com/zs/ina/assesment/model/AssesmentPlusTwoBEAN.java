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
public class AssesmentPlusTwoBEAN {

    private final StringProperty rowId = new SimpleStringProperty();
    private final StringProperty enquiryId = new SimpleStringProperty();
    private final StringProperty plusTwoBoard = new SimpleStringProperty();
    private final StringProperty plusTwoMedium = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getPlusTwoMedium() {
        return plusTwoMedium.get();
    }

    /**
     *
     * @param value
     */
    public void setPlusTwoMedium(String value) {
        plusTwoMedium.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty plusTwoMediumProperty() {
        return plusTwoMedium;
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
