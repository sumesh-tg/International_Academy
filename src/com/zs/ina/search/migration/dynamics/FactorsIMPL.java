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
public class FactorsIMPL implements FactorsDAO {

    static Logger logger = Logger.getLogger(FactorsIMPL.class);
    @Autowired
    DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean insertFactors(MigrationSkillsBEAN migrationSkillsBEAN) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "INSERT INTO " + TableNames.TBL_MIG_POINTS_FACTORS + " (\n"
                + "	row_id,\n"
                + "	migra_provider_id,\n"
                + "	factors,\n"
                + "	eligibility_point,\n"
                + "	select_point,\n"
                + "	created_user,\n"
                + "	created_date\n"
                + ")\n"
                + "VALUES\n"
                + "	(?, ?, ?, ?, ?, ?, NOW());";
        int row = jdbcTemplate.update(sql, new Object[]{migrationSkillsBEAN.getRowId(), migrationSkillsBEAN.getMigraProviderId(),
            migrationSkillsBEAN.getFactor(), migrationSkillsBEAN.getEligibilityPoint(), migrationSkillsBEAN.getSelectPointSingle(),
            migrationSkillsBEAN.getCreatedUser()
        });
        return (row > 0);
    }

    @Override
    public boolean updateFactors(MigrationSkillsBEAN migrationSkillsBEAN) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "UPDATE " + TableNames.TBL_MIG_POINTS_FACTORS + "\n"
                + "SET factors =  ?,\n"
                + " eligibility_point =  ?,\n"
                + " select_point =  ?,\n"
                + " updated_user =  ?,\n"
                + " updated_date =  NOW()\n"
                + " WHERE\n"
                + "	row_id =  ?";
        int row = jdbcTemplate.update(sql, new Object[]{
            migrationSkillsBEAN.getFactor(), migrationSkillsBEAN.getEligibilityPoint(), migrationSkillsBEAN.getSelectPointSingle(),
            migrationSkillsBEAN.getCreatedUser(), migrationSkillsBEAN.getRowId()
        });
        return (row > 0);
    }

    @Override
    public boolean deleteFactors(String rowId) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "DELETE FROM " + TableNames.TBL_MIG_POINTS_FACTORS + "\n"
                + " WHERE\n"
                + "row_id=?";
        int row = jdbcTemplate.update(sql, new Object[]{rowId});
        return (row > 0);
    }

    @Override
    public boolean existsFactors(MigrationSkillsBEAN migrationSkillsBEAN) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<MigrationSkillsBEAN> retrieveFactors(String migrProviderId) {

        ObservableList<MigrationSkillsBEAN> listContacts = FXCollections.observableArrayList();
        jdbcTemplate = new JdbcTemplate(dataSource);
        String query = "SELECT * FROM " + TableNames.TBL_MIG_POINTS_FACTORS + " WHERE migra_provider_id = ?";
        List<MigrationSkillsBEAN> temp = jdbcTemplate.query(query, new Object[]{migrProviderId}, new FactorsMapper());
        listContacts.addAll(temp);
        return listContacts;

    }

    public class FactorsMapper implements RowMapper<MigrationSkillsBEAN> {

        /**
         *
         * @param rs
         * @param rowNum
         * @return
         * @throws SQLException
         */
        @Override
        public MigrationSkillsBEAN mapRow(ResultSet rs, int rowNum) throws SQLException {
            MigrationSkillsBEAN factorBEAN = new MigrationSkillsBEAN();
            factorBEAN.setRowId(rs.getString("row_id"));
            factorBEAN.setMigraProviderId(rs.getString("migra_provider_id"));
            factorBEAN.setFactor(rs.getString("factors"));
            factorBEAN.setEligibilityPoint(rs.getString("eligibility_point"));
            factorBEAN.setSelectPointSingle(rs.getString("select_point"));
            factorBEAN.setCreatedUser(rs.getString("created_user"));
            factorBEAN.setCreatedUser(rs.getString("created_user"));
            factorBEAN.setUpdatedUser(rs.getString("updated_user"));
            factorBEAN.setUpdatedDate(rs.getString("updated_date"));
            return factorBEAN;
        }

    }
}
