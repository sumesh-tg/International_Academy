/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.admin.master.qualification.dao;

import com.zs.ina.common.UiiDGenerator;
import com.zs.ina.context.Context;
import com.zs.ina.utility.ClosingResourcesInDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import zs.com.ina.db.mysql.pool.DBPool;

/**
 *
 * @author zoft
 */
public class QualificationDao {

    /**
     *
     * @return
     */
    public static ObservableList getQualificationLevel() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        ObservableList programLevelList = FXCollections.observableArrayList();
        con = DBPool.getInstance().getConn();
        try {
            String query = "select DISTINCT program_level  from mastertbl_program_level ";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                programLevelList.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return programLevelList;
    }

    /**
     *
     * @return
     */
    public static int insert() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        int row = 0;
        String query = "insert into mastertbl_program_field(program_level_id,program_field,program_field_id)"
                + " values((select program_level_id from mastertbl_program_level where program_level=?),?,?) ";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, Context.getInstance().currentProfile().getQualificationBean().getQualificationLevel());
            ps.setString(2, Context.getInstance().currentProfile().getQualificationBean().getQualificationField());
            ps.setString(3,UiiDGenerator.getUIID8());
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
    public static ObservableList<QualificationBean> getQualificationFieldDetails() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        ObservableList<QualificationBean> functionalAreaList = FXCollections.observableArrayList();
        try {
            String query = "select p.program_field_id,p1.program_level,p.program_field from mastertbl_program_field AS p INNER JOIN mastertbl_program_level AS p1 ON p.program_level_id=p1.program_level_id";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                QualificationBean qualificationBean = new QualificationBean();
                qualificationBean.setId(rs.getString(1));
                qualificationBean.setQualificationLevel(rs.getString(2));
                qualificationBean.setQualificationField(rs.getString(3));
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
     * @param qualificationId
     * @return
     */
    public static int update(String qualificationId) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        int row = 0;
        String query = "update mastertbl_program_field set program_field=?,program_level_id=(select program_level_id from mastertbl_program_level where program_level=?) where program_field_id=?";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, Context.getInstance().currentProfile().getQualificationBean().getQualificationField());
            ps.setString(2, Context.getInstance().currentProfile().getQualificationBean().getQualificationLevel());
            ps.setString(3, qualificationId);
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
        String query = "delete from mastertbl_program_field  where program_field_id=?";

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
     * @param qualificationBean
     * @return
     */
    public static boolean checkDuplicateEntry(QualificationBean qualificationBean) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        boolean f = false;
        con = DBPool.getInstance().getConn();
        String quesry = "SELECT EXISTS(SELECT 1 FROM mastertbl_program_field pf,mastertbl_program_level pl WHERE pf.program_field='" + qualificationBean.getQualificationField() + "'"
                + " and (pf.program_level_id=pl.program_level_id) and pl.program_level='" + qualificationBean.getQualificationLevel() + "')";
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
