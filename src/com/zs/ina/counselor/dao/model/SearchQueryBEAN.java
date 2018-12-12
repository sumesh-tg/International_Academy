/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.counselor.dao.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author zscomp1
 */
public class SearchQueryBEAN {
    private final StringProperty nameOrPhone = new SimpleStringProperty();
    private final StringProperty district = new SimpleStringProperty();
    private final StringProperty branch = new SimpleStringProperty();
    private final StringProperty assignedTo = new SimpleStringProperty();
    private final StringProperty enquiryFor = new SimpleStringProperty();
    private final StringProperty enquirySource = new SimpleStringProperty();
    private final StringProperty programLevel = new SimpleStringProperty();
    private final StringProperty programField = new SimpleStringProperty();
    private final StringProperty enquiryMethod = new SimpleStringProperty();
    private final StringProperty dateFrom = new SimpleStringProperty();
    private final StringProperty dateTo = new SimpleStringProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty phone = new SimpleStringProperty();
    private final StringProperty dob = new SimpleStringProperty();
    private final StringProperty passportNum = new SimpleStringProperty();
    private final StringProperty state = new SimpleStringProperty();
    private final StringProperty country = new SimpleStringProperty();
    private final StringProperty qualificationLevel = new SimpleStringProperty();
    private final StringProperty qualificationField = new SimpleStringProperty();
    private final StringProperty languageTest = new SimpleStringProperty();
    private final StringProperty score = new SimpleStringProperty();
    private final StringProperty profession = new SimpleStringProperty();
    private final StringProperty duration = new SimpleStringProperty();
    private final StringProperty intake = new SimpleStringProperty();
    private final StringProperty institution = new SimpleStringProperty();
    private final StringProperty countryPrgmRequired = new SimpleStringProperty();
    private final StringProperty locationPrgmRequired = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getLocationPrgmRequired() {
        return locationPrgmRequired.get();
    }

