/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.enquiry.search.dao;

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
    private final StringProperty enquiryStatus = new SimpleStringProperty();
    private final StringProperty enquiryMethod = new SimpleStringProperty();
    private final StringProperty dateFrom = new SimpleStringProperty();
    private final StringProperty dateTo = new SimpleStringProperty();

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
    public String getEnquiryStatus() {
        return enquiryStatus.get();
    }

    /**
     *
     * @param value
     */
    public void setEnquiryStatus(String value) {
        enquiryStatus.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty enquiryStatusProperty() {
        return enquiryStatus;
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
        return "SearchQueryBEAN{" + "nameOrPhone=" + nameOrPhone + ", district=" + district + ", branch=" + branch + ", assignedTo=" + assignedTo + ", enquiryFor=" + enquiryFor + ", enquirySource=" + enquirySource + ", programLevel=" + programLevel + ", programField=" + programField + ", enquiryStatus=" + enquiryStatus + ", enquiryMethod=" + enquiryMethod + ", dateFrom=" + dateFrom + ", dateTo=" + dateTo + '}';
    }

}
