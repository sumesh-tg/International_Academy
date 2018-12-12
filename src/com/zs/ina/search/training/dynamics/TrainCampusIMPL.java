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
package com.zs.ina.search.training.dynamics;

import com.zs.ina.common.constants.TableNames;
import com.zs.ina.search.colleges.campuses.dao.CampusIMPL;
import com.zs.ina.search.common.bean.CampusBEAN;
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
public class TrainCampusIMPL implements TrainCampusDAO {

    Logger logger = Logger.getLogger(TrainCampusIMPL.class);
    @Autowired
    DataSource dataSource;
    private JdbcTemplate mySqlJdbcTemplate;

    @Override
    public boolean insertTrainingCampuses(CampusBEAN campusBEAN) {
        int row = 0;
        String sqls = "INSERT INTO " + TableNames.TBL_TR_COURSE_CAMPUSES + " (\n"
                + "	campus_id,\n"
                + "	training_id,\n"
                + "	campus_name,\n"
                + "	country,\n"
                + "	location,\n"
                + "	contact_address,\n"
                + "	accomodation\n"
                + ")\n"
                + "VALUES\n"
                + "	(\n"
                + "		?,\n"
                + "		?,\n"
                + "		?,\n"
                + "		?,\n"
                + "		?,\n"
                + "		?,\n"
                + "		?\n"
                + "	);";
        row = mySqlJdbcTemplate.update(sqls, new Object[]{campusBEAN.getRowId(),
            campusBEAN.getTrainingId(),
            campusBEAN.getCampus(),
            campusBEAN.getCountry(),
            campusBEAN.getLocation(),
            campusBEAN.getAddress(),
            campusBEAN.getAccomodation()
        });
        return (row > 0) ? true : false;

    }

    @Override
    public boolean updateTrainingCampuses(CampusBEAN campusBEAN) {

        mySqlJdbcTemplate = new JdbcTemplate(dataSource);
        int row = 0;

        String sql = "UPDATE " + TableNames.TBL_TR_COURSE_CAMPUSES + "\n"
                + "SET training_id = ?,campus_name = ?,country = ?,location = ?,contact_address = ?,accomodation = ?\n"
                + "WHERE\n"
                + "	campus_id = ?";
        row = mySqlJdbcTemplate.update(sql, new Object[]{campusBEAN.getTrainingId(),
            campusBEAN.getCampus(),
            campusBEAN.getCountry(),
            campusBEAN.getLocation(),
            campusBEAN.getAddress(),
            campusBEAN.getAccomodation(),
            campusBEAN.getRowId()

        });

        return (row > 0) ? true : false;
    }

    @Override
    public boolean deleteTrainingCampuses(String id) {
        int row = 0;
        mySqlJdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "DELETE FROM  " + TableNames.TBL_TR_COURSE_CAMPUSES + "\n"
                + " WHERE\n"
                + "	campus_id = ?";
        row = mySqlJdbcTemplate.update(sql, new Object[]{id});
        return (row > 0) ? true : false;

    }

    @Override
    public ObservableList<CampusBEAN> retrieveTrainingCampuses(String trainingId) {
        mySqlJdbcTemplate = new JdbcTemplate(dataSource);
        List<CampusBEAN> listTemp = new ArrayList<>();
        ObservableList<CampusBEAN> listCampuses = FXCollections.observableArrayList();
        String query = "SELECT * FROM " + TableNames.TBL_TR_COURSE_CAMPUSES + " WHERE training_id = '" + trainingId + "'";
        listTemp = mySqlJdbcTemplate.query(query, new TrainCampusMapperTemplates());
        listCampuses.addAll(listTemp);
        return listCampuses;

    }

    public class TrainCampusMapperTemplates implements RowMapper<CampusBEAN> {

        @Override
        public CampusBEAN mapRow(ResultSet rs, int rowNum) throws SQLException {
            CampusBEAN campusBEAN = new CampusBEAN();
            campusBEAN.setRowId(rs.getString("campus_id"));
            campusBEAN.setTrainingId(rs.getString("training_id"));
            campusBEAN.setCampus(rs.getString("campus_name"));
            campusBEAN.setCountry(rs.getString("country"));
            campusBEAN.setLocation(rs.getString("location"));
            campusBEAN.setAddress(rs.getString("contact_address"));
            campusBEAN.setAccomodation(rs.getString("accomodation"));
            return campusBEAN;
        }

    }

}
