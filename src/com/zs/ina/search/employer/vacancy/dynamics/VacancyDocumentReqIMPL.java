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
package com.zs.ina.search.employer.vacancy.dynamics;

import com.zs.ina.common.constants.TableNames;
import com.zs.ina.search.common.bean.DocumentReqBEAN;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class VacancyDocumentReqIMPL implements VacancyDocumentReqDAO {

    static Logger logger = Logger.getLogger(VacancyDocumentReqIMPL.class);
    @Autowired
    DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean insertDocumentsReq(DocumentReqBEAN documentReqBEAN) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "INSERT INTO search_emplyr_vacancy_documents (\n"
                + "	document_req_id,\n"
                + "	vacancy_id,\n"
                + "	document_id,\n"
                + "	document_description\n"
                + ")\n"
                + "VALUES\n"
                + "	(?, ?, ?, ?);";
        int row = jdbcTemplate.update(sql, new Object[]{documentReqBEAN.getRowId(), documentReqBEAN.getVacancyId(),
            documentReqBEAN.getDocumentId(), documentReqBEAN.getDocumentDescription()});
        return (row > 0);
    }

    @Override
    public boolean updateDocumentsReq(DocumentReqBEAN documentReqBEAN) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "UPDATE search_emplyr_vacancy_documents\n"
                + "SET document_id = ?,\n"
                + " document_description = ?\n"
                + "WHERE\n"
                + "	document_req_id = ?";
        int row = jdbcTemplate.update(sql, new Object[]{
            documentReqBEAN.getDocumentId(), documentReqBEAN.getDocumentDescription(), documentReqBEAN.getRowId()});
        return (row > 0);
    }

    @Override
    public boolean deleteDocumentsReq(String rowId) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "DELETE FROM search_emplyr_vacancy_documents\n"
                + " WHERE\n"
                + " document_req_id=?";
        int row = jdbcTemplate.update(sql, new Object[]{rowId});
        return (row > 0);
    }

    @Override
    public boolean existsDocumentsReq(DocumentReqBEAN documentReqBEAN) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<DocumentReqBEAN> retrieveDocumentsReq(String vacancyId) {

        ObservableList<DocumentReqBEAN> listDocsReq = FXCollections.observableArrayList();
        jdbcTemplate = new JdbcTemplate(dataSource);
        String query = "SELECT * FROM " + TableNames.TBL_SR_EMPLYR_VACANCY_DOCS + " WHERE vacancy_id = ?";
        List<DocumentReqBEAN> temp = jdbcTemplate.query(query, new Object[]{vacancyId}, new DocumentReqMapper());
        listDocsReq.addAll(temp);
        return listDocsReq;

    }

    public class DocumentReqMapper implements RowMapper<DocumentReqBEAN> {

        /**
         *
         * @param rs
         * @param rowNum
         * @return
         * @throws SQLException
         */
        @Override
        public DocumentReqBEAN mapRow(ResultSet rs, int rowNum) throws SQLException {
            DocumentReqBEAN documentReqBEAN = new DocumentReqBEAN();
            documentReqBEAN.setRowId(rs.getString("document_req_id"));
            documentReqBEAN.setVacancyId(rs.getString("vacancy_id"));
            documentReqBEAN.setDocumentId(rs.getString("document_id"));
            documentReqBEAN.setDocumentDescription(rs.getString("document_description"));
            return documentReqBEAN;
        }

    }
}
