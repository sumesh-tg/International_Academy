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
package com.ina.application.config;

import com.zs.ina.search.training.courses.TrainReqmtBasicDAO;
import com.zs.ina.search.training.courses.TrainReqmtBasicIMPL;
import com.zs.ina.search.training.courses.dynamics.TrainCourseAcademicReqmtDAO;
import com.zs.ina.search.training.courses.dynamics.TrainCourseAcademicReqmtIMPL;
import com.zs.ina.search.training.courses.dynamics.TrainCourseAdmissionTestDAO;
import com.zs.ina.search.training.courses.dynamics.TrainCourseAdmissionTestIMPL;
import com.zs.ina.search.training.courses.dynamics.TrainCourseDocumentReqDAO;
import com.zs.ina.search.training.courses.dynamics.TrainCourseDocumentReqIMPL;
import com.zs.ina.search.training.courses.dynamics.TrainCourseExperienceReqDAO;
import com.zs.ina.search.training.courses.dynamics.TrainCourseExperienceReqIMPL;
import com.zs.ina.search.training.courses.dynamics.TrainCourseLanguageTestDAO;
import com.zs.ina.search.training.courses.dynamics.TrainCourseLanguageTestIMPL;
import com.zs.ina.search.training.dao.TrainingDAO;
import com.zs.ina.search.training.dao.TrainingIMPL;
import com.zs.ina.search.training.dynamics.TrainInstallmentIMPL;
import com.zs.ina.search.training.dynamics.TrainCampusDAO;
import com.zs.ina.search.training.dynamics.TrainCampusIMPL;
import com.zs.ina.search.training.dynamics.TrainContactDAO;
import com.zs.ina.search.training.dynamics.TrainContactIMPL;
import com.zs.ina.search.training.dynamics.TrainProcessFeeDAO;
import com.zs.ina.search.training.dynamics.TrainProcessFeeIMPL;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.zs.ina.search.training.dynamics.TrainInstallmentDAO;

/**
 *
 * @author 100035
 */
@Configuration
public class TrainingCourseBeansConfig {

    @Bean
    TrainingDAO trainingDAO() {
        return new TrainingIMPL();
    }

    @Bean
    TrainReqmtBasicDAO trainReqmtBasicDAO() {
        return new TrainReqmtBasicIMPL();
    }

      @Bean
    TrainCourseAcademicReqmtDAO tcourseAcademicReqmtDAO() {
        return new TrainCourseAcademicReqmtIMPL();
    }
      @Bean
    TrainCourseLanguageTestDAO tcourseLanguageTestDAO() {
        return new TrainCourseLanguageTestIMPL();
    }
    
      @Bean
    TrainCourseAdmissionTestDAO tcourseAdmissionTestDAO() {
        return new TrainCourseAdmissionTestIMPL();
    }
    
    @Bean
    TrainCourseExperienceReqDAO texperienceReqDAO() {
        return new TrainCourseExperienceReqIMPL();
    }
    
    
  @Bean
    TrainCourseDocumentReqDAO tcourseDocumentReqDAO() {
        return new TrainCourseDocumentReqIMPL();
    } 
    
    @Bean
    TrainInstallmentDAO trainInstallmentDAO() {
        return new TrainInstallmentIMPL();
    }

    @Bean
    TrainCampusDAO trainCampusDAO() {
        return new TrainCampusIMPL();
    }

    @Bean
    TrainContactDAO trainContactDAO() {
        return new TrainContactIMPL();
    }

    @Bean
    TrainProcessFeeDAO trainProcessFeeDAO() {
        return new TrainProcessFeeIMPL();
    }

}
