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
public class WorktExperienceBEAN {
    private final StringProperty profession = new SimpleStringProperty();
    private final StringProperty duration = new SimpleStringProperty();
    private final StringProperty rowId = new SimpleStringProperty();
    private final StringProperty enquiryId = new SimpleStringProperty();
    private final ObjectProperty<LocalDate> fromDate = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDate> toDate = new SimpleObjectProperty<>();

    /**
     *
     * @return
     */
    public LocalDate getToDate() {
        return toDate.get();
    }

    /**
     *
     * @param value
     */
    public void setToDate(LocalDate value) {
        toDate.set(value);
    }

    /**
     *
     * @return
     */
    public ObjectProperty toDateProperty() {
        return toDate;
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

    private final StringProperty employerName = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getEmployerName() {
        return employerName.get();
    }

    /**
     *
     * @param value
     */
    public void setEmployerName(String value) {
        employerName.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty employerNameProperty() {
        return employerName;
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
    public String getProfession() {
        return profession.get();
    }

    /**
     *
     * @param value
     */
    public void setProfession(String value) {
        profession.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty professionProperty() {
        return profession;
    }

    @Override
    public String toString() {
        return "AssessmentExperienceBEAN{" + "profession=" + profession + ", duration=" + duration + ", rowId=" + rowId + ", enquiryId=" + enquiryId + '}';
    }

  

}
