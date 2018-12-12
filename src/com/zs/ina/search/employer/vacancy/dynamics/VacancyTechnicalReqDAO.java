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
package com.zs.ina.search.employer.vacancy.dynamics;

import com.zs.ina.search.common.bean.TechnicalSkillBEAN;
import javafx.collections.ObservableList;

/**
 * Vacancy technical Requirements
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public interface VacancyTechnicalReqDAO {

    /**
     *
     * @param technicalSkillBEAN
     * @return updation status
     */
    public boolean insertTechnicalRequirements(TechnicalSkillBEAN technicalSkillBEAN);

    /**
     *
     * @param rowId
     * @return deletion status
     */
    public boolean delteteTechnicalRequirements(String rowId);

    /**
     *
     * @param technicalSkillBEAN
     * @return status whether update or not
     */
    public boolean updateTechnicalRequirements(TechnicalSkillBEAN technicalSkillBEAN);

    /**
     *
     * @param technicalSkillBEAN
     * @return status whether insert or not
     */
    public boolean checkExistsTechnicalRequirements(TechnicalSkillBEAN technicalSkillBEAN);

    /**
     *
     * @param vacancyId
     * @return LIst of technical requirements
     */
    public ObservableList<TechnicalSkillBEAN> retrieveTechnicalRequirements(String vacancyId);
}
