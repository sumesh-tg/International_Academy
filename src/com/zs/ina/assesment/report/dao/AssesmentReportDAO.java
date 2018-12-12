/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.assesment.report.dao;

import com.zs.ina.common.constants.TableNames;
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
 * @author user
 */
public class AssesmentReportDAO {

    //Get assesd enquiry based on time periods
    /**
     *
     * @param period
     * @param branch
     * @param counselorName
     * @return
     */
    public static List<Count> getAssesmentEnquiry(int period, String branch, String counselorName) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String query = "";
        con = DBPool.getInstance().getConn();
        List<Count> countList = new ArrayList<>();
        if (branch.equals("All Branch")) {
            query = "Select count(*),branch from " + TableNames.TABLE_ENQ_DETAILS + " where read_flag=1 and edate between curdate()-" + period + " and curdate() group by branch";
        } else if (!branch.equals("All Branch") && counselorName.equals("")) {
            query = "Select count(*),branch from " + TableNames.TABLE_ENQ_DETAILS + " where read_flag=1 and edate between curdate()-" + period + " and curdate() and branch='" + branch + "' group by branch";
        } else if (!counselorName.equals("")) {
            query = "Select count(*),branch from " + TableNames.TABLE_ENQ_DETAILS + " where read_flag=1 and edate between curdate()-" + period + " "
                    + "and curdate() and branch='" + branch + "' and enquiry_assigning='" + counselorName + "' group by branch";
        }
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                Count counter = new Count();
                counter.setBranch(rs.getString(2));
                counter.setCount(rs.getInt(1));
                countList.add(counter);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return countList;
    }

    //Method to get not assesd enquiry based on time periods
    /**
     *
     * @param period
     * @param branch
     * @param counselorName
     * @return
     */
    public static List<Count> getNotAssesdEnquiry(int period, String branch, String counselorName) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String query = "";
        con = DBPool.getInstance().getConn();
        List<Count> countList = new ArrayList<>();
        if (branch.equals("All Branch")) {
            query = "Select count(*),branch from " + TableNames.TABLE_ENQ_DETAILS + " where read_flag=0 and edate between curdate()-" + period + " and curdate() group by branch";
        } else if (!branch.equals("All Branch") && counselorName.equals("")) {
            System.out.println("branch==>" + branch);
            query = "Select count(*),branch from " + TableNames.TABLE_ENQ_DETAILS + " where read_flag=0 and edate between curdate()-" + period + " and curdate() and branch='" + branch + "' group by branch";
        } else if (!counselorName.equals("")) {
            query = "Select count(*),branch from " + TableNames.TABLE_ENQ_DETAILS + " where read_flag=0 and edate between curdate()-" + period + " "
                    + "and curdate() and branch='" + branch + "' and enquiry_assigning='" + counselorName + "' group by branch";
        }
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                Count counter = new Count();
                counter.setBranch(rs.getString(2));
                counter.setCount(rs.getInt(1));
                countList.add(counter);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);

        }
        return countList;
    }

    //Method to get all counsellors based on branch
    /**
     *
     * @param branch
     * @return
     */
    public static List<String> getCounselors(String branch) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String query = "";
        con = DBPool.getInstance().getConn();
        List<String> counselorList = new ArrayList<>();
        query = "select user_name from login_tbl l,branch_tbl b,login_details_tbl ld"
                + " where l.role_id=2 and l.login_id=ld.login_id and "
                + "ld.branch_id=b.branch_id and ld.branch_id=(select branch_id from branch_tbl where branch_name='" + branch + "')";
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                counselorList.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);

        }
        return counselorList;
    }

    //Inner class for get branch and count
    /**
     *
     */
    public static class Count {

        String branch;
        int count;

        /**
         *
         * @return
         */
        public String getBranch() {
            return branch;
        }

        /**
         *
         * @param branch
         */
        public void setBranch(String branch) {
            this.branch = branch;
        }

        /**
         *
         * @return
         */
        public int getCount() {
            return count;
        }

        /**
         *
         * @param count
         */
        public void setCount(int count) {
            this.count = count;
        }

    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        getCounselors("ernakulam");
    }
}
