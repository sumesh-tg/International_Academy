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
package com.zs.ina.search.master.duration.dao;

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
public class DurationIMPL implements DurationDAO {

    Logger logger = Logger.getLogger(DurationIMPL.class);
    @Autowired
    DataSource dataSource;
    private JdbcTemplate mySqlJdbcTemplate;

    /**
     *
     * @param durationBEAN
     * @return
     */
    @Override
    public int insertDuration(DurationBEAN durationBEAN) {
        int row = 0;
        String sqls = "INSERT INTO " + TableNames.TABLE_TRAINING_DURATION + " (\n"
                + "	duration_id,\n"
                + "	duration,\n"
                + "	duration_in_days\n"
                + ")\n"
                + "VALUES\n"
                + "	(\n"
                + "		0,\n"
                + "		?,\n"
                + "		?\n"
                + "	);";
        row = mySqlJdbcTemplate.update(sqls, new Object[]{durationBEAN.getDuration(), durationBEAN.getDurationDays()});
        return row;
    }

    /**
     *
     * @param durationBEAN
     * @return
     */
    @Override
    public int deleteDuration(DurationBEAN durationBEAN) {
        mySqlJdbcTemplate = new JdbcTemplate(dataSource);
        int row = 0;

        String sql = "DELETE FROM " + TableNames.TABLE_TRAINING_DURATION + "\n"
                + "WHERE\n"
                + "	duration_id = ?";
        row = mySqlJdbcTemplate.update(sql, new Object[]{durationBEAN.getDurationId()
        });
        return row;

    }

    /**
     *
     * @param durationBEAN
     * @return
     */
    @Override
    public int updateDuration(DurationBEAN durationBEAN) {
        mySqlJdbcTemplate = new JdbcTemplate(dataSource);
        int row = 0;

        String sql = "UPDATE " + TableNames.TABLE_TRAINING_DURATION + "\n"
                + "SET duration = ?,duration_in_days = ?\n"
                + "WHERE\n"
                + "	duration_id = ?";
        row = mySqlJdbcTemplate.update(sql, new Object[]{durationBEAN.getDuration(),
            durationBEAN.getDurationDays(),
            durationBEAN.getDurationId()
        });

        return row;
    }

    /**
     *
     * @return
     */
    @Override
    public ObservableList<DurationBEAN> retrieveDuration() {
        List<DurationBEAN> listTemp = new ArrayList<>();
        ObservableList<DurationBEAN> listDurations = FXCollections.observableArrayList();
        mySqlJdbcTemplate = new JdbcTemplate(dataSource);
        String query = "SELECT * FROM " + TableNames.TABLE_TRAINING_DURATION + "";
        listTemp = mySqlJdbcTemplate.query(query, new DurationIMPL.DurationMapperTemplates());
        listDurations.addAll(listTemp);
        return listDurations;

    }

    /**
     *
     */
    public class DurationMapperTemplates implements RowMapper<DurationBEAN> {

        /**
         *
         * @param rs
         * @param rowNum
         * @return
         * @throws SQLException
         */
        @Override
        public DurationBEAN mapRow(ResultSet rs, int rowNum) throws SQLException {
            DurationBEAN durationBEAN = new DurationBEAN();
            durationBEAN.setDurationId(rs.getString("duration_id"));
            durationBEAN.setDuration(rs.getString("duration"));
            durationBEAN.setDurationDays(rs.getString("duration_in_days"));
            return durationBEAN;
        }

    }
}
