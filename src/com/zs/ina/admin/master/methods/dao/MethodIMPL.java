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
package com.zs.ina.admin.master.methods.dao;

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
public class MethodIMPL implements MethodDAO {

    Logger logger = Logger.getLogger(MethodIMPL.class);
    ObservableList<MethodBEAN> retrieveMethodsList = FXCollections.observableArrayList();
    ObservableList<MethodPOJO> retrieveMethodsOnlyList = FXCollections.observableArrayList();

    /**
     *
     * @param methodBEAN
     * @return
     */
    @Override
    public int insertMethod(MethodBEAN methodBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        try {

            con = DBPool.getInstance().getConn();
            String query_insert = "insert into mastertbl_enquiry_methods(method_name) values(?)";
            PreparedStatement ps = con.prepareStatement(query_insert);
            ps.setString(1, methodBEAN.getMethodName());
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
     * @param methodBEAN
     * @return
     */
    @Override
    public int updateMethod(MethodBEAN methodBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        try {

            con = DBPool.getInstance().getConn();
            String query = "update mastertbl_enquiry_methods set method_name=? where enquiry_method_id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, methodBEAN.getMethodName());
            ps.setString(2, methodBEAN.getMethodId());
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
     * @param methodBEAN
     * @return
     */
    @Override
    public int updateMethodType(MethodBEAN methodBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        int row1 = 0;
        int row2 = 0;
        try {

            con = DBPool.getInstance().getConn();
            String query = "update mastertbl_enquiry_methods set method_type=? where enquiry_method_id=?";
            ps = con.prepareStatement(query);
            ps.setString(1, methodBEAN.getMethodType());
            ps.setString(2, methodBEAN.getMethodId());
            row1 = ps.executeUpdate();
            if (row1 > 0) {
                String query_update = "update mastertbl_enquiry_methods set method_type=? where enquiry_method_id <> ?";
                ps = con.prepareStatement(query_update);
                ps.setString(1, null);
                ps.setString(2, methodBEAN.getMethodId());
                row2 = ps.executeUpdate();
            }

        } catch (SQLException sqle) {
            logger.error(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }

        return row2;
    }

    /**
     *
     * @return
     */
    @Override
    public ObservableList<MethodBEAN> retrieveMethods() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {

            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();
            String query = "SELECT * FROM mastertbl_enquiry_methods";
            rs = stmt.executeQuery(query);
            retrieveMethodsList.clear();
            while (rs.next()) {
                MethodBEAN methodBEAN = new MethodBEAN();
                methodBEAN.setMethodId(rs.getString("enquiry_method_id"));
                methodBEAN.setMethodName(rs.getString("method_name"));
                if (rs.getString("method_type") == null) {
                    methodBEAN.setMethodType("");
                } else {
                    methodBEAN.setMethodType(rs.getString("method_type"));
                }

                retrieveMethodsList.add(methodBEAN);
            }

        } catch (SQLException sqle) {
            logger.error(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return retrieveMethodsList;
    }

    /**
     *
     * @return
     */
    @Override
    public ObservableList<MethodPOJO> retrieveMethodsOnly() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {

            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();
            String query = "SELECT\n"
                    + "	enquiry_method_id,\n"
                    + "	method_name \n"
                    + "FROM\n"
                    + "	mastertbl_enquiry_methods";
            rs = stmt.executeQuery(query);
            retrieveMethodsOnlyList.clear();
            while (rs.next()) {
                MethodPOJO methodPOJO = new MethodPOJO();
                methodPOJO.setMethodId(rs.getString("enquiry_method_id"));
                methodPOJO.setMethod(rs.getString("method_name"));
                retrieveMethodsOnlyList.add(methodPOJO);
            }

        } catch (SQLException sqle) {
            logger.error(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return retrieveMethodsOnlyList;

    }

    /**
     *
     * @return
     */
    public static List<String> getAllEnquiryMethods() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<String> allMethodsList = new ArrayList<>();
        String query = "select method_name from mastertbl_enquiry_methods";
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                allMethodsList.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return allMethodsList;
    }
}
