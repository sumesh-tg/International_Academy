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
package com.zs.ina.search.employer.branch.dao;

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
public class EmployerBranchIMPL implements EmployerBranchDAO {

    static Logger logger = Logger.getLogger(EmployerBranchIMPL.class);
    @Autowired
    DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean insertEmpBranch(EmployerBranchBEAN employerBranchBEAN) {
        int row = 0;
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "INSERT INTO search_emplyr_employer_branches (\n"
                + "	emp_branch_id,\n"
                + "	emp_branch_name,\n"
                + "	country,\n"
                + "	location,\n"
                + "	contact_address,\n"
                + "	employer_id\n"
                + ")\n"
                + "VALUES\n"
                + "	(\n"
                + "		?,\n"
                + "		?,\n"
                + "		?,\n"
                + "		?,\n"
                + "		?,\n"
                + "		?\n"
                + "	);";
        row = jdbcTemplate.update(sql, new Object[]{
            employerBranchBEAN.getEmpBranchId(), employerBranchBEAN.getEmpBranchName(), employerBranchBEAN.getCountry(),
            employerBranchBEAN.getLocation(), employerBranchBEAN.getContactAddress(), employerBranchBEAN.getEmployerId()});
        return (row > 0);
    }

    @Override
    public boolean updateEmpBranch(EmployerBranchBEAN employerBranchBEAN) {
        int row = 0;
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "UPDATE search_emplyr_employer_branches\n"
                + "SET \n"
                + " emp_branch_name = ?,\n"
                + " country = ?,\n"
                + " location = ?,\n"
                + " contact_address = ?\n"
                + "WHERE\n"
                + "emp_branch_id=?";
        row = jdbcTemplate.update(sql, new Object[]{employerBranchBEAN.getEmpBranchName(), employerBranchBEAN.getCountry(),
            employerBranchBEAN.getLocation(), employerBranchBEAN.getContactAddress(), employerBranchBEAN.getEmpBranchId()});
        return (row > 0) ? true : false;
    }

    @Override
    public boolean checkBranchExists(EmployerBranchBEAN employerBranchBEAN) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteEmpBranch(String rowId) {
        int row = 0;
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "DELETE FROM search_emplyr_employer_branches\n"
                + " WHERE\n"
                + "emp_branch_id=?";
        row = jdbcTemplate.update(sql, new Object[]{rowId});
        return (row > 0) ? true : false;
    }

    public class BranchMapper implements RowMapper<EmployerBranchBEAN> {

        /**
         *
         * @param rs
         * @param rowNum
         * @return
         * @throws SQLException
         */
        @Override
        public EmployerBranchBEAN mapRow(ResultSet rs, int rowNum) throws SQLException {
            EmployerBranchBEAN branchBEAN = new EmployerBranchBEAN();
            branchBEAN.setEmpBranchId(rs.getString("emp_branch_id"));
            branchBEAN.setEmpBranchName(rs.getString("emp_branch_name"));
            branchBEAN.setEmployerId(rs.getString("employer_id"));
            branchBEAN.setCountry(rs.getString("country"));
            branchBEAN.setLocation(rs.getString("location"));
            branchBEAN.setContactAddress(rs.getString("contact_address"));
            return branchBEAN;
        }

    }

    @Override
    public ObservableList<EmployerBranchBEAN> retrieveEmpBranch(String employerId) {
        ObservableList<EmployerBranchBEAN> listContacts = FXCollections.observableArrayList();
        jdbcTemplate = new JdbcTemplate(dataSource);
        String query = "SELECT * FROM " + TableNames.TBL_SR_EMPLYR_BRANCHES + " WHERE employer_id = ?";
        List<EmployerBranchBEAN> temp = jdbcTemplate.query(query, new Object[]{employerId}, new BranchMapper());
        listContacts.addAll(temp);
        return listContacts;
    }

}
