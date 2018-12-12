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
package com.zs.ina.search.master.intake.dao;

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
public class IntakeIMPL implements IntakeDAO {

    Logger logger = Logger.getLogger(IntakeIMPL.class);
    @Autowired
    DataSource dataSource;
    private JdbcTemplate mySqlJdbcTemplate;

    /**
     *
     * @param intakeBEAN
     * @return
     */
    @Override
    public int insertIntake(IntakeBEAN intakeBEAN) {
        int row = 0;
        String sqls = "INSERT INTO " + TableNames.TABLE_INTAKES + " (\n"
                + "	intake_id,\n"
                + "	intake\n"
                + ")\n"
                + "VALUES\n"
                + "	(\n"
                + "		0,\n"
                + "		?\n"
                + "	);";
        row = mySqlJdbcTemplate.update(sqls, new Object[]{intakeBEAN.getIntake()});
        return row;
    }

    /**
     *
     * @param intakeBEAN
     * @return
     */
    @Override
    public int updateIntake(IntakeBEAN intakeBEAN) {
        mySqlJdbcTemplate = new JdbcTemplate(dataSource);
        int row = 0;

        String sql = "UPDATE " + TableNames.TABLE_INTAKES + "\n"
                + "SET intake = ?\n"
                + "WHERE\n"
                + "	intake_id = ?";
        row = mySqlJdbcTemplate.update(sql, new Object[]{intakeBEAN.getIntake(),
            intakeBEAN.getIntakeId()
        });

        return row;
    }

    /**
     *
     * @return
     */
    @Override
    public ObservableList<IntakeBEAN> retrieveIntakes() {
        List<IntakeBEAN> listTemp = new ArrayList<>();
        ObservableList<IntakeBEAN> listIntakes = FXCollections.observableArrayList();
        mySqlJdbcTemplate = new JdbcTemplate(dataSource);
        String query = "SELECT * FROM " + TableNames.TABLE_INTAKES + "";
        listTemp = mySqlJdbcTemplate.query(query, new IntakeIMPL.IntakeMapperTemplates());
        listIntakes.addAll(listTemp);
        return listIntakes;
    }

    /**
     *
     * @param intakeBEAN
     * @return
     */
    @Override
    public int deleteIntake(IntakeBEAN intakeBEAN) {
        mySqlJdbcTemplate = new JdbcTemplate(dataSource);
        int row = 0;

        String sql = "DELETE FROM " + TableNames.TABLE_INTAKES + "\n"
                + "WHERE\n"
                + "	intake_id = ?";
        row = mySqlJdbcTemplate.update(sql, new Object[]{intakeBEAN.getIntakeId()
        });
        return row;

    }

    /**
     *
     */
    public class IntakeMapperTemplates implements RowMapper<IntakeBEAN> {

        /**
         *
         * @param rs
         * @param rowNum
         * @return
         * @throws SQLException
         */
        @Override
        public IntakeBEAN mapRow(ResultSet rs, int rowNum) throws SQLException {
            IntakeBEAN intakeBEAN = new IntakeBEAN();
            intakeBEAN.setIntakeId(rs.getString("intake_id"));
            intakeBEAN.setIntake(rs.getString("intake"));
            return intakeBEAN;
        }

    }

}
