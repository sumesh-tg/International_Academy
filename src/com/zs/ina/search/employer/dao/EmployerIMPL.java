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
package com.zs.ina.search.employer.dao;

import com.zs.ina.common.constants.TableNames;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
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
 * @author SUMESH T.G <ZoftSolutions>
 */
public class EmployerIMPL implements EmployerDAO, EmployerVacancyDAO {

    static Logger logger = Logger.getLogger(EmployerIMPL.class);
    @Autowired
    DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    /**
     *
     * @param employerBEAN
     * @return
     */
    @Override
    public int insertEmployerBasics(EmployerBEAN employerBEAN) {
        int row = 0;
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "INSERT INTO " + TableNames.TBL_SR_EMPLYR_EMPLOYER_MASTER + " (\n"
                + "	employer_id,\n"
                + "	employer,\n"
                + "	commission,\n"
                + "	description,\n"
                + "	remarks,\n"
                + "	website,\n"
                + "	logo,\n"
                + "	address,\n"
                + "	employer_type,\n"
                + "	currency_used,\n"
                + "	agency_status,\n"
                + "	tieup_name,\n"
                + "	contract_from,\n"
                + "	contract_to,\n"
                + "	contarct_by,\n"
                + "	renewal_by,\n"
                + "	created_date,\n"
                + "	created_user\n"
                + ")\n"
                + "VALUES\n"
                + "	(\n"
                + "		?, ?, ?, ?, ?,  ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(),?\n"
                + "	);";
        System.out.println("Test Dates 1 " + employerBEAN.getContractFrom());
        System.out.println("Test Dates 2 " + employerBEAN.getContractTo());
        row = jdbcTemplate.update(sql, new Object[]{employerBEAN.getEmployerId(), employerBEAN.getEmployer(), employerBEAN.getCommission(),
            employerBEAN.getDescription(), employerBEAN.getRemarks(), employerBEAN.getWebsite(), employerBEAN.getInputStream(), employerBEAN.getAddress(),
            employerBEAN.getEmployerType(), employerBEAN.getCurrencyUsed(), employerBEAN.getAgencyStatus(),
            employerBEAN.getTieupName(), employerBEAN.getContractFrom().toString(), employerBEAN.getContractTo().toString(), employerBEAN.getContarctBy(),
            employerBEAN.getRenewalBy(), employerBEAN.getCreatedUser()});
        return row;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public int deleteEmployerBasics(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param employerBEAN
     * @return
     */
    @Override
    public int updateEmployerBasics(EmployerBEAN employerBEAN) {
        int row = 0;
        NamedParameterJdbcTemplate parameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        String sql = "UPDATE " + TableNames.TBL_SR_EMPLYR_EMPLOYER_MASTER + "\n"
                + "SET employer =:employer,\n"
                + " commission =:commission,\n"
                + " description =:description,\n"
                + " remarks =:remarks,\n"
                + " website =:website,\n"
                + " logo =:logo,\n"
                + " address =:address,\n"
                + " employer_type =:employer_type,\n"
                + " currency_used =:currency_used,\n"
                + " agency_status =:agency_status,\n"
                + " tieup_name =:tieup_name,\n"
                + " contract_from =:contract_from,\n"
                + " contract_to =:contract_to,\n"
                + " contarct_by =:contarct_by,\n"
                + " renewal_by =:renewal_by,\n"
                + " created_user =:created_user,\n"
                + " updated_date =now()\n"
                + " WHERE\n"
                + " employer_id =:employer_id";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("employer", employerBEAN.getEmployer());
        parameters.addValue("commission", employerBEAN.getCommission());
        parameters.addValue("description", employerBEAN.getDescription());
        parameters.addValue("remarks", employerBEAN.getRemarks());
        parameters.addValue("website", employerBEAN.getWebsite());
        try {
            if (employerBEAN.getInputStream() != null) {
                byte[] bytes = IOUtils.toByteArray(employerBEAN.getInputStream());
                parameters.addValue("logo", bytes, Types.BLOB);
            } else {
                parameters.addValue("logo", null);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            java.util.logging.Logger.getLogger(EmployerIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        parameters.addValue("address", employerBEAN.getAddress());
        parameters.addValue("employer_type", employerBEAN.getEmployerType());
        parameters.addValue("currency_used", employerBEAN.getCurrencyUsed());
        parameters.addValue("agency_status", employerBEAN.getAgencyStatus());
        parameters.addValue("tieup_name", employerBEAN.getTieupName());
        parameters.addValue("contract_from", employerBEAN.getContractFrom().toString());
        parameters.addValue("contract_to", employerBEAN.getContractTo().toString());
        parameters.addValue("contarct_by", employerBEAN.getContarctBy());
        parameters.addValue("renewal_by", employerBEAN.getRenewalBy());
        parameters.addValue("created_user", employerBEAN.getCreatedUser());
        parameters.addValue("employer_id", employerBEAN.getEmployerId());
        row = parameterJdbcTemplate.update(sql, parameters);
        return row;
    }

    /**
     *
     * @return
     */
    @Override
    public ObservableList<EmployerBEAN> retrieveEmployerBasics() {
        ObservableList<EmployerBEAN> listContacts = FXCollections.observableArrayList();
        jdbcTemplate = new JdbcTemplate(dataSource);
        String query = "SELECT * FROM " + TableNames.TBL_SR_EMPLYR_EMPLOYER_MASTER + "  ";
        List<EmployerBEAN> temp = jdbcTemplate.query(query, new EmployerBasicsMapper());
        listContacts.addAll(temp);
        return listContacts;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public ObservableList<EmployerBEAN> retrieveEmployerBasics(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param employerBEAN
     * @return
     */
    @Override
    public boolean checkEmployerExists(EmployerBEAN employerBEAN) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insertEmployerVacancy(EmployerBEAN employerBEAN) {
        int row = 0;
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "INSERT INTO search_emplyr_vacancy (\n"
                + "	vacancy_id,\n"
                + "	employer_id,\n"
                + "	employment_sector,\n"
                + "	job,\n"
                + "	no_of_vacancies,\n"
                + "	vacancy_status,\n"
                + "	gender,\n"
                + "	age_from,\n"
                + "	age_to,\n"
                + "	marital_status,\n"
                + "	salary_from_currency,\n"
                + "	salary_from_amount,\n"
                + "	salary_to_currency,\n"
                + "	salary_to_amount,\n"
                + "	sslc_board,\n"
                + "	sslc_medium,\n"
                + "	plus2_board,\n"
                + "	plus2_medium,\n"
                + "	created_user,\n"
                + "	expiry_date,\n"
                + "	eng_medium,\n"
                + "	eng_duration,\n"
                + "	currency,\n"
                + "	created_date\n"
                + ")\n"
                + "VALUES\n"
                + "	(\n"
                + "		?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW()\n"
                + "	);";
        row = jdbcTemplate.update(sql, new Object[]{employerBEAN.getVacancyId(), employerBEAN.getEmployerId(), employerBEAN.getEmploymentSector(),
            employerBEAN.getJob(), employerBEAN.getNoOfVacancies(), employerBEAN.getVacancyStatus(), employerBEAN.getGender(), employerBEAN.getAgeFrom(),
            employerBEAN.getAgeTo(), employerBEAN.getMaritalStatus(), employerBEAN.getSalaryFromCurrency(), employerBEAN.getSalaryFromAmount(),
            employerBEAN.getSalaryToCurrency(), employerBEAN.getSalaryToAmount(), employerBEAN.getSslcBoard(), employerBEAN.getSslcMedium(), employerBEAN.getPlus2Board(), employerBEAN.getPlus2Medium(),
            employerBEAN.getCreatedUser(), employerBEAN.getExpiryDate().toString(), employerBEAN.getEngMediumBoard(), employerBEAN.getEngMediumDuration(), employerBEAN.getCurrency()});
        return (row > 0);

    }

    @Override
    public boolean updateEmployerVacancy(EmployerBEAN employerBEAN) {
        int row = 0;
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "UPDATE search_emplyr_vacancy\n"
                + "SET employment_sector =  ?,\n"
                + " job =  ?,\n"
                + " no_of_vacancies =  ?,\n"
                + " vacancy_status =  ?,\n"
                + " gender =  ?,\n"
                + " age_from =  ?,\n"
                + " age_to =  ?,\n"
                + " marital_status =  ?,\n"
                + " salary_from_currency =  ?,\n"
                + " salary_from_amount =  ?,\n"
                + " salary_to_currency =  ?,\n"
                + " salary_to_amount =  ?,\n"
                + " sslc_board =  ?,\n"
                + " sslc_medium =  ?,\n"
                + " plus2_board =  ?,\n"
                + " plus2_medium =  ?,\n"
                + " updated_user =  ?,\n"
                + " expiry_date =  ?,\n"
                + " eng_medium =  ?,\n"
                + " eng_duration =  ?,\n"
                + " currency =  ?,\n"
                + " updated_date =  NOW()\n"
                + "WHERE\n"
                + "	vacancy_id =  ?";
        row = jdbcTemplate.update(sql, new Object[]{employerBEAN.getEmploymentSector(),
            employerBEAN.getJob(), employerBEAN.getNoOfVacancies(), employerBEAN.getVacancyStatus(), employerBEAN.getGender(), employerBEAN.getAgeFrom(),
            employerBEAN.getAgeTo(), employerBEAN.getMaritalStatus(), employerBEAN.getSalaryFromCurrency(), employerBEAN.getSalaryFromAmount(),
            employerBEAN.getSalaryToCurrency(), employerBEAN.getSalaryToAmount(),
            employerBEAN.getSslcBoard(), employerBEAN.getSslcMedium(),
            employerBEAN.getPlus2Board(), employerBEAN.getPlus2Medium(),
            employerBEAN.getUpdatedUser(), employerBEAN.getExpiryDate().toString(), employerBEAN.getEngMediumBoard(), employerBEAN.getEngMediumDuration(), employerBEAN.getCurrency(), employerBEAN.getVacancyId()});
        return (row > 0);

    }

    @Override
    public boolean deleteEmployerVacancy(String rowId) {

        int row = 0;
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "DELETE FROM " + TableNames.TBL_SR_EMPLYR_VACANCY + " \n"
                + " WHERE\n"
                + "emp_branch_id=?";
        row = jdbcTemplate.update(sql, new Object[]{rowId});
        return (row > 0);

    }

    @Override
    public boolean checkEmployerVacancyExists(EmployerBEAN employerBEAN) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<EmployerBEAN> retrieveEmployerVacancy(String employerId) {

        ObservableList<EmployerBEAN> listVacancies = FXCollections.observableArrayList();
        jdbcTemplate = new JdbcTemplate(dataSource);
        String query = "SELECT * FROM " + TableNames.TBL_SR_EMPLYR_VACANCY + " WHERE employer_id = ?";
        List<EmployerBEAN> temp = jdbcTemplate.query(query, new Object[]{employerId.trim()}, new EmployeeVacancyMapper());
        listVacancies.addAll(temp);
        return listVacancies;

    }

    public class EmployeeVacancyMapper implements RowMapper<EmployerBEAN> {

        /**
         *
         * @param rs
         * @param rowNum
         * @return
         * @throws SQLException
         */
        @Override
        public EmployerBEAN mapRow(ResultSet rs, int rowNum) throws SQLException {
            EmployerBEAN vacancyBEAN = new EmployerBEAN();
            vacancyBEAN.setVacancyId(rs.getString("vacancy_id"));
            vacancyBEAN.setEmployerId(rs.getString("employer_id"));
            vacancyBEAN.setEmploymentSector(rs.getString("employment_sector"));
            vacancyBEAN.setJob(rs.getString("job"));
            vacancyBEAN.setNoOfVacancies(rs.getString("no_of_vacancies"));
            vacancyBEAN.setVacancyStatus(rs.getString("vacancy_status"));
            vacancyBEAN.setGender(rs.getString("gender"));
            vacancyBEAN.setAgeFrom(rs.getString("age_from"));
            vacancyBEAN.setAgeTo(rs.getString("age_to"));
            vacancyBEAN.setMaritalStatus(rs.getString("marital_status"));
            vacancyBEAN.setSalaryFromCurrency(rs.getString("salary_from_currency"));
            vacancyBEAN.setSalaryToCurrency(rs.getString("salary_to_currency"));
            vacancyBEAN.setSalaryFromAmount(rs.getString("salary_from_amount"));
            vacancyBEAN.setSalaryToAmount(rs.getString("salary_to_amount"));
            vacancyBEAN.setSslcBoard(rs.getString("sslc_board"));
            vacancyBEAN.setSslcMedium(rs.getString("sslc_medium"));
            vacancyBEAN.setPlus2Board(rs.getString("plus2_board"));
            vacancyBEAN.setPlus2Medium(rs.getString("plus2_medium"));
            vacancyBEAN.setCreatedUser(rs.getString("created_user"));
            vacancyBEAN.setUpdatedUser(rs.getString("updated_user"));
            vacancyBEAN.setCreatedDate(rs.getString("created_date"));
            vacancyBEAN.setUpdatedDate(rs.getString("updated_date"));
            vacancyBEAN.setCurrency(rs.getString("currency"));
            vacancyBEAN.setEngMediumBoard(rs.getString("eng_medium"));
            vacancyBEAN.setEngMediumDuration(rs.getString("eng_duration"));
            if (rs.getString("expiry_date") != null) {
                vacancyBEAN.setExpiryDate(LocalDate.parse(rs.getString("expiry_date")));
            }
            return vacancyBEAN;
        }

    }

    public class EmployerBasicsMapper implements RowMapper<EmployerBEAN> {

        /**
         *
         * @param rs
         * @param rowNum
         * @return employerBasicsBEAN Employer Basic Information
         * @throws SQLException
         */
        @Override
        public EmployerBEAN mapRow(ResultSet rs, int rowNum) throws SQLException {
            EmployerBEAN employerBasicsBEAN = new EmployerBEAN();
            employerBasicsBEAN.setEmployerId(rs.getString("employer_id"));
            employerBasicsBEAN.setEmployer(rs.getString("employer"));
            employerBasicsBEAN.setCommission(rs.getString("commission"));
            employerBasicsBEAN.setDescription(rs.getString("description"));
            employerBasicsBEAN.setRemarks(rs.getString("remarks"));
            employerBasicsBEAN.setWebsite(rs.getString("website"));
//            employerBasicsBEAN.setLogo(rs.getString("logo"));
            employerBasicsBEAN.setInputStream(rs.getBinaryStream("logo"));
            employerBasicsBEAN.setAddress(rs.getString("address"));
            employerBasicsBEAN.setEmployerType(rs.getString("employer_type"));
            employerBasicsBEAN.setCurrencyUsed(rs.getString("currency_used"));
            employerBasicsBEAN.setAgencyStatus(rs.getString("agency_status"));
            employerBasicsBEAN.setTieupName(rs.getString("tieup_name"));
            employerBasicsBEAN.setContractFrom(LocalDate.parse(rs.getString("contract_from")));
            employerBasicsBEAN.setContractTo(LocalDate.parse(rs.getString("contract_to")));
            employerBasicsBEAN.setContarctBy(rs.getString("contarct_by"));
            employerBasicsBEAN.setRenewalBy(rs.getString("renewal_by"));
            employerBasicsBEAN.setCreatedUser(rs.getString("created_user"));
            employerBasicsBEAN.setUpdatedUser(rs.getString("updated_user"));
            employerBasicsBEAN.setCreatedDate(rs.getString("created_date"));
            employerBasicsBEAN.setUpdatedDate(rs.getString("updated_date"));
            return employerBasicsBEAN;
        }

    }
}
