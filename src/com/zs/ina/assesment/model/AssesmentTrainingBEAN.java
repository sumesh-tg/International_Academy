/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.assesment.model;

import java.time.LocalDate;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author user
 */
public class AssesmentTrainingBEAN {

    private final StringProperty training = new SimpleStringProperty();
    private final StringProperty batch = new SimpleStringProperty();
    private final StringProperty timing = new SimpleStringProperty();
    private final StringProperty duration = new SimpleStringProperty();
    private final StringProperty remmarks = new SimpleStringProperty();
    private final StringProperty choice = new SimpleStringProperty();
    private final StringProperty enquiryId = new SimpleStringProperty();
    private final StringProperty rowId = new SimpleStringProperty();
    private final ObjectProperty<LocalDate> fromDate = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDate> classFromDate = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDate> classToDate = new SimpleObjectProperty<>();

    /**
     *
     * @return
     */
    public LocalDate getClassToDate() {
        return classToDate.get();
    }

    /**
     *
     * @param value
     */
    public void setClassToDate(LocalDate value) {
        classToDate.set(value);
    }

    /**
     *
     * @return
     */
    public ObjectProperty classToDateProperty() {
        return classToDate;
    }
    
    /**
     *
     * @return
     */
    public LocalDate getClassFromDate() {
        return classFromDate.get();
    }

    /**
     *
     * @param value
     */
    public void setClassFromDate(LocalDate value) {
        classFromDate.set(value);
    }

    /**
     *
     * @return
     */
    public ObjectProperty classFromDateProperty() {
        return classFromDate;
    }
   
    /**
     *
     * @return
     */
    public LocalDate getFromDate() {
        return fromDate.get();
    }

    /**
     *
     * @param value
     */
    public void setFromDate(LocalDate value) {
        fromDate.set(value);
    }

    /**
     *
     * @return
     */
    public ObjectProperty fromDateProperty() {
        return fromDate;
    }

    /**
     *
     * @return
     */

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
    public String getChoice() {
        return choice.get();
    }

    /**
     *
     * @param value
     */
    public void setChoice(String value) {
        choice.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty choiceProperty() {
        return choice;
    }

    /**
     *
     * @return
     */
    public String getRemmarks() {
        return remmarks.get();
    }

    /**
     *
     * @param value
     */
    public void setRemmarks(String value) {
        remmarks.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty remmarksProperty() {
        return remmarks;
    }


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
    public String getTiming() {
        return timing.get();
    }

    /**
     *
     * @param value
     */
    public void setTiming(String value) {
        timing.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty timingProperty() {
        return timing;
    }

    /**
     *
     * @return
     */
    public String getBatch() {
        return batch.get();
    }

    /**
     *
     * @param value
     */
    public void setBatch(String value) {
        batch.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty batchProperty() {
        return batch;
    }

    /**
     *
     * @return
     */
    public String getTraining() {
        return training.get();
    }

    /**
     *
     * @param value
     */
    public void setTraining(String value) {
        training.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty trainingProperty() {
        return training;
    }

    @Override
    public String toString() {
        return "AssesmentTrainingBEAN{" + "training=" + training + ", batch=" + batch + ", timing=" + timing + ", duration=" + duration + ", remmarks=" + remmarks + ", choice=" + choice + ", enquiryId=" + enquiryId + ", rowId=" + rowId + ", fromDate=" + fromDate + ", classFromDate=" + classFromDate + ", classToDate=" + classToDate + '}';
    }

   
}
