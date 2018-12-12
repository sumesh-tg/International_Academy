/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.enquiry.dao;

import com.zs.ina.admin.master.retrieve.RetrieveStaticMasterDAO;
import com.zs.ina.common.constants.TableNames;
import com.zs.ina.counselor.dao.model.CounselorDetailsBEAN;
import com.zs.ina.login.dao.ProfilePOJO;
import com.zs.ina.utility.ClosingResourcesInDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import zs.com.ina.db.mysql.pool.DBPool;

/**
 *
 * @author user
 */
public class EnquiryDetailsDAO {

    static Logger logger = Logger.getLogger(EnquiryDetailsDAO.class);

    /**
     *
     * @param enquiryDetailsBEAN
     * @return
     */
    public static int insertEnquiryIntoDB(CounselorDetailsBEAN enquiryDetailsBEAN) {

        int row = 0;
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            con = DBPool.getInstance().getConn();
            String query = "INSERT INTO  " + TableNames.TABLE_ENQ_DETAILS + "  (\n"
                    + "	 enquiry_id ,\n"
                    + "	 contact_name ,\n"
                    + "	 contact_email ,\n"
                    + "	 contact_number ,\n"
                    + "	 contact_number2 ,\n"
                    + "	 std_code ,\n"
                    + "	 std_code2 ,\n"
                    + "	 branch ,\n"
                    + "	 country ,\n"
                    + "	 state ,\n"
                    + "	 district ,\n"
                    + "	 enquiry_source ,\n"
                    + "	 enquiry_assigning ,\n"
                    + "	 other_enquiry ,	\n"
                    + "	 rating ,\n"
                    + "	 status ,\n"
                    + "	 edate ,\n"
                    + "	 read_flag ,\n"
                    + "	 staff_usrname ,\n"
                    + "	 staff_branch ,\n"
                    + "	 last_update ,\n"
                    + "	 completion_flag ,\n"
                    + "	 enquiry_method ,\n"
                    + "	 search_dummy ,\n"
                    + "	 updated_time ,\n"
                    + "	 department \n"
                    + ")\n"
                    + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,(NOW()),?,?,?,?,?,?,?,?,?)\n";
            PreparedStatement ps = con.prepareStatement(query);
            enquiryDetailsBEAN.setReadflag("0");
            ps.setString(1, enquiryDetailsBEAN.getEnquiryID());
            ps.setString(2, enquiryDetailsBEAN.getContactName());
            ps.setString(3, enquiryDetailsBEAN.getContactEmail());
            ps.setString(4, enquiryDetailsBEAN.getContactNumber1());
            ps.setString(5, enquiryDetailsBEAN.getContactNumber2());
            ps.setString(6, enquiryDetailsBEAN.getStdcode1());
            ps.setString(7, enquiryDetailsBEAN.getStdcode2());
            ps.setString(8, enquiryDetailsBEAN.getBracnch());
            ps.setString(9, enquiryDetailsBEAN.getCountry());
            ps.setString(10, enquiryDetailsBEAN.getState());
            ps.setString(11, enquiryDetailsBEAN.getDistrict());
            ps.setString(12, enquiryDetailsBEAN.getEnquirySource());
            ps.setString(13, enquiryDetailsBEAN.getEnquiryAssignedTo());
            ps.setString(14, enquiryDetailsBEAN.getOtherEnquiry());
            ps.setString(15, enquiryDetailsBEAN.getRating());
            ps.setString(16, "0");
            //   ps.setString(17, enquiryDetailsBEAN.getEdate());
            ps.setString(17, enquiryDetailsBEAN.getReadflag());
            ps.setString(18, enquiryDetailsBEAN.getStaffUsername());
            ps.setString(19, enquiryDetailsBEAN.getStaffBranch());
            ps.setString(20, enquiryDetailsBEAN.getLastUpdate());
            ps.setString(21, enquiryDetailsBEAN.getCompletionflag());
            ps.setString(22, enquiryDetailsBEAN.getEnquiryMethod());
            ps.setString(23, enquiryDetailsBEAN.getStaffUsername());
            ps.setString(24, enquiryDetailsBEAN.getLastUpdate());
            ps.setString(25, enquiryDetailsBEAN.getDepartment());
            row = ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return row;

    }

    /**
     *
     * @param enquiryDetailsBEAN
     * @return
     */
    public static int updateEnquiryIntoDB(CounselorDetailsBEAN enquiryDetailsBEAN) {
        int row = 0;
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = DBPool.getInstance().getConn();
            String query = "UPDATE " + TableNames.TABLE_ENQ_DETAILS + "  SET\n"
                    + "	 contact_name=? ,\n"
                    + "	 contact_email =?,\n"
                    + "	 contact_number=? ,\n"
                    + "	 contact_number2=? ,\n"
                    + "	 std_code=? ,\n"
                    + "	 std_code2 =?,\n"
                    + "	 branch=? ,\n"
                    + "	 country =?,\n"
                    + "	 state=? ,\n"
                    + "	 district=? ,\n"
                    + "	 enquiry_source=? ,\n"
                    + "	 enquiry_assigning =?,\n"
                    + "	 other_enquiry =?,	\n"
                    + "	 rating=? ,\n"
                    + "	 status =?,\n"
                    + "	 read_flag=? ,\n"
                    + "	 last_update=? ,\n"
                    + "	 completion_flag =?,\n"
                    + "	 enquiry_method =?,\n"
                    + "	 search_dummy=? ,\n"
                    + "	 updated_time=? ,\n"
                    + "	 department=? \n"
                    + "  WHERE enquiry_id='" + enquiryDetailsBEAN.getEnquiryID() + "'\n";
            //     System.out.println("Update Query :: " + query);
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, enquiryDetailsBEAN.getContactName());
            ps.setString(2, enquiryDetailsBEAN.getContactEmail());
            ps.setString(3, enquiryDetailsBEAN.getContactNumber1());
            ps.setString(4, enquiryDetailsBEAN.getContactNumber2());
            ps.setString(5, enquiryDetailsBEAN.getStdcode1());
            ps.setString(6, enquiryDetailsBEAN.getStdcode2());
            ps.setString(7, enquiryDetailsBEAN.getBracnch());
            ps.setString(8, enquiryDetailsBEAN.getCountry());
            ps.setString(9, enquiryDetailsBEAN.getState());
            ps.setString(10, enquiryDetailsBEAN.getDistrict());
            ps.setString(11, enquiryDetailsBEAN.getEnquirySource());
            ps.setString(12, enquiryDetailsBEAN.getEnquiryAssignedTo());
            ps.setString(13, enquiryDetailsBEAN.getOtherEnquiry());
            ps.setString(14, enquiryDetailsBEAN.getRating());
            ps.setString(15, "0");
            ps.setString(16, enquiryDetailsBEAN.getReadflag());
            ps.setString(17, enquiryDetailsBEAN.getLastUpdate());
            ps.setString(18, enquiryDetailsBEAN.getCompletionflag());
            ps.setString(19, enquiryDetailsBEAN.getEnquiryMethod());
            ps.setString(20, enquiryDetailsBEAN.getStaffUsername());
            ps.setString(21, enquiryDetailsBEAN.getLastUpdate());
            ps.setString(22, enquiryDetailsBEAN.getDepartment());
            row = ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return row;

    }

    /**
     *
     * @param enquiryId
     * @param currentDetailsBEAN
     * @return
     */
    public static CounselorDetailsBEAN getEnquiryById(String enquiryId, CounselorDetailsBEAN currentDetailsBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        try {
            String sql = "SELECT\n"
                    + "	ed.*,et.enquiry_status,et.remarks\n"
                    + "FROM\n"
                    + "	" + TableNames.TABLE_ENQ_DETAILS + " ed\n"
                    + "LEFT JOIN " + TableNames.TABLE_ENQ_ASMNT_STATUS + " et on et.enquiry_id=ed.enquiry_id\n"
                    + "WHERE ed.enquiry_id= '" + enquiryId + "'\n";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            System.out.println("Enquiry Query :: " + sql);
            while (rs.next()) {
                currentDetailsBEAN.setEnquiryID(rs.getString("enquiry_id"));
                currentDetailsBEAN.setContactName(rs.getString("contact_name"));
                currentDetailsBEAN.setContactNumber1(rs.getString("contact_number"));
                currentDetailsBEAN.setContactNumber2(rs.getString("contact_number2"));
                currentDetailsBEAN.setContactEmail(rs.getString("contact_email"));
                currentDetailsBEAN.setStdcode1(rs.getString("std_code"));
                currentDetailsBEAN.setStdcode2(rs.getString("std_code2"));
                currentDetailsBEAN.setBracnch(rs.getString("branch"));
                currentDetailsBEAN.setCountry(rs.getString("country"));
                currentDetailsBEAN.setState(rs.getString("state"));
                currentDetailsBEAN.setDistrict(rs.getString("district"));
                currentDetailsBEAN.setEnquirySource(rs.getString("enquiry_source"));
                currentDetailsBEAN.setEnquiryAssignedTo(rs.getString("enquiry_assigning"));
                currentDetailsBEAN.setOtherEnquiry(rs.getString("other_enquiry"));
//                enquiryDetails.setStudyTxt(rs.getString(20));
//                enquiryDetails.setWorkTxt(rs.getString(21));
//                enquiryDetails.setMigrationTxt(rs.getString(22));
//                enquiryDetails.setTrainingTxt(rs.getString(23));
                currentDetailsBEAN.setRating(rs.getString("rating"));
//                enquiryDetails.setStdCode(rs.getString(27));
//                enquiryDetails.setProposedDate(rs.getDate(32));
//                enquiryDetails.setImportant(rs.getInt(33));
//                enquiryDetails.setJoinTime(rs.getString(34));
                currentDetailsBEAN.setEnquiryMethod(rs.getString("enquiry_method"));
//                enquiryDetails.setMethod(rs.getString(36));
//                enquiryDetails.setContact_number2(rs.getString("contact_number2"));
//                enquiryDetails.setStdcode2(rs.getString("std_code2"));
                currentDetailsBEAN.setStatus(rs.getString("enquiry_status"));
                if (rs.getString("remarks") != null) {
                    currentDetailsBEAN.setRemarks(rs.getString("remarks"));
                }
                currentDetailsBEAN.setDepartment(rs.getString("department"));
                if (currentDetailsBEAN.getStatus() == null || currentDetailsBEAN.getStatus().equals("")) {
                    currentDetailsBEAN.setStatus("Assessment Pending");
                }
            }
        } catch (SQLException sqle) {
            logger.info(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return currentDetailsBEAN;
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        RetrieveStaticMasterDAO.getStdCodes();
    }

    //Method for get the details which is already in the database
    /**
     *
     * @param stdCode
     * @param number
     * @return
     */
    public static List<EnquiryDetailsSearchPOJO> getEnquiryDetails(String stdCode, String number) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<EnquiryDetailsSearchPOJO> enquiryList = new ArrayList<EnquiryDetailsSearchPOJO>();
        try {
            String query = "Select *from " + TableNames.TABLE_ENQ_DETAILS + " where (contact_number='" + number + "' OR contact_number2= '" + number + "') AND std_code='" + stdCode + "'";
            // String query = "Select *from " + TableNames.TABLE_ENQ_DETAILS + " where contact_number='" + number + "'  and std_code='" + stdCode + "'";
            String sql = "SELECT\n"
                    + "	ed.*,et.enquiry_status,et.remarks\n"
                    + "FROM\n"
                    + "	" + TableNames.TABLE_ENQ_DETAILS + " ed\n"
                    + "LEFT JOIN " + TableNames.TABLE_ENQ_ASMNT_STATUS + " et on et.enquiry_id=ed.enquiry_id\n"
                    + "WHERE\n"
                    + "	(contact_number='" + number + "' OR contact_number2= '" + number + "') AND std_code='" + stdCode + "'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                EnquiryDetailsSearchPOJO enquiryDetails = new EnquiryDetailsSearchPOJO();
                enquiryDetails.setEnquiryId(rs.getString(1));
                enquiryDetails.setConatctName(rs.getString(2));
                enquiryDetails.setContactNumber(rs.getString(3));
                enquiryDetails.setContactMail(rs.getString(4));
                enquiryDetails.setBranch(rs.getString(5));
                enquiryDetails.setCountry(rs.getString(6));
                enquiryDetails.setState(rs.getString(7));
                enquiryDetails.setDistrict(rs.getString(8));
                enquiryDetails.setSource(rs.getString(9));
                enquiryDetails.setAssign(rs.getString(10));
                enquiryDetails.setStudy(rs.getString(12));
                enquiryDetails.setWork(rs.getString(13));
                enquiryDetails.setMigration(rs.getString(14));
                enquiryDetails.setTraining(rs.getString(15));
                enquiryDetails.setStudy1(rs.getString(16));
                enquiryDetails.setWork1(rs.getString(17));
                enquiryDetails.setMigration1(rs.getString(18));
                enquiryDetails.setTraining1(rs.getString(19));
                enquiryDetails.setStudyTxt(rs.getString(20));
                enquiryDetails.setWorkTxt(rs.getString(21));
                enquiryDetails.setMigrationTxt(rs.getString(22));
                enquiryDetails.setTrainingTxt(rs.getString(23));
                enquiryDetails.setRating(rs.getInt(24));
                enquiryDetails.setStdCode(rs.getString(27));
                enquiryDetails.setProposedDate(rs.getDate(32));
                enquiryDetails.setImportant(rs.getInt(33));
                enquiryDetails.setJoinTime(rs.getString(34));
                enquiryDetails.setEnquiryMethod(rs.getString(36));
                enquiryDetails.setMethod(rs.getString(36));
                enquiryDetails.setContact_number2(rs.getString("contact_number2"));
                enquiryDetails.setStdcode2(rs.getString("std_code2"));
                enquiryDetails.setEnquiryStatus(rs.getString("enquiry_status"));
                enquiryDetails.setRemarks(rs.getString("remarks"));
                enquiryDetails.setDepartment(rs.getString("department"));
                enquiryList.add(enquiryDetails);
                System.out.println("Enquiry Query :: " + query);
            }
        } catch (SQLException sqle) {
            logger.info(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return enquiryList;
    }

    //Method to get all contact numbers
    /**
     *
     * @return
     */
    public static List<String> getAllContactNumbers() {
        List<String> conatctNumbers = new ArrayList<>();

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        try {
            String query = "Select contact_number,contact_number2 from " + TableNames.TABLE_ENQ_DETAILS + "";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                conatctNumbers.add(rs.getString(1));
                if (rs.getString(2) != null) {
                    conatctNumbers.add(rs.getString(2));
                }
            }

        } catch (SQLException sqle) {
            logger.info(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return conatctNumbers;
    }

    /**
     *
     * @param searchTxt
     * @return
     */
    public static List<PhoneSearchPOJO> searchPhoneNumbers(String searchTxt) {
        List<PhoneSearchPOJO> listConatctNumbers = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        try {
            String query = "SELECT\n"
                    + "	enquiry_id,\n"
                    + "	contact_number,\n"
                    + "	contact_number2\n"
                    + "FROM\n"
                    + "	" + TableNames.TABLE_ENQ_DETAILS + "\n"
                    + "WHERE\n"
                    + "	(\n"
                    + "		contact_number LIKE '" + searchTxt + "%'\n"
                    + "	)\n"
                    + "AND contact_number <> ''";
            String sql = "SELECT contact_number,enquiry_id FROM enquiry_details\n"
                    + "WHERE contact_number like '" + searchTxt + "%'\n"
                    + "UNION ALL\n"
                    + "SELECT contact_number2 ,enquiry_id FROM enquiry_details\n"
                    + "WHERE contact_number2 like '" + searchTxt + "%' and contact_number2<>''";
            System.out.println("Searching Phone :: " + query);
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                PhoneSearchPOJO phoneSearchPOJO = new PhoneSearchPOJO();
                phoneSearchPOJO.setContactNumber1(rs.getString("contact_number"));
                phoneSearchPOJO.setEnquiryId(rs.getString("enquiry_id"));
                listConatctNumbers.add(phoneSearchPOJO);
            }

        } catch (SQLException sqle) {
            logger.info(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return listConatctNumbers;
    }

    //Method to get all contact Emails
    /**
     *
     * @return
     */
    public static List<String> getAllContactEmails() {
        List<String> conatctEmails = new ArrayList<>();

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        try {
            String query = "Select DISTINCT contact_email from " + TableNames.TABLE_ENQ_DETAILS + "";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                conatctEmails.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            logger.info(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return conatctEmails;
    }

    //Method to get all contact Names
    /**
     *
     * @return
     */
    public static List<String> getAllContactNames() {
        List<String> conatctNames = new ArrayList<>();

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        try {
            String query = "Select DISTINCT contact_Name from " + TableNames.TABLE_ENQ_DETAILS + "";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                conatctNames.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            logger.info(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return conatctNames;
    }

    //Method for getbranch name
    /**
     *
     * @param pojo
     * @return
     */
    public static ProfilePOJO getUserProfile(ProfilePOJO pojo) {
        String loginId = "";
        Connection con = null;
        Statement stmt = null, stmt2 = null, stmt3 = null;
        ResultSet rs = null, rs2 = null, rs3 = null;

        con = DBPool.getInstance().getConn();
        String query = " select ld.login_id,ld.first_name,ld.last_name,ld.email,ld.phone from login_details_tbl ld,login_tbl l where l.login_id=ld.login_id and l.user_name='" + pojo.getUsername() + "' ";
        String query2 = "select r.role from role_tbl r,login_tbl l where l.role_id=r.role_id and l.user_name='" + pojo.getUsername() + "'";
        try {
            stmt = con.createStatement();
            stmt2 = con.createStatement();
            rs2 = stmt2.executeQuery(query2);
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                pojo.setFname(rs.getString(2));
                pojo.setLname(rs.getString(3));
                pojo.setPhone(rs.getString(5));
                pojo.setUsername(pojo.getUsername());
                pojo.setEmail(rs.getString(4));
                pojo.setId(rs.getString(1));
                loginId = rs.getString(1);
            }

            String query3 = "select b.branch_name from branch_tbl b,login_details_tbl l where l.branch_id=b.branch_id and l.login_id='" + loginId + "'";
            stmt3 = con.createStatement();
            rs3 = stmt3.executeQuery(query3);
            while (rs3.next()) {
                pojo.setBranch(rs3.getString(1));
            }
            while (rs2.next()) {
                pojo.setRole(rs2.getString(1));
            }
        } catch (SQLException sqle) {
            logger.info(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return pojo;
    }

    /**
     *
     * @param staffname
     * @return
     */
    public static int getStaffreport(String staffname) {
        int ecount = 0;
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        try {
            String query = "SELECT\n"
                    + "	COUNT(*)\n"
                    + "FROM\n"
                    + "	" + TableNames.TABLE_ENQ_DETAILS + "\n"
                    + "WHERE\n"
                    + "	edate = CURDATE()\n"
                    + "AND staff_usrname = '" + staffname + "';";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                ecount = rs.getInt(1);
            }
        } catch (SQLException sqle) {
            logger.info(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return ecount;
    }

    /**
     *
     * @param branch
     * @return
     */
    public static List<StaffCountPOJO> getStaffEnquiryCount(String branch) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<StaffCountPOJO> countList = new ArrayList<StaffCountPOJO>();
        con = DBPool.getInstance().getConn();
        try {
            String query = "SELECT erb.USER_NAME,upper(erb.fullname) as fullname,erb.branch,count(ed.enquiry_id) AS added,count(ed2.enquiry_id) AS fake  from employee_role_branch erb\n"
                    + "left JOIN enquiry_details ed ON (\n"
                    + "	erb.USER_NAME = ed.staff_usrname AND ed.edate=CURDATE()\n"
                    + ")\n"
                    + "left JOIN enquiry_assesment_status_tbl ed2 ON (\n"
                    + "	ed.enquiry_id = ed2.enquiry_id\n"
                    + "	AND ed2.enquiry_status = (SELECT app_status_id FROM mastertbl_app_status WHERE app_status like  '%fake%' limit 1)\n"
                    + ")\n"
                    + " WHERE\n"
                    + "	erb.branch = '" + branch + "' AND erb.role='ROLE_OFFICE'\n"
                    + "GROUP BY erb.USER_NAME";
            System.out.println("Staff Count :: " + query);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                StaffCountPOJO scount = new StaffCountPOJO();
                scount.setUsername(rs.getString("USER_NAME"));
                scount.setFullname(rs.getString("fullname"));
                scount.setBranch(rs.getString("branch"));
                scount.setCount(rs.getInt("added"));
                scount.setFake(rs.getInt("fake"));
                countList.add(scount);
            }
        } catch (SQLException sqle) {
            logger.info(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return countList;
    }

    /**
     *
     */
    public static class StaffCountPOJO {

        String username, fullname;
        String branch;
        int count;
        int fake;

        /**
         *
         * @return
         */
        public String getFullname() {
            return fullname;
        }

        /**
         *
         * @param fullname
         */
        public void setFullname(String fullname) {
            this.fullname = fullname;
        }

        /**
         *
         * @return
         */
        public int getFake() {
            return fake;
        }

        /**
         *
         * @param fake
         */
        public void setFake(int fake) {
            this.fake = fake;
        }

        /**
         *
         * @return
         */
        public String getUsername() {
            return username;
        }

        /**
         *
         * @param username
         */
        public void setUsername(String username) {
            this.username = username;
        }

        /**
         *
         * @return
         */
        public String getBranch() {
            return branch;
        }

        /**
         *
         * @param branch
         */
        public void setBranch(String branch) {
            this.branch = branch;
        }

        /**
         *
         * @return
         */
        public int getCount() {
            return count;
        }

        /**
         *
         * @param count
         */
        public void setCount(int count) {
            this.count = count;
        }

        @Override
        public String toString() {
            return "StaffCountPOJO{" + "username=" + username + ", branch=" + branch + ", count=" + count + '}';
        }

    }
// retrieve follow up based on proposed joining date

    /**
     *
     * @param username
     * @param branch
     * @return
     */
    public static List<EnquiryDetailsSearchPOJO> getFollowUpCallRegister(String username, String branch) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<EnquiryDetailsSearchPOJO> enquiryList = new ArrayList<EnquiryDetailsSearchPOJO>();
        try {
            String query = "SELECT\n"
                    + "	ed.*,\n"
                    + "	et.training as programs,\n"
                    + "	et.enquiry_id,\n"
                    + "	et.from_date\n"
                    + "FROM\n"
                    + "	" + TableNames.TABLE_ENQ_DETAILS + " ed,\n"
                    + "	" + TableNames.TABLE_ENQ_ASMNT_TRAINING + " et\n"
                    + "WHERE\n"
                    + "	ed.enquiry_id = et.enquiry_id\n"
                    + "AND ed.staff_branch = '" + branch + "'\n"
                    + "AND ed.staff_usrname = '" + username + "'\n"
                    + "AND et.from_date >= CURDATE()";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                EnquiryDetailsSearchPOJO enquiryDetails = new EnquiryDetailsSearchPOJO();
                enquiryDetails.setEnquiryId(rs.getString("enquiry_id"));
                enquiryDetails.setConatctName(rs.getString("contact_name"));
                enquiryDetails.setContactNumber(rs.getString("contact_number"));
                enquiryDetails.setContactMail(rs.getString("contact_email"));
                enquiryDetails.setBranch(rs.getString("branch"));
                enquiryDetails.setCountry(rs.getString("country"));
                enquiryDetails.setState(rs.getString("state"));
                enquiryDetails.setDistrict(rs.getString("district"));
                enquiryDetails.setSource(rs.getString("enquiry_source"));
                enquiryDetails.setAssign(rs.getString("enquiry_assigning"));
                enquiryDetails.setFollowUpDateTime(rs.getString("from_date"));
                enquiryDetails.setListOfPrograms(rs.getString("programs"));
                enquiryList.add(enquiryDetails);
            }
        } catch (SQLException sqle) {
            logger.info(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return enquiryList;
    }

    // Pattern search
    /**
     *
     * @param search
     * @return
     */
    public static List<EnquiryDetailsSearchPOJO> patternSearchEnquiryDetails(String search) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<EnquiryDetailsSearchPOJO> enquiryList = new ArrayList<EnquiryDetailsSearchPOJO>();
        try {
            String query = "SELECT * FROM " + TableNames.TABLE_ENQ_DETAILS + " where contact_name LIKE '%" + search + "%'  OR "
                    + "contact_number LIKE '%" + search + "%' OR "
                    + "contact_email LIKE '%" + search + "%' OR "
                    + "branch LIKE '%" + search + "%' OR "
                    + "country LIKE '%" + search + "%' OR "
                    + "state LIKE '%" + search + "%' OR "
                    + "district LIKE '%" + search + "%' OR "
                    + "enquiry_source LIKE '%" + search + "%' OR "
                    + "enquiry_assigning LIKE '%" + search + "%' OR "
                    + "other_enquiry LIKE '%" + search + "%' OR "
                    //                    + "study LIKE '%" + search + "%' OR "
                    //                    + "work LIKE '%" + search + "%' OR "
                    //                    + "migration LIKE '%" + search + "%' OR "
                    //                    + "training LIKE '%" + search + "%' OR "
                    //                    + "study_details LIKE '%" + search + "%' OR "
                    //                    + "work_details LIKE '%" + search + "%' OR "
                    //                    + "migration_details LIKE '%" + search + "%' OR "
                    //                    + "training_details LIKE '%" + search + "%' OR "
                    //                    + "study_remarks LIKE '%" + search + "%' OR "
                    + "edate LIKE '%" + search + "%' OR "
                    + "std_code LIKE '%" + search + "%' OR "
                    + "staff_usrname LIKE '%" + search + "%' OR "
                    + "staff_branch LIKE '%" + search + "%' OR "
                    + "proposed_training_date LIKE '%" + search + "%' OR "
                    + "proposed_training_time LIKE '%" + search + "%' OR "
                    + "contact_number2 LIKE '%" + search + "%' ";
            System.out.println("Pattern Search :: " + query);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                EnquiryDetailsSearchPOJO enquiryDetails = new EnquiryDetailsSearchPOJO();
                enquiryDetails.setEnquiryId(rs.getString("enquiry_id"));
                enquiryDetails.setConatctName(rs.getString("contact_name"));
                enquiryDetails.setContactNumber(rs.getString("contact_number"));
                enquiryDetails.setContactMail(rs.getString("contact_email"));
                enquiryDetails.setBranch(rs.getString("branch"));
                enquiryDetails.setCountry(rs.getString("country"));
                enquiryDetails.setState(rs.getString("state"));
                enquiryDetails.setDistrict(rs.getString("district"));
                enquiryDetails.setSource(rs.getString("enquiry_source"));
                enquiryDetails.setAssign(rs.getString("enquiry_assigning"));
                enquiryDetails.setRating(rs.getInt("rating"));
                enquiryDetails.setStdCode(rs.getString("std_code"));
                enquiryDetails.setMethod(rs.getString("enquiry_method"));
                enquiryDetails.setContact_number2(rs.getString("contact_number2"));
                /* ====================== Code of creating pattern search in ui ====================== */

//                EnquiryDetailsSearchPOJO enquiryDetails2 = new EnquiryDetailsSearchPOJO();
//                BeanUtils.copyProperties(enquiryDetails, enquiryDetails2);
//                enquiryDetails2.setConatctName(rs.getString("contact_number"));
//                enquiryDetails2.setContactNumber(rs.getString("contact_name"));

//                EnquiryDetailsSearchPOJO enquiryDetails3 = new EnquiryDetailsSearchPOJO();
//                BeanUtils.copyProperties(enquiryDetails, enquiryDetails3);
//                enquiryDetails3.setConatctName(rs.getString("contact_email"));
//                enquiryDetails3.setContactMail(rs.getString("contact_name"));

//                EnquiryDetailsSearchPOJO enquiryDetails4 = new EnquiryDetailsSearchPOJO();
//                BeanUtils.copyProperties(enquiryDetails, enquiryDetails4);
//                enquiryDetails4.setConatctName(rs.getString("contact_number2"));
//                enquiryDetails4.setContact_number2(rs.getString("contact_name"));

//                EnquiryDetailsSearchPOJO enquiryDetails5 = new EnquiryDetailsSearchPOJO();
//                BeanUtils.copyProperties(enquiryDetails, enquiryDetails5);
//                enquiryDetails5.setConatctName(rs.getString("district"));
//                enquiryDetails5.setDistrict(rs.getString("contact_name"));

                enquiryList.add(enquiryDetails);
//                enquiryList.add(enquiryDetails2);
//                enquiryList.add(enquiryDetails3);
//                enquiryList.add(enquiryDetails4);
            }
        } catch (SQLException sqle) {
            logger.info(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return enquiryList;

    }

    /**
     *
     * @param name
     * @param phone
     * @return
     */
    public static List<EnquiryDetailsSearchPOJO> searchEnquiryDetails(String name, String phone) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<EnquiryDetailsSearchPOJO> enquiryList = new ArrayList<EnquiryDetailsSearchPOJO>();
        try {
            String sql = "SELECT\n"
                    + "	ed.*,et.enquiry_status,et.remarks\n"
                    + "FROM\n"
                    + "	" + TableNames.TABLE_ENQ_DETAILS + " ed\n"
                    + "LEFT JOIN " + TableNames.TABLE_ENQ_ASMNT_STATUS + " et on et.enquiry_id=ed.enquiry_id\n"
                    + "WHERE\n"
                    + "	contact_name='" + name + "' OR contact_number= '" + phone + "' limit 50";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                EnquiryDetailsSearchPOJO enquiryDetails = new EnquiryDetailsSearchPOJO();
                enquiryDetails.setEnquiryId(rs.getString(1));
                enquiryDetails.setConatctName(rs.getString(2));
                enquiryDetails.setContactNumber(rs.getString(3));
                enquiryDetails.setContactMail(rs.getString(4));
                enquiryDetails.setBranch(rs.getString(5));
                enquiryDetails.setCountry(rs.getString(6));
                enquiryDetails.setState(rs.getString(7));
                enquiryDetails.setDistrict(rs.getString(8));
                enquiryDetails.setSource(rs.getString(9));
                enquiryDetails.setAssign(rs.getString(10));
                enquiryDetails.setStudy(rs.getString(12));
                enquiryDetails.setWork(rs.getString(13));
                enquiryDetails.setMigration(rs.getString(14));
                enquiryDetails.setTraining(rs.getString(15));
                enquiryDetails.setStudy1(rs.getString(16));
                enquiryDetails.setWork1(rs.getString(17));
                enquiryDetails.setMigration1(rs.getString(18));
                enquiryDetails.setTraining1(rs.getString(19));
                enquiryDetails.setStudyTxt(rs.getString(20));
                enquiryDetails.setWorkTxt(rs.getString(21));
                enquiryDetails.setMigrationTxt(rs.getString(22));
                enquiryDetails.setTrainingTxt(rs.getString(23));
                enquiryDetails.setRating(rs.getInt(24));
                enquiryDetails.setStdCode(rs.getString(27));
                enquiryDetails.setProposedDate(rs.getDate(32));
                enquiryDetails.setImportant(rs.getInt(33));
                enquiryDetails.setJoinTime(rs.getString(34));
                enquiryDetails.setMethod(rs.getString(36));
                enquiryDetails.setContact_number2(rs.getString("contact_number2"));
                enquiryDetails.setStdCode(rs.getString("std_code"));
                enquiryDetails.setStdcode2(rs.getString("std_code2"));
                enquiryDetails.setEnquiryStatus(rs.getString("enquiry_status"));
                enquiryDetails.setRemarks(rs.getString("remarks"));
                enquiryList.add(enquiryDetails);
                System.out.println("Search Query :: " + sql);
            }
        } catch (SQLException sqle) {
            logger.info(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return enquiryList;
    }

}
