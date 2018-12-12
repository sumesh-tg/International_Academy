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
package com.zs.ina.enquiry.followup.dao;

import com.zs.ina.admin.master.enquirystatus.dao.EnquiryStatusBEAN;
import com.zs.ina.common.constants.TableNames;
import com.zs.ina.counselor.dao.model.CounselorDetailsBEAN;
import com.zs.ina.utility.ClosingResourcesInDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import zs.com.ina.db.mysql.pool.DBPool;

/**
 *
 * @author 100018
 */
public class FollowUpIMPL implements FollowUpDAO {

    static Logger logger = Logger.getLogger(FollowUpIMPL.class);

    /**
     *
     * @param username
     * @param branch
     * @param statusId
     * @param role
     * @return
     */
    @Override
    public List<CounselorDetailsBEAN> retrieveOfficeStaffFollowUpDetails(String username, String branch, String statusId, String role) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String query = null;
        con = DBPool.getInstance().getConn();
        List<CounselorDetailsBEAN> listFollowUp = new ArrayList<CounselorDetailsBEAN>();
        try {
            switch (role) {
                case "ROLE_OFFICE":
//                    query="SELECT * FROM history_enquiry_assigning_tbl hea\n" +
//"LEFT JOIN enquiry_details ed ON ed.enquiry_id = hea.enquiry_id\n" +
//"WHERE hea.enquiry_id IN (SELECT DISTINCT enquiry_id FROM history_enquiry_assigning_tbl\n" +
//"WHERE assigned_date>=NOW() \n" +
//")\n" +
//"AND hea.assigned_branch = '"+branch+"' AND hea.current_status<>'Assessment Pending'\n" +
//"AND hea.assigned_by = '"+username+"' and DATE(assigned_date) <= DATE_ADD(CURRENT_DATE(),INTERVAL 1 DAY) AND current_status='"+statusId+"'\n" +
//"ORDER BY assigned_date DESC";
                    query = "SELECT * FROM history_enquiry_assigning_tbl hea\n"
                            + "LEFT JOIN enquiry_details ed ON ed.enquiry_id = hea.enquiry_id\n"
                            + "WHERE  hea.assigned_branch = '" + branch + "' AND hea.current_status<>'Assessment Pending'\n"
                            + "AND hea.assigned_by = '" + username + "'\n"
                            + "AND assigned_date>=NOW()\n"
                            + "AND DATE(assigned_date) <= DATE_ADD(CURRENT_DATE(),INTERVAL 1 DAY) \n"
                            + "AND current_status='" + statusId + "'\n"
                            + "AND latest_flag='y'\n"
                            + "ORDER BY assigned_date DESC";

                    query = "SELECT\n"
                            + "	*\n"
                            + "FROM\n"
                            + "	(\n"
                            + "		SELECT\n"
                            + "	hid,\n"
                            + "	assigned_by,\n"
                            + "	assigned_to,\n"
                            + "	assigned_branch,\n"
                            + "	assigned_date,\n"
                            + "	hea.enquiry_id,\n"
                            + "	purpose,\n"
                            + "	remarks,\n"
                            + "	study_required,\n"
                            + "	work_required,\n"
                            + "	training_required,\n"
                            + "	migration_required,\n"
                            + "	latest_flag,\n"
                            + "	contact_name,\n"
                            + "	contact_number,\n"
                            + "	contact_number2,\n"
                            + "	contact_email,\n"
                            + "	branch,\n"
                            + "	country,\n"
                            + "	state,\n"
                            + "	district,\n"
                            + "	enquiry_source,\n"
                            + "	enquiry_assigning,\n"
                            + "	other_enquiry,\n"
                            + "	staff_branch,\n"
                            + "	staff_usrname,\n"
                            + "	last_update,\n"
                            + "	rating,\n"
                            + "	STATUS,\n"
                            + "	edate,\n"
                            + "	std_code,\n"
                            + "	std_code2,\n"
                            + "	read_flag,\n"
                            + "	current_status,\n"
                            + "	holded_by\n"
                            + "		FROM\n"
                            + "			history_enquiry_assigning_tbl hea\n"
                            + "		LEFT JOIN enquiry_details ed ON ed.enquiry_id = hea.enquiry_id\n"
                            + "		WHERE\n"
                            + "			hea.assigned_branch = '" + branch + "'\n"
                            + "		AND hea.assigned_by = '" + username + "'\n"
                            + "		AND hea.current_status <> 'Assessment Pending'\n"
                            + "		AND hea.current_status = '" + statusId + "'\n"
                            + "		AND assigned_date >= NOW()\n"
                            + "		AND DATE(assigned_date) < DATE_ADD(\n"
                            + "			CURRENT_DATE (),\n"
                            + "			INTERVAL 1 DAY\n"
                            + "		)\n"
                            + "	) S\n"
                            + "WHERE\n"
                            + "	NOT EXISTS (\n"
                            + "		SELECT\n"
                            + "			1\n"
                            + "		FROM\n"
                            + "			history_enquiry_assigning_tbl hea\n"
                            + "		WHERE\n"
                            + "			hea.current_status <> 'Assessment Pending'\n"
                            + "		AND DATE(assigned_date) > DATE_ADD(\n"
                            + "			CURRENT_DATE (),\n"
                            + "			INTERVAL 1 DAY\n"
                            + "		)\n"
                            + "		AND latest_flag = 'y'\n"
                            + "		AND hea.enquiry_id = S.enquiry_id\n"
                            + "		AND hea.assigned_branch = S.assigned_branch\n"
                            + "	)\n"
                            + "AND EXISTS (\n"
                            + "	SELECT\n"
                            + "		1\n"
                            + "	FROM\n"
                            + "		history_enquiry_assigning_tbl\n"
                            + "	WHERE\n"
                            + "		current_status <> 'Assessment Pending'\n"
                            + "	AND assigned_date >= NOW()\n"
                            + "	AND DATE(assigned_date) < DATE_ADD(\n"
                            + "		CURRENT_DATE (),\n"
                            + "		INTERVAL 1 DAY\n"
                            + "	)\n"
                            + "	AND latest_flag = 'y'\n"
                            + "	AND enquiry_id = S.enquiry_id\n"
                            + "	AND assigned_branch = S.assigned_branch\n"
                            + ")\n"
                            + "ORDER BY\n"
                            + "	enquiry_id";
                    System.out.println("Enquiry branch Follow Up :: "+query);

                    break;
                case "ROLE_COUNSELOR":
//                     query="SELECT * FROM history_enquiry_assigning_tbl hea\n" +
//"LEFT JOIN enquiry_details ed ON ed.enquiry_id = hea.enquiry_id\n" +
//"WHERE hea.enquiry_id IN (SELECT DISTINCT enquiry_id FROM history_enquiry_assigning_tbl\n" +
//"WHERE assigned_date>=NOW() \n" +
//")\n" +
//"AND hea.assigned_branch = '"+branch+"' AND hea.current_status<>'Assessment Pending'\n" +
//"AND hea.assigned_to = '"+username+"' and DATE(assigned_date) <= DATE_ADD(CURRENT_DATE(),INTERVAL 1 DAY) AND current_status='"+statusId+"'\n" +
//"ORDER BY assigned_date DESC";
                    query = "SELECT * FROM history_enquiry_assigning_tbl hea\n"
                            + "LEFT JOIN enquiry_details ed ON ed.enquiry_id = hea.enquiry_id\n"
                            + "WHERE  hea.assigned_branch = '" + branch + "' AND hea.current_status<>'Assessment Pending'\n"
                            + "AND hea.assigned_to = '" + username + "'\n"
                            + "AND assigned_date>=NOW()\n"
                            + "AND DATE(assigned_date) <= DATE_ADD(CURRENT_DATE(),INTERVAL 1 DAY) \n"
                            + "AND current_status='" + statusId + "'\n"
                            + "AND latest_flag='y'\n"
                            + "ORDER BY assigned_date DESC";
                    query = "SELECT\n"
                            + "	*\n"
                            + "FROM\n"
                            + "	(\n"
                            + "		SELECT\n"
                            + "	hid,\n"
                            + "	assigned_by,\n"
                            + "	assigned_to,\n"
                            + "	assigned_branch,\n"
                            + "	assigned_date,\n"
                            + "	hea.enquiry_id,\n"
                            + "	purpose,\n"
                            + "	remarks,\n"
                            + "	study_required,\n"
                            + "	work_required,\n"
                            + "	training_required,\n"
                            + "	migration_required,\n"
                            + "	latest_flag,\n"
                            + "	contact_name,\n"
                            + "	contact_number,\n"
                            + "	contact_number2,\n"
                            + "	contact_email,\n"
                            + "	branch,\n"
                            + "	country,\n"
                            + "	state,\n"
                            + "	district,\n"
                            + "	enquiry_source,\n"
                            + "	enquiry_assigning,\n"
                            + "	other_enquiry,\n"
                            + "	staff_branch,\n"
                            + "	staff_usrname,\n"
                            + "	last_update,\n"
                            + "	rating,\n"
                            + "	STATUS,\n"
                            + "	edate,\n"
                            + "	std_code,\n"
                            + "	std_code2,\n"
                            + "	read_flag,\n"
                            + "	current_status,\n"
                            + "	holded_by\n"
                            + "		FROM\n"
                            + "			history_enquiry_assigning_tbl hea\n"
                            + "		LEFT JOIN enquiry_details ed ON ed.enquiry_id = hea.enquiry_id\n"
                            + "		WHERE\n"
                            + "			hea.assigned_branch = '" + branch + "'\n"
                            + "		AND hea.assigned_to = '" + username + "'\n"
                            + "		AND hea.current_status <> 'Assessment Pending'\n"
                            + "		AND hea.current_status = '" + statusId + "'\n"
                            + "		AND assigned_date >= NOW()\n"
                            + "		AND DATE(assigned_date) < DATE_ADD(\n"
                            + "			CURRENT_DATE (),\n"
                            + "			INTERVAL 1 DAY\n"
                            + "		)\n"
                            + "	) S\n"
                            + "WHERE\n"
                            + "	NOT EXISTS (\n"
                            + "		SELECT\n"
                            + "			1\n"
                            + "		FROM\n"
                            + "			history_enquiry_assigning_tbl hea\n"
                            + "		WHERE\n"
                            + "			hea.current_status <> 'Assessment Pending'\n"
                            + "		AND DATE(assigned_date) > DATE_ADD(\n"
                            + "			CURRENT_DATE (),\n"
                            + "			INTERVAL 1 DAY\n"
                            + "		)\n"
                            + "		AND latest_flag = 'y'\n"
                            + "		AND hea.enquiry_id = S.enquiry_id\n"
                            + "		AND hea.assigned_branch = S.assigned_branch\n"
                            + "	)\n"
                            + "AND EXISTS (\n"
                            + "	SELECT\n"
                            + "		1\n"
                            + "	FROM\n"
                            + "		history_enquiry_assigning_tbl\n"
                            + "	WHERE\n"
                            + "		current_status <> 'Assessment Pending'\n"
                            + "	AND assigned_date >= NOW()\n"
                            + "	AND DATE(assigned_date) < DATE_ADD(\n"
                            + "		CURRENT_DATE (),\n"
                            + "		INTERVAL 1 DAY\n"
                            + "	)\n"
                            + "	AND latest_flag = 'y'\n"
                            + "	AND enquiry_id = S.enquiry_id\n"
                            + "	AND assigned_branch = S.assigned_branch\n"
                            + ")\n"
                            + "ORDER BY\n"
                            + "	enquiry_id";
                    break;
                case "ROLE_ADMIN":
//                    query="SELECT * FROM history_enquiry_assigning_tbl hea\n" +
//"LEFT JOIN enquiry_details ed ON ed.enquiry_id = hea.enquiry_id\n" +
//"WHERE hea.enquiry_id IN (SELECT DISTINCT enquiry_id FROM history_enquiry_assigning_tbl\n" +
//"WHERE assigned_date>=NOW() \n" +
//")\n" +
//"AND hea.assigned_branch = '"+branch+"' AND hea.current_status<>'Assessment Pending'\n" +
//"and DATE(assigned_date) <= DATE_ADD(CURRENT_DATE(),INTERVAL 1 DAY) AND current_status='"+statusId+"'\n" +
//"ORDER BY assigned_date DESC";
                    query = "SELECT * FROM history_enquiry_assigning_tbl hea\n"
                            + "LEFT JOIN enquiry_details ed ON ed.enquiry_id = hea.enquiry_id\n"
                            + "WHERE  hea.assigned_branch = '" + branch + "' AND hea.current_status<>'Assessment Pending'\n"
                            + "AND assigned_date>=NOW()\n"
                            + "AND DATE(assigned_date) <= DATE_ADD(CURRENT_DATE(),INTERVAL 1 DAY) \n"
                            + "AND current_status='" + statusId + "'\n"
                            + "AND latest_flag='y'\n"
                            + "ORDER BY assigned_date DESC";
                    query = "SELECT\n"
                            + "	*\n"
                            + "FROM\n"
                            + "	(\n"
                            + "		SELECT\n"
                            + "	hid,\n"
                            + "	assigned_by,\n"
                            + "	assigned_to,\n"
                            + "	assigned_branch,\n"
                            + "	assigned_date,\n"
                            + "	hea.enquiry_id,\n"
                            + "	purpose,\n"
                            + "	remarks,\n"
                            + "	study_required,\n"
                            + "	work_required,\n"
                            + "	training_required,\n"
                            + "	migration_required,\n"
                            + "	latest_flag,\n"
                            + "	contact_name,\n"
                            + "	contact_number,\n"
                            + "	contact_number2,\n"
                            + "	contact_email,\n"
                            + "	branch,\n"
                            + "	country,\n"
                            + "	state,\n"
                            + "	district,\n"
                            + "	enquiry_source,\n"
                            + "	enquiry_assigning,\n"
                            + "	other_enquiry,\n"
                            + "	staff_branch,\n"
                            + "	staff_usrname,\n"
                            + "	last_update,\n"
                            + "	rating,\n"
                            + "	STATUS,\n"
                            + "	edate,\n"
                            + "	std_code,\n"
                            + "	std_code2,\n"
                            + "	read_flag,\n"
                            + "	current_status,\n"
                            + "	holded_by\n"
                            + "		FROM\n"
                            + "			history_enquiry_assigning_tbl hea\n"
                            + "		LEFT JOIN enquiry_details ed ON ed.enquiry_id = hea.enquiry_id\n"
                            + "		WHERE\n"
                            + "			hea.assigned_branch = '" + branch + "'\n"
                            + "		AND hea.current_status <> 'Assessment Pending'\n"
                            + "		AND hea.current_status = '" + statusId + "'\n"
                            + "		AND assigned_date >= NOW()\n"
                            + "		AND DATE(assigned_date) < DATE_ADD(\n"
                            + "			CURRENT_DATE (),\n"
                            + "			INTERVAL 1 DAY\n"
                            + "		)\n"
                            + "	) S\n"
                            + "WHERE\n"
                            + "	NOT EXISTS (\n"
                            + "		SELECT\n"
                            + "			1\n"
                            + "		FROM\n"
                            + "			history_enquiry_assigning_tbl hea\n"
                            + "		WHERE\n"
                            + "			hea.current_status <> 'Assessment Pending'\n"
                            + "		AND DATE(assigned_date) > DATE_ADD(\n"
                            + "			CURRENT_DATE (),\n"
                            + "			INTERVAL 1 DAY\n"
                            + "		)\n"
                            + "		AND latest_flag = 'y'\n"
                            + "		AND hea.enquiry_id = S.enquiry_id\n"
                            + "		AND hea.assigned_branch = S.assigned_branch\n"
                            + "	)\n"
                            + "AND EXISTS (\n"
                            + "	SELECT\n"
                            + "		1\n"
                            + "	FROM\n"
                            + "		history_enquiry_assigning_tbl\n"
                            + "	WHERE\n"
                            + "		current_status <> 'Assessment Pending'\n"
                            + "	AND assigned_date >= NOW()\n"
                            + "	AND DATE(assigned_date) < DATE_ADD(\n"
                            + "		CURRENT_DATE (),\n"
                            + "		INTERVAL 1 DAY\n"
                            + "	)\n"
                            + "	AND latest_flag = 'y'\n"
                            + "	AND enquiry_id = S.enquiry_id\n"
                            + "	AND assigned_branch = S.assigned_branch\n"
                            + ")\n"
                            + "ORDER BY\n"
                            + "	enquiry_id";
                    break;
                default:
                    System.out.println("No role detected!");
            }

//            String query ="SELECT * FROM history_enquiry_assigning_tbl hea\n" +
//"LEFT JOIN enquiry_details ed ON ed.enquiry_id = hea.enquiry_id\n" +
//"WHERE hea.enquiry_id IN (\n" +
//"SELECT enquiry_id FROM history_enquiry_assigning_tbl\n" +
//"WHERE current_status = '"+statusId+"' AND DATE(assigned_date) = CURDATE()\n" +
//")\n" +
//"AND hea.assigned_branch = '"+branch+"'\n" +
//"AND hea.assigned_by = '"+username+"'";
            System.out.println("Follow Up Query :: " + query);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                CounselorDetailsBEAN counselorDetailsBEAN = new CounselorDetailsBEAN();
                counselorDetailsBEAN.setEnquiryID(rs.getString("enquiry_id"));
                counselorDetailsBEAN.setHid(rs.getString("hid"));
                counselorDetailsBEAN.setContactName(rs.getString("contact_name"));
                counselorDetailsBEAN.setContactNumber1(rs.getString("contact_number"));
                counselorDetailsBEAN.setContactNumber2(rs.getString("contact_number2"));
                counselorDetailsBEAN.setContactEmail(rs.getString("contact_email"));
                counselorDetailsBEAN.setBracnch(rs.getString("branch"));
                counselorDetailsBEAN.setCountry(rs.getString("country"));
                counselorDetailsBEAN.setState(rs.getString("state"));
                counselorDetailsBEAN.setDistrict(rs.getString("district"));
                counselorDetailsBEAN.setEnquirySource(rs.getString("enquiry_source"));
                counselorDetailsBEAN.setEnquiryAssignedTo(rs.getString("enquiry_assigning"));
                counselorDetailsBEAN.setOtherEnquiry(rs.getString("other_enquiry"));
                counselorDetailsBEAN.setStudyRequired(rs.getString("study_required"));
//                counselorDetailsBEAN.setStudyCountryLocation(rs.getString("study_country_location"));
                counselorDetailsBEAN.setWorkRequired(rs.getString("work_required"));
//                counselorDetailsBEAN.setWorkCountryLocation(rs.getString("work_country_location"));
                counselorDetailsBEAN.setMigrationRequired(rs.getString("migration_required"));
//                counselorDetailsBEAN.setMigrateCountryLocation(rs.getString("migration_country_location"));
                counselorDetailsBEAN.setTrainingRequired(rs.getString("training_required"));
                counselorDetailsBEAN.setForwardFor(rs.getString("purpose"));
                counselorDetailsBEAN.setRemarks(rs.getString("remarks"));
                counselorDetailsBEAN.setStaffBranch(rs.getString("staff_branch"));
                counselorDetailsBEAN.setStaffUsername(rs.getString("staff_usrname"));
                counselorDetailsBEAN.setStaffBranch(rs.getString("staff_branch"));
                counselorDetailsBEAN.setLastUpdate(rs.getString("last_update"));
                counselorDetailsBEAN.setRating(rs.getString("rating"));
                counselorDetailsBEAN.setEmailStatus(rs.getString("status"));
                counselorDetailsBEAN.setEdate(rs.getString("edate"));
                counselorDetailsBEAN.setStdcode1(rs.getString("std_code"));
                counselorDetailsBEAN.setStdcode2(rs.getString("std_code2"));
                counselorDetailsBEAN.setReadflag(rs.getString("read_flag"));
                counselorDetailsBEAN.setFollowUpStatus(rs.getString("current_status"));
                counselorDetailsBEAN.setFollowUpDates(rs.getString("assigned_date"));
                counselorDetailsBEAN.setStaffUsername(rs.getString("holded_by"));
                listFollowUp.add(counselorDetailsBEAN);
            }
        } catch (SQLException sqle) {
            logger.info(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return listFollowUp;
    }

    /**
     *
     * @param branch
     * @return
     */
    @Override
    public List<CounselorDetailsBEAN> retrieveFollowUpDetails(String branch) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<CounselorDetailsBEAN> listFollowUp = new ArrayList<CounselorDetailsBEAN>();
        try {
            String query = "SELECT\n"
                    + "	*\n"
                    + "FROM\n"
                    + "	" + TableNames.TABLE_ENQ_HISTORY_ASSIGNING + " hea\n"
                    + "LEFT JOIN " + TableNames.TABLE_ENQ_DETAILS + " ed on ed.enquiry_id=hea.enquiry_id\n"
                    + "WHERE\n"
                    + "	assigned_branch = '" + branch + "'  ORDER BY assigned_date DESC";
            System.out.println("Follow Up Query :: " + query);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                CounselorDetailsBEAN counselorDetailsBEAN = new CounselorDetailsBEAN();
                counselorDetailsBEAN.setEnquiryID(rs.getString("enquiry_id"));
                counselorDetailsBEAN.setHid(rs.getString("hid"));
                counselorDetailsBEAN.setContactName(rs.getString("contact_name"));
                counselorDetailsBEAN.setContactNumber1(rs.getString("contact_number"));
                counselorDetailsBEAN.setContactNumber2(rs.getString("contact_number2"));
                counselorDetailsBEAN.setContactEmail(rs.getString("contact_email"));
                counselorDetailsBEAN.setBracnch(rs.getString("branch"));
                counselorDetailsBEAN.setCountry(rs.getString("country"));
                counselorDetailsBEAN.setState(rs.getString("state"));
                counselorDetailsBEAN.setDistrict(rs.getString("district"));
                counselorDetailsBEAN.setEnquirySource(rs.getString("enquiry_source"));
                counselorDetailsBEAN.setEnquiryAssignedTo(rs.getString("enquiry_assigning"));
                counselorDetailsBEAN.setOtherEnquiry(rs.getString("other_enquiry"));
                counselorDetailsBEAN.setStudyRequired(rs.getString("study_required"));
//                counselorDetailsBEAN.setStudyCountryLocation(rs.getString("study_country_location"));
                counselorDetailsBEAN.setWorkRequired(rs.getString("work_required"));
//                counselorDetailsBEAN.setWorkCountryLocation(rs.getString("work_country_location"));
                counselorDetailsBEAN.setMigrationRequired(rs.getString("migration_required"));
//                counselorDetailsBEAN.setMigrateCountryLocation(rs.getString("migration_country_location"));
                counselorDetailsBEAN.setTrainingRequired(rs.getString("training_required"));
                counselorDetailsBEAN.setForwardFor(rs.getString("purpose"));
                counselorDetailsBEAN.setRemarks(rs.getString("remarks"));
                counselorDetailsBEAN.setStaffBranch(rs.getString("staff_branch"));
                counselorDetailsBEAN.setStaffUsername(rs.getString("staff_usrname"));
                counselorDetailsBEAN.setStaffBranch(rs.getString("staff_branch"));
                counselorDetailsBEAN.setLastUpdate(rs.getString("last_update"));
                counselorDetailsBEAN.setRating(rs.getString("rating"));
                counselorDetailsBEAN.setEmailStatus(rs.getString("status"));
                counselorDetailsBEAN.setEdate(rs.getString("edate"));
                counselorDetailsBEAN.setStdcode1(rs.getString("std_code"));
                counselorDetailsBEAN.setStdcode2(rs.getString("std_code2"));
                counselorDetailsBEAN.setReadflag(rs.getString("read_flag"));
                counselorDetailsBEAN.setFollowUpStatus(rs.getString("current_status"));
                counselorDetailsBEAN.setFollowUpDates(rs.getString("assigned_date"));
                counselorDetailsBEAN.setStaffUsername(rs.getString("holded_by"));
                listFollowUp.add(counselorDetailsBEAN);
            }
        } catch (SQLException sqle) {
            logger.info(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return listFollowUp;
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
//        FollowUpDAO followUpDAO = new FollowUpIMPL();
//        System.out.println("folo :: " + followUpDAO.retrieveOfficeStaffFollowUpDetails("a", "kollam"));
    }

    /**
     *
     * @param username
     * @param branch
     * @return
     */
    @Override
    public List<CounselorDetailsBEAN> retrieveCounselorFollowUpDetails(String username, String branch) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param username
     * @param branch
     * @param role
     * @return
     */
    @Override
    public List<CounselorDetailsBEAN> retrieveOfficeStaffFollowTotal(String username, String branch, String role) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String query = null;
        con = DBPool.getInstance().getConn();
        List<CounselorDetailsBEAN> listFollowUp = new ArrayList<CounselorDetailsBEAN>();
        try {
            switch (role) {
                case "ROLE_OFFICE":
//                    query = "SELECT * FROM history_enquiry_assigning_tbl hea\n"
//                            + "LEFT JOIN enquiry_details ed ON ed.enquiry_id = hea.enquiry_id\n"
//                            + "WHERE hea.enquiry_id IN (\n"
//                            + "SELECT enquiry_id FROM history_enquiry_assigning_tbl\n"
//                            + "WHERE DATE(assigned_date) = CURDATE()\n"
//                            + ")\n"
//                            + "AND hea.assigned_branch = '" + CUR_BRANCH + "' AND hea.current_status<>'Assessment Pending'\n"
//                            + "AND hea.assigned_by = '" + CUR_USERNAME + "' ORDER BY assigned_date DESC";
//                    query = "SELECT * FROM history_enquiry_assigning_tbl hea\n"
//                            + "LEFT JOIN enquiry_details ed ON ed.enquiry_id = hea.enquiry_id\n"
//                            + "WHERE hea.enquiry_id IN (\n"
//                            + "SELECT DISTINCT enquiry_id FROM history_enquiry_assigning_tbl\n"
//                            + "WHERE assigned_date>=NOW()\n"
//                            + ")\n"
//                            + "AND hea.assigned_branch = '" + CUR_BRANCH + "' AND hea.current_status<>'Assessment Pending'\n"
//                            + "AND hea.assigned_by = '" + CUR_USERNAME + "' \n"
//                            + "and DATE(assigned_date) <= DATE_ADD(CURRENT_DATE(),INTERVAL 1 DAY) and assigned_date>=NOW() AND latest_flag='y' \n"
//                            + "ORDER BY assigned_date DESC";
//                    query = "SELECT\n"
//                            + "	hid,\n"
//                            + "	current_status,\n"
//                            + "	assigned_by,\n"
//                            + "	assigned_to,\n"
//                            + "	assigned_branch,\n"
//                            + "	assigned_date,\n"
//                            + "	hea.enquiry_id,\n"
//                            + "	purpose,\n"
//                            + "	remarks,\n"
//                            + "	study_required,\n"
//                            + "	work_required,\n"
//                            + "	training_required,\n"
//                            + "	migration_required,\n"
//                            + "	latest_flag,\n"
//                            + "	contact_name,\n"
//                            + "	contact_number,\n"
//                            + "	contact_number2,\n"
//                            + "	contact_email,\n"
//                            + "	branch,\n"
//                            + "	country,\n"
//                            + "	state,\n"
//                            + "	district,\n"
//                            + "	enquiry_source,\n"
//                            + "	enquiry_assigning,\n"
//                            + "	other_enquiry,\n"
//                            + "	staff_branch,\n"
//                            + "	staff_usrname,\n"
//                            + "	staff_branch,\n"
//                            + "	last_update,\n"
//                            + "	rating,\n"
//                            + "	STATUS,\n"
//                            + "	edate,\n"
//                            + "	std_code,\n"
//                            + "	std_code2,\n"
//                            + "	read_flag,\n"
//                            + "	current_status,\n"
//                            + "	holded_by\n"
//                            + "FROM\n"
//                            + "	history_enquiry_assigning_tbl hea\n"
//                            + "LEFT JOIN enquiry_details ed ON ed.enquiry_id = hea.enquiry_id\n"
//                            + "WHERE\n"
//                            + "	hea.assigned_branch = 'kollam'\n"
//                            + "AND hea.assigned_by = 'b'\n"
//                            + "AND hea.current_status <> 'Assessment Pending'\n"
//                            + "AND assigned_date >= NOW()\n"
//                            + "AND DATE(assigned_date) < DATE_ADD(\n"
//                            + "	CURRENT_DATE (),\n"
//                            + "	INTERVAL 1 DAY\n"
//                            + ")\n"
//                            + "AND latest_flag = 'y'\n"
//                            + "UNION\n"
//                            + "	SELECT\n"
//                            + "	hid,\n"
//                            + "	current_status,\n"
//                            + "	assigned_by,\n"
//                            + "	assigned_to,\n"
//                            + "	assigned_branch,\n"
//                            + "	assigned_date,\n"
//                            + "	hea.enquiry_id,\n"
//                            + "	purpose,\n"
//                            + "	remarks,\n"
//                            + "	study_required,\n"
//                            + "	work_required,\n"
//                            + "	training_required,\n"
//                            + "	migration_required,\n"
//                            + "	latest_flag,\n"
//                            + "	contact_name,\n"
//                            + "	contact_number,\n"
//                            + "	contact_number2,\n"
//                            + "	contact_email,\n"
//                            + "	branch,\n"
//                            + "	country,\n"
//                            + "	state,\n"
//                            + "	district,\n"
//                            + "	enquiry_source,\n"
//                            + "	enquiry_assigning,\n"
//                            + "	other_enquiry,\n"
//                            + "	staff_branch,\n"
//                            + "	staff_usrname,\n"
//                            + "	staff_branch,\n"
//                            + "	last_update,\n"
//                            + "	rating,\n"
//                            + "	STATUS,\n"
//                            + "	edate,\n"
//                            + "	std_code,\n"
//                            + "	std_code2,\n"
//                            + "	read_flag,\n"
//                            + "	current_status,\n"
//                            + "	holded_by\n"
//                            + "	FROM\n"
//                            + "		history_enquiry_assigning_tbl hea\n"
//                            + "	LEFT JOIN enquiry_details ed ON ed.enquiry_id = hea.enquiry_id\n"
//                            + "	WHERE\n"
//                            + "		hea.assigned_branch = '" + branch + "'\n"
//                            + "	AND hea.assigned_by = '" + username + "'\n"
//                            + "	AND hea.current_status <> 'Assessment Pending'\n"
//                            + "	AND assigned_date >= NOW()\n"
//                            + "	AND DATE(assigned_date) < DATE_ADD(\n"
//                            + "		CURRENT_DATE (),\n"
//                            + "		INTERVAL 1 DAY\n"
//                            + "	)\n"
//                            + "	AND latest_flag <> 'y'";
                    query = "SELECT\n"
                            + "	*\n"
                            + "FROM\n"
                            + "	(\n"
                            + "		SELECT\n"
                            + "	hid,\n"
                            + "	assigned_by,\n"
                            + "	assigned_to,\n"
                            + "	assigned_branch,\n"
                            + "	assigned_date,\n"
                            + "	hea.enquiry_id,\n"
                            + "	purpose,\n"
                            + "	remarks,\n"
                            + "	study_required,\n"
                            + "	work_required,\n"
                            + "	training_required,\n"
                            + "	migration_required,\n"
                            + "	latest_flag,\n"
                            + "	contact_name,\n"
                            + "	contact_number,\n"
                            + "	contact_number2,\n"
                            + "	contact_email,\n"
                            + "	branch,\n"
                            + "	country,\n"
                            + "	state,\n"
                            + "	district,\n"
                            + "	enquiry_source,\n"
                            + "	enquiry_assigning,\n"
                            + "	other_enquiry,\n"
                            + "	staff_branch,\n"
                            + "	staff_usrname,\n"
                            + "	last_update,\n"
                            + "	rating,\n"
                            + "	STATUS,\n"
                            + "	edate,\n"
                            + "	std_code,\n"
                            + "	std_code2,\n"
                            + "	read_flag,\n"
                            + "	current_status,\n"
                            + "	holded_by\n"
                            + "		FROM\n"
                            + "			history_enquiry_assigning_tbl hea\n"
                            + "		LEFT JOIN enquiry_details ed ON ed.enquiry_id = hea.enquiry_id\n"
                            + "		WHERE\n"
                            + "			hea.assigned_branch = '" + branch + "'\n"
                            + "		AND hea.assigned_by = '" + username + "'\n"
                            + "		AND hea.current_status <> 'Assessment Pending'\n"
                            + "		AND assigned_date >= NOW()\n"
                            + "		AND DATE(assigned_date) < DATE_ADD(\n"
                            + "			CURRENT_DATE (),\n"
                            + "			INTERVAL 1 DAY\n"
                            + "		)\n"
                            + "	) S\n"
                            + "WHERE\n"
                            + "	NOT EXISTS (\n"
                            + "		SELECT\n"
                            + "			1\n"
                            + "		FROM\n"
                            + "			history_enquiry_assigning_tbl hea\n"
                            + "		WHERE\n"
                            + "			hea.current_status <> 'Assessment Pending'\n"
                            + "		AND DATE(assigned_date) > DATE_ADD(\n"
                            + "			CURRENT_DATE (),\n"
                            + "			INTERVAL 1 DAY\n"
                            + "		)\n"
                            + "		AND latest_flag = 'y'\n"
                            + "		AND hea.enquiry_id = S.enquiry_id\n"
                            + "		AND hea.assigned_branch = S.assigned_branch\n"
                            + "	)\n"
                            + "AND EXISTS (\n"
                            + "	SELECT\n"
                            + "		1\n"
                            + "	FROM\n"
                            + "		history_enquiry_assigning_tbl\n"
                            + "	WHERE\n"
                            + "		current_status <> 'Assessment Pending'\n"
                            + "	AND assigned_date >= NOW()\n"
                            + "	AND DATE(assigned_date) < DATE_ADD(\n"
                            + "		CURRENT_DATE (),\n"
                            + "		INTERVAL 1 DAY\n"
                            + "	)\n"
                            + "	AND latest_flag = 'y'\n"
                            + "	AND enquiry_id = S.enquiry_id\n"
                            + "	AND assigned_branch = S.assigned_branch\n"
                            + ")\n"
                            + "ORDER BY\n"
                            + "	enquiry_id";

                    break;
                case "ROLE_COUNSELOR":
//                    query = "SELECT * FROM history_enquiry_assigning_tbl hea\n"
//                            + "LEFT JOIN enquiry_details ed ON ed.enquiry_id = hea.enquiry_id\n"
//                            + "WHERE hea.enquiry_id IN (\n"
//                            + "SELECT enquiry_id FROM history_enquiry_assigning_tbl\n"
//                            + "WHERE DATE(assigned_date) = CURDATE()\n"
//                            + ")\n"
//                            + "AND hea.assigned_branch = '" + CUR_BRANCH + "' AND hea.current_status<>'Assessment Pending'\n"
//                            + "AND hea.assigned_to = '" + CUR_USERNAME + "' ORDER BY assigned_date DESC";
                    /* ======================== Backup 7 june 2016 ==================== */
//                    query = "SELECT * FROM history_enquiry_assigning_tbl hea\n"
//                            + "LEFT JOIN enquiry_details ed ON ed.enquiry_id = hea.enquiry_id\n"
//                            + "WHERE hea.enquiry_id IN (\n"
//                            + "SELECT DISTINCT enquiry_id FROM history_enquiry_assigning_tbl\n"
//                            + "WHERE assigned_date>=NOW()\n"
//                            + ")\n"
//                            + "AND hea.assigned_branch = '" + branch + "' AND hea.current_status<>'Assessment Pending'\n"
//                            + "AND hea.assigned_to = '" + username + "' \n"
//                            + "and DATE(assigned_date) <= DATE_ADD(CURRENT_DATE(),INTERVAL 1 DAY) and assigned_date>=NOW() AND latest_flag='y' \n"
//                            + "ORDER BY assigned_date DESC";
                    query = "SELECT\n"
                            + "	*\n"
                            + "FROM\n"
                            + "	(\n"
                            + "		SELECT\n"
                            + "	hid,\n"
                            + "	assigned_by,\n"
                            + "	assigned_to,\n"
                            + "	assigned_branch,\n"
                            + "	assigned_date,\n"
                            + "	hea.enquiry_id,\n"
                            + "	purpose,\n"
                            + "	remarks,\n"
                            + "	study_required,\n"
                            + "	work_required,\n"
                            + "	training_required,\n"
                            + "	migration_required,\n"
                            + "	latest_flag,\n"
                            + "	contact_name,\n"
                            + "	contact_number,\n"
                            + "	contact_number2,\n"
                            + "	contact_email,\n"
                            + "	branch,\n"
                            + "	country,\n"
                            + "	state,\n"
                            + "	district,\n"
                            + "	enquiry_source,\n"
                            + "	enquiry_assigning,\n"
                            + "	other_enquiry,\n"
                            + "	staff_branch,\n"
                            + "	staff_usrname,\n"
                            + "	last_update,\n"
                            + "	rating,\n"
                            + "	STATUS,\n"
                            + "	edate,\n"
                            + "	std_code,\n"
                            + "	std_code2,\n"
                            + "	read_flag,\n"
                            + "	current_status,\n"
                            + "	holded_by\n"
                            + "		FROM\n"
                            + "			history_enquiry_assigning_tbl hea\n"
                            + "		LEFT JOIN enquiry_details ed ON ed.enquiry_id = hea.enquiry_id\n"
                            + "		WHERE\n"
                            + "			hea.assigned_branch = '" + branch + "'\n"
                            + "		AND hea.assigned_to = '" + username + "'\n"
                            + "		AND hea.current_status <> 'Assessment Pending'\n"
                            + "		AND assigned_date >= NOW()\n"
                            + "		AND DATE(assigned_date) < DATE_ADD(\n"
                            + "			CURRENT_DATE (),\n"
                            + "			INTERVAL 1 DAY\n"
                            + "		)\n"
                            + "	) S\n"
                            + "WHERE\n"
                            + "	NOT EXISTS (\n"
                            + "		SELECT\n"
                            + "			1\n"
                            + "		FROM\n"
                            + "			history_enquiry_assigning_tbl hea\n"
                            + "		WHERE\n"
                            + "			hea.current_status <> 'Assessment Pending'\n"
                            + "		AND DATE(assigned_date) > DATE_ADD(\n"
                            + "			CURRENT_DATE (),\n"
                            + "			INTERVAL 1 DAY\n"
                            + "		)\n"
                            + "		AND latest_flag = 'y'\n"
                            + "		AND hea.enquiry_id = S.enquiry_id\n"
                            + "		AND hea.assigned_branch = S.assigned_branch\n"
                            + "	)\n"
                            + "AND EXISTS (\n"
                            + "	SELECT\n"
                            + "		1\n"
                            + "	FROM\n"
                            + "		history_enquiry_assigning_tbl\n"
                            + "	WHERE\n"
                            + "		current_status <> 'Assessment Pending'\n"
                            + "	AND assigned_date >= NOW()\n"
                            + "	AND DATE(assigned_date) < DATE_ADD(\n"
                            + "		CURRENT_DATE (),\n"
                            + "		INTERVAL 1 DAY\n"
                            + "	)\n"
                            + "	AND latest_flag = 'y'\n"
                            + "	AND enquiry_id = S.enquiry_id\n"
                            + "	AND assigned_branch = S.assigned_branch\n"
                            + ")\n"
                            + "ORDER BY\n"
                            + "	enquiry_id";
                    break;
                case "ROLE_ADMIN":
//                    query = "SELECT * FROM history_enquiry_assigning_tbl hea\n"
//                            + "LEFT JOIN enquiry_details ed ON ed.enquiry_id = hea.enquiry_id\n"
//                            + "WHERE hea.enquiry_id IN (\n"
//                            + "SELECT enquiry_id FROM history_enquiry_assigning_tbl\n"
//                            + "WHERE DATE(assigned_date) = CURDATE()\n"
//                            + ")\n"
//                            + "AND hea.assigned_branch = '" + CUR_BRANCH + "' AND hea.current_status<>'Assessment Pending'\n"
//                            + " ORDER BY assigned_date DESC";
                    query = "SELECT * FROM history_enquiry_assigning_tbl hea\n"
                            + "LEFT JOIN enquiry_details ed ON ed.enquiry_id = hea.enquiry_id\n"
                            + "WHERE hea.enquiry_id IN (\n"
                            + "SELECT DISTINCT enquiry_id FROM history_enquiry_assigning_tbl\n"
                            + "WHERE assigned_date>=NOW()\n"
                            + ")\n"
                            + "AND hea.assigned_branch = '" + branch + "' AND hea.current_status<>'Assessment Pending'\n"
                            + "and DATE(assigned_date) <= DATE_ADD(CURRENT_DATE(),INTERVAL 1 DAY) and assigned_date>=NOW() AND latest_flag='y' \n"
                            + "ORDER BY assigned_date DESC";
                    break;
                default:
                    System.out.println("No role detected!");
            }

            System.out.println("Follow Up Query :: " + query);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                CounselorDetailsBEAN counselorDetailsBEAN = new CounselorDetailsBEAN();
                counselorDetailsBEAN.setEnquiryID(rs.getString("enquiry_id"));
                counselorDetailsBEAN.setHid(rs.getString("hid"));
                counselorDetailsBEAN.setContactName(rs.getString("contact_name"));
                counselorDetailsBEAN.setContactNumber1(rs.getString("contact_number"));
                counselorDetailsBEAN.setContactNumber2(rs.getString("contact_number2"));
                counselorDetailsBEAN.setContactEmail(rs.getString("contact_email"));
                counselorDetailsBEAN.setBracnch(rs.getString("branch"));
                counselorDetailsBEAN.setCountry(rs.getString("country"));
                counselorDetailsBEAN.setState(rs.getString("state"));
                counselorDetailsBEAN.setDistrict(rs.getString("district"));
                counselorDetailsBEAN.setEnquirySource(rs.getString("enquiry_source"));
                counselorDetailsBEAN.setEnquiryAssignedTo(rs.getString("enquiry_assigning"));
                counselorDetailsBEAN.setOtherEnquiry(rs.getString("other_enquiry"));
                counselorDetailsBEAN.setStudyRequired(rs.getString("study_required"));
//                counselorDetailsBEAN.setStudyCountryLocation(rs.getString("study_country_location"));
                counselorDetailsBEAN.setWorkRequired(rs.getString("work_required"));
//                counselorDetailsBEAN.setWorkCountryLocation(rs.getString("work_country_location"));
                counselorDetailsBEAN.setMigrationRequired(rs.getString("migration_required"));
//                counselorDetailsBEAN.setMigrateCountryLocation(rs.getString("migration_country_location"));
                counselorDetailsBEAN.setTrainingRequired(rs.getString("training_required"));
                counselorDetailsBEAN.setForwardFor(rs.getString("purpose"));
                counselorDetailsBEAN.setRemarks(rs.getString("remarks"));
                counselorDetailsBEAN.setStaffBranch(rs.getString("staff_branch"));
                counselorDetailsBEAN.setStaffUsername(rs.getString("staff_usrname"));
                counselorDetailsBEAN.setStaffBranch(rs.getString("staff_branch"));
                counselorDetailsBEAN.setLastUpdate(rs.getString("last_update"));
                counselorDetailsBEAN.setRating(rs.getString("rating"));
                counselorDetailsBEAN.setEmailStatus(rs.getString("status"));
                counselorDetailsBEAN.setEdate(rs.getString("edate"));
                counselorDetailsBEAN.setStdcode1(rs.getString("std_code"));
                counselorDetailsBEAN.setStdcode2(rs.getString("std_code2"));
                counselorDetailsBEAN.setReadflag(rs.getString("read_flag"));
                counselorDetailsBEAN.setFollowUpStatus(rs.getString("current_status"));
                counselorDetailsBEAN.setFollowUpDates(rs.getString("assigned_date"));
                counselorDetailsBEAN.setStaffUsername(rs.getString("holded_by"));
                listFollowUp.add(counselorDetailsBEAN);
            }
        } catch (SQLException sqle) {
            logger.info(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return listFollowUp;
    }

    /**
     *
     * @return
     */
    @Override
    public List<EnquiryStatusBEAN> getStatusDetails() {
        return null;
    }

    /**
     *
     * @param followUpStatus
     * @return
     */
    @Override
    public EnquiryStatusBEAN getStatusDetails(String followUpStatus) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        EnquiryStatusBEAN enquiryStatusBEAN = new EnquiryStatusBEAN();
        try {
            String query = "select * FROM mastertbl_app_status WHERE app_status_id='" + followUpStatus + "'";
            System.out.println("Follow Up Query :: " + query);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                enquiryStatusBEAN.setAppStatus(rs.getString("app_status"));
                enquiryStatusBEAN.setEmailSubject(rs.getString("subject"));
                enquiryStatusBEAN.setEmailBody(rs.getString("email_body"));
            }
        } catch (SQLException sqle) {
            logger.info(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return enquiryStatusBEAN;
    }

}
