/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.admin.master.migrationcategory.dao;

import com.zs.ina.admin.master.language.dao.LanguageBEAN;
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
public class MigrationDAO {

    //Method for get all languages

    /**
     *
     * @return
     */
    public static List<String> getAllMigrationCategory() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<String> allMigrationCategory = new ArrayList<>();
        try {
            String query = "select migrate_category from mastertbl_migration_category";
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
    public static ObservableList<MigrationBEAN> getMigrationCategoryDetails() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        ObservableList<MigrationBEAN> migrationBEANs = FXCollections.observableArrayList();
        try {
            String query = "select migrate_category_id,migrate_category from mastertbl_migration_category";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                MigrationBEAN migrationBEAN = new MigrationBEAN();
                migrationBEAN.setId(rs.getString(1));
                migrationBEAN.setMigrattionCategory(rs.getString(2));
                migrationBEANs.add(migrationBEAN);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return migrationBEANs;
    }

    //Method to insert new language

    /**
     *
     * @param migrationBEAN
     * @return
     */
    public static int insertMigrationCategory(MigrationBEAN migrationBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        int row = 0;
        String query = "insert into mastertbl_migration_category(migrate_category,migrate_category_id) values(?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, migrationBEAN.getMigrattionCategory());
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
     * @param migrationBEAN
     * @return
     */
    public static int updateMigrationCategory(MigrationBEAN migrationBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        int row = 0;
        String query = "update mastertbl_migration_category set migrate_category=? where migrate_category_id=?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, migrationBEAN.getMigrattionCategory());
            ps.setString(2, migrationBEAN.getId());
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
     * @param migrationId
     * @return
     */
    public static int deleteMigrationCategory(String migrationId) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        int row = 0;
        String query = "delete from mastertbl_migration_category where migrate_category_id=?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, migrationId);
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
     * @param migrateCategory
     * @return
     */
    public static boolean checkDuplicateEntry(String migrateCategory) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        boolean f = false;
        con = DBPool.getInstance().getConn();
        String quesry = "SELECT EXISTS(SELECT 1 FROM mastertbl_migration_category WHERE migrate_category='" + migrateCategory + "')";
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
