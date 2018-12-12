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
package com.zs.ina.admin.master.payment.dao;

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
public class PaymentIMPL implements PaymentDAO, MandatoryDAO {

    /* ========= Add Payment Head Implementation part ================= */
    Logger logger = Logger.getLogger(PaymentIMPL.class);
    ObservableList<PaymentPOJO> retrievePaymentsList = FXCollections.observableArrayList();
    ObservableList<PaymentBEAN> retrievePaymentHeadsList = FXCollections.observableArrayList();
    final String TABLE_MSTR_PAYMNT_MANDATORY = "master_payment_mandatory";
    ObservableList<PaymentBEAN> retrievePaymentHeadsAmountList = FXCollections.observableArrayList();

    /**
     *
     * @param paymentBEAN
     * @return
     */
    @Override
    public int insertPaymentHead(PaymentBEAN paymentBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        try {

            con = DBPool.getInstance().getConn();
            String query_insert = "insert into master_payment_heads(head_id,head,enable) values(?,?,'y')";
            PreparedStatement ps = con.prepareStatement(query_insert);
            ps.setString(1, "0");
            ps.setString(2, paymentBEAN.getHead());
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

//    @Override
//    public int updatePaymentHead(PaymentBEAN paymentBEAN) {
//        Connection con = null;
//        Statement stmt = null;
//        ResultSet rs = null;
//        int row = 0;
//        try {
//
//            con = DBPool.getInstance().getConn();
//            String query = "update master_payment_heads set head=? where head_id=?";
//            PreparedStatement ps = con.prepareStatement(query);
//            ps.setString(1, paymentBEAN.getHead());
//            ps.setString(2, paymentBEAN.getHead_id());
//            row = ps.executeUpdate();
//
//        } catch (SQLException sqle) {
//            logger.error(sqle.toString());
//            sqle.printStackTrace();
//        } finally {
//            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
//            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
//        }
//
//        return row;
//    }

    /**
     *
     * @param paymentBEAN
     * @return
     */
    @Override
    public int disablePaymentHead(PaymentBEAN paymentBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        try {
            con = DBPool.getInstance().getConn();
            String query = "UPDATE master_payment_heads\n"
                    + "SET ENABLE = ?\n"
                    + "WHERE\n"
                    + "	head_id = ? ";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, paymentBEAN.getEnable());
            ps.setString(2, paymentBEAN.getHead_id());
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
    public ObservableList<PaymentPOJO> retrieveEnabledPayments() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ObservableList<PaymentPOJO> retrievePaymentsList = FXCollections.observableArrayList();
        try {

            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();
            String query = "SELECT\n"
                    + "	* \n"
                    + "FROM\n"
                    + "	master_payment_heads where enable='y'";
            rs = stmt.executeQuery(query);
            retrievePaymentsList.clear();
            while (rs.next()) {
                PaymentPOJO paymentPOJO = new PaymentPOJO();
                paymentPOJO.setHead_id(rs.getString("head_id"));
                paymentPOJO.setHead(rs.getString("head"));
                retrievePaymentsList.add(paymentPOJO);

            }

        } catch (SQLException sqle) {
            logger.error(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return retrievePaymentsList;
    }

    /**
     *
     * @return
     */
    @Override
    public ObservableList<PaymentPOJO> retrieveDisabledPayments() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ObservableList<PaymentPOJO> retrievePaymentsList = FXCollections.observableArrayList();
        try {

            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();
            String query = "SELECT\n"
                    + "	* \n"
                    + "FROM\n"
                    + "	master_payment_heads where enable='n'";
            rs = stmt.executeQuery(query);
            retrievePaymentsList.clear();
            while (rs.next()) {
                PaymentPOJO paymentPOJO = new PaymentPOJO();
                paymentPOJO.setHead_id(rs.getString("head_id"));
                paymentPOJO.setHead(rs.getString("head"));
                retrievePaymentsList.add(paymentPOJO);
            }

        } catch (SQLException sqle) {
            logger.error(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return retrievePaymentsList;
    }

    /* ========= Add Payment Amount Implementation part ================= */

    /**
     *
     * @param paymentHeadsIdList
     * @param programReqd
     * @return
     */

    @Override
    public int insertPaymentHeadAmount(ObservableList<PaymentBEAN> paymentHeadsIdList, String programReqd) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        try {

            con = DBPool.getInstance().getConn();
            String query_insert = "insert into master_payment_heads_assigned(assign_id,head_id,program,fees) values(?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query_insert);
            for (PaymentBEAN paymentBEAN : paymentHeadsIdList) {
                ps.setString(1, "0");
                ps.setString(2, paymentBEAN.getHead_id());
                ps.setString(3, programReqd);
                ps.setString(4, paymentBEAN.getAmount());
                ps.addBatch();
            }
            row = ps.executeBatch()[0];
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
     * @param paymentHeadsIdList
     * @param programReqd
     * @return
     */
    @Override
    public int updatePaymentHeadAmount(ObservableList<PaymentBEAN> paymentHeadsIdList, String programReqd) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        try {

            con = DBPool.getInstance().getConn();
            String query_update = "update master_payment_heads_assigned  set head_id=?,program=?,fees=? where assign_id=?";
            PreparedStatement ps = con.prepareStatement(query_update);
            for (PaymentBEAN paymentBEAN : paymentHeadsIdList) {
                ps.setString(1, paymentBEAN.getHead_id());
                ps.setString(2, programReqd);
                ps.setString(3, paymentBEAN.getAmount());
                ps.setString(4, paymentBEAN.getAssignId());
                ps.addBatch();
            }
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

//    @Override
//    public ObservableList<PaymentBEAN> retrievePayAmounts() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    /**
     *
     * @return
     */
    @Override
    public ObservableList<PaymentBEAN> retrievePaymentHeads() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {

            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();
            String query = "SELECT\n"
                    + "	* \n"
                    + "FROM\n"
                    + "	master_payment_heads where enable='y'";
            rs = stmt.executeQuery(query);
            retrievePaymentHeadsList.clear();
            while (rs.next()) {
                PaymentBEAN paymentBEAN = new PaymentBEAN();
                paymentBEAN.setHead_id(rs.getString("head_id"));
                paymentBEAN.setHead(rs.getString("head"));

                retrievePaymentHeadsList.add(paymentBEAN);
            }

        } catch (SQLException sqle) {
            logger.error(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return retrievePaymentHeadsList;
    }

    /**
     *
     * @param statusId
     * @return
     */
    @Override
    public ObservableList<MandatoryBEAN> getMandatoryHeadsByStatus(String statusId) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ObservableList<MandatoryBEAN> listMandatory = FXCollections.observableArrayList();
        try {

            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();
            String query = "SELECT * FROM master_payment_heads mph\n"
                    + "LEFT JOIN  " + TABLE_MSTR_PAYMNT_MANDATORY + " mpm ON mpm.heads_id=mph.head_id\n"
                    + "and mpm.app_status_id=" + statusId + "\n"
                    + "WHERE mph.enable='y'";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                MandatoryBEAN mandatoryBEAN = new MandatoryBEAN();
                mandatoryBEAN.setHeadId(rs.getString("head_id"));
                mandatoryBEAN.setHeadName(rs.getString("head"));
                mandatoryBEAN.setMandatoryId(rs.getString("mandatory_id"));
                mandatoryBEAN.setAppStatusId(rs.getString("app_status_id"));
                mandatoryBEAN.setIsMandatoryHeadId(rs.getString("heads_id"));
                listMandatory.add(mandatoryBEAN);
            }

        } catch (SQLException sqle) {
            logger.error(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return listMandatory;
    }

    /**
     *
     * @param mandatoryBEANs
     * @return
     */
    @Override
    public int insertMandatoryHeads(ObservableList<MandatoryBEAN> mandatoryBEANs) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        try {
            con = DBPool.getInstance().getConn();
            String query_insert = "INSERT INTO " + TABLE_MSTR_PAYMNT_MANDATORY + " (\n"
                    + "	mandatory_id,\n"
                    + "	app_status_id,\n"
                    + "	heads_id\n"
                    + ")\n"
                    + "VALUES\n"
                    + "	(?, ?, ?);";
            PreparedStatement ps = con.prepareStatement(query_insert);
            for (MandatoryBEAN mandatoryBEAN : mandatoryBEANs) {
                ps.setString(1, "0");
                ps.setString(2, mandatoryBEAN.getAppStatusId());
                ps.setString(3, mandatoryBEAN.getHeadId());
                ps.addBatch();
            }
            row = ps.executeBatch()[0];
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
     * @param statusId
     * @return
     */
    @Override
    public int deleteMandatoryHeads(String statusId) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        try {

            con = DBPool.getInstance().getConn();
            String query = "DELETE from " + TABLE_MSTR_PAYMNT_MANDATORY + " WHERE app_status_id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, statusId);
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
     * @param programReqd
     * @return
     */
    @Override
    public ObservableList<PaymentBEAN> retrievePaymentHeadsAmount(String programReqd) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {

            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();
            String query = "SELECT mph.head_id,mph.enable,mph.head,mpha.head_id as heads_id,mpha.assign_id,mpha.program,mpha.fees FROM master_payment_heads mph\n"
                    + "LEFT JOIN master_payment_heads_assigned  mpha ON mpha.head_id=mph.head_id\n"
                    + "and mpha.program='" + programReqd + "'\n"
                    + "WHERE mph.enable='y'";
            rs = stmt.executeQuery(query);
            retrievePaymentHeadsAmountList.clear();
            while (rs.next()) {

                PaymentBEAN paymentBEAN = new PaymentBEAN();
                paymentBEAN.setAssignId(rs.getString("assign_id"));
                paymentBEAN.setHead_id(rs.getString("head_id"));
                paymentBEAN.setHead(rs.getString("head"));
                paymentBEAN.setProgramReqd(rs.getString("program"));
                paymentBEAN.setAmount(rs.getString("fees"));
                paymentBEAN.setHeadsId(rs.getString("heads_id"));
                retrievePaymentHeadsAmountList.add(paymentBEAN);
            }

        } catch (SQLException sqle) {
            logger.error(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return retrievePaymentHeadsAmountList;

//        Connection con = null;
//        Statement stmt = null;
//        ResultSet rs = null;
//
//        try {
//            System.out.println("programReqd  ------------"+programReqd);
//            con = DBPool.getInstance().getConn();
//            String query = "SELECT\n"
//                    + "	* \n"
//                    + "FROM\n"
//                    + "	master_payment_heads_assigned where program=?";
//            PreparedStatement ps = con.prepareStatement(query);
//            ps.setString(1, programReqd);
//            rs = ps.executeQuery();
//            retrievePaymentHeadsAmountList.clear();
//            while (rs.next()) {
//                PaymentBEAN paymentBEAN = new PaymentBEAN();
//                paymentBEAN.setAssignId(rs.getString("assign_id"));
//                paymentBEAN.setHead_id(rs.getString("head_id"));
//                paymentBEAN.setProgramReqd(rs.getString("program"));
//                paymentBEAN.setAmount(rs.getString("fees"));
//                retrievePaymentHeadsAmountList.add(paymentBEAN);
//            }
//
//        } catch (SQLException sqle) {
//            logger.error(sqle.toString());
//            sqle.printStackTrace();
//        } finally {
//            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
//            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
//        }
//        return retrievePaymentHeadsAmountList;
    }

    /**
     *
     * @param programReqd
     * @return
     */
    @Override
    public int deletePaymentHeadsAmount(String programReqd) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        try {

            con = DBPool.getInstance().getConn();
            String query = "delete from master_payment_heads_assigned where program=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, programReqd);
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

}
