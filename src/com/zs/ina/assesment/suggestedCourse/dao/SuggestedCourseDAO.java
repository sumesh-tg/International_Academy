/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.assesment.suggestedCourse.dao;

import com.zs.ina.common.constants.TableNames;
import com.zs.ina.utility.ClosingResourcesInDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import zs.com.ina.db.mysql.pool.DBPool;

/**
 *
 * @author zoft
 */
public class SuggestedCourseDAO {

    static Connection con = null;
    static Statement stmt = null;
    static ResultSet rs = null;

    /**
     *
     * @param value
     * @return
     */
    public static List<String> getLocation(String value) {
        List<String> location = new ArrayList<>();
        try {

            con = DBPool.getInstance().getConn();
            //  JOptionPane.showMessageDialog(null, id);
            String query = "select DISTINCT location from mastertbl_suggested_course where country='" + value + "'";

            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
//                 String[] parts = rs.getString(1).split(",");
//String part1 = parts[0]; // 004
//String part2 = parts[1]; // 034556
                location.add(rs.getString(1));
            }

        } catch (SQLException ex) {
            Logger.getLogger(SuggestedCourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return location;
    }

    /**
     *
     * @param value
     * @return
     */
    public static List<String> getUniversities(String value) {
        List<String> university = new ArrayList<>();
        try {

            con = DBPool.getInstance().getConn();
            //  JOptionPane.showMessageDialog(null, id);
            String query = "select DISTINCT university from mastertbl_suggested_course where location='" + value + "'";

            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
//                 String[] parts = rs.getString(1).split(",");
//String part1 = parts[0]; // 004
//String part2 = parts[1]; // 034556
                university.add(rs.getString(1));
            }

        } catch (SQLException ex) {
            Logger.getLogger(SuggestedCourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return university;
    }

    /**
     *
     * @param value
     * @return
     */
    public static List<String> getLevels(String value) {
        List<String> levels = new ArrayList<>();
        try {

            con = DBPool.getInstance().getConn();
            //  JOptionPane.showMessageDialog(null, id);
            String query = "select DISTINCT level from mastertbl_suggested_course where university='" + value + "'";

            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
//                 String[] parts = rs.getString(1).split(",");
//String part1 = parts[0]; // 004
//String part2 = parts[1]; // 034556
                levels.add(rs.getString(1));
            }

        } catch (SQLException ex) {
            Logger.getLogger(SuggestedCourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return levels;
    }

    /**
     *
     * @param country
     * @param location
     * @param university
     * @param level
     * @return
     */
    public static String getCourse(String country, String location, String university, String level) {
        String course = null;
        try {
            con = DBPool.getInstance().getConn();

            String query = "select DISTINCT course from mastertbl_suggested_course where level='" + level + "' "
                    + "and country='" + country + "' and location='" + location + "' and university='" + university + "'";

            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                course = rs.getString(1)+",";
            }

        } catch (SQLException ex) {
            Logger.getLogger(SuggestedCourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("........."+course);
        return course;
    }
    
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(">>>"+getCourse("Australia", "saaa", "a", "Bachelor"));
    }

    int ENQUIRY_ID;

    /**
     *
     * @param id
     * @return
     */
    public static List<String> getCountry(String id) {
        List<String> country = new ArrayList<>();
        try {

            con = DBPool.getInstance().getConn();
           // JOptionPane.showMessageDialog(null, id);
            String query = "select DISTINCT study from "+TableNames.TABLE_ENQ_DETAILS+" where enquiry_id='" + id + "'";

            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
//                 String[] parts = rs.getString(1).split(",");
//String part1 = parts[0]; // 004
//String part2 = parts[1]; // 034556
                country.add(rs.getString(1));
            }

        } catch (SQLException ex) {
            Logger.getLogger(SuggestedCourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return country;
    }

}
