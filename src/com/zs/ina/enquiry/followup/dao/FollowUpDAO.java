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
package com.zs.ina.enquiry.followup.dao;

import com.zs.ina.admin.master.enquirystatus.dao.EnquiryStatusBEAN;
import com.zs.ina.counselor.dao.model.CounselorDetailsBEAN;
import java.util.List;

/**
 *
 * @author 100018
 */
public interface FollowUpDAO {

    /**
     *
     * @return
     */
    public  List<EnquiryStatusBEAN> getStatusDetails();

    /**
     *
     * @param username
     * @param branch
     * @param statusId
     * @param role
     * @return
     */
    public List<CounselorDetailsBEAN> retrieveOfficeStaffFollowUpDetails(String username, String branch,String statusId,String role);

    /**
     *
     * @param username
     * @param branch
     * @return
     */
    public List<CounselorDetailsBEAN> retrieveCounselorFollowUpDetails(String username, String branch);

    /**
     *
     * @param branch
     * @return
     */
    public List<CounselorDetailsBEAN> retrieveFollowUpDetails(String branch);
    /* ======================== Used method ==================== */

    /**
     *
     * @param username
     * @param branch
     * @param role
     * @return
     */

    public List<CounselorDetailsBEAN> retrieveOfficeStaffFollowTotal(String username, String branch,String role);

    /**
     *
     * @param followUpStatus
     * @return
     */
    public EnquiryStatusBEAN getStatusDetails(String followUpStatus);

}
