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
package com.zs.ina.admin.master.admissiontest.dao;

import com.zs.ina.utility.ClosingResourcesInDB;
import java.sql.Connection;
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
public class AdmissionTestMasterIMPL implements AdmissionTestMasterDAO {

    private final String MSTR_TABLE_ADMISSION_TEST = "mastertbl_admission_test";
    static Logger logger = Logger.getLogger(AdmissionTestMasterIMPL.class);

    /**
     *
     * @param test
     * @return
     */
    @Override
    public int insertAdmissionTest(String test) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        try {
            String query = "INSERT INTO " + MSTR_TABLE_ADMISSION_TEST + " (`id`, `admission_test`)\n"
                    + "VALUES\n"
                    + "	(0, '" + test + "');";

            stmt = con.createStatement();
            row = stmt.executeUpdate(query);

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
     * @return
     */
    @Override
    public ObservableList retrieveAdmissionTest() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        ObservableList<String> listAdmissionTest = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM mastertbl_admission_test";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                listAdmissionTest.add(rs.getString("admission_test"));
            }

        } catch (SQLException sqle) {
            logger.info(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return listAdmissionTest;
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        AdmissionTestMasterDAO admissionTestMasterDAO = new AdmissionTestMasterIMPL();
        System.out.println("test :: " + admissionTestMasterDAO.retrieveAdmissionTest());
        admissionTestMasterDAO.insertAdmissionTest("hiii");
    }

    /**
     *
     * @param test
     * @return
     */
    @Override
    public int insertMasterAdmissionTest(String test) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        try {
            String query = "INSERT INTO mastertbl_admission_test(admission_test)VALUES('" + test + "')";
            stmt = con.createStatement();
            row = stmt.executeUpdate(query);
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
     * @param admissionTest
     * @return
     */
    @Override
    public int updatemasterAdmissionTest(AdmissionTestBEAN admissionTest) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        try {
            String query = "UPDATE mastertbl_admission_test set admission_test='" + admissionTest.getTest() + "' where id='" + admissionTest.getId() + "'";
            stmt = con.createStatement();
            row = stmt.executeUpdate(query);
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
     * @return
     */
    @Override
    public ObservableList<AdmissionTestBEAN> getAdmissionTest() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        ObservableList<AdmissionTestBEAN> listAdmissionTest = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM mastertbl_admission_test";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                AdmissionTestBEAN admissionTest = new AdmissionTestBEAN();
                admissionTest.setId(rs.getString("id"));
                admissionTest.setTest(rs.getString("admission_test"));
                listAdmissionTest.add(admissionTest);
            }

        } catch (SQLException sqle) {
            logger.info(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return listAdmissionTest;
    }

    /**
     *
     * @param admissionTestId
     * @return
     */
    @Override
    public int deleteMasterAdmissionTest(String admissionTestId) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        try {
            String query = "DELETE FROM mastertbl_admission_test WHERE id='" + admissionTestId + "' \n";
            stmt = con.createStatement();
            row = stmt.executeUpdate(query);
        } catch (SQLException sqle) {
            logger.info(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return row;
    }

}
