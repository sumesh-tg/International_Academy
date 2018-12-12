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

import com.zs.ina.search.common.bean.CoursesBEAN;
import javafx.collections.ObservableList;

/**
 * Employer Academic Requirements DAO
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public interface VacancyAcademicReqDAO {

    /**
     *
     * @param coursesBEAN
     * @return insertion status
     */
    public boolean insertAcademicRequirements(CoursesBEAN coursesBEAN);

    /**
     *
     * @param rowId
     * @return deletion status
     */
    public boolean deleteAcademicRequirements(String rowId);

    /**
     *
     * @param coursesBEAN
     * @return updation status
     */
    public boolean updateAcademicRequirements(CoursesBEAN coursesBEAN);

    /**
     *
     * @param vacancyId
     * @return List of academic requirements
     */
    public ObservableList<CoursesBEAN> retrieveAcademicRequirements(String vacancyId);
}
