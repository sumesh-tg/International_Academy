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
package com.zs.ina.admin.master.otherskills.dao;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author 100017
 */
public class OtherSkills {
    private final StringProperty otherSkillsId = new SimpleStringProperty();
    private final StringProperty skill = new SimpleStringProperty();
    private final StringProperty type = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getType() {
        return type.get();
    }

    /**
     *
     * @param value
     */
    public void setType(String value) {
        type.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty typeProperty() {
        return type;
    }

    /**
     *
     * @return
     */
    public String getSkill() {
        return skill.get();
    }

    /**
     *
     * @param value
     */
    public void setSkill(String value) {
        skill.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty skillProperty() {
        return skill;
    }
    
    /**
     *
     * @return
     */
    public String getOtherSkillsId() {
        return otherSkillsId.get();
    }

    /**
     *
     * @param value
     */
    public void setOtherSkillsId(String value) {
        otherSkillsId.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty otherSkillsIdProperty() {
        return otherSkillsId;
    }
    
}