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
package com.zs.ina.search.employer.fee.dao;

import com.zs.ina.common.constants.TableNames;
import com.zs.ina.search.employer.branch.dao.EmployerBranchBEAN;
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
public class EmployerProcessingFeeIMPL implements EmployerProcessingFeeDAO {

    static Logger logger = Logger.getLogger(EmployerProcessingFeeIMPL.class);
    @Autowired
    DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean insertProcessingFee(EmployerProcessingFeeBEAN processingFeeBEAN) {

        int row = 0;
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "INSERT INTO   " + TableNames.TBL_SR_EMPLYR_PROCESSING_FEE + "  (\n"
                + "	 processing_fee_id ,\n"
                + "	 employer_id ,\n"
                + "	 processing_fee ,\n"
                + "	 currency ,\n"
                + "	 amount \n"
                + ")\n"
                + "VALUES\n"
                + "	(?, ?, ?, ?, ?);";
        row = jdbcTemplate.update(sql, new Object[]{
            processingFeeBEAN.getProcessingFeeId(), processingFeeBEAN.getEmployerId(),
            processingFeeBEAN.getProcessingFee(), processingFeeBEAN.getCurrency(), Double.parseDouble(processingFeeBEAN.getAmount())});
        return (row > 0);

    }

    @Override
    public boolean deleteProcessingFee(String rowId) {

        int row = 0;
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "DELETE FROM " + TableNames.TBL_SR_EMPLYR_PROCESSING_FEE + "\n"
                + " WHERE\n"
                + "processing_fee_id=?";
        row = jdbcTemplate.update(sql, new Object[]{rowId});
        return (row > 0) ? true : false;

    }

    @Override
    public boolean checkProcessingFeeExists(EmployerProcessingFeeBEAN processingFeeBEAN) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateProcessingFee(EmployerProcessingFeeBEAN processingFeeBEAN) {

        int row = 0;
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "UPDATE search_emplyr_processing_fee\n"
                + "SET processing_fee = ?,\n"
                + " currency = ?,\n"
                + " amount = ?\n"
                + "WHERE\n"
                + "	processing_fee_id = ?";
        row = jdbcTemplate.update(sql, new Object[]{processingFeeBEAN.getProcessingFee(),
            processingFeeBEAN.getCurrency(), processingFeeBEAN.getAmount(), processingFeeBEAN.getProcessingFeeId()});
        return (row > 0) ? true : false;

    }

    @Override
    public ObservableList<EmployerProcessingFeeBEAN> retrieveProcessingFee(String employerId) {

        ObservableList<EmployerProcessingFeeBEAN> listContacts = FXCollections.observableArrayList();
        jdbcTemplate = new JdbcTemplate(dataSource);
        String query = "SELECT * FROM " + TableNames.TBL_SR_EMPLYR_PROCESSING_FEE + " WHERE employer_id = ?";
        List<EmployerProcessingFeeBEAN> temp = jdbcTemplate.query(query, new Object[]{employerId}, new ProcessingFeeMapper());
        listContacts.addAll(temp);
        return listContacts;

    }

    public class ProcessingFeeMapper implements RowMapper<EmployerProcessingFeeBEAN> {

        /**
         *
         * @param rs
         * @param rowNum
         * @return
         * @throws SQLException
         */
        @Override
        public EmployerProcessingFeeBEAN mapRow(ResultSet rs, int rowNum) throws SQLException {
            EmployerProcessingFeeBEAN feeBEAN = new EmployerProcessingFeeBEAN();
            feeBEAN.setProcessingFeeId(rs.getString("processing_fee_id"));
            feeBEAN.setEmployerId(rs.getString("employer_id"));
            feeBEAN.setAmount(rs.getString("amount"));
            feeBEAN.setCurrency(rs.getString("currency"));
            feeBEAN.setProcessingFee(rs.getString("processing_fee"));
            return feeBEAN;
        }

    }
}
