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
import zs.com.ina.db.mysql.pool.DBPool;

/**
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class LanguageTestCrudDAO {

    //Method for get other qualification details
    /**
     *
     * @param enquiryId
     * @return
     */
    public static List<AssesmentLanguageTestBEAN> getLanguageTestDetails(String enquiryId) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<AssesmentLanguageTestBEAN> assesmentLanguageTestBEANs = new ArrayList<AssesmentLanguageTestBEAN>();
        try {
            String query = "Select * from " + TableNames.TABLE_ENQ_ASMNT_LANG_TEST + " where enquiry_id='" + enquiryId + "'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                AssesmentLanguageTestBEAN assesmentLanguageTestBEAN = new AssesmentLanguageTestBEAN();
                if (rs.getString("reading") != null) {
                    if (!rs.getString("reading").equalsIgnoreCase("")) {
                        assesmentLanguageTestBEAN.setReading(rs.getString("reading"));

                    }
                }
                if (rs.getString("writing") != null) {
                    if (!rs.getString("writing").equalsIgnoreCase("")) {
                        assesmentLanguageTestBEAN.setWriting(rs.getString("writing"));

                    }
                }
                if (rs.getString("speaking") != null) {
                    if (!rs.getString("speaking").equalsIgnoreCase("")) {
                        assesmentLanguageTestBEAN.setSpeaking(rs.getString("speaking"));

                    }
                }
                if (rs.getString("listening") != null) {
                    if (!rs.getString("listening").equalsIgnoreCase("")) {
                        assesmentLanguageTestBEAN.setListening(rs.getString("listening"));

                    }
                }
                assesmentLanguageTestBEAN.setRowId(rs.getString("spouse_language_test_id"));
                assesmentLanguageTestBEAN.setEnquiryId(rs.getString("enquiry_id"));
                assesmentLanguageTestBEAN.setLanguageTest(rs.getString("test"));
                assesmentLanguageTestBEAN.setReading(rs.getString("reading"));
                assesmentLanguageTestBEAN.setListening(rs.getString("listening"));
                assesmentLanguageTestBEAN.setWriting(rs.getString("writing"));
                assesmentLanguageTestBEAN.setSpeaking(rs.getString("speaking"));
                assesmentLanguageTestBEAN.setOverall(rs.getString("overall"));
                assesmentLanguageTestBEANs.add(assesmentLanguageTestBEAN);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return assesmentLanguageTestBEANs;
    }

    /**
     *
     * @param assesmentLanguageTestBEAN
     */
    public static void updateTertiaryLangTestDetails(AssesmentLanguageTestBEAN assesmentLanguageTestBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        try {
            String query = "update " + TableNames.TABLE_ENQ_ASMNT_LANG_TEST + " set enquiry_id=?,test=?,status=?,reading=?,writing=?,speaking=?,listening=?,overall=? where spouse_language_test_id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, assesmentLanguageTestBEAN.getEnquiryId());
            ps.setString(2, assesmentLanguageTestBEAN.getLanguageTest());
            ps.setString(3, assesmentLanguageTestBEAN.getStatus());
            ps.setString(4, assesmentLanguageTestBEAN.getReading());
            ps.setString(5, assesmentLanguageTestBEAN.getWriting());
            ps.setString(6, assesmentLanguageTestBEAN.getSpeaking());
            ps.setString(7, assesmentLanguageTestBEAN.getListening());
            ps.setString(8, assesmentLanguageTestBEAN.getOverall());
            ps.setString(9, assesmentLanguageTestBEAN.getRowId());
            ps.executeUpdate();
            System.out.println("lang test updated");
        } catch (SQLException sqle) {
            sqle.printStackTrace();
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
    public static void insertLanguageTestDetails(AssesmentLanguageTestBEAN assesmentLanguageTestBEAN) {
        String GENERATE_ID = Context.getInstance().currentProfile().getProfilePOJO().getBranch().substring(0, 3);
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        GENERATE_ID = GENERATE_ID + UiiDGenerator.getUIID8();
        assesmentLanguageTestBEAN.setRowId(GENERATE_ID);
        try {

            if (assesmentLanguageTestBEAN.getReading() != null) {
                assesmentLanguageTestBEAN.setReading(assesmentLanguageTestBEAN.getReading());
            } else {
                assesmentLanguageTestBEAN.setReading(null);
            }
            if (assesmentLanguageTestBEAN.getWriting() != null) {
                assesmentLanguageTestBEAN.setWriting(assesmentLanguageTestBEAN.getWriting());
            } else {
                assesmentLanguageTestBEAN.setWriting(null);
            }
            if (assesmentLanguageTestBEAN.getSpeaking() != null) {
                assesmentLanguageTestBEAN.setSpeaking(assesmentLanguageTestBEAN.getSpeaking());
            } else {
                assesmentLanguageTestBEAN.setSpeaking(null);
            }
            if (assesmentLanguageTestBEAN.getListening() != null) {
                assesmentLanguageTestBEAN.setListening(assesmentLanguageTestBEAN.getListening());
            } else {
                assesmentLanguageTestBEAN.setListening(null);
            }
            String sql = "INSERT INTO " + TableNames.TABLE_ENQ_ASMNT_LANG_TEST + " (\n" + "\tspouse_language_test_id,\n" + "\tenquiry_id,\n" + "\ttest,\n" + "\tstatus,\n" + "\treading,\n" + "\twriting,\n" + "\tspeaking,\n" + "\tlistening,\n" + "\toverall,\n" + "\tcreated_user,\n" + "\tcreated_date,\n" + "\tupdated_user,\n" + "\tupdated_date,\n" + "\tlatest_flag\n" + ")\n" + "VALUES\n" + "\t(\n" + "\t\t?,\n" + "\t\t?,\n" + "\t\t?,\n" + "\t\t?,\n" + "\t\t?,\n" + "\t\t?,\n" + "\t\t?,\n" + "\t\t?,\n" + "\t\t?,\n" + "\t\t?,\n" + "\t\tNOW(),\n" + "\t\tNULL,\n" + "\t\tNULL,\n" + "\t\t'y'\n" + "\t);\n" + "";
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
                GlobalClassDAO.updateLatestFlags(assesmentLanguageTestBEAN.getEnquiryId(), TableNames.TABLE_ENQ_ASMNT_LANG_TEST, "n", " spouse_language_test_id NOT IN('" + GENERATE_ID + "')");
            }
            assesmentLanguageTestBEAN.setRowId(GENERATE_ID);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
    }

    //Method for insert Trining details
    /**
     *
     * @param enquiryId
     * @param assesmentLanguageTestBEANs
     */
    public static void insertLanguageTestDetails(String enquiryId, List<AssesmentLanguageTestBEAN> assesmentLanguageTestBEANs) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int i = 0;
        String languageList = "";
        con = DBPool.getInstance().getConn();
        String GENERATE_ENQ_ID = Context.getInstance().currentProfile().getProfilePOJO().getBranch().substring(0, 3).toLowerCase();
        try {
            for (AssesmentLanguageTestBEAN assesmentLanguageTestBEAN : assesmentLanguageTestBEANs) {
                if (assesmentLanguageTestBEAN.getReading() != null) {
                    assesmentLanguageTestBEAN.setReading(assesmentLanguageTestBEAN.getReading());
                } else {
                    assesmentLanguageTestBEAN.setReading(null);
                }
                if (assesmentLanguageTestBEAN.getWriting() != null) {
                    assesmentLanguageTestBEAN.setWriting(assesmentLanguageTestBEAN.getWriting());
                } else {
                    assesmentLanguageTestBEAN.setWriting(null);
                }
                if (assesmentLanguageTestBEAN.getSpeaking() != null) {
                    assesmentLanguageTestBEAN.setSpeaking(assesmentLanguageTestBEAN.getSpeaking());
                } else {
                    assesmentLanguageTestBEAN.setSpeaking(null);
                }
                if (assesmentLanguageTestBEAN.getListening() != null) {
                    assesmentLanguageTestBEAN.setListening(assesmentLanguageTestBEAN.getListening());
                } else {
                    assesmentLanguageTestBEAN.setListening(null);
                }
                GENERATE_ENQ_ID = GENERATE_ENQ_ID + UiiDGenerator.getUIID8();
                String query = "Insert into " + TableNames.TABLE_ENQ_ASMNT_LANG_TEST + "(enquiry_id,language_test_attend,reading,"
                        + "listening,writing,speaking,overall,all_languages,id)"
                        + "values(?,?,?,?,?,?,?,?,?)";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, enquiryId);
                languageList = languageList + assesmentLanguageTestBEAN.getLanguageTest() + ",";
                ps.setString(2, assesmentLanguageTestBEAN.getLanguageTest());
                ps.setString(3, assesmentLanguageTestBEAN.getReading());
                ps.setString(4, assesmentLanguageTestBEAN.getListening());
                ps.setString(5, assesmentLanguageTestBEAN.getWriting());
                ps.setString(6, assesmentLanguageTestBEAN.getSpeaking());
                ps.setString(7, assesmentLanguageTestBEAN.getOverall());
                System.out.println("LANGUAGE LIST=====>" + languageList);
                ps.setString(8, languageList);
                ps.setString(9, GENERATE_ENQ_ID);
                if (ps.executeUpdate() > 0) {
                    assesmentLanguageTestBEAN.setRowId(GENERATE_ENQ_ID);
                    GlobalClassDAO.updateLatestFlags(GENERATE_ENQ_ID, TableNames.TABLE_ENQ_ASMNT_LANG_TEST, "n", " language_test_attend NOT IN('" + GENERATE_ENQ_ID + "')");
                }
            }
//            String query = "update " + TableNames.TABLE_ENQ_ASMNT_LANG_TEST + " set all_languages=? where enquiry_id=? ";
//            PreparedStatement ps = con.prepareStatement(query);
//            ps.setString(1, languageList);
//            ps.setString(2, enquiryId);
//            ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
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
    public static List<AssesmentLanguageTestBEAN> getTeritaryLanuageTestData(String enquiryId) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<AssesmentLanguageTestBEAN> assesmentLanguageTestBEANs = new ArrayList<AssesmentLanguageTestBEAN>();
        try {
            String query = "Select * from " + TableNames.TABLE_ENQ_ASMNT_LANG_TEST + " where enquiry_id='" + enquiryId + "'";
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

                if (assesmentLanguageTestBEAN.getReading() != null) {
                    assesmentLanguageTestBEAN.setReading(assesmentLanguageTestBEAN.getReading());
                } else {
                    assesmentLanguageTestBEAN.setReading(null);
                }
                if (assesmentLanguageTestBEAN.getWriting() != null) {
                    assesmentLanguageTestBEAN.setWriting(assesmentLanguageTestBEAN.getWriting());
                } else {
                    assesmentLanguageTestBEAN.setWriting(null);
                }
                if (assesmentLanguageTestBEAN.getSpeaking() != null) {
                    assesmentLanguageTestBEAN.setSpeaking(assesmentLanguageTestBEAN.getSpeaking());
                } else {
                    assesmentLanguageTestBEAN.setSpeaking(null);
                }
                if (assesmentLanguageTestBEAN.getListening() != null) {
                    assesmentLanguageTestBEAN.setListening(assesmentLanguageTestBEAN.getListening());
                } else {
                    assesmentLanguageTestBEAN.setListening(null);
                }
                assesmentLanguageTestBEANs.add(assesmentLanguageTestBEAN);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return assesmentLanguageTestBEANs;
    }

    /**
     *
     * @param enquiryId
     * @param assesmentLanguageTestBEAN
     */
    public static void updateLanguageTestDetails(String enquiryId, AssesmentLanguageTestBEAN assesmentLanguageTestBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String languageList = "";
        int row = 0;
        con = DBPool.getInstance().getConn();
        try {

            if (assesmentLanguageTestBEAN.getReading() != null) {
                assesmentLanguageTestBEAN.setReading(assesmentLanguageTestBEAN.getReading());
            } else {
                assesmentLanguageTestBEAN.setReading(null);
            }
            if (assesmentLanguageTestBEAN.getWriting() != null) {
                assesmentLanguageTestBEAN.setWriting(assesmentLanguageTestBEAN.getWriting());
            } else {
                assesmentLanguageTestBEAN.setWriting(null);
            }
            if (assesmentLanguageTestBEAN.getSpeaking() != null) {
                assesmentLanguageTestBEAN.setSpeaking(assesmentLanguageTestBEAN.getSpeaking());
            } else {
                assesmentLanguageTestBEAN.setSpeaking(null);
            }
            if (assesmentLanguageTestBEAN.getListening() != null) {
                assesmentLanguageTestBEAN.setListening(assesmentLanguageTestBEAN.getListening());
            } else {
                assesmentLanguageTestBEAN.setListening(null);
            }
            String sql = "UPDATE " + TableNames.TABLE_ENQ_ASMNT_LANG_TEST + "\n" + "SET test = ?,\n" + " reading = ?,\n" + " writing = ?,\n" + " speaking = ?,\n" + " listening = ?,\n" + " overall = ?,\n" + " updated_user = ?,\n" + " updated_date = NOW(),\n" + " latest_flag = 'y'\n" + "WHERE\n" + "\tspouse_language_test_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            languageList = languageList + assesmentLanguageTestBEAN.getLanguageTest() + ",";
            ps.setString(1, assesmentLanguageTestBEAN.getLanguageTest());
            ps.setString(2, assesmentLanguageTestBEAN.getReading());
            ps.setString(3, assesmentLanguageTestBEAN.getWriting());
            ps.setString(4, assesmentLanguageTestBEAN.getSpeaking());
            ps.setString(5, assesmentLanguageTestBEAN.getListening());
            ps.setString(6, assesmentLanguageTestBEAN.getOverall());
            ps.setString(7, Context.getInstance().currentProfile().getProfilePOJO().getUsername());
            System.out.println("LANGUAGE LIST=====>" + languageList);
            ps.setString(8, assesmentLanguageTestBEAN.getRowId());
            row = ps.executeUpdate();
            if (row > 0) {
                GlobalClassDAO.updateLatestFlags(assesmentLanguageTestBEAN.getEnquiryId(), TableNames.TABLE_ENQ_ASMNT_LANG_TEST, "n", " spouse_language_test_id NOT IN('" + assesmentLanguageTestBEAN.getRowId() + "')");
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
    }

    /**
     *
     * @param enquiryId
     * @param assesmentLanguageTestBEANs
     */
    public static void updateLanguageTestDetails(int enquiryId, List<AssesmentLanguageTestBEAN> assesmentLanguageTestBEANs) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int i = 0;
        String languageList = "";
        con = DBPool.getInstance().getConn();
        System.out.println("Enquryy idd==>>" + enquiryId);
        try {
            for (AssesmentLanguageTestBEAN assesmentLanguageTestBEAN : assesmentLanguageTestBEANs) {

                if (assesmentLanguageTestBEAN.getReading() != null) {
                    assesmentLanguageTestBEAN.setReading(assesmentLanguageTestBEAN.getReading());
                } else {
                    assesmentLanguageTestBEAN.setReading(null);
                }
                if (assesmentLanguageTestBEAN.getWriting() != null) {
                    assesmentLanguageTestBEAN.setWriting(assesmentLanguageTestBEAN.getWriting());
                } else {
                    assesmentLanguageTestBEAN.setWriting(null);
                }
                if (assesmentLanguageTestBEAN.getSpeaking() != null) {
                    assesmentLanguageTestBEAN.setSpeaking(assesmentLanguageTestBEAN.getSpeaking());
                } else {
                    assesmentLanguageTestBEAN.setSpeaking(null);
                }
                if (assesmentLanguageTestBEAN.getListening() != null) {
                    assesmentLanguageTestBEAN.setListening(assesmentLanguageTestBEAN.getListening());
                } else {
                    assesmentLanguageTestBEAN.setListening(null);
                }
                String query = "UPDATE " + TableNames.TABLE_ENQ_ASMNT_LANG_TEST + "\n" + "SET enquiry_id =?, language_test_attend =?, reading =?, listening =?, writing =?, speaking =?, overall =?, all_languages =?\n" + "WHERE\tid =?;";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, enquiryId);
                languageList = languageList + assesmentLanguageTestBEAN.getLanguageTest() + ",";
                ps.setString(2, assesmentLanguageTestBEAN.getLanguageTest());
                ps.setString(3, assesmentLanguageTestBEAN.getReading());
                ps.setString(4, assesmentLanguageTestBEAN.getListening());
                ps.setString(5, assesmentLanguageTestBEAN.getWriting());
                ps.setString(6, assesmentLanguageTestBEAN.getSpeaking());
                ps.setString(7, assesmentLanguageTestBEAN.getOverall());
                System.out.println("LANGUAGE LIST=====>" + languageList);
                ps.setString(8, languageList);
                ps.setInt(9, Integer.parseInt(assesmentLanguageTestBEAN.getRowId()));
                ps.executeUpdate();
            }
            String query = "update " + TableNames.TABLE_ENQ_ASMNT_LANG_TEST + " set all_languages=? where enquiry_id=? ";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, languageList);
            ps.setInt(2, enquiryId);
            ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
    }

    //<------------Method for delete spouse Language test details-------->
    /**
     *
     * @param rowId
     */
    public static void deleteTeritaryLanguageTestDetails(String rowId) {
        String _isLatestFlag = GlobalClassDAO.checkCurrentEnquiryIdHaveLatestFlag(rowId, TableNames.TABLE_ENQ_ASMNT_LANG_TEST, "spouse_language_test_id");
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        try {
            String query = "Delete from " + TableNames.TABLE_ENQ_ASMNT_LANG_TEST + " where spouse_language_test_id='" + rowId + "'";
            stmt = con.createStatement();
            row = stmt.executeUpdate(query);
            if (row == 1) {
                if (_isLatestFlag != null) {
                    GlobalClassDAO.updateLatestflagOnDelete(rowId, TableNames.TABLE_ENQ_ASMNT_LANG_TEST);
                }
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
    }

}
