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
package com.zs.ina.search.migration.dao;

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
public class MigrationBasicIMPL implements MigrationBasicDAO {

    static Logger logger = Logger.getLogger(MigrationBasicIMPL.class);
    @Autowired
    DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean insertBasicInfo(MigrationBasicBEAN migrationBasicBEAN) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "INSERT INTO " + TableNames.TBL_MIG_POINTS_BASIC_INFO + " (\n"
                + "	migra_provider_id,\n"
                + "	country,sub_class,\n"
                + "	ocupation_cat,\n"
                + "	created_date,\n"
                + "	created_user\n"
                + ")\n"
                + "VALUES\n"
                + "	(?, ?, ?,?, NOW(), ?);";
        int row = jdbcTemplate.update(sql, new Object[]{migrationBasicBEAN.getMigraProviderId(),
            migrationBasicBEAN.getCountry(),
            migrationBasicBEAN.getSubClass(),
            migrationBasicBEAN.getOcupationCat(), migrationBasicBEAN.getCreatedUser()});
        return (row > 0);
    }

    @Override
    public boolean updateBasicInfo(MigrationBasicBEAN migrationBasicBEAN) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "UPDATE " + TableNames.TBL_MIG_POINTS_BASIC_INFO + "\n"
                + "SET sub_class = ?,\n"
                + " ocupation_cat = ?,\n"
                + " updated_date = NOW(),\n"
                + " updated_user = ?\n"
                + "WHERE\n"
                + "	migra_provider_id = ?";
        int row = jdbcTemplate.update(sql, new Object[]{migrationBasicBEAN.getSubClass(),
            migrationBasicBEAN.getOcupationCat(), migrationBasicBEAN.getUpdatedUser(), migrationBasicBEAN.getMigraProviderId()});
        return (row > 0);
    }

    @Override
    public boolean deleteBasicInfo(String rowId) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "DELETE FROM " + TableNames.TBL_MIG_POINTS_BASIC_INFO + "\n"
                + " WHERE\n"
                + "migra_provider_id=?";
        int row = jdbcTemplate.update(sql, new Object[]{rowId});
        return (row > 0);
    }

    @Override
    public boolean existBasicInfo(MigrationBasicBEAN migrationBasicBEAN) {
        return false;
    }

    @Override
    public ObservableList<MigrationBasicBEAN> retrieveBasicInfo(String migrationProviderId) {

        ObservableList<MigrationBasicBEAN> listContacts = FXCollections.observableArrayList();
        jdbcTemplate = new JdbcTemplate(dataSource);
        String query = "SELECT * FROM " + TableNames.TBL_MIG_POINTS_BASIC_INFO + " WHERE migra_provider_id = ?";
        List<MigrationBasicBEAN> temp = jdbcTemplate.query(query, new Object[]{migrationProviderId}, new BasicInfoMapper());
        listContacts.addAll(temp);
        return listContacts;

    }

    @Override
    public MigrationBasicBEAN retrieveBasicInfo(String country, String subclass) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String query = "SELECT * FROM " + TableNames.TBL_MIG_POINTS_BASIC_INFO + " WHERE country = ? and sub_class=?";
        List< MigrationBasicBEAN> listBasicBEAN = jdbcTemplate.query(query, new Object[]{country, subclass}, new BasicInfoMapper());
        if (listBasicBEAN.size() <= 0) {
            MigrationBasicBEAN basicBEAN=new MigrationBasicBEAN();
            basicBEAN.setCountry(country);
            basicBEAN.setSubClass(subclass);
            return basicBEAN;
        } else {
            return listBasicBEAN.get(0);
        }
    }

    @Override
    public ObservableList<MigrationBasicBEAN> retrieveBasicInfo() {
        ObservableList<MigrationBasicBEAN> listContacts = FXCollections.observableArrayList();
        jdbcTemplate = new JdbcTemplate(dataSource);
        String query = "SELECT * FROM " + TableNames.TBL_MIG_POINTS_BASIC_INFO;
        List<MigrationBasicBEAN> temp = jdbcTemplate.query(query, new Object[]{}, new BasicInfoMapper());
        listContacts.addAll(temp);
        return listContacts;

    }

    public class BasicInfoMapper implements RowMapper<MigrationBasicBEAN> {

        /**
         * @param rs
         * @param rowNum
         * @return
         * @throws SQLException
         */
        @Override
        public MigrationBasicBEAN mapRow(ResultSet rs, int rowNum) throws SQLException {
            MigrationBasicBEAN basicBEAN = new MigrationBasicBEAN();
            basicBEAN.setMigraProviderId(rs.getString("migra_provider_id"));
            basicBEAN.setSubClass(rs.getString("sub_class"));
            basicBEAN.setCountry(rs.getString("country"));
            basicBEAN.setOcupationCat(rs.getString("ocupation_cat"));
            basicBEAN.setCreatedDate(rs.getString("created_date"));
            basicBEAN.setUpdatedDate(rs.getString("updated_date"));
            basicBEAN.setCreatedUser(rs.getString("created_user"));
            basicBEAN.setUpdatedUser(rs.getString("updated_user"));
            return basicBEAN;
        }

    }
}
