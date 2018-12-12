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
package com.zs.ina.accounts.invoice.dao;

import java.time.LocalDate;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class InvoiceBEAN {

    private final StringProperty invoiceIid = new SimpleStringProperty();
    private final StringProperty invoiceNumber = new SimpleStringProperty();
    private final StringProperty enquiryId = new SimpleStringProperty();
    private final StringProperty invoiceStatus = new SimpleStringProperty();
    private final StringProperty paymentTerms = new SimpleStringProperty();
    private final StringProperty paymentLabel = new SimpleStringProperty();
    private final StringProperty paymentExpectedDate = new SimpleStringProperty();
    private final StringProperty referenceNumber = new SimpleStringProperty();
    private final StringProperty contactPersons = new SimpleStringProperty();
    private final StringProperty currencyId = new SimpleStringProperty();
    private final StringProperty currencyCode = new SimpleStringProperty();
    private final StringProperty exchangeRate = new SimpleStringProperty();
    private final StringProperty discount = new SimpleStringProperty();
    private final StringProperty isDiscountBeforeTax = new SimpleStringProperty();
    private final StringProperty discountType = new SimpleStringProperty();
    private final StringProperty isInclusiveTax = new SimpleStringProperty();
    private final StringProperty recurringInvoiceId = new SimpleStringProperty();
    private final StringProperty additionalCharge = new SimpleStringProperty();
    private final StringProperty adjustment = new SimpleStringProperty();
    private final StringProperty adjustmentDescription = new SimpleStringProperty();
    private final StringProperty subTotal = new SimpleStringProperty();
    private final StringProperty taxTotal = new SimpleStringProperty();
    private final StringProperty total = new SimpleStringProperty();
    private final StringProperty paymentReminderEnabled = new SimpleStringProperty();
    private final StringProperty paymentMade = new SimpleStringProperty();
    private final StringProperty creditsApplied = new SimpleStringProperty();
    private final StringProperty taxAmountWithheld = new SimpleStringProperty();
    private final StringProperty balance = new SimpleStringProperty();
    private final StringProperty writeOffAmount = new SimpleStringProperty();
    private final StringProperty allowPartiaPayments = new SimpleStringProperty();
    private final StringProperty isEmailed = new SimpleStringProperty();
    private final StringProperty remindersSent = new SimpleStringProperty();
    private final StringProperty lastReminderSentDate = new SimpleStringProperty();
    private final StringProperty notes = new SimpleStringProperty();
    private final StringProperty terms = new SimpleStringProperty();
    private final StringProperty templateId = new SimpleStringProperty();
    private final StringProperty templateName = new SimpleStringProperty();
    private final StringProperty canSendInEmail = new SimpleStringProperty();
    private final StringProperty createdUser = new SimpleStringProperty();
    private final StringProperty updatedUser = new SimpleStringProperty();
    private final StringProperty createdDate = new SimpleStringProperty();
    private final StringProperty updatedDate = new SimpleStringProperty();
    private final ObjectProperty<LocalDate> dueDate = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDate> invoiceCreatedDate = new SimpleObjectProperty<>();
    private final StringProperty tersAndConditions = new SimpleStringProperty();
    private final StringProperty branch = new SimpleStringProperty();

    public String getBranch() {
        return branch.get();
    }

    public void setBranch(String value) {
        branch.set(value);
    }

    public StringProperty branchProperty() {
        return branch;
    }

    public String getTersAndConditions() {
        return tersAndConditions.get();
    }

    public void setTersAndConditions(String value) {
        tersAndConditions.set(value);
    }

    public StringProperty tersAndConditionsProperty() {
        return tersAndConditions;
    }

    public LocalDate getInvoiceCreatedDate() {
        return invoiceCreatedDate.get();
    }

    public void setInvoiceCreatedDate(LocalDate value) {
        invoiceCreatedDate.set(value);
    }

    public ObjectProperty invoiceCreatedDateProperty() {
        return invoiceCreatedDate;
    }

    public LocalDate getDueDate() {
        return dueDate.get();
    }

    public void setDueDate(LocalDate value) {
        dueDate.set(value);
    }

    public ObjectProperty dueDateProperty() {
        return dueDate;
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

    public String getCanSendInEmail() {
        return canSendInEmail.get();
    }

    public void setCanSendInEmail(String value) {
        canSendInEmail.set(value);
    }

    public StringProperty canSendInEmailProperty() {
        return canSendInEmail;
    }

    public String getTemplateName() {
        return templateName.get();
    }

    public void setTemplateName(String value) {
        templateName.set(value);
    }

    public StringProperty templateNameProperty() {
        return templateName;
    }

    public String getTemplateId() {
        return templateId.get();
    }

    public void setTemplateId(String value) {
        templateId.set(value);
    }

    public StringProperty templateIdProperty() {
        return templateId;
    }

    public String getTerms() {
        return terms.get();
    }

    public void setTerms(String value) {
        terms.set(value);
    }

    public StringProperty termsProperty() {
        return terms;
    }

    public String getNotes() {
        return notes.get();
    }

    public void setNotes(String value) {
        notes.set(value);
    }

    public StringProperty notesProperty() {
        return notes;
    }

    public String getLastReminderSentDate() {
        return lastReminderSentDate.get();
    }

    public void setLastReminderSentDate(String value) {
        lastReminderSentDate.set(value);
    }

    public StringProperty lastReminderSentDateProperty() {
        return lastReminderSentDate;
    }

    public String getRemindersSent() {
        return remindersSent.get();
    }

    public void setRemindersSent(String value) {
        remindersSent.set(value);
    }

    public StringProperty remindersSentProperty() {
        return remindersSent;
    }

    public String getIsEmailed() {
        return isEmailed.get();
    }

    public void setIsEmailed(String value) {
        isEmailed.set(value);
    }

    public StringProperty isEmailedProperty() {
        return isEmailed;
    }

    public String getAllowPartiaPayments() {
        return allowPartiaPayments.get();
    }

    public void setAllowPartiaPayments(String value) {
        allowPartiaPayments.set(value);
    }

    public StringProperty allowPartiaPaymentsProperty() {
        return allowPartiaPayments;
    }

    public String getWriteOffAmount() {
        return writeOffAmount.get();
    }

    public void setWriteOffAmount(String value) {
        writeOffAmount.set(value);
    }

    public StringProperty writeOffAmountProperty() {
        return writeOffAmount;
    }

    public String getBalance() {
        return balance.get();
    }

    public void setBalance(String value) {
        balance.set(value);
    }

    public StringProperty balanceProperty() {
        return balance;
    }

    public String getTaxAmountWithheld() {
        return taxAmountWithheld.get();
    }

    public void setTaxAmountWithheld(String value) {
        taxAmountWithheld.set(value);
    }

    public StringProperty taxAmountWithheldProperty() {
        return taxAmountWithheld;
    }

    public String getCreditsApplied() {
        return creditsApplied.get();
    }

    public void setCreditsApplied(String value) {
        creditsApplied.set(value);
    }

    public StringProperty creditsAppliedProperty() {
        return creditsApplied;
    }

    public String getPaymentMade() {
        return paymentMade.get();
    }

    public void setPaymentMade(String value) {
        paymentMade.set(value);
    }

    public StringProperty paymentMadeProperty() {
        return paymentMade;
    }

    public String getPaymentReminderEnabled() {
        return paymentReminderEnabled.get();
    }

    public void setPaymentReminderEnabled(String value) {
        paymentReminderEnabled.set(value);
    }

    public StringProperty paymentReminderEnabledProperty() {
        return paymentReminderEnabled;
    }

    public String getTotal() {
        return total.get();
    }

    public void setTotal(String value) {
        total.set(value);
    }

    public StringProperty totalProperty() {
        return total;
    }

    public String getTaxTotal() {
        return taxTotal.get();
    }

    public void setTaxTotal(String value) {
        taxTotal.set(value);
    }

    public StringProperty taxTotalProperty() {
        return taxTotal;
    }

    public String getSubTotal() {
        return subTotal.get();
    }

    public void setSubTotal(String value) {
        subTotal.set(value);
    }

    public StringProperty subTotalProperty() {
        return subTotal;
    }

    public String getAdjustmentDescription() {
        return adjustmentDescription.get();
    }

    public void setAdjustmentDescription(String value) {
        adjustmentDescription.set(value);
    }

    public StringProperty adjustmentDescriptionProperty() {
        return adjustmentDescription;
    }

    public String getAdjustment() {
        return adjustment.get();
    }

    public void setAdjustment(String value) {
        adjustment.set(value);
    }

    public StringProperty adjustmentProperty() {
        return adjustment;
    }

    public String getAdditionalCharge() {
        return additionalCharge.get();
    }

    public void setAdditionalCharge(String value) {
        additionalCharge.set(value);
    }

    public StringProperty additionalChargeProperty() {
        return additionalCharge;
    }

    public String getRecurringInvoiceId() {
        return recurringInvoiceId.get();
    }

    public void setRecurringInvoiceId(String value) {
        recurringInvoiceId.set(value);
    }

    public StringProperty recurringInvoiceIdProperty() {
        return recurringInvoiceId;
    }

    public String getIsInclusiveTax() {
        return isInclusiveTax.get();
    }

    public void setIsInclusiveTax(String value) {
        isInclusiveTax.set(value);
    }

    public StringProperty isInclusiveTaxProperty() {
        return isInclusiveTax;
    }

    public String getDiscountType() {
        return discountType.get();
    }

    public void setDiscountType(String value) {
        discountType.set(value);
    }

    public StringProperty discountTypeProperty() {
        return discountType;
    }

    public String getIsDiscountBeforeTax() {
        return isDiscountBeforeTax.get();
    }

    public void setIsDiscountBeforeTax(String value) {
        isDiscountBeforeTax.set(value);
    }

    public StringProperty isDiscountBeforeTaxProperty() {
        return isDiscountBeforeTax;
    }

    public String getDiscount() {
        return discount.get();
    }

    public void setDiscount(String value) {
        discount.set(value);
    }

    public StringProperty discountProperty() {
        return discount;
    }

    public String getExchangeRate() {
        return exchangeRate.get();
    }

    public void setExchangeRate(String value) {
        exchangeRate.set(value);
    }

    public StringProperty exchangeRateProperty() {
        return exchangeRate;
    }

    public String getCurrencyCode() {
        return currencyCode.get();
    }

    public void setCurrencyCode(String value) {
        currencyCode.set(value);
    }

    public StringProperty currencyCodeProperty() {
        return currencyCode;
    }

    public String getCurrencyId() {
        return currencyId.get();
    }

    public void setCurrencyId(String value) {
        currencyId.set(value);
    }

    public StringProperty currencyIdProperty() {
        return currencyId;
    }

    public String getContactPersons() {
        return contactPersons.get();
    }

    public void setContactPersons(String value) {
        contactPersons.set(value);
    }

    public StringProperty contactPersonsProperty() {
        return contactPersons;
    }

    public String getReferenceNumber() {
        return referenceNumber.get();
    }

    public void setReferenceNumber(String value) {
        referenceNumber.set(value);
    }

    public StringProperty referenceNumberProperty() {
        return referenceNumber;
    }

    public String getPaymentExpectedDate() {
        return paymentExpectedDate.get();
    }

    public void setPaymentExpectedDate(String value) {
        paymentExpectedDate.set(value);
    }

    public StringProperty paymentExpectedDateProperty() {
        return paymentExpectedDate;
    }

    public String getPaymentLabel() {
        return paymentLabel.get();
    }

    public void setPaymentLabel(String value) {
        paymentLabel.set(value);
    }

    public StringProperty paymentLabelProperty() {
        return paymentLabel;
    }

    public String getPaymentTerms() {
        return paymentTerms.get();
    }

    public void setPaymentTerms(String value) {
        paymentTerms.set(value);
    }

    public StringProperty paymentTermsProperty() {
        return paymentTerms;
    }
    public String getInvoiceStatus() {
        return invoiceStatus.get();
    }

    public void setInvoiceStatus(String value) {
        invoiceStatus.set(value);
    }

    public StringProperty invoiceStatusProperty() {
        return invoiceStatus;
    }

    public String getEnquiryId() {
        return enquiryId.get();
    }

    public void setEnquiryId(String value) {
        enquiryId.set(value);
    }

    public StringProperty enquiryIdProperty() {
        return enquiryId;
    }

    public String getInvoiceNumber() {
        return invoiceNumber.get();
    }

    public void setInvoiceNumber(String value) {
        invoiceNumber.set(value);
    }

    public StringProperty invoiceNumberProperty() {
        return invoiceNumber;
    }

    public String getInvoiceIid() {
        return invoiceIid.get();
    }

    public void setInvoiceIid(String value) {
        invoiceIid.set(value);
    }

    public StringProperty invoiceIidProperty() {
        return invoiceIid;
    }

}
