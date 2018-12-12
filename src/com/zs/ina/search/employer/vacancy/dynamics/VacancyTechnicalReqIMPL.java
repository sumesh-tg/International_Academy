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
import com.zs.ina.search.common.bean.TechnicalSkillBEAN;
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
public class VacancyTechnicalReqIMPL implements VacancyTechnicalReqDAO {

    static Logger logger = Logger.getLogger(VacancyTechnicalReqIMPL.class);
    @Autowired
    DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean insertTechnicalRequirements(TechnicalSkillBEAN technicalSkillBEAN) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "INSERT INTO " + TableNames.TBL_SR_EMPLYR_TECHNICAL_SKILL + " (\n"
                + "	tech_reqmt_id,\n"
                + "	vacancy_id,\n"
                + "	technology,\n"
                + "	knowledge_level\n"
                + ")\n"
                + "VALUES\n"
                + "	(?, ?, ?, ?);";
        int row = jdbcTemplate.update(sql, new Object[]{technicalSkillBEAN.getRowId(), technicalSkillBEAN.getVacancyId(),
            technicalSkillBEAN.getTechnology(), technicalSkillBEAN.getKnowledgeLevel()});
        return (row > 0);
    }

    @Override
    public boolean delteteTechnicalRequirements(String rowId) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "DELETE FROM " + TableNames.TBL_SR_EMPLYR_TECHNICAL_SKILL + "\n"
                + " WHERE\n"
                + "tech_reqmt_id=?";
        int row = jdbcTemplate.update(sql, new Object[]{rowId});
        return (row > 0);
    }

    @Override
    public boolean updateTechnicalRequirements(TechnicalSkillBEAN technicalSkillBEAN) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "UPDATE search_emplyr_technical_skills\n"
                + "SET technology = ?,\n"
                + " knowledge_level = ?\n"
                + "WHERE\n"
                + "	tech_reqmt_id = ?";
        int row = jdbcTemplate.update(sql, new Object[]{
            technicalSkillBEAN.getTechnology(), technicalSkillBEAN.getKnowledgeLevel(), technicalSkillBEAN.getRowId()});
        return (row > 0);
    }

    @Override
    public boolean checkExistsTechnicalRequirements(TechnicalSkillBEAN technicalSkillBEAN) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<TechnicalSkillBEAN> retrieveTechnicalRequirements(String vacancyId) {
      
        ObservableList<TechnicalSkillBEAN> listTechreq = FXCollections.observableArrayList();
        jdbcTemplate = new JdbcTemplate(dataSource);
        String query = "SELECT * FROM " + TableNames.TBL_SR_EMPLYR_TECHNICAL_SKILL + " WHERE vacancy_id = ?";
        List<TechnicalSkillBEAN> temp = jdbcTemplate.query(query, new Object[]{vacancyId}, new TechnicalReqMapper());
        listTechreq.addAll(temp);
        return listTechreq;
    
    }
  public class TechnicalReqMapper implements RowMapper<TechnicalSkillBEAN> {

        /**
         *
         * @param rs
         * @param rowNum
         * @return
         * @throws SQLException
         */
        @Override
        public TechnicalSkillBEAN mapRow(ResultSet rs, int rowNum) throws SQLException {
            TechnicalSkillBEAN technicalSkillBEAN = new TechnicalSkillBEAN();
            technicalSkillBEAN.setRowId(rs.getString("tech_reqmt_id"));
            technicalSkillBEAN.setVacancyId(rs.getString("vacancy_id"));
            technicalSkillBEAN.setTechnology(rs.getString("technology"));
            technicalSkillBEAN.setKnowledgeLevel(rs.getString("knowledge_level"));
            return technicalSkillBEAN;
        }

    }
}
