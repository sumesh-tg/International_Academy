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
package com.zs.ina.search.migration.dynamics;

import com.zs.ina.common.constants.TableNames;
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
public class EducationalSkillsWithIeltsIMPL implements EducationalSkillsWithIeltsDAO {

    static Logger logger = Logger.getLogger(EducationalSkillsWithIeltsIMPL.class);
    @Autowired
    DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean insertEducationalSkillsWidIelts(MigrationSkillsBEAN educationalSkillsBEAN) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "INSERT INTO " + TableNames.TBL_MIG_POINTS_EDU_SKILLS_WITH_IELTS + " (\n"
                + "	row_id,\n"
                + "	migra_provider_id,\n"
                + "	education_level,\n"
                + "	education_field,\n"
                + "	ielts_score,\n"
                + "	eligibility_point,\n"
                + "	created_user,\n"
                + "	created_date\n"
                + ")\n"
                + "VALUES\n"
                + "	(?, ?, ?, ?, ?, ?, ?, NOW());";
        int row = jdbcTemplate.update(sql, new Object[]{educationalSkillsBEAN.getRowId(), educationalSkillsBEAN.getMigraProviderId(),
            educationalSkillsBEAN.getEducationLevel(), educationalSkillsBEAN.getEducationField(), educationalSkillsBEAN.getIeltsScore(),
            educationalSkillsBEAN.getEligibilityPoint(), educationalSkillsBEAN.getCreatedUser()});
        return (row > 0);
    }

    @Override
    public boolean updateEducationalSkillsWidIelts(MigrationSkillsBEAN educationalSkillsBEAN) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "UPDATE " + TableNames.TBL_MIG_POINTS_EDU_SKILLS_WITH_IELTS + "\n"
                + "SET \n"
                + " education_level = ?,\n"
                + " education_field = ?,\n"
                + " ielts_score = ?,\n"
                + " eligibility_point = ?,\n"
                + " updated_user = ?,\n"
                + " updated_date = NOW()\n"
                + "WHERE\n"
                + "	row_id=?";
        int row = jdbcTemplate.update(sql, new Object[]{
            educationalSkillsBEAN.getEducationLevel(), educationalSkillsBEAN.getEducationField(), educationalSkillsBEAN.getIeltsScore(),
            educationalSkillsBEAN.getEligibilityPoint(), educationalSkillsBEAN.getUpdatedUser(), educationalSkillsBEAN.getRowId()});
        return (row > 0);
    }

    @Override
    public boolean deleteEducationalSkillsWidIelts(String rowId) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "DELETE FROM " + TableNames.TBL_MIG_POINTS_EDU_SKILLS_WITH_IELTS + "\n"
                + " WHERE\n"
                + "row_id=?";
        int row = jdbcTemplate.update(sql, new Object[]{rowId});
        return (row > 0);
    }

    @Override
    public boolean existsEducationalSkillsWidIelts(MigrationSkillsBEAN educationalSkillsBEAN) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<MigrationSkillsBEAN> retrieveEducationalSkillsWidIelts(String migProviderId) {
       
        ObservableList<MigrationSkillsBEAN> listContacts = FXCollections.observableArrayList();
        jdbcTemplate = new JdbcTemplate(dataSource);
        String query = "SELECT * FROM " + TableNames.TBL_MIG_POINTS_EDU_SKILLS_WITH_IELTS + " WHERE migra_provider_id = ?";
        List<MigrationSkillsBEAN> temp = jdbcTemplate.query(query, new Object[]{migProviderId}, new EduSkillIeltsMapper());
        listContacts.addAll(temp);
        return listContacts;
     
    }

    public class EduSkillIeltsMapper implements RowMapper<MigrationSkillsBEAN> {

        /**
         *
         * @param rs
         * @param rowNum
         * @return
         * @throws SQLException
         */
        @Override
        public MigrationSkillsBEAN mapRow(ResultSet rs, int rowNum) throws SQLException {
            MigrationSkillsBEAN skillsBEAN = new MigrationSkillsBEAN();
            skillsBEAN.setRowId(rs.getString("row_id"));
            skillsBEAN.setMigraProviderId(rs.getString("migra_provider_id"));
            skillsBEAN.setEducationLevel(rs.getString("education_level"));
            skillsBEAN.setEducationField(rs.getString("education_field"));
            skillsBEAN.setIeltsScore(rs.getString("ielts_score"));
            skillsBEAN.setEligibilityPoint(rs.getString("eligibility_point"));
            skillsBEAN.setCreatedUser(rs.getString("created_user"));
            skillsBEAN.setUpdatedUser(rs.getString("updated_user"));
            skillsBEAN.setCreatedDate(rs.getString("created_date"));
            skillsBEAN.setUpdatedDate(rs.getString("updated_date"));
            return skillsBEAN;
        }

    }
}
