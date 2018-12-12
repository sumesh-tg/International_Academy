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
package com.zs.ina.common.inbox.retrieve;

/**
 *
 * @author 100018
 */
public class InboxCountsBEAN {

    String status,
            StatusId,
            TotalCount,
            study,
            Work,
            Migrat,
            Training,
            branch, concatCount, tableDiff, contactName;

    /**
     *
     * @param contactName
     */
    public InboxCountsBEAN(String contactName) {
        this.contactName = contactName;
    }

    /**
     *
     */
    public InboxCountsBEAN() {
    }

    /**
     *
     * @return
     */
    public String getContactName() {
        return contactName;
    }

    /**
     *
     * @param contactName
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     *
     * @return
     */
    public String getTableDiff() {
        return tableDiff;
    }

    /**
     *
     * @param tableDiff
     */
    public void setTableDiff(String tableDiff) {
        this.tableDiff = tableDiff;
    }

    /**
     *
     * @return
     */
    public String getConcatCount() {
        return concatCount;
    }

    /**
     *
     * @param concatCount
     */
    public void setConcatCount(String concatCount) {
        this.concatCount = concatCount;
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

    /**
     *
     * @return
     */
    public String getStatusId() {
        return StatusId;
    }

    /**
     *
     * @param StatusId
     */
    public void setStatusId(String StatusId) {
        this.StatusId = StatusId;
    }

    /**
     *
     * @return
     */
    public String getTotalCount() {
        return TotalCount;
    }

    /**
     *
     * @param TotalCount
     */
    public void setTotalCount(String TotalCount) {
        this.TotalCount = TotalCount;
    }

    /**
     *
     * @return
     */
    public String getStudy() {
        return study;
    }

    /**
     *
     * @param study
     */
    public void setStudy(String study) {
        this.study = study;
    }

    /**
     *
     * @return
     */
    public String getWork() {
        return Work;
    }

    /**
     *
     * @param Work
     */
    public void setWork(String Work) {
        this.Work = Work;
    }

    /**
     *
     * @return
     */
    public String getMigrat() {
        return Migrat;
    }

    /**
     *
     * @param Migrat
     */
    public void setMigrat(String Migrat) {
        this.Migrat = Migrat;
    }

    /**
     *
     * @return
     */
    public String getTraining() {
        return Training;
    }

    /**
     *
     * @param Training
     */
    public void setTraining(String Training) {
        this.Training = Training;
    }

    /**
     *
     * @return
     */
    public String getBranch() {
        return branch;
    }

    /**
     *
     * @param branch
     */
    public void setBranch(String branch) {
        this.branch = branch;
    }

    @Override
    public String toString() {
        return "InboxCountsBEAN{" + "status=" + status + ", StatusId=" + StatusId + ", TotalCount=" + TotalCount + ", study=" + study + ", Work=" + Work + ", Migrat=" + Migrat + ", Training=" + Training + ", branch=" + branch + '}';
    }
}
