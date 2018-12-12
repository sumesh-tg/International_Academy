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
public class ApplicantAbilityEnglishIMPL implements ApplicantAbilityEnglishDAO {

    static Logger logger = Logger.getLogger(ApplicantAbilityEnglishIMPL.class);
    @Autowired
    DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean insertAbilityEnglish(MigrationSkillsBEAN migrationSkillsBEAN) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "INSERT INTO "+TableNames.TBL_MIG_POINTS_ABILITY_ENGLISH+" (\n"
                + "	row_id,\n"
                + "	migra_provider_id,\n"
                + "	application_type,\n"
                + "	language_test,\n"
                + "	reading_score,\n"
                + "	listeing_score,\n"
                + "	writing_score,\n"
                + "	point_type,\n"
                + "	point,\n"
                + "	created_user,\n"
                + "	created_date\n"
                + ")\n"
                + "VALUES\n"
                + "	(?, ?, ?, ?,  ?, ?, ?, ?, ?,?, NOW());";
        int row = jdbcTemplate.update(sql, new Object[]{migrationSkillsBEAN.getRowId(), migrationSkillsBEAN.getMigraProviderId(),
            migrationSkillsBEAN.getApplicationType(), migrationSkillsBEAN.getLanguagTest(), migrationSkillsBEAN.getReadingScore(), migrationSkillsBEAN.getListeingScore(), migrationSkillsBEAN.getWritingScore(), 
            migrationSkillsBEAN.getPointType(),migrationSkillsBEAN.getPoint(), migrationSkillsBEAN.getCreatedUser()
        });
        return (row > 0);
    }

    @Override
    public boolean updateAbilityEnglish(MigrationSkillsBEAN migrationSkillsBEAN) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "UPDATE "+TableNames.TBL_MIG_POINTS_ABILITY_ENGLISH+"\n"
                + "SET application_type =   ?,\n"
                + " language_test =   ?,\n"
                + " reading_score =   ?,\n"
                + " listeing_score =   ?,\n"
                + " writing_score =   ?,\n"
                + " point_type =   ?,\n"
                + " point =   ?,\n"
                + " created_user =   ?,\n"
                + " created_date =   NOW()\n"
                + "WHERE\n"
                + "	row_id=  ?";
        int row = jdbcTemplate.update(sql, new Object[]{
            migrationSkillsBEAN.getApplicationType(), migrationSkillsBEAN.getLanguagTest(), migrationSkillsBEAN.getReadingScore(), migrationSkillsBEAN.getListeingScore(), migrationSkillsBEAN.getWritingScore(), 
            migrationSkillsBEAN.getPointType(),migrationSkillsBEAN.getPoint(), migrationSkillsBEAN.getUpdatedUser(), migrationSkillsBEAN.getRowId()
        });
        return (row > 0);
    }

    @Override
    public boolean deleteAbilityEnglish(String rowId) {
               jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "DELETE FROM "+TableNames.TBL_MIG_POINTS_ABILITY_ENGLISH+"\n"
                + " WHERE\n"
                + " row_id=?";
        int row = jdbcTemplate.update(sql, new Object[]{rowId});
        return (row > 0);
    }

    @Override
    public boolean existsAbilityEnglish(MigrationSkillsBEAN migrationSkillsBEAN) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<MigrationSkillsBEAN> retrieveAbilityEnglish(String migProviderId) {
        
        ObservableList<MigrationSkillsBEAN> listAbilityEnglish = FXCollections.observableArrayList();
        jdbcTemplate = new JdbcTemplate(dataSource);
        String query = "SELECT * FROM " + TableNames.TBL_MIG_POINTS_ABILITY_ENGLISH + " WHERE migra_provider_id = ?";
        List<MigrationSkillsBEAN> temp = jdbcTemplate.query(query, new Object[]{migProviderId}, new AbilityEnglishMapper());
        listAbilityEnglish.addAll(temp);
        return listAbilityEnglish;
    
    }
  public class AbilityEnglishMapper implements RowMapper<MigrationSkillsBEAN> {

        /**
         *
         * @param rs
         * @param rowNum
         * @return
         * @throws SQLException
         */
        @Override
        public MigrationSkillsBEAN mapRow(ResultSet rs, int rowNum) throws SQLException {
            MigrationSkillsBEAN englishAbilityBEAN = new MigrationSkillsBEAN();
            englishAbilityBEAN.setRowId(rs.getString("row_id"));
            englishAbilityBEAN.setMigraProviderId(rs.getString("migra_provider_id"));
            englishAbilityBEAN.setApplicationType(rs.getString("application_type"));
            englishAbilityBEAN.setLanguagTest(rs.getString("language_test"));
            englishAbilityBEAN.setReadingScore(rs.getString("reading_score"));
            englishAbilityBEAN.setListeingScore(rs.getString("listeing_score"));
            englishAbilityBEAN.setWritingScore(rs.getString("writing_score"));
            englishAbilityBEAN.setPointType(rs.getString("point_type"));
            englishAbilityBEAN.setPoint(rs.getString("point"));
            englishAbilityBEAN.setCreatedUser(rs.getString("created_user"));
            englishAbilityBEAN.setUpdatedUser(rs.getString("updated_user"));
            englishAbilityBEAN.setCreatedDate(rs.getString("created_date"));
            englishAbilityBEAN.setUpdatedDate(rs.getString("updated_date"));
            return englishAbilityBEAN;
        }

    }
}
