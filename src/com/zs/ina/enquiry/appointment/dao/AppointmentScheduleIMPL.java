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

import com.zs.ina.common.TimeAgoFunctions;
import com.zs.ina.common.UiiDGenerator;
import com.zs.ina.context.Context;
import com.zs.ina.utility.ClosingResourcesInDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.log4j.Logger;
import zs.com.ina.db.mysql.pool.DBPool;

/**
 *
 * @author 100035
 */
public class AppointmentScheduleIMPL implements AppointmentScheduleDAO {

    Logger logger = Logger.getLogger(AppointmentScheduleIMPL.class);
    final static int REMINDER_INTERVAL = 10;

    ObservableList<AppointmentScheduleBEAN> retrievalList = FXCollections.observableArrayList();

    /**
     *
     * @param appointmentScheduleBEAN
     * @return
     */
    @Override
    public int insertApponitment(AppointmentScheduleBEAN appointmentScheduleBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        try {

            con = DBPool.getInstance().getConn();
            appointmentScheduleBEAN.setMeetingId("MEET_" + UiiDGenerator.getUIID8());
            System.out.println("meet_master_id :" + appointmentScheduleBEAN.getMeetingId());
            appointmentScheduleBEAN.setMeetingStatus("scheduled");
            appointmentScheduleBEAN.setLatestFlag("y");
            stmt = con.createStatement();
            String query = "insert into appointment_master (meeting_id,meeting_name,meeting_description,start_date_time,end_date_time,anchor,location,meeting_status,created_user,created_date,latest_flag)values('" + appointmentScheduleBEAN.getMeetingId() + "','" + appointmentScheduleBEAN.getMeetingName() + "','" + appointmentScheduleBEAN.getMeetingDescription() + "','" + appointmentScheduleBEAN.getStartDateTime() + "','" + appointmentScheduleBEAN.getEndDateTime() + "','" + appointmentScheduleBEAN.getAnchor() + "','" + appointmentScheduleBEAN.getLocation() + "','" + appointmentScheduleBEAN.getMeetingStatus() + "','" + appointmentScheduleBEAN.getCreatedUser() + "',now(),'" + appointmentScheduleBEAN.getLatestFlag() + "')";
            stmt.addBatch(query);
            for (UserPOJO userPOJO : appointmentScheduleBEAN.getParticipantsIdList()) {
                appointmentScheduleBEAN.setAppDetailsId("PART_" + UiiDGenerator.getUIID8());
                appointmentScheduleBEAN.setParticipantId(userPOJO.getUsername());
                String query1 = "insert into appointment_details (app_details_id,participant_id,meeting_id,created_user,created_date,latest_flag)values("
                        + "'" + appointmentScheduleBEAN.getAppDetailsId() + "',"
                        + "'" + appointmentScheduleBEAN.getParticipantId() + "',"
                        + "'" + appointmentScheduleBEAN.getMeetingId() + "',"
                        + "'" + appointmentScheduleBEAN.getCreatedUser() + "',"
                        + "now(),"
                        + "'" + appointmentScheduleBEAN.getLatestFlag() + "')";
                stmt.addBatch(query1);
            }

            String query2 = "update appointment_details set invitation_status='accepted' where participant_id='" + appointmentScheduleBEAN.getCreatedUser() + "' and meeting_id='" + appointmentScheduleBEAN.getMeetingId() + "'";
            /* ====================== Insert a reminder by default ====================== */
            String reminderInsertSql = "INSERT INTO reminder_tbl (\n"
                    + "	rem_id,\n"
                    + "	meeting_id,\n"
                    + "	participant_id,\n"
                    + "	reminder_date,\n"
                    + "	reminder_desc,\n"
                    + "	created_user,\n"
                    + "	created_time,\n"
                    + "	read_flag,\n"
                    + "	updated_user,\n"
                    + "	updated_time,branch\n"
                    + ")\n"
                    + "VALUES\n"
                    + "	(\n"
                    + "		'" + "REM_" + UiiDGenerator.getUIID8() + "',\n"
                    + "		'" + appointmentScheduleBEAN.getMeetingId() + "',\n"
                    + "		'" + appointmentScheduleBEAN.getCreatedUser() + "',\n"
                    + "	        (SELECT date_sub('" + appointmentScheduleBEAN.getStartDateTime() + "', INTERVAL " + REMINDER_INTERVAL + " MINUTE))	,\n"
                    + "		'reminder desc',\n"
                    + "		'" + appointmentScheduleBEAN.getCreatedUser() + "',\n"
                    + "		NOW(),\n"
                    + "		'0',\n"
                    + "		NULL,\n"
                    + "		NULL,'" + Context.getInstance().currentProfile().getProfilePOJO().getBranch() + "'\n"
                    + "	);";
            stmt.addBatch(query2);
            stmt.addBatch(reminderInsertSql);
            row = stmt.executeBatch()[0];
            System.out.println("test Status :: " + row);
        } catch (SQLException sqle) {
            logger.error(sqle);
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }

        return row;
    }

