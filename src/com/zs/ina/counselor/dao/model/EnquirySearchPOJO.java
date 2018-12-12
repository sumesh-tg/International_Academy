/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.counselor.dao.model;

/**
 *
 * @author user
 */
public class EnquirySearchPOJO {

    private int enquiryId;
    private int sno;
    private double priority;
    private String contactName, contactNumber, contactEmail;
    private String enquiryBracnch, country, state, district, enquirySource;
    private String enquiryAssigning, otherEnquiry, study;
    private String work, migration, training, enquiryDetails;
    private String workDetails, migrationDetails, trainingDetails, studyDetails;
    private String otherStudyDetails, otherWorkDetails, otherMigrationDetails, OtherTrainingDetails;
    private Double rating;
    private String edate, stdcode;
    private String enquiryBy;
    private String remarks;
    private String Status, CounselorName, CounselorBranch;
    private String staffUsername, staffBranch;
    private String contactDetails;
    private String listOfPrograms;
    private int impotantFlag;
    private String proposedTrainingDate,ProposedTrainingTime,lastUpdate;

    /**
     *
     * @return
     */
    public String getListOfPrograms() {
        return listOfPrograms;
    }

    /**
     *
     * @param listOfPrograms
     */
    public void setListOfPrograms(String listOfPrograms) {
        this.listOfPrograms = listOfPrograms;
    }

    /**
     *
     * @return
     */
    public String getProposedTrainingDate() {
        return proposedTrainingDate;
    }

    /**
     *
     * @param proposedTrainingDate
     */
    public void setProposedTrainingDate(String proposedTrainingDate) {
        this.proposedTrainingDate = proposedTrainingDate;
    }

    /**
     *
     * @return
     */
    public String getProposedTrainingTime() {
        return ProposedTrainingTime;
    }

    /**
     *
     * @param ProposedTrainingTime
     */
    public void setProposedTrainingTime(String ProposedTrainingTime) {
        this.ProposedTrainingTime = ProposedTrainingTime;
    }

    /**
     *
     * @return
     */
    public String getLastUpdate() {
        return lastUpdate;
    }

    /**
     *
     * @param lastUpdate
     */
    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     *
     * @return
     */
    public int getImpotantFlag() {
        return impotantFlag;
    }

    /**
     *
     * @param impotantFlag
     */
    public void setImpotantFlag(int impotantFlag) {
        this.impotantFlag = impotantFlag;
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
    public String getContactDetails() {
        //  return contactDetails;
        contactDetails = "" + getContactName() + "\n" + "" + getContactEmail() + "\n"
                + "" + getContactNumber();
        return contactDetails;
    }

    /**
     *
     * @param contactDetails
     */
    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }

    /**
     *
     * @return
     */
    public int getSno() {
        return sno;
    }

    /**
     *
     * @param sno
     */
    public void setSno(int sno) {
        this.sno = sno;
    }

    /**
     *
     * @return
     */
    public int getEnquiryId() {
        return enquiryId;
    }

    /**
     *
     * @param enquiryId
     */
    public void setEnquiryId(int enquiryId) {
        this.enquiryId = enquiryId;
    }

    /**
     *
     * @return
     */
    public double getPriority() {
        return priority;
    }

    /**
     *
     * @param priority
     */
    public void setPriority(double priority) {
        this.priority = priority;
    }

    /**
     *
     * @return
     */
    public String getContactName() {
        return contactName;
    }

    /**
     *
     * @param contactName
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     *
     * @return
     */
    public String getContactNumber() {
        return contactNumber;
    }

    /**
     *
     * @param contactNumber
     */
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    /**
     *
     * @return
     */
    public String getContactEmail() {
        return contactEmail;
    }

    /**
     *
     * @param contactEmail
     */
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    /**
     *
     * @return
     */
    public String getEnquiryBracnch() {
        return enquiryBracnch;
    }

    /**
     *
     * @param enquiryBracnch
     */
    public void setEnquiryBracnch(String enquiryBracnch) {
        this.enquiryBracnch = enquiryBracnch;
    }

