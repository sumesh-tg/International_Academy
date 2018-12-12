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
package com.zs.ina.search.colleges.dao;

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
public class CourseReqmtBasicIMPL implements CourseReqmtBasicDAO {

    static Logger logger = Logger.getLogger(CourseReqmtBasicIMPL.class);
    @Autowired
    DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean insertCourseReqmtBasics(EduProviderBEAN eduProviderBEAN) {
        int row = 0;
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "INSERT INTO " + TableNames.TBL_SR_EDU_COURSE_BASICS + "  (\n"
                + "	row_id,\n"
                + "	college_id,\n"
                + "	course_id,\n"
                + "	gender,\n"
                + "	age_from,\n"
                + "	age_to,\n"
                + "	marital_status,\n"
                + "	sslc_board,\n"
                + "	sslc_medium,\n"
                + "	plus2_board,\n"
                + "	plus2_medium,\n"
                + "	english_medium_board,\n"
                + "	english_medium_duration,\n"
                + "	fee_currency,\n"
                + "	application_fee,\n"
                + "	annual_fee,\n"
                + "	semester_fee,\n"
                + "	other_fee,\n"
                + "	intake,\n"
                + "	career,\n"
                + "	advantage,\n"
                + "	created_user,\n"
                + "	created_date,\n"
                + "	campuses\n"
                + ")\n"
                + "VALUES\n"
                + "	(\n"
                + "		?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(),?\n"
                + "	);";
        row = jdbcTemplate.update(sql, new Object[]{
            eduProviderBEAN.getRowId(),
            eduProviderBEAN.getCollegeId(),
            eduProviderBEAN.getCourseId(),
            eduProviderBEAN.getGender(),
            eduProviderBEAN.getAgeFrom(),
            eduProviderBEAN.getAgeTo(),
            eduProviderBEAN.getMaritalStatus(),
            eduProviderBEAN.getSslcBoard(),
            eduProviderBEAN.getSslcMedium(),
            eduProviderBEAN.getPlus2Board(),
            eduProviderBEAN.getPlus2Medium(),
            eduProviderBEAN.getEngMediumBorad(),
            eduProviderBEAN.getEngMeduimDuration(),
            eduProviderBEAN.getFeeCurrency(),
            eduProviderBEAN.getApplicationFee(),
            eduProviderBEAN.getAnnualFee(),
            eduProviderBEAN.getSemesterFee(),
            eduProviderBEAN.getOtherFee(),
            eduProviderBEAN.getIntake(),
            eduProviderBEAN.getCareer(),
            eduProviderBEAN.getAdvantage(),
            eduProviderBEAN.getCreatedUser(),
            eduProviderBEAN.getCampuses()
        });
        return (row > 0);

    }

    @Override
    public boolean updateCourseReqmtBasics(EduProviderBEAN eduProviderBEAN) {

        int row = 0;
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "UPDATE " + TableNames.TBL_SR_EDU_COURSE_BASICS + " \n"
                + "SET \n"
                + " college_id = ?,\n"
                + " gender = ?,\n"
                + " age_from = ?,\n"
                + " age_to = ?,\n"
                + " marital_status = ?,\n"
                + " sslc_board = ?,\n"
                + " sslc_medium = ?,\n"
                + " plus2_board = ?,\n"
                + " plus2_medium = ?,\n"
                + " english_medium_board = ?,\n"
                + " english_medium_duration = ?,\n"
                + " fee_currency = ?,\n"
                + " application_fee = ?,\n"
                + " annual_fee = ?,\n"
                + " semester_fee = ?,\n"
                + " other_fee = ?,\n"
                + " intake = ?,\n"
                + " career = ?,\n"
                + " advantage = ?,\n"
                + " updated_user = ?,\n"
                + " updated_date = NOW(),\n"
                + " campuses = ?\n"
                + "WHERE\n"
                + "	course_id=?";
        row = jdbcTemplate.update(sql, new Object[]{
            eduProviderBEAN.getCollegeId(),
            eduProviderBEAN.getGender(),
            eduProviderBEAN.getAgeFrom(),
            eduProviderBEAN.getAgeTo(),
            eduProviderBEAN.getMaritalStatus(),
            eduProviderBEAN.getSslcBoard(),
            eduProviderBEAN.getSslcMedium(),
            eduProviderBEAN.getPlus2Board(),
            eduProviderBEAN.getPlus2Medium(),
            eduProviderBEAN.getEngMediumBorad(),
            eduProviderBEAN.getEngMeduimDuration(),
            eduProviderBEAN.getFeeCurrency(),
            eduProviderBEAN.getApplicationFee(),
            eduProviderBEAN.getAnnualFee(),
            eduProviderBEAN.getSemesterFee(),
            eduProviderBEAN.getOtherFee(),
            eduProviderBEAN.getIntake(),
            eduProviderBEAN.getCareer(),
            eduProviderBEAN.getAdvantage(),
            eduProviderBEAN.getUpdatedUser(),
            eduProviderBEAN.getCampuses(),
            eduProviderBEAN.getCourseId()
        });
        return (row > 0);

    }

