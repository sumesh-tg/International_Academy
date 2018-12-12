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
public class AssessmentChildBEAN {
    private final StringProperty row_id = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getRow_id() {
        return row_id.get();
    }

    /**
     *
     * @param value
     */
    public void setRow_id(String value) {
        row_id.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty row_idProperty() {
        return row_id;
    }
    private final StringProperty name = new SimpleStringProperty();

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
    private final StringProperty age = new SimpleStringProperty();

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
    private final StringProperty gender = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getSex() {
        return gender.get();
    }

    /**
     *
     * @param value
     */
    public void setSex(String value) {
        gender.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty sexProperty() {
        return gender;
    }
    private final StringProperty enquiryId = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getEnquiry_id() {
        return enquiryId.get();
    }

    /**
     *
     * @param value
     */
    public void setEnquiry_id(String value) {
        enquiryId.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty enquiry_idProperty() {
        return enquiryId;
    }
    private final StringProperty countryRelative = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getCountry_relative() {
        return countryRelative.get();
    }

    /**
     *
     * @param value
     */
    public void setCountry_relative(String value) {
        countryRelative.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty country_relativeProperty() {
        return countryRelative;
    }
    private final StringProperty relation = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getRelation() {
        return relation.get();
    }

    /**
     *
     * @param value
     */
    public void setRelation(String value) {
        relation.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty relationProperty() {
        return relation;
    }

    @Override
    public String toString() {
        return "AssessmenChildBEAN{" + "row_id=" + row_id + ", name=" + name + ", age=" + age + ", sex=" + gender + ", enquiry_id=" + enquiryId + ", country_relative=" + countryRelative + ", relation=" + relation + '}';
    }
    
}
