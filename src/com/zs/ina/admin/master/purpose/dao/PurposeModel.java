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
package com.zs.ina.admin.master.purpose.dao;

/**
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class PurposeModel {

//    private final StringProperty puposeId = new SimpleStringProperty();
//    private final StringProperty purpose = new SimpleStringProperty();

    String puposeId,purpose;

    /**
     *
     * @return
     */
    public String getPuposeId() {
        return puposeId;
    }

    /**
     *
     * @param puposeId
     */
    public void setPuposeId(String puposeId) {
        this.puposeId = puposeId;
    }

    /**
     *
     * @return
     */
    public String getPurpose() {
        return purpose;
    }

    /**
     *
     * @param purpose
     */
    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
    

    @Override
    public String toString() {
        return  ""+purpose;
    }

}