    @Override
    public boolean deleteCourseReqmtBasics(String courseId) {
        int row = 0;
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "DELETE FROM " + TableNames.TBL_SR_EDU_COURSE_BASICS + " \n"
                + " WHERE\n"
                + "course_id=?";
        row = jdbcTemplate.update(sql, new Object[]{courseId});
        return (row > 0);

    }

    @Override
    public EduProviderBEAN retrieveCourseReqmtBasics(String courseId) {

        List<EduProviderBEAN> temp = new ArrayList<>();
        jdbcTemplate = new JdbcTemplate(dataSource);
        String query = "SELECT\n"
                + "	se_cb.row_id, \n"
                + "	se_cb.college_id,\n"
                + "     se_cb.course_id,\n"
                + "	se_cb.gender,\n"
                + "	se_cb.age_from,\n"
                + "	se_cb.age_to,\n"
                + "	se_cb.marital_status,\n"
                + "	se_cb.sslc_board,\n"
                + "	se_cb.sslc_medium,\n"
                + "	se_cb.plus2_board,\n"
                + "	se_cb.plus2_medium,\n"
                + "	se_cb.english_medium_board,\n"
                + "	se_cb.english_medium_duration,\n"
                + "	se_cb.fee_currency,\n"
                + "	se_cb.application_fee,\n"
                + "	se_cb.annual_fee,\n"
                + "	se_cb.semester_fee,\n"
                + "	se_cb.other_fee,\n"
                + "	se_cb.intake,\n"
                + "	se_cb.career,\n"
                + "	se_cb.advantage,\n"
                + "	se_cb.created_user,\n"
                + "	se_cb.updated_user,\n"
                + "	se_cb.created_date,\n"
                + "	se_cb.updated_date,"
                + "	se_cb.campuses,\n"
                + "	se_prov.college_name,\n"
                + "	se_coff.program_level,\n"
                + "	se_coff.program_field,\n"
                + "  master_univ.university_name\n"
                + "FROM\n"
                + "	search_edu_providers se_prov\n"
                + "RIGHT JOIN search_edu_course_basics se_cb ON se_prov.college_id = se_cb.college_id\n"
                + "RIGHT JOIN search_edu_college_courses_offered se_coff ON se_coff.course_id = se_cb.course_id\n"
                + "RIGHT JOIN master_university master_univ ON master_univ.university_id = se_prov.university_id WHERE se_cb.course_id = ?";
        temp = jdbcTemplate.query(query, new Object[]{courseId}, new CourseBasicsMapper());
        if (temp.size() > 0) {
            return temp.get(0);
        } else {
            return new EduProviderBEAN();
        }
    }

