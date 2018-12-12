/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.assesment.sslc.dao;

import com.zs.ina.assesment.dao.SpouseCrudDAO;
import com.zs.ina.assesment.model.AssesmentSsslcBEAN;
import com.zs.ina.assesment.model.EnquiryAssesmentSslcBEAN;
import com.zs.ina.common.GlobalClassDAO;
import com.zs.ina.common.UiiDGenerator;
import com.zs.ina.common.constants.TableNames;
import com.zs.ina.context.Context;
import com.zs.ina.utility.ClosingResourcesInDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.log4j.Logger;
import zs.com.ina.db.mysql.pool.DBPool;

/**
 *
 * @author zoft
 */
public class AssessmentSSLCDAO {

    static Logger logger = Logger.getLogger(AssessmentSSLCDAO.class);

    /**
     *
     * @param enquiryId
     * @return
     */
    public static AssesmentSsslcBEAN getSslcDetails(String enquiryId) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        String query = "Select * from "+ TableNames.TABLE_ENQ_ASMNT_SSLC+" where enquiry_id='" + enquiryId + "'";
        AssesmentSsslcBEAN assesmentSsslcBEAN = new AssesmentSsslcBEAN();
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                assesmentSsslcBEAN.setRowId(rs.getString("sslc_id"));
                assesmentSsslcBEAN.setSslcBoard(rs.getString("board"));
                assesmentSsslcBEAN.setSslcMedium(rs.getString("medium_of_instruction"));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());

        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return assesmentSsslcBEAN;
    }

    /*==================Insert Sslc details============>*/
    /**
     *
     * @param assesmentSsslcBEAN
     */
    public static void insertSslcDetails(AssesmentSsslcBEAN assesmentSsslcBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        String GENERATE_ID = "SSLC";
        GENERATE_ID = GENERATE_ID + UiiDGenerator.getUIID8();
        String sql = "INSERT INTO " + TableNames.TABLE_ENQ_ASMNT_SSLC + " (\n"
                + "	sslc_id,\n"
                + "	enquiry_id,\n"
                + "	board,\n"
                + "	medium_of_instruction,\n"
                + "	created_user,\n"
                + "	created_date,\n"
                + "	updated_date,\n"
                + "	updated_user,\n"
                + "	latest_flag\n"
                + ")\n"
                + "VALUES\n"
                + "	(\n"
                + "		?,\n"
                + "		?,\n"
                + "		?,\n"
                + "		?,\n"
                + "		'" + Context.getInstance().currentProfile().getProfilePOJO().getUsername() + "',\n"
                + "		NOW(),\n"
                + "		NULL,\n"
                + "		NULL,\n"
                + "		'y'\n"
                + "	);\n"
                + "";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, GENERATE_ID);
            ps.setString(2, assesmentSsslcBEAN.getEnquiryId());
            ps.setString(3, assesmentSsslcBEAN.getSslcBoard());
            ps.setString(4, assesmentSsslcBEAN.getSslcMedium());
            row = ps.executeUpdate();
            if (row > 0) {
                assesmentSsslcBEAN.setRowId(GENERATE_ID);
                GlobalClassDAO.updateLatestFlags(assesmentSsslcBEAN.getEnquiryId(), TableNames.TABLE_ENQ_ASMNT_SSLC, "n", " sslc_id NOT IN('" + assesmentSsslcBEAN.getRowId() + "')");
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());

        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
    }

    /*==================Insert Sslc details============>*/
    /**
     *
     * @param assesmentSsslcBEAN
     */
    public static void updateSslcDetails(AssesmentSsslcBEAN assesmentSsslcBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        String sql = "UPDATE "+ TableNames.TABLE_ENQ_ASMNT_SSLC+"\n"
                + "SET board = ?,\n"
                + " medium_of_instruction = ?,\n"
                + " updated_date = NOW(),\n"
                + " updated_user = ?\n"
                + "\n"
                + "WHERE\n"
                + "	(sslc_id = ?);";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, assesmentSsslcBEAN.getSslcBoard());
            ps.setString(2, assesmentSsslcBEAN.getSslcMedium());
            ps.setString(3, Context.getInstance().currentProfile().getProfilePOJO().getUsername());
            ps.setString(4, assesmentSsslcBEAN.getRowId());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
    }

}
