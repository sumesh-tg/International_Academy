/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.admin.scheduling;

import com.zs.ina.common.UiiDGenerator;
import com.zs.ina.utility.ClosingResourcesInDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import zs.com.ina.db.mysql.pool.DBPool;

/**
 *
 * @author user
 */
public class SchedulingDAO {

    //Method for get all branches

    /**
     *
     * @return
     */
    public static List<String> getAllBranches() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<String> allBranches = new ArrayList<>();
        try {
            String query = "select branch_name from branch_tbl";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                allBranches.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return allBranches;
    }

    /**
     *
     */
    public static void schedulingOperations() {

        List<String> allBranches = getAllBranches();
        for (String s : allBranches) {
            List<String> allCounselors = getCounselors(s);
           // Collections.shuffle(allCounselors);
            for (String c : allCounselors) {
                int row = updateAssigning(c, s);
                if (row == 1) {
                    System.out.println("Updated Branch = " + s + " Counselor :: " + c);
                } else {
                    System.out.println("Not Updated Branch = " + s + " Counselor :: " + c);

                }
            }
        }
    }

    /**
     *
     * @param branch
     * @return
     */
    public static List<String> getCounselors(String branch) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<String> allCounselors = new ArrayList<>();
        try {
            String query = "select l.user_name,b.branch_name from login_tbl l,login_details_tbl ld,branch_tbl b "
                    + "where l.login_id=ld.login_id and l.role_id=2 and b.branch_id=ld.branch_id and b.branch_name='" + branch + "' ORDER BY RAND()";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                System.out.println("" + rs.getString(1));
                allCounselors.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return allCounselors;
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
                    + "	enquiry_details ed, \n"
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
            System.out.println("counselor limit :: >> " + workQuery);
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
     * @param args
     */
    public static void main(String[] args) {
        getCounselors("Kottayam");
    }

    /**
     *
     * @param username
     * @return
     */
    public static Map<String, String> checkExpiredEnquiries(String username) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        Map<String, String> mapExpiredEnquiry = new HashMap();
        String sql = "SELECT\n"
                + "	he.enquiry_id,\n"
                + "	DATEDIFF(NOW(), he.assigned_date) as diff\n"
                + "FROM\n"
                + "	history_enquiry_assigning_tbl he,enquiry_details ed \n"
                + "WHERE he.assigned_to=? AND ed.enquiry_assigning= he.assigned_to  AND ed.enquiry_id=he.enquiry_id AND\n"
                + "	DATEDIFF(NOW(), he.assigned_date) > (SELECT if((unit='days'),time_limit,1 ) from  master_counselore_timelimit)";
        System.out.println("Expire Key :: " + sql);
        try {
            con = DBPool.getInstance().getConn();

            stmt = con.createStatement();
            //   rs = stmt.executeQuery(sql);
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);

            rs = ps.executeQuery();
            while (rs.next()) {
                mapExpiredEnquiry.put(rs.getString("enquiry_id"), rs.getString("diff"));
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return mapExpiredEnquiry;
    }

    /**
     *
     * @param enquiryId
     */
    public static void makeNotAssigned(String enquiryId) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "UPDATE enquiry_details\n"
                + "SET enquiry_assigning = 'Not Assigned'\n"
                + "\n"
                + "WHERE\n"
                + "	enquiry_id = '" + enquiryId + "'";
        String sql2 = "UPDATE enquiry_assesment_status_tbl\n"
                + "SET counsellor = 'Not Assigned'\n"
                + "WHERE\n"
                + "	enquiry_id = '" + enquiryId + "'";
        try {
            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();
            stmt.addBatch(sql);
            stmt.addBatch(sql2);

            int row[] = stmt.executeBatch();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
    }

    /**
     *
     * @param counselor
     * @param branch
     * @return
     */
    public static int updateAssigning(String counselor, String branch) {
        /*========================== Make Expired enquiries of Counselor To 'Not Assigned' =============================== */
        Map<String, String> mapExpiredEnquiry = checkExpiredEnquiries(counselor);
        for (String enquiryId : mapExpiredEnquiry.keySet()) {
            System.out.println("Expired Enquiry id of " + counselor + "(" + branch + ")" + " :: >> " + enquiryId);
            makeNotAssigned(enquiryId);
        }
        /*========================== End Make Expired enquiries of Counselor 'Not Assigned' =============================== */
        System.out.println("Councelor  = " + counselor + "  Branch=" + branch);
        Connection con = null;
        Statement stmt = null, stmt3 = null;
        ResultSet rs = null;
        int row = 0;
        String query1 = "select enquiry_id from enquiry_details where enquiry_assigning='Not Assigned' and branch='" + branch + "' ORDER BY edate ASC";
        try {
            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();
            stmt3 = con.createStatement();
            rs = stmt.executeQuery(query1);
            if (rs.next()) {
                boolean _checkAreadyAssigned = true;
                for (String enquiryId : mapExpiredEnquiry.keySet()) {
                    if (enquiryId.equalsIgnoreCase(rs.getString(1))) {
                        _checkAreadyAssigned = false;
                        System.out.println("This Enquiry Already His Expired One ");
                    }
                }
                if (_checkAreadyAssigned) {
                    Map<String, String> map = checkCounselorEnquiryLimit(counselor);
                    System.out.println("Limits Of " + counselor + " CURRENT_ENQUIRIES :: " + map.get("enquiry_curr") + " ENQUIRY_LIMIT :: " + map.get("enquiry_limit"));
                    if (Integer.parseInt(map.get("enquiry_curr")) <= Integer.parseInt(map.get("enquiry_limit")) || Integer.parseInt(map.get("enquiry_limit")) == 0) {
                        String query = "update enquiry_details set enquiry_assigning='" + counselor + "' where enquiry_id='" + rs.getString(1) + "';";
                        String queryStatus = "UPDATE enquiry_assesment_status_tbl\n"
                                + "SET counsellor = 'Not Assigned'\n"
                                + "WHERE\n"
                                + "	enquiry_id = '" + rs.getString(1) + "';";
                        String queryHis = "INSERT INTO history_enquiry_assigning_tbl (\n"
                                + "	hid,\n"
                                + "	assigned_by,\n"
                                + "	assigned_to,\n"
                                + "	assigned_branch,\n"
                                + "	assigned_date,\n"
                                + "	enquiry_id,\n"
                                + "	holded_by\n"
                                + ")\n"
                                + "VALUES\n"
                                + "	(\n"
                                + "		'serv_" + UiiDGenerator.getUIID8() + "',\n"
                                + "		'server',\n"
                                + "		'" + counselor + "',\n"
                                + "		'" + branch + "',\n"
                                + "		'" + new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()).toString() + "',\n"
                                + "		'" + rs.getString(1) + "',\n"
                                + "		'Not Assigned'\n"
                                + "	);";
                        System.out.println("Enquiry Details Query :: " + query);
                        System.out.println("Enquiry Status Query :: " + queryStatus);
                        System.out.println("History Query :: " + queryHis);
                        stmt3.addBatch(query);
                        stmt3.addBatch(queryStatus);
                        stmt3.addBatch(queryHis);
                        int done[] = stmt3.executeBatch();
                    } else {
                        System.out.println("reached limit" + counselor);
                    }
                }

            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return row;
    }

}
