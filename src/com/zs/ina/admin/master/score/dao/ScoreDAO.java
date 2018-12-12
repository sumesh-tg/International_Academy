/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.admin.master.score.dao;

import com.zs.ina.admin.master.othertest.dao.OtherTestBEAN;
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
public class ScoreDAO {

    //Method for get all Scores

    /**
     *
     * @return
     */
    public static List<String> getAllScores() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<String> scoreList = new ArrayList<>();
        try {
            String query = "select score from mastertbl_score_management";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                scoreList.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return scoreList;
    }

    //Method to get Other Test details

    /**
     *
     * @return
     */
    public static ObservableList<ScoreBEAN> getScoreDetails() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        ObservableList<ScoreBEAN> scoreBEANs = FXCollections.observableArrayList();
        try {
            String query = "select score_id,score from mastertbl_score_management";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                ScoreBEAN scoreBEAN = new ScoreBEAN();
                scoreBEAN.setId(rs.getString(1));
                scoreBEAN.setScore(rs.getString(2));
                scoreBEANs.add(scoreBEAN);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return scoreBEANs;
    }

    //Method to update score details

    /**
     *
     * @param scoreBEAN
     * @return
     */
    public static int updateScore(ScoreBEAN scoreBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        int row = 0;
        String query = "update mastertbl_score_management set score=? where score_id=?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, scoreBEAN.getScore());
            ps.setString(2, scoreBEAN.getId());
            row = ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{null}, new ResultSet[]{null}, stackTraceElements);
        }
        return row;
    }
    //Method to insert new language

    /**
     *
     * @param scoreBEAN
     * @return
     */
    public static int insertScore(ScoreBEAN scoreBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        int row = 0;
        String query = "insert into mastertbl_score_management(score,score_id) values(?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, scoreBEAN.getScore());
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

    //Method to delete scores

    /**
     *
     * @param scoreId
     * @return
     */
    public static int deleteScore(String scoreId) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        int row = 0;
        String query = "delete from mastertbl_score_management where score_id=?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, scoreId);
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
     * @param score
     * @return
     */
    public static boolean checkDuplicateEntry(String score) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        boolean f = false;
        con = DBPool.getInstance().getConn();
        String quesry = "SELECT EXISTS(SELECT 1 FROM mastertbl_score_management WHERE score='" + score + "')";
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
