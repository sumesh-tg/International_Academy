/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zs.com.ina.db.mysql.pool;

import com.zs.ina.utility.ClosingResourcesInDB;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.prefs.Preferences;

import org.apache.log4j.Logger;
import snaq.db.ConnectionPoolEvent;

import snaq.db.ConnectionPoolManager;
import snaq.db.DBPoolDataSource;

/**
 * DBPool class file will be used to get new Database connection with the help
 * of ConnectionPoolManager.
 *
 * It will fetch all the DB Connection properties from DBPool.properties file
 * and it will create new connection.
 */
public class DBPool {

    /**
     *
     */
    protected Connection conn;

    /**
     *
     */
    protected ConnectionPoolManager connManager;
    private static DBPool dbPool;
    private DBPoolDataSource ds;
    private Preferences myfilePrefs;
    static Logger logger = Logger.getLogger(DBPool.class);

    // Name of the database connection name from DBPool.properties file.
    static final String databaseName = "pool-mysql";

    /**
     * Class constructor creates ConnectionPoolManager object
     *
     */
    public DBPool() {
        try {
            myfilePrefs = Preferences.userRoot();
            myfilePrefs = myfilePrefs.node("com.zs.ina.login.INALoginForm");

            if (myfilePrefs.get("server", "not found").equalsIgnoreCase("not found") || myfilePrefs.get("db", "not found").equalsIgnoreCase("not found")
                    || myfilePrefs.get("user", "not found").equalsIgnoreCase("not found") || myfilePrefs.get("password", "not found").equalsIgnoreCase("not found")
                    || myfilePrefs.get("server", "not found").equalsIgnoreCase("") || myfilePrefs.get("db", "").equalsIgnoreCase("")
                    || myfilePrefs.get("user", "not found").equalsIgnoreCase("") || myfilePrefs.get("password", "not found").equalsIgnoreCase("")) {
                /* ====================== If no preference values found ====================== */

                connManager = ConnectionPoolManager.getInstance("DBPool.properties");

            } else {
                /* ====================== Set Properties From Preference ====================== */
                ds = new DBPoolDataSource();
                ds.setName("pool-mysql");
                ds.setDescription("Pooling DataSource");
                ds.setDriverClassName("com.mysql.jdbc.Driver");
                ds.setUrl("jdbc:mysql://" + myfilePrefs.get("server", "not found") + "/" + myfilePrefs.get("db", "not found"));
                ds.setUser(myfilePrefs.get("user", "not found"));
                ds.setPassword(myfilePrefs.get("password", "not found"));
                ds.setMinPool(1);
                ds.setMaxPool(10);
                ds.setMaxSize(60);
                ds.setIdleTimeout(5);
                System.out.println("Check Connection Properties From Preference :: " + ds.toString());
            }

        } catch (IOException ex) {
            logger.info("Error While Connecting with DBPool Properties file :=> " + ex.toString());

        }
    }

    /**
     * Creates/Provides the instance of the Pool.
     *
     * @return DBPool
     */
    public static DBPool getInstance() {
        if (dbPool == null) {
            dbPool = new DBPool();
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

            if (myfilePrefs.get("server", "not found").equalsIgnoreCase("not found") || myfilePrefs.get("db", "not found").equalsIgnoreCase("not found")
                    || myfilePrefs.get("user", "not found").equalsIgnoreCase("not found") || myfilePrefs.get("password", "not found").equalsIgnoreCase("not found")
                    || myfilePrefs.get("server", "not found").equalsIgnoreCase("") || myfilePrefs.get("db", "").equalsIgnoreCase("")
                    || myfilePrefs.get("user", "not found").equalsIgnoreCase("") || myfilePrefs.get("password", "not found").equalsIgnoreCase("")) {
                /* ====================== If no preference Values ====================== */
                con = connManager.getConnection(databaseName);

            } else {
                /* ====================== Create connection From Preference  ====================== */
                logger.info("Server (Pref) ::" + myfilePrefs.get("server", "not found"));
                System.out.println("Check Preference Connection :: " + ds.toString());
                con = ds.getConnection();

            }

            //  logger.info("Connection Created: " + con.toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
            logger.info("Error While Creating Connection :=> " + ex.toString());
        }
        if (con != null) {
            this.conn = con;
            //  logger.info("Connection Released: " + this.conn.toString());
            return con;
        } else {
            return con;
        }
    }

    /* ======================== Method for Closing connections ==================== */

    /**
     *
     * @return
     */

    public int CloseConnections() {
        System.out.println("Test Connection Close Calll...........");
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        int row = 0;
        try {
            String user = "root";
            if (myfilePrefs.get("user", "not found").equalsIgnoreCase("not found")) {
                user = myfilePrefs.get("user", "not found");
            } else {
                Properties prop = new Properties();
                InputStream input = null;
                try {
                    input = new FileInputStream("DBPool.properties");
                    prop.load(input);
                    user = prop.getProperty("pool-mysql.user");
                } catch (FileNotFoundException ex) {
                    logger.error(ex);
                    user = "root";
                } catch (IOException ex) {
                    logger.error(ex);
                    user = "root";
                }
            }
            String query = "SELECT CONCAT('KILL ',ID,';')\n"
                    + "FROM   INFORMATION_SCHEMA.PROCESSLIST\n"
                    + "WHERE  USER = '" + user + "'\n"
                    + "       AND COMMAND = 'Sleep'\n"
                    + "       AND TIME > 50";
            
            String sql = "SELECT CONCAT('KILL ',ID,';')\n"
                    + "FROM   INFORMATION_SCHEMA.PROCESSLIST\n"
                    + "WHERE   COMMAND = 'Sleep'\n"
                    + "       AND TIME > 50";
            System.out.println("KIll Query :: " + sql);
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                stmt.addBatch(rs.getString(1));
                System.out.println("Killing Process id :: " + rs.getString(1));
            }
            stmt.executeBatch();
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
