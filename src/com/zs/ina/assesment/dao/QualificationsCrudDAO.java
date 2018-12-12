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
package com.zs.ina.assesment.dao;

import com.zs.ina.assesment.model.AssesmentQualificationBEAN;
import com.zs.ina.assesment.model.AssesmentTertiaryBEAN;
import com.zs.ina.assesment.technical.dao.TechnicalSkilllsIMPL;
import com.zs.ina.common.GlobalClassDAO;
import com.zs.ina.common.UiiDGenerator;
import com.zs.ina.common.constants.TableNames;
import com.zs.ina.context.Context;
import com.zs.ina.utility.ClosingResourcesInDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import zs.com.ina.db.mysql.pool.DBPool;

/**
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class QualificationsCrudDAO {

    static Logger logger = Logger.getLogger(QualificationsCrudDAO.class);

    /* ========================= Delete Teritary Qualification Details==============================*/
    /**
     *
     * @param rowId
     */
    public static void deleteTeritaryQualifications(String rowId) {
        String _isLatestFlag = GlobalClassDAO.checkCurrentEnquiryIdHaveLatestFlag(rowId, TableNames.TABLE_ENQ_ASMNT_TERITARY_QUALI, "teriary_quali_id");
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        try {
            String query = "Delete from " + TableNames.TABLE_ENQ_ASMNT_TERITARY_QUALI + " where teriary_quali_id='" + rowId + "'";
            stmt = con.createStatement();
            row = stmt.executeUpdate(query);
            if (row == 1) {
                if (_isLatestFlag != null) {
                    GlobalClassDAO.updateLatestflagOnDelete(_isLatestFlag, TableNames.TABLE_ENQ_ASMNT_TERITARY_QUALI);
                }
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
    }

    /* ======== insert teritary quallifications ========*/
    /**
     *
     * @param assesmentTertiaryBEAN
     */
    public static void insertTeritaryQualifications(AssesmentTertiaryBEAN assesmentTertiaryBEAN) {
        String GENERATE_ID = "";
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        GENERATE_ID = "quali_" + UiiDGenerator.getUIID8();
        assesmentTertiaryBEAN.setRowId(GENERATE_ID);
        try {
            String sql = "INSERT INTO " + TableNames.TABLE_ENQ_ASMNT_TERITARY_QUALI + " (\n"
                    + "	teriary_quali_id,\n"
                    + "	enquiry_id,\n"
                    + "	teriary_quali_field,\n"
                    + "	teriary_quali_level,\n"
                    + "	university,\n"
                    + "	created_user,\n"
                    + "	created_date,\n"
                    + "	updated_date,\n"
                    + "	updated_user,\n"
                    + "	latest_flag\n"
                    + ")\n"
                    + "VALUES\n"
                    + "	(\n"
                    + "		?,\n"
                    + "		?,\n"
                    + "		?,\n"
                    + "		?,\n"
                    + "		?,\n"
                    + "		?,\n"
                    + "		NOW(),\n"
                    + "		NULL,\n"
                    + "		NULL,\n"
                    + "		'y'\n"
                    + "	);";
            String sqlReg = "INSERT INTO " + TableNames.TABLE_ENQ_ASMNT_TERITARY_QUALI + " (\n"
                    + "	teriary_quali_id,\n"
                    + "	enquiry_id,\n"
                    + "	teriary_quali_field,\n"
                    + "	teriary_quali_level,\n"
                    + "	university,\n"
                    + "	created_user,\n"
                    + "	created_date,\n"
                    + "	updated_date,\n"
                    + "	updated_user,\n"
                    + "	latest_flag,\n"
                    + "	mark_percentage,\n"
                    + "	mode_exam,\n"
                    + "	repeat_absent,\n"
                    + "	duration\n"
                    + ")\n"
                    + "VALUES\n"
                    + "	(\n"
                    + "		?,\n"
                    + "		?,\n"
                    + "		?,\n"
                    + "		?,\n"
                    + "		?,\n"
                    + "		?,\n"
                    + "		NOW(),\n"
                    + "		NULL,\n"
                    + "		NULL,\n"
                    + "		'y',\n"
                    + "		?,\n"
                    + "		?,\n"
                    + "		?,\n"
                    + "		?\n"
                    + "	);\n"
                    + "";
            PreparedStatement ps = con.prepareStatement(sqlReg);
            ps.setString(1, GENERATE_ID);
            ps.setString(2, assesmentTertiaryBEAN.getEnquiryId());
            ps.setString(3, assesmentTertiaryBEAN.getTertieryField());
            ps.setString(4, assesmentTertiaryBEAN.getTertieryLevel());
            ps.setString(5, assesmentTertiaryBEAN.getUniversity());
            ps.setString(6, Context.getInstance().currentProfile().getProfilePOJO().getUsername());
            ps.setString(7, assesmentTertiaryBEAN.getMarkPercentage());
            ps.setString(8, assesmentTertiaryBEAN.getModeExam());
            ps.setString(9, assesmentTertiaryBEAN.getExamRepeatAbsent());
            ps.setString(10, assesmentTertiaryBEAN.getDuration());
            row = ps.executeUpdate();
            if (row > 0) {
                assesmentTertiaryBEAN.setRowId(GENERATE_ID);
                GlobalClassDAO.updateLatestFlags(assesmentTertiaryBEAN.getEnquiryId(), TableNames.TABLE_ENQ_ASMNT_TERITARY_QUALI, "n", " teriary_quali_id NOT IN('"
                        + assesmentTertiaryBEAN.getRowId() + "')");
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());

        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
    }

    /**
     *
     * @param assesmentQualificationBEAN
     */
    public static void updateOtherQualifications(AssesmentQualificationBEAN assesmentQualificationBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        try {
            String query = "UPDATE enquiry_assessment_other_qalification_tbl\n" + "SET enquiry_id =?, qualification_level =?, field =?, duration =?, mark =?, ostatus =?\n" + "WHERE\tother_quali_id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, assesmentQualificationBEAN.getEnquiryId());
            ps.setString(2, assesmentQualificationBEAN.getQualificationLevel());
            ps.setString(3, assesmentQualificationBEAN.getQualificationField());
            ps.setString(4, assesmentQualificationBEAN.getQualificationDuration());
            ps.setString(5, assesmentQualificationBEAN.getQualifictionMark());
            ps.setString(6, assesmentQualificationBEAN.getQualificationstatus());
            ps.setString(7, assesmentQualificationBEAN.getRowId());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());

        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
    }

    //insert other qualifications from counserlor inbox
    /**
     *
     * @param assesmentQualificationBEAN
     */
    public static void insertOtherQualifications(AssesmentQualificationBEAN assesmentQualificationBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        String GENERATE_ENQ_ID = "";
        GENERATE_ENQ_ID = "TEMP" + UiiDGenerator.getUIID8();
        try {
            String query = "Insert into enquiry_assessment_other_qalification_tbl(enquiry_id,qualification_level,field,duration,mark,ostatus,other_quali_id)" + " values(?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, assesmentQualificationBEAN.getEnquiryId());
            ps.setString(2, assesmentQualificationBEAN.getQualificationLevel());
            ps.setString(3, assesmentQualificationBEAN.getQualificationField());
            ps.setString(4, assesmentQualificationBEAN.getQualificationDuration());
            ps.setString(5, assesmentQualificationBEAN.getQualifictionMark());
            ps.setString(6, assesmentQualificationBEAN.getQualificationstatus());
            ps.setString(7, GENERATE_ENQ_ID);
            ps.executeUpdate();
            assesmentQualificationBEAN.setRowId(GENERATE_ENQ_ID);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());

        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
    }

    /**
     *
     * @param enquiryId
     * @param assesmentQualificationPOJOs
     */
    public static void otherQualificationInsert(String enquiryId, List<AssesmentQualificationBEAN> assesmentQualificationPOJOs) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        String GENERATE_ENQ_ID = Context.getInstance().currentProfile().getProfilePOJO().getBranch().substring(0, 3).toLowerCase();
        try {
            for (AssesmentQualificationBEAN assesmentQualificationPOJO : assesmentQualificationPOJOs) {
                GENERATE_ENQ_ID = GENERATE_ENQ_ID + UiiDGenerator.getUIID8();
                String query = "Insert into enquiry_assessment_other_qalification_tbl(enquiry_id,qualification_level,field,duration,mark,ostatus,other_quali_id)" + " values(?,?,?,?,?,?,?)";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, enquiryId);
                ps.setString(2, assesmentQualificationPOJO.getQualificationLevel());
                ps.setString(3, assesmentQualificationPOJO.getQualificationField());
                ps.setString(4, assesmentQualificationPOJO.getQualificationDuration());
                ps.setString(5, assesmentQualificationPOJO.getQualifictionMark());
                ps.setString(6, assesmentQualificationPOJO.getQualificationstatus());
                ps.setString(7, GENERATE_ENQ_ID);
                ps.executeUpdate();
                GENERATE_ENQ_ID = Context.getInstance().currentProfile().getProfilePOJO().getBranch().substring(0, 3).toLowerCase();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());

        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
    }

    /* ======== UPDATE TERITARY QUALIFICATIONS ================ */
    /**
     *
     * @param assesmentTertiaryBEAN
     */
    public static void updateTeritaryQualifications(AssesmentTertiaryBEAN assesmentTertiaryBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        try {
            String sql = "UPDATE " + TableNames.TABLE_ENQ_ASMNT_TERITARY_QUALI + "\n"
                    + "SET teriary_quali_field = ?,\n"
                    + " teriary_quali_level = ?,\n"
                    + " university = ?,\n"
                    + " mark_percentage = ?,\n"
                    + " mode_exam = ?,\n"
                    + " repeat_absent = ?,\n"
                    + " duration = ?,\n"
                    + " updated_date = NOW(),latest_flag='y',\n"
                    + " updated_user = '" + Context.getInstance().currentProfile().getProfilePOJO().getUsername() + "'\n"
                    + " WHERE \n"
                    + "	teriary_quali_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, assesmentTertiaryBEAN.getTertieryField());
            ps.setString(2, assesmentTertiaryBEAN.getTertieryLevel());
            ps.setString(3, assesmentTertiaryBEAN.getUniversity());
            ps.setString(4, assesmentTertiaryBEAN.getMarkPercentage());
            ps.setString(5, assesmentTertiaryBEAN.getModeExam());
            ps.setString(6, assesmentTertiaryBEAN.getExamRepeatAbsent());
            ps.setString(7, assesmentTertiaryBEAN.getDuration());
            ps.setString(8, assesmentTertiaryBEAN.getRowId());
            row = ps.executeUpdate();
            if (row > 0) {
                GlobalClassDAO.updateLatestFlags(assesmentTertiaryBEAN.getEnquiryId(), TableNames.TABLE_ENQ_ASMNT_TERITARY_QUALI, "n", " teriary_quali_id NOT IN('"
                        + assesmentTertiaryBEAN.getRowId() + "')");
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());

        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
    }

    /* ======****** retrieve teritary qualifications ****** =================== */
    /**
     *
     * @param enquiryId
     * @return
     */
    public static List<AssesmentTertiaryBEAN> retrieveTeritaryQualifications(String enquiryId) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<AssesmentTertiaryBEAN> assesmentTertiaryBEANs = new ArrayList<AssesmentTertiaryBEAN>();
        try {
            String query = "Select * from " + TableNames.TABLE_ENQ_ASMNT_TERITARY_QUALI + " where enquiry_id='" + enquiryId + "'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                AssesmentTertiaryBEAN assesmentTertiaryBEAN = new AssesmentTertiaryBEAN();
                assesmentTertiaryBEAN.setRowId(rs.getString("teriary_quali_id"));
                assesmentTertiaryBEAN.setEnquiryId(rs.getString("enquiry_id"));
                assesmentTertiaryBEAN.setTertieryField(rs.getString("teriary_quali_field"));
                assesmentTertiaryBEAN.setTertieryLevel(rs.getString("teriary_quali_level"));
                assesmentTertiaryBEAN.setUniversity(rs.getString("university"));
                assesmentTertiaryBEAN.setMarkPercentage(rs.getString("mark_percentage"));
                assesmentTertiaryBEAN.setModeExam(rs.getString("mode_exam"));
                assesmentTertiaryBEAN.setExamRepeatAbsent(rs.getString("repeat_absent"));
                assesmentTertiaryBEAN.setDuration(rs.getString("duration"));
                assesmentTertiaryBEANs.add(assesmentTertiaryBEAN);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());

        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return assesmentTertiaryBEANs;
    }

}
