/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.counselor.empstatus.dao;

import com.zs.ina.utility.ClosingResourcesInDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import zs.com.ina.db.mysql.pool.DBPool;

/**
 *
 * @author zoft
 */
public class EmployeeStatusDAO {

    /**
     *
     * @param selectedBranch
     * @return
     */
    public static List<EmpStatusBEAN> getEmpStatus(String selectedBranch) {
        int row = 0;
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<EmpStatusBEAN> empStatusList = new ArrayList<>();
        try {
            String query = "SELECT\n"
                    + "	DISTINCT CONCAT(ld.first_name,\" \",ld.last_name) as staff_name ,ld.phone,ld.ext_number,r.role,IFNULL((DATEDIFF(CURDATE() ,la.attempt_date)),2),b.phone_number\n"
                    + "FROM\n"
                    + "	login_details_tbl ld\n"
                    + "LEFT JOIN login_tbl l ON (\n"
                    + "	ld.login_id = l.login_id\n"
                    + ")\n"
                    + "LEFT JOIN role_tbl r ON(\n"
                    + "r.role_id=l.role_id\n"
                    + ")\n"
                    + "LEFT JOIN login_auditor la ON(\n"
                    + "la.user_id=ld.login_id \n"
                    + ")LEFT JOIN branch_tbl b ON(\n"
                    + "ld.branch_id=b.branch_id \n"
                    + ") WHERE ld.branch_id=b.branch_id and b.branch_name=?";
            System.out.println("test attendance" + query);
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, selectedBranch);
            rs = ps.executeQuery();
            while (rs.next()) {
                EmpStatusBEAN scheduleBEAN = new EmpStatusBEAN();
                //   scheduleBEAN.setId(rs.getString(1));
                scheduleBEAN.setName(rs.getString(1));
                scheduleBEAN.setExt_number(rs.getString(3));
                scheduleBEAN.setPosition(rs.getString(4));
                if (rs.getString(5).equals("0")) {
                    scheduleBEAN.setStatus("Present");
                } else {
                    scheduleBEAN.setStatus("Absent");
                }
                scheduleBEAN.setContact_number(rs.getString(6));
                empStatusList.add(scheduleBEAN);

            }
            for (EmpStatusBEAN empStatusBEAN : empStatusList) {
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return empStatusList;
    }

    /**
     *
     * @param username
     * @return
     */
    public static List<EmpStatusBEAN> getStaffData(String username) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<EmpStatusBEAN> empStatusList = new ArrayList<>();
        try {
            String query = "SELECT DISTINCT\n"
                    + "	CONCAT(\n"
                    + "		ld.first_name,\n"
                    + "		\" \",\n"
                    + "		ld.last_name\n"
                    + "	) AS staff_name,\n"
                    + "	ld.phone,\n"
                    + "	ld.ext_number,\n"
                    + "	r.role,\n"
                    + "	b.phone_number,b.branch_name\n"
                    + "FROM\n"
                    + "	login_details_tbl ld\n"
                    + "LEFT JOIN login_tbl l ON (ld.login_id = l.login_id)\n"
                    + "LEFT JOIN role_tbl r ON (r.role_id = l.role_id)\n"
                    + "LEFT JOIN login_auditor la ON (la.user_id = ld.login_id)\n"
                    + "LEFT JOIN branch_tbl b ON (ld.branch_id = b.branch_id)\n"
                    + "WHERE\n"
                    + "	ld.branch_id = b.branch_id\n"
                    + "AND l.user_name='" + username + "'";
            System.out.println("test attendance" + query);
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                EmpStatusBEAN scheduleBEAN = new EmpStatusBEAN();
                //   scheduleBEAN.setId(rs.getString(1));
                scheduleBEAN.setName(rs.getString("staff_name"));
                scheduleBEAN.setExt_number(rs.getString("phone"));
                scheduleBEAN.setPosition(rs.getString("ext_number"));
                scheduleBEAN.setContact_number(rs.getString("phone_number"));
                scheduleBEAN.setPosition(rs.getString("role"));
                scheduleBEAN.setBranchName(rs.getString("branch_name"));
                empStatusList.add(scheduleBEAN);

            }
          
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return empStatusList;
    }

    /**
     *
     * @return
     */
    public static ObservableList<OfficeStaffPOJO> getOfficeStaffNames() {
        ObservableList<OfficeStaffPOJO> observableListStaff = FXCollections.observableArrayList();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        try {
            String query = "SELECT\n"
                    + "	DISTINCT CONCAT(ld.first_name,\" \",ld.last_name,\"(\",l.user_name,\")\") as staff_name ,l.user_name,ld.phone,ld.ext_number,r.role\n"
                    + "FROM\n"
                    + "	login_details_tbl ld\n"
                    + "LEFT JOIN login_tbl l ON (\n"
                    + "	ld.login_id = l.login_id\n"
                    + ")\n"
                    + "LEFT JOIN role_tbl r ON(\n"
                    + "r.role_id=l.role_id\n"
                    + ")\n"
                    + "LEFT JOIN login_auditor la ON(\n"
                    + "la.user_id=ld.login_id \n"
                    + ")LEFT JOIN branch_tbl b ON(\n"
                    + "ld.branch_id=b.branch_id \n"
                    + ") WHERE ld.branch_id=b.branch_id AND r.role='ROLE_OFFICE'";
            PreparedStatement ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                OfficeStaffPOJO staffPOJO = new OfficeStaffPOJO();
                staffPOJO.setFullname(rs.getString("staff_name"));
                staffPOJO.setUsername(rs.getString("user_name"));
                observableListStaff.add(staffPOJO);
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return observableListStaff;
    }

    /**
     *
     * @param branch
     * @return
     */
    public static ObservableList<OfficeStaffPOJO> getOfficeStaffNames(String branch) {

        ObservableList<OfficeStaffPOJO> observableListStaff = FXCollections.observableArrayList();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        try {
            String query = "SELECT\n"
                    + "	DISTINCT CONCAT(ld.first_name,\" \",ld.last_name,\"(\",l.user_name,\")\") as staff_name ,l.user_name,ld.phone,ld.ext_number,r.role\n"
                    + "FROM\n"
                    + "	login_details_tbl ld\n"
                    + "LEFT JOIN login_tbl l ON (\n"
                    + "	ld.login_id = l.login_id\n"
                    + ")\n"
                    + "LEFT JOIN role_tbl r ON(\n"
                    + "r.role_id=l.role_id\n"
                    + ")\n"
                    + "LEFT JOIN login_auditor la ON(\n"
                    + "la.user_id=ld.login_id \n"
                    + ")LEFT JOIN branch_tbl b ON(\n"
                    + "ld.branch_id=b.branch_id \n"
                    + ") WHERE ld.branch_id=b.branch_id AND r.role='ROLE_OFFICE' AND b.branch=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, branch);
            rs = ps.executeQuery();
            while (rs.next()) {
                OfficeStaffPOJO staffPOJO = new OfficeStaffPOJO();
                staffPOJO.setFullname(rs.getString("staff_name"));
                staffPOJO.setUsername(rs.getString("user_name"));
                observableListStaff.add(staffPOJO);
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return observableListStaff;

    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        List<EmpStatusBEAN> appointmentList = getEmpStatus("kollam");
        for (EmpStatusBEAN ed : appointmentList) {
            System.out.println("test ===>>> " + ed.toString());
        }
    }
}
