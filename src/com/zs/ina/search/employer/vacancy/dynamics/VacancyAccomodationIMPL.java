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
package com.zs.ina.search.employer.vacancy.dynamics;

import com.zs.ina.common.constants.TableNames;
import com.zs.ina.search.common.bean.AccomBEAN;
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
public class VacancyAccomodationIMPL implements VacancyAccomodationDAO {

    static Logger logger = Logger.getLogger(VacancyAccomodationIMPL.class);
    @Autowired
    DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean insertAccomodation(AccomBEAN accomBEAN) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "INSERT INTO " + TableNames.TBL_SR_EMPLYR_VACANCY_ACCOMODATION + " (\n"
                + "	rowId,\n"
                + "	vacancy_id,\n"
                + "	accomodation\n"
                + ")\n"
                + "VALUES\n"
                + "	(?, ?, ?);";
        int row = jdbcTemplate.update(sql, new Object[]{accomBEAN.getRowId(), accomBEAN.getVacancyId(),
            accomBEAN.getAccomodation()});
        return (row > 0);
    }

    @Override
    public boolean updateAccomodation(AccomBEAN accomBEAN) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "UPDATE " + TableNames.TBL_SR_EMPLYR_VACANCY_ACCOMODATION + "\n"
                + "SET accomodation = ?\n"
                + "WHERE\n"
                + "	rowId = ?";
        int row = jdbcTemplate.update(sql, new Object[]{
            accomBEAN.getAccomodation(),
            accomBEAN.getRowId()});
        return (row > 0);
    }

    @Override
    public boolean deleteAccomodation(String rowId) {
              jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "DELETE FROM " + TableNames.TBL_SR_EMPLYR_VACANCY_ACCOMODATION + "\n"
                + " WHERE\n"
                + "vacancy_academic_reqmt_id=?";
        int row = jdbcTemplate.update(sql, new Object[]{rowId});
        return (row > 0);
    }

    @Override
    public boolean checkAccomodation(AccomBEAN accomBEAN) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<AccomBEAN> retrieveAccomodation(String vacancyId) {
        
        ObservableList<AccomBEAN> listAccomodation = FXCollections.observableArrayList();
        jdbcTemplate = new JdbcTemplate(dataSource);
        String query = "SELECT * FROM " + TableNames.TBL_SR_EMPLYR_VACANCY_ACCOMODATION + " WHERE vacancy_id = ?";
        List<AccomBEAN> temp = jdbcTemplate.query(query, new Object[]{vacancyId}, new AccomodationMapper());
        listAccomodation.addAll(temp);
        return listAccomodation;
    
    }
  public class AccomodationMapper implements RowMapper<AccomBEAN> {

        /**
         *
         * @param rs
         * @param rowNum
         * @return
         * @throws SQLException
         */
        @Override
        public AccomBEAN mapRow(ResultSet rs, int rowNum) throws SQLException {
            AccomBEAN branchBEAN = new AccomBEAN();
            branchBEAN.setRowId(rs.getString("rowId"));
            branchBEAN.setVacancyId(rs.getString("vacancy_id"));
            branchBEAN.setAccomodation(rs.getString("accomodation"));
            return branchBEAN;
        }

    }
}
