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
public class SpouseAbilityEnglishIMPL implements SpouseAbilityEnglishDAO {

    static Logger logger = Logger.getLogger(SpouseAbilityEnglishIMPL.class);
    @Autowired
    DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean insertSpouseEnglishAbility(MigrationSkillsBEAN migrationSkillsBEAN) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "INSERT INTO " + TableNames.TBL_MIG_POINTS_SPOUSE_ABILITY_ENGLISH + " (\n"
                + "	row_id,\n"
                + "	migra_provider_id,\n"
                + "	application_type,\n"
                + "	language_test,\n"
                + "	reading_score,\n"
                + "	listeing_score,\n"
                + "	writing_score,\n"
                + "	speaking_score,\n"
                + "	point_type,\n"
                + "	point,\n"
                + "	created_user,\n"
                + "	created_date\n"
                + ")\n"
                + "VALUES\n"
                + "	(?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, NOW());";
        int row = jdbcTemplate.update(sql, new Object[]{migrationSkillsBEAN.getRowId(), migrationSkillsBEAN.getMigraProviderId(),
            migrationSkillsBEAN.getApplicationType(), migrationSkillsBEAN.getLanguagTest(), migrationSkillsBEAN.getReadingScore(),
            migrationSkillsBEAN.getListeingScore(), migrationSkillsBEAN.getWritingScore(),migrationSkillsBEAN.getSpeakingScore(),
            migrationSkillsBEAN.getPointType(),migrationSkillsBEAN.getPoint(), migrationSkillsBEAN.getCreatedUser()
        });
        return (row > 0);
    }

    @Override
    public boolean updateSpouseEnglishAbility(MigrationSkillsBEAN migrationSkillsBEAN) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "UPDATE " + TableNames.TBL_MIG_POINTS_SPOUSE_ABILITY_ENGLISH + "\n"
                + "SET application_type =   ?,\n"
                + " language_test =   ?,\n"
                + " reading_score =   ?,\n"
                + " listeing_score =   ?,\n"
                + " writing_score =   ?,\n"
                + " speaking_score =   ?,\n"
                + " point_type =   ?,\n"
                + " point =   ?,\n"
                + " updated_user =   ?,\n"
                + " updated_date =   NOW()\n"
                + "WHERE\n"
                + "	row_id =   ?";
        int row = jdbcTemplate.update(sql, new Object[]{
            migrationSkillsBEAN.getApplicationType(), migrationSkillsBEAN.getLanguagTest(), migrationSkillsBEAN.getReadingScore(),
            migrationSkillsBEAN.getListeingScore(), migrationSkillsBEAN.getWritingScore(),migrationSkillsBEAN.getSpeakingScore(), 
            migrationSkillsBEAN.getPointType(),migrationSkillsBEAN.getPoint(), migrationSkillsBEAN.getCreatedUser(),
            migrationSkillsBEAN.getRowId()
        });
        return (row > 0);
    }

    @Override
    public boolean deleteSpouseEnglishAbility(String rowId) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "DELETE FROM " + TableNames.TBL_MIG_POINTS_SPOUSE_ABILITY_ENGLISH + "\n"
                + " WHERE\n"
                + " row_id=?";
        int row = jdbcTemplate.update(sql, new Object[]{rowId});
        return (row > 0);
    }

    @Override
    public boolean existsSpouseEnglishAbility(MigrationSkillsBEAN migrationSkillsBEAN) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<MigrationSkillsBEAN> retrieveSpouseEnglishAbility(String migProviderId) {
       
        ObservableList<MigrationSkillsBEAN> listContacts = FXCollections.observableArrayList();
        jdbcTemplate = new JdbcTemplate(dataSource);
        String query = "SELECT * FROM " + TableNames.TBL_MIG_POINTS_SPOUSE_ABILITY_ENGLISH + " WHERE migra_provider_id = ?";
        List<MigrationSkillsBEAN> temp = jdbcTemplate.query(query, new Object[]{migProviderId}, new SpouseEnglishAbilityMapper());
        listContacts.addAll(temp);
        return listContacts;
    
    }

    public class SpouseEnglishAbilityMapper implements RowMapper<MigrationSkillsBEAN> {

        /**
         *
         * @param rs
         * @param rowNum
         * @return
         * @throws SQLException
         */
        @Override
        public MigrationSkillsBEAN mapRow(ResultSet rs, int rowNum) throws SQLException {
            MigrationSkillsBEAN spouseAbilityEnglish = new MigrationSkillsBEAN();
            spouseAbilityEnglish.setRowId(rs.getString("row_id"));
            spouseAbilityEnglish.setMigraProviderId(rs.getString("migra_provider_id"));
            spouseAbilityEnglish.setApplicationType(rs.getString("application_type"));
            spouseAbilityEnglish.setLanguagTest(rs.getString("language_test"));
            spouseAbilityEnglish.setReadingScore(rs.getString("reading_score"));
            spouseAbilityEnglish.setListeingScore(rs.getString("listeing_score"));
            spouseAbilityEnglish.setWritingScore(rs.getString("writing_score"));
            spouseAbilityEnglish.setSpeakingScore(rs.getString("speaking_score"));
            spouseAbilityEnglish.setPointType(rs.getString("point_type"));
            spouseAbilityEnglish.setPoint(rs.getString("point"));
            spouseAbilityEnglish.setCreatedUser(rs.getString("created_user"));
            spouseAbilityEnglish.setUpdatedUser(rs.getString("updated_user"));
            spouseAbilityEnglish.setCreatedDate(rs.getString("created_date"));
            spouseAbilityEnglish.setUpdatedDate(rs.getString("updated_date"));
            return spouseAbilityEnglish;
        }

    }
}
