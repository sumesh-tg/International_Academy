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
package com.zs.ina.admin.master.admissiontest.dao;

import javafx.collections.ObservableList;

/**
 *
 * @author 100018
 */
public interface AdmissionTestMasterDAO {

    /**
     *
     * @param test
     * @return
     */
    public int insertAdmissionTest(String test);

    /**
     *
     * @return
     */
    public ObservableList retrieveAdmissionTest();

    /**
     *
     * @param test
     * @return
     */
    public int insertMasterAdmissionTest(String test);

    /**
     *
     * @param admissionTest
     * @return
     */
    public int updatemasterAdmissionTest(AdmissionTestBEAN admissionTest);

    /**
     *
     * @return
     */
    public ObservableList<AdmissionTestBEAN> getAdmissionTest();

    /**
     *
     * @param admissionTestId
     * @return
     */
    public int deleteMasterAdmissionTest(String admissionTestId);

}
