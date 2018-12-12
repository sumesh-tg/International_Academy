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
package com.zs.ina.search.colleges.dao;

import java.io.InputStream;
import java.time.LocalDate;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author 100035
 */
public class EduProviderBEAN {

    private final StringProperty collegeId = new SimpleStringProperty();
    private final StringProperty college = new SimpleStringProperty();
    private final StringProperty universityId = new SimpleStringProperty();
    private final StringProperty university = new SimpleStringProperty();
    private final StringProperty description = new SimpleStringProperty();
    private final StringProperty remarks = new SimpleStringProperty();
    private final StringProperty address = new SimpleStringProperty();
    private final StringProperty logo = new SimpleStringProperty();
    private final StringProperty website = new SimpleStringProperty();
    private final StringProperty commission = new SimpleStringProperty();
    private final StringProperty currency = new SimpleStringProperty();
    private final StringProperty institutionType = new SimpleStringProperty();
    private final StringProperty agencyStatus = new SimpleStringProperty();
    private final StringProperty tieUpName = new SimpleStringProperty();
    private final StringProperty contractBy = new SimpleStringProperty();
    private final ObjectProperty<LocalDate> contractFrom = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDate> contractTo = new SimpleObjectProperty<>();
    private final StringProperty renewalBy = new SimpleStringProperty();
    private final StringProperty createdDate = new SimpleStringProperty();
    private final StringProperty updatedDate = new SimpleStringProperty();
    private final StringProperty createdUser = new SimpleStringProperty();
    private final StringProperty updatedUser = new SimpleStringProperty();

    //========== Course Requirements ============ //
    private final StringProperty rowId = new SimpleStringProperty();
    private final StringProperty courseId = new SimpleStringProperty();
    private final StringProperty gender = new SimpleStringProperty();

    private final StringProperty ageFrom = new SimpleStringProperty();
    private final StringProperty ageTo = new SimpleStringProperty();
    private final StringProperty maritalStatus = new SimpleStringProperty();
    private final StringProperty sslcBoard = new SimpleStringProperty();
    private final StringProperty sslcMedium = new SimpleStringProperty();
    private final StringProperty plus2Board = new SimpleStringProperty();
    private final StringProperty plus2Medium = new SimpleStringProperty();
    private final StringProperty engMediumBorad = new SimpleStringProperty();
    private final StringProperty engMeduimDuration = new SimpleStringProperty();
    private final StringProperty feeCurrency = new SimpleStringProperty();
    private final StringProperty applicationFee = new SimpleStringProperty();
    private final StringProperty annualFee = new SimpleStringProperty();
    private final StringProperty semesterFee = new SimpleStringProperty();
    private final StringProperty otherFee = new SimpleStringProperty();
    private final StringProperty intake = new SimpleStringProperty();
    private final StringProperty career = new SimpleStringProperty();
    private final StringProperty advantage = new SimpleStringProperty();
    private final StringProperty courseLevel = new SimpleStringProperty();
    private final StringProperty courseField = new SimpleStringProperty();
    private final StringProperty levelAndField = new SimpleStringProperty();
    private final StringProperty campuses = new SimpleStringProperty();
    
    
     /* =============== For Search Based On PROGRAMS ================ */
    private final StringProperty campusId = new SimpleStringProperty();
    private final StringProperty campus = new SimpleStringProperty();
    private final StringProperty country = new SimpleStringProperty();
    private final StringProperty location = new SimpleStringProperty();
    private final StringProperty campusAddress = new SimpleStringProperty();

    public String getCampusAddress() {
        return campusAddress.get();
    }

    public void setCampusAddress(String value) {
        campusAddress.set(value);
    }

    public StringProperty campusAddressProperty() {
        return campusAddress;
    }

    public String getLocation() {
        return location.get();
    }

    public void setLocation(String value) {
        location.set(value);
    }

    public StringProperty locationProperty() {
        return location;
    }

    public String getCountry() {
        return country.get();
    }

    public void setCountry(String value) {
        country.set(value);
    }

    public StringProperty countryProperty() {
        return country;
    }

    public String getCampus() {
        return campus.get();
    }

    public void setCampus(String value) {
        campus.set(value);
    }

