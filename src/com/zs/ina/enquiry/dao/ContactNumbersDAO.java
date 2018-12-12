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

import com.zs.ina.counselor.dao.model.CounselorDetailsBEAN;
import java.util.List;

/**
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public interface ContactNumbersDAO {

    /**
     *
     * @param enquiryId
     * @return
     */
    public List<ContactNumberBEAN> retrieveAllContactNumbers(String enquiryId);

    /**
     *
     * @param contactId
     * @return
     */
    public int deleteContactNumber(String contactId);

    /**
     *
     * @param numberBEAN
     * @return
     */
    public int updateContactNumber(ContactNumberBEAN numberBEAN);

    /**
     *
     * @param numberBEAN
     * @return
     */
    public int updateContactNumber(CounselorDetailsBEAN numberBEAN);

    /**
     *
     * @param numberBEAN
     * @return
     */
    public int insertContactNumber(ContactNumberBEAN numberBEAN);

}
