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

import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public interface TasksDAO {

    /**
     *
     * @param createdUser
     * @param assignedTo
     * @param branch
     * @return
     */
    public List<TasksBEAN> retrieveAllTasks(String createdUser, String assignedTo, String branch);

    /**
     *
     * @param createdUser
     * @param assignedTo
     * @param branch
     * @param keyword
     * @return
     */
    public List<TasksBEAN> retrieveAllTasks(String createdUser, String assignedTo, String branch, String keyword);

    /**
     *
     * @param username
     * @param branch
     * @param status
     * @return
     */
    public List<TasksBEAN> retrieveByStatus(String username, String branch, String status);

    /**
     *
     * @param username
     * @param branch
     * @param keyword
     * @return
     */
    public List<TasksBEAN> retrieveAllIncoming(String username, String branch, String keyword);

    /**
     *
     * @param username
     * @param branch
     * @param keyword
     * @return
     */
    public List<TasksBEAN> retrieveTaskCreated(String username, String branch, String keyword);

    /**
     *
     * @param username
     * @param branch
     * @param status
     * @param keyword
     * @return
     */
    public List<TasksBEAN> retrieveByStatus(String username, String branch, String status, String keyword);

    /**
     *
     * @param taskId
     * @return
     */
    public TasksBEAN retrieveTaskById(String taskId);

    /**
     *
     * @param tasksBEAN
     * @return
     */
    public int insertTask(TasksBEAN tasksBEAN);

    /**
     *
     * @param tasksBEAN
     * @return
     */
    public int updateTask(TasksBEAN tasksBEAN);

    /**
     *
     * @param tasksBEAN
     * @return
     */
    public int insertTaskDetailsLog(TasksBEAN tasksBEAN);

    /**
     *
     * @param taskId
     * @return
     */
    public int deleteTask(String taskId);

    /**
     *
     * @return
     */
    public String getNextTicketNo();

    /**
     *
     * @param username
     * @param branch
     * @param taskId
     * @return
     */
    public ObservableList<TasksBEAN> retrieveTaskLogById(String username, String branch, String taskId);

}
