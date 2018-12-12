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
public class AssesmentTertiaryBEAN {

    private final StringProperty tertieryLevel = new SimpleStringProperty();
    private final StringProperty tertieryField = new SimpleStringProperty();
    private final StringProperty enquiryId = new SimpleStringProperty();
    private final StringProperty rowId = new SimpleStringProperty();
    private final StringProperty university = new SimpleStringProperty();
    private final StringProperty markPercentage = new SimpleStringProperty();
    private final StringProperty modeExam = new SimpleStringProperty();
    private final StringProperty examRepeatAbsent = new SimpleStringProperty();
    private final StringProperty duration = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getDuration() {
        return duration.get();
    }

    /**
     *
     * @param value
     */
    public void setDuration(String value) {
        duration.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty durationProperty() {
        return duration;
    }

    /**
     *
     * @return
     */
    public String getExamRepeatAbsent() {
        return examRepeatAbsent.get();
    }

    /**
     *
     * @param value
     */
    public void setExamRepeatAbsent(String value) {
        examRepeatAbsent.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty examRepeatAbsentProperty() {
        return examRepeatAbsent;
    }

    /**
     *
     * @return
     */
    public String getModeExam() {
        return modeExam.get();
    }

    /**
     *
     * @param value
     */
    public void setModeExam(String value) {
        modeExam.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty modeExamProperty() {
        return modeExam;
    }

    /**
     *
     * @return
     */
    public String getMarkPercentage() {
        return markPercentage.get();
    }

    /**
     *
     * @param value
     */
    public void setMarkPercentage(String value) {
        markPercentage.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty markPercentageProperty() {
        return markPercentage;
    }

    /**
     *
     * @return
     */
    public String getUniversity() {
        return university.get();
    }

    /**
     *
     * @param value
     */
    public void setUniversity(String value) {
        university.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty universityProperty() {
        return university;
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
    public String getTertieryField() {
        return tertieryField.get();
    }

    /**
     *
     * @param value
     */
    public void setTertieryField(String value) {
        tertieryField.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty tertieryFieldProperty() {
        return tertieryField;
    }

    /**
     *
     * @return
     */
    public String getTertieryLevel() {
        return tertieryLevel.get();
    }

    /**
     *
     * @param value
     */
    public void setTertieryLevel(String value) {
        tertieryLevel.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty tertieryLevelProperty() {
        return tertieryLevel;
    }

}
