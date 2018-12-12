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
package com.zs.ina.admin.master.payment.dao;

import java.util.Objects;

/**
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class MandatoryBEAN {
   String headId,headName,enable,mandatoryId,appStatusId,isMandatoryHeadId ;

    /**
     *
     * @return
     */
    public String getHeadId() {
        return headId;
    }

    /**
     *
     * @param headId
     */
    public void setHeadId(String headId) {
        this.headId = headId;
    }

    /**
     *
     * @return
     */
    public String getHeadName() {
        return headName;
    }

    /**
     *
     * @param headName
     */
    public void setHeadName(String headName) {
        this.headName = headName;
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
     * @return
     */
    public String getMandatoryId() {
        return mandatoryId;
    }

    /**
     *
     * @param mandatoryId
     */
    public void setMandatoryId(String mandatoryId) {
        this.mandatoryId = mandatoryId;
    }

    /**
     *
     * @return
     */
    public String getAppStatusId() {
        return appStatusId;
    }

    /**
     *
     * @param appStatusId
     */
    public void setAppStatusId(String appStatusId) {
        this.appStatusId = appStatusId;
    }

    /**
     *
     * @return
     */
    public String getIsMandatoryHeadId() {
        return isMandatoryHeadId;
    }

    /**
     *
     * @param isMandatoryHeadId
     */
    public void setIsMandatoryHeadId(String isMandatoryHeadId) {
        this.isMandatoryHeadId = isMandatoryHeadId;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.headId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MandatoryBEAN other = (MandatoryBEAN) obj;
        if (!Objects.equals(this.headId, other.headId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MandatoryBEAN{" + "headId=" + headId + ", headName=" + headName + ", enable=" + enable + ", mandatoryId=" + mandatoryId + ", appStatusId=" + appStatusId + ", isMandatoryHeadId=" + isMandatoryHeadId + '}';
    }
   
}
