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
import com.zs.ina.search.colleges.courses.dynamics.CourseAdmissionTestIMPL;
import com.zs.ina.search.common.bean.AdmissionBEAN;
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
public class TrainCourseAdmissionTestIMPL implements TrainCourseAdmissionTestDAO {

    static Logger logger = Logger.getLogger(CourseAdmissionTestIMPL.class);
    @Autowired
    DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean insertTrainAdmissionTest(AdmissionBEAN admissionBEAN) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "INSERT INTO " + TableNames.TBL_TR_COURSE_ADMISION_TEST + " (\n"
                + "	admission_test_id,\n"
                + "	training_id,\n"
                + "	admission_test,\n"
                + "	reading,\n"
                + "	listening,\n"
                + "	writing,\n"
                + "	speaking,\n"
                + "	created_date,\n"
                + "	created_user\n"
                + ")\n"
                + "VALUES\n"
                + "	(?, ?, ?, ?, ?, ?, ?, now(), ?);";
        int row = jdbcTemplate.update(sql, new Object[]{
            admissionBEAN.getRowId(),
            admissionBEAN.getTrainingId(),
            admissionBEAN.getAdmissionTest(),
            admissionBEAN.getReading(),
            admissionBEAN.getListening(),
            admissionBEAN.getWriting(), 
            admissionBEAN.getSpeaking(),
            admissionBEAN.getCreatedUser()
        });
        return (row > 0);
    }

    @Override
    public boolean deleteTrainAdmissionTest(String rowId) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "DELETE FROM " + TableNames.TBL_TR_COURSE_ADMISION_TEST + "\n"
                + " WHERE\n"
                + "admission_test_id=?";
        int row = jdbcTemplate.update(sql, new Object[]{rowId});
        return (row > 0);
    }

    @Override
    public boolean updateTrainAdmissionTest(AdmissionBEAN admissionBEAN) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "UPDATE " + TableNames.TBL_TR_COURSE_ADMISION_TEST + "\n"
                + "SET admission_test = ?,\n"
                + " reading = ?,\n"
                + " listening = ?,\n"
                + " writing = ?,\n"
                + " speaking = ?,\n"
                + " updated_user = ?,"
                + " updated_date=now()\n"
                + "WHERE\n"
                + "	admission_test_id = ?";
        int row = jdbcTemplate.update(sql, new Object[]{
            admissionBEAN.getAdmissionTest(), admissionBEAN.getReading(), admissionBEAN.getListening(),
            admissionBEAN.getWriting(), admissionBEAN.getSpeaking(),
            admissionBEAN.getUpdatedUser(),
            admissionBEAN.getRowId()
        });
        return (row > 0);

    }

    @Override
    public ObservableList<AdmissionBEAN> retrieveTrainAdmissionTest(String courseId) {
        ObservableList<AdmissionBEAN> listAdmissionTests = FXCollections.observableArrayList();
        jdbcTemplate = new JdbcTemplate(dataSource);
        String query = "SELECT * FROM " + TableNames.TBL_TR_COURSE_ADMISION_TEST + " WHERE training_id = ?";
        List<AdmissionBEAN> temp = jdbcTemplate.query(query, new Object[]{courseId}, new TrainAdmissionTestMapper());
        listAdmissionTests.addAll(temp);
        return listAdmissionTests;

    }

    public class TrainAdmissionTestMapper implements RowMapper<AdmissionBEAN> {

        @Override
        public AdmissionBEAN mapRow(ResultSet rs, int rowNum) throws SQLException {
            AdmissionBEAN admissionBEAN = new AdmissionBEAN();
            admissionBEAN.setRowId(rs.getString("admission_test_id"));
            admissionBEAN.setTrainingId(rs.getString("training_id"));
            admissionBEAN.setAdmissionTest(rs.getString("admission_test"));
            admissionBEAN.setReading(rs.getString("reading"));
            admissionBEAN.setListening(rs.getString("listening"));
            admissionBEAN.setWriting(rs.getString("writing"));
            admissionBEAN.setSpeaking(rs.getString("speaking"));
            admissionBEAN.setCreatedUser(rs.getString("created_user"));
            admissionBEAN.setCreatedDate(rs.getString("created_date"));
            admissionBEAN.setUpdatedUser(rs.getString("updated_user"));
            admissionBEAN.setUpdatedDate(rs.getString("updated_date"));
            return admissionBEAN;
        }

    }

}
