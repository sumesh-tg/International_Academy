/*
 * Copyright (C) 2016 100035
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
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package com.zs.ina.search.master.university.dao;

import com.zs.ina.common.constants.TableNames;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
 * @author 100035
 */
public class UniversityIMPL implements UniversityDAO {

    Logger logger = Logger.getLogger(UniversityIMPL.class);
    @Autowired
    DataSource dataSource;
    private JdbcTemplate mySqlJdbcTemplate;

    /**
     *
     * @return University List
     */
    @Override
    public ObservableList<UniversityBEAN> retreiveUniversities() {

        List<UniversityBEAN> listTemp = new ArrayList<>();
        ObservableList<UniversityBEAN> listUniversity = FXCollections.observableArrayList();
        mySqlJdbcTemplate = new JdbcTemplate(dataSource);
        String query = "SELECT * FROM " + TableNames.TBL_SR_UNIVERSITIES + "";
        listTemp = mySqlJdbcTemplate.query(query, new UniversityMapperTemplates());
        listUniversity.addAll(listTemp);
        return listUniversity;

    }

    /**
     *
     * @param universityBEAN
     * @return integer(success or failure)
     */
    @Override
    public int insertUniversity(UniversityBEAN universityBEAN) {

        mySqlJdbcTemplate = new JdbcTemplate(dataSource);
        int row = 0;
        String sqls = "INSERT INTO " + TableNames.TBL_SR_UNIVERSITIES + " (\n"
                + "	university_id,\n"
                + "	university_name,\n"
                + "	type\n"
                + ")\n"
                + "VALUES\n"
                + "	(\n"
                + "		0,\n"
                + "		?,\n"
                + "		?\n"
                + "	);";
        row = mySqlJdbcTemplate.update(sqls, new Object[]{
            universityBEAN.getUniversity(),
            universityBEAN.getType()
        });
        return row;

    }

    /**
     *
     * @param universityBEAN
     * @return integer(success or failure)
     */
    @Override
    public int deleteUniversity(UniversityBEAN universityBEAN) {

        mySqlJdbcTemplate = new JdbcTemplate(dataSource);
        int row = 0;

        String sql = "DELETE FROM " + TableNames.TBL_SR_UNIVERSITIES + "\n"
                + "WHERE\n"
                + "	university_id = ?";
        row = mySqlJdbcTemplate.update(sql, new Object[]{
            universityBEAN.getUniversityId()
        });
        return row;
    }

    /**
     *
     * @param universityBEAN
     * @return integer(success or failure)
     */
    @Override
    public int updateUniversity(UniversityBEAN universityBEAN) {
        mySqlJdbcTemplate = new JdbcTemplate(dataSource);
        int row = 0;

        String sql = "UPDATE " + TableNames.TBL_SR_UNIVERSITIES + "\n"
                + "SET university_name = ?,type = ?\n"
                + "WHERE\n"
                + "	university_id = ?";
        row = mySqlJdbcTemplate.update(sql, new Object[]{
            universityBEAN.getUniversity(),
            universityBEAN.getType(),
            universityBEAN.getUniversityId()
        });
        return row;
    }

    /**
     *
     */
    public class UniversityMapperTemplates implements RowMapper<UniversityBEAN> {

        /**
         *
         * @param rs
         * @param rowNum
         * @return
         * @throws SQLException
         */
        @Override
        public UniversityBEAN mapRow(ResultSet rs, int rowNum) throws SQLException {
            UniversityBEAN universityBEAN = new UniversityBEAN();
            universityBEAN.setUniversityId(rs.getString("university_id"));
            universityBEAN.setUniversity(rs.getString("university_name"));
            universityBEAN.setType(rs.getString("type"));
            return universityBEAN;
        }

    }

}
