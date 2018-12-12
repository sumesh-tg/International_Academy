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
package com.zs.ina.enquiry.dao;

import java.util.Objects;

/**
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class PhoneSearchPOJO {

    String enquiryId ;
    String stdCode1 ;
    String stdCode2 ;
    String contactNumber1 ;
    String contactNumber2 ;

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
    public String getStdCode1() {
        return stdCode1;
    }

    /**
     *
     * @param stdCode1
     */
    public void setStdCode1(String stdCode1) {
        this.stdCode1 = stdCode1;
    }

    /**
     *
     * @return
     */
    public String getStdCode2() {
        return stdCode2;
    }

    /**
     *
     * @param stdCode2
     */
    public void setStdCode2(String stdCode2) {
        this.stdCode2 = stdCode2;
    }

    /**
     *
     * @return
     */
    public String getContactNumber1() {
        return contactNumber1;
    }

    /**
     *
     * @param contactNumber1
     */
    public void setContactNumber1(String contactNumber1) {
        this.contactNumber1 = contactNumber1;
    }

    /**
     *
     * @return
     */
    public String getContactNumber2() {
        return contactNumber2;
    }

    /**
     *
     * @param contactNumber2
     */
    public void setContactNumber2(String contactNumber2) {
        this.contactNumber2 = contactNumber2;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final PhoneSearchPOJO other = (PhoneSearchPOJO) obj;
        if (!Objects.equals(this.contactNumber1, other.contactNumber1)) {
            return false;
        }
        if (!Objects.equals(this.contactNumber2, other.contactNumber2)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return contactNumber1;
    }
    
}
