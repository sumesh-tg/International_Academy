/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.assesment.children.dao;

import com.zs.ina.assesment.model.AssessmentChildBEAN;
import com.zs.ina.common.GlobalClassDAO;
import com.zs.ina.common.UiiDGenerator;
import com.zs.ina.common.constants.TableNames;
import com.zs.ina.context.Context;
import com.zs.ina.utility.ClosingResourcesInDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import zs.com.ina.db.mysql.pool.DBPool;

/**
 *
 * @author zoft
 */
public class ChildrenDAO {

    /**
     *
     * @param enquiryId
     */
    public static void deleteChildDetails(String enquiryId) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        try {
            String query = "Delete from " + TableNames.TABLE_ENQ_ASMNT_CHILD + " where enquiry_id='" + enquiryId + "'";
            stmt = con.createStatement();
            stmt.executeUpdate(query);
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
    public static List<AssessmentChildBEAN> retriveChildrenDetails(String enquiryId) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<AssessmentChildBEAN> childBEANList = new ArrayList();
        try {
            con = DBPool.getInstance().getConn();
            String query = "Select * from " + TableNames.TABLE_ENQ_ASMNT_CHILD + " where enquiry_id='" + enquiryId + "'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                AssessmentChildBEAN acbean = new AssessmentChildBEAN();
                acbean.setRow_id(rs.getString("children_id"));
                acbean.setName(rs.getString("name"));
                acbean.setAge(rs.getString("age"));
                acbean.setCountry_relative(rs.getString("country_relative"));
                acbean.setEnquiry_id(rs.getString("enquiry_id"));
                acbean.setRelation(rs.getString("relation"));
                acbean.setSex(rs.getString("sex"));
                childBEANList.add(acbean);

            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return childBEANList;

    }

    /**
     *
     * @param assessmentChildBEAN
     * @return
     */
    public static boolean insertChildrenDetails(AssessmentChildBEAN assessmentChildBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        String GENERATE_ID = "ch_" + UiiDGenerator.getUIID8();

        try {
            String sql = "INSERT INTO  " + TableNames.TABLE_ENQ_ASMNT_CHILD + "  (\n"
                    + "	 children_id ,\n"
                    + "	 name ,\n"
                    + "	 age ,\n"
                    + "	 sex ,\n"
                    + "	 enquiry_id ,\n"
                    + "	 country_relative ,\n"
                    + "	 relation ,\n"
                    + "	 created_user ,\n"
                    + "	 created_date ,\n"
                    + "	 updated_user ,\n"
                    + "	 updated_date ,\n"
                    + "	 latest_flag \n"
                    + ")\n"
                    + "VALUES\n"
                    + "	(\n"
                    + "		'" + GENERATE_ID + "',\n"
                    + "		'" + assessmentChildBEAN.getName() + "',\n"
                    + "		'" + assessmentChildBEAN.getAge() + "',\n"
                    + "		'" + assessmentChildBEAN.getSex() + "',\n"
                    + "		'" + assessmentChildBEAN.getEnquiry_id() + "',\n"
                    + "		'" + assessmentChildBEAN.getCountry_relative() + "',\n"
                    + "		'" + assessmentChildBEAN.getRelation() + "',\n"
                    + "		'" + Context.getInstance().currentProfile().getProfilePOJO().getUsername() + "',\n"
                    + "		NOW(),\n"
                    + "		NULL,\n"
                    + "		NULL,\n"
                    + "		'y'\n"
                    + "	);";
            String query = "insert into " + TableNames.TABLE_ENQ_ASMNT_CHILD + " values ('" + GENERATE_ID + "','" + assessmentChildBEAN.getName() + "','" + assessmentChildBEAN.getAge() + "','" + assessmentChildBEAN.getSex() + "','" + assessmentChildBEAN.getEnquiry_id() + "','" + assessmentChildBEAN.getCountry_relative() + "','" + assessmentChildBEAN.getRelation() + "')";
            stmt = con.createStatement();
            row = stmt.executeUpdate(sql);
            if (row > 0) {
                assessmentChildBEAN.setRow_id(GENERATE_ID);
                GlobalClassDAO.updateLatestFlags(assessmentChildBEAN.getEnquiry_id(), TableNames.TABLE_ENQ_ASMNT_CHILD, "n", " children_id NOT IN('" + GENERATE_ID + "')");
                return true;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return false;
    }

    /**
     *
     * @param rowId
     * @return
     */
    public static boolean deleteChildrenDetails(String rowId) {
        String _isLatestFlag = GlobalClassDAO.checkCurrentEnquiryIdHaveLatestFlag(rowId, TableNames.TABLE_ENQ_ASMNT_CHILD, "children_id");
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        try {
            String query = "delete from " + TableNames.TABLE_ENQ_ASMNT_CHILD + " where children_id='" + rowId + "'";
            stmt = con.createStatement();
            row = stmt.executeUpdate(query);
            if (row == 1) {
                if (_isLatestFlag != null) {
                    GlobalClassDAO.updateLatestflagOnDelete(rowId, TableNames.TABLE_ENQ_ASMNT_CHILD);
                }
                return true;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return false;
    }

    /**
     *
     * @param updateChildBEAN
     * @return
     */
    public static boolean updateChildrenDetails(AssessmentChildBEAN updateChildBEAN) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        try {
            String sql = "UPDATE  " + TableNames.TABLE_ENQ_ASMNT_CHILD + " \n"
                    + "SET  \n"
                    + "  name  = '" + updateChildBEAN.getName() + "',\n"
                    + "  age  = '" + updateChildBEAN.getAge() + "',\n"
                    + "  sex  = '" + updateChildBEAN.getSex() + "',\n"
                    + "  country_relative  = '" + updateChildBEAN.getCountry_relative() + "',\n"
                    + "  relation  = '" + updateChildBEAN.getRelation() + "',\n"
                    + "  updated_user  = '" + Context.getInstance().currentProfile().getProfilePOJO().getUsername() + "',\n"
                    + "  updated_date  = NOW()\n"
                    + "WHERE\n"
                    + " children_id  = '" + updateChildBEAN.getRow_id() + "'";
            String query = "update " + TableNames.TABLE_ENQ_ASMNT_CHILD + "   set name='"
                    + updateChildBEAN.getName() + "',age='" + updateChildBEAN.getAge()
                    + "',sex='" + updateChildBEAN.getSex() + "',country_relative='"
                    + updateChildBEAN.getCountry_relative() + "',relation='" + updateChildBEAN.getRelation() + "' where children_id='" + updateChildBEAN.getRow_id() + "'";
            System.out.println("test :: " + query);
            stmt = con.createStatement();
            row = stmt.executeUpdate(sql);
            if (row > 0) {
                return true;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return false;

    }

}
