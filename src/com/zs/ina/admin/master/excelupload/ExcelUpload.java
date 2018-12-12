/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.admin.master.excelupload;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import zs.com.ina.db.mysql.pool.DBPool;

/**
 *
 * @author user
 */
public class ExcelUpload {

    String fileName = null;
    String tableName;

    /**
     *
     * @param tblName
     * @throws SQLException
     */
    public void exelFileChooser(String tblName) throws SQLException {
        JFileChooser fc = new JFileChooser();
        int result = fc.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            fileName = fc.getSelectedFile().getAbsolutePath();
            tableName = tblName;
            if (tblName.equals("branch_tbl")) {
                branchExelUpload();
            } else if (tblName.equals("source_tbl")) {
                sourceExelUpload();
            } else if (tblName.equals("agencies_tbl")) {
                agencyExelUpload();
            } else if (tblName.equals("role_tbl")) {
                roleExelUpload();
            } else if (tblName.equals("mastertbl_language")) {
                languageExelUpload();
            } else if (tblName.equals("mastertbl_other_test")) {
                otherTestExelUpload();
            } else if (tblName.equals("mastertbl_score_management")) {
                scoreExelUpload();
            } else if (tblName.equals("mastertbl_timing")) {
                timingExelUpload();
            } else if (tblName.equals("mastertbl_institute")) {
                instituteExelUpload();
            } else if (tblName.equals("mastertbl_salary")) {
                salaryExelUpload();
            } else if (tblName.equals("mastertbl_experience_duration")) {
                experienceExelUpload();
            } else if (tblName.equals("mastertbl_qualification_duration")) {
                qualificationDurationExelUpload();
            } else if (tblName.equals("mastertbl_course_management")) {
                courseExelUpload();
            } else if (tblName.equals("mastertbl_migration_category")) {
                migrationCategoryUpload();
            } else if (tblName.equals("mastertbl_course_type")) {
                migrationCategoryUpload();
            } else if (tblName.equals("mastertbl_batch_timing")) {
                batchTimingUpload();
            } else if (tblName.equals("mastertbl_profession")) {
                professionUpload();
            } else if (tblName.equals("mastertbl_jobtype")) {
                jobTypeUpload();
            } else if (tblName.equals("mastertbl_exam_board")) {
                examBoardUpload();
            }

        }
    }
//Method to upload branch table excel file

    /**
     *
     * @throws SQLException
     */
    public void branchExelUpload() throws SQLException {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        String query, query2, query3 = null;
        PreparedStatement ps = null;
        try {
            query2 = "Select  *from " + tableName + "";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query2);
        } catch (SQLException sqle) {
            query = "create table " + tableName + "(branch_id NOT NULL AUTO_INCREMENT,branch_name VARCHAR(50),location VARCHAR(50),"
                    + "address VARCHAR(50),company_owner VARCHAR(50),branch_owner VARCHAR(50),"
                    + "phone_number VARCHAR(50),PRIMARY KEY (branch_id))";
            ps = con.prepareStatement(query);
            ps.executeUpdate();
        }

        ArrayList dataHolder = readExcelFile(fileName);

        String query1 = "insert into " + tableName + "(branch_name,location,address,company_owner,branch_owner,phone_number)"
                + " values(?,?,?,?,?,?)";
//    PreparedStatement ps1 = null;
        ps = con.prepareStatement(query1);
        int count = 0;
        ArrayList cellStoreArrayList = null;
//For inserting into database
        System.out.println(dataHolder.size());
        for (int i = 1; i < dataHolder.size(); i++) {
            cellStoreArrayList = (ArrayList) dataHolder.get(i);
            ps.setString(1, ((Cell) cellStoreArrayList.get(0)).toString());
            ps.setString(2, ((Cell) cellStoreArrayList.get(1)).toString());
            ps.setString(3, ((Cell) cellStoreArrayList.get(2)).toString());
            ps.setString(4, ((Cell) cellStoreArrayList.get(3)).toString());
            ps.setString(5, ((Cell) cellStoreArrayList.get(4)).toString());
            ps.setString(6, ((Cell) cellStoreArrayList.get(5)).toString());
            count = ps.executeUpdate();
        }
        if (count > 0) {
            JOptionPane.showMessageDialog(null, "File uploaded successfully");
        }
    }
