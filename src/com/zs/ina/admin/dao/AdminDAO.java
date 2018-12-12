/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.admin.dao;

import com.zs.ina.common.UiiDGenerator;
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
 * @author user
 */
public class AdminDAO {

    private static final String BRANCH_DETAILS_INSERT_QUERY = "Insert into branch_tbl(branch_name,location, "
            + "address,company_owner,branch_owner )values(?,?,?,?,?)";
    private static final String BRANCH_DETAILS_UPDATE_QUERY = "Update branch_tbl set branch_name=?,location=?, "
            + "address=?,company_owner=?,branch_owner=? where branch_id=?";
//    private static final String INSERT_LOGIN_DETAILS_QUERY = "Insert into login_tbl(user_name,password,role_id) values(?,(SELECT SUBSTRING(MD5(RAND()) FROM 1 FOR 8) ),"
//            + "(select role_id from role_tbl where role='" + Context.getInstance().currentProfile().getLoginPOJO().getRole() + "'))";

    //Method for insert login Details

    /**
     *
     * @return
     */
    public static int insertLoginDetails() {
        int row = 0;
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        try {
//            String loginQuery = "Insert into login_tbl(user_name,password,role_id) values(?,(SELECT SUBSTRING(MD5(RAND()) FROM 1 FOR 8) ),"
//                    + "(select role_id from role_tbl where role='" + Context.getInstance().currentProfile().getLoginPOJO().getRole() + "'))WHERE NOT EXISTS (\n"
//                    + "    SELECT user_name FROM login_tbl WHERE user_name = ?\n"
//                    + ") LIMIT 1";

            String loginQuery = "Insert into login_tbl(user_name,password,role_id,login_id) SELECT * FROM (SELECT ?,(SELECT SUBSTRING(MD5(RAND()) FROM 1 FOR 8) ),"
                    + "(select role_id from role_tbl where role='" + Context.getInstance().currentProfile().getLoginPOJO().getRole() + "'),?) AS temp WHERE NOT EXISTS (\n"
                    + "    SELECT user_name FROM login_tbl WHERE user_name = ?\n"
                    + ") LIMIT 1";

            PreparedStatement ps = con.prepareStatement(loginQuery);
            ps.setString(1, Context.getInstance().currentProfile().getLoginPOJO().getUserName());
            ps.setString(2, Context.getInstance().currentProfile().getLoginPOJO().getLoginId());
            ps.setString(3, Context.getInstance().currentProfile().getLoginPOJO().getUserName());

            row = ps.executeUpdate();

            String loginDetailsQuery = "insert into login_details_tbl(login_id,first_name,last_name,email,phone,branch_id,login_details_id) "
                    + "SELECT * FROM (SELECT (select login_id from login_tbl where user_name='" + Context.getInstance().currentProfile().getLoginPOJO().getUserName() + "'),?,?,?,?,(select branch_id from branch_tbl where branch_name='" + Context.getInstance().currentProfile().getLoginPOJO().getBranch() + "'),?) as temp WHERE NOT EXISTS (\n"
                    + "    SELECT login_id FROM login_details_tbl WHERE login_id = (select login_id from login_tbl where user_name=?)\n"
                    + ") LIMIT 1";
            PreparedStatement ps1 = con.prepareStatement(loginDetailsQuery);
            ps1.setString(1, Context.getInstance().currentProfile().getLoginPOJO().getFirstName());
            ps1.setString(2, Context.getInstance().currentProfile().getLoginPOJO().getLastName());
            ps1.setString(3, Context.getInstance().currentProfile().getLoginPOJO().getEmail());
            ps1.setString(4, Context.getInstance().currentProfile().getLoginPOJO().getPhone());
            ps1.setString(5, UiiDGenerator.getUIID8());
            ps1.setString(6, Context.getInstance().currentProfile().getLoginPOJO().getUserName());

            System.out.println(">>>>>" + loginDetailsQuery);
            row = ps1.executeUpdate();

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
     * @param loginId
     * @return
     */
    public static String mailSend(String loginId) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        String password = "";
        try {
            String query = "Select password from login_tbl where login_id='" + loginId + "'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            rs.next();
            password = rs.getString(1);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return password;
    }

    //Method for update login details

    /**
     *
     * @param loginId
     * @return
     */
    public static int updateLoginDetails(String loginId) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        try {
            String loginQuery = "Update login_tbl set user_name=?,role_id=(select role_id from role_tbl where role='" + Context.getInstance().currentProfile().getLoginPOJO().getRole() + "') where login_id='" + loginId + "'";
            PreparedStatement ps = con.prepareStatement(loginQuery);
            ps.setString(1, Context.getInstance().currentProfile().getLoginPOJO().getUserName());
            row = ps.executeUpdate();

            String loginDetailsQuery = "Update login_details_tbl set first_name=?,last_name=?,email=?,phone=?"
                    + ",branch_id=(select branch_id from branch_tbl where branch_name='" + Context.getInstance().currentProfile().getLoginPOJO().getBranch() + "') where login_id='" + loginId + "'";
            PreparedStatement ps1 = con.prepareStatement(loginDetailsQuery);
            ps1.setString(1, Context.getInstance().currentProfile().getLoginPOJO().getFirstName());
            ps1.setString(2, Context.getInstance().currentProfile().getLoginPOJO().getLastName());
            ps1.setString(3, Context.getInstance().currentProfile().getLoginPOJO().getEmail());
            ps1.setString(4, Context.getInstance().currentProfile().getLoginPOJO().getPhone());
            row = ps1.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return row;
    }

    //Method for delete login details

    /**
     *
     * @param loginId
     */
    public static void deleteLoginDetails(String loginId) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        try {
            String deleteBranchQuery = "delete from login_tbl where login_id='" + loginId + "'";
            stmt = con.createStatement();
            stmt.executeUpdate(deleteBranchQuery);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
    }

//Method for insert branch Details

    /**
     *
     * @return
     */
    public static int insertBranchDetails() {
        int row = 0;
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        try {
            String BRANCH_DETAILS_INSERT_QUERY = "Insert into branch_tbl(branch_id,branch_name,location, "
                    + "address,company_owner,branch_owner )values(?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(BRANCH_DETAILS_INSERT_QUERY);
            ps.setString(1, UiiDGenerator.getUIID8());
            ps.setString(2, Context.getInstance().currentProfile().getBranchPOJO().getBranchName());
            ps.setString(3, Context.getInstance().currentProfile().getBranchPOJO().getLocation());
            ps.setString(4, Context.getInstance().currentProfile().getBranchPOJO().getAddress());
            ps.setString(5, Context.getInstance().currentProfile().getBranchPOJO().getCompanyOwner());
            ps.setString(6, Context.getInstance().currentProfile().getBranchPOJO().getBranchOwner());
            row = ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return row;
    }

    //Method for update branch details

    /**
     *
     * @param branchId
     */
    public static void updateBranchDetails(String branchId) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        try {
            String BRANCH_DETAILS_UPDATE_QUERY = "Update branch_tbl set branch_name=?,location=?, "
                    + "address=?,company_owner=?,branch_owner=? where branch_id=?";
            PreparedStatement ps = con.prepareStatement(BRANCH_DETAILS_UPDATE_QUERY);
            ps.setString(1, Context.getInstance().currentProfile().getBranchPOJO().getBranchName());
            ps.setString(2, Context.getInstance().currentProfile().getBranchPOJO().getLocation());
            ps.setString(3, Context.getInstance().currentProfile().getBranchPOJO().getAddress());
            ps.setString(4, Context.getInstance().currentProfile().getBranchPOJO().getCompanyOwner());
            ps.setString(5, Context.getInstance().currentProfile().getBranchPOJO().getBranchOwner());
            ps.setString(6, branchId);
            int row = ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
    }

    //Method for delete branch details

    /**
     *
     * @param branchId
     */
    public static void deleteBranchDetails(String branchId) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        try {
            String deleteBranchQuery = "delete from branch_tbl where branch_id='" + branchId + "'";
            stmt = con.createStatement();
            stmt.executeUpdate(deleteBranchQuery);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
    }
//Method for insert all privilages

    /**
     *
     * @return
     */
    public static int insertPrivilageDetails() {
        int row = 0;
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        try {
            String query = "Insert into privilage_tbl(privilage,privilage_desc,privilage_id) "
                    + "values(?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, Context.getInstance().currentProfile().getPrivilagePOJO().getPrivilage());
            ps.setString(2, Context.getInstance().currentProfile().getPrivilagePOJO().getPrivilageDesc());
            ps.setString(3, UiiDGenerator.getUIID8());
            row = ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return row;
    }

    //Method for update branch details

    /**
     *
     * @param privilageId
     */
    public static void updatePrivilageDetails(String privilageId) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        try {
            String query = "Update privilage_tbl set privilage=?,privilage_desc=?"
                    + " where privilage_id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, Context.getInstance().currentProfile().getPrivilagePOJO().getPrivilage());
            ps.setString(2, Context.getInstance().currentProfile().getPrivilagePOJO().getPrivilageDesc());
            ps.setString(3, privilageId);
            System.out.println(query.toString());
            int row = ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
    }
//Method for delete privilage details

    /**
     *
     * @param privilageId
     */
    public static void deletePrivilageDetails(String privilageId) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        try {
            String deleteBranchQuery = "delete from privilage_tbl where privilage_id='" + privilageId + "'";
            stmt = con.createStatement();
            stmt.executeUpdate(deleteBranchQuery);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
    }

    //Method for insert role details

    /**
     *
     * @return
     */
    public static int insertRoleDetails() {
        int row = 0;
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        try {
            String query = "Insert into role_tbl(role,privilage_id,role_id) "
                    + "values(?,(select privilage_id from privilage_tbl where privilage='" + Context.getInstance().currentProfile().getRolePOJO().getPrivilage() + "'),?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, Context.getInstance().currentProfile().getRolePOJO().getRole());
            ps.setString(2, UiiDGenerator.getUIID8());
            row = ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return row;
    }

    //Method for insert source details

    /**
     *
     * @return
     */
    public static int insertSourceDetails() {
        int row = 0;
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        try {
            String query = "Insert into source_tbl(source,description)values(?,?) ";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, Context.getInstance().currentProfile().getSourcePOJO().getSource());
            ps.setString(2, Context.getInstance().currentProfile().getSourcePOJO().getDescription());
            row = ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return row;
    }

    //Method for insert source details

    /**
     *
     * @param sourceId
     * @return
     */
    public static int updateSourceDetails(int sourceId) {
        int row = 0;
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        try {
            String query = "update  source_tbl set source=?,description=? where id=? ";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, Context.getInstance().currentProfile().getSourcePOJO().getSource());
            ps.setString(2, Context.getInstance().currentProfile().getSourcePOJO().getDescription());
            ps.setInt(3, sourceId);
            row = ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return row;
    }

    //Method for update branch details

    /**
     *
     * @param roleId
     */
    public static void updateRoleDetails(String roleId) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        try {
            String query = "Update role_tbl set role=?,"
                    + "privilage_id=(select privilage_id from privilage_tbl where privilage='" + Context.getInstance().currentProfile().getRolePOJO().getPrivilage() + "')"
                    + " where role_id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, Context.getInstance().currentProfile().getRolePOJO().getRole());
            ps.setString(2, roleId);
            System.out.println(query.toString());
            int row = ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
    }
//Method for delete Role Details

    /**
     *
     * @param roleId
     */
    public static void deleteRoleDetails(String roleId) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        try {
            String deleteBranchQuery = "delete from role_tbl where role_id='" + roleId + "'";
            stmt = con.createStatement();
            stmt.executeUpdate(deleteBranchQuery);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
    }

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
//Method for get all privilages

    /**
     *
     * @return
     */
    public static List<String> getAllPrivilages() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<String> allPrivilages = new ArrayList<>();
        try {
            String query = "select privilage from privilage_tbl";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                allPrivilages.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return allPrivilages;
    }

    //Method for get all privilages

    /**
     *
     * @return
     */
    public static List<PrivilageDetails> getAllPrivilageDetails() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<PrivilageDetails> privilegeList = new ArrayList<PrivilageDetails>();
        try {
            String query = "select * from privilage_tbl";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                PrivilageDetails privilageDetails = new PrivilageDetails();
                privilageDetails.setPrivilageId(rs.getString(1));
                privilageDetails.setPrivilageName(rs.getString(2));
                privilageDetails.setDescription(rs.getString(3));
                privilegeList.add(privilageDetails);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return privilegeList;
    }
//Method for get all Roles

    /**
     *
     * @return
     */
    public static List<String> getAllRole() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<String> allRoles = new ArrayList<>();
        con = DBPool.getInstance().getConn();
        try {
            String query = "select role from role_tbl ";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                allRoles.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return allRoles;
    }
///Method for get all login usernames

    /**
     *
     * @return
     */
    public static List<String> getAllUserName() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<String> allUserNames = new ArrayList<>();
        con = DBPool.getInstance().getConn();
        try {
            String query = "select user_name from login_tbl ";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                allUserNames.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return allUserNames;
    }

    //Method for get all details who is already in database

    /**
     *
     * @param branchName
     * @return
     */
    public static List<BranchDetails> getBranchDetails(String branchName) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<BranchDetails> branchList = new ArrayList<BranchDetails>();
        try {
            String query = "select * from branch_tbl where branch_name='" + branchName + "'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                BranchDetails branchDetails = new BranchDetails();
                branchDetails.setBranchId(rs.getString(1));
                branchDetails.setBranchName(rs.getString(2));
                branchDetails.setLocation(rs.getString(3));
                branchDetails.setAddress(rs.getString(4));
                branchDetails.setCompanyOwner(rs.getString(5));
                branchDetails.setBranchOwner(rs.getString(6));
                branchList.add(branchDetails);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return branchList;
    }

    // Method for get all branch details 

    /**
     *
     * @return
     */
    public static List<BranchDetails> getAllBranchDetails() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<BranchDetails> branchList = new ArrayList<BranchDetails>();
        try {
            String query = "select * from branch_tbl";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                BranchDetails branchDetails = new BranchDetails();
                branchDetails.setBranchId(rs.getString(1));
                branchDetails.setBranchName(rs.getString(2));
                branchDetails.setLocation(rs.getString(3));
                branchDetails.setAddress(rs.getString(4));
                branchDetails.setCompanyOwner(rs.getString(5));
                branchDetails.setBranchOwner(rs.getString(6));
                branchList.add(branchDetails);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return branchList;
    }

    // Method for get all Source details 

    /**
     *
     * @return
     */
    public static List<SourcePOJO> getAllSourceDetails() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<SourcePOJO> sourceList = new ArrayList<SourcePOJO>();
        try {
            String query = "select * from source_tbl";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                SourcePOJO sourcePOJO = new SourcePOJO();
////                sourcePOJO.setSourceId(rs.getInt(1));
////                sourcePOJO.setSource(rs.getString(2));
////                sourcePOJO.setDescription(rs.getString(3));
                sourceList.add(sourcePOJO);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return sourceList;
    }

    // Method for get all role details 

    /**
     *
     * @return
     */
    public static List<RoleDetails> getAllRoleDetails() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<RoleDetails> roleList = new ArrayList<RoleDetails>();
        try {
            String query = "select r.role_id,r.role,p.privilage from role_tbl r,privilage_tbl p where p.privilage_id=r.privilage_id";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                RoleDetails roleDetails = new RoleDetails();
                roleDetails.setRoleId(rs.getString(1));
                roleDetails.setRole(rs.getString(2));
                roleDetails.setPrivilage(rs.getString(3));
                roleList.add(roleDetails);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return roleList;
    }

    //Method for get all Role details who is already in database

    /**
     *
     * @param role
     * @return
     */
    public static List<RoleDetails> getRoleDetails(String role) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<RoleDetails> roleList = new ArrayList<RoleDetails>();
        try {
            String query = "select r.role_id,r.role,p.privilage from role_tbl r,privilage_tbl p where r.role='" + role + "'"
                    + " and p.privilage_id=r.privilage_id;";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                RoleDetails roleDetails = new RoleDetails();
                roleDetails.setRoleId(rs.getString(1));
                roleDetails.setPrivilage(rs.getString(3));
                roleList.add(roleDetails);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return roleList;
    }

    //Method for get all Login details who is already in database

    /**
     *
     * @param userName
     * @return
     */
    public static List<LoginDetails> getLoginDetails(String userName) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<LoginDetails> loginList = new ArrayList<LoginDetails>();
        try {
            String query = "select l.login_id,l.user_name,r.role,ld.first_name,ld.last_name,ld.email,ld.phone,b.branch_name "
                    + "from login_tbl l,login_details_tbl ld,branch_tbl b,role_tbl r "
                    + "where l.user_name='" + userName + "' and (l.login_id=ld.login_id) and (r.role_id=l.role_id) and (b.branch_id=ld.branch_id) ";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                LoginDetails loginDetails = new LoginDetails();
                loginDetails.setLoginId(rs.getString(1));
                loginDetails.setUserName(rs.getString(2));
                loginDetails.setRole(rs.getString(3));
                loginDetails.setFirstName(rs.getString(4));
                loginDetails.setLastName(rs.getString(5));
                loginDetails.setEmail(rs.getString(6));
                loginDetails.setPhone(rs.getString(7));
                loginDetails.setBranch(rs.getString(8));
                loginList.add(loginDetails);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return loginList;
    }

    //Method for get all Login details who is already in database

    /**
     *
     * @return
     */
    public static List<LoginDetails> getAllLoginDetails() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<LoginDetails> loginList = new ArrayList<LoginDetails>();
        try {
            String query = "select l.login_id,l.user_name,r.role,ld.first_name,ld.last_name,ld.email,ld.phone,b.branch_name from login_tbl l,login_details_tbl ld,branch_tbl b,role_tbl r "
                    + "where (l.login_id=ld.login_id) and (r.role_id=l.role_id) and (b.branch_id=ld.branch_id)";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                LoginDetails loginDetails = new LoginDetails();
                loginDetails.setLoginId(rs.getString(1));
                loginDetails.setUserName(rs.getString(2));
                loginDetails.setRole(rs.getString(3));
                loginDetails.setFirstName(rs.getString(4));
                loginDetails.setLastName(rs.getString(5));
                loginDetails.setEmail(rs.getString(6));
                loginDetails.setPhone(rs.getString(7));
                loginDetails.setBranch(rs.getString(8));
                loginList.add(loginDetails);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return loginList;
    }

    //Method for get privilage details

    /**
     *
     * @param privilage
     * @return
     */
    public static List<PrivilageDetails> getPrivilageDetails(String privilage) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<PrivilageDetails> privilageList = new ArrayList<PrivilageDetails>();
        try {
            String query = "select * from privilage_tbl where privilage='" + privilage + "'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                PrivilageDetails privilageDetails = new PrivilageDetails();
                privilageDetails.setPrivilageId(rs.getString(1));
                privilageDetails.setPrivilageName(rs.getString(2));
                privilageDetails.setDescription(rs.getString(3));
                privilageList.add(privilageDetails);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return privilageList;
    }

//Branch inner Class

    /**
     *
     */
    public static class BranchDetails {

        String branchId;
        String branchName, location, address, companyOwner, branchOwner;

        /**
         *
         * @return
         */
        public String getBranchId() {
            return branchId;
        }

        /**
         *
         * @param branchId
         */
        public void setBranchId(String branchId) {
            this.branchId = branchId;
        }

        /**
         *
         * @return
         */
        public String getBranchName() {
            return branchName;
        }

        /**
         *
         * @param branchName
         */
        public void setBranchName(String branchName) {
            this.branchName = branchName;
        }

        /**
         *
         * @return
         */
        public String getLocation() {
            return location;
        }

        /**
         *
         * @param location
         */
        public void setLocation(String location) {
            this.location = location;
        }

        /**
         *
         * @return
         */
        public String getAddress() {
            return address;
        }

        /**
         *
         * @param address
         */
        public void setAddress(String address) {
            this.address = address;
        }

        /**
         *
         * @return
         */
        public String getCompanyOwner() {
            return companyOwner;
        }

        /**
         *
         * @param companyOwner
         */
        public void setCompanyOwner(String companyOwner) {
            this.companyOwner = companyOwner;
        }

        /**
         *
         * @return
         */
        public String getBranchOwner() {
            return branchOwner;
        }

        /**
         *
         * @param branchOwner
         */
        public void setBranchOwner(String branchOwner) {
            this.branchOwner = branchOwner;
        }

        @Override
        public String toString() {
            return "brancDetails{" + "branchId=" + branchId + ", branchName=" + branchName + ", location=" + location + ", address=" + address + ", companyOwner=" + companyOwner + ", branchOwner=" + branchOwner + '}';
        }
    }

    // Inner Class for role deatils

    /**
     *
     */
    public static class RoleDetails {

        String roleId;
        String privilage, role;

        /**
         *
         * @return
         */
        public String getRole() {
            return role;
        }

        /**
         *
         * @param role
         */
        public void setRole(String role) {
            this.role = role;
        }

        /**
         *
         * @return
         */
        public String getRoleId() {
            return roleId;
        }

        /**
         *
         * @param roleId
         */
        public void setRoleId(String roleId) {
            this.roleId = roleId;
        }

        /**
         *
         * @return
         */
        public String getPrivilage() {
            return privilage;
        }

        /**
         *
         * @param privilage
         */
        public void setPrivilage(String privilage) {
            this.privilage = privilage;
        }
    }

    //Inner class for login Details

    /**
     *
     */
    public static class LoginDetails {

        String loginId;
        String userName, role, firstName, lastName;
        String email, phone, branch;

        /**
         *
         * @return
         */
        public String getUserName() {
            return userName;
        }

        /**
         *
         * @return
         */
        public String getLoginId() {
            return loginId;
        }

        /**
         *
         * @param loginId
         */
        public void setLoginId(String loginId) {
            this.loginId = loginId;
        }

        /**
         *
         * @param userName
         */
        public void setUserName(String userName) {
            this.userName = userName;
        }

        /**
         *
         * @return
         */
        public String getRole() {
            return role;
        }

        /**
         *
         * @param role
         */
        public void setRole(String role) {
            this.role = role;
        }

        /**
         *
         * @return
         */
        public String getFirstName() {
            return firstName;
        }

        /**
         *
         * @param firstName
         */
        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        /**
         *
         * @return
         */
        public String getLastName() {
            return lastName;
        }

        /**
         *
         * @param lastName
         */
        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        /**
         *
         * @return
         */
        public String getEmail() {
            return email;
        }

        /**
         *
         * @param email
         */
        public void setEmail(String email) {
            this.email = email;
        }

        /**
         *
         * @return
         */
        public String getPhone() {
            return phone;
        }

        /**
         *
         * @param phone
         */
        public void setPhone(String phone) {
            this.phone = phone;
        }

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
    }
    //Inner Class for Privilage

    /**
     *
     */
    public static class PrivilageDetails {

        String privilageId;
        String privilageName, Description;

        /**
         *
         * @return
         */
        public String getPrivilageId() {
            return privilageId;
        }

        /**
         *
         * @param privilageId
         */
        public void setPrivilageId(String privilageId) {
            this.privilageId = privilageId;
        }

        /**
         *
         * @return
         */
        public String getPrivilageName() {
            return privilageName;
        }

        /**
         *
         * @param privilageName
         */
        public void setPrivilageName(String privilageName) {
            this.privilageName = privilageName;
        }

        /**
         *
         * @return
         */
        public String getDescription() {
            return Description;
        }

        /**
         *
         * @param Description
         */
        public void setDescription(String Description) {
            this.Description = Description;
        }

    }
}
