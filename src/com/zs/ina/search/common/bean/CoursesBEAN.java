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
package com.zs.ina.search.common.bean;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author 100035
 */
public class CoursesBEAN {

    private final StringProperty courseId = new SimpleStringProperty();
    private final StringProperty collegeId = new SimpleStringProperty();
    private final StringProperty universityId = new SimpleStringProperty();
    private final StringProperty level = new SimpleStringProperty();
    private final StringProperty field = new SimpleStringProperty();
    private final StringProperty duration = new SimpleStringProperty();
    private final StringProperty rowId = new SimpleStringProperty();
    private final StringProperty vacancyId = new SimpleStringProperty();
    private final StringProperty trainingId = new SimpleStringProperty();
    private final StringProperty lastUpdate = new SimpleStringProperty();
    private final StringProperty createdUser = new SimpleStringProperty();
    private final StringProperty updatedUser = new SimpleStringProperty();
    private final StringProperty createdDate = new SimpleStringProperty();
    private final StringProperty updatedDate = new SimpleStringProperty();
    private final StringProperty campusesList = new SimpleStringProperty();

    public String getCampusesList() {
        return campusesList.get();
    }

    public void setCampusesList(String value) {
        campusesList.set(value);
    }

    public StringProperty campusesListProperty() {
        return campusesList;
    }
    

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
    

    public String getLastUpdate() {
        return lastUpdate.get();
    }

    public void setLastUpdate(String value) {
        lastUpdate.set(value);
    }

    public StringProperty lastUpdateProperty() {
        return lastUpdate;
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

    public String getCourseId() {
        return courseId.get();
    }

    public void setCourseId(String value) {
        courseId.set(value);
    }

    public StringProperty courseIdProperty() {
        return courseId;
    }

    public String getUniversityId() {
        return universityId.get();
    }

    public void setUniversityId(String value) {
        universityId.set(value);
    }

    public StringProperty universityIdProperty() {
        return universityId;
    }

    public String getDuration() {
        return duration.get();
    }

    public void setDuration(String value) {
        duration.set(value);
    }

    public StringProperty durationProperty() {
        return duration;
    }

    public String getField() {
        return field.get();
    }

    public void setField(String value) {
        field.set(value);
    }

    public StringProperty fieldProperty() {
        return field;
    }

    public String getLevel() {
        return level.get();
    }

    public void setLevel(String value) {
        level.set(value);
    }

    public StringProperty levelProperty() {
        return level;
    }

    public String getCollegeId() {
        return collegeId.get();
    }

    public void setCollegeId(String value) {
        collegeId.set(value);
    }

    public StringProperty collegeIdProperty() {
        return collegeId;
    }

    @Override
    public String toString() {
        return level + " " + field;
    }

}
