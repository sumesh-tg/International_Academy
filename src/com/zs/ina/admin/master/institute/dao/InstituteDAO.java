/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.admin.master.institute.dao;

import com.zs.ina.admin.master.language.dao.LanguageBEAN;
import com.zs.ina.common.UiiDGenerator;
import com.zs.ina.utility.ClosingResourcesInDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import zs.com.ina.db.mysql.pool.DBPool;

/**
 *
 * @author zoft
 */
public class InstituteDAO {

    /**
     *
     */
    public static List<String> getAllInstitute;

    /**
     *
     * @return
     */
    public static ObservableList<InstituteBean> getInstituteDetails() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        ObservableList<InstituteBean> instituteBeans = FXCollections.observableArrayList();
        try {
            String query = "select institute_id,institute,country from mastertbl_institute";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                InstituteBean instituteBean = new InstituteBean();
                instituteBean.setId(rs.getString(1));
                instituteBean.setInstitute(rs.getString(2));
                instituteBean.setCountry(rs.getString(3));
                instituteBeans.add(instituteBean);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return instituteBeans;
    }

    /**
     *
     * @param instituteBean
     * @return
     */
    public static int updateInstitute(InstituteBean instituteBean) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        int row = 0;
        String query = "update mastertbl_institute set institute=?,country=? where institute_id=?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, instituteBean.getInstitute());
            ps.setString(2, instituteBean.getCountry());
            ps.setString(3, instituteBean.getId());
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
     * @param instituteBean
     * @return
     */
    public static int insertLanguage(InstituteBean instituteBean) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        int row = 0;
        String query = "insert into mastertbl_institute(institute,country,institute_id) values(?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, instituteBean.getInstitute());
            ps.setString(2, instituteBean.getCountry());
            ps.setString(3,UiiDGenerator.getUIID8());
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
    public static int deleteLanguage(String id) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        int row = 0;
        String query = "delete from mastertbl_institute where institute_id=?";
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
     * @return
     */
    public static ObservableList getCountries() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        ObservableList instituteBeans = FXCollections.observableArrayList();
        try {
            String query = "select country from mastertbl_country";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                instituteBeans.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return instituteBeans;
    }

    /**
     *
     * @return
     */
    public static List<String> getAllInstitute() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<String> allBranches = new ArrayList<>();
        try {
            String query = "select institute from mastertbl_institute";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                allBranches.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return allBranches;
    }
    //Check for duplicate entry

    /**
     *
     * @param instituteBean
     * @return
     */
    public static boolean checkDuplicateEntry(InstituteBean instituteBean) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        boolean f = false;
        con = DBPool.getInstance().getConn();
        String quesry = "SELECT EXISTS(SELECT 1 FROM mastertbl_institute WHERE institute='" + instituteBean.getInstitute() + "' and country='"+instituteBean.getCountry()+"')";
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(quesry);
            rs.next();
            f = rs.getBoolean(1);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{null}, new ResultSet[]{null}, stackTraceElements);
        }
        return f;
    }
}
