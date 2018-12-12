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
package com.zs.ina.assesment.langskills.dao;

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
import zs.com.ina.db.mysql.pool.DBPool;

/**
 *
 * @author 100018
 */
public class LanguageSkillsIMPL implements LanguageSkillsDAO {

    /**
     *
     * @param languageSkillsPOJO
     * @return
     */
    @Override
    public int insertLanguageSkills(LanguageSkillsBEAN languageSkillsPOJO) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        languageSkillsPOJO.setRowId("LN_" + UiiDGenerator.getUIID8());
        try {
            languageSkillsPOJO.setProficiency(languageSkillsPOJO.getRead() + "," + languageSkillsPOJO.getWrite() + "," + languageSkillsPOJO.getSpeak());
            String sql = "INSERT INTO  " + TableNames.TABLE_ENQ_ASMNT_LANG_SKILLS + "  (\n"
                    + "	 lang_skill_id ,\n"
                    + "	 language ,\n"
                    + "	 proficiency ,\n"
                    + "	 enquiry_id ,\n"
                    + "	 created_user ,\n"
                    + "	 created_date ,\n"
                    + "	 updated_date ,\n"
                    + "	 updated_user ,\n"
                    + "	 latest_flag \n"
                    + ")\n"
                    + "VALUES\n"
                    + "	(\n"
                    + "		'" + languageSkillsPOJO.getRowId() + "',\n"
                    + "		'" + languageSkillsPOJO.getLanguage() + "',\n"
                    + "		'" + languageSkillsPOJO.getProficiency() + "',\n"
                    + "		'" + languageSkillsPOJO.getEnquiryId() + "',\n"
                    + "		'" + Context.getInstance().currentProfile().getProfilePOJO().getUsername() + "',\n"
                    + "		now(),\n"
                    + "		NULL,\n"
                    + "		NULL,\n"
                    + "		'y'\n"
                    + "	);";
            System.out.println("Test Proficiency "+languageSkillsPOJO.getProficiency());
            stmt = con.createStatement();
            row = stmt.executeUpdate(sql);
            if (row > 0) {
                GlobalClassDAO.updateLatestFlags(languageSkillsPOJO.getEnquiryId(), TableNames.TABLE_ENQ_ASMNT_LANG_SKILLS, "n", " lang_skill_id NOT IN('" + languageSkillsPOJO.getRowId() + "')");
            }

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
     * @param languageSkillsPOJO
     * @return
     */
    @Override
    public int updateLanguageSkills(LanguageSkillsBEAN languageSkillsPOJO) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        try {
            languageSkillsPOJO.setProficiency(languageSkillsPOJO.getRead() + "," + languageSkillsPOJO.getWrite() + "," + languageSkillsPOJO.getSpeak());

            String sql = "UPDATE " + TableNames.TABLE_ENQ_ASMNT_LANG_SKILLS + "\n"
                    + "SET LANGUAGE = '" + languageSkillsPOJO.getLanguage() + "',\n"
                    + " proficiency = '" + languageSkillsPOJO.getProficiency() + "',\n"
                    + " updated_date = now(),\n"
                    + " updated_user = '" + Context.getInstance().currentProfile().getProfilePOJO().getUsername() + "'\n"
                    + "WHERE\n"
                    + "	lang_skill_id = '" + languageSkillsPOJO.getRowId() + "'";
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
     * @param rowId
     * @return
     */
    @Override
    public int deleteLanguageSkills(String rowId) {
        String _isLatestFlag = GlobalClassDAO.checkCurrentEnquiryIdHaveLatestFlag(rowId, TableNames.TABLE_ENQ_ASMNT_LANG_SKILLS, "lang_skill_id");

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        try {
            String query = "DELETE FROM  " + TableNames.TABLE_ENQ_ASMNT_LANG_SKILLS + "\n"
                    + "WHERE\n"
                    + "	lang_skill_id='" + rowId + "'";
            stmt = con.createStatement();
            row = stmt.executeUpdate(query);
            if (row == 1) {
                if (_isLatestFlag != null) {
                    GlobalClassDAO.updateLatestflagOnDelete(_isLatestFlag, TableNames.TABLE_ENQ_ASMNT_LANG_SKILLS);
                }
            }
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
     * @param enquiryId
     * @return
     */
    @Override
    public List<LanguageSkillsBEAN> retrieveLanguageSkills(String enquiryId) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        List<LanguageSkillsBEAN> listLangSkills = new ArrayList<>();
        try {
            String query = "SELECT *  FROM  " + TableNames.TABLE_ENQ_ASMNT_LANG_SKILLS + "\n"
                    + "WHERE\n"
                    + "	enquiry_id='" + enquiryId + "'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                LanguageSkillsBEAN languageSkillsPOJO = new LanguageSkillsBEAN();
                languageSkillsPOJO.setEnquiryId(rs.getString("enquiry_id"));
                languageSkillsPOJO.setRowId(rs.getString("lang_skill_id"));
                languageSkillsPOJO.setProficiency(rs.getString("proficiency"));
                System.out.println("PROFICIENCY +++++++++++ =====" + languageSkillsPOJO.getProficiency());
                if (languageSkillsPOJO.getProficiency() != null) {
                    String[] profArray = languageSkillsPOJO.getProficiency().split(",");
                    languageSkillsPOJO.setRead(profArray[0]);
                    languageSkillsPOJO.setWrite(profArray[1]);
                    languageSkillsPOJO.setSpeak(profArray[2]);
                    System.out.println("READ ::::" + languageSkillsPOJO.getRead());
                    System.out.println("WRITE ::::" + languageSkillsPOJO.getWrite());
                    System.out.println("SPEAK ::::" + languageSkillsPOJO.getSpeak());
                }
                languageSkillsPOJO.setLanguage(rs.getString("language"));
                listLangSkills.add(languageSkillsPOJO);

            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return listLangSkills;
    }

}
