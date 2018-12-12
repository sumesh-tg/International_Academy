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
package com.zs.ina.search.training.courses;

import com.zs.ina.common.constants.TableNames;
import com.zs.ina.search.training.dao.TrainingBEAN;
import com.zs.ina.search.training.dao.TrainingIMPL;
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
public class TrainReqmtBasicIMPL implements TrainReqmtBasicDAO {

    Logger logger = Logger.getLogger(TrainReqmtBasicIMPL.class);
    @Autowired
    DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean updateTrainingCourseReqmtBasics(TrainingBEAN trainingBEAN) {
        int row = 0;
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "UPDATE " + TableNames.TBL_TR_BASIC + " \n"
                + "SET \n"
                + " sex = ?,\n"
                + " age_from = ?,\n"
                + " age_to = ?,\n"
                + " marital_status = ?,\n"
                + " sslc_board = ?,\n"
                + " sslc_medium = ?,\n"
                + " plus2_board = ?,\n"
                + " plus2_medium = ?,\n"
                + " eng_medium_board = ?,\n"
                + " eng_medium_duration = ?,\n"
                + " intake = ?,\n"
                + " career = ?,\n"
                + " advantage = ?,\n"
                + " campuses = ?\n"
                + "WHERE\n"
                + "	training_id=?";
        row = jdbcTemplate.update(sql, new Object[]{
            trainingBEAN.getGender(),
            trainingBEAN.getAgeFrom(),
            trainingBEAN.getAgeTo(),
            trainingBEAN.getMaritalStatus(),
            trainingBEAN.getSslcBoard(),
            trainingBEAN.getSslcMedium(),
            trainingBEAN.getPlus2board(),
            trainingBEAN.getPlus2medium(),
            trainingBEAN.getEngMediumBoard(),
            trainingBEAN.getEngMediumDuration(),
            trainingBEAN.getIntake(),
            trainingBEAN.getCareer(),
            trainingBEAN.getAdvantage(),
            trainingBEAN.getTrainCampuses(),
            trainingBEAN.getTrainingId()
        });
        return (row > 0);

    }

    @Override
    public boolean deleteTrainingCourseReqmtBasics(TrainingBEAN trainingBEAN) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<TrainingBEAN> retrieveTrainingCourseReqmtBasics() {
        ObservableList<TrainingBEAN> listTrainingCourseBasics = FXCollections.observableArrayList();
        jdbcTemplate = new JdbcTemplate(dataSource);
        String query = "SELECT * FROM " + TableNames.TBL_TR_BASIC + "";;
        List<TrainingBEAN> temp = jdbcTemplate.query(query, new TrainingCourseReqmtBasicsMapper());
        listTrainingCourseBasics.addAll(temp);
        return listTrainingCourseBasics;

    }

    @Override
    public TrainingBEAN retrieveSelectedTrainingCourseReqmtBasics(String trainingId) {
        ObservableList<TrainingBEAN> listTrainingCourseBasics = FXCollections.observableArrayList();
        jdbcTemplate = new JdbcTemplate(dataSource);
        String query = "SELECT * FROM " + TableNames.TBL_TR_BASIC + " WHERE training_id = ?";;
        List<TrainingBEAN> temp = jdbcTemplate.query(query, new Object[]{trainingId}, new TrainingCourseReqmtBasicsMapper());
        if (temp.size() > 0) {
            return temp.get(0);
        } else {
            return new TrainingBEAN();
        }

    }

//    @Override
//    public ObservableList<TrainingBEAN> retrieveTrainingCourseReqmt(String trainingId) {
//
//        ObservableList<TrainingBEAN> listTrainingCourseBasics = FXCollections.observableArrayList();
//        jdbcTemplate = new JdbcTemplate(dataSource);
//        String query = "SELECT * FROM " + TableNames.TBL_TR_BASIC + " WHERE training_id = '" + trainingId + "'";;
//        List<TrainingBEAN> temp = jdbcTemplate.query(query, new TrainingCourseReqmtBasicsMapper());
//        listTrainingCourseBasics.addAll(temp);
//        return listTrainingCourseBasics;
//    }
    public class TrainingCourseReqmtBasicsMapper implements RowMapper<TrainingBEAN> {

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
            trainingBEAN.setGender(rs.getString("sex"));
            trainingBEAN.setAgeFrom(rs.getString("age_from"));
            trainingBEAN.setAgeTo(rs.getString("age_to"));
            trainingBEAN.setMaritalStatus(rs.getString("marital_status"));
            trainingBEAN.setSslcBoard(rs.getString("sslc_board"));
            trainingBEAN.setSslcMedium(rs.getString("sslc_medium"));
            trainingBEAN.setPlus2board(rs.getString("plus2_board"));
            trainingBEAN.setPlus2medium(rs.getString("plus2_medium"));
            trainingBEAN.setEngMediumBoard(rs.getString("eng_medium_board"));
            trainingBEAN.setEngMediumDuration(rs.getString("eng_medium_duration"));
            trainingBEAN.setIntake(rs.getString("intake"));
            trainingBEAN.setCareer(rs.getString("career"));
            trainingBEAN.setAdvantage(rs.getString("advantage"));
            trainingBEAN.setTrainCampuses(rs.getString("campuses"));
            return trainingBEAN;
        }

    }

}
