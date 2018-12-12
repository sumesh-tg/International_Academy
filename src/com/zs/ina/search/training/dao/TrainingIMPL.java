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
package com.zs.ina.search.training.dao;

import com.zs.ina.common.constants.TableNames;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
public class TrainingIMPL implements TrainingDAO {

    Logger logger = Logger.getLogger(TrainingIMPL.class);
    @Autowired
    DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean insertTrainingCourseBasics(TrainingBEAN trainingBEAN) {
        int row = 0;
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sqls = "INSERT INTO " + TableNames.TBL_TR_BASIC + " (\n"
                + "	training_id,\n"
                + "	training_course,\n"
                + "	website,\n"
                + "	description,\n"
                + "	remark,\n"
                + "	course_duration,\n"
                + "	course_mode,\n"
                + "	affiliation,\n"
                + "	exam_fee,\n"
                + "	provider_type,\n"
                + "	agency_status,\n"
                + "	tieup_name,\n"
                + "	commission,\n"
                + "	contract_from,\n"
                + "	contract_to,\n"
                + "	contract_by,\n"
                + "	renewal_by,\n"
                + "	course_fee,\n"
                + "	payment_mode,\n"
                + "	created_date,\n"
                + "	created_user\n"
                + ")\n"
                + "VALUES\n"
                + "	(\n"
                + "		?,\n"
                + "		?,\n"
                + "		?,\n"
                + "		?,\n"
                + "		?,\n"
                + "		?,\n"
                + "		?,\n"
                + "		?,\n"
                + "		?,\n"
                + "		?,\n"
                + "		?,\n"
                + "		?,\n"
                + "		?,\n"
                + "		?,\n"
                + "		?,\n"
                + "		?,\n"
                + "		?,\n"
                + "		?,\n"
                + "		?,\n"
                + "		now(),\n"
                + "		?\n"
                + "	);";
        System.out.println("sqls TRAINING BASIC === " + sqls);
        row = jdbcTemplate.update(sqls, new Object[]{
            trainingBEAN.getTrainingId(),
            trainingBEAN.getTrainingCourse(),
            trainingBEAN.getWebsite(),
            trainingBEAN.getDescription(),
            trainingBEAN.getRemarks(),
            trainingBEAN.getDuration(),
            trainingBEAN.getCourseMode(),
            trainingBEAN.getAffiliation(),
            trainingBEAN.getExamFee(),
            trainingBEAN.getProviderType(),
            trainingBEAN.getAgencyStatus(),
            trainingBEAN.getTieUpName(),
            trainingBEAN.getCommission(),
            trainingBEAN.getContractFrom().toString(),
            trainingBEAN.getContractTo().toString(),
            trainingBEAN.getContractBy(),
            trainingBEAN.getRenewalBy(),
            trainingBEAN.getCourseFee(),
            trainingBEAN.getPaymentMode(),
            trainingBEAN.getCreatedUser()
        });
        return (row > 0);

    }

    @Override
    public boolean updateTrainingCourseBasics(TrainingBEAN trainingBEAN) {
        int row = 0;
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "UPDATE " + TableNames.TBL_TR_BASIC + " \n"
                + "SET \n"
                + " training_course = ?,\n"
                + " website = ?,\n"
                + " description = ?,\n"
                + " remark = ?,\n"
                + " course_duration = ?,\n"
                + " course_mode = ?,\n"
                + " affiliation = ?,\n"
                + " exam_fee = ?,\n"
                + " provider_type = ?,\n"
                + " agency_status = ?,\n"
                + " tieup_name = ?,\n"
                + " commission = ?,\n"
                + " contract_from = ?,\n"
                + " contract_to = ?,\n"
                + " contract_by = ?,\n"
                + " renewal_by = ?,\n"
                + " course_fee = ?,\n"
                + " payment_mode = ?,\n"
                + " updated_date = NOW()\n"
                + "WHERE\n"
                + "	training_id=?";
        row = jdbcTemplate.update(sql, new Object[]{
            trainingBEAN.getTrainingCourse(),
            trainingBEAN.getWebsite(),
            trainingBEAN.getDescription(),
            trainingBEAN.getRemarks(),
            trainingBEAN.getDuration(),
            trainingBEAN.getCourseMode(),
            trainingBEAN.getAffiliation(),
            trainingBEAN.getExamFee(),
            trainingBEAN.getProviderType(),
            trainingBEAN.getAgencyStatus(),
            trainingBEAN.getTieUpName(),
            trainingBEAN.getCommission(),
            trainingBEAN.getContractFrom().toString(),
            trainingBEAN.getContractTo().toString(),
            trainingBEAN.getContractBy(),
            trainingBEAN.getRenewalBy(),
            trainingBEAN.getCourseFee(),
            trainingBEAN.getPaymentMode(),
            trainingBEAN.getTrainingId()
        });
        return (row > 0) ? true : false;

    }

