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
package com.zs.ina.search.common.bean;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class OtherSkillBEAN {

    private final StringProperty rowId = new SimpleStringProperty();
    private final StringProperty vacancyId = new SimpleStringProperty();
    private final StringProperty otherSkill = new SimpleStringProperty();
    private final StringProperty otherType = new SimpleStringProperty();

    public String getOtherType() {
        return otherType.get();
    }

    public void setOtherType(String value) {
        otherType.set(value);
    }

    public StringProperty otherTypeProperty() {
        return otherType;
    }

    public String getOtherSkill() {
        return otherSkill.get();
    }

    public void setOtherSkill(String value) {
        otherSkill.set(value);
    }

    public StringProperty otherSkillProperty() {
        return otherSkill;
    }

    public String getVacancyId() {
        return vacancyId.get();
    }

    public void setVacancyId(String value) {
        vacancyId.set(value);
    }

    public StringProperty vacancyIdProperty() {
        return vacancyId;
    }

    public String getRowId() {
        return rowId.get();
    }

    public void setRowId(String value) {
        rowId.set(value);
    }

    public StringProperty rowIdProperty() {
        return rowId;
    }

    @Override
    public String toString() {
        return "OtherSkillBEAN{" + "rowId=" + rowId + ", vacancyId=" + vacancyId + ", otherSkill=" + otherSkill + ", otherType=" + otherType + '}';
    }
}
