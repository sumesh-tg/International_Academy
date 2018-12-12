/*
 * Copyright (C) 2016 100035
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
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package com.zs.ina.admin.master.userlogin.dao;

import com.zs.ina.common.UiiDGenerator;
import com.zs.ina.common.email.E_MailSender;
import com.zs.ina.common.email.MailMail;
import com.zs.ina.common.email.MailSentPOJO;
import com.zs.ina.context.Context;
import com.zs.ina.notice.dao.BranchPOJO;
import com.zs.ina.utility.ClosingResourcesInDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import zs.com.ina.db.mysql.pool.DBPool;

/**
 *
 * @author 100035
 */
public class LoginIMPL implements LoginDAO {

    Logger logger = Logger.getLogger(LoginIMPL.class);
    ObservableList<LoginBEAN> retrieveLoginList = FXCollections.observableArrayList();

    /**
     *
     * @param loginBEAN
     * @return
     */
    @Override
    public int insertLogin(LoginBEAN loginBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row1 = 0, row2 = 0, row3 = 0;
        try {

            con = DBPool.getInstance().getConn();
            loginBEAN.setLoginId("LOG_" + UiiDGenerator.getUIID8());
            System.out.println("LOG_ID :" + loginBEAN.getLoginId());
            //   String password = UiiDGenerator.getUIID8();
            stmt = con.createStatement();
            String query_login_tbl = "INSERT INTO login_tbl (login_id,user_name,password,role_id)VALUES('" + loginBEAN.getLoginId() + "','" + loginBEAN.getUsername() + "','" + loginBEAN.getPassword() + "','" + loginBEAN.getRoleId() + "')";
            row1 = stmt.executeUpdate(query_login_tbl);
            if (row1 > 0) {
                loginBEAN.setLoginDetailsId("LOGD_" + UiiDGenerator.getUIID8());
                System.out.println("LOG_DETAILS_ID :" + loginBEAN.getLoginDetailsId());
                String query_login_details_tbl = "INSERT INTO login_details_tbl (login_details_id,login_id,first_name,last_name,email,phone,branch_id)VALUES('" + loginBEAN.getLoginDetailsId() + "','" + loginBEAN.getLoginId() + "','" + loginBEAN.getFirstName() + "','" + loginBEAN.getLastName() + "','" + loginBEAN.getEmail() + "','" + loginBEAN.getPhoneNo() + "','" + loginBEAN.getBranchId() + "')";
                row2 = stmt.executeUpdate(query_login_details_tbl);
                if (row2 > 0) {
                    loginBEAN.setPrivilageId("PRI_" + UiiDGenerator.getUIID8());
                    System.out.println("PRIVLG_ID :" + loginBEAN.getPrivilageId());
                    String query_privilage_tbl = "INSERT INTO privilage_tbl (privilage_id,username,privilages)VALUES('" + loginBEAN.getPrivilageId() + "','" + loginBEAN.getUsername() + "','" + loginBEAN.getPrivilages() + "')";
                    row3 = stmt.executeUpdate(query_privilage_tbl);
                }
            }

        } catch (SQLException sqle) {
            logger.error(sqle);
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }

        return row3;

    }

