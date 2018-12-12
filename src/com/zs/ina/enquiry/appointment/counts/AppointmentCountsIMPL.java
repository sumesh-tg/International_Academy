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

import com.zs.ina.utility.ClosingResourcesInDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.log4j.Logger;
import zs.com.ina.db.mysql.pool.DBPool;

/**
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class AppointmentCountsIMPL implements AppoinmentCountsDAO {

    Logger logger = Logger.getLogger(AppointmentCountsIMPL.class);

    /**
     *
     * @param username
     * @param branch
     * @param role
     * @return
     */
    @Override
    public AppointmentCountsBEAN retrieveAppointmentCounts(String username, String branch, String role) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        AppointmentCountsBEAN appointmentCountsBEAN = new AppointmentCountsBEAN();
        try {
            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();
            String query = "SELECT DISTINCT\n"
                    + "(\n"
                    + "SELECT\n"
                    + "COUNT(meeting_id)\n"
                    + "FROM\n"
                    + "appointment_master am\n"
                    + "WHERE\n"
                    + " am.created_user = '" + username + "'\n"
                    + " AND start_date_time > NOW()\n"
                    + " AND am.meeting_status = 'scheduled'\n"
                    + ") AS count_scheduled,\n"
                    + "(\n"
                    + "SELECT\n"
                    + "COUNT(meeting_id)\n"
                    + "FROM\n"
                    + "appointment_master am\n"
                    + "WHERE\n"
                    + "created_user = '" + username + "'\n"
                    + "AND end_date_time < NOW()\n"
                    + ") AS count_completed,\n"
                    + "(\n"
                    + "SELECT\n"
                    + "COUNT(meeting_id)\n"
                    + "FROM\n"
                    + "appointment_master am\n"
                    + "WHERE\n"
                    + "anchor = '" + username + "'\n"
                    + ") AS count_attendance,\n"
                    + "(\n"
                    + "SELECT\n"
                    + "COUNT(participant_id)\n"
                    + "FROM\n"
                    + "appointment_details ad\n"
                    + "RIGHT JOIN appointment_master am ON ad.meeting_id = am.meeting_id\n"
                    + "WHERE\n"
                    + "ad.participant_id = '" + username + "'\n"
                    + "AND am.start_date_time > NOW()\n"
                    + "AND ad.invitation_status IS NULL\n"
                    + ") AS count_invitations ,\n"
                    + "\n"
                    + "(\n"
                    + "SELECT\n"
                    + "COUNT(participant_id)\n"
                    + "FROM\n"
                    + "appointment_details ad\n"
                    + "RIGHT JOIN appointment_master am ON ad.meeting_id = am.meeting_id\n"
                    + "WHERE\n"
                    + "ad.participant_id = '" + username + "'\n"
                    + "AND  DATE_FORMAT(am.start_date_time,'%Y-%m-%d') = CURRENT_DATE ()\n"
                    + "AND ad.invitation_status ='accepted'\n"
                    + ") AS count_today\n"
                    + "\n"
                    + "FROM\n"
                    + "appointment_details ad\n"
                    + "RIGHT JOIN appointment_master am ON ad.meeting_id = am.meeting_id\n"
                    + "WHERE\n"
                    + "am.meeting_status = 'scheduled'";
            rs = stmt.executeQuery(query);
            if (rs.next()) {
                appointmentCountsBEAN.setCountScheduled(rs.getString("count_scheduled"));
                appointmentCountsBEAN.setCountCompleted(rs.getString("count_completed"));
                appointmentCountsBEAN.setCountAttendance(rs.getString("count_attendance"));
                appointmentCountsBEAN.setCountInvitations(rs.getString("count_invitations"));
                appointmentCountsBEAN.setCountToday(rs.getString("count_today"));
            }

        } catch (SQLException sqle) {
            logger.error(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return appointmentCountsBEAN;
    }

}
