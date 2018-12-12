/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.counselor.dao.model;

import java.util.Objects;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author user
 */
public class CounselorDetailsBEAN {

    private final StringProperty enquiryID = new SimpleStringProperty();
    private StringProperty contactName = new SimpleStringProperty();
    private final StringProperty contactEmail = new SimpleStringProperty();
    private final StringProperty stdcode1 = new SimpleStringProperty();
    private final StringProperty stdcode2 = new SimpleStringProperty();
    private final StringProperty contactNumber1 = new SimpleStringProperty();
    private final StringProperty contactNumber2 = new SimpleStringProperty();
    private final StringProperty bracnch = new SimpleStringProperty();
    private final StringProperty country = new SimpleStringProperty();
    private final StringProperty state = new SimpleStringProperty();
    private final StringProperty district = new SimpleStringProperty();
    private final StringProperty enquirySource = new SimpleStringProperty();
    private final StringProperty enquiryMethod = new SimpleStringProperty();
    private final StringProperty enquiryAssignedTo = new SimpleStringProperty();
    private final StringProperty department = new SimpleStringProperty();
    private final StringProperty otherEnquiry = new SimpleStringProperty();
    private final StringProperty study = new SimpleStringProperty();
    private final StringProperty work = new SimpleStringProperty();
    private final StringProperty migration = new SimpleStringProperty();
    private final StringProperty training = new SimpleStringProperty();
    private final StringProperty enquiryDetails = new SimpleStringProperty();
    private final StringProperty workDetails = new SimpleStringProperty();
    private final StringProperty migrationDetails = new SimpleStringProperty();
    private final StringProperty trainingDetails = new SimpleStringProperty();
    private final StringProperty otherStudyDetails = new SimpleStringProperty();
    private final StringProperty otherWorkDetails = new SimpleStringProperty();
    private final StringProperty otherMigrationDetails = new SimpleStringProperty();
    private final StringProperty OtherTrainingDetails = new SimpleStringProperty();
    private final StringProperty rating = new SimpleStringProperty();
    private final StringProperty important = new SimpleStringProperty();
    private final StringProperty readflag = new SimpleStringProperty();
    private final StringProperty completionflag = new SimpleStringProperty();
    private final StringProperty edate = new SimpleStringProperty();
    private final StringProperty staffUsername = new SimpleStringProperty();
    private final StringProperty staffBranch = new SimpleStringProperty();
    private final StringProperty trainingTime = new SimpleStringProperty();
    private final StringProperty proposedDate = new SimpleStringProperty();
    private final StringProperty emailStatus = new SimpleStringProperty();
    private final StringProperty studyDetails = new SimpleStringProperty();
    private final StringProperty CounselorName = new SimpleStringProperty();
    private final StringProperty counselorBranch = new SimpleStringProperty();
    private final StringProperty lastUpdate = new SimpleStringProperty();
    private final StringProperty proposedTime = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();
    private final StringProperty remarks = new SimpleStringProperty();
    private final StringProperty sno = new SimpleStringProperty();
    private final StringProperty listOfPrograms = new SimpleStringProperty();

    private final StringProperty count = new SimpleStringProperty();
    private final StringProperty enquiryAssignedBy = new SimpleStringProperty();
    private final StringProperty appointmentDate = new SimpleStringProperty();
    private final StringProperty appointmentTime = new SimpleStringProperty();
    private final StringProperty reference = new SimpleStringProperty();

    private final StringProperty qualification = new SimpleStringProperty();
    private final StringProperty languageTest = new SimpleStringProperty();
    private final StringProperty admissionTest = new SimpleStringProperty();
    private final StringProperty experience = new SimpleStringProperty();
    private final StringProperty applicationType = new SimpleStringProperty();
    private final StringProperty salaryExpected = new SimpleStringProperty();
    private final StringProperty hid = new SimpleStringProperty();
    private final StringProperty followUpStatus = new SimpleStringProperty();
    private final StringProperty followUpDates = new SimpleStringProperty();
    private final StringProperty age = new SimpleStringProperty();
    private final StringProperty gender = new SimpleStringProperty();
    private final StringProperty maritalStatus = new SimpleStringProperty();
    private final StringProperty passportNo = new SimpleStringProperty();
    private final StringProperty rowId = new SimpleStringProperty();
    private final StringProperty latestFlag = new SimpleStringProperty();

