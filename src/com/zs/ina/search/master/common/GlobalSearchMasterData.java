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
package com.zs.ina.search.master.common;

import com.zs.ina.admin.master.admissiontest.dao.AdmissionTestMasterDAO;
import com.zs.ina.admin.master.admissiontest.dao.AdmissionTestMasterIMPL;
import com.zs.ina.admin.master.documents.dao.DocumentDAO;
import com.zs.ina.admin.master.documents.dao.DocumentIMPL;
import com.zs.ina.admin.master.documents.dao.DocumentPOJO;
import com.zs.ina.admin.master.locations.dao.CountryDAO;
import com.zs.ina.admin.master.locations.dao.CountryIMPL;
import com.zs.ina.admin.master.locations.dao.LocationsDAO;
import com.zs.ina.admin.master.locations.dao.LocationsIMPL;

import com.zs.ina.admin.master.otherskills.dao.OtherSkillsMasterDAO;
import com.zs.ina.admin.master.otherskills.dao.OtherSkillsMasterIMPL;
import com.zs.ina.admin.master.technicalskills.dao.TechnicalSkillsMasterDAO;
import com.zs.ina.admin.master.technicalskills.dao.TechnicalSkillsMasterIMPL;
import com.zs.ina.context.Context;
import com.zs.ina.search.master.accomodation.dao.AccomodationBEAN;
import com.zs.ina.search.master.accomodation.dao.AccomodationDAO;
import com.zs.ina.search.master.duration.dao.DurationBEAN;
import com.zs.ina.search.master.duration.dao.DurationDAO;
import com.zs.ina.search.master.feetype.dao.FeeTypeBEAN;
import com.zs.ina.search.master.feetype.dao.FeeTypeDAO;
import com.zs.ina.search.master.intake.dao.IntakeBEAN;
import com.zs.ina.search.master.intake.dao.IntakeDAO;
import com.zs.ina.search.master.jobtitle.dao.JobTitleBEAN;
import com.zs.ina.search.master.jobtitle.dao.JobTitleDAO;
import com.zs.ina.search.master.occupation.category.dao.OccupationCategoryBEAN;
import com.zs.ina.search.master.occupation.category.dao.OccupationCategoryDAO;
import com.zs.ina.search.master.subclass.dao.SubClassBEAN;
import com.zs.ina.search.master.subclass.dao.SubClassDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class GlobalSearchMasterData {

    ApplicationContext springAppContext = Context.getInstance().currentProfile().getSpringContext();
    JobTitleDAO jobTitleDAO = springAppContext.getBean(JobTitleDAO.class);
    OccupationCategoryDAO occupationCategoryDAO = springAppContext.getBean(OccupationCategoryDAO.class);
    SubClassDAO subClassDAO = springAppContext.getBean(SubClassDAO.class);
    FeeTypeDAO feeTypeDAO = springAppContext.getBean(FeeTypeDAO.class);
    IntakeDAO intakeDAO = springAppContext.getBean(IntakeDAO.class);
    AccomodationDAO accomodationDAO = springAppContext.getBean(AccomodationDAO.class);
    DurationDAO durationDAO = springAppContext.getBean(DurationDAO.class);
    CountryDAO countryDAO = new CountryIMPL();
    LocationsDAO locationsDAO = new LocationsIMPL();

    DocumentDAO documentDAO = new DocumentIMPL();
    AdmissionTestMasterDAO admissionTestMasterDAO = new AdmissionTestMasterIMPL();
    TechnicalSkillsMasterDAO masterTechSkillsDAO = new TechnicalSkillsMasterIMPL();
    OtherSkillsMasterDAO masterOtherSkillsDAO = new OtherSkillsMasterIMPL();

    /**
     *
     * @return
     */
    public ObservableList<String> retriveJobTitles() {
        ObservableList<String> listClass = FXCollections.observableArrayList();
        for (JobTitleBEAN bEAN : jobTitleDAO.retrieveJobTitles()) {
            listClass.add(bEAN.getJobTitle());
        }
        return listClass;
    }

    /**
     *
     * @return
     */
    public ObservableList<String> retrieveOccupationCategory() {
        ObservableList<String> listClass = FXCollections.observableArrayList();
        for (OccupationCategoryBEAN bEAN : occupationCategoryDAO.retrieveOccupationCategory()) {
            listClass.add(bEAN.getOccupationCat());
        }
        return listClass;
    }

    public ObservableList<String> retrieveAllCountries() {
        return countryDAO.retrieveAllCountries();
    }

    public ObservableList<String> retrievLocationsByCountry(String country) {
        return locationsDAO.retrievLocationsByCountry(country);
    }

    /**
     *
     * @return
     */
    public ObservableList<String> retrieveSubClass() {
        ObservableList<String> listClass = FXCollections.observableArrayList();
        for (SubClassBEAN bEAN : subClassDAO.retrieveSubClass()) {
            listClass.add(bEAN.getSubClass());
        }
        return listClass;
    }

    /**
     *
     * @return
     */
    public ObservableList<String> retrieveFeeType() {
        ObservableList<String> listFee = FXCollections.observableArrayList();
        for (FeeTypeBEAN feeTypeBEAN : feeTypeDAO.retrieveFeeType()) {
            listFee.add(feeTypeBEAN.getFeeType());
        }
        return listFee;
    }

    /**
     *
     * @return
     */
    public ObservableList<String> retrieveIntakes() {
        ObservableList<String> listIntakes = FXCollections.observableArrayList();
        for (IntakeBEAN intakeBEAN : intakeDAO.retrieveIntakes()) {
            listIntakes.add(intakeBEAN.getIntake());
        }
        return listIntakes;
    }

//    /**
//     *
//     * @return
//     */
//    public ObservableList<AccomodationBEAN> retrieveAcccomodation() {
//        return accomodationDAO.retrieveAcccomodation();
//    }
    /**
     *
     * @return
     */
    public ObservableList<DurationBEAN> retrieveDuration() {
       
        return durationDAO.retrieveDuration();

    }

    /**
     *
     * @return
     */
    public ObservableList<String> retrieveAccomodation() {
        ObservableList<String> listAccomodation = FXCollections.observableArrayList();
        for (AccomodationBEAN accomodationBEAN : accomodationDAO.retrieveAcccomodation()) {
            listAccomodation.add(accomodationBEAN.getAccomodation());
        }
        return listAccomodation;
    }

    public ObservableList<DocumentPOJO> retreiveDocument() {
        return documentDAO.retreiveDocument();
    }

    public ObservableList<String> retrieveTechnicalSkills() {
        return masterTechSkillsDAO.retrieveTechnicalSkills();
    }

    public ObservableList<String> retrieveOtherSkills() {
        return masterOtherSkillsDAO.retrieveOtherSkills();
    }

    public ObservableList<String> retrieveOtherSkillsType() {
        return masterOtherSkillsDAO.retrieveOtherSkillsType();
    }

    public ObservableList<String> retrieveAdmissionTest() {
        return admissionTestMasterDAO.retrieveAdmissionTest();
    }
}
