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
package com.zs.ina.admin.master.sources.dao;

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
public class SourceIMPL implements SourceDAO {

    Logger logger = Logger.getLogger(SourceIMPL.class);
    ObservableList<SourceBEAN> retrieveSourcesList = FXCollections.observableArrayList();
    ObservableList<SourcePOJO> retrieveSourcesOnlyList = FXCollections.observableArrayList();

    /**
     *
     * @param sourceBEAN
     * @return
     */
    @Override
    public int insertSource(SourceBEAN sourceBEAN) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        try {

            con = DBPool.getInstance().getConn();
            String query = "insert into enquiry_sources(source_name) values(?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, sourceBEAN.getSourceName());
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
     * @param sourceBEAN
     * @return
     */
    @Override
    public int updateSource(SourceBEAN sourceBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        try {

            con = DBPool.getInstance().getConn();
            String query = "update enquiry_sources set source_name=? where enquiry_source_id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, sourceBEAN.getSourceName());
            ps.setString(2, sourceBEAN.getEnquirySourceId());
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
    public ObservableList<SourceBEAN> retrieveSources() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {

            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();
            String query = "SELECT\n"
                    + "	enquiry_source_id,\n"
                    + "	source_name,\n"
                    + "	source_type\n"
                    + "FROM\n"
                    + "	enquiry_sources";
            rs = stmt.executeQuery(query);
            retrieveSourcesList.clear();
            while (rs.next()) {
                SourceBEAN sourceBEAN = new SourceBEAN();
                sourceBEAN.setEnquirySourceId(rs.getString("enquiry_source_id"));
                sourceBEAN.setSourceName(rs.getString("source_name"));
                if (rs.getString("source_type") == null) {
                    sourceBEAN.setSourceType("");
                } else {
                    sourceBEAN.setSourceType(rs.getString("source_type"));
                }
                retrieveSourcesList.add(sourceBEAN);
            }

        } catch (SQLException sqle) {
            logger.error(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return retrieveSourcesList;
    }

    /**
     *
     * @return
     */
    @Override
    public ObservableList<SourcePOJO> retrieveSourcesOnly() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {

            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();
            String query = "SELECT\n"
                    + "	enquiry_source_id,\n"
                    + "	source_name \n"
                    + "FROM\n"
                    + "	enquiry_sources";
            rs = stmt.executeQuery(query);
            retrieveSourcesOnlyList.clear();
            while (rs.next()) {
                SourcePOJO sourcePOJO = new SourcePOJO();
                sourcePOJO.setSourceId(rs.getString("enquiry_source_id"));
                sourcePOJO.setSource(rs.getString("source_name"));
                retrieveSourcesOnlyList.add(sourcePOJO);
            }

        } catch (SQLException sqle) {
            logger.error(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return retrieveSourcesOnlyList;

    }

    /**
     *
     * @param sourceBEAN
     * @return
     */
    @Override
    public int updateSourceType(SourceBEAN sourceBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        int row1 = 0;
        int row2 = 0;
        try {

            con = DBPool.getInstance().getConn();
            String query = "update enquiry_sources set source_type=? where enquiry_source_id=?";
            ps = con.prepareStatement(query);
            ps.setString(1, sourceBEAN.getSourceType());
            ps.setString(2, sourceBEAN.getEnquirySourceId());
            row1 = ps.executeUpdate();
            if (row1 > 0) {
                String query_update = "update enquiry_sources set source_type=? where enquiry_source_id <> ?";
                ps = con.prepareStatement(query_update);
                ps.setString(1, null);
                ps.setString(2, sourceBEAN.getEnquirySourceId());
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
    public static List<String> getAllSources() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<String> allSourcesList = new ArrayList<>();
        String query = "select source_name from enquiry_sources";
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                allSourcesList.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return allSourcesList;
    }
   


}
