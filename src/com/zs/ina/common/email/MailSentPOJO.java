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
package com.zs.ina.common.email;

/**
 *
 * @author 100018
 */
public class MailSentPOJO {

    String content, subject, to, cc,from, emailLogId, enquiryId, emailHead, emailsubhead,sendDate,salutation,signature,message;

    /**
     *
     * @return
     */
    public String getCc() {
        return cc;
    }

    /**
     *
     * @param cc
     */
    public void setCc(String cc) {
        this.cc = cc;
    }

    /**
     *
     * @param content
     * @param subject
     * @param to
     * @param from
     */
    public MailSentPOJO(String content, String subject, String to, String from) {
        this.content = content;
        this.subject = subject;
        this.to = to;
        this.from = from;
    }

    /**
     *
     */
    public MailSentPOJO() {
    }
    
    /**
     *
     * @return
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     *
     * @return
     */
    public String getSalutation() {
        return salutation;
    }

    /**
     *
     * @param salutation
     */
    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    /**
     *
     * @return
     */
    public String getSignature() {
        return signature;
    }

    /**
     *
     * @param signature
     */
    public void setSignature(String signature) {
        this.signature = signature;
    }

    /**
     *
     * @return
     */
    public String getEmailHead() {
        return emailHead;
    }

    /**
     *
     * @param emailHead
     */
    public void setEmailHead(String emailHead) {
        this.emailHead = emailHead;
    }

    /**
     *
     * @return
     */
    public String getEmailsubhead() {
        return emailsubhead;
    }

    /**
     *
     * @param emailsubhead
     */
    public void setEmailsubhead(String emailsubhead) {
        this.emailsubhead = emailsubhead;
    }

    /**
     *
     * @return
     */
    public String getEmailLogId() {
        return emailLogId;
    }

    /**
     *
     * @param emailLogId
     */
    public void setEmailLogId(String emailLogId) {
        this.emailLogId = emailLogId;
    }

    /**
     *
     * @return
     */
    public String getSendDate() {
        return sendDate;
    }

    /**
     *
     * @param sendDate
     */
    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    /**
     *
     * @return
     */
    public String getEnquiryId() {
        return enquiryId;
    }

    /**
     *
     * @param enquiryId
     */
    public void setEnquiryId(String enquiryId) {
        this.enquiryId = enquiryId;
    }

    /**
     *
     * @return
     */
    public String getFrom() {
        return from;
    }

    /**
     *
     * @param from
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     *
     * @return
     */
    public String getContent() {
        return content;
    }

    /**
     *
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     *
     * @return
     */
    public String getSubject() {
        return subject;
    }

    /**
     *
     * @param subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     *
     * @return
     */
    public String getTo() {
        return to;
    }

    /**
     *
     * @param to
     */
    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "MailSentPOJO{" + "content=" + content + ", subject=" + subject + ", to=" + to + ", cc=" + cc + ", from=" + from + ", emailLogId=" + emailLogId + ", enquiryId=" + enquiryId + ", emailHead=" + emailHead + ", emailsubhead=" + emailsubhead + ", sendDate=" + sendDate + ", salutation=" + salutation + ", signature=" + signature + ", message=" + message + '}';
    }
   
}
