/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;

/**
 *
 * @author 100018
 */
public class ChangePropertiesFile {

    /**
     *
     * @param filelocation
     * @param server
     * @param database
     * @param dbuser
     * @param dbpass
     * @throws URISyntaxException
     */
    public static void changePropertiesFile(String filelocation, String server, String database, String dbuser, String dbpass) throws URISyntaxException {

        Properties prop = new Properties();
        OutputStream output = null;
        ClassLoader cLoader = ClassLoader.getSystemClassLoader();
        URL url = Class.class.getResource("DBPool.properties");
        File file = new File(url.toURI());
        try {
            output = new FileOutputStream(file);
            prop.setProperty("name", "pool-mysql");
            prop.setProperty("drivers", "com.mysql.jdbc.Driver");
            prop.setProperty("pool-mysql.url", server + "/" + database);
            prop.setProperty("pool-mysql.user", dbuser);
            prop.setProperty("pool-mysql.password", dbpass);
            prop.setProperty("pool-mysql.validator", "snaq.db.AutoCommitValidator");
            prop.setProperty("pool-mysql.minpool", "1");
            prop.setProperty("pool-mysql.maxpool", "40");
            prop.setProperty("pool-mysql.maxsize", "100");
            prop.setProperty("pool-mysql.idleTimeout", "10");
            prop.setProperty("pool-mysql.prop.useUnicode", "true");
            prop.setProperty("pool-mysql.prop.characterEncoding", "UTF-8");
            //  prop.store(output, null);
            store(prop, filelocation);
        } catch (FileNotFoundException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    /**
     *
     * @param props
     * @param propertyFilePath
     * @throws FileNotFoundException
     */
    public static void store(Properties props, String propertyFilePath)
            throws FileNotFoundException {

        try (PrintWriter pw = new PrintWriter(propertyFilePath)) {
            for (Enumeration e = props.propertyNames(); e.hasMoreElements();) {
                String key = (String) e.nextElement();
                pw.println(key + "=" + props.getProperty(key));
            }
        }
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        Properties prop = new Properties();
        OutputStream output = null;

        try {

            output = new FileOutputStream("src/config.properties");
            prop.setProperty("pool-mysql.url", "jdbc:mysql://192.168.0.16:3306/ia");

            prop.setProperty("pool-mysql.user", "root");
            prop.setProperty("pool-mysql.password", "root");

            prop.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
