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
package com.zs.ina.accounts.master.dao;

import com.zs.ina.common.constants.TableNames;
import com.zs.ina.search.employer.branch.dao.EmployerBranchIMPL;
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
public class PaymentTermsIMPL implements PaymentTermsDAO {

    static Logger logger = Logger.getLogger(EmployerBranchIMPL.class);
    @Autowired
    DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean insertPaymentTerms(PaymentTermsBEAN paymentTermsBEAN) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "INSERT INTO " + TableNames.TBL_INV_MSTR_ACCOUNT_PAYMENT_TERMS + " (\n"
                + "	terms_id,\n"
                + "	show_it_as,\n"
                + "	number_of_days,\n"
                + "	is_delete\n"
                + ")\n"
                + "VALUES\n"
                + "	(?, ?, ?, ?);";
        int row = jdbcTemplate.update(sql, new Object[]{paymentTermsBEAN.getTermsId(), paymentTermsBEAN.getShowItAs(),
            paymentTermsBEAN.getNumberOfDays(), paymentTermsBEAN.getIsDelete()});
        return (row > 0);
    }

    @Override
    public boolean updatePaymentTerms(PaymentTermsBEAN paymentTermsBEAN) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "UPDATE " + TableNames.TBL_INV_MSTR_ACCOUNT_PAYMENT_TERMS + "\n"
                + "SET show_it_as = ?, number_of_days = ?, is_delete = ?\n"
                + " WHERE\n"
                + "	terms_id =?;";
        int row = jdbcTemplate.update(sql, new Object[]{paymentTermsBEAN.getShowItAs(),
            paymentTermsBEAN.getNumberOfDays(), paymentTermsBEAN.getIsDelete(), paymentTermsBEAN.getTermsId()});
        return (row > 0);
    }

    @Override
    public boolean deletePaymentTerms(String rowId) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "DELETE FROM " + TableNames.TBL_INV_MSTR_ACCOUNT_PAYMENT_TERMS + "\n"
                + " WHERE\n"
                + " terms_id=?";
        int row = jdbcTemplate.update(sql, new Object[]{rowId});
        return (row > 0);
    }

    @Override
    public ObservableList<PaymentTermsBEAN> retrievePaymentTerms() {
        ObservableList<PaymentTermsBEAN> listContacts = FXCollections.observableArrayList();
        jdbcTemplate = new JdbcTemplate(dataSource);
        String query = "SELECT * FROM " + TableNames.TBL_INV_MSTR_ACCOUNT_PAYMENT_TERMS + " ORDER BY show_it_as ASC ";
        List<PaymentTermsBEAN> temp = jdbcTemplate.query(query, new Object[]{}, new PaymentTermsMapper());
        listContacts.addAll(temp);
        return listContacts;

    }

    @Override
    public boolean checkExistsPaymentTerms(PaymentTermsBEAN paymentTermsBEAN, String curRowId) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = null;
        if (curRowId == null) {
            sql = "SELECT\n"
                    + "	count(*)\n"
                    + "FROM\n"
                    + "	account_master_payment_terms_tbl\n"
                    + "WHERE\n"
                    + "	show_it_as = ?\n"
                    + " OR number_of_days = ?;";
        } else {
            sql = "SELECT\n"
                    + "	count(*)\n"
                    + "FROM\n"
                    + "	account_master_payment_terms_tbl\n"
                    + "WHERE\n"
                    + "	(show_it_as = ?\n"
                    + "OR number_of_days = ?) AND terms_id <> '" + curRowId + "';";
        }

        int row = jdbcTemplate.queryForObject(sql, new Object[]{paymentTermsBEAN.getShowItAs(), paymentTermsBEAN.getNumberOfDays()}, Integer.class);
        return (row > 0);
    }

    public class PaymentTermsMapper implements RowMapper<PaymentTermsBEAN> {

        /**
         *
         * @param rs
         * @param rowNum
         * @return
         * @throws SQLException
         */
        @Override
        public PaymentTermsBEAN mapRow(ResultSet rs, int rowNum) throws SQLException {
            PaymentTermsBEAN bEAN = new PaymentTermsBEAN();
            bEAN.setTermsId(rs.getString("terms_id"));
            bEAN.setShowItAs(rs.getString("show_it_as"));
            bEAN.setNumberOfDays(rs.getString("number_of_days"));
            bEAN.setIsDelete(rs.getString("is_delete"));
            return bEAN;
        }

    }
}