    /* ===================== Additional fields for registration ===================== */
    private final StringProperty previousName = new SimpleStringProperty();
    private final StringProperty houseName = new SimpleStringProperty();
    private final StringProperty street = new SimpleStringProperty();
    private final StringProperty place = new SimpleStringProperty();
    private final StringProperty postOffice = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getPostOffice() {
        return postOffice.get();
    }

    /**
     *
     * @param value
     */
    public void setPostOffice(String value) {
        postOffice.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty postOfficeProperty() {
        return postOffice;
    }

    /**
     *
     * @return
     */
    public String getPlace() {
        return place.get();
    }

    /**
     *
     * @param value
     */
    public void setPlace(String value) {
        place.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty placeProperty() {
        return place;
    }

    /**
     *
     * @return
     */
    public String getStreet() {
        return street.get();
    }

    /**
     *
     * @param value
     */
    public void setStreet(String value) {
        street.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty streetProperty() {
        return street;
    }

    /**
     *
     * @return
     */
    public String getHouseName() {
        return houseName.get();
    }

    /**
     *
     * @param value
     */
    public void setHouseName(String value) {
        houseName.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty houseNameProperty() {
        return houseName;
    }

    /**
     *
     * @return
     */
    public String getPreviousName() {
        return previousName.get();
    }

    /**
     *
     * @param value
     */
    public void setPreviousName(String value) {
        previousName.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty previousNameProperty() {
        return previousName;
    }

    /**
     *
     * @return
     */
    public String getLatestFlag() {
        return latestFlag.get();
    }

    /**
     *
     * @param value
     */
    public void setLatestFlag(String value) {
        latestFlag.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty latestFlagProperty() {
        return latestFlag;
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

    /**
     *
     * @return
     */
    public String getPassportNo() {
        return passportNo.get();
    }

    /**
     *
     * @param value
     */
    public void setPassportNo(String value) {
        passportNo.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty passportNoProperty() {
        return passportNo;
    }

    /**
     *
     * @return
     */
    public String getMaritalStatus() {
        return maritalStatus.get();
    }

    /**
     *
     * @param value
     */
    public void setMaritalStatus(String value) {
        maritalStatus.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty maritalStatusProperty() {
        return maritalStatus;
    }

    /**
     *
     * @return
     */
    public String getGender() {
        return gender.get();
    }

    /**
     *
     * @param value
     */
    public void setGender(String value) {
        gender.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty genderProperty() {
        return gender;
    }

    /**
     *
     * @return
     */
    public String getAge() {
        return age.get();
    }

    /**
     *
     * @param value
     */
    public void setAge(String value) {
        age.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty ageProperty() {
        return age;
    }

    /**
     *
     * @return
     */
    public String getFollowUpDates() {
        return followUpDates.get();
    }

    /**
     *
     * @param value
     */
    public void setFollowUpDates(String value) {
        followUpDates.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty followUpDatesProperty() {
        return followUpDates;
    }

    /**
     *
     * @return
     */
    public String getFollowUpStatus() {
        return followUpStatus.get();
    }

    /**
     *
     * @param value
     */
    public void setFollowUpStatus(String value) {
        followUpStatus.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty followUpStatusProperty() {
        return followUpStatus;
    }

    /**
     *
     */
    public CounselorDetailsBEAN() {
    }

    /**
     *
     * @param contactName
     */
    public CounselorDetailsBEAN(String contactName) {
        this.setContactName(contactName);
    }

    /**
     *
     * @return
     */
    public String getHid() {
        return hid.get();
    }

    /**
     *
     * @param value
     */
    public void setHid(String value) {
        hid.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty hidProperty() {
        return hid;
    }

    /**
     *
     * @return
     */
    public String getSalaryExpected() {
        return salaryExpected.get();
    }

    /**
     *
     * @param value
     */
    public void setSalaryExpected(String value) {
        salaryExpected.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty salaryExpectedProperty() {
        return salaryExpected;
    }

    /**
     *
     * @return
     */
    public String getApplicationType() {
        return applicationType.get();
    }

    /**
     *
     * @param value
     */
    public void setApplicationType(String value) {
        applicationType.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty applicationTypeProperty() {
        return applicationType;
    }

    /**
     *
     * @return
     */
    public String getExperience() {
        return experience.get();
    }

    /**
     *
     * @param value
     */
    public void setExperience(String value) {
        experience.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty experienceProperty() {
        return experience;
    }

    /**
     *
     * @return
     */
    public String getAdmissionTest() {
        return admissionTest.get();
    }

    /**
     *
     * @param value
     */
    public void setAdmissionTest(String value) {
        admissionTest.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty admissionTestProperty() {
        return admissionTest;
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
    public String getQualification() {
        return qualification.get();
    }

    /**
     *
     * @param value
     */
    public void setQualification(String value) {
        qualification.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty qualificationProperty() {
        return qualification;
    }

    /**
     *
     * @return
     */
    public String getOtherStudyDetails() {
        return otherStudyDetails.get();
    }

    /**
     *
     * @param value
     */
    public void setOtherStudyDetails(String value) {
        otherStudyDetails.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty otherStudyDetailsProperty() {
        return otherStudyDetails;
    }

    //for primary inbox
    private final StringProperty program = new SimpleStringProperty();
    private final StringProperty programRequired = new SimpleStringProperty();

    //for forward for inbox
    private final StringProperty forwardFor = new SimpleStringProperty();

    //for registere inbox
    private final StringProperty dob = new SimpleStringProperty();

    //For study,work,migration,training Inbox
    private final StringProperty assignedBy = new SimpleStringProperty();
    private final StringProperty studyRequired = new SimpleStringProperty();
    private final StringProperty workRequired = new SimpleStringProperty();
    private final StringProperty migrationRequired = new SimpleStringProperty();
    private final StringProperty trainingRequired = new SimpleStringProperty();
    private final StringProperty joiningDate = new SimpleStringProperty();
    private final StringProperty batch = new SimpleStringProperty();
    private final StringProperty workCountryLocation = new SimpleStringProperty();
    private final StringProperty workCountry = new SimpleStringProperty();
    private final StringProperty workLocation = new SimpleStringProperty();

    private final StringProperty studyCountryLocation = new SimpleStringProperty();
    private final StringProperty studyCountry = new SimpleStringProperty();
    private final StringProperty studyLocation = new SimpleStringProperty();

    private final StringProperty migrateCountryLocation = new SimpleStringProperty();
    private final StringProperty migrateCountry = new SimpleStringProperty();
    private final StringProperty migrateLocation = new SimpleStringProperty();

    private final StringProperty trainingCountryLocation = new SimpleStringProperty();
    private final StringProperty programLevel = new SimpleStringProperty();
    private final StringProperty programField = new SimpleStringProperty();
    private final StringProperty profession = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getMigrateLocation() {
        return migrateLocation.get();
    }

    /**
     *
     * @param value
     */
    public void setMigrateLocation(String value) {
        migrateLocation.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty migrateLocationProperty() {
        return migrateLocation;
    }

    /**
     *
     * @return
     */
    public String getMigrateCountry() {
        return migrateCountry.get();
    }

    /**
     *
     * @param value
     */
    public void setMigrateCountry(String value) {
        migrateCountry.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty migrateCountryProperty() {
        return migrateCountry;
    }

    /**
     *
     * @return
     */
    public String getWorkLocation() {
        return workLocation.get();
    }

    /**
     *
     * @param value
     */
    public void setWorkLocation(String value) {
        workLocation.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty workLocationProperty() {
        return workLocation;
    }

    /**
     *
     * @return
     */
    public String getWorkCountry() {
        return workCountry.get();
    }

    /**
     *
     * @param value
     */
    public void setWorkCountry(String value) {
        workCountry.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty workCountryProperty() {
        return workCountry;
    }

    /**
     *
     * @return
     */
    public String getStudyLocation() {
        return studyLocation.get();
    }

    /**
     *
     * @param value
     */
    public void setStudyLocation(String value) {
        studyLocation.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty studyLocationProperty() {
        return studyLocation;
    }

    /**
     *
     * @return
     */
    public String getStudyCountry() {
        return studyCountry.get();
    }

    /**
     *
     * @param value
     */
    public void setStudyCountry(String value) {
        studyCountry.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty studyCountryProperty() {
        return studyCountry;
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
    public String getForwardFor() {
        return forwardFor.get();
    }

    /**
     *
     * @param value
     */
    public void setForwardFor(String value) {
        forwardFor.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty forwardForProperty() {
        return forwardFor;
    }

    /**
     *
     * @return
     */
    public String getDepartment() {
        return department.get();
    }

    /**
     *
     * @param value
     */
    public void setDepartment(String value) {
        department.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty departmentProperty() {
        return department;
    }

    /**
     *
     * @return
     */
    public String getReference() {
        return reference.get();
    }

    /**
     *
     * @param value
     */
    public void setReference(String value) {
        reference.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty referenceProperty() {
        return reference;
    }

    /**
     *
     * @return
     */
    public String getAppointmentTime() {
        return appointmentTime.get();
    }

    /**
     *
     * @param value
     */
    public void setAppointmentTime(String value) {
        appointmentTime.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty appointmentTimeProperty() {
        return appointmentTime;
    }

    /**
     *
     * @return
     */
    public String getAppointmentDate() {
        return appointmentDate.get();
    }

    /**
     *
     * @param value
     */
    public void setAppointmentDate(String value) {
        appointmentDate.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty appointmentDateProperty() {
        return appointmentDate;
    }

    /**
     *
     * @return
     */
    public String getEnquiryAssignedBy() {
        return enquiryAssignedBy.get();
    }

    /**
     *
     * @param value
     */
    public void setEnquiryAssignedBy(String value) {
        enquiryAssignedBy.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty enquiryAssignedByProperty() {
        return enquiryAssignedBy;
    }

    /**
     *
     * @return
     */
    public String getCount() {
        return count.get();
    }

    /**
     *
     * @param value
     */
    public void setCount(String value) {
        count.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty countProperty() {
        return count;
    }

    /**
     *
     * @return
     */
    public String getContactNumber2() {
        return contactNumber2.get();
    }

    /**
     *
     * @param value
     */
    public void setContactNumber2(String value) {
        contactNumber2.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty contactNumber2Property() {
        return contactNumber2;
    }

    /**
     *
     * @return
     */
    public String getStdcode2() {
        return stdcode2.get();
    }

    /**
     *
     * @param value
     */
    public void setStdcode2(String value) {
        stdcode2.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty stdcode2Property() {
        return stdcode2;
    }

    /**
     *
     * @return
     */
    public String getTrainingCountryLocation() {
        return trainingCountryLocation.get();
    }

    /**
     *
     * @param value
     */
    public void setTrainingCountryLocation(String value) {
        trainingCountryLocation.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty trainingCountryLocationProperty() {
        return trainingCountryLocation;
    }

    /**
     *
     * @return
     */
    public String getMigrateCountryLocation() {
        return migrateCountryLocation.get();
    }

    /**
     *
     * @param value
     */
    public void setMigrateCountryLocation(String value) {
        migrateCountryLocation.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty migrateCountryLocationProperty() {
        return migrateCountryLocation;
    }

    /**
     *
     * @return
     */
    public String getStudyCountryLocation() {
        return studyCountryLocation.get();
    }

    /**
     *
     * @param value
     */
    public void setStudyCountryLocation(String value) {
        studyCountryLocation.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty studyCountryLocationProperty() {
        return studyCountryLocation;
    }

    /**
     *
     * @return
     */
    public String getWorkCountryLocation() {
        return workCountryLocation.get();
    }

    /**
     *
     * @param value
     */
    public void setWorkCountryLocation(String value) {
        workCountryLocation.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty workCountryLocationProperty() {
        return workCountryLocation;
    }

    /**
     *
     * @return
     */
    public String getBatch() {
        return batch.get();
    }

    /**
     *
     * @param value
     */
    public void setBatch(String value) {
        batch.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty batchProperty() {
        return batch;
    }

    /**
     *
     * @return
     */
    public String getJoiningDate() {
        return joiningDate.get();
    }

    /**
     *
     * @param value
     */
    public void setJoiningDate(String value) {
        joiningDate.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty joiningDateProperty() {
        return joiningDate;
    }

    /**
     *
     * @return
     */
    public String getTrainingRequired() {
        return trainingRequired.get();
    }

    /**
     *
     * @param value
     */
    public void setTrainingRequired(String value) {
        trainingRequired.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty trainingRequiredProperty() {
        return trainingRequired;
    }

    /**
     *
     * @return
     */
    public String getMigrationRequired() {
        return migrationRequired.get();
    }

    /**
     *
     * @param value
     */
    public void setMigrationRequired(String value) {
        migrationRequired.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty migrationRequiredProperty() {
        return migrationRequired;
    }

    /**
     *
     * @return
     */
    public String getWorkRequired() {
        return workRequired.get();
    }

    /**
     *
     * @param value
     */
    public void setWorkRequired(String value) {
        workRequired.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty workRequiredProperty() {
        return workRequired;
    }

    /**
     *
     * @return
     */
    public String getStudyRequired() {
        return studyRequired.get();
    }

    /**
     *
     * @param value
     */
    public void setStudyRequired(String value) {
        studyRequired.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty studyRequiredProperty() {
        return studyRequired;
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

    //check box
    private final BooleanProperty active = new SimpleBooleanProperty();

    /**
     *
     * @return
     */
    public boolean isActive() {
        return active.get();
    }

    /**
     *
     * @param value
     */
    public void setActive(boolean value) {
        active.set(value);
    }

    /**
     *
     * @return
     */
    public BooleanProperty activeProperty() {
        return active;
    }

    /**
     *
     * @return
     */
    public String getProgramRequired() {
        return programRequired.get();
    }

    /**
     *
     * @param value
     */
    public void setProgramRequired(String value) {
        programRequired.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty programRequiredProperty() {
        return programRequired;
    }

    /**
     *
     * @return
     */
    public String getProgram() {
        return program.get();
    }

    /**
     *
     * @param value
     */
    public void setProgram(String value) {
        program.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty programProperty() {
        return program;
    }

    /**
     *
     * @return
     */
    public String getListOfPrograms() {
        return listOfPrograms.get();
    }

    /**
     *
     * @param value
     */
    public void setListOfPrograms(String value) {
        listOfPrograms.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty listOfProgramsProperty() {
        return listOfPrograms;
    }

    /**
     *
     * @return
     */
    public String getSno() {
        return sno.get();
    }

    /**
     *
     * @param value
     */
    public void setSno(String value) {
        sno.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty snoProperty() {
        return sno;
    }

    /**
     *
     * @return
     */
    public String getRemarks() {
        return remarks.get();
    }

    /**
     *
     * @param value
     */
    public void setRemarks(String value) {
        remarks.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty remarksProperty() {
        return remarks;
    }

    /**
     *
     * @return
     */
    public String getStatus() {
        return status.get();
    }

    /**
     *
     * @param value
     */
    public void setStatus(String value) {
        status.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty statusProperty() {
        return status;
    }

    /**
     *
     * @return
     */
    public String getProposedTime() {
        return proposedTime.get();
    }

    /**
     *
     * @param value
     */
    public void setProposedTime(String value) {
        proposedTime.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty proposedTimeProperty() {
        return proposedTime;
    }

    /**
     *
     * @return
     */
    public String getLastUpdate() {
        return lastUpdate.get();
    }

    /**
     *
     * @param value
     */
    public void setLastUpdate(String value) {
        lastUpdate.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty lastUpdateProperty() {
        return lastUpdate;
    }

    /**
     *
     * @return
     */
    public String getCounselorBranch() {
        return counselorBranch.get();
    }

    /**
     *
     * @param value
     */
    public void setCounselorBranch(String value) {
        counselorBranch.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty counselorBranchProperty() {
        return counselorBranch;
    }

    /**
     *
     * @return
     */
    public String getCounselorName() {
        return CounselorName.get();
    }

    /**
     *
     * @param value
     */
    public void setCounselorName(String value) {
        CounselorName.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty CounselorNameProperty() {
        return CounselorName;
    }

    /**
     *
     * @return
     */
    public String getStudyDetails() {
        return studyDetails.get();
    }

    /**
     *
     * @param value
     */
    public void setStudyDetails(String value) {
        studyDetails.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty studyDetailsProperty() {
        return studyDetails;
    }

    /**
     *
     * @return
     */
    public String getEmailStatus() {
        return emailStatus.get();
    }

    /**
     *
     * @param value
     */
    public void setEmailStatus(String value) {
        emailStatus.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty emailStatusProperty() {
        return emailStatus;
    }

    /**
     *
     * @return
     */
    public String getProposedDate() {
        return proposedDate.get();
    }

    /**
     *
     * @param value
     */
    public void setProposedDate(String value) {
        proposedDate.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty proposedDateProperty() {
        return proposedDate;
    }

    /**
     *
     * @return
     */
    public String getTrainingTime() {
        return trainingTime.get();
    }

    /**
     *
     * @param value
     */
    public void setTrainingTime(String value) {
        trainingTime.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty trainingTimeProperty() {
        return trainingTime;
    }

    /**
     *
     * @return
     */
    public String getStaffBranch() {
        return staffBranch.get();
    }

    /**
     *
     * @param value
     */
    public void setStaffBranch(String value) {
        staffBranch.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty staffBranchProperty() {
        return staffBranch;
    }

    /**
     *
     * @return
     */
    public String getStaffUsername() {
        return staffUsername.get();
    }

    /**
     *
     * @param value
     */
    public void setStaffUsername(String value) {
        staffUsername.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty staffUsernameProperty() {
        return staffUsername;
    }

    /**
     *
     * @return
     */
    public String getStdcode1() {
        return stdcode1.get();
    }

    /**
     *
     * @param value
     */
    public void setStdcode1(String value) {
        stdcode1.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty stdcode1Property() {
        return stdcode1;
    }

    /**
     *
     * @return
     */
    public String getEdate() {
        return edate.get();
    }

    /**
     *
     * @param value
     */
    public void setEdate(String value) {
        edate.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty edateProperty() {
        return edate;
    }

    /**
     *
     * @return
     */
    public String getCompletionflag() {
        return completionflag.get();
    }

    /**
     *
     * @param value
     */
    public void setCompletionflag(String value) {
        completionflag.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty completionflagProperty() {
        return completionflag;
    }

    /**
     *
     * @return
     */
    public String getReadflag() {
        return readflag.get();
    }

    /**
     *
     * @param value
     */
    public void setReadflag(String value) {
        readflag.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty readflagProperty() {
        return readflag;
    }

    /**
     *
     * @return
     */
    public String getImportant() {
        return important.get();
    }

    /**
     *
     * @param value
     */
    public void setImportant(String value) {
        important.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty importantProperty() {
        return important;
    }

    /**
     *
     * @return
     */
    public String getRating() {
        return rating.get();
    }

    /**
     *
     * @param value
     */
    public void setRating(String value) {
        rating.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty ratingProperty() {
        return rating;
    }

    /**
     *
     * @return
     */
    public String getOtherTrainingDetails() {
        return OtherTrainingDetails.get();
    }

    /**
     *
     * @param value
     */
    public void setOtherTrainingDetails(String value) {
        OtherTrainingDetails.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty OtherTrainingDetailsProperty() {
        return OtherTrainingDetails;
    }

    /**
     *
     * @return
     */
    public String getOtherMigrationDetails() {
        return otherMigrationDetails.get();
    }

    /**
     *
     * @param value
     */
    public void setOtherMigrationDetails(String value) {
        otherMigrationDetails.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty otherMigrationDetailsProperty() {
        return otherMigrationDetails;
    }

    /**
     *
     * @return
     */
    public String getOtherWorkDetails() {
        return otherWorkDetails.get();
    }

    /**
     *
     * @param value
     */
    public void setOtherWorkDetails(String value) {
        otherWorkDetails.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty otherWorkDetailsProperty() {
        return otherWorkDetails;
    }

    /**
     *
     * @return
     */
    public String getTrainingDetails() {
        return trainingDetails.get();
    }

    /**
     *
     * @param value
     */
    public void setTrainingDetails(String value) {
        trainingDetails.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty trainingDetailsProperty() {
        return trainingDetails;
    }

    /**
     *
     * @return
     */
    public String getMigrationDetails() {
        return migrationDetails.get();
    }

    /**
     *
     * @param value
     */
    public void setMigrationDetails(String value) {
        migrationDetails.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty migrationDetailsProperty() {
        return migrationDetails;
    }

    /**
     *
     * @return
     */
    public String getWorkDetails() {
        return workDetails.get();
    }

    /**
     *
     * @param value
     */
    public void setWorkDetails(String value) {
        workDetails.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty workDetailsProperty() {
        return workDetails;
    }

    /**
     *
     * @return
     */
    public String getEnquiryDetails() {
        return enquiryDetails.get();
    }

    /**
     *
     * @param value
     */
    public void setEnquiryDetails(String value) {
        enquiryDetails.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty enquiryDetailsProperty() {
        return enquiryDetails;
    }

    /**
     *
     * @return
     */
    public String getTraining() {
        return training.get();
    }

    /**
     *
     * @param value
     */
    public void setTraining(String value) {
        training.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty trainingProperty() {
        return training;
    }

    /**
     *
     * @return
     */
    public String getMigration() {
        return migration.get();
    }

    /**
     *
     * @param value
     */
    public void setMigration(String value) {
        migration.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty migrationProperty() {
        return migration;
    }

    /**
     *
     * @return
     */
    public String getWork() {
        return work.get();
    }

    /**
     *
     * @param value
     */
    public void setWork(String value) {
        work.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty workProperty() {
        return work;
    }

    /**
     *
     * @return
     */
    public String getStudy() {
        return study.get();
    }

    /**
     *
     * @param value
     */
    public void setStudy(String value) {
        study.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty studyProperty() {
        return study;
    }

    /**
     *
     * @return
     */
    public String getOtherEnquiry() {
        return otherEnquiry.get();
    }

    /**
     *
     * @param value
     */
    public void setOtherEnquiry(String value) {
        otherEnquiry.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty otherEnquiryProperty() {
        return otherEnquiry;
    }

    /**
     *
     * @return
     */
    public String getEnquiryAssignedTo() {
        return enquiryAssignedTo.get();
    }

    /**
     *
     * @param value
     */
    public void setEnquiryAssignedTo(String value) {
        enquiryAssignedTo.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty enquiryAssignedToProperty() {
        return enquiryAssignedTo;
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
    public String getBracnch() {
        return bracnch.get();
    }

    /**
     *
     * @param value
     */
    public void setBracnch(String value) {
        bracnch.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty bracnchProperty() {
        return bracnch;
    }

    /**
     *
     * @return
     */
    public String getContactEmail() {
        return contactEmail.get();
    }

    /**
     *
     * @param value
     */
    public void setContactEmail(String value) {
        contactEmail.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty contactEmailProperty() {
        return contactEmail;
    }

    /**
     *
     * @return
     */
    public String getContactNumber1() {
        return contactNumber1.get();
    }

    /**
     *
     * @param value
     */
    public void setContactNumber1(String value) {
        contactNumber1.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty contactNumber1Property() {
        return contactNumber1;
    }

    /**
     *
     * @return
     */
    public String getContactName() {
        return contactName.get();
    }

    /**
     *
     * @param value
     */
    public void setContactName(String value) {
        contactName.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty contactNameProperty() {
        return contactName;
    }

    /**
     *
     * @return
     */
    public String getEnquiryID() {
        return enquiryID.get();
    }

    /**
     *
     * @param value
     */
    public void setEnquiryID(String value) {
        enquiryID.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty enquiryIDProperty() {
        return enquiryID;
    }

    @Override
    public String toString() {
        return "\nCounselorDetailsBEAN{" + "enquiryID=" + enquiryID + ", contactName=" + contactName + ", contactEmail=" + contactEmail + ", stdcode1=" + stdcode1 + ", stdcode2=" + stdcode2 + ", contactNumber1=" + contactNumber1 + ", contactNumber2=" + contactNumber2 + ", bracnch=" + bracnch + ", country=" + country + ", state=" + state + ", district=" + district + ", enquirySource=" + enquirySource + ", enquiryMethod=" + enquiryMethod + ", enquiryAssignedTo=" + enquiryAssignedTo + ", department=" + department + ", otherEnquiry=" + otherEnquiry + ", study=" + study + ", work=" + work + ", migration=" + migration + ", training=" + training + ", enquiryDetails=" + enquiryDetails + ", workDetails=" + workDetails + ", migrationDetails=" + migrationDetails + ", trainingDetails=" + trainingDetails + ", OtherTrainingDetails=" + OtherTrainingDetails + ", rating=" + rating + ", important=" + important + ", readflag=" + readflag + ", completionflag=" + completionflag + ", edate=" + edate + ", staffUsername=" + staffUsername + ", staffBranch=" + staffBranch + ", trainingTime=" + trainingTime + ", proposedDate=" + proposedDate + ", studyDetails=" + studyDetails + ", CounselorName=" + CounselorName + ", counselorBranch=" + counselorBranch + ", proposedTime=" + proposedTime + ", status=" + status + ", remarks=" + remarks + ", listOfPrograms=" + listOfPrograms + ", count=" + count + ", enquiryAssignedBy=" + enquiryAssignedBy + ", appointmentDate=" + appointmentDate + ", appointmentTime=" + appointmentTime + ", reference=" + reference + ", program=" + program + ", programRequired=" + programRequired + ", forwardFor=" + forwardFor + ", dob=" + dob + ", assignedBy=" + assignedBy + ", studyRequired=" + studyRequired + ", workRequired=" + workRequired + ", migrationRequired=" + migrationRequired + ", trainingRequired=" + trainingRequired + ", joiningDate=" + joiningDate + ", batch=" + batch + ", workCountryLocation=" + workCountryLocation + ", workCountry=" + workCountry + ", workLocation=" + workLocation + ", studyCountryLocation=" + studyCountryLocation + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.enquiryID);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CounselorDetailsBEAN other = (CounselorDetailsBEAN) obj;
        if (!Objects.equals(this.enquiryID, other.enquiryID)) {
            return false;
        }
        return true;
    }

}
