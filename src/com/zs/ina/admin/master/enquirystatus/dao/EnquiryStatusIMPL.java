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
package com.zs.ina.admin.master.enquirystatus.dao;

import com.zs.ina.enquiry.appointment.dao.AppointmentScheduleIMPL;
import com.zs.ina.utility.ClosingResourcesInDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
public class EnquiryStatusIMPL implements EnquiryStatusDAO {

    Logger logger = Logger.getLogger(AppointmentScheduleIMPL.class);

    ObservableList<EnquiryStatusBEAN> retrieveStatusList = FXCollections.observableArrayList();

    /**
     *
     * @param enquiryStatusBEAN
     * @return
     */
    @Override
    public int insertEnquiryStatus(EnquiryStatusBEAN enquiryStatusBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        try {

            con = DBPool.getInstance().getConn();
            String query = "insert into mastertbl_app_status(app_status_id,app_status,date_reason_enable,from_mail,subject,email_body,enable) values(?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, "0");
            ps.setString(2, enquiryStatusBEAN.getAppStatus());
            ps.setString(3, enquiryStatusBEAN.getDateReasonEnable());
            ps.setString(4, enquiryStatusBEAN.getFromMail());
            ps.setString(5, enquiryStatusBEAN.getEmailSubject());
            ps.setString(6, enquiryStatusBEAN.getEmailBody());
            ps.setString(7, enquiryStatusBEAN.getEnable());
            row = ps.executeUpdate();

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
     * @return
     */
    @Override
    public ObservableList<EnquiryStatusBEAN> retrieveStatusValues() {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {

            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();
            String query = "select * from mastertbl_app_status";
            rs = stmt.executeQuery(query);
            retrieveStatusList.clear();
            while (rs.next()) {
                EnquiryStatusBEAN enquiryStatusBEAN = new EnquiryStatusBEAN();
                enquiryStatusBEAN.setAppStatusId(rs.getString("app_status_id"));
                enquiryStatusBEAN.setAppStatus(rs.getString("app_status"));
                enquiryStatusBEAN.setFromMail(rs.getString("from_mail"));
                enquiryStatusBEAN.setEmailSubject(rs.getString("subject"));
                enquiryStatusBEAN.setEmailBody(rs.getString("email_body"));
                enquiryStatusBEAN.setProcessCompletion(rs.getString("pro_completion_status"));
         
                if (rs.getInt(3) == 1) {
                    enquiryStatusBEAN.setDateReasonEnable("YES");
                }
                if (rs.getInt(3) == 0) {
                    enquiryStatusBEAN.setDateReasonEnable("NO");
                }
                if (rs.getInt(7) == 1) {
                    enquiryStatusBEAN.setEnable("YES");
                }
                if (rs.getInt(7) == 0) {
                    enquiryStatusBEAN.setEnable("NO");
                }

                retrieveStatusList.add(enquiryStatusBEAN);
            }

        } catch (SQLException sqle) {
            logger.error(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return retrieveStatusList;

    }

    /**
     *
     * @param enquiryStatusBEAN
     * @return
     */
    @Override
    public int updateStatusDetails(EnquiryStatusBEAN enquiryStatusBEAN) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        try {

            con = DBPool.getInstance().getConn();
            String query = "update mastertbl_app_status set app_status=?,date_reason_enable=?,from_mail=?,subject=?,email_body=?,enable=? where app_status_id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, enquiryStatusBEAN.getAppStatus());
            ps.setString(2, enquiryStatusBEAN.getDateReasonEnable());
            ps.setString(3, enquiryStatusBEAN.getFromMail());
            ps.setString(4, enquiryStatusBEAN.getEmailSubject());
            ps.setString(5, enquiryStatusBEAN.getEmailBody());
            ps.setString(6, enquiryStatusBEAN.getEnable());
            ps.setString(7, enquiryStatusBEAN.getAppStatusId());
            row = ps.executeUpdate();

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
     * @param enquiryStatusBEAN
     * @return
     */
    @Override
    public int setProcessCompletionStatus(EnquiryStatusBEAN enquiryStatusBEAN) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        try {

            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();
            String sql = "UPDATE mastertbl_app_status\n"
                    + "SET pro_completion_status = 1\n"
                    + "WHERE\n"
                    + "	app_status_id = " + enquiryStatusBEAN.getAppStatusId();
            String sql2 = "UPDATE mastertbl_app_status\n"
                    + "SET pro_completion_status = 0\n"
                    + "WHERE\n"
                    + "	app_status_id not in( " + enquiryStatusBEAN.getAppStatusId() + ")";
            stmt.addBatch(sql);
            stmt.addBatch(sql2);
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

}
