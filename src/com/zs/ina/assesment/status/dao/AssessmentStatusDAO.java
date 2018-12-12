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
package com.zs.ina.assesment.status.dao;

import com.zs.ina.assesment.model.AssessmentStatusBEAN;

/**
 *
 * @author 100018
 */
public interface AssessmentStatusDAO {

    /**
     *
     * @param assessmentStatusBEAN
     * @return
     */
    public int insertAssessmentStatus(AssessmentStatusBEAN assessmentStatusBEAN);

    /**
     *
     * @param assessmentStatusBEAN
     * @return
     */
    public int updateAssessmentStatus(AssessmentStatusBEAN assessmentStatusBEAN);

    /**
     *
     * @param enquiryID
     * @return
     */
    public int deleteAssessmentStatus(String enquiryID);

    /**
     *
     * @param enquiryId
     * @return
     */
    public AssessmentStatusBEAN retrieveAssessmentStatus(String enquiryId);
}
