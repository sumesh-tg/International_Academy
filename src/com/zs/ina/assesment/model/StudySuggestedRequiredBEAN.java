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
 * @author zsuser1
 */
public class StudySuggestedRequiredBEAN {
    private final StringProperty study_pgm_id = new SimpleStringProperty();
    private final StringProperty country = new SimpleStringProperty();
    private final StringProperty program_level = new SimpleStringProperty();
    private final StringProperty program_field = new SimpleStringProperty();
    private final StringProperty intake = new SimpleStringProperty();
    private final StringProperty institution = new SimpleStringProperty();
    private final StringProperty enquiry_id = new SimpleStringProperty();
    private final StringProperty study = new SimpleStringProperty();
    private final StringProperty otherUniversity = new SimpleStringProperty();
    private final StringProperty location = new SimpleStringProperty();
    private final StringProperty currency = new SimpleStringProperty();
    private final StringProperty amount = new SimpleStringProperty();
    private final StringProperty sem_fees = new SimpleStringProperty();
    private final StringProperty choice = new SimpleStringProperty();
    private final StringProperty remarks = new SimpleStringProperty();
    private final StringProperty children_accompany = new SimpleStringProperty();
    private final StringProperty spouse_accompany = new SimpleStringProperty();
    private final StringProperty rowId = new SimpleStringProperty();

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
    public String getSpouse_accompany() {
        return spouse_accompany.get();
    }

    /**
     *
     * @param value
     */
    public void setSpouse_accompany(String value) {
        spouse_accompany.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty spouse_accompanyProperty() {
        return spouse_accompany;
    }

    /**
     *
     * @return
     */
    public String getChildren_accompany() {
        return children_accompany.get();
    }

    /**
     *
     * @param value
     */
    public void setChildren_accompany(String value) {
        children_accompany.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty children_accompanyProperty() {
        return children_accompany;
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
    public String getChoice() {
        return choice.get();
    }

    /**
     *
     * @param value
     */
    public void setChoice(String value) {
        choice.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty choiceProperty() {
        return choice;
    }

    /**
     *
     * @return
     */
    public String getSem_fees() {
        return sem_fees.get();
    }

    /**
     *
     * @param value
     */
    public void setSem_fees(String value) {
        sem_fees.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty sem_feesProperty() {
        return sem_fees;
    }

    /**
     *
     * @return
     */
    public String getAmount() {
        return amount.get();
    }

    /**
     *
     * @param value
     */
    public void setAmount(String value) {
        amount.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty amountProperty() {
        return amount;
    }

    /**
     *
     * @return
     */
    public String getCurrency() {
        return currency.get();
    }

    /**
     *
     * @param value
     */
    public void setCurrency(String value) {
        currency.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty currencyProperty() {
        return currency;
    }

    /**
     *
     * @return
     */
    public String getLocation() {
        return location.get();
    }

    /**
     *
     * @param value
     */
    public void setLocation(String value) {
        location.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty locationProperty() {
        return location;
    }

    /**
     *
     * @return
     */
    public String getOtherUniversity() {
        return otherUniversity.get();
    }

    /**
     *
     * @param value
     */
    public void setOtherUniversity(String value) {
        otherUniversity.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty otherUniversityProperty() {
        return otherUniversity;
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
    public String getEnquiry_id() {
        return enquiry_id.get();
    }

    /**
     *
     * @param value
     */
    public void setEnquiry_id(String value) {
        enquiry_id.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty enquiry_idProperty() {
        return enquiry_id;
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
    public String getProgram_field() {
        return program_field.get();
    }

    /**
     *
     * @param value
     */
    public void setProgram_field(String value) {
        program_field.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty program_fieldProperty() {
        return program_field;
    }

    /**
     *
     * @return
     */
    public String getProgram_level() {
        return program_level.get();
    }

    /**
     *
     * @param value
     */
    public void setProgram_level(String value) {
        program_level.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty program_levelProperty() {
        return program_level;
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
    public String getStudy_pgm_id() {
        return study_pgm_id.get();
    }

    /**
     *
     * @param value
     */
    public void setStudy_pgm_id(String value) {
        study_pgm_id.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty study_pgm_idProperty() {
        return study_pgm_id;
    }

    @Override
    public String toString() {
        return "ProgramSuggestedRequiredBEAN{" + "study_pgm_id=" + study_pgm_id + ", country=" + country + ", program_level=" + program_level + ", program_field=" + program_field + ", intake=" + intake + ", institution=" + institution + ", enquiry_id=" + enquiry_id + ", study=" + study + ", otherUniversity=" + otherUniversity + ", location=" + location + ", currency=" + currency + ", amount=" + amount + ", sem_fees=" + sem_fees + ", choice=" + choice + ", remarks=" + remarks + '}';
    }
    
}
