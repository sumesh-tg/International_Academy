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
package com.zs.ina.common.email;

import com.zs.ina.utility.ClosingResourcesInDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import zs.com.ina.db.mysql.pool.DBPool;

/**
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class MailConfigDAO {

    static Logger logger = Logger.getLogger(MailConfigDAO.class);

    /**
     *
     * @return
     */
    public static Map<String, String> retrieveEmailConfig() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        Map<String, String> emailConfig = new HashMap();
        try {
            String sql = "SELECT (AES_DECRYPT(email_id, 'mail')) as email_id,(AES_DECRYPT(password, 'pass'))as password, host from admin_email_id;";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            System.out.println("Enquiry Query :: " + sql);
            if (rs.next()) {
                emailConfig.put("email", rs.getString("email_id"));
                emailConfig.put("pass", rs.getString("password"));
                emailConfig.put("host", rs.getString("host"));
            }
        } catch (SQLException sqle) {
            logger.info(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return emailConfig;
    }

    /**
     *
     * @param mapEmailConfig
     * @return
     */
    public static int insertEmailConfig(Map<String, String> mapEmailConfig) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        int row = 0;
        try {
            String sqlDelete = "DELETE FROM admin_email_id";
            String sql = "INSERT INTO  admin_email_id  ( id ,  email_id ,  password, host )\n"
                    + "VALUES\n"
                    + "	(\n"
                    + "		0,\n"
                    + "		(AES_ENCRYPT('" + mapEmailConfig.get("email") + "', 'mail')),\n"
                    + "		(AES_ENCRYPT('" + mapEmailConfig.get("pass") + "', 'pass')),\n"
                    + "		('" + mapEmailConfig.get("host") + "')\n"
                    + "	);";
            stmt = con.createStatement();
            stmt.executeUpdate(sqlDelete);
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
     * @param args
     */
    public static void main(String[] args) {
//        Map<String, String> emailConfig = new HashMap();
//        emailConfig.put("email", "helloooo");
//        emailConfig.put("pass", "haiiii");
//        insertEmailConfig(emailConfig);
        System.out.println("retrive email config " + retrieveEmailConfig());
    }

}
