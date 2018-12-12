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
package com.zs.ina.enquiry.dao;

import com.zs.ina.common.GlobalClassDAO;
import com.zs.ina.common.UiiDGenerator;
import com.zs.ina.common.constants.TableNames;
import com.zs.ina.context.Context;
import com.zs.ina.counselor.dao.model.CounselorDetailsBEAN;
import com.zs.ina.utility.ClosingResourcesInDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import zs.com.ina.db.mysql.pool.DBPool;

/**
 *
 * @author SUMESH T.G ZoftSolutions
 */
public class ContactNumbersIMPL implements ContactNumbersDAO {

    /**
     *
     */
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    static Logger logger = Logger.getLogger(ContactNumbersIMPL.class);

    /**
     *
     * @param enquiryId
     * @return
     */
    @Override
    public List<ContactNumberBEAN> retrieveAllContactNumbers(String enquiryId) {

        List<ContactNumberBEAN> numberBEANList = new ArrayList<>();
        try {
            con = DBPool.getInstance().getConn();
            String query = "SELECT\n"
                    + "	*\n"
                    + "FROM\n"
                    + "	" + TableNames.TABLE_ENQ_ASMNT_CONTACT_NUMBERS + " \n"
                    + "WHERE\n"
                    + "	enquiry_id = '" + enquiryId + "'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                ContactNumberBEAN numberBEAN = new ContactNumberBEAN();
                numberBEAN.setRowId(rs.getString("contact_id"));
                numberBEAN.setEnquiryId(rs.getString("enquiry_id"));
                numberBEAN.setStdIsd1(rs.getString("std_isd_code"));
                numberBEAN.setContactNumber1(rs.getString("contact_number"));
                numberBEAN.setStdIsd2(rs.getString("std_isd_code2"));
                numberBEAN.setContactNumber2(rs.getString("contact_number2"));
                numberBEANList.add(numberBEAN);

            }
        } catch (SQLException sqle) {
            logger.error(sqle.toString());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return numberBEANList;

    }

    /**
     *
     * @param contactId
     * @return
     */
    @Override
    public int deleteContactNumber(String contactId) {
        int row = 0;
        try {
            con = DBPool.getInstance().getConn();
            String query = "DELETE\n"
                    + "FROM \n"
                    + TableNames.TABLE_ENQ_ASMNT_CONTACT_NUMBERS + "	\n"
                    + " WHERE \n"
                    + "	contact_id = '" + contactId + "';";
            stmt = con.createStatement();
            row = stmt.executeUpdate(query);
        } catch (SQLException sqle) {
            logger.error(sqle.toString());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return row;

    }

    /**
     *
     * @param numberBEAN
     * @return
     */
    @Override
    public int updateContactNumber(ContactNumberBEAN numberBEAN) {
        int row = 0;
        try {
            con = DBPool.getInstance().getConn();
            String sql = "UPDATE " + TableNames.TABLE_ENQ_ASMNT_CONTACT_NUMBERS + "\n"
                    + "SET std_isd_code = '" + numberBEAN.getStdIsd1() + "',\n"
                    + " contact_number = '" + numberBEAN.getContactNumber1() + "',\n"
                    + " std_isd_code2 = '" + numberBEAN.getStdIsd2() + "',\n"
                    + " contact_number2 = '" + numberBEAN.getContactNumber2() + "',\n"
                    + " updated_user = '" + Context.getInstance().currentProfile().getProfilePOJO().getUsername() + "',\n"
                    + " updated_date = NOW()\n"
                    + "WHERE\n"
                    + "	contact_id = '" + numberBEAN.getRowId() + "'";
            stmt = con.createStatement();
            row = stmt.executeUpdate(sql);
        } catch (SQLException sqle) {
            logger.error(sqle.toString());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return row;

    }

    /**
     *
     * @param numberBEAN
     * @return
     */
    @Override
    public int insertContactNumber(ContactNumberBEAN numberBEAN) {
        int row = 0;
        try {
            con = DBPool.getInstance().getConn();
            numberBEAN.setRowId("no_" + UiiDGenerator.getUIID8());
            String sql = "INSERT INTO " + TableNames.TABLE_ENQ_ASMNT_CONTACT_NUMBERS + " (\n"
                    + "	contact_id,\n"
                    + "	enquiry_id,\n"
                    + "	std_isd_code,\n"
                    + "	contact_number,\n"
                    + "	std_isd_code2,\n"
                    + "	contact_number2,\n"
                    + "	created_user,\n"
                    + "	created_date,\n"
                    + "	updated_date,\n"
                    + "	updated_user,\n"
                    + "	latest_flag\n"
                    + ")\n"
                    + "VALUES\n"
                    + "	(\n"
                    + "	'" + numberBEAN.getRowId() + "',\n"
                    + "	'" + numberBEAN.getEnquiryId() + "',\n"
                    + "	'" + numberBEAN.getStdIsd1() + "',\n"
                    + "	'" + numberBEAN.getContactNumber1() + "',\n"
                    + "	'" + numberBEAN.getStdIsd2() + "',\n"
                    + "	 '" + numberBEAN.getContactNumber2() + "',\n"
                    + "	'" + Context.getInstance().currentProfile().getProfilePOJO().getUsername() + "',\n"
                    + "		NOW(),\n"
                    + "		NULL,\n"
                    + "		NULL,\n"
                    + "		'y'\n"
                    + "	);\n"
                    + "";

            stmt = con.createStatement();
            row = stmt.executeUpdate(sql);
            if (row > 0) {
                GlobalClassDAO.updateLatestFlags(numberBEAN.getEnquiryId(),
                        TableNames.TABLE_ENQ_ASMNT_CONTACT_NUMBERS, "n", " contact_id NOT IN('"
                        + numberBEAN.getRowId() + "')");
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error(sqle.toString());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return row;

    }

    /**
     *
     * @param numberBEAN
     * @return
     */
    @Override
    public int updateContactNumber(CounselorDetailsBEAN numberBEAN) {
        int row = 0;
        try {
            con = DBPool.getInstance().getConn();
            String sql = "UPDATE " + TableNames.TABLE_ENQ_DETAILS + "\n"
                    + "SET std_code = '" + numberBEAN.getStdcode1() + "',\n"
                    + " contact_number = '" + numberBEAN.getContactNumber1() + "',\n"
                    + " std_code2 = '" + numberBEAN.getStdcode2() + "',\n"
                    + " contact_number2 = '" + numberBEAN.getContactNumber2() + "'\n"
                    + "WHERE\n"
                    + "	enquiry_id = '" + numberBEAN.getEnquiryID() + "'";
            System.out.println("Test :: " + sql);
            stmt = con.createStatement();
            row = stmt.executeUpdate(sql);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error(sqle.toString());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return row;

    }

}
