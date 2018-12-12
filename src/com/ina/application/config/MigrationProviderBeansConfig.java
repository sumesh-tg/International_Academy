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
package com.ina.application.config;

/**
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
import com.zs.ina.search.migration.dao.MigrationBasicDAO;
import com.zs.ina.search.migration.dao.MigrationBasicIMPL;
import com.zs.ina.search.migration.dynamics.AgePointsDAO;
import com.zs.ina.search.migration.dynamics.AgePointsIMPL;
import com.zs.ina.search.migration.dynamics.ApplicantAbilityEnglishDAO;
import com.zs.ina.search.migration.dynamics.ApplicantAbilityEnglishIMPL;
import com.zs.ina.search.migration.dynamics.EducationalSkillsDAO;
import com.zs.ina.search.migration.dynamics.EducationalSkillsIMPL;
import com.zs.ina.search.migration.dynamics.EducationalSkillsWithIeltsDAO;
import com.zs.ina.search.migration.dynamics.EducationalSkillsWithIeltsIMPL;
import com.zs.ina.search.migration.dynamics.FactorsDAO;
import com.zs.ina.search.migration.dynamics.FactorsIMPL;
import com.zs.ina.search.migration.dynamics.OverseasExperienceDAO;
import com.zs.ina.search.migration.dynamics.OverseasExperienceIMPL;
import com.zs.ina.search.migration.dynamics.SpouseAbilityEnglishDAO;
import com.zs.ina.search.migration.dynamics.SpouseAbilityEnglishIMPL;
import com.zs.ina.search.migration.dynamics.SpouseEducationSkillsDAO;
import com.zs.ina.search.migration.dynamics.SpouseEducationSkillsIMPL;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MigrationProviderBeansConfig {

    @Bean
    MigrationBasicDAO migrationBasicDAO() {
        return new MigrationBasicIMPL();
    }
    @Bean
    EducationalSkillsDAO educationalSkillsDAO() {
        return new EducationalSkillsIMPL();
    }
    @Bean
    EducationalSkillsWithIeltsDAO educationalSkillsWithIeltsDAO() {
        return new EducationalSkillsWithIeltsIMPL();
    }
    @Bean
    OverseasExperienceDAO overseasExperienceDAO() {
        return new OverseasExperienceIMPL();
    }
    @Bean
    AgePointsDAO agePointsDAO() {
        return new AgePointsIMPL();
    }
    @Bean
    ApplicantAbilityEnglishDAO applicantAbilityEnglishDAO() {
        return new ApplicantAbilityEnglishIMPL();
    }
    @Bean
    SpouseAbilityEnglishDAO spouseAbilityEnglishDAO() {
        return new SpouseAbilityEnglishIMPL();
    }
    @Bean
    FactorsDAO factorsDAO() {
        return new FactorsIMPL();
    }
    @Bean
    SpouseEducationSkillsDAO spouseEducationSkillsDAO() {
        return new SpouseEducationSkillsIMPL();
    }
}
