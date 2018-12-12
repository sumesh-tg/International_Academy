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
import com.zs.ina.search.common.bean.CoursesBEAN;
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
public class TrainCourseAcademicReqmtIMPL implements TrainCourseAcademicReqmtDAO {

    static Logger logger = Logger.getLogger(TrainCourseAcademicReqmtIMPL.class);
    @Autowired
    DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean insertTrainingAcademicRequirements(CoursesBEAN coursesBEAN) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "INSERT INTO " + TableNames.TBL_TR_COURSE_ACADEMIC_REQ + " (\n"
                + "	academic_reqmt_id,\n"
                + "	training_id,\n"
                + "	level,\n"
                + "	field,\n"
                + "	duration,\n"
                + "	created_date,\n"
                + "	created_user\n"
                + ")\n"
                + "VALUES\n"
                + "	(?, ?, ?, ?, ?, now(),?)";
        int row = jdbcTemplate.update(sql, new Object[]{
            coursesBEAN.getRowId(),
            coursesBEAN.getTrainingId(),
            coursesBEAN.getLevel(),
            coursesBEAN.getField(),
            coursesBEAN.getDuration(),
            coursesBEAN.getCreatedUser()
        });
        return (row > 0);
    }

    @Override
    public boolean deleteTrainingAcademicRequirements(String academicId) {

        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "DELETE FROM " + TableNames.TBL_TR_COURSE_ACADEMIC_REQ + "\n"
                + " WHERE\n"
                + "academic_reqmt_id=?";
        int row = jdbcTemplate.update(sql, new Object[]{academicId});
        return (row > 0);
    }

    @Override
    public boolean updateTrainingAcademicRequirements(CoursesBEAN coursesBEAN) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "UPDATE " + TableNames.TBL_TR_COURSE_ACADEMIC_REQ + "\n"
                + "SET level = ?,\n"
                + " field = ?,\n"
                + " duration = ?,"
                + " updated_user = ?,"
                + " updated_date=now()\n"
                + "WHERE\n"
                + "	academic_reqmt_id = ?";
        int row = jdbcTemplate.update(sql, new Object[]{
            coursesBEAN.getLevel(), coursesBEAN.getField(), coursesBEAN.getDuration(),
            coursesBEAN.getUpdatedUser(),
            coursesBEAN.getRowId(),});
        return (row > 0);

    }

    @Override
    public ObservableList<CoursesBEAN> retrieveTrainingAcademicRequirements(String trainingId) {
        ObservableList<CoursesBEAN> listAcademicReqmts = FXCollections.observableArrayList();
        jdbcTemplate = new JdbcTemplate(dataSource);
        String query = "SELECT * FROM " + TableNames.TBL_TR_COURSE_ACADEMIC_REQ + " WHERE training_id = ?";
        List<CoursesBEAN> temp = jdbcTemplate.query(query, new Object[]{trainingId}, new TrainAcademicReqMapper());
        listAcademicReqmts.addAll(temp);
        return listAcademicReqmts;
    }

    public class TrainAcademicReqMapper implements RowMapper<CoursesBEAN> {

        @Override
        public CoursesBEAN mapRow(ResultSet rs, int rowNum) throws SQLException {
            CoursesBEAN academicReqBEAN = new CoursesBEAN();
            academicReqBEAN.setRowId(rs.getString("academic_reqmt_id"));
            academicReqBEAN.setTrainingId(rs.getString("training_id"));
            academicReqBEAN.setLevel(rs.getString("level"));
            academicReqBEAN.setField(rs.getString("field"));
            academicReqBEAN.setDuration(rs.getString("duration"));
            academicReqBEAN.setCreatedUser(rs.getString("created_user"));
            academicReqBEAN.setCreatedDate(rs.getString("created_date"));
            academicReqBEAN.setUpdatedUser(rs.getString("updated_user"));
            academicReqBEAN.setUpdatedDate(rs.getString("updated_date"));
            return academicReqBEAN;
        }

    }

}
