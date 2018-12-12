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
package com.zs.ina.admin.master.relationship.dao;

import com.zs.ina.admin.master.qualificationlevel.dao.LevelBEAN;
import com.zs.ina.admin.master.qualificationlevel.dao.LevelIMPL;
import com.zs.ina.common.UiiDGenerator;
import com.zs.ina.utility.ClosingResourcesInDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.log4j.Logger;
import zs.com.ina.db.mysql.pool.DBPool;

/**
 *
 * @author 100035
 */
public class RelationIMPL implements RelationDAO {

    Logger logger = Logger.getLogger(LevelIMPL.class);
    ObservableList<RelationBEAN> retrieveRelationList = FXCollections.observableArrayList();

    /**
     *
     * @param relationBEAN
     * @return
     */
    @Override
    public int insertRelationship(RelationBEAN relationBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        try {

            con = DBPool.getInstance().getConn();
            relationBEAN.setRelationshipId("REL_" + UiiDGenerator.getUIID8());
            String query_insert = "insert into mastertbl_relationship(relationship_id,relationship) values(?,?)";
            PreparedStatement ps = con.prepareStatement(query_insert);
            ps.setString(1, relationBEAN.getRelationshipId());
            ps.setString(2, relationBEAN.getRelationship());
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
     * @param relationBEAN
     * @return
     */
    @Override
    public int updateRelationship(RelationBEAN relationBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        try {

            con = DBPool.getInstance().getConn();
            String query = "update mastertbl_relationship set relationship=? where relationship_id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, relationBEAN.getRelationship());
            ps.setString(2, relationBEAN.getRelationshipId());
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
    public ObservableList<RelationBEAN> retrieveRelationships() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {

            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();
            String query = "SELECT * FROM mastertbl_relationship";
            rs = stmt.executeQuery(query);
            retrieveRelationList.clear();
            while (rs.next()) {
                RelationBEAN relationBEAN = new RelationBEAN();
                relationBEAN.setRelationshipId(rs.getString("relationship_id"));
                relationBEAN.setRelationship(rs.getString("relationship"));
                retrieveRelationList.add(relationBEAN);
            }

        } catch (SQLException sqle) {
            logger.error(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return retrieveRelationList;
    }

    /**
     *
     * @param relationBEAN
     * @return
     */
    @Override
    public int deleteRelationship(RelationBEAN relationBEAN) {
  Connection con = null;
        con = DBPool.getInstance().getConn();
        int row = 0;
        String query = "DELETE from mastertbl_relationship where relationship_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, relationBEAN.getRelationshipId());
            row = ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{null}, new ResultSet[]{null}, stackTraceElements);
        }
        return row;
    }

}