//Method to upload source table excel file

    /**
     *
     * @throws SQLException
     */
    public void sourceExelUpload() throws SQLException {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        String query, query2, query3 = null;
        PreparedStatement ps = null;
        try {
            query2 = "Select  *from " + tableName + "";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query2);
        } catch (SQLException sqle) {
            query = "create table " + tableName + "(id NOT NULL AUTO_INCREMENT,source VARCHAR(50),description VARCHAR(50),"
                    + "PRIMARY KEY (id))";
            ps = con.prepareStatement(query);
            ps.executeUpdate();
        }

        ArrayList dataHolder = readExcelFile(fileName);

        String query1 = "insert into " + tableName + "(source,description)"
                + " values(?,?)";
//    PreparedStatement ps1 = null;
        ps = con.prepareStatement(query1);
        int count = 0;
        ArrayList cellStoreArrayList = null;
//For inserting into database
        System.out.println(dataHolder.size());
        for (int i = 1; i < dataHolder.size(); i++) {
            cellStoreArrayList = (ArrayList) dataHolder.get(i);
            ps.setString(1, ((Cell) cellStoreArrayList.get(0)).toString());
            ps.setString(2, ((Cell) cellStoreArrayList.get(1)).toString());
            count = ps.executeUpdate();
        }
        if (count > 0) {
            JOptionPane.showMessageDialog(null, "File uploaded successfully");
        }
    }

    //Method to upload source table excel file

    /**
     *
     * @throws SQLException
     */
    public void agencyExelUpload() throws SQLException {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        String query, query2, query3 = null;
        PreparedStatement ps = null;
        try {
            query2 = "Select  *from " + tableName + "";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query2);
        } catch (SQLException sqle) {
            query = "create table " + tableName + "(id NOT NULL AUTO_INCREMENT,username VARCHAR(50),branch_id int,lastname,"
                    + "firstname,email,phone,country,address"
                    + "PRIMARY KEY (id))";
            ps = con.prepareStatement(query);
            ps.executeUpdate();
        }

        ArrayList dataHolder = readExcelFile(fileName);

        String query1 = "insert into " + tableName + "(username,branch_id,lastname,firstname,email,phone,country,address)"
                + " values(?,(select branch_id from branch_tbl where branch_name=?),?,?,?,?,?,?)";
//    PreparedStatement ps1 = null;
        ps = con.prepareStatement(query1);
        int count = 0;
        ArrayList cellStoreArrayList = null;
//For inserting into database
        System.out.println(dataHolder.size());
        for (int i = 1; i < dataHolder.size(); i++) {
            cellStoreArrayList = (ArrayList) dataHolder.get(i);
            ps.setString(1, ((Cell) cellStoreArrayList.get(0)).toString());
            ps.setString(2, ((Cell) cellStoreArrayList.get(1)).toString());
            ps.setString(3, ((Cell) cellStoreArrayList.get(2)).toString());
            ps.setString(4, ((Cell) cellStoreArrayList.get(3)).toString());
            ps.setString(5, ((Cell) cellStoreArrayList.get(4)).toString());
            ps.setString(6, ((Cell) cellStoreArrayList.get(5)).toString());
            ps.setString(7, ((Cell) cellStoreArrayList.get(6)).toString());
            ps.setString(8, ((Cell) cellStoreArrayList.get(7)).toString());
            count = ps.executeUpdate();
        }
        if (count > 0) {
            JOptionPane.showMessageDialog(null, "File uploaded successfully");
        }

    }

    //Method to upload role table excel file

    /**
     *
     * @throws SQLException
     */
    public void roleExelUpload() throws SQLException {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        String query, query2, query3 = null;
        PreparedStatement ps = null;
        try {
            query2 = "Select  *from " + tableName + "";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query2);
        } catch (SQLException sqle) {
            query = "create table " + tableName + "(role_id NOT NULL AUTO_INCREMENT,role VARCHAR(50),privilage_id int,"
                    + "PRIMARY KEY (role_id))";
            ps = con.prepareStatement(query);
            ps.executeUpdate();
        }

        ArrayList dataHolder = readExcelFile(fileName);

        String query1 = "insert into " + tableName + "(role,privilage_id)"
                + " values(?,(select privilage_id from privilage_tbl where privilage=?))";
