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
package com.zs.ina.search.training.courses.dynamics;

import com.zs.ina.common.constants.TableNames;
import com.zs.ina.search.colleges.courses.dynamics.CourseDocumentReqIMPL;
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
 * @author 100035
 */
public class TrainCourseDocumentReqIMPL implements TrainCourseDocumentReqDAO {

    static Logger logger = Logger.getLogger(TrainCourseDocumentReqIMPL.class);
    @Autowired
    DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean insertTrainDocumentsReq(DocumentReqBEAN documentReqBEAN) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "INSERT INTO " + TableNames.TBL_TR_COURSE_DOCS + " (\n"
                + "	document_req_id,\n"
                + "	training_id,\n"
                + "	document_id,\n"
                + "	document_description,\n"
                + "	created_date,\n"
                + "	created_user\n"
                + ")\n"
                + "VALUES\n"
                + "	(?, ?, ?, ?, now(), ?);";
        int row = jdbcTemplate.update(sql, new Object[]{
            documentReqBEAN.getRowId(),
            documentReqBEAN.getTrainingId(),
            documentReqBEAN.getDocumentId(),
            documentReqBEAN.getDocumentDescription(),
            documentReqBEAN.getCreatedUser()
        });
        return (row > 0);

    }

    @Override
    public boolean updateTrainDocumentsReq(DocumentReqBEAN documentReqBEAN) {

        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "UPDATE " + TableNames.TBL_TR_COURSE_DOCS + "\n"
                + "SET document_id = ?,\n"
                + " document_description = ?,\n"
                + " updated_user = ?,"
                + " updated_date=now()\n"
                + "WHERE\n"
                + "	document_req_id = ?";
        int row = jdbcTemplate.update(sql, new Object[]{
            documentReqBEAN.getDocumentId(), documentReqBEAN.getDocumentDescription(),
            documentReqBEAN.getUpdatedUser(),
            documentReqBEAN.getRowId()
        });
        return (row > 0);
    }

    @Override
    public boolean deleteDocumentsReq(String rowId) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "DELETE FROM " + TableNames.TBL_TR_COURSE_DOCS + "\n"
                + " WHERE\n"
                + " document_req_id=?";
        int row = jdbcTemplate.update(sql, new Object[]{rowId});
        return (row > 0);

    }

    @Override
    public boolean existsTrainDocumentsReq(DocumentReqBEAN documentReqBEAN) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<DocumentReqBEAN> retrieveTrainDocumentsReq(String trainingId) {
        ObservableList<DocumentReqBEAN> listDocsReq = FXCollections.observableArrayList();
        jdbcTemplate = new JdbcTemplate(dataSource);
        String query = "SELECT * FROM " + TableNames.TBL_TR_COURSE_DOCS + " WHERE training_id = ?";
        List<DocumentReqBEAN> temp = jdbcTemplate.query(query, new Object[]{trainingId}, new TrainDocumentReqMapper());
        listDocsReq.addAll(temp);
        return listDocsReq;

    }

    public class TrainDocumentReqMapper implements RowMapper<DocumentReqBEAN> {

        @Override
        public DocumentReqBEAN mapRow(ResultSet rs, int rowNum) throws SQLException {

            DocumentReqBEAN documentReqBEAN = new DocumentReqBEAN();
            documentReqBEAN.setRowId(rs.getString("document_req_id"));
            documentReqBEAN.setTrainingId(rs.getString("training_id"));
            documentReqBEAN.setDocumentId(rs.getString("document_id"));
            documentReqBEAN.setDocumentDescription(rs.getString("document_description"));
            documentReqBEAN.setCreatedUser(rs.getString("created_user"));
            documentReqBEAN.setCreatedDate(rs.getString("created_date"));
            documentReqBEAN.setUpdatedUser(rs.getString("updated_user"));
            documentReqBEAN.setUpdatedDate(rs.getString("updated_date"));
            return documentReqBEAN;
        }

    }

}
