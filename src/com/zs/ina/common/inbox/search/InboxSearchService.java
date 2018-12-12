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
package com.zs.ina.common.inbox.search;

import com.zs.ina.common.inbox.retrieve.InboxRetrievalDAO;
import com.zs.ina.common.inbox.retrieve.InboxRetrievalIMPL;
import com.zs.ina.counselor.dao.model.CounselorDetailsBEAN;
import com.zs.ina.counselor.dao.model.SearchQueryBEAN;
import java.util.List;
import java.util.Map;

/**
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class InboxSearchService {

    static InboxRetrievalDAO inboxRetrievalDAO = new InboxRetrievalIMPL();

    /**
     *
     * @param queryBEAN
     * @param branch
     * @param username
     * @param role
     * @param dynamic_query
     * @return
     */
    public static Map<String, List<CounselorDetailsBEAN>> getPrimarySearch(SearchQueryBEAN queryBEAN, String branch, String username, String role, String dynamic_query) {
        return inboxRetrievalDAO.getPrimarySearch(queryBEAN, branch, username, role, dynamic_query);
    }

}
