/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.utility;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import zs.com.ina.db.mysql.pool.DBPool;

/**
 *
 * @author user
 */
public class ClosingResourcesInDB {

    /**
     *
     */
    public static final String ANSI_RESET = "\u001B[0m";

    /**
     *
     */
    public static final String ANSI_BLACK = "\u001B[30m";

    /**
     *
     */
    public static final String ANSI_RED = "\u001B[31m";

    /**
     *
     */
    public static final String ANSI_GREEN = "\u001B[32m";

    /**
     *
     */
    public static final String ANSI_YELLOW = "\u001B[33m";

    /**
     *
     */
    public static final String ANSI_BLUE = "\u001B[34m";

    /**
     *
     */
    public static final String ANSI_PURPLE = "\u001B[35m";

    /**
     *
     */
    public static final String ANSI_CYAN = "\u001B[36m";

    /**
     *
     */
    public static final String ANSI_WHITE = "\u001B[37m";

    /**
     * @param connection
     * @param statements
     * @param resultSets
     * @param stackTraceElements
     */
    public static void closingAllResources(Connection connection, Statement[] statements, ResultSet[] resultSets, StackTraceElement[] stackTraceElements) {
        try {
            for (ResultSet resultSet : resultSets) {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                    System.out.println(ANSI_BLUE + "Resultset released.....");
                }
            }
            for (Statement statement : statements) {
                if (statement != null) {
                    statement.close();
                    statement = null;
                    System.out.println(ANSI_BLUE + "Statement released.....");
                }
            }
            if (connection != null) {
                connection.close();
                connection = null;
                System.out.println(ANSI_BLUE + "Connection released.....");
            }

            int count = 0;
//            System.out.println(ANSI_GREEN + "<::\tZoftSolution Stackrace\t::>");
//            for (StackTraceElement s : stackTraceElements) {
//                System.out.println(ANSI_YELLOW + "<::\t===========" + ++count + "==========\t::>");
//
//                System.out.println("\t\tClass Name :" + s.getClassName());
//                System.out.println("\t\tFile Name :" + s.getFileName());
//                System.out.println("\t\tMethod Name :" + s.getMethodName());
//                System.out.println("\t\tLine Number :" + s.getLineNumber());
//
//                System.out.println(ANSI_YELLOW + "<::\t======================\t::>");
//            }
//
//            System.out.println(ANSI_GREEN + "<::::--\tZoftSolution Stackrace\t--::::>");

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

    }

    /**
     *
     * @param connection
     * @param stackTraceElements
     */
    public static void closingAllResources(Connection connection, StackTraceElement[] stackTraceElements) {
        try {
            if (connection != null) {
                connection.close();
                connection = null;
                System.out.println("Connection released.....");
            }

            int count = 0;
            System.out.println(ANSI_GREEN + "<::\tZoftSolution Stackrace\t::>");
            for (StackTraceElement s : stackTraceElements) {
                System.out.println(ANSI_YELLOW + "<::\t===========" + ++count + "==========\t::>");

                System.out.println("\t\tClass Name :" + s.getClassName());
                System.out.println("\t\tFile Name :" + s.getFileName());
                System.out.println("\t\tMethod Name :" + s.getMethodName());
                System.out.println("\t\tLine Number :" + s.getLineNumber());

                System.out.println(ANSI_YELLOW + "<::\t======================\t::>");
            }

            System.out.println(ANSI_GREEN + "<::::--\tZoftSolution Stackrace\t--::::>");

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    /**
     *
     * @param args
     * @throws SQLException
     */
    public static void main(String[] args) throws SQLException {
        String query = "Select branch_name from branches";
        Connection connection = DBPool.getInstance().getConn();
        Statement Statement1 = connection.createStatement(), statement2 = connection.createStatement();
        Statement[] statements = {Statement1, statement2};
        ResultSet resultSet = Statement1.executeQuery(query);
        ResultSet[] resultSets = {resultSet};
//        closingAllResources(connection, statements, resultSets);
    }

}
