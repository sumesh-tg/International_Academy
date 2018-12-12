/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.admin.master.othertest.dao;

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
 * @author user
 */
public class OtherTestDAO {

    //Method for get all Other Test

    /**
     *
     * @return
     */
    public static List<String> getAllOtherTest() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<String> allOtherTests = new ArrayList<>();
        try {
            String query = "select other_test from mastertbl_other_test";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                allOtherTests.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return allOtherTests;
    }

    //Method to get Other Test details

    /**
     *
     * @return
     */
    public static ObservableList<OtherTestBEAN> getOtherTestDetails() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        ObservableList<OtherTestBEAN> otherTestBEANs = FXCollections.observableArrayList();
        try {
            String query = "select other_test_id,other_test from mastertbl_other_test";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                OtherTestBEAN otherTestBEAN = new OtherTestBEAN();
                otherTestBEAN.setId(rs.getString(1));
                otherTestBEAN.setOtherTest(rs.getString(2));
                otherTestBEANs.add(otherTestBEAN);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return otherTestBEANs;
    }
//Method to insert new language

    /**
     *
     * @param otherTestBEAN
     * @return
     */
    public static int insertOtherTest(OtherTestBEAN otherTestBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        int row = 0;
        String query = "insert into mastertbl_other_test(other_test,other_test_id) values(?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, otherTestBEAN.getOtherTest());
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

    //Method to update Other test details

    /**
     *
     * @param otherTestBEAN
     * @return
     */
    public static int updateOtherTest(OtherTestBEAN otherTestBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        int row = 0;
        String query = "update mastertbl_other_test set other_test=? where other_test_id=?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, otherTestBEAN.getOtherTest());
            ps.setString(2, otherTestBEAN.getId());
            row = ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{null}, new ResultSet[]{null}, stackTraceElements);
        }
        return row;
    }

    //Method to delete a Other test

    /**
     *
     * @param otherTestId
     * @return
     */
    public static int deleteOtherTest(String otherTestId) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        int row = 0;
        String query = "delete from mastertbl_other_test where other_test_id=?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, otherTestId);
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
     * @param otherTest
     * @return
     */
    public static boolean checkDuplicateEntry(String otherTest) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        boolean f = false;
        con = DBPool.getInstance().getConn();
        String quesry = "SELECT EXISTS(SELECT 1 FROM mastertbl_other_test WHERE other_test='" + otherTest + "')";
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