    public StringProperty campusProperty() {
        return campus;
    }
    

    public String getCampusId() {
        return campusId.get();
    }

    public void setCampusId(String value) {
        campusId.set(value);
    }

    public StringProperty campusIdProperty() {
        return campusId;
    }
   
    public String getCampuses() {
        return campuses.get();
    }

    public void setCampuses(String value) {
        campuses.set(value);
    }

    public StringProperty campusesProperty() {
        return campuses;
    }
  
    public String getLevelAndField() {
        return levelAndField.get();
    }

    public void setLevelAndField(String value) {
        levelAndField.set(value);
    }

    public StringProperty levelAndFieldProperty() {
        return levelAndField;
    }
    

    public String getCourseField() {
        return courseField.get();
    }

    public void setCourseField(String value) {
        courseField.set(value);
    }

    public StringProperty courseFieldProperty() {
        return courseField;
    }

    public String getCourseLevel() {
        return courseLevel.get();
    }

    public void setCourseLevel(String value) {
        courseLevel.set(value);
    }

    public StringProperty courseLevelProperty() {
        return courseLevel;
    }

    public String getOtherFee() {
        return otherFee.get();
    }

    public void setOtherFee(String value) {
        otherFee.set(value);
    }

    public StringProperty otherFeeProperty() {
        return otherFee;
    }

    public String getSemesterFee() {
        return semesterFee.get();
    }

    public void setSemesterFee(String value) {
        semesterFee.set(value);
    }

    public StringProperty semesterFeeProperty() {
        return semesterFee;
    }

    public String getAnnualFee() {
        return annualFee.get();
    }

    public void setAnnualFee(String value) {
        annualFee.set(value);
    }

    public StringProperty annualFeeProperty() {
        return annualFee;
    }

    public String getApplicationFee() {
        return applicationFee.get();
    }

    public void setApplicationFee(String value) {
        applicationFee.set(value);
    }

    public StringProperty applicationFeeProperty() {
        return applicationFee;
    }

    public String getFeeCurrency() {
        return feeCurrency.get();
    }

    public void setFeeCurrency(String value) {
        feeCurrency.set(value);
    }

    public StringProperty feeCurrencyProperty() {
        return feeCurrency;
    }

    public String getGender() {
        return gender.get();
    }

    public void setGender(String value) {
        gender.set(value);
    }

    public StringProperty genderProperty() {
        return gender;
    }

    public String getRowId() {
        return rowId.get();
    }

    public void setRowId(String value) {
        rowId.set(value);
    }

    public StringProperty rowIdProperty() {
        return rowId;
    }

    public String getEngMeduimDuration() {
        return engMeduimDuration.get();
    }

    public void setEngMeduimDuration(String value) {
        engMeduimDuration.set(value);
    }

    public StringProperty engMeduimDurationProperty() {
        return engMeduimDuration;
    }

    public String getEngMediumBorad() {
        return engMediumBorad.get();
    }

    public void setEngMediumBorad(String value) {
        engMediumBorad.set(value);
    }

    public StringProperty engMediumBoradProperty() {
        return engMediumBorad;
    }

    public String getPlus2Medium() {
        return plus2Medium.get();
    }

    public void setPlus2Medium(String value) {
        plus2Medium.set(value);
    }

    public StringProperty plus2MediumProperty() {
        return plus2Medium;
    }

    public String getPlus2Board() {
        return plus2Board.get();
    }

    public void setPlus2Board(String value) {
        plus2Board.set(value);
    }

    public StringProperty plus2BoardProperty() {
        return plus2Board;
    }

    public String getSslcMedium() {
        return sslcMedium.get();
    }

    public void setSslcMedium(String value) {
        sslcMedium.set(value);
    }

    public StringProperty sslcMediumProperty() {
        return sslcMedium;
    }

    public String getSslcBoard() {
        return sslcBoard.get();
    }

    public void setSslcBoard(String value) {
        sslcBoard.set(value);
    }

    public StringProperty sslcBoardProperty() {
        return sslcBoard;
    }

    public String getAdvantage() {
        return advantage.get();
    }

    public void setAdvantage(String value) {
        advantage.set(value);
    }

