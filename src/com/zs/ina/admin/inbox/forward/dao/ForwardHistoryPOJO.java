/*
 * Copyright ZoftSolutions(C) 2015 100018
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
package com.zs.ina.admin.inbox.forward.dao;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author 100018
 */
public class ForwardHistoryPOJO {

    private final StringProperty hid = new SimpleStringProperty();
    private final StringProperty assigned_by = new SimpleStringProperty();
    private final StringProperty assigned_to = new SimpleStringProperty();
    private final StringProperty assigned_to_branch = new SimpleStringProperty();
    private final StringProperty assigned_branch = new SimpleStringProperty();
    private final StringProperty assigned_date = new SimpleStringProperty();
    private final StringProperty enquiry_id = new SimpleStringProperty();
    private final StringProperty holded_by = new SimpleStringProperty();
    private final StringProperty purpose = new SimpleStringProperty();
    private final StringProperty current_status = new SimpleStringProperty();
    private final StringProperty remarks = new SimpleStringProperty();
    private final StringProperty study_required = new SimpleStringProperty();
    private final StringProperty work_required = new SimpleStringProperty();
    private final StringProperty training_required = new SimpleStringProperty();
    private final StringProperty migration_required = new SimpleStringProperty();
    private final StringProperty completionFlag = new SimpleStringProperty();
    private final StringProperty readFlag = new SimpleStringProperty();
    private final StringProperty importantFlag = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getImportantFlag() {
        return importantFlag.get();
    }

    /**
     *
     * @param value
     */
    public void setImportantFlag(String value) {
        importantFlag.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty importantFlagProperty() {
        return importantFlag;
    }

    /**
     *
     * @return
     */
    public String getReadFlag() {
        return readFlag.get();
    }

    /**
     *
     * @param value
     */
    public void setReadFlag(String value) {
        readFlag.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty readFlagProperty() {
        return readFlag;
    }

    /**
     *
     * @return
     */
    public String getCompletionFlag() {
        return completionFlag.get();
    }

    /**
     *
     * @param value
     */
    public void setCompletionFlag(String value) {
        completionFlag.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty completionFlagProperty() {
        return completionFlag;
    }

    /**
     *
     * @return
     */
    public String getMigration_required() {
        return migration_required.get();
    }

    /**
     *
     * @param value
     */
    public void setMigration_required(String value) {
        migration_required.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty migration_requiredProperty() {
        return migration_required;
    }

    /**
     *
     * @return
     */
    public String getTraining_required() {
        return training_required.get();
    }

    /**
     *
     * @param value
     */
    public void setTraining_required(String value) {
        training_required.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty training_requiredProperty() {
        return training_required;
    }

    /**
     *
     * @return
     */
    public String getWork_required() {
        return work_required.get();
    }

    /**
     *
     * @param value
     */
    public void setWork_required(String value) {
        work_required.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty work_requiredProperty() {
        return work_required;
    }

    /**
     *
     * @return
     */
    public String getStudy_required() {
        return study_required.get();
    }

    /**
     *
     * @param value
     */
    public void setStudy_required(String value) {
        study_required.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty study_requiredProperty() {
        return study_required;
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
    public String getCurrent_status() {
        return current_status.get();
    }

    /**
     *
     * @param value
     */
    public void setCurrent_status(String value) {
        current_status.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty current_statusProperty() {
        return current_status;
    }

    /**
     *
     * @return
     */
    public String getPurpose() {
        return purpose.get();
    }

    /**
     *
     * @param value
     */
    public void setPurpose(String value) {
        purpose.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty purposeProperty() {
        return purpose;
    }

    /**
     *
     * @return
     */
    public String getHolded_by() {
        return holded_by.get();
    }

    /**
     *
     * @param value
     */
    public void setHolded_by(String value) {
        holded_by.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty holded_byProperty() {
        return holded_by;
    }

    /**
     *
     * @return
     */
    public String getEnquiry_id() {
        return enquiry_id.get();
    }

    /**
     *
     * @param value
     */
    public void setEnquiry_id(String value) {
        enquiry_id.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty enquiry_idProperty() {
        return enquiry_id;
    }

    /**
     *
     * @return
     */
    public String getAssigned_date() {
        return assigned_date.get();
    }

    /**
     *
     * @param value
     */
    public void setAssigned_date(String value) {
        assigned_date.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty assigned_dateProperty() {
        return assigned_date;
    }

    /**
     *
     * @return
     */
    public String getAssigned_branch() {
        return assigned_branch.get();
    }

    /**
     *
     * @param value
     */
    public void setAssigned_branch(String value) {
        assigned_branch.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty assigned_branchProperty() {
        return assigned_branch;
    }

    /**
     *
     * @return
     */
    public String getAssigned_to_branch() {
        return assigned_to_branch.get();
    }

    /**
     *
     * @param value
     */
    public void setAssigned_to_branch(String value) {
        assigned_to_branch.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty assigned_to_branchProperty() {
        return assigned_to_branch;
    }

    /**
     *
     * @return
     */
    public String getAssigned_to() {
        return assigned_to.get();
    }

    /**
     *
     * @param value
     */
    public void setAssigned_to(String value) {
        assigned_to.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty assigned_toProperty() {
        return assigned_to;
    }

    /**
     *
     * @return
     */
    public String getAssigned_by() {
        return assigned_by.get();
    }

    /**
     *
     * @param value
     */
    public void setAssigned_by(String value) {
        assigned_by.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty assigned_byProperty() {
        return assigned_by;
    }

    /**
     *
     * @return
     */
    public String getHid() {
        return hid.get();
    }

    /**
     *
     * @param value
     */
    public void setHid(String value) {
        hid.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty hidProperty() {
        return hid;
    }

    @Override
    public String toString() {
        return "ForwardHistoryPOJO{" + "hid=" + hid + ", assigned_by=" + assigned_by + ", assigned_to=" + assigned_to + ", assigned_to_branch=" + assigned_to_branch + ", assigned_branch=" + assigned_branch + ", assigned_date=" + assigned_date + ", enquiry_id=" + enquiry_id + ", holded_by=" + holded_by + ", purpose=" + purpose + ", current_status=" + current_status + ", remarks=" + remarks + ", study_required=" + study_required + ", work_required=" + work_required + ", training_required=" + training_required + ", migration_required=" + migration_required + '}';
    }
}
