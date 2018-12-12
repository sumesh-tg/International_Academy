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
package com.zs.ina.assesment.dao;

import com.zs.ina.assesment.model.WorktExperienceBEAN;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import zs.com.ina.db.mysql.pool.DBPool;

/**
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class WorkExperienceDAO {

    static Logger logger = Logger.getLogger(QualificationsCrudDAO.class);

    /**
     *
     * @param rowId
     * @return
     */
    public static int deleteWrkExpDetailsByid(String rowId) {
        String _isLatestFlag = GlobalClassDAO.checkCurrentEnquiryIdHaveLatestFlag(rowId, TableNames.TABLE_ENQ_ASMNT_WORK_EXP, "work_exp_id");
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        try {
            String query = "Delete from " + TableNames.TABLE_ENQ_ASMNT_WORK_EXP + " where work_exp_id='" + rowId + "'";
            stmt = con.createStatement();
            row = stmt.executeUpdate(query);
            if (row == 1) {
                if (_isLatestFlag != null) {
                    GlobalClassDAO.updateLatestflagOnDelete(_isLatestFlag, TableNames.TABLE_ENQ_ASMNT_WORK_EXP);
                }
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());

        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return row;
    }

    //method for insert work experience single bean
    /**
     *
     * @param enquiryId
     * @param assessmentExperienceBEAN
     */
    public static void insertWorkExperience(String enquiryId, WorktExperienceBEAN assessmentExperienceBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        String GENERATE_ENQ_ID = "wk_";
        con = DBPool.getInstance().getConn();
        try {
            GENERATE_ENQ_ID = GENERATE_ENQ_ID + UiiDGenerator.getUIID8();
            String sql = "INSERT INTO " + TableNames.TABLE_ENQ_ASMNT_WORK_EXP + " (\n"
                    + "	work_exp_id,\n"
                    + "	enquiry_id,\n"
                    + "	profession,\n"
                    + "	duration,\n"
                    + "	created_user,\n"
                    + "	created_date,\n"
                    + "	updated_date,\n"
                    + "	updated_user,\n"
                    + "	latest_flag,\n"
                    + "	workexp_from,\n"
                    + "	workexp_to,\n"
                    + "	employer_name\n"
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
                    + "		'y',\n"
                    + "		?,\n"
                    + "		?,\n"
                    + "		?\n"
                    + "	);";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, GENERATE_ENQ_ID);
            ps.setString(2, enquiryId);
            ps.setString(3, assessmentExperienceBEAN.getProfession());
            ps.setString(4, assessmentExperienceBEAN.getDuration());
            ps.setString(5, Context.getInstance().currentProfile().getProfilePOJO().getUsername());
            if (assessmentExperienceBEAN.getFromDate() != null) {
                ps.setString(6, assessmentExperienceBEAN.getFromDate().toString());
            } else {
                ps.setNull(6, java.sql.Types.DATE);
            }
            if (assessmentExperienceBEAN.getToDate() != null) {
                ps.setString(7, assessmentExperienceBEAN.getToDate().toString());
            } else {
                ps.setNull(7, java.sql.Types.DATE);
            }
            if (assessmentExperienceBEAN.getEmployerName() != null) {
                ps.setString(8, assessmentExperienceBEAN.getEmployerName());
            } else {
                ps.setString(8, null);
            }

            row = ps.executeUpdate();
            if (row > 0) {
                assessmentExperienceBEAN.setRowId(GENERATE_ENQ_ID);
                GlobalClassDAO.updateLatestFlags(enquiryId, TableNames.TABLE_ENQ_ASMNT_WORK_EXP, "n", " work_exp_id NOT IN('"
                        + assessmentExperienceBEAN.getRowId() + "')");

            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());

        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
    }

    //update work experience with single bean
    /**
     *
     * @param assesmentExperiencePOJO
     */
    public static void updateWorkExperience(WorktExperienceBEAN assesmentExperiencePOJO) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        try {
            String sql = "UPDATE " + TableNames.TABLE_ENQ_ASMNT_WORK_EXP + "\n"
                    + "SET profession = ?,\n"
                    + " duration = ?,\n"
                    + " updated_date = now(),\n"
                    + " updated_user = ?,latest_flag='y',workexp_from=?,workexp_to=?,employer_name=?\n"
                    + "WHERE\n"
                    + "	work_exp_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, assesmentExperiencePOJO.getProfession());
            ps.setString(2, assesmentExperiencePOJO.getDuration());
            ps.setString(3, Context.getInstance().currentProfile().getProfilePOJO().getUsername());

            if (assesmentExperiencePOJO.getFromDate() != null) {
                ps.setString(4, assesmentExperiencePOJO.getFromDate().toString());
            } else {
                ps.setNull(4, java.sql.Types.DATE);
            }
            if (assesmentExperiencePOJO.getToDate() != null) {
                ps.setString(5, assesmentExperiencePOJO.getToDate().toString());
            } else {
                ps.setNull(5, java.sql.Types.DATE);
            }
            if (assesmentExperiencePOJO.getEmployerName() != null) {
                ps.setString(6, assesmentExperiencePOJO.getEmployerName());
            } else {
                ps.setString(6, null);
            }
            ps.setString(7, assesmentExperiencePOJO.getRowId());
            row = ps.executeUpdate();
            System.out.println("assessment work experience :: " + sql);
            if (row > 0) {
                GlobalClassDAO.updateLatestFlags(assesmentExperiencePOJO.getEnquiryId(), TableNames.TABLE_ENQ_ASMNT_WORK_EXP, "n", " work_exp_id NOT IN('" + assesmentExperiencePOJO.getRowId() + "')");
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());

        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
    }

    /**
     *
     * @param enquiryId
     * @return
     */
    public static List<WorktExperienceBEAN> getWorkExperience(String enquiryId) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<WorktExperienceBEAN> assesmentExperiencePOJOs = new ArrayList<WorktExperienceBEAN>();
        try {
            String query = "Select * from " + TableNames.TABLE_ENQ_ASMNT_WORK_EXP + " where enquiry_id='" + enquiryId + "' ORDER BY latest_flag desc";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                WorktExperienceBEAN assessmentExperienceBEAN = new WorktExperienceBEAN();
                assessmentExperienceBEAN.setRowId(rs.getString("work_exp_id"));
                assessmentExperienceBEAN.setEnquiryId(rs.getString("enquiry_id"));
                assessmentExperienceBEAN.setProfession(rs.getString("profession"));
                assessmentExperienceBEAN.setDuration(rs.getString("duration"));
                if (rs.getString("workexp_from") != null) {
                    if (!rs.getString("workexp_from").equalsIgnoreCase("")) {
                        assessmentExperienceBEAN.setFromDate(LocalDate.parse(rs.getString("workexp_from")));
                    }
                }
                if (rs.getString("workexp_to") != null) {
                    if (!rs.getString("workexp_to").equalsIgnoreCase("")) {
                        assessmentExperienceBEAN.setToDate(LocalDate.parse(rs.getString("workexp_to")));
                    }
                }
                if (rs.getString("employer_name") != null) {
                    if (!rs.getString("employer_name").equalsIgnoreCase("")) {
                        assessmentExperienceBEAN.setEmployerName(rs.getString("employer_name"));

                    }
                }
                assesmentExperiencePOJOs.add(assessmentExperienceBEAN);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());

        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return assesmentExperiencePOJOs;
    }

}
