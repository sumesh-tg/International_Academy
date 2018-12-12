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
public class LanguageTestBEAN {

    private final StringProperty rowId = new SimpleStringProperty();
    private final StringProperty vacancyId = new SimpleStringProperty();
    private final StringProperty languageTest = new SimpleStringProperty();
    private final StringProperty reading = new SimpleStringProperty();
    private final StringProperty listening = new SimpleStringProperty();
    private final StringProperty writing = new SimpleStringProperty();
    private final StringProperty speaking = new SimpleStringProperty();
    private final StringProperty overall = new SimpleStringProperty();
    private final StringProperty courseId = new SimpleStringProperty();
    private final StringProperty createdUser = new SimpleStringProperty();
    private final StringProperty updatedUser = new SimpleStringProperty();
    private final StringProperty createdDate = new SimpleStringProperty();
    private final StringProperty updatedDate = new SimpleStringProperty();
    private final StringProperty trainingId = new SimpleStringProperty();

    public String getTrainingId() {
        return trainingId.get();
    }

    public void setTrainingId(String value) {
        trainingId.set(value);
    }

    public StringProperty trainingIdProperty() {
        return trainingId;
    }
    

    public String getUpdatedDate() {
        return updatedDate.get();
    }

    public void setUpdatedDate(String value) {
        updatedDate.set(value);
    }

    public StringProperty updatedDateProperty() {
        return updatedDate;
    }

    public String getCreatedDate() {
        return createdDate.get();
    }

    public void setCreatedDate(String value) {
        createdDate.set(value);
    }

    public StringProperty createdDateProperty() {
        return createdDate;
    }

    public String getUpdatedUser() {
        return updatedUser.get();
    }

    public void setUpdatedUser(String value) {
        updatedUser.set(value);
    }

    public StringProperty updatedUserProperty() {
        return updatedUser;
    }

    public String getCreatedUser() {
        return createdUser.get();
    }

    public void setCreatedUser(String value) {
        createdUser.set(value);
    }

    public StringProperty createdUserProperty() {
        return createdUser;
    }

    public String getOverall() {
        return overall.get();
    }

    public void setOverall(String value) {
        overall.set(value);
    }

    public StringProperty overallProperty() {
        return overall;
    }

    public String getCourseId() {
        return courseId.get();
    }

    public void setCourseId(String value) {
        courseId.set(value);
    }

    public StringProperty courseIdProperty() {
        return courseId;
    }

    public String getSpeaking() {
        return speaking.get();
    }

    public void setSpeaking(String value) {
        speaking.set(value);
    }

    public StringProperty speakingProperty() {
        return speaking;
    }

    public String getWriting() {
        return writing.get();
    }

    public void setWriting(String value) {
        writing.set(value);
    }

    public StringProperty writingProperty() {
        return writing;
    }

    public String getListening() {
        return listening.get();
    }

    public void setListening(String value) {
        listening.set(value);
    }

    public StringProperty listeningProperty() {
        return listening;
    }

    public String getReading() {
        return reading.get();
    }

    public void setReading(String value) {
        reading.set(value);
    }

    public StringProperty readingProperty() {
        return reading;
    }

    public String getLanguageTest() {
        return languageTest.get();
    }

    public void setLanguageTest(String value) {
        languageTest.set(value);
    }

    public StringProperty languageTestProperty() {
        return languageTest;
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
        return "LanguageTestBEAN{" + "rowId=" + rowId + ", vacancyId=" + vacancyId + ", languageTest=" + languageTest + ", reading=" + reading + ", listening=" + listening + ", writing=" + writing + ", speaking=" + speaking + '}';
    }

}
