/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.admin.master.country.dao;

import com.zs.ina.common.UiiDGenerator;
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
public class CountryDAO {

    //Method for get all languages

    /**
     *
     * @return
     */
    public static List<CountryBEAN> getAllCountries() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<CountryBEAN> allCountries = new ArrayList<>();
        try {
            String workQuery = "select 'Work' as Spec ,country,id FROM work_country\n"
                    + "UNION\n"
                    + "Select 'Study' as Spec,country,id from study_country\n"
                    + "UNION\n"
                    + "Select 'Migration' as Spec,country,id FROM migration_country";
            stmt = con.createStatement();

            rs = stmt.executeQuery(workQuery);

            while (rs.next()) {
                CountryBEAN countryBEAN = new CountryBEAN();
                countryBEAN.setCountryName(rs.getString(2));
                countryBEAN.setSpecification(rs.getString(1));
                countryBEAN.setId(rs.getString(3));
                allCountries.add(countryBEAN);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return allCountries;
    }

    //Method to insert new language

    /**
     *
     * @param countryBEAN
     * @return
     */
    public static int insertCountries(CountryBEAN countryBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        int row = 0;
        String query = "";
        if (countryBEAN.getSpecification().equals("Study")) {
            query = "insert into  study_country (country,id) values(?,? )";
        } else if (countryBEAN.getSpecification().equals("Work")) {
            query = "insert into  work_country (country,id) values(?,?)";
        } else if (countryBEAN.getSpecification().equals("Migration")) {
            query = "insert into  migration_country (country,id) values(?,?)";
        }
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, countryBEAN.getCountryName());
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
     * @param countryBEAN
     * @return
     */
    public static int updateCountry(CountryBEAN countryBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        int row = 0;
        String query = "";
        if (countryBEAN.getSpecification().equals("Study")) {
            query = "Update study_country set country=? where id=?";
        } else if (countryBEAN.getSpecification().equals("Work")) {
            query = "update work_country set country=? where id=?";
        } else if (countryBEAN.getSpecification().equals("Migration")) {
            query = "update migration_country set country=? where id=?";
        }

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, countryBEAN.getCountryName());
            ps.setString(2, countryBEAN.getId());
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
     * @param countryBEAN
     * @return
     */
    public static int deleteCountry(CountryBEAN countryBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        int row = 0;
        String query = "";
        if (countryBEAN.getSpecification().equals("Study")) {
            query = "delete from study_country where id=?";
        } else if (countryBEAN.getSpecification().equals("Work")) {
            query = "delete from work_country  where id=?";
        } else if (countryBEAN.getSpecification().equals("Migration")) {
            query = "delete from migration_country  where id=?";
        }
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, countryBEAN.getId());
            row = ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{null}, new ResultSet[]{null}, stackTraceElements);
        }
        return row;
    } //Check for duplicate entry

    /**
     *
     * @param countryBEAN
     * @return
     */
    public static boolean checkDuplicateEntry(CountryBEAN countryBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        boolean f = false;
        String query = "";
        con = DBPool.getInstance().getConn();
        if (countryBEAN.getSpecification().equals("Study")) {
            query = "SELECT EXISTS(SELECT 1 FROM study_country WHERE country='" + countryBEAN.getCountryName() + "')";
        } else if (countryBEAN.getSpecification().equals("Work")) {
            query = "SELECT EXISTS(SELECT 1 FROM work_country WHERE country='" + countryBEAN.getCountryName() + "')";
        } else if (countryBEAN.getSpecification().equals("Migration")) {
            query = "SELECT EXISTS(SELECT 1 FROM migration_country WHERE country='" + countryBEAN.getCountryName() + "')";
        }

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
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

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        List<CountryBEAN> allCountries = getAllCountries();
    }
}