//    PreparedStatement ps1 = null;
        ps = con.prepareStatement(query1);
        int count = 0;
        ArrayList cellStoreArrayList = null;
//For inserting into database
        System.out.println(dataHolder.size());
        for (int i = 1; i < dataHolder.size(); i++) {
            cellStoreArrayList = (ArrayList) dataHolder.get(i);
            ps.setString(1, ((Cell) cellStoreArrayList.get(0)).toString());
            ps.setString(2, ((Cell) cellStoreArrayList.get(1)).toString());
            count = ps.executeUpdate();
        }
        if (count > 0) {
            JOptionPane.showMessageDialog(null, "File uploaded successfully");
        }

    }

    //Method to upload language excel

    /**
     *
     * @throws SQLException
     */
    public void languageExelUpload() throws SQLException {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        String query, query2, query3 = null;
        PreparedStatement ps = null;
        try {
            query2 = "Select  *from " + tableName + "";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query2);
        } catch (SQLException sqle) {
            query = "create table " + tableName + "(language_id NOT NULL AUTO_INCREMENT,language VARCHAR(50),PRIMARY KEY (language_id))";
            ps = con.prepareStatement(query);
            ps.executeUpdate();
        }

        ArrayList dataHolder = readExcelFile(fileName);

        String query1 = "insert into " + tableName + "(language)"
                + " values(?)";
//    PreparedStatement ps1 = null;
        ps = con.prepareStatement(query1);
        int count = 0;
        ArrayList cellStoreArrayList = null;
//For inserting into database
        System.out.println(dataHolder.size());
        for (int i = 1; i < dataHolder.size(); i++) {
            cellStoreArrayList = (ArrayList) dataHolder.get(i);
            ps.setString(1, ((Cell) cellStoreArrayList.get(0)).toString());
            count = ps.executeUpdate();
        }
        if (count > 0) {
            JOptionPane.showMessageDialog(null, "File uploaded successfully");
        } else {
            JOptionPane.showMessageDialog(null, "Error");
        }
    }

    //Method to upload language excel

    /**
     *
     * @throws SQLException
     */
    public void otherTestExelUpload() throws SQLException {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        String query, query2, query3 = null;
        PreparedStatement ps = null;
        try {
            query2 = "Select  *from " + tableName + "";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query2);
        } catch (SQLException sqle) {
            query = "create table " + tableName + "(other_test_id NOT NULL AUTO_INCREMENT,other_test VARCHAR(50),PRIMARY KEY (other_test_id))";
            ps = con.prepareStatement(query);
            ps.executeUpdate();
        }

        ArrayList dataHolder = readExcelFile(fileName);

        String query1 = "insert into " + tableName + "(other_test)"
                + " values(?)";
//    PreparedStatement ps1 = null;
        ps = con.prepareStatement(query1);
        int count = 0;
        ArrayList cellStoreArrayList = null;
