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
package com.zs.ina.admin.master.stdisdcodes.dao;

import com.zs.ina.utility.ClosingResourcesInDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
public class StdCodesIMPL implements StdCodesDAO {

    private final String TABLE_STD_CODES = "std_code_tbl";
    private final String TABLE_ISD_CODES = "country_codes_tbl";

    /**
     *
     * @param codesBEAN
     * @return
     */
    @Override
    public int insertStdCodes(StdCodesBEAN codesBEAN) {
        Connection con = null;
        con = DBPool.getInstance().getConn();
        int row = 0;
        String query = "INSERT INTO " + TABLE_STD_CODES + " (id, place, std_code)\n"
                + "VALUES \n"
                + "	(0,'" + codesBEAN.getPlace() + "', '" + codesBEAN.getCode() + "')";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            System.out.println("Testing ..." + query);
            row = ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
         
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{null}, new ResultSet[]{null}, stackTraceElements);
        }
        return row;
    }

    /**
     *
     * @param codesBEAN
     * @return
     */
    @Override
    public int updateStdCodes(StdCodesBEAN codesBEAN) {
        Connection con = null;
        con = DBPool.getInstance().getConn();
        int row = 0;
        String query = "UPDATE " + TABLE_STD_CODES + "\n"
                + "SET \n"
                + " place = ?,\n"
                + " std_code = ?\n"
                + "WHERE\n"
                + "	(id = ?);";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, codesBEAN.getPlace());
            ps.setString(2, codesBEAN.getCode());
            ps.setString(3, codesBEAN.getId());
            row = ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{null}, new ResultSet[]{null}, stackTraceElements);
        }
        return row;
    }

    /**
     *
     * @param codesBEAN
     * @return
     */
    @Override
    public int deleteStdCodes(StdCodesBEAN codesBEAN) {
        Connection con = null;
        con = DBPool.getInstance().getConn();
        int row = 0;
        String query = "DELETE from " + TABLE_STD_CODES + " where id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, codesBEAN.getId());
            row = ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{null}, new ResultSet[]{null}, stackTraceElements);
        }
        return row;
    }

    /**
     *
     * @return
     */
    @Override
    public ObservableList<StdCodesBEAN> retrieveStdCodes() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        ObservableList<StdCodesBEAN> observableList = FXCollections.observableArrayList();
        int row = 0;
        String query = "select * from " + TABLE_STD_CODES + "";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                StdCodesBEAN bEAN = new StdCodesBEAN();
                bEAN.setId(rs.getString("id"));
                bEAN.setPlace(rs.getString("place"));
                bEAN.setCode(rs.getString("std_code"));
                observableList.add(bEAN);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{null}, new ResultSet[]{null}, stackTraceElements);
        }
        return observableList;
    }

    /**
     *
     * @param codesBEAN
     * @return
     */
    @Override
    public int insertIsdCodes(StdCodesBEAN codesBEAN) {
        Connection con = null;
        con = DBPool.getInstance().getConn();
        int row = 0;
        String query = "INSERT INTO " + TABLE_ISD_CODES + " (id, name, nicename,phonecode)\n"
                + "VALUES \n"
                + "	(?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, 0);
            ps.setString(2, codesBEAN.getPlace().toUpperCase());
            ps.setString(3, codesBEAN.getPlace());
            ps.setString(4, codesBEAN.getCode());
            System.out.println("Testing ISD ..." + query);
            row = ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{null}, new ResultSet[]{null}, stackTraceElements);
    }
        return row;
    }

    /**
     *
     * @param codesBEAN
     * @return
     */
    @Override
    public int updateIsdCodes(StdCodesBEAN codesBEAN) {
        Connection con = null;
        con = DBPool.getInstance().getConn();
        int row = 0;
        String query = "UPDATE " + TABLE_ISD_CODES + "\n"
                + "SET \n"
                + " name = ?,\n"
                + " nicename = ?,\n"
                + " phonecode = ?\n"
                + "WHERE\n"
                + "	(id = ?);";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, codesBEAN.getPlace().toUpperCase());
            ps.setString(2, codesBEAN.getPlace());
            ps.setString(3, codesBEAN.getCode());
            ps.setString(4, codesBEAN.getId());
            row = ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{null}, new ResultSet[]{null}, stackTraceElements);
    }
        return row;
    }

    /**
     *
     * @param codesBEAN
     * @return
     */
    @Override
    public int deleteIsdCodes(StdCodesBEAN codesBEAN) {
     Connection con = null;
        con = DBPool.getInstance().getConn();
        int row = 0;
        String query = "DELETE from " + TABLE_ISD_CODES + " where id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, codesBEAN.getId());
            row = ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{null}, new ResultSet[]{null}, stackTraceElements);
    }
        return row;
    }

    /**
     *
     * @return
     */
    @Override
    public ObservableList<StdCodesBEAN> retrieveIsdCodes() {
     Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        ObservableList<StdCodesBEAN> observableList = FXCollections.observableArrayList();
        int row = 0;
        String query = "select * from " + TABLE_ISD_CODES + "";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                StdCodesBEAN bEAN = new StdCodesBEAN();
                bEAN.setId(rs.getString("id"));
                bEAN.setPlace(rs.getString("nicename"));
                bEAN.setCode(rs.getString("phonecode"));
                observableList.add(bEAN);
    }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{null}, new ResultSet[]{null}, stackTraceElements);
        }
        return observableList;
    }

}
