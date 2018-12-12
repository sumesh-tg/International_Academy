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
package com.zs.ina.search.master.jobtitle.dao;

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
public class JobTitleIMPL implements JobTitleDAO {

    static Logger logger = Logger.getLogger(JobTitleIMPL.class);
    @Autowired
    DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    /**
     *
     * @param jobTitleBEAN
     * @return
     */
    @Override
    public int insertJobTitle(JobTitleBEAN jobTitleBEAN) {
        int row = 0;
        jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "INSERT INTO " + TableNames.TBL_MSTR_JOB_TITLE + " ( id,job_title)\n"
                + "VALUES\n"
                + "	(0, ?);";
        row = jdbcTemplate.update(sql, new Object[]{jobTitleBEAN.getJobTitle()});
        return row;
    }

    /**
     *
     * @param jobTitleBEAN
     * @return
     */
    @Override
    public int updateJobTitle(JobTitleBEAN jobTitleBEAN) {
        int row = 0;
        jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "UPDATE master_sr_jobtitle\n"
                + "SET \n"
                + " job_title = ?\n"
                + "WHERE\n"
                + "	id=?";
        row = jdbcTemplate.update(sql, new Object[]{jobTitleBEAN.getJobTitle(), jobTitleBEAN.getId()});
        return row;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public int deleteJobTitle(String id) {
        int row = 0;
        jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "DELETE FROM " + TableNames.TBL_MSTR_JOB_TITLE 
                + " WHERE id = ?\n";
        row = jdbcTemplate.update(sql, new Object[]{id});
        return row;
    }

    /**
     *
     * @return
     */
    @Override
    public ObservableList<JobTitleBEAN> retrieveJobTitles() {

        ObservableList<JobTitleBEAN> listJobTitles = FXCollections.observableArrayList();
        jdbcTemplate = new JdbcTemplate(dataSource);
        String query = "SELECT * FROM " + TableNames.TBL_MSTR_JOB_TITLE + "";
        List<JobTitleBEAN> temp = jdbcTemplate.query(query, new JobTitleMapper());
        listJobTitles.addAll(temp);
        return listJobTitles;
    }

    /**
     *
     */
    public class JobTitleMapper implements RowMapper<JobTitleBEAN> {

        /**
         *
         * @param rs
         * @param rowNum
         * @return
         * @throws SQLException
         */
        @Override
        public JobTitleBEAN mapRow(ResultSet rs, int rowNum) throws SQLException {
            JobTitleBEAN jobTitleBEAN = new JobTitleBEAN();
            jobTitleBEAN.setId(rs.getString("id"));
            jobTitleBEAN.setJobTitle(rs.getString("job_title"));
            return jobTitleBEAN;
        }

    }
}
