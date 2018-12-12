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
package com.zs.ina.search.training.dao;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.time.LocalDate;

/**
 *
 * @author 100035
 */
public class TrainingBEAN {

    private final StringProperty trainingId = new SimpleStringProperty();
    private final StringProperty trainingCourse = new SimpleStringProperty();
    private final StringProperty description = new SimpleStringProperty();
    private final StringProperty remarks = new SimpleStringProperty();
    private final StringProperty website = new SimpleStringProperty();
    private final StringProperty affiliation = new SimpleStringProperty();
    private final StringProperty courseMode = new SimpleStringProperty();
    private final StringProperty examFee = new SimpleStringProperty();
    private final StringProperty providerType = new SimpleStringProperty();
    private final StringProperty agencyStatus = new SimpleStringProperty();
    private final StringProperty tieUpName = new SimpleStringProperty();
    private final StringProperty commission = new SimpleStringProperty();
    private final StringProperty contractBy = new SimpleStringProperty();
    private final StringProperty renewalBy = new SimpleStringProperty();
    private final StringProperty paymentMode = new SimpleStringProperty();
    private final StringProperty courseFee = new SimpleStringProperty();
    private final StringProperty duration = new SimpleStringProperty();

    private final StringProperty createdUser = new SimpleStringProperty();
    private final StringProperty createdDate = new SimpleStringProperty();
    private final StringProperty updatedUser = new SimpleStringProperty();
    private final StringProperty updatedDate = new SimpleStringProperty();
    private final ObjectProperty<LocalDate> contractFrom = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDate> contractTo = new SimpleObjectProperty<>();

    /* ============================== For Training Course Requirement ======================= */
    private final StringProperty gender = new SimpleStringProperty();
    private final StringProperty ageFrom = new SimpleStringProperty();
    private final StringProperty ageTo = new SimpleStringProperty();
    private final StringProperty maritalStatus = new SimpleStringProperty();
    private final StringProperty sslcBoard = new SimpleStringProperty();
    private final StringProperty sslcMedium = new SimpleStringProperty();
    private final StringProperty plus2board = new SimpleStringProperty();
    private final StringProperty plus2medium = new SimpleStringProperty();
    private final StringProperty engMediumBoard = new SimpleStringProperty();
    private final StringProperty engMediumDuration = new SimpleStringProperty();
    private final StringProperty intake = new SimpleStringProperty();
    private final StringProperty career = new SimpleStringProperty();
    private final StringProperty advantage = new SimpleStringProperty();
    private final StringProperty trainCampuses = new SimpleStringProperty();

    public String getTrainCampuses() {
        return trainCampuses.get();
    }

    public void setTrainCampuses(String value) {
        trainCampuses.set(value);
    }

