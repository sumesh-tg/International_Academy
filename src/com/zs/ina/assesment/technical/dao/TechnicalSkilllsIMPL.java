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
package com.zs.ina.assesment.technical.dao;

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
public class TechnicalSkilllsIMPL implements TechnicalSkillsDAO {

    static Logger logger = Logger.getLogger(TechnicalSkilllsIMPL.class);

    /**
     *
     * @param technicalSkillsBEAN
     * @return
     */
    @Override
    public int insertTechnicalSkills(TechnicalSkillsBEAN technicalSkillsBEAN) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        technicalSkillsBEAN.setRowId("TECH_" + UiiDGenerator.getUIID8());
        try {
            String sql = "INSERT INTO " + TableNames.TABLE_ENQ_ASMNT_TECHNICAL_SKILLS + " (\n"
                    + "	tech_id,\n"
                    + "	enquiry_id,\n"
                    + "	technology,\n"
                    + "	knowledge_level,\n"
                    + "	created_user,\n"
                    + "	created_date,\n"
                    + "	updated_date,\n"
                    + "	updated_user,\n"
                    + "	latest_flag\n"
                    + ")\n"
                    + "VALUES\n"
                    + "	(\n"
                    + "	'" + technicalSkillsBEAN.getRowId() + "',\n"
                    + "	'" + technicalSkillsBEAN.getEnquiryId() + "',\n"
                    + "	'" + technicalSkillsBEAN.getTechnology() + "',\n"
                    + "	'" + technicalSkillsBEAN.getKnowledgeLevel() + "',\n"
                    + "	'" + Context.getInstance().currentProfile().getProfilePOJO().getUsername() + "',\n"
                    + "	NOW(),\n"
                    + "	NULL,\n"
                    + "	NULL,\n"
                    + "	'y'\n"
                    + "	);\n"
                    + "";

            stmt = con.createStatement();
            row = stmt.executeUpdate(sql);
            if (row > 0) {
                GlobalClassDAO.updateLatestFlags(technicalSkillsBEAN.getEnquiryId(), TableNames.TABLE_ENQ_ASMNT_TECHNICAL_SKILLS, "n", " tech_id NOT IN('"
                        + technicalSkillsBEAN.getRowId() + "')");
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
     * @param technicalSkillsBEAN
     * @return
     */
    @Override
    public int updateTechnicalSkills(TechnicalSkillsBEAN technicalSkillsBEAN) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        technicalSkillsBEAN.setRowId("TECH_" + UiiDGenerator.getUIID8());
        try {
            String sql = "UPDATE " + TableNames.TABLE_ENQ_ASMNT_TECHNICAL_SKILLS + "\n"
                    + "SET technology = '" + technicalSkillsBEAN.getTechnology() + "',\n"
                    + " knowledge_level = '" + technicalSkillsBEAN.getKnowledgeLevel() + "',\n"
                    + " updated_date = NOW(),\n"
                    + " updated_user = '" + Context.getInstance().currentProfile().getProfilePOJO().getUsername() + "'\n"
                    + "WHERE\n"
                    + "	tech_id = '" + technicalSkillsBEAN.getRowId() + "'";
            stmt = con.createStatement();
            row = stmt.executeUpdate(sql);
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
     * @param rowId
     * @return
     */
    @Override
    public int deleteTechnicalSkills(String rowId) {
        String _isLatestFlag = GlobalClassDAO.checkCurrentEnquiryIdHaveLatestFlag(rowId, TableNames.TABLE_ENQ_ASMNT_TECHNICAL_SKILLS, "tech_id");
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        try {
            String query = "DELETE FROM " + TableNames.TABLE_ENQ_ASMNT_TECHNICAL_SKILLS + "\n"
                    + "WHERE\n"
                    + "	`tech_id`='" + rowId + "';";
            stmt = con.createStatement();
            row = stmt.executeUpdate(query);
            if (row == 1) {
                if (_isLatestFlag != null) {
                    GlobalClassDAO.updateLatestflagOnDelete(_isLatestFlag, TableNames.TABLE_ENQ_ASMNT_TECHNICAL_SKILLS);
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

    /**
     *
     * @param EnquiryId
     * @return
     */
    @Override
    public List<TechnicalSkillsBEAN> retrieveTechnicalSkills(String EnquiryId) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        List<TechnicalSkillsBEAN> listTechnicalSkillsBEAN = new ArrayList<>();
        try {
            String query = "SELECT *  FROM " + TableNames.TABLE_ENQ_ASMNT_TECHNICAL_SKILLS + "\n"
                    + "WHERE\n"
                    + "	`enquiry_id`='" + EnquiryId + "';";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                TechnicalSkillsBEAN skillsBEAN = new TechnicalSkillsBEAN();
                skillsBEAN.setRowId(rs.getString("tech_id"));
                skillsBEAN.setEnquiryId(rs.getString("enquiry_id"));
                skillsBEAN.setTechnology(rs.getString("technology"));
                skillsBEAN.setKnowledgeLevel(rs.getString("knowledge_level"));
                listTechnicalSkillsBEAN.add(skillsBEAN);
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return listTechnicalSkillsBEAN;
    }

}
