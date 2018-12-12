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
public class AssesmentQualificationBEAN {
    private final StringProperty qualificationLevel = new SimpleStringProperty();
    private final StringProperty qualificationField = new SimpleStringProperty();
    private final StringProperty qualificationDuration = new SimpleStringProperty();
    private final StringProperty qualificationstatus = new SimpleStringProperty();
    private final StringProperty qualifictionMark = new SimpleStringProperty();
    private final StringProperty rowId = new SimpleStringProperty();
    private final StringProperty enquiryId = new SimpleStringProperty();
    private final StringProperty rowCount = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getRowCount() {
        return rowCount.get();
    }

    /**
     *
     * @param value
     */
    public void setRowCount(String value) {
        rowCount.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty rowCountProperty() {
        return rowCount;
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
    
    /**
     *
     * @return
     */
    public String getQualifictionMark() {
        return qualifictionMark.get();
    }

    /**
     *
     * @param value
     */
    public void setQualifictionMark(String value) {
        qualifictionMark.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty qualifictionMarkProperty() {
        return qualifictionMark;
    }

    /**
     *
     * @return
     */
    public String getQualificationstatus() {
        return qualificationstatus.get();
    }

    /**
     *
     * @param value
     */
    public void setQualificationstatus(String value) {
        qualificationstatus.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty qualificationstatusProperty() {
        return qualificationstatus;
    }

    /**
     *
     * @return
     */
    public String getQualificationDuration() {
        return qualificationDuration.get();
    }

    /**
     *
     * @param value
     */
    public void setQualificationDuration(String value) {
        qualificationDuration.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty qualificationDurationProperty() {
        return qualificationDuration;
    }

    /**
     *
     * @return
     */
    public String getQualificationField() {
        return qualificationField.get();
    }

    /**
     *
     * @param value
     */
    public void setQualificationField(String value) {
        qualificationField.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty qualificationFieldProperty() {
        return qualificationField;
    }

    /**
     *
     * @return
     */
    public String getQualificationLevel() {
        return qualificationLevel.get();
    }

    /**
     *
     * @param value
     */
    public void setQualificationLevel(String value) {
        qualificationLevel.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty qualificationLevelProperty() {
        return qualificationLevel;
    }

    @Override
    public String toString() {
        return "AssesmentQualificationBEAN{" + "qualificationLevel=" + qualificationLevel + ", qualificationField=" + qualificationField + ", qualificationDuration=" + qualificationDuration + ", qualificationstatus=" + qualificationstatus + ", qualifictionMark=" + qualifictionMark + ", rowId=" + rowId + ", enquiryId=" + enquiryId + '}';
    }


    
}
