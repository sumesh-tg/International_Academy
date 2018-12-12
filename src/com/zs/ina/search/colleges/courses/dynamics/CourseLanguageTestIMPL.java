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
package com.zs.ina.search.colleges.courses.dynamics;

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
 * @author 100035
 */
public class CourseLanguageTestIMPL implements CourseLanguageTestDAO {

    static Logger logger = Logger.getLogger(CourseLanguageTestIMPL.class);
    @Autowired
    DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean insertLanguageTest(LanguageTestBEAN languageTestBEAN) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "INSERT INTO " + TableNames.TBL_SR_EDU_COURSE_LANGUAGE_TEST + " (\n"
                + "	language_reqmt_id,\n"
                + "	course_id,\n"
                + "	language_test,\n"
                + "	reading,\n"
                + "	listening,\n"
                + "	writing,\n"
                + "	speaking\n"
                + ")\n"
                + "VALUES\n"
                + "	(?, ?, ?, ?, ?, ?, ?);";
        int row = jdbcTemplate.update(sql, new Object[]{languageTestBEAN.getRowId(), languageTestBEAN.getCourseId(),
            languageTestBEAN.getLanguageTest(), languageTestBEAN.getReading(), languageTestBEAN.getListening(),
            languageTestBEAN.getWriting(), languageTestBEAN.getSpeaking()
        });
        return (row > 0);

    }

    @Override
    public boolean deleteLanguageTest(String langReqmtId) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "DELETE FROM " + TableNames.TBL_SR_EDU_COURSE_LANGUAGE_TEST + "\n"
                + " WHERE\n"
                + "language_reqmt_id=?";
        int row = jdbcTemplate.update(sql, new Object[]{langReqmtId});
        return (row > 0);

    }

    @Override
    public boolean updateLanguageTest(LanguageTestBEAN languageTestBEAN) {

        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "UPDATE " + TableNames.TBL_SR_EDU_COURSE_LANGUAGE_TEST + "\n"
                + "SET language_test = ?,\n"
                + " reading = ?,\n"
                + " listening = ?,\n"
                + " writing = ?,\n"
                + " speaking = ?\n"
                + "WHERE\n"
                + "	language_reqmt_id = ?";
        int row = jdbcTemplate.update(sql, new Object[]{
            languageTestBEAN.getLanguageTest(), languageTestBEAN.getReading(), languageTestBEAN.getListening(),
            languageTestBEAN.getWriting(), languageTestBEAN.getSpeaking(), languageTestBEAN.getRowId()
        });
        return (row > 0);
    }

    @Override
    public ObservableList<LanguageTestBEAN> retrieveLanguageTest(String courseId) {
        ObservableList<LanguageTestBEAN> listLanguageTests = FXCollections.observableArrayList();
        jdbcTemplate = new JdbcTemplate(dataSource);
        String query = "SELECT * FROM " + TableNames.TBL_SR_EDU_COURSE_LANGUAGE_TEST + " WHERE course_id = ?";
        List<LanguageTestBEAN> temp = jdbcTemplate.query(query, new Object[]{courseId}, new LanguageTestMapper());
        listLanguageTests.addAll(temp);
        return listLanguageTests;

    }

    /**
     *
     */
    public class LanguageTestMapper implements RowMapper<LanguageTestBEAN> {

        @Override
        public LanguageTestBEAN mapRow(ResultSet rs, int rowNum) throws SQLException {
            LanguageTestBEAN languageTestBEAN = new LanguageTestBEAN();
            languageTestBEAN.setRowId(rs.getString("language_reqmt_id"));
            languageTestBEAN.setCourseId(rs.getString("course_id"));
            languageTestBEAN.setLanguageTest(rs.getString("language_test"));
            languageTestBEAN.setReading(rs.getString("reading"));
            languageTestBEAN.setListening(rs.getString("listening"));
            languageTestBEAN.setWriting(rs.getString("writing"));
            languageTestBEAN.setSpeaking(rs.getString("speaking"));
            return languageTestBEAN;
        }

    }

}
