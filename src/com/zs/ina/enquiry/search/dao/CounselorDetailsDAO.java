/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.enquiry.search.dao;

import com.zs.ina.common.InboxListMaker;
import com.zs.ina.common.constants.TableNames;
import com.zs.ina.counselor.dao.model.CounselorDetailsBEAN;
import com.zs.ina.enquiry.search.FXMLEnquirySearchController;
import com.zs.ina.utility.ClosingResourcesInDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import zs.com.ina.db.mysql.pool.DBPool;

/**
 *
 * @author user
 */
public class CounselorDetailsDAO {

    static Logger logger = Logger.getLogger(CounselorDetailsDAO.class);

    /**
     *
     * @param enquiryId
     * @return
     */
    public static boolean setReadFlag(String enquiryId) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        boolean completion = false;
        con = DBPool.getInstance().getConn();
        String sql = "UPDATE " + TableNames.TABLE_ENQ_DETAILS + "\n"
                + "SET read_flag = 1\n"
                + "WHERE\n"
                + "enquiry_id = " + enquiryId + "";
        try {
            stmt = con.createStatement();
            completion = stmt.execute(sql);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return completion;

    }

    //follow up
    /**
     *
     * @param username
     * @param branch
     * @return
     */
    public static List<EnquiryDetails> getFollowUpCallRegister(String username, String branch) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<EnquiryDetails> enquiryList = new ArrayList<EnquiryDetails>();
        try {
            String query = "SELECT\n"
                    + "	*\n"
                    + "FROM\n"
                    + "	" + TableNames.TABLE_ENQ_DETAILS + "\n"
                    + "WHERE\n"
                    + "	proposed_training_date >= CURDATE()\n"
                    + "AND enquiry_assigning = '" + username + "'\n"
                    + "AND branch = '" + branch + "'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                EnquiryDetails enquiryDetails = new EnquiryDetails();
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
//                System.out.println("Migration Exp:::"+rs.getString(22));
                String ListOfPrograms = "";
                if (!enquiryDetails.getStudy().equals("")) {
                    ListOfPrograms = "Study : " + enquiryDetails.getStudy() + "\n";
                    ListOfPrograms += "Course Level,Field,Remarks : " + enquiryDetails.getStudy1() + "," + enquiryDetails.getStudyTxt() + "\n";
                }
                if (!enquiryDetails.getWork().equals("")) {
                    ListOfPrograms = "Work : " + enquiryDetails.getWork() + "\n";
                    ListOfPrograms += "Designation,Education,Experience : " + enquiryDetails.getWork1() + "," + enquiryDetails.getWorkTxt() + "\n";
                }
                if (!enquiryDetails.getMigration().equals("")) {
                    ListOfPrograms = "Migration : " + enquiryDetails.getMigration() + "\n";
                    ListOfPrograms += "Designation,Education,Experience : " + enquiryDetails.getMigration1() + "," + enquiryDetails.getMigrationTxt() + "\n";
                }
                if (!enquiryDetails.getTraining().equals("")) {
                    ListOfPrograms = "Training : " + enquiryDetails.getTraining() + "\n";
                    ListOfPrograms += "Batch,Timing,Joining Date : " + enquiryDetails.getTraining1() + "," + enquiryDetails.getTrainingTxt() + "\n";
                }
                enquiryDetails.setFollowUpDateTime("" + enquiryDetails.getProposedDate() + enquiryDetails.getJoinTime());
                enquiryDetails.setListOfPrograms(ListOfPrograms);
                enquiryList.add(enquiryDetails);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return enquiryList;

    }

    /**
     *
     * @param branch
     * @param username
     * @return
     */
    public static Map<String, List<CounselorDetailsBEAN>> getPrimaryInbox(String branch, String username) {

        Map<String, List<CounselorDetailsBEAN>> primaryInboxMap = new HashMap<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        try {
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
                    + "      ed.completion_flag,\n"
                    + "      ed.staff_usrname,\n"
                    + "      ed.staff_branch,\n"
                    + "      ed.last_update,\n"
                    + "      ed.department,\n"
                    + "      ed.important_flag,\n"
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
                    + "WHERE	ed.staff_branch=? ORDER BY ed.edate DESC";
            System.out.println("All Inbox Data ===>> " + sql);
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, branch);

            rs = ps.executeQuery();
            primaryInboxMap = InboxListMaker.makePrimaryInboxMap(rs);

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }

