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
package com.zs.ina.assesment.status.dao;

import com.zs.ina.assesment.model.AssessmentStatusBEAN;
import com.zs.ina.common.UiiDGenerator;
import com.zs.ina.common.constants.TableNames;
import com.zs.ina.context.Context;
import com.zs.ina.utility.ClosingResourcesInDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import org.apache.log4j.Logger;
import zs.com.ina.db.mysql.pool.DBPool;

/**
 *
 * @author 100018
 */
public class AssessmentStatusIMPL implements AssessmentStatusDAO {

    static Logger logger = Logger.getLogger(AssessmentStatusIMPL.class);

    /**
     *
     * @param assessmentStatusBEAN
     * @return
     */
    @Override
    public int insertAssessmentStatus(AssessmentStatusBEAN assessmentStatusBEAN) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        try {
            assessmentStatusBEAN.setStatusId("staus_" + UiiDGenerator.getUIID8());
            con = DBPool.getInstance().getConn();
            String query = "INSERT INTO " + TableNames.TABLE_ENQ_ASMNT_STATUS + "(\n"
                    + "	assement_status_id,\n"
                    + "	enquiry_id,\n"
                    + "	enquiry_status,\n"
                    + "	remarks,\n"
                    + "	priority,\n"
                    + "	date,\n"
                    + "	time,\n"
                    + "	branch,\n"
                    + "	counsellor,\n"
                    + "	reference,\n"
                    + "	created_user,\n"
                    + "	created_date \n"
                    + ")\n"
                    + "VALUES(?,?,?,?,?,?,?,?,?,?,?,NOW())\n";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, assessmentStatusBEAN.getStatusId());
            ps.setString(2, assessmentStatusBEAN.getEnquiryId());
            ps.setString(3, assessmentStatusBEAN.getAssessmentStatus());
            if (assessmentStatusBEAN.getRemarks() != null) {
                ps.setString(4, assessmentStatusBEAN.getRemarks());
            } else {
                ps.setString(4, null);
            }

