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
package com.zs.ina.assesment.dao;

import com.zs.ina.assesment.model.AssessmentPersonBEAN;
import com.zs.ina.common.GlobalClassDAO;
import com.zs.ina.common.UiiDGenerator;
import com.zs.ina.common.constants.TableNames;
import com.zs.ina.context.Context;
import com.zs.ina.counselor.dao.model.CounselorDetailsBEAN;
import com.zs.ina.utility.ClosingResourcesInDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import zs.com.ina.db.mysql.pool.DBPool;

/**
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class PersonalCrudDAO {

    //Method for get personal details.
    /**
     *
     * @param enquiryId
     * @return
     */
    public static AssessmentPersonBEAN getPersonalDetails(String enquiryId) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        AssessmentPersonBEAN assessmentPersonPOJO = new AssessmentPersonBEAN();
        String query = "Select * from " + TableNames.TABLE_ENQ_ASMNT_PERSONAL_INFO + " where enquiry_id='" + enquiryId + "'";
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                assessmentPersonPOJO.setRowId(rs.getString("person_id"));
                assessmentPersonPOJO.setAge(rs.getString("age"));
                assessmentPersonPOJO.setGender(rs.getString("gender"));
                assessmentPersonPOJO.setMaritalStatus(rs.getString("marial_status"));
                assessmentPersonPOJO.setEmail(rs.getString("email"));
                assessmentPersonPOJO.setState(rs.getString("state"));
                assessmentPersonPOJO.setDistrict(rs.getString("district"));
                assessmentPersonPOJO.setBranch(rs.getString("branch"));
                assessmentPersonPOJO.setStdCode1(rs.getString("stdcode1"));
                assessmentPersonPOJO.setStdCode2(rs.getString("stdcode2"));
                assessmentPersonPOJO.setPhone(rs.getString("phone1"));
                assessmentPersonPOJO.setPhone2(rs.getString("phone2"));
                assessmentPersonPOJO.setDob(rs.getString("dob"));
                assessmentPersonPOJO.setPassportNo(rs.getString("passport"));
                assessmentPersonPOJO.setPreviousName(rs.getString("previous_name"));
                assessmentPersonPOJO.setHouseName(rs.getString("house_name"));
                assessmentPersonPOJO.setStreet(rs.getString("street"));
                assessmentPersonPOJO.setPlace(rs.getString("place"));
                assessmentPersonPOJO.setPostOffice(rs.getString("post_office"));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return assessmentPersonPOJO;
    }

    /**
     *
     * @param personBEAN
     */
    public static void updatePersonalDetails(CounselorDetailsBEAN personBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        con = DBPool.getInstance().getConn();
        try {
            String query = "UPDATE " + TableNames.TABLE_ENQ_ASMNT_PERSONAL_INFO + "\n"
                    + "SET age = ?,\n"
                    + " gender = ?, \n"
                    + " marial_status = ?,\n"
                    + " email = ?,\n"
                    + " state = ?,\n"
                    + " district = ?,\n"
                    + " branch =?,\n"
                    + " stdcode1 = ?,\n"
                    + " stdcode2 = ?,\n"
                    + " phone1 = ?,\n"
                    + " phone2 = ?,\n"
                    + " country = ?,\n"
                    + " dob = ?,\n"
                    + " passport = ?,\n"
                    + " updated_date = NOW(),\n"
                    + " updated_user = ?,\n"
                    + "	previous_name = ?,\n"
                    + "	house_name = ?,\n"
                    + "	street = ?,\n"
                    + "	place = ?,\n"
                    + "	post_office = ?\n"
                    + "WHERE\n"
                    + "	enquiry_id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, personBEAN.getAge());
            ps.setString(2, personBEAN.getGender());
            ps.setString(3, personBEAN.getMaritalStatus());
            ps.setString(4, personBEAN.getContactEmail());
            ps.setString(5, personBEAN.getState());
            ps.setString(6, personBEAN.getDistrict());
            ps.setString(7, personBEAN.getBracnch());
            ps.setString(8, personBEAN.getStdcode1());
            ps.setString(9, personBEAN.getStdcode2());
            ps.setString(10, personBEAN.getContactNumber1());
            ps.setString(11, personBEAN.getContactNumber2());
            ps.setString(12, personBEAN.getCountry());
            ps.setString(13, personBEAN.getDob());
            ps.setString(14, personBEAN.getPassportNo());
            ps.setString(15, Context.getInstance().currentProfile().getProfilePOJO().getUsername());
            ps.setString(16, personBEAN.getPreviousName());
            ps.setString(17, personBEAN.getHouseName());
            ps.setString(18, personBEAN.getStreet());
            ps.setString(19, personBEAN.getPlace());
            ps.setString(20, personBEAN.getPostOffice());
            ps.setString(21, personBEAN.getEnquiryID());
            ps.executeUpdate();
            System.out.println("UPdate 1 :: " + ps);
            String enquiryUpdate = "UPDATE " + TableNames.TABLE_ENQ_DETAILS + "\n" + "SET  \n" + " contact_name = ?,\n"
                    + " contact_number = ?,\n" + " contact_email = ?,\n"
                    + "state =?, district =?\n" + ",country=?,\n" + " std_code = ?,\n"
                    + " contact_number2 = ?,\n" + " std_code2 = ?\n" + "WHERE\n" + "\tenquiry_id = ?";
            ps = con.prepareStatement(enquiryUpdate);
            ps.setString(1, personBEAN.getContactName());
            ps.setString(2, personBEAN.getContactNumber1());
            ps.setString(3, personBEAN.getContactEmail());
            ps.setString(4, personBEAN.getState());
            ps.setString(5, personBEAN.getDistrict());
            ps.setString(6, personBEAN.getCountry());
            ps.setString(7, personBEAN.getStdcode1());
            ps.setString(8, personBEAN.getContactNumber2());
            ps.setString(9, personBEAN.getStdcode2());
            ps.setString(10, personBEAN.getEnquiryID());
            ps.executeUpdate();
            System.out.println("UPdate 2 :: " + ps);

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
    }

    //<------------Method to insert personal details--------------------------------->
    /**
     *
     * @param assessmentPersonBEAN
     */
    public static void insertPersonalDetails(CounselorDetailsBEAN assessmentPersonBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        con = DBPool.getInstance().getConn();
        String GENERATE_ENQ_ID = "PED";
        GENERATE_ENQ_ID = GENERATE_ENQ_ID + UiiDGenerator.getUIID8();
        try {
            String query = "INSERT INTO " + TableNames.TABLE_ENQ_ASMNT_PERSONAL_INFO + " (\n"
                    + "	person_id,\n"
                    + "	enquiry_id,\n"
                    + "	age,\n"
                    + "	gender,\n"
                    + "	marial_status,\n"
                    + "	email,\n"
                    + "	state,\n"
                    + "	district,\n"
                    + "	branch,\n"
                    + "	stdcode1,\n"
                    + "	phone1,\n"
                    + "	stdcode2,\n"
                    + "	phone2,\n"
                    + "	country,\n"
                    + "	dob,\n"
                    + "	passport,\n"
                    + "	created_user,\n"
                    + "	created_date,\n"
                    + "	updated_date,\n"
                    + "	updated_user,\n"
                    + "	latest_flag,\n"
                    + "	previous_name,\n"
                    + "	house_name,\n"
                    + "	street,\n"
                    + "	place,\n"
                    + "	post_office\n"
                    + ")\n"
                    + "VALUES\n"
                    + "	(\n"
                    + "		?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, NOW(),\n"
                    + "		NULL,\n"
                    + "		NULL,\n"
                    + "		'y',?,?,?,?,?\n"
                    + "	);\n"
                    + "";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, GENERATE_ENQ_ID);
            ps.setString(2, assessmentPersonBEAN.getEnquiryID());
            ps.setString(3, assessmentPersonBEAN.getAge());
            ps.setString(4, assessmentPersonBEAN.getGender());
            ps.setString(5, assessmentPersonBEAN.getMaritalStatus());
            ps.setString(6, assessmentPersonBEAN.getContactEmail());
            ps.setString(7, assessmentPersonBEAN.getState());
            ps.setString(8, assessmentPersonBEAN.getDistrict());
            ps.setString(9, assessmentPersonBEAN.getBracnch());
            ps.setString(10, assessmentPersonBEAN.getStdcode1());
            ps.setString(11, assessmentPersonBEAN.getContactNumber1());
            ps.setString(12, assessmentPersonBEAN.getStdcode2());
            ps.setString(13, assessmentPersonBEAN.getContactNumber2());
            ps.setString(14, assessmentPersonBEAN.getCountry());
            ps.setString(15, assessmentPersonBEAN.getDob());
            ps.setString(16, assessmentPersonBEAN.getPassportNo());
            ps.setString(17, Context.getInstance().currentProfile().getProfilePOJO().getUsername());
            ps.setString(18, assessmentPersonBEAN.getPreviousName());
            ps.setString(19, assessmentPersonBEAN.getHouseName());
            ps.setString(20, assessmentPersonBEAN.getStreet());
            ps.setString(21, assessmentPersonBEAN.getPlace());
            ps.setString(22, assessmentPersonBEAN.getPostOffice());

            if (ps.executeUpdate() > 0) {
                assessmentPersonBEAN.setRowId(GENERATE_ENQ_ID);
                GlobalClassDAO.updateLatestFlags(assessmentPersonBEAN.getEnquiryID(),
                        TableNames.TABLE_ENQ_ASMNT_PERSONAL_INFO, "n",
                        " person_id NOT IN('" + assessmentPersonBEAN.getRowId() + "')");
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
    }

    /**
     *
     * @param assessmentPersonPOJO
     */
    public static void getPersonalDetails(CounselorDetailsBEAN assessmentPersonPOJO) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        String query = "Select * from " + TableNames.TABLE_ENQ_ASMNT_PERSONAL_INFO + " where enquiry_id='" + assessmentPersonPOJO.getEnquiryID() + "'";
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                assessmentPersonPOJO.setRowId(rs.getString("person_id"));
                assessmentPersonPOJO.setAge(rs.getString("age"));
                assessmentPersonPOJO.setGender(rs.getString("gender"));
                assessmentPersonPOJO.setMaritalStatus(rs.getString("marial_status"));
                assessmentPersonPOJO.setContactEmail(rs.getString("email"));
                assessmentPersonPOJO.setState(rs.getString("state"));
                assessmentPersonPOJO.setDistrict(rs.getString("district"));
                assessmentPersonPOJO.setBracnch(rs.getString("branch"));
                assessmentPersonPOJO.setStdcode1(rs.getString("stdcode1"));
                assessmentPersonPOJO.setStdcode2(rs.getString("stdcode2"));
                assessmentPersonPOJO.setContactNumber1(rs.getString("phone1"));
                assessmentPersonPOJO.setContactNumber2(rs.getString("phone2"));
                assessmentPersonPOJO.setDob(rs.getString("dob"));
                assessmentPersonPOJO.setPassportNo(rs.getString("passport"));
                assessmentPersonPOJO.setPreviousName(rs.getString("previous_name"));
                assessmentPersonPOJO.setHouseName(rs.getString("house_name"));
                assessmentPersonPOJO.setStreet(rs.getString("street"));
                assessmentPersonPOJO.setPlace(rs.getString("place"));
                assessmentPersonPOJO.setPostOffice(rs.getString("post_office"));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
    }

    //Update Personal details in enquiry details table....
    /**
     *
     * @param enquiryID
     */
    public static void updateEnquiryPersonalDetails(String enquiryID) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        con = DBPool.getInstance().getConn();
        try {
            String query = "Update " + TableNames.TABLE_ENQ_DETAILS + "  set contact_number=?,contact_email=?,state=?,district=?,branch=?,std_code=?,contact_number2=?,std_code2=?,country=? where enquiry_id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, Context.getInstance().currentProfile().getEnquiryAssessmentPersonPOJO().getPhone());
            ps.setString(2, Context.getInstance().currentProfile().getEnquiryAssessmentPersonPOJO().getEmail());
            ps.setString(3, Context.getInstance().currentProfile().getEnquiryAssessmentPersonPOJO().getState());
            ps.setString(4, Context.getInstance().currentProfile().getEnquiryAssessmentPersonPOJO().getDistrict());
            ps.setString(5, Context.getInstance().currentProfile().getEnquiryAssessmentPersonPOJO().getBranch());
            ps.setString(6, Context.getInstance().currentProfile().getEnquiryAssessmentPersonPOJO().getStdCode1());
            ps.setString(7, Context.getInstance().currentProfile().getEnquiryAssessmentPersonPOJO().getPhone2());
            ps.setString(8, Context.getInstance().currentProfile().getEnquiryAssessmentPersonPOJO().getStdCode2());
            ps.setString(9, Context.getInstance().currentProfile().getEnquiryAssessmentPersonPOJO().getCountry());
            ps.setString(10, enquiryID);
            ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
    }

}
