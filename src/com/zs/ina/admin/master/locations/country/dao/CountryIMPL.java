/*
 * Copyright (C) 2016 100035
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package com.zs.ina.admin.master.locations.country.dao;

import com.zs.ina.admin.master.userlogin.dao.LoginIMPL;
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
import org.apache.log4j.Logger;
import zs.com.ina.db.mysql.pool.DBPool;

/**
 *
 * @author 100035
 */
public class CountryIMPL implements CountryDAO {

    Logger logger = Logger.getLogger(LoginIMPL.class);
    ObservableList<CountryBEAN> retrieveLocationsList = FXCollections.observableArrayList();
    ObservableList<String> retrieveCountryList = FXCollections.observableArrayList();
    ObservableList<String> retrieveStateList = FXCollections.observableArrayList();
    ObservableList<String> retrieveDistrictList = FXCollections.observableArrayList();

    /**
     *
     * @param countryBEAN
     * @return
     */
    @Override
    public int insertCountry(CountryBEAN countryBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        int row = 0;
        try {
            con = DBPool.getInstance().getConn();
            String query = "insert into country_state_district(Country) values(?)";
            ps = con.prepareStatement(query);
            ps.setString(1, countryBEAN.getCountry());
            row = ps.executeUpdate();

        } catch (SQLException sqle) {
            logger.error(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }

        return row;
    }

    /**
     *
     * @return
     */
    @Override
    public ObservableList<String> loadcmbCountryCommon() {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;

        try {

            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();
            String query = "select DISTINCT Country FROM country_state_district";
            rs = stmt.executeQuery(query);
            retrieveCountryList.clear();
            while (rs.next()) {
                retrieveCountryList.add(rs.getString("Country"));
            }

        } catch (SQLException sqle) {
            logger.error(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return retrieveCountryList;

    }

    /**
     *
     * @return
     */
    @Override
    public ObservableList<String> loadcmbDistrictDel() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;

        try {

            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();
            String query = "select DISTINCT District FROM country_state_district";
            rs = stmt.executeQuery(query);
            retrieveDistrictList.clear();
            while (rs.next()) {
                 if(rs.getString("District")!=null)
                retrieveDistrictList.add(rs.getString("District"));
            }

        } catch (SQLException sqle) {
            logger.error(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return retrieveDistrictList;
    }

    /**
     *
     * @param countryBEAN
     * @return
     */
    @Override
    public int deleteCountry(CountryBEAN countryBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;

        try {
            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();
            String query_delete_country = "delete from country_state_district where Country='" + countryBEAN.getCountry() + "'";
            row = stmt.executeUpdate(query_delete_country);
            System.out.println("query_delete_country :::" + query_delete_country);

        } catch (SQLException sqle) {
            logger.error(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return row;
    }

    /**
     *
     * @param countryBEAN
     * @return
     */
    @Override
    public int insertState(CountryBEAN countryBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        int row = 0;
        try {
            con = DBPool.getInstance().getConn();
            String query = "insert into country_state_district(Country,State) values(?,?)";
            ps = con.prepareStatement(query);
            ps.setString(1, countryBEAN.getCountry());
            ps.setString(2, countryBEAN.getState());
            row = ps.executeUpdate();

        } catch (SQLException sqle) {
            logger.error(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }

        return row;
    }

    /**
     *
     * @param countryBEAN
     * @return
     */
    @Override
    public int deleteState(CountryBEAN countryBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;

        try {
            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();
            String query_delete_state = "delete from country_state_district where State='" + countryBEAN.getState() + "'";
            row = stmt.executeUpdate(query_delete_state);
            System.out.println("query_delete_state :::" + query_delete_state);

        } catch (SQLException sqle) {
            logger.error(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return row;
    }

    /**
     *
     * @return
     */
    @Override
    public ObservableList<String> loadcmbStateDel() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;

        try {

            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();
            String query = "select DISTINCT State FROM country_state_district";
            rs = stmt.executeQuery(query);
            retrieveStateList.clear();
            while (rs.next()) {
                if(rs.getString("State")!=null)
                retrieveStateList.add(rs.getString("State"));
            }

        } catch (SQLException sqle) {
            logger.error(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return retrieveStateList;
    }

    /**
     *
     * @param country
     * @return
     */
    @Override
    public ObservableList<String> loadcmbStateChoose(String country) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;

        try {

            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();
            String query = "select DISTINCT State FROM country_state_district where country='" + country + "'";
            rs = stmt.executeQuery(query);
            retrieveStateList.clear();
            while (rs.next()) {
                 if(rs.getString("State")!=null)
                retrieveStateList.add(rs.getString("State"));
            }

        } catch (SQLException sqle) {
            logger.error(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return retrieveStateList;

    }

    /**
     *
     * @param countryBEAN
     * @return
     */
    @Override
    public int insertDistrict(CountryBEAN countryBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        int row = 0;
        try {
            con = DBPool.getInstance().getConn();
            String query = "insert into country_state_district(Country,State,District) values(?,?,?)";
            ps = con.prepareStatement(query);
            ps.setString(1, countryBEAN.getCountry());
            ps.setString(2, countryBEAN.getState());
            ps.setString(3, countryBEAN.getDistrict());
            row = ps.executeUpdate();

        } catch (SQLException sqle) {
            logger.error(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }

        return row;
    }

    /**
     *
     * @param countryBEAN
     * @return
     */
    @Override
    public int deleteDistrict(CountryBEAN countryBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;

        try {
            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();
            String query_delete_district = "delete from country_state_district where District='" + countryBEAN.getDistrict() + "'";
            row = stmt.executeUpdate(query_delete_district);
            System.out.println("query_delete_state :::" + query_delete_district);

        } catch (SQLException sqle) {
            logger.error(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return row;
    }

    /**
     *
     * @return
     */
    @Override
    public ObservableList<CountryBEAN> retrieveLocations() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;

        try {

            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();
            String query = "SELECT\n"
                    + "	*\n"
                    + "FROM\n"
                    + "	country_state_district\n"
                    + "WHERE\n"
                    + "	Country <> ''\n"
                    + "AND State <> ''\n"
                    + "AND District <> ''";
            rs = stmt.executeQuery(query);
            retrieveLocationsList.clear();
            while (rs.next()) {
                CountryBEAN countryBEAN=new CountryBEAN();
                countryBEAN.setCountry(rs.getString("Country"));
                countryBEAN.setState(rs.getString("State"));
                countryBEAN.setDistrict(rs.getString("District"));
                retrieveLocationsList.add(countryBEAN);
            }

        } catch (SQLException sqle) {
            logger.error(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return retrieveLocationsList;

    }

    /**
     *
     * @return
     */
    public static List<String> getAllCountries() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<String> allCountriesList = new ArrayList<>();
        String query = "select distinct Country from country_state_district";
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                allCountriesList.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return allCountriesList;
    }

}
