/*
 * Copyright (C) 2016 100017
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

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author 100017
 */
public class TechnicalBean {
    private final StringProperty technologyId = new SimpleStringProperty();
    private final StringProperty technology = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getTechnology() {
        return technology.get();
    }

    /**
     *
     * @param value
     */
    public void setTechnology(String value) {
        technology.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty technologyProperty() {
        return technology;
    }

    /**
     *
     * @return
     */
    public String getTechnologyId() {
        return technologyId.get();
    }

    /**
     *
     * @param value
     */
    public void setTechnologyId(String value) {
        technologyId.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty technologyIdProperty() {
        return technologyId;
    }
   
    
}
