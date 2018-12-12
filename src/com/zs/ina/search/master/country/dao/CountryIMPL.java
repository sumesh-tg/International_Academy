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
package com.zs.ina.search.master.country.dao;

import com.zs.ina.common.constants.TableNames;
import com.zs.ina.search.master.university.dao.UniversityBEAN;
import com.zs.ina.search.master.university.dao.UniversityIMPL;
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
public class CountryIMPL implements CountryDAO {

    Logger logger = Logger.getLogger(CountryIMPL.class);
    @Autowired
    DataSource dataSource;
    private JdbcTemplate mySqlJdbcTemplate;

    @Override
    public int insertCountry(CountryBEAN countryBEAN) {
  mySqlJdbcTemplate = new JdbcTemplate(dataSource);
        int row = 0;
        String sqls = "INSERT INTO " + TableNames.TBL_SR_COUNTRIES + " (\n"
                + "	country_id,\n"
                + "	country\n"
                + ")\n"
                + "VALUES\n"
                + "	(\n"
                + "		0,\n"
                + "		?\n"
                + "	);";
        row = mySqlJdbcTemplate.update(sqls, new Object[]{
            countryBEAN.getCountry()
        });
        return row;

    }

    @Override
    public int updateCountry(CountryBEAN countryBEAN) {
  mySqlJdbcTemplate = new JdbcTemplate(dataSource);
        int row = 0;

        String sql = "UPDATE " + TableNames.TBL_SR_COUNTRIES + "\n"
                + "SET country = ?\n"
                + "WHERE\n"
                + "	country_id = ?";
        row = mySqlJdbcTemplate.update(sql, new Object[]{
            countryBEAN.getCountry(),
            countryBEAN.getCountryId()
        });
        return row;
    }

    @Override
    public ObservableList<CountryBEAN> retreiveCountries() {

 List<CountryBEAN> listTemp = new ArrayList<>();
        ObservableList<CountryBEAN> listCountry = FXCollections.observableArrayList();
        mySqlJdbcTemplate = new JdbcTemplate(dataSource);
        String query = "SELECT * FROM " + TableNames.TBL_SR_COUNTRIES + "";
        listTemp = mySqlJdbcTemplate.query(query, new CountryMapperTemplates());
        listCountry.addAll(listTemp);
        return listCountry;
    }
public class CountryMapperTemplates implements RowMapper<CountryBEAN> {

        /**
         *
         * @param rs
         * @param rowNum
         * @return
         * @throws SQLException
         */
        @Override
        public CountryBEAN mapRow(ResultSet rs, int rowNum) throws SQLException {
            CountryBEAN countryBEAN = new CountryBEAN();
            countryBEAN.setCountryId(rs.getString("country_id"));
            countryBEAN.setCountry(rs.getString("country"));
            return countryBEAN;
        }

    }

}
