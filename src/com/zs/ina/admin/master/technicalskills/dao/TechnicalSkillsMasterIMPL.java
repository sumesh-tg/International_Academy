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
package com.zs.ina.admin.master.technicalskills.dao;

import com.zs.ina.utility.ClosingResourcesInDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.log4j.Logger;
import zs.com.ina.db.mysql.pool.DBPool;

/**
 *
 * @author 100018
 *
 */
public class TechnicalSkillsMasterIMPL implements TechnicalSkillsMasterDAO {

    private final String MSTR_TBL_TECHNICAL_SKILLS = "mastertbl_technology";
    static Logger logger = Logger.getLogger(TechnicalSkillsMasterIMPL.class);

    /**
     *
     * @param technicalSkill
     * @return
     */
    @Override
    public int insertTechnicalSkills(String technicalSkill) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        int row = 0;
        String query = "insert into mastertbl_technology(technology) values(?)";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, technicalSkill);
            row = ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{null}, new ResultSet[]{null}, stackTraceElements);
        }
        return row;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public int deleteTechnicalSkills(String id) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        int row = 0;
        String query = "delete from mastertbl_technology where tech_id=?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, id);
            row = ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{null}, new ResultSet[]{null}, stackTraceElements);
        }
        return row;
    }

    /**
     *
     * @param id
     * @param technicalSkill
     * @return
     */
    @Override
    public int updateTechnicalSkills(String id, String technicalSkill) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        int row = 0;
        String query = "update mastertbl_technology set technology=? where tech_id=?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, technicalSkill);
            ps.setString(2, id);
            row = ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{null}, new ResultSet[]{null}, stackTraceElements);
        }
        return row;
    }

    /**
     *
     * @return
     */
    @Override
    public ObservableList<TechnicalBean> getTechnicalSkills() {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        ObservableList<TechnicalBean> obsTechnology = FXCollections.observableArrayList();
        try {
            String query = "SELECT *  FROM " + MSTR_TBL_TECHNICAL_SKILLS + "\n";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                TechnicalBean technicalBean = new TechnicalBean();
                technicalBean.setTechnologyId(rs.getString("tech_id"));
                technicalBean.setTechnology(rs.getString("technology"));
                obsTechnology.add(technicalBean);
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return obsTechnology;
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        TechnicalSkillsMasterDAO masterDAO = new TechnicalSkillsMasterIMPL();
        masterDAO.getTechnicalSkills();
        System.out.println("test :: " + masterDAO.getTechnicalSkills());
    }

    /**
     *
     * @return
     */
    @Override
    public ObservableList retrieveTechnicalSkills() {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        ObservableList obsTechnology = FXCollections.observableArrayList();
        try {
            String query = "SELECT *  FROM " + MSTR_TBL_TECHNICAL_SKILLS + "\n";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                obsTechnology.add(rs.getString("technology"));
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return obsTechnology;
    }

}
