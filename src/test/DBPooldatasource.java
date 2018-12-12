/*
 * Copyright ZoftSolutions(C) 2016 100018
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
package test;


import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import snaq.db.ConnectionPoolManager;
import snaq.db.DBPoolDataSource;

/**
 * DBPool class file will be used to get new Database connection with the help
 * of ConnectionPoolManager.
 *
 * It will fetch all the DB Connection properties from DBPool.properties file
 * and it will create new connection.
 */
public class DBPooldatasource {

    /**
     *
     */
    protected Connection conn;

    /**
     *
     */
    protected ConnectionPoolManager connManager;
    private static DBPooldatasource dbPool;
    private DBPoolDataSource ds;

    static Logger logger = Logger.getLogger(DBPooldatasource.class);

    // Name of the database connection name from DBPool.properties file.
    static final String databaseName = "pool-mysql";

    /**
     * Class constructor creates ConnectionPoolManager object
     *
     */
    public DBPooldatasource() {
        ds = new DBPoolDataSource();
        ds.setName("pool-mysql");
        ds.setDescription("Pooling DataSource");
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/ia");
        ds.setUser("root");
        ds.setPassword("root");
        ds.setMinPool(5);
        ds.setMaxPool(10);
        ds.setMaxSize(30);
        ds.setIdleTimeout(3600);
    }

    /**
     * Creates/Provides the instance of the Pool.
     *
     * @return DBPool
     */
    public static DBPooldatasource getInstance() {
        if (dbPool == null) {
            dbPool = new DBPooldatasource();
        }
        return dbPool;
    }

    /**
     * Sets the connection object.
     *
     * @return 
     * @exception cannot get connection.
     */
    public Connection getConn() {
        Connection con = null;
        try {
            con = ds.getConnection();
            logger.info("Connection Created: " + con.toString());
        } catch (SQLException ex) {
            logger.info("Error While Creating Connection :=> " + ex.toString());
        }
        if (con != null) {
            this.conn = con;
            logger.info("Connection Released: " + this.conn.toString());
            return con;
        } else {
            return con;
        }
    }
}