    public StringProperty advantageProperty() {
        return advantage;
    }

    public String getCareer() {
        return career.get();
    }

    public void setCareer(String value) {
        career.set(value);
    }

    public StringProperty careerProperty() {
        return career;
    }

    public String getIntake() {
        return intake.get();
    }

    public void setIntake(String value) {
        intake.set(value);
    }

    public StringProperty intakeProperty() {
        return intake;
    }

    public String getMaritalStatus() {
        return maritalStatus.get();
    }

    public void setMaritalStatus(String value) {
        maritalStatus.set(value);
    }

    public StringProperty maritalStatusProperty() {
        return maritalStatus;
    }

    public String getAgeTo() {
        return ageTo.get();
    }

    public void setAgeTo(String value) {
        ageTo.set(value);
    }

    public StringProperty ageToProperty() {
        return ageTo;
    }

    public String getAgeFrom() {
        return ageFrom.get();
    }

    public void setAgeFrom(String value) {
        ageFrom.set(value);
    }

    public StringProperty ageFromProperty() {
        return ageFrom;
    }

    public String getCourseId() {
        return courseId.get();
    }

    public void setCourseId(String value) {
        courseId.set(value);
    }

    public StringProperty courseIdProperty() {
        return courseId;
    }

    InputStream inputStream = null;

    public String getUniversity() {
        return university.get();
    }

    public void setUniversity(String value) {
        university.set(value);
    }

    public StringProperty universityProperty() {
        return university;
    }

    public String getUpdatedDate() {
        return updatedDate.get();
    }

    public void setUpdatedDate(String value) {
        updatedDate.set(value);
    }

    public StringProperty updatedDateProperty() {
        return updatedDate;
    }

    public String getCreatedDate() {
        return createdDate.get();
    }

    public void setCreatedDate(String value) {
        createdDate.set(value);
    }

