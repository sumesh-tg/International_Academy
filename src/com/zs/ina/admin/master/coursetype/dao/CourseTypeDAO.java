/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.admin.master.coursetype.dao;

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
public class CourseTypeDAO {

    //Method for get all languages

    /**
     *
     * @return
     */
    public static List<String> getAllCourseType() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<String> allMigrationCategory = new ArrayList<>();
        try {
            String query = "select course_type from mastertbl_course_type";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                allMigrationCategory.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return allMigrationCategory;
    }

    /**
     *
     * @return
     */
    public static ObservableList<CourseTypeBEAN> getCourseTypeDetails() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        ObservableList<CourseTypeBEAN> courseTypeBEANs = FXCollections.observableArrayList();
        try {
            String query = "select course_id,course_type from mastertbl_course_type";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                CourseTypeBEAN courseTypeBEAN = new CourseTypeBEAN();
                courseTypeBEAN.setId(rs.getString(1));
                courseTypeBEAN.setCourseType(rs.getString(2));
                courseTypeBEANs.add(courseTypeBEAN);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return courseTypeBEANs;
    }

    //Method to insert new language

    /**
     *
     * @param courseTypeBEAN
     * @return
     */
    public static int insertCourseType(CourseTypeBEAN courseTypeBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        int row = 0;
        String query = "insert into mastertbl_course_type(course_type,course_id) values(?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, courseTypeBEAN.getCourseType());
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

    //Method to update language details

    /**
     *
     * @param courseTypeBEAN
     * @return
     */
    public static int updateCourseType(CourseTypeBEAN courseTypeBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        int row = 0;
        String query = "update mastertbl_course_type set course_type=? where course_id=?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, courseTypeBEAN.getCourseType());
            ps.setString(2, courseTypeBEAN.getId());
            row = ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{null}, new ResultSet[]{null}, stackTraceElements);
        }
        return row;
    }

    //Method to delete a language

    /**
     *
     * @param courseTypeId
     * @return
     */
    public static int deleteCourseType(String courseTypeId) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        int row = 0;
        String query = "delete from mastertbl_course_type where course_id=?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, courseTypeId);
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
     * @param courseType
     * @return
     */
    public static boolean checkDuplicateEntry(String courseType) {
        
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        boolean f = false;
        con = DBPool.getInstance().getConn();
        String quesry = "SELECT EXISTS(SELECT 1 FROM mastertbl_course_type WHERE course_type='" + courseType + "')";
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
