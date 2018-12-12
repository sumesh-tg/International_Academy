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
public class AssesmentLanguageTestBEAN {

    private final StringProperty languageTest = new SimpleStringProperty();
    private final StringProperty reading = new SimpleStringProperty();
    private final StringProperty overall = new SimpleStringProperty();
    private final StringProperty speaking = new SimpleStringProperty();
    private final StringProperty listening = new SimpleStringProperty();
    private final StringProperty writing = new SimpleStringProperty();
    private final StringProperty enquiryId = new SimpleStringProperty();
    private final StringProperty rowId = new SimpleStringProperty();
    private final StringProperty rowCount = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getStatus() {
        return status.get();
    }

    /**
     *
     * @param value
     */
    public void setStatus(String value) {
        status.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty statusProperty() {
        return status;
    }

    /**
     *
     * @return
     */
    public String getRowCount() {
        return rowCount.get();
    }

    /**
     *
     * @param value
     */
    public void setRowCount(String value) {
        rowCount.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty rowCountProperty() {
        return rowCount;
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
    public String getWriting() {
        return writing.get();
    }

    /**
     *
     * @param value
     */
    public void setWriting(String value) {
        writing.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty writingProperty() {
        return writing;
    }

    /**
     *
     * @return
     */
    public String getListening() {
        return listening.get();
    }

    /**
     *
     * @param value
     */
    public void setListening(String value) {
        listening.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty listeningProperty() {
        return listening;
    }

    /**
     *
     * @return
     */
    public String getSpeaking() {
        return speaking.get();
    }

    /**
     *
     * @param value
     */
    public void setSpeaking(String value) {
        speaking.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty speakingProperty() {
        return speaking;
    }

    /**
     *
     * @return
     */
    public String getOverall() {
        return overall.get();
    }

    /**
     *
     * @param value
     */
    public void setOverall(String value) {
        overall.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty overallProperty() {
        return overall;
    }

    /**
     *
     * @return
     */
    public String getReading() {
        return reading.get();
    }

    /**
     *
     * @param value
     */
    public void setReading(String value) {
        reading.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty readingProperty() {
        return reading;
    }

    /**
     *
     * @return
     */
    public String getLanguageTest() {
        return languageTest.get();
    }

    /**
     *
     * @param value
     */
    public void setLanguageTest(String value) {
        languageTest.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty languageTestProperty() {
        return languageTest;
    }

    @Override
    public String toString() {
        return "AssesmentLanguageTestBEAN{" + "languageTest=" + languageTest + ", reading=" + reading + ", overall=" + overall + ", speaking=" + speaking + ", listening=" + listening + ", writing=" + writing + ", enquiryId=" + enquiryId + ", rowId=" + rowId + ", rowCount=" + rowCount + '}';
    }

  
   

}
