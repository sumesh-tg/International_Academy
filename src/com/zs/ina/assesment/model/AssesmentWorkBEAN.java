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
 * @author user
 */
public class AssesmentWorkBEAN {

    private final StringProperty rowId = new SimpleStringProperty();
    private final StringProperty enquiryId = new SimpleStringProperty();
    private final StringProperty work = new SimpleStringProperty();
    private final StringProperty profession = new SimpleStringProperty();
    private final StringProperty location = new SimpleStringProperty();
    private final StringProperty choice = new SimpleStringProperty();
    private final StringProperty spouce_accompany = new SimpleStringProperty();
    private final StringProperty children_accompany = new SimpleStringProperty();
    private final StringProperty special_comment = new SimpleStringProperty();
    private final StringProperty min_salary = new SimpleStringProperty();
    private final StringProperty max_salary = new SimpleStringProperty();
    private final StringProperty curreny = new SimpleStringProperty();
    private final StringProperty country = new SimpleStringProperty();
    private final StringProperty industry = new SimpleStringProperty();
    private final StringProperty employer_choice = new SimpleStringProperty();
    private final StringProperty skillArea = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getSkillArea() {
        return skillArea.get();
    }

    /**
     *
     * @param value
     */
    public void setSkillArea(String value) {
        skillArea.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty skillAreaProperty() {
        return skillArea;
    }

    /**
     *
     * @return
     */
    public String getEmployer_choice() {
        return employer_choice.get();
    }

    /**
     *
     * @param value
     */
    public void setEmployer_choice(String value) {
        employer_choice.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty employer_choiceProperty() {
        return employer_choice;
    }

    /**
     *
     * @return
     */
    public String getIndustry() {
        return industry.get();
    }

    /**
     *
     * @param value
     */
    public void setIndustry(String value) {
        industry.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty industryProperty() {
        return industry;
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
    public String getCurreny() {
        return curreny.get();
    }

    /**
     *
     * @param value
     */
    public void setCurreny(String value) {
        curreny.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty currenyProperty() {
        return curreny;
    }

    /**
     *
     * @return
     */
    public String getMax_salary() {
        return max_salary.get();
    }

    /**
     *
     * @param value
     */
    public void setMax_salary(String value) {
        max_salary.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty max_salaryProperty() {
        return max_salary;
    }

    /**
     *
     * @return
     */
    public String getMin_salary() {
        return min_salary.get();
    }

    /**
     *
     * @param value
     */
    public void setMin_salary(String value) {
        min_salary.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty min_salaryProperty() {
        return min_salary;
    }

    /**
     *
     * @return
     */
    public String getSpecial_comment() {
        return special_comment.get();
    }

    /**
     *
     * @param value
     */
    public void setSpecial_comment(String value) {
        special_comment.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty special_commentProperty() {
        return special_comment;
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
    public String getSpouce_accompany() {
        return spouce_accompany.get();
    }

    /**
     *
     * @param value
     */
    public void setSpouce_accompany(String value) {
        spouce_accompany.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty spouce_accompanyProperty() {
        return spouce_accompany;
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

    @Override
    public String toString() {
        return "AssesmentWorkBEAN{" + "rowId=" + rowId + ", enquiryId=" + enquiryId + ", work=" + work + ", profession=" + profession + ", location=" + location + ", choice=" + choice + ", spouce_accompany=" + spouce_accompany + ", children_accompany=" + children_accompany + ", special_comment=" + special_comment + ", min_salary=" + min_salary + ", max_salary=" + max_salary + ", curreny=" + curreny + ", country=" + country + ", industry=" + industry + ", employer_choice=" + employer_choice + '}';
    }

}
