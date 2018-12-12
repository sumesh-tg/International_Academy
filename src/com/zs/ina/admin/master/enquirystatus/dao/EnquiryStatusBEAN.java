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
package com.zs.ina.admin.master.enquirystatus.dao;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author 100035
 */
public class EnquiryStatusBEAN {

    private final StringProperty appStatusId = new SimpleStringProperty();
    private final StringProperty appStatus = new SimpleStringProperty();
    private final StringProperty dateReasonEnable = new SimpleStringProperty();
    private final StringProperty fromMail = new SimpleStringProperty();
    private final StringProperty emailSubject = new SimpleStringProperty();
    private final StringProperty emailBody = new SimpleStringProperty();
    private final StringProperty enable = new SimpleStringProperty();
    private final StringProperty processCompletion = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getProcessCompletion() {
        return processCompletion.get();
    }

    /**
     *
     * @param value
     */
    public void setProcessCompletion(String value) {
        processCompletion.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty processCompletionProperty() {
        return processCompletion;
    }

    /**
     *
     * @return
     */
    public String getFromMail() {
        return fromMail.get();
    }

    /**
     *
     * @param value
     */
    public void setFromMail(String value) {
        fromMail.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty fromMailProperty() {
        return fromMail;
    }

    /**
     *
     * @return
     */
    public String getEmailSubject() {
        return emailSubject.get();
    }

    /**
     *
     * @param value
     */
    public void setEmailSubject(String value) {
        emailSubject.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty emailSubjectProperty() {
        return emailSubject;
    }

    /**
     *
     * @return
     */
    public String getEnable() {
        return enable.get();
    }

    /**
     *
     * @param value
     */
    public void setEnable(String value) {
        enable.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty enableProperty() {
        return enable;
    }

    /**
     *
     * @return
     */
    public String getEmailBody() {
        return emailBody.get();
    }

    /**
     *
     * @param value
     */
    public void setEmailBody(String value) {
        emailBody.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty emailBodyProperty() {
        return emailBody;
    }

    /**
     *
     * @return
     */
    public String getDateReasonEnable() {
        return dateReasonEnable.get();
    }

    /**
     *
     * @param value
     */
    public void setDateReasonEnable(String value) {
        dateReasonEnable.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty dateReasonEnableProperty() {
        return dateReasonEnable;
    }

    /**
     *
     * @return
     */
    public String getAppStatus() {
        return appStatus.get();
    }

    /**
     *
     * @param value
     */
    public void setAppStatus(String value) {
        appStatus.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty appStatusProperty() {
        return appStatus;
    }

    /**
     *
     * @return
     */
    public String getAppStatusId() {
        return appStatusId.get();
    }

    /**
     *
     * @param value
     */
    public void setAppStatusId(String value) {
        appStatusId.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty appStatusIdProperty() {
        return appStatusId;
    }

    @Override
    public String toString() {
        return  appStatus.get();
    }

}
