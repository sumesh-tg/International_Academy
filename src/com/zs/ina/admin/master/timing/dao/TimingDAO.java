/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.admin.master.timing.dao;

import com.zs.ina.admin.master.score.dao.ScoreBEAN;
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
public class TimingDAO {

    //Method for get all Scores

    /**
     *
     * @return
     */
    public static List<String> getAllTimings() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<String> timingList = new ArrayList<>();
        try {
            String query = "select timing from mastertbl_timing";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                timingList.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return timingList;
    }

    //Method to get timing details

    /**
     *
     * @return
     */
    public static ObservableList<TimingBEAN> getTimingDetails() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        ObservableList<TimingBEAN> timingBEANs = FXCollections.observableArrayList();
        try {
            String query = "select timing_id,timing from mastertbl_timing";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                TimingBEAN timingBEAN = new TimingBEAN();
                timingBEAN.setId(rs.getString(1));
                timingBEAN.setTiming(rs.getString(2));
                timingBEANs.add(timingBEAN);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return timingBEANs;
    }

    //Method to update timing details

    /**
     *
     * @param timingBEAN
     * @return
     */
    public static int updateTiming(TimingBEAN timingBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        int row = 0;
        String query = "update mastertbl_timing set timing=? where timing_id=?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, timingBEAN.getTiming());
            ps.setString(2, timingBEAN.getId());
            row = ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{null}, new ResultSet[]{null}, stackTraceElements);
        }
        return row;
    }
    //Method to insert new timing

    /**
     *
     * @param timingBEAN
     * @return
     */
    public static int insertTiming(TimingBEAN timingBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        int row = 0;
        String query = "insert into mastertbl_timing(timing,timing_id) values(?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, timingBEAN.getTiming());
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

    //Method to delete scores

    /**
     *
     * @param timingId
     * @return
     */
    public static int deleteTiming(String timingId) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        int row = 0;
        String query = "delete from mastertbl_timing where timing_id=?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, timingId);
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
     * @param timing
     * @return
     */
    public static boolean checkDuplicateEntry(String timing) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        boolean f = false;
        con = DBPool.getInstance().getConn();
        String quesry = "SELECT EXISTS(SELECT 1 FROM mastertbl_timing WHERE timing='" + timing + "')";
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
