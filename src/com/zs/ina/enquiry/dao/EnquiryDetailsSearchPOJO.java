/*
 * Copyright ZoftSolutions(C) 2015 100018
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
package com.zs.ina.enquiry.dao;

import java.util.Date;
import java.util.Objects;

// Inner Class for Enquiry Details

/**
 *
 * @author 100018
 */

public class EnquiryDetailsSearchPOJO implements java.io.Serializable{

    String enquiryId;
    String conatctName;
    String contactNumber;
    String contactMail;
    String branch;
    String country;
    String state;
    String district;
    String source;
    String assign;
    String method;
    String study;
    String work;
    String migration;
    String training;
    String study1;
    String work1;
    String migration1;
    String training1;
    String studyTxt;
    String workTxt;
    String migrationTxt;
    String trainingTxt;
    String stdCode;
    String joinTime;
    int rating;
    int important;
    Date proposedDate;
    String enquiryStatus;
    String remarks;
    String listOfPrograms;
    String followUpDateTime;
    String enquiryMethod;
    String contact_number2;
    String stdcode2,department;

    /**
     *
     * @return
     */
    public String getDepartment() {
        return department;
    }

    /**
     *
     * @param department
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     *
     * @return
     */
    public String getEnquiryStatus() {
        return enquiryStatus;
    }

    /**
     *
     * @param enquiryStatus
     */
    public void setEnquiryStatus(String enquiryStatus) {
        this.enquiryStatus = enquiryStatus;
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
    public String getStdcode2() {
        return stdcode2;
    }

    /**
     *
     * @param stdcode2
     */
    public void setStdcode2(String stdcode2) {
        this.stdcode2 = stdcode2;
    }

    /**
     *
     * @return
     */
    public String getContact_number2() {
        return contact_number2;
    }

    /**
     *
     * @param contact_number2
     */
    public void setContact_number2(String contact_number2) {
        this.contact_number2 = contact_number2;
    }

    /**
     *
     * @return
     */
    public String getFollowUpDateTime() {
        return followUpDateTime;
    }

    /**
     *
     * @return
     */
    public String getEnquiryMethod() {
        return enquiryMethod;
    }

    /**
     *
     * @param enquiryMethod
     */
    public void setEnquiryMethod(String enquiryMethod) {
        this.enquiryMethod = enquiryMethod;
    }

    /**
     *
     * @return
     */
    public String getMethod() {
        return method;
    }

    /**
     *
     * @param method
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     *
     * @param followUpDateTime
     */
    public void setFollowUpDateTime(String followUpDateTime) {
        this.followUpDateTime = followUpDateTime;
    }

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
    public String getJoinTime() {
        return joinTime;
    }

    /**
     *
     * @param joinTime
     */
    public void setJoinTime(String joinTime) {
        this.joinTime = joinTime;
    }

    /**
     *
     * @return
     */
    public int getImportant() {
        return important;
    }

    /**
     *
     * @param important
     */
    public void setImportant(int important) {
        this.important = important;
    }

    /**
     *
     * @return
     */
    public Date getProposedDate() {
        return proposedDate;
    }

    /**
     *
     * @param proposedDate
     */
    public void setProposedDate(Date proposedDate) {
        this.proposedDate = proposedDate;
    }

    /**
     *
     * @return
     */
    public String getEnquiryId() {
        return enquiryId;
    }

    /**
     *
     * @param enquiryId
     */
    public void setEnquiryId(String enquiryId) {
        this.enquiryId = enquiryId;
    }

    /**
     *
     * @return
     */
    public String getConatctName() {
        return conatctName;
    }

    /**
     *
     * @param conatctName
     */
    public void setConatctName(String conatctName) {
        this.conatctName = conatctName;
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
    public String getContactMail() {
        return contactMail;
    }

    /**
     *
     * @param contactMail
     */
    public void setContactMail(String contactMail) {
        this.contactMail = contactMail;
    }

    /**
     *
     * @return
     */
    public String getBranch() {
        return branch;
    }

    /**
     *
     * @param branch
     */
    public void setBranch(String branch) {
        this.branch = branch;
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
    public String getSource() {
        return source;
    }

    /**
     *
     * @param source
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     *
     * @return
     */
    public String getAssign() {
        return assign;
    }

    /**
     *
     * @param assign
     */
    public void setAssign(String assign) {
        this.assign = assign;
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
    public String getStudy1() {
        return study1;
    }

    /**
     *
     * @param study1
     */
    public void setStudy1(String study1) {
        this.study1 = study1;
    }

    /**
     *
     * @return
     */
    public String getWork1() {
        return work1;
    }

    /**
     *
     * @param work1
     */
    public void setWork1(String work1) {
        this.work1 = work1;
    }

    /**
     *
     * @return
     */
    public String getMigration1() {
        return migration1;
    }

    /**
     *
     * @param migration1
     */
    public void setMigration1(String migration1) {
        this.migration1 = migration1;
    }

    /**
     *
     * @return
     */
    public String getTraining1() {
        return training1;
    }

    /**
     *
     * @param training1
     */
    public void setTraining1(String training1) {
        this.training1 = training1;
    }

    /**
     *
     * @return
     */
    public String getStudyTxt() {
        return studyTxt;
    }

    /**
     *
     * @param studyTxt
     */
    public void setStudyTxt(String studyTxt) {
        this.studyTxt = studyTxt;
    }

    /**
     *
     * @return
     */
    public String getWorkTxt() {
        return workTxt;
    }

    /**
     *
     * @param workTxt
     */
    public void setWorkTxt(String workTxt) {
        this.workTxt = workTxt;
    }

    /**
     *
     * @return
     */
    public String getMigrationTxt() {
        return migrationTxt;
    }

    /**
     *
     * @param migrationTxt
     */
    public void setMigrationTxt(String migrationTxt) {
        this.migrationTxt = migrationTxt;
    }

    /**
     *
     * @return
     */
    public String getTrainingTxt() {
        return trainingTxt;
    }

    /**
     *
     * @param trainingTxt
     */
    public void setTrainingTxt(String trainingTxt) {
        this.trainingTxt = trainingTxt;
    }

    /**
     *
     * @return
     */
    public int getRating() {
        return rating;
    }

    /**
     *
     * @param rating
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     *
     * @return
     */
    public String getStdCode() {
        return stdCode;
    }

    /**
     *
     * @param stdCode
     */
    public void setStdCode(String stdCode) {
        this.stdCode = stdCode;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.conatctName);
        hash = 29 * hash + Objects.hashCode(this.contactNumber);
        hash = 29 * hash + Objects.hashCode(this.contactMail);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EnquiryDetailsSearchPOJO other = (EnquiryDetailsSearchPOJO) obj;
        if (!Objects.equals(this.conatctName, other.conatctName)) {
            return false;
        }
        if (!Objects.equals(this.contactNumber, other.contactNumber)) {
            return false;
        }
        if (!Objects.equals(this.contactMail, other.contactMail)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return conatctName + " " + contactNumber + " " + contact_number2 + " " + contactMail + " " + branch + " " + assign + "";
    }
    
}
