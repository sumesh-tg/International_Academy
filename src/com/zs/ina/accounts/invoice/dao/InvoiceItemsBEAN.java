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

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class InvoiceItemsBEAN {

    private final StringProperty lineId = new SimpleStringProperty();
    private final StringProperty invoiceId = new SimpleStringProperty();
    private final StringProperty itemId = new SimpleStringProperty();
    private final StringProperty timeEntryIds = new SimpleStringProperty();
    private final StringProperty expenseId = new SimpleStringProperty();
    private final StringProperty expenseRecieptName = new SimpleStringProperty();
    private final StringProperty itemName = new SimpleStringProperty();
    private final StringProperty description = new SimpleStringProperty();
    private final StringProperty itemCount = new SimpleStringProperty();
    private final StringProperty bcyRate = new SimpleStringProperty();
    private final StringProperty rate = new SimpleStringProperty();
    private final StringProperty quantity = new SimpleStringProperty();
    private final StringProperty unit = new SimpleStringProperty();
    private final StringProperty discountAmount = new SimpleStringProperty();
    private final StringProperty taxId = new SimpleStringProperty();
    private final StringProperty taxName = new SimpleStringProperty();
    private final StringProperty taxType = new SimpleStringProperty();
    private final StringProperty taxPercentage = new SimpleStringProperty();
    private final StringProperty itemTotal = new SimpleStringProperty();
    private final StringProperty identificationCode = new SimpleStringProperty();
    private final StringProperty category = new SimpleStringProperty();
    private final StringProperty isThisMandatory = new SimpleStringProperty();
    private final StringProperty mandatoryFor = new SimpleStringProperty();
    private final StringProperty isDelete = new SimpleStringProperty();
    private final StringProperty discountType = new SimpleStringProperty();

    public String getDiscountType() {
        return discountType.get();
    }

    public void setDiscountType(String value) {
        discountType.set(value);
    }

    public StringProperty discountTypeProperty() {
        return discountType;
    }

    public String getMandatoryFor() {
        return mandatoryFor.get();
    }

    public void setMandatoryFor(String value) {
        mandatoryFor.set(value);
    }

    public StringProperty mandatoryForProperty() {
        return mandatoryFor;
    }

    public String getIsDelete() {
        return isDelete.get();
    }

    public void setIsDelete(String value) {
        isDelete.set(value);
    }

    public StringProperty isDeleteProperty() {
        return isDelete;
    }

    public String getIsThisMandatory() {
        return isThisMandatory.get();
    }

    public void setIsThisMandatory(String value) {
        isThisMandatory.set(value);
    }

    public StringProperty isThisMandatoryProperty() {
        return isThisMandatory;
    }

    public String getCategory() {
        return category.get();
    }

    public void setCategory(String value) {
        category.set(value);
    }

    public StringProperty categoryProperty() {
        return category;
    }

    public String getIdentificationCode() {
        return identificationCode.get();
    }

    public void setIdentificationCode(String value) {
        identificationCode.set(value);
    }

    public StringProperty identificationCodeProperty() {
        return identificationCode;
    }

    public String getItemTotal() {
        return itemTotal.get();
    }

    public void setItemTotal(String value) {
        itemTotal.set(value);
    }

    public StringProperty itemTotalProperty() {
        return itemTotal;
    }

    public String getTaxPercentage() {
        return taxPercentage.get();
    }

    public void setTaxPercentage(String value) {
        taxPercentage.set(value);
    }

    public StringProperty taxPercentageProperty() {
        return taxPercentage;
    }

    public String getTaxType() {
        return taxType.get();
    }

    public void setTaxType(String value) {
        taxType.set(value);
    }

    public StringProperty taxTypeProperty() {
        return taxType;
    }

    public String getTaxName() {
        return taxName.get();
    }

    public void setTaxName(String value) {
        taxName.set(value);
    }

    public StringProperty taxNameProperty() {
        return taxName;
    }

    public String getTaxId() {
        return taxId.get();
    }

    public void setTaxId(String value) {
        taxId.set(value);
    }

    public StringProperty taxIdProperty() {
        return taxId;
    }

    public String getDiscountAmount() {
        return discountAmount.get();
    }

    public void setDiscountAmount(String value) {
        discountAmount.set(value);
    }

    public StringProperty discountAmountProperty() {
        return discountAmount;
    }

    public String getUnit() {
        return unit.get();
    }

    public void setUnit(String value) {
        unit.set(value);
    }

    public StringProperty unitProperty() {
        return unit;
    }

    public String getQuantity() {
        return quantity.get();
    }

    public void setQuantity(String value) {
        quantity.set(value);
    }

    public StringProperty quantityProperty() {
        return quantity;
    }

    public String getRate() {
        return rate.get();
    }

    public void setRate(String value) {
        rate.set(value);
    }

    public StringProperty rateProperty() {
        return rate;
    }

    public String getBcyRate() {
        return bcyRate.get();
    }

    public void setBcyRate(String value) {
        bcyRate.set(value);
    }

    public StringProperty bcyRateProperty() {
        return bcyRate;
    }

    public String getItemCount() {
        return itemCount.get();
    }

    public void setItemCount(String value) {
        itemCount.set(value);
    }

    public StringProperty itemCountProperty() {
        return itemCount;
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

    public String getItemName() {
        return itemName.get();
    }

    public void setItemName(String value) {
        itemName.set(value);
    }

    public StringProperty itemNameProperty() {
        return itemName;
    }

    public String getExpenseRecieptName() {
        return expenseRecieptName.get();
    }

    public void setExpenseRecieptName(String value) {
        expenseRecieptName.set(value);
    }

    public StringProperty expenseRecieptNameProperty() {
        return expenseRecieptName;
    }

    public String getExpenseId() {
        return expenseId.get();
    }

    public void setExpenseId(String value) {
        expenseId.set(value);
    }

    public StringProperty expenseIdProperty() {
        return expenseId;
    }

    public String getTimeEntryIds() {
        return timeEntryIds.get();
    }

    public void setTimeEntryIds(String value) {
        timeEntryIds.set(value);
    }

    public StringProperty timeEntryIdsProperty() {
        return timeEntryIds;
    }

    public String getItemId() {
        return itemId.get();
    }

    public void setItemId(String value) {
        itemId.set(value);
    }

    public StringProperty itemIdProperty() {
        return itemId;
    }

    public String getInvoiceId() {
        return invoiceId.get();
    }

    public void setInvoiceId(String value) {
        invoiceId.set(value);
    }

    public StringProperty invoiceIdProperty() {
        return invoiceId;
    }

    public String getLineId() {
        return lineId.get();
    }

    public void setLineId(String value) {
        lineId.set(value);
    }

    public StringProperty lineIdProperty() {
        return lineId;
    }

}
