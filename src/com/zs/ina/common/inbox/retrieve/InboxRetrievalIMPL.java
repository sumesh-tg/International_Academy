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
package com.zs.ina.common.inbox.retrieve;

import com.zs.ina.common.InboxListMaker;
import com.zs.ina.counselor.dao.model.CounselorDetailsBEAN;
import com.zs.ina.counselor.dao.model.SearchQueryBEAN;
import com.zs.ina.utility.ClosingResourcesInDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.log4j.Logger;
import zs.com.ina.db.mysql.pool.DBPool;

/**
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class InboxRetrievalIMPL implements InboxRetrievalDAO {

    static Logger logger = Logger.getLogger(InboxRetrievalIMPL.class);

    final static String VIEW_HYPERLINKS_OFFICE = "hyperlink_count_branch";
    final static String VIEW_HYPERLINKS_COUNSELOR = "hyperlink_count_counselor";
    final static String VIEW_HYPERLINKS_ADMIN = "hyperlink_count_admin";
    final static String VIEW_HYPERLINKS_OFFICE_FOLLOWUP = "hyperlink_count_branch_followup";
    final static String VIEW_HYPERLINKS_COUNSELOR_FOLLOWUP = "hyperlink_count_counselor_followup";
    final static String VIEW_HYPERLINKS_ADMIN_FOLLOWUP = "hyperlink_count_admin_followup";
    static String queryEnquiryReport = "SELECT\n"
            + "     ed.enquiry_id,\n"
            + "     ed.contact_name,\n"
            + "	ed.contact_email,\n"
            + "	ed.contact_number,\n"
            + "	ed.contact_number2,\n"
            + "	ed.branch,\n"
            + "	ed.country,\n"
            + "	ed.state,\n"
            + "	ed.district,\n"
            + "	ed.enquiry_source,\n"
            + "	ed.other_enquiry,\n"
            + "	ed.enquiry_assigning,\n"
            + "	UPPER((SELECT FULLNAME FROM employee_role_branch WHERE user_name=ed.enquiry_assigning)) as counselor_name,\n"
            + "	 ed.edate,\n"
            + "      ed.rating,\n"
            + "      ed.status,\n"
            + "      ed.std_code,\n"
            + "      ed.std_code2,\n"
            + "      ed.read_flag,\n"
            + "      ed.staff_usrname,\n"
            + "      ed.staff_branch,\n"
            + "      ed.last_update,\n"
            + "      ed.department,\n"
            + "      ed.important_flag,\n"
            + "      ed.completion_flag,\n"
            + "      erb.enquiry_assigned_by,\n"
            + "      ea.enquiry_status AS cur_status,\n"
            + "      ea.remarks,\n"
            + "      ea.date as status_date,\n"
            + "      ea.time as status_time,\n"
            + "      ea.reference,\n"
            + "      hea.purpose,\n"
            + "      eas.study,\n"
            + "      ewe.work,\n"
            + "      eam.migrate as migration,\n"
            + "      eat.training,\n"
            + "      eat.batch,\n"
            + "      eat.from_date as joining_date,\n"
            + "      eas.program_level,\n"
            + "      eas.program_field,\n"
            + "      eas.intake,\n"
            + "      eas.institution,\n"
            + "      asq.teriary_quali_field,\n"
            + "      asq.teriary_quali_level,\n"
            + "      CONCAT(asq.teriary_quali_level ,\"-\",asq.teriary_quali_field) as qualification,\n"
            + "      CONCAT(asl.test,\"-\",asl.overall) as language_test,\n"
            + "      CONCAT(aat.test,\"-\",aat.overall) as admission_test,\n"
            + "      aae.duration as experience,ewe.max_salary,\n"
            + "      aae.profession,\n"
            + "      ep.marial_status as application_type,\n"
            + "      ep.dob,\n"
            + "      asl.test,\n"
            + "      asl.overall,\n"
            + "      ep.passport,\n"
            + "     eas.country,\n"
            + "     eas.location,\n"
            + "      CONCAT(eas.program_level,\"-\",eas.program_field) as study_required,\n"
            + "      CONCAT(eas.country,' - ',IFNULL(eas.location,'NA')) as study_country_location,\n"
            + "      ewe.profession as work_required,\n"
            + "      CONCAT(ewe.country,' - ',IFNULL(ewe.location,'NA')) as work_country_location,\n"
            + "      eam.profession as migration_required,\n"
            + "      CONCAT(eam.country,' - ',IFNULL(eam.location,'NA')) as migration_country_location,\n"
            + "      CONCAT(eat.batch,' ',eat.timing) as timing,\n"
            + "      app_stat.app_status\n"
            + "FROM  enquiry_details ed\n"
            + "LEFT JOIN EMPLOYEE_ROLE_BRANCH erb ON ED.staff_usrname = ERB.USER_NAME\n"
            + "LEFT JOIN enquiry_assesment_status_tbl ea ON ea.enquiry_id = ed.enquiry_id\n"
            + "LEFT JOIN enquiry_personal_details_tbl ep ON ea.enquiry_id = ep.enquiry_id\n"
            + "LEFT JOIN enquiry_assessment_training_tbl eat ON eat.enquiry_id = ed.enquiry_id and eat.latest_flag = 'y'\n"
            + "LEFT JOIN enquiry_assessment_work_tbl ewe ON ewe.enquiry_id = ed.enquiry_id and ewe.latest_flag = 'y'\n"
            + "LEFT JOIN enquiry_assessment_migrate_tbl eam ON eam.enquiry_id = ed.enquiry_id and eam.latest_flag = 'y'\n"
            + "LEFT JOIN enquiry_assessment_study_tbl eas ON eas.enquiry_id = ed.enquiry_id AND eas.latest_flag = 'y'\n"
            + "LEFT JOIN history_enquiry_assigning_tbl hea ON hea.enquiry_id = ed.enquiry_id and hea.latest_flag = 'y'\n"
            + "LEFT JOIN enquiry_assessment_teritary_qualification asq ON asq.enquiry_id = ed.enquiry_id and asq.latest_flag = 'y'\n"
            + "LEFT JOIN enquiry_assesment_languagetest_tbl asl ON asl.enquiry_id = ed.enquiry_id and asl.latest_flag = 'y'\n"
            + "LEFT JOIN enquiry_assessment_admission_test_tbl aat ON aat.enquiry_id = ed.enquiry_id and aat.latest_flag = 'y'\n"
            + "LEFT JOIN mastertbl_app_status app_stat on ea.enquiry_status=app_stat.app_status_id\n"
            + "LEFT JOIN enquiry_assessment_work_exp_tbl aae ON aae.enquiry_id=ed.enquiry_id and aae.latest_flag = 'y'";

    /**
     *
     * @param branch
     * @param username
     * @param role
     * @return
     */
    @Override
    public Map<String, List<CounselorDetailsBEAN>> getPrimaryInbox(String branch, String username, String role) {
        Map<String, List<CounselorDetailsBEAN>> primaryInboxMap = new HashMap<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        try {
            String sql = queryEnquiryReport;
            switch (role) {
                case "ROLE_OFFICE":
                    sql = sql + " WHERE  ed.staff_branch='" + branch + "' ORDER BY ed.edate DESC";
                    break;
                case "ROLE_COUNSELOR":
                    sql = sql + " WHERE  ed.enquiry_assigning='" + username + "' ORDER BY ed.edate DESC";
                    break;
                case "ROLE_ADMIN":
                    //    sql = sql + "and ed.enquiry_assigning<>'Not Assigned'";
                    break;
                default:
                    System.out.println("No role detected!");
            }
            System.out.println("primary no search ===>> " + sql);
            PreparedStatement ps = con.prepareStatement(sql);
            //  ps.setString(1, username);
            rs = ps.executeQuery();
            primaryInboxMap = InboxListMaker.makePrimaryInboxMap(rs);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error(sqle);
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return primaryInboxMap;
    }

    /**
     *
     * @param branch
     * @param username
     * @param role
     * @return
     */
    @Override
    public Map<String, List<CounselorDetailsBEAN>> getCommonPoolEnquiries(String branch, String username, String role) {
        Map<String, List<CounselorDetailsBEAN>> primaryInboxMap = new HashMap<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        try {
            String sql = queryEnquiryReport;
            switch (role) {
                case "ROLE_OFFICE":
                    sql = sql + " WHERE  ed.staff_branch='" + branch + "' ORDER BY ed.edate DESC";
                    break;
                case "ROLE_COUNSELOR":
                    sql = sql + " WHERE  ed.enquiry_assigning='Not Assigned'  and ed.branch='" + branch + "'  ORDER BY ed.edate DESC";
                    break;
                case "ROLE_ADMIN":
                    sql = sql + " WHERE  ed.enquiry_assigning='Not Assigned' OR ed.enquiry_assigning is null  ORDER BY ed.edate DESC";
                    break;
                default:
                    System.out.println("No role detected!");
            }
            System.out.println("primary no search ===>> " + sql);
            PreparedStatement ps = con.prepareStatement(sql);
            //  ps.setString(1, username);
            rs = ps.executeQuery();
            primaryInboxMap = InboxListMaker.makePrimaryInboxMap(rs);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error(sqle);
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return primaryInboxMap;
    }

    /**
     *
     * @param branch
     * @param username
     * @param role
     * @param appStatusId
     * @return
     */
    @Override
    public Map<String, List<CounselorDetailsBEAN>> retrieveAppStatusData(String branch, String username, String role, String appStatusId) {
        System.out.println("Current role" + role);
        Map<String, List<CounselorDetailsBEAN>> primaryInboxMap = new HashMap<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        try {
            String sql = "select m.*,\n"
                    + "ed.enquiry_id,\n"
                    + "     ed.contact_name,\n"
                    + "	ed.contact_email,\n"
                    + "	ed.contact_number,\n"
                    + "	ed.contact_number2,\n"
                    + "	ed.branch,\n"
                    + "	ed.country,\n"
                    + "	ed.state,\n"
                    + "	ed.district,\n"
                    + "	ed.enquiry_source,\n"
                    + "	ed.other_enquiry,\n"
                    + "	ed.enquiry_assigning,\n"
                    + "	UPPER((SELECT FULLNAME FROM employee_role_branch WHERE user_name=ed.enquiry_assigning)) as counselor_name,\n"
                    + "	 ed.edate,\n"
                    + "      ed.rating,\n"
                    + "      ed.status,\n"
                    + "      ed.std_code,\n"
                    + "      ed.std_code2,\n"
                    + "      ed.read_flag,\n"
                    + "      ed.staff_usrname,\n"
                    + "      ed.staff_branch,\n"
                    + "      ed.last_update,\n"
                    + "      ed.department,\n"
                    + "      ed.important_flag,\n"
                    + "      ed.completion_flag,\n"
                    + "      erb.enquiry_assigned_by,\n"
                    + " st.enquiry_status AS cur_status,\n"
                    + "      st.remarks,\n"
                    + "      st.date as status_date,\n"
                    + "      st.time as status_time,\n"
                    + "      st.reference,\n"
                    + "hea.purpose,\n"
                    + "east.study,\n"
                    + "ewt.work,\n"
                    + "emt.migrate as migration,\n"
                    + "ett.training,\n"
                    + "ett.batch,\n"
                    + "ett.from_date as joining_date,\n"
                    + "CONCAT(asq.teriary_quali_level ,\"\",asq.teriary_quali_field) as qualification,\n"
                    + "      CONCAT(asl.test,\"-\",asl.overall) as language_test,\n"
                    + "      CONCAT(aat.test,\"-\",aat.overall) as admission_test,\n"
                    + "      aae.duration as experience,ewt.max_salary,\n"
                    + "      ep.marial_status as application_type,\n"
                    + "      CONCAT(east.program_level,\"-\",east.program_field) as study_required,\n"
                    + "      CONCAT(east.country,' - ',IFNULL(east.location,'NA')) as study_country_location,\n"
                    + "      ewt.profession as work_required,\n"
                    + "      CONCAT(ewt.country,' - ',IFNULL(ewt.location,'NA')) as work_country_location,\n"
                    + "      emt.profession as migration_required,\n"
                    + "      CONCAT(emt.country,' - ',IFNULL(emt.location,'NA')) as migration_country_location,\n"
                    + "      CONCAT(ett.batch,' ',ett.timing) as timing\n"
                    + "from mastertbl_app_status m\n"
                    + "LEFT OUTER JOIN enquiry_assesment_status_tbl st on (m.app_status_id=st.enquiry_status)\n"
                    + "LEFT OUTER JOIN enquiry_details ed on (ed.enquiry_id = st.enquiry_id)\n"
                    + "LEFT JOIN  enquiry_assessment_study_tbl east  on (east.enquiry_id=st.enquiry_id) and east.latest_flag='y'\n"
                    + "LEFT JOIN enquiry_assessment_work_tbl ewt  on (ewt.enquiry_id = st.enquiry_id) and ewt.latest_flag='y'\n"
                    + "LEFT JOIN enquiry_assessment_migrate_tbl emt  on (emt.enquiry_id = st.enquiry_id) and emt.latest_flag='y'\n"
                    + "LEFT JOIN enquiry_assessment_training_tbl ett  on (ett.enquiry_id = st.enquiry_id) and ett.latest_flag='y'\n"
                    + "LEFT JOIN history_enquiry_assigning_tbl hea ON hea.enquiry_id = ed.enquiry_id and hea.latest_flag = 'y' \n"
                    + "\n"
                    + "LEFT JOIN enquiry_assessment_teritary_qualification asq ON asq.enquiry_id = ed.enquiry_id and asq.latest_flag = 'y'\n"
                    + "LEFT JOIN enquiry_assesment_languagetest_tbl asl ON asl.enquiry_id = ed.enquiry_id and asl.latest_flag = 'y'\n"
                    + "LEFT JOIN enquiry_assessment_admission_test_tbl aat ON aat.enquiry_id = ed.enquiry_id and aat.latest_flag = 'y'\n"
                    + "LEFT JOIN enquiry_assessment_work_exp_tbl aae ON aae.enquiry_id=ed.enquiry_id and aae.latest_flag = 'y'\n"
                    + "LEFT JOIN enquiry_personal_details_tbl ep ON ed.enquiry_id = ep.enquiry_id\n"
                    + "LEFT JOIN employee_role_branch erb ON erb.USER_NAME=ed.enquiry_assigning\n"
                    + "where ed.enquiry_id is not null\n"
                    + "  and m.app_status_id = " + appStatusId + "\n";
            System.out.println("All Inbox Data ===>> " + sql);
            switch (role) {
                case "ROLE_OFFICE":
                    sql = sql + "and ed.staff_branch='" + branch + "'";
                    break;
                case "ROLE_COUNSELOR":
                    sql = sql + "and ed.enquiry_assigning='" + username + "'";
                    break;
                case "ROLE_ADMIN":
                    //   sql = sql + "and ed.enquiry_assigning<>'Not Assigned'";
                    break;
                default:
                    System.out.println("No role detected!");
            }
            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setString(1, branch);
            rs = ps.executeQuery();
            primaryInboxMap = InboxListMaker.makePrimaryInboxMap(rs);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error(sqle);
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }

        return primaryInboxMap;

    }

    /**
     *
     * @param username
     * @param branch
     * @param role
     * @return
     */
    @Override
    public ObservableList<InboxCountsBEAN> retrieveInboxCounts(String username, String branch, String role) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ObservableList<InboxCountsBEAN> listInboxCounts = FXCollections.observableArrayList();
        try {
            con = DBPool.getInstance().getConn();
            /* ====================== Check role for later this is for front office stsff ====================== */
            String countsQuery = null;
            switch (role) {
                case "ROLE_OFFICE":
                    countsQuery = "SELECT *,CONCAT(study,\",\",Work,\",\",Migrat,\",\",Training) as concatConuts FROM " + VIEW_HYPERLINKS_OFFICE + " WHERE branch='" + branch + "' AND Total_count<>0";
                    System.out.println("Office Count " + countsQuery);
                    break;
                case "ROLE_COUNSELOR":
                    countsQuery = "SELECT *,CONCAT(study,\",\",Work,\",\",Migrat,\",\",Training) as concatConuts FROM " + VIEW_HYPERLINKS_COUNSELOR + " WHERE branch='" + branch + "' and user='" + username + "' AND Total_count<>0";
                    break;
                case "ROLE_ADMIN":
                    countsQuery = "SELECT *,CONCAT(study,\",\",Work,\",\",Migrat,\",\",Training) as concatConuts FROM " + VIEW_HYPERLINKS_ADMIN + "  WHERE status<>'' AND Total_count<>0";
                    break;
                default:
                    System.out.println("No role detected!");
            }
            System.out.println("Count Retrieval Query :: " + countsQuery);
            stmt = con.createStatement();
            rs = stmt.executeQuery(countsQuery);
            while (rs.next()) {
                InboxCountsBEAN inboxCountsBEAN = new InboxCountsBEAN();
                inboxCountsBEAN.setStatus(rs.getString("status"));
                inboxCountsBEAN.setStatusId(rs.getString("Status_id"));
                inboxCountsBEAN.setTotalCount(rs.getString("Total_count"));
                inboxCountsBEAN.setStudy(rs.getString("study"));
                inboxCountsBEAN.setWork(rs.getString("Work"));
                inboxCountsBEAN.setMigrat(rs.getString("Migrat"));
                inboxCountsBEAN.setTraining(rs.getString("Training"));
                inboxCountsBEAN.setBranch(rs.getString("branch"));
                inboxCountsBEAN.setConcatCount(rs.getString("concatConuts"));
                inboxCountsBEAN.setTableDiff(rs.getString("tabname"));
                listInboxCounts.add(inboxCountsBEAN);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error(sqle);
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return listInboxCounts;
    }

    /**
     *
     * @param username
     * @param branch
     * @param role
     * @return
     */
    @Override
    public ObservableList<InboxCountsBEAN> retrieveInboxCountsFollowup(String username, String branch, String role) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ObservableList<InboxCountsBEAN> listInboxCounts = FXCollections.observableArrayList();
        try {
            con = DBPool.getInstance().getConn();
            /* ====================== Check role for later this is for front office stsff ====================== */
            String countsQuery = null;
            switch (role) {
                case "ROLE_OFFICE":
                    countsQuery = "SELECT * FROM " + VIEW_HYPERLINKS_OFFICE_FOLLOWUP + " WHERE branch='" + branch + "' AND Total_count<>0";
                    System.out.println("Office Follow Count Query :: " + countsQuery);
                    break;
                case "ROLE_COUNSELOR":
                    countsQuery = "SELECT * FROM " + VIEW_HYPERLINKS_COUNSELOR_FOLLOWUP + " WHERE branch='" + branch + "' and user='" + username + "' AND Total_count<>0";
                    break;
                case "ROLE_ADMIN":
                    countsQuery = "SELECT * FROM " + VIEW_HYPERLINKS_ADMIN_FOLLOWUP + "  WHERE status<>'' AND Total_count<>0";
                    break;
                default:
                    System.out.println("No role detected!");
            }
            System.out.println("Count Retrieval Query :: " + countsQuery);
            stmt = con.createStatement();
            rs = stmt.executeQuery(countsQuery);
            while (rs.next()) {
                InboxCountsBEAN inboxCountsBEAN = new InboxCountsBEAN();
                inboxCountsBEAN.setStatus(rs.getString("status"));
                inboxCountsBEAN.setStatusId(rs.getString("Status_id"));
                inboxCountsBEAN.setTotalCount(rs.getString("Total_count"));
                //  inboxCountsBEAN.setStudy(rs.getString("study"));
                // inboxCountsBEAN.setWork(rs.getString("Work"));
                //  inboxCountsBEAN.setMigrat(rs.getString("Migrat"));
                //inboxCountsBEAN.setTraining(rs.getString("Training"));
                inboxCountsBEAN.setBranch(rs.getString("branch"));
                // inboxCountsBEAN.setConcatCount(rs.getString("concatConuts"));
                inboxCountsBEAN.setTableDiff(rs.getString("tabname"));
                listInboxCounts.add(inboxCountsBEAN);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error(sqle);
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return listInboxCounts;
    }

    /**
     *
     * @param branch
     * @param username
     * @param role
     * @param taskStatusId
     * @return
     */
    @Override
    public Map<String, List<CounselorDetailsBEAN>> retrieveTaskStatusData(String branch, String username, String role, String taskStatusId) {
        System.out.println("Current role" + role);
        Map<String, List<CounselorDetailsBEAN>> primaryInboxMap = new HashMap<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        try {
            String sql = "SELECT\n"
                    + "	app_details.enquiry_id,\n"
                    + "	app_details.contact_name,\n"
                    + "	app_details.contact_email,\n"
                    + "	app_details.contact_number,\n"
                    + "	app_details.contact_number2,\n"
                    + "	app_details.branch,\n"
                    + "	app_details.country,\n"
                    + "	app_details.state,\n"
                    + "	app_details.district,\n"
                    + "	app_details.enquiry_source,\n"
                    + "	app_details.other_enquiry,\n"
                    + "	app_details.enquiry_assigning,\n"
                    + "	UPPER((SELECT FULLNAME FROM employee_role_branch WHERE user_name=ed.enquiry_assigning)) as counselor_name,\n"
                    + "	app_details.edate,\n"
                    + "	app_details.rating,\n"
                    + "	app_details.STATUS,\n"
                    + "	app_details.std_code,\n"
                    + "	app_details.std_code2,\n"
                    + "	app_details.read_flag,\n"
                    + "	app_details.important_flag,\n"
                    + "	app_details.completion_flag,\n"
                    + "	app_details.staff_usrname,\n"
                    + "	app_details.staff_branch,\n"
                    + "	app_details.last_update,\n"
                    + "	app_details.department,\n"
                    + "	app_details.enquiry_assigned_by,\n"
                    + "	app_details.cur_status,\n"
                    + "	app_details.remarks,\n"
                    + "	app_details.status_date,\n"
                    + "	app_details.status_time,\n"
                    + "	app_details.reference,\n"
                    + "	app_details.purpose,\n"
                    + "	app_details.study,\n"
                    + "	app_details.WORK,\n"
                    + "	app_details.migration,\n"
                    + "	app_details.training,\n"
                    + "	app_details.batch,\n"
                    + "	app_details.joining_date,\n"
                    + "	app_details.qualification,\n"
                    + "	app_details.language_test,\n"
                    + "	app_details.admission_test,\n"
                    + "	app_details.experience,\n"
                    + "	app_details.max_salary,\n"
                    + "	app_details.application_type,\n"
                    + "	app_details.study_required,\n"
                    + "	app_details.study_country_location,\n"
                    + "	app_details.work_required,\n"
                    + "	app_details.work_country_location,\n"
                    + "	app_details.migration_required,\n"
                    + "	app_details.migration_country_location,\n"
                    + "	app_details.timing\n"
                    + "FROM\n"
                    + "	(\n"
                    + "		SELECT\n"
                    + "			keyword,det.assessment_substatus\n"
                    + "		FROM\n"
                    + "			mastertbl_task_follow_status mst\n"
                    + "		INNER JOIN task_details det ON (\n"
                    + "			det.assessment_substatus = mst.task_follow_id\n"
                    + "			AND det.latest_flag = 'y'\n"
                    + "		)\n"
                    + "		INNER JOIN task_master tms ON (tms.task_id = det.task_id)\n"
                    + "		WHERE\n"
                    + "			keyword <> 'generic'\n"
                    + "	) AS task_details,\n"
                    + "	(\n"
                    + "		SELECT\n"
                    + "			m.*, ed.enquiry_id,\n"
                    + "			ed.contact_name,\n"
                    + "			ed.contact_email,\n"
                    + "			ed.contact_number,\n"
                    + "			ed.contact_number2,\n"
                    + "			ed.branch,\n"
                    + "			ed.country,\n"
                    + "			ed.state,\n"
                    + "			ed.district,\n"
                    + "			ed.enquiry_source,\n"
                    + "			ed.other_enquiry,\n"
                    + "			ed.enquiry_assigning,\n"
                    + "			ed.edate,\n"
                    + "			ed.rating,\n"
                    + "			ed. STATUS,\n"
                    + "			ed.std_code,\n"
                    + "			ed.std_code2,\n"
                    + "			ed.read_flag,\n"
                    + "			ed.important_flag,\n"
                    + "			ed.completion_flag,\n"
                    + "			ed.staff_usrname,\n"
                    + "			ed.staff_branch,\n"
                    + "			ed.last_update,\n"
                    + "			ed.department,\n"
                    + "			erb.enquiry_assigned_by,\n"
                    + "			st.enquiry_status AS cur_status,\n"
                    + "			st.remarks,\n"
                    + "			st.date AS status_date,\n"
                    + "			st.time AS status_time,\n"
                    + "			st.reference,\n"
                    + "			hea.purpose,\n"
                    + "			east.study,\n"
                    + "			ewt. WORK,\n"
                    + "			emt.migrate AS migration,\n"
                    + "			ett.training,\n"
                    + "			ett.batch,\n"
                    + "			ett.from_date AS joining_date,\n"
                    + "			CONCAT(	asq.teriary_quali_level,\"\",asq.teriary_quali_field) AS qualification,\n"
                    + "			CONCAT(asl.test, \"-\", asl.overall) AS language_test,\n"
                    + "			CONCAT(aat.test, \"-\", aat.overall) AS admission_test,	aae.duration AS experience,ewt.max_salary,ep.marial_status AS application_type,\n"
                    + "			CONCAT(east.program_level,\"-\",east.program_field) AS study_required,\n"
                    + "			CONCAT(	east.country,' - ',ifnull(east.location,'NA') AS study_country_location,\n"
                    + "			ewt.profession AS work_required,CONCAT(ewt.country,' - ',ifnull(ewt.location,'NA')) AS work_country_location,\n"
                    + "			emt.profession AS migration_required,\n"
                    + "			CONCAT(	emt.country,	' - ',ifnull(emt.location,'NA')	) AS migration_country_location,CONCAT(ett.batch, ' ', ett.timing) AS timing\n"
                    + "		FROM\n"
                    + "			mastertbl_app_status m\n"
                    + "		LEFT OUTER JOIN enquiry_assesment_status_tbl st ON (	m.app_status_id = st.enquiry_status)\n"
                    + "		LEFT OUTER JOIN enquiry_details ed ON (		ed.enquiry_id = st.enquiry_id		)\n"
                    + "		LEFT JOIN enquiry_assessment_study_tbl east ON (east.enquiry_id = st.enquiry_id		)		AND east.latest_flag = 'y'\n"
                    + "		LEFT JOIN enquiry_assessment_work_tbl ewt ON (ewt.enquiry_id = st.enquiry_id)		AND ewt.latest_flag = 'y'\n"
                    + "		LEFT JOIN enquiry_assessment_migrate_tbl emt ON (emt.enquiry_id = st.enquiry_id)	AND emt.latest_flag = 'y'\n"
                    + "		LEFT JOIN enquiry_assessment_training_tbl ett ON (ett.enquiry_id = st.enquiry_id	)		AND ett.latest_flag = 'y'\n"
                    + "		LEFT JOIN history_enquiry_assigning_tbl hea ON hea.enquiry_id = ed.enquiry_id		AND hea.latest_flag = 'y'\n"
                    + "		LEFT JOIN enquiry_assessment_teritary_qualification asq ON asq.enquiry_id = ed.enquiry_id		AND asq.latest_flag = 'y'\n"
                    + "		LEFT JOIN enquiry_assesment_languagetest_tbl asl ON asl.enquiry_id = ed.enquiry_id		AND asl.latest_flag = 'y'\n"
                    + "		LEFT JOIN enquiry_assessment_admission_test_tbl aat ON aat.enquiry_id = ed.enquiry_id		AND aat.latest_flag = 'y'\n"
                    + "		LEFT JOIN enquiry_assessment_work_exp_tbl aae ON aae.enquiry_id = ed.enquiry_id		AND aae.latest_flag = 'y'\n"
                    + "		LEFT JOIN enquiry_personal_details_tbl ep ON ed.enquiry_id = ep.enquiry_id\n"
                    + "		LEFT JOIN employee_role_branch erb ON erb.USER_NAME = ed.enquiry_assigning\n"
                    + "		WHERE\n"
                    + "			ed.enquiry_id IS NOT NULL\n"
                    + "	) AS app_details\n"
                    + "WHERE\n"
                    + "	app_details.enquiry_id = task_details.keyword \n"
                    + "  and task_details.assessment_substatus=" + taskStatusId + "\n";
            switch (role) {
                case "ROLE_OFFICE":
                    sql = sql + "and app_details.staff_branch='" + branch + "'";
                    break;
                case "ROLE_COUNSELOR":
                    sql = sql + "and app_details.enquiry_assigning='" + username + "'";
                    break;
                case "ROLE_ADMIN":
                    //  sql = sql + "and app_details.enquiry_assigning<>'Not Assigned'";
                    break;
                default:
                    System.out.println("No role detected!");
            }
            System.out.println("All Inbox Taskk ===>> " + sql);
            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setString(1, branch);
            rs = ps.executeQuery();
            primaryInboxMap = InboxListMaker.makePrimaryInboxMap(rs);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error(sqle);
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }

        return primaryInboxMap;

    }

    /**
     *
     * @param branch
     * @param username
     * @param role
     * @param pending_asesed
     * @param whichField
     * @return
     */
    @Override
    public Map<String, List<CounselorDetailsBEAN>> retrieveStarredEnquiries(String branch, String username, String role, String pending_asesed, String whichField) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        Map<String, List<CounselorDetailsBEAN>> mapStarredEnquiries = new HashMap<>();
        String sql = queryEnquiryReport;
        switch (role) {
            case "ROLE_OFFICE":
                sql = sql + " WHERE	ed.staff_branch = '" + branch + "' AND ed.rating=1 ORDER BY ed.edate DESC";
                break;
            case "ROLE_COUNSELOR":
                sql = sql + " WHERE  ed.enquiry_assigning='" + username + "' AND ed.rating=1 ORDER BY ed.edate DESC";
                break;
            case "ROLE_ADMIN":
                sql = sql + " WHERE ed.rating=1 ORDER BY ed.edate DESC ";
                break;
            default:
                System.out.println("No role detected!");
        }
        System.out.println("important === >> " + sql);
        try {
            con = DBPool.getInstance().getConn();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            mapStarredEnquiries = InboxListMaker.makePrimaryInboxMap(rs);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error(sqle);
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }

        return mapStarredEnquiries;

    }

    /**
     *
     * @param queryBEAN
     * @param branch
     * @param username
     * @param role
     * @param dynamic_query
     * @return
     */
    @Override
    public Map<String, List<CounselorDetailsBEAN>> getPrimarySearch(SearchQueryBEAN queryBEAN, String branch, String username, String role, String dynamic_query) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        Map<String, List<CounselorDetailsBEAN>> primarySearchMap = new HashMap<>();
        String dateTo = queryBEAN.getDateTo();
        String enquiryStatus = "";

        // check null params
        if (queryBEAN.getAssignedTo() == null) {
            queryBEAN.setAssignedTo("");
        }
        if (queryBEAN.getAssignedBy() == null) {
            queryBEAN.setAssignedBy("");
        }
        if (queryBEAN.getEnquiryMethod() == null) {
            queryBEAN.setEnquiryMethod("");
        }
        if (queryBEAN.getEnquirySource() == null) {
            queryBEAN.setEnquirySource("");
        }
        if (queryBEAN.getBranch() == null || queryBEAN.getBranch().equalsIgnoreCase("All")) {
            queryBEAN.setBranch("");
        }
        if (queryBEAN.getProgramLevel() == null) {
            queryBEAN.setProgramLevel("");
        }
        if (queryBEAN.getProgramField() == null) {
            queryBEAN.setProgramField("");
        }
        if (queryBEAN.getDistrict() == null) {
            queryBEAN.setDistrict("");
        }
        if (queryBEAN.getState() == null) {
            queryBEAN.setState("");
        }
        if (queryBEAN.getCountry() == null) {
            queryBEAN.setCountry("");
        }
        if (queryBEAN.getDateTo() == null || queryBEAN.getDateTo().equals("")) {
            dateTo = LocalDate.now().toString();
        }
        if (queryBEAN.getDateFrom() == null) {
            queryBEAN.setDateFrom("''");
        }
        if (queryBEAN.getApplicationStatus() == null) {
            queryBEAN.setApplicationStatus("");
            enquiryStatus = "";

        } else {
            enquiryStatus = "AND app_stat.app_status LIKE '" + queryBEAN.getApplicationStatus() + "%'";
        }
        String sql = queryEnquiryReport;
        switch (role) {
            case "ROLE_OFFICE":
                sql = sql + "  WHERE	(ed.edate BETWEEN '" + queryBEAN.getDateFrom() + "'AND '" + dateTo + "') \n"
                        + "         AND ed.contact_name LIKE '" + queryBEAN.getName() + "%'	AND (ed.contact_number LIKE '" + queryBEAN.getPhone() + "%' OR ed.contact_number2 LIKE '" + queryBEAN.getPhone() + "%' ) \n"
                        + "         AND ed.enquiry_assigning LIKE '" + queryBEAN.getAssignedBy() + "%'\n"
                        + "         AND ed.enquiry_method LIKE '" + queryBEAN.getEnquiryMethod() + "%'\n"
                        + "	    AND ed.enquiry_source LIKE '" + queryBEAN.getEnquirySource() + "%' \n"
                        + "         AND ed.branch LIKE '" + queryBEAN.getBranch() + "%' \n"
                        + "         AND ed.district LIKE '" + queryBEAN.getDistrict() + "%' \n"
                        + "         AND ed.state LIKE '" + queryBEAN.getState() + "%' \n"
                        + "         AND ed.country LIKE '" + queryBEAN.getCountry() + "%' \n"
                        + "     " + enquiryStatus + " \n"
                        + "         AND ed.staff_branch =  '" + branch + "' \n"
                        + " " + dynamic_query + "\n"
                        + "   ORDER BY ed.edate DESC     ";
                break;
            case "ROLE_COUNSELOR":
                sql = sql + "  WHERE	(ed.edate BETWEEN '" + queryBEAN.getDateFrom() + "'AND '" + dateTo + "') \n"
                        + "    AND ed.contact_name LIKE '" + queryBEAN.getName() + "%'	AND (ed.contact_number LIKE '" + queryBEAN.getPhone() + "%' OR ed.contact_number2 LIKE '" + queryBEAN.getPhone() + "%' ) \n"
                        + "    AND ed.staff_usrname LIKE '" + queryBEAN.getAssignedBy() + "%'\n"
                        + "    AND ed.enquiry_method LIKE '" + queryBEAN.getEnquiryMethod() + "%'\n"
                        + "    AND ed.enquiry_source LIKE '" + queryBEAN.getEnquirySource() + "%' \n"
                        + "    AND ed.branch LIKE '" + queryBEAN.getBranch() + "%' \n"
                        + "    AND ed.district LIKE '" + queryBEAN.getDistrict() + "%' \n"
                        + "    AND ed.state LIKE '" + queryBEAN.getState() + "%' \n"
                        + "    AND ed.country LIKE '" + queryBEAN.getCountry() + "%' \n"
                        + "     " + enquiryStatus + " \n"
                        + "    AND ed.enquiry_assigning =  '" + username + "' \n"
                        + " " + dynamic_query + "\n"
                        + "   ORDER BY ed.edate DESC     ";
                break;
            case "ROLE_ADMIN":
                sql = sql + "  WHERE (ed.edate BETWEEN '" + queryBEAN.getDateFrom() + "'AND '" + dateTo + "') \n"
                        + "         AND ed.contact_name LIKE '" + queryBEAN.getName() + "%'	AND (ed.contact_number LIKE '" + queryBEAN.getPhone() + "%' OR ed.contact_number2 LIKE '" + queryBEAN.getPhone() + "%' ) \n"
                        + "         AND ed.staff_usrname LIKE '" + queryBEAN.getAssignedBy() + "%'\n"
                        + "         AND ed.enquiry_method LIKE '" + queryBEAN.getEnquiryMethod() + "%'\n"
                        + "         AND ed.enquiry_source LIKE '" + queryBEAN.getEnquirySource() + "%' \n"
                        + "         AND ed.branch LIKE '" + queryBEAN.getBranch() + "%' \n"
                        + "         AND ed.district LIKE '" + queryBEAN.getDistrict() + "%' \n"
                        + "         AND ed.state LIKE '" + queryBEAN.getState() + "%' \n"
                        + "         AND ed.country LIKE '" + queryBEAN.getCountry() + "%' \n"
                        + "     " + enquiryStatus + " \n"
                        + "     " + dynamic_query + "\n"
                        + "     ORDER BY ed.edate DESC  ";
                break;
            case "COMMON_POOL_COUNSELOR":
                sql = sql + "  WHERE	(ed.edate BETWEEN '" + queryBEAN.getDateFrom() + "'AND '" + dateTo + "') \n"
                        + "         AND ed.contact_name LIKE '" + queryBEAN.getName() + "%'	AND (ed.contact_number LIKE '" + queryBEAN.getPhone() + "%' OR ed.contact_number2 LIKE '" + queryBEAN.getPhone() + "%' ) \n"
                        + "         AND ed.staff_usrname LIKE '" + queryBEAN.getAssignedBy() + "%'\n"
                        + "         AND ed.enquiry_method LIKE '" + queryBEAN.getEnquiryMethod() + "%'\n"
                        + "	    AND ed.enquiry_source LIKE '" + queryBEAN.getEnquirySource() + "%' \n"
                        + "         AND ed.branch LIKE '" + queryBEAN.getBranch() + "%' \n"
                        + "         AND ed.district LIKE '" + queryBEAN.getDistrict() + "%' \n"
                        + "         AND ed.state LIKE '" + queryBEAN.getState() + "%' \n"
                        + "         AND ed.country LIKE '" + queryBEAN.getCountry() + "%' \n"
                        + "     " + enquiryStatus + " \n"
                        + "         AND ed.enquiry_assigning='Not Assigned' and ed.branch='" + branch + "' \n"
                        + " " + dynamic_query + "\n"
                        + "   ORDER BY ed.edate DESC     ";
                break;
            case "COMMON_POOL_ADMIN":
                sql = sql + "  WHERE	(ed.edate BETWEEN '" + queryBEAN.getDateFrom() + "'AND '" + dateTo + "') \n"
                        + "         AND ed.contact_name LIKE '" + queryBEAN.getName() + "%'	AND (ed.contact_number LIKE '" + queryBEAN.getPhone() + "%' OR ed.contact_number2 LIKE '" + queryBEAN.getPhone() + "%' ) \n"
                        + "         AND ed.staff_usrname LIKE '" + queryBEAN.getAssignedBy() + "%'\n"
                        + "         AND ed.enquiry_method LIKE '" + queryBEAN.getEnquiryMethod() + "%'\n"
                        + "	    AND ed.enquiry_source LIKE '" + queryBEAN.getEnquirySource() + "%' \n"
                        + "         AND ed.branch LIKE '" + queryBEAN.getBranch() + "%' \n"
                        + "         AND ed.district LIKE '" + queryBEAN.getDistrict() + "%' \n"
                        + "         AND ed.state LIKE '" + queryBEAN.getState() + "%' \n"
                        + "         AND ed.country LIKE '" + queryBEAN.getCountry() + "%' \n"
                        + "     " + enquiryStatus + " \n"
                        + "         AND ed.branch =  '" + branch + "' \n"
                        + " " + dynamic_query + "\n"
                        + "   ORDER BY ed.edate DESC     ";
                break;

            default:
                System.out.println("No role detected!");
        }
        System.out.println("Search Query :: " + sql);
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            primarySearchMap = InboxListMaker.makePrimaryInboxMap(rs);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error(sqle);
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        //reset prompt text
        if (queryBEAN.getAssignedTo().equals("")) {
            queryBEAN.setAssignedTo(null);
        }
        if (queryBEAN.getAssignedBy().equals("")) {
            queryBEAN.setAssignedBy(null);
        }
        if (queryBEAN.getEnquiryMethod().equals("")) {
            queryBEAN.setEnquiryMethod(null);
        }
        if (queryBEAN.getEnquirySource().equals("")) {
            queryBEAN.setEnquirySource(null);
        }
        if (queryBEAN.getBranch().equals("")) {
            queryBEAN.setBranch("All");
        }
        if (queryBEAN.getProgramLevel().equals("")) {
            queryBEAN.setProgramLevel(null);
        }
        if (queryBEAN.getProgramField().equals("")) {
            queryBEAN.setProgramField(null);
        }
        if (queryBEAN.getDistrict().equals("")) {
            queryBEAN.setDistrict(null);
        }
        if (queryBEAN.getState().equals("")) {
            queryBEAN.setState(null);
        }
        if (queryBEAN.getCountry().equals("")) {
            queryBEAN.setCountry(null);
        }
        if (queryBEAN.getApplicationStatus().equals("")) {
            queryBEAN.setApplicationStatus(null);
        }
        if (queryBEAN.getDateFrom().equals("''")) {
            queryBEAN.setDateFrom(null);
        }
        return primarySearchMap;

    }

    /**
     *
     * @param branch
     * @param username
     * @param role
     * @param enquiryId
     * @return
     */
    @Override
    public CounselorDetailsBEAN retrieveEnquiryById(String branch, String username, String role, String enquiryId) {
        CounselorDetailsBEAN counselorDetailsBEAN = new CounselorDetailsBEAN();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        try {
            String sql = queryEnquiryReport;
            switch (role) {
                case "ROLE_OFFICE":
                    sql = sql + " WHERE  ed.enquiry_id='" + enquiryId + "' ORDER BY ed.edate DESC";
                    break;
                case "ROLE_COUNSELOR":
                    sql = sql + " WHERE  ed.enquiry_id='" + enquiryId + "' ORDER BY ed.edate DESC";
                    break;
                case "ROLE_ADMIN":
                    sql = sql + " WHERE  ed.enquiry_id='" + enquiryId + "' ORDER BY ed.edate DESC";
                    break;
                default:
                    System.out.println("No role detected!");
            }
            System.out.println("Query By Id ===>> " + sql);
            PreparedStatement ps = con.prepareStatement(sql);
            //  ps.setString(1, username);
            rs = ps.executeQuery();
            counselorDetailsBEAN = InboxListMaker.retrieveEnquiryById(rs);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error(sqle);
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return counselorDetailsBEAN;
    }

}
