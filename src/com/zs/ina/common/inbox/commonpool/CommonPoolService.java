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
package com.zs.ina.common.inbox.commonpool;

import com.zs.ina.counselor.dao.model.CounselorDetailsBEAN;
import java.util.Map;

/**
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class CommonPoolService {

    static CommonPoolDAO commonPoolDAO = new CommonPoolIMPL();

    /**
     *
     * @param username
     * @param branch
     * @param role
     * @param lockDetailsBEAN
     * @return
     */
    public static int lockEnquiry(String username, String branch, String role, CounselorDetailsBEAN lockDetailsBEAN) {
        return commonPoolDAO.lockEnquiry(username, branch, role, lockDetailsBEAN);
    }

    /**
     *
     * @param username
     * @param branch
     * @param role
     * @return
     */
    public static Map<String, String> retrieveCommonPoolConfig(String username, String branch, String role) {
        return commonPoolDAO.retrieveCommonPoolConfig(username, branch, role);
    }

    /**
     *
     * @param username
     * @param branch
     * @param role
     * @return
     */
    public static Map<String, String> checkFlagEnquiryStatus(String username, String branch, String role) {
        return commonPoolDAO.checkFlagEnquiryStatus(username, branch, role);
    }

    /**
     *
     * @param username
     * @param branch
     * @param enquiryId
     * @return
     */
    public static boolean verifyEnquiryLocking(String username, String branch, String enquiryId) {
        return commonPoolDAO.verifyEnquiryLocking(username, branch, enquiryId);
    }

    /**
     *
     * @param username
     * @param branch
     * @return
     */
    public static boolean updateExpiredToNotAssigned(String username, String branch) {
        return commonPoolDAO.updateExpiredToNotAssigned(username, branch);
    }

    /**
     *
     * @param username
     * @param branch
     * @return
     */
    public static boolean updateExpiredToHistoryNotAssigned(String username, String branch) {
        return commonPoolDAO.updateExpiredToHistoryNotAssigned(username, branch);
    }

    /**
     *
     * @param username
     * @param branch
     * @param role
     * @param enquiryId
     * @return
     */
    public static boolean checkExpiredEnquiry(String username, String branch, String role, String enquiryId) {
        return commonPoolDAO.checkExpiredEnquiry(username, branch, role, enquiryId);
    }
}
