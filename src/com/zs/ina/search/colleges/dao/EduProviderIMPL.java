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
package com.zs.ina.search.colleges.dao;

import com.zs.ina.common.constants.TableNames;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 *
 * @author 100035
 */
public class EduProviderIMPL implements EduProviderDAO {

    Logger logger = Logger.getLogger(EduProviderIMPL.class);
    @Autowired
    DataSource dataSource;
    private JdbcTemplate mySqlJdbcTemplate;

    /**
     *
     * @param eduProviderBEAN
     * @return Educational Provider insertion status
     */
    @Override
    public int insertEduProviderBasics(EduProviderBEAN eduProviderBEAN) {
        int row = 0;
        mySqlJdbcTemplate = new JdbcTemplate(dataSource);
        String sqls = "INSERT INTO " + TableNames.TBL_SR_EDU_PROVIDERS + " (\n"
                + "	college_id,\n"
                + "	college_name,\n"
                + "	university_id,\n"
                + "	description,\n"
                + "	remarks,\n"
                + "	college_address,\n"
                + "	logo,\n"
                + "	website,\n"
                + "	commission,\n"
                + "	currency,\n"
                + "	institution_type,\n"
                + "	agency_status,\n"
                + "	tieup_name,\n"
                + "	contract_from,\n"
                + "	contract_to,\n"
                + "	contract_by,\n"
                + "	renewal_by,\n"
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
                + "		now(),\n"
                + "		?\n"
                + "	);";
        row = mySqlJdbcTemplate.update(sqls, new Object[]{
            eduProviderBEAN.getCollegeId(),
            eduProviderBEAN.getCollege(),
            eduProviderBEAN.getUniversityId(),
            eduProviderBEAN.getDescription(),
            eduProviderBEAN.getRemarks(),
            eduProviderBEAN.getAddress(),
            eduProviderBEAN.getInputStream(),
            eduProviderBEAN.getWebsite(),
            eduProviderBEAN.getCommission(),
            eduProviderBEAN.getCurrency(),
            eduProviderBEAN.getInstitutionType(),
            eduProviderBEAN.getAgencyStatus(),
            eduProviderBEAN.getTieUpName(),
            eduProviderBEAN.getContractFrom().toString(),
            eduProviderBEAN.getContractTo().toString(),
            eduProviderBEAN.getContractBy(),
            eduProviderBEAN.getRenewalBy(),
            eduProviderBEAN.getCreatedUser()
        });
        return row;

    }

