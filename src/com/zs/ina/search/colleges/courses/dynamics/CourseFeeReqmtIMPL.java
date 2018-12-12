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
import com.zs.ina.search.common.bean.FeeBEAN;
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
public class CourseFeeReqmtIMPL implements CourseFeeReqmtDAO {

    static Logger logger = Logger.getLogger(CourseFeeReqmtIMPL.class);
    @Autowired
    DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean insertFeeDetails(FeeBEAN feeBEAN) {

        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "INSERT INTO " + TableNames.TBL_SR_EDU_COURSE_FEE + " (\n"
                + "	course_fee_id,\n"
                + "	course_id,\n"
                + "	currency,\n"
                + "	application_fee,\n"
                + "	annual_fee,\n"
                + "	semester_fee,\n"
                + "	other_fee\n"
                + ")\n"
                + "VALUES\n"
                + "	(?, ?, ?, ?, ?, ?, ?);";
        int row = jdbcTemplate.update(sql, new Object[]{feeBEAN.getRowId(), feeBEAN.getCourseId(),
            feeBEAN.getCurrency(), feeBEAN.getApplicationFee(), feeBEAN.getAnnualFee(),
            feeBEAN.getSemesterFee(), feeBEAN.getOtherFee()
        });
        return (row > 0);

    }

    @Override
    public boolean deleteFeeDetails(String courseFeeId) {

        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "DELETE FROM " + TableNames.TBL_SR_EDU_COURSE_FEE + "\n"
                + " WHERE\n"
                + "course_fee_id=?";
        int row = jdbcTemplate.update(sql, new Object[]{courseFeeId});
        return (row > 0);

    }

    @Override
    public boolean updateFeeDetails(FeeBEAN feeBEAN) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "UPDATE " + TableNames.TBL_SR_EDU_COURSE_FEE + "\n"
                + "SET currency = ?,\n"
                + " application_fee = ?,\n"
                + " annual_fee = ?,\n"
                + " semester_fee = ?,\n"
                + " other_fee = ?\n"
                + "WHERE\n"
                + "	course_fee_id = ?";
        int row = jdbcTemplate.update(sql, new Object[]{
            feeBEAN.getCurrency(),
            feeBEAN.getApplicationFee(), feeBEAN.getAnnualFee(),
            feeBEAN.getSemesterFee(), feeBEAN.getOtherFee(), feeBEAN.getRowId()
        });
        return (row > 0);

    }

    @Override
    public ObservableList<FeeBEAN> retrieveFeeDetails(String courseId) {
        ObservableList<FeeBEAN> listFees = FXCollections.observableArrayList();
        jdbcTemplate = new JdbcTemplate(dataSource);
        String query = "SELECT * FROM " + TableNames.TBL_SR_EDU_COURSE_FEE + " WHERE course_id = ?";
        List<FeeBEAN> temp = jdbcTemplate.query(query, new Object[]{courseId}, new FeeMapper());
        listFees.addAll(temp);
        return listFees;

    }

    public class FeeMapper implements RowMapper<FeeBEAN> {

        @Override
        public FeeBEAN mapRow(ResultSet rs, int rowNum) throws SQLException {
            FeeBEAN feeBEAN = new FeeBEAN();
            feeBEAN.setRowId(rs.getString("course_fee_id"));
            feeBEAN.setCourseId(rs.getString("course_id"));
            feeBEAN.setCurrency(rs.getString("currency"));
            feeBEAN.setApplicationFee(rs.getString("application_fee"));
            feeBEAN.setAnnualFee(rs.getString("annual_fee"));
            feeBEAN.setSemesterFee(rs.getString("semester_fee"));
            feeBEAN.setOtherFee(rs.getString("other_fee"));
            return feeBEAN;
        }

    }

}
