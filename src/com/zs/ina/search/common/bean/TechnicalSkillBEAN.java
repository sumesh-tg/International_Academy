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
public class TechnicalSkillBEAN {

    private final StringProperty rowId = new SimpleStringProperty();
    private final StringProperty vacancyId = new SimpleStringProperty();
    private final StringProperty technology = new SimpleStringProperty();
    private final StringProperty knowledgeLevel = new SimpleStringProperty();

    public String getKnowledgeLevel() {
        return knowledgeLevel.get();
    }

    public void setKnowledgeLevel(String value) {
        knowledgeLevel.set(value);
    }

    public StringProperty knowledgeLevelProperty() {
        return knowledgeLevel;
    }

    public String getTechnology() {
        return technology.get();
    }

    public void setTechnology(String value) {
        technology.set(value);
    }

    public StringProperty technologyProperty() {
        return technology;
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
        return "TechnicalSkillBEAN{" + "rowId=" + rowId + ", vacancyId=" + vacancyId + ", technology=" + technology + ", knowledgeLevel=" + knowledgeLevel + '}';
    }

}
