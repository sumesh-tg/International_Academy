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
public class AssesmentOtherTestBEAN {

    private final StringProperty testName = new SimpleStringProperty();
    private final StringProperty testScore = new SimpleStringProperty();
    private final StringProperty testStatus = new SimpleStringProperty();
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
    public String getTestStatus() {
        return testStatus.get();
    }

    /**
     *
     * @param value
     */
    public void setTestStatus(String value) {
        testStatus.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty testStatusProperty() {
        return testStatus;
    }

    /**
     *
     * @return
     */
    public String getTestScore() {
        return testScore.get();
    }

    /**
     *
     * @param value
     */
    public void setTestScore(String value) {
        testScore.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty testScoreProperty() {
        return testScore;
    }

    /**
     *
     * @return
     */
    public String getTestName() {
        return testName.get();
    }

    /**
     *
     * @param value
     */
    public void setTestName(String value) {
        testName.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty testNameProperty() {
        return testName;
    }

    @Override
    public String toString() {
        return "AssesmentOtherTestBEAN{" + "testName=" + testName + ", testScore=" + testScore + ", testStatus=" + testStatus + '}';
    }

}
