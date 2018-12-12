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
package com.zs.ina.search.training.courses.dynamics;

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
public class TrainCourseLanguageTestIMPL implements TrainCourseLanguageTestDAO {

    static Logger logger = Logger.getLogger(TrainCourseLanguageTestIMPL.class);
    @Autowired
    DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean insertTrainLanguageTest(LanguageTestBEAN languageTestBEAN) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "INSERT INTO " + TableNames.TBL_TR_COURSE_LANG_TEST + " (\n"
                + "	language_reqmt_id,\n"
                + "	training_id,\n"
                + "	language_test,\n"
                + "	reading,\n"
                + "	listening,\n"
                + "	writing,\n"
                + "	speaking,\n"
                + "	created_date,\n"
                + "	created_user\n"
                + ")\n"
                + "VALUES\n"
                + "	(?, ?, ?, ?, ?, ?, ?, now(), ?);";
        int row = jdbcTemplate.update(sql, new Object[]{
            languageTestBEAN.getRowId(), 
            languageTestBEAN.getTrainingId(),
            languageTestBEAN.getLanguageTest(),
            languageTestBEAN.getReading(), 
            languageTestBEAN.getListening(),
            languageTestBEAN.getWriting(), 
            languageTestBEAN.getSpeaking(),
            languageTestBEAN.getCreatedUser()
        });
        return (row > 0);
    }

    @Override
    public boolean deleteTrainLanguageTest(String languagetestId) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "DELETE FROM " + TableNames.TBL_TR_COURSE_LANG_TEST + "\n"
                + " WHERE\n"
                + "language_reqmt_id=?";
        int row = jdbcTemplate.update(sql, new Object[]{languagetestId});
        return (row > 0);
    }

    @Override
    public boolean updateTrainLanguageTest(LanguageTestBEAN languageTestBEAN) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "UPDATE " + TableNames.TBL_TR_COURSE_LANG_TEST + "\n"
                + "SET language_test = ?,\n"
                + " reading = ?,\n"
                + " listening = ?,\n"
                + " writing = ?,\n"
                + " speaking = ?,\n"
                + " updated_user = ?,"
                + " updated_date=now()\n"
                + "WHERE\n"
                + "	language_reqmt_id = ?";
        int row = jdbcTemplate.update(sql, new Object[]{
            languageTestBEAN.getLanguageTest(), languageTestBEAN.getReading(), languageTestBEAN.getListening(),
            languageTestBEAN.getWriting(), languageTestBEAN.getSpeaking(),
            languageTestBEAN.getUpdatedUser(),
            languageTestBEAN.getRowId()
        });
        return (row > 0);

    }

    @Override
    public ObservableList<LanguageTestBEAN> retrieveTrainLanguageTest(String trainingId) {
        ObservableList<LanguageTestBEAN> listLanguageTests = FXCollections.observableArrayList();
        jdbcTemplate = new JdbcTemplate(dataSource);
        String query = "SELECT * FROM " + TableNames.TBL_TR_COURSE_LANG_TEST + " WHERE training_id = ?";
        List<LanguageTestBEAN> temp = jdbcTemplate.query(query, new Object[]{trainingId}, new TrainLanguageTestMapper());
        listLanguageTests.addAll(temp);
        return listLanguageTests;
    }

    public class TrainLanguageTestMapper implements RowMapper<LanguageTestBEAN> {

        @Override
        public LanguageTestBEAN mapRow(ResultSet rs, int rowNum) throws SQLException {
            LanguageTestBEAN languageTestBEAN = new LanguageTestBEAN();
            languageTestBEAN.setRowId(rs.getString("language_reqmt_id"));
            languageTestBEAN.setTrainingId(rs.getString("training_id"));
            languageTestBEAN.setLanguageTest(rs.getString("language_test"));
            languageTestBEAN.setReading(rs.getString("reading"));
            languageTestBEAN.setListening(rs.getString("listening"));
            languageTestBEAN.setWriting(rs.getString("writing"));
            languageTestBEAN.setSpeaking(rs.getString("speaking"));
            languageTestBEAN.setCreatedUser(rs.getString("created_user"));
            languageTestBEAN.setCreatedDate(rs.getString("created_date"));
            languageTestBEAN.setUpdatedUser(rs.getString("updated_user"));
            languageTestBEAN.setUpdatedDate(rs.getString("updated_date"));
            return languageTestBEAN;
        }

    }
}
