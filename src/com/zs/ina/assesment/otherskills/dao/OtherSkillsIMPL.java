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
package com.zs.ina.assesment.otherskills.dao;

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
public class OtherSkillsIMPL implements OtherSkillsDAO {

    static Logger logger = Logger.getLogger(TechnicalSkilllsIMPL.class);

    /**
     *
     * @param otherSkillBEAN
     * @return
     */
    @Override
    public int insertOtherSkills(OtherSkillBEAN otherSkillBEAN) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        otherSkillBEAN.setRowId("TECH_" + UiiDGenerator.getUIID8());
        try {
            String sql = "INSERT INTO " + TableNames.TABLE_ENQ_ASMNT_OTHER_SKILLS + " (\n"
                    + "	other_skill_id,\n"
                    + "	enquiry_id,\n"
                    + "	other_skill,\n"
                    + "	category,\n"
                    + "	created_user,\n"
                    + "	created_date,\n"
                    + "	updated_date,\n"
                    + "	updated_user,\n"
                    + "	latest_flag\n"
                    + ")\n"
                    + "VALUES\n"
                    + "	(\n"
                    + "		'" + otherSkillBEAN.getRowId() + "',\n"
                    + "		 '" + otherSkillBEAN.getEnquiryId() + "',\n"
                    + "		'" + otherSkillBEAN.getOtherSkill() + "',\n"
                    + "		'" + otherSkillBEAN.getCategory() + "',\n"
                    + "		'" + Context.getInstance().currentProfile().getProfilePOJO().getUsername() + "',\n"
                    + "		now(),\n"
                    + "		NULL,\n"
                    + "		NULL,\n"
                    + "		'y'\n"
                    + "	);";

            stmt = con.createStatement();
            row = stmt.executeUpdate(sql);
            if (row > 0) {
                GlobalClassDAO.updateLatestFlags(otherSkillBEAN.getEnquiryId(), TableNames.TABLE_ENQ_ASMNT_OTHER_SKILLS, "n", " other_skill_id NOT IN('" + otherSkillBEAN.getRowId() + "')");
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
     * @param otherSkillBEAN
     * @return
     */
    @Override
    public int updateOtherSkills(OtherSkillBEAN otherSkillBEAN) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        try {
            String sql = "UPDATE " + TableNames.TABLE_ENQ_ASMNT_OTHER_SKILLS + "\n"
                    + "SET other_skill = '" + otherSkillBEAN.getOtherSkill() + "',\n"
                    + " category = '" + otherSkillBEAN.getCategory() + "',\n"
                    + " updated_date = NULL,\n"
                    + " updated_user = '" + Context.getInstance().currentProfile().getProfilePOJO().getUsername() + "'\n"
                    + "WHERE\n"
                    + "	other_skill_id = '" + otherSkillBEAN.getRowId() + "'";

            stmt = con.createStatement();
            row = stmt.executeUpdate(sql);

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
    public int deleteOtherSkills(String rowId) {
        String _isLatestFlag = GlobalClassDAO.checkCurrentEnquiryIdHaveLatestFlag(rowId, TableNames.TABLE_ENQ_ASMNT_OTHER_SKILLS, "other_skill_id");
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        try {
            String query = "DELETE\n"
                    + "FROM\n"
                    + "	`" + TableNames.TABLE_ENQ_ASMNT_OTHER_SKILLS + "`\n"
                    + "WHERE\n"
                    + "	other_skill_id = '" + rowId + "';";
            stmt = con.createStatement();
            row = stmt.executeUpdate(query);
            if (row == 1) {
                if (_isLatestFlag != null) {
                    GlobalClassDAO.updateLatestflagOnDelete(rowId, TableNames.TABLE_ENQ_ASMNT_OTHER_SKILLS);
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
     * @param enquiryId
     * @return
     */
    @Override
    public List<OtherSkillBEAN> retrieveOtherSkills(String enquiryId) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<OtherSkillBEAN> listOtherSkills = new ArrayList<>();
        try {
            String query = "SELECT * FROM  " + TableNames.TABLE_ENQ_ASMNT_OTHER_SKILLS + "\n"
                    + " WHERE \n"
                    + "	enquiry_id='" + enquiryId + "';";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                OtherSkillBEAN otherSkillBEAN = new OtherSkillBEAN();
                otherSkillBEAN.setRowId(rs.getString("other_skill_id"));
                otherSkillBEAN.setEnquiryId(rs.getString("enquiry_id"));
                otherSkillBEAN.setOtherSkill(rs.getString("other_skill"));
                otherSkillBEAN.setCategory(rs.getString("category"));
                listOtherSkills.add(otherSkillBEAN);
            }

        } catch (SQLException sqle) {
            logger.info(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return listOtherSkills;
    }

}
