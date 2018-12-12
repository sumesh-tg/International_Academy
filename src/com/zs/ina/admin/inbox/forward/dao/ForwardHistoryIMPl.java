/*
 * Copyright ZoftSolutions(C) 2015 100018
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
package com.zs.ina.admin.inbox.forward.dao;

import com.zs.ina.common.GlobalClassDAO;
import com.zs.ina.common.constants.TableNames;
import com.zs.ina.context.Context;
import com.zs.ina.utility.ClosingResourcesInDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import zs.com.ina.db.mysql.pool.DBPool;

/**
 *
 * @author 100018
 */
public class ForwardHistoryIMPl implements ForwardHistoryDAO {

    /**
     *
     * @param historyPOJO
     * @return
     */
    @Override
    public int[] forwardEnquiry(ForwardHistoryPOJO historyPOJO) {
        System.out.println("inside forward impl 1");

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int done[] = null;
        con = DBPool.getInstance().getConn();
        String _readFlag = "";
        System.out.println("TEst Completion Fl;ag == >> " + historyPOJO.getCompletionFlag());
        /* ======================== Check Null ==================== */
        if (historyPOJO.getTraining_required() == null) {
            historyPOJO.setTraining_required("");
        }
        if (historyPOJO.getMigration_required() == null) {
            historyPOJO.setMigration_required("");
        }
        if (historyPOJO.getStudy_required() == null) {
            historyPOJO.setStudy_required("");
        }
        if (historyPOJO.getPurpose() == null) {
            historyPOJO.setPurpose("");
        }
        if (historyPOJO.getWork_required() == null) {
            historyPOJO.setWork_required("");
        }
        if (historyPOJO.getImportantFlag() == null) {
            historyPOJO.setImportantFlag("0");
        }
        if (historyPOJO.getCompletionFlag() == null) {
            historyPOJO.setCompletionFlag("0");
            _readFlag = "0";
        } else if (historyPOJO.getCompletionFlag().equalsIgnoreCase("")) {
            historyPOJO.setCompletionFlag("0");
            _readFlag = "0";
        } else if (historyPOJO.getCompletionFlag().equalsIgnoreCase("0")) {
            _readFlag = "0";
        } else if (historyPOJO.getCompletionFlag().equalsIgnoreCase("1")) {
            _readFlag = "1";
        }
        String sql = "UPDATE " + TableNames.TABLE_ENQ_DETAILS + "\n"
                + "SET enquiry_assigning = '" + historyPOJO.getAssigned_to() + "',"
                + "branch='" + historyPOJO.getAssigned_to_branch() + "',"
                + " completion_flag='" + historyPOJO.getCompletionFlag() + "',important_flag=" + historyPOJO.getImportantFlag() + ",read_flag=" + _readFlag + " \n"
                + "WHERE\n"
                + "	enquiry_id = '" + historyPOJO.getEnquiry_id() + "' ";
        String sqlPersonal = "UPDATE " + TableNames.TABLE_ENQ_ASMNT_PERSONAL_INFO + "\n"
                + "SET  branch='" + historyPOJO.getAssigned_to_branch() + "' \n"
                + "WHERE\n"
                + "	enquiry_id = '" + historyPOJO.getEnquiry_id() + "'";
        String sqlstatus = "UPDATE " + TableNames.TABLE_ENQ_ASMNT_STATUS + "\n"
                + "SET  branch='" + historyPOJO.getAssigned_to_branch() + "',remarks='" + historyPOJO.getRemarks() + "', \n"
                + " counsellor = '" + historyPOJO.getAssigned_to() + "',  updated_date=NOW(), \n"
                + "  updated_user='" + Context.getInstance().currentProfile().getProfilePOJO().getUsername() + "' \n"
                + "WHERE\n"
                + "	enquiry_id = '" + historyPOJO.getEnquiry_id() + "'";
        String sqlHistory1 = "INSERT INTO " + TableNames.TABLE_ENQ_HISTORY_ASSIGNING + " (\n"
                + "	 hid ,\n"
                + "	 assigned_by ,\n"
                + "	 assigned_to ,\n"
                + "	 assigned_branch ,\n"
                + "	 assigned_date ,\n"
                + "	 enquiry_id ,\n"
                + "	 holded_by , purpose,current_status,remarks,study_required, \n"
                + "work_required,training_required,migration_required,	created_user,\n"
                + "	created_date,\n"
                + "	updated_date,\n"
                + "	updated_user,\n"
                + "	latest_flag)\n"
                + "VALUES\n"
                + "	(\n"
                + "		'" + historyPOJO.getHid() + "',\n"
                + "		'" + historyPOJO.getAssigned_by() + "',\n"
                + "		'" + historyPOJO.getAssigned_to() + "',\n"
                + "		'" + historyPOJO.getAssigned_branch() + "',\n"
                + "		'" + historyPOJO.getAssigned_date() + "',\n"
                + "		'" + historyPOJO.getEnquiry_id() + "',\n"
                + "		'" + historyPOJO.getHolded_by() + "',\n"
                + "		'" + historyPOJO.getPurpose() + "',\n"
                + "		'" + historyPOJO.getCurrent_status() + "',\n"
                + "		'" + historyPOJO.getRemarks() + "',\n"
                + "		'" + historyPOJO.getStudy_required() + "',\n"
                + "		'" + historyPOJO.getWork_required() + "',\n"
                + "		'" + historyPOJO.getTraining_required() + "',\n"
                + "		'" + historyPOJO.getMigration_required() + "',\n"
                + "		'" + Context.getInstance().currentProfile().getProfilePOJO().getUsername() + "',\n"
                + "		NOW(),\n"
                + "		NULL,\n"
                + "		NULL,\n"
                + "		'y'\n"
                + "	)";

        String sqlHistory2 = "INSERT INTO " + TableNames.TABLE_ENQ_HISTORY_ASSIGNING + " (\n"
                + "	 hid ,\n"
                + "	 assigned_by ,\n"
                + "	 assigned_to ,\n"
                + "	 assigned_branch ,\n"
                + "	 assigned_date ,\n"
                + "	 enquiry_id ,\n"
                + "	 holded_by , purpose,current_status,remarks,study_required, \n"
                + "work_required,training_required,migration_required,	created_user,\n"
                + "	created_date,\n"
                + "	updated_date,\n"
                + "	updated_user,\n"
                + "	latest_flag)\n"
                + "VALUES\n"
                + "	(\n"
                + "		'" + historyPOJO.getHid() + "',\n"
                + "		'" + historyPOJO.getAssigned_by() + "',\n"
                + "		'" + historyPOJO.getAssigned_to() + "',\n"
                + "		'" + historyPOJO.getAssigned_branch() + "',\n"
                + "		NULL,\n"
                + "		'" + historyPOJO.getEnquiry_id() + "',\n"
                + "		'" + historyPOJO.getHolded_by() + "',\n"
                + "		'" + historyPOJO.getPurpose() + "',\n"
                + "		'" + historyPOJO.getCurrent_status() + "',\n"
                + "		'" + historyPOJO.getRemarks() + "',\n"
                + "		'" + historyPOJO.getStudy_required() + "',\n"
                + "		'" + historyPOJO.getWork_required() + "',\n"
                + "		'" + historyPOJO.getTraining_required() + "',\n"
                + "		'" + historyPOJO.getMigration_required() + "',\n"
                + "		'" + Context.getInstance().currentProfile().getProfilePOJO().getUsername() + "',\n"
                + "		NOW(),\n"
                + "		NULL,\n"
                + "		NULL,\n"
                + "		'y'\n"
                + "	)";
        /* ======================== Set null if any var null ==================== */
        sqlHistory2 = sqlHistory2.replace("'null'", "NULL");
        sqlHistory1 = sqlHistory1.replace("'null'", "NULL");
        sqlPersonal = sqlPersonal.replace("'null'", "NULL");
        sqlstatus = sqlstatus.replace("'null'", "NULL");

        try {
            stmt = con.createStatement();
            //   rs = stmt.executeQuery(sql);
            stmt.addBatch(sql);
            if (historyPOJO.getAssigned_date() != null) {
                if (historyPOJO.getAssigned_date().equalsIgnoreCase("")) {
                    stmt.addBatch(sqlHistory2);
                } else {
                    stmt.addBatch(sqlHistory1);
                }
            } else {
                stmt.addBatch(sqlHistory2);
            }

            stmt.addBatch(sqlPersonal);
            System.out.println("Sql Status :: " + sqlstatus);
            stmt.addBatch(sqlstatus);
            System.out.println("Sql Check Completion Flag ===>> Details :: " + sql);

            done = stmt.executeBatch();
            /* ======================== Change Latest Flag ==================== */
            GlobalClassDAO.updateLatestFlags(historyPOJO.getEnquiry_id(), TableNames.TABLE_ENQ_HISTORY_ASSIGNING, "n", " hid NOT IN('" + historyPOJO.getHid() + "')");

        } catch (SQLException sqle) {
            System.out.println("inside forward impl failed query try 1");

            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return done;

    }

    /**
     *
     * @param enquiryId
     * @return
     */
    @Override
    public List<ForwardHistoryPOJO> retriveForwardHistory(String enquiryId) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int done[] = null;
        con = DBPool.getInstance().getConn();
        List<ForwardHistoryPOJO> listForwardHistoryPOJO = new ArrayList<>();
        String sql = "SELECT\n"
                + "	he.*, (\n"
                + "		SELECT\n"
                + "			app_status\n"
                + "		FROM\n"
                + "			mastertbl_app_status\n"
                + "		WHERE\n"
                + "			app_status_id = he.current_status\n"
                + "	) AS cur_status\n"
                + "FROM\n"
                + TableNames.TABLE_ENQ_HISTORY_ASSIGNING + "	 he\n"
                + "WHERE he.enquiry_id='" + enquiryId + "'\n"
                + "ORDER BY he.assigned_date desc ";
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ForwardHistoryPOJO fhpojo = new ForwardHistoryPOJO();
                fhpojo.setHid(rs.getString("hid"));
                fhpojo.setAssigned_by(rs.getString("assigned_by"));
                fhpojo.setAssigned_to(rs.getString("assigned_to"));
                fhpojo.setAssigned_branch(rs.getString("assigned_branch"));
                fhpojo.setAssigned_date(rs.getString("assigned_date"));
                fhpojo.setEnquiry_id(rs.getString("enquiry_id"));
                fhpojo.setHolded_by(rs.getString("holded_by"));
                fhpojo.setPurpose(rs.getString("purpose"));
                fhpojo.setCurrent_status(rs.getString("cur_status"));
                fhpojo.setRemarks(rs.getString("remarks"));
                fhpojo.setStudy_required(rs.getString("study_required"));
                fhpojo.setWork_required(rs.getString("work_required"));
                fhpojo.setTraining_required(rs.getString("training_required"));
                fhpojo.setMigration_required(rs.getString("migration_required"));
                listForwardHistoryPOJO.add(fhpojo);
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return listForwardHistoryPOJO;

    }

}
