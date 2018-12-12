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
package com.zs.ina.assesment.otherskills.dao;

import java.util.List;

/**
 *
 * @author 100018
 */
public interface OtherSkillsDAO {

    /**
     *
     * @param otherSkillBEAN
     * @return
     */
    public int insertOtherSkills(OtherSkillBEAN otherSkillBEAN);

    /**
     *
     * @param otherSkillBEAN
     * @return
     */
    public int updateOtherSkills(OtherSkillBEAN otherSkillBEAN);

    /**
     *
     * @param rowId
     * @return
     */
    public int deleteOtherSkills(String rowId);

    /**
     *
     * @param enquiryId
     * @return
     */
    public List<OtherSkillBEAN> retrieveOtherSkills(String enquiryId);
}
