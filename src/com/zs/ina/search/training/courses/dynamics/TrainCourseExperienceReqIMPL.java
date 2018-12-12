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
import com.zs.ina.search.colleges.courses.dynamics.CourseExperienceReqIMPL;
import com.zs.ina.search.common.bean.ExperienceReqBEAN;
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
public class TrainCourseExperienceReqIMPL implements TrainCourseExperienceReqDAO {

    static Logger logger = Logger.getLogger(CourseExperienceReqIMPL.class);
    @Autowired
    DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean insertTrainExperience(ExperienceReqBEAN experienceReqBEAN) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "INSERT INTO " + TableNames.TBL_TR_COURSE_EXP_REQ + " (\n"
                + "	exp_reqmt_id,\n"
                + "	training_id,\n"
                + "	profession,\n"
                + "	experience_duration,\n"
                + "	created_date,\n"
                + "	created_user\n"
                + ")\n"
                + "VALUES\n"
                + "	(?, ?, ?, ?, now(), ?);";
        int row = jdbcTemplate.update(sql, new Object[]{
            experienceReqBEAN.getRowId(),
            experienceReqBEAN.getTrainingId(),
            experienceReqBEAN.getProfession(),
            experienceReqBEAN.getExperinceDuration(),
            experienceReqBEAN.getCreatedUser()
        });
        return (row > 0);

    }

    @Override
    public boolean deleteTrainExperience(String expReqmtId) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "DELETE FROM " + TableNames.TBL_TR_COURSE_EXP_REQ + "\n"
                + " WHERE\n"
                + "exp_reqmt_id=?";
        int row = jdbcTemplate.update(sql, new Object[]{expReqmtId});
        return (row > 0);

    }

    @Override
    public boolean updateTrainExperience(ExperienceReqBEAN experienceReqBEAN) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "UPDATE " + TableNames.TBL_TR_COURSE_EXP_REQ + "\n"
                + "SET profession = ?,\n"
                + " experience_duration = ?,\n"
                + " updated_user = ?,"
                + " updated_date=now()\n"
                + "WHERE\n"
                + "	exp_reqmt_id = ?";
        int row = jdbcTemplate.update(sql, new Object[]{
            experienceReqBEAN.getProfession(), experienceReqBEAN.getExperinceDuration(),
            experienceReqBEAN.getUpdatedUser(),
            experienceReqBEAN.getRowId()
        });
        return (row > 0);

    }

    @Override
    public ObservableList<ExperienceReqBEAN> retrieveTrainExperience(String trainingId) {
        ObservableList<ExperienceReqBEAN> listExperience = FXCollections.observableArrayList();
        jdbcTemplate = new JdbcTemplate(dataSource);
        String query = "SELECT * FROM " + TableNames.TBL_TR_COURSE_EXP_REQ + " WHERE training_id = ?";
        List<ExperienceReqBEAN> temp = jdbcTemplate.query(query, new Object[]{trainingId}, new TrainExperienceMapper());
        listExperience.addAll(temp);
        return listExperience;
    }

    public class TrainExperienceMapper implements RowMapper<ExperienceReqBEAN> {

        /**
         *
         * @param rs
         * @param rowNum
         * @return
         * @throws SQLException
         */
        @Override
        public ExperienceReqBEAN mapRow(ResultSet rs, int rowNum) throws SQLException {
            ExperienceReqBEAN experienceReqBEAN = new ExperienceReqBEAN();
            experienceReqBEAN.setRowId(rs.getString("exp_reqmt_id"));
            experienceReqBEAN.setTrainingId(rs.getString("training_id"));
            experienceReqBEAN.setProfession(rs.getString("profession"));
            experienceReqBEAN.setExperinceDuration(rs.getString("experience_duration"));
            experienceReqBEAN.setCreatedUser(rs.getString("created_user"));
            experienceReqBEAN.setCreatedDate(rs.getString("created_date"));
            experienceReqBEAN.setUpdatedUser(rs.getString("updated_user"));
            experienceReqBEAN.setUpdatedDate(rs.getString("updated_date"));
            return experienceReqBEAN;
        }

    }

}
