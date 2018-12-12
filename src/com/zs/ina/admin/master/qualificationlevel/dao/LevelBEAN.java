/*
 * Copyright (C) 2016 100035
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
package com.zs.ina.admin.master.qualificationlevel.dao;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author 100035
 */
public class LevelBEAN {
    private final StringProperty programLevelId = new SimpleStringProperty();
    private final StringProperty programLevel = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getProgramLevel() {
        return programLevel.get();
    }

    /**
     *
     * @param value
     */
    public void setProgramLevel(String value) {
        programLevel.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty programLevelProperty() {
        return programLevel;
    }

    /**
     *
     * @return
     */
    public String getProgramLevelId() {
        return programLevelId.get();
    }

    /**
     *
     * @param value
     */
    public void setProgramLevelId(String value) {
        programLevelId.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty programLevelIdProperty() {
        return programLevelId;
    }
    
}
