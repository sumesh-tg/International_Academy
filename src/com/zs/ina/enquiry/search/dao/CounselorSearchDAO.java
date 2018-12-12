/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.enquiry.search.dao;

import com.zs.ina.counselor.dao.model.CounselorDetailsBEAN;
import com.zs.ina.counselor.dao.model.SearchQueryBEAN;
import com.zs.ina.common.InboxListMaker;
import com.zs.ina.utility.ClosingResourcesInDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import zs.com.ina.db.mysql.pool.DBPool;

/**
 *
 * @author zscomp1
 */
public class CounselorSearchDAO {

    /**
     *
     * @param queryBEAN
     * @param branch
     * @param username
     * @param dynamic_query
     * @return
     */
    public static Map<String, List<CounselorDetailsBEAN>> getPrimarySearch(SearchQueryBEAN queryBEAN, String branch, String username, String dynamic_query) {

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
            enquiryStatus = "AND ea.enquiry_status LIKE '" + queryBEAN.getApplicationStatus() + "%'";
        }
        //end params

        //    System.out.println("Testing date formats " + LocalDate.parse(queryBEAN.getDateFrom()));
        String sql = "SELECT\n"
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
                + "      CONCAT(asq.teriary_quali_level ,\"\",asq.teriary_quali_field) as qualification,\n"
                + "      CONCAT(asl.test,\"-\",asl.overall) as language_test,\n"
                + "      CONCAT(aat.test,\"-\",aat.overall) as admission_test,\n"
                + "      aae.duration as experience,ewe.max_salary,\n"
                + "      ep.marial_status as application_type,\n"
                + "      CONCAT(eas.program_level,\"-\",eas.program_field) as study_required,\n"
                + "      CONCAT(eas.country,' - ',eas.location) as study_country_location,\n"
                + "      ewe.profession as work_required,\n"
                + "      CONCAT(ewe.country,' - ',ewe.location) as work_country_location,\n"
                + "      eam.profession as migration_required,\n"
                + "      CONCAT(eam.country,' - ',eam.location) as migration_country_location,\n"
                + "      CONCAT(eat.batch,' ',eat.timing) as timing\n"
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
                + "LEFT JOIN enquiry_assessment_work_exp_tbl aae ON aae.enquiry_id=ed.enquiry_id and aae.latest_flag = 'y'"
                + "  WHERE	(ed.edate BETWEEN '" + queryBEAN.getDateFrom() + "'AND '" + dateTo + "') \n"
                + "         AND ed.contact_name LIKE '" + queryBEAN.getName() + "%'	AND (ed.contact_number LIKE '" + queryBEAN.getPhone() + "%' OR ed.contact_number2 LIKE '" + queryBEAN.getPhone() + "%' ) \n"
                //   + "         AND (ed.study_details like '" + queryBEAN.getProgramLevel() + "%' OR ed.study_details like '" + queryBEAN.getProgramField() + "%')\n"
                //   + "         AND ed.enquiry_assigning LIKE '" + queryBEAN.getAssignedTo() + "%'\n"
                + "         AND ed.staff_usrname LIKE '" + queryBEAN.getAssignedBy() + "%'\n"
                + "         AND ed.enquiry_method LIKE '" + queryBEAN.getEnquiryMethod() + "%'\n"
                + "	       AND ed.enquiry_source LIKE '" + queryBEAN.getEnquirySource() + "%' \n"
                + "         AND ed.branch LIKE '" + queryBEAN.getBranch() + "%' \n"
                + "         AND ed.district LIKE '" + queryBEAN.getDistrict() + "%' \n"
                + "         AND ed.state LIKE '" + queryBEAN.getState() + "%' \n"
                + "         AND ed.country LIKE '" + queryBEAN.getCountry() + "%' \n"
                + "     " + enquiryStatus + " \n"
                + "         AND ed.staff_branch=  '" + branch + "' \n"
                + " " + dynamic_query + "\n"
                + "   ORDER BY ed.edate DESC     ";
        System.out.println("Search Query :: " + sql);
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            primarySearchMap = InboxListMaker.makePrimaryInboxMap(rs);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
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

}
