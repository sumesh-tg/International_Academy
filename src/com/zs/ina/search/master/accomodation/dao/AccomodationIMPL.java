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
package com.zs.ina.search.master.accomodation.dao;

import com.zs.ina.common.constants.TableNames;
import com.zs.ina.utility.ClosingResourcesInDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import zs.com.ina.db.mysql.pool.DBPool;

/**
 *
 * @author 100035
 */
public class AccomodationIMPL implements AccomodationDAO {

    Logger logger = Logger.getLogger(AccomodationIMPL.class);
    @Autowired
    DataSource dataSource;
    private JdbcTemplate mySqlJdbcTemplate;

    /**
     *
     * @param accomodationBEAN
     * @return
     */
    @Override
    public int insertAcccomodation(AccomodationBEAN accomodationBEAN) {
        mySqlJdbcTemplate = new JdbcTemplate(dataSource);
        int row = 0;
        String sqls = "INSERT INTO " + TableNames.TABLE_ACCOMODATION + " (\n"
                + "	accomodation_id,\n"
                + "	accomodation\n"
                + ")\n"
                + "VALUES\n"
                + "	(\n"
                + "		0,\n"
                + "		?\n"
                + "	);";
        row = mySqlJdbcTemplate.update(sqls, new Object[]{accomodationBEAN.getAccomodation()});
        return row;
    }

    /**
     *
     * @param accomodationBEAN
     * @return
     */
    @Override
    public int updateAcccomodation(AccomodationBEAN accomodationBEAN) {
        mySqlJdbcTemplate = new JdbcTemplate(dataSource);
        int row = 0;

        String sql = "UPDATE " + TableNames.TABLE_ACCOMODATION + "\n"
                + "SET accomodation = ?\n"
                + "WHERE\n"
                + "	accomodation_id = ?";
        row = mySqlJdbcTemplate.update(sql, new Object[]{accomodationBEAN.getAccomodation(),
            accomodationBEAN.getAccomodationId()
        });

        return row;
    }

    /**
     *
     * @return
     */
    @Override
    public ObservableList<AccomodationBEAN> retrieveAcccomodation() {
        ObservableList<AccomodationBEAN> listAccomodations=FXCollections.observableArrayList();
        List<AccomodationBEAN> listTemp = new ArrayList<>();
        mySqlJdbcTemplate = new JdbcTemplate(dataSource);
        String query = "SELECT * FROM " + TableNames.TABLE_ACCOMODATION + "";
        listTemp = mySqlJdbcTemplate.query(query, new AccomodationIMPL.DocumentMapperTemplates());
        listAccomodations.addAll(listTemp);
        return listAccomodations;
    }

    /**
     *
     * @param accomodationBEAN
     * @return
     */
    @Override
    public int deleteAccomodation(AccomodationBEAN accomodationBEAN) {
        mySqlJdbcTemplate = new JdbcTemplate(dataSource);
        int row = 0;

        String sql = "DELETE FROM " + TableNames.TABLE_ACCOMODATION + "\n"
                + "WHERE\n"
                + "	accomodation_id = ?";
        row = mySqlJdbcTemplate.update(sql, new Object[]{accomodationBEAN.getAccomodationId()
        });
        return row;
    }

    /**
     *
     */
    public class DocumentMapperTemplates implements RowMapper<AccomodationBEAN> {

        /**
         *
         * @param rs
         * @param rowNum
         * @return
         * @throws SQLException
         */
        @Override
        public AccomodationBEAN mapRow(ResultSet rs, int rowNum) throws SQLException {
            AccomodationBEAN accomodationBEAN = new AccomodationBEAN();
            accomodationBEAN.setAccomodationId(rs.getString("accomodation_id"));
            accomodationBEAN.setAccomodation(rs.getString("accomodation"));
            return accomodationBEAN;
        }

    }

    /**
     *
     * @return
     */
    public static List<String> getAllAccomodations() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<String> allAccomodationsList = new ArrayList<>();
        String query = "select accomodation from master_accomodation";
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                allAccomodationsList.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return allAccomodationsList;
    }
}
