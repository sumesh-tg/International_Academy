/*
 * Copyright ZoftSolutions(C) 2016 SUMESH T.G <ZoftSolutions>
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
 *  Developed by ZoftSolutions (2015) Company.
 */
package com.zs.ina.admin.master.locations.dao;

import com.zs.ina.common.UiiDGenerator;
import com.zs.ina.utility.ClosingResourcesInDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import zs.com.ina.db.mysql.pool.DBPool;

/**
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class LocationsIMPL implements LocationsDAO {

    String TABLE_COUNTRY_LOCATION = "mastertbl_location";

    /**
     *
     * @param country
     * @param location
     */
    @Override
    public void insertLocation(String country, String location) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = DBPool.getInstance().getConn();
            String insertQuery = "INSERT INTO " + TABLE_COUNTRY_LOCATION + " (location_id, location, country)\n"
                    + "SELECT * FROM (SELECT 'cn_" + UiiDGenerator.getUIID8() + "', '" + location + "', '" + country + "') AS tmp\n"
                    + "WHERE NOT EXISTS (\n"
                    + "    SELECT country FROM mastertbl_location WHERE country = '" + country + "' and location='" + location + "'\n"
                    + ") LIMIT 1;";
            stmt = con.createStatement();
            stmt.execute(insertQuery);

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
    }

    /**
     *
     * @param id
     */
    @Override
    public void deleteLocation(String id) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = DBPool.getInstance().getConn();
            String insertQuery = "DELETE  FROM `mastertbl_location` WHERE location_id='" + id + "'";
            stmt = con.createStatement();
            stmt.execute(insertQuery);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
    }

    /**
     *
     * @param country
     * @param location
     */
    @Override
    public void updateLocation(String country, String location) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @return
     */
    @Override
    public ObservableList<LocationsPOJO> retrieveAllLocations() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ObservableList<LocationsPOJO> ObsLocations = FXCollections.observableArrayList();
        try {
            con = DBPool.getInstance().getConn();

            String insertQuery = "SELECT * FROM " + TABLE_COUNTRY_LOCATION + " where location <> '' ORDER BY country ASC ;";
            stmt = con.createStatement();
            rs = stmt.executeQuery(insertQuery);
            while (rs.next()) {
                LocationsPOJO lpojo = new LocationsPOJO();
                lpojo.setId(rs.getString("location_id"));
                lpojo.setCountry(rs.getString("country"));
                lpojo.setLocation(rs.getString("location"));
                ObsLocations.add(lpojo);
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return ObsLocations;
    }

    /**
     *
     * @param country
     * @return
     */
    @Override
    public ObservableList retrievLocationsByCountry(String country) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ObservableList<String> ObsLocation = FXCollections.observableArrayList();
        try {
            con = DBPool.getInstance().getConn();

            String insertQuery = " SELECT DISTINCT location FROM " + TABLE_COUNTRY_LOCATION + " where country = '" + country + "' AND location <> '' ";
            stmt = con.createStatement();
            rs = stmt.executeQuery(insertQuery);
            while (rs.next()) {
                ObsLocation.add(rs.getString("location"));
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return ObsLocation;
    }

}
