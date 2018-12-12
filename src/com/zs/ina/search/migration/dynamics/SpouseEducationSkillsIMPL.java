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
public class SpouseEducationSkillsIMPL implements SpouseEducationSkillsDAO {

    static Logger logger = Logger.getLogger(SpouseEducationSkillsIMPL.class);
    @Autowired
    DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean insertEducationSkills(MigrationSkillsBEAN migrationSkillsBEAN) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "INSERT INTO " + TableNames.TBL_MIG_POINTS_SPOUSE_EDUCATION + " (\n"
                + "	row_id,\n"
                + "	migra_provider_id,\n"
                + "	edu_level,\n"
                + "	edu_field,\n"
                + "	eligibility_point,\n"
                + "	select_point,\n"
                + "	created_user,\n"
                + "	created_date\n"
                + ")\n"
                + "VALUES\n"
                + "	(?, ?, ?, ?, ?, ?, ?, NOW() );";
        int row = jdbcTemplate.update(sql, new Object[]{migrationSkillsBEAN.getRowId(), migrationSkillsBEAN.getMigraProviderId(),
            migrationSkillsBEAN.getEducationLevel(), migrationSkillsBEAN.getEducationField(), migrationSkillsBEAN.getEligibilityPoint(),
            migrationSkillsBEAN.getSelectPointSingle(), migrationSkillsBEAN.getCreatedUser()
        });
        return (row > 0);
    }

    @Override
    public boolean updateEducationSkills(MigrationSkillsBEAN migrationSkillsBEAN) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "UPDATE " + TableNames.TBL_MIG_POINTS_SPOUSE_EDUCATION + "\n"
                + "SET edu_level =  ?,\n"
                + " edu_field =  ?,\n"
                + " eligibility_point =  ?,\n"
                + " select_point =  ?,\n"
                + " updated_user =  ?,\n"
                + " updated_date =  NOW()\n"
                + "WHERE\n"
                + "	row_id =  ?";
        int row = jdbcTemplate.update(sql, new Object[]{
            migrationSkillsBEAN.getEducationLevel(), migrationSkillsBEAN.getEducationField(), migrationSkillsBEAN.getEligibilityPoint(),
            migrationSkillsBEAN.getSelectPointSingle(), migrationSkillsBEAN.getCreatedUser(), migrationSkillsBEAN.getRowId()
        });
        return (row > 0);
    }

    @Override
    public boolean deleteEducationSkills(String rowId) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "DELETE FROM " + TableNames.TBL_MIG_POINTS_SPOUSE_EDUCATION + "\n"
                + " WHERE\n"
                + " row_id=?";
        int row = jdbcTemplate.update(sql, new Object[]{rowId});
        return (row > 0);
    }

    @Override
    public boolean existsEducationSkills(MigrationSkillsBEAN migrationSkillsBEAN) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<MigrationSkillsBEAN> retrieveEducationSkills(String migProviderId) {

        ObservableList<MigrationSkillsBEAN> listContacts = FXCollections.observableArrayList();
        jdbcTemplate = new JdbcTemplate(dataSource);
        String query = "SELECT * FROM " + TableNames.TBL_MIG_POINTS_SPOUSE_EDUCATION + " WHERE migra_provider_id = ?";
        List<MigrationSkillsBEAN> temp = jdbcTemplate.query(query, new Object[]{migProviderId}, new SpouseEduSkillsMapper());
        listContacts.addAll(temp);
        return listContacts;

    }

    public class SpouseEduSkillsMapper implements RowMapper<MigrationSkillsBEAN> {

        /**
         *
         * @param rs
         * @param rowNum
         * @return
         * @throws SQLException
         */
        @Override
        public MigrationSkillsBEAN mapRow(ResultSet rs, int rowNum) throws SQLException {
            MigrationSkillsBEAN branchBEAN = new MigrationSkillsBEAN();
            branchBEAN.setRowId(rs.getString("row_id"));
            branchBEAN.setMigraProviderId(rs.getString("migra_provider_id"));
            branchBEAN.setEducationLevel(rs.getString("edu_level"));
            branchBEAN.setEducationField(rs.getString("edu_field"));
            branchBEAN.setEligibilityPoint(rs.getString("eligibility_point"));
            branchBEAN.setSelectPointSingle(rs.getString("select_point"));
            branchBEAN.setCreatedUser(rs.getString("created_user"));
            branchBEAN.setUpdatedUser(rs.getString("updated_user"));
            branchBEAN.setCreatedDate(rs.getString("created_date"));
            branchBEAN.setUpdatedDate(rs.getString("updated_date"));
            return branchBEAN;
        }

    }
}
