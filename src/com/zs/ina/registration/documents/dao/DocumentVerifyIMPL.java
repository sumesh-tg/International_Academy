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
package com.zs.ina.registration.documents.dao;

import com.zs.ina.common.GlobalClassDAO;
import com.zs.ina.common.UiiDGenerator;
import com.zs.ina.common.constants.TableNames;
import com.zs.ina.common.email.MailSentPOJO;
import com.zs.ina.context.Context;
import com.zs.ina.utility.ClosingResourcesInDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import zs.com.ina.db.mysql.pool.DBPool;

/**
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class DocumentVerifyIMPL implements DocumentVerifyDAO {

    static Logger logger = Logger.getLogger(DocumentVerifyIMPL.class);
    @Autowired
    DataSource dataSource;
    private JdbcTemplate mySqlJdbcTemplate;

    /**
     *
     * @param documentVerfiyBEAN
     * @return
     */
    @Override
    public int insertDocumentVerfications(DocumentVerifyBEAN documentVerfiyBEAN) {
        int row = 0;
        mySqlJdbcTemplate = new JdbcTemplate(dataSource);
        String followDate = "";
        if (documentVerfiyBEAN.getFollowupDate() != null) {
            followDate = documentVerfiyBEAN.getFollowupDate().toString();
        }

        documentVerfiyBEAN.setDocVerifyId("doc_" + UiiDGenerator.getUIID8());
        String sql = "INSERT INTO " + TableNames.TABLE_REG_DOCUMENT_VERIFY + " (\n"
                + "	docs_verify_id,\n"
                + "	enquiry_id,\n"
                + "	document_id,\n"
                + "	STATUS,\n"
                + "	followup_date,\n"
                + "	remarks,\n"
                + "	created_user,\n"
                + "	created_date,\n"
                + "	updated_user,\n"
                + "	updated_date,\n"
                + "	latest_flag\n"
                + ")\n"
                + "VALUES\n"
                + "	(\n"
                + "		?,\n"
                + "		?,\n"
                + "		?,\n"
                + "		?,\n"
                + "		?,\n"
                + "		?,\n"
                + "		?,\n"
                + "		NOW(),\n"
                + "		NULL,\n"
                + "		NULL,\n"
                + "		'y'\n"
                + "	);";
        row = mySqlJdbcTemplate.update(sql, new Object[]{documentVerfiyBEAN.getDocVerifyId(),
            documentVerfiyBEAN.getEnquiryId(),
            documentVerfiyBEAN.getDocumentId(),
            documentVerfiyBEAN.getStatus(),
            followDate,
            documentVerfiyBEAN.getRemarks(),
            Context.getInstance().currentProfile().getProfilePOJO().getUsername()
        });

        if (row > 0) {
            GlobalClassDAO.updateLatestFlags(documentVerfiyBEAN.getEnquiryId(), TableNames.TABLE_REG_DOCUMENT_VERIFY, "n", " docs_verify_id NOT IN('"
                    + documentVerfiyBEAN.getDocVerifyId() + "')");
        }

        return row;
    }

    /**
     *
     * @param documentVerfiyBEAN
     * @return
     */
    @Override
    public int updateDocumentVerfications(DocumentVerifyBEAN documentVerfiyBEAN) {
        mySqlJdbcTemplate = new JdbcTemplate(dataSource);
        int row = 0;
        String followDate = "";
        if (documentVerfiyBEAN.getFollowupDate() != null) {
            followDate = documentVerfiyBEAN.getFollowupDate().toString();
        }
        String sql = "UPDATE enq_reg_document_verify\n"
                + "SET status = ?,\n"
                + " followup_date = ?,\n"
                + " remarks = ?,\n"
                + " updated_user = ?,\n"
                + " updated_date = NOW()\n"
                + "WHERE\n"
                + "	document_id = ?";
        row = mySqlJdbcTemplate.update(sql, new Object[]{documentVerfiyBEAN.getStatus(),
            followDate,
            documentVerfiyBEAN.getRemarks(),
            Context.getInstance().currentProfile().getProfilePOJO().getUsername(),
            documentVerfiyBEAN.getDocumentId()
        });

        if (row > 0) {
            GlobalClassDAO.updateLatestFlags(documentVerfiyBEAN.getEnquiryId(), TableNames.TABLE_REG_DOCUMENT_VERIFY, "n", " docs_verify_id NOT IN('"
                    + documentVerfiyBEAN.getDocVerifyId() + "')");
        }

        return row;
    }

    /**
     *
     * @param verifyId
     * @return
     */
    @Override
    public int deleteDocumentVerfications(String verifyId) {
        String _isLatestFlag = GlobalClassDAO.checkCurrentEnquiryIdHaveLatestFlag(verifyId, TableNames.TABLE_REG_DOCUMENT_VERIFY, "docs_verify_id");
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        try {
            String sql = "DELETE FROM " + TableNames.TABLE_REG_DOCUMENT_VERIFY + " WHERE docs_verify_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, verifyId);
            row = ps.executeUpdate();
            if (row == 1) {
                if (_isLatestFlag != null) {
                    GlobalClassDAO.updateLatestflagOnDelete(verifyId, TableNames.TABLE_REG_DOCUMENT_VERIFY);
                }
            }
        } catch (SQLException sqle) {
            logger.info(sqle.toString());
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return row;
    }

    /**
     *
     * @param program
     * @param enquiryId
     * @return
     */
    @Override
    public List<DocumentVerifyBEAN> retrieveDocumentVerfications(String program, String enquiryId) {
        List<DocumentVerifyBEAN> listDocumentVerfiy = new ArrayList<>();
        mySqlJdbcTemplate = new JdbcTemplate(dataSource);
        String query = "SELECT\n"
                + "                doc.document_id,\n"
                + "                doc.document,\n"
                + "                ver.docs_verify_id,\n"
                + "                ver.enquiry_id,\n"
                + "                ver. STATUS,\n"
                + "                ver.followup_date,\n"
                + "                ver.remarks,\n"
                + "                doc_asgn.doc_assign_id,\n"
                + "                doc_asgn.program\n"
                + "FROM\n"
                + "                (SELECT * FROM master_documents_assigned doc_asgn1 WHERE\n"
                + "                doc_asgn1.program = ? )as doc_asgn\n"
                + "LEFT JOIN master_documents doc ON doc_asgn.document_id = doc.document_id\n"
                + "LEFT JOIN enq_reg_document_verify ver ON ver.document_id = doc_asgn.document_id\n"
                + "AND ver.enquiry_id = ?";
        listDocumentVerfiy = mySqlJdbcTemplate.query(query, new Object[]{program, enquiryId}, new DocumentMapper());
        return listDocumentVerfiy;
    }

    /**
     *
     * @param documentPhotoBEAN
     * @return
     */
    @Override
    public int insertPhotoDocumentVerfication(DocumentPhotoBEAN documentPhotoBEAN) {
        int row = 0;
        mySqlJdbcTemplate = new JdbcTemplate(dataSource);
        System.out.println("INSERT OPERATION PHOTO");
        System.out.println("documentPhotoBEAN.getInputStream()====" + documentPhotoBEAN.getInputStream());
        System.out.println("documentPhotoBEAN.getPhotoStatus()====" + documentPhotoBEAN.getPhotoStatus());
        System.out.println("documentPhotoBEAN.getEnquiryId()====" + documentPhotoBEAN.getEnquiryId());
        String sqls = "INSERT INTO " + TableNames.TABLE_REG_DOCUMENT_VERIFY_PHOTO + " (\n"
                + "	photo_id,\n"
                + "	photo,\n"
                + "	photo_status,\n"
                + "	enquiry_id\n"
                + ")\n"
                + "VALUES\n"
                + "	(\n"
                + "		0,\n"
                + "		?,\n"
                + "		?,\n"
                + "		?\n"
                + "	);";
        row = mySqlJdbcTemplate.update(sqls, new Object[]{documentPhotoBEAN.getInputStream(),
            documentPhotoBEAN.getPhotoStatus(),
            documentPhotoBEAN.getEnquiryId()
        });

        return row;
    }

    /**
     *
     * @param enquiryId
     * @return
     */
    @Override
    public DocumentPhotoBEAN retrievePhotoDocumentVerfications(String enquiryId) {
        DocumentPhotoBEAN documentPhotoBEAN = new DocumentPhotoBEAN();
        List<DocumentPhotoBEAN> listDocumentVerfiy = new ArrayList<>();

        mySqlJdbcTemplate = new JdbcTemplate(dataSource);
        String query = "SELECT\n"
                + "	photo_id,\n"
                + "	photo,\n"
                + "	photo_status,\n"
                + "	enquiry_id\n"
                + "FROM\n"
                + "	enq_reg_document_verify_photo\n"
                + "WHERE\n"
                + "	enquiry_id =?";
        listDocumentVerfiy = mySqlJdbcTemplate.query(query, new Object[]{enquiryId}, new DocumentMapperPhoto());
        if (listDocumentVerfiy.isEmpty()) {
            return documentPhotoBEAN;
        } else {
            return listDocumentVerfiy.get(0);
        }

    }

    /**
     *
     * @param enquiryId
     * @return
     */
    @Override
    public List<MailSentPOJO> retrieveLogDetails(String enquiryId) {
        mySqlJdbcTemplate = new JdbcTemplate(dataSource);
        String query = "SELECT\n"
                + "	*,DATE_FORMAT(sent_date,'%d-%M-%Y %H:%i:%s %p') as sent_date\n"
                + "FROM\n"
                + "	email_log\n"
                + "WHERE\n"
                + "	enquiry_id = ?\n"
                + "ORDER BY\n"
                + "	sent_date DESC";
        System.out.println("Test ID :: " + enquiryId);
        List<MailSentPOJO> listLogDetails = mySqlJdbcTemplate.query(query, new Object[]{enquiryId}, new DocumentMapperLog());
        System.out.println("Log Size IMPL :: " + listLogDetails.size());
        return listLogDetails;

    }

    /**
     *
     * @param documentPhotoBEAN
     * @return
     */
    @Override
    public int updatePhotoDocumentVerfication(DocumentPhotoBEAN documentPhotoBEAN) {

        mySqlJdbcTemplate = new JdbcTemplate(dataSource);
        int row = 0;
        System.out.println("documentPhotoBEAN.getInputStream()====" + documentPhotoBEAN.getInputStream());
        System.out.println("documentPhotoBEAN.getPhotoStatus()====" + documentPhotoBEAN.getPhotoStatus());
        System.out.println("documentPhotoBEAN.getEnquiryId()====" + documentPhotoBEAN.getEnquiryId());

        String sql = "UPDATE enq_reg_document_verify_photo\n"
                + "SET photo = ?,\n"
                + " photo_status = ?\n"
                + "WHERE\n"
                + "	enquiry_id = ?";
        row = mySqlJdbcTemplate.update(sql, new Object[]{documentPhotoBEAN.getInputStream(),
            documentPhotoBEAN.getPhotoStatus(),
            documentPhotoBEAN.getEnquiryId()
        });

//        if (row > 0) {
//            GlobalClassDAO.updateLatestFlags(documentPhotoBEAN.getEnquiryId(), TableNames.TABLE_REG_DOCUMENT_VERIFY_PHOTO, "n", " email_log_id NOT IN('"
//                    + documentPhotoBEAN.getPhotoId()+ "')");
//        }
        return row;

    }

    /**
     *
     * @param enquiryId
     * @return
     */
    @Override
    public int deletePhotoDocumentVerfication(String enquiryId) {

        mySqlJdbcTemplate = new JdbcTemplate(dataSource);
        int row = 0;
        System.out.println("enquiryId inside DELETION" + enquiryId);

        String sql = "DELETE\n"
                + "FROM\n"
                + "	enq_reg_document_verify_photo\n"
                + "WHERE\n"
                + "	enquiry_id = ?";
        row = mySqlJdbcTemplate.update(sql, new Object[]{enquiryId});

//        if (row > 0) {
//            GlobalClassDAO.updateLatestFlags(documentPhotoBEAN.getEnquiryId(), TableNames.TABLE_REG_DOCUMENT_VERIFY_PHOTO, "n", " email_log_id NOT IN('"
//                    + documentPhotoBEAN.getPhotoId()+ "')");
//        }
        return row;

    }

    /**
     *
     */
    public class DocumentMapperLog implements RowMapper<MailSentPOJO> {

        /**
         *
         * @param rs
         * @param rowNum
         * @return
         * @throws SQLException
         */
        @Override
        public MailSentPOJO mapRow(ResultSet rs, int rowNum) throws SQLException {
            MailSentPOJO mailSentPOJO = new MailSentPOJO();
            mailSentPOJO.setEmailLogId(rs.getString("email_log_id"));
            mailSentPOJO.setFrom(rs.getString("mail_from"));
            mailSentPOJO.setTo(rs.getString("mail_to"));
            mailSentPOJO.setSubject(rs.getString("subject"));
            mailSentPOJO.setMessage(rs.getString("content"));
            mailSentPOJO.setEnquiryId(rs.getString("enquiry_id"));
            if (rs.getString("sent_date") != null) {
                if (!rs.getString("sent_date").equalsIgnoreCase("")) {
                   mailSentPOJO.setSendDate((rs.getString("sent_date")));
                }
            }
            return mailSentPOJO;
        }
    }

    /**
     *
     */
    public class DocumentMapper implements RowMapper<DocumentVerifyBEAN> {

        /**
         *
         * @param rs
         * @param rowNum
         * @return
         * @throws SQLException
         */
        @Override
        public DocumentVerifyBEAN mapRow(ResultSet rs, int rowNum) throws SQLException {
            DocumentVerifyBEAN documentVerfiyBEAN = new DocumentVerifyBEAN();
            documentVerfiyBEAN.setDocVerifyId(rs.getString("docs_verify_id"));
            documentVerfiyBEAN.setDocsAssignId(rs.getString("doc_assign_id"));
            documentVerfiyBEAN.setDocumentId(rs.getString("document_id"));
            documentVerfiyBEAN.setEnquiryId(rs.getString("enquiry_id"));
            if (rs.getString("followup_date") != null) {
                if (!rs.getString("followup_date").equalsIgnoreCase("")) {
                    try {
                        documentVerfiyBEAN.setFollowupDate(LocalDate.parse(rs.getString("followup_date")));
                    } catch (DateTimeParseException dateException) {
                        dateException.printStackTrace();
                        logger.error(dateException.toString());
                    }

                }
            }
            documentVerfiyBEAN.setProgram(rs.getString("program"));
            documentVerfiyBEAN.setRemarks(rs.getString("remarks"));
            documentVerfiyBEAN.setStatus(rs.getString("status"));
            documentVerfiyBEAN.setDocumentName(rs.getString("document"));
            return documentVerfiyBEAN;
        }
    }

    /**
     *
     */
    public class DocumentMapperPhoto implements RowMapper<DocumentPhotoBEAN> {

        /**
         *
         * @param rs
         * @param rowNum
         * @return
         * @throws SQLException
         */
        @Override
        public DocumentPhotoBEAN mapRow(ResultSet rs, int rowNum) throws SQLException {
            DocumentPhotoBEAN documentPhotoBEAN = new DocumentPhotoBEAN();
            documentPhotoBEAN.setPhotoId(rs.getString("photo_id"));
            documentPhotoBEAN.setInputStream(rs.getBinaryStream("photo"));
            documentPhotoBEAN.setPhotoStatus(rs.getString("photo_status"));
            documentPhotoBEAN.setEnquiryId(rs.getString("enquiry_id"));
            return documentPhotoBEAN;
        }
    }
}