    public StringProperty createdDateProperty() {
        return createdDate;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    /**
     *
     * @return
     */
    public LocalDate getContractTo() {
        return contractTo.get();
    }

    /**
     *
     * @param value
     */
    public void setContractTo(LocalDate value) {
        contractTo.set(value);
    }

    /**
     *
     * @return
     */
    public ObjectProperty contractToProperty() {
        return contractTo;
    }

    /**
     *
     * @return
     */
    public LocalDate getContractFrom() {
        return contractFrom.get();
    }

    /**
     *
     * @param value
     */
    public void setContractFrom(LocalDate value) {
        contractFrom.set(value);
    }

    /**
     *
     * @return
     */
    public ObjectProperty contractFromProperty() {
        return contractFrom;
    }

    /**
     *
     * @return
     */
    public String getWebsite() {
        return website.get();
    }

    /**
     *
     * @param value
     */
    public void setWebsite(String value) {
        website.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty websiteProperty() {
        return website;
    }

    /**
     *
     * @return
     */
    public String getRenewalBy() {
        return renewalBy.get();
    }

    /**
     *
     * @param value
     */
    public void setRenewalBy(String value) {
        renewalBy.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty renewalByProperty() {
        return renewalBy;
    }

    /**
     *
     * @return
     */
    public String getCreatedUser() {
        return createdUser.get();
    }

    /**
     *
     * @param value
     */
    public void setCreatedUser(String value) {
        createdUser.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty createdUserProperty() {
        return createdUser;
    }

    /**
     *
     * @return
     */
    public String getUpdatedUser() {
        return updatedUser.get();
    }

    /**
     *
     * @param value
     */
    public void setUpdatedUser(String value) {
        updatedUser.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty updatedUserProperty() {
        return updatedUser;
    }

    /**
     *
     * @return
     */
    public String getContractBy() {
        return contractBy.get();
    }

    /**
     *
     * @param value
     */
    public void setContractBy(String value) {
        contractBy.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty contractByProperty() {
        return contractBy;
    }

    /**
     *
     * @return
     */
    public String getTieUpName() {
        return tieUpName.get();
    }

    /**
     *
     * @param value
     */
    public void setTieUpName(String value) {
        tieUpName.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty tieUpNameProperty() {
        return tieUpName;
    }

    /**
     *
     * @return
     */
    public String getAgencyStatus() {
        return agencyStatus.get();
    }

    /**
     *
     * @param value
     */
    public void setAgencyStatus(String value) {
        agencyStatus.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty agencyStatusProperty() {
        return agencyStatus;
    }

    /**
     *
     * @return
     */
    public String getInstitutionType() {
        return institutionType.get();
    }

    /**
     *
     * @param value
     */
    public void setInstitutionType(String value) {
        institutionType.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty institutionTypeProperty() {
        return institutionType;
    }

    /**
     *
     * @return
     */
    public String getCurrency() {
        return currency.get();
    }

    /**
     *
     * @param value
     */
    public void setCurrency(String value) {
        currency.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty currencyProperty() {
        return currency;
    }

    /**
     *
     * @return
     */
    public String getCommission() {
        return commission.get();
    }

    /**
     *
     * @param value
     */
    public void setCommission(String value) {
        commission.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty commissionProperty() {
        return commission;
    }

    /**
     *
     * @return
     */
    public String getLogo() {
        return logo.get();
    }

    /**
     *
     * @param value
     */
    public void setLogo(String value) {
        logo.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty logoProperty() {
        return logo;
    }

    /**
     *
     * @return
     */
    public String getAddress() {
        return address.get();
    }

    /**
     *
     * @param value
     */
    public void setAddress(String value) {
        address.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty addressProperty() {
        return address;
    }

    /**
     *
     * @return
     */
    public String getRemarks() {
        return remarks.get();
    }

    /**
     *
     * @param value
     */
    public void setRemarks(String value) {
        remarks.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty remarksProperty() {
        return remarks;
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return description.get();
    }

    /**
     *
     * @param value
     */
    public void setDescription(String value) {
        description.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty descriptionProperty() {
        return description;
    }

    /**
     *
     * @return
     */
    public String getUniversityId() {
        return universityId.get();
    }

    /**
     *
     * @param value
     */
    public void setUniversityId(String value) {
        universityId.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty universityIdProperty() {
        return universityId;
    }

    /**
     *
     * @return
     */
    public String getCollege() {
        return college.get();
    }

    /**
     *
     * @param value
     */
    public void setCollege(String value) {
        college.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty collegeProperty() {
        return college;
    }

    /**
     *
     * @return
     */
    public String getCollegeId() {
        return collegeId.get();
    }

    /**
     *
     * @param value
     */
    public void setCollegeId(String value) {
        collegeId.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty collegeIdProperty() {
        return collegeId;
    }

    @Override
    public String toString() {
        return "EduProviderBEAN{" + "collegeId=" + collegeId + ", college=" + college + ", universityId=" + universityId + ", university=" + university + ", description=" + description + ", remarks=" + remarks + ", address=" + address + ", logo=" + logo + ", website=" + website + ", commission=" + commission + ", currency=" + currency + ", institutionType=" + institutionType + ", agencyStatus=" + agencyStatus + ", tieUpName=" + tieUpName + ", contractBy=" + contractBy + ", contractFrom=" + contractFrom + ", contractTo=" + contractTo + ", renewalBy=" + renewalBy + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + ", createdUser=" + createdUser + ", updatedUser=" + updatedUser + ", rowId=" + rowId + ", courseId=" + courseId + ", gender=" + gender + ", ageFrom=" + ageFrom + ", ageTo=" + ageTo + ", maritalStatus=" + maritalStatus + ", sslcBoard=" + sslcBoard + ", sslcMedium=" + sslcMedium + ", plus2Board=" + plus2Board + ", plus2Medium=" + plus2Medium + ", engMediumBorad=" + engMediumBorad + ", engMeduimDuration=" + engMeduimDuration + ", feeCurrency=" + feeCurrency + ", applicationFee=" + applicationFee + ", annualFee=" + annualFee + ", semesterFee=" + semesterFee + ", otherFee=" + otherFee + ", intake=" + intake + ", career=" + career + ", advantage=" + advantage + ", courseLevel=" + courseLevel + ", courseField=" + courseField + ", inputStream=" + inputStream + '}';
    }


}
