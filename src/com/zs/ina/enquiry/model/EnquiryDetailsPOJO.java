/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.enquiry.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author user
 */
public class EnquiryDetailsPOJO {

    private final StringProperty enquiryID = new SimpleStringProperty();
    private final StringProperty contactName = new SimpleStringProperty();
    private final StringProperty stdcode = new SimpleStringProperty();
    private final StringProperty stdcode1 = new SimpleStringProperty();
    private final StringProperty contactNumber1 = new SimpleStringProperty();
    private final StringProperty contactNumber2 = new SimpleStringProperty();
    private final StringProperty contactEmail = new SimpleStringProperty();
    private final StringProperty bracnch = new SimpleStringProperty();
    private final StringProperty country = new SimpleStringProperty();
    private final StringProperty state = new SimpleStringProperty();
    private final StringProperty district = new SimpleStringProperty();
    private final StringProperty enquirySource = new SimpleStringProperty();
    private final StringProperty enquiryMethod = new SimpleStringProperty();
    private final StringProperty enquiryAssigning = new SimpleStringProperty();
    private final StringProperty otherEnquiry = new SimpleStringProperty();
    private final StringProperty enquiryDetails = new SimpleStringProperty();
    private final StringProperty staffUsername = new SimpleStringProperty();
    private final StringProperty staffBranch = new SimpleStringProperty();
    private final StringProperty department = new SimpleStringProperty();
    private final StringProperty enquiryId = new SimpleStringProperty();
    private final StringProperty rating = new SimpleStringProperty();
    private final StringProperty edate = new SimpleStringProperty();

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
    public String getStdcode() {
        return stdcode.get();
    }

    /**
     *
     * @param value
     */
    public void setStdcode(String value) {
        stdcode.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty stdcodeProperty() {
        return stdcode;
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
    public String getEnquiryAssigning() {
        return enquiryAssigning.get();
    }

    /**
     *
     * @param value
     */
    public void setEnquiryAssigning(String value) {
        enquiryAssigning.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty enquiryAssigningProperty() {
        return enquiryAssigning;
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
    public String getContactNumber() {
        return contactNumber1.get();
    }

    /**
     *
     * @param value
     */
    public void setContactNumber(String value) {
        contactNumber1.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty contactNumberProperty() {
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

    @Override
    public String toString() {
        return "EnquiryDetailsPOJO{" + "contactName=" + contactName + ", stdcode=" + stdcode + ", stdcode1=" + stdcode1 + ", contactNumber1=" + contactNumber1 + ", contactNumber2=" + contactNumber2 + ", contactEmail=" + contactEmail + ", bracnch=" + bracnch + ", country=" + country + ", state=" + state + ", district=" + district + ", enquirySource=" + enquirySource + ", enquiryMethod=" + enquiryMethod + ", enquiryAssigning=" + enquiryAssigning + ", otherEnquiry=" + otherEnquiry + ", enquiryDetails=" + enquiryDetails + ", staffUsername=" + staffUsername + ", staffBranch=" + staffBranch + ", department=" + department + ", enquiryId=" + enquiryId + ", rating=" + rating + ", edate=" + edate + '}';
    }



}
