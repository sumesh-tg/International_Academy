/*
 * Copyright ZoftSolutions(C) 2016 SUMESH T.G <ZoftSolutions>
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
 *  Developed by ZoftSolutions (2015) Company.
 */
package com.zs.ina.enquiry.appointment.counts;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class AppointmentCountsBEAN {

    private final StringProperty countScheduled = new SimpleStringProperty();
    private final StringProperty countCompleted = new SimpleStringProperty();
    private final StringProperty countAttendance = new SimpleStringProperty();
    private final StringProperty countInvitations = new SimpleStringProperty();
    private final StringProperty countToday = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getCountToday() {
        return countToday.get();
    }

    /**
     *
     * @param value
     */
    public void setCountToday(String value) {
        countToday.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty countTodayProperty() {
        return countToday;
    }

    /**
     *
     * @return
     */
    public String getCountInvitations() {
        return countInvitations.get();
    }

    /**
     *
     * @param value
     */
    public void setCountInvitations(String value) {
        countInvitations.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty countInvitationsProperty() {
        return countInvitations;
    }

    /**
     *
     * @return
     */
    public String getCountAttendance() {
        return countAttendance.get();
    }

    /**
     *
     * @param value
     */
    public void setCountAttendance(String value) {
        countAttendance.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty countAttendanceProperty() {
        return countAttendance;
    }

    /**
     *
     * @return
     */
    public String getCountCompleted() {
        return countCompleted.get();
    }

    /**
     *
     * @param value
     */
    public void setCountCompleted(String value) {
        countCompleted.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty countCompletedProperty() {
        return countCompleted;
    }

    /**
     *
     * @return
     */
    public String getCountScheduled() {
        return countScheduled.get();
    }

    /**
     *
     * @param value
     */
    public void setCountScheduled(String value) {
        countScheduled.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty countScheduledProperty() {
        return countScheduled;
    }

    @Override
    public String toString() {
        return "AppointmentCountsBEAN{" + "countScheduled=" + countScheduled + ", countCompleted=" + countCompleted + ", countAttendance=" + countAttendance + ", countInvitations=" + countInvitations + ", countToday=" + countToday + '}';
    }
    
}
