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

import javafx.collections.ObservableList;

/**
 *
 * @author 100035
 */
public interface AppointmentScheduleDAO {

    /*================= Display invitation count ===================== */

    /**
     *
     * @param User
     * @param Branch
     * @return
     */

    public int countInvitations(String User, String Branch);

    /**
     *
     * @param appointmentScheduleBEAN
     * @return
     */
    public int insertApponitment(AppointmentScheduleBEAN appointmentScheduleBEAN);

    /**
     *
     * @param User
     * @param Branch
     * @return
     */
    public ObservableList<AppointmentScheduleBEAN> retrieveScheduledEvents(String User, String Branch);

    /**
     *
     * @param User
     * @param Branch
     * @return
     */
    public ObservableList<AppointmentScheduleBEAN> retrieveCompletedEvents(String User, String Branch);

    /**
     *
     * @param User
     * @param Branch
     * @return
     */
    public ObservableList<AppointmentScheduleBEAN> retrieveApponitmentsAccepted(String User, String Branch);

    /**
     *
     * @param CUR_USERNAME
     * @param CUR_BRANCH
     * @param noOfDays
     * @return
     */
    public ObservableList<AppointmentScheduleBEAN> retrieveWeekEvents(String CUR_USERNAME, String CUR_BRANCH, int noOfDays);

    /*================= Invitation Button ===================== */

    /**
     *
     * @param User
     * @param Branch
     * @return
     */

    public ObservableList<AppointmentScheduleBEAN> viewInvitations(String User, String Branch);

    /**
     *
     * @param appointmentScheduleBEAN
     * @return
     */
    public int updateInvitationStatus(AppointmentScheduleBEAN appointmentScheduleBEAN);

    /**
     *
     * @param appointmentScheduleBEAN
     * @return
     */
    public int updateRemarks(AppointmentScheduleBEAN appointmentScheduleBEAN);

    /*================= Attendance Button ===================== */

    /**
     *
     * @param User
     * @param Branch
     * @return
     */

    public ObservableList<AppointmentScheduleBEAN> viewEventsForAnchor(String User, String Branch);

    /**
     *
     * @param User
     * @param Branch
     * @param appointmentScheduleBEAN
     * @return
     */
    public ObservableList<UserPOJO> viewParticipants(String User, String Branch, AppointmentScheduleBEAN appointmentScheduleBEAN);

    /**
     *
     * @param participantsIdList
     * @param meeting_id
     * @return
     */
    public int updateParticipationStatus(ObservableList<UserPOJO> participantsIdList, String meeting_id);

    /*================= Events Today on Left ===================== */

    /**
     *
     * @param CUR_USERNAME
     * @param CUR_BRANCH
     * @return
     */

    public ObservableList<AppointmentScheduleBEAN> viewTodayEvents(String CUR_USERNAME, String CUR_BRANCH);

    /**
     *
     * @param CUR_USERNAME
     * @param CUR_BRANCH
     * @return
     */
    public ObservableList<AppointmentScheduleBEAN> viewTodayNextEvents(String CUR_USERNAME, String CUR_BRANCH);

    /**
     *
     * @param CUR_USERNAME
     * @param CUR_BRANCH
     * @return
     */
    public ObservableList<AppointmentScheduleBEAN> viewTodayNextEventsSnoozed(String CUR_USERNAME, String CUR_BRANCH);

    /**
     *
     * @param appointmentScheduleBEAN
     * @return
     */
    public int updateSnoozeValue(AppointmentScheduleBEAN appointmentScheduleBEAN);
    
      /*================= Event Cancel ===================== */

    /**
     *
     * @param appointmentScheduleBEAN
     * @param CUR_USERNAME
     * @param CUR_BRANCH
     * @return
     */


    public int updateMeetingStatus(AppointmentScheduleBEAN appointmentScheduleBEAN, String CUR_USERNAME, String CUR_BRANCH);

    /**
     *
     * @param appointmentScheduleBEAN
     * @param CUR_USERNAME
     * @param CUR_BRANCH
     * @return
     */
    public int updateAllInvitationStatus(AppointmentScheduleBEAN appointmentScheduleBEAN, String CUR_USERNAME, String CUR_BRANCH);

}