    /**
     *
     * @return
     */
    public static ObservableList<UserPOJO> retrieveUsers() {

        ObservableList<UserPOJO> usersList = FXCollections.observableArrayList();
        Logger logger1 = Logger.getLogger(AppointmentScheduleIMPL.class);
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {

            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();
            String query = "SELECT\n"
                    + "lt.user_name,\n"
                    + "ld.first_name,\n"
                    + "ld.last_name\n"
                    + "FROM\n"
                    + "login_tbl lt,\n"
                    + "login_details_tbl ld\n"
                    + "WHERE\n"
                    + "lt.login_id = ld.login_id;";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                UserPOJO userPOJO = new UserPOJO();
                userPOJO.setUsername(rs.getString("user_name"));
                userPOJO.setFirstName(rs.getString("first_name"));
                userPOJO.setLastName(rs.getString("last_name"));
                usersList.add(userPOJO);
            }
        } catch (SQLException sqle) {
            logger1.error(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return usersList;
    }

    /**
     *
     * @param username
     * @param branch
     * @return
     */
    @Override
    public ObservableList<AppointmentScheduleBEAN> retrieveApponitmentsAccepted(String username, String branch) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {

            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();
            String query = "SELECT\n"
                    + "	am.meeting_id,\n"
                    + "	am.meeting_name,\n"
                    + "	am.meeting_description,\n"
                    + "	DATE_FORMAT(\n"
                    + "		am.start_date_time,\n"
                    + "		'%Y-%m-%d %H:%i:%s'\n"
                    + "	) AS start_date_time,\n"
                    + "	DATE_FORMAT(\n"
                    + "		am.end_date_time,\n"
                    + "		'%Y-%m-%d %H:%i:%s'\n"
                    + "	) AS end_date_time,\n"
                    + "	DATE_FORMAT(\n"
                    + "		am.start_date_time,\n"
                    + "		'%D-%M-%Y %H:%i:%s %p'\n"
                    + "	) AS formatted_start_date,\n"
                    + "	DATE_FORMAT(\n"
                    + "		am.end_date_time,\n"
                    + "		'%D-%M-%Y %H:%i:%s %p'\n"
                    + "	) AS formatted_end_date,\n"
                    + "	am.location,\n"
                    + "	am.anchor\n"
                    + "FROM\n"
                    + "	appointment_master am\n"
                    + "RIGHT JOIN appointment_details ad ON am.meeting_id = ad.meeting_id\n"
                    + "WHERE\n"
                    + "	ad.invitation_status = 'accepted'\n"
                    + "AND ad.participant_id = '" + username + "'\n"
                    + "AND am.start_date_time > NOW()\n"
                    + "ORDER BY\n"
                    + "	am.start_date_time";
            System.out.println("Retrive Accepted Enquiries :: " + query);
            rs = stmt.executeQuery(query);
            retrievalList.clear();
            while (rs.next()) {
                AppointmentScheduleBEAN appointmentScheduleBEAN = new AppointmentScheduleBEAN();
                appointmentScheduleBEAN.setMeetingId(rs.getString("meeting_id"));
                appointmentScheduleBEAN.setMeetingName(rs.getString("meeting_name"));
                appointmentScheduleBEAN.setMeetingDescription(rs.getString("meeting_description"));
                appointmentScheduleBEAN.setStartDateTime(rs.getString("start_date_time"));
                appointmentScheduleBEAN.setEndDateTime(rs.getString("end_date_time"));
                appointmentScheduleBEAN.setFormattedStartDate(rs.getString("formatted_start_date"));
                appointmentScheduleBEAN.setFormattedEndDate(rs.getString("formatted_end_date"));
                appointmentScheduleBEAN.setLocation(rs.getString("location"));
                appointmentScheduleBEAN.setAnchor(rs.getString("anchor"));
                retrievalList.add(appointmentScheduleBEAN);

            }

        } catch (SQLException sqle) {
            logger.error(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return retrievalList;
    }

    /**
     *
     * @param username
     * @param branch
     * @return
     */
    @Override
    public ObservableList<AppointmentScheduleBEAN> retrieveScheduledEvents(String username, String branch) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {

            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();
            String query = "SELECT\n"
                    + "	meeting_id,\n"
                    + "	meeting_name,\n"
                    + "	meeting_description,\n"
                    + "DATE_FORMAT(\n"
                    + "		start_date_time,\n"
                    + "		\"%Y-%m-%d %H:%i:%s\"\n"
                    + "	) AS start_date_time,\n"
                    + "	DATE_FORMAT(\n"
                    + "		end_date_time,\n"
                    + "		\"%Y-%m-%d %H:%i:%s\"\n"
                    + "	) AS end_date_time,\n"
                    + "	DATE_FORMAT(\n"
                    + "		start_date_time,\n"
                    + "		\"%D-%M-%Y %H:%i:%s %p\"\n"
                    + "	) AS formatted_start_date,\n"
                    + "	DATE_FORMAT(\n"
                    + "		end_date_time,\n"
                    + "		\"%D-%M-%Y %H:%i:%s %p\"\n"
                    + "	) AS formatted_end_date,\n"
                    + "	location,\n"
                    + "	anchor,\n"
                    + "	meeting_status\n"
                    + "FROM\n"
                    + "	appointment_master\n"
                    + "WHERE\n"
                    + "	created_user = '" + username + "'\n"
                    + "AND meeting_status = 'scheduled'"
                    + "AND start_date_time > now() order by start_date_time";
            rs = stmt.executeQuery(query);
            retrievalList.clear();
            while (rs.next()) {
                AppointmentScheduleBEAN appointmentScheduleBEAN = new AppointmentScheduleBEAN();
                appointmentScheduleBEAN.setMeetingId(rs.getString("meeting_id"));
                appointmentScheduleBEAN.setMeetingName(rs.getString("meeting_name"));
                appointmentScheduleBEAN.setMeetingDescription(rs.getString("meeting_description"));
                appointmentScheduleBEAN.setStartDateTime(rs.getString("start_date_time"));
                appointmentScheduleBEAN.setEndDateTime(rs.getString("end_date_time"));
                appointmentScheduleBEAN.setFormattedStartDate(rs.getString("formatted_start_date"));
                appointmentScheduleBEAN.setFormattedEndDate(rs.getString("formatted_end_date"));
                appointmentScheduleBEAN.setLocation(rs.getString("location"));
                appointmentScheduleBEAN.setAnchor(rs.getString("anchor"));
                retrievalList.add(appointmentScheduleBEAN);
            }

        } catch (SQLException sqle) {
            logger.error(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return retrievalList;
    }

    /**
     *
     * @param username
     * @param branch
     * @return
     */
    @Override
    public ObservableList<AppointmentScheduleBEAN> retrieveCompletedEvents(String username, String branch) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {

            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();
            String query = "SELECT\n"
                    + "	meeting_name,\n"
                    + "	meeting_description,\n"
                    + "	DATE_FORMAT(\n"
                    + "		start_date_time,\n"
                    + "		\"%Y-%m-%d %H:%i:%s\"\n"
                    + "	) AS start_date_time,\n"
                    + "	DATE_FORMAT(\n"
                    + "		end_date_time,\n"
                    + "		\"%Y-%m-%d %H:%i:%s\"\n"
                    + "	) AS end_date_time,\n"
                    + "	DATE_FORMAT(\n"
                    + "		start_date_time,\n"
                    + "		\"%D-%M-%Y %H:%i:%s %p\"\n"
                    + "	) AS formatted_start_date,\n"
                    + "	DATE_FORMAT(\n"
                    + "		end_date_time,\n"
                    + "		\"%D-%M-%Y %H:%i:%s %p\"\n"
                    + "	) AS formatted_end_date,\n"
                    + "	location,\n"
                    + "	anchor,\n"
                    + "	meeting_status\n"
                    + "FROM\n"
                    + "	appointment_master\n"
                    + "WHERE\n"
                    + "	created_user = '" + username + "'\n"
                    + "AND end_date_time < now() order by start_date_time";

            rs = stmt.executeQuery(query);
            retrievalList.clear();
            while (rs.next()) {
                AppointmentScheduleBEAN appointmentScheduleBEAN = new AppointmentScheduleBEAN();
                appointmentScheduleBEAN.setMeetingName(rs.getString("meeting_name"));
                appointmentScheduleBEAN.setMeetingDescription(rs.getString("meeting_description"));
                appointmentScheduleBEAN.setStartDateTime(rs.getString("start_date_time"));
                appointmentScheduleBEAN.setEndDateTime(rs.getString("end_date_time"));
                appointmentScheduleBEAN.setFormattedStartDate(rs.getString("formatted_start_date"));
                appointmentScheduleBEAN.setFormattedEndDate(rs.getString("formatted_end_date"));
                appointmentScheduleBEAN.setLocation(rs.getString("location"));
                appointmentScheduleBEAN.setAnchor(rs.getString("anchor"));
                retrievalList.add(appointmentScheduleBEAN);
            }

        } catch (SQLException sqle) {
            logger.error(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return retrievalList;
    }

    /**
     *
     * @param username
     * @param branch
     * @param noOfDays
     * @return
     */
    @Override
    public ObservableList<AppointmentScheduleBEAN> retrieveWeekEvents(String username, String branch, int noOfDays) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String query = null;
        try {

            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();

            query = "SELECT\n"
                    + "	am.meeting_id,\n"
                    + "	am.meeting_name,\n"
                    + "	am.meeting_description,\n"
                    + "	DATE_FORMAT(\n"
                    + "		am.start_date_time,\n"
                    + "		'%Y-%m-%d %H:%i:%s'\n"
                    + "	) AS start_date_time,\n"
                    + "	DATE_FORMAT(\n"
                    + "		am.end_date_time,\n"
                    + "		'%Y-%m-%d %H:%i:%s'\n"
                    + "	) AS end_date_time,\n"
                    + "	DATE_FORMAT(\n"
                    + "		am.start_date_time,\n"
                    + "		'%D-%M-%Y %H:%i:%s %p'\n"
                    + "	) AS formatted_start_date,\n"
                    + "	DATE_FORMAT(\n"
                    + "		am.end_date_time,\n"
                    + "		'%D-%M-%Y %H:%i:%s %p'\n"
                    + "	) AS formatted_end_date,\n"
                    + "	am.location,\n"
                    + "	am.anchor\n"
                    + "FROM\n"
                    + "	appointment_master am\n"
                    + "RIGHT JOIN appointment_details ad ON am.meeting_id = ad.meeting_id\n"
                    + "WHERE\n"
                    + "	am.start_date_time > now()\n"
                    + "AND ad.participant_id = '" + username + "'\n"
                    + "AND am.start_date_time < CURDATE() + INTERVAL '" + noOfDays + "' DAY\n"
                    + "AND ad.invitation_status = 'accepted' order by am.start_date_time";

            rs = stmt.executeQuery(query);
            retrievalList.clear();
            while (rs.next()) {
                AppointmentScheduleBEAN appointmentScheduleBEAN = new AppointmentScheduleBEAN();
                appointmentScheduleBEAN.setMeetingName(rs.getString("meeting_name"));
                appointmentScheduleBEAN.setMeetingDescription(rs.getString("meeting_description"));
                appointmentScheduleBEAN.setStartDateTime(rs.getString("start_date_time"));
                appointmentScheduleBEAN.setEndDateTime(rs.getString("end_date_time"));
                appointmentScheduleBEAN.setFormattedStartDate(rs.getString("formatted_start_date"));
                appointmentScheduleBEAN.setFormattedEndDate(rs.getString("formatted_end_date"));
                appointmentScheduleBEAN.setLocation(rs.getString("location"));
                appointmentScheduleBEAN.setAnchor(rs.getString("anchor"));
                retrievalList.add(appointmentScheduleBEAN);
            }
            System.out.println("combo search  List inside weekIMPL");
            System.out.println("" + retrievalList.toString());
        } catch (SQLException sqle) {
            logger.error(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return retrievalList;
    }

    /**
     *
     * @param username
     * @param branch
     * @return
     */
    @Override
    public ObservableList<AppointmentScheduleBEAN> viewInvitations(String username, String branch) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {

            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();

            String query = "SELECT\n"
                    + "	am.meeting_id,\n"
                    + "	am.meeting_name,\n"
                    + "	am.meeting_description,\n"
                    + "	DATE_FORMAT(\n"
                    + "		am.start_date_time,\n"
                    + "		'%Y-%m-%d %H:%i:%s'\n"
                    + "	) AS start_date_time,\n"
                    + "	DATE_FORMAT(\n"
                    + "		am.end_date_time,\n"
                    + "		'%Y-%m-%d %H:%i:%s'\n"
                    + "	) AS end_date_time,\n"
                    + "	DATE_FORMAT(\n"
                    + "		am.start_date_time,\n"
                    + "		'%D-%M-%Y %H:%i:%s %p'\n"
                    + "	) AS formatted_start_date,\n"
                    + "	DATE_FORMAT(\n"
                    + "		am.end_date_time,\n"
                    + "		'%D-%M-%Y %H:%i:%s %p'\n"
                    + "	) AS formatted_end_date,\n"
                    + "	am.location,\n"
                    + "	am.anchor,\n"
                    + " ad.participation_status\n"
                    + "FROM\n"
                    + " appointment_master am\n"
                    + "RIGHT JOIN appointment_details ad ON am.meeting_id = ad.meeting_id\n"
                    + "WHERE\n"
                    + "	ad.invitation_status IS NULL\n"
                    + "AND ad.participant_id = '" + username + "' "
                    + "AND am.start_date_time > now() order by am.start_date_time";
            System.out.println("view invite query :: " + query);
            rs = stmt.executeQuery(query);
            retrievalList.clear();
            while (rs.next()) {
                AppointmentScheduleBEAN appointmentScheduleBEAN = new AppointmentScheduleBEAN();
                appointmentScheduleBEAN.setMeetingId(rs.getString("meeting_id"));
                appointmentScheduleBEAN.setParticipantStatus(rs.getString("participation_status"));
                appointmentScheduleBEAN.setMeetingName(rs.getString("meeting_name"));
                appointmentScheduleBEAN.setMeetingDescription(rs.getString("meeting_description"));
                appointmentScheduleBEAN.setStartDateTime(rs.getString("start_date_time"));
                appointmentScheduleBEAN.setEndDateTime(rs.getString("end_date_time"));
                appointmentScheduleBEAN.setFormattedStartDate(rs.getString("formatted_start_date"));
                appointmentScheduleBEAN.setFormattedEndDate(rs.getString("formatted_end_date"));
                appointmentScheduleBEAN.setLocation(rs.getString("location"));
                appointmentScheduleBEAN.setAnchor(rs.getString("anchor"));
                retrievalList.add(appointmentScheduleBEAN);
            }

        } catch (SQLException sqle) {
            logger.error(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return retrievalList;

    }

    /**
     *
     * @param appointmentScheduleBEAN
     * @return
     */
    @Override
    public int updateInvitationStatus(AppointmentScheduleBEAN appointmentScheduleBEAN) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        try {

            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();
            String query = "UPDATE appointment_details\n"
                    + "SET invitation_status = '" + appointmentScheduleBEAN.getInvitationStatus() + "'\n"
                    + "WHERE\n"
                    + "	participant_id = '" + appointmentScheduleBEAN.getParticipantId() + "'\n"
                    + "AND meeting_id = '" + appointmentScheduleBEAN.getMeetingId() + "'";
            System.out.println("" + query);
            /* ====================== Add Reminder For this user ====================== */
            String reminderInsertSql = "INSERT INTO reminder_tbl (\n"
                    + "	rem_id,\n"
                    + "	meeting_id,\n"
                    + "	participant_id,\n"
                    + "	reminder_date,\n"
                    + "	reminder_desc,\n"
                    + "	created_user,\n"
                    + "	created_time,\n"
                    + "	read_flag,\n"
                    + "	updated_user,\n"
                    + "	updated_time,branch\n"
                    + ")\n"
                    + "VALUES\n"
                    + "	(\n"
                    + "		'" + "REM_" + UiiDGenerator.getUIID8() + "',\n"
                    + "		'" + appointmentScheduleBEAN.getMeetingId() + "',\n"
                    + "		'" + appointmentScheduleBEAN.getParticipantId() + "',\n"
                    + "	        (SELECT date_sub('" + appointmentScheduleBEAN.getStartDateTime() + "', INTERVAL " + REMINDER_INTERVAL + " MINUTE))	,\n"
                    + "		'reminder desc',\n"
                    + "		'" + appointmentScheduleBEAN.getParticipantId() + "',\n"
                    + "		NOW(),\n"
                    + "		'0',\n"
                    + "		NULL,\n"
                    + "		NULL,'" + Context.getInstance().currentProfile().getProfilePOJO().getBranch() + "'\n"
                    + "	);";
            stmt.addBatch(query);
            stmt.addBatch(reminderInsertSql);
            row = stmt.executeBatch()[0];

        } catch (SQLException sqle) {
            logger.error(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return row;

    }

    /**
     *
     * @param appointmentScheduleBEAN
     * @return
     */
    @Override
    public int updateRemarks(AppointmentScheduleBEAN appointmentScheduleBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        try {

            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();
            String query = "UPDATE appointment_details\n"
                    + "SET remarks = '" + appointmentScheduleBEAN.getRemarks() + "'\n"
                    + "WHERE\n"
                    + "	participant_id = '" + appointmentScheduleBEAN.getParticipantId() + "'\n"
                    + "AND meeting_id = '" + appointmentScheduleBEAN.getMeetingId() + "'";
            System.out.println("" + query);
            row = stmt.executeUpdate(query);

        } catch (SQLException sqle) {
            logger.error(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return row;
    }

    /**
     *
     * @param username
     * @param branch
     * @return
     */
    @Override
    public ObservableList<AppointmentScheduleBEAN> viewEventsForAnchor(String username, String branch) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {

            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();
            String query = "SELECT\n"
                    + "	meeting_id,\n"
                    + "	meeting_name,\n"
                    + "	meeting_description,\n"
                    + "	DATE_FORMAT(\n"
                    + "		start_date_time,\n"
                    + "		'%Y-%m-%d %H:%i:%s'\n"
                    + "	) AS start_date_time,\n"
                    + "	DATE_FORMAT(\n"
                    + "		end_date_time,\n"
                    + "		'%Y-%m-%d %H:%i:%s'\n"
                    + "	) AS end_date_time,\n"
                    + "	DATE_FORMAT(\n"
                    + "		start_date_time,\n"
                    + "		'%D-%M-%Y %H:%i:%s %p'\n"
                    + "	) AS formatted_start_date,\n"
                    + "	DATE_FORMAT(\n"
                    + "		end_date_time,\n"
                    + "		'%D-%M-%Y %H:%i:%s %p'\n"
                    + "	) AS formatted_end_date,\n"
                    + "	location,\n"
                    + "	anchor\n"
                    + "FROM\n"
                    + "appointment_master a\n"
                    + "WHERE\n"
                    + "	meeting_status = 'scheduled'\n"
                    + "AND anchor = '" + username + "' ORDER BY start_date_time desc";

            rs = stmt.executeQuery(query);
            retrievalList.clear();
            while (rs.next()) {
                AppointmentScheduleBEAN appointmentScheduleBEAN = new AppointmentScheduleBEAN();
                appointmentScheduleBEAN.setMeetingId(rs.getString("meeting_id"));
                appointmentScheduleBEAN.setMeetingName(rs.getString("meeting_name"));
                appointmentScheduleBEAN.setMeetingDescription(rs.getString("meeting_description"));
                appointmentScheduleBEAN.setStartDateTime(rs.getString("start_date_time"));
                appointmentScheduleBEAN.setEndDateTime(rs.getString("end_date_time"));
                appointmentScheduleBEAN.setFormattedStartDate(rs.getString("formatted_start_date"));
                appointmentScheduleBEAN.setFormattedEndDate(rs.getString("formatted_end_date"));
                appointmentScheduleBEAN.setLocation(rs.getString("location"));
                appointmentScheduleBEAN.setAnchor(rs.getString("anchor"));
                retrievalList.add(appointmentScheduleBEAN);
            }

        } catch (SQLException sqle) {
            logger.error(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return retrievalList;

    }

    /**
     *
     * @param username
     * @param branch
     * @param appointmentScheduleBEAN
     * @return
     */
    @Override
    public ObservableList<UserPOJO> viewParticipants(String username, String branch, AppointmentScheduleBEAN appointmentScheduleBEAN) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ObservableList<UserPOJO> retrievalUserPOJOList = FXCollections.observableArrayList();
        try {

            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();
            String query = "SELECT\n"
                    + "	ld.first_name,\n"
                    + "	ld.last_name,\n"
                    + "	lt.user_name,\n"
                    + "	ad.participation_status,\n"
                    + "	ad.remarks\n"
                    + "FROM\n"
                    + "	login_details_tbl ld\n"
                    + "RIGHT JOIN login_tbl lt ON ld.login_id = lt.login_id\n"
                    + "RIGHT JOIN appointment_details ad ON ad.participant_id = lt.user_name\n"
                    + "WHERE\n"
                    + "	ad.meeting_id = '" + appointmentScheduleBEAN.getMeetingId() + "'";
            System.out.println("view events for viewParticipants query :: " + query);
            rs = stmt.executeQuery(query);
            retrievalList.clear();
            while (rs.next()) {
                UserPOJO userPOJO = new UserPOJO();
                userPOJO.setFirstName(rs.getString("first_name"));
                userPOJO.setLastName(rs.getString("last_name"));
                userPOJO.setUsername(rs.getString("user_name"));
                userPOJO.setRemarks(rs.getString("remarks"));
                userPOJO.setParticipationStatus(rs.getString("participation_status"));
                retrievalUserPOJOList.add(userPOJO);
            }

        } catch (SQLException sqle) {
            logger.error(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return retrievalUserPOJOList;
    }

    /**
     *
     * @param participantsIdList
     * @param meeting_id
     * @return
     */
    @Override
    public int updateParticipationStatus(ObservableList<UserPOJO> participantsIdList, String meeting_id) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        try {

            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();
            String absentIds = null;
            for (UserPOJO userPOJO : participantsIdList) {
                String query = "UPDATE appointment_details\n"
                        + "SET participation_status='present'\n"
                        + " WHERE\n"
                        + "participant_id ='" + userPOJO.getUsername() + "'\n"
                        + " AND meeting_id = '" + meeting_id + "'";
                System.out.println("" + query);
                if (absentIds == null) {
                    absentIds = "'" + userPOJO.getUsername() + "'";
                } else {
                    absentIds = absentIds + ",'" + userPOJO.getUsername() + "'";
                }

                stmt.addBatch(query);
            }
            String queryAbsent = "UPDATE appointment_details\n"
                    + "SET participation_status='absent'\n"
                    + " WHERE\n"
                    + "	participant_id not in (" + absentIds + ") \n"
                    + " AND meeting_id = '" + meeting_id + "'";
            System.out.println("Absent Query :: " + queryAbsent);
            stmt.addBatch(queryAbsent);
            row = stmt.executeBatch()[0];

        } catch (SQLException sqle) {
            logger.error(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return row;
    }

    /**
     *
     * @param username
     * @param branch
     * @return
     */
    @Override
    public ObservableList<AppointmentScheduleBEAN> viewTodayEvents(String username, String branch) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {

            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();
            String query = "SELECT\n"
                    + "	am.meeting_id,\n"
                    + "	am.meeting_name,\n"
                    + "	am.meeting_description,\n"
                    + "	DATE_FORMAT(\n"
                    + "		am.start_date_time,\n"
                    + "		'%Y-%M-%d %H:%i:%s'\n"
                    + "	) AS start_date_time,\n"
                    + "	DATE_FORMAT(\n"
                    + "		am.end_date_time,\n"
                    + "		'%Y-%M-%d %H:%i:%s'\n"
                    + "	) AS end_date_time,\n"
                    + "	DATE_FORMAT(\n"
                    + "		am.start_date_time,\n"
                    + "		'%D-%M-%Y %H:%i:%s %p'\n"
                    + "	) AS formatted_start_date,\n"
                    + "	DATE_FORMAT(\n"
                    + "		am.end_date_time,\n"
                    + "		'%D-%M-%Y %H:%i:%s %p'\n"
                    + "	) AS formatted_end_date,\n"
                    + "	am.location,\n"
                    + "	am.anchor\n"
                    + "FROM\n"
                    + "	appointment_master am \n"
                    + "RIGHT JOIN appointment_details ad ON ad.meeting_id = am.meeting_id\n"
                    + "WHERE\n"
                    + "	ad.participant_id = '" + username + "'\n"
                    + "AND ad.invitation_status = 'accepted'\n"
                    + "AND DATE_FORMAT(\n"
                    + "	am.start_date_time,\n"
                    + "	'%Y-%m-%d'\n"
                    + ") = CURRENT_DATE () order by am.start_date_time";

            rs = stmt.executeQuery(query);
            retrievalList.clear();
            while (rs.next()) {
                AppointmentScheduleBEAN appointmentScheduleBEAN = new AppointmentScheduleBEAN();
                appointmentScheduleBEAN.setMeetingName(rs.getString("meeting_name"));
                appointmentScheduleBEAN.setMeetingDescription(rs.getString("meeting_description"));
                appointmentScheduleBEAN.setStartDateTime(rs.getString("start_date_time"));
                appointmentScheduleBEAN.setEndDateTime(rs.getString("end_date_time"));
                appointmentScheduleBEAN.setFormattedStartDate(rs.getString("formatted_start_date"));
                appointmentScheduleBEAN.setFormattedEndDate(rs.getString("formatted_end_date"));
                appointmentScheduleBEAN.setLocation(rs.getString("location"));
                appointmentScheduleBEAN.setAnchor(rs.getString("anchor"));
                retrievalList.add(appointmentScheduleBEAN);
            }

        } catch (SQLException sqle) {
            logger.error(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return retrievalList;
    }

    /**
     *
     * @param username
     * @param branch
     * @return
     */
    @Override
    public int countInvitations(String username, String branch) {
        int invitationsCount = 0;
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {

            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();
            String query = "SELECT\n"
                    + "	count(*)\n"
                    + "FROM\n"
                    + "	appointment_details\n"
                    + "WHERE\n"
                    + "	invitation_status IS NULL\n"
                    + "AND participant_id = '" + username + "'";

            rs = stmt.executeQuery(query);

            if (rs.next()) {
                invitationsCount = rs.getInt(1);
            }

        } catch (SQLException sqle) {
            logger.error(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return invitationsCount;
    }

    /**
     *
     * @param username
     * @param branch
     * @return
     */
    @Override
    public ObservableList<AppointmentScheduleBEAN> viewTodayNextEvents(String username, String branch) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        long milliSeconds;

        try {

            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();
            String query = "SELECT\n"
                    + "	am.meeting_id,\n"
                    + "	am.meeting_name,\n"
                    + "	am.meeting_description,\n"
                    + "	DATE_FORMAT(\n"
                    + "		am.start_date_time,\n"
                    + "		'%Y-%M-%d %H:%i:%s'\n"
                    + "	) AS start_date_time,\n"
                    + "	DATE_FORMAT(\n"
                    + "		am.end_date_time,\n"
                    + "		'%Y-%M-%d %H:%i:%s'\n"
                    + "	) AS end_date_time,\n"
                    + "	DATE_FORMAT(\n"
                    + "		am.start_date_time,\n"
                    + "		'%D-%M-%Y %H:%i:%s %p'\n"
                    + "	) AS formatted_start_date,\n"
                    + "	DATE_FORMAT(\n"
                    + "		am.end_date_time,\n"
                    + "		'%D-%M-%Y %H:%i:%s %p'\n"
                    + "	) AS formatted_end_date,\n"
                    + "	am.location,\n"
                    + "	am.anchor,\n"
                    + "	TIMESTAMPDIFF(\n"
                    + "		SECOND,\n"
                    + "		NOW(),\n"
                    + "		am.start_date_time\n"
                    + "	) AS seconds_remaining,\n"
                    + " ad.snooze_value\n"
                    + "FROM\n"
                    + "	appointment_master am\n"
                    + "RIGHT JOIN appointment_details ad ON ad.meeting_id = am.meeting_id\n"
                    + "WHERE\n"
                    + "	ad.participant_id = '" + username + "'\n"
                    + "AND ad.invitation_status = 'accepted'\n"
                    + "AND am.start_date_time > NOW()\n"
                    + "AND DATE(start_date_time) = CURDATE()";

            rs = stmt.executeQuery(query);
            retrievalList.clear();
            while (rs.next()) {
                AppointmentScheduleBEAN appointmentScheduleBEAN = new AppointmentScheduleBEAN();
                appointmentScheduleBEAN.setMeetingId(rs.getString("meeting_id"));
                appointmentScheduleBEAN.setMeetingName(rs.getString("meeting_name"));
                appointmentScheduleBEAN.setMeetingDescription(rs.getString("meeting_description"));
                appointmentScheduleBEAN.setStartDateTime(rs.getString("start_date_time"));
                appointmentScheduleBEAN.setEndDateTime(rs.getString("end_date_time"));
                appointmentScheduleBEAN.setFormattedStartDate(rs.getString("formatted_start_date"));
                appointmentScheduleBEAN.setFormattedEndDate(rs.getString("formatted_end_date"));
                appointmentScheduleBEAN.setLocation(rs.getString("location"));
                appointmentScheduleBEAN.setAnchor(rs.getString("anchor"));
                appointmentScheduleBEAN.setSecondsRemaining(rs.getString("seconds_remaining"));
                appointmentScheduleBEAN.setSnoozeValue(rs.getString("snooze_value"));
                milliSeconds = (Long.parseLong(appointmentScheduleBEAN.getSecondsRemaining())) * 1000;
                appointmentScheduleBEAN.setSecondsRemaining(TimeAgoFunctions.toRelative(milliSeconds));

                retrievalList.add(appointmentScheduleBEAN);
                System.out.println("retrievalList )))):" + retrievalList.toString());
            }

        } catch (SQLException sqle) {
            logger.error(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return retrievalList;
    }

    /**
     *
     * @param appointmentScheduleBEAN
     * @return
     */
    @Override
    public int updateSnoozeValue(AppointmentScheduleBEAN appointmentScheduleBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        try {

            System.out.println("inside updateSnoozeValue() method");
            System.out.println("getSnoozeValue :: " + appointmentScheduleBEAN.getSnoozeValue());
            System.out.println("getParticipantId :: " + appointmentScheduleBEAN.getParticipantId());
            System.out.println("getMeetingId :: " + appointmentScheduleBEAN.getMeetingId());
            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();
            String query = "UPDATE appointment_details\n"
                    + "SET snooze_value = '" + appointmentScheduleBEAN.getSnoozeValue() + "'\n"
                    + "WHERE\n"
                    + "	participant_id = '" + appointmentScheduleBEAN.getParticipantId() + "'\n"
                    + "AND meeting_id = '" + appointmentScheduleBEAN.getMeetingId() + "'";
            System.out.println("" + query);
            row = stmt.executeUpdate(query);

        } catch (SQLException sqle) {
            logger.error(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return row;
    }

    /**
     *
     * @param username
     * @param branch
     * @return
     */
    @Override
    public ObservableList<AppointmentScheduleBEAN> viewTodayNextEventsSnoozed(String username, String branch) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        long milliSeconds;

        try {

            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();
            String query = "SELECT\n"
                    + "	am.meeting_id,\n"
                    + "	am.meeting_name,\n"
                    + "	am.meeting_description,\n"
                    + "	DATE_FORMAT(\n"
                    + "		am.start_date_time,\n"
                    + "		'%Y-%M-%d %H:%i:%s'\n"
                    + "	) AS start_date_time,\n"
                    + "	DATE_FORMAT(\n"
                    + "		am.end_date_time,\n"
                    + "		'%Y-%M-%d %H:%i:%s'\n"
                    + "	) AS end_date_time,\n"
                    + "	DATE_FORMAT(\n"
                    + "		am.start_date_time,\n"
                    + "		'%D-%M-%Y %H:%i:%s %p'\n"
                    + "	) AS formatted_start_date,\n"
                    + "	DATE_FORMAT(\n"
                    + "		am.end_date_time,\n"
                    + "		'%D-%M-%Y %H:%i:%s %p'\n"
                    + "	) AS formatted_end_date,\n"
                    + "	am.location,\n"
                    + "	am.anchor,\n"
                    + "	TIMESTAMPDIFF(\n"
                    + "		SECOND,\n"
                    + "		NOW(),\n"
                    + "		am.start_date_time\n"
                    + "	) AS seconds_remaining,\n"
                    + "	ad.snooze_value\n"
                    + "FROM\n"
                    + "	appointment_master am\n"
                    + "RIGHT JOIN appointment_details ad ON ad.meeting_id = am.meeting_id\n"
                    + "WHERE\n"
                    + "	ad.participant_id = '" + username + "'\n"
                    + "AND ad.invitation_status = 'accepted'\n"
                    + "AND am.start_date_time > NOW()\n"
                    + "AND ad.snooze_value <>''";

            rs = stmt.executeQuery(query);
            retrievalList.clear();
            while (rs.next()) {
                AppointmentScheduleBEAN appointmentScheduleBEAN = new AppointmentScheduleBEAN();
                appointmentScheduleBEAN.setMeetingId(rs.getString("meeting_id"));
                appointmentScheduleBEAN.setMeetingName(rs.getString("meeting_name"));
                appointmentScheduleBEAN.setMeetingDescription(rs.getString("meeting_description"));
                appointmentScheduleBEAN.setStartDateTime(rs.getString("start_date_time"));
                appointmentScheduleBEAN.setEndDateTime(rs.getString("end_date_time"));
                appointmentScheduleBEAN.setFormattedStartDate(rs.getString("formatted_start_date"));
                appointmentScheduleBEAN.setFormattedEndDate(rs.getString("formatted_end_date"));
                appointmentScheduleBEAN.setLocation(rs.getString("location"));
                appointmentScheduleBEAN.setAnchor(rs.getString("anchor"));
                appointmentScheduleBEAN.setSecondsRemaining(rs.getString("seconds_remaining"));
                appointmentScheduleBEAN.setSnoozeValue(rs.getString("snooze_value"));
                milliSeconds = (Long.parseLong(appointmentScheduleBEAN.getSecondsRemaining())) * 1000;
                appointmentScheduleBEAN.setSecondsRemaining(TimeAgoFunctions.toRelative(milliSeconds));
                retrievalList.add(appointmentScheduleBEAN);
            }

        } catch (SQLException sqle) {
            logger.error(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return retrievalList;
    }

    /**
     *
     * @param appointmentScheduleBEAN
     * @param username
     * @param branch
     * @return
     */
    @Override
    public int updateMeetingStatus(AppointmentScheduleBEAN appointmentScheduleBEAN, String username, String branch) {
       Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        try {

            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();
            String query = "UPDATE appointment_master\n"
                    + "SET meeting_status = 'cancelled'\n"
                    + "WHERE\n"
                    + "	created_user = '" + username + "'\n"
                    + "AND meeting_id = '" + appointmentScheduleBEAN.getMeetingId() + "'\n"
                    + "AND meeting_status = 'scheduled'";
            System.out.println("updateMeetingStatus query :::" + query);
            row = stmt.executeUpdate(query);

        } catch (SQLException sqle) {
            logger.error(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return row; 
    }

    /**
     *
     * @param appointmentScheduleBEAN
     * @param CUR_USERNAME
     * @param CUR_BRANCH
     * @return
     */
    @Override
    public int updateAllInvitationStatus(AppointmentScheduleBEAN appointmentScheduleBEAN, String CUR_USERNAME, String CUR_BRANCH) {
  
      Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        try {

            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();
            String query = "UPDATE appointment_details\n"
                    + "SET invitation_status = 'cancelled'\n"
                    + "WHERE\n"
                    + " meeting_id = '" + appointmentScheduleBEAN.getMeetingId() + "'";
                 
            System.out.println("updateupdateAllInvitationStatus query::: " + query);
            row = stmt.executeUpdate(query);

        } catch (SQLException sqle) {
            logger.error(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return row; 
    }

}
