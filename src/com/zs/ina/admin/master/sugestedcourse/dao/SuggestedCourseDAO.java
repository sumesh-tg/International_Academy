/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.admin.master.sugestedcourse.dao;

import com.zs.ina.common.UiiDGenerator;
import com.zs.ina.context.Context;
import com.zs.ina.utility.ClosingResourcesInDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import zs.com.ina.db.mysql.pool.DBPool;

/**
 *
 * @author user
 */
public class SuggestedCourseDAO {

    // Method for get all Source details 

    /**
     *
     * @return
     */
    public static List<SuggestedCoursePOJO> getSuggestedCourse() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<SuggestedCoursePOJO> courseList = new ArrayList<SuggestedCoursePOJO>();
        try {
            String query = "select * from mastertbl_suggested_course";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                SuggestedCoursePOJO coursePOJO = new SuggestedCoursePOJO();
                coursePOJO.setCourseId(rs.getString("course_id"));
                coursePOJO.setCountry(rs.getString("country"));
                coursePOJO.setUniversity(rs.getString("university"));
                coursePOJO.setLevel(rs.getString("level"));
                coursePOJO.setCourse(rs.getString("course"));
                coursePOJO.setLocation(rs.getString("location"));
                courseList.add(coursePOJO);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return courseList;
    }

    //Method to load countryList

    /**
     *
     * @return
     */
    public static List<String> getCountries() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<String> country = new ArrayList<>();

        String query = "select country from mastertbl_country";
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                country.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return country;
    }

    //Method to load countryList

    /**
     *
     * @return
     */
    public static List<String> getUniversity() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<String> universities = new ArrayList<>();
        String query = "select university from mastertbl_university";
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                universities.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return universities;
    }

    //Method for update suggested Course

    /**
     *
     * @param courseId
     * @param coursePOJO
     * @return
     */
    public static int updateSuggestedCourse(String courseId, SuggestedCoursePOJO coursePOJO) {
        int row = 0;
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        try {
            String query = "update  mastertbl_suggested_course set country=?,university=?,level=?,course=?,location=? where course_id=? ";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, coursePOJO.getCountry());
            ps.setString(2, coursePOJO.getUniversity());
            ps.setString(3, coursePOJO.getLevel());
            ps.setString(4, coursePOJO.getCourse());
            ps.setString(5, coursePOJO.getLocation());
            ps.setString(6, courseId);
            row = ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return row;
    }

    //Method for insert suggested course

    /**
     *
     * @param coursePOJO
     * @return
     */
    public static int insertSourceDetails(SuggestedCoursePOJO coursePOJO) {
        int row = 0;
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        try {
            String query = "Insert into mastertbl_suggested_course(country,university,level,course,location,course_id)values(?,?,?,?,?,?) ";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, coursePOJO.getCountry());
            ps.setString(2, coursePOJO.getUniversity());
            ps.setString(3, coursePOJO.getLevel());
            ps.setString(4, coursePOJO.getCourse());
            ps.setString(5, coursePOJO.getLocation());
            ps.setString(6, UiiDGenerator.getUIID8());
            row = ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return row;
    }

    // Method to get program level

    /**
     *
     * @return
     */
    public static List<String> getProgramLevel() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<String> programLevel = new ArrayList<>();
        String query = "select distinct(program_level) from mastertbl_program_level order by program_level";
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                programLevel.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return programLevel;
    }

    //Method to get course

    /**
     *
     * @return
     */
    public static List<String> getCourse() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<String> programcourse = new ArrayList<>();
        String query = "select distinct(program_field) from mastertbl_program_field order by program_field";
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                programcourse.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return programcourse;
    }

    //Method for delete login details

    /**
     *
     * @param courseId
     */
    public static void deleteSuggestedCourse(String courseId) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        try {
            String deleteBranchQuery = "delete from mastertbl_suggested_course where course_id='" + courseId + "'";
            stmt = con.createStatement();
            stmt.executeUpdate(deleteBranchQuery);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
    }
}
