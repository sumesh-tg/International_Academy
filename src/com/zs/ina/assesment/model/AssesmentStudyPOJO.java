/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.assesment.model;

/**
 *
 * @author user
 */
public class AssesmentStudyPOJO {

    String study, programLevel, programField, intake, university, location, choice, comments,otherUniversity,studyDetails,studyLocations,studyComments;
    int spousceAccompany, childrenAccompany;

    /**
     *
     */
    public AssesmentStudyPOJO() {
    }

    /**
     *
     * @param study
     * @param programLevel
     * @param programField
     * @param intake
     * @param university
     * @param otherUniversity
     * @param location
     * @param choice
     * @param spousceAccompany
     * @param childrenAccompany
     * @param comments
     */
    public AssesmentStudyPOJO(String study, String programLevel, String programField, String intake, String university,String otherUniversity, String location, String choice, int spousceAccompany, int childrenAccompany, String comments) {
        this.study = study;
        this.programLevel = programLevel;
        this.programField = programField;
        this.intake = intake;
        this.university = university;
        this.location = location;
        this.choice = choice;
        this.spousceAccompany = spousceAccompany;
        this.childrenAccompany = childrenAccompany;
        this.comments = comments;
        this.otherUniversity=otherUniversity;
    }

    /**
     *
     * @return
     */
    public String getStudyDetails() {
        return studyDetails;
    }

    /**
     *
     * @param studyDetails
     */
    public void setStudyDetails(String studyDetails) {
        this.studyDetails = studyDetails;
    }

    /**
     *
     * @return
     */
    public String getStudyLocations() {
        return studyLocations;
    }

    /**
     *
     * @param studyLocations
     */
    public void setStudyLocations(String studyLocations) {
        this.studyLocations = studyLocations;
    }

    /**
     *
     * @return
     */
    public String getStudyComments() {
        return studyComments;
    }

    /**
     *
     * @param studyComments
     */
    public void setStudyComments(String studyComments) {
        this.studyComments = studyComments;
    }
    
    /**
     *
     * @return
     */
    public String getOtherUniversity() {
        return otherUniversity;
    }

    /**
     *
     * @param otherUniversity
     */
    public void setOtherUniversity(String otherUniversity) {
        this.otherUniversity = otherUniversity;
    }

    /**
     *
     * @return
     */
    public String getStudy() {
        return study;
    }

    @Override
    public String toString() {
        return "AssesmentStudyPOJO{" + "study=" + study + ", programLevel=" + programLevel + ", programField=" + programField + ", intake=" + intake + ", university=" + university + ", location=" + location + ", choice=" + choice + ", comments=" + comments + ", spousceAccompany=" + spousceAccompany + ", childrenAccompany=" + childrenAccompany + '}';
    }

    /**
     *
     * @return
     */
    public String getComments() {
        return comments;
    }

    /**
     *
     * @param comments
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     *
     * @param study
     */
    public void setStudy(String study) {
        this.study = study;
    }

    /**
     *
     * @return
     */
    public String getProgramLevel() {
        return programLevel;
    }

    /**
     *
     * @param programLevel
     */
    public void setProgramLevel(String programLevel) {
        this.programLevel = programLevel;
    }

    /**
     *
     * @return
     */
    public String getProgramField() {
        return programField;
    }

    /**
     *
     * @param programField
     */
    public void setProgramField(String programField) {
        this.programField = programField;
    }

    /**
     *
     * @return
     */
    public String getIntake() {
        return intake;
    }

    /**
     *
     * @param intake
     */
    public void setIntake(String intake) {
        this.intake = intake;
    }

    /**
     *
     * @return
     */
    public String getUniversity() {
        return university;
    }

    /**
     *
     * @param university
     */
    public void setUniversity(String university) {
        this.university = university;
    }

    /**
     *
     * @return
     */
    public String getLocation() {
        return location;
    }

    /**
     *
     * @param location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     *
     * @return
     */
    public String getChoice() {
        return choice;
    }

    /**
     *
     * @param choice
     */
    public void setChoice(String choice) {
        this.choice = choice;
    }

    /**
     *
     * @return
     */
    public int getSpousceAccompany() {
        return spousceAccompany;
    }

    /**
     *
     * @param spousceAccompany
     */
    public void setSpousceAccompany(int spousceAccompany) {
        this.spousceAccompany = spousceAccompany;
    }

    /**
     *
     * @return
     */
    public int getChildrenAccompany() {
        return childrenAccompany;
    }

    /**
     *
     * @param childrenAccompany
     */
    public void setChildrenAccompany(int childrenAccompany) {
        this.childrenAccompany = childrenAccompany;
    }

}
