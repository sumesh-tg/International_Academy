/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.assesment.dao;

import com.zs.ina.assesment.model.AssesmentMigrateBEAN;
import com.zs.ina.assesment.model.AssesmentTrainingBEAN;
import com.zs.ina.assesment.model.AssesmentWorkBEAN;
import com.zs.ina.assesment.model.StudySuggestedRequiredBEAN;
import com.zs.ina.common.GlobalClassDAO;
import com.zs.ina.common.UiiDGenerator;
import com.zs.ina.common.constants.TableNames;
import com.zs.ina.context.Context;
import com.zs.ina.utility.ClosingResourcesInDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import zs.com.ina.db.mysql.pool.DBPool;

/**
 *
 * @author zsuser1
 */
public class ProgramSuggestedRequiredDAO {

    static Logger logger = Logger.getLogger(ProgramSuggestedRequiredDAO.class);

    /**
     *
     * @param enquiryId
     * @return
     */
    public static List<StudySuggestedRequiredBEAN> retrieveStudyDetails(String enquiryId) {
        List<StudySuggestedRequiredBEAN> studySuggestedRequiredList = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        boolean f = false;
        con = DBPool.getInstance().getConn();

        String query = "Select * from " + TableNames.TABLE_ENQ_ASMNT_STUDY + " where enquiry_id='" + enquiryId + "' order by choice asc";
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                StudySuggestedRequiredBEAN suggestedRequiredBEAN = new StudySuggestedRequiredBEAN();
                suggestedRequiredBEAN.setRowId(rs.getString("study_pgm_id"));
                suggestedRequiredBEAN.setAmount(rs.getString("amount"));
                suggestedRequiredBEAN.setChoice(rs.getString("choice"));
                suggestedRequiredBEAN.setCountry(rs.getString("country"));
                suggestedRequiredBEAN.setCurrency(rs.getString("currency"));
                suggestedRequiredBEAN.setEnquiry_id(rs.getString("enquiry_id"));
                suggestedRequiredBEAN.setInstitution(rs.getString("institution"));
                suggestedRequiredBEAN.setIntake(rs.getString("intake"));
                suggestedRequiredBEAN.setLocation(rs.getString("location"));
                suggestedRequiredBEAN.setOtherUniversity(rs.getString("otherUniversity"));
                suggestedRequiredBEAN.setProgram_field(rs.getString("program_field"));
                suggestedRequiredBEAN.setProgram_level(rs.getString("program_level"));
                suggestedRequiredBEAN.setRemarks(rs.getString("remarks"));
                suggestedRequiredBEAN.setSem_fees(rs.getString("sem_fees"));
                suggestedRequiredBEAN.setStudy(rs.getString("Study"));
                suggestedRequiredBEAN.setStudy_pgm_id(rs.getString("study_pgm_id"));
                suggestedRequiredBEAN.setSpouse_accompany(rs.getString("spouse_accompany"));
                suggestedRequiredBEAN.setChildren_accompany(rs.getString("children_accompany"));
                studySuggestedRequiredList.add(suggestedRequiredBEAN);

            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());

        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return studySuggestedRequiredList;

    }

    /**
     *
     * @param suggestedRequiredBEAN
     * @return
     */
    public static int insertStudyDetails(StudySuggestedRequiredBEAN suggestedRequiredBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        String GENERATE_ENQ_ID = "st_";
        GENERATE_ENQ_ID = GENERATE_ENQ_ID + UiiDGenerator.getUIID8();
        try {
            String query = "INSERT INTO " + TableNames.TABLE_ENQ_ASMNT_STUDY + " (\n"
                    + "	study_pgm_id,\n"
                    + "	country,\n"
                    + "	program_level,\n"
                    + "	program_field,\n"
                    + "	intake,\n"
                    + "	institution,\n"
                    + "	enquiry_id,\n"
                    + "	study,\n"
                    + "	otherUniversity,\n"
                    + "	location,\n"
                    + "	currency,\n"
                    + "	amount,\n"
                    + "	sem_fees,\n"
                    + "	choice,\n"
                    + "	remarks,\n"
                    + "	children_accompany,\n"
                    + "	spouse_accompany,\n"
                    + "	created_user,\n"
                    + "	created_date,\n"
                    + "	updated_date,\n"
                    + "	updated_user,\n"
                    + "	latest_flag\n"
                    + ")\n"
                    + "VALUES\n"
                    + "	(\n"
                    + "		?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(),\n"
                    + "		NULL,\n"
                    + "		NULL,\n"
                    + "		'y'\n"
                    + "	);\n"
                    + "";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, GENERATE_ENQ_ID);
            suggestedRequiredBEAN.setRowId(GENERATE_ENQ_ID);
            ps.setString(2, suggestedRequiredBEAN.getCountry());
            ps.setString(3, suggestedRequiredBEAN.getProgram_level());
            ps.setString(4, suggestedRequiredBEAN.getProgram_field());
            ps.setString(5, suggestedRequiredBEAN.getIntake());
            ps.setString(6, suggestedRequiredBEAN.getInstitution());
            ps.setString(7, suggestedRequiredBEAN.getEnquiry_id());
            ps.setString(8, suggestedRequiredBEAN.getStudy());
            ps.setString(9, suggestedRequiredBEAN.getOtherUniversity());
            ps.setString(10, suggestedRequiredBEAN.getLocation());
            ps.setString(11, suggestedRequiredBEAN.getCurrency());
            ps.setString(12, suggestedRequiredBEAN.getAmount());
            ps.setString(13, suggestedRequiredBEAN.getSem_fees());
            ps.setString(14, suggestedRequiredBEAN.getChoice());

            ps.setString(15, suggestedRequiredBEAN.getRemarks());
            ps.setString(16, suggestedRequiredBEAN.getChildren_accompany());
            ps.setString(17, suggestedRequiredBEAN.getSpouse_accompany());
            ps.setString(18, Context.getInstance().currentProfile().getProfilePOJO().getUsername());

            row = ps.executeUpdate();
            if (row > 0) {
                GlobalClassDAO.updateLatestFlags(suggestedRequiredBEAN.getEnquiry_id(), TableNames.TABLE_ENQ_ASMNT_STUDY, "n", " study_pgm_id NOT IN('" + suggestedRequiredBEAN.getRowId() + "')");
            }

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
     * @param suggestedRequiredBEAN
     * @return
     */
    public static int updateStudyDetails(StudySuggestedRequiredBEAN suggestedRequiredBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        try {
            String query = "UPDATE " + TableNames.TABLE_ENQ_ASMNT_STUDY + "\n"
                    + "SET country = ?,\n"
                    + " program_level = ?,\n"
                    + " program_field = ?,\n"
                    + " intake = ?,\n"
                    + " institution = ?,\n"
                    + " study = ?,\n"
                    + " otherUniversity = ?,\n"
                    + " location = ?,\n"
                    + " currency = ?,\n"
                    + " amount = ?,\n"
                    + " sem_fees = ?,\n"
                    + " choice = ?,\n"
                    + " remarks = ?,\n"
                    + " children_accompany = ?,\n"
                    + " spouse_accompany = ?,\n"
                    + " updated_date = NOW(),\n"
                    + " updated_user = ?\n"
                    + "WHERE\n"
                    + "	study_pgm_id = ? ";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, suggestedRequiredBEAN.getCountry());
            ps.setString(2, suggestedRequiredBEAN.getProgram_level());
            ps.setString(3, suggestedRequiredBEAN.getProgram_field());
            ps.setString(4, suggestedRequiredBEAN.getIntake());
            ps.setString(5, suggestedRequiredBEAN.getInstitution());
            ps.setString(6, suggestedRequiredBEAN.getStudy());
            ps.setString(7, suggestedRequiredBEAN.getOtherUniversity());
            ps.setString(8, suggestedRequiredBEAN.getLocation());
            ps.setString(9, suggestedRequiredBEAN.getCurrency());
            ps.setString(10, suggestedRequiredBEAN.getAmount());
            ps.setString(11, suggestedRequiredBEAN.getSem_fees());
            ps.setString(12, suggestedRequiredBEAN.getChoice());

            ps.setString(13, suggestedRequiredBEAN.getRemarks());
            ps.setString(14, suggestedRequiredBEAN.getChildren_accompany());
            ps.setString(15, suggestedRequiredBEAN.getSpouse_accompany());

            ps.setString(16, Context.getInstance().currentProfile().getProfilePOJO().getUsername());
            ps.setString(17, suggestedRequiredBEAN.getStudy_pgm_id());

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
     * @param rowId
     * @return
     */
    public static int deleteStudyDetails(String rowId) {
        String _isLatestFlag = GlobalClassDAO.checkCurrentEnquiryIdHaveLatestFlag(rowId, TableNames.TABLE_ENQ_ASMNT_STUDY, "study_pgm_id");
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        try {
            String query = "DELETE FROM  " + TableNames.TABLE_ENQ_ASMNT_STUDY + " WHERE  study_pgm_id=? ";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, rowId);
            row = ps.executeUpdate();
            if (row == 1) {
                if (_isLatestFlag != null) {
                    GlobalClassDAO.updateLatestflagOnDelete(_isLatestFlag, TableNames.TABLE_ENQ_ASMNT_STUDY);
                }
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());

        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return row;
    }

    //<-----------------------------Method to  Migration Details---------------------->
    /**
     *
     * @param enquiryId
     * @return
     */
    public static List<AssesmentMigrateBEAN> getMigrationDetails(String enquiryId) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<AssesmentMigrateBEAN> assesmentMigrateBEANs = new ArrayList<>();
        String query = "Select *from " + TableNames.TABLE_ENQ_ASMNT_MIGRATION + " where enquiry_id='" + enquiryId + "'";
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                AssesmentMigrateBEAN assesmentMigrateBEAN = new AssesmentMigrateBEAN();
                assesmentMigrateBEAN.setEnquiryId(rs.getString("enquiry_id"));
                assesmentMigrateBEAN.setCountry(rs.getString("country"));
                assesmentMigrateBEAN.setRowId(rs.getString("migrate_id"));
                assesmentMigrateBEAN.setMigrate(rs.getString("migrate"));
                assesmentMigrateBEAN.setCategory(rs.getString("category"));
                assesmentMigrateBEAN.setProfession(rs.getString("profession"));
                assesmentMigrateBEAN.setLocation(rs.getString("location"));
                assesmentMigrateBEAN.setChoice(rs.getString("choice"));
                assesmentMigrateBEAN.setSpouseAccompany(rs.getString("spouse_accompany"));
                assesmentMigrateBEAN.setChildrenAccompany(rs.getString("children_accompany"));
                assesmentMigrateBEAN.setComment(rs.getString("special_comment"));
                assesmentMigrateBEAN.setApplicationType(rs.getString("application_type"));
                assesmentMigrateBEANs.add(assesmentMigrateBEAN);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());

        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return assesmentMigrateBEANs;
    }

    /**
     *
     * @param rowID
     * @return
     */
    public static int deleteWorkDetailsByid(String rowID) {
        String _isLatestFlag = GlobalClassDAO.checkCurrentEnquiryIdHaveLatestFlag(rowID, TableNames.TABLE_ENQ_ASMNT_WORK, "work_id");
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        try {
            String query = "Delete from " + TableNames.TABLE_ENQ_ASMNT_WORK + " where work_id='" + rowID + "'";
            stmt = con.createStatement();
            row = stmt.executeUpdate(query);
            if (row == 1) {
                if (_isLatestFlag != null) {
                    GlobalClassDAO.updateLatestflagOnDelete(_isLatestFlag, TableNames.TABLE_ENQ_ASMNT_WORK);
                }
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());

        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return row;
    }

    //<-------------Method for insert Migrate  details------>
    /**
     *
     * @param assesmentMigrateBEAN
     */
    public static void insertMigrateDetails(AssesmentMigrateBEAN assesmentMigrateBEAN) {
        String GENERATE_ID = Context.getInstance().currentProfile().getProfilePOJO().getBranch().substring(0, 3);
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        GENERATE_ID = GENERATE_ID + UiiDGenerator.getUIID8();
        assesmentMigrateBEAN.setRowId(GENERATE_ID);
        try {
            String sql = "INSERT INTO " + TableNames.TABLE_ENQ_ASMNT_MIGRATION + " (\n"
                    + "	migrate_id,\n"
                    + "	enquiry_id,\n"
                    + "	migrate,\n"
                    + "	category,\n"
                    + "	profession,\n"
                    + "	choice,\n"
                    + "	spouse_accompany,\n"
                    + "	children_accompany,\n"
                    + "	special_comment,\n"
                    + "	country,\n"
                    + "	application_type,\n"
                    + "	location,\n"
                    + "	created_user,\n"
                    + "	created_date,\n"
                    + "	updated_date,\n"
                    + "	updated_user,\n"
                    + "	latest_flag\n"
                    + ")\n"
                    + "VALUES\n"
                    + "	(\n"
                    + "		?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,\n"
                    + "		?, NOW(),\n"
                    + "		NULL,\n"
                    + "		NULL,\n"
                    + "		'y'\n"
                    + "	);";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, GENERATE_ID);
            ps.setString(2, assesmentMigrateBEAN.getEnquiryId());
            ps.setString(3, assesmentMigrateBEAN.getMigrate());
            ps.setString(4, assesmentMigrateBEAN.getCategory());
            ps.setString(5, assesmentMigrateBEAN.getProfession());
            ps.setString(6, assesmentMigrateBEAN.getChoice());
            ps.setString(7, assesmentMigrateBEAN.getSpouseAccompany());
            ps.setString(8, assesmentMigrateBEAN.getChildrenAccompany());
            ps.setString(9, assesmentMigrateBEAN.getComment());
            ps.setString(10, assesmentMigrateBEAN.getCountry());
            ps.setString(11, assesmentMigrateBEAN.getApplicationType());
            ps.setString(12, assesmentMigrateBEAN.getLocation());
            ps.setString(13, Context.getInstance().currentProfile().getProfilePOJO().getUsername());
            row = ps.executeUpdate();
            if (row > 0) {
                assesmentMigrateBEAN.setRowId(GENERATE_ID);
                GlobalClassDAO.updateLatestFlags(assesmentMigrateBEAN.getEnquiryId(), TableNames.TABLE_ENQ_ASMNT_MIGRATION, "n", " migrate_id NOT IN('" + GENERATE_ID + "')");
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());

        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
    }

    //<-----------------------------Method to get work Details---------------------->
    /**
     *
     * @param enquiryId
     * @return
     */
    public static List<AssesmentTrainingBEAN> getTrainingDetails(String enquiryId) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<AssesmentTrainingBEAN> assesmentTrainingBEANs = new ArrayList<>();
        String query = "Select *from " + TableNames.TABLE_ENQ_ASMNT_TRAINING + " where enquiry_id='" + enquiryId + "'order by choice";
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                AssesmentTrainingBEAN assesmentTrainingBEAN = new AssesmentTrainingBEAN();
                assesmentTrainingBEAN.setEnquiryId(rs.getString("enquiry_id"));
                assesmentTrainingBEAN.setRowId(rs.getString("training_id"));
                assesmentTrainingBEAN.setTraining(rs.getString("training"));
                assesmentTrainingBEAN.setBatch(rs.getString("batch"));
                assesmentTrainingBEAN.setTiming(rs.getString("timing"));
                assesmentTrainingBEAN.setChoice(rs.getString("choice"));
                assesmentTrainingBEAN.setDuration(rs.getString("duration"));
                if (rs.getString("from_date") != null || !rs.getString("from_date").equalsIgnoreCase("")) {
                    assesmentTrainingBEAN.setFromDate(LocalDate.parse(rs.getString("from_date")));
                }
                if (rs.getString("class_from_date") != null) {
                    if (!rs.getString("class_from_date").equalsIgnoreCase("")) {
                        assesmentTrainingBEAN.setClassFromDate(LocalDate.parse(rs.getString("class_from_date")));
                    }
                }
                if (rs.getString("class_to_date") != null) {
                    if (!rs.getString("class_to_date").equalsIgnoreCase("")) {
                        assesmentTrainingBEAN.setClassToDate(LocalDate.parse(rs.getString("class_to_date")));
                    }
                }
                assesmentTrainingBEAN.setRemmarks(rs.getString("remarks"));
                assesmentTrainingBEANs.add(assesmentTrainingBEAN);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());

        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return assesmentTrainingBEANs;
    }
    //Method to insert assesment status details

    //Method for delete training details
    /**
     *
     * @param rowId
     */
    public static void deleteTrainingDetails(String rowId) {
        String _isLatestFlag = GlobalClassDAO.checkCurrentEnquiryIdHaveLatestFlag(rowId, TableNames.TABLE_ENQ_ASMNT_TRAINING, "training_id");
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        try {
            String query = "Delete from " + TableNames.TABLE_ENQ_ASMNT_TRAINING + " where training_id='" + rowId + "'";
            stmt = con.createStatement();
            int row = stmt.executeUpdate(query);
            if (row == 1) {
                if (_isLatestFlag != null) {
                    GlobalClassDAO.updateLatestflagOnDelete(_isLatestFlag, TableNames.TABLE_ENQ_ASMNT_TRAINING);
                }
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());

        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
    }

    //<-------------Method for insert Migrate  details------>
    /**
     *
     * @param assesmentMigrateBEAN
     */
    public static void updateMigrateDetails(AssesmentMigrateBEAN assesmentMigrateBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        try {
            String sql = "UPDATE " + TableNames.TABLE_ENQ_ASMNT_MIGRATION + "\n"
                    + "SET migrate = ?,\n"
                    + " category = ?,\n"
                    + " profession = ?,\n"
                    + " choice = ?,\n"
                    + " spouse_accompany = ?,\n"
                    + " children_accompany = ?,\n"
                    + " special_comment = ?,\n"
                    + " country = ?,\n"
                    + " application_type = ?,\n"
                    + " location = ?,\n"
                    + " updated_date = NOW(),\n"
                    + " updated_user = ?\n"
                    + "WHERE\n"
                    + "	migrate_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, assesmentMigrateBEAN.getMigrate());
            ps.setString(2, assesmentMigrateBEAN.getCategory());
            ps.setString(3, assesmentMigrateBEAN.getProfession());
            ps.setString(4, assesmentMigrateBEAN.getChoice());
            ps.setString(5, assesmentMigrateBEAN.getSpouseAccompany());
            ps.setString(6, assesmentMigrateBEAN.getChildrenAccompany());
            ps.setString(7, assesmentMigrateBEAN.getComment());
            ps.setString(8, assesmentMigrateBEAN.getCountry());
            ps.setString(9, assesmentMigrateBEAN.getApplicationType());
            ps.setString(10, assesmentMigrateBEAN.getLocation());
            ps.setString(11, Context.getInstance().currentProfile().getProfilePOJO().getUsername());
            ps.setString(12, assesmentMigrateBEAN.getRowId());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());

        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
    }

    //<-------------Method for insert training  details------>
    /**
     *
     * @param assesmentTrainingBEAN
     */
    public static void insertTrainingDetails(AssesmentTrainingBEAN assesmentTrainingBEAN) {
        String GENERATE_ID = Context.getInstance().currentProfile().getProfilePOJO().getBranch().substring(0, 3);
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        GENERATE_ID = GENERATE_ID + UiiDGenerator.getUIID8();
        assesmentTrainingBEAN.setRowId(GENERATE_ID);
        try {
            String sql = "INSERT INTO " + TableNames.TABLE_ENQ_ASMNT_TRAINING + " (\n"
                    + "	training_id,\n"
                    + "	enquiry_id,\n"
                    + "	training,\n"
                    + "	batch,\n"
                    + "	timing,\n"
                    + "	duration,\n"
                    + "	from_date,\n"
                    + " class_from_date,\n"
                    + "	class_to_date,\n"
                    + "	remarks,\n"
                    + "	choice,\n"
                    + "	created_user,\n"
                    + "	created_date,\n"
                    + "	updated_date,\n"
                    + "	updated_user,\n"
                    + "	latest_flag\n"
                    + ")\n"
                    + "VALUES\n"
                    + "	(\n"
                    + "		?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(),\n"
                    + "		NULL,\n"
                    + "		NULL,\n"
                    + "		'y'\n"
                    + "	);\n"
                    + "";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, GENERATE_ID);
            ps.setString(2, assesmentTrainingBEAN.getEnquiryId());
            ps.setString(3, assesmentTrainingBEAN.getTraining());
            ps.setString(4, assesmentTrainingBEAN.getBatch());
            ps.setString(5, assesmentTrainingBEAN.getTiming());
            ps.setString(6, assesmentTrainingBEAN.getDuration());
            ps.setString(7, assesmentTrainingBEAN.getFromDate().toString());
            if (assesmentTrainingBEAN.getClassFromDate() != null) {
                ps.setString(8, assesmentTrainingBEAN.getClassFromDate().toString());
            } else {
                ps.setNull(8, java.sql.Types.DATE);
            }
            if (assesmentTrainingBEAN.getClassToDate() != null) {
                ps.setString(9, assesmentTrainingBEAN.getClassToDate().toString());
            } else {
                ps.setNull(9, java.sql.Types.DATE);
            }

            ps.setString(10, assesmentTrainingBEAN.getRemmarks());
            ps.setString(11, assesmentTrainingBEAN.getChoice());
            ps.setString(12, Context.getInstance().currentProfile().getProfilePOJO().getUsername());
            row = ps.executeUpdate();
            if (row > 0) {
                assesmentTrainingBEAN.setRowId(GENERATE_ID);
                GlobalClassDAO.updateLatestFlags(assesmentTrainingBEAN.getEnquiryId(),
                        TableNames.TABLE_ENQ_ASMNT_TRAINING, "n", " training_id NOT IN('"
                        + assesmentTrainingBEAN.getRowId() + "')");

            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());

        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
    }

    //<-----------------------------Method to get work Details---------------------->
    /**
     *
     * @param enquiryId
     * @return
     */
    public static List<AssesmentWorkBEAN> getWorkDetails(String enquiryId) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<AssesmentWorkBEAN> assesmentWorkBEANs = new ArrayList<>();
        String query = "Select * from " + TableNames.TABLE_ENQ_ASMNT_WORK + " where enquiry_id='" + enquiryId + "'";
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                AssesmentWorkBEAN assesmentWorkBEAN = new AssesmentWorkBEAN();
                assesmentWorkBEAN.setEnquiryId(rs.getString("enquiry_id"));
                assesmentWorkBEAN.setCountry(rs.getString("country"));
                assesmentWorkBEAN.setRowId(rs.getString("work_id"));
                assesmentWorkBEAN.setWork(rs.getString("work"));
                assesmentWorkBEAN.setLocation(rs.getString("location"));
                assesmentWorkBEAN.setProfession(rs.getString("profession"));
                assesmentWorkBEAN.setChoice(rs.getString("choice"));
                assesmentWorkBEAN.setSpouce_accompany(rs.getString("spouce_accompany"));
                assesmentWorkBEAN.setChildren_accompany(rs.getString("children_accompany"));
                assesmentWorkBEAN.setSpecial_comment(rs.getString("special_comment"));
                assesmentWorkBEAN.setMin_salary(rs.getString("min_salary"));
                assesmentWorkBEAN.setMax_salary(rs.getString("max_salary"));
                assesmentWorkBEAN.setCurreny(rs.getString("currency"));
                assesmentWorkBEAN.setIndustry(rs.getString("industry"));
                assesmentWorkBEAN.setEmployer_choice(rs.getString("employer_choice"));
                assesmentWorkBEAN.setSkillArea(rs.getString("skill_area"));
                assesmentWorkBEANs.add(assesmentWorkBEAN);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());

        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return assesmentWorkBEANs;
    }

    //<-------------Method for insert training  details------>
    /**
     *
     * @param assesmentTrainingBEAN
     */
    public static void updateTrainingDetails(AssesmentTrainingBEAN assesmentTrainingBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        try {
            String sql = "UPDATE " + TableNames.TABLE_ENQ_ASMNT_TRAINING + "\n"
                    + "SET training = ?,\n"
                    + " batch = ?,\n"
                    + " timing = ?,\n"
                    + " duration = ?,\n"
                    + " from_date = ?,\n"
                    + " class_from_date = ?,\n"
                    + " class_to_date = ?,\n"
                    + " remarks = ?,\n"
                    + " choice = ?,\n"
                    + " updated_date = NOW(),\n"
                    + " updated_user = ?\n"
                    + " WHERE \n"
                    + "	training_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, assesmentTrainingBEAN.getTraining());
            ps.setString(2, assesmentTrainingBEAN.getBatch());
            ps.setString(3, assesmentTrainingBEAN.getTiming());
            ps.setString(4, assesmentTrainingBEAN.getDuration());
            ps.setString(5, assesmentTrainingBEAN.getFromDate().toString());
            System.out.println("UPDATE getFromDate :::" + assesmentTrainingBEAN.getFromDate().toString());

            if (assesmentTrainingBEAN.getClassFromDate() != null) {
                ps.setString(6, assesmentTrainingBEAN.getClassFromDate().toString());
            } else {
                ps.setNull(6, java.sql.Types.DATE);
            }
            if (assesmentTrainingBEAN.getClassToDate() != null) {
                ps.setString(7, assesmentTrainingBEAN.getClassToDate().toString());
            } else {
                ps.setNull(7, java.sql.Types.DATE);
            }
            ps.setString(8, assesmentTrainingBEAN.getRemmarks());
            ps.setString(9, assesmentTrainingBEAN.getChoice());
            ps.setString(10, Context.getInstance().currentProfile().getProfilePOJO().getUsername());
            ps.setString(11, assesmentTrainingBEAN.getRowId());
            System.out.println("getRowId :::::::::" + assesmentTrainingBEAN.getRowId());
            row = ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());

        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
    }

    //method for insert work experience single bean
    /**
     *
     * @param enquiryId
     * @param assesmentWorkBEAN
     */
    public static void insertWorkDetails(String enquiryId, AssesmentWorkBEAN assesmentWorkBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        String GENERATE_ENQ_ID = "wk_";
        con = DBPool.getInstance().getConn();
        try {
            GENERATE_ENQ_ID = GENERATE_ENQ_ID + UiiDGenerator.getUIID8();
            String sql = "INSERT INTO " + TableNames.TABLE_ENQ_ASMNT_WORK + " (\n"
                    + "	work_id,\n"
                    + "	enquiry_id,\n"
                    + "	WORK,\n"
                    + "	profession,\n"
                    + "	location,\n"
                    + "	choice,\n"
                    + "	spouce_accompany,\n"
                    + "	children_accompany,\n"
                    + "	special_comment,\n"
                    + "	min_salary,\n"
                    + "	max_salary,\n"
                    + "	currency,\n"
                    + "	country,\n"
                    + "	industry,\n"
                    + "	employer_choice,\n"
                    + "	created_user,\n"
                    + "	created_date,\n"
                    + "	updated_date,\n"
                    + "	updated_user,\n"
                    + "	latest_flag\n"
                    + ")\n"
                    + "VALUES\n"
                    + "	(\n"
                    + "		?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, NOW(),\n"
                    + "		NULL,\n"
                    + "		NULL,\n"
                    + "		'y'\n"
                    + "	);\n"
                    + "";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, GENERATE_ENQ_ID);
            ps.setString(2, assesmentWorkBEAN.getEnquiryId());
            ps.setString(3, assesmentWorkBEAN.getWork());
            ps.setString(4, assesmentWorkBEAN.getProfession());
            ps.setString(5, assesmentWorkBEAN.getLocation());
            ps.setString(6, assesmentWorkBEAN.getChoice());
            ps.setString(7, assesmentWorkBEAN.getSpouce_accompany());
            ps.setString(8, assesmentWorkBEAN.getChildren_accompany());
            ps.setString(9, assesmentWorkBEAN.getSpecial_comment());
            ps.setString(10, assesmentWorkBEAN.getMin_salary());
            ps.setString(11, assesmentWorkBEAN.getMax_salary());
            ps.setString(12, assesmentWorkBEAN.getCurreny());
            ps.setString(13, assesmentWorkBEAN.getCountry());
            ps.setString(14, assesmentWorkBEAN.getIndustry());
            ps.setString(15, assesmentWorkBEAN.getEmployer_choice());
            ps.setString(16, Context.getInstance().currentProfile().getProfilePOJO().getUsername());
            assesmentWorkBEAN.setRowId(GENERATE_ENQ_ID);
            row = ps.executeUpdate();
            if (row > 0) {
                GlobalClassDAO.updateLatestFlags(assesmentWorkBEAN.getEnquiryId(),
                        TableNames.TABLE_ENQ_ASMNT_WORK, "n",
                        " work_id NOT IN('" + assesmentWorkBEAN.getRowId() + "')");

            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());

        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
    }

    //<-------------Method for insert Migrate  details------>
    /**
     *
     * @param assesmentWorkBEAN
     */
    public static void insertWorkDetails(AssesmentWorkBEAN assesmentWorkBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        String GENERATE_ENQ_ID = "wk_";
        con = DBPool.getInstance().getConn();
        try {
            GENERATE_ENQ_ID = GENERATE_ENQ_ID + UiiDGenerator.getUIID8();
            String sql = "INSERT INTO " + TableNames.TABLE_ENQ_ASMNT_WORK + " (\n"
                    + "	work_id,\n"
                    + "	enquiry_id,\n"
                    + "	WORK,\n"
                    + "	profession,\n"
                    + "	location,\n"
                    + "	choice,\n"
                    + "	spouce_accompany,\n"
                    + "	children_accompany,\n"
                    + "	special_comment,\n"
                    + "	min_salary,\n"
                    + "	max_salary,\n"
                    + "	currency,\n"
                    + "	country,\n"
                    + "	industry,\n"
                    + "	employer_choice,\n"
                    + "	created_user,\n"
                    + "	created_date,\n"
                    + "	updated_date,\n"
                    + "	updated_user,\n"
                    + "	latest_flag,skill_area\n"
                    + ")\n"
                    + "VALUES\n"
                    + "	(\n"
                    + "		?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, NOW(),\n"
                    + "		NULL,\n"
                    + "		NULL,\n"
                    + "		'y',?\n"
                    + "	);\n"
                    + "";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, GENERATE_ENQ_ID);
            ps.setString(2, assesmentWorkBEAN.getEnquiryId());
            ps.setString(3, assesmentWorkBEAN.getWork());
            ps.setString(4, assesmentWorkBEAN.getProfession());
            ps.setString(5, assesmentWorkBEAN.getLocation());
            ps.setString(6, assesmentWorkBEAN.getChoice());
            ps.setString(7, assesmentWorkBEAN.getSpouce_accompany());
            ps.setString(8, assesmentWorkBEAN.getChildren_accompany());
            ps.setString(9, assesmentWorkBEAN.getSpecial_comment());
            ps.setString(10, assesmentWorkBEAN.getMin_salary());
            ps.setString(11, assesmentWorkBEAN.getMax_salary());
            ps.setString(12, assesmentWorkBEAN.getCurreny());
            ps.setString(13, assesmentWorkBEAN.getCountry());
            ps.setString(14, assesmentWorkBEAN.getIndustry());
            ps.setString(15, assesmentWorkBEAN.getEmployer_choice());
            ps.setString(16, Context.getInstance().currentProfile().getProfilePOJO().getUsername());
            ps.setString(17, assesmentWorkBEAN.getSkillArea());
            assesmentWorkBEAN.setRowId(GENERATE_ENQ_ID);
            row = ps.executeUpdate();
            if (row > 0) {
                GlobalClassDAO.updateLatestFlags(assesmentWorkBEAN.getEnquiryId(),
                        TableNames.TABLE_ENQ_ASMNT_WORK, "n",
                        " work_id NOT IN('" + assesmentWorkBEAN.getRowId() + "')");

            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());

        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }

    }

    /**
     *
     * @param rowId
     */
    public static void deleteMigrateDetails(String rowId) {
        String _isLatestFlag = GlobalClassDAO.checkCurrentEnquiryIdHaveLatestFlag(rowId, TableNames.TABLE_ENQ_ASMNT_MIGRATION, "migrate_id");
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        try {
            String query = "Delete from " + TableNames.TABLE_ENQ_ASMNT_MIGRATION + " where migrate_id='" + rowId + "'";
            stmt = con.createStatement();
            int row = stmt.executeUpdate(query);
            if (row == 1) {
                if (_isLatestFlag != null) {
                    GlobalClassDAO.updateLatestflagOnDelete(_isLatestFlag, TableNames.TABLE_ENQ_ASMNT_MIGRATION);
                }
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());

        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
    }

    //<-------------Method for update Migrate  details------>
    /**
     *
     * @param assesmentWorkBEAN
     */
    public static void updateWorkDetails(AssesmentWorkBEAN assesmentWorkBEAN) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        try {
            String sql = "UPDATE " + TableNames.TABLE_ENQ_ASMNT_WORK + "\n"
                    + "SET WORK = ?,\n"
                    + " profession = ?,\n"
                    + " location = ?,\n"
                    + " choice = ?,\n"
                    + " spouce_accompany = ?,\n"
                    + " children_accompany = ?,\n"
                    + " special_comment = ?,\n"
                    + " min_salary = ?,\n"
                    + " max_salary = ?,\n"
                    + " currency = ?,\n"
                    + " country = ?,\n"
                    + " industry = ?,\n"
                    + " skill_area = ?,\n"
                    + " employer_choice = ?,\n"
                    + " updated_date = NOW(),\n"
                    + " updated_user = ?\n"
                    + "WHERE\n"
                    + "	work_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, assesmentWorkBEAN.getWork());
            ps.setString(2, assesmentWorkBEAN.getProfession());
            ps.setString(3, assesmentWorkBEAN.getLocation());
            ps.setString(4, assesmentWorkBEAN.getChoice());
            ps.setString(5, assesmentWorkBEAN.getSpouce_accompany());
            ps.setString(6, assesmentWorkBEAN.getChildren_accompany());
            ps.setString(7, assesmentWorkBEAN.getSpecial_comment());
            ps.setString(8, assesmentWorkBEAN.getMin_salary());
            ps.setString(9, assesmentWorkBEAN.getMax_salary());
            ps.setString(10, assesmentWorkBEAN.getCurreny());
            ps.setString(11, assesmentWorkBEAN.getCountry());
            ps.setString(12, assesmentWorkBEAN.getIndustry());
            ps.setString(13, assesmentWorkBEAN.getSkillArea());
            ps.setString(14, assesmentWorkBEAN.getEmployer_choice());
            ps.setString(15, Context.getInstance().currentProfile().getProfilePOJO().getUsername());
            ps.setString(16, assesmentWorkBEAN.getRowId());
            row = ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.info(sqle.toString());

        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
    }
}
