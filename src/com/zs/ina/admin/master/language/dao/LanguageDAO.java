/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.admin.master.language.dao;

import com.zs.ina.admin.master.institute.dao.InstituteBean;
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
public class LanguageDAO {

    //Method to insert new language

    /**
     *
     * @param languageBEAN
     * @return
     */
    public static int insertLanguage(LanguageBEAN languageBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        int row = 0;
        String query = "insert into mastertbl_language(language,language_id) values(?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, languageBEAN.getLanguage());
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

    //Method to update language details

    /**
     *
     * @param languageBEAN
     * @return
     */
    public static int updateLanguage(LanguageBEAN languageBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        int row = 0;
        String query = "update mastertbl_language set language=? where language_id=?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, languageBEAN.getLanguage());
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

    //Method for get all languages

    /**
     *
     * @return
     */
    public static List<String> getAllLanguages() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<String> allBranches = new ArrayList<>();
        try {
            String query = "select language from mastertbl_language";
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
//Method to get language details

    /**
     *
     * @return
     */
    public static ObservableList<LanguageBEAN> getLanguagetails() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        ObservableList<LanguageBEAN> languageBEANs = FXCollections.observableArrayList();
        try {
            String query = "select language_id,language from mastertbl_language";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                LanguageBEAN languageBEAN = new LanguageBEAN();
                languageBEAN.setId(rs.getString(1));
                languageBEAN.setLanguage(rs.getString(2));
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

    //Method to delete a language

    /**
     *
     * @param languageId
     * @return
     */
    public static int deleteLanguage(String languageId) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        int row = 0;
        String query = "delete from mastertbl_language where language_id=?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, languageId);
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
     * @param language
     * @return
     */
    public static boolean checkDuplicateEntry(String language) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        boolean f = false;
        con = DBPool.getInstance().getConn();
        String quesry = "SELECT EXISTS(SELECT 1 FROM mastertbl_language WHERE language='" + language + "')";
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
