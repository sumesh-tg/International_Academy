/*
 * Copyright (C) 2016 100035
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
package com.zs.ina.enquiry.appointment.dao;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author 100035
 */
public class AppointmentScheduleBEAN {
    /*=====for appointment_master =====*/

    private final StringProperty meetingName = new SimpleStringProperty();
    private final StringProperty meetingDescription = new SimpleStringProperty();
    private final StringProperty startDateTime = new SimpleStringProperty();
    private final StringProperty endDateTime = new SimpleStringProperty();
    private final StringProperty anchor = new SimpleStringProperty();
    private final StringProperty location = new SimpleStringProperty();
    private final StringProperty meetingStatus = new SimpleStringProperty();

    /*=====common to both appointment_master and appointment_details=====*/
    private final StringProperty meetingId = new SimpleStringProperty();
    private final StringProperty createdUser = new SimpleStringProperty();
    private final StringProperty createdDate = new SimpleStringProperty();
    private final StringProperty updatedUser = new SimpleStringProperty();
    private final StringProperty updatedDate = new SimpleStringProperty();
    private final StringProperty latestFlag = new SimpleStringProperty();

    /*=====for appointment_details =====*/
    private final StringProperty appDetailsId = new SimpleStringProperty();
    private final StringProperty participantId = new SimpleStringProperty();
    private final StringProperty invitationStatus = new SimpleStringProperty();
    private final StringProperty participantStatus = new SimpleStringProperty();
    private final StringProperty remarks = new SimpleStringProperty();

    /*=====for additional purpose =====*/
    ObservableList<UserPOJO> participantsIdList = FXCollections.observableArrayList();
    private final StringProperty eventAccept = new SimpleStringProperty();
    private final StringProperty eventReject = new SimpleStringProperty();
    private final StringProperty formattedStartDate = new SimpleStringProperty();
    private final StringProperty formattedEndDate = new SimpleStringProperty();
    private final StringProperty secondsRemaining = new SimpleStringProperty();
    private final StringProperty snoozeValue = new SimpleStringProperty();
    private final StringProperty snoozeTime = new SimpleStringProperty();
    private final StringProperty eventCancel = new SimpleStringProperty();
    
    /* ====================== For Reminder ====================== */
    private final StringProperty reminderDate = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getEventCancel() {
        return eventCancel.get();
    }

    /**
     *
     * @param value
     */
    public void setEventCancel(String value) {
        eventCancel.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty eventCancelProperty() {
        return eventCancel;
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
    public String getSnoozeTime() {
        return snoozeTime.get();
    }

    /**
     *
     * @param value
     */
    public void setSnoozeTime(String value) {
        snoozeTime.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty snoozeTimeProperty() {
        return snoozeTime;
    }

    /**
     *
     * @return
     */
    public String getSnoozeValue() {
        return snoozeValue.get();
    }

    /**
     *
     * @param value
     */
    public void setSnoozeValue(String value) {
        snoozeValue.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty snoozeValueProperty() {
        return snoozeValue;
    }

    /**
     *
     * @return
     */
    public String getSecondsRemaining() {
        return secondsRemaining.get();
    }

    /**
     *
     * @param value
     */
    public void setSecondsRemaining(String value) {
        secondsRemaining.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty secondsRemainingProperty() {
        return secondsRemaining;
    }

    /**
     *
     * @return
     */
    public String getFormattedEndDate() {
        return formattedEndDate.get();
    }

    /**
     *
     * @param value
     */
    public void setFormattedEndDate(String value) {
        formattedEndDate.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty formattedEndDateProperty() {
        return formattedEndDate;
    }

    /**
     *
     * @return
     */
    public String getFormattedStartDate() {
        return formattedStartDate.get();
    }

    /**
     *
     * @param value
     */
    public void setFormattedStartDate(String value) {
        formattedStartDate.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty formattedStartDateProperty() {
        return formattedStartDate;
    }

    /**
     *
     * @return
     */
    public String getEventReject() {
        return eventReject.get();
    }

    /**
     *
     * @param value
     */
    public void setEventReject(String value) {
        eventReject.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty eventRejectProperty() {
        return eventReject;
    }

    /**
     *
     * @return
     */
    public String getEventAccept() {
        return eventAccept.get();
    }

    /**
     *
     * @param value
     */
    public void setEventAccept(String value) {
        eventAccept.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty eventAcceptProperty() {
        return eventAccept;
    }

    /**
     *
     * @return
     */
    public String getAppDetailsId() {
        return appDetailsId.get();
    }

    /**
     *
     * @param value
     */
    public void setAppDetailsId(String value) {
        appDetailsId.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty appDetailsIdProperty() {
        return appDetailsId;
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
    public ObservableList<UserPOJO> getParticipantsIdList() {
        return participantsIdList;
    }

    /**
     *
     * @param participantsIdList
     */
    public void setParticipantsIdList(ObservableList<UserPOJO> participantsIdList) {
        this.participantsIdList = participantsIdList;
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
    public String getParticipantStatus() {
        return participantStatus.get();
    }

    /**
     *
     * @param value
     */
    public void setParticipantStatus(String value) {
        participantStatus.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty participantStatusProperty() {
        return participantStatus;
    }

    /**
     *
     * @return
     */
    public String getInvitationStatus() {
        return invitationStatus.get();
    }

    /**
     *
     * @param value
     */
    public void setInvitationStatus(String value) {
        invitationStatus.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty invitationStatusProperty() {
        return invitationStatus;
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
    public String getMeetingStatus() {
        return meetingStatus.get();
    }

    /**
     *
     * @param value
     */
    public void setMeetingStatus(String value) {
        meetingStatus.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty meetingStatusProperty() {
        return meetingStatus;
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
    public String getEndDateTime() {
        return endDateTime.get();
    }

    /**
     *
     * @param value
     */
    public void setEndDateTime(String value) {
        endDateTime.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty endDateTimeProperty() {
        return endDateTime;
    }

    /**
     *
     * @return
     */
    public String getStartDateTime() {
        return startDateTime.get();
    }

    /**
     *
     * @param value
     */
    public void setStartDateTime(String value) {
        startDateTime.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty startDateTimeProperty() {
        return startDateTime;
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

    @Override
    public String toString() {
        return "AppointmentScheduleBEAN{" + "meetingName=" + meetingName + ", meetingDescription=" + meetingDescription + ", startDateTime=" + startDateTime + ", endDateTime=" + endDateTime + ", anchor=" + anchor + ", location=" + location + ", meetingStatus=" + meetingStatus + ", meetingId=" + meetingId + ", createdUser=" + createdUser + ", createdDate=" + createdDate + ", updatedUser=" + updatedUser + ", updatedDate=" + updatedDate + ", latestFlag=" + latestFlag + ", appDetailsId=" + appDetailsId + ", participantId=" + participantId + ", invitationStatus=" + invitationStatus + ", participantStatus=" + participantStatus + ", remarks=" + remarks + ", participantsIdList=" + participantsIdList + ", eventAccept=" + eventAccept + ", eventReject=" + eventReject + ", formattedStartDate=" + formattedStartDate + ", formattedEndDate=" + formattedEndDate + ", secondsRemaining=" + secondsRemaining + ", snoozeValue=" + snoozeValue + ", snoozeTime=" + snoozeTime + '}';
    }

}
