/*
 * Copyright ZoftSolutions(C) 2016 100018
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
package com.zs.ina.admin.master.counselorlimit.dao;

import com.zs.ina.admin.master.retrieve.CounselorsListPOJO;
import com.zs.ina.admin.master.retrieve.RetrieveStaticMasterDAO;
import com.zs.ina.common.UiiDGenerator;
import com.zs.ina.common.constants.TableNames;
import com.zs.ina.enquiry.dao.EnquiryDetailsDAO;
import com.zs.ina.utility.ClosingResourcesInDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.collections.ObservableList;
import zs.com.ina.db.mysql.pool.DBPool;

/**
 *
 * @author 100018
 */
public class CounselorLimitDAO {

    private static final String TABLE_COUNSELOR_TIMELIMIT = "master_counselore_timelimit";

    /**
     *
     * @return
     */
    public static Map<String, String> retrieveEnquiryHoldTime() {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        Map<String, String> map = new HashMap<>();
        try {
            String workQuery = "SELECT * FROM " + TABLE_COUNSELOR_TIMELIMIT;
            System.out.println("counselor limit :: >> " + workQuery);
            stmt = con.createStatement();

            rs = stmt.executeQuery(workQuery);

            while (rs.next()) {
                map.put("time", rs.getString("time_limit"));
                map.put("uinit", rs.getString("unit"));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return map;

    }

    /**
     *
     * @param username
     * @return
     */
    public static Map<String, String> checkCounselorEnquiryLimit(String username) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        Map<String, String> map = new HashMap<>();
        try {
            String workQuery = "SELECT\n"
                    + "	count(*) as enquiry_curr,\n"
                    + " IFNULL(ml.enquiry_limit,0) as enquiry_limit	\n"
                    + "FROM\n"
                    + "	" + TableNames.TABLE_ENQ_DETAILS + " ed, \n"
                    + "	master_counselor_limit ml\n"
                    + "WHERE\n"
                    + "	ed.enquiry_assigning = '" + username + "'\n"
                    + "AND ml.login_id = (\n"
                    + "	SELECT\n"
                    + "		login_id\n"
                    + "	FROM\n"
                    + "		login_tbl\n"
                    + "	WHERE\n"
                    + "		user_name = '" + username + "'\n"
                    + ")";
            //  System.out.println("counselor limit :: >> " + workQuery);
            stmt = con.createStatement();

            rs = stmt.executeQuery(workQuery);

            while (rs.next()) {
                map.put("enquiry_curr", rs.getString("enquiry_curr"));
                map.put("enquiry_limit", rs.getString("enquiry_limit"));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return map;

    }

    /**
     *
     * @param holdTime
     * @param unit
     * @return
     */
    public static int[] setEnquiryHoldTime(String holdTime, String unit) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row[] = null;
        con = DBPool.getInstance().getConn();
        try {
            String timeDelQuery = "DELETE  FROM " + TABLE_COUNSELOR_TIMELIMIT;
            String timeQuery = "INSERT INTO " + TABLE_COUNSELOR_TIMELIMIT + " (id, time_limit,unit) VALUES ('time_1234', '" + holdTime + "','" + unit + "');";
            stmt = con.createStatement();
            stmt.addBatch(timeDelQuery);
            stmt.addBatch(timeQuery);
            System.out.println("test Insert :: " + timeQuery);
            row = stmt.executeBatch();

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
     * @param userName
     * @return
     */
    public static CounselorLimitPOJO getLimitById(String userName) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        CounselorLimitPOJO limitPOJO = new CounselorLimitPOJO();
        try {
            String workQuery = "SELECT\n"
                    + "	cl.id,cl.login_id,\n"
                    + "	ld.email,\n"
                    + "	(\n"
                    + "		SELECT\n"
                    + "			branch_name\n"
                    + "		FROM\n"
                    + "			branch_tbl bt\n"
                    + "		WHERE\n"
                    + "			bt.branch_id = ld.branch_id\n"
                    + "	) AS branch,\n"
                    + "	concat(\n"
                    + "		ld.first_name,\n"
                    + "		\" \",\n"
                    + "		ld.last_name\n"
                    + "	) AS counselor,cl.enquiry_limit\n"
                    + "FROM\n"
                    + "	master_counselor_limit cl,\n"
                    + "	login_details_tbl ld\n"
                    + "WHERE\n"
                    + "	cl.login_id = ld.login_id AND ld.login_id = (select login_id from login_tbl where user_name = '" + userName + "' )";
            System.out.println("counselor limit :: >> " + workQuery);
            stmt = con.createStatement();

            rs = stmt.executeQuery(workQuery);

            while (rs.next()) {
                limitPOJO.setId(rs.getString("id"));
                limitPOJO.setEnquiry_limit(rs.getString("enquiry_limit"));
                limitPOJO.setLogin_id(rs.getString("login_id"));
                limitPOJO.setBranch(rs.getString("branch"));
                limitPOJO.setCounselorName(rs.getString("counselor"));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return limitPOJO;

    }

    /**
     *
     * @return
     */
    public static List<CounselorLimitPOJO> getAllData() {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<CounselorLimitPOJO> counselorLimitList = new ArrayList<>();
        try {
            String workQuery = "SELECT\n"
                    + "	cl.id,cl.login_id,\n"
                    + "	ld.email,\n"
                    + "	(\n"
                    + "		SELECT\n"
                    + "			branch_name\n"
                    + "		FROM\n"
                    + "			branch_tbl bt\n"
                    + "		WHERE\n"
                    + "			bt.branch_id = ld.branch_id\n"
                    + "	) AS branch,\n"
                    + "	concat(\n"
                    + "		ld.first_name,\n"
                    + "		\" \",\n"
                    + "		ld.last_name\n"
                    + "	) AS counselor,cl.enquiry_limit\n"
                    + "FROM\n"
                    + "	master_counselor_limit cl,\n"
                    + "	login_details_tbl ld\n"
                    + "WHERE\n"
                    + "	cl.login_id = ld.login_id";
            System.out.println("counselor limit :: >> " + workQuery);
            stmt = con.createStatement();

            rs = stmt.executeQuery(workQuery);

            while (rs.next()) {
                CounselorLimitPOJO limitPOJO = new CounselorLimitPOJO();
                limitPOJO.setId(rs.getString("id"));
                limitPOJO.setEnquiry_limit(rs.getString("enquiry_limit"));
                limitPOJO.setLogin_id(rs.getString("login_id"));
                limitPOJO.setBranch(rs.getString("branch"));
                limitPOJO.setCounselorName(rs.getString("counselor"));
                counselorLimitList.add(limitPOJO);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return counselorLimitList;

    }

    /**
     *
     * @param limitPOJO
     * @return
     */
    public static int updateCounselorLimit(CounselorLimitPOJO limitPOJO) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 5;
        con = DBPool.getInstance().getConn();
        try {
            String workQuery = "UPDATE master_counselor_limit SET  enquiry_limit='" + limitPOJO.getEnquiry_limit() + "' WHERE id='" + limitPOJO.getId() + "';";
            stmt = con.createStatement();

            row = stmt.executeUpdate(workQuery);

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
     * @param limitPOJO
     * @return
     */
    public static int deleteCounselorLimit(CounselorLimitPOJO limitPOJO) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 5;
        con = DBPool.getInstance().getConn();
        try {
            String workQuery = "DELETE FROM master_counselor_limit WHERE id='" + limitPOJO.getId() + "'";
            stmt = con.createStatement();

            row = stmt.executeUpdate(workQuery);

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
     * @param limitPOJO
     * @return
     */
    public static int insertCounselorLimit(CounselorLimitPOJO limitPOJO) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 5;
        con = DBPool.getInstance().getConn();
        try {
            String workQuery = "INSERT INTO master_counselor_limit (id, login_id, enquiry_limit) VALUES ('lm_" + UiiDGenerator.getUIID8() + "', (SELECT login_id FROM login_tbl WHERE user_name = '" + limitPOJO.getCounselorName() + " ' ), '" + limitPOJO.getEnquiry_limit() + "');";
            stmt = con.createStatement();
            System.out.println("test Insert :: " + workQuery);
            row = stmt.executeUpdate(workQuery);

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
     * @param args
     */
    public static void main(String[] args) {
        //  System.out.println("test " + getAllData());
        ObservableList<CounselorsListPOJO> counselors = RetrieveStaticMasterDAO.getAllCounselors("ernakulam");
        System.out.println("cou :: " + counselors.toString());
        for (CounselorsListPOJO eachCounselor : counselors) {
            System.out.println(" Test :: " + eachCounselor + checkCounselorEnquiryLimit(eachCounselor.getUsername()));
        }
    }

}
