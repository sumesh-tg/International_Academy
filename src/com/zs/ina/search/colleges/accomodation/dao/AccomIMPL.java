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
package com.zs.ina.search.colleges.accomodation.dao;

import com.zs.ina.search.common.bean.AccomBEAN;
import com.zs.ina.common.constants.TableNames;
import com.zs.ina.search.colleges.campuses.dao.CampusIMPL;
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
public class AccomIMPL implements AccomDAO {

    Logger logger = Logger.getLogger(CampusIMPL.class);
    @Autowired
    DataSource dataSource;
    private JdbcTemplate mySqlJdbcTemplate;

    @Override
    public int insertAccomodation(AccomBEAN accomBEAN) {
        int row = 0;
        String sqls = "INSERT INTO " + TableNames.TBL_SR_EDU_ACCOMODATION + " (\n"
                + "	row_id,\n"
                + "	college_id,\n"
                + "	accomodation\n"
                + ")\n"
                + "VALUES\n"
                + "	(\n"
                + "		?,\n"
                + "		?,\n"
                + "		?\n"
                + "	);";
        row = mySqlJdbcTemplate.update(sqls, new Object[]{accomBEAN.getRowId(),
            accomBEAN.getCollegeId(),
            accomBEAN.getAccomodation()
        });
        return row;

    }

    @Override
    public int updateAccomodation(AccomBEAN accomBEAN) {
        mySqlJdbcTemplate = new JdbcTemplate(dataSource);
        int row = 0;

        String sql = "UPDATE " + TableNames.TBL_SR_EDU_ACCOMODATION + "\n"
                + "SET college_id = ?,accomodation = ?\n"
                + "WHERE\n"
                + "	row_id = ?";
        row = mySqlJdbcTemplate.update(sql, new Object[]{accomBEAN.getCollegeId(),
            accomBEAN.getAccomodation(),
            accomBEAN.getRowId()
        });

        return row;

    }

    @Override
    public ObservableList<AccomBEAN> retrieveAccomodation(String id) {
        mySqlJdbcTemplate = new JdbcTemplate(dataSource);
        List<AccomBEAN> listTemp = new ArrayList<>();
        ObservableList<AccomBEAN> listAccomodations = FXCollections.observableArrayList();
        String query = "SELECT * FROM " + TableNames.TBL_SR_EDU_ACCOMODATION + " WHERE college_id = '" + id + "'";
        listTemp = mySqlJdbcTemplate.query(query, new AccomodationMapperTemplates());
        listAccomodations.addAll(listTemp);
        return listAccomodations;
    }

    @Override
    public boolean deleteAccomodation(String id) {

        int row = 0;
        mySqlJdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "DELETE FROM  " + TableNames.TBL_SR_EDU_ACCOMODATION + "\n"
                + " WHERE\n"
                + "	row_id = ?";
        row = mySqlJdbcTemplate.update(sql, new Object[]{id});
        return (row > 0) ? true : false;

    }

    public class AccomodationMapperTemplates implements RowMapper<AccomBEAN> {

        @Override
        public AccomBEAN mapRow(ResultSet rs, int rowNum) throws SQLException {
            AccomBEAN accomBEAN = new AccomBEAN();
            accomBEAN.setRowId(rs.getString("row_id"));
            accomBEAN.setCollegeId(rs.getString("college_id"));
            accomBEAN.setAccomodation(rs.getString("accomodation"));
            return accomBEAN;
        }

    }
}