        return primaryInboxMap;

    }

    //Method is used for set Phone2,stdcode2 etc
    /**
     *
     * @param counselorDetailsBEAN
     * @param username
     * @return
     */
    public static Map<String, List<CounselorDetailsBEAN>> getRegisteredInbox(String branch, String username) {

        Map<String, List<CounselorDetailsBEAN>> registeredInboxMap = new HashMap<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        try {
            String sql = "SELECT DISTINCT ed.*, ea.enquiry_status AS cur_status,ea.remarks,ep.district AS location,\n"
                    + "oqq.qualification as qualification_level,we.experience,es.program_field,dm.modified_date,ea.date\n"
                    + ",ea.time,asl.language_test_attend,ep.dob FROM " + TableNames.TABLE_ENQ_DETAILS + " ed\n"
                    + "LEFT JOIN " + TableNames.TABLE_ENQ_ASMNT_STATUS + " ea ON ea.enquiry_id = ed.enquiry_id\n"
                    + "LEFT JOIN " + TableNames.TABLE_ENQ_ASMNT_PERSONAL_INFO + " ep ON ea.enquiry_id = ep.enquiry_id\n"
                    + "LEFT JOIN (SELECT oq.enquiry_id,GROUP_CONCAT(teriary_quali_level,\"-\",teriary_quali_field,\"-\",university SEPARATOR ',\\n') as qualification FROM " + TableNames.TABLE_ENQ_ASMNT_TERITARY_QUALI + " oq  GROUP BY oq.enquiry_id)oqq ON oqq.enquiry_id=ed.enquiry_id \n"
                    + "LEFT JOIN (SELECT ewe.enquiry_id,GROUP_CONCAT(profession,\"-\",duration SEPARATOR ' ,\\n') as experience FROM " + TableNames.TABLE_ENQ_ASMNT_WORK_EXP + " ewe  GROUP BY ewe.enquiry_id)we ON we.enquiry_id=ed.enquiry_id\n"
                    + "LEFT JOIN  (SELECT eas.enquiry_id,GROUP_CONCAT(study,\"-\",program_level,\"-\",program_field,\"-\",institution SEPARATOR ' ,\\n') as program_field FROM " + TableNames.TABLE_ENQ_ASMNT_STUDY + " eas GROUP BY eas.enquiry_id )es ON es.enquiry_id=ed.enquiry_id\n"
                    + "LEFT JOIN  (SELECT * FROM " + TableNames.TABLE_ENQ_DATE_MODIFIED + " dmm GROUP BY dmm.enquiry_id)dm  ON dm.enquiry_id=ed.enquiry_id\n"
                    + "LEFT JOIN  (SELECT asl.enquiry_id,GROUP_CONCAT(language_test_attend SEPARATOR ' ,\\n') as language_test_attend FROM " + TableNames.TABLE_ENQ_ASMNT_LANG_TEST + " asl GROUP BY asl.enquiry_id)asl  ON asl.enquiry_id=ed.enquiry_id\n" + "WHERE \n"
                    + "ed.enquiry_assigning =? AND ed.completion_flag=1 ORDER BY ed.edate DESC";
            System.out.println("////////||||||||||||" + sql);
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);

            rs = ps.executeQuery();
            registeredInboxMap = InboxListMaker.makeRegisteredInboxMap(rs);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return registeredInboxMap;

    }

    // Inner Class for Enquiry Details
    /**
     *
     */
    public static class EnquiryDetails {

        String enquiryId;
        String conatctName, contactNumber, contactMail, branch;
        String country, state, district, source, assign;
        String study, work, migration, training, study1, work1;
        String migration1, training1, studyTxt, workTxt, migrationTxt;
        String trainingTxt, stdCode, joinTime;
        int rating, important;
        Date proposedDate;

        String listOfPrograms;
        String followUpDateTime;

        /**
         *
         * @return
         */
        public String getEnquiryId() {
            return enquiryId;
        }

        /**
         *
         * @param enquiryId
         */
        public void setEnquiryId(String enquiryId) {
            this.enquiryId = enquiryId;
        }

        /**
         *
         * @return
         */
        public String getFollowUpDateTime() {
            return followUpDateTime;
        }

        /**
         *
         * @param followUpDateTime
         */
        public void setFollowUpDateTime(String followUpDateTime) {
            this.followUpDateTime = followUpDateTime;
        }

        /**
         *
         * @return
         */
        public String getListOfPrograms() {
            return listOfPrograms;
        }

        /**
         *
         * @param listOfPrograms
         */
        public void setListOfPrograms(String listOfPrograms) {
            this.listOfPrograms = listOfPrograms;
        }

        /**
         *
         * @return
         */
        public String getJoinTime() {
            return joinTime;
        }

        /**
         *
         * @param joinTime
         */
        public void setJoinTime(String joinTime) {
            this.joinTime = joinTime;
        }

        /**
         *
         * @return
         */
        public int getImportant() {
            return important;
        }

        /**
         *
         * @param important
         */
        public void setImportant(int important) {
            this.important = important;
        }

        /**
         *
         * @return
         */
        public Date getProposedDate() {
            return proposedDate;
        }

        /**
         *
         * @param proposedDate
         */
        public void setProposedDate(Date proposedDate) {
            this.proposedDate = proposedDate;
        }

        /**
         *
         * @return
         */
        public String getConatctName() {
            return conatctName;
        }

        /**
         *
         * @param conatctName
         */
        public void setConatctName(String conatctName) {
            this.conatctName = conatctName;
        }

        /**
         *
         * @return
         */
        public String getContactNumber() {
            return contactNumber;
        }

        /**
         *
         * @param contactNumber
         */
        public void setContactNumber(String contactNumber) {
            this.contactNumber = contactNumber;
        }

        /**
         *
         * @return
         */
        public String getContactMail() {
            return contactMail;
        }

        /**
         *
         * @param contactMail
         */
        public void setContactMail(String contactMail) {
            this.contactMail = contactMail;
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
        public String getCountry() {
            return country;
        }

        /**
         *
         * @param country
         */
        public void setCountry(String country) {
            this.country = country;
        }

        /**
         *
         * @return
         */
        public String getState() {
            return state;
        }

        /**
         *
         * @param state
         */
        public void setState(String state) {
            this.state = state;
        }

        /**
         *
         * @return
         */
        public String getDistrict() {
            return district;
        }

        /**
         *
         * @param district
         */
        public void setDistrict(String district) {
            this.district = district;
        }

        /**
         *
         * @return
         */
        public String getSource() {
            return source;
        }

        /**
         *
         * @param source
         */
        public void setSource(String source) {
            this.source = source;
        }

        /**
         *
         * @return
         */
        public String getAssign() {
            return assign;
        }

        /**
         *
         * @param assign
         */
        public void setAssign(String assign) {
            this.assign = assign;
        }

        /**
         *
         * @return
         */
        public String getStudy() {
            return study;
        }

        /**
         *
         * @param study
         */
        public void setStudy(String study) {
            this.study = study;
        }

        /**
         *
         * @return
         */
        public String getWork() {
            return work;
        }

        /**
         *
         * @param work
         */
        public void setWork(String work) {
            this.work = work;
        }

        /**
         *
         * @return
         */
        public String getMigration() {
            return migration;
        }

        /**
         *
         * @param migration
         */
        public void setMigration(String migration) {
            this.migration = migration;
        }

        /**
         *
         * @return
         */
        public String getTraining() {
            return training;
        }

        /**
         *
         * @param training
         */
        public void setTraining(String training) {
            this.training = training;
        }

        /**
         *
         * @return
         */
        public String getStudy1() {
            return study1;
        }

        /**
         *
         * @param study1
         */
        public void setStudy1(String study1) {
            this.study1 = study1;
        }

        /**
         *
         * @return
         */
        public String getWork1() {
            return work1;
        }

        /**
         *
         * @param work1
         */
        public void setWork1(String work1) {
            this.work1 = work1;
        }

        /**
         *
         * @return
         */
        public String getMigration1() {
            return migration1;
        }

        /**
         *
         * @param migration1
         */
        public void setMigration1(String migration1) {
            this.migration1 = migration1;
        }

        /**
         *
         * @return
         */
        public String getTraining1() {
            return training1;
        }

        /**
         *
         * @param training1
         */
        public void setTraining1(String training1) {
            this.training1 = training1;
        }

        /**
         *
         * @return
         */
        public String getStudyTxt() {
            return studyTxt;
        }

        /**
         *
         * @param studyTxt
         */
        public void setStudyTxt(String studyTxt) {
            this.studyTxt = studyTxt;
        }

        /**
         *
         * @return
         */
        public String getWorkTxt() {
            return workTxt;
        }

        /**
         *
         * @param workTxt
         */
        public void setWorkTxt(String workTxt) {
            this.workTxt = workTxt;
        }

        /**
         *
         * @return
         */
        public String getMigrationTxt() {
            return migrationTxt;
        }

        /**
         *
         * @param migrationTxt
         */
        public void setMigrationTxt(String migrationTxt) {
            this.migrationTxt = migrationTxt;
        }

        /**
         *
         * @return
         */
        public String getTrainingTxt() {
            return trainingTxt;
        }

        /**
         *
         * @param trainingTxt
         */
        public void setTrainingTxt(String trainingTxt) {
            this.trainingTxt = trainingTxt;
        }

        /**
         *
         * @return
         */
        public int getRating() {
            return rating;
        }

        /**
         *
         * @param rating
         */
        public void setRating(int rating) {
            this.rating = rating;
        }

        /**
         *
         * @return
         */
        public String getStdCode() {
            return stdCode;
        }

        /**
         *
         * @param stdCode
         */
        public void setStdCode(String stdCode) {
            this.stdCode = stdCode;
        }

    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
//        List<CounselorDetailsBEAN> unreadInboxList = getRegisteredInbox("kollam", "a");
//        for (CounselorDetailsBEAN s : unreadInboxList) {
//            System.out.println("test ===>> ::" + s.toString());
//        }

    }
}
