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
package com.zs.ina.admin.master.threaddelay.dao;

import com.zs.ina.utility.ClosingResourcesInDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.log4j.Logger;
import zs.com.ina.db.mysql.pool.DBPool;

/**
 *
 * @author 100035
 */
public class ThreadDelayIMPL implements ThreadDelayDAO {

    Logger logger = Logger.getLogger(ThreadDelayIMPL.class);

    /**
     *
     * @param seconds
     * @return
     */
    @Override
    public int insertThreadDelay(int seconds) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        try {

            con = DBPool.getInstance().getConn();
            String query = "INSERT INTO mastertbl_thread_delay (thread_delay_id, thread_delay)\n"
                    + "VALUES\n"
                    + "	(1, ?) ON DUPLICATE KEY UPDATE thread_delay = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, seconds);
            ps.setInt(2, seconds);
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
    public String retrieveThreadDelay() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String second = null;
        try {

            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();
            String query = "SELECT thread_delay from mastertbl_thread_delay";
            rs = stmt.executeQuery(query);
            if (rs.next()) {
                second = rs.getString(1);
            } else {
                 second="15";
            }

        } catch (SQLException sqle) {
            logger.error(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return second;

    }

}