    @Override
    public ObservableList<TrainingBEAN> retrieveTrainingCourseBasics() {

        ObservableList<TrainingBEAN> listTrainingCourseBasics = FXCollections.observableArrayList();
        jdbcTemplate = new JdbcTemplate(dataSource);
        String query = "SELECT * FROM " + TableNames.TBL_TR_BASIC + "";
        List<TrainingBEAN> temp = jdbcTemplate.query(query, new TrainingBasicsMapper());
        listTrainingCourseBasics.addAll(temp);
        return listTrainingCourseBasics;

    }

    public class TrainingBasicsMapper implements RowMapper<TrainingBEAN> {

        @Override
        public TrainingBEAN mapRow(ResultSet rs, int rowNum) throws SQLException {
            TrainingBEAN trainingBEAN = new TrainingBEAN();
            trainingBEAN.setTrainingId(rs.getString("training_id"));
            trainingBEAN.setTrainingCourse(rs.getString("training_course"));
            trainingBEAN.setDescription(rs.getString("description"));
            trainingBEAN.setRemarks(rs.getString("remark"));
            trainingBEAN.setWebsite(rs.getString("website"));
            trainingBEAN.setDuration(rs.getString("course_duration"));
            trainingBEAN.setCourseMode(rs.getString("course_mode"));
            trainingBEAN.setAffiliation(rs.getString("affiliation"));
            trainingBEAN.setExamFee(rs.getString("exam_fee"));
            trainingBEAN.setProviderType(rs.getString("provider_type"));
            trainingBEAN.setAgencyStatus(rs.getString("agency_status"));
            trainingBEAN.setTieUpName(rs.getString("tieup_name"));
            trainingBEAN.setCommission(rs.getString("commission"));
            trainingBEAN.setContractFrom(LocalDate.parse(rs.getString("contract_from")));
            trainingBEAN.setContractTo(LocalDate.parse(rs.getString("contract_to")));
            trainingBEAN.setContractBy(rs.getString("contract_by"));
            trainingBEAN.setRenewalBy(rs.getString("renewal_by"));
            trainingBEAN.setCourseFee(rs.getString("course_fee"));
            trainingBEAN.setPaymentMode(rs.getString("payment_mode"));
            trainingBEAN.setCreatedUser(rs.getString("created_user"));
            trainingBEAN.setUpdatedUser(rs.getString("updated_user"));
            trainingBEAN.setCreatedDate(rs.getString("created_date"));
            trainingBEAN.setUpdatedDate(rs.getString("updated_date"));
            return trainingBEAN;
        }

    }

    

    @Override
    public boolean deleteTrainingCourseBasics(TrainingBEAN trainingBEAN) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        int row = 0;

        String sql = "DELETE FROM " + TableNames.TBL_TR_BASIC + "\n"
                + "WHERE\n"
                + "	training_id = ?";
        row = jdbcTemplate.update(sql, new Object[]{trainingBEAN.getTrainingId()
        });
        return (row > 0) ? true : false;
    }

}
