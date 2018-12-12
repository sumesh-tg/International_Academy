/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.admin.master.examboard.dao;

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
 * @author zoft
 */
public class ExamBoardDao {

    /**
     *
     * @return
     */
    public static List<String> getAllBoards() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<String> allBranches = new ArrayList<>();
        try {
            String query = "select exam_board from mastertbl_exam_board";
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

    /**
     *
     * @return
     */
    public static List<ExamBoardBean> getBoarddetails() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        ObservableList<ExamBoardBean> functionalAreaList = FXCollections.observableArrayList();
        try {
            String query = "select exam_board_id,exam_board from mastertbl_exam_board";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                ExamBoardBean qualificationBean = new ExamBoardBean();
                qualificationBean.setId(rs.getString(1));
                qualificationBean.setExamBoard(rs.getString(2));
                functionalAreaList.add(qualificationBean);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return functionalAreaList;
    }

    /**
     *
     * @param qualificationBean
     * @return
     */
    public static int insert(ExamBoardBean qualificationBean) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        int row = 0;
        String query = "insert into mastertbl_exam_board(exam_board,exam_board_id) values(?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, qualificationBean.getExamBoard());
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
     * @param qualificationId
     * @param qualificationBean
     * @return
     */
    public static int update(String qualificationId, ExamBoardBean qualificationBean) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        int row = 0;
        String query = "update mastertbl_exam_board set exam_board=? where exam_board_id=?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, qualificationBean.getExamBoard());
            ps.setString(2, qualificationId);
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
     * @param qualificationId
     * @return
     */
    public static int delete(String qualificationId) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        int row = 0;
        String query = "delete from mastertbl_exam_board where exam_board_id=?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, qualificationId);
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
     * @param board
     * @return
     */
    public static boolean checkDuplicateEntry(String board) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        boolean f = false;
        con = DBPool.getInstance().getConn();
        String quesry = "SELECT EXISTS(SELECT 1 FROM mastertbl_exam_board WHERE exam_board='" + board + "')";
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
