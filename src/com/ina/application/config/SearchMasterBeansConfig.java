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

import com.zs.ina.search.master.accomodation.dao.AccomodationDAO;
import com.zs.ina.search.master.accomodation.dao.AccomodationIMPL;
import com.zs.ina.search.master.country.dao.CountryDAO;
import com.zs.ina.search.master.country.dao.CountryIMPL;
import com.zs.ina.search.master.currency.dao.CurrencyDAO;
import com.zs.ina.search.master.currency.dao.CurrencyIMPL;
import com.zs.ina.search.master.feetype.dao.FeeTypeDAO;
import com.zs.ina.search.master.feetype.dao.FeeTypeIMPL;
import com.zs.ina.search.master.intake.dao.IntakeDAO;
import com.zs.ina.search.master.intake.dao.IntakeIMPL;
import com.zs.ina.search.master.jobtitle.dao.JobTitleDAO;
import com.zs.ina.search.master.jobtitle.dao.JobTitleIMPL;
import com.zs.ina.search.master.occupation.category.dao.OccupationCategoryDAO;
import com.zs.ina.search.master.occupation.category.dao.OccupationCategoryIMPL;
import com.zs.ina.search.master.subclass.dao.SubClassDAO;
import com.zs.ina.search.master.subclass.dao.SubClassIMPL;
import com.zs.ina.search.master.duration.dao.DurationDAO;
import com.zs.ina.search.master.duration.dao.DurationIMPL;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
@Configuration
public class SearchMasterBeansConfig {

    @Bean
    SubClassDAO subClassDAO() {
        return new SubClassIMPL();
    }

    @Bean
    IntakeDAO intakeDAO() {
        return new IntakeIMPL();
    }

    @Bean
    FeeTypeDAO feeTypeDAO() {
        return new FeeTypeIMPL();
    }

    @Bean
    JobTitleDAO jobTitleDAO() {
        return new JobTitleIMPL();
    }

    @Bean
    AccomodationDAO accomodationDAO() {
        return new AccomodationIMPL();
    }

    @Bean
    OccupationCategoryDAO occupationCategoryDAO() {
        return new OccupationCategoryIMPL();
    }

    @Bean
    DurationDAO durationDAO() {
        return new DurationIMPL();
    }
    @Bean
    CurrencyDAO currencyDAO(){
        return new CurrencyIMPL();
    }
    @Bean
    CountryDAO countryDAO() {
        return new CountryIMPL();
    }
}
