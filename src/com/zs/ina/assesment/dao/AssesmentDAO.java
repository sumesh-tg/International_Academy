/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.assesment.dao;

import com.zs.ina.assesment.model.AssesmentOtherTestBEAN;
import com.zs.ina.assesment.model.WorktExperienceBEAN;
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
import java.util.List;
import zs.com.ina.db.mysql.pool.DBPool;

/**
 *
 * @author user
 */
public class AssesmentDAO {

    /**
     *
     * @param enquiryId
     * @return
     */
    public static boolean setReadFlag(String enquiryId) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        boolean completion = false;
        con = DBPool.getInstance().getConn();
        String sql = "UPDATE " + TableNames.TABLE_ENQ_DETAILS + "\n"
                + "SET read_flag = 1\n"
                + "WHERE\n"
                + "enquiry_id = '" + enquiryId + "'";
        try {
            stmt = con.createStatement();
            completion = stmt.execute(sql);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return completion;

    }

    // Set assement Modifed Date
    /**
     *
     * @param enquiryID
     */
    public static void updateModifiedDate(String enquiryID) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        String GENERATE_ENQ_ID = Context.getInstance().currentProfile().getProfilePOJO().getBranch().substring(0, 3).toLowerCase();
        GENERATE_ENQ_ID = GENERATE_ENQ_ID + UiiDGenerator.getUIID8();
        con = DBPool.getInstance().getConn();

        String query = "";
        try {
            if (checkModifedDate(enquiryID)) {
                query = "Update " + TableNames.TABLE_ENQ_DATE_MODIFIED + " "
                        + "set modified_date=curdate() , updated_date=NOW(),updated_user=? "
                        + " where enquiry_id=?";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, Context.getInstance().currentProfile().getProfilePOJO().getUsername());
                ps.setString(2, enquiryID);
                ps.executeUpdate();
            } else {
                query = "INSERT INTO " + TableNames.TABLE_ENQ_DATE_MODIFIED + " (\n"
                        + "	id,\n"
                        + "	enquiry_id,\n"
                        + "	modified_date,\n"
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
                        + "		curdate(),\n"
                        + "		?,\n"
                        + "		now(),\n"
                        + "		NULL,\n"
                        + "		NULL,\n"
                        + "		'y'\n"
                        + "	);\n"
                        + "";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, GENERATE_ENQ_ID);
                ps.setString(2, enquiryID);
                ps.setString(3, Context.getInstance().currentProfile().getProfilePOJO().getUsername());
                row = ps.executeUpdate();
                if (row > 0) {
                    GlobalClassDAO.updateLatestFlags(enquiryID, TableNames.TABLE_ENQ_DATE_MODIFIED, "n", " id NOT IN('" + GENERATE_ENQ_ID + "')");
                }
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
     * @param enquiryID
     * @return
     */
    public static boolean checkModifedDate(String enquiryID) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        boolean flag = false;
        con = DBPool.getInstance().getConn();
        try {
            String query = "SELECT EXISTS(SELECT 1 FROM " + TableNames.TABLE_ENQ_DATE_MODIFIED + " WHERE enquiry_id='" + enquiryID + "')";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            rs.next();
            flag = rs.getBoolean(1);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return flag;
    }

    /**
     *
     * @param enquiryId
     * @param status
     * @return
     */
    public static boolean setCompletionFlag(String enquiryId, String status) {
//if(counselorDetailsBEAN.gete)
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        boolean completion = false;
        con = DBPool.getInstance().getConn();
        String sql = "";
        if (status.equals("Register Now")) {

            sql = "UPDATE " + TableNames.TABLE_ENQ_DETAILS + "\n"
                    + "SET completion_flag = 1\n"
                    + "WHERE\n"
                    + "enquiry_id = '" + enquiryId + "'";
        } else {
            sql = "UPDATE " + TableNames.TABLE_ENQ_DETAILS + "\n"
                    + "SET completion_flag = 0\n"
                    + "WHERE\n"
                    + "enquiry_id = '" + enquiryId + "'";
        }
        try {
            stmt = con.createStatement();
            completion = stmt.execute(sql);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }

        return completion;

    }

