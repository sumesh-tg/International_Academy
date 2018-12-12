/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.common;

import com.zs.ina.counselor.dao.model.CounselorDetailsBEAN;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
/**
 *
 * @author zsuser1
 */
public class InboxListMaker {

    static Logger logger = Logger.getLogger(InboxListMaker.class);

    /* ============== make primary inbox list ==========================*/
    /**
     *
     * @param resultSet
     * @return
     */
    public static Map<String, List<CounselorDetailsBEAN>> makePrimaryInboxMap(ResultSet resultSet) {
        Map<String, List<CounselorDetailsBEAN>> primarySearchMap = new HashMap<>();
        List<CounselorDetailsBEAN> primaryInboxList = new ArrayList<>();
        List<CounselorDetailsBEAN> primaryInboxStudyList = new ArrayList<>();
        List<CounselorDetailsBEAN> primaryInboxWorkList = new ArrayList<>();
        List<CounselorDetailsBEAN> primaryInboxMigrationList = new ArrayList<>();
        List<CounselorDetailsBEAN> primaryInboxTrainingList = new ArrayList<>();
        try {
            int i = 1;
            while (resultSet.next()) {
                CounselorDetailsBEAN counselorDetailsBEAN = new CounselorDetailsBEAN();
                counselorDetailsBEAN.setEnquiryID(resultSet.getString("enquiry_id"));
                counselorDetailsBEAN.setContactName(resultSet.getString("contact_name"));
                counselorDetailsBEAN.setContactNumber1(resultSet.getString("contact_number"));
                counselorDetailsBEAN.setContactNumber2(resultSet.getString("contact_number2"));
                counselorDetailsBEAN.setContactEmail(resultSet.getString("contact_email"));
                counselorDetailsBEAN.setBracnch(resultSet.getString("branch"));
                counselorDetailsBEAN.setCountry(resultSet.getString("country"));
                counselorDetailsBEAN.setState(resultSet.getString("state"));
                counselorDetailsBEAN.setDistrict(resultSet.getString("district"));
                counselorDetailsBEAN.setEnquirySource(resultSet.getString("enquiry_source"));
                counselorDetailsBEAN.setEnquiryAssignedTo(resultSet.getString("enquiry_assigning"));
                counselorDetailsBEAN.setCounselorName(resultSet.getString("counselor_name"));
                counselorDetailsBEAN.setOtherEnquiry(resultSet.getString("other_enquiry"));
                counselorDetailsBEAN.setStudy(resultSet.getString("study"));
                counselorDetailsBEAN.setWork(resultSet.getString("work"));
                counselorDetailsBEAN.setMigration(resultSet.getString("migration"));
                counselorDetailsBEAN.setTraining(resultSet.getString("training"));
//                counselorDetailsBEAN.setStudyDetails(resultSet.getString("study_details"));
                //     counselorDetailsBEAN.setWorkDetails(resultSet.getString("work_details"));
//                counselorDetailsBEAN.setMigrationDetails(resultSet.getString("migration_details"));
//                counselorDetailsBEAN.setTrainingDetails(resultSet.getString("training_details"));
//                counselorDetailsBEAN.setOtherStudyDetails(resultSet.getString("study_remarks"));
                //        counselorDetailsBEAN.setOtherWorkDetails(resultSet.getString("work_experience"));
                //          counselorDetailsBEAN.setOtherMigrationDetails(resultSet.getString("migration_experience"));
                counselorDetailsBEAN.setOtherTrainingDetails(resultSet.getString("joining_date"));
                counselorDetailsBEAN.setRating(resultSet.getString("rating"));
                counselorDetailsBEAN.setEmailStatus(resultSet.getString("status"));
                counselorDetailsBEAN.setEdate(resultSet.getString("edate"));
                counselorDetailsBEAN.setStdcode1(resultSet.getString("std_code"));
                counselorDetailsBEAN.setStdcode2(resultSet.getString("std_code2"));
                counselorDetailsBEAN.setReadflag(resultSet.getString("read_flag"));
                counselorDetailsBEAN.setStaffUsername(resultSet.getString("staff_usrname"));
                counselorDetailsBEAN.setEnquiryAssignedBy(resultSet.getString("enquiry_assigned_by"));
                counselorDetailsBEAN.setStaffBranch(resultSet.getString("staff_branch"));
                counselorDetailsBEAN.setLastUpdate(resultSet.getString("last_update"));
//                counselorDetailsBEAN.setProposedDate(resultSet.getString("proposed_training_date"));
                counselorDetailsBEAN.setImportant(resultSet.getString("important_flag"));
                counselorDetailsBEAN.setCompletionflag(resultSet.getString("completion_flag"));
//                counselorDetailsBEAN.setProposedTime(resultSet.getString("proposed_training_time"));
                counselorDetailsBEAN.setStatus(resultSet.getString("cur_status"));
                counselorDetailsBEAN.setRemarks(resultSet.getString("remarks"));
                //    counselorDetailsBEAN.setDistrict(resultSet.getString(40));
                //      ea.date as status_date,ea.time as status_time,ea.reference
                counselorDetailsBEAN.setDepartment(resultSet.getString("department"));

                counselorDetailsBEAN.setAppointmentDate(resultSet.getString("status_date"));
                counselorDetailsBEAN.setAppointmentTime(resultSet.getString("status_time"));
                counselorDetailsBEAN.setReference(resultSet.getString("reference"));
                counselorDetailsBEAN.setForwardFor(resultSet.getString("purpose"));
                /* ====================== New columns 25-feb-2016 ====================== */
                counselorDetailsBEAN.setQualification(resultSet.getString("qualification"));
                counselorDetailsBEAN.setLanguageTest(resultSet.getString("language_test"));
                counselorDetailsBEAN.setAdmissionTest(resultSet.getString("admission_test"));
                counselorDetailsBEAN.setExperience(resultSet.getString("experience"));
                counselorDetailsBEAN.setSalaryExpected(resultSet.getString("max_salary"));
                counselorDetailsBEAN.setApplicationType(resultSet.getString("application_type"));

                /* ====================== End columns 25-feb-2016 ====================== */
                counselorDetailsBEAN.setSno(i + "");
                String ListOfPrograms = "";
                //for primary inbox
                String programs = "";
                String programRequired = "";
                //end primary inbox
                if (counselorDetailsBEAN.getStatus() == null || counselorDetailsBEAN.getStatus().equals("")) {
                    counselorDetailsBEAN.setStatus("Assessment Pending");
                }
                if (counselorDetailsBEAN.getStudy() != null) {
                    counselorDetailsBEAN.setStudyRequired(resultSet.getString("study_required"));
                    counselorDetailsBEAN.setStudyCountryLocation(resultSet.getString("study_country_location"));
                    primaryInboxStudyList.add(counselorDetailsBEAN);

                }
                if (counselorDetailsBEAN.getWork() != null) {
                    counselorDetailsBEAN.setWorkRequired(resultSet.getString("work_required"));
                    counselorDetailsBEAN.setWorkCountryLocation(resultSet.getString("work_country_location"));
                    primaryInboxWorkList.add(counselorDetailsBEAN);
                }
                if (counselorDetailsBEAN.getMigration() != null) {
                    ListOfPrograms = "Migration : " + counselorDetailsBEAN.getMigration() + "\n";
                    ListOfPrograms += "Designation,Education,Experience : " + counselorDetailsBEAN.getMigrationDetails() + "," + counselorDetailsBEAN.getOtherMigrationDetails() + "\n";
                    counselorDetailsBEAN.setMigrationRequired(resultSet.getString("migration_required"));
                    counselorDetailsBEAN.setMigrateCountryLocation(resultSet.getString("migration_country_location"));
                    programs += "Migration :" + counselorDetailsBEAN.getMigration();
                    programRequired += counselorDetailsBEAN.getMigrationDetails() + "," + counselorDetailsBEAN.getOtherMigrationDetails() + "\n";
                    primaryInboxMigrationList.add(counselorDetailsBEAN);

                }
                if (counselorDetailsBEAN.getTraining() != null) {
                    ListOfPrograms = "Training : " + counselorDetailsBEAN.getTraining() + "\n";
                    ListOfPrograms += "Batch,Timing,Joining Date : " + counselorDetailsBEAN.getTrainingDetails() + "," + counselorDetailsBEAN.getOtherTrainingDetails() + "\n";
                    counselorDetailsBEAN.setTrainingRequired(resultSet.getString("training"));
                    counselorDetailsBEAN.setJoiningDate(resultSet.getString("joining_date"));
                    counselorDetailsBEAN.setBatch(resultSet.getString("batch"));
                    programs += "Training :" + counselorDetailsBEAN.getTraining();
                    programRequired += counselorDetailsBEAN.getTrainingDetails() + "," + counselorDetailsBEAN.getOtherTrainingDetails() + "\n";
                    primaryInboxTrainingList.add(counselorDetailsBEAN);

                }
                //   counselorDetailsBEAN.setFollowUpDateTime("" + counselorDetailsBEAN.getProposedDate() + counselorDetailsBEAN.getProposedTime());
                counselorDetailsBEAN.setProgram(programs);
                counselorDetailsBEAN.setProgramRequired(programRequired);
                counselorDetailsBEAN.setListOfPrograms(ListOfPrograms);
                primaryInboxList.add(counselorDetailsBEAN);
                i++;

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            logger.error(ex.toString());
        }
        primarySearchMap.put("study", primaryInboxStudyList);
        primarySearchMap.put("work", primaryInboxWorkList);
        primarySearchMap.put("migration", primaryInboxMigrationList);
        primarySearchMap.put("training", primaryInboxTrainingList);
        primarySearchMap.put("all", primaryInboxList);
        return primarySearchMap;
    }

    /**
     *
     * @param resultSet
     * @return
     */
    public static CounselorDetailsBEAN retrieveEnquiryById(ResultSet resultSet) {
        CounselorDetailsBEAN counselorDetailsBEAN = new CounselorDetailsBEAN();
        try {
            int i = 1;
            if (resultSet.next()) {
                counselorDetailsBEAN.setEnquiryID(resultSet.getString(1));
                counselorDetailsBEAN.setContactName(resultSet.getString("contact_name"));
                counselorDetailsBEAN.setContactNumber1(resultSet.getString("contact_number"));
                counselorDetailsBEAN.setContactNumber2(resultSet.getString("contact_number2"));
                counselorDetailsBEAN.setContactEmail(resultSet.getString("contact_email"));
                counselorDetailsBEAN.setBracnch(resultSet.getString("branch"));
                counselorDetailsBEAN.setCountry(resultSet.getString("country"));
                counselorDetailsBEAN.setState(resultSet.getString("state"));
                counselorDetailsBEAN.setDistrict(resultSet.getString("district"));
                counselorDetailsBEAN.setEnquirySource(resultSet.getString("enquiry_source"));
                counselorDetailsBEAN.setEnquiryAssignedTo(resultSet.getString("enquiry_assigning"));
                counselorDetailsBEAN.setCounselorName(resultSet.getString("counselor_name"));
                counselorDetailsBEAN.setOtherEnquiry(resultSet.getString("other_enquiry"));
                counselorDetailsBEAN.setStudy(resultSet.getString("study"));
                counselorDetailsBEAN.setWork(resultSet.getString("work"));
                counselorDetailsBEAN.setMigration(resultSet.getString("migration"));
                counselorDetailsBEAN.setTraining(resultSet.getString("training"));
//                counselorDetailsBEAN.setStudyDetails(resultSet.getString("study_details"));
                //     counselorDetailsBEAN.setWorkDetails(resultSet.getString("work_details"));
//                counselorDetailsBEAN.setMigrationDetails(resultSet.getString("migration_details"));
//                counselorDetailsBEAN.setTrainingDetails(resultSet.getString("training_details"));
//                counselorDetailsBEAN.setOtherStudyDetails(resultSet.getString("study_remarks"));
                //        counselorDetailsBEAN.setOtherWorkDetails(resultSet.getString("work_experience"));
                //          counselorDetailsBEAN.setOtherMigrationDetails(resultSet.getString("migration_experience"));
                counselorDetailsBEAN.setOtherTrainingDetails(resultSet.getString("joining_date"));
                counselorDetailsBEAN.setRating(resultSet.getString("rating"));
                counselorDetailsBEAN.setEmailStatus(resultSet.getString("status"));
                counselorDetailsBEAN.setEdate(resultSet.getString("edate"));
                counselorDetailsBEAN.setStdcode1(resultSet.getString("std_code"));
                counselorDetailsBEAN.setStdcode2(resultSet.getString("std_code2"));
                counselorDetailsBEAN.setReadflag(resultSet.getString("read_flag"));
                counselorDetailsBEAN.setStaffUsername(resultSet.getString("staff_usrname"));
                counselorDetailsBEAN.setEnquiryAssignedBy(resultSet.getString("enquiry_assigned_by"));
                counselorDetailsBEAN.setStaffBranch(resultSet.getString("staff_branch"));
                counselorDetailsBEAN.setLastUpdate(resultSet.getString("last_update"));
//                counselorDetailsBEAN.setProposedDate(resultSet.getString("proposed_training_date"));
                counselorDetailsBEAN.setImportant(resultSet.getString("important_flag"));
                counselorDetailsBEAN.setCompletionflag(resultSet.getString("completion_flag"));
//                counselorDetailsBEAN.setProposedTime(resultSet.getString("proposed_training_time"));
                counselorDetailsBEAN.setStatus(resultSet.getString("cur_status"));
                counselorDetailsBEAN.setRemarks(resultSet.getString("remarks"));
                //    counselorDetailsBEAN.setDistrict(resultSet.getString(40));
                //      ea.date as status_date,ea.time as status_time,ea.reference
                counselorDetailsBEAN.setDepartment(resultSet.getString("department"));

                counselorDetailsBEAN.setAppointmentDate(resultSet.getString("status_date"));
                counselorDetailsBEAN.setAppointmentTime(resultSet.getString("status_time"));
                counselorDetailsBEAN.setReference(resultSet.getString("reference"));
                counselorDetailsBEAN.setForwardFor(resultSet.getString("purpose"));
                /* ====================== New columns 25-feb-2016 ====================== */
                counselorDetailsBEAN.setQualification(resultSet.getString("qualification"));
                counselorDetailsBEAN.setLanguageTest(resultSet.getString("language_test"));
                counselorDetailsBEAN.setAdmissionTest(resultSet.getString("admission_test"));
                counselorDetailsBEAN.setExperience(resultSet.getString("experience"));
                counselorDetailsBEAN.setSalaryExpected(resultSet.getString("max_salary"));
                counselorDetailsBEAN.setApplicationType(resultSet.getString("application_type"));

                /* ====================== End columns 25-feb-2016 ====================== */
                counselorDetailsBEAN.setSno(i + "");
                String ListOfPrograms = "";
                //for primary inbox
                String programs = "";
                String programRequired = "";
                //end primary inbox
                if (counselorDetailsBEAN.getStatus() == null || counselorDetailsBEAN.getStatus().equals("")) {
                    counselorDetailsBEAN.setStatus("Assessment Pending");
                }
                if (counselorDetailsBEAN.getStudy() != null) {
                    counselorDetailsBEAN.setStudyRequired(resultSet.getString("study_required"));
                    counselorDetailsBEAN.setStudyCountryLocation(resultSet.getString("study_country_location"));

                }
                if (counselorDetailsBEAN.getWork() != null) {
                    counselorDetailsBEAN.setWorkRequired(resultSet.getString("work_required"));
                    counselorDetailsBEAN.setWorkCountryLocation(resultSet.getString("work_country_location"));
                }
                if (counselorDetailsBEAN.getMigration() != null) {
                    ListOfPrograms = "Migration : " + counselorDetailsBEAN.getMigration() + "\n";
                    ListOfPrograms += "Designation,Education,Experience : " + counselorDetailsBEAN.getMigrationDetails() + "," + counselorDetailsBEAN.getOtherMigrationDetails() + "\n";
                    counselorDetailsBEAN.setMigrationRequired(resultSet.getString("migration_required"));
                    counselorDetailsBEAN.setMigrateCountryLocation(resultSet.getString("migration_country_location"));
                    programs += "Migration :" + counselorDetailsBEAN.getMigration();
                    programRequired += counselorDetailsBEAN.getMigrationDetails() + "," + counselorDetailsBEAN.getOtherMigrationDetails() + "\n";

                }
                if (counselorDetailsBEAN.getTraining() != null) {
                    ListOfPrograms = "Training : " + counselorDetailsBEAN.getTraining() + "\n";
                    ListOfPrograms += "Batch,Timing,Joining Date : " + counselorDetailsBEAN.getTrainingDetails() + "," + counselorDetailsBEAN.getOtherTrainingDetails() + "\n";
                    counselorDetailsBEAN.setTrainingRequired(resultSet.getString("training"));
                    counselorDetailsBEAN.setJoiningDate(resultSet.getString("joining_date"));
                    counselorDetailsBEAN.setBatch(resultSet.getString("batch"));
                    programs += "Training :" + counselorDetailsBEAN.getTraining();
                    programRequired += counselorDetailsBEAN.getTrainingDetails() + "," + counselorDetailsBEAN.getOtherTrainingDetails() + "\n";

                }
                //   counselorDetailsBEAN.setFollowUpDateTime("" + counselorDetailsBEAN.getProposedDate() + counselorDetailsBEAN.getProposedTime());
                counselorDetailsBEAN.setProgram(programs);
                counselorDetailsBEAN.setProgramRequired(programRequired);
                counselorDetailsBEAN.setListOfPrograms(ListOfPrograms);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return counselorDetailsBEAN;
    }

    /**
     *
     * @param resultSet
     * @return
     */
    public static List<CounselorDetailsBEAN> makePrimaryInboxList(ResultSet resultSet) {
        List<CounselorDetailsBEAN> primaryInboxList = new ArrayList<>();

        try {
            int i = 1;
            while (resultSet.next()) {

                CounselorDetailsBEAN counselorDetailsBEAN = new CounselorDetailsBEAN();
                counselorDetailsBEAN.setEnquiryID(resultSet.getString(1));
                counselorDetailsBEAN.setContactName(resultSet.getString("contact_name"));
                counselorDetailsBEAN.setContactNumber1(resultSet.getString("contact_number"));
                counselorDetailsBEAN.setContactEmail(resultSet.getString("contact_email"));
                counselorDetailsBEAN.setBracnch(resultSet.getString("branch"));
                counselorDetailsBEAN.setCountry(resultSet.getString("country"));
                counselorDetailsBEAN.setState(resultSet.getString("state"));
                counselorDetailsBEAN.setDistrict(resultSet.getString("district"));
                counselorDetailsBEAN.setEnquirySource(resultSet.getString("enquiry_source"));
                counselorDetailsBEAN.setEnquiryAssignedTo(resultSet.getString("enquiry_assigning"));
                counselorDetailsBEAN.setOtherEnquiry(resultSet.getString("other_enquiry"));
                counselorDetailsBEAN.setStudy(resultSet.getString("study"));
                counselorDetailsBEAN.setWork(resultSet.getString("work"));
                counselorDetailsBEAN.setMigration(resultSet.getString("migration"));
                counselorDetailsBEAN.setTraining(resultSet.getString("training"));
                counselorDetailsBEAN.setStudyDetails(resultSet.getString("study_details"));
                counselorDetailsBEAN.setWorkDetails(resultSet.getString("work_details"));
                counselorDetailsBEAN.setMigrationDetails(resultSet.getString("migration_details"));
                counselorDetailsBEAN.setTrainingDetails(resultSet.getString("training_details"));
                counselorDetailsBEAN.setOtherStudyDetails(resultSet.getString("study_remarks"));
                counselorDetailsBEAN.setOtherWorkDetails(resultSet.getString("work_experience"));
                counselorDetailsBEAN.setOtherMigrationDetails(resultSet.getString("migration_experience"));
                counselorDetailsBEAN.setOtherTrainingDetails(resultSet.getString("proposed_training_date1"));
                counselorDetailsBEAN.setRating(resultSet.getString("rating"));
                counselorDetailsBEAN.setEmailStatus(resultSet.getString("status"));
                counselorDetailsBEAN.setEdate(resultSet.getString("edate"));
                counselorDetailsBEAN.setStdcode1(resultSet.getString("std_code"));
                counselorDetailsBEAN.setReadflag(resultSet.getString("read_flag"));
                counselorDetailsBEAN.setStaffUsername(resultSet.getString("staff_usrname"));
                counselorDetailsBEAN.setStaffBranch(resultSet.getString("staff_branch"));
                counselorDetailsBEAN.setLastUpdate(resultSet.getString("last_update"));
                counselorDetailsBEAN.setProposedDate(resultSet.getString("proposed_training_date"));
                counselorDetailsBEAN.setImportant(resultSet.getString("important_flag"));
                counselorDetailsBEAN.setProposedTime(resultSet.getString("proposed_training_time"));
                counselorDetailsBEAN.setStatus(resultSet.getString("cur_status"));
                counselorDetailsBEAN.setRemarks(resultSet.getString("remarks"));
                //    counselorDetailsBEAN.setDistrict(resultSet.getString(40));
                counselorDetailsBEAN.setSno(i + "");
                String ListOfPrograms = "";
                //for primary inbox
                String programs = "";
                String programRequired = "";
                //end primary inbox
                if (!counselorDetailsBEAN.getStudy().equals("")) {
                    ListOfPrograms = "Study : " + counselorDetailsBEAN.getStudy() + "\n";
                    ListOfPrograms += "Course Level,Field,Remarks : " + counselorDetailsBEAN.getStudyDetails() + "," + counselorDetailsBEAN.getOtherStudyDetails() + "\n";

                    programs = "Study :" + counselorDetailsBEAN.getStudy();
                    programRequired = counselorDetailsBEAN.getStudyDetails() + "," + counselorDetailsBEAN.getOtherStudyDetails() + "\n";
                }
                if (!counselorDetailsBEAN.getWork().equals("")) {
                    ListOfPrograms = "Work : " + counselorDetailsBEAN.getWork() + "\n";
                    ListOfPrograms += "Designation,Education,Experience : " + counselorDetailsBEAN.getWorkDetails() + "," + counselorDetailsBEAN.getOtherWorkDetails() + "\n";

                    programs += "Work :" + counselorDetailsBEAN.getWork();
                    programRequired += counselorDetailsBEAN.getWorkDetails() + "," + counselorDetailsBEAN.getOtherWorkDetails() + "\n";

                }
                if (!counselorDetailsBEAN.getMigration().equals("")) {
                    ListOfPrograms = "Migration : " + counselorDetailsBEAN.getMigration() + "\n";
                    ListOfPrograms += "Designation,Education,Experience : " + counselorDetailsBEAN.getMigrationDetails() + "," + counselorDetailsBEAN.getOtherMigrationDetails() + "\n";

                    programs += "Migration :" + counselorDetailsBEAN.getMigration();
                    programRequired += counselorDetailsBEAN.getMigrationDetails() + "," + counselorDetailsBEAN.getOtherMigrationDetails() + "\n";

                }
                if (!counselorDetailsBEAN.getTraining().equals("")) {
                    ListOfPrograms = "Training : " + counselorDetailsBEAN.getTraining() + "\n";
                    ListOfPrograms += "Batch,Timing,Joining Date : " + counselorDetailsBEAN.getTrainingDetails() + "," + counselorDetailsBEAN.getOtherTrainingDetails() + "\n";

                    programs += "Training :" + counselorDetailsBEAN.getTraining();
                    programRequired += counselorDetailsBEAN.getTrainingDetails() + "," + counselorDetailsBEAN.getOtherTrainingDetails() + "\n";

                }
                //   counselorDetailsBEAN.setFollowUpDateTime("" + counselorDetailsBEAN.getProposedDate() + counselorDetailsBEAN.getProposedTime());
                counselorDetailsBEAN.setProgram(programs);
                counselorDetailsBEAN.setProgramRequired(programRequired);
                counselorDetailsBEAN.setListOfPrograms(ListOfPrograms);
                primaryInboxList.add(counselorDetailsBEAN);
                i++;

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            logger.error(ex.toString());
        }
        return primaryInboxList;
    }

    /* ==============end  making primary inbox list ==========================*/

 /* ============== make perspective inbox list ==========================*/
    /**
     *
     * @param resultSet
     * @return
     */
    public static Map<String, List<CounselorDetailsBEAN>> makePerspectiveInboxMap(ResultSet resultSet) {
        System.out.println("From inbox list maker");
        Map<String, List<CounselorDetailsBEAN>> perspectiveInboxMap = new HashMap<>();

        List<CounselorDetailsBEAN> perspectiveInboxList = new ArrayList<>();
        List<CounselorDetailsBEAN> perspectiveInboxStudyList = new ArrayList<>();
        List<CounselorDetailsBEAN> perspectiveInboxWorkList = new ArrayList<>();
        List<CounselorDetailsBEAN> perspectiveInboxMigrationList = new ArrayList<>();
        List<CounselorDetailsBEAN> perspectiveInboxTrainingList = new ArrayList<>();
        try {
            int i = 1;
            while (resultSet.next()) {
                String appoinment = "";
                CounselorDetailsBEAN counselorDetailsBEAN = new CounselorDetailsBEAN();
                counselorDetailsBEAN.setEnquiryID(resultSet.getString(1));
                counselorDetailsBEAN.setContactName(resultSet.getString("contact_name"));
                counselorDetailsBEAN.setContactNumber1(resultSet.getString("contact_number"));
                counselorDetailsBEAN.setContactEmail(resultSet.getString("contact_email"));
                counselorDetailsBEAN.setBracnch(resultSet.getString("branch"));
                counselorDetailsBEAN.setCountry(resultSet.getString("country"));
                counselorDetailsBEAN.setState(resultSet.getString("state"));
                counselorDetailsBEAN.setDistrict(resultSet.getString("district"));
                counselorDetailsBEAN.setEnquirySource(resultSet.getString("enquiry_source"));
                counselorDetailsBEAN.setEnquiryAssignedTo(resultSet.getString("enquiry_assigning"));
                counselorDetailsBEAN.setOtherEnquiry(resultSet.getString("other_enquiry"));
                counselorDetailsBEAN.setStudy(resultSet.getString("study"));
                counselorDetailsBEAN.setWork(resultSet.getString("work"));
                counselorDetailsBEAN.setMigration(resultSet.getString("migration"));
                counselorDetailsBEAN.setTraining(resultSet.getString("training"));
                counselorDetailsBEAN.setStudyDetails(resultSet.getString("study_details"));
                counselorDetailsBEAN.setWorkDetails(resultSet.getString("work_details"));
                counselorDetailsBEAN.setMigrationDetails(resultSet.getString("migration_details"));
                counselorDetailsBEAN.setTrainingDetails(resultSet.getString("training_details"));
                counselorDetailsBEAN.setOtherStudyDetails(resultSet.getString("study_remarks"));
                counselorDetailsBEAN.setOtherWorkDetails(resultSet.getString("work_experience"));
                counselorDetailsBEAN.setOtherMigrationDetails(resultSet.getString("migration_experience"));
                counselorDetailsBEAN.setOtherTrainingDetails(resultSet.getString("proposed_training_date1"));
                counselorDetailsBEAN.setRating(resultSet.getString("rating"));
                counselorDetailsBEAN.setEmailStatus(resultSet.getString("status"));
                counselorDetailsBEAN.setEdate(resultSet.getString("edate"));
                counselorDetailsBEAN.setStdcode1(resultSet.getString("std_code"));
                counselorDetailsBEAN.setReadflag(resultSet.getString("read_flag"));
                counselorDetailsBEAN.setStaffUsername(resultSet.getString("staff_usrname"));
                counselorDetailsBEAN.setStaffBranch(resultSet.getString("staff_branch"));
                counselorDetailsBEAN.setLastUpdate(resultSet.getString("last_update"));
                counselorDetailsBEAN.setProposedDate(resultSet.getString("proposed_training_date"));
                counselorDetailsBEAN.setImportant(resultSet.getString("important_flag"));
                counselorDetailsBEAN.setProposedTime(resultSet.getString("proposed_training_time"));
                counselorDetailsBEAN.setStatus(resultSet.getString("cur_status"));
                counselorDetailsBEAN.setRemarks(resultSet.getString("remarks"));
                counselorDetailsBEAN.setDistrict(resultSet.getString("location"));

                if (resultSet.getString("time") != null) {
                    try {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS");
                        System.out.println("DATE>>>M<<<<<::>" + resultSet.getString("time"));
                        Date date = dateFormat.parse(resultSet.getString("time"));
                        String strDateFormat = "hh:mm:ss a";
                        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
                        String time = sdf.format(date);
                        appoinment = "Direct Meeting on " + resultSet.getString("date") + " @" + time;
                    } catch (SQLException | ParseException e) {
                        e.printStackTrace();
                    }
                }
                counselorDetailsBEAN.setSno(i + "");
                String ListOfPrograms = "";
                //for primary inbox
                String programs = "";
                String programRequired = "";
                //end primary inbox
                if (!counselorDetailsBEAN.getStudy().equals("")) {
                    ListOfPrograms = "Study : " + counselorDetailsBEAN.getStudy() + "\n";
                    ListOfPrograms += "Course Level,Field,Remarks : " + counselorDetailsBEAN.getStudyDetails() + "," + counselorDetailsBEAN.getOtherStudyDetails() + "\n";

                    programs = "Study :" + counselorDetailsBEAN.getStudy();
                    programRequired = counselorDetailsBEAN.getStudyDetails() + "," + counselorDetailsBEAN.getOtherStudyDetails() + "\n";
                    perspectiveInboxStudyList.add(counselorDetailsBEAN);

                }
                if (!counselorDetailsBEAN.getWork().equals("")) {
                    ListOfPrograms = "Work : " + counselorDetailsBEAN.getWork() + "\n";
                    ListOfPrograms += "Designation,Education,Experience : " + counselorDetailsBEAN.getWorkDetails() + "," + counselorDetailsBEAN.getOtherWorkDetails() + "\n";

                    programs += "Work :" + counselorDetailsBEAN.getWork();
                    programRequired += counselorDetailsBEAN.getWorkDetails() + "," + counselorDetailsBEAN.getOtherWorkDetails() + "\n";
                    perspectiveInboxWorkList.add(counselorDetailsBEAN);

                }
                if (!counselorDetailsBEAN.getMigration().equals("")) {
                    ListOfPrograms = "Migration : " + counselorDetailsBEAN.getMigration() + "\n";
                    ListOfPrograms += "Designation,Education,Experience : " + counselorDetailsBEAN.getMigrationDetails() + "," + counselorDetailsBEAN.getOtherMigrationDetails() + "\n";

                    programs += "Migration :" + counselorDetailsBEAN.getMigration();
                    programRequired += counselorDetailsBEAN.getMigrationDetails() + "," + counselorDetailsBEAN.getOtherMigrationDetails() + "\n";
                    perspectiveInboxMigrationList.add(counselorDetailsBEAN);

                }
                if (!counselorDetailsBEAN.getTraining().equals("")) {
                    ListOfPrograms = "Training : " + counselorDetailsBEAN.getTraining() + "\n";
                    ListOfPrograms += "Batch,Timing,Joining Date : " + counselorDetailsBEAN.getTrainingDetails() + "," + counselorDetailsBEAN.getOtherTrainingDetails() + "\n";

                    programs += "Training :" + counselorDetailsBEAN.getTraining();
                    programRequired += counselorDetailsBEAN.getTrainingDetails() + "," + counselorDetailsBEAN.getOtherTrainingDetails() + "\n";
                    perspectiveInboxTrainingList.add(counselorDetailsBEAN);

                }
                //   counselorDetailsBEAN.setFollowUpDateTime("" + counselorDetailsBEAN.getProposedDate() + counselorDetailsBEAN.getProposedTime());
                counselorDetailsBEAN.setProgram(programs);
                counselorDetailsBEAN.setProgramRequired(programRequired);
                counselorDetailsBEAN.setListOfPrograms(ListOfPrograms);
                perspectiveInboxList.add(counselorDetailsBEAN);
                i++;

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            logger.error(ex.toString());
        }
        perspectiveInboxMap.put("study", perspectiveInboxStudyList);
        perspectiveInboxMap.put("work", perspectiveInboxWorkList);
        perspectiveInboxMap.put("migration", perspectiveInboxMigrationList);
        perspectiveInboxMap.put("training", perspectiveInboxTrainingList);
        perspectiveInboxMap.put("all", perspectiveInboxList);
        return perspectiveInboxMap;

    }

    /**
     *
     * @param resultSet
     * @return
     */
    public static List<CounselorDetailsBEAN> makePerspectiveInboxList(ResultSet resultSet) {
        System.out.println("From inbox list maker");
        List<CounselorDetailsBEAN> perspectiveInboxList = new ArrayList<>();
        try {
            int i = 1;
            while (resultSet.next()) {
                String appoinment = "";
                CounselorDetailsBEAN counselorDetailsBEAN = new CounselorDetailsBEAN();
                counselorDetailsBEAN.setEnquiryID(resultSet.getString(1));
                counselorDetailsBEAN.setContactName(resultSet.getString("contact_name"));
                counselorDetailsBEAN.setContactNumber1(resultSet.getString("contact_number"));
                counselorDetailsBEAN.setContactEmail(resultSet.getString("contact_email"));
                counselorDetailsBEAN.setBracnch(resultSet.getString("branch"));
                counselorDetailsBEAN.setCountry(resultSet.getString("country"));
                counselorDetailsBEAN.setState(resultSet.getString("state"));
                counselorDetailsBEAN.setDistrict(resultSet.getString("district"));
                counselorDetailsBEAN.setEnquirySource(resultSet.getString("enquiry_source"));
                counselorDetailsBEAN.setEnquiryAssignedTo(resultSet.getString("enquiry_assigning"));
                counselorDetailsBEAN.setOtherEnquiry(resultSet.getString("other_enquiry"));
                counselorDetailsBEAN.setStudy(resultSet.getString("study"));
                counselorDetailsBEAN.setWork(resultSet.getString("work"));
                counselorDetailsBEAN.setMigration(resultSet.getString("migration"));
                counselorDetailsBEAN.setTraining(resultSet.getString("training"));
                counselorDetailsBEAN.setStudyDetails(resultSet.getString("study_details"));
                counselorDetailsBEAN.setWorkDetails(resultSet.getString("work_details"));
                counselorDetailsBEAN.setMigrationDetails(resultSet.getString("migration_details"));
                counselorDetailsBEAN.setTrainingDetails(resultSet.getString("training_details"));
                counselorDetailsBEAN.setOtherStudyDetails(resultSet.getString("study_remarks"));
                counselorDetailsBEAN.setOtherWorkDetails(resultSet.getString("work_experience"));
                counselorDetailsBEAN.setOtherMigrationDetails(resultSet.getString("migration_experience"));
                counselorDetailsBEAN.setOtherTrainingDetails(resultSet.getString("proposed_training_date1"));
                counselorDetailsBEAN.setRating(resultSet.getString("rating"));
                counselorDetailsBEAN.setEmailStatus(resultSet.getString("status"));
                counselorDetailsBEAN.setEdate(resultSet.getString("edate"));
                counselorDetailsBEAN.setStdcode1(resultSet.getString("std_code"));
                counselorDetailsBEAN.setReadflag(resultSet.getString("read_flag"));
                counselorDetailsBEAN.setStaffUsername(resultSet.getString("staff_usrname"));
                counselorDetailsBEAN.setStaffBranch(resultSet.getString("staff_branch"));
                counselorDetailsBEAN.setLastUpdate(resultSet.getString("last_update"));
                counselorDetailsBEAN.setProposedDate(resultSet.getString("proposed_training_date"));
                counselorDetailsBEAN.setImportant(resultSet.getString("important_flag"));
                counselorDetailsBEAN.setProposedTime(resultSet.getString("proposed_training_time"));
                counselorDetailsBEAN.setStatus(resultSet.getString("cur_status"));
                counselorDetailsBEAN.setRemarks(resultSet.getString("remarks"));
                counselorDetailsBEAN.setDistrict(resultSet.getString("location"));

                if (resultSet.getString("time") != null) {
                    try {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS");
                        System.out.println("DATE>>>M<<<<<::>" + resultSet.getString("time"));
                        Date date = dateFormat.parse(resultSet.getString("time"));
                        String strDateFormat = "hh:mm:ss a";
                        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
                        String time = sdf.format(date);
                        appoinment = "Direct Meeting on " + resultSet.getString("date") + " @" + time;
                    } catch (SQLException | ParseException e) {
                        e.printStackTrace();
                    }
                }
                counselorDetailsBEAN.setSno(i + "");
                String ListOfPrograms = "";
                //for primary inbox
                String programs = "";
                String programRequired = "";
                //end primary inbox
                if (!counselorDetailsBEAN.getStudy().equals("")) {
                    ListOfPrograms = "Study : " + counselorDetailsBEAN.getStudy() + "\n";
                    ListOfPrograms += "Course Level,Field,Remarks : " + counselorDetailsBEAN.getStudyDetails() + "," + counselorDetailsBEAN.getOtherStudyDetails() + "\n";

                    programs = "Study :" + counselorDetailsBEAN.getStudy();
                    programRequired = counselorDetailsBEAN.getStudyDetails() + "," + counselorDetailsBEAN.getOtherStudyDetails() + "\n";
                }
                if (!counselorDetailsBEAN.getWork().equals("")) {
                    ListOfPrograms = "Work : " + counselorDetailsBEAN.getWork() + "\n";
                    ListOfPrograms += "Designation,Education,Experience : " + counselorDetailsBEAN.getWorkDetails() + "," + counselorDetailsBEAN.getOtherWorkDetails() + "\n";

                    programs += "Work :" + counselorDetailsBEAN.getWork();
                    programRequired += counselorDetailsBEAN.getWorkDetails() + "," + counselorDetailsBEAN.getOtherWorkDetails() + "\n";

                }
                if (!counselorDetailsBEAN.getMigration().equals("")) {
                    ListOfPrograms = "Migration : " + counselorDetailsBEAN.getMigration() + "\n";
                    ListOfPrograms += "Designation,Education,Experience : " + counselorDetailsBEAN.getMigrationDetails() + "," + counselorDetailsBEAN.getOtherMigrationDetails() + "\n";

                    programs += "Migration :" + counselorDetailsBEAN.getMigration();
                    programRequired += counselorDetailsBEAN.getMigrationDetails() + "," + counselorDetailsBEAN.getOtherMigrationDetails() + "\n";

                }
                if (!counselorDetailsBEAN.getTraining().equals("")) {
                    ListOfPrograms = "Training : " + counselorDetailsBEAN.getTraining() + "\n";
                    ListOfPrograms += "Batch,Timing,Joining Date : " + counselorDetailsBEAN.getTrainingDetails() + "," + counselorDetailsBEAN.getOtherTrainingDetails() + "\n";

                    programs += "Training :" + counselorDetailsBEAN.getTraining();
                    programRequired += counselorDetailsBEAN.getTrainingDetails() + "," + counselorDetailsBEAN.getOtherTrainingDetails() + "\n";

                }
                //   counselorDetailsBEAN.setFollowUpDateTime("" + counselorDetailsBEAN.getProposedDate() + counselorDetailsBEAN.getProposedTime());
                counselorDetailsBEAN.setProgram(programs);
                counselorDetailsBEAN.setProgramRequired(programRequired);
                counselorDetailsBEAN.setListOfPrograms(ListOfPrograms);
                perspectiveInboxList.add(counselorDetailsBEAN);
                i++;

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            logger.error(ex.toString());
        }
        return perspectiveInboxList;

    }

    /* ============== end  making perspective inbox list ==========================*/

 /* ============== make registered inbox list ==========================*/
    /**
     *
     * @param resultSet
     * @return
     */
    public static List<CounselorDetailsBEAN> makeRegisteredInboxList(ResultSet resultSet) {
        System.out.println("Inside make reg inbox list");
        List<CounselorDetailsBEAN> registeredInboxList = new ArrayList<>();
        try {
            int i = 1;
            while (resultSet.next()) {
                String appoinment = "";
                CounselorDetailsBEAN counselorDetailsBEAN = new CounselorDetailsBEAN();
                counselorDetailsBEAN.setEnquiryID(resultSet.getString(1));
                counselorDetailsBEAN.setContactName(resultSet.getString("contact_name"));
                counselorDetailsBEAN.setContactNumber1(resultSet.getString("contact_number"));
                counselorDetailsBEAN.setContactEmail(resultSet.getString("contact_email"));
                counselorDetailsBEAN.setBracnch(resultSet.getString("branch"));
                counselorDetailsBEAN.setCountry(resultSet.getString("country"));
                counselorDetailsBEAN.setState(resultSet.getString("state"));
                counselorDetailsBEAN.setDistrict(resultSet.getString("district"));
                counselorDetailsBEAN.setEnquirySource(resultSet.getString("enquiry_source"));
                counselorDetailsBEAN.setEnquiryAssignedTo(resultSet.getString("enquiry_assigning"));
                counselorDetailsBEAN.setOtherEnquiry(resultSet.getString("other_enquiry"));
                counselorDetailsBEAN.setStudy(resultSet.getString("study"));
                counselorDetailsBEAN.setWork(resultSet.getString("work"));
                counselorDetailsBEAN.setMigration(resultSet.getString("migration"));
                counselorDetailsBEAN.setTraining(resultSet.getString("training"));
                counselorDetailsBEAN.setStudyDetails(resultSet.getString("study_details"));
                counselorDetailsBEAN.setWorkDetails(resultSet.getString("work_details"));
                counselorDetailsBEAN.setMigrationDetails(resultSet.getString("migration_details"));
                counselorDetailsBEAN.setTrainingDetails(resultSet.getString("training_details"));
                counselorDetailsBEAN.setOtherStudyDetails(resultSet.getString("study_remarks"));
                counselorDetailsBEAN.setOtherWorkDetails(resultSet.getString("work_experience"));
                counselorDetailsBEAN.setOtherMigrationDetails(resultSet.getString("migration_experience"));
                counselorDetailsBEAN.setOtherTrainingDetails(resultSet.getString("proposed_training_date1"));
                counselorDetailsBEAN.setRating(resultSet.getString("rating"));
                counselorDetailsBEAN.setEmailStatus(resultSet.getString("status"));
                counselorDetailsBEAN.setEdate(resultSet.getString("edate"));
                counselorDetailsBEAN.setStdcode1(resultSet.getString("std_code"));
                counselorDetailsBEAN.setReadflag(resultSet.getString("read_flag"));
                counselorDetailsBEAN.setStaffUsername(resultSet.getString("staff_usrname"));
                counselorDetailsBEAN.setStaffBranch(resultSet.getString("staff_branch"));
                counselorDetailsBEAN.setLastUpdate(resultSet.getString("last_update"));
                counselorDetailsBEAN.setProposedDate(resultSet.getString("proposed_training_date"));
                counselorDetailsBEAN.setImportant(resultSet.getString("important_flag"));
                counselorDetailsBEAN.setProposedTime(resultSet.getString("proposed_training_time"));
                counselorDetailsBEAN.setStatus(resultSet.getString("cur_status"));
                counselorDetailsBEAN.setRemarks(resultSet.getString("remarks"));
                counselorDetailsBEAN.setDistrict(resultSet.getString("location"));
                counselorDetailsBEAN.setDob(resultSet.getString("dob"));
                if (resultSet.getString("time") != null) {
                    try {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS");
                        System.out.println("DATE>>>M<<<<<::>" + resultSet.getString("time"));
                        Date date = dateFormat.parse(resultSet.getString("time"));
                        String strDateFormat = "hh:mm:ss a";
                        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
                        String time = sdf.format(date);
                        appoinment = "Direct Meeting on " + resultSet.getString("date") + " @" + time;
                    } catch (SQLException | ParseException e) {
                        e.printStackTrace();
                    }
                }
                counselorDetailsBEAN.setSno(i + "");
                String ListOfPrograms = "";
                //for primary inbox
                String programs = "";
                String programRequired = "";
                //end primary inbox
                if (!counselorDetailsBEAN.getStudy().equals("")) {
                    ListOfPrograms = "Study : " + counselorDetailsBEAN.getStudy() + "\n";
                    ListOfPrograms += "Course Level,Field,Remarks : " + counselorDetailsBEAN.getStudyDetails() + "," + counselorDetailsBEAN.getOtherStudyDetails() + "\n";

                    programs = "Study :" + counselorDetailsBEAN.getStudy();
                    programRequired = counselorDetailsBEAN.getStudyDetails() + "," + counselorDetailsBEAN.getOtherStudyDetails() + "\n";
                }
                if (!counselorDetailsBEAN.getWork().equals("")) {
                    ListOfPrograms = "Work : " + counselorDetailsBEAN.getWork() + "\n";
                    ListOfPrograms += "Designation,Education,Experience : " + counselorDetailsBEAN.getWorkDetails() + "," + counselorDetailsBEAN.getOtherWorkDetails() + "\n";

                    programs += "Work :" + counselorDetailsBEAN.getWork();
                    programRequired += counselorDetailsBEAN.getWorkDetails() + "," + counselorDetailsBEAN.getOtherWorkDetails() + "\n";

                }
                if (!counselorDetailsBEAN.getMigration().equals("")) {
                    ListOfPrograms = "Migration : " + counselorDetailsBEAN.getMigration() + "\n";
                    ListOfPrograms += "Designation,Education,Experience : " + counselorDetailsBEAN.getMigrationDetails() + "," + counselorDetailsBEAN.getOtherMigrationDetails() + "\n";

                    programs += "Migration :" + counselorDetailsBEAN.getMigration();
                    programRequired += counselorDetailsBEAN.getMigrationDetails() + "," + counselorDetailsBEAN.getOtherMigrationDetails() + "\n";

                }
                if (!counselorDetailsBEAN.getTraining().equals("")) {
                    ListOfPrograms = "Training : " + counselorDetailsBEAN.getTraining() + "\n";
                    ListOfPrograms += "Batch,Timing,Joining Date : " + counselorDetailsBEAN.getTrainingDetails() + "," + counselorDetailsBEAN.getOtherTrainingDetails() + "\n";

                    programs += "Training :" + counselorDetailsBEAN.getTraining();
                    programRequired += counselorDetailsBEAN.getTrainingDetails() + "," + counselorDetailsBEAN.getOtherTrainingDetails() + "\n";

                }
                //   counselorDetailsBEAN.setFollowUpDateTime("" + counselorDetailsBEAN.getProposedDate() + counselorDetailsBEAN.getProposedTime());
                counselorDetailsBEAN.setProgram(programs);
                counselorDetailsBEAN.setProgramRequired(programRequired);
                counselorDetailsBEAN.setListOfPrograms(ListOfPrograms);
                registeredInboxList.add(counselorDetailsBEAN);
                i++;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            logger.error(ex.toString());
        }
        return registeredInboxList;
    }

    /**
     *
     * @param resultSet
     * @return
     */
    public static Map<String, List<CounselorDetailsBEAN>> makeRegisteredInboxMap(ResultSet resultSet) {
        System.out.println("Inside make reg inbox map");
        Map<String, List<CounselorDetailsBEAN>> registeredInboxMap = new HashMap<>();

        List<CounselorDetailsBEAN> registeredInboxList = new ArrayList<>();
        List<CounselorDetailsBEAN> registeredInboxStudyList = new ArrayList<>();
        List<CounselorDetailsBEAN> registeredInboxWorkList = new ArrayList<>();
        List<CounselorDetailsBEAN> registeredInboxMigrationList = new ArrayList<>();
        List<CounselorDetailsBEAN> registeredInboxTrainingList = new ArrayList<>();
        try {
            int i = 1;
            while (resultSet.next()) {
                String appoinment = "";
                CounselorDetailsBEAN counselorDetailsBEAN = new CounselorDetailsBEAN();
                counselorDetailsBEAN.setEnquiryID(resultSet.getString(1));
                counselorDetailsBEAN.setContactName(resultSet.getString("contact_name"));
                counselorDetailsBEAN.setContactNumber1(resultSet.getString("contact_number"));
                counselorDetailsBEAN.setContactEmail(resultSet.getString("contact_email"));
                counselorDetailsBEAN.setBracnch(resultSet.getString("branch"));
                counselorDetailsBEAN.setCountry(resultSet.getString("country"));
                counselorDetailsBEAN.setState(resultSet.getString("state"));
                counselorDetailsBEAN.setDistrict(resultSet.getString("district"));
                counselorDetailsBEAN.setEnquirySource(resultSet.getString("enquiry_source"));
                counselorDetailsBEAN.setEnquiryAssignedTo(resultSet.getString("enquiry_assigning"));
                counselorDetailsBEAN.setOtherEnquiry(resultSet.getString("other_enquiry"));
                counselorDetailsBEAN.setStudy(resultSet.getString("study"));
                counselorDetailsBEAN.setWork(resultSet.getString("work"));
                counselorDetailsBEAN.setMigration(resultSet.getString("migration"));
                counselorDetailsBEAN.setTraining(resultSet.getString("training"));
                counselorDetailsBEAN.setStudyDetails(resultSet.getString("study_details"));
                counselorDetailsBEAN.setWorkDetails(resultSet.getString("work_details"));
                counselorDetailsBEAN.setMigrationDetails(resultSet.getString("migration_details"));
                counselorDetailsBEAN.setTrainingDetails(resultSet.getString("training_details"));
                counselorDetailsBEAN.setOtherStudyDetails(resultSet.getString("study_remarks"));
                counselorDetailsBEAN.setOtherWorkDetails(resultSet.getString("work_experience"));
                counselorDetailsBEAN.setOtherMigrationDetails(resultSet.getString("migration_experience"));
                counselorDetailsBEAN.setOtherTrainingDetails(resultSet.getString("proposed_training_date1"));
                counselorDetailsBEAN.setRating(resultSet.getString("rating"));
                counselorDetailsBEAN.setEmailStatus(resultSet.getString("status"));
                counselorDetailsBEAN.setEdate(resultSet.getString("edate"));
                counselorDetailsBEAN.setStdcode1(resultSet.getString("std_code"));
                counselorDetailsBEAN.setReadflag(resultSet.getString("read_flag"));
                counselorDetailsBEAN.setStaffUsername(resultSet.getString("staff_usrname"));
                counselorDetailsBEAN.setStaffBranch(resultSet.getString("staff_branch"));
                counselorDetailsBEAN.setLastUpdate(resultSet.getString("last_update"));
                counselorDetailsBEAN.setProposedDate(resultSet.getString("proposed_training_date"));
                counselorDetailsBEAN.setImportant(resultSet.getString("important_flag"));
                counselorDetailsBEAN.setProposedTime(resultSet.getString("proposed_training_time"));
                counselorDetailsBEAN.setStatus(resultSet.getString("cur_status"));
                counselorDetailsBEAN.setRemarks(resultSet.getString("remarks"));
                counselorDetailsBEAN.setDistrict(resultSet.getString("location"));
                counselorDetailsBEAN.setDob(resultSet.getString("dob"));
                if (resultSet.getString("time") != null) {
                    try {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS");
                        System.out.println("DATE>>>M<<<<<::>" + resultSet.getString("time"));
                        Date date = dateFormat.parse(resultSet.getString("time"));
                        String strDateFormat = "hh:mm:ss a";
                        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
                        String time = sdf.format(date);
                        appoinment = "Direct Meeting on " + resultSet.getString("date") + " @" + time;
                    } catch (SQLException | ParseException e) {
                        e.printStackTrace();
                    }
                }
                counselorDetailsBEAN.setSno(i + "");
                String ListOfPrograms = "";
                //for primary inbox
                String programs = "";
                String programRequired = "";
                //end primary inbox
                if (!counselorDetailsBEAN.getStudy().equals("")) {
                    ListOfPrograms = "Study : " + counselorDetailsBEAN.getStudy() + "\n";
                    ListOfPrograms += "Course Level,Field,Remarks : " + counselorDetailsBEAN.getStudyDetails() + "," + counselorDetailsBEAN.getOtherStudyDetails() + "\n";

                    programs = "Study :" + counselorDetailsBEAN.getStudy();
                    programRequired = counselorDetailsBEAN.getStudyDetails() + "," + counselorDetailsBEAN.getOtherStudyDetails() + "\n";
                    registeredInboxStudyList.add(counselorDetailsBEAN);

                }
                if (!counselorDetailsBEAN.getWork().equals("")) {
                    ListOfPrograms = "Work : " + counselorDetailsBEAN.getWork() + "\n";
                    ListOfPrograms += "Designation,Education,Experience : " + counselorDetailsBEAN.getWorkDetails() + "," + counselorDetailsBEAN.getOtherWorkDetails() + "\n";

                    programs += "Work :" + counselorDetailsBEAN.getWork();
                    programRequired += counselorDetailsBEAN.getWorkDetails() + "," + counselorDetailsBEAN.getOtherWorkDetails() + "\n";
                    registeredInboxWorkList.add(counselorDetailsBEAN);

                }
                if (!counselorDetailsBEAN.getMigration().equals("")) {
                    ListOfPrograms = "Migration : " + counselorDetailsBEAN.getMigration() + "\n";
                    ListOfPrograms += "Designation,Education,Experience : " + counselorDetailsBEAN.getMigrationDetails() + "," + counselorDetailsBEAN.getOtherMigrationDetails() + "\n";

                    programs += "Migration :" + counselorDetailsBEAN.getMigration();
                    programRequired += counselorDetailsBEAN.getMigrationDetails() + "," + counselorDetailsBEAN.getOtherMigrationDetails() + "\n";
                    registeredInboxMigrationList.add(counselorDetailsBEAN);

                }
                if (!counselorDetailsBEAN.getTraining().equals("")) {
                    ListOfPrograms = "Training : " + counselorDetailsBEAN.getTraining() + "\n";
                    ListOfPrograms += "Batch,Timing,Joining Date : " + counselorDetailsBEAN.getTrainingDetails() + "," + counselorDetailsBEAN.getOtherTrainingDetails() + "\n";

                    programs += "Training :" + counselorDetailsBEAN.getTraining();
                    programRequired += counselorDetailsBEAN.getTrainingDetails() + "," + counselorDetailsBEAN.getOtherTrainingDetails() + "\n";
                    registeredInboxTrainingList.add(counselorDetailsBEAN);

                }
                //   counselorDetailsBEAN.setFollowUpDateTime("" + counselorDetailsBEAN.getProposedDate() + counselorDetailsBEAN.getProposedTime());
                counselorDetailsBEAN.setProgram(programs);
                counselorDetailsBEAN.setProgramRequired(programRequired);
                counselorDetailsBEAN.setListOfPrograms(ListOfPrograms);
                registeredInboxList.add(counselorDetailsBEAN);
                i++;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            logger.error(ex.toString());
        }
        registeredInboxMap.put("study", registeredInboxStudyList);
        registeredInboxMap.put("work", registeredInboxWorkList);
        registeredInboxMap.put("migration", registeredInboxMigrationList);
        registeredInboxMap.put("training", registeredInboxTrainingList);
        registeredInboxMap.put("all", registeredInboxList);
        return registeredInboxMap;
    }

    /* ==============end  making registered inbox list ==========================*/
}
