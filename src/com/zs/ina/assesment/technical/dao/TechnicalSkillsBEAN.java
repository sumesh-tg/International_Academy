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
package com.zs.ina.assesment.technical.dao;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author 100018
 */
public class TechnicalSkillsBEAN {
    private final StringProperty rowId = new SimpleStringProperty();
    private final StringProperty enquiryId = new SimpleStringProperty();
    private final StringProperty technology = new SimpleStringProperty();
    private final StringProperty knowledgeLevel = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getKnowledgeLevel() {
        return knowledgeLevel.get();
    }

    /**
     *
     * @param value
     */
    public void setKnowledgeLevel(String value) {
        knowledgeLevel.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty knowledgeLevelProperty() {
        return knowledgeLevel;
    }

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
    public String getEnquiryId() {
        return enquiryId.get();
    }

    /**
     *
     * @param value
     */
    public void setEnquiryId(String value) {
        enquiryId.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty enquiryIdProperty() {
        return enquiryId;
    }

    /**
     *
     * @return
     */
    public String getRowId() {
        return rowId.get();
    }

    /**
     *
     * @param value
     */
    public void setRowId(String value) {
        rowId.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty rowIdProperty() {
        return rowId;
    }

    @Override
    public String toString() {
        return "TechnicalSkillsBEAN{" + "rowId=" + rowId + ", enquiryId=" + enquiryId + ", technology=" + technology + ", knowledgeLevel=" + knowledgeLevel + '}';
    }

}
