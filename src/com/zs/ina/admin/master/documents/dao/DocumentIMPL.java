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
package com.zs.ina.admin.master.documents.dao;

import com.zs.ina.admin.master.payment.dao.PaymentBEAN;
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
public class DocumentIMPL implements DocumentDAO {

    Logger logger = Logger.getLogger(DocumentIMPL.class);

    final static String TABLE_MSTR_DOCUMENTS = "master_documents";
    final static String TABLE_MSTR_ASSIGN_DOCUMENTS = "master_documents_assigned";
    ObservableList<PaymentBEAN> retrievePaymentHeadsAmountList = FXCollections.observableArrayList();

    /**
     *
     * @param documentBEAN
     * @return
     */
    @Override
    public int insertDocument(DocumentBEAN documentBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        try {
            con = DBPool.getInstance().getConn();
            String query_insert = "INSERT INTO " + TABLE_MSTR_DOCUMENTS + " (\n"
                    + "	document_id,\n"
                    + "	document\n"
                    + ")\n"
                    + "VALUES\n"
                    + "	(?, ?);";
            PreparedStatement ps = con.prepareStatement(query_insert);
            ps.setString(1, "0");
            ps.setString(2, documentBEAN.getDocument());
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
    public ObservableList<DocumentBEAN> retrieveDocuments() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ObservableList<DocumentBEAN> retrieveDocumentsList = FXCollections.observableArrayList();
        try {

            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();
            String query = "SELECT * FROM " + TABLE_MSTR_DOCUMENTS + "";
            rs = stmt.executeQuery(query);
            retrieveDocumentsList.clear();
            while (rs.next()) {
                DocumentBEAN documentBEAN = new DocumentBEAN();
                documentBEAN.setDocumentId(rs.getString("document_id"));
                documentBEAN.setDocument(rs.getString("document"));
                retrieveDocumentsList.add(documentBEAN);
            }

        } catch (SQLException sqle) {
            logger.error(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return retrieveDocumentsList;
    }

    /**
     *
     * @param documentBEAN
     * @return
     */
    @Override
    public int updateDocument(DocumentBEAN documentBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        try {

            con = DBPool.getInstance().getConn();
            String query_update = "UPDATE " + TABLE_MSTR_DOCUMENTS + " SET document=? WHERE document_id=?";
            PreparedStatement ps = con.prepareStatement(query_update);
            ps.setString(1, documentBEAN.getDocument());
            ps.setString(2, documentBEAN.getDocumentId());
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
    public static List<String> getAllDocuments() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<String> allDocumentsList = new ArrayList<>();
        String query = "SELECT document FROM " + TABLE_MSTR_DOCUMENTS + "";

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                allDocumentsList.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return allDocumentsList;
    }

    /**
     *
     * @param programReqd
     * @return
     */
    @Override
    public ObservableList<DocumentBEAN> retrieveAssignedDocuments(String programReqd) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ObservableList<DocumentBEAN> retrieveAssignedDocumentsList = FXCollections.observableArrayList();
        try {

            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();
            String query = "SELECT md.document_id,md.document,mda.document_id as documents_id,mda.doc_assign_id,mda.program FROM master_documents md\n"
                    + "LEFT JOIN master_documents_assigned  mda ON mda.document_id=md.document_id\n"
                    + " AND mda.program='" + programReqd + "'";
            rs = stmt.executeQuery(query);
            retrieveAssignedDocumentsList.clear();
            while (rs.next()) {

                DocumentBEAN documentBEAN = new DocumentBEAN();
                documentBEAN.setAssignId(rs.getString("doc_assign_id"));
                documentBEAN.setDocumentId(rs.getString("document_id"));
                documentBEAN.setDocument(rs.getString("document"));
                documentBEAN.setProgram(rs.getString("program"));
                documentBEAN.setDocument_id(rs.getString("documents_id"));
                retrieveAssignedDocumentsList.add(documentBEAN);
            }

        } catch (SQLException sqle) {
            logger.error(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return retrieveAssignedDocumentsList;

    }

    /**
     *
     * @param masterDocumentsList
     * @param programReqd
     * @return
     */
    @Override
    public int insertAssignedDocument(ObservableList<DocumentBEAN> masterDocumentsList, String programReqd) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        try {

            con = DBPool.getInstance().getConn();
            String query_insert = "insert into " + TABLE_MSTR_ASSIGN_DOCUMENTS + "(doc_assign_id,document_id,program) values(?,?,?)";
            PreparedStatement ps = con.prepareStatement(query_insert);
            for (DocumentBEAN documentBEAN : masterDocumentsList) {
                ps.setString(1, "0");
                ps.setString(2, documentBEAN.getDocumentId());
                ps.setString(3, programReqd);
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
     * @param programReqd
     * @return
     */
    @Override
    public int deleteAssignedDocument(String programReqd) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        try {

            con = DBPool.getInstance().getConn();
            String query = "delete from " + TABLE_MSTR_ASSIGN_DOCUMENTS + " where program=?";
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

    /**
     *
     * @param masterDocumentsList
     * @param programReqd
     * @return
     */
    @Override
    public int updateAssignedDocument(ObservableList<DocumentBEAN> masterDocumentsList, String programReqd) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        try {

            con = DBPool.getInstance().getConn();
            String query_update = "update " + TABLE_MSTR_ASSIGN_DOCUMENTS + " set document_id=?,program=? where doc_assign_id=?";
            PreparedStatement ps = con.prepareStatement(query_update);
            for (DocumentBEAN documentBEAN : masterDocumentsList) {
                ps.setString(1, documentBEAN.getDocumentId());
                ps.setString(2, programReqd);
                ps.setString(3, documentBEAN.getAssignId());
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

    @Override
    public ObservableList<DocumentPOJO> retreiveDocument() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ObservableList<DocumentPOJO> retrieveDocumentsList = FXCollections.observableArrayList();
        try {

            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();
            String query = "SELECT * FROM " + TABLE_MSTR_DOCUMENTS + "";
            rs = stmt.executeQuery(query);
            retrieveDocumentsList.clear();
            while (rs.next()) {
                DocumentPOJO documentPOJO = new DocumentPOJO();
                documentPOJO.setDocumentId(Integer.parseInt(rs.getString("document_id")));
                documentPOJO.setDocument(rs.getString("document"));
                retrieveDocumentsList.add(documentPOJO);
            }

        } catch (SQLException sqle) {
            logger.error(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return retrieveDocumentsList;

    }

}
