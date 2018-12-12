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
import com.zs.ina.counselor.dao.model.SearchQueryBEAN;
import java.util.List;
import java.util.Map;
import javafx.collections.ObservableList;

/**
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public interface InboxRetrievalDAO {

    /**
     *
     * @param branch
     * @param username
     * @param role
     * @return
     */
    public Map<String, List<CounselorDetailsBEAN>> getPrimaryInbox(String branch, String username, String role);

    /**
     *
     * @param branch
     * @param username
     * @param role
     * @return
     */
    public Map<String, List<CounselorDetailsBEAN>> getCommonPoolEnquiries(String branch, String username, String role);

    /**
     *
     * @param branch
     * @param username
     * @param role
     * @param appStatusId
     * @return
     */
    public Map<String, List<CounselorDetailsBEAN>> retrieveAppStatusData(String branch, String username, String role, String appStatusId);

    /**
     *
     * @param username
     * @param branch
     * @param role
     * @return
     */
    public ObservableList<InboxCountsBEAN> retrieveInboxCounts(String username, String branch, String role);

    /**
     *
     * @param username
     * @param branch
     * @param role
     * @return
     */
    public ObservableList<InboxCountsBEAN> retrieveInboxCountsFollowup(String username, String branch, String role);

    /**
     *
     * @param branch
     * @param username
     * @param role
     * @param taskStatusId
     * @return
     */
    public Map<String, List<CounselorDetailsBEAN>> retrieveTaskStatusData(String branch, String username, String role, String taskStatusId);

    /**
     *
     * @param branch
     * @param username
     * @param role
     * @param pending_asesed
     * @param whichField
     * @return
     */
    public Map<String, List<CounselorDetailsBEAN>> retrieveStarredEnquiries(String branch, String username, String role, String pending_asesed, String whichField);

    /**
     *
     * @param queryBEAN
     * @param branch
     * @param username
     * @param role
     * @param dynamic_query
     * @return
     */
    public Map<String, List<CounselorDetailsBEAN>> getPrimarySearch(SearchQueryBEAN queryBEAN, String branch, String username, String role, String dynamic_query);

    /**
     *
     * @param branch
     * @param username
     * @param role
     * @param enquiryId
     * @return
     */
    public CounselorDetailsBEAN retrieveEnquiryById(String branch, String username,String role, String enquiryId);
}
