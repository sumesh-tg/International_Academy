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
public class CountryIMPL implements CountryDAO {

    String TABLE_COUNTRY_LOCATION = "mastertbl_location";

    /**
     *
     * @param country
     */
    @Override
    public void insertCountry(String country) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = DBPool.getInstance().getConn();
            String query = "INSERT INTO " + TABLE_COUNTRY_LOCATION + " (\n"
                    + "	location_id,\n"
                    + "	location,\n"
                    + "	country\n"
                    + ")\n"
                    + "VALUES\n"
                    + "	(\n"
                    + "		'cn_" + UiiDGenerator.getUIID8() + "',\n"
                    + "		'',\n"
                    + "		'" + country + "'\n"
                    + "	);\n"
                    + "";
            String insertQuery = "INSERT INTO mastertbl_location (location_id, location, country)\n"
                    + "SELECT * FROM (SELECT 'cn_" + UiiDGenerator.getUIID8() + "', '', '" + country + "') AS tmp\n"
                    + "WHERE NOT EXISTS (\n"
                    + "    SELECT country FROM mastertbl_location WHERE country = '" + country + "' and location=''\n"
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
     * @param country
     */
    @Override
    public void deleteCountry(String country) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = DBPool.getInstance().getConn();

            String insertQuery = "DELETE FROM mastertbl_location WHERE country='" + country + "'\n"
                    + "";
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
     * @return
     */
    @Override
    public ObservableList retrieveAllCountries() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ObservableList ObsCountry = FXCollections.observableArrayList();
        try {
            con = DBPool.getInstance().getConn();

            String insertQuery = "SELECT DISTINCT\n"
                    + "	country\n"
                    + "FROM \n"
                    + TABLE_COUNTRY_LOCATION + "\n"
                    + " WHERE\n"
                    + "	location <> ''";
            stmt = con.createStatement();
            rs = stmt.executeQuery(insertQuery);
            while (rs.next()) {
                ObsCountry.add(rs.getString("country"));
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return ObsCountry;
    }

    /**
     *
     * @param country
     */
    @Override
    public void updateCountry(String country) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @return
     */
    @Override
    public ObservableList retrieveMasterAllCountries() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ObservableList ObsCountry = FXCollections.observableArrayList();
        try {
            con = DBPool.getInstance().getConn();

            String insertQuery = "SELECT DISTINCT\n"
                    + "	country\n"
                    + "FROM \n"
                    + TABLE_COUNTRY_LOCATION + "\n";
                  
            stmt = con.createStatement();
            rs = stmt.executeQuery(insertQuery);
            while (rs.next()) {
                ObsCountry.add(rs.getString("country"));
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return ObsCountry;
    }

}
