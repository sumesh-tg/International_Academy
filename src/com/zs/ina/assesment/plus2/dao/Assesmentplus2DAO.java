/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.assesment.plus2.dao;

import com.zs.ina.assesment.dao.ProgramSuggestedRequiredDAO;
import com.zs.ina.assesment.model.AssesmentPlusTwoBEAN;
import com.zs.ina.assesment.plus2.model.AssessmentPlus2BEAN;
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
public class Assesmentplus2DAO {

    static Logger logger = Logger.getLogger(Assesmentplus2DAO.class);


    /*==================Update plustwo details============>*/
    /**
     *
     * @param assesmentPlusTwoBEAN
     */
    public static void updatePlusTwoDetails(AssesmentPlusTwoBEAN assesmentPlusTwoBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        String sql = "UPDATE "+ TableNames.TABLE_ENQ_ASMNT_PLUS2+"\n"
                + "SET board = ?,\n"
                + " medium_of_instruction = ?,\n"
                + " updated_date = now(),\n"
                + " updated_user = ?\n"
                + "WHERE\n"
                + "	plustwo_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, assesmentPlusTwoBEAN.getPlusTwoBoard());
            ps.setString(2, assesmentPlusTwoBEAN.getPlusTwoMedium());
            ps.setString(3, Context.getInstance().currentProfile().getProfilePOJO().getUsername());
            ps.setString(4, assesmentPlusTwoBEAN.getRowId());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
    }

    /*=====================get plus two details=======================*/
    /**
     *
     * @param enquiryId
     * @return
     */
    public static AssesmentPlusTwoBEAN getPlusDetails(String enquiryId) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        String query = "Select * from " + TableNames.TABLE_ENQ_ASMNT_PLUS2 + " where enquiry_id='" + enquiryId + "'";
        AssesmentPlusTwoBEAN assesmentPlusTwoBEAN = new AssesmentPlusTwoBEAN();
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                assesmentPlusTwoBEAN.setRowId(rs.getString("plustwo_id"));
                assesmentPlusTwoBEAN.setPlusTwoBoard(rs.getString("board"));
                assesmentPlusTwoBEAN.setPlusTwoMedium(rs.getString("medium_of_instruction"));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return assesmentPlusTwoBEAN;
    }

    /*==================Insert plustwo details============>*/
    /**
     *
     * @param assesmentPlusTwoBEAN
     */
    public static void insertPlusTwoDetails(AssesmentPlusTwoBEAN assessmentPlus2BEAN) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        String GENERATE_ID = "PLUS";
        GENERATE_ID = GENERATE_ID + UiiDGenerator.getUIID8();
        try {
            String sql = "INSERT INTO " + TableNames.TABLE_ENQ_ASMNT_PLUS2 + " (\n"
                    + "	plustwo_id,\n"
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
                    + "		?,\n"
                    + "		now(),\n"
                    + "		NULL,\n"
                    + "		NULL,\n"
                    + "		'y'\n"
                    + "	);";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "plus2_" + UiiDGenerator.getUIID8());
            ps.setString(2, assessmentPlus2BEAN.getEnquiryId());
            ps.setString(3, assessmentPlus2BEAN.getPlusTwoBoard());
            ps.setString(4, assessmentPlus2BEAN.getPlusTwoMedium());
            ps.setString(5, Context.getInstance().currentProfile().getProfilePOJO().getUsername());
            row = ps.executeUpdate();
            if (row > 0) {
                assessmentPlus2BEAN.setRowId(GENERATE_ID);
                GlobalClassDAO.updateLatestFlags(assessmentPlus2BEAN.getEnquiryId(), TableNames.TABLE_ENQ_ASMNT_PLUS2, "n", " plustwo_id NOT IN('" + assessmentPlus2BEAN.getRowId() + "')");
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }

      
    }
}