    /**
     *
     * @return
     */
    @Override
    public ObservableList<LoginBEAN> retrieveLogin() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {

            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();
            String query = "SELECT\n"
                    + "	lt.login_id,\n"
                    + "	lt.user_name,\n"
                    + "	lt. PASSWORD,\n"
                    + "  rt.role_id,\n"
                    + "	rt.role,\n"
                    + "  rt.role_text,\n"
                    + "	ld.first_name,\n"
                    + "	ld.last_name,\n"
                    + "	ld.email,\n"
                    + "	ld.phone,\n"
                    + "	bt.branch_name,\n"
                    + "  priv.privilages\n"
                    + "FROM\n"
                    + "	login_tbl lt\n"
                    + "LEFT JOIN login_details_tbl ld ON lt.login_id = ld.login_id\n"
                    + "LEFT JOIN privilage_tbl priv ON priv.username = lt.user_name\n"
                    + "LEFT JOIN branch_tbl bt ON bt.branch_id = ld.branch_id\n"
                    + "LEFT JOIN role_tbl rt ON lt.role_id = rt.role_id\n"
                    + "ORDER BY\n"
                    + "	ld.first_name";
            rs = stmt.executeQuery(query);
            retrieveLoginList.clear();
            while (rs.next()) {
                LoginBEAN loginBEAN = new LoginBEAN();
                loginBEAN.setLoginId(rs.getString("login_id"));
                loginBEAN.setUsername(rs.getString("user_name"));
                loginBEAN.setFirstName(rs.getString("first_name"));
                loginBEAN.setLastName(rs.getString("last_name"));
                loginBEAN.setRoleId(rs.getString("role_id"));
                loginBEAN.setRole(rs.getString("role"));
                loginBEAN.setRoleText(rs.getString("role_text"));
                loginBEAN.setEmail(rs.getString("email"));
                loginBEAN.setPhoneNo(rs.getString("phone"));
                loginBEAN.setBranchId(rs.getString("branch_name"));
                loginBEAN.setPrivilages(rs.getString("privilages"));
                retrieveLoginList.add(loginBEAN);
            }

        } catch (SQLException sqle) {
            logger.error(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return retrieveLoginList;
    }

    /**
     *
     * @return
     */
    @Override
    public ObservableList<BranchPOJO> retrieveBranches() {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ObservableList<BranchPOJO> branchList = FXCollections.observableArrayList();

        try {
            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();
            String query = "select * from branch_tbl";
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                BranchPOJO branchPOJO = new BranchPOJO();
                branchPOJO.setBranchId(rs.getString("branch_id"));
                branchPOJO.setBranch(rs.getString("branch_name"));
                branchList.add(branchPOJO);
            }

        } catch (SQLException sqle) {
            logger.error(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return branchList;
    }

    /**
     *
     * @return
     */
    @Override
    public ObservableList<RolePOJO> retrieveRoles() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ObservableList<RolePOJO> rolesList = FXCollections.observableArrayList();

        try {
            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();
            String query = "select * from role_tbl";
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                RolePOJO rolePOJO = new RolePOJO();
                rolePOJO.setRoleId(rs.getString("role_id"));
                rolePOJO.setRole(rs.getString("role"));
                rolePOJO.setRoleText(rs.getString("role_text"));
                rolesList.add(rolePOJO);
            }

        } catch (SQLException sqle) {
            logger.error(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return rolesList;
    }

    /**
     *
     * @param privilages
     * @return
     */
    @Override
    public ObservableList<RolePOJO> retrieveRolesUsingPrivilages(String privilages) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ObservableList<RolePOJO> rolesList = FXCollections.observableArrayList();

        try {
            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();
            System.out.println("STRING privilages in IMPL" + privilages);
            String query = "SELECT * FROM role_tbl WHERE  role_id in (" + privilages + ")";
            System.out.println("SSSSSSSSSSsssss " + query);
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                RolePOJO rolePOJO = new RolePOJO();
                rolePOJO.setRoleId(rs.getString("role_id"));
                rolePOJO.setRole(rs.getString("role"));
                rolePOJO.setRoleText(rs.getString("role_text"));
                rolesList.add(rolePOJO);
            }

        } catch (SQLException sqle) {
            logger.error(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return rolesList;
    }

    /**
     *
     * @param loginBEAN
     * @return
     */
    @Override
    public int updateLogin(LoginBEAN loginBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        try {

            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();
            String query_update_login_tbl = "UPDATE login_tbl\n"
                    + "SET user_name = '" + loginBEAN.getUsername() + "',\n"
                    + " role_id = '" + loginBEAN.getRoleId() + "'\n"
                    + "WHERE\n"
                    + "	login_id ='" + loginBEAN.getLoginId() + "'";
            stmt.addBatch(query_update_login_tbl);

            String query_update_login_details_tbl = "UPDATE login_details_tbl\n"
                    + "SET first_name = '" + loginBEAN.getFirstName() + "',\n"
                    + " last_name = '" + loginBEAN.getLastName() + "',\n"
                    + " email = '" + loginBEAN.getEmail() + "',\n"
                    + " phone = '" + loginBEAN.getPhoneNo() + "',\n"
                    + " branch_id = '" + loginBEAN.getBranchId() + "'\n"
                    + "WHERE\n"
                    + "	login_id = '" + loginBEAN.getLoginId() + "'";
            stmt.addBatch(query_update_login_details_tbl);
            String query_update_privilage_tbl = "UPDATE privilage_tbl SET privilages= '" + loginBEAN.getPrivilages() + "' WHERE username= '" + loginBEAN.getUsername() + "'";
            stmt.addBatch(query_update_privilage_tbl);
            row = stmt.executeBatch()[0];
            System.out.println("" + row);

        } catch (SQLException sqle) {
            logger.error(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }

        return row;
    }

    /**
     *
     * @param loginId
     * @return
     */
    @Override
    public int deleteLogin(String loginId) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;

        try {
            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();
            String query_delete_master = "delete from login_tbl where login_id='" + loginId + "'";
            row = stmt.executeUpdate(query_delete_master);
        } catch (SQLException sqle) {
            logger.error(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return row;
    }

    /**
     *
     * @param loginBEAN
     * @return
     */
    @Override
    public int resetPassword(LoginBEAN loginBEAN) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;

        try {

            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();
            String query_update_password = "UPDATE login_tbl\n"
                    + "SET password = '" + loginBEAN.getPassword() + "',flag=0\n"
                    + " WHERE\n"
                    + "login_id ='" + loginBEAN.getLoginId() + "'";
            System.out.println("loginBEAN.getLoginId()  ::::::" + loginBEAN.getLoginId());
            row = stmt.executeUpdate(query_update_password);

        } catch (SQLException sqle) {
            logger.error(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }

        return row;

    }

    /**
     *
     * @return
     */
    public static List<String> getAllUsernames() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<String> allUsernames = new ArrayList<>();
        try {
            String query = "select user_name from login_tbl";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                allUsernames.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return allUsernames;
    }

    /**
     *
     * @param username
     * @return
     */
    @Override
    public LoginBEAN retrieveSingleUserPrivilage(String username) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        LoginBEAN loginBEAN = new LoginBEAN();
        try {
            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();
            String query = "select * from privilage_tbl where username = '" + username + "'";
            System.out.println("dgfrdtwete " + query);
            rs = stmt.executeQuery(query);
            if (rs.next()) {
                loginBEAN.setPrivilageId(rs.getString("privilage_id"));
                loginBEAN.setUsername(rs.getString("username"));
                loginBEAN.setPrivilages(rs.getString("privilages"));
            }

        } catch (SQLException sqle) {
            logger.error(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return loginBEAN;
    }

}
