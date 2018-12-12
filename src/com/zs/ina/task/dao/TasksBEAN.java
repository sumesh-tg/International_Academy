/*
 * Copyright ZoftSolutions(C) 2016 SUMESH T.G <ZoftSolutions>
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
package com.zs.ina.task.dao;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class TasksBEAN {

    private final StringProperty lineNo = new SimpleStringProperty();
    private final StringProperty taskId = new SimpleStringProperty();
    private final StringProperty remarks = new SimpleStringProperty();
    private final StringProperty createdUser = new SimpleStringProperty();
    private final StringProperty createdDate = new SimpleStringProperty();
    private final StringProperty updatedUser = new SimpleStringProperty();
    private final StringProperty updatedDate = new SimpleStringProperty();
    private final StringProperty latestFlag = new SimpleStringProperty();
    private final StringProperty ticketNo = new SimpleStringProperty();
    private final StringProperty taskName = new SimpleStringProperty();
    private final StringProperty taskDescription = new SimpleStringProperty();
    private final StringProperty assignedTo = new SimpleStringProperty();
    private final StringProperty keyword = new SimpleStringProperty();
    private final StringProperty taskStatus = new SimpleStringProperty();
    private final StringProperty assessmentSubStatus = new SimpleStringProperty();
    private final StringProperty timeAgo = new SimpleStringProperty();
    private final StringProperty createdFullname = new SimpleStringProperty();
    private final StringProperty updatedFullname = new SimpleStringProperty();
    private final StringProperty color = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getColor() {
        return color.get();
    }

    /**
     *
     * @param value
     */
    public void setColor(String value) {
        color.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty colorProperty() {
        return color;
    }

    /**
     *
     * @return
     */
    public String getUpdatedFullname() {
        return updatedFullname.get();
    }

    /**
     *
     * @param value
     */
    public void setUpdatedFullname(String value) {
        updatedFullname.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty updatedFullnameProperty() {
        return updatedFullname;
    }

    /**
     *
     * @return
     */
    public String getCreatedFullname() {
        return createdFullname.get();
    }

    /**
     *
     * @param value
     */
    public void setCreatedFullname(String value) {
        createdFullname.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty createdFullnameProperty() {
        return createdFullname;
    }

    /**
     *
     * @return
     */
    public String getTimeAgo() {
        return timeAgo.get();
    }

    /**
     *
     * @param value
     */
    public void setTimeAgo(String value) {
        timeAgo.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty timeAgoProperty() {
        return timeAgo;
    }

    /**
     *
     * @return
     */
    public String getAssessmentSubStatus() {
        return assessmentSubStatus.get();
    }

    /**
     *
     * @param value
     */
    public void setAssessmentSubStatus(String value) {
        assessmentSubStatus.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty assessmentSubStatusProperty() {
        return assessmentSubStatus;
    }

    /**
     *
     * @return
     */
    public String getTaskStatus() {
        return taskStatus.get();
    }

    /**
     *
     * @param value
     */
    public void setTaskStatus(String value) {
        taskStatus.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty taskStatusProperty() {
        return taskStatus;
    }

    /**
     *
     * @return
     */
    public String getKeyword() {
        return keyword.get();
    }

    /**
     *
     * @param value
     */
    public void setKeyword(String value) {
        keyword.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty keywordProperty() {
        return keyword;
    }

    /**
     *
     * @return
     */
    public String getAssignedTo() {
        return assignedTo.get();
    }

    /**
     *
     * @param value
     */
    public void setAssignedTo(String value) {
        assignedTo.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty assignedToProperty() {
        return assignedTo;
    }

    /**
     *
     * @return
     */
    public String getTaskDescription() {
        return taskDescription.get();
    }

    /**
     *
     * @param value
     */
    public void setTaskDescription(String value) {
        taskDescription.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty taskDescriptionProperty() {
        return taskDescription;
    }

    /**
     *
     * @return
     */
    public String getTaskName() {
        return taskName.get();
    }

    /**
     *
     * @param value
     */
    public void setTaskName(String value) {
        taskName.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty taskNameProperty() {
        return taskName;
    }

    /**
     *
     * @return
     */
    public String getTicketNo() {
        return ticketNo.get();
    }

    /**
     *
     * @param value
     */
    public void setTicketNo(String value) {
        ticketNo.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty ticketNoProperty() {
        return ticketNo;
    }

    /**
     *
     * @return
     */
    public String getLatestFlag() {
        return latestFlag.get();
    }

    /**
     *
     * @param value
     */
    public void setLatestFlag(String value) {
        latestFlag.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty latestFlagProperty() {
        return latestFlag;
    }

    /**
     *
     * @return
     */
    public String getUpdatedDate() {
        return updatedDate.get();
    }

    /**
     *
     * @param value
     */
    public void setUpdatedDate(String value) {
        updatedDate.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty updatedDateProperty() {
        return updatedDate;
    }

    /**
     *
     * @return
     */
    public String getUpdatedUser() {
        return updatedUser.get();
    }

    /**
     *
     * @param value
     */
    public void setUpdatedUser(String value) {
        updatedUser.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty updatedUserProperty() {
        return updatedUser;
    }

    /**
     *
     * @return
     */
    public String getCreatedDate() {
        return createdDate.get();
    }

    /**
     *
     * @param value
     */
    public void setCreatedDate(String value) {
        createdDate.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty createdDateProperty() {
        return createdDate;
    }

    /**
     *
     * @return
     */
    public String getCreatedUser() {
        return createdUser.get();
    }

    /**
     *
     * @param value
     */
    public void setCreatedUser(String value) {
        createdUser.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty createdUserProperty() {
        return createdUser;
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
    public String getTaskId() {
        return taskId.get();
    }

    /**
     *
     * @param value
     */
    public void setTaskId(String value) {
        taskId.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty taskIdProperty() {
        return taskId;
    }

    /**
     *
     * @return
     */
    public String getLineNo() {
        return lineNo.get();
    }

    /**
     *
     * @param value
     */
    public void setLineNo(String value) {
        lineNo.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty lineNoProperty() {
        return lineNo;
    }

    @Override
    public String toString() {
        return "TasksBEAN{" + "lineNo=" + lineNo + ", taskId=" + taskId + ", remarks=" + remarks + ", createdUser=" + createdUser + ", createdDate=" + createdDate + ", updatedUser=" + updatedUser + ", updatedDate=" + updatedDate + ", latestFlag=" + latestFlag + ", ticketNo=" + ticketNo + ", taskName=" + taskName + ", taskDescription=" + taskDescription + ", assignedTo=" + assignedTo + ", keyword=" + keyword + ", taskStatus=" + taskStatus + ", assessmentSubStatus=" + assessmentSubStatus + '}';
    }

}
