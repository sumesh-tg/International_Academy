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
package com.zs.ina.admin.master.otherskills.dao;

import javafx.collections.ObservableList;

/**
 *
 * @author 100018
 */
public interface OtherSkillsMasterDAO {

    /**
     *
     * @param category
     * @param otherSkill
     * @return
     */
    public int insertOtherSkills(String category, String otherSkill);

    /**
     *
     * @param category
     * @return
     */
    public int insertOtherSkills(String category);

    /**
     *
     * @return
     */
    public ObservableList<OtherSkills> getOtherSkills();

    /**
     *
     * @return
     */
    public ObservableList retrieveOtherSkills();

    /**
     *
     * @return
     */
    public ObservableList retrieveOtherSkillsType();

    /**
     *
     * @param id
     * @return
     */
    public int deleteOtherSkill(String id);

    /**
     *
     * @return
     */
    public ObservableList<OtherSkills> retriveSkills();

    /**
     *
     * @param otherSkills
     * @return
     */
    public int updateOtherSkills(OtherSkills otherSkills);

}