    /**
     *
     * @return
     */
    public String getCountry() {
        return country;
    }

    /**
     *
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     *
     * @return
     */
    public String getState() {
        return state;
    }

    /**
     *
     * @param state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     *
     * @return
     */
    public String getDistrict() {
        return district;
    }

    /**
     *
     * @param district
     */
    public void setDistrict(String district) {
        this.district = district;
    }

    /**
     *
     * @return
     */
    public String getEnquirySource() {
        return enquirySource;
    }

    /**
     *
     * @param enquirySource
     */
    public void setEnquirySource(String enquirySource) {
        this.enquirySource = enquirySource;
    }

    /**
     *
     * @return
     */
    public String getEnquiryAssigning() {
        return enquiryAssigning;
    }

    /**
     *
     * @param enquiryAssigning
     */
    public void setEnquiryAssigning(String enquiryAssigning) {
        this.enquiryAssigning = enquiryAssigning;
    }

    /**
     *
     * @return
     */
    public String getOtherEnquiry() {
        return otherEnquiry;
    }

    /**
     *
     * @param otherEnquiry
     */
    public void setOtherEnquiry(String otherEnquiry) {
        this.otherEnquiry = otherEnquiry;
    }

    /**
     *
     * @return
     */
    public String getStudy() {
        return study;
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
    public String getWork() {
        return work;
    }

    /**
     *
     * @param work
     */
    public void setWork(String work) {
        this.work = work;
    }

    /**
     *
     * @return
     */
    public String getMigration() {
        return migration;
    }

    /**
     *
     * @param migration
     */
    public void setMigration(String migration) {
        this.migration = migration;
    }

    /**
     *
     * @return
     */
    public String getTraining() {
        return training;
    }

    /**
     *
     * @param training
     */
    public void setTraining(String training) {
        this.training = training;
    }

    /**
     *
     * @return
     */
    public String getEnquiryDetails() {
        return enquiryDetails;
    }

    /**
     *
     * @param enquiryDetails
     */
    public void setEnquiryDetails(String enquiryDetails) {
        this.enquiryDetails = enquiryDetails;
    }

    /**
     *
     * @return
     */
    public String getWorkDetails() {
        return workDetails;
    }

    /**
     *
     * @param workDetails
     */
    public void setWorkDetails(String workDetails) {
        this.workDetails = workDetails;
    }

    /**
     *
     * @return
     */
    public String getMigrationDetails() {
        return migrationDetails;
    }

    /**
     *
     * @param migrationDetails
     */
    public void setMigrationDetails(String migrationDetails) {
        this.migrationDetails = migrationDetails;
    }

    /**
     *
     * @return
     */
    public String getTrainingDetails() {
        return trainingDetails;
    }

    /**
     *
     * @param trainingDetails
     */
    public void setTrainingDetails(String trainingDetails) {
        this.trainingDetails = trainingDetails;
    }

    /**
     *
     * @return
     */
    public String getOtherStudyDetails() {
        return otherStudyDetails;
    }

    /**
     *
     * @param otherStudyDetails
     */
    public void setOtherStudyDetails(String otherStudyDetails) {
        this.otherStudyDetails = otherStudyDetails;
    }

    /**
     *
     * @return
     */
    public String getOtherWorkDetails() {
        return otherWorkDetails;
    }

    /**
     *
     * @param otherWorkDetails
     */
    public void setOtherWorkDetails(String otherWorkDetails) {
        this.otherWorkDetails = otherWorkDetails;
    }

    /**
     *
     * @return
     */
    public String getOtherMigrationDetails() {
        return otherMigrationDetails;
    }

    /**
     *
     * @param otherMigrationDetails
     */
    public void setOtherMigrationDetails(String otherMigrationDetails) {
        this.otherMigrationDetails = otherMigrationDetails;
    }

    /**
     *
     * @return
     */
    public String getOtherTrainingDetails() {
        return OtherTrainingDetails;
    }

    /**
     *
     * @param OtherTrainingDetails
     */
    public void setOtherTrainingDetails(String OtherTrainingDetails) {
        this.OtherTrainingDetails = OtherTrainingDetails;
    }

    /**
     *
     * @return
     */
    public Double getRating() {
        return rating;
    }

    /**
     *
     * @param rating
     */
    public void setRating(Double rating) {
        this.rating = rating;
    }

    /**
     *
     * @return
     */
    public String getEdate() {
        return edate;
    }

    /**
     *
     * @param edate
     */
    public void setEdate(String edate) {
        this.edate = edate;
    }

    /**
     *
     * @return
     */
    public String getStdcode() {
        return stdcode;
    }

    /**
     *
     * @param stdcode
     */
    public void setStdcode(String stdcode) {
        this.stdcode = stdcode;
    }

    /**
     *
     * @return
     */
    public String getEnquiryBy() {
        return enquiryBy;
    }

    /**
     *
     * @param enquiryBy
     */
    public void setEnquiryBy(String enquiryBy) {
        this.enquiryBy = enquiryBy;
    }

    /**
     *
     * @return
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     *
     * @param remarks
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     *
     * @return
     */
    public String getStatus() {
        return Status;
    }

    /**
     *
     * @param Status
     */
    public void setStatus(String Status) {
        this.Status = Status;
    }

    /**
     *
     * @return
     */
    public String getCounselorName() {
        return CounselorName;
    }

    /**
     *
     * @param CounselorName
     */
    public void setCounselorName(String CounselorName) {
        this.CounselorName = CounselorName;
    }

    /**
     *
     * @return
     */
    public String getCounselorBranch() {
        return CounselorBranch;
    }

    /**
     *
     * @param CounselorBranch
     */
    public void setCounselorBranch(String CounselorBranch) {
        this.CounselorBranch = CounselorBranch;
    }

    /**
     *
     * @return
     */
    public String getStaffUsername() {
        return staffUsername;
    }

    /**
     *
     * @param staffUsername
     */
    public void setStaffUsername(String staffUsername) {
        this.staffUsername = staffUsername;
    }

    /**
     *
     * @return
     */
    public String getStaffBranch() {
        return staffBranch;
    }

    /**
     *
     * @param staffBranch
     */
    public void setStaffBranch(String staffBranch) {
        this.staffBranch = staffBranch;
    }

    @Override
    public String toString() {
        return "EnquirySearchPOJO{" + "enquiryId=" + enquiryId + ", sno=" + sno + ", priority=" + priority + ", contactName=" + contactName + ", contactNumber=" + contactNumber + ", contactEmail=" + contactEmail + ", enquiryBracnch=" + enquiryBracnch + ", country=" + country + ", state=" + state + ", district=" + district + ", enquirySource=" + enquirySource + ", enquiryAssigning=" + enquiryAssigning + ", otherEnquiry=" + otherEnquiry + ", study=" + study + ", work=" + work + ", migration=" + migration + ", training=" + training + ", enquiryDetails=" + enquiryDetails + ", workDetails=" + workDetails + ", migrationDetails=" + migrationDetails + ", trainingDetails=" + trainingDetails + ", studyDetails=" + studyDetails + ", otherStudyDetails=" + otherStudyDetails + ", otherWorkDetails=" + otherWorkDetails + ", otherMigrationDetails=" + otherMigrationDetails + ", OtherTrainingDetails=" + OtherTrainingDetails + ", rating=" + rating + ", edate=" + edate + ", stdcode=" + stdcode + ", enquiryBy=" + enquiryBy + ", remarks=" + remarks + ", Status=" + Status + ", CounselorName=" + CounselorName + ", CounselorBranch=" + CounselorBranch + ", staffUsername=" + staffUsername + ", staffBranch=" + staffBranch + ", contactDetails=" + contactDetails + ", listOfPrograms=" + listOfPrograms + ", impotantFlag=" + impotantFlag + ", proposedTrainingDate=" + proposedTrainingDate + ", ProposedTrainingTime=" + ProposedTrainingTime + ", lastUpdate=" + lastUpdate + '}';
    }

  
}
