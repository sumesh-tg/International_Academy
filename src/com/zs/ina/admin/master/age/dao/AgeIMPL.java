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
package com.zs.ina.admin.master.age.dao;

import com.zs.ina.utility.ClosingResourcesInDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import zs.com.ina.db.mysql.pool.DBPool;

/**
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class AgeIMPL implements AgeDAO {

    private final String TABLE_AGES = "mastertbl_age";

    /**
     *
     * @param ageBEAN
     * @return
     */
    @Override
    public int insertAge(AgeBEAN ageBEAN) {
        Connection con = null;
        con = DBPool.getInstance().getConn();
        int row = 0;
        String query = "INSERT INTO mastertbl_age (age_id, age)\n"
                + "VALUES\n"
                + "	(0, '" + ageBEAN.getAge() + "');";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            System.out.println("Testing ..." + query);
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
     * @param ageBEAN
     * @return
     */
    @Override
    public int updateAge(AgeBEAN ageBEAN) {
        Connection con = null;
        con = DBPool.getInstance().getConn();
        int row = 0;
        String query = "INSERT INTO mastertbl_age (age_id, age)\n"
                + "VALUES\n"
                + "	(0, '" + ageBEAN.getAge() + "');";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            System.out.println("Testing ..." + query);
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
     * @param ageBEAN
     * @return
     */
    @Override
    public int deleteAge(AgeBEAN ageBEAN) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @return
     */
    @Override
    public ObservableList<AgeBEAN> retrieveAges() {
        Connection con = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        ObservableList<AgeBEAN> ageBEANs = FXCollections.observableArrayList();
        int row = 0;
        String query = "SELECT *  FROM mastertbl_age";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                AgeBEAN ageBEAN = new AgeBEAN();
                ageBEAN.setId(rs.getString("age_id"));
                ageBEAN.setAge(rs.getString("age"));
                ageBEANs.add(ageBEAN);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();

        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{null}, new ResultSet[]{null}, stackTraceElements);
        }
        return ageBEANs;
    }

    /**
     *
     * @return
     */
    @Override
    public int resetAge() {
        Connection con = null;
        con = DBPool.getInstance().getConn();
        int row = 0;
        String query = "DELETE FROM mastertbl_age";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            row = ps.executeUpdate();
            for (int i = 1; i <= 100; i++) {
                insertAge("" + i);
            }
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
     * @param age
     * @return
     */
    public static int insertAge(String age) {
        Connection con = null;
        con = DBPool.getInstance().getConn();
        int row = 0;
        String query = "INSERT INTO mastertbl_age (age_id, age)\n"
                + "VALUES\n"
                + "	(0, '" + age + "');";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            row = ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();

        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{null}, new ResultSet[]{null}, stackTraceElements);
        }
        return row;
    }
}
