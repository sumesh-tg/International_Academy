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
package com.zs.ina.search.master.feetype.dao;

import com.zs.ina.common.constants.TableNames;
import com.zs.ina.registration.documents.dao.DocumentVerifyIMPL;
import com.zs.ina.search.master.jobtitle.dao.JobTitleBEAN;
import com.zs.ina.search.master.jobtitle.dao.JobTitleIMPL;
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
public class FeeTypeIMPL implements FeeTypeDAO {

    static Logger logger = Logger.getLogger(DocumentVerifyIMPL.class);
    @Autowired
    DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    /**
     *
     * @param feeTypeBEAN
     * @return
     */
    @Override
    public int insertFeeType(FeeTypeBEAN feeTypeBEAN) {
        int row = 0;
        jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "INSERT INTO " + TableNames.TBL_MSTR_FEE_TYPE + " (id, fee_type)\n"
                + "VALUES\n"
                + "	(0, ?);";
        row = jdbcTemplate.update(sql, new Object[]{feeTypeBEAN.getFeeType()});
        return row;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public int deleteFeeType(String id) {
        int row = 0;
        jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "DELETE FROM " + TableNames.TBL_MSTR_FEE_TYPE
                + " WHERE id = ?\n";
        row = jdbcTemplate.update(sql, new Object[]{id});
        return row;
    }

    /**
     *
     * @param feeTypeBEAN
     * @return
     */
    @Override
    public int updateFeeType(FeeTypeBEAN feeTypeBEAN) {
        int row = 0;
        jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "UPDATE " + TableNames.TBL_MSTR_FEE_TYPE + "\n"
                + " SET \n"
                + " fee_type = ?\n"
                + " WHERE\n"
                + "	id=?";
        row = jdbcTemplate.update(sql, new Object[]{feeTypeBEAN.getFeeType(), feeTypeBEAN.getId()});
        return row;
    }

    /**
     *
     * @return
     */
    @Override
    public ObservableList<FeeTypeBEAN> retrieveFeeType() {
        ObservableList<FeeTypeBEAN> listFeeType = FXCollections.observableArrayList();
        jdbcTemplate = new JdbcTemplate(dataSource);
        String query = "SELECT * FROM " + TableNames.TBL_MSTR_FEE_TYPE + "";
        List<FeeTypeBEAN> temp = jdbcTemplate.query(query, new FeeTypeMapper());
        listFeeType.addAll(temp);
        return listFeeType;
    }

    /**
     *
     */
    public class FeeTypeMapper implements RowMapper<FeeTypeBEAN> {

        /**
         *
         * @param rs
         * @param rowNum
         * @return
         * @throws SQLException
         */
        @Override
        public FeeTypeBEAN mapRow(ResultSet rs, int rowNum) throws SQLException {
            FeeTypeBEAN feeTypeBEAN = new FeeTypeBEAN();
            feeTypeBEAN.setId(rs.getString("id"));
            feeTypeBEAN.setFeeType(rs.getString("fee_type"));
            return feeTypeBEAN;
        }

    }
}
