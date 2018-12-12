/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.assesment.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author sumesh
 */
public class AssessmentPersonBEAN {

    private final StringProperty rowId = new SimpleStringProperty();
    private final StringProperty gender = new SimpleStringProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty phone = new SimpleStringProperty();
    private final StringProperty email = new SimpleStringProperty();
    private final StringProperty state = new SimpleStringProperty();
    private final StringProperty district = new SimpleStringProperty();
    private final StringProperty branch = new SimpleStringProperty();
    private final StringProperty maritalStatus = new SimpleStringProperty();
    private final StringProperty phone2 = new SimpleStringProperty();
    private final StringProperty stdCode1 = new SimpleStringProperty();
    private final StringProperty stdCode2 = new SimpleStringProperty();
    private final StringProperty country = new SimpleStringProperty();
    private final StringProperty passportNo = new SimpleStringProperty();
    private final StringProperty dob = new SimpleStringProperty();
    private final StringProperty age = new SimpleStringProperty();
    private final StringProperty enquiryId = new SimpleStringProperty();

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
    public String getStdCode2() {
        return stdCode2.get();
    }

    /**
     *
     * @param value
     */
    public void setStdCode2(String value) {
        stdCode2.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty stdCode2Property() {
        return stdCode2;
    }

    /**
     *
     * @return
     */
    public String getStdCode1() {
        return stdCode1.get();
    }

    /**
     *
     * @param value
     */
    public void setStdCode1(String value) {
        stdCode1.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty stdCode1Property() {
        return stdCode1;
    }

    /**
     *
     * @return
     */
    public String getPhone2() {
        return phone2.get();
    }

    /**
     *
     * @param value
     */
    public void setPhone2(String value) {
        phone2.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty phone2Property() {
        return phone2;
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
    public String getEmail() {
        return email.get();
    }

    /**
     *
     * @param value
     */
    public void setEmail(String value) {
        email.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty emailProperty() {
        return email;
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

    @Override
    public String toString() {
        return "AssessmentPersonBEAN{" + "gender=" + gender + ", name=" + name + ", phone=" + phone + ", email=" + email + ", state=" + state + ", district=" + district + ", branch=" + branch + ", maritalStatus=" + maritalStatus + ", phone2=" + phone2 + ", stdCode1=" + stdCode1 + ", stdCode2=" + stdCode2 + ", country=" + country + ", passportNo=" + passportNo + ", dob=" + dob + ", age=" + age + '}';
    }

}
