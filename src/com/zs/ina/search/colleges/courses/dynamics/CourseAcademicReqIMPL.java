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
import com.zs.ina.search.common.bean.CoursesBEAN;
import com.zs.ina.search.employer.vacancy.dynamics.VacancyAcademicReqIMPL;
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
public class CourseAcademicReqIMPL implements CourseAcademicReqDAO {

    static Logger logger = Logger.getLogger(CourseAcademicReqIMPL.class);
    @Autowired
    DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean insertAcademicRequirements(CoursesBEAN coursesBEAN) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "INSERT INTO " + TableNames.TBL_SR_EDU_COURSE_ACADEMIC_REQUIREMENT + " (\n"
                + "	academic_id,\n"
                + "	course_id,\n"
                + "	level,\n"
                + "	field,\n"
                + "	duration,\n"
                + "	last_update\n"
                + ")\n"
                + "VALUES\n"
                + "	(?, ?, ?, ?, ?,NOW());";
        int row = jdbcTemplate.update(sql, new Object[]{coursesBEAN.getRowId(), coursesBEAN.getCourseId(),
            coursesBEAN.getLevel(), coursesBEAN.getField(), coursesBEAN.getDuration()});
        return (row > 0)? true : false ;

    }

    @Override
    public boolean deleteAcademicRequirements(String academicId) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "DELETE FROM " + TableNames.TBL_SR_EDU_COURSE_ACADEMIC_REQUIREMENT + "\n"
                + " WHERE\n"
                + "academic_id=?";
        int row = jdbcTemplate.update(sql, new Object[]{academicId});
         return (row > 0)? true : false ;

    }

    @Override
    public boolean updateAcademicRequirements(CoursesBEAN coursesBEAN) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "UPDATE " + TableNames.TBL_SR_EDU_COURSE_ACADEMIC_REQUIREMENT + "\n"
                + "SET level = ?,\n"
                + " field = ?,\n"
                + " duration = ?,last_update=now()\n"
                + "WHERE\n"
                + "	academic_id = ?";
        int row = jdbcTemplate.update(sql, new Object[]{
            coursesBEAN.getLevel(), coursesBEAN.getField(), coursesBEAN.getDuration(), coursesBEAN.getRowId()});
        return (row > 0)? true : false ;

    }

    @Override
    public ObservableList<CoursesBEAN> retrieveAcademicRequirements(String courseId) {
        ObservableList<CoursesBEAN> listAcademicReqmts = FXCollections.observableArrayList();
        jdbcTemplate = new JdbcTemplate(dataSource);
        String query = "SELECT * FROM " + TableNames.TBL_SR_EDU_COURSE_ACADEMIC_REQUIREMENT + " WHERE course_id = ? ORDER BY last_update DESC";
        List<CoursesBEAN> temp = jdbcTemplate.query(query, new Object[]{courseId}, new AcademicReqMapper());
        listAcademicReqmts.addAll(temp);
        return listAcademicReqmts;
    }

    public class AcademicReqMapper implements RowMapper<CoursesBEAN> {

        @Override
        public CoursesBEAN mapRow(ResultSet rs, int rowNum) throws SQLException {
            CoursesBEAN academicReqBEAN = new CoursesBEAN();
            academicReqBEAN.setRowId(rs.getString("academic_id"));
            academicReqBEAN.setCourseId(rs.getString("course_id"));
            academicReqBEAN.setLevel(rs.getString("level"));
            academicReqBEAN.setField(rs.getString("field"));
            academicReqBEAN.setDuration(rs.getString("duration"));
            academicReqBEAN.setLastUpdate(rs.getString("last_update"));
            return academicReqBEAN;
        }

    }

}
