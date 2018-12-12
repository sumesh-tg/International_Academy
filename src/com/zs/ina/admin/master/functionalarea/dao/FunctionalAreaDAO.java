/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.admin.master.functionalarea.dao;

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
public class FunctionalAreaDAO {

    //Method for insert Functional Area

    /**
     *
     * @return
     */
    public static int insertFunctionalArea() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        int row = 0;
        String query = "insert into study_work_migration_training_mstr_tbl(operation_area,functional_area,course_level)"
                + " values(?,?,?) ";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, Context.getInstance().currentProfile().getFunctionalAreaPOJO().getOperationArea());
            ps.setString(2, Context.getInstance().currentProfile().getFunctionalAreaPOJO().getFuntionalArea());
            ps.setString(3, Context.getInstance().currentProfile().getFunctionalAreaPOJO().getLevel());
            row = ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{null}, new ResultSet[]{null}, stackTraceElements);
        }
        return row;
    }
//Method for update Functional Area

    /**
     *
     * @param functionalId
     * @return
     */
    public static int updateFunctionalArea(int functionalId) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        int row = 0;
        String query = "update study_work_migration_training_mstr_tbl set operation_area=?,functional_area=?,course_level=? where id=?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, Context.getInstance().currentProfile().getFunctionalAreaPOJO().getOperationArea());
            ps.setString(2, Context.getInstance().currentProfile().getFunctionalAreaPOJO().getFuntionalArea());
            ps.setString(3, Context.getInstance().currentProfile().getFunctionalAreaPOJO().getLevel());
            ps.setInt(4, functionalId);
            row = ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{null}, new ResultSet[]{null}, stackTraceElements);
        }
        return row;
    }

    //Method for get all details from study_work_migration_training_mstr_tbl

    /**
     *
     * @return
     */
    public static List<FunctionalAreaPOJO> getAgencyDetails() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<FunctionalAreaPOJO> functionalAreaList = new ArrayList<FunctionalAreaPOJO>();
        try {
            String query = "select * from study_work_migration_training_mstr_tbl";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                FunctionalAreaPOJO functionalAreaPOJO = new FunctionalAreaPOJO();
                functionalAreaPOJO.setId(rs.getInt(1));
                functionalAreaPOJO.setOperationArea(rs.getString(2));
                functionalAreaPOJO.setFuntionalArea(rs.getString(3));
                functionalAreaPOJO.setLevel(rs.getString(4));
                functionalAreaList.add(functionalAreaPOJO);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return functionalAreaList;
    }
}
