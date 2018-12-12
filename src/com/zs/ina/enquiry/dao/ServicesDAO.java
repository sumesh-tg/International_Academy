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
package com.zs.ina.enquiry.dao;

import java.util.List;

/**
 *
 * @author 100018
 */
public interface ServicesDAO {

    /**
     *
     * @param enquiryId
     * @return
     */
    public List<ServicesBEAN> getAllServices(String enquiryId);

    /**
     *
     * @param enquiryId
     * @return
     */
    public boolean deleteStudy(String enquiryId);

    /**
     *
     * @param enquiryId
     * @return
     */
    public boolean deleteWork(String enquiryId);

    /**
     *
     * @param enquiryId
     * @return
     */
    public boolean deleteMigrate(String enquiryId);

    /**
     *
     * @param enquiryId
     * @return
     */
    public boolean updateStudy(String enquiryId);

    /**
     *
     * @param enquiryId
     * @return
     */
    public boolean updateWork(String enquiryId);

    /**
     *
     * @param enquiryId
     * @return
     */
    public boolean updateMigration(String enquiryId);
}
