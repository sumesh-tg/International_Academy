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
import com.zs.ina.search.common.bean.OtherSkillBEAN;
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
public class VacancyOtherSkillIMPL implements VacancyOtherSkillDAO {

    static Logger logger = Logger.getLogger(VacancyOtherSkillIMPL.class);
    @Autowired
    DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean insertOtherSkills(OtherSkillBEAN otherSkillBEAN) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "INSERT INTO " + TableNames.TBL_SR_EMPLYR_OTHER_SKILL + " (\n"
                + "	other_skill_id,\n"
                + "	vacancy_id,\n"
                + "	other_skill,\n"
                + "	other_type\n"
                + ")\n"
                + "VALUES\n"
                + "	(?, ?, ?, ?);";
        int row = jdbcTemplate.update(sql, new Object[]{otherSkillBEAN.getRowId(), otherSkillBEAN.getVacancyId(),
            otherSkillBEAN.getOtherSkill(), otherSkillBEAN.getOtherType()});
        return (row > 0);
    }

    @Override
    public boolean deleteOtherSkills(String rowId) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "DELETE FROM " + TableNames.TBL_SR_EMPLYR_OTHER_SKILL + "\n"
                + " WHERE\n"
                + "other_skill_id=?";
        int row = jdbcTemplate.update(sql, new Object[]{rowId});
        return (row > 0);
    }

    @Override
    public boolean updateOtherSkills(OtherSkillBEAN otherSkillBEAN) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "UPDATE " + TableNames.TBL_SR_EMPLYR_OTHER_SKILL + "\n"
                + "SET other_skill = ?,\n"
                + " other_type = ?\n"
                + "WHERE\n"
                + "	other_skill_id = ?";
        int row = jdbcTemplate.update(sql, new Object[]{
            otherSkillBEAN.getOtherSkill(), otherSkillBEAN.getOtherType(), otherSkillBEAN.getRowId()});
        return (row > 0);
    }

    @Override
    public boolean checkOtherSkillsExists(OtherSkillBEAN otherSkillBEAN) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<OtherSkillBEAN> retrieveOtherSkills(String vacancyId) {
        ObservableList<OtherSkillBEAN> listOtherSkills = FXCollections.observableArrayList();
        jdbcTemplate = new JdbcTemplate(dataSource);
        String query = "SELECT * FROM " + TableNames.TBL_SR_EMPLYR_OTHER_SKILL + " WHERE vacancy_id = ?";
        List<OtherSkillBEAN> temp = jdbcTemplate.query(query, new Object[]{vacancyId}, new OtherSkillMapper());
        listOtherSkills.addAll(temp);
        return listOtherSkills;
    
    }

    public class OtherSkillMapper implements RowMapper<OtherSkillBEAN> {

        /**
         *
         * @param rs
         * @param rowNum
         * @return
         * @throws SQLException
         */
        @Override
        public OtherSkillBEAN mapRow(ResultSet rs, int rowNum) throws SQLException {
            OtherSkillBEAN otherSkillBEAN = new OtherSkillBEAN();
            otherSkillBEAN.setRowId(rs.getString("other_skill_id"));
            otherSkillBEAN.setVacancyId(rs.getString("vacancy_id"));
            otherSkillBEAN.setOtherSkill(rs.getString("other_skill"));
            otherSkillBEAN.setOtherType(rs.getString("other_type"));
            return otherSkillBEAN;
        }

    }
}
