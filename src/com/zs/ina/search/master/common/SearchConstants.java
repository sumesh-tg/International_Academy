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
package com.zs.ina.search.master.common;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class SearchConstants {

    static ObservableList<String> listAgencyStatus = FXCollections.observableArrayList("Direct", "Tie Up");
    static ObservableList<String> listEmployerType = FXCollections.observableArrayList("Public", "Private");
    static ObservableList<String> listGender = FXCollections.observableArrayList("Male", "Female", "Both");
    static ObservableList<String> listMaritalStatus = FXCollections.observableArrayList("Married", "Single", "Divorced", "Any", "Other");
    static ObservableList<String> listApplicationType = FXCollections.observableArrayList("Single", "With Spouse", "Any", "Other");
    static ObservableList obsKnowledgeLevel = FXCollections.observableArrayList("Advanced", "Average", "Excellent", "Competitive", "Moderate");
    static ObservableList obsPointType = FXCollections.observableArrayList("Selection Point", "Eligibility Point");
    static ObservableList<String> listCourseMode = FXCollections.observableArrayList("Regular", "Distance");

    public static ObservableList<String> getListCourseMode() {
        return listCourseMode;
    }

    public static void setListCourseMode(ObservableList<String> listCourseMode) {
        SearchConstants.listCourseMode = listCourseMode;
    }

    public static ObservableList<String> getListApplicationType() {
        return listApplicationType;
    }

    public static void setListApplicationType(ObservableList<String> listApplicationStatus) {
        SearchConstants.listApplicationType = listApplicationStatus;
    }

    public static ObservableList getObsPointType() {
        return obsPointType;
    }

    public static void setObsPointType(ObservableList obsPointType) {
        SearchConstants.obsPointType = obsPointType;
    }

    public static ObservableList getObsKnowledgeLevel() {
        return obsKnowledgeLevel;
    }

    public static void setObsKnowledgeLevel(ObservableList obsKnowledgeLevel) {
        SearchConstants.obsKnowledgeLevel = obsKnowledgeLevel;
    }

    public static ObservableList<String> getListMaritalStatus() {
        return listMaritalStatus;
    }

    public static void setListMaritalStatus(ObservableList<String> listMaritalStatus) {
        SearchConstants.listMaritalStatus = listMaritalStatus;
    }

    public static ObservableList<String> getListGender() {
        return listGender;
    }

    public static void setListGender(ObservableList<String> listGender) {
        SearchConstants.listGender = listGender;
    }

    public static ObservableList<String> getListAgencyStatus() {
        return listAgencyStatus;
    }

    public static void setListAgencyStatus(ObservableList<String> listAgencyStatus) {
        SearchConstants.listAgencyStatus = listAgencyStatus;
    }

    public static ObservableList<String> getListEmployerType() {
        return listEmployerType;
    }

    public static void setListEmployerType(ObservableList<String> listEmployerType) {
        SearchConstants.listEmployerType = listEmployerType;
    }

}
