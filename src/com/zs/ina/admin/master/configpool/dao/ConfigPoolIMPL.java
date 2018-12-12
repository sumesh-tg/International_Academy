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
package com.zs.ina.admin.master.configpool.dao;

import com.zs.ina.common.UiiDGenerator;
import com.zs.ina.utility.ClosingResourcesInDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.log4j.Logger;
import zs.com.ina.db.mysql.pool.DBPool;

/**
 *
 * @author 100035
 */
public class ConfigPoolIMPL implements ConfigPoolDAO {

    Logger logger = Logger.getLogger(ConfigPoolIMPL.class);
    ObservableList<String> retrievebranchList = FXCollections.observableArrayList();
    ObservableList<NamePOJO> retrieveFullNameList = FXCollections.observableArrayList();
    ObservableList<ConfigPoolBEAN> configPoolList = FXCollections.observableArrayList();

    /**
     *
     * @return
     */
    @Override
    public ObservableList<String> retrieveBranches() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ObservableList<String> branchList = FXCollections.observableArrayList();

        try {
            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();
            String query = "SELECT DISTINCT\n"
                    + "	branch\n"
                    + "FROM\n"
                    + "	employee_role_branch";
            rs = stmt.executeQuery(query);

            while (rs.next()) {

                branchList.add(rs.getString("branch"));
            }

        } catch (SQLException sqle) {
            logger.error(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return branchList;
    }

    /**
     *
     * @param branch
     * @return
     */
    @Override
    public ObservableList<NamePOJO> retrieveFullNames(String branch) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {

            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();
            String query = "SELECT\n"
                    + "	UPPER(fullname) as fullname,\n"
                    + "	enquiry_assigned_by,\n"
                    + "	USER_NAME,\n"
                    + "	branch,\n"
                    + "	role\n"
                    + "FROM\n"
                    + "	employee_role_branch\n"
                    + "WHERE\n"
                    + "	role = 'ROLE_COUNSELOR'\n"
                    + "AND branch ='" + branch + "'";
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                NamePOJO namePOJO = new NamePOJO();
                namePOJO.setFullName(rs.getString("fullname"));
                namePOJO.setUsername(rs.getString("USER_NAME"));
                retrieveFullNameList.add(namePOJO);
            }

        } catch (SQLException sqle) {
            logger.error(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return retrieveFullNameList;
    }

    /**
     *
     * @param configPoolBEAN
     * @return
     */
    @Override
    public int insertConfigPool(ConfigPoolBEAN configPoolBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        try {

            con = DBPool.getInstance().getConn();
            configPoolBEAN.setId("POOL_" + UiiDGenerator.getUIID8());
            stmt = con.createStatement();
            String query_common_pool_insert = "INSERT INTO master_common_pool_config (id,user_name,flag_limit,enquiry_limit,enquiry_duration)VALUES('" + configPoolBEAN.getId() + "','" + configPoolBEAN.getUsername() + "','" + configPoolBEAN.getFlagLimit() + "','" + configPoolBEAN.getEnquiryLimit() + "','" + configPoolBEAN.getEnquiryDuration() + "')";
            System.out.println("query_common_pool_insert :::" + query_common_pool_insert);
            row = stmt.executeUpdate(query_common_pool_insert);
        } catch (SQLException sqle) {
            logger.error(sqle);
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
    public ObservableList<ConfigPoolBEAN> retrieveConfigPool() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {

            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();
            String query = "SELECT\n"
                    + "	erb.branch,\n"
                    + " com.id,\n"
                    + " com.user_name,\n"
                    + "	com.enquiry_limit,\n"
                    + "	com.flag_limit,\n"
                    + "	com.enquiry_duration,\n"
                    + "	UPPER(erb.fullname) AS fullname\n"
                    + "FROM\n"
                    + "	master_common_pool_config com\n"
                    + "RIGHT JOIN employee_role_branch erb ON com.user_name = erb.USER_NAME\n"
                    + "WHERE\n"
                    + "	com.enquiry_limit <> '' order by erb.branch";
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                ConfigPoolBEAN configPoolBEAN = new ConfigPoolBEAN();
                configPoolBEAN.setBranch(rs.getString("branch"));
                configPoolBEAN.setId(rs.getString("id"));
                configPoolBEAN.setUsername(rs.getString("user_name"));
                configPoolBEAN.setEnquiryLimit(rs.getString("enquiry_limit"));
                configPoolBEAN.setFlagLimit(rs.getString("flag_limit"));
                configPoolBEAN.setFullname(rs.getString("fullname"));
                int seconds = Integer.parseInt(rs.getString("enquiry_duration"));
                int day = (int) TimeUnit.SECONDS.toDays(seconds);
                long hours = TimeUnit.SECONDS.toHours(seconds) - (day * 24);
                long minute = TimeUnit.SECONDS.toMinutes(seconds) - (TimeUnit.SECONDS.toHours(seconds) * 60);
                long second = TimeUnit.SECONDS.toSeconds(seconds) - (TimeUnit.SECONDS.toMinutes(seconds) * 60);
                configPoolBEAN.setDays(day + "");
                configPoolBEAN.setHours(hours + "");
                configPoolBEAN.setMinutes(minute + "");
                configPoolBEAN.setTblTimeLimit(configPoolBEAN.getDays()+ " Days    " + configPoolBEAN.getHours()+ " Hrs    " + configPoolBEAN.getMinutes() + " Mins");
                configPoolList.add(configPoolBEAN);

            }

        } catch (SQLException sqle) {
            logger.error(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return configPoolList;
    }

    /**
     *
     * @param configPoolBEAN
     * @return
     */
    @Override
    public int updateConfigPool(ConfigPoolBEAN configPoolBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        try {

            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();
            String query_update_config_pool = "UPDATE master_common_pool_config\n"
                    + "SET flag_limit='" + configPoolBEAN.getFlagLimit() + "',\n"
                    + " enquiry_limit='" + configPoolBEAN.getEnquiryLimit() + "',\n"
                    + " enquiry_duration='" + configPoolBEAN.getEnquiryDuration() + "'\n"
                    + "WHERE\n"
                    + "	id='" + configPoolBEAN.getId() + "'";

            row = stmt.executeUpdate(query_update_config_pool);
            System.out.println("row value iside updateConfig:::: :: " + row);

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
     * @param CofigPoolId
     * @return
     */
    @Override
    public int deleteConfigPool(String CofigPoolId) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;

        try {
            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();
            String query_delete_master = "delete from master_common_pool_config where id='" + CofigPoolId + "'";
            row = stmt.executeUpdate(query_delete_master);
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
