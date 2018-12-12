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

import com.zs.ina.assesment.model.AssesmentLanguageTestBEAN;
import com.zs.ina.assesment.model.AssesmentSpouseExpBEAN;
import com.zs.ina.assesment.model.AssesmentTertiaryBEAN;
import com.zs.ina.assesment.model.SpouseDetailsBEAN;
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
public class SpouseCrudDAO {

    static Logger logger = Logger.getLogger(SpouseCrudDAO.class);

    /**
     *
     * @param rowId
     */
    public static void deleteSpouseQualDetails(String rowId) {
        String _isLatestFlag = GlobalClassDAO.checkCurrentEnquiryIdHaveLatestFlag(rowId, TableNames.TABLE_ENQ_ASMNT_SPOUSE_QUALI, "spouse_qual_id");
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        try {
            String query = "Delete from " + TableNames.TABLE_ENQ_ASMNT_SPOUSE_QUALI + " where spouse_qual_id='" + rowId + "'";
            stmt = con.createStatement();
            row = stmt.executeUpdate(query);
            if (row == 1) {
                if (_isLatestFlag != null) {
                    GlobalClassDAO.updateLatestflagOnDelete(_isLatestFlag, TableNames.TABLE_ENQ_ASMNT_SPOUSE_QUALI);
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

    //<----------------------- Update Spouse Details-------------------------->
    /**
     *
     * @param assesmentSpouseDetailsBEAN
     */
    public static void updateSpouseDetails(SpouseDetailsBEAN assesmentSpouseDetailsBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        String query = "UPDATE " + TableNames.TABLE_ENQ_ASMNT_SPOUSE_DETAILS + "\n"
                + " SET spouse_name = ?,\n"
                + " spouse_age = ?,\n"
                + " sslc_board = ?,\n"
                + " plus2_board = ?,\n"
                + " sslc_medium = ?,\n"
                + " plus2_field = ?,\n"
                + " updated_date = NOW(),\n"
                + " updated_user = ?\n"
                + "WHERE\n"
                + " enquiry_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, assesmentSpouseDetailsBEAN.getSpouseName());
            ps.setString(2, assesmentSpouseDetailsBEAN.getSpouseAge());
            ps.setString(3, assesmentSpouseDetailsBEAN.getSslcBoard());
            ps.setString(4, assesmentSpouseDetailsBEAN.getPlusTwoBoard());
            ps.setString(5, assesmentSpouseDetailsBEAN.getSslcMedium());
            ps.setString(6, assesmentSpouseDetailsBEAN.getPlusTwoField());
            ps.setString(7, Context.getInstance().currentProfile().getProfilePOJO().getUsername());
            ps.setString(8, assesmentSpouseDetailsBEAN.getEnquiryId());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
    }

    //<----------------------- Insert Spouse Details-------------------------->
    /**
     *
     * @param assesmentSpouseDetailsBEAN
     */
    public static void insertSpouseDetails(SpouseDetailsBEAN assesmentSpouseDetailsBEAN) {
        String GENERATE_ENQID = "spo_" + UiiDGenerator.getUIID8();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        String query = "INSERT INTO " + TableNames.TABLE_ENQ_ASMNT_SPOUSE_DETAILS + " (\n"
                + "	spouse_id,\n"
                + "	spouse_Name,\n"
                + "	enquiry_id,\n"
                + "	spouse_age,\n"
                + "	sslc_board,\n"
                + "	plus2_board,\n"
                + "	sslc_medium,\n"
                + "	plus2_field,\n"
                + "	created_user,\n"
                + "	created_date,\n"
                + "	updated_date,\n"
                + "	updated_user,\n"
                + "	latest_flag\n"
                + ")\n"
                + "VALUES\n"
                + "	(\n"
                + "		'" + GENERATE_ENQID + "',\n"
                + "		'" + assesmentSpouseDetailsBEAN.getSpouseName() + "',\n"
                + "		'" + assesmentSpouseDetailsBEAN.getEnquiryId() + "',\n"
                + "		'" + assesmentSpouseDetailsBEAN.getSpouseAge() + "',\n"
                + "		'" + assesmentSpouseDetailsBEAN.getSslcBoard() + "',\n"
                + "		'" + assesmentSpouseDetailsBEAN.getPlusTwoBoard() + "',\n"
                + "		'" + assesmentSpouseDetailsBEAN.getSslcMedium() + "',\n"
                + "		'" + assesmentSpouseDetailsBEAN.getPlusTwoField() + "',\n"
                + "		'" + Context.getInstance().currentProfile().getProfilePOJO().getUsername() + "',\n"
                + "		NOW(),\n"
                + "		NULL,\n"
                + "		NULL,\n"
                + "		'y'\n"
                + "	);\n"
                + "";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            if (ps.executeUpdate() > 0) {
                assesmentSpouseDetailsBEAN.setRowId(GENERATE_ENQID);
                GlobalClassDAO.updateLatestFlags(assesmentSpouseDetailsBEAN.getEnquiryId(), TableNames.TABLE_ENQ_ASMNT_SPOUSE_DETAILS, "n", " spouse_id NOT IN('" + assesmentSpouseDetailsBEAN.getRowId() + "')");
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
    }

    //<<---------------------Method for get spouse language tes details---------------------->>
    /**
     *
     * @param enquiryId
     * @return
     */
    public static List<AssesmentLanguageTestBEAN> getSpouseLanuageTestDetails(String enquiryId) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<AssesmentLanguageTestBEAN> assesmentLanguageTestBEANs = new ArrayList<AssesmentLanguageTestBEAN>();
        try {
            String query = "Select * from " + TableNames.TABLE_ENQ_ASMNT_SPOUSE_LANG_TEST + " where enquiry_id='" + enquiryId + "'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                AssesmentLanguageTestBEAN assesmentLanguageTestBEAN = new AssesmentLanguageTestBEAN();
                assesmentLanguageTestBEAN.setRowId(rs.getString("spouse_language_test_id"));
                assesmentLanguageTestBEAN.setEnquiryId(rs.getString("enquiry_id"));
                assesmentLanguageTestBEAN.setLanguageTest(rs.getString("test"));
                assesmentLanguageTestBEAN.setStatus(rs.getString("status"));
                assesmentLanguageTestBEAN.setReading(rs.getString("reading"));
                assesmentLanguageTestBEAN.setWriting(rs.getString("writing"));
                assesmentLanguageTestBEAN.setSpeaking(rs.getString("speaking"));
                assesmentLanguageTestBEAN.setListening(rs.getString("listening"));
                assesmentLanguageTestBEAN.setOverall(rs.getString("overall"));
                assesmentLanguageTestBEANs.add(assesmentLanguageTestBEAN);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return assesmentLanguageTestBEANs;
    }

    //<-------------Method for update spouse qualification details------>
    /**
     *
     * @param assesmentSpouseExpBEAN
     */
    public static void updateSpouseExpDetails(AssesmentSpouseExpBEAN assesmentSpouseExpBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        try {
            String sql = "UPDATE  " + TableNames.TABLE_ENQ_ASMNT_SPOUSE_EXP + " \n"
                    + "SET  \n"
                    + "  profession  = ?,\n"
                    + "  duration  = ?,\n"
                    + "  updated_date  = NOW(),\n"
                    + "  updated_user  = ?,\n"
                    + "  latest_flag  = 'y'\n"
                    + "WHERE\n"
                    + "		 spouse_exp_id  = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, assesmentSpouseExpBEAN.getProfession());
            ps.setString(2, assesmentSpouseExpBEAN.getDuration());
            ps.setString(3, Context.getInstance().currentProfile().getProfilePOJO().getUsername());
            ps.setString(4, assesmentSpouseExpBEAN.getRowId());
            row = ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
    }

    //<-------------Method for Update spouse qualification details------>
    /**
     *
     * @param assesmentLanguageTestBEAN
     */
    public static void updateSpouseLanguageTestDetails(AssesmentLanguageTestBEAN assesmentLanguageTestBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        try {
            String sql = "UPDATE " + TableNames.TABLE_ENQ_ASMNT_SPOUSE_LANG_TEST + "\n"
                    + "SET test = ?,\n"
                    + " STATUS = ?,\n"
                    + " reading = ?,\n"
                    + " writing = ?,\n"
                    + " speaking = ?,\n"
                    + " listening = ?,\n"
                    + " overall = ?,\n"
                    + " updated_date = NOW(),\n"
                    + " updated_user =? \n"
                    + "WHERE\n"
                    + "	spouse_language_test_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, assesmentLanguageTestBEAN.getLanguageTest());
            ps.setString(2, assesmentLanguageTestBEAN.getStatus());
            ps.setString(3, assesmentLanguageTestBEAN.getReading());
            ps.setString(4, assesmentLanguageTestBEAN.getWriting());
            ps.setString(5, assesmentLanguageTestBEAN.getSpeaking());
            ps.setString(6, assesmentLanguageTestBEAN.getListening());
            ps.setString(7, assesmentLanguageTestBEAN.getOverall());
            ps.setString(8, Context.getInstance().currentProfile().getProfilePOJO().getUsername());
            ps.setString(9, assesmentLanguageTestBEAN.getRowId());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
    }
    //update language test details

    //<<---------------------Method for get spouse qualification details---------------------->>
    /**
     *
     * @param enquiryId
     * @return
     */
    public static List<AssesmentTertiaryBEAN> getSpouseQualDetails(String enquiryId) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<AssesmentTertiaryBEAN> assesmentTertiaryBEANs = new ArrayList<AssesmentTertiaryBEAN>();
        try {
            String query = "Select * from " + TableNames.TABLE_ENQ_ASMNT_SPOUSE_QUALI + " where enquiry_id='" + enquiryId + "'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                AssesmentTertiaryBEAN assesmentTertiaryBEAN = new AssesmentTertiaryBEAN();
                assesmentTertiaryBEAN.setRowId(rs.getString("spouse_qual_id"));
                assesmentTertiaryBEAN.setEnquiryId(rs.getString("enquiry_id"));
                assesmentTertiaryBEAN.setTertieryField(rs.getString("field"));
                assesmentTertiaryBEAN.setTertieryLevel(rs.getString("level"));
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

    //<------------Method for delete spouse Language test details-------->
    /**
     *
     * @param assesmentLanguageTestBEAN
     * @param rowId
     */
    public static void deleteSpouseLanguageTestDetails(String rowId) {
        String _isLatestFlag = GlobalClassDAO.checkCurrentEnquiryIdHaveLatestFlag(rowId, TableNames.TABLE_ENQ_ASMNT_SPOUSE_LANG_TEST, "spouse_language_test_id");
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        try {
            String query = "Delete from " + TableNames.TABLE_ENQ_ASMNT_SPOUSE_LANG_TEST + " where spouse_language_test_id='" + rowId + "'";
            stmt = con.createStatement();
            row = stmt.executeUpdate(query);
            if (row == 1) {
                if (_isLatestFlag != null) {
                    GlobalClassDAO.updateLatestflagOnDelete(_isLatestFlag, TableNames.TABLE_ENQ_ASMNT_SPOUSE_LANG_TEST);
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

    //<<---------------------Method for get spouse Experience details---------------------->>
    /**
     *
     * @param enquiryId
     * @return
     */
    public static List<AssesmentSpouseExpBEAN> getSpouseExpDetails(String enquiryId) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<AssesmentSpouseExpBEAN> assesmentSpouseExpBEANs = new ArrayList<AssesmentSpouseExpBEAN>();
        try {
            String query = "Select * from " + TableNames.TABLE_ENQ_ASMNT_SPOUSE_EXP + " where enquiry_id='" + enquiryId + "'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                AssesmentSpouseExpBEAN assesmentSpouseExpBEAN = new AssesmentSpouseExpBEAN();
                assesmentSpouseExpBEAN.setRowId(rs.getString("spouse_exp_id"));
                assesmentSpouseExpBEAN.setEnquiryId(rs.getString("enquiry_id"));
                assesmentSpouseExpBEAN.setProfession(rs.getString("profession"));
                assesmentSpouseExpBEAN.setDuration(rs.getString("duration"));
                assesmentSpouseExpBEANs.add(assesmentSpouseExpBEAN);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return assesmentSpouseExpBEANs;
    }

    //<---------------------Delete Spouse Details------------------------------>
    /**
     *
     * @param enquiryId
     */
    public static void deleteDpouseDetails(String enquiryId) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        String query = "Delete from " + TableNames.TABLE_ENQ_ASMNT_SPOUSE_QUALI + " where enquiry_id=?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, enquiryId);
            ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
    }

    /*====================Check Spouse details=======================*/
    /**
     *
     * @param enquiryId
     * @return
     */
    public static boolean checkSpouseDetails(String enquiryId) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        boolean f = false;
        con = DBPool.getInstance().getConn();
        String query = "SELECT EXISTS(select 1 from " + TableNames.TABLE_ENQ_ASMNT_SPOUSE_QUALI + " where enquiry_id='" + enquiryId + "')";
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            rs.next();
            f = rs.getBoolean(1);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return f;
    }

    //<-----------------------------Method to get spouse Details---------------------->
    /**
     *
     * @param enquiryId
     * @return
     */
    public static SpouseDetailsBEAN getSpouseDetails(String enquiryId) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        SpouseDetailsBEAN assesmentSpouseDetailsBEAN = new SpouseDetailsBEAN();
        String query = "Select * from " + TableNames.TABLE_ENQ_ASMNT_SPOUSE_DETAILS + " where enquiry_id='" + enquiryId + "'";
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                assesmentSpouseDetailsBEAN.setEnquiryId(rs.getString("enquiry_id"));
                assesmentSpouseDetailsBEAN.setRowId(rs.getString("spouse_id"));
                assesmentSpouseDetailsBEAN.setSpouseName(rs.getString("spouse_Name"));
                assesmentSpouseDetailsBEAN.setSpouseAge(rs.getString("spouse_age"));
                assesmentSpouseDetailsBEAN.setSslcBoard(rs.getString("sslc_board"));
                assesmentSpouseDetailsBEAN.setSslcMedium(rs.getString("sslc_medium"));
                assesmentSpouseDetailsBEAN.setPlusTwoBoard(rs.getString("plus2_board"));
                assesmentSpouseDetailsBEAN.setPlusTwoField(rs.getString("plus2_field"));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return assesmentSpouseDetailsBEAN;
    }

    //<-------------Method for update spouse qualification details------>
    /**
     *
     * @param assesmentTertiaryBEAN
     */
    public static void updateSpouseQualDetails(AssesmentTertiaryBEAN assesmentTertiaryBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        try {
            String sql = "UPDATE " + TableNames.TABLE_ENQ_ASMNT_SPOUSE_QUALI + "\n"
                    + "SET LEVEL = ?, field = ?, updated_date = NOW(),\n"
                    + " updated_user = ?\n"
                    + "WHERE\n"
                    + "	spouse_qual_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, assesmentTertiaryBEAN.getTertieryLevel());
            ps.setString(2, assesmentTertiaryBEAN.getTertieryField());
            ps.setString(3, Context.getInstance().currentProfile().getProfilePOJO().getUsername());
            ps.setString(4, assesmentTertiaryBEAN.getRowId());
            row = ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
    }

    //<-------------Method for insert spouse qualification details------>
    /**
     *
     * @param assesmentSpouseExpBEAN
     */
    public static void insertSpouseExpDetails(AssesmentSpouseExpBEAN assesmentSpouseExpBEAN) {
        String GENERATE_ID = Context.getInstance().currentProfile().getProfilePOJO().getBranch().substring(0, 3);
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        GENERATE_ID = GENERATE_ID + UiiDGenerator.getUIID8();
        assesmentSpouseExpBEAN.setRowId(GENERATE_ID);
        try {
            String sql = "INSERT INTO  " + TableNames.TABLE_ENQ_ASMNT_SPOUSE_EXP + "  (\n"
                    + "	 spouse_exp_id ,\n"
                    + "	 enquiry_id ,\n"
                    + "	 profession ,\n"
                    + "	 duration ,\n"
                    + "	 created_user ,\n"
                    + "	 created_date ,\n"
                    + "	 updated_date ,\n"
                    + "	 updated_user ,\n"
                    + "	 latest_flag \n"
                    + ")\n"
                    + "VALUES\n"
                    + "	(\n"
                    + "		?,\n"
                    + "		?,\n"
                    + "		?,\n"
                    + "		?,\n"
                    + "		?,\n"
                    + "		now(),\n"
                    + "		NULL,\n"
                    + "		NULL,\n"
                    + "		'y'\n"
                    + "	);";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, GENERATE_ID);
            ps.setString(2, assesmentSpouseExpBEAN.getEnquiryId());
            ps.setString(3, assesmentSpouseExpBEAN.getProfession());
            ps.setString(4, assesmentSpouseExpBEAN.getDuration());
            ps.setString(5, Context.getInstance().currentProfile().getProfilePOJO().getUsername());
            row = ps.executeUpdate();
            if (row > 0) {
                assesmentSpouseExpBEAN.setRowId(GENERATE_ID);
                GlobalClassDAO.updateLatestFlags(assesmentSpouseExpBEAN.getEnquiryId(), TableNames.TABLE_ENQ_ASMNT_SPOUSE_EXP, "n", " spouse_exp_id NOT IN('" + GENERATE_ID + "')");
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
    }

    //<------------Method for delete spouse Qualfification details-------->
    /**
     *
     * @param assesmentSpouseExpBEAN
     * @param rowId
     */
    public static void deleteSpouseExpDetails(String rowId) {
        String _isLatestFlag = GlobalClassDAO.checkCurrentEnquiryIdHaveLatestFlag(rowId, TableNames.TABLE_ENQ_ASMNT_SPOUSE_EXP, "spouse_exp_id");
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        try {
            String query = "Delete from " + TableNames.TABLE_ENQ_ASMNT_SPOUSE_EXP + " where spouse_exp_id='" + rowId + "'";
            stmt = con.createStatement();
            row = stmt.executeUpdate(query);
            if (row == 1) {
                if (_isLatestFlag != null) {
                    GlobalClassDAO.updateLatestflagOnDelete(_isLatestFlag, TableNames.TABLE_ENQ_ASMNT_SPOUSE_EXP);
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

    //<-------------Method for insert spouse qualification details------>
    /**
     *
     * @param assesmentLanguageTestBEAN
     */
    public static void insertSpouseLanguageTestDetails(AssesmentLanguageTestBEAN assesmentLanguageTestBEAN) {
        String GENERATE_ID = Context.getInstance().currentProfile().getProfilePOJO().getBranch().substring(0, 3);
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        GENERATE_ID = GENERATE_ID + UiiDGenerator.getUIID8();
        assesmentLanguageTestBEAN.setRowId(GENERATE_ID);
        try {
            String sql = "INSERT INTO " + TableNames.TABLE_ENQ_ASMNT_SPOUSE_LANG_TEST + " (\n"
                    + "	spouse_language_test_id,\n"
                    + "	enquiry_id,\n"
                    + "	test,\n"
                    + "	STATUS,\n"
                    + "	reading,\n"
                    + "	writing,\n"
                    + "	speaking,\n"
                    + "	listening,\n"
                    + "	overall,\n"
                    + "	created_user,\n"
                    + "	created_date,\n"
                    + "	updated_date,\n"
                    + "	updated_user,\n"
                    + "	latest_flag\n"
                    + ")\n"
                    + "VALUES\n"
                    + "	(\n"
                    + "		?, ?, ?, ?, ?, ?, ?, ?,?,\n"
                    + "		?,\n"
                    + "		NOW(),\n"
                    + "		NULL,\n"
                    + "		NULL,\n"
                    + "		'y'\n"
                    + "	);";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, GENERATE_ID);
            ps.setString(2, assesmentLanguageTestBEAN.getEnquiryId());
            ps.setString(3, assesmentLanguageTestBEAN.getLanguageTest());
            ps.setString(4, assesmentLanguageTestBEAN.getStatus());
            ps.setString(5, assesmentLanguageTestBEAN.getReading());
            ps.setString(6, assesmentLanguageTestBEAN.getWriting());
            ps.setString(7, assesmentLanguageTestBEAN.getSpeaking());
            ps.setString(8, assesmentLanguageTestBEAN.getListening());
            ps.setString(9, assesmentLanguageTestBEAN.getOverall());
            ps.setString(10, Context.getInstance().currentProfile().getProfilePOJO().getUsername());
            row = ps.executeUpdate();
            if (row > 0) {
                assesmentLanguageTestBEAN.setRowId(GENERATE_ID);
                GlobalClassDAO.updateLatestFlags(assesmentLanguageTestBEAN.getEnquiryId(), TableNames.TABLE_ENQ_ASMNT_SPOUSE_LANG_TEST, "n", " spouse_language_test_id NOT IN('" + GENERATE_ID + "')");
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
    }

    //<-------------Method for insert spouse qualification details------>
    /**
     *
     * @param assesmentTertiaryBEAN
     */
    public static void insertSpouseQualDetails(AssesmentTertiaryBEAN assesmentTertiaryBEAN) {
        String GENERATE_ID = Context.getInstance().currentProfile().getProfilePOJO().getBranch().substring(0, 3);
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        GENERATE_ID = GENERATE_ID + UiiDGenerator.getUIID8();
        assesmentTertiaryBEAN.setRowId(GENERATE_ID);
        try {
            String sql = "INSERT INTO " + TableNames.TABLE_ENQ_ASMNT_SPOUSE_QUALI + " (\n"
                    + "	spouse_qual_id,\n"
                    + "	enquiry_id,\n"
                    + "	LEVEL,\n"
                    + "	field,\n"
                    + "	created_user,\n"
                    + "	created_date,\n"
                    + "	updated_date,\n"
                    + "	updated_user,\n"
                    + "	latest_flag\n"
                    + ")\n"
                    + "VALUES\n"
                    + "	(\n"
                    + "		?, ?, ?, ?, ?, NOW(),\n"
                    + "		NULL,\n"
                    + "		NULL,\n"
                    + "		'y'\n"
                    + "	);";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, GENERATE_ID);
            ps.setString(2, assesmentTertiaryBEAN.getEnquiryId());
            ps.setString(3, assesmentTertiaryBEAN.getTertieryLevel());
            ps.setString(4, assesmentTertiaryBEAN.getTertieryField());
            ps.setString(5, Context.getInstance().currentProfile().getProfilePOJO().getUsername());
            row = ps.executeUpdate();
            if (row > 0) {
                assesmentTertiaryBEAN.setRowId(GENERATE_ID);
                GlobalClassDAO.updateLatestFlags(assesmentTertiaryBEAN.getEnquiryId(), TableNames.TABLE_ENQ_ASMNT_SPOUSE_QUALI, "n", " spouse_qual_id NOT IN('" + GENERATE_ID + "')");
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
    }

}
