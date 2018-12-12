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
package com.zs.ina.search.employer.dao;

import java.io.InputStream;
import java.time.LocalDate;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class EmployerBEAN {

    private final StringProperty employerId = new SimpleStringProperty();
    private final StringProperty employer = new SimpleStringProperty();
    private final StringProperty updatedUser = new SimpleStringProperty();
    private final StringProperty createdUser = new SimpleStringProperty();
    private final StringProperty updatedDate = new SimpleStringProperty();
    private final StringProperty createdDate = new SimpleStringProperty();
    private final StringProperty renewalBy = new SimpleStringProperty();
    private final StringProperty contarctBy = new SimpleStringProperty();
    private final ObjectProperty<LocalDate> contractTo = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDate> contractFrom = new SimpleObjectProperty<>();
    private final StringProperty tieupName = new SimpleStringProperty();
    private final StringProperty agencyStatus = new SimpleStringProperty();
    private final StringProperty currencyUsed = new SimpleStringProperty();
    private final StringProperty employerType = new SimpleStringProperty();
    private final StringProperty address = new SimpleStringProperty();
    private final StringProperty logo = new SimpleStringProperty();
    private final StringProperty website = new SimpleStringProperty();
    private final StringProperty remarks = new SimpleStringProperty();
    private final StringProperty description = new SimpleStringProperty();
    private final StringProperty commission = new SimpleStringProperty();
    private final StringProperty vacancyId = new SimpleStringProperty();
    private final StringProperty employmentSector = new SimpleStringProperty();
    private final StringProperty job = new SimpleStringProperty();
    private final StringProperty noOfVacancies = new SimpleStringProperty();
    private final StringProperty vacancyStatus = new SimpleStringProperty();
    private final StringProperty gender = new SimpleStringProperty();
    private final StringProperty ageFrom = new SimpleStringProperty();
    private final StringProperty ageTo = new SimpleStringProperty();
    private final StringProperty maritalStatus = new SimpleStringProperty();
    private final StringProperty salaryFromCurrency = new SimpleStringProperty();
    private final StringProperty salaryFromAmount = new SimpleStringProperty();
    private final StringProperty salaryToCurrency = new SimpleStringProperty();
    private final StringProperty salaryToAmount = new SimpleStringProperty();
    private final StringProperty sslcBoard = new SimpleStringProperty();
    private final StringProperty sslcMedium = new SimpleStringProperty();
    private final StringProperty plus2Board = new SimpleStringProperty();
    private final StringProperty plus2Medium = new SimpleStringProperty();
    private final StringProperty currency = new SimpleStringProperty();
    private final StringProperty engMediumBoard = new SimpleStringProperty();
    private final StringProperty engMediumDuration = new SimpleStringProperty();

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

    
    InputStream inputStream = null;
    private final ObjectProperty<LocalDate> expiryDate = new SimpleObjectProperty<>();

    public LocalDate getExpiryDate() {
        return expiryDate.get();
    }

    public void setExpiryDate(LocalDate value) {
        expiryDate.set(value);
    }

    public ObjectProperty expiryDateProperty() {
        return expiryDate;
    }

    public String getEmployer() {
        return employer.get();
    }

    public void setEmployer(String value) {
        employer.set(value);
    }

    public String getCurrency() {
        return currency.get();
    }

    public void setCurrency(String value) {
        currency.set(value);
    }

    public StringProperty currencyProperty() {
        return currency;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
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

    public String getUpdatedUser() {
        return updatedUser.get();
    }

    public void setUpdatedUser(String value) {
        updatedUser.set(value);
    }

    public StringProperty updatedUserProperty() {
        return updatedUser;
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

    public String getSalaryToAmount() {
        return salaryToAmount.get();
    }

    public void setSalaryToAmount(String value) {
        salaryToAmount.set(value);
    }

    public StringProperty salaryToAmountProperty() {
        return salaryToAmount;
    }

    public String getSalaryToCurrency() {
        return salaryToCurrency.get();
    }

    public void setSalaryToCurrency(String value) {
        salaryToCurrency.set(value);
    }

    public StringProperty salaryToCurrencyProperty() {
        return salaryToCurrency;
    }

    public String getSalaryFromAmount() {
        return salaryFromAmount.get();
    }

    public void setSalaryFromAmount(String value) {
        salaryFromAmount.set(value);
    }

    public StringProperty salaryFromAmountProperty() {
        return salaryFromAmount;
    }

    public String getSalaryFromCurrency() {
        return salaryFromCurrency.get();
    }

    public void setSalaryFromCurrency(String value) {
        salaryFromCurrency.set(value);
    }

    public StringProperty salaryFromCurrencyProperty() {
        return salaryFromCurrency;
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

    public String getGender() {
        return gender.get();
    }

    public void setGender(String value) {
        gender.set(value);
    }

    public StringProperty genderProperty() {
        return gender;
    }

    public String getVacancyStatus() {
        return vacancyStatus.get();
    }

    public void setVacancyStatus(String value) {
        vacancyStatus.set(value);
    }

    public StringProperty vacancyStatusProperty() {
        return vacancyStatus;
    }

    public String getNoOfVacancies() {
        return noOfVacancies.get();
    }

    public void setNoOfVacancies(String value) {
        noOfVacancies.set(value);
    }

    public StringProperty noOfVacanciesProperty() {
        return noOfVacancies;
    }

    public String getJob() {
        return job.get();
    }

    public void setJob(String value) {
        job.set(value);
    }

    public StringProperty jobProperty() {
        return job;
    }

    public String getEmploymentSector() {
        return employmentSector.get();
    }

    public void setEmploymentSector(String value) {
        employmentSector.set(value);
    }

    public StringProperty employmentSectorProperty() {
        return employmentSector;
    }

    public String getEmployerId() {
        return employerId.get();
    }

    public void setEmployerId(String value) {
        employerId.set(value);
    }

    public StringProperty employerIdProperty() {
        return employerId;
    }

    public String getVacancyId() {
        return vacancyId.get();
    }

    public void setVacancyId(String value) {
        vacancyId.set(value);
    }

    public StringProperty vacancyIdProperty() {
        return vacancyId;
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

    public LocalDate getContractTo() {
        return contractTo.get();
    }

    public void setContractTo(LocalDate value) {
        contractTo.set(value);
    }

    public ObjectProperty contractToProperty() {
        return contractTo;
    }

    public StringProperty employerProperty() {
        return employer;
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

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String value) {
        description.set(value);
    }

    public StringProperty descriptionProperty() {
        return description;
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

    public String getWebsite() {
        return website.get();
    }

    public void setWebsite(String value) {
        website.set(value);
    }

    public StringProperty websiteProperty() {
        return website;
    }

    public String getLogo() {
        return logo.get();
    }

    public void setLogo(String value) {
        logo.set(value);
    }

    public StringProperty logoProperty() {
        return logo;
    }

    public String getAddress() {
        return address.get();
    }

    public void setAddress(String value) {
        address.set(value);
    }

    public StringProperty addressProperty() {
        return address;
    }

    public String getEmployerType() {
        return employerType.get();
    }

    public void setEmployerType(String value) {
        employerType.set(value);
    }

    public StringProperty employerTypeProperty() {
        return employerType;
    }

    public String getCurrencyUsed() {
        return currencyUsed.get();
    }

    public void setCurrencyUsed(String value) {
        currencyUsed.set(value);
    }

    public StringProperty currencyUsedProperty() {
        return currencyUsed;
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

    public String getTieupName() {
        return tieupName.get();
    }

    public void setTieupName(String value) {
        tieupName.set(value);
    }

    public StringProperty tieupNameProperty() {
        return tieupName;
    }

    public String getContarctBy() {
        return contarctBy.get();
    }

    public void setContarctBy(String value) {
        contarctBy.set(value);
    }

    public StringProperty contarctByProperty() {
        return contarctBy;
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

    @Override
    public String toString() {
        return "EmployerBEAN{" + "employerId=" + employerId + ", employer=" + employer + ", updatedUser=" + updatedUser + ", createdUser=" + createdUser + ", updatedDate=" + updatedDate + ", createdDate=" + createdDate + ", renewalBy=" + renewalBy + ", contarctBy=" + contarctBy + ", tieupName=" + tieupName + ", agencyStatus=" + agencyStatus + ", currencyUsed=" + currencyUsed + ", employerType=" + employerType + ", address=" + address + ", logo=" + logo + ", website=" + website + ", remarks=" + remarks + ", description=" + description + ", commission=" + commission + '}';
    }

}
