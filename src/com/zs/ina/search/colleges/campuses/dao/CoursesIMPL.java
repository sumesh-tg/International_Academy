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
package com.zs.ina.search.colleges.campuses.dao;

import com.zs.ina.search.common.bean.CoursesBEAN;
import com.zs.ina.common.constants.TableNames;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class CoursesIMPL implements CoursesDAO {

    Logger logger = Logger.getLogger(CoursesIMPL.class);
    @Autowired
    DataSource dataSource;
    private JdbcTemplate mySqlJdbcTemplate;

    @Override
    public int insertCourses(CoursesBEAN coursesBEAN) {
        int row = 0;
        String sqls = "INSERT INTO " + TableNames.TBL_SR_EDU_COLLEGE_COURSES_OFFERED + " (\n"
                + "	course_id,\n"
                + "	college_id,\n"
                + "	university_id,\n"
                + "	program_level,\n"
                + "	program_field,\n"
                + "	duration\n"
                + ")\n"
                + "VALUES\n"
                + "	(\n"
                + "		?,\n"
                + "		?,\n"
                + "		?,\n"
                + "		?,\n"
                + "		?,\n"
                + "		?\n"
                + "	);";
        row = mySqlJdbcTemplate.update(sqls, new Object[]{coursesBEAN.getCourseId(),
            coursesBEAN.getCollegeId(),
            coursesBEAN.getUniversityId(),
            coursesBEAN.getLevel(),
            coursesBEAN.getField(),
            coursesBEAN.getDuration()
        });
        return row;

    }

    @Override
    public int updateCourses(CoursesBEAN coursesBEAN) {
        mySqlJdbcTemplate = new JdbcTemplate(dataSource);
        int row = 0;

        String sql = "UPDATE " + TableNames.TBL_SR_EDU_COLLEGE_COURSES_OFFERED + "\n"
                + "SET college_id = ?,university_id = ?,program_level = ?,program_field = ?,duration = ?\n"
                + "WHERE\n"
                + "	course_id = ?";
        row = mySqlJdbcTemplate.update(sql, new Object[]{coursesBEAN.getCollegeId(),
            coursesBEAN.getUniversityId(),
            coursesBEAN.getLevel(),
            coursesBEAN.getField(),
            coursesBEAN.getDuration(),
            coursesBEAN.getCourseId()
        });

        return row;

    }

    @Override
    public ObservableList<CoursesBEAN> retrieveCourses(String id) {
        mySqlJdbcTemplate = new JdbcTemplate(dataSource);
        List<CoursesBEAN> listTemp = new ArrayList<>();
        ObservableList<CoursesBEAN> listCourses = FXCollections.observableArrayList();
        String query = "SELECT * FROM " + TableNames.TBL_SR_EDU_COLLEGE_COURSES_OFFERED + " WHERE college_id = '" + id + "'";
        listTemp = mySqlJdbcTemplate.query(query, new CoursesMapperTemplates());
        listCourses.addAll(listTemp);
        return listCourses;
    }

    @Override
    public boolean deleteCourse(String id) {
        int row = 0;
        mySqlJdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "DELETE FROM " + TableNames.TBL_SR_EDU_COLLEGE_COURSES_OFFERED + "\n"
                + " WHERE\n"
                + "course_id=?";
        row = mySqlJdbcTemplate.update(sql, new Object[]{id});
        return (row > 0) ? true : false;
    }

//    @Override
//    public CoursesBEAN retrieveCampusListFromCourse(String courseId) {
//        List<CoursesBEAN> temp = new ArrayList<>();
//        mySqlJdbcTemplate = new JdbcTemplate(dataSource);
//        String query = "SELECT * FROM " + TableNames.TBL_SR_EDU_COLLEGE_COURSES_OFFERED + " WHERE course_id = '" + courseId + "'";
//        temp = mySqlJdbcTemplate.query(query, new CoursesMapperTemplates());
//        if (temp.size() > 0) {
//            return temp.get(0);
//        } else {
//            return new CoursesBEAN();
//        }
//
//    }
//
//    @Override
//    public int updateCampusList(CoursesBEAN coursesBEAN) {
//        mySqlJdbcTemplate = new JdbcTemplate(dataSource);
//        int row = 0;
//
//        String sql = "UPDATE " + TableNames.TBL_SR_EDU_COLLEGE_COURSES_OFFERED + "\n"
//                + "SET campuses_list = ?\n"
//                + "WHERE\n"
//                + "	course_id = ?";
//        row = mySqlJdbcTemplate.update(sql, new Object[]{
//            coursesBEAN.getCampusesList(),
//            coursesBEAN.getCourseId()
//        });
//
//        return row;
//
//    }

//    @Override
//    public CoursesBEAN checkCampusIdExists(String campusId, String courseId) {
//
//        mySqlJdbcTemplate = new JdbcTemplate(dataSource);
//        List<CoursesBEAN> temp = new ArrayList<>();
//        String sql = "SELECT * FROM " + TableNames.TBL_SR_EDU_COLLEGE_COURSES_OFFERED + " WHERE course_id = '" + courseId + "' AND campuses_list LIKE '%" + campusId + "%'";
//        temp = mySqlJdbcTemplate.query(sql, new CoursesMapperTemplates());
//        if (temp.size() > 0) {
//            return temp.get(0);
//        } else {
//            return new CoursesBEAN();
//        }
//
//    }

    public class CoursesMapperTemplates implements RowMapper<CoursesBEAN> {

        @Override
        public CoursesBEAN mapRow(ResultSet rs, int rowNum) throws SQLException {
            CoursesBEAN coursesBEAN = new CoursesBEAN();
            coursesBEAN.setCourseId(rs.getString("course_id"));
            coursesBEAN.setCollegeId(rs.getString("college_id"));
            coursesBEAN.setUniversityId(rs.getString("university_id"));
            coursesBEAN.setLevel(rs.getString("program_level"));
            coursesBEAN.setField(rs.getString("program_field"));
            coursesBEAN.setDuration(rs.getString("duration"));
            return coursesBEAN;
        }

    }

}
