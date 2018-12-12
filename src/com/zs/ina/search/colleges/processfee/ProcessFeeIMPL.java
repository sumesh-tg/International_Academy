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
package com.zs.ina.search.colleges.processfee;

import com.zs.ina.search.common.bean.ProcessFeeBEAN;
import com.zs.ina.common.constants.TableNames;
import com.zs.ina.search.colleges.dao.EduProviderIMPL;
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
public class ProcessFeeIMPL implements ProcessFeeDAO {

    Logger logger = Logger.getLogger(EduProviderIMPL.class);
    @Autowired
    DataSource dataSource;
    private JdbcTemplate mySqlJdbcTemplate;

    @Override
    public int insertProcessFee(ProcessFeeBEAN procesFeeBEAN) {
        int row = 0;
        String sqls = "INSERT INTO " + TableNames.TBL_SR_EDU_COLLEGE_PROCESSING_FEE + " (\n"
                + "	processing_fee_id,\n"
                + "	college_id,\n"
                + "	currency,\n"
                + "	fee_type,\n"
                + "	amount\n"
                + ")\n"
                + "VALUES\n"
                + "	(\n"
                + "		?,\n"
                + "		?,\n"
                + "		?,\n"
                + "		?,\n"
                + "		?\n"
                + "	);";
        row = mySqlJdbcTemplate.update(sqls, new Object[]{procesFeeBEAN.getRowId(),
            procesFeeBEAN.getCollegeId(),
            procesFeeBEAN.getCurrency(),
            procesFeeBEAN.getFeeType(),
            procesFeeBEAN.getAmount()
        });
        return row;

    }

    @Override
    public int updateProcessFee(ProcessFeeBEAN procesFeeBEAN) {
        mySqlJdbcTemplate = new JdbcTemplate(dataSource);
        int row = 0;

        String sql = "UPDATE " + TableNames.TBL_SR_EDU_COLLEGE_PROCESSING_FEE + "\n"
                + "SET college_id = ?,currency = ?,fee_type = ?,amount = ?\n"
                + "WHERE\n"
                + "	processing_fee_id = ?";
        row = mySqlJdbcTemplate.update(sql, new Object[]{procesFeeBEAN.getCollegeId(),
            procesFeeBEAN.getCurrency(),
            procesFeeBEAN.getFeeType(),
            procesFeeBEAN.getAmount(),
            procesFeeBEAN.getRowId()
        });

        return row;
    }

    @Override
    public ObservableList<ProcessFeeBEAN> retrieveProcessFee(String id) {
        mySqlJdbcTemplate = new JdbcTemplate(dataSource);
        List<ProcessFeeBEAN> listTemp = new ArrayList<>();
        ObservableList<ProcessFeeBEAN> listProcessFee = FXCollections.observableArrayList();
        String query = "SELECT * FROM " + TableNames.TBL_SR_EDU_COLLEGE_PROCESSING_FEE + " WHERE college_id = '" + id + "'";
        listTemp = mySqlJdbcTemplate.query(query, new ProcessFeeMapperTemplates());
        listProcessFee.addAll(listTemp);
        return listProcessFee;
    }

    @Override
    public boolean deleteCampuses(String id) {
        int row = 0;
        mySqlJdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "DELETE FROM  " + TableNames.TBL_SR_EDU_COLLEGE_PROCESSING_FEE + "\n"
                + " WHERE\n"
                + "	processing_fee_id = ?";
        row = mySqlJdbcTemplate.update(sql, new Object[]{id});
        return (row > 0) ? true : false;
    }

    public class ProcessFeeMapperTemplates implements RowMapper<ProcessFeeBEAN> {

        @Override
        public ProcessFeeBEAN mapRow(ResultSet rs, int rowNum) throws SQLException {
            ProcessFeeBEAN processFeeBEAN = new ProcessFeeBEAN();
            processFeeBEAN.setRowId(rs.getString("processing_fee_id"));
            processFeeBEAN.setCollegeId(rs.getString("college_id"));
            processFeeBEAN.setCurrency(rs.getString("currency"));
            processFeeBEAN.setFeeType(rs.getString("fee_type"));
            processFeeBEAN.setAmount(rs.getString("amount"));
            return processFeeBEAN;
        }

    }

}
