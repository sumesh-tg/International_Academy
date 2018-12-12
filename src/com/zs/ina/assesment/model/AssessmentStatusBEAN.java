/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.assesment.model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author user
 */
public class AssessmentStatusBEAN {

    private final StringProperty assessmentStatus = new SimpleStringProperty();
    private final StringProperty remarks = new SimpleStringProperty();
    private final StringProperty priority = new SimpleStringProperty();
   
//    private final StringProperty appointmentDate = new SimpleStringProperty();
//    private final StringProperty appointmentTime = new SimpleStringProperty();
    private final StringProperty branch = new SimpleStringProperty();
    private final StringProperty counselor = new SimpleStringProperty();
    private final StringProperty forwardFor = new SimpleStringProperty();
    private final StringProperty statusId = new SimpleStringProperty();
    private final StringProperty enquiryId = new SimpleStringProperty();
    private final StringProperty reference = new SimpleStringProperty();
    private final StringProperty department = new SimpleStringProperty();
    private final ObjectProperty<LocalDate> appointmentDate = new SimpleObjectProperty<>();
     private final ObjectProperty<LocalTime> appointmentTime = new SimpleObjectProperty<>();

    /**
     *
     * @return
     */
    public LocalTime getAppointmentTime() {
        return appointmentTime.get();
    }

    /**
     *
     * @param value
     */
    public void setAppointmentTime(LocalTime value) {
        appointmentTime.set(value);
    }

    /**
     *
     * @return
     */
    public ObjectProperty appointmentTimeProperty() {
        return appointmentTime;
    }

    /**
     *
     * @return
     */
    public LocalDate getAppointmentDate() {
        return appointmentDate.get();
    }

    /**
     *
     * @param value
     */
    public void setAppointmentDate(LocalDate value) {
        appointmentDate.set(value);
    }

    /**
     *
     * @return
     */
    public ObjectProperty appointmentDateProperty() {
        return appointmentDate;
    }
    
    /**
     *
     * @return
     */
    public String getDepartment() {
        return department.get();
    }

    /**
     *
     * @param value
     */
    public void setDepartment(String value) {
        department.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty departmentProperty() {
        return department;
    }

    /**
     *
     * @return
     */
    public String getReference() {
        return reference.get();
    }

    /**
     *
     * @param value
     */
    public void setReference(String value) {
        reference.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty referenceProperty() {
        return reference;
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
    public String getStatusId() {
        return statusId.get();
    }

    /**
     *
     * @param value
     */
    public void setStatusId(String value) {
        statusId.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty statusIdProperty() {
        return statusId;
    }

    /**
     *
     * @return
     */
    public String getForwardFor() {
        return forwardFor.get();
    }

    /**
     *
     * @param value
     */
    public void setForwardFor(String value) {
        forwardFor.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty forwardForProperty() {
        return forwardFor;
    }

    /**
     *
     * @return
     */
    public String getCounselor() {
        return counselor.get();
    }

    /**
     *
     * @param value
     */
    public void setCounselor(String value) {
        counselor.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty counselorProperty() {
        return counselor;
    }

    /**
     *
     * @return
     */
    public String getBranch() {
        return branch.get();
    }

    /**
     *
     * @param value
     */
    public void setBranch(String value) {
        branch.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty branchProperty() {
        return branch;
    }

//    public String getAppointmentTime() {
//        return appointmentTime.get();
//    }
//
//    public void setAppointmentTime(String value) {
//        appointmentTime.set(value);
//    }
//
//    public StringProperty appointmentTimeProperty() {
//        return appointmentTime;
//    }
//
//    public String getAppointmentDate() {
//        return appointmentDate.get();
//    }
//
//    public void setAppointmentDate(String value) {
//        appointmentDate.set(value);
//    }
//
//    public StringProperty appointmentDateProperty() {
//        return appointmentDate;
//    }

    /**
     *
     * @return
     */

    public String getPriority() {
        return priority.get();
    }

    /**
     *
     * @param value
     */
    public void setPriority(String value) {
        priority.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty priorityProperty() {
        return priority;
    }

    /**
     *
     * @return
     */
    public String getRemarks() {
        return remarks.get();
    }

    /**
     *
     * @param value
     */
    public void setRemarks(String value) {
        remarks.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty remarksProperty() {
        return remarks;
    }

    /**
     *
     * @return
     */
    public String getAssessmentStatus() {
        return assessmentStatus.get();
    }

    /**
     *
     * @param value
     */
    public void setAssessmentStatus(String value) {
        assessmentStatus.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty assessmentStatusProperty() {
        return assessmentStatus;
    }

//    @Override
//    public String toString() {
//        return "AssessmentStatusBEAN{" + "assessmentStatus=" + assessmentStatus + ", remarks=" + remarks + ", priority=" + priority + ", appointmentDate=" + appointmentDate + ", appointmentTime=" + appointmentTime + ", branch=" + branch + ", counselor=" + counselor + ", reference=" + forwardFor + ", statusId=" + statusId + '}';
//    }

}
