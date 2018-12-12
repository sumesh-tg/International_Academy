/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.admin.master.retrieve;

import com.zs.ina.utility.ClosingResourcesInDB;
import java.sql.Connection;
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
public class MasterCountryStateDistrictDAO {

    //Method for get distict countries from country_state_district table
    /**
     *
     * @return
     */
    public static List<String> getAllCountries() {
        List<String> countries = new ArrayList<>();
        String source = null;
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        con = DBPool.getInstance().getConn();
        try {
            String query = "Select distinct(country) from country_state_district ";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                countries.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return countries;
    }

    //Method for get all states from coutry_state_district table
    /**
     *
     * @param s
     * @return
     */
    public static List<String> getAllStates(String s) {
        List<String> states = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        con = DBPool.getInstance().getConn();
        try {
            String query = "";
            if (s == null) {
                query = "Select distinct(state) from country_state_district where Country<>'' and state<>'' order by state ";
            } else {
                query = "Select distinct(state) from country_state_district where Country='" + s.trim() + "' and state<>'' order by state ";
            }
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                states.add(rs.getString("state"));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return states;
    }

    //Method for get all states from coutry_state_district table
    /**
     *
     * @param country
     * @param like
     * @return
     */
    public static List<String> getAllSearchStates(String country, String like) {
        List<String> states = new ArrayList<>();
        String source = null;
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        con = DBPool.getInstance().getConn();
        try {
            String query = "Select distinct(state) from country_state_district where Country='" + country.trim() + "' and state like '" + like.trim() + "%'  ";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                states.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return states;
    }

    //Method for get all states from coutry_state_district table
    /**
     *
     * @param s
     * @param dist
     * @return
     */
    public static List<String> getAllSistrictSearch(String s, String dist) {
        List<String> states = new ArrayList<>();
        String source = null;
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        con = DBPool.getInstance().getConn();
        try {
            String query = "Select distinct(district) from country_state_district where district like '" + s.trim() + "%' and state='" + dist + "' ";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                states.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return states;
    }

    //Method for get all states from coutry_state_district table
    /**
     *
     * @param state
     * @return
     */
    public static List<String> getAllDistricts(String state) {
        List<String> districts = new ArrayList<>();
        String source = null;
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        con = DBPool.getInstance().getConn();
        try {
            String query = "Select distinct(district) from country_state_district where country='India' and state='" + state + "' order by district ";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                districts.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return districts;
    }

    /**
     *
     * @param state
     * @param country
     * @return
     */
    public static List<String> getAllDistricts(String state, String country) {
        List<String> districts = new ArrayList<>();
        String source = null;
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        con = DBPool.getInstance().getConn();
        try {
            String query = "Select distinct(district) from country_state_district where country='" + country + "' and state='" + state + "' order by district ";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                districts.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return districts;
    }

}
