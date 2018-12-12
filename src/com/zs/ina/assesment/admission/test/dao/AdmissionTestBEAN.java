/*
 * Copyright (C) 2016 100018
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package com.zs.ina.assesment.admission.test.dao;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author 100018
 */
public class AdmissionTestBEAN {
    private final StringProperty rowId = new SimpleStringProperty();
    private final StringProperty enquiryId = new SimpleStringProperty();
    private final StringProperty test = new SimpleStringProperty();
    private final StringProperty overAllScore = new SimpleStringProperty();
    private final StringProperty reading = new SimpleStringProperty();
    private final StringProperty writing = new SimpleStringProperty();
    private final StringProperty speaking = new SimpleStringProperty();
    private final StringProperty listening = new SimpleStringProperty();

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
    public String getOverAllScore() {
        return overAllScore.get();
    }

    /**
     *
     * @param value
     */
    public void setOverAllScore(String value) {
        overAllScore.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty overAllScoreProperty() {
        return overAllScore;
    }

    /**
     *
     * @return
     */
    public String getTest() {
        return test.get();
    }

    /**
     *
     * @param value
     */
    public void setTest(String value) {
        test.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty testProperty() {
        return test;
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
        return "AdmissionTestBEAN{" + "rowId=" + rowId + ", enquiryId=" + enquiryId + ", test=" + test + ", overAllScore=" + overAllScore + '}';
    }
    
}
