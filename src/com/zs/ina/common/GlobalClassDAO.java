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
package com.zs.ina.common;

import com.zs.ina.utility.ClosingResourcesInDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.log4j.Logger;
import zs.com.ina.db.mysql.pool.DBPool;

/**
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class GlobalClassDAO {

    static Logger logger = Logger.getLogger(GlobalClassDAO.class);

    /**
     *
     * @param enquiryId
     * @param tableName
     * @return
     */
    public static boolean checkCurrentEnquiryIdHaveLatestFlag(String enquiryId, String tableName) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        try {

            con = DBPool.getInstance().getConn();
            String query = " SELECT\n"
                    + "	*\n"
                    + "FROM\n"
                    + "	" + tableName + "\n"
                    + " WHERE\n"
                    + "	latest_flag = 'y'\n"
                    + " AND enquiry_id = '" + enquiryId + "'";
            System.out.println("Updating All flags After Delete A Record :: " + query);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            if (rs.next()) {
                return true;
            }

        } catch (SQLException ex) {
            logger.info(ex.toString());
            ex.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return false;

    }

    /**
     *
     * @param rowId
     * @param tableName
     * @param columnName
     * @return
     */
    public static String checkCurrentEnquiryIdHaveLatestFlag(String rowId, String tableName, String columnName) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        try {
            /* ======================== Check Current RowId having latest flag 'y' ==================== */
            con = DBPool.getInstance().getConn();
            String query = " SELECT\n"
                    + "	*\n"
                    + "FROM\n"
                    + "	" + tableName + "\n"
                    + " WHERE\n"
                    + "	latest_flag = 'y'\n"
                    + " AND " + columnName + " = '" + rowId + "'";
            System.out.println("Check Current RowID having latest flag 'y' :: " + query);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            if (rs.next()) {
                return rs.getString("enquiry_id");
            }

        } catch (SQLException ex) {
            logger.info(ex.toString());
            ex.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return null;

    }

    /**
     *
     * @param enquiryId
     * @param tableName
     * @return
     */
    public static int updateLatestflagOnDelete(String enquiryId, String tableName) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        try {
            con = DBPool.getInstance().getConn();
            String query = "UPDATE\n"
                    + "" + tableName + " tb,\n"
                    + "(select max(tb2.updated_date) updated_date, max(tb2.created_date) created_date, tb2.enquiry_id\n"
                    + "from " + tableName + " tb2\n"
                    + "where tb2.enquiry_id = '" + enquiryId + "'\n"
                    + "  and tb2.latest_flag <> 'y'\n"
                    + "group by tb2.enquiry_id limit 1) as tb3\n"
                    + "SET tb.latest_flag = 'y'\n"
                    + "where tb.enquiry_id = tb3.enquiry_id\n"
                    + "  and ifnull(tb.updated_date,tb.created_date) = IfNULL(tb3.updated_date,tb3.created_date)";
            System.out.println("Updating All flags After Delete A Record :: " + query);
            stmt = con.createStatement();
            row = stmt.executeUpdate(query);
            return 0;
        } catch (SQLException ex) {
            logger.info(ex.toString());
            ex.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return row;

    }

    /**
     *
     * @param enquiryId
     * @param tableName
     * @param flag
     * @param queryLatest
     * @return
     */
    public static int updateLatestFlags(String enquiryId, String tableName, String flag, String queryLatest) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        try {

            con = DBPool.getInstance().getConn();
            String query = "UPDATE " + tableName + "\n"
                    + "SET latest_flag = '" + flag + "'\n"
                    + "WHERE\n"
                    + queryLatest + "\n"
                    + "AND enquiry_id = '" + enquiryId + "'";
            System.out.println("Updating All flags :: " + query);
            stmt = con.createStatement();
            row = stmt.executeUpdate(query);
            return 0;
        } catch (SQLException ex) {
            logger.info(ex.toString());
            ex.printStackTrace();
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
        System.out.println("test " + checkCurrentEnquiryIdHaveLatestFlag("st_01c7c2de625a", "enquiry_assessment_study_tbl", "study_pgm_id"));
    }
}
