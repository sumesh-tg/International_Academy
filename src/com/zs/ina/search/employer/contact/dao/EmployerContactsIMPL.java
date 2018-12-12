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
package com.zs.ina.search.employer.contact.dao;

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
public class EmployerContactsIMPL implements EmployerContactsDAO {

    static Logger logger = Logger.getLogger(EmployerContactsIMPL.class);
    @Autowired
    DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    /**
     *
     * @param employerContactsBEAN
     * @return insertion status as boolean
     */
    @Override
    public boolean insertContactDetails(EmployerContactsBEAN employerContactsBEAN) {
        int row = 0;
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "INSERT INTO search_emplyr_head_contact (\n"
                + "	employer_contact_id,\n"
                + "	employer_id,\n"
                + "	contact_name,\n"
                + "	department,\n"
                + "	phone_no,\n"
                + "	code,\n"
                + "	email\n"
                + ")\n"
                + "VALUES\n"
                + "	(\n"
                + "		?,\n"
                + "		?,\n"
                + "		?,\n"
                + "		?,\n"
                + "		?,\n"
                + "		?,\n"
                + "		?\n"
                + "	);";
        row = jdbcTemplate.update(sql, new Object[]{employerContactsBEAN.getEmployerContactId(),
            employerContactsBEAN.getEmployerId(), employerContactsBEAN.getContactName(),
            employerContactsBEAN.getDepartment(),
            employerContactsBEAN.getPhoneNo(),
            employerContactsBEAN.getStdIsd(),
            employerContactsBEAN.getEmail()});
        return (row > 0);
    }

    /**
     *
     * @param id ContactId
     * @return Is Deleted Or Not as boolean
     */
    @Override
    public boolean deleteContactDetails(String id) {
        int row = 0;
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "DELETE FROM search_emplyr_head_contact\n"
                + " WHERE\n"
                + "	employer_contact_id = ?";
        row = jdbcTemplate.update(sql, new Object[]{id});
        return (row > 0);
    }

    /**
     *
     * @param employerContactsBEAN updation bean
     * @return updation status as boolean
     */
    @Override
    public boolean updateContactDetails(EmployerContactsBEAN employerContactsBEAN) {
        int row = 0;
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "UPDATE search_emplyr_head_contact\n"
                + "SET contact_name = ?,\n"
                + " department = ?,\n"
                + " phone_no = ?,\n"
                + " code = ?,\n"
                + " email = ?\n"
                + "WHERE\n"
                + "	employer_contact_id = ?";
        row = jdbcTemplate.update(sql, new Object[]{employerContactsBEAN.getContactName(),
            employerContactsBEAN.getDepartment(), employerContactsBEAN.getPhoneNo(),
            employerContactsBEAN.getStdIsd(),
            employerContactsBEAN.getEmail(),
            employerContactsBEAN.getEmployerContactId()});
        return (row > 0);
    }

    /**
     *
     * @return all the contacts details
     */
    @Override
    public ObservableList<EmployerContactsBEAN> retrieveContactDetails(String employerId) {
        ObservableList<EmployerContactsBEAN> listContacts = FXCollections.observableArrayList();
        jdbcTemplate = new JdbcTemplate(dataSource);
        String query = "SELECT * FROM " + TableNames.TBL_SR_EMPLYR_HEAD_CONTACT + " WHERE employer_id = ?";
        List<EmployerContactsBEAN> temp = jdbcTemplate.query(query, new Object[]{employerId}, new HeadContactsMapper());
        listContacts.addAll(temp);
        return listContacts;
    }

    /**
     * Mapper Class for Employer contacts
     */
    public class HeadContactsMapper implements RowMapper<EmployerContactsBEAN> {

        /**
         *
         * @param rs
         * @param rowNum
         * @return
         * @throws SQLException
         */
        @Override
        public EmployerContactsBEAN mapRow(ResultSet rs, int rowNum) throws SQLException {
            EmployerContactsBEAN contactsBEAN = new EmployerContactsBEAN();
            contactsBEAN.setEmployerContactId(rs.getString("employer_contact_id"));
            contactsBEAN.setEmployerId(rs.getString("employer_id"));
            contactsBEAN.setContactName(rs.getString("contact_name"));
            contactsBEAN.setDepartment(rs.getString("department"));
            contactsBEAN.setPhoneNo(rs.getString("phone_no"));
            contactsBEAN.setStdIsd(rs.getString("code"));
            contactsBEAN.setEmail(rs.getString("email"));
            return contactsBEAN;
        }

    }

    /**
     *
     * @param employerContactsBEAN
     * @return
     */
    @Override
    public boolean checkContactExists(EmployerContactsBEAN employerContactsBEAN) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