    @Override
    public ObservableList<EduProviderBEAN> retrieveEduProviderCourseReqmtBasics(String collegeId) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        List<EduProviderBEAN> listTemp = new ArrayList<>();
        ObservableList<EduProviderBEAN> listEduCourseBasics = FXCollections.observableArrayList();
        String query = "SELECT\n"
                + "	se_cb.row_id, \n"
                + "	se_cb.college_id,\n"
                + "     se_cb.course_id,\n"
                + "	se_cb.gender,\n"
                + "	se_cb.age_from,\n"
                + "	se_cb.age_to,\n"
                + "	se_cb.marital_status,\n"
                + "	se_cb.sslc_board,\n"
                + "	se_cb.sslc_medium,\n"
                + "	se_cb.plus2_board,\n"
                + "	se_cb.plus2_medium,\n"
                + "	se_cb.english_medium_board,\n"
                + "	se_cb.english_medium_duration,\n"
                + "	se_cb.fee_currency,\n"
                + "	se_cb.application_fee,\n"
                + "	se_cb.annual_fee,\n"
                + "	se_cb.semester_fee,\n"
                + "	se_cb.other_fee,\n"
                + "	se_cb.intake,\n"
                + "	se_cb.career,\n"
                + "	se_cb.advantage,\n"
                + "	se_cb.created_user,\n"
                + "	se_cb.updated_user,\n"
                + "	se_cb.created_date,\n"
                + "	se_cb.updated_date,\n"
                + "	se_cb.campuses,\n"
                + "	se_prov.college_name,\n"
                + "	se_coff.program_level,\n"
                + "	se_coff.program_field,\n"
                + "  master_univ.university_name\n"
                + "FROM\n"
                + "	search_edu_providers se_prov\n"
                + "RIGHT JOIN search_edu_course_basics se_cb ON se_prov.college_id = se_cb.college_id\n"
                + "INNER JOIN search_edu_college_courses_offered se_coff ON se_coff.course_id = se_cb.course_id\n"
                + "INNER JOIN master_university master_univ ON master_univ.university_id = se_prov.university_id WHERE se_cb.college_id = '" + collegeId + "'";
        listTemp = jdbcTemplate.query(query, new CourseBasicsMapper());
        listEduCourseBasics.addAll(listTemp);
        return listEduCourseBasics;

    }

    public class CourseBasicsMapper implements RowMapper<EduProviderBEAN> {

        /**
         *
         * @param rs
         * @param rowNum
         * @return
         * @throws SQLException
         */
        @Override
        public EduProviderBEAN mapRow(ResultSet rs, int rowNum) throws SQLException {
            EduProviderBEAN eduProviderBEAN = new EduProviderBEAN();
            eduProviderBEAN.setRowId(rs.getString("row_id"));
            eduProviderBEAN.setCollegeId(rs.getString("college_id"));
            eduProviderBEAN.setCourseId(rs.getString("course_id"));
            eduProviderBEAN.setGender(rs.getString("gender"));
            eduProviderBEAN.setAgeFrom(rs.getString("age_from"));
            eduProviderBEAN.setAgeTo(rs.getString("age_to"));
            eduProviderBEAN.setMaritalStatus(rs.getString("marital_status"));
            eduProviderBEAN.setSslcBoard(rs.getString("sslc_board"));
            eduProviderBEAN.setSslcMedium(rs.getString("sslc_medium"));
            eduProviderBEAN.setPlus2Board(rs.getString("plus2_board"));
            eduProviderBEAN.setPlus2Medium(rs.getString("plus2_medium"));
            eduProviderBEAN.setEngMediumBorad(rs.getString("english_medium_board"));
            eduProviderBEAN.setEngMeduimDuration(rs.getString("english_medium_duration"));
            eduProviderBEAN.setFeeCurrency(rs.getString("fee_currency"));
            eduProviderBEAN.setApplicationFee(rs.getString("application_fee"));
            eduProviderBEAN.setAnnualFee(rs.getString("annual_fee"));
            eduProviderBEAN.setSemesterFee(rs.getString("semester_fee"));
            eduProviderBEAN.setOtherFee(rs.getString("other_fee"));
            eduProviderBEAN.setIntake(rs.getString("intake"));
            eduProviderBEAN.setCareer(rs.getString("career"));
            eduProviderBEAN.setAdvantage(rs.getString("advantage"));
            eduProviderBEAN.setCreatedUser(rs.getString("created_user"));
            eduProviderBEAN.setUpdatedUser(rs.getString("updated_user"));
            eduProviderBEAN.setCreatedDate(rs.getString("created_date"));
            eduProviderBEAN.setUpdatedDate(rs.getString("updated_date"));
            eduProviderBEAN.setCollege(rs.getString("college_name"));
            eduProviderBEAN.setCourseLevel(rs.getString("program_level"));
            eduProviderBEAN.setCourseField(rs.getString("program_field"));
            eduProviderBEAN.setCollege(rs.getString("college_name"));
            eduProviderBEAN.setUniversity(rs.getString("university_name"));
            eduProviderBEAN.setCampuses(rs.getString("campuses"));
            eduProviderBEAN.setLevelAndField(eduProviderBEAN.getCourseLevel() + " " + eduProviderBEAN.getCourseField());
            return eduProviderBEAN;
        }

    }
}
