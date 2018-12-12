/*
 * Copyright (C) 2016 100035
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
package com.zs.ina.enquiry.appointment.dao;

/**
 *
 * @author 100035
 */
public class UserPOJO {

    String username;
    String firstName;
    String lastName;
    String participationStatus;
    String remarks;

    /**
     *
     * @return
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     *
     * @param remarks
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     *
     * @return
     */
    public String getParticipationStatus() {
        return participationStatus;
    }

    /**
     *
     * @param participationStatus
     */
    public void setParticipationStatus(String participationStatus) {
        this.participationStatus = participationStatus;
    }

    /**
     *
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    /**
     *
     * @param username
     * @param firstName
     * @param lastName
     * @param participationStatus
     * @param remarks
     */
    public UserPOJO(String username, String firstName, String lastName, String participationStatus, String remarks) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.participationStatus = participationStatus;
        this.remarks = remarks;
    }

    /**
     *
     */
    public UserPOJO() {

    }

}
