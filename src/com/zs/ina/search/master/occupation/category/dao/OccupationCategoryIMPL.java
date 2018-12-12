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
package com.zs.ina.search.master.occupation.category.dao;

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
public class OccupationCategoryIMPL implements OccupationCategoryDAO {

    static Logger logger = Logger.getLogger(OccupationCategoryIMPL.class);
    @Autowired
    DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    /**
     *
     * @param occupationCategoryBEAN
     * @return
     */
    @Override
    public int insertOccupationCategory(OccupationCategoryBEAN occupationCategoryBEAN) {
        int row = 0;
        jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "INSERT INTO " + TableNames.TBL_MSTR_OCCUPATION_CAT + " ( id,occupation_cat)\n"
                + " VALUES\n"
                + "	(0, ?);";
        row = jdbcTemplate.update(sql, new Object[]{occupationCategoryBEAN.getOccupationCat()});
        return row;
    }

    /**
     *
     * @param occupationCategoryBEAN
     * @return
     */
    @Override
    public int updateOccupationCategory(OccupationCategoryBEAN occupationCategoryBEAN) {
        int row = 0;
        jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "UPDATE " + TableNames.TBL_MSTR_OCCUPATION_CAT + "\n"
                + "SET \n"
                + " occupation_cat = ?\n"
                + " WHERE\n"
                + "	id=?";
        row = jdbcTemplate.update(sql, new Object[]{occupationCategoryBEAN.getOccupationCat(), occupationCategoryBEAN.getId()});
        return row;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public int deleteOccupationCategory(String id) {
        int row = 0;
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "DELETE FROM " + TableNames.TBL_MSTR_OCCUPATION_CAT
                + " WHERE id = ?\n";
        row = jdbcTemplate.update(sql, new Object[]{id});
        return row;
    }

    /**
     *
     * @return
     */
    @Override
    public ObservableList<OccupationCategoryBEAN> retrieveOccupationCategory() {
        ObservableList<OccupationCategoryBEAN> listSubClasses = FXCollections.observableArrayList();
        jdbcTemplate = new JdbcTemplate(dataSource);
        String query = "SELECT * FROM " + TableNames.TBL_MSTR_OCCUPATION_CAT + "";
        List<OccupationCategoryBEAN> temp = jdbcTemplate.query(query, new OccupationCatMapper());
        listSubClasses.addAll(temp);
        return listSubClasses;
    }

    /**
     *
     */
    public class OccupationCatMapper implements RowMapper<OccupationCategoryBEAN> {

        /**
         *
         * @param rs
         * @param rowNum
         * @return
         * @throws SQLException
         */
        @Override
        public OccupationCategoryBEAN mapRow(ResultSet rs, int rowNum) throws SQLException {
            OccupationCategoryBEAN categoryBEAN = new OccupationCategoryBEAN();
            categoryBEAN.setId(rs.getString("id"));
            categoryBEAN.setOccupationCat(rs.getString("occupation_cat"));
            return categoryBEAN;
        }

    }
}
