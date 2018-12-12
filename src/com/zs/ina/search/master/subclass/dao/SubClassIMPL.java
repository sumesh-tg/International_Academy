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
package com.zs.ina.search.master.subclass.dao;

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
public class SubClassIMPL implements SubClassDAO {

    static Logger logger = Logger.getLogger(SubClassIMPL.class);
    @Autowired
    DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    /**
     *
     * @param subClassBEAN
     * @return
     */
    @Override
    public int insertSubClass(SubClassBEAN subClassBEAN) {
        int row = 0;
        jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "INSERT INTO " + TableNames.TBL_MSTR_SUB_CLASS + " ( id,sub_class)\n"
                + " VALUES\n"
                + "	(0, ?);";
        row = jdbcTemplate.update(sql, new Object[]{subClassBEAN.getSubClass()});
        return row;
    }

    /**
     *
     * @param subClassBEAN
     * @return
     */
    @Override
    public int updateSubClass(SubClassBEAN subClassBEAN) {
        int row = 0;
        jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "UPDATE " + TableNames.TBL_MSTR_SUB_CLASS + "\n"
                + "SET \n"
                + " sub_class = ?\n"
                + " WHERE\n"
                + "	id=?";
        row = jdbcTemplate.update(sql, new Object[]{subClassBEAN.getSubClass(), subClassBEAN.getId()});
        return row;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public int deleteSubClass(String id) {
        int row = 0;
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "DELETE FROM " + TableNames.TBL_MSTR_SUB_CLASS
                + " WHERE id = ?\n";
        row = jdbcTemplate.update(sql, new Object[]{id});
        return row;
    }

    /**
     *
     * @return
     */
    @Override
    public ObservableList<SubClassBEAN> retrieveSubClass() {
        ObservableList<SubClassBEAN> listSubClasses = FXCollections.observableArrayList();
        jdbcTemplate = new JdbcTemplate(dataSource);
        String query = "SELECT * FROM " + TableNames.TBL_MSTR_SUB_CLASS + "";
        List<SubClassBEAN> temp = jdbcTemplate.query(query, new SubClassMapper());
        listSubClasses.addAll(temp);
        return listSubClasses;
    }

    /**
     *
     */
    public class SubClassMapper implements RowMapper<SubClassBEAN> {

        /**
         *
         * @param rs
         * @param rowNum
         * @return
         * @throws SQLException
         */
        @Override
        public SubClassBEAN mapRow(ResultSet rs, int rowNum) throws SQLException {
            SubClassBEAN subClassBEAN = new SubClassBEAN();
            subClassBEAN.setId(rs.getString("id"));
            subClassBEAN.setSubClass(rs.getString("sub_class"));
            return subClassBEAN;
        }

    }
}
