/*
 * Copyright (C) 2016 100035
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
package com.zs.ina.search.colleges.courses.dynamics;

import com.zs.ina.common.constants.TableNames;
import com.zs.ina.search.common.bean.DocumentReqBEAN;
import com.zs.ina.search.employer.vacancy.dynamics.VacancyDocumentReqIMPL;
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
 * @author 100035
 */
public class CourseDocumentReqIMPL implements CourseDocumentReqDAO {

    static Logger logger = Logger.getLogger(CourseDocumentReqIMPL.class);
    @Autowired
    DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean insertDocumentsReq(DocumentReqBEAN documentReqBEAN) {
  jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "INSERT INTO " + TableNames.TBL_SR_EDU_COURSE_DOCUMENTS + " (\n"
                + "	document_req_id,\n"
                + "	course_id,\n"
                + "	document_id,\n"
                + "	document_description\n"
                + ")\n"
                + "VALUES\n"
                + "	(?, ?, ?, ?);";
        int row = jdbcTemplate.update(sql, new Object[]{
            documentReqBEAN.getRowId(), 
            documentReqBEAN.getCourseId(),
            documentReqBEAN.getDocumentId(),
            documentReqBEAN.getDocumentDescription()
        });
        return (row > 0);

    }

    @Override
    public boolean updateDocumentsReq(DocumentReqBEAN documentReqBEAN) {
  jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "UPDATE " + TableNames.TBL_SR_EDU_COURSE_DOCUMENTS + "\n"
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
        String sql = "DELETE FROM " + TableNames.TBL_SR_EDU_COURSE_DOCUMENTS + "\n"
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
    public ObservableList<DocumentReqBEAN> retrieveDocumentsReq(String courseId) {
      ObservableList<DocumentReqBEAN> listDocsReq = FXCollections.observableArrayList();
        jdbcTemplate = new JdbcTemplate(dataSource);
        String query = "SELECT * FROM " + TableNames.TBL_SR_EDU_COURSE_DOCUMENTS + " WHERE course_id = ?";
        List<DocumentReqBEAN> temp = jdbcTemplate.query(query, new Object[]{courseId}, new DocumentReqMapper());
        listDocsReq.addAll(temp);
        return listDocsReq;

    }

    public class DocumentReqMapper implements RowMapper<DocumentReqBEAN> {

 
        @Override
        public DocumentReqBEAN mapRow(ResultSet rs, int rowNum) throws SQLException {

            DocumentReqBEAN documentReqBEAN = new DocumentReqBEAN();
            documentReqBEAN.setRowId(rs.getString("document_req_id"));
            documentReqBEAN.setCourseId(rs.getString("course_id"));
            documentReqBEAN.setDocumentId(rs.getString("document_id"));
            documentReqBEAN.setDocumentDescription(rs.getString("document_description"));
            return documentReqBEAN;
        }

    }

}
