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
package com.zs.ina.search.migration.dynamics;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class MigrationSkillsBEAN {
    private final StringProperty rowId = new SimpleStringProperty();
    private final StringProperty migraProviderId = new SimpleStringProperty();
    private final StringProperty educationLevel = new SimpleStringProperty();
    private final StringProperty educationField = new SimpleStringProperty();
    private final StringProperty selectPointSingle = new SimpleStringProperty();
    private final StringProperty selectPointSpouse = new SimpleStringProperty();
    private final StringProperty eligibilityPoint = new SimpleStringProperty();
    private final StringProperty createdUser = new SimpleStringProperty();
    private final StringProperty updatedUser = new SimpleStringProperty();
    private final StringProperty createdDate = new SimpleStringProperty();
    private final StringProperty updatedDate = new SimpleStringProperty();
    private final StringProperty ieltsScore = new SimpleStringProperty();
    private final StringProperty experience = new SimpleStringProperty();
    private final StringProperty age = new SimpleStringProperty();
    private final StringProperty applicationType = new SimpleStringProperty();
    private final StringProperty languagTest = new SimpleStringProperty();
    private final StringProperty readingScore = new SimpleStringProperty();
    private final StringProperty listeingScore = new SimpleStringProperty();
    private final StringProperty writingScore = new SimpleStringProperty();
    private final StringProperty speakingScore = new SimpleStringProperty();
    private final StringProperty pointType = new SimpleStringProperty();
    private final StringProperty factor = new SimpleStringProperty();
    private final StringProperty point = new SimpleStringProperty();

    public String getPoint() {
        return point.get();
    }

    public void setPoint(String value) {
        point.set(value);
    }

    public StringProperty pointProperty() {
        return point;
    }

    public String getFactor() {
        return factor.get();
    }

    public void setFactor(String value) {
        factor.set(value);
    }

    public StringProperty factorProperty() {
        return factor;
    }

    public String getSpeakingScore() {
        return speakingScore.get();
    }

    public void setSpeakingScore(String value) {
        speakingScore.set(value);
    }

    public StringProperty speakingScoreProperty() {
        return speakingScore;
    }

    public String getPointType() {
        return pointType.get();
    }

    public void setPointType(String value) {
        pointType.set(value);
    }

    public StringProperty pointTypeProperty() {
        return pointType;
    }

    public String getWritingScore() {
        return writingScore.get();
    }

    public void setWritingScore(String value) {
        writingScore.set(value);
    }

    public StringProperty writingScoreProperty() {
        return writingScore;
    }

    public String getListeingScore() {
        return listeingScore.get();
    }

    public void setListeingScore(String value) {
        listeingScore.set(value);
    }

    public StringProperty listeingScoreProperty() {
        return listeingScore;
    }

    public String getReadingScore() {
        return readingScore.get();
    }

    public void setReadingScore(String value) {
        readingScore.set(value);
    }

    public StringProperty readingScoreProperty() {
        return readingScore;
    }

    public String getLanguagTest() {
        return languagTest.get();
    }

    public void setLanguagTest(String value) {
        languagTest.set(value);
    }

    public StringProperty languagTestProperty() {
        return languagTest;
    }

    public String getApplicationType() {
        return applicationType.get();
    }

    public void setApplicationType(String value) {
        applicationType.set(value);
    }

    public StringProperty applicationTypeProperty() {
        return applicationType;
    }

    public String getAge() {
        return age.get();
    }

    public void setAge(String value) {
        age.set(value);
    }

    public StringProperty ageProperty() {
        return age;
    }

    public String getExperience() {
        return experience.get();
    }

    public void setExperience(String value) {
        experience.set(value);
    }

    public StringProperty experienceProperty() {
        return experience;
    }

    public String getIeltsScore() {
        return ieltsScore.get();
    }

    public void setIeltsScore(String value) {
        ieltsScore.set(value);
    }

    public StringProperty ieltsScoreProperty() {
        return ieltsScore;
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

    public String getEligibilityPoint() {
        return eligibilityPoint.get();
    }

    public void setEligibilityPoint(String value) {
        eligibilityPoint.set(value);
    }

    public StringProperty eligibilityPointProperty() {
        return eligibilityPoint;
    }

    public String getSelectPointSpouse() {
        return selectPointSpouse.get();
    }

    public void setSelectPointSpouse(String value) {
        selectPointSpouse.set(value);
    }

    public StringProperty selectPointSpouseProperty() {
        return selectPointSpouse;
    }

    public String getSelectPointSingle() {
        return selectPointSingle.get();
    }

    public void setSelectPointSingle(String value) {
        selectPointSingle.set(value);
    }

    public StringProperty selectPointSingleProperty() {
        return selectPointSingle;
    }

    public String getEducationField() {
        return educationField.get();
    }

    public void setEducationField(String value) {
        educationField.set(value);
    }

    public StringProperty educationFieldProperty() {
        return educationField;
    }

    public String getEducationLevel() {
        return educationLevel.get();
    }

    public void setEducationLevel(String value) {
        educationLevel.set(value);
    }

    public StringProperty educationLevelProperty() {
        return educationLevel;
    }

    public String getMigraProviderId() {
        return migraProviderId.get();
    }

    public void setMigraProviderId(String value) {
        migraProviderId.set(value);
    }

    public StringProperty migraProviderIdProperty() {
        return migraProviderId;
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
        return "EducationalSkillsBEAN{" + "rowId=" + rowId + ", migraProviderId=" + migraProviderId + ", educationLevel=" + educationLevel + ", educationField=" + educationField + ", selectPointSingle=" + selectPointSingle + ", selectPointSpouse=" + selectPointSpouse + ", eligibilityPoint=" + eligibilityPoint + ", createdUser=" + createdUser + ", updatedUser=" + updatedUser + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + '}';
    }
}