    /**
     *
     * @param value
     */
    public void setLocationPrgmRequired(String value) {
        locationPrgmRequired.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty locationPrgmRequiredProperty() {
        return locationPrgmRequired;
    }

    /**
     *
     * @return
     */
    public String getCountryPrgmRequired() {
        return countryPrgmRequired.get();
    }

    /**
     *
     * @param value
     */
    public void setCountryPrgmRequired(String value) {
        countryPrgmRequired.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty countryPrgmRequiredProperty() {
        return countryPrgmRequired;
    }

    /**
     *
     * @return
     */
    public String getInstitution() {
        return institution.get();
    }

    /**
     *
     * @param value
     */
    public void setInstitution(String value) {
        institution.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty institutionProperty() {
        return institution;
    }
    
    private final StringProperty assignedBy = new SimpleStringProperty();
    private final StringProperty applicationStatus = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getApplicationStatus() {
        return applicationStatus.get();
    }

    /**
     *
     * @param value
     */
    public void setApplicationStatus(String value) {
        applicationStatus.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty applicationStatusProperty() {
        return applicationStatus;
    }

    /**
     *
     * @return
     */
    public String getAssignedBy() {
        return assignedBy.get();
    }

    /**
     *
     * @param value
     */
    public void setAssignedBy(String value) {
        assignedBy.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty assignedByProperty() {
        return assignedBy;
    }

    /**
     *
     * @return
     */
    public String getIntake() {
        return intake.get();
    }

    /**
     *
     * @param value
     */
    public void setIntake(String value) {
        intake.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty intakeProperty() {
        return intake;
    }

    /**
     *
     * @return
     */
    public String getDuration() {
        return duration.get();
    }

    /**
     *
     * @param value
     */
    public void setDuration(String value) {
        duration.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty durationProperty() {
        return duration;
    }

    /**
     *
     * @return
     */
    public String getProfession() {
        return profession.get();
    }

    /**
     *
     * @param value
     */
    public void setProfession(String value) {
        profession.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty professionProperty() {
        return profession;
    }

    /**
     *
     * @return
     */
    public String getScore() {
        return score.get();
    }

    /**
     *
     * @param value
     */
    public void setScore(String value) {
        score.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty scoreProperty() {
        return score;
    }

    /**
     *
     * @return
     */
    public String getLanguageTest() {
        return languageTest.get();
    }

    /**
     *
     * @param value
     */
    public void setLanguageTest(String value) {
        languageTest.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty languageTestProperty() {
        return languageTest;
    }

    /**
     *
     * @return
     */
    public String getQualificationField() {
        return qualificationField.get();
    }

    /**
     *
     * @param value
     */
    public void setQualificationField(String value) {
        qualificationField.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty qualificationFieldProperty() {
        return qualificationField;
    }

    /**
     *
     * @return
     */
    public String getQualificationLevel() {
        return qualificationLevel.get();
    }

    /**
     *
     * @param value
     */
    public void setQualificationLevel(String value) {
        qualificationLevel.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty qualificationLevelProperty() {
        return qualificationLevel;
    }

    /**
     *
     * @return
     */
    public String getCountry() {
        return country.get();
    }

    /**
     *
     * @param value
     */
    public void setCountry(String value) {
        country.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty countryProperty() {
        return country;
    }

    /**
     *
     * @return
     */
    public String getState() {
        return state.get();
    }

    /**
     *
     * @param value
     */
    public void setState(String value) {
        state.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty stateProperty() {
        return state;
    }

    /**
     *
     * @return
     */
    public String getPassportNum() {
        return passportNum.get();
    }

    /**
     *
     * @param value
     */
    public void setPassportNum(String value) {
        passportNum.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty passportNumProperty() {
        return passportNum;
    }

    /**
     *
     * @return
     */
    public String getDob() {
        return dob.get();
    }

    /**
     *
     * @param value
     */
    public void setDob(String value) {
        dob.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty dobProperty() {
        return dob;
    }

    /**
     *
     * @return
     */
    public String getPhone() {
        return phone.get();
    }

    /**
     *
     * @param value
     */
    public void setPhone(String value) {
        phone.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty phoneProperty() {
        return phone;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name.get();
    }

    /**
     *
     * @param value
     */
    public void setName(String value) {
        name.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty nameProperty() {
        return name;
    }

    /**
     *
     * @return
     */
    public String getDateTo() {
        return dateTo.get();
    }

    /**
     *
     * @param value
     */
    public void setDateTo(String value) {
        dateTo.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty dateToProperty() {
        return dateTo;
    }

    /**
     *
     * @return
     */
    public String getDateFrom() {
        return dateFrom.get();
    }

    /**
     *
     * @param value
     */
    public void setDateFrom(String value) {
        dateFrom.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty dateFromProperty() {
        return dateFrom;
    }

    /**
     *
     * @return
     */
    public String getEnquiryMethod() {
        return enquiryMethod.get();
    }

    /**
     *
     * @param value
     */
    public void setEnquiryMethod(String value) {
        enquiryMethod.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty enquiryMethodProperty() {
        return enquiryMethod;
    }

    /**
     *
     * @return
     */
    public String getProgramField() {
        return programField.get();
    }

    /**
     *
     * @param value
     */
    public void setProgramField(String value) {
        programField.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty programFieldProperty() {
        return programField;
    }

    /**
     *
     * @return
     */
    public String getProgramLevel() {
        return programLevel.get();
    }

    /**
     *
     * @param value
     */
    public void setProgramLevel(String value) {
        programLevel.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty programLevelProperty() {
        return programLevel;
    }

    /**
     *
     * @return
     */
    public String getEnquirySource() {
        return enquirySource.get();
    }

    /**
     *
     * @param value
     */
    public void setEnquirySource(String value) {
        enquirySource.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty enquirySourceProperty() {
        return enquirySource;
    }

    /**
     *
     * @return
     */
    public String getEnquiryFor() {
        return enquiryFor.get();
    }

    /**
     *
     * @param value
     */
    public void setEnquiryFor(String value) {
        enquiryFor.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty enquiryForProperty() {
        return enquiryFor;
    }

    /**
     *
     * @return
     */
    public String getAssignedTo() {
        return assignedTo.get();
    }

    /**
     *
     * @param value
     */
    public void setAssignedTo(String value) {
        assignedTo.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty assignedToProperty() {
        return assignedTo;
    }

    /**
     *
     * @return
     */
    public String getBranch() {
        return branch.get();
    }

    /**
     *
     * @param value
     */
    public void setBranch(String value) {
        branch.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty branchProperty() {
        return branch;
    }

    /**
     *
     * @return
     */
    public String getDistrict() {
        return district.get();
    }

    /**
     *
     * @param value
     */
    public void setDistrict(String value) {
        district.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty districtProperty() {
        return district;
    }

    /**
     *
     * @return
     */
    public String getNameOrPhone() {
        return nameOrPhone.get();
    }

    /**
     *
     * @param value
     */
    public void setNameOrPhone(String value) {
        nameOrPhone.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty nameOrPhoneProperty() {
        return nameOrPhone;
    }

    @Override
    public String toString() {
        return "SearchQueryBEAN{" + "nameOrPhone=" + nameOrPhone + ", district=" + district + ", branch=" + branch + ", assignedTo=" + assignedTo + ", enquiryFor=" + enquiryFor + ", enquirySource=" + enquirySource + ", programLevel=" + programLevel + ", programField=" + programField + ", enquiryMethod=" + enquiryMethod + ", dateFrom=" + dateFrom + ", dateTo=" + dateTo + ", name=" + name + ", phone=" + phone + ", dob=" + dob + ", passportNum=" + passportNum + ", state=" + state + ", country=" + country + ", qualificationLevel=" + qualificationLevel + ", qualificationField=" + qualificationField + ", languageTest=" + languageTest + ", score=" + score + ", profession=" + profession + ", duration=" + duration + ", intake=" + intake + ", institution=" + institution + ", countryPrgmRequired=" + countryPrgmRequired + ", locationPrgmRequired=" + locationPrgmRequired + ", assignedBy=" + assignedBy + ", applicationStatus=" + applicationStatus + '}';
    }

   


}