//For inserting into database
        System.out.println(dataHolder.size());
        for (int i = 1; i < dataHolder.size(); i++) {
            cellStoreArrayList = (ArrayList) dataHolder.get(i);
            ps.setString(1, ((Cell) cellStoreArrayList.get(0)).toString());
            count = ps.executeUpdate();
        }
        if (count > 0) {
            JOptionPane.showMessageDialog(null, "File uploaded successfully");
        } else {
            JOptionPane.showMessageDialog(null, "Error");
        }
    }

    //Method to upload language excel

    /**
     *
     * @throws SQLException
     */
    public void scoreExelUpload() throws SQLException {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        String query, query2, query3 = null;
        PreparedStatement ps = null;
        try {
            query2 = "Select  *from " + tableName + "";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query2);
        } catch (SQLException sqle) {
            query = "create table " + tableName + "(score_id NOT NULL AUTO_INCREMENT,score VARCHAR(50),PRIMARY KEY (other_test_id))";
            ps = con.prepareStatement(query);
            ps.executeUpdate();
        }

        ArrayList dataHolder = readExcelFile(fileName);

        String query1 = "insert into " + tableName + "(score)"
                + " values(?)";
//    PreparedStatement ps1 = null;
        ps = con.prepareStatement(query1);
        int count = 0;
        ArrayList cellStoreArrayList = null;
//For inserting into database
        System.out.println(dataHolder.size());
        for (int i = 1; i < dataHolder.size(); i++) {
            cellStoreArrayList = (ArrayList) dataHolder.get(i);
            ps.setString(1, ((Cell) cellStoreArrayList.get(0)).toString());
            count = ps.executeUpdate();
        }
        if (count > 0) {
            JOptionPane.showMessageDialog(null, "File uploaded successfully");
        } else {
            JOptionPane.showMessageDialog(null, "Error");
        }
    }

    //Method to upload language excel

    /**
     *
     * @throws SQLException
     */
    public void timingExelUpload() throws SQLException {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        String query, query2, query3 = null;
        PreparedStatement ps = null;
        try {
            query2 = "Select  *from " + tableName + "";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query2);
        } catch (SQLException sqle) {
            query = "create table " + tableName + "(timing_id NOT NULL AUTO_INCREMENT,timing VARCHAR(50),PRIMARY KEY (timing_id))";
            ps = con.prepareStatement(query);
            ps.executeUpdate();
        }

        ArrayList dataHolder = readExcelFile(fileName);

        String query1 = "insert into " + tableName + "(timing)"
                + " values(?)";
//    PreparedStatement ps1 = null;
        ps = con.prepareStatement(query1);
        int count = 0;
        ArrayList cellStoreArrayList = null;
