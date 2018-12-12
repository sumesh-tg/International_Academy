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
 * @author SUMESH T.G <ZoftSolutions>
 */
public class VacancyAcademicReqIMPL implements VacancyAcademicReqDAO {

    static Logger logger = Logger.getLogger(VacancyAcademicReqIMPL.class);
    @Autowired
    DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean insertAcademicRequirements(CoursesBEAN coursesBEAN) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "INSERT INTO " + TableNames.TBL_SR_EMPLYR_ACADEMIC_REQ + " (\n"
                + "	vacancy_academic_reqmt_id,\n"
                + "	vacancy_id,\n"
                + "	required_program_level,\n"
                + "	required_program_field,\n"
                + "	required_program_duration,last_update\n"
                + ")\n"
                + "VALUES\n"
                + "	(?, ?, ?, ?, ?,NOW());";
        int row = jdbcTemplate.update(sql, new Object[]{coursesBEAN.getRowId(), coursesBEAN.getVacancyId(),
            coursesBEAN.getLevel(), coursesBEAN.getField(), coursesBEAN.getDuration()});
        return (row > 0);
    }

    @Override
    public boolean deleteAcademicRequirements(String rowId) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "DELETE FROM " + TableNames.TBL_SR_EMPLYR_ACADEMIC_REQ + "\n"
                + " WHERE\n"
                + "vacancy_academic_reqmt_id=?";
        int row = jdbcTemplate.update(sql, new Object[]{rowId});
        return (row > 0);
    }

    @Override
    public boolean updateAcademicRequirements(CoursesBEAN coursesBEAN) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "UPDATE " + TableNames.TBL_SR_EMPLYR_ACADEMIC_REQ + "\n"
                + "SET required_program_level = ?,\n"
                + " required_program_field = ?,\n"
                + " required_program_duration = ?,last_update=now()\n"
                + "WHERE\n"
                + "	vacancy_academic_reqmt_id = ?";
        int row = jdbcTemplate.update(sql, new Object[]{
            coursesBEAN.getLevel(), coursesBEAN.getField(), coursesBEAN.getDuration(), coursesBEAN.getRowId()});
        return (row > 0);
    }

    @Override
    public ObservableList<CoursesBEAN> retrieveAcademicRequirements(String vacancyId) {
        ObservableList<CoursesBEAN> listContacts = FXCollections.observableArrayList();
        jdbcTemplate = new JdbcTemplate(dataSource);
        String query = "SELECT * FROM " + TableNames.TBL_SR_EMPLYR_ACADEMIC_REQ + " WHERE vacancy_id = ? ORDER BY last_update DESC";
        List<CoursesBEAN> temp = jdbcTemplate.query(query, new Object[]{vacancyId}, new AcademicReqMapper());
        listContacts.addAll(temp);
        return listContacts;
    }

    /**
     *
     */
    public class AcademicReqMapper implements RowMapper<CoursesBEAN> {

        /**
         * Mapper Class for academic requirements
         * @param rs
         * @param rowNum
         * @return
         * @throws SQLException
         */
        @Override
        public CoursesBEAN mapRow(ResultSet rs, int rowNum) throws SQLException {
            CoursesBEAN academicReqBEAN = new CoursesBEAN();
            academicReqBEAN.setVacancyId(rs.getString("vacancy_id"));
            academicReqBEAN.setRowId(rs.getString("vacancy_academic_reqmt_id"));
            academicReqBEAN.setField(rs.getString("required_program_level"));
            academicReqBEAN.setLevel(rs.getString("required_program_field"));
            academicReqBEAN.setDuration(rs.getString("required_program_duration"));
            return academicReqBEAN;
        }

    }
}
