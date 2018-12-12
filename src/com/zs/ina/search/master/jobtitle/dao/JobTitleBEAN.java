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
package com.zs.ina.search.master.jobtitle.dao;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class JobTitleBEAN {

    private final StringProperty id = new SimpleStringProperty();
    private final StringProperty jobTitle = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getJobTitle() {
        return jobTitle.get();
    }

    /**
     *
     * @param value
     */
    public void setJobTitle(String value) {
        jobTitle.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty jobTitleProperty() {
        return jobTitle;
    }

    /**
     *
     * @return
     */
    public String getId() {
        return id.get();
    }

    /**
     *
     * @param value
     */
    public void setId(String value) {
        id.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty idProperty() {
        return id;
    }
    /* ======================== Used For Auto Completion ==================== */

    @Override
    public String toString() {
        return  ""+jobTitle.get() ;
    }
    
}
