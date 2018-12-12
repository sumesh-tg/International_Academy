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
package com.zs.ina.assesment.langskills.dao;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author 100018
 */
public class LanguageSkillsBEAN {
    private final StringProperty rowId = new SimpleStringProperty();
    private final StringProperty enquiryId = new SimpleStringProperty();
    private final StringProperty language = new SimpleStringProperty();
    private final StringProperty proficiency = new SimpleStringProperty();
    private final StringProperty read = new SimpleStringProperty();
    private final StringProperty write = new SimpleStringProperty();
    private final StringProperty speak = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getSpeak() {
        return speak.get();
    }

    /**
     *
     * @param value
     */
    public void setSpeak(String value) {
        speak.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty speakProperty() {
        return speak;
    }

    /**
     *
     * @return
     */
    public String getWrite() {
        return write.get();
    }

    /**
     *
     * @param value
     */
    public void setWrite(String value) {
        write.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty writeProperty() {
        return write;
    }

    /**
     *
     * @return
     */
    public String getRead() {
        return read.get();
    }

    /**
     *
     * @param value
     */
    public void setRead(String value) {
        read.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty readProperty() {
        return read;
    }
    
    /**
     *
     * @return
     */
    public String getProficiency() {
        return proficiency.get();
    }

    /**
     *
     * @param value
     */
    public void setProficiency(String value) {
        proficiency.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty proficiencyProperty() {
        return proficiency;
    }

    /**
     *
     * @return
     */
    public String getLanguage() {
        return language.get();
    }

    /**
     *
     * @param value
     */
    public void setLanguage(String value) {
        language.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty languageProperty() {
        return language;
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
        return "LanguageSkillsPOJO{" + "rowId=" + rowId + ", enquiryId=" + enquiryId + ", language=" + language + ", proficiency=" + proficiency + '}';
    }
    
}
