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

import com.zs.ina.search.employer.branch.dao.EmployerBranchDAO;
import com.zs.ina.search.employer.branch.dao.EmployerBranchIMPL;
import com.zs.ina.search.employer.contact.dao.EmployerContactsDAO;
import com.zs.ina.search.employer.contact.dao.EmployerContactsIMPL;
import com.zs.ina.search.employer.dao.EmployerDAO;
import com.zs.ina.search.employer.dao.EmployerIMPL;
import com.zs.ina.search.employer.fee.dao.EmployerProcessingFeeDAO;
import com.zs.ina.search.employer.fee.dao.EmployerProcessingFeeIMPL;
import com.zs.ina.search.employer.vacancy.dynamics.VacancyAcademicReqIMPL;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.zs.ina.search.employer.vacancy.dynamics.VacancyAcademicReqDAO;
import com.zs.ina.search.employer.vacancy.dynamics.VacancyAccomodationDAO;
import com.zs.ina.search.employer.vacancy.dynamics.VacancyAccomodationIMPL;
import com.zs.ina.search.employer.vacancy.dynamics.VacancyDocumentReqDAO;
import com.zs.ina.search.employer.vacancy.dynamics.VacancyDocumentReqIMPL;
import com.zs.ina.search.employer.vacancy.dynamics.VacancyExperienceReqDAO;
import com.zs.ina.search.employer.vacancy.dynamics.VacancyExperienceReqIMPL;
import com.zs.ina.search.employer.vacancy.dynamics.VacancyLanguageTestDAO;
import com.zs.ina.search.employer.vacancy.dynamics.VacancyLanguageTestIMPL;
import com.zs.ina.search.employer.vacancy.dynamics.VacancyOtherSkillDAO;
import com.zs.ina.search.employer.vacancy.dynamics.VacancyOtherSkillIMPL;
import com.zs.ina.search.employer.vacancy.dynamics.VacancyTechnicalReqDAO;
import com.zs.ina.search.employer.vacancy.dynamics.VacancyTechnicalReqIMPL;

/**
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
@Configuration
public class EmployerBeansConfig {

    @Bean
    EmployerDAO employerDAO() {
        return new EmployerIMPL();
    }

    @Bean
    EmployerContactsDAO employerContactsDAO() {
        return new EmployerContactsIMPL();
    }

    @Bean
    EmployerBranchDAO employerBranchDAO() {
        return new EmployerBranchIMPL();
    }

    @Bean
    EmployerProcessingFeeDAO employerProcessingFeeDAO() {
        return new EmployerProcessingFeeIMPL();
    }
    /* ======================== Employer Vacancy Beans ==================== */
    @Bean
    VacancyAcademicReqDAO vacancyAcademicReqDAO() {
        return new VacancyAcademicReqIMPL();
    }
    @Bean
    VacancyLanguageTestDAO vacancyLanguageTestDAO() {
        return new VacancyLanguageTestIMPL();
    }
    @Bean
    VacancyTechnicalReqDAO vacancyTechnicalReqDAO() {
        return new VacancyTechnicalReqIMPL();
    }
    @Bean
    VacancyOtherSkillDAO vacancyOtherSkillDAO() {
        return new VacancyOtherSkillIMPL();
    }
    @Bean
    VacancyDocumentReqDAO vacancyDocumentReqDAO() {
        return new VacancyDocumentReqIMPL();
    }
    @Bean
    VacancyAccomodationDAO vacancyAccomodationDAO() {
        return new VacancyAccomodationIMPL();
    }
    @Bean
    VacancyExperienceReqDAO vacancyExperienceReqDAO() {
        return new VacancyExperienceReqIMPL();
    }
}
