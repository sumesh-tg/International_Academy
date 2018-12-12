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
package com.zs.ina.enquiry.search.dao;

/**
 *
 * @author 100018
 */
public class CounselorWorkCount {
    String cname;
    String totalCount;
    String pendingCount;
    String notStartedCount;
    String cbranch;
    String assesed;

    /**
     *
     * @return
     */
    public String getAssesed() {
        return assesed;
    }

    /**
     *
     * @param assesed
     */
    public void setAssesed(String assesed) {
        this.assesed = assesed;
    }

    /**
     *
     * @return
     */
    public String getCbranch() {
        return cbranch;
    }

    /**
     *
     * @param cbranch
     */
    public void setCbranch(String cbranch) {
        this.cbranch = cbranch;
    }

    /**
     *
     * @return
     */
    public String getCname() {
        return cname;
    }

    /**
     *
     * @param cname
     */
    public void setCname(String cname) {
        this.cname = cname;
    }

    /**
     *
     * @return
     */
    public String getTotalCount() {
        return totalCount;
    }

    /**
     *
     * @param totalCount
     */
    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    /**
     *
     * @return
     */
    public String getPendingCount() {
        return pendingCount;
    }

    /**
     *
     * @param pendingCount
     */
    public void setPendingCount(String pendingCount) {
        this.pendingCount = pendingCount;
    }

    /**
     *
     * @return
     */
    public String getNotStartedCount() {
        return notStartedCount;
    }

    /**
     *
     * @param notStartedCount
     */
    public void setNotStartedCount(String notStartedCount) {
        this.notStartedCount = notStartedCount;
    }
    
}
