/*
 * Copyright (C) 2016 100018
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
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package com.zs.ina.enquiry.appointment.reminder.dao;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author 100018
 */
public class ReminderBEAN {
    
    
    private final StringProperty remId = new SimpleStringProperty();
    private final StringProperty meetingId = new SimpleStringProperty();
    private final StringProperty participantId = new SimpleStringProperty();
    private final StringProperty reminderDate = new SimpleStringProperty();
    private final StringProperty reminderDesc = new SimpleStringProperty();
    private final StringProperty createdUser = new SimpleStringProperty();
    private final StringProperty createdTime = new SimpleStringProperty();
    private final StringProperty readFlag = new SimpleStringProperty();
    private final StringProperty updatedUser = new SimpleStringProperty();
    private final StringProperty updatedTime = new SimpleStringProperty();
    private final StringProperty branch = new SimpleStringProperty();
    private final StringProperty meetingName = new SimpleStringProperty();
    private final StringProperty meetingDescription = new SimpleStringProperty();
    private final StringProperty location = new SimpleStringProperty();
    private final StringProperty startDate = new SimpleStringProperty();
    private final StringProperty endDate = new SimpleStringProperty();
    private final StringProperty anchor = new SimpleStringProperty();
    private final StringProperty timeRemaining = new SimpleStringProperty();
    private final StringProperty interval = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getInterval() {
        return interval.get();
    }

    /**
     *
     * @param value
     */
    public void setInterval(String value) {
        interval.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty intervalProperty() {
        return interval;
    }

    /**
     *
     * @return
     */
    public String getTimeRemaining() {
        return timeRemaining.get();
    }

    /**
     *
     * @param value
     */
    public void setTimeRemaining(String value) {
        timeRemaining.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty timeRemainingProperty() {
        return timeRemaining;
    }

    /**
     *
     * @return
     */
    public String getAnchor() {
        return anchor.get();
    }

    /**
     *
     * @param value
     */
    public void setAnchor(String value) {
        anchor.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty anchorProperty() {
        return anchor;
    }

    /**
     *
     * @return
     */
    public String getEndDate() {
        return endDate.get();
    }

    /**
     *
     * @param value
     */
    public void setEndDate(String value) {
        endDate.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty endDateProperty() {
        return endDate;
    }

    /**
     *
     * @return
     */
    public String getStartDate() {
        return startDate.get();
    }

    /**
     *
     * @param value
     */
    public void setStartDate(String value) {
        startDate.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty startDateProperty() {
        return startDate;
    }

    /**
     *
     * @return
     */
    public String getLocation() {
        return location.get();
    }

    /**
     *
     * @param value
     */
    public void setLocation(String value) {
        location.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty locationProperty() {
        return location;
    }

    /**
     *
     * @return
     */
    public String getMeetingDescription() {
        return meetingDescription.get();
    }

    /**
     *
     * @param value
     */
    public void setMeetingDescription(String value) {
        meetingDescription.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty meetingDescriptionProperty() {
        return meetingDescription;
    }

    /**
     *
     * @return
     */
    public String getMeetingName() {
        return meetingName.get();
    }

    /**
     *
     * @param value
     */
    public void setMeetingName(String value) {
        meetingName.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty meetingNameProperty() {
        return meetingName;
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

    /**
     *
     * @return
     */
    public String getUpdatedTime() {
        return updatedTime.get();
    }

    /**
     *
     * @param value
     */
    public void setUpdatedTime(String value) {
        updatedTime.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty updatedTimeProperty() {
        return updatedTime;
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
    public String getCreatedTime() {
        return createdTime.get();
    }

    /**
     *
     * @param value
     */
    public void setCreatedTime(String value) {
        createdTime.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty createdTimeProperty() {
        return createdTime;
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
    public String getReminderDesc() {
        return reminderDesc.get();
    }

    /**
     *
     * @param value
     */
    public void setReminderDesc(String value) {
        reminderDesc.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty reminderDescProperty() {
        return reminderDesc;
    }

    /**
     *
     * @return
     */
    public String getReminderDate() {
        return reminderDate.get();
    }

    /**
     *
     * @param value
     */
    public void setReminderDate(String value) {
        reminderDate.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty reminderDateProperty() {
        return reminderDate;
    }

    /**
     *
     * @return
     */
    public String getParticipantId() {
        return participantId.get();
    }

    /**
     *
     * @param value
     */
    public void setParticipantId(String value) {
        participantId.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty participantIdProperty() {
        return participantId;
    }

    /**
     *
     * @return
     */
    public String getMeetingId() {
        return meetingId.get();
    }

    /**
     *
     * @param value
     */
    public void setMeetingId(String value) {
        meetingId.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty meetingIdProperty() {
        return meetingId;
    }

    /**
     *
     * @return
     */
    public String getRemId() {
        return remId.get();
    }

    /**
     *
     * @param value
     */
    public void setRemId(String value) {
        remId.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty remIdProperty() {
        return remId;
    }

    @Override
    public String toString() {
        return "ReminderBEAN{" + "remId=" + remId + ", meetingId=" + meetingId + ", participantId=" + participantId + ", reminderDate=" + reminderDate + ", reminderDesc=" + reminderDesc + ", createdUser=" + createdUser + ", createdTime=" + createdTime + ", readFlag=" + readFlag + ", updatedUser=" + updatedUser + ", updatedTime=" + updatedTime + '}';
    }

    

}
