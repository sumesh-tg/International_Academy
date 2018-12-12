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
package com.zs.ina.admin.master.purpose.dao;

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
 * @author SUMESH T.G <ZoftSolutions>
 */
public class PurposeIMPL implements PurposeDAO {

    String TABLE_PURPOSE = "mastertbl_purpose";
    static Logger logger = Logger.getLogger(PurposeIMPL.class);

    /**
     *
     * @param purposeModel
     * @return
     */
    @Override
    public int deletePurpose(PurposeModel purposeModel) {
        Connection con = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        int row = 0;
        String query = "DELETE FROM  " + TABLE_PURPOSE + " WHERE purpose=?;";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, purposeModel.getPurpose());
            row = ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());
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
    public ObservableList<PurposeModel> listPurpose() {

        ObservableList<PurposeModel> purposeModels = FXCollections.observableArrayList();
        String query = "SELECT * FROM " + TABLE_PURPOSE;
        Connection con = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        try {
            PreparedStatement ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                PurposeModel purposeModel = new PurposeModel();
                purposeModel.setPuposeId(rs.getString("purpose_id"));
                purposeModel.setPurpose(rs.getString("purpose"));
                purposeModels.add(purposeModel);
            }
        } catch (SQLException sqle) {
            logger.info(sqle.toString());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{null}, new ResultSet[]{null}, stackTraceElements);
        }
        return purposeModels;

    }

    /**
     *
     * @param purposeModel
     * @return
     */
    @Override
    public int insertPurpose(PurposeModel purposeModel) {
        Connection con = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        int row = 0;
        String query = "INSERT INTO " + TABLE_PURPOSE + " (purpose_id, purpose) VALUES (?,?);";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, "PU_" + UiiDGenerator.getUIID8());
            ps.setString(2, purposeModel.getPurpose());
            row = ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{null}, new ResultSet[]{null}, stackTraceElements);
        }
        return row;
    }

    /**
     *
     * @param purposeModel
     * @return
     */
    @Override
    public int upadatePurpose(PurposeModel purposeModel) {
        String query = "UPDATE " + TABLE_PURPOSE + " SET purpose=? WHERE purpose=?;";
        Connection con = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        int row = 0;
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, purposeModel.getPurpose());
            row = ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());
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
    public ObservableList getPurpose() {

        ObservableList purposeList = FXCollections.observableArrayList();
        String query = "SELECT * FROM " + TABLE_PURPOSE;
        Connection con = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        try {
            PreparedStatement ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                purposeList.add(rs.getString("purpose"));
            }
        } catch (SQLException sqle) {
            logger.info(sqle.toString());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{null}, new ResultSet[]{null}, stackTraceElements);
        }
        return purposeList;

    }
}
