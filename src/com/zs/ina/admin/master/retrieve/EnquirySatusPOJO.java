/*
 * Copyright (C) 2016 100017
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
package com.zs.ina.admin.master.retrieve;

/**
 *
 * @author 100017
 */
public class EnquirySatusPOJO {

    String id;
    String status;
    String dateReasonEnable;
    String subject, emailBody, enable, from;
    String processCompletionStatus;

    /**
     *
     * @return
     */
    public String getProcessCompletionStatus() {
        return processCompletionStatus;
    }

    /**
     *
     * @param processCompletionStatus
     */
    public void setProcessCompletionStatus(String processCompletionStatus) {
        this.processCompletionStatus = processCompletionStatus;
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
    public String getDateReasonEnable() {
        return dateReasonEnable;
    }

    /**
     *
     * @param dateReasonEnable
     */
    public void setDateReasonEnable(String dateReasonEnable) {
        this.dateReasonEnable = dateReasonEnable;
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
    public String getEmailBody() {
        return emailBody;
    }

    /**
     *
     * @param emailBody
     */
    public void setEmailBody(String emailBody) {
        this.emailBody = emailBody;
    }

    /**
     *
     * @return
     */
    public String getEnable() {
        return enable;
    }

    /**
     *
     * @param enable
     */
    public void setEnable(String enable) {
        this.enable = enable;
    }

    /**
     *
     * @param id
     * @param status
     */
    public EnquirySatusPOJO(String id, String status) {
        this.id = id;
        this.status = status;
    }

    /**
     *
     */
    public EnquirySatusPOJO() {
    }

    /**
     *
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }

    @Override
    public boolean equals(Object obj) {

        String otherTCountryCode = "";
        if (obj instanceof EnquirySatusPOJO) {
            otherTCountryCode = ((EnquirySatusPOJO) obj).id;
        } else if (obj instanceof String) {
            otherTCountryCode = (String) obj;
        } else {
            return false;
        }

        if ((this.id == null && otherTCountryCode != null) || (this.id != null && !this.id.equals(otherTCountryCode))) {
            return false;
        }
        return true;

    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

}
