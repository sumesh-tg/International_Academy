/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.admin.master.dipartment.dao;

import com.zs.ina.admin.master.coursetype.dao.CourseTypeBEAN;
import com.zs.ina.common.UiiDGenerator;
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
 * @author shine
 */
public class DepartmentDAO {

    /**
     *
     * @param departmentBean
     * @return
     */
    public static int updateDepartment(DepartmentBEAN departmentBean) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        int row = 0;
        String query = "update mastertbl_department set department=? where department_id=?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, departmentBean.getDepartment());
            ps.setString(2, departmentBean.getId());
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
     * @param text
     * @return
     */
    public static boolean checkDuplicateEntry(String text) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        boolean f = false;
        con = DBPool.getInstance().getConn();
        String quesry = "SELECT EXISTS(SELECT 1 FROM mastertbl_department WHERE department='" + text + "')";
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(quesry);
            rs.next();
            f = rs.getBoolean(1);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{null}, new ResultSet[]{null}, stackTraceElements);
        }
        return f;
    }

    /**
     *
     * @param departmentBean
     * @return
     */
    public static int insert(DepartmentBEAN departmentBean) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        int row = 0;
        String query = "insert into mastertbl_department(department,department_id) values(?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, departmentBean.getDepartment());
            ps.setString(2, UiiDGenerator.getUIID8());
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
     * @return
     */
    public static ObservableList<DepartmentBEAN> getDepartmentDetails() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        ObservableList<DepartmentBEAN> departmentBEANS = FXCollections.observableArrayList();
        try {
            String query = "select department_id,department from mastertbl_department";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                DepartmentBEAN departmentBEAN = new DepartmentBEAN();
                departmentBEAN.setId(rs.getString("department_id"));
                departmentBEAN.setDepartment(rs.getString("department"));
                departmentBEANS.add(departmentBEAN);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return departmentBEANS;
    }


    /**
     *
     * @param id
     * @return
     */
    public static int delete(String id) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        int row = 0;
        String query = "delete from mastertbl_department where department_id=?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, id);
            row = ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{null}, new ResultSet[]{null}, stackTraceElements);
        }
        return row;
    }

}
