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
package com.zs.ina.admin.master.technicalskills.dao;

import javafx.collections.ObservableList;

/**
 *
 * @author 100018
 */
public interface TechnicalSkillsMasterDAO {

 //   public static ObservableList<TechnicalBean> getTechnicalSkillDetails();

    /**
     *
     * @param technicalSkill
     * @return
     */

    public int insertTechnicalSkills(String technicalSkill);

    /**
     *
     * @param id
     * @return
     */
    public int deleteTechnicalSkills(String id);

    /**
     *
     * @param id
     * @param technicalSkill
     * @return
     */
    public int updateTechnicalSkills(String id,String technicalSkill);

    /**
     *
     * @return
     */
    public ObservableList<TechnicalBean> getTechnicalSkills();

    /**
     *
     * @return
     */
    public ObservableList retrieveTechnicalSkills();

}
