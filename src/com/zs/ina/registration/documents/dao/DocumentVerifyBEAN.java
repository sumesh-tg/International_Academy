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
package com.zs.ina.registration.documents.dao;

import java.time.LocalDate;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class DocumentVerifyBEAN {

    private final StringProperty docVerifyId = new SimpleStringProperty();
    private final StringProperty program = new SimpleStringProperty();
    private final StringProperty enquiryId = new SimpleStringProperty();
    private final StringProperty docsAssignId = new SimpleStringProperty();
    private final StringProperty documentId = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();
    private final ObjectProperty<LocalDate> followupDate = new SimpleObjectProperty<>();
    private final StringProperty remarks = new SimpleStringProperty();
    private final StringProperty documentName = new SimpleStringProperty();
    
    
    /* ======================For Notification Log ========================= */
    
    private final StringProperty mailTo = new SimpleStringProperty();
    private final StringProperty mailFrom = new SimpleStringProperty();
    private final StringProperty subject = new SimpleStringProperty();
    private final StringProperty content = new SimpleStringProperty();
    private final StringProperty purpose = new SimpleStringProperty();
    private final StringProperty logId = new SimpleStringProperty();
    private final StringProperty sendDate = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getSendDate() {
        return sendDate.get();
    }

    /**
     *
     * @param value
     */
    public void setSendDate(String value) {
        sendDate.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty sendDateProperty() {
        return sendDate;
    }
    
    /**
     *
     * @return
     */
    public String getLogId() {
        return logId.get();
    }

    /**
     *
     * @param value
     */
    public void setLogId(String value) {
        logId.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty logIdProperty() {
        return logId;
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
    public String getContent() {
        return content.get();
    }

    /**
     *
     * @param value
     */
    public void setContent(String value) {
        content.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty contentProperty() {
        return content;
    }
    
    /**
     *
     * @return
     */
    public String getSubject() {
        return subject.get();
    }

    /**
     *
     * @param value
     */
    public void setSubject(String value) {
        subject.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty subjectProperty() {
        return subject;
    }
    
    /**
     *
     * @return
     */
    public String getMailFrom() {
        return mailFrom.get();
    }

    /**
     *
     * @param value
     */
    public void setMailFrom(String value) {
        mailFrom.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty mailFromProperty() {
        return mailFrom;
    }
    
    /**
     *
     * @return
     */
    public String getMailTo() {
        return mailTo.get();
    }

    /**
     *
     * @param value
     */
    public void setMailTo(String value) {
        mailTo.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty mailToProperty() {
        return mailTo;
    }
    
    /**
     *
     * @return
     */
    public String getDocumentId() {
        return documentId.get();
    }

    /**
     *
     * @param value
     */
    public void setDocumentId(String value) {
        documentId.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty documentIdProperty() {
        return documentId;
    }

    /**
     *
     * @return
     */
    public String getDocumentName() {
        return documentName.get();
    }

    /**
     *
     * @param value
     */
    public void setDocumentName(String value) {
        documentName.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty documentNameProperty() {
        return documentName;
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
    public LocalDate getFollowupDate() {
        return followupDate.get();
    }

    /**
     *
     * @param value
     */
    public void setFollowupDate(LocalDate value) {
        followupDate.set(value);
    }

    /**
     *
     * @return
     */
    public ObjectProperty followupDateProperty() {
        return followupDate;
    }

    /**
     *
     * @return
     */
    public String getStatus() {
        return status.get();
    }

    /**
     *
     * @param value
     */
    public void setStatus(String value) {
        status.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty statusProperty() {
        return status;
    }

    /**
     *
     * @return
     */
    public String getDocsAssignId() {
        return docsAssignId.get();
    }

    /**
     *
     * @param value
     */
    public void setDocsAssignId(String value) {
        docsAssignId.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty docsAssignIdProperty() {
        return docsAssignId;
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
    public String getProgram() {
        return program.get();
    }

    /**
     *
     * @param value
     */
    public void setProgram(String value) {
        program.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty programProperty() {
        return program;
    }

    /**
     *
     * @return
     */
    public String getDocVerifyId() {
        return docVerifyId.get();
    }

    /**
     *
     * @param value
     */
    public void setDocVerifyId(String value) {
        docVerifyId.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty docVerifyIdProperty() {
        return docVerifyId;
    }

    @Override
    public String toString() {
        return "DocumentVerfiyBEAN{" + "docVerifyId=" + docVerifyId + ", program=" + program + ", enquiryId=" + enquiryId + ", docsAssignId=" + docsAssignId + ", documentId=" + documentId + ", status=" + status + ", followupDate=" + followupDate + ", remarks=" + remarks + ", documentName=" + documentName + '}';
    }

}