    //Method fro delete static other test details
    /**
     *
     * @param assesmentOtherTestBEAN
     * @param enquiryId
     */
    public static void deleteOtherTest(AssesmentOtherTestBEAN assesmentOtherTestBEAN, String enquiryId) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        String query = "delete from " + TableNames.TABLE_ENQ_ASMNT_OTHER_TEST + " where enquiry_id=? and test_name=? and test_score=? and test_status=?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, enquiryId);
            ps.setString(2, assesmentOtherTestBEAN.getTestName());
            ps.setString(3, assesmentOtherTestBEAN.getTestScore());
            ps.setString(4, assesmentOtherTestBEAN.getTestStatus());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
    }

    //Method for delete static work exp details
    /**
     *
     * @param assessmentExperienceBEAN
     * @param enquiryId
     */
    public static void deleteWorkExpDetails(WorktExperienceBEAN assessmentExperienceBEAN, String enquiryId) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        String query = "delete from " + TableNames.TABLE_ENQ_ASMNT_WORK_EXP + " where enquiry_id=? and profession=? and duration=?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, enquiryId);
            ps.setString(2, assessmentExperienceBEAN.getProfession());
            ps.setString(3, assessmentExperienceBEAN.getDuration());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
//        deleteOtherTest();
    }

    //Method for insert Trining details
    /**
     *
     * @param enquiryId
     * @param assesmentOtherTestBEANs
     */
    public static void insertOtherTestDetails(String enquiryId, List<AssesmentOtherTestBEAN> assesmentOtherTestBEANs) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int i = 0;
        con = DBPool.getInstance().getConn();
        String GENERATE_ENQ_ID = Context.getInstance().currentProfile().getProfilePOJO().getBranch().substring(0, 3).toLowerCase();
        try {
            for (AssesmentOtherTestBEAN assesmentOtherTestBEAN : assesmentOtherTestBEANs) {
                GENERATE_ENQ_ID = GENERATE_ENQ_ID + UiiDGenerator.getUIID8();
                String sql = "INSERT INTO  " + TableNames.TABLE_ENQ_ASMNT_OTHER_TEST + "  (\n"
                        + "	 id ,\n"
                        + "	 enquiry_id ,\n"
                        + "	 test_name ,\n"
                        + "	 test_score ,\n"
                        + "	 test_status ,\n"
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
                        + "		?,\n"
                        + "		NOW(),\n"
                        + "		NULL,\n"
                        + "		NULL,\n"
                        + "		'y'\n"
                        + "	);";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, GENERATE_ENQ_ID);
                ps.setString(2, enquiryId);
                ps.setString(3, assesmentOtherTestBEAN.getTestName());
                ps.setString(4, assesmentOtherTestBEAN.getTestScore());
                ps.setString(5, assesmentOtherTestBEAN.getTestStatus());
                ps.setString(6, Context.getInstance().currentProfile().getProfilePOJO().getUsername());
                if (ps.executeUpdate() > 0) {
                    assesmentOtherTestBEAN.setRowId(GENERATE_ENQ_ID);
                    GlobalClassDAO.updateLatestFlags(enquiryId, TableNames.TABLE_ENQ_ASMNT_OTHER_TEST, "n", " id NOT IN('" + GENERATE_ENQ_ID + "')");
                }
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
     * @return
     */
    //Method for update assesment status details
    /**
     *
     * @param enquiryId
     * @param assementStatusPojo
     */
    /**
     *
     * @param enquiryId
     * @param enquiryAssementStatusPojo
     */
    //Method to set Completion flag in enquiry details
    /**
     *
     * @param enquiryId
     */
    public static void setCompletionFlag(String enquiryId) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        String query = "Update " + TableNames.TABLE_ENQ_DETAILS + " set completion_flag=? where enquiry_id=?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, 1);
            ps.setString(2, enquiryId);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
    }

}
