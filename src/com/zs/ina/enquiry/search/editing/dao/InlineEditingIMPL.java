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
package com.zs.ina.enquiry.search.editing.dao;

import com.zs.ina.common.UiiDGenerator;
import com.zs.ina.common.constants.TableNames;
import com.zs.ina.counselor.dao.model.CounselorDetailsBEAN;
import com.zs.ina.utility.ClosingResourcesInDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.apache.log4j.Logger;
import zs.com.ina.db.mysql.pool.DBPool;

/**
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class InlineEditingIMPL implements InlineEditingDAO {

    static Logger logger = Logger.getLogger(InlineEditingIMPL.class);

    /**
     *
     * @param editDetailsBEAN
     * @return
     */
    @Override
    public int editStudyRequired(CounselorDetailsBEAN editDetailsBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        try {

            String query = "UPDATE " + TableNames.TABLE_ENQ_ASMNT_STUDY + "\n"
                    + "SET  program_level = '" + editDetailsBEAN.getProgramLevel() + "',\n"
                    + " program_field = '" + editDetailsBEAN.getProgramField() + "'\n"
                    + " WHERE enquiry_id = '" + editDetailsBEAN.getEnquiryID() + "' \n";
            stmt = con.createStatement();
            row = stmt.executeUpdate(query);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return row;
    }

    /**
     *
     * @param editDetailsBEAN
     * @return
     */
    @Override
    public int editWorkRequired(CounselorDetailsBEAN editDetailsBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        try {

            String query = "UPDATE " + TableNames.TABLE_ENQ_ASMNT_WORK + "\n"
                    + "SET \n"
                    + " profession = '" + editDetailsBEAN.getProfession() + "'\n"
                    + "WHERE\n"
                    + "	enquiry_id = '" + editDetailsBEAN.getEnquiryID() + "' \n";
            stmt = con.createStatement();
            row = stmt.executeUpdate(query);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return row;
    }

    /**
     *
     * @param editDetailsBEAN
     * @return
     */
    @Override
    public int editMigrationRequired(CounselorDetailsBEAN editDetailsBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        try {

            String query = "UPDATE " + TableNames.TABLE_ENQ_ASMNT_MIGRATION + "\n"
                    + "SET \n"
                    + " profession = '" + editDetailsBEAN.getProfession() + "'\n"
                    + " \n"
                    + "WHERE\n"
                    + "	enquiry_id = '" + editDetailsBEAN.getEnquiryID() + "'\n"
                    + "	\n"
                    + "";
            System.out.println("Query :: " + query);
            stmt = con.createStatement();
            row = stmt.executeUpdate(query);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());

        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return row;
    }

    /**
     *
     * @param editDetailsBEAN
     * @return
     */
    @Override
    public int editTrainingRequired(CounselorDetailsBEAN editDetailsBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        try {

            String query = "UPDATE " + TableNames.TABLE_ENQ_ASMNT_TRAINING + "\n"
                    + "SET \n"
                    + " training = '" + editDetailsBEAN.getTrainingRequired() + "',\n"
                    + " batch = '" + editDetailsBEAN.getBatch() + "'\n"
                    + "WHERE\n"
                    + "	enquiry_id='" + editDetailsBEAN.getEnquiryID() + "'";
            System.out.println("Query :: " + query);
            stmt = con.createStatement();
            row = stmt.executeUpdate(query);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());

        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return row;
    }

    /**
     *
     * @param enquiryDetailsBEAN
     * @return
     */
    @Override
    public int updateEnquiryIntoDB(CounselorDetailsBEAN enquiryDetailsBEAN) {
        int row = 0;
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = DBPool.getInstance().getConn();
            String query = "UPDATE " + TableNames.TABLE_ENQ_DETAILS + "  SET\n"
                    + "	 contact_name=? ,\n"
                    + "	 contact_email =?,\n"
                    + "	 contact_number=? ,\n"
                    + "	 country =?,\n"
                    + "	 state=? ,\n"
                    + "	 district=? ,\n"
                    + "	 enquiry_source=? ,\n"
                    + "	 other_enquiry =?,	\n"
                    + "	 rating=? ,\n"
                    + "	 last_update=? ,\n"
                    + "	 enquiry_method =?,\n"
                    + "  WHERE enquiry_id='" + enquiryDetailsBEAN.getEnquiryID() + "'\n";
            //     System.out.println("Update Query :: " + query);
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, enquiryDetailsBEAN.getContactName());
            ps.setString(2, enquiryDetailsBEAN.getContactEmail());
            ps.setString(3, enquiryDetailsBEAN.getContactNumber1());
            ps.setString(4, enquiryDetailsBEAN.getCountry());
            ps.setString(5, enquiryDetailsBEAN.getState());
            ps.setString(6, enquiryDetailsBEAN.getDistrict());
            ps.setString(7, enquiryDetailsBEAN.getEnquirySource());
            ps.setString(8, enquiryDetailsBEAN.getOtherEnquiry());
            ps.setString(9, enquiryDetailsBEAN.getRating());
            ps.setString(10, enquiryDetailsBEAN.getLastUpdate());
            ps.setString(11, enquiryDetailsBEAN.getEnquiryMethod());
            row = ps.executeUpdate();
        } catch (SQLException sqle) {
            //   sqle.printStackTrace();
            logger.info(sqle.toString());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return row;

    }

    /**
     *
     * @param editDetailsBEAN
     * @return
     */
    @Override
    public int editRemarks(CounselorDetailsBEAN editDetailsBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        try {

            String query = "UPDATE " + TableNames.TABLE_ENQ_ASMNT_STATUS + "\n"
                    + " SET \n"
                    + " remarks = '" + editDetailsBEAN.getRemarks() + "'\n"
                    + " WHERE\n"
                    + "enquiry_id = '" + editDetailsBEAN.getEnquiryID() + "'";
            System.out.println("Query :: " + query);
            stmt = con.createStatement();
            row = stmt.executeUpdate(query);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error(sqle);
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return row;
    }

    /**
     *
     * @param editDetailsBEAN
     * @return
     */
    @Override
    public int updateStudyCountryLocaion(CounselorDetailsBEAN editDetailsBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        try {

            String query = "UPDATE " + TableNames.TABLE_ENQ_ASMNT_STUDY + "\n"
                    + "SET \n"
                    + " country = '" + editDetailsBEAN.getStudyCountry() + "',\n"
                    + " location = '" + editDetailsBEAN.getStudyLocation() + "'\n"
                    + "WHERE\n"
                    + "		enquiry_id = '" + editDetailsBEAN.getEnquiryID() + "'";
            System.out.println("Query :: " + query);
            stmt = con.createStatement();
            row = stmt.executeUpdate(query);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());

        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return row;
    }

    /**
     *
     * @param editDetailsBEAN
     * @return
     */
    @Override
    public int updateWorkCountryLocaion(CounselorDetailsBEAN editDetailsBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        try {

            String query = "UPDATE " + TableNames.TABLE_ENQ_ASMNT_WORK + "\n"
                    + "SET \n"
                    + " location = '" + editDetailsBEAN.getWorkLocation() + "',\n"
                    + " country = '" + editDetailsBEAN.getWorkCountry() + "'\n"
                    + "WHERE\n"
                    + "	enquiry_id = '" + editDetailsBEAN.getEnquiryID() + "'";
            System.out.println("Query :: " + query);
            stmt = con.createStatement();
            row = stmt.executeUpdate(query);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());

        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return row;
    }

    /**
     *
     * @param editDetailsBEAN
     * @return
     */
    @Override
    public int updateMigrateCountryLocaion(CounselorDetailsBEAN editDetailsBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        try {

            String query = "UPDATE " + TableNames.TABLE_ENQ_ASMNT_MIGRATION + "\n"
                    + "SET \n"
                    + " country = '" + editDetailsBEAN.getMigrateCountry() + "',\n"
                    + " location = '" + editDetailsBEAN.getMigrateLocation() + "'\n"
                    + "WHERE\n"
                    + "	enquiry_id='" + editDetailsBEAN.getEnquiryID() + "'";
            System.out.println("Query :: " + query);
            stmt = con.createStatement();
            row = stmt.executeUpdate(query);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return row;
    }

    /**
     *
     * @param editDetailsBEAN
     * @return
     */
    @Override
    public int updateTrainingJoiningDate(CounselorDetailsBEAN editDetailsBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        try {

            String query = "UPDATE " + TableNames.TABLE_ENQ_ASMNT_TRAINING + "\n"
                    + "SET \n"
                    + " from_date = '" + editDetailsBEAN.getJoiningDate() + "'\n"
                    + "WHERE\n"
                    + "	enquiry_id='" + editDetailsBEAN.getEnquiryID() + "'";
            System.out.println("Query :: " + query);
            stmt = con.createStatement();
            row = stmt.executeUpdate(query);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());

        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return row;
    }

    /**
     *
     * @param editDetailsBEAN
     * @return
     */
    @Override
    public int updateDistrictStateCountry(CounselorDetailsBEAN editDetailsBEAN) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        try {

            String query = "UPDATE " + TableNames.TABLE_ENQ_DETAILS + "\n"
                    + "SET country = '" + editDetailsBEAN.getCountry() + "',\n"
                    + " state = '" + editDetailsBEAN.getState() + "',\n"
                    + " district = '" + editDetailsBEAN.getDistrict() + "'\n"
                    + "WHERE\n"
                    + "	enquiry_id = '" + editDetailsBEAN.getEnquiryID() + "'";
            System.out.println("Query :: " + query);
            stmt = con.createStatement();
            row = stmt.executeUpdate(query);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return row;

    }

    /**
     *
     * @param inboxList
     * @return
     */
    @Override
    public int updateStarStatus(List<CounselorDetailsBEAN> inboxList) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;

        try {
            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();

            for (CounselorDetailsBEAN detailsBEAN : inboxList) {
                String query = "UPDATE " + TableNames.TABLE_ENQ_DETAILS + "\n"
                        + "SET rating = '" + detailsBEAN.getRating() + "'\n"
                        + "WHERE\n"
                        + "	enquiry_id = '" + detailsBEAN.getEnquiryID() + "'";
                String query2 = "UPDATE " + TableNames.TABLE_ENQ_ASMNT_STATUS + "\n"
                        + "SET priority = '" + detailsBEAN.getRating() + "'\n"
                        + "WHERE\n"
                        + "	enquiry_id = '" + detailsBEAN.getEnquiryID() + "'";
                System.out.println("" + query + "\n query2 \n" + query2);
                stmt.addBatch(query);
                stmt.addBatch(query2);
            }

            stmt.executeBatch();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return row;

    }

    /**
     *
     * @param editDetailsBEAN
     * @return
     */
    @Override
    public int updateAppointmentStatus(CounselorDetailsBEAN editDetailsBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        try {

            String sql = "INSERT INTO " + TableNames.TABLE_ENQ_ASMNT_STATUS + "(assement_status_id,enquiry_status, enquiry_id, remarks,date)\n"
                    + "VALUES('as_" + UiiDGenerator.getUIID8() + "' ,'" + editDetailsBEAN.getStatus() + "',"
                    + " '" + editDetailsBEAN.getEnquiryID() + "',"
                    + " '" + editDetailsBEAN.getRemarks() + "',";
            if (editDetailsBEAN.getAppointmentDate() == null) {
                sql = sql + " NULL)\n";
            } else if (editDetailsBEAN.getAppointmentDate().equalsIgnoreCase("")) {
                sql = sql + " NULL)\n";
            } else {
                sql = sql + " '" + editDetailsBEAN.getAppointmentDate() + "')\n";
            }
            sql = sql + " ON DUPLICATE KEY \n"
                    + "UPDATE \n"
                    + "assement_status_id=values(assement_status_id),\n"
                    + "enquiry_id=values(enquiry_id),\n"
                    + "remarks=values(remarks),\n"
                    + "date=values(date),\n"
                    + "enquiry_status=VALUES(enquiry_status)";
            System.out.println("Query :: " + sql);
            stmt = con.createStatement();
            row = stmt.executeUpdate(sql);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return row;
    }

    /**
     *
     * @param inboxList
     * @return
     */
    @Override
    public int updateImportantFlag(List<CounselorDetailsBEAN> inboxList) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;

        try {
            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();

            for (CounselorDetailsBEAN detailsBEAN : inboxList) {
                String query = "UPDATE " + TableNames.TABLE_ENQ_DETAILS + "\n"
                        + "SET important_flag = '" + detailsBEAN.getImportant()+ "'\n"
                        + "WHERE\n"
                        + "	enquiry_id = '" + detailsBEAN.getEnquiryID() + "'";
                stmt.addBatch(query);
            }

            stmt.executeBatch();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return row;

    }

}
