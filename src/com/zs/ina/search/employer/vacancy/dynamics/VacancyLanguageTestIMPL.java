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
package com.zs.ina.search.employer.vacancy.dynamics;

import com.zs.ina.common.constants.TableNames;
import com.zs.ina.search.common.bean.LanguageTestBEAN;
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
public class VacancyLanguageTestIMPL implements VacancyLanguageTestDAO {

    static Logger logger = Logger.getLogger(VacancyLanguageTestIMPL.class);
    @Autowired
    DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    /**
     *
     * @param languageTestBEAN
     * @return
     */
    @Override
    public boolean insertLanguageTest(LanguageTestBEAN languageTestBEAN) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "INSERT INTO " + TableNames.TBL_SR_EMPLYR_VACANCY_LANG_TEST + " (\n"
                + "	language_reqmt_id,\n"
                + "	vacancy_id,\n"
                + "	language_test,\n"
                + "	reading,\n"
                + "	listening,\n"
                + "	writing,\n"
                + "	speaking,\n"
                + "	overall\n"
                + ")\n"
                + "VALUES\n"
                + "	(?, ?, ?, ?, ?, ?, ?, ?);";
        int row = jdbcTemplate.update(sql, new Object[]{languageTestBEAN.getRowId(), languageTestBEAN.getVacancyId(),
            languageTestBEAN.getLanguageTest(), languageTestBEAN.getReading(), languageTestBEAN.getListening(),
            languageTestBEAN.getWriting(), languageTestBEAN.getSpeaking(),languageTestBEAN.getOverall()
        });
        return (row > 0);
    }

    /**
     *
     * @param rowId
     * @return
     */
    @Override
    public boolean deleteLanguageTest(String rowId) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "DELETE FROM " + TableNames.TBL_SR_EMPLYR_VACANCY_LANG_TEST + "\n"
                + " WHERE\n"
                + "language_reqmt_id=?";
        int row = jdbcTemplate.update(sql, new Object[]{rowId});
        return (row > 0);
    }

    /**
     *
     * @param languageTestBEAN
     * @return
     */
    @Override
    public boolean updateLanguageTest(LanguageTestBEAN languageTestBEAN) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "UPDATE " + TableNames.TBL_SR_EMPLYR_VACANCY_LANG_TEST + "\n"
                + "SET language_test = ?,\n"
                + " reading = ?,\n"
                + " listening = ?,\n"
                + " writing = ?,\n"
                + " speaking = ?,\n"
                + " overall = ?\n"
                + "WHERE\n"
                + "	language_reqmt_id = ?";
        int row = jdbcTemplate.update(sql, new Object[]{
            languageTestBEAN.getLanguageTest(), languageTestBEAN.getReading(), languageTestBEAN.getListening(),
            languageTestBEAN.getWriting(), languageTestBEAN.getSpeaking(),languageTestBEAN.getOverall(),languageTestBEAN.getRowId()
        });
        return (row > 0);
    }

    /**
     *
     * @param vacancyId
     * @return
     */
    @Override
    public ObservableList<LanguageTestBEAN> retrieveLanguageTest(String vacancyId) {

        ObservableList<LanguageTestBEAN> listLanguageTests = FXCollections.observableArrayList();
        jdbcTemplate = new JdbcTemplate(dataSource);
        String query = "SELECT * FROM " + TableNames.TBL_SR_EMPLYR_VACANCY_LANG_TEST + " WHERE vacancy_id = ?";
        List<LanguageTestBEAN> temp = jdbcTemplate.query(query, new Object[]{vacancyId}, new LanguageTestMapper());
        listLanguageTests.addAll(temp);
        return listLanguageTests;

    }

    /**
     *
     */
    public class LanguageTestMapper implements RowMapper<LanguageTestBEAN> {

        /**
         *
         * @param rs
         * @param rowNum
         * @return
         * @throws SQLException
         */
        @Override
        public LanguageTestBEAN mapRow(ResultSet rs, int rowNum) throws SQLException {
            LanguageTestBEAN languageTestBEAN = new LanguageTestBEAN();
            languageTestBEAN.setRowId(rs.getString("language_reqmt_id"));
            languageTestBEAN.setLanguageTest(rs.getString("language_test"));
            languageTestBEAN.setVacancyId(rs.getString("vacancy_id"));
            languageTestBEAN.setReading(rs.getString("reading"));
            languageTestBEAN.setListening(rs.getString("listening"));
            languageTestBEAN.setWriting(rs.getString("writing"));
            languageTestBEAN.setSpeaking(rs.getString("speaking"));
            languageTestBEAN.setOverall(rs.getString("overall"));
            return languageTestBEAN;
        }

    }
}
