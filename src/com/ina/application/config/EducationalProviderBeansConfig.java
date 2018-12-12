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

import com.zs.ina.search.colleges.accomodation.dao.AccomDAO;
import com.zs.ina.search.colleges.accomodation.dao.AccomIMPL;
import com.zs.ina.search.colleges.campuses.dao.CampusDAO;
import com.zs.ina.search.colleges.campuses.dao.CampusIMPL;
import com.zs.ina.search.colleges.campuses.dao.CoursesDAO;
import com.zs.ina.search.colleges.campuses.dao.CoursesIMPL;
import com.zs.ina.search.colleges.contacts.EduContactDAO;
import com.zs.ina.search.colleges.contacts.EduContactIMPL;
import com.zs.ina.search.colleges.courses.dynamics.CourseAcademicReqDAO;
import com.zs.ina.search.colleges.courses.dynamics.CourseAcademicReqIMPL;
import com.zs.ina.search.colleges.courses.dynamics.CourseAdmissionTestDAO;
import com.zs.ina.search.colleges.courses.dynamics.CourseAdmissionTestIMPL;
import com.zs.ina.search.colleges.courses.dynamics.CourseDocumentReqDAO;
import com.zs.ina.search.colleges.courses.dynamics.CourseDocumentReqIMPL;
import com.zs.ina.search.colleges.courses.dynamics.CourseExperienceReqDAO;
import com.zs.ina.search.colleges.courses.dynamics.CourseExperienceReqIMPL;
import com.zs.ina.search.colleges.courses.dynamics.CourseLanguageTestDAO;
import com.zs.ina.search.colleges.courses.dynamics.CourseLanguageTestIMPL;
import com.zs.ina.search.colleges.dao.CourseReqmtBasicDAO;
import com.zs.ina.search.colleges.dao.CourseReqmtBasicIMPL;
import com.zs.ina.search.colleges.dao.EduProviderDAO;
import com.zs.ina.search.colleges.dao.EduProviderIMPL;
import com.zs.ina.search.colleges.processfee.ProcessFeeDAO;
import com.zs.ina.search.colleges.processfee.ProcessFeeIMPL;
import com.zs.ina.search.colleges.visadocs.VisadocDAO;
import com.zs.ina.search.colleges.visadocs.VisadocIMPL;
import com.zs.ina.search.master.university.dao.UniversityDAO;
import com.zs.ina.search.master.university.dao.UniversityIMPL;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author 100035
 */
@Configuration
public class EducationalProviderBeansConfig {
      @Bean
    EduProviderDAO eduProviderDAO() {
        return new EduProviderIMPL();
    }
      @Bean
    CoursesDAO coursesDAO() {
        return new CoursesIMPL();
    }
      @Bean
    ProcessFeeDAO processFeeDAO() {
        return new ProcessFeeIMPL();
    }
      @Bean
    VisadocDAO visadocDAO() {
        return new VisadocIMPL();
    }
      @Bean
    AccomDAO accomDAO() {
        return new AccomIMPL();
    }
      @Bean
    EduContactDAO eduContactDAO() {
        return new EduContactIMPL();
    } 
    @Bean
    CampusDAO campusDAO() {
        return new CampusIMPL();
    }
     @Bean
    UniversityDAO universityDAO() {
        return new UniversityIMPL();
    }

     @Bean
    CourseReqmtBasicDAO courseReqmtBasicDAO() {
        return new CourseReqmtBasicIMPL();
    }
   @Bean
    CourseAcademicReqDAO courseAcademicReqDAO() {
        return new CourseAcademicReqIMPL();
    } 
    
     @Bean
    CourseLanguageTestDAO courseLanguageTestDAO() {
        return new CourseLanguageTestIMPL();
    } 
      @Bean
    CourseAdmissionTestDAO courseAdmissionTestDAO() {
        return new CourseAdmissionTestIMPL();
    } 
     @Bean
    CourseExperienceReqDAO courseExperienceReqDAO() {
        return new CourseExperienceReqIMPL();
    } 
    @Bean
    CourseDocumentReqDAO  courseDocumentReqDAO() {
        return new CourseDocumentReqIMPL();
    } 
    
}
