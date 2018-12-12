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
package com.zs.ina.enquiry.search.editing.dao;

import com.zs.ina.counselor.dao.model.CounselorDetailsBEAN;
import java.util.List;

/**
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public interface InlineEditingDAO {

    /**
     *
     * @param editDetailsBEAN
     * @return
     */
    public int editStudyRequired(CounselorDetailsBEAN editDetailsBEAN);

    /**
     *
     * @param editDetailsBEAN
     * @return
     */
    public int editWorkRequired(CounselorDetailsBEAN editDetailsBEAN);

    /**
     *
     * @param editDetailsBEAN
     * @return
     */
    public int editMigrationRequired(CounselorDetailsBEAN editDetailsBEAN);

    /**
     *
     * @param editDetailsBEAN
     * @return
     */
    public int editTrainingRequired(CounselorDetailsBEAN editDetailsBEAN);

    /**
     *
     * @param enquiryDetailsBEAN
     * @return
     */
    public abstract int updateEnquiryIntoDB(CounselorDetailsBEAN enquiryDetailsBEAN);

    /**
     *
     * @param editDetailsBEAN
     * @return
     */
    public int editRemarks(CounselorDetailsBEAN editDetailsBEAN);

    /**
     *
     * @param editDetailsBEAN
     * @return
     */
    public int updateStudyCountryLocaion(CounselorDetailsBEAN editDetailsBEAN);

    /**
     *
     * @param editDetailsBEAN
     * @return
     */
    public int updateWorkCountryLocaion(CounselorDetailsBEAN editDetailsBEAN);

    /**
     *
     * @param editDetailsBEAN
     * @return
     */
    public int updateMigrateCountryLocaion(CounselorDetailsBEAN editDetailsBEAN);

    /**
     *
     * @param editDetailsBEAN
     * @return
     */
    public int updateTrainingJoiningDate(CounselorDetailsBEAN editDetailsBEAN);

    /**
     *
     * @param editDetailsBEAN
     * @return
     */
    public int updateDistrictStateCountry(CounselorDetailsBEAN editDetailsBEAN);

    /**
     *
     * @param editDetailsBEAN
     * @return
     */
    public int updateAppointmentStatus(CounselorDetailsBEAN editDetailsBEAN);

    /**
     *
     * @param inboxList
     * @return
     */
    public int updateStarStatus(List<CounselorDetailsBEAN> inboxList);

    /**
     *
     * @param inboxList
     * @return
     */
    public int updateImportantFlag(List<CounselorDetailsBEAN> inboxList);

}
