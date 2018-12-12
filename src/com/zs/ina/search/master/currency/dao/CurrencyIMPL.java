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
package com.zs.ina.search.master.currency.dao;

import com.zs.ina.common.constants.TableNames;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class CurrencyIMPL implements CurrencyDAO {

    static Logger logger = Logger.getLogger(CurrencyIMPL.class);
    @Autowired
    DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    /**
     *
     * @param currencyBEAN
     * @return
     */
    @Override
    public int insertCurrency(CurrencyBEAN currencyBEAN) {
        int row = 0;
        jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "INSERT INTO " + TableNames.TBL_MSTR_CURRENCY_LIST + " ( id,job_title)\n"
                + "VALUES\n"
                + "	(0, ?);";
        row = jdbcTemplate.update(sql, new Object[]{currencyBEAN.getCurrency()});
        return row;
    }

    /**
     *
     * @param currencyBEAN
     * @return
     */
    @Override
    public int updateCurrency(CurrencyBEAN currencyBEAN) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public int deleteCurrency(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param currencyBEAN
     * @return
     */
    @Override
    public String checkCurrencyExist(CurrencyBEAN currencyBEAN) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "SELECT base_id  FROM currency_remote_base_tbl \n"
                + " WHERE update_date =CURDATE();";
        List<String> listCurrencyId = jdbcTemplate.query(sql, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int i) throws SQLException {
                return rs.getString("base_id");
            }
        });
        if (listCurrencyId.isEmpty()) {
            return null;
        } else {
            return listCurrencyId.get(0);
        }
    }

    @Override
    public int insertBaseCurrency(CurrencyBEAN currencyBEAN) {
        int row = 0;
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "INSERT INTO currency_remote_base_tbl (\n"
                + "	base_id,\n"
                + "	base_currency_code,\n"
                + "	update_date\n"
                + ")\n"
                + "VALUES\n"
                + "	(?, ?, CURDATE());";
        row = jdbcTemplate.update(sql, new Object[]{currencyBEAN.getBaseId(), currencyBEAN.getBaseCurrencyCode()});
        return row;
    }

    @Override
    public int insertCurrencyRates(String baseId, Map<String, String> mapRates) {
        int row = 0;
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "INSERT INTO currency_remote_rates_tbl (\n"
                + "	rate_id,\n"
                + "	base_id,\n"
                + "	currency_code,\n"
                + "	rate\n"
                + ")\n"
                + "VALUES\n"
                + "	(?, ?, ?, ?);";
        System.out.println("Map Size :: " + mapRates.size());
        for (final Map.Entry<String, String> entry : mapRates.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());

            this.jdbcTemplate.update(sql, new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps) throws SQLException {
                    ps.setInt(1, 0);
                    ps.setString(2, baseId);
                    ps.setString(3, entry.getKey());
                    ps.setString(4, entry.getValue());
                }
            });
        }
        return row;
    }

    @Override
    public int updateCurrencyRates(String baseId, Map<String, String> mapRates) {
        int row = 0;
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "UPDATE currency_remote_rates_tbl\n"
                + "SET \n"
                + " rate = ?\n"
                + "WHERE\n"
                + "	base_id = ? AND currency_code=?";
        for (final Map.Entry<String, String> entry : mapRates.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
            row = this.jdbcTemplate.update(sql, new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps) throws SQLException {
                    ps.setString(1, entry.getValue());
                    ps.setString(2, baseId);
                    ps.setString(3, entry.getKey());
                }
            });
        }
        return row;
    }

    /**
     *
     * @return
     */
    @Override
    public ObservableList<CurrencyBEAN> retrieveCurrency() {
        ObservableList<CurrencyBEAN> listCurrency = FXCollections.observableArrayList();
        jdbcTemplate = new JdbcTemplate(dataSource);
        String query = "SELECT * FROM currency_remote_base_tbl crbt\n"
                + "LEFT JOIN currency_remote_rates_tbl crrt on crrt.base_id=crbt.base_id\n"
                + "LEFT JOIN currency_iso_codes_tbl cict on cict.currency_code=crrt.currency_code\n"
                + "ORDER BY crbt.update_date desc";
        List<CurrencyBEAN> temp = jdbcTemplate.query(query, new Object[]{}, new CurrencyMapper());
        listCurrency.addAll(temp);
        return listCurrency;
    }

    public class CurrencyMapper implements RowMapper<CurrencyBEAN> {

        /**
         *
         * @param rs
         * @param rowNum
         * @return
         * @throws SQLException
         */
        @Override
        public CurrencyBEAN mapRow(ResultSet rs, int rowNum) throws SQLException {
            CurrencyBEAN currencyBEAN = new CurrencyBEAN();
            currencyBEAN.setBaseCurrencyCode(rs.getString("base_currency_code"));
            currencyBEAN.setCdate(rs.getString("update_date"));
            currencyBEAN.setId(rs.getString("rate_id"));
            currencyBEAN.setCurrency(rs.getString("currency_code"));
            currencyBEAN.setRate(rs.getString("rate"));
            currencyBEAN.setCurrencyName(rs.getString("currency_name"));
            currencyBEAN.setCountry(rs.getString("used_countries"));
            return currencyBEAN;
        }

    }
}
