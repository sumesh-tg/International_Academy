/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.assesment.plus2.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author zoft
 */
public class AssessmentPlus2BEAN {
    int flag;
    private final StringProperty board = new SimpleStringProperty();
    private final StringProperty medium = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();
    private final StringProperty mark = new SimpleStringProperty();
    private final StringProperty enquiryId = new SimpleStringProperty();
    private final StringProperty rowId = new SimpleStringProperty();

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
    public String getMark() {
        return mark.get();
    }

    /**
     *
     * @param value
     */
    public void setMark(String value) {
        mark.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty markProperty() {
        return mark;
    }

    /**
     *
     * @return
     */
    public String getStatus() {
        return status.get();
    }

    /**
     *
     * @param value
     */
    public void setStatus(String value) {
        status.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty statusProperty() {
        return status;
    }

    /**
     *
     * @return
     */
    public String getMedium() {
        return medium.get();
    }

    /**
     *
     * @param value
     */
    public void setMedium(String value) {
        medium.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty mediumProperty() {
        return medium;
    }

    /**
     *
     * @return
     */
    public String getBoard() {
        return board.get();
    }

    /**
     *
     * @param value
     */
    public void setBoard(String value) {
        board.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty boardProperty() {
        return board;
    }

    /**
     *
     * @return
     */
    public int getFlag() {
        return flag;
    }

    /**
     *
     * @param flag
     */
    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "AssessmentPlus2BEAN{" + "flag=" + flag + ", board=" + board + ", medium=" + medium + ", status=" + status + ", mark=" + mark + ", enquiryId=" + enquiryId + ", rowId=" + rowId + '}';
    }
    

}
