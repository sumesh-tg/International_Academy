/*
 * Copyright (C) 2016 100035
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
package com.zs.ina.admin.master.qualificationlevel.dao;

import com.zs.ina.admin.master.methods.dao.MethodBEAN;
import com.zs.ina.admin.master.methods.dao.MethodIMPL;
import com.zs.ina.admin.master.methods.dao.MethodPOJO;
import com.zs.ina.common.UiiDGenerator;
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
 * @author 100035
 */
public class LevelIMPL implements LevelDAO {

    Logger logger = Logger.getLogger(LevelIMPL.class);
    ObservableList<LevelBEAN> retrieveLevelsList = FXCollections.observableArrayList();

    /**
     *
     * @param levelBEAN
     * @return
     */
    @Override
    public int insertProgramLevel(LevelBEAN levelBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        try {

            con = DBPool.getInstance().getConn();
            levelBEAN.setProgramLevelId("PGML_" + UiiDGenerator.getUIID8());
            String query_insert = "insert into mastertbl_program_level(program_level_id,program_level) values(?,?)";
            PreparedStatement ps = con.prepareStatement(query_insert);
            ps.setString(1, levelBEAN.getProgramLevelId());
            ps.setString(2, levelBEAN.getProgramLevel());
            row = ps.executeUpdate();
        } catch (SQLException sqle) {
            logger.error(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }

        return row;
    }

    /**
     *
     * @return
     */
    @Override
    public ObservableList<LevelBEAN> retrieveProgramLevels() {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {

            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();
            String query = "SELECT * FROM mastertbl_program_level";
            rs = stmt.executeQuery(query);
            retrieveLevelsList.clear();
            while (rs.next()) {
                LevelBEAN levelBEAN = new LevelBEAN();
                levelBEAN.setProgramLevelId(rs.getString("program_level_id"));
                levelBEAN.setProgramLevel(rs.getString("program_level"));
                retrieveLevelsList.add(levelBEAN);
            }

        } catch (SQLException sqle) {
            logger.error(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return retrieveLevelsList;
    }

    /**
     *
     * @param levelBEAN
     * @return
     */
    @Override
    public int updateProgramLevel(LevelBEAN levelBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        try {

            con = DBPool.getInstance().getConn();
            String query = "update mastertbl_program_level set program_level=? where program_level_id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, levelBEAN.getProgramLevel());
            ps.setString(2, levelBEAN.getProgramLevelId());
            row = ps.executeUpdate();

        } catch (SQLException sqle) {
            logger.error(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }

        return row;

    }

}
