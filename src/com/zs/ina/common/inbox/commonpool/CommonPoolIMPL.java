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
package com.zs.ina.common.inbox.commonpool;

import com.zs.ina.common.constants.TableNames;
import com.zs.ina.counselor.dao.model.CounselorDetailsBEAN;
import com.zs.ina.utility.ClosingResourcesInDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import zs.com.ina.db.mysql.pool.DBPool;

/**
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class CommonPoolIMPL implements CommonPoolDAO {

    static Logger logger = Logger.getLogger(CommonPoolIMPL.class);

    /**
     *
     * @param username
     * @param branch
     * @param role
     * @param lockDetailsBEAN
     * @return
     */
    @Override
    public int lockEnquiry(String username, String branch, String role, CounselorDetailsBEAN lockDetailsBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();

        try {
            String lockEnqDetails = "UPDATE " + TableNames.TABLE_ENQ_DETAILS + "\n"
                    + "SET branch = '" + branch + "',\n"
                    + " enquiry_assigning = '" + username + "' ,important_flag=0 \n"
                    + "WHERE\n"
                    + "	enquiry_id = '" + lockDetailsBEAN.getEnquiryID() + "'";
            String lockEnqStatus = "UPDATE " + TableNames.TABLE_ENQ_ASMNT_STATUS + "\n"
                    + "SET \n"
                    + " branch = '" + branch + "',\n"
                    + " counsellor = '" + username + "',\n"
                    + " updated_date = NOW(),\n"
                    + " updated_user = '" + username + "'\n"
                    + "WHERE\n"
                    + "	enquiry_id = '" + lockDetailsBEAN.getEnquiryID() + "'";
            stmt = con.createStatement();
            stmt.addBatch(lockEnqDetails);
            stmt.addBatch(lockEnqStatus);
            row = stmt.executeBatch()[0];
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error(sqle.toString());
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
    public int checkFlagLimit(String username, String branch, String role) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param username
     * @param branch
     * @param role
     * @return
     */
    @Override
    public Map<String, String> retrieveCommonPoolConfig(String username, String branch, String role) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        Map<String, String> mapCommonPoolConfig = new HashMap<>();
        try {
            con = DBPool.getInstance().getConn();
            String queryPool = "SELECT * FROM " + TableNames.TABLE_MASTER_COMMON_POOL_CONFIG + " WHERE user_name ='" + username + "'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(queryPool);
            if (rs.next()) {
                mapCommonPoolConfig.put("username", rs.getString("user_name"));
                mapCommonPoolConfig.put("flag_limit", rs.getString("flag_limit"));
                mapCommonPoolConfig.put("enquiry_limit", rs.getString("enquiry_limit"));
                mapCommonPoolConfig.put("enquiry_duration", rs.getString("enquiry_duration"));
            } else {
                mapCommonPoolConfig.put("username", "0");
                mapCommonPoolConfig.put("flag_limit", "0");
                mapCommonPoolConfig.put("enquiry_limit", "0");
                mapCommonPoolConfig.put("enquiry_duration", "0");
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error(sqle.toString());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return mapCommonPoolConfig;
    }

    /**
     *
     * @param username
     * @param branch
     * @param role
     * @return
     */
    @Override
    public Map<String, String> checkFlagEnquiryStatus(String username, String branch, String role) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        Map<String, String> mapFlagEnquiryStatus = new HashMap<>();
        try {
            con = DBPool.getInstance().getConn();
            String queryPool = "SELECT \n"
                    + "(SELECT count(*)  from " + TableNames.TABLE_ENQ_DETAILS + " WHERE important_flag =1 AND enquiry_assigning='" + username + "') as flag_limit,\n"
                    + "(SELECT count(*) FROM " + TableNames.TABLE_ENQ_DETAILS + " WHERE enquiry_assigning='" + username + "' AND ( completion_flag is null || completion_flag=0 ) ) as enquiry_limit";
            stmt = con.createStatement();

            rs = stmt.executeQuery(queryPool);
            if (rs.next()) {
                mapFlagEnquiryStatus.put("flag_limit", rs.getString("flag_limit"));
                mapFlagEnquiryStatus.put("enquiry_limit", rs.getString("enquiry_limit"));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error(sqle.toString());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return mapFlagEnquiryStatus;
    }

    /**
     *
     * @param username
     * @param branch
     * @param enquiryId
     * @return
     */
    @Override
    public boolean verifyEnquiryLocking(String username, String branch, String enquiryId) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        boolean flag = false;
        try {
            con = DBPool.getInstance().getConn();
            String queryPool = "SELECT\n"
                    + "	*\n"
                    + "FROM\n"
                    + "	enquiry_details\n"
                    + "WHERE\n"
                    + "	enquiry_id = '" + enquiryId + "'\n"
                    + "AND enquiry_assigning IN ('Not Assigned', '', NULL)";
            stmt = con.createStatement();
            rs = stmt.executeQuery(queryPool);
            if (rs.next()) {
                flag = true;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error(sqle.toString());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return flag;
    }

    /**
     *
     * @param username
     * @param branch
     * @return
     */
    @Override
    public boolean updateExpiredToNotAssigned(String username, String branch) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        boolean flag = false;
        try {
            con = DBPool.getInstance().getConn();
            String queryPool = "UPDATE ENQUIRY_EXPIRY_VW vw,\n"
                    + " enquiry_details enq\n"
                    + "SET enq.enquiry_assigning = 'Not Assigned' , important_flag=0\n"
                    + "WHERE\n"
                    + "	vw.assigned_to = '" + username + "'\n"
                    + "AND vw.enquiry_id = enq.enquiry_id\n"
                    + "AND vw.assigned_to = enq.enquiry_assigning\n"
                    + "AND now() >= end_date;";

            String queryPool2 = "UPDATE enquiry_details vw,\n"
                    + " enquiry_assesment_status_tbl enq\n"
                    + "SET enq.counsellor = 'Not Assigned'\n"
                    + "WHERE\n"
                    + "	vw.enquiry_assigning = 'Not Assigned'\n"
                    + "AND vw.enquiry_id = enq.enquiry_id;\n";

            System.out.println("TEST ASSMENT STATUS UPDATE :: " + queryPool2);
            System.out.println("TEST ASSMENT STATUS UPDATE 1 :: " + queryPool);
            stmt = con.createStatement();
            stmt.addBatch(queryPool);
            stmt.addBatch(queryPool2);
            row = stmt.executeBatch()[0];
            if (row > 0) {
                flag = true;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error(sqle.toString());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return flag;
    }

    /**
     *
     * @param username
     * @param branch
     * @return
     */
    @Override
    public boolean updateExpiredToHistoryNotAssigned(String username, String branch) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        boolean flag = false;
        try {
            con = DBPool.getInstance().getConn();
            String queryPool = "UPDATE\n"
                    + "     enquiry_details vw,\n"
                    + "		 history_enquiry_assigning_tbl enq\n"
                    + "SET enq.assigned_to = 'Not Assigned'\n"
                    + "where vw.enquiry_assigning = 'Not Assigned'\n"
                    + "  and vw.enquiry_id = enq.enquiry_id\n"
                    + "  and vw.enquiry_assigning <> enq.assigned_to\n"
                    + "  and enq.latest_flag = 'y'";
            stmt = con.createStatement();
            row = stmt.executeUpdate(queryPool);
            if (row > 0) {
                flag = true;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error(sqle.toString());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return flag;
    }

    /**
     *
     * @param username
     * @param branch
     * @param role
     * @param enquiryId
     * @return
     */
    @Override
    public boolean checkExpiredEnquiry(String username, String branch, String role, String enquiryId) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        boolean flag = false;
        try {
            con = DBPool.getInstance().getConn();
            String queryPool = "SELECT\n"
                    + "	*\n"
                    + "FROM\n"
                    + "	enquiry_details\n"
                    + "WHERE\n"
                    + "	enquiry_assigning = '" + username + "'\n"
                    + "AND enquiry_id = '" + enquiryId + "'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(queryPool);
            if (rs.next()) {
                flag = true;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error(sqle.toString());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return flag;
    }

}
