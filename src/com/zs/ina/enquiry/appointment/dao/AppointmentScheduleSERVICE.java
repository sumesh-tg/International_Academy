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
public class AppointmentScheduleSERVICE {

    AppointmentScheduleBEAN appointmentScheduleBEAN = new AppointmentScheduleBEAN();
    AppointmentScheduleDAO appointmentScheduleDAO;

    /**
     *
     * @param appointmentScheduleDAO
     */
    public AppointmentScheduleSERVICE(AppointmentScheduleIMPL appointmentScheduleDAO) {
        this.appointmentScheduleDAO = appointmentScheduleDAO;
    }

    /**
     *
     * @param appointmentScheduleBEAN
     * @return
     */
    public int insertAppointment(AppointmentScheduleBEAN appointmentScheduleBEAN) {
        return appointmentScheduleDAO.insertApponitment(appointmentScheduleBEAN);

    }

//    public ObservableList<AppointmentScheduleBEAN> retrieveAllApponitments(String CUR_USERNAME, String CUR_BRANCH) {
//        return appointmentScheduleDAO.retrieveAllApponitments(CUR_USERNAME, CUR_BRANCH);
//    }

    /**
     *
     * @param CUR_USERNAME
     * @param CUR_BRANCH
     * @return
     */
    public ObservableList<AppointmentScheduleBEAN> retrieveApponitmentsAccepted(String CUR_USERNAME, String CUR_BRANCH) {
        return appointmentScheduleDAO.retrieveApponitmentsAccepted(CUR_USERNAME, CUR_BRANCH);
    }

    /**
     *
     * @param CUR_USERNAME
     * @param CUR_BRANCH
     * @return
     */
    public ObservableList<AppointmentScheduleBEAN> retrieveScheduledEvents(String CUR_USERNAME, String CUR_BRANCH) {
        return appointmentScheduleDAO.retrieveScheduledEvents(CUR_USERNAME, CUR_BRANCH);
    }

    /**
     *
     * @param CUR_USERNAME
     * @param CUR_BRANCH
     * @return
     */
    public ObservableList<AppointmentScheduleBEAN> retrieveCompletedEvents(String CUR_USERNAME, String CUR_BRANCH) {
        return appointmentScheduleDAO.retrieveCompletedEvents(CUR_USERNAME, CUR_BRANCH);
    }

    /**
     *
     * @param CUR_USERNAME
     * @param CUR_BRANCH
     * @param noOfDays
     * @return
     */
    public ObservableList<AppointmentScheduleBEAN> retrieveWeekEvents(String CUR_USERNAME, String CUR_BRANCH, int noOfDays) {
        return appointmentScheduleDAO.retrieveWeekEvents(CUR_USERNAME, CUR_BRANCH, noOfDays); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param CUR_USERNAME
     * @param CUR_BRANCH
     * @return
     */
    public ObservableList<AppointmentScheduleBEAN> viewInvitations(String CUR_USERNAME, String CUR_BRANCH) {
        return appointmentScheduleDAO.viewInvitations(CUR_USERNAME, CUR_BRANCH); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param appointmentScheduleBEAN
     * @return
     */
    public int updateInvitationStatus(AppointmentScheduleBEAN appointmentScheduleBEAN) {
        return appointmentScheduleDAO.updateInvitationStatus(appointmentScheduleBEAN); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param appointmentScheduleBEAN
     * @return
     */
    public int updateRemarks(AppointmentScheduleBEAN appointmentScheduleBEAN) {
        return appointmentScheduleDAO.updateRemarks(appointmentScheduleBEAN);
    }

    /**
     *
     * @param CUR_USERNAME
     * @param CUR_BRANCH
     * @return
     */
    public ObservableList<AppointmentScheduleBEAN> viewEventsForAnchor(String CUR_USERNAME, String CUR_BRANCH) {
        return appointmentScheduleDAO.viewEventsForAnchor(CUR_USERNAME, CUR_BRANCH);
    }

    /**
     *
     * @param CUR_USERNAME
     * @param CUR_BRANCH
     * @param appointmentScheduleBEAN
     * @return
     */
    public ObservableList<UserPOJO> viewParticipants(String CUR_USERNAME, String CUR_BRANCH, AppointmentScheduleBEAN appointmentScheduleBEAN) {

        return appointmentScheduleDAO.viewParticipants(CUR_USERNAME, CUR_BRANCH, appointmentScheduleBEAN);
    }

    /**
     *
     * @param participantsIdList
     * @param meeting_id
     * @return
     */
    public int updateParticipationStatus(ObservableList<UserPOJO> participantsIdList, String meeting_id) {
        return appointmentScheduleDAO.updateParticipationStatus(participantsIdList, meeting_id);
    }

    /**
     *
     * @param CUR_USERNAME
     * @param CUR_BRANCH
     * @return
     */
    public ObservableList<AppointmentScheduleBEAN> viewTodayEvents(String CUR_USERNAME, String CUR_BRANCH) {
        return appointmentScheduleDAO.viewTodayEvents(CUR_USERNAME, CUR_BRANCH);
    }

    /**
     *
     * @param CUR_USERNAME
     * @param CUR_BRANCH
     * @return
     */
    public int countInvitations(String CUR_USERNAME, String CUR_BRANCH) {
        return appointmentScheduleDAO.countInvitations(CUR_USERNAME, CUR_BRANCH);
    }

    /**
     *
     * @param CUR_USERNAME
     * @param CUR_BRANCH
     * @return
     */
    public ObservableList<AppointmentScheduleBEAN> viewTodayNextEvents(String CUR_USERNAME, String CUR_BRANCH) {
        return appointmentScheduleDAO.viewTodayNextEvents(CUR_USERNAME, CUR_BRANCH);
    }

    /**
     *
     * @param appointmentScheduleBEAN
     * @return
     */
    public int updateSnoozeValue(AppointmentScheduleBEAN appointmentScheduleBEAN) {
         return appointmentScheduleDAO.updateSnoozeValue(appointmentScheduleBEAN);
    }

    /**
     *
     * @param CUR_USERNAME
     * @param CUR_BRANCH
     * @return
     */
    public ObservableList<AppointmentScheduleBEAN> viewTodayNextEventsSnoozed(String CUR_USERNAME, String CUR_BRANCH) {
        return appointmentScheduleDAO.viewTodayNextEventsSnoozed(CUR_USERNAME, CUR_BRANCH);
    }

    /**
     *
     * @param appointmentScheduleBEAN
     * @param CUR_USERNAME
     * @param CUR_BRANCH
     * @return
     */
    public int updateMeetingStatus(AppointmentScheduleBEAN appointmentScheduleBEAN, String CUR_USERNAME, String CUR_BRANCH) {
       return appointmentScheduleDAO.updateMeetingStatus(appointmentScheduleBEAN,CUR_USERNAME,CUR_BRANCH);
    }

    /**
     *
     * @param appointmentScheduleBEAN
     * @param CUR_USERNAME
     * @param CUR_BRANCH
     * @return
     */
    public int updateAllInvitationStatus(AppointmentScheduleBEAN appointmentScheduleBEAN, String CUR_USERNAME, String CUR_BRANCH) {
      return appointmentScheduleDAO.updateAllInvitationStatus(appointmentScheduleBEAN,CUR_USERNAME,CUR_BRANCH);
    }

}
