/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.agency.dao;

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
public class AgencyDAO {

    // Method for insert agency details

    /**
     *
     * @return
     */
    public static int agencyDetailsInsert() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        int row = 0;
        String query = "insert into agencies_tbl(branch_id,username,firstname,lastname,email,phone,address,id)"
                + "values((select branch_id from branch_tbl where"
                + " branch_name='" + Context.getInstance().currentProfile().getAgencyPOJO().getBranch() + "'),?,?,?,?,?,?,?)";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, Context.getInstance().currentProfile().getAgencyPOJO().getUserName());
            ps.setString(2, Context.getInstance().currentProfile().getAgencyPOJO().getFirstName());
            ps.setString(3, Context.getInstance().currentProfile().getAgencyPOJO().getLastName());
            ps.setString(4, Context.getInstance().currentProfile().getAgencyPOJO().getEmail());
            ps.setString(5, Context.getInstance().currentProfile().getAgencyPOJO().getPhone());
            ps.setString(6, Context.getInstance().currentProfile().getAgencyPOJO().getAddress());
            ps.setString(7, UiiDGenerator.getUIID8());
            row = ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return row;
    }

    //Method for update agency details

    /**
     *
     * @param agencyId
     * @return
     */
    public static int updateAgencyDetails(String agencyId) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        try {
            String query = "Update agencies_tbl set username=?,branch_id=(select branch_id from branch_tbl where branch_name='" + Context.getInstance().currentProfile().getAgencyPOJO().getBranch() + "'),firstname=?,lastname=?,email=?,phone=?,address=? where id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, Context.getInstance().currentProfile().getAgencyPOJO().getUserName());
            ps.setString(2, Context.getInstance().currentProfile().getAgencyPOJO().getFirstName());
            ps.setString(3, Context.getInstance().currentProfile().getAgencyPOJO().getLastName());
            ps.setString(4, Context.getInstance().currentProfile().getAgencyPOJO().getEmail());
            ps.setString(5, Context.getInstance().currentProfile().getAgencyPOJO().getPhone());
            ps.setString(6, Context.getInstance().currentProfile().getAgencyPOJO().getAddress());
            ps.setString(7, agencyId);
            System.out.println("Query==>" + query.toString());
            row = ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return row;
    }

    //Metod to delete an agency

    /**
     *
     * @param agencyId
     */
    public static void deleteAgency(String agencyId) {
        Connection con = null;
        Statement stmt = null;
        con = DBPool.getInstance().getConn();
        String query = "delete from agencies_tbl where id='" + agencyId + "'";
        try {
            stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{null}, stackTraceElements);
        }
    }

    //Method to get usernames of agencies

    /**
     *
     * @return
     */
    public static List<String> getAllUsers() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<String> allUserNames = new ArrayList<>();
        String query = "select username from agencies_tbl";
        try {
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

    //Method for get all Login details who is already in database

    /**
     *
     * @param userName
     * @return
     */
    public static List<AgencyDetails> getAgencyDetails(String userName) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<AgencyDetails> agencyList = new ArrayList<AgencyDetails>();
        try {
            String query = "select a.id,a.username,a.firstname,a.lastname,b.branch_name,a.email,a.phone,a.address from agencies_tbl a,branch_tbl b"
                    + " where a.username='" + userName + "' and b.branch_id=a.branch_id";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                AgencyDetails agencyDetails = new AgencyDetails();
                agencyDetails.setAgencyId(rs.getString(1));
                agencyDetails.setUserName(rs.getString(2));
                agencyDetails.setFirstName(rs.getString(3));
                agencyDetails.setLastName(rs.getString(4));
                agencyDetails.setBranch(rs.getString(5));
                agencyDetails.setEmail(rs.getString(6));
                agencyDetails.setPhone(rs.getString(7));
                agencyDetails.setAddress(rs.getString(8));
                agencyList.add(agencyDetails);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return agencyList;
    }

    // Method for get all branch details 

    /**
     *
     * @return
     */
    public static List<AgencyPOJO> getAllBranchDetails() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<AgencyPOJO> branchList = new ArrayList<AgencyPOJO>();
        try {
            String query = " select a.id,a.username,a.lastname,a.firstname,a.email,a.phone,a.address,b.branch_name from agencies_tbl a,branch_tbl b where b.branch_id=a.branch_id;";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                AgencyPOJO branchDetails = new AgencyPOJO();
                branchDetails.setAgencyId(rs.getString(1));
                branchDetails.setUserNameClmn(rs.getString(2));
                branchDetails.setFirstNameClmn(rs.getString(4));
                branchDetails.setLastNameClmn(rs.getString(3));
                branchDetails.setEmailClmn(rs.getString(5));
                branchDetails.setPhoneClmn(rs.getString(6));
                branchDetails.setAddressClmn(rs.getString(7));
                branchDetails.setBranchClmn(rs.getString(8));
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

    //Inner Class for get Agency Details

    /**
     *
     */
    public static class AgencyDetails {

        String agencyId;
        String userName, firstName, lastName;
        String email, phone, branch, address;

        /**
         *
         * @return
         */
        public String getAgencyId() {
            return agencyId;
        }

        /**
         *
         * @param agencyId
         */
        public void setAgencyId(String agencyId) {
            this.agencyId = agencyId;
        }

        /**
         *
         * @return
         */
        public String getUserName() {
            return userName;
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

    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        getAgencyDetails("jinszoft");
    }
}
