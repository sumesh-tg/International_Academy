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
package com.zs.ina.task.dao;

import com.zs.ina.common.UiiDGenerator;
import com.zs.ina.context.Context;
import com.zs.ina.utility.ClosingResourcesInDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.log4j.Logger;
import zs.com.ina.db.mysql.pool.DBPool;

/**
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class TasksIMPL implements TasksDAO {

    List<String> listColors = new ArrayList<>();
    static Logger logger = Logger.getLogger(TasksIMPL.class);

    /**
     *
     * @param createdUser
     * @param assignedTo
     * @param branch
     * @return
     */
    @Override
    public List<TasksBEAN> retrieveAllTasks(String createdUser, String assignedTo, String branch) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<TasksBEAN> listTasks = new ArrayList<>();
        try {
            String query = "SELECT *,CONCAT('task',ticket_no) as ticket FROM task_master where created_user ='" + createdUser + "' or assigned_to = '" + assignedTo + "'"
                    + " ORDER BY (CASE\n"
                    + "    WHEN updated_date  IS NULL THEN created_date\n"
                    + "    ELSE updated_date\n"
                    + "END) DESC";

            if (Context.getInstance().currentProfile().getProfilePOJO().getRole() != null) {
                if (Context.getInstance().currentProfile().getProfilePOJO().getRole().equalsIgnoreCase("ROLE_ADMIN")) {
                    query = "SELECT *,CONCAT('task',ticket_no) as ticket FROM task_master ORDER BY (CASE\n"
                            + "    WHEN updated_date  IS NULL THEN created_date\n"
                            + "    ELSE updated_date\n"
                            + "END) DESC ";
                    System.out.println("Privilleged to admin1");

                }
            }
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            System.out.println("Query " + query);
            while (rs.next()) {
                TasksBEAN tasksBEAN = new TasksBEAN();
                tasksBEAN.setTaskId(rs.getString("task_id"));
                tasksBEAN.setTicketNo(rs.getString("ticket"));
                tasksBEAN.setTaskName(rs.getString("task_name"));
                tasksBEAN.setTaskDescription(rs.getString("task_description"));
                tasksBEAN.setAssignedTo(rs.getString("assigned_to"));
                tasksBEAN.setKeyword(rs.getString("keyword"));
                tasksBEAN.setTaskStatus(rs.getString("task_status"));
                tasksBEAN.setCreatedUser(rs.getString("created_user"));
                tasksBEAN.setCreatedDate(rs.getString("created_date"));
                tasksBEAN.setUpdatedUser(rs.getString("updated_user"));
                tasksBEAN.setUpdatedDate(rs.getString("updated_date"));
                tasksBEAN.setLatestFlag(rs.getString("latest_flag"));
                listTasks.add(tasksBEAN);
            }
        } catch (SQLException sqle) {
            logger.info(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return listTasks;
    }

    /**
     *
     * @param createdUser
     * @param assignedTo
     * @param branch
     * @param keyword
     * @return
     */
    @Override
    public List<TasksBEAN> retrieveAllTasks(String createdUser, String assignedTo, String branch, String keyword) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<TasksBEAN> listTasks = new ArrayList<>();
        try {
            String query = "SELECT *,CONCAT('task',ticket_no) as ticket FROM task_master where keyword='" + keyword + "' and  (created_user ='" + createdUser + "' or assigned_to = '" + assignedTo + "') ORDER BY (CASE\n"
                    + "    WHEN updated_date  IS NULL THEN created_date\n"
                    + "    ELSE updated_date\n"
                    + "END) DESC";

            if (Context.getInstance().currentProfile().getProfilePOJO().getRole() != null) {
                if (Context.getInstance().currentProfile().getProfilePOJO().getRole().equalsIgnoreCase("ROLE_ADMIN")) {
                    query = "SELECT *,CONCAT('task',ticket_no) as ticket FROM task_master ORDER BY (CASE\n"
                            + "    WHEN updated_date  IS NULL THEN created_date\n"
                            + "    ELSE updated_date\n"
                            + "END) DESC";
                    System.out.println("Privilleged to admin1");

                }
            }
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            System.out.println("Query " + query);
            while (rs.next()) {
                TasksBEAN tasksBEAN = new TasksBEAN();
                tasksBEAN.setTaskId(rs.getString("task_id"));
                tasksBEAN.setTicketNo(rs.getString("ticket"));
                tasksBEAN.setTaskName(rs.getString("task_name"));
                tasksBEAN.setTaskDescription(rs.getString("task_description"));
                tasksBEAN.setAssignedTo(rs.getString("assigned_to"));
                tasksBEAN.setKeyword(rs.getString("keyword"));
                tasksBEAN.setTaskStatus(rs.getString("task_status"));
                tasksBEAN.setCreatedUser(rs.getString("created_user"));
                tasksBEAN.setCreatedDate(rs.getString("created_date"));
                tasksBEAN.setUpdatedUser(rs.getString("updated_user"));
                tasksBEAN.setUpdatedDate(rs.getString("updated_date"));
                tasksBEAN.setLatestFlag(rs.getString("latest_flag"));
                listTasks.add(tasksBEAN);
            }
        } catch (SQLException sqle) {
            logger.info(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return listTasks;
    }

    /**
     *
     * @param taskId
     * @return
     */
    @Override
    public TasksBEAN retrieveTaskById(String taskId) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        TasksBEAN tasksBEAN = new TasksBEAN();
        try {
            String query = "SELECT *,CONCAT('task',ticket_no) as ticket FROM task_master where task_id ='" + taskId + "'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                tasksBEAN.setTaskId(rs.getString("task_id"));
                tasksBEAN.setTicketNo(rs.getString("ticket"));
                tasksBEAN.setTaskName(rs.getString("task_name"));
                tasksBEAN.setTaskDescription(rs.getString("task_description"));
                tasksBEAN.setAssignedTo(rs.getString("assigned_to"));
                tasksBEAN.setKeyword(rs.getString("keyword"));
                tasksBEAN.setTaskStatus(rs.getString("task_status"));
                tasksBEAN.setCreatedUser(rs.getString("created_user"));
                tasksBEAN.setCreatedDate(rs.getString("created_date"));
                tasksBEAN.setUpdatedUser(rs.getString("updated_user"));
                tasksBEAN.setUpdatedDate(rs.getString("updated_date"));
                tasksBEAN.setLatestFlag(rs.getString("latest_flag"));
            }
        } catch (SQLException sqle) {
            logger.info(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return tasksBEAN;
    }

    /**
     *
     * @param tasksBEAN
     * @return
     */
    @Override
    public int insertTask(TasksBEAN tasksBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        int row = 0;
        tasksBEAN.setTaskId("task_" + UiiDGenerator.getUIID8());
        if (tasksBEAN.getKeyword() == null) {
            tasksBEAN.setKeyword("generic");
        }
        try {
            String query = "INSERT INTO task_master (\n"
                    + "	task_id,\n"
                    + "	ticket_no,\n"
                    + "	task_name,\n"
                    + "	task_description,\n"
                    + "	assigned_to,\n"
                    + "	keyword,\n"
                    + "	task_status,\n"
                    + "	created_user,\n"
                    + "	created_date,\n"
                    + "	updated_user,\n"
                    + "	updated_date,\n"
                    + "	latest_flag\n"
                    + ")\n"
                    + "VALUES\n"
                    + "	(\n"
                    + "		'" + tasksBEAN.getTaskId() + "',\n"
                    + "		'" + tasksBEAN.getTicketNo() + "',\n"
                    + "		'" + tasksBEAN.getTaskName() + "',\n"
                    + "		'" + tasksBEAN.getTaskDescription() + "',\n"
                    + "		'" + tasksBEAN.getAssignedTo() + "',\n"
                    + "		'" + tasksBEAN.getKeyword() + "',\n"
                    + "		'" + tasksBEAN.getTaskStatus() + "',\n"
                    + "		'" + tasksBEAN.getCreatedUser() + "',\n"
                    + "		NOW(),\n"
                    + "		NULL,\n"
                    + "		NULL,\n"
                    + "		'y'\n"
                    + "	);";
            PreparedStatement ps = con.prepareStatement(query);
            row = ps.executeUpdate();

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
     * @param tasksBEAN
     * @return
     */
    @Override
    public int updateTask(TasksBEAN tasksBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        int row = 0;
        try {
            String sql = "UPDATE task_master\n"
                    + "SET \n"
                    + " task_name = '" + tasksBEAN.getTaskName() + "',\n"
                    + " task_description = '" + tasksBEAN.getTaskDescription() + "',\n"
                    + " assigned_to = '" + tasksBEAN.getAssignedTo() + "',\n"
                    + " keyword = '" + tasksBEAN.getKeyword() + "',\n"
                    + " task_status = '" + tasksBEAN.getTaskStatus() + "',\n"
                    + " updated_user = '" + Context.getInstance().currentProfile().getProfilePOJO().getUsername() + "',\n"
                    + " updated_date = NOW()\n"
                    + "WHERE\n"
                    + "	task_id='" + tasksBEAN.getTaskId() + "'";
            System.out.println("Update Task :: " + sql);
            stmt = con.createStatement();
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
     * @param taskId
     * @return
     */
    @Override
    public int deleteTask(String taskId) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        int row = 0;
        try {
            String sql = "DELETE from  task_master  where task_id='" + taskId + "'";
            stmt = con.createStatement();
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
     * @param username
     * @param branch
     * @param status
     * @return
     */
    @Override
    public List<TasksBEAN> retrieveByStatus(String username, String branch, String status) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<TasksBEAN> listTasks = new ArrayList<>();
        try {
            String query = "SELECT\n"
                    + "	*, CONCAT('task', ticket_no) AS ticket\n"
                    + "FROM\n"
                    + "	task_master\n"
                    + "WHERE\n"
                    + "	(created_user = '" + username + "'\n"
                    + "AND task_status = '" + status + "') or (assigned_to ='" + username + "' and task_status = '" + status + "' ) ORDER BY (CASE\n"
                    + "    WHEN updated_date  IS NULL THEN created_date\n"
                    + "    ELSE updated_date\n"
                    + "END) DESC";
            if (Context.getInstance().currentProfile().getProfilePOJO().getRole() != null) {
                if (Context.getInstance().currentProfile().getProfilePOJO().getRole().equalsIgnoreCase("ROLE_ADMIN")) {
                    query = "SELECT\n"
                            + "	*, CONCAT('task', ticket_no) AS ticket\n"
                            + "FROM\n"
                            + "	task_master\n"
                            + "WHERE\n"
                            + " task_status = '" + status + "' ORDER BY (CASE\n"
                            + "    WHEN updated_date  IS NULL THEN created_date\n"
                            + "    ELSE updated_date\n"
                            + "END) DESC";
                    System.out.println("Privilleged to admin");
                }
            }
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                TasksBEAN tasksBEAN = new TasksBEAN();
                tasksBEAN.setTaskId(rs.getString("task_id"));
                tasksBEAN.setTicketNo(rs.getString("ticket"));
                tasksBEAN.setTaskName(rs.getString("task_name"));
                tasksBEAN.setTaskDescription(rs.getString("task_description"));
                tasksBEAN.setAssignedTo(rs.getString("assigned_to"));
                tasksBEAN.setKeyword(rs.getString("keyword"));
                tasksBEAN.setTaskStatus(rs.getString("task_status"));
                tasksBEAN.setCreatedUser(rs.getString("created_user"));
                tasksBEAN.setCreatedDate(rs.getString("created_date"));
                tasksBEAN.setUpdatedUser(rs.getString("updated_user"));
                tasksBEAN.setUpdatedDate(rs.getString("updated_date"));
                tasksBEAN.setLatestFlag(rs.getString("latest_flag"));
                listTasks.add(tasksBEAN);
            }
        } catch (SQLException sqle) {
            logger.info(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return listTasks;
    }

    /**
     *
     * @param username
     * @param branch
     * @param keyword
     * @return
     */
    @Override
    public List<TasksBEAN> retrieveAllIncoming(String username, String branch, String keyword) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<TasksBEAN> listTasks = new ArrayList<>();
        try {

            String query = "SELECT\n"
                    + "	*, CONCAT('task', ticket_no) AS ticket\n"
                    + "FROM\n"
                    + "	task_master\n"
                    + "WHERE\n"
                    + "	assigned_to = '" + username + "'\n";
            if (keyword != null) {
                if (!keyword.isEmpty()) {
                    query = query + " and keyword = '" + keyword + "'  ORDER BY (CASE\n"
                            + "    WHEN updated_date  IS NULL THEN created_date\n"
                            + "    ELSE updated_date\n"
                            + "END) DESC";
                }
            } else {
                query = query + " ORDER BY (CASE\n"
                        + "    WHEN updated_date  IS NULL THEN created_date\n"
                        + "    ELSE updated_date\n"
                        + "END) DESC \n";
            }
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                TasksBEAN tasksBEAN = new TasksBEAN();
                tasksBEAN.setTaskId(rs.getString("task_id"));
                tasksBEAN.setTicketNo(rs.getString("ticket"));
                tasksBEAN.setTaskName(rs.getString("task_name"));
                tasksBEAN.setTaskDescription(rs.getString("task_description"));
                tasksBEAN.setAssignedTo(rs.getString("assigned_to"));
                tasksBEAN.setKeyword(rs.getString("keyword"));
                tasksBEAN.setTaskStatus(rs.getString("task_status"));
                tasksBEAN.setCreatedUser(rs.getString("created_user"));
                tasksBEAN.setCreatedDate(rs.getString("created_date"));
                tasksBEAN.setUpdatedUser(rs.getString("updated_user"));
                tasksBEAN.setUpdatedDate(rs.getString("updated_date"));
                tasksBEAN.setLatestFlag(rs.getString("latest_flag"));
                listTasks.add(tasksBEAN);
            }
        } catch (SQLException sqle) {
            logger.info(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return listTasks;
    }

    /**
     *
     * @param username
     * @param branch
     * @param status
     * @param keyword
     * @return
     */
    @Override
    public List<TasksBEAN> retrieveByStatus(String username, String branch, String status, String keyword) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<TasksBEAN> listTasks = new ArrayList<>();
        try {
            String query = "SELECT\n"
                    + "	*, CONCAT('task', ticket_no) AS ticket\n"
                    + "FROM\n"
                    + "	task_master\n"
                    + "WHERE\n"
                    + "	(created_user = '" + username + "'\n"
                    + "AND task_status = '" + status + "' AND keyword='" + keyword + "') or (assigned_to ='" + username + "' and task_status = '" + status + "' )  ORDER BY (CASE\n"
                    + "    WHEN updated_date  IS NULL THEN created_date\n"
                    + "    ELSE updated_date\n"
                    + "END) DESC";
            System.out.println("test query :: " + query);
            if (Context.getInstance().currentProfile().getProfilePOJO().getRole() != null) {
                if (Context.getInstance().currentProfile().getProfilePOJO().getRole().equalsIgnoreCase("ROLE_ADMIN")) {
                    query = "SELECT\n"
                            + "	*, CONCAT('task', ticket_no) AS ticket\n"
                            + "FROM\n"
                            + "	task_master\n"
                            + "WHERE\n"
                            + " task_status = '" + status + "' and keyword='" + keyword + "'  ORDER BY (CASE\n"
                            + "    WHEN updated_date  IS NULL THEN created_date\n"
                            + "    ELSE updated_date\n"
                            + "END) DESC";
                    System.out.println("Privilleged to admin");
                }
            }
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                TasksBEAN tasksBEAN = new TasksBEAN();
                tasksBEAN.setTaskId(rs.getString("task_id"));
                tasksBEAN.setTicketNo(rs.getString("ticket"));
                tasksBEAN.setTaskName(rs.getString("task_name"));
                tasksBEAN.setTaskDescription(rs.getString("task_description"));
                tasksBEAN.setAssignedTo(rs.getString("assigned_to"));
                tasksBEAN.setKeyword(rs.getString("keyword"));
                tasksBEAN.setTaskStatus(rs.getString("task_status"));
                tasksBEAN.setCreatedUser(rs.getString("created_user"));
                tasksBEAN.setCreatedDate(rs.getString("created_date"));
                tasksBEAN.setUpdatedUser(rs.getString("updated_user"));
                tasksBEAN.setUpdatedDate(rs.getString("updated_date"));
                tasksBEAN.setLatestFlag(rs.getString("latest_flag"));
                listTasks.add(tasksBEAN);
            }
        } catch (SQLException sqle) {
            logger.info(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return listTasks;
    }

    /**
     *
     * @return
     */
    @Override
    public String getNextTicketNo() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        String ticketNo = "";
        try {
            String sql = "SELECT CONCAT('task',LPAD(AUTO_INCREMENT,6,0)) as ticket\n"
                    + "FROM information_schema.tables \n"
                    + "WHERE table_name='task_master'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                ticketNo = rs.getString("ticket");
            }
        } catch (SQLException sqle) {
            logger.info(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return ticketNo;

    }

    /**
     *
     * @param username
     * @param branch
     * @param taskId
     * @return
     */
    @Override
    public ObservableList<TasksBEAN> retrieveTaskLogById(String username, String branch, String taskId) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        ObservableList<TasksBEAN> listTasks = FXCollections.observableArrayList();
        /* ======================== Set Color ==================== */
        listColors.add("#104fa2");
        listColors.add("#990000");
        listColors.add("#9900ff");
        listColors.add("#009999");
        listColors.add("#0C6B75");
        listColors.add("#195214");
        listColors.add("#5F7523");
        listColors.add("#943625");
        try {
            String query = "SELECT *, (UNIX_TIMESTAMP(NOW())-UNIX_TIMESTAMP(updated_date))*1000 as timeago,"
                    + "DATE_FORMAT(updated_date, \"%a %D-%M-%Y %h:%i:%s %p\") as formatted,"
                    + "(SELECT task_follow_status from mastertbl_task_follow_status "
                    + " WHERE task_follow_id =assessment_substatus) as substatus "
                    + ",(SELECT UPPER(fullname) FROM employee_role_branch  WHERE USER_NAME=created_user) as created_fullname"
                    + ",(SELECT UPPER(fullname) FROM employee_role_branch  WHERE USER_NAME=updated_user) as updated_fullname"
                    + " FROM task_details where task_id='" + taskId + "' "
                    + "order by updated_date asc";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                TasksBEAN tasksBEAN = new TasksBEAN();
                tasksBEAN.setLineNo(rs.getString("line_no"));
                tasksBEAN.setTaskId(rs.getString("task_id"));
                tasksBEAN.setRemarks(rs.getString("remarks"));
                tasksBEAN.setTimeAgo(rs.getString("timeago"));
                tasksBEAN.setCreatedUser(rs.getString("created_user"));
                tasksBEAN.setCreatedDate(rs.getString("created_date"));
                tasksBEAN.setUpdatedUser(rs.getString("updated_user"));
                tasksBEAN.setUpdatedDate(rs.getString("formatted"));
                tasksBEAN.setAssessmentSubStatus(rs.getString("substatus"));
                tasksBEAN.setCreatedFullname(rs.getString("created_fullname"));
                tasksBEAN.setUpdatedFullname(rs.getString("updated_fullname"));
                tasksBEAN.setLatestFlag(rs.getString("latest_flag"));
                tasksBEAN.setColor(getRandomList(listColors));
                listTasks.add(tasksBEAN);
            }
        } catch (SQLException sqle) {
            logger.info(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return listTasks;
    }

    /**
     *
     * @param list
     * @return
     */
    public String getRandomList(List<String> list) {
        //0-4
        int index = ThreadLocalRandom.current().nextInt(list.size());
        System.out.println("\nIndex :" + index);
        return list.get(index);

    }

    /**
     *
     * @param tasksBEAN
     * @return
     */
    @Override
    public int insertTaskDetailsLog(TasksBEAN tasksBEAN) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        int row = 0;
        String lineId = " tk_" + UiiDGenerator.getUIID8();
        try {
            String sql = "INSERT INTO task_details (\n"
                    + "	line_no,\n"
                    + "	task_id,\n"
                    + "	remarks,\n"
                    + "	assessment_substatus,\n"
                    + "	created_user,\n"
                    + "	created_date,\n"
                    + "	updated_user,\n"
                    + "	updated_date,\n"
                    + "	latest_flag\n"
                    + ")\n"
                    + "VALUES\n"
                    + "	(\n"
                    + "		?,\n"
                    + "		?,\n"
                    + "		?,\n"
                    + "		?,\n"
                    + "		?,\n"
                    + "		?,\n"
                    + "		?,\n"
                    + "		NOW(),\n"
                    + "		'y'\n"
                    + "	);";
            System.out.println("test " + sql);
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, lineId);
            ps.setString(2, tasksBEAN.getTaskId());
            ps.setString(3, tasksBEAN.getRemarks());
            ps.setString(4, tasksBEAN.getAssessmentSubStatus());
            ps.setString(5, tasksBEAN.getCreatedUser());
            ps.setString(6, tasksBEAN.getCreatedDate());
            ps.setString(7, tasksBEAN.getUpdatedUser());
            row = ps.executeUpdate();
            if (row > 0) {
                String flagQuery = "UPDATE task_details\n"
                        + "SET latest_flag = 'n'\n"
                        + "WHERE\n"
                        + "	line_no NOT IN ('" + lineId + "') and task_id ='" + tasksBEAN.getTaskId() + "';";
                ps.executeUpdate(flagQuery);
            }
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
     * @param username
     * @param branch
     * @param keyword
     * @return
     */
    @Override
    public List<TasksBEAN> retrieveTaskCreated(String username, String branch, String keyword) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<TasksBEAN> listTasks = new ArrayList<>();
        try {
            String query = "SELECT\n"
                    + "	*, CONCAT('task', ticket_no) AS ticket\n"
                    + "FROM\n"
                    + "	task_master\n"
                    + " WHERE\n"
                    + "	created_user = '" + username + "' \n";
            if (keyword != null) {
                if (!keyword.isEmpty()) {
                    query = query + " and keyword = '" + keyword + "'  ORDER BY (CASE\n"
                            + "    WHEN updated_date  IS NULL THEN created_date\n"
                            + "    ELSE updated_date\n"
                            + "END) DESC";
                }
            }
            if (Context.getInstance().currentProfile().getProfilePOJO().getRole() != null) {
                if (Context.getInstance().currentProfile().getProfilePOJO().getRole().equalsIgnoreCase("ROLE_ADMIN")) {
                    query = "SELECT\n"
                            + "	*, CONCAT('task', ticket_no) AS ticket\n"
                            + "FROM\n"
                            + "	task_master\n"
                            + " WHERE\n"
                            + "	created_user = '" + username + "'\n";
                    if (keyword != null) {
                        if (!keyword.isEmpty()) {
                            query = query + " and keyword = '" + keyword + "'  ORDER BY (CASE\n"
                                    + "    WHEN updated_date  IS NULL THEN created_date\n"
                                    + "    ELSE updated_date\n"
                                    + "END) DESC";
                        }
                    }
                    System.out.println("Privilleged to admin");
                }
            }
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                TasksBEAN tasksBEAN = new TasksBEAN();
                tasksBEAN.setTaskId(rs.getString("task_id"));
                tasksBEAN.setTicketNo(rs.getString("ticket"));
                tasksBEAN.setTaskName(rs.getString("task_name"));
                tasksBEAN.setTaskDescription(rs.getString("task_description"));
                tasksBEAN.setAssignedTo(rs.getString("assigned_to"));
                tasksBEAN.setKeyword(rs.getString("keyword"));
                tasksBEAN.setTaskStatus(rs.getString("task_status"));
                tasksBEAN.setCreatedUser(rs.getString("created_user"));
                tasksBEAN.setCreatedDate(rs.getString("created_date"));
                tasksBEAN.setUpdatedUser(rs.getString("updated_user"));
                tasksBEAN.setUpdatedDate(rs.getString("updated_date"));
                tasksBEAN.setLatestFlag(rs.getString("latest_flag"));
                listTasks.add(tasksBEAN);
            }
        } catch (SQLException sqle) {
            logger.info(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return listTasks;
    }

}
