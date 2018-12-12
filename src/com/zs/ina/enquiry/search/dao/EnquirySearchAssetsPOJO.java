/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.enquiry.search.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author zscomp1
 */
public class EnquirySearchAssetsPOJO {

    ObservableList branch = FXCollections.observableArrayList();
    ObservableList districs = FXCollections.observableArrayList();
    ObservableList programLevel = FXCollections.observableArrayList();
    ObservableList programField = FXCollections.observableArrayList();

    /**
     *
     * @return
     */
    public ObservableList getProgramField() {
        return programField;
    }

    /**
     *
     * @param programField
     */
    public void setProgramField(ObservableList programField) {
        this.programField = programField;
    }

    /**
     *
     * @return
     */
    public ObservableList getProgramLevel() {
        return programLevel;
    }

    /**
     *
     * @param programLevel
     */
    public void setProgramLevel(ObservableList programLevel) {
        this.programLevel = programLevel;
    }

    /**
     *
     * @return
     */
    public ObservableList getDistrics() {
        return districs;
    }

    /**
     *
     * @param districs
     */
    public void setDistrics(ObservableList districs) {
        this.districs = districs;
    }

    /**
     *
     * @return
     */
    public ObservableList getBranch() {
        return branch;
    }

    /**
     *
     * @param branch
     */
    public void setBranch(ObservableList branch) {
        this.branch = branch;
    }

}