            ps.setString(5, assessmentStatusBEAN.getPriority());
            if (assessmentStatusBEAN.getAppointmentDate() != null) {
                ps.setString(6, assessmentStatusBEAN.getAppointmentDate().toString());
            } else {
                ps.setNull(6, java.sql.Types.DATE);
            }
            if (assessmentStatusBEAN.getAppointmentTime() != null) {
                ps.setString(7, assessmentStatusBEAN.getAppointmentTime().toString());
            } else {
                ps.setString(7, null);
            }
            ps.setString(8, assessmentStatusBEAN.getBranch());
            if (assessmentStatusBEAN.getCounselor() != null) {
                ps.setString(9, assessmentStatusBEAN.getCounselor());
            } else {
                ps.setString(9, "Not Assigned");

            }
            ps.setString(10, assessmentStatusBEAN.getForwardFor());
            ps.setString(11, Context.getInstance().currentProfile().getProfilePOJO().getUsername());

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
     * @param assessmentStatusBEAN
     * @return
     */
    @Override
    public int updateAssessmentStatus(AssessmentStatusBEAN assessmentStatusBEAN) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        assessmentStatusBEAN.setStatusId("staus_" + UiiDGenerator.getUIID8());
        try {
            if (assessmentStatusBEAN.getRemarks() != null) {
                assessmentStatusBEAN.setRemarks(assessmentStatusBEAN.getRemarks());
            } else {
                assessmentStatusBEAN.setRemarks(null);
            }
            if (assessmentStatusBEAN.getAppointmentTime() != null) {
                assessmentStatusBEAN.setAppointmentTime(assessmentStatusBEAN.getAppointmentTime());
            } else {

                assessmentStatusBEAN.setAppointmentTime(null);
            }
            if (assessmentStatusBEAN.getAppointmentDate() != null) {
                assessmentStatusBEAN.setAppointmentDate(assessmentStatusBEAN.getAppointmentDate());
            }

            String query = "UPDATE " + TableNames.TABLE_ENQ_ASMNT_STATUS + "\n"
                    + "SET enquiry_status = '" + assessmentStatusBEAN.getAssessmentStatus() + "',\n"
                    + " remarks = '" + assessmentStatusBEAN.getRemarks() + "',\n"
                    + " priority = '" + assessmentStatusBEAN.getPriority() + "',\n"
                    + " date = '" + assessmentStatusBEAN.getAppointmentDate() + "',\n"
                    + " time = '" + assessmentStatusBEAN.getAppointmentTime() + "',\n"
                    + " branch = '" + assessmentStatusBEAN.getBranch() + "',\n"
                    + " counsellor = '" + assessmentStatusBEAN.getCounselor() + "',\n"
                    + " reference = '" + assessmentStatusBEAN.getForwardFor() + "',\n"
                    + " updated_date = NOW(),\n"
                    + " updated_user = '" + Context.getInstance().currentProfile().getProfilePOJO().getUsername() + "'\n"
                    + "WHERE\n"
                    + "	enquiry_id = '" + assessmentStatusBEAN.getEnquiryId() + "'";
            String enquiryUpdate = "UPDATE " + TableNames.TABLE_ENQ_DETAILS + "\n"
                    + "SET department = '" + assessmentStatusBEAN.getDepartment() + "'\n"
                    + "WHERE\n"
                    + "	enquiry_id = '" + assessmentStatusBEAN.getEnquiryId() + "';";
            stmt = con.createStatement();
            stmt.addBatch(query);
            stmt.addBatch(enquiryUpdate);
            row = stmt.executeBatch()[0];

        } catch (SQLException sqle) {
            logger.info(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return row;
    }

    /**
     *
     * @param enquiryID
     * @return
     */
    @Override
    public int deleteAssessmentStatus(String enquiryID) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        int row = 0;
        try {

            String query = "DELETE\n"
                    + "	*\n"
                    + "FROM\n"
                    + "	`" + TableNames.TABLE_ENQ_ASMNT_STATUS + "`\n"
                    + "WHERE\n"
                    + "	enquiry_id = '" + enquiryID + "';";

            stmt = con.createStatement();
            row = stmt.executeUpdate(query);
        } catch (SQLException sqle) {
            logger.info(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return row;
    }

    /**
     *
     * @param enquiryId
     * @return
     */
    @Override
    public AssessmentStatusBEAN retrieveAssessmentStatus(String enquiryId) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        AssessmentStatusBEAN assessmentStatusBEAN = new AssessmentStatusBEAN();
        try {

            String query = "SELECT\n"
                    + "	*\n"
                    + "FROM\n"
                    + "	`" + TableNames.TABLE_ENQ_ASMNT_STATUS + "`\n"
                    + "WHERE\n"
                    + "	enquiry_id = '" + enquiryId + "';";

            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                assessmentStatusBEAN.setStatusId(rs.getString("assement_status_id"));
                assessmentStatusBEAN.setEnquiryId(rs.getString("enquiry_id"));
                assessmentStatusBEAN.setAssessmentStatus(rs.getString("enquiry_status"));
                if (rs.getString("remarks") != null) {
                    if (!rs.getString("remarks").equalsIgnoreCase("")) {
                        assessmentStatusBEAN.setRemarks(rs.getString("remarks"));

                    }
                }
                assessmentStatusBEAN.setPriority(rs.getString("priority"));
                if (rs.getString("date") != null) {
                    if (!rs.getString("date").equalsIgnoreCase("")) {
                        assessmentStatusBEAN.setAppointmentDate(LocalDate.parse(rs.getString("date")));

                    }
                }
                if (rs.getString("time") != null) {
                    if (!rs.getString("time").equalsIgnoreCase("")) {
                            try{
                        assessmentStatusBEAN.setAppointmentTime(LocalTime.parse(rs.getString("time")));
                        }  catch(DateTimeParseException e){
                            e.printStackTrace();
                        }

                    }
                }

                assessmentStatusBEAN.setBranch(rs.getString("branch"));
                assessmentStatusBEAN.setCounselor(rs.getString("counsellor"));
                assessmentStatusBEAN.setForwardFor(rs.getString("reference"));
            }

        } catch (SQLException sqle) {
            logger.info(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return assessmentStatusBEAN;
    }

}
