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
package com.zs.ina.admin.master.stdisdcodes.dao;

import javafx.collections.ObservableList;

/**
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class StdCodesService {

    static StdCodesDAO stdCodesDAO = new StdCodesIMPL();

    /**
     *
     * @param codesBEAN
     * @return
     */
    public static int insertStdCodes(StdCodesBEAN codesBEAN) {
        return stdCodesDAO.insertStdCodes(codesBEAN);
    }

    /**
     *
     * @param codesBEAN
     * @return
     */
    public static int updateStdCodes(StdCodesBEAN codesBEAN) {
        return stdCodesDAO.updateStdCodes(codesBEAN);
    }

    /**
     *
     * @param codesBEAN
     * @return
     */
    public static int deleteStdCodes(StdCodesBEAN codesBEAN) {
        return stdCodesDAO.deleteStdCodes(codesBEAN);
    }

    /**
     *
     * @return
     */
    public static ObservableList<StdCodesBEAN> retrieveStdCodes() {
        return stdCodesDAO.retrieveStdCodes();
    }

    /**
     *
     * @param codesBEAN
     * @return
     */
    public static int insertIsdCodes(StdCodesBEAN codesBEAN) {
        return stdCodesDAO.insertIsdCodes(codesBEAN);
    }

    /**
     *
     * @param codesBEAN
     * @return
     */
    public static int updateIsdCodes(StdCodesBEAN codesBEAN) {
        return stdCodesDAO.updateIsdCodes(codesBEAN);
    }

    /**
     *
     * @param codesBEAN
     * @return
     */
    public static int deleteIsdCodes(StdCodesBEAN codesBEAN) {
        return stdCodesDAO.deleteIsdCodes(codesBEAN);
    }

    /**
     *
     * @return
     */
    public static ObservableList<StdCodesBEAN> retrieveIsdCodes() {
        return stdCodesDAO.retrieveIsdCodes();
    }

}
