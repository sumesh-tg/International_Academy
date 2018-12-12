/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.admin.master.university.dao;

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
import javax.swing.JOptionPane;
import zs.com.ina.db.mysql.pool.DBPool;

/**
 *
 * @author zoft
 */
public class SalaryDAO {

    /**
     *
     * @return eturns all universities
     */
    public static List<String> getAllUniversities() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<String> allUniversity = new ArrayList<>();
        try {
            String query = "select university from mastertbl_university";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                allUniversity.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return allUniversity;
    }

    /**
     *
     * @return
     */
    public static ObservableList<SalaryBean> getUniversityDetails() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        ObservableList<SalaryBean> languageBEANs = FXCollections.observableArrayList();
        try {
            String query = "select university_id,university from mastertbl_university";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                SalaryBean languageBEAN = new SalaryBean();
                languageBEAN.setId(rs.getString(1));
                languageBEAN.setSalary(rs.getString(2));
                languageBEANs.add(languageBEAN);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return languageBEANs;
    }

    /**
     *
     * @param languageBEAN
     * @return
     */
    public static int updateUniversity(SalaryBean languageBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        int row = 0;
        String query = "update mastertbl_university set university=? where university_id=?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, languageBEAN.getSalary());
            ps.setString(2, languageBEAN.getId());
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
     * @param languageBEAN
     * @return
     */
    public static int insertUniversity(SalaryBean languageBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        int row = 0;
        String query = "insert into mastertbl_university(university,university_id) values(?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, languageBEAN.getSalary());
            ps.setString(2,UiiDGenerator.getUIID8());
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
     * @param id
     * @return
     */
    public static int deleteSalary(String id) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        int row = 0;
        String query = "delete from mastertbl_university where university_id=?";
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
    //Check for duplicate entry

    /**
     *
     * @param university
     * @return
     */
    public static boolean checkDuplicateEntry(String university) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        boolean f = false;
        con = DBPool.getInstance().getConn();
        String quesry = "SELECT EXISTS(SELECT 1 FROM mastertbl_university WHERE university='" + university + "')";
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
}
