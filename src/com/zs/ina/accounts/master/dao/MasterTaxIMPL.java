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
public class MasterTaxIMPL implements MasterTaxDAO {

    static Logger logger = Logger.getLogger(MasterTaxIMPL.class);
    @Autowired
    DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean insertTax(MasterTaxBEAN masterTaxBEAN) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "INSERT INTO " + TableNames.TBL_INV_MSTR_ACCOUNT_TAX + " (\n"
                + "	tax_id,\n"
                + "	tax_name,\n"
                + "	tax_rate,\n"
                + "	is_compound_tax,\n"
                + "	is_delete\n"
                + ")\n"
                + "VALUES\n"
                + "	(?, ?,?, ?, 0);";
        int row = jdbcTemplate.update(sql, new Object[]{masterTaxBEAN.getTaxId(), masterTaxBEAN.getTaxName(),
            masterTaxBEAN.getRate(),Integer.parseInt(masterTaxBEAN.isCompoundTaxProperty().get())
        });
        return (row > 0);
    }

    @Override
    public boolean updateTax(MasterTaxBEAN masterTaxBEAN) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "UPDATE " + TableNames.TBL_INV_MSTR_ACCOUNT_TAX + "\n"
                + " SET \n"
                + " tax_name = ?,\n"
                + " tax_rate = ?,\n"
                + " is_compound_tax = ?,\n"
                + " is_delete = 0\n"
                + " WHERE\n"
                + "	tax_id=?";
        int row = jdbcTemplate.update(sql, new Object[]{masterTaxBEAN.getTaxName(), masterTaxBEAN.getRate(),
           Integer.parseInt(masterTaxBEAN.isCompoundTaxProperty().get()), masterTaxBEAN.getTaxId()
        });
        return (row > 0);

    }

    @Override
    public boolean deleteTax(String rowId) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "DELETE FROM " + TableNames.TBL_INV_MSTR_ACCOUNT_TAX + "\n"
                + " WHERE\n"
                + " tax_id=?";
        int row = jdbcTemplate.update(sql, new Object[]{rowId});
        return (row > 0);
    }

    @Override
    public ObservableList<MasterTaxBEAN> retrieveTax() {
        ObservableList<MasterTaxBEAN> listTax = FXCollections.observableArrayList();
        jdbcTemplate = new JdbcTemplate(dataSource);
        String query = "SELECT * FROM " + TableNames.TBL_INV_MSTR_ACCOUNT_TAX + " ORDER BY tax_name ASC ";
        List<MasterTaxBEAN> temp = jdbcTemplate.query(query, new Object[]{}, new TaxMasterMapper());
        listTax.addAll(temp);
        return listTax;
    }

    @Override
    public boolean checkTaxExists(MasterTaxBEAN masterTaxBEAN) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "DELETE FROM " + TableNames.TBL_INV_MSTR_ACCOUNT_TAX + "\n"
                + " WHERE\n"
                + " tax_id=?";
        int row = jdbcTemplate.update(sql, new Object[]{});
        return (row > 0);
    }

    public class TaxMasterMapper implements RowMapper<MasterTaxBEAN> {

        /**
         * @param rs
         * @param rowNum
         * @return
         * @throws SQLException
         */
        @Override
        public MasterTaxBEAN mapRow(ResultSet rs, int rowNum) throws SQLException {
            MasterTaxBEAN taxBEAN = new MasterTaxBEAN();
            taxBEAN.setTaxId(rs.getString("tax_id"));
            taxBEAN.setTaxName(rs.getString("tax_name"));
            taxBEAN.setRate(rs.getString("tax_rate"));
            taxBEAN.setIsCompoundTax(rs.getString("is_compound_tax"));
            taxBEAN.setIsDelete(rs.getString("is_delete"));
            return taxBEAN;
        }

    }
}