    public StringProperty trainCampusesProperty() {
        return trainCampuses;
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

    public String getGender() {
        return gender.get();
    }

    public void setGender(String value) {
        gender.set(value);
    }

    public StringProperty genderProperty() {
        return gender;
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

    public String getEngMediumDuration() {
        return engMediumDuration.get();
    }

    public void setEngMediumDuration(String value) {
        engMediumDuration.set(value);
    }

    public StringProperty engMediumDurationProperty() {
        return engMediumDuration;
    }

    public String getEngMediumBoard() {
        return engMediumBoard.get();
    }

    public void setEngMediumBoard(String value) {
        engMediumBoard.set(value);
    }

    public StringProperty engMediumBoardProperty() {
        return engMediumBoard;
    }

    public String getPlus2medium() {
        return plus2medium.get();
    }

    public void setPlus2medium(String value) {
        plus2medium.set(value);
    }

    public StringProperty plus2mediumProperty() {
        return plus2medium;
    }

    public String getPlus2board() {
        return plus2board.get();
    }

    public void setPlus2board(String value) {
        plus2board.set(value);
    }

    public StringProperty plus2boardProperty() {
        return plus2board;
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

    public LocalDate getContractTo() {
        return contractTo.get();
    }

    public void setContractTo(LocalDate value) {
        contractTo.set(value);
    }

    public ObjectProperty contractToProperty() {
        return contractTo;
    }

    public LocalDate getContractFrom() {
        return contractFrom.get();
    }

    public void setContractFrom(LocalDate value) {
        contractFrom.set(value);
    }

    public ObjectProperty contractFromProperty() {
        return contractFrom;
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

    public String getUpdatedUser() {
        return updatedUser.get();
    }

    public void setUpdatedUser(String value) {
        updatedUser.set(value);
    }

    public StringProperty updatedUserProperty() {
        return updatedUser;
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

    public String getCreatedUser() {
        return createdUser.get();
    }

    public void setCreatedUser(String value) {
        createdUser.set(value);
    }

    public StringProperty createdUserProperty() {
        return createdUser;
    }

    public String getDuration() {
        return duration.get();
    }

    public void setDuration(String value) {
        duration.set(value);
    }

    public StringProperty durationProperty() {
        return duration;
    }

    public String getCourseFee() {
        return courseFee.get();
    }

    public void setCourseFee(String value) {
        courseFee.set(value);
    }

    public StringProperty courseFeeProperty() {
        return courseFee;
    }

    public String getPaymentMode() {
        return paymentMode.get();
    }

    public void setPaymentMode(String value) {
        paymentMode.set(value);
    }

    public StringProperty paymentModeProperty() {
        return paymentMode;
    }

    public String getRenewalBy() {
        return renewalBy.get();
    }

    public void setRenewalBy(String value) {
        renewalBy.set(value);
    }

    public StringProperty renewalByProperty() {
        return renewalBy;
    }

    public String getContractBy() {
        return contractBy.get();
    }

    public void setContractBy(String value) {
        contractBy.set(value);
    }

    public StringProperty contractByProperty() {
        return contractBy;
    }

    public String getCommission() {
        return commission.get();
    }

    public void setCommission(String value) {
        commission.set(value);
    }

    public StringProperty commissionProperty() {
        return commission;
    }

    public String getTieUpName() {
        return tieUpName.get();
    }

    public void setTieUpName(String value) {
        tieUpName.set(value);
    }

    public StringProperty tieUpNameProperty() {
        return tieUpName;
    }

    public String getAgencyStatus() {
        return agencyStatus.get();
    }

    public void setAgencyStatus(String value) {
        agencyStatus.set(value);
    }

    public StringProperty agencyStatusProperty() {
        return agencyStatus;
    }

    public String getProviderType() {
        return providerType.get();
    }

    public void setProviderType(String value) {
        providerType.set(value);
    }

    public StringProperty providerTypeProperty() {
        return providerType;
    }

    public String getExamFee() {
        return examFee.get();
    }

    public void setExamFee(String value) {
        examFee.set(value);
    }

    public StringProperty examFeeProperty() {
        return examFee;
    }

    public String getCourseMode() {
        return courseMode.get();
    }

    public void setCourseMode(String value) {
        courseMode.set(value);
    }

    public StringProperty courseModeProperty() {
        return courseMode;
    }

    public String getAffiliation() {
        return affiliation.get();
    }

    public void setAffiliation(String value) {
        affiliation.set(value);
    }

    public StringProperty affiliationProperty() {
        return affiliation;
    }

    public String getWebsite() {
        return website.get();
    }

    public void setWebsite(String value) {
        website.set(value);
    }

    public StringProperty websiteProperty() {
        return website;
    }

    public String getRemarks() {
        return remarks.get();
    }

    public void setRemarks(String value) {
        remarks.set(value);
    }

    public StringProperty remarksProperty() {
        return remarks;
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String value) {
        description.set(value);
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public String getTrainingCourse() {
        return trainingCourse.get();
    }

    public void setTrainingCourse(String value) {
        trainingCourse.set(value);
    }

    public StringProperty trainingCourseProperty() {
        return trainingCourse;
    }

    public String getTrainingId() {
        return trainingId.get();
    }

    public void setTrainingId(String value) {
        trainingId.set(value);
    }

    public StringProperty trainingIdProperty() {
        return trainingId;
    }

    @Override
    public String toString() {
        return "TrainingBEAN{" + "trainingId=" + trainingId + ", trainingCourse=" + trainingCourse + ", description=" + description + ", remarks=" + remarks + ", website=" + website + ", affiliation=" + affiliation + ", courseMode=" + courseMode + ", examFee=" + examFee + ", providerType=" + providerType + ", agencyStatus=" + agencyStatus + ", tieUpName=" + tieUpName + ", commission=" + commission + ", contractBy=" + contractBy + ", renewalBy=" + renewalBy + ", paymentMode=" + paymentMode + ", courseFee=" + courseFee + ", duration=" + duration + ", createdUser=" + createdUser + ", createdDate=" + createdDate + ", updatedUser=" + updatedUser + ", updatedDate=" + updatedDate + ", contractFrom=" + contractFrom + ", contractTo=" + contractTo + ", gender=" + gender + ", ageFrom=" + ageFrom + ", ageTo=" + ageTo + ", maritalStatus=" + maritalStatus + ", sslcBoard=" + sslcBoard + ", sslcMedium=" + sslcMedium + ", plus2board=" + plus2board + ", plus2medium=" + plus2medium + ", engMediumBoard=" + engMediumBoard + ", engMediumDuration=" + engMediumDuration + ", intake=" + intake + ", career=" + career + ", advantage=" + advantage + '}';
    }

   

}
