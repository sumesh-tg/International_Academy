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
package com.zs.ina.notice.dao;

import com.zs.ina.common.UiiDGenerator;
import com.zs.ina.notifications.dao.NotificationsDAO;
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
public class NoticeIMPL implements NoticeDAO {

    Logger logger = Logger.getLogger(NoticeIMPL.class);
    ObservableList<NoticeBEAN> retrieveNoticeList = FXCollections.observableArrayList();

    /**
     *
     * @param noticeBEAN
     * @return
     */
    @Override
    public int insertNotice(NoticeBEAN noticeBEAN) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        try {

            con = DBPool.getInstance().getConn();
            noticeBEAN.setId("NOT_" + UiiDGenerator.getUIID8());
            String query = "insert into notice_table(id,created_date,title,description,expired_date,branch_id) values(?,now(),?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, noticeBEAN.getId());
            ps.setString(2, noticeBEAN.getTitle());
            ps.setString(3, noticeBEAN.getDescription());
            ps.setString(4, noticeBEAN.getExpiredDate());
            if (noticeBEAN.getBranchId().equals("All")) {
                ps.setString(5, null);
            } else {
                ps.setString(5, noticeBEAN.getBranchId());
            }
            row = ps.executeUpdate();
            if (row > 0) {
                NotificationsDAO.setCounter("notice");
            }

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
    public ObservableList<NoticeBEAN> retrieveNotices() {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {

            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();
            String query = "SELECT\n"
                    + "	nt.id,\n"
                    + "	nt.created_date,\n"
                    + "	nt.title,\n"
                    + "	nt.description,\n"
                    + "	nt.expired_date,\n"
                    + "	nt.from_date,\n"
                    + "	nt.flag,\n"
                    + "	(\n"
                    + "		CASE\n"
                    + "		WHEN branch_id IS NULL THEN\n"
                    + "			'All'\n"
                    + "		ELSE\n"
                    + "			branch_id\n"
                    + "		END\n"
                    + "	) AS branch_id,\n"
                    + "	(\n"
                    + "		SELECT\n"
                    + "			branch_name\n"
                    + "		FROM\n"
                    + "			branch_tbl bt\n"
                    + "		WHERE\n"
                    + "			bt.branch_id = nt.branch_id\n"
                    + "	) AS branch_name\n"
                    + "FROM\n"
                    + "	notice_table nt";
            rs = stmt.executeQuery(query);
            retrieveNoticeList.clear();
            while (rs.next()) {
                NoticeBEAN noticeBEAN = new NoticeBEAN();
                noticeBEAN.setId(rs.getString("id"));
                noticeBEAN.setCreatedDate(rs.getString("created_date"));
                noticeBEAN.setTitle(rs.getString("title"));
                noticeBEAN.setDescription(rs.getString("description"));
                noticeBEAN.setExpiredDate(rs.getString("expired_date"));
                System.out.println("ExpiredDate:::::" + noticeBEAN.getExpiredDate());
                // noticeBEAN.setBranchId(rs.getString("branch_id"));
                if (rs.getString("branch_id").equals("All")) {
                    noticeBEAN.setBranchId("All");
                } else {
                    noticeBEAN.setBranchId(rs.getString("branch_name"));
                }
                noticeBEAN.setFromDate(rs.getString("from_date"));
                noticeBEAN.setFlag(rs.getString("flag"));
                retrieveNoticeList.add(noticeBEAN);
            }

        } catch (SQLException sqle) {
            logger.error(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return retrieveNoticeList;
    }

    /**
     *
     * @return
     */
    @Override
    public ObservableList<BranchPOJO> retrieveBranches() {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ObservableList<BranchPOJO> branchList = FXCollections.observableArrayList();

        try {
            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();
            String query = "select * from branch_tbl";
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                BranchPOJO branchPOJO = new BranchPOJO();
                branchPOJO.setBranchId(rs.getString("branch_id"));
                branchPOJO.setBranch(rs.getString("branch_name"));
                branchList.add(branchPOJO);
            }
            BranchPOJO branchPOJO = new BranchPOJO("All", "All");
            branchList.add(0, branchPOJO);
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
     * @param noticeBEAN
     * @return
     */
    @Override
    public int updateNotice(NoticeBEAN noticeBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        try {
            System.out.println("getBranchId  ::: " + noticeBEAN.getBranchId());
            con = DBPool.getInstance().getConn();
            String query = "update notice_table set title=?,description=?,expired_date=?,branch_id=? where id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, noticeBEAN.getTitle());
            ps.setString(2, noticeBEAN.getDescription());
            ps.setString(3, noticeBEAN.getExpiredDate());
            if (noticeBEAN.getBranchId().equals("All")) {
                ps.setString(4, null);
            } else {
                ps.setString(4, noticeBEAN.getBranchId());
            }
            ps.setString(5, noticeBEAN.getId());
            row = ps.executeUpdate();
            if (row > 0) {
                NotificationsDAO.setCounter("notice");
            }
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
     * @param noticeId
     * @return
     */
    @Override
    public int deleteNotice(String noticeId) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;

        try {
            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();
            String query = "delete from notice_table where id='" + noticeId + "'";
            row = stmt.executeUpdate(query);

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
