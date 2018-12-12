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
public class TrainInstallmentIMPL implements TrainInstallmentDAO {

    Logger logger = Logger.getLogger(TrainInstallmentIMPL.class);
    @Autowired
    DataSource dataSource;
    private JdbcTemplate mySqlJdbcTemplate;

    @Override
    public boolean insertInstallment(TrainInstallmentBEAN installmentBEAN) {
        int row = 0;
        String sqls = "INSERT INTO " + TableNames.TBL_TR_COURSEFEE_SCHEDULE + " (\n"
                + "	fee_schedule_id,\n"
                + "	training_id,\n"
                + "	duration,\n"
                + "	amount\n"
                + ")\n"
                + "VALUES\n"
                + "	(\n"
                + "		?,\n"
                + "		?,\n"
                + "		?,\n"
                + "		?\n"
                + "	);";
        row = mySqlJdbcTemplate.update(sqls, new Object[]{installmentBEAN.getRowId(),
            installmentBEAN.getTrainingId(),
            installmentBEAN.getDuration(),
            installmentBEAN.getAmount()
        });
        return (row > 0) ? true : false;
    }

    @Override
    public boolean updateInstallment(TrainInstallmentBEAN installmentBEAN) {
        mySqlJdbcTemplate = new JdbcTemplate(dataSource);
        int row = 0;

        String sql = "UPDATE " + TableNames.TBL_TR_COURSEFEE_SCHEDULE + "\n"
                + "SET training_id = ?,duration = ?,amount = ?\n"
                + "WHERE\n"
                + "	fee_schedule_id = ?";
        row = mySqlJdbcTemplate.update(sql, new Object[]{installmentBEAN.getTrainingId(),
            installmentBEAN.getDuration(),
            installmentBEAN.getAmount(),
            installmentBEAN.getRowId()

        });

        return (row > 0) ? true : false;

    }

    @Override
    public boolean deleteInstallmentDetails(String id) {
        int row = 0;
        mySqlJdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "DELETE FROM  " + TableNames.TBL_TR_COURSEFEE_SCHEDULE + "\n"
                + " WHERE\n"
                + "	fee_schedule_id = ?";
        row = mySqlJdbcTemplate.update(sql, new Object[]{id});
        return (row > 0) ? true : false;

    }

    @Override
    public ObservableList<TrainInstallmentBEAN> retrieveInstallment(String id) {
        mySqlJdbcTemplate = new JdbcTemplate(dataSource);
        List<TrainInstallmentBEAN> listTemp = new ArrayList<>();
        ObservableList<TrainInstallmentBEAN> listContacts = FXCollections.observableArrayList();
        String query = "SELECT * FROM " + TableNames.TBL_TR_COURSEFEE_SCHEDULE + " WHERE training_id = '" + id + "'";
        listTemp = mySqlJdbcTemplate.query(query, new InstallmentMapperTemplates());
        listContacts.addAll(listTemp);
        return listContacts;
    }

    public class InstallmentMapperTemplates implements RowMapper<TrainInstallmentBEAN> {

        @Override
        public TrainInstallmentBEAN mapRow(ResultSet rs, int rowNum) throws SQLException {
            TrainInstallmentBEAN installmentBEAN = new TrainInstallmentBEAN();
            installmentBEAN.setRowId(rs.getString("fee_schedule_id"));
            installmentBEAN.setTrainingId(rs.getString("training_id"));
            installmentBEAN.setDuration(rs.getString("duration"));
            installmentBEAN.setAmount(rs.getString("amount"));
            return installmentBEAN;
        }

    }

}
