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
package com.zs.ina.enquiry.appointment.reminder.dao;

import com.zs.ina.common.TimeAgoFunctions;
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
 * @author 100018
 */
public class ReminderIMPL implements ReminderDAO {

    static Logger logger = Logger.getLogger(ReminderIMPL.class);

    /**
     *
     * @param reminderBEAN
     * @return
     */
    @Override
    public int insertReminder(ReminderBEAN reminderBEAN) {
        Connection con = null;
        int row = 0;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        try {
            String query = "INSERT INTO reminder_tbl (\n"
                    + "	id,\n"
                    + "	meeting_id,\n"
                    + "	participant_id,\n"
                    + "	reminder_date,\n"
                    + "	reminder_desc,\n"
                    + "	created_user,\n"
                    + "	created_time,\n"
                    + "	read_flg,\n"
                    + "	updated_user,\n"
                    + "	updated_time,branch\n"
                    + ")\n"
                    + "VALUES\n"
                    + "	(\n"
                    + "		'" + reminderBEAN.getRemId() + "',\n"
                    + "		'" + reminderBEAN.getMeetingId() + "',\n"
                    + "		'" + reminderBEAN.getParticipantId() + "',\n"
                    + "		'" + reminderBEAN.getReminderDate() + "',\n"
                    + "		'" + reminderBEAN.getReminderDesc() + "',\n"
                    + "		'" + reminderBEAN.getCreatedUser() + "',\n"
                    + "		NOW(),\n"
                    + "		0,\n"
                    + "		NULL,\n"
                    + "		NULL,\n"
                    + "		'" + reminderBEAN.getBranch() + "',\n"
                    + "	);";
            stmt = con.createStatement();
            row = stmt.executeUpdate(query);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.trace(sqle);
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return row;
    }

    /**
     *
     * @param reminderBEAN
     * @return
     */
    @Override
    public int handleSnoozeReminder(ReminderBEAN reminderBEAN) {
        Connection con = null;
        int row = 0;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        try {
            String query = "UPDATE reminder_tbl\n"
                    + "SET \n"
                    + "	reminder_date = (SELECT date_sub('" + reminderBEAN.getStartDate() + "', INTERVAL " + reminderBEAN.getInterval() + " MINUTE))	,\n"
                    + " reminder_desc = '" + reminderBEAN.getReminderDesc() + "',\n"
                    + " read_flag = '0',\n"
                    + " updated_user = '" + reminderBEAN.getUpdatedUser() + "',\n"
                    + " updated_time = NOW()\n"
                    + "WHERE\n"
                    + "	rem_id='" + reminderBEAN.getRemId() + "';";
            System.out.println("snooze query :: " + query);
            stmt = con.createStatement();
            row = stmt.executeUpdate(query);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return row;
    }

    /**
     *
     * @param reminderId
     * @return
     */
    @Override
    public int deleteReminder(String reminderId) {
        Connection con = null;
        int row = 0;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        try {
            String query = "DELETE \n"
                    + "FROM\n"
                    + "	reminder_tbl\n"
                    + " WHERE\n"
                    + "	rem_id = '" + reminderId + "'";
            stmt = con.createStatement();
            row = stmt.executeUpdate(query);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.trace(sqle);
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
     * @param role
     * @return
     */
    @Override
    public ObservableList<ReminderBEAN> retrieveReminder(String username, String branch, String role) {
        Connection con = null;
        int row = 0;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        ObservableList<ReminderBEAN> listReminderBEAN = FXCollections.observableArrayList();
        try {
            String sql = "SELECT *,TIMESTAMPDIFF(SECOND, NOW(), am.start_date_time) as time_remains from reminder_tbl rt\n"
                    + "LEFT JOIN appointment_master am on am.meeting_id=rt.meeting_id\n"
                    + "WHERE rt.participant_id='" + username + "'\n"
                    + "AND read_flag=0 AND participant_id='" + username + "' AND branch='" + branch + "' AND (NOW() BETWEEN  rt.reminder_date AND am.start_date_time )  ";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ReminderBEAN reminderBEAN = new ReminderBEAN();
                reminderBEAN.setRemId(rs.getString("rem_id"));
                reminderBEAN.setMeetingId(rs.getString("meeting_id"));
                reminderBEAN.setParticipantId(rs.getString("participant_id"));
                reminderBEAN.setReminderDate(rs.getString("reminder_date"));
                reminderBEAN.setReminderDesc(rs.getString("reminder_desc"));
                reminderBEAN.setCreatedUser(rs.getString("created_user"));
                reminderBEAN.setCreatedTime(rs.getString("created_time"));
                reminderBEAN.setReadFlag(rs.getString("read_flag"));
                reminderBEAN.setUpdatedUser(rs.getString("updated_user"));
                reminderBEAN.setUpdatedTime(rs.getString("updated_time"));
                reminderBEAN.setBranch(rs.getString("branch"));
                reminderBEAN.setMeetingName(rs.getString("meeting_name"));
                reminderBEAN.setMeetingDescription(rs.getString("meeting_description"));
                reminderBEAN.setLocation(rs.getString("location"));
                reminderBEAN.setStartDate(rs.getString("start_date_time"));
                reminderBEAN.setEndDate(rs.getString("end_date_time"));
                reminderBEAN.setAnchor(rs.getString("anchor"));
                reminderBEAN.setTimeRemaining(TimeAgoFunctions.toRelative(Long.parseLong(rs.getString("time_remains")) * 1000));
                listReminderBEAN.add(reminderBEAN);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error(sqle);
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return listReminderBEAN;
    }

}
