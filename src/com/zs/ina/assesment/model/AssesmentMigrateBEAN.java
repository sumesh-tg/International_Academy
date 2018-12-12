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
public class AssesmentMigrateBEAN {

    private final StringProperty rowId = new SimpleStringProperty();
    private final StringProperty enquiryId = new SimpleStringProperty();
    private final StringProperty migrate = new SimpleStringProperty();
    private final StringProperty category = new SimpleStringProperty();
    private final StringProperty profession = new SimpleStringProperty();
    private final StringProperty choice = new SimpleStringProperty();
    private final StringProperty spouseAccompany = new SimpleStringProperty();
    private final StringProperty childrenAccompany = new SimpleStringProperty();
    private final StringProperty comment = new SimpleStringProperty();
    private final StringProperty country = new SimpleStringProperty();
    private final StringProperty applicationType = new SimpleStringProperty();
    private final StringProperty location = new SimpleStringProperty();

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
    public String getComment() {
        return comment.get();
    }

    /**
     *
     * @param value
     */
    public void setComment(String value) {
        comment.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty commentProperty() {
        return comment;
    }

    /**
     *
     * @return
     */
    public String getChildrenAccompany() {
        return childrenAccompany.get();
    }

    /**
     *
     * @param value
     */
    public void setChildrenAccompany(String value) {
        childrenAccompany.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty childrenAccompanyProperty() {
        return childrenAccompany;
    }

    /**
     *
     * @return
     */
    public String getSpouseAccompany() {
        return spouseAccompany.get();
    }

    /**
     *
     * @param value
     */
    public void setSpouseAccompany(String value) {
        spouseAccompany.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty spouseAccompanyProperty() {
        return spouseAccompany;
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
    public String getCategory() {
        return category.get();
    }

    /**
     *
     * @param value
     */
    public void setCategory(String value) {
        category.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty categoryProperty() {
        return category;
    }

    /**
     *
     * @return
     */
    public String getMigrate() {
        return migrate.get();
    }

    /**
     *
     * @param value
     */
    public void setMigrate(String value) {
        migrate.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty migrateProperty() {
        return migrate;
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
        return "AssesmentMigrateBEAN{" + "rowId=" + rowId + ", enquiryId=" + enquiryId + ", migrate=" + migrate + ", category=" + category + ", profession=" + profession + ", choice=" + choice + ", spouseAccompany=" + spouseAccompany + ", childrenAccompany=" + childrenAccompany + ", comment=" + comment + ", country=" + country + ", applicationType=" + applicationType + '}';
    }

}