//For inserting into database
        System.out.println(dataHolder.size());
        for (int i = 1; i < dataHolder.size(); i++) {
            cellStoreArrayList = (ArrayList) dataHolder.get(i);
            ps.setString(1, ((Cell) cellStoreArrayList.get(0)).toString());
            count = ps.executeUpdate();
        }
        if (count > 0) {
            JOptionPane.showMessageDialog(null, "File uploaded successfully");
        } else {
            JOptionPane.showMessageDialog(null, "Error");
        }
    }

    private static ArrayList readExcelFile(String fileName) {
        ArrayList cellArrayLisstHolder = new ArrayList();
        try {
            // FileInputStream myInput = new FileInputStream(fileName);
            XSSFWorkbook myWorkbook = new XSSFWorkbook(fileName);
            XSSFSheet mySheet = myWorkbook.getSheetAt(0);
            Iterator<Row> rowIterator = mySheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                ArrayList cellStoreArrayList = new ArrayList();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    cellStoreArrayList.add(cell);
                }
                cellArrayLisstHolder.add(cellStoreArrayList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cellArrayLisstHolder;
    }

    private void instituteExelUpload() {
        try {
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            con = DBPool.getInstance().getConn();
            String query, query2, query3 = null;
            PreparedStatement ps = null;
            try {
                query2 = "Select  *from " + tableName + "";
                stmt = con.createStatement();
                rs = stmt.executeQuery(query2);
            } catch (SQLException sqle) {
                try {
                    query = "create table " + tableName + "(institute_id NOT NULL AUTO_INCREMENT,institute VARCHAR(50),country VARCHAR(50), PRIMARY KEY (institute_id))";
                    ps = con.prepareStatement(query);
                    ps.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(ExcelUpload.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            ArrayList dataHolder = readExcelFile(fileName);

            String query1 = "insert into " + tableName + "(institute,country)"
                    + " values(?,?)";
//    PreparedStatement ps1 = null;
            ps = con.prepareStatement(query1);
            int count = 0;
            ArrayList cellStoreArrayList = null;
//For inserting into database
            System.out.println(dataHolder.size());
            for (int i = 1; i < dataHolder.size(); i++) {
                cellStoreArrayList = (ArrayList) dataHolder.get(i);
                ps.setString(1, ((Cell) cellStoreArrayList.get(0)).toString());
                ps.setString(2, ((Cell) cellStoreArrayList.get(1)).toString());
                count = ps.executeUpdate();
            }
            if (count > 0) {
                JOptionPane.showMessageDialog(null, "File uploaded successfully");
            } else {
                JOptionPane.showMessageDialog(null, "Error");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExcelUpload.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void salaryExelUpload() {
        try {
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            con = DBPool.getInstance().getConn();
            String query, query2, query3 = null;
            PreparedStatement ps = null;
            try {
                query2 = "Select  *from " + tableName + "";
                stmt = con.createStatement();
                rs = stmt.executeQuery(query2);
            } catch (SQLException sqle) {
                try {
                    query = "create table " + tableName + "(salary_id NOT NULL AUTO_INCREMENT,salary VARCHAR(50),PRIMARY KEY (salary_id))";
                    ps = con.prepareStatement(query);
                    ps.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(ExcelUpload.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            ArrayList dataHolder = readExcelFile(fileName);

            String query1 = "insert into " + tableName + "(salary)"
                    + " values(?)";
//    PreparedStatement ps1 = null;
            ps = con.prepareStatement(query1);
            int count = 0;
            ArrayList cellStoreArrayList = null;
//For inserting into database
            System.out.println(dataHolder.size());
            for (int i = 1; i < dataHolder.size(); i++) {
                cellStoreArrayList = (ArrayList) dataHolder.get(i);
                ps.setString(1, ((Cell) cellStoreArrayList.get(0)).toString());
                count = ps.executeUpdate();
            }
            if (count > 0) {
                JOptionPane.showMessageDialog(null, "File uploaded successfully");
            } else {
                JOptionPane.showMessageDialog(null, "Error");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExcelUpload.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void experienceExelUpload() {
        try {
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            con = DBPool.getInstance().getConn();
            String query, query2, query3 = null;
            PreparedStatement ps = null;
            try {
                query2 = "Select  *from " + tableName + "";
                stmt = con.createStatement();
                rs = stmt.executeQuery(query2);
            } catch (SQLException sqle) {
                try {
                    query = "create table " + tableName + "(experience_duration_id NOT NULL AUTO_INCREMENT,duration VARCHAR(50),PRIMARY KEY (experience_duration_id))";
                    ps = con.prepareStatement(query);
                    ps.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(ExcelUpload.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            ArrayList dataHolder = readExcelFile(fileName);

            String query1 = "insert into " + tableName + "(duration)"
                    + " values(?)";
//    PreparedStatement ps1 = null;
            ps = con.prepareStatement(query1);
            int count = 0;
            ArrayList cellStoreArrayList = null;
//For inserting into database
            System.out.println(dataHolder.size());
            for (int i = 1; i < dataHolder.size(); i++) {
                cellStoreArrayList = (ArrayList) dataHolder.get(i);
                ps.setString(1, ((Cell) cellStoreArrayList.get(0)).toString());
                count = ps.executeUpdate();
            }
            if (count > 0) {
                JOptionPane.showMessageDialog(null, "File uploaded successfully");
            } else {
                JOptionPane.showMessageDialog(null, "Error");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExcelUpload.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void qualificationDurationExelUpload() {
        try {
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            con = DBPool.getInstance().getConn();
            String query, query2, query3 = null;
            PreparedStatement ps = null;
            try {
                query2 = "Select  *from " + tableName + "";
                stmt = con.createStatement();
                rs = stmt.executeQuery(query2);
            } catch (SQLException sqle) {
                try {
                    query = "create table " + tableName + "(duration_id NOT NULL AUTO_INCREMENT,duration VARCHAR(50),PRIMARY KEY (duration_id))";
                    ps = con.prepareStatement(query);
                    ps.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(ExcelUpload.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            ArrayList dataHolder = readExcelFile(fileName);

            String query1 = "insert into " + tableName + "(duration)"
                    + " values(?)";
//    PreparedStatement ps1 = null;
            ps = con.prepareStatement(query1);
            int count = 0;
            ArrayList cellStoreArrayList = null;
//For inserting into database
            System.out.println(dataHolder.size());
            for (int i = 1; i < dataHolder.size(); i++) {
                cellStoreArrayList = (ArrayList) dataHolder.get(i);
                ps.setString(1, ((Cell) cellStoreArrayList.get(0)).toString());
                count = ps.executeUpdate();
            }
            if (count > 0) {
                JOptionPane.showMessageDialog(null, "File uploaded successfully");
            } else {
                JOptionPane.showMessageDialog(null, "Error");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExcelUpload.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void courseExelUpload() {
        try {
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            con = DBPool.getInstance().getConn();
            String query, query2, query3 = null;
            PreparedStatement ps = null;
            try {
                query2 = "Select  *from " + tableName + "";
                stmt = con.createStatement();
                rs = stmt.executeQuery(query2);
            } catch (SQLException sqle) {
                try {
                    query = "create table " + tableName + "(course_id NOT NULL AUTO_INCREMENT,course VARCHAR(50),PRIMARY KEY (course_id))";
                    ps = con.prepareStatement(query);
                    ps.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(ExcelUpload.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            ArrayList dataHolder = readExcelFile(fileName);

            String query1 = "insert into " + tableName + "(course)"
                    + " values(?)";
//    PreparedStatement ps1 = null;
            ps = con.prepareStatement(query1);
            int count = 0;
            ArrayList cellStoreArrayList = null;
//For inserting into database
            System.out.println(dataHolder.size());
            for (int i = 1; i < dataHolder.size(); i++) {
                cellStoreArrayList = (ArrayList) dataHolder.get(i);
                ps.setString(1, ((Cell) cellStoreArrayList.get(0)).toString());
                count = ps.executeUpdate();
            }
            if (count > 0) {
                JOptionPane.showMessageDialog(null, "File uploaded successfully");
            } else {
                JOptionPane.showMessageDialog(null, "Error");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExcelUpload.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void migrationCategoryUpload() {
        try {
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            con = DBPool.getInstance().getConn();
            String query, query2, query3 = null;
            PreparedStatement ps = null;
            try {
                query2 = "Select  *from " + tableName + "";
                stmt = con.createStatement();
                rs = stmt.executeQuery(query2);
            } catch (SQLException sqle) {
                try {
                    query = "create table " + tableName + "(migrate_category_id NOT NULL AUTO_INCREMENT,migrate_category VARCHAR(50), PRIMARY KEY (migrate_category_id))";
                    ps = con.prepareStatement(query);
                    ps.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(ExcelUpload.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            ArrayList dataHolder = readExcelFile(fileName);

            String query1 = "insert into " + tableName + "(migrate_category)"
                    + " values(?)";
//    PreparedStatement ps1 = null;
            ps = con.prepareStatement(query1);
            int count = 0;
            ArrayList cellStoreArrayList = null;
//For inserting into database
            System.out.println(dataHolder.size());
            for (int i = 1; i < dataHolder.size(); i++) {
                cellStoreArrayList = (ArrayList) dataHolder.get(i);
                ps.setString(1, ((Cell) cellStoreArrayList.get(0)).toString());
                ps.setString(2, ((Cell) cellStoreArrayList.get(1)).toString());
                count = ps.executeUpdate();
            }
            if (count > 0) {
                JOptionPane.showMessageDialog(null, "File uploaded successfully");
            } else {
                JOptionPane.showMessageDialog(null, "Error");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExcelUpload.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void courseTypeExcelUpload() {
        try {
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            con = DBPool.getInstance().getConn();
            String query, query2, query3 = null;
            PreparedStatement ps = null;
            try {
                query2 = "Select  *from " + tableName + "";
                stmt = con.createStatement();
                rs = stmt.executeQuery(query2);
            } catch (SQLException sqle) {
                try {
                    query = "create table " + tableName + "(course_id NOT NULL AUTO_INCREMENT,course_type VARCHAR(50), PRIMARY KEY (course_id))";
                    ps = con.prepareStatement(query);
                    ps.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(ExcelUpload.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            ArrayList dataHolder = readExcelFile(fileName);

            String query1 = "insert into " + tableName + "(course_type)"
                    + " values(?)";
//    PreparedStatement ps1 = null;
            ps = con.prepareStatement(query1);
            int count = 0;
            ArrayList cellStoreArrayList = null;
//For inserting into database
            System.out.println(dataHolder.size());
            for (int i = 1; i < dataHolder.size(); i++) {
                cellStoreArrayList = (ArrayList) dataHolder.get(i);
                ps.setString(1, ((Cell) cellStoreArrayList.get(0)).toString());
                ps.setString(2, ((Cell) cellStoreArrayList.get(1)).toString());
                count = ps.executeUpdate();
            }
            if (count > 0) {
                JOptionPane.showMessageDialog(null, "File uploaded successfully");
            } else {
                JOptionPane.showMessageDialog(null, "Error");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExcelUpload.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void batchTimingUpload() {
        try {
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            con = DBPool.getInstance().getConn();
            String query, query2, query3 = null;
            PreparedStatement ps = null;
            try {
                query2 = "Select  *from " + tableName + "";
                stmt = con.createStatement();
                rs = stmt.executeQuery(query2);
            } catch (SQLException sqle) {
                try {
                    query = "create table " + tableName + "(time_id NOT NULL AUTO_INCREMENT,timing VARCHAR(50), PRIMARY KEY (time_id))";
                    ps = con.prepareStatement(query);
                    ps.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(ExcelUpload.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            ArrayList dataHolder = readExcelFile(fileName);

            String query1 = "insert into " + tableName + "(timing)"
                    + " values(?)";
//    PreparedStatement ps1 = null;
            ps = con.prepareStatement(query1);
            int count = 0;
            ArrayList cellStoreArrayList = null;
//For inserting into database
            System.out.println(dataHolder.size());
            for (int i = 1; i < dataHolder.size(); i++) {
                cellStoreArrayList = (ArrayList) dataHolder.get(i);
                ps.setString(1, ((Cell) cellStoreArrayList.get(0)).toString());
                count = ps.executeUpdate();
            }
            if (count > 0) {
                JOptionPane.showMessageDialog(null, "File uploaded successfully");
            } else {
                JOptionPane.showMessageDialog(null, "Error");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExcelUpload.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void professionUpload() {
        try {
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            con = DBPool.getInstance().getConn();
            String query, query2, query3 = null;
            PreparedStatement ps = null;
            try {
                query2 = "Select  *from " + tableName + "";
                stmt = con.createStatement();
                rs = stmt.executeQuery(query2);
            } catch (SQLException sqle) {
                try {
                    query = "create table " + tableName + "(proffesion_id NOT NULL AUTO_INCREMENT,profession VARCHAR(50), PRIMARY KEY (proffesion_id))";
                    ps = con.prepareStatement(query);
                    ps.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(ExcelUpload.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            ArrayList dataHolder = readExcelFile(fileName);

            String query1 = "insert into " + tableName + "(profession)"
                    + " values(?)";
//    PreparedStatement ps1 = null;
            ps = con.prepareStatement(query1);
            int count = 0;
            ArrayList cellStoreArrayList = null;
//For inserting into database
            System.out.println(dataHolder.size());
            for (int i = 1; i < dataHolder.size(); i++) {
                cellStoreArrayList = (ArrayList) dataHolder.get(i);
                ps.setString(1, ((Cell) cellStoreArrayList.get(0)).toString());
                count = ps.executeUpdate();
            }
            if (count > 0) {
                JOptionPane.showMessageDialog(null, "File uploaded successfully");
            } else {
                JOptionPane.showMessageDialog(null, "Error");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExcelUpload.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void jobTypeUpload() {
        try {
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            con = DBPool.getInstance().getConn();
            String query, query2, query3 = null;
            PreparedStatement ps = null;
            try {
                query2 = "Select  *from " + tableName + "";
                stmt = con.createStatement();
                rs = stmt.executeQuery(query2);
            } catch (SQLException sqle) {
                try {
                    query = "create table " + tableName + "(jobtype_id NOT NULL AUTO_INCREMENT,jobtype VARCHAR(50), PRIMARY KEY (jobtype_id))";
                    ps = con.prepareStatement(query);
                    ps.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(ExcelUpload.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            ArrayList dataHolder = readExcelFile(fileName);

            String query1 = "insert into " + tableName + "(jobtype)"
                    + " values(?)";
//    PreparedStatement ps1 = null;
            ps = con.prepareStatement(query1);
            int count = 0;
            ArrayList cellStoreArrayList = null;
//For inserting into database
            System.out.println(dataHolder.size());
            for (int i = 1; i < dataHolder.size(); i++) {
                cellStoreArrayList = (ArrayList) dataHolder.get(i);
                ps.setString(1, ((Cell) cellStoreArrayList.get(0)).toString());
                count = ps.executeUpdate();
            }
            if (count > 0) {
                JOptionPane.showMessageDialog(null, "File uploaded successfully");
            } else {
                JOptionPane.showMessageDialog(null, "Error");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExcelUpload.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void examBoardUpload() {
        try {
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            con = DBPool.getInstance().getConn();
            String query, query2, query3 = null;
            PreparedStatement ps = null;
            try {
                query2 = "Select  *from " + tableName + "";
                stmt = con.createStatement();
                rs = stmt.executeQuery(query2);
            } catch (SQLException sqle) {
                try {
                    query = "create table " + tableName + "(exam_board_id NOT NULL AUTO_INCREMENT,exam_board VARCHAR(50), PRIMARY KEY (exam_board_id))";
                    ps = con.prepareStatement(query);
                    ps.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(ExcelUpload.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            ArrayList dataHolder = readExcelFile(fileName);

            String query1 = "insert into " + tableName + "(exam_board)"
                    + " values(?)";
//    PreparedStatement ps1 = null;
            ps = con.prepareStatement(query1);
            int count = 0;
            ArrayList cellStoreArrayList = null;
//For inserting into database
            System.out.println(dataHolder.size());
            for (int i = 1; i < dataHolder.size(); i++) {
                cellStoreArrayList = (ArrayList) dataHolder.get(i);
                ps.setString(1, ((Cell) cellStoreArrayList.get(0)).toString());
                count = ps.executeUpdate();
            }
            if (count > 0) {
                JOptionPane.showMessageDialog(null, "File uploaded successfully");
            } else {
                JOptionPane.showMessageDialog(null, "Error");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExcelUpload.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