    /**
     *
     * @param eduProviderBEAN
     * @return Educational Provider update status
     */
    @Override
    public int updateEduProviderBasics(EduProviderBEAN eduProviderBEAN) {

        int row = 0;
        NamedParameterJdbcTemplate parameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        String sql = "UPDATE " + TableNames.TBL_SR_EDU_PROVIDERS + "\n"
                + "SET college_name =:college_name,\n"
                + " university_id =:university_id,\n"
                + " description =:description,\n"
                + " remarks =:remarks,\n"
                + " college_address =:college_address,\n"
                + " logo =:logo,\n"
                + " website =:website,\n"
                + " commission =:commission,\n"
                + " currency =:currency,\n"
                + " institution_type =:institution_type,\n"
                + " agency_status =:agency_status,\n"
                + " tieup_name =:tieup_name,\n"
                + " contract_from =:contract_from,\n"
                + " contract_to =:contract_to,\n"
                + " contract_by =:contract_by,\n"
                + " renewal_by =:renewal_by,\n"
                + " updated_user =:updated_user,\n"
                + " updated_date =now()\n"
                + " WHERE\n"
                + " college_id =:college_id";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("college_name", eduProviderBEAN.getCollege());
        parameters.addValue("university_id", eduProviderBEAN.getUniversityId());
        parameters.addValue("description", eduProviderBEAN.getDescription());
        parameters.addValue("remarks", eduProviderBEAN.getRemarks());
        parameters.addValue("college_address", eduProviderBEAN.getAddress());
        try {
            byte[] bytes = IOUtils.toByteArray(eduProviderBEAN.getInputStream());
            parameters.addValue("logo", bytes, Types.BLOB);
        } catch (IOException ex) {
            ex.printStackTrace();
            java.util.logging.Logger.getLogger(EduProviderIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        parameters.addValue("website", eduProviderBEAN.getWebsite());
        parameters.addValue("commission", eduProviderBEAN.getCommission());
        parameters.addValue("currency", eduProviderBEAN.getCurrency());
        parameters.addValue("institution_type", eduProviderBEAN.getInstitutionType());
        parameters.addValue("agency_status", eduProviderBEAN.getAgencyStatus());
        parameters.addValue("tieup_name", eduProviderBEAN.getTieUpName());
        parameters.addValue("contract_from", eduProviderBEAN.getContractFrom().toString());
        parameters.addValue("contract_to", eduProviderBEAN.getContractTo().toString());
        parameters.addValue("contract_by", eduProviderBEAN.getContractBy());
        parameters.addValue("renewal_by", eduProviderBEAN.getRenewalBy());
        parameters.addValue("updated_user", eduProviderBEAN.getUpdatedUser());
        parameters.addValue("college_id", eduProviderBEAN.getCollegeId());
        row = parameterJdbcTemplate.update(sql, parameters);
        return row;

    }

    /**
     *
     * @return Educational Provider deletion status
     */
    @Override
    public int deleteEduProviderBasics(EduProviderBEAN eduProviderBEAN) {
        mySqlJdbcTemplate = new JdbcTemplate(dataSource);
        int row = 0;

        String sql = "DELETE FROM " + TableNames.TBL_SR_EDU_PROVIDERS + "\n"
                + "WHERE\n"
                + "	college_id = ?";
        row = mySqlJdbcTemplate.update(sql, new Object[]{eduProviderBEAN.getCollegeId()
        });
        return row;

    }

//    /**
//     *
//     * @return
//     */
//    @Override
//    public ObservableList<EduProviderBEAN> retrieveDuration() {
//        mySqlJdbcTemplate = new JdbcTemplate(dataSource);
//        List<EduProviderBEAN> listTemp = new ArrayList<>();
//        ObservableList<EduProviderBEAN> listEduProvider = FXCollections.observableArrayList();
//        String query = "SELECT * FROM " + TableNames.TBL_SR_EDU_PROVIDERS + "";
//        listTemp = mySqlJdbcTemplate.query(query, new EduProviderIMPL.EduProviderMapperTemplates());
//        listEduProvider.addAll(listTemp);
//        return listEduProvider;
//
//    }
//    public class EduProviderMapperTemplates implements RowMapper<DurationBEAN> {
//
//        /**
//         *
//         * @param rs
//         * @param rowNum
//         * @return
//         * @throws SQLException
//         */
//        @Override
//        public DurationBEAN mapRow(ResultSet rs, int rowNum) throws SQLException {
//            DurationBEAN durationBEAN = new DurationBEAN();
//            durationBEAN.setDurationId(rs.getString("duration_id"));
//            durationBEAN.setDuration(rs.getString("duration"));
//            durationBEAN.setDurationDays(rs.getString("duration_in_days"));
//            return durationBEAN;
//        }
//
//    }
    @Override
    public ObservableList<EduProviderBEAN> retrieveEduProviderBasics() {

        ObservableList<EduProviderBEAN> listEduProviderBasics = FXCollections.observableArrayList();
        mySqlJdbcTemplate = new JdbcTemplate(dataSource);
        String query = "SELECT\n"
                + "	se_prov.college_id,\n"
                + "	se_prov.university_id,\n"
                + "	se_prov.college_name,\n"
                + "	se_prov.commission,\n"
                + "	se_prov.description,\n"
                + "	se_prov.remarks,\n"
                + "	se_prov.website,\n"
                + "	se_prov.logo,\n"
                + "	se_prov.college_address,\n"
                + "	se_prov.institution_type,\n"
                + "	se_prov.currency,\n"
                + "	se_prov.agency_status,\n"
                + "	se_prov.tieup_name,\n"
                + "	se_prov.contract_from,\n"
                + "	se_prov.contract_to,\n"
                + "	se_prov.contract_by,\n"
                + "	se_prov.renewal_by,\n"
                + "	se_prov.created_user,\n"
                + "	se_prov.updated_user,\n"
                + "	se_prov.created_date,\n"
                + "	se_prov.updated_date,\n"
                + "	master_univ.university_name\n"
                + "FROM\n"
                + "	master_university master_univ\n"
                + "RIGHT JOIN search_edu_providers se_prov ON master_univ.university_id = se_prov.university_id";
        List<EduProviderBEAN> temp = mySqlJdbcTemplate.query(query, new EduProviderBasicsMapper());
        listEduProviderBasics.addAll(temp);
        return listEduProviderBasics;
    }

    public class EduProviderBasicsMapper implements RowMapper<EduProviderBEAN> {

        /**
         *
         * @param rs
         * @param rowNum
         * @return employerBasicsBEAN Employer Basic Information
         * @throws SQLException
         */
        @Override
        public EduProviderBEAN mapRow(ResultSet rs, int rowNum) throws SQLException {
            EduProviderBEAN eduProviderBEAN = new EduProviderBEAN();
            eduProviderBEAN.setCollegeId(rs.getString("college_id"));
            eduProviderBEAN.setUniversityId(rs.getString("university_id"));
            eduProviderBEAN.setCollege(rs.getString("college_name"));
            eduProviderBEAN.setCommission(rs.getString("commission"));
            eduProviderBEAN.setDescription(rs.getString("description"));
            eduProviderBEAN.setRemarks(rs.getString("remarks"));
            eduProviderBEAN.setWebsite(rs.getString("website"));
            eduProviderBEAN.setInputStream(rs.getBinaryStream("logo"));
            eduProviderBEAN.setAddress(rs.getString("college_address"));
            eduProviderBEAN.setInstitutionType(rs.getString("institution_type"));
            eduProviderBEAN.setCurrency(rs.getString("currency"));
            eduProviderBEAN.setAgencyStatus(rs.getString("agency_status"));
            eduProviderBEAN.setTieUpName(rs.getString("tieup_name"));
            eduProviderBEAN.setContractFrom(LocalDate.parse(rs.getString("contract_from")));
            eduProviderBEAN.setContractTo(LocalDate.parse(rs.getString("contract_to")));
            eduProviderBEAN.setContractBy(rs.getString("contract_by"));
            eduProviderBEAN.setRenewalBy(rs.getString("renewal_by"));
            eduProviderBEAN.setCreatedUser(rs.getString("created_user"));
            eduProviderBEAN.setUpdatedUser(rs.getString("updated_user"));
            eduProviderBEAN.setCreatedDate(rs.getString("created_date"));
            eduProviderBEAN.setUpdatedDate(rs.getString("updated_date"));
            eduProviderBEAN.setUniversity(rs.getString("university_name"));
            return eduProviderBEAN;
        }

    }

    @Override
    public ObservableList<EduProviderBEAN> retrieveEduProviderCourses(String collegeId) {

        mySqlJdbcTemplate = new JdbcTemplate(dataSource);
        List<EduProviderBEAN> listTemp = new ArrayList<>();
        ObservableList<EduProviderBEAN> listCourses = FXCollections.observableArrayList();
        String query = "SELECT * FROM " + TableNames.TBL_SR_EDU_COLLEGE_COURSES_OFFERED + " WHERE college_id = '" + collegeId + "'";
        listTemp = mySqlJdbcTemplate.query(query, new EduCoursesMapperTemplates());
        listCourses.addAll(listTemp);
        return listCourses;
    }

    public class EduCoursesMapperTemplates implements RowMapper<EduProviderBEAN> {

        @Override
        public EduProviderBEAN mapRow(ResultSet rs, int rowNum) throws SQLException {
            EduProviderBEAN eduProviderBEAN = new EduProviderBEAN();
            eduProviderBEAN.setCourseId(rs.getString("course_id"));
            eduProviderBEAN.setCollegeId(rs.getString("college_id"));
            //   eduProviderBEAN.setUniversityId(rs.getString("university_id"));
            eduProviderBEAN.setCourseLevel(rs.getString("program_level"));
            eduProviderBEAN.setCourseField(rs.getString("program_field"));
//                        eduProviderBEAN.setDuration(rs.getString("duration"));

            return eduProviderBEAN;
        }

    }

}
