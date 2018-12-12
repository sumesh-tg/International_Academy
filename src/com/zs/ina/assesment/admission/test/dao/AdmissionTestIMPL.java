/*
 * Copyright (C) 2016 100018
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
package com.zs.ina.assesment.admission.test.dao;

import com.zs.ina.assesment.dao.ProgramSuggestedRequiredDAO;
import com.zs.ina.assesment.technical.dao.TechnicalSkilllsIMPL;
import com.zs.ina.common.GlobalClassDAO;
import com.zs.ina.common.UiiDGenerator;
import com.zs.ina.common.constants.TableNames;
import com.zs.ina.context.Context;
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
 * @author 100018
 */
public class AdmissionTestIMPL implements AdmissionTestDAO {

    static Logger logger = Logger.getLogger(TechnicalSkilllsIMPL.class);

    /**
     *
     * @param admissionTestBEAN
     * @return
     */
    @Override
    public int insertAdmissionTest(AdmissionTestBEAN admissionTestBEAN) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        admissionTestBEAN.setRowId("ad_" + UiiDGenerator.getUIID8());
        try {
            if (admissionTestBEAN.getReading() != null) {
                admissionTestBEAN.setReading(admissionTestBEAN.getReading());
            } else {
                admissionTestBEAN.setReading(null);
            }
            if (admissionTestBEAN.getWriting() != null) {
                admissionTestBEAN.setWriting(admissionTestBEAN.getWriting());
            } else {
                admissionTestBEAN.setWriting(null);
            }
            if (admissionTestBEAN.getSpeaking() != null) {
                admissionTestBEAN.setSpeaking(admissionTestBEAN.getSpeaking());
            } else {
                admissionTestBEAN.setSpeaking(null);
            }
            if (admissionTestBEAN.getListening() != null) {
                admissionTestBEAN.setListening(admissionTestBEAN.getListening());
            } else {
                admissionTestBEAN.setListening(null);
            }
            String sql = "INSERT INTO " + TableNames.TABLE_ENQ_ASMNT_ADMISION_TEST + " (\n"
                    + "	admission_test_id,\n"
                    + "	test,\n"
                    + "	enquiry_id,\n"
                    + "	created_user,\n"
                    + "	created_date,\n"
                    + "	updated_date,\n"
                    + "	updated_user,\n"
                    + "	latest_flag,\n"
                    + "	reading,\n"
                    + "	writing,\n"
                    + "	speaking,\n"
                    + "	listening,\n"
                    + "	overall\n"
                    + ")\n"
                    + "VALUES\n"
                    + "	(\n"
                    + "		'" + admissionTestBEAN.getRowId() + "',\n"
                    + "		'" + admissionTestBEAN.getTest() + "',\n"
                    + "		 '" + admissionTestBEAN.getEnquiryId() + "',\n"
                    + "		'" + Context.getInstance().currentProfile().getProfilePOJO().getUsername() + "',\n"
                    + "		NOW(),\n"
                    + "		NULL,\n"
                    + "		NULL,\n"
                    + "		'y',\n"
                    + "		'" + admissionTestBEAN.getReading() + "',\n"
                    + "		'" + admissionTestBEAN.getWriting() + "',\n"
                    + "		'" + admissionTestBEAN.getSpeaking() + "',\n"
                    + "		'" + admissionTestBEAN.getListening() + "',\n"
                    + "		'" + admissionTestBEAN.getOverAllScore() + "'\n"
                    + "	);";

            stmt = con.createStatement();
            row = stmt.executeUpdate(sql);
            if (row > 0) {
                GlobalClassDAO.updateLatestFlags(admissionTestBEAN.getEnquiryId(), TableNames.TABLE_ENQ_ASMNT_ADMISION_TEST, "n", " admission_test_id NOT IN('" + admissionTestBEAN.getRowId() + "')");
            }
        } catch (SQLException sqle) {
            logger.info(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return row;
    }

    /**
     *
     * @param rowId
     * @return
     */
    @Override
    public int deleteAdmissionTest(String rowId) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        String _isLatestFlag = GlobalClassDAO.checkCurrentEnquiryIdHaveLatestFlag(rowId, TableNames.TABLE_ENQ_ASMNT_ADMISION_TEST, "admission_test_id");
        con = DBPool.getInstance().getConn();
        try {
            String query = "DELETE FROM " + TableNames.TABLE_ENQ_ASMNT_ADMISION_TEST + " \n"
                    + " WHERE admission_test_id='" + rowId + "' \n";
            System.out.println("Delete Admission Test " + query);
            stmt = con.createStatement();
            row = stmt.executeUpdate(query);
            if (row == 1) {
                if (_isLatestFlag != null) {
                    GlobalClassDAO.updateLatestflagOnDelete(_isLatestFlag, TableNames.TABLE_ENQ_ASMNT_ADMISION_TEST);
                }
            }
        } catch (SQLException sqle) {
            logger.info(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return row;

    }

    /**
     *
     * @param admissionTestBEAN
     * @return
     */
    @Override
    public int updateAdmissionTest(AdmissionTestBEAN admissionTestBEAN) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        try {
            if (admissionTestBEAN.getReading() != null) {
                admissionTestBEAN.setReading(admissionTestBEAN.getReading());
            } else {
                admissionTestBEAN.setReading(null);
            }
            if (admissionTestBEAN.getWriting() != null) {
                admissionTestBEAN.setWriting(admissionTestBEAN.getWriting());
            } else {
                admissionTestBEAN.setWriting(null);
            }
            if (admissionTestBEAN.getSpeaking() != null) {
                admissionTestBEAN.setSpeaking(admissionTestBEAN.getSpeaking());
            } else {
                admissionTestBEAN.setSpeaking(null);
            }
            if (admissionTestBEAN.getListening() != null) {
                admissionTestBEAN.setListening(admissionTestBEAN.getListening());
            } else {
                admissionTestBEAN.setListening(null);
            }
            String sql = "UPDATE " + TableNames.TABLE_ENQ_ASMNT_ADMISION_TEST + "\n"
                    + "SET \n"
                    + " test = '" + admissionTestBEAN.getTest() + "',\n"
                    + " overall = '" + admissionTestBEAN.getOverAllScore() + "',\n"
                    + " updated_date = NOW(),\n"
                    + " updated_user = '" + Context.getInstance().currentProfile().getProfilePOJO().getUsername() + "'\n"
                    + " ,latest_flag='y',reading = '" + admissionTestBEAN.getReading() + "',writing = '" + admissionTestBEAN.getWriting() + "',speaking = '" + admissionTestBEAN.getSpeaking() + "',listening = '" + admissionTestBEAN.getListening() + "' WHERE \n"
                    + "	admission_test_id = '" + admissionTestBEAN.getRowId() + "'";
            stmt = con.createStatement();
            row = stmt.executeUpdate(sql);
            if (row > 0) {
                GlobalClassDAO.updateLatestFlags(admissionTestBEAN.getEnquiryId(), TableNames.TABLE_ENQ_ASMNT_ADMISION_TEST, "n", " admission_test_id NOT IN('" + admissionTestBEAN.getRowId() + "')");
            }
        } catch (SQLException sqle) {
            logger.info(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return row;
    }

    /**
     *
     * @param enquiryId
     * @return
     */
    @Override
    public List<AdmissionTestBEAN> retrieveAdmissionTest(String enquiryId) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<AdmissionTestBEAN> listAdmissionTest = new ArrayList<>();
        try {
            String query = "SELECT * FROM  " + TableNames.TABLE_ENQ_ASMNT_ADMISION_TEST + "\n"
                    + " WHERE \n"
                    + "	enquiry_id='" + enquiryId + "';";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                AdmissionTestBEAN admissionTestBEAN = new AdmissionTestBEAN();
                admissionTestBEAN.setRowId(rs.getString("admission_test_id"));
                admissionTestBEAN.setEnquiryId(rs.getString("enquiry_id"));
                admissionTestBEAN.setTest(rs.getString("test"));
                admissionTestBEAN.setOverAllScore(rs.getString("overall"));
                if (rs.getString("reading") != null) {
                    if (!rs.getString("reading").equalsIgnoreCase("")) {
                        admissionTestBEAN.setReading(rs.getString("reading"));

                    }
                }
                if (rs.getString("writing") != null) {
                    if (!rs.getString("writing").equalsIgnoreCase("")) {
                        admissionTestBEAN.setWriting(rs.getString("writing"));

                    }
                }
                if (rs.getString("speaking") != null) {
                    if (!rs.getString("speaking").equalsIgnoreCase("")) {
                        admissionTestBEAN.setSpeaking(rs.getString("speaking"));

                    }
                }
                if (rs.getString("listening") != null) {
                    if (!rs.getString("listening").equalsIgnoreCase("")) {
                        admissionTestBEAN.setListening(rs.getString("listening"));

                    }
                }

                listAdmissionTest.add(admissionTestBEAN);
            }

        } catch (SQLException sqle) {
            logger.info(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return listAdmissionTest;
    }

}
