/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import zs.com.ina.db.mysql.pool.DBPool;

/**
 *
 * @author zoft
 */
public class NewClass {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            String DB_URL = "jdbc:mysql://192.168.30.20:3306/ia";

            //  Database credentials
            String USER = "root";
            String PASS = "";

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);

            if (con
                    != null) {

                JOptionPane.showMessageDialog(null, "con");
            } else {
                JOptionPane.showMessageDialog(null, "no con");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
