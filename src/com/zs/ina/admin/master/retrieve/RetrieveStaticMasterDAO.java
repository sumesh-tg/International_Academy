/*
 * Copyright (C) 2016 100018
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
package com.zs.ina.admin.master.retrieve;

import com.zs.ina.common.constants.TableNames;
import com.zs.ina.utility.ClosingResourcesInDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.log4j.Logger;
import zs.com.ina.db.mysql.pool.DBPool;

/**
 *
 * @author 100018
 */
public class RetrieveStaticMasterDAO {

    private static final String TBL_ENQ_SUB_STATUS = "mastertbl_task_follow_status";
    static Logger logger = Logger.getLogger(RetrieveStaticMasterDAO.class);

    /**
     *
     * @return
     */
    public static ObservableList<EnquirySatusPOJO> retrieveEnquiryAppStatus() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ObservableList<EnquirySatusPOJO> obsEnqStatus = FXCollections.observableArrayList();
        try {
            con = DBPool.getInstance().getConn();
            String insertQuery = "SELECT * from " + TableNames.TABLE_MASTER_APP_STATUS + " where enable=1 order by app_status_id asc";
            stmt = con.createStatement();
            rs = stmt.executeQuery(insertQuery);
            while (rs.next()) {
                EnquirySatusPOJO as = new EnquirySatusPOJO();
                as.setId(rs.getString("app_status_id"));
                as.setStatus(rs.getString("app_status"));
                as.setDateReasonEnable(rs.getString("date_reason_enable"));
                as.setSubject(rs.getString("subject"));
                as.setEmailBody(rs.getString("email_body"));
                as.setEnable(rs.getString("enable"));
                as.setFrom(rs.getString("from_mail"));
                as.setProcessCompletionStatus(rs.getString("pro_completion_status"));
                obsEnqStatus.add(as);
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error(sqle.getStackTrace());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return obsEnqStatus;
    }

    /**
     *
     * @return
     */
    public static Map<String, ObservableList<String>> retrieveEnquiryMethod() {
        Map<String, ObservableList<String>> mapEnquiryMethod = new HashMap<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ObservableList<String> obsEnquiryMethods = FXCollections.observableArrayList();
        try {
            con = DBPool.getInstance().getConn();
            String insertQuery = "SELECT * FROM mastertbl_enquiry_methods;";
            stmt = con.createStatement();
            rs = stmt.executeQuery(insertQuery);
            while (rs.next()) {
                obsEnquiryMethods.add(rs.getString("method_name"));
                if (rs.getString("method_type") != null) {
                    if (!rs.getString("method_type").equalsIgnoreCase("")) {
                        mapEnquiryMethod.put(rs.getString("method_name"), obsEnquiryMethods);
                    }
                }
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error(sqle.getStackTrace());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return mapEnquiryMethod;
    }

    /**
     *
     * @return
     */
    public static ObservableList retrieveEnquiryStatus() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ObservableList obsEnqStatus = FXCollections.observableArrayList();
        try {
            con = DBPool.getInstance().getConn();
            String insertQuery = "SELECT DISTINCT(app_status) from " + TableNames.TABLE_MASTER_APP_STATUS;
            stmt = con.createStatement();
            rs = stmt.executeQuery(insertQuery);
            while (rs.next()) {
                obsEnqStatus.add(rs.getString("app_status"));
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error(sqle.getStackTrace());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return obsEnqStatus;
    }

    /**
     *
     * @return
     */
    public static ObservableList<SubStatusPOJO> retrieveEnquirySubStatus() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ObservableList<SubStatusPOJO> obsEnqStatus = FXCollections.observableArrayList();
        try {
            con = DBPool.getInstance().getConn();
            String insertQuery = "SELECT DISTINCT(task_follow_status),task_follow_id,enable from " + TBL_ENQ_SUB_STATUS;
            stmt = con.createStatement();
            rs = stmt.executeQuery(insertQuery);
            while (rs.next()) {
                SubStatusPOJO subStatusPOJO = new SubStatusPOJO();
                subStatusPOJO.setSubStatus(rs.getString("task_follow_status"));
                subStatusPOJO.setId(rs.getString("task_follow_id"));
                subStatusPOJO.setEnabled(rs.getString("enable"));
                obsEnqStatus.add(subStatusPOJO);
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error(sqle.getStackTrace());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return obsEnqStatus;
    }

    /**
     *
     * @return
     */
    public static ObservableList getUniversities() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ObservableList univerList = FXCollections.observableArrayList();
        con = DBPool.getInstance().getConn();
        try {
            String query = "select DISTINCT university  from mastertbl_university";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                univerList.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error(sqle.getStackTrace());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return univerList;
    }

    /**
     *
     * @return
     */
    public static ObservableList getMigrateCategories() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ObservableList migrateCategoryList = FXCollections.observableArrayList();
        con = DBPool.getInstance().getConn();
        try {
            String query = "select DISTINCT migrate_category  from mastertbl_migration_category";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                migrateCategoryList.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error(sqle.getStackTrace());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return migrateCategoryList;
    }

    /**
     *
     * @return
     */
    public static ObservableList getProfession() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ObservableList professionList = FXCollections.observableArrayList();
        con = DBPool.getInstance().getConn();
        try {
            String query = "select DISTINCT profession  from mastertbl_profession";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                professionList.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error(sqle.getStackTrace());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return professionList;
    }

    /**
     *
     * @return
     */
    public static ObservableList getTraings() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ObservableList trainingList = FXCollections.observableArrayList();
        con = DBPool.getInstance().getConn();
        try {
            String query = "select DISTINCT course  from mastertbl_course_management";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                trainingList.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error(sqle.getStackTrace());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return trainingList;
    }

    /**
     *
     * @return
     */
    public static ObservableList getBatches() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ObservableList batchList = FXCollections.observableArrayList();
        con = DBPool.getInstance().getConn();
        try {
            String query = "select DISTINCT timing  from mastertbl_batch_timing";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                batchList.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error(sqle.getStackTrace());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return batchList;
    }

    /**
     *
     * @return
     */
    public static ObservableList getDuration() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ObservableList duratopnList = FXCollections.observableArrayList();
        con = DBPool.getInstance().getConn();
        try {
            String query = "select DISTINCT duration  from mastertbl_experience_duration";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                duratopnList.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error(sqle.getStackTrace());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return duratopnList;
    }

    /**
     *
     * @return
     */
    public static ObservableList getExamBoards() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ObservableList otherTestList = FXCollections.observableArrayList();
        con = DBPool.getInstance().getConn();
        try {
            String query = "select DISTINCT exam_board  from mastertbl_exam_board";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                otherTestList.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error(sqle.getStackTrace());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return otherTestList;
    }

    /**
     *
     * @return
     */
    public static ObservableList getIndustries() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        ObservableList industryList = FXCollections.observableArrayList();
        String query = "select DISTINCT  industry from mastertbl_industry";
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                industryList.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error(sqle.getStackTrace());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return industryList;
    }

    /**
     *
     * @return
     */
    public static List<String> getIntake() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<String> intakeList = new ArrayList<>();
        con = DBPool.getInstance().getConn();
        try {
            String query = "select DISTINCT intake  from mastertbl_intake";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                intakeList.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error(sqle.getStackTrace());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return intakeList;
    }

    /**
     *
     * @return
     */
    public static ObservableList getLanguage() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ObservableList languageList = FXCollections.observableArrayList();
        con = DBPool.getInstance().getConn();
        try {
            String query = "select DISTINCT language from mastertbl_language";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                languageList.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error(sqle.getStackTrace());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return languageList;
    }
    //Method to get  assesment Timing

    //<------------------------Method to get test Scores------------------------->
    /**
     *
     * @return
     */
    public static List<String> getTestScore() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<String> intakeList = new ArrayList<>();
        con = DBPool.getInstance().getConn();
        try {
            String query = "select DISTINCT score  from mastertbl_score_management order by score asc";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                intakeList.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error(sqle.getStackTrace());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return intakeList;
    }

    /**
     *
     * @param value
     * @return
     */
    public static List<String> getProgramFields(String value) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<String> programFieldList = new ArrayList<>();
        con = DBPool.getInstance().getConn();
        try {
            String query = "SELECT p.program_field FROM mastertbl_program_field p,mastertbl_program_level pl WHERE p.program_level_id=pl.program_level_id AND\n" + "p.program_level_id=(SELECT program_level_id from mastertbl_program_level WHERE program_level=?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, value);
            rs = ps.executeQuery();
            while (rs.next()) {
                programFieldList.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error(sqle.getStackTrace());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return programFieldList;
    }

    //Method to get  assesment Timing
    /**
     *
     * @return
     */
    public static ObservableList getMaxSalary() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ObservableList salary = FXCollections.observableArrayList();
        con = DBPool.getInstance().getConn();
        String query = "Select Distinct(salary) from mastertbl_salary order by CAST(salary as signed) DESC";
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                salary.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error(sqle.getStackTrace());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return salary;
    }

    /**
     *
     * @return
     */
    public static ObservableList getScore() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ObservableList scoreList = FXCollections.observableArrayList();
        con = DBPool.getInstance().getConn();
        try {
            String query = "select DISTINCT score  from mastertbl_score_management";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                scoreList.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error(sqle.getStackTrace());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return scoreList;
    }

    /**
     *
     * @return
     */
    public static ObservableList getTimings() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ObservableList timings = FXCollections.observableArrayList();
        String query = "Select Distinct(timing) from mastertbl_timing";
        try {
            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                timings.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error(sqle.getStackTrace());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return timings;
    }

    //Method to get  assesment Timing
    /**
     *
     * @return
     */
    public static ObservableList getSalary() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ObservableList salary = FXCollections.observableArrayList();
        con = DBPool.getInstance().getConn();
        String query = "Select Distinct(salary) from mastertbl_salary order by CAST(salary as signed)";
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                salary.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error(sqle.getStackTrace());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return salary;
    }

    /**
     *
     * @return
     */
    public static List<String> getProgramLevels() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<String> programLevelList = new ArrayList<>();
        try {
            con = DBPool.getInstance().getConn();
            String query = "select DISTINCT program_level  from mastertbl_program_level ";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                programLevelList.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error(sqle.getStackTrace());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return programLevelList;
    }

    //<---------------------get country and currency names---------------------------------------------------------->
    /**
     *
     * @return
     */
    public static ObservableList getCurrencies() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ObservableList currencyList = FXCollections.observableArrayList();
        String query = "select concat(country,'(',currency,')') from mastertbl_currency_list";
        try {
            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                currencyList.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error(sqle.getStackTrace());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return currencyList;
    }
    //<------------------ Method to get Industry-------------------------------------------->

    //<---------------Method to get location-------------------------------->
    /**
     *
     * @param country
     * @return
     */
    public static ObservableList getLocation(String country) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        ObservableList locationList = FXCollections.observableArrayList();
        String query = "select DISTINCT  location from mastertbl_location where country='" + country + "'";
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                locationList.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error(sqle.getStackTrace());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return locationList;
    }

    /**
     *
     * @return
     */
    public static ObservableList getOtherTest() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ObservableList otherTestList = FXCollections.observableArrayList();
        con = DBPool.getInstance().getConn();
        try {
            String query = "select DISTINCT other_test  from mastertbl_other_test";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                otherTestList.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error(sqle.getStackTrace());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return otherTestList;
    }

    //<------------------------Method to get application type----------->
    /**
     *
     * @return
     */
    public static ObservableList getApplicationType() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        ObservableList applicationList = FXCollections.observableArrayList();
        try {
            String query = "select DISTINCT application  from mastertbl_application_type ";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                applicationList.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error(sqle.getStackTrace());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return applicationList;
    }

    /**
     *
     * @param age
     * @param assesmentMigratePOJO
     * @return 
     */
    public static int insertAge(String age) {
        Connection con = null;
        con = DBPool.getInstance().getConn();
        int row = 0;
        String query = "INSERT INTO mastertbl_age (age_id, age)\n"
                + "VALUES\n"
                + "	(0, '" + age + "');";
        try {
            PreparedStatement ps = con.prepareStatement(query);
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
    public static List<String> getAllages() {
        Boolean a = false;
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<String> ageList = new ArrayList<>();
        String query = "select age from mastertbl_age";
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                ageList.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error(sqle.getStackTrace());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return ageList;
    }

    /**
     *
     * @return
     */
    public static Map<String, ObservableList<String>> getLanguageTestScore() {
        ObservableList<String> testList = FXCollections.observableArrayList();
        ObservableList<String> scoreList = FXCollections.observableArrayList();
        Map<String, ObservableList<String>> mapLangTestScore = new HashMap<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String test = null;
        String score = null;
        con = DBPool.getInstance().getConn();
        String query = "SELECT\n" + "\tGROUP_CONCAT(DISTINCT test) as test,\n" + "\tGROUP_CONCAT(DISTINCT overall) as score\n" + "FROM\n" + "\t" + TableNames.TABLE_ENQ_ASMNT_LANG_TEST + "\n" + "ORDER BY\n" + "\toverall DESC";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                test = rs.getString("test");
                score = rs.getString("score");
            }
            if (test != null) {
                for (String s : test.split(",")) {
                    testList.add(s);
                }
            }
            if (score != null) {
                for (String s : score.split(",")) {
                    scoreList.add(s);
                }
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error(sqle.getStackTrace());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        mapLangTestScore.put("test", testList);
        mapLangTestScore.put("score", scoreList);
        return mapLangTestScore;
    }

    /**
     *
     * @return
     */
    public static List<String> getMobCodes() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<String> stdCodes = new ArrayList<>();
        try {
            String query = "select CONCAT(phonecode,'-',nicename) from country_codes_tbl";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                stdCodes.add(rs.getString(1));
            }
            String query1 = "select distinct CONCAT(std_code,'-',place) from std_code_tbl";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query1);
            while (rs.next()) {
                stdCodes.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error(sqle.getStackTrace());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return stdCodes;
    }

    //Get names of countries from study_country
    /**
     *
     * @return
     */
    public static List<String> getStudyCountry() {
        List<String> country = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        String query = " select country from study_country";
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                country.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error(sqle.getStackTrace());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return country;
    }

    /**
     *
     * @return
     */
    public static List<String> getStdCodes() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<String> stdCodes = new ArrayList<>();
        try {
            String query = "select distinct CONCAT(std_code,'-',place) from std_code_tbl";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                stdCodes.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error(sqle.getStackTrace());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return stdCodes;
    }

    //Method to get study course level
    /**
     *
     * @return
     */
    public static List<String> getStudyCourseLevel() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<String> studyCourseLevelList = new ArrayList<>();
        con = DBPool.getInstance().getConn();
        try {
            String query = "select DISTINCT course_level  from study_work_migration_training_mstr_tbl where operation_area='study'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                studyCourseLevelList.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error(sqle.getStackTrace());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return studyCourseLevelList;
    }

    //BELOW METHODS ARE USED FOR GET STUDY,MIGRATION,WORK AND TRAINING DETAILS...
    //Method to get study functional area
    /**
     *
     * @return
     */
    public static List<String> getStudyFunctionalArea() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<String> studyFunctionalList = new ArrayList<>();
        con = DBPool.getInstance().getConn();
        try {
            String query = "select DISTINCT program_level from mastertbl_program_level ";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                studyFunctionalList.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error(sqle.getStackTrace());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return studyFunctionalList;
    }

    //Get names of countries from work_country
    /**
     *
     * @return
     */
    public static List<String> getWorkCountry() {
        List<String> country = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        String query = " select country from work_country";
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                country.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error(sqle.getStackTrace());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return country;
    }

    //Get training timings based on batch
    /**
     *
     * @param timing
     * @return
     */
    public static List<String> getTiming(String timing) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<String> triningCourseLevelList = new ArrayList<>();
        con = DBPool.getInstance().getConn();
        String query = "";
        try {
            if (timing.startsWith("Morning") || timing.endsWith("Morning")) {
                query = "select timing from mastertbl_timing where timing like '%am'";
            } else {
                query = "select timing from mastertbl_timing where timing like '%pm'";
            }
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                triningCourseLevelList.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error(sqle.getStackTrace());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return triningCourseLevelList;
    }

    //Get names of countries from migration_country
    /**
     *
     * @return
     */
    public static List<String> getMigrationCountry() {
        List<String> country = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        String query = " select country from migration_country";
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                country.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error(sqle.getStackTrace());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return country;
    }

    //Method to get migration course level
    /**
     *
     * @return
     */
    public static List<String> getMigrationCourseLevel() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<String> migrationCourseLevelList = new ArrayList<>();
        con = DBPool.getInstance().getConn();
        try {
            String query = "select DISTINCT course_level  from study_work_migration_training_mstr_tbl where operation_area='migration'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                migrationCourseLevelList.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error(sqle.getStackTrace());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return migrationCourseLevelList;
    }
    //Method to get trining course level

    //Method to get Migration functional area
    /**
     *
     * @return
     */
    public static List<String> getMigrationFunctionalArea() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<String> migrationFunctionalList = new ArrayList<>();
        con = DBPool.getInstance().getConn();
        try {
            String query = "select DISTINCT functional_area from study_work_migration_training_mstr_tbl where operation_area='migration'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                migrationFunctionalList.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error(sqle.getStackTrace());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return migrationFunctionalList;
    }

    /**
     *
     * @return
     */
    public static List<String> gettrainingCourseLevel() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<String> triningCourseLevelList = new ArrayList<>();
        con = DBPool.getInstance().getConn();
        try {
            String query = "select DISTINCT timing from mastertbl_timing";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                triningCourseLevelList.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error(sqle.getStackTrace());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return triningCourseLevelList;
    }

    //Method to get Training functional area
    /**
     *
     * @return
     */
    public static List<String> getTrainingFunctionalArea() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<String> trainingFunctionalList = new ArrayList<>();
        con = DBPool.getInstance().getConn();
        try {
            String query = "select DISTINCT timing from mastertbl_batch_timing";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                trainingFunctionalList.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error(sqle.getStackTrace());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return trainingFunctionalList;
    }

    //Method to get work course level
    /**
     *
     * @return
     */
    public static List<String> getWorkCourseLevel() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<String> workCourseLevelList = new ArrayList<>();
        con = DBPool.getInstance().getConn();
        try {
            String query = "select DISTINCT course_level  from study_work_migration_training_mstr_tbl where operation_area='work'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                workCourseLevelList.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error(sqle.getStackTrace());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return workCourseLevelList;
    }

    //Method to get work functional area
    /**
     *
     * @return
     */
    public static List<String> getWorkFunctionalArea() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<String> workFunctionalList = new ArrayList<>();
        con = DBPool.getInstance().getConn();
        try {
            String query = "select DISTINCT profession from mastertbl_profession";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                workFunctionalList.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error(sqle.getStackTrace());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return workFunctionalList;
    }

    //Get names of countries from migration_country
    /**
     *
     * @return
     */
    public static List<String> getTrainingCourse() {
        List<String> course = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        String query = " select course from mastertbl_course_management";
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                course.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error(sqle.getStackTrace());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return course;
    }

    /**
     *
     * @return
     */
    public static List<String> getAllBranches() {
        List<String> branches = new ArrayList<>();
        String branch = null;
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        try {
            String query = "Select branch_name from branch_tbl";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                branch = rs.getString(1);
                branches.add(branch);
            }
        } catch (SQLException sqle) {
            logger.error(sqle.getStackTrace());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return branches;
    }

    /**
     *
     * @return
     */
    public static List<String> getAllSources() {
        List<String> sources = new ArrayList<>();
        String source = null;
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        try {
            //  String query = "SELECT\n" + "\tsource_name AS source\n" + "FROM\n" + "\tenquiry_sources\n" + "UNION\n" + "\tSELECT\n" + "\t\tconcat('Agency-',a.firstname,'(',\tb.branch_name,')') AS source\n" + "\tFROM\n" + "\t\tagencies_tbl a,\n" + "\t\tbranch_tbl b\n" + "\tWHERE\n" + "a.branch_id = b.branch_id";
            String query = "SELECT\n"
                    + "	source_name AS source\n"
                    + "FROM\n"
                    + "	enquiry_sources\n"
                    + "UNION\n"
                    + "	SELECT\n"
                    + "		concat(\n"
                    + "			a.firstname,\n"
                    + "			' ',\n"
                    + "			a.lastname,\n"
                    + "			' ',\n"
                    + "			b.branch_name,\n"
                    + "			'_Agency'\n"
                    + "		) AS source\n"
                    + "	FROM\n"
                    + "		agencies_tbl a,\n"
                    + "		branch_tbl b\n"
                    + "	WHERE\n"
                    + "		a.branch_id = b.branch_id";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                source = rs.getString(1);
                sources.add(source);
            }
        } catch (SQLException sqle) {
            logger.error(sqle.getStackTrace());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return sources;
    }

    /**
     *
     * @return
     */
    public static List<String> getAssignedSources() {
        List<String> AssignedSources = new ArrayList<>();
        String source = null;
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        try {
            String query = "select e.firstname from employees e,enquiry_sources eq " + "where eq.source_name='Direct' and e.branch='kollam'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                AssignedSources.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error(sqle.getStackTrace());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return AssignedSources;
    }

    /**
     *
     * @return
     */
    public static List<String> getAllDepartment() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<String> allDepartments = new ArrayList<>();
        try {
            String query = "select department from mastertbl_department";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                allDepartments.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error(sqle.getStackTrace());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return allDepartments;
    }

    //Method for get all states from coutry_state_district table
    /**
     *
     * @param branch
     * @return
     */
    public static ObservableList<CounselorsListPOJO> getAllCounselors(String branch) {
        ObservableList<CounselorsListPOJO> listCounselors = FXCollections.observableArrayList();
        String source = null;
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String query = "";
        con = DBPool.getInstance().getConn();
        try {
            if (branch == null) {
                query = "select * from employee_role_branch where role ='ROLE_COUNSELOR'";
            } else {
                query = "select * from employee_role_branch where branch = '" + branch + "' and role ='ROLE_COUNSELOR' ";
            }
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            System.out.println("");
            while (rs.next()) {
                CounselorsListPOJO listPOJO = new CounselorsListPOJO();
                listPOJO.setEnquiryAssignedBy(rs.getString("enquiry_assigned_by"));
                listPOJO.setUsername(rs.getString("user_name"));
                listPOJO.setBranch(rs.getString("branch"));
                listCounselors.add(listPOJO);
            }
        } catch (SQLException sqle) {
            logger.info(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return listCounselors;
    }

    /**
     *
     * @param branch
     * @return
     */
    public static ObservableList<CounselorsListPOJO> getAllUsersByBranch(String branch) {
        ObservableList<CounselorsListPOJO> listCounselors = FXCollections.observableArrayList();
        String source = null;
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String query = "";
        con = DBPool.getInstance().getConn();
        try {
            if (branch == null) {
                query = "select * from employee_role_branch  ";
            } else {
                query = "select * from employee_role_branch where branch = '" + branch + "' ";
            }
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            System.out.println("");
            while (rs.next()) {
                CounselorsListPOJO listPOJO = new CounselorsListPOJO();
                listPOJO.setEnquiryAssignedBy(rs.getString("enquiry_assigned_by"));
                listPOJO.setUsername(rs.getString("user_name"));
                listPOJO.setBranch(rs.getString("branch"));
                listCounselors.add(listPOJO);
            }
        } catch (SQLException sqle) {
            logger.info(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return listCounselors;
    }

    /**
     *
     * @return
     */
    public static int retrieveThreadDelayInt() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int seconds = 0;

        try {

            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();
            String query = "SELECT thread_delay from mastertbl_thread_delay";
            rs = stmt.executeQuery(query);
            if (rs.next()) {
                seconds = Integer.parseInt(rs.getString(1));
            } else {
                seconds = 15;
            }
        } catch (SQLException sqle) {
            logger.error(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return seconds;

    }

    /**
     *
     * @return
     */
    public static List<String> getAllProgramLevels() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<String> allProgramLevelsList = new ArrayList<>();
        String query = "select program_level from mastertbl_program_level";
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                allProgramLevelsList.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return allProgramLevelsList;
    }

    /**
     *
     * @return
     */
    public static List<String> getAllRelations() {
      Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<String> allrelationsList = new ArrayList<>();
        String query = "select relationship from mastertbl_relationship";
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                allrelationsList.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return allrelationsList;
    }
}
