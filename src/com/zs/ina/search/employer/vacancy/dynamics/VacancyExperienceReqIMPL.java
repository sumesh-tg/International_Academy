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
import com.zs.ina.search.common.bean.ExperienceReqBEAN;
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
public class VacancyExperienceReqIMPL implements VacancyExperienceReqDAO {

    static Logger logger = Logger.getLogger(VacancyExperienceReqIMPL.class);
    @Autowired
    DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean insertExperience(ExperienceReqBEAN experienceReqBEAN) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "INSERT INTO " + TableNames.TBL_SR_EMPLYR_VACANCY_EXP_REQ + " (\n"
                + "	exp_reqmt_id,\n"
                + "	vacancy_id,\n"
                + "	profession,\n"
                + "	experince_duration\n"
                + ")\n"
                + "VALUES\n"
                + "	(?, ?, ?, ?);";
        int row = jdbcTemplate.update(sql, new Object[]{experienceReqBEAN.getRowId(), experienceReqBEAN.getVacancyId(),
            experienceReqBEAN.getProfession(), experienceReqBEAN.getExperinceDuration()});
        return (row > 0);
    }

    @Override
    public boolean deleteExperience(String rowId) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "DELETE FROM " + TableNames.TBL_SR_EMPLYR_VACANCY_EXP_REQ + "\n"
                + " WHERE\n"
                + "exp_reqmt_id=?";
        int row = jdbcTemplate.update(sql, new Object[]{rowId});
        return (row > 0);
    }

    @Override
    public boolean updateExperience(ExperienceReqBEAN experienceReqBEAN) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "UPDATE " + TableNames.TBL_SR_EMPLYR_VACANCY_EXP_REQ + "\n"
                + "SET profession = ?,\n"
                + " experince_duration = ?\n"
                + "WHERE\n"
                + "	exp_reqmt_id = ?";
        int row = jdbcTemplate.update(sql, new Object[]{
            experienceReqBEAN.getProfession(), experienceReqBEAN.getExperinceDuration(), experienceReqBEAN.getRowId()});
        return (row > 0);
    }

    @Override
    public ObservableList<ExperienceReqBEAN> retrieveExperience(String vacancyId) {
       
        ObservableList<ExperienceReqBEAN> listExperience = FXCollections.observableArrayList();
        jdbcTemplate = new JdbcTemplate(dataSource);
        String query = "SELECT * FROM " + TableNames.TBL_SR_EMPLYR_VACANCY_EXP_REQ + " WHERE vacancy_id = ?";
        List<ExperienceReqBEAN> temp = jdbcTemplate.query(query, new Object[]{vacancyId}, new ExperienceMapper());
        listExperience.addAll(temp);
        return listExperience;
    }

    public class ExperienceMapper implements RowMapper<ExperienceReqBEAN> {

        /**
         *
         * @param rs
         * @param rowNum
         * @return
         * @throws SQLException
         */
        @Override
        public ExperienceReqBEAN mapRow(ResultSet rs, int rowNum) throws SQLException {
            ExperienceReqBEAN experienceReqBEAN = new ExperienceReqBEAN();
            experienceReqBEAN.setRowId(rs.getString("exp_reqmt_id"));
            experienceReqBEAN.setVacancyId(rs.getString("vacancy_id"));
            experienceReqBEAN.setProfession(rs.getString("profession"));
            experienceReqBEAN.setExperinceDuration(rs.getString("experince_duration"));
            return experienceReqBEAN;
        }

    }
}
