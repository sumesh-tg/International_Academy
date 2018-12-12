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
package com.zs.ina.admin.master.otherskills.dao;

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
 */
public class OtherSkillsMasterIMPL implements OtherSkillsMasterDAO {

    private final String MSTR_TABLE_OTHER_SKILLS = "mastertbl_other_skills";
    static Logger logger = Logger.getLogger(OtherSkillsMasterIMPL.class);

    /**
     *
     * @param category
     * @param otherSkill
     * @return
     */
    @Override
    public int insertOtherSkills(String category, String otherSkill) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String query = "";
        con = DBPool.getInstance().getConn();
        int row = 0;
        if (!skillTypeCheck(category, otherSkill)) {
            query = "insert into mastertbl_other_skills(type,skill) values(?,?)";
//            JOptionPane.showMessageDialog(null, "insert");
        } else {
            query = "update mastertbl_other_skills set type=? where skill=?";
        }
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, otherSkill);
            ps.setString(2, category);
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
     * @param category
     * @return
     */
    @Override
    public int insertOtherSkills(String category) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        int row = 0;
        String query = "insert into mastertbl_other_skills(skill) values(?)";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, category);
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
    public ObservableList<OtherSkills> getOtherSkills() {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        ObservableList<OtherSkills> obsOtherSkills = FXCollections.observableArrayList();
        try {
            String query = "SELECT * from " + MSTR_TABLE_OTHER_SKILLS + " where type<>''";

            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                OtherSkills otherSkills = new OtherSkills();
                otherSkills.setOtherSkillsId(rs.getString("id"));
                otherSkills.setSkill(rs.getString("skill"));
                otherSkills.setType(rs.getString("type"));
                obsOtherSkills.add(otherSkills);
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return obsOtherSkills;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public int deleteOtherSkill(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @return
     */
    @Override
    public ObservableList retrieveOtherSkills() {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        ObservableList obsOtherSkills = FXCollections.observableArrayList();
        try {
            String query = "SELECT\n"
                    + "	DISTINCT(skill)\n"
                    + "FROM\n"
                    + "	" + MSTR_TABLE_OTHER_SKILLS + "\n";

            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                obsOtherSkills.add(rs.getString("skill"));
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return obsOtherSkills;
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        OtherSkillsMasterDAO otherSkillsDAO = new OtherSkillsMasterIMPL();
        System.out.println("skills " + otherSkillsDAO.getOtherSkills());
        System.out.println("skills 2 " + otherSkillsDAO.retrieveOtherSkills());
    }

    /**
     *
     * @return
     */
    @Override
    public ObservableList<OtherSkills> retriveSkills() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        ObservableList<OtherSkills> obsOtherSkills = FXCollections.observableArrayList();
        try {
            String query = "SELECT distinct skill from " + MSTR_TABLE_OTHER_SKILLS + "";

            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                OtherSkills otherSkills = new OtherSkills();
                otherSkills.setSkill(rs.getString("skill"));
                obsOtherSkills.add(otherSkills);
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return obsOtherSkills;
    }

    private boolean skillTypeCheck() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private boolean skillTypeCheck(String category, String otherSkill) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String query = "";
        boolean b = false;
        con = DBPool.getInstance().getConn();
        try {
            query = "SELECT exists(select skill,type from mastertbl_other_skills where skill='" + category + "' and type='" + otherSkill + "')";

            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            System.out.println(query);
            while (rs.next()) {
                b = rs.getBoolean(1);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return b;
    }

    /**
     *
     * @param otherSkills
     * @return
     */
    @Override
    public int updateOtherSkills(OtherSkills otherSkills) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String query = "";
        con = DBPool.getInstance().getConn();
        int row = 0;

        query = "update mastertbl_other_skills set skill=?,type=? where id=?";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, otherSkills.getSkill());
            ps.setString(2, otherSkills.getType());
            ps.setString(3, otherSkills.getOtherSkillsId());
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
    public ObservableList retrieveOtherSkillsType() {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        ObservableList obsOtherSkills = FXCollections.observableArrayList();
        try {
            String query = "SELECT\n"
                    + "		DISTINCT(type)\n"
                    + "FROM\n"
                    + "	" + MSTR_TABLE_OTHER_SKILLS + " WHERE type <>''\n";

            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                obsOtherSkills.add(rs.getString("type"));
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return obsOtherSkills;
    }
}
