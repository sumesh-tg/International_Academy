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
package com.zs.ina.common.inbox.retrieve;

import com.zs.ina.counselor.dao.model.CounselorDetailsBEAN;
import java.util.List;
import java.util.Map;
import javafx.collections.ObservableList;

/**
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class InboxRetrievalService {

    static InboxRetrievalDAO inboxRetrievalDAO = new InboxRetrievalIMPL();

    /**
     *
     * @param branch
     * @param username
     * @param role
     * @return
     */
    public static Map<String, List<CounselorDetailsBEAN>> getPrimaryInbox(String branch, String username, String role) {
        return inboxRetrievalDAO.getPrimaryInbox(branch, username, role);
    }

    /**
     *
     * @param branch
     * @param username
     * @param role
     * @return
     */
    public static Map<String, List<CounselorDetailsBEAN>> getCommonPoolEnquiries(String branch, String username, String role) {
        return inboxRetrievalDAO.getCommonPoolEnquiries(branch, username, role);
    }

    /**
     *
     * @param username
     * @param branch
     * @param role
     * @return
     */
    public static ObservableList<InboxCountsBEAN> retrieveInboxCountsFollowup(String username, String branch, String role) {
        return inboxRetrievalDAO.retrieveInboxCountsFollowup(username, branch, role);
    }

    /**
     *
     * @param branch
     * @param username
     * @param role
     * @param appStatusId
     * @return
     */
    public static Map<String, List<CounselorDetailsBEAN>> retrieveAppStatusData(String branch, String username, String role, String appStatusId) {
        return inboxRetrievalDAO.retrieveAppStatusData(branch, username, role, appStatusId);
    }

    /**
     *
     * @param username
     * @param branch
     * @param role
     * @return
     */
    public static ObservableList<InboxCountsBEAN> retrieveInboxCounts(String username, String branch, String role) {
        return inboxRetrievalDAO.retrieveInboxCounts(username, branch, role);
    }

    /**
     *
     * @param branch
     * @param username
     * @param role
     * @param taskStatusId
     * @return
     */
    public static Map<String, List<CounselorDetailsBEAN>> retrieveTaskStatusData(String branch, String username, String role, String taskStatusId) {
        return inboxRetrievalDAO.retrieveTaskStatusData(branch, username, role, taskStatusId);
    }

    /**
     *
     * @param branch
     * @param username
     * @param role
     * @param pending_asesed
     * @param whichField
     * @return
     */
    public static Map<String, List<CounselorDetailsBEAN>> retrieveStarredEnquiries(String branch, String username, String role, String pending_asesed, String whichField) {
        return inboxRetrievalDAO.retrieveStarredEnquiries(branch, username, role, pending_asesed, whichField);
    }

    /**
     *
     * @param branch
     * @param username
     * @param role
     * @param enquiryId
     * @return
     */
    public static CounselorDetailsBEAN retrieveEnquiryById(String branch, String username, String role, String enquiryId) {
        return inboxRetrievalDAO.retrieveEnquiryById(branch, username, role, enquiryId);
    }

}
