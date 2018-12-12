/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.login.dao;

import com.zs.ina.utility.ClosingResourcesInDB;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import zs.com.ina.db.mysql.pool.DBPool;

/**
 *
 * @author user
 */
public class LoginFormDAO {

    /**
     *
     */
    public LoginFormDAO() {
    }

    /**
     *
     * @return
     */
    public static String getIpAddress() {
        String hostIP = null;
        try {
            InetAddress address = InetAddress.getLocalHost();
            hostIP = address.getHostAddress();
            String hostName = address.getHostName();
        } catch (UnknownHostException unknownHostException) {
            unknownHostException.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(null, new Statement[]{null}, new ResultSet[]{null}, stackTraceElements);
        }
        return hostIP;
    }

//    public static int login(ProfilePOJO user) {
//        int loginStatus = 0;
//        int status = 0;
//        int flag = 0;
//        Connection con = null;
//        Statement stmt = null;
//        ResultSet rs = null;
//        con = DBPool.getInstance().getConn();
//        try {
//            String query = null;
//            query = "select loginCheck('" + user.getUsername() + "','" + user.getPassword() + "')";
//            stmt = con.createStatement();
//            rs = stmt.executeQuery(query);
//            while (rs.next()) {
//                status = Integer.parseInt(rs.getString(1));
//                if (status == 1) {
//                    String query1 = "select flag from login_tbl where user_name='" + user.getUsername() + "' and password='" + user.getPassword() + "'";
//                    stmt = con.createStatement();
//                    rs = stmt.executeQuery(query1);
//                    rs.next();
//
//                    flag = rs.getInt("flag");
//                }
//            }
//            if (status == 1 && flag == 0) {
//                loginStatus = 3;
//            } else if (status == 1 && flag == 1) {
//                loginStatus = 1;
//            } else {
//                loginStatus = 2;
//            }
//
//        } catch (SQLException sqle) {
//            sqle.printStackTrace();
//            loginStatus = 3;
//        } finally {
//            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
//            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
//        }
//        return loginStatus;
//    }
    /**
     *
     * @param user
     * @return
     */
    public static int login(ProfilePOJO user) {
        int loginStatus = 0;
        int status = 0;
        int flag = 0;
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        try {
            status = loginCheck(user.getUsername(), user.getPassword());
            if (status == 1) {
                String query1 = "select flag from login_tbl where user_name='" + user.getUsername().trim() + "' and password='" + user.getPassword().trim() + "'";
                stmt = con.createStatement();
                rs = stmt.executeQuery(query1);
                rs.next();

                flag = rs.getInt("flag");
            }
            if (status == 1 && flag == 0) {
                loginStatus = 3;
            } else if (status == 1 && flag == 1) {
                loginStatus = 1;
            } else {
                loginStatus = 2;
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            loginStatus = 3;
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return loginStatus;
    }
    //Method for login check

    /**
     *
     * @param uname
     * @param pword
     * @return
     */
    public static int loginCheck(String uname, String pword) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int loginFlag = 0;
        con = DBPool.getInstance().getConn();

        String query = "select exists(select login_id from login_tbl where user_name= BINARY '" + uname + "' and password= BINARY '" + pword + "')";
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            rs.next();
            if (rs.getBoolean(1)) {
                loginFlag = 1;
            } else {
                loginFlag = 2;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return loginFlag;
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        loginCheck("a", "a");
    }

    /**
     *
     * @param user
     */
    public static void insertLoginDetails(ProfilePOJO user) {

        if (checkLoginAuditor(user.getUsername())) {
            updateLoginDetails(user);

        } else {

            String ipAddress = getIpAddress();
            Connection con = null;
            con = DBPool.getInstance().getConn();
            try {
                String query = "INSERT INTO login_auditor (\n"
                        + "	user_id,\n"
                        + "	ipaddress,\n"
                        + "	attempt_date,\n"
                        + "	last_attempt_time,\n"
                        + "	attempt\n"
                        + ")\n"
                        + "VALUES\n"
                        + "	((\n"
                        + "		SELECT\n"
                        + "			login_id\n"
                        + "		FROM\n"
                        + "			login_tbl\n"
                        + "		WHERE\n"
                        + "			user_name = '" + user.getUsername() + "'\n"
                        + "	),?, curdate(), curtime() ,?)";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, ipAddress);
                ps.setInt(2, 1);
                int row = ps.executeUpdate();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } finally {
                StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
                ClosingResourcesInDB.closingAllResources(con, new Statement[]{null}, new ResultSet[]{null}, stackTraceElements);
            }

        }
    }

    /**
     *
     * @param username
     * @return
     */
    public static int deleteLoginAudit(String username) {

        Connection con = null;
        con = DBPool.getInstance().getConn();
        int row = 0;
        ResultSet rs;
        try {
            String query = "DELETE\n"
                    + "FROM\n"
                    + "	login_auditor\n"
                    + "WHERE\n"
                    + "	user_id = (\n"
                    + "		SELECT\n"
                    + "			login_id\n"
                    + "		FROM\n"
                    + "			login_tbl\n"
                    + "		WHERE\n"
                    + "			user_name = '" + username + "'\n"
                    + "	);";
            PreparedStatement ps = con.prepareStatement(query);
            row = ps.executeUpdate();

        } catch (SQLException sqle) {

            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{null}, new ResultSet[]{null}, stackTraceElements);
        }
        return row;

    }

    /**
     *
     * @param userName
     * @return
     */
    public static Boolean checkLoginAuditor(String userName) {

        Connection con = null;
        con = DBPool.getInstance().getConn();
        Boolean row = false;
        ResultSet rs;
        try {
            String query = "SELECT\n"
                    + "	*\n"
                    + "FROM\n"
                    + "	login_auditor\n"
                    + "WHERE\n"
                    + "	user_id =(SELECT login_id from login_tbl WHERE user_name='" + userName + "');";
            PreparedStatement ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("user exists");
                return true;

            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{null}, new ResultSet[]{null}, stackTraceElements);
        }
        return row;
    }

    /**
     *
     * @param user
     */
    public static void updateLoginDetails(ProfilePOJO user) {
        Connection con = null;
        con = DBPool.getInstance().getConn();
        try {
            String query = "UPDATE login_auditor\n"
                    + "SET attempt = attempt + 1,\n"
                    + " last_attempt_time = (SELECT curtime())\n"
                    + ",attempt_date=(SELECT CURDATE())\n"
                    + "WHERE\n"
                    + "	user_id= (SELECT\n"
                    + "			login_id\n"
                    + "		FROM\n"
                    + "			login_tbl\n"
                    + "		WHERE\n"
                    + "			user_name = '" + user.getUsername() + "')";
            PreparedStatement ps = con.prepareStatement(query);
            ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{null}, new ResultSet[]{null}, stackTraceElements);
        }
    }

//    public static void main(String[] args) {
//        System.out.println("" + checkLoginAuditor("b"));
//    }
}
