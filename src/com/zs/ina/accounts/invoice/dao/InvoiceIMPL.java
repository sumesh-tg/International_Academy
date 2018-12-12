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

import com.zs.ina.common.constants.TableNames;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class InvoiceIMPL implements InvoiceDAO, InvoiceItemsDAO {

    static Logger logger = Logger.getLogger(InvoiceIMPL.class);
    @Autowired
    DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean insertInvoiceMain(InvoiceBEAN invoiceBEAN) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "INSERT INTO " + TableNames.TBL_INV_MAIN + " (\n"
                + "	invoice_id,\n"
                + "	invoice_number,\n"
                + "	enquiry_id,\n"
                + "	invoice_status,\n"
                + "	invoice_created_date,\n"
                + "	due_date,\n"
                + "	payment_terms,\n"
                + "	payment_label,\n"
                + "	payment_expected_date,\n"
                + "	last_payment_date,\n"
                + "	reference_number,\n"
                + "	contact_persons,\n"
                + "	currency_id,\n"
                + "	currency_code,\n"
                + "	exchange_rate,\n"
                + "	discount,\n"
                + "	is_discount_before_tax,\n"
                + "	discount_type,\n"
                + "	is_inclusive_tax,\n"
                + "	recurring_invoice_id,\n"
                + "	additional_charge,\n"
                + "	adjustment,\n"
                + "	adjustment_description,\n"
                + "	sub_total,\n"
                + "	tax_total,\n"
                + "	total,\n"
                + "	payment_reminder_enabled,\n"
                + "	payment_made,\n"
                + "	credits_applied,\n"
                + "	tax_amount_withheld,\n"
                + "	balance,\n"
                + "	write_off_amount,\n"
                + "	allow_partial_payments,\n"
                + "	is_emailed,\n"
                + "	reminders_sent,\n"
                + "	last_reminder_sent_date,\n"
                + "	notes,\n"
                + "	terms,\n"
                + "	template_id,\n"
                + "	template_name,\n"
                + "	can_send_in_email,\n"
                + "	created_user,\n"
                + "	updated_user,\n"
                + "	created_date,\n"
                + "	updated_date\n"
                + ")\n"
                + "VALUES\n"
                + "	(\n"
                + "		?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?\n"
                + "	);";
        int row = jdbcTemplate.update(sql, new Object[]{invoiceBEAN.getInvoiceIid(), invoiceBEAN.getInvoiceNumber(),
            invoiceBEAN.getEnquiryId(), invoiceBEAN.getInvoiceStatus(), invoiceBEAN.getInvoiceCreatedDate().toString(),
            invoiceBEAN.getDueDate().toString(), invoiceBEAN.getPaymentTerms(), invoiceBEAN.getPaymentLabel(),
            invoiceBEAN.getPaymentExpectedDate(), invoiceBEAN.getLastReminderSentDate(),
            invoiceBEAN.getReferenceNumber(), invoiceBEAN.getContactPersons(), invoiceBEAN.getCurrencyId(), invoiceBEAN.getCurrencyCode(), invoiceBEAN.getExchangeRate(),
            invoiceBEAN.getDiscount(), invoiceBEAN.getIsDiscountBeforeTax(), invoiceBEAN.getDiscountType(), invoiceBEAN.getIsInclusiveTax(), invoiceBEAN.getRecurringInvoiceId(),
            invoiceBEAN.getAdditionalCharge(), invoiceBEAN.getAdjustment(), invoiceBEAN.getAdjustmentDescription(), invoiceBEAN.getSubTotal(), invoiceBEAN.getTaxTotal(),
            invoiceBEAN.getTotal(), invoiceBEAN.getPaymentReminderEnabled(), invoiceBEAN.getPaymentMade(), invoiceBEAN.getCreditsApplied(), invoiceBEAN.getTaxAmountWithheld(),
            invoiceBEAN.getBalance(), invoiceBEAN.getWriteOffAmount(), invoiceBEAN.getAllowPartiaPayments(), invoiceBEAN.getIsEmailed(), invoiceBEAN.getRemindersSent(),
            invoiceBEAN.getLastReminderSentDate(), invoiceBEAN.getNotes(), invoiceBEAN.getTerms(), invoiceBEAN.getTemplateId(), invoiceBEAN.getTemplateName(),
            invoiceBEAN.getCanSendInEmail(), invoiceBEAN.getCreatedUser(), invoiceBEAN.getUpdatedUser(), invoiceBEAN.getCreatedDate(), invoiceBEAN.getUpdatedDate()
        });
        return (row > 0);
    }

    @Override
    public boolean updateInvoiceMain(InvoiceBEAN invoiceBEAN) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "UPDATE " + TableNames.TBL_INV_MAIN + " \n"
                + "SET invoice_number =  ?,\n"
                + " enquiry_id =  ?,\n"
                + " invoice_status =  ?,\n"
                + " invoice_created_date =  ?,\n"
                + " branch =  ?,\n"
                + " due_date =  ?,\n"
                + " payment_terms =  ?,\n"
                + " payment_label =  ?,\n"
                + " payment_expected_date =  ?,\n"
                + " last_payment_date =  ?,\n"
                + " reference_number =  ?,\n"
                + " contact_persons =  ?,\n"
                + " currency_id =  ?,\n"
                + " currency_code =  ?,\n"
                + " exchange_rate =  ?,\n"
                + " discount =  ?,\n"
                + " is_discount_before_tax =  ?,\n"
                + " discount_type =  ?,\n"
                + " is_inclusive_tax =  ?,\n"
                + " recurring_invoice_id =  ?,\n"
                + " additional_charge =  ?,\n"
                + " adjustment =  ?,\n"
                + " adjustment_description =  ?,\n"
                + " sub_total =  ?,\n"
                + " tax_total =  ?,\n"
                + " total =  ?,\n"
                + " payment_reminder_enabled =  ?,\n"
                + " payment_made =  ?,\n"
                + " credits_applied =  ?,\n"
                + " tax_amount_withheld =  ?,\n"
                + " balance =  ?,\n"
                + " write_off_amount =  ?,\n"
                + " allow_partial_payments =  ?,\n"
                + " is_emailed =  ?,\n"
                + " reminders_sent =  ?,\n"
                + " last_reminder_sent_date =  ?,\n"
                + " notes =  ?,\n"
                + " terms =  ?,\n"
                + " template_id =  ?,\n"
                + " template_name =  ?,\n"
                + " can_send_in_email =  ?,\n"
                + " created_user =  ?,\n"
                + " updated_user =  ?,\n"
                + " created_date =  ?,\n"
                + " updated_date =  ?\n"
                + "WHERE\n"
                + "	invoice_id= ?";
        int row = jdbcTemplate.update(sql, new Object[]{invoiceBEAN.getInvoiceNumber(),
            invoiceBEAN.getEnquiryId(), invoiceBEAN.getInvoiceStatus(), invoiceBEAN.getInvoiceCreatedDate().toString(), invoiceBEAN.getBranch(),
            invoiceBEAN.getDueDate().toString(), invoiceBEAN.getPaymentTerms(), invoiceBEAN.getPaymentLabel(),
            invoiceBEAN.getPaymentExpectedDate(), invoiceBEAN.getLastReminderSentDate(),
            invoiceBEAN.getReferenceNumber(), invoiceBEAN.getContactPersons(), invoiceBEAN.getCurrencyId(), invoiceBEAN.getCurrencyCode(), invoiceBEAN.getExchangeRate(),
            invoiceBEAN.getDiscount(), invoiceBEAN.getIsDiscountBeforeTax(), invoiceBEAN.getDiscountType(), invoiceBEAN.getIsInclusiveTax(), invoiceBEAN.getRecurringInvoiceId(),
            invoiceBEAN.getAdditionalCharge(), invoiceBEAN.getAdjustment(), invoiceBEAN.getAdjustmentDescription(), invoiceBEAN.getSubTotal(), invoiceBEAN.getTaxTotal(),
            invoiceBEAN.getTotal(), invoiceBEAN.getPaymentReminderEnabled(), invoiceBEAN.getPaymentMade(), invoiceBEAN.getCreditsApplied(), invoiceBEAN.getTaxAmountWithheld(),
            invoiceBEAN.getBalance(), invoiceBEAN.getWriteOffAmount(), invoiceBEAN.getAllowPartiaPayments(), invoiceBEAN.getIsEmailed(), invoiceBEAN.getRemindersSent(),
            invoiceBEAN.getLastReminderSentDate(), invoiceBEAN.getNotes(), invoiceBEAN.getTerms(), invoiceBEAN.getTemplateId(), invoiceBEAN.getTemplateName(),
            invoiceBEAN.getCanSendInEmail(), invoiceBEAN.getCreatedUser(), invoiceBEAN.getUpdatedUser(), invoiceBEAN.getCreatedDate(), invoiceBEAN.getUpdatedDate(),
            invoiceBEAN.getInvoiceIid()
        });
        return (row > 0);
    }

    @Override
    public boolean deleteInvoiceMain(String invoiceId) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "DELETE FROM " + TableNames.TBL_INV_MAIN + "\n"
                + " WHERE\n"
                + " invoice_id=?";
        int row = jdbcTemplate.update(sql, new Object[]{invoiceId});
        return (row > 0);
    }

    @Override
    public ObservableList<InvoiceBEAN> retrieveInvoiceByEnquiryId(String enquiryId) {
        ObservableList<InvoiceBEAN> listInvoices = FXCollections.observableArrayList();
        jdbcTemplate = new JdbcTemplate(dataSource);
        String query = "SELECT * FROM " + TableNames.TBL_INV_MAIN + " WHERE enquiry_id=?";
        List<InvoiceBEAN> temp = jdbcTemplate.query(query, new Object[]{enquiryId}, new InvoiceMapper());
        listInvoices.addAll(temp);
        return listInvoices;
    }

    @Override
    public ObservableList<InvoiceBEAN> retrieveInvoiceByInvoiceId(String invoiceId) {
        ObservableList<InvoiceBEAN> listInvoices = FXCollections.observableArrayList();
        jdbcTemplate = new JdbcTemplate(dataSource);
        String query = "SELECT * FROM " + TableNames.TBL_INV_MAIN + " WHERE invoice_id=?";
        List<InvoiceBEAN> temp = jdbcTemplate.query(query, new Object[]{invoiceId}, new InvoiceMapper());
        listInvoices.addAll(temp);
        return listInvoices;
    }

    @Override
    public boolean insertInvoiceItem(InvoiceItemsBEAN invoiceItemsBEAN) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "INSERT INTO " + TableNames.TBL_INV_ITEMS + " (\n"
                + "	line_id,\n"
                + "	invoice_id,\n"
                + "	item_id,\n"
                + "	time_entry_ids,\n"
                + "	expense_id,\n"
                + "	expense_reciept_name,\n"
                + "	item_name,\n"
                + "	description,\n"
                + "	item_count,\n"
                + "	bcy_rate,\n"
                + "	rate,\n"
                + "	quantity,\n"
                + "	unit,\n"
                + "	discount_amount,\n"
                + "	discount_type,\n"
                + "	tax_id,\n"
                + "	tax_name,\n"
                + "	tax_type,\n"
                + "	tax_percentage,\n"
                + "	item_total\n"
                + ")\n"
                + "VALUES\n"
                + "	(\n"
                + "		?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?\n"
                + "	);";
        int row = jdbcTemplate.update(sql, new Object[]{invoiceItemsBEAN.getLineId(), invoiceItemsBEAN.getInvoiceId(),
            invoiceItemsBEAN.getItemId(), invoiceItemsBEAN.getTimeEntryIds(), invoiceItemsBEAN.getExpenseId(),
            invoiceItemsBEAN.getExpenseRecieptName(), invoiceItemsBEAN.getItemName(), invoiceItemsBEAN.getDescription(),
            invoiceItemsBEAN.getItemCount(), invoiceItemsBEAN.getBcyRate(),
            invoiceItemsBEAN.getRate(), invoiceItemsBEAN.getQuantity(), invoiceItemsBEAN.getUnit(), invoiceItemsBEAN.getDiscountAmount(), invoiceItemsBEAN.getDiscountType(),
            invoiceItemsBEAN.getTaxId(), invoiceItemsBEAN.getTaxName(), invoiceItemsBEAN.getTaxType(), invoiceItemsBEAN.getTaxPercentage(),
            invoiceItemsBEAN.getItemTotal()
        });
        return (row > 0);
    }

    @Override
    public boolean insertInvoiceItem(List<InvoiceItemsBEAN> listinvoiceItems) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateInvoiceItem(InvoiceItemsBEAN invoiceItemsBEAN) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "UPDATE ia_reg.account_invoice_items_tbl\n"
                + "SET line_id =  ?,\n"
                + " invoice_id =  ?,\n"
                + " item_id =  ?,\n"
                + " time_entry_ids =  ?,\n"
                + " expense_id =  ?,\n"
                + " expense_reciept_name =  ?,\n"
                + " item_name =  ?,\n"
                + " description =  ?,\n"
                + " item_count =  ?,\n"
                + " bcy_rate =  ?,\n"
                + " rate =  ?,\n"
                + " quantity =  ?,\n"
                + " unit =  ?,\n"
                + " discount_amount =  ?,\n"
                + " discount_type =  ?,\n"
                + " tax_id =  ?,\n"
                + " tax_name =  ?,\n"
                + " tax_type =  ?,\n"
                + " tax_percentage =  ?,\n"
                + " item_total =  ?\n"
                + "WHERE\n"
                + "	line_id= ?";
        int row = jdbcTemplate.update(sql, new Object[]{invoiceItemsBEAN.getLineId(), invoiceItemsBEAN.getInvoiceId(),
            invoiceItemsBEAN.getItemId(), invoiceItemsBEAN.getTimeEntryIds(), invoiceItemsBEAN.getExpenseId(),
            invoiceItemsBEAN.getExpenseRecieptName(), invoiceItemsBEAN.getItemName(), invoiceItemsBEAN.getDescription(),
            invoiceItemsBEAN.getItemCount(), invoiceItemsBEAN.getBcyRate(),
            invoiceItemsBEAN.getRate(), invoiceItemsBEAN.getQuantity(), invoiceItemsBEAN.getUnit(), invoiceItemsBEAN.getDiscountAmount(), invoiceItemsBEAN.getDiscountType(),
            invoiceItemsBEAN.getTaxId(), invoiceItemsBEAN.getTaxName(), invoiceItemsBEAN.getTaxType(), invoiceItemsBEAN.getTaxPercentage(),
            invoiceItemsBEAN.getItemTotal(), invoiceItemsBEAN.getLineId()
        });
        return (row > 0);
    }

    @Override
    public boolean deleteInvoiceItem(String lineId) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "DELETE FROM " + TableNames.TBL_INV_ITEMS + "\n"
                + " WHERE\n"
                + " line_id=?";
        int row = jdbcTemplate.update(sql, new Object[]{lineId});
        return (row > 0);
    }

    @Override
    public ObservableList<InvoiceItemsBEAN> retrieveInvoiceItem(String invoiceId) {

        ObservableList<InvoiceItemsBEAN> listInvoices = FXCollections.observableArrayList();
        jdbcTemplate = new JdbcTemplate(dataSource);
        String query = "SELECT * FROM " + TableNames.TBL_INV_ITEMS + " WHERE invoice_id = ?";
        List<InvoiceItemsBEAN> temp = jdbcTemplate.query(query, new Object[]{invoiceId}, new InvoiceItemMapper());
        listInvoices.addAll(temp);
        return listInvoices;

    }

    @Override
    public ObservableList<InvoiceBEAN> retrieveInvoiceByBranch(String branchName) {

        ObservableList<InvoiceBEAN> listInvoices = FXCollections.observableArrayList();
        jdbcTemplate = new JdbcTemplate(dataSource);
        String query = "SELECT * FROM " + TableNames.TBL_INV_MAIN + " WHERE branch=?";
        List<InvoiceBEAN> temp = jdbcTemplate.query(query, new Object[]{branchName}, new InvoiceMapper());
        listInvoices.addAll(temp);
        return listInvoices;

    }

    @Override
    public ObservableList<InvoiceBEAN> retrieveInvoiceAll() {

        ObservableList<InvoiceBEAN> listInvoices = FXCollections.observableArrayList();
        jdbcTemplate = new JdbcTemplate(dataSource);
        String query = "SELECT * FROM " + TableNames.TBL_INV_MAIN;
        List<InvoiceBEAN> temp = jdbcTemplate.query(query, new Object[]{}, new InvoiceMapper());
        listInvoices.addAll(temp);
        return listInvoices;

    }

    @Override
    public String createInvoiceNumber() {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String query = "SELECT MAX(invoice_number) FROM  " + TableNames.TBL_INV_MAIN;
        String invoiceNumber = jdbcTemplate.queryForObject(query, String.class);
        return invoiceNumber;
    }

    public class InvoiceMapper implements RowMapper<InvoiceBEAN> {

        /**
         *
         * @param rs
         * @param rowNum
         * @return
         * @throws SQLException
         */
        @Override
        public InvoiceBEAN mapRow(ResultSet rs, int rowNum) throws SQLException {

            InvoiceBEAN invoiceItemsBEAN = new InvoiceBEAN();
            invoiceItemsBEAN.setInvoiceIid(rs.getString("invoice_id"));
            invoiceItemsBEAN.setInvoiceNumber(rs.getString("invoice_number"));
            invoiceItemsBEAN.setEnquiryId(rs.getString("enquiry_id"));
            invoiceItemsBEAN.setInvoiceStatus(rs.getString("invoice_status"));
            invoiceItemsBEAN.setInvoiceCreatedDate(LocalDate.parse(rs.getString("invoice_created_date")));
            invoiceItemsBEAN.setDueDate(LocalDate.parse(rs.getString("due_date")));
            invoiceItemsBEAN.setPaymentTerms(rs.getString("payment_terms"));
            invoiceItemsBEAN.setPaymentLabel(rs.getString("payment_label"));
            invoiceItemsBEAN.setPaymentExpectedDate(rs.getString("payment_expected_date"));
            invoiceItemsBEAN.setLastReminderSentDate(rs.getString("last_payment_date"));
            invoiceItemsBEAN.setReferenceNumber(rs.getString("reference_number"));
            invoiceItemsBEAN.setContactPersons(rs.getString("contact_persons"));
            invoiceItemsBEAN.setCurrencyId(rs.getString("currency_id"));
            invoiceItemsBEAN.setCurrencyCode(rs.getString("currency_code"));
            invoiceItemsBEAN.setExchangeRate(rs.getString("exchange_rate"));
            invoiceItemsBEAN.setDiscount(rs.getString("discount"));
            invoiceItemsBEAN.setIsDiscountBeforeTax(rs.getString("is_discount_before_tax"));
            invoiceItemsBEAN.setDiscountType(rs.getString("discount_type"));
            invoiceItemsBEAN.setIsInclusiveTax(rs.getString("is_inclusive_tax"));
            invoiceItemsBEAN.setRecurringInvoiceId(rs.getString("recurring_invoice_id"));
            invoiceItemsBEAN.setAdditionalCharge(rs.getString("additional_charge"));
            invoiceItemsBEAN.setAdjustment(rs.getString("adjustment"));
            invoiceItemsBEAN.setAdjustmentDescription(rs.getString("adjustment_description"));
            invoiceItemsBEAN.setSubTotal(rs.getString("sub_total"));
            invoiceItemsBEAN.setTaxTotal(rs.getString("tax_total"));
            invoiceItemsBEAN.setTotal(rs.getString("total"));
            invoiceItemsBEAN.setPaymentReminderEnabled(rs.getString("payment_reminder_enabled"));
            invoiceItemsBEAN.setPaymentMade(rs.getString("payment_made"));
            invoiceItemsBEAN.setCreditsApplied(rs.getString("credits_applied"));
            invoiceItemsBEAN.setTaxAmountWithheld(rs.getString("tax_amount_withheld"));
            invoiceItemsBEAN.setBalance(rs.getString("balance"));
            invoiceItemsBEAN.setWriteOffAmount(rs.getString("write_off_amount"));
            invoiceItemsBEAN.setAllowPartiaPayments(rs.getString("allow_partial_payments"));
            invoiceItemsBEAN.setIsEmailed(rs.getString("is_emailed"));
            invoiceItemsBEAN.setRemindersSent(rs.getString("reminders_sent"));
            invoiceItemsBEAN.setLastReminderSentDate(rs.getString("last_reminder_sent_date"));
            invoiceItemsBEAN.setNotes(rs.getString("notes"));
            invoiceItemsBEAN.setTerms(rs.getString("terms"));
            invoiceItemsBEAN.setTemplateId(rs.getString("template_id"));
            invoiceItemsBEAN.setTemplateName(rs.getString("template_name"));
            invoiceItemsBEAN.setCanSendInEmail(rs.getString("can_send_in_email"));
            invoiceItemsBEAN.setCreatedUser(rs.getString("created_user"));
            invoiceItemsBEAN.setUpdatedUser(rs.getString("updated_user"));
            invoiceItemsBEAN.setCreatedDate(rs.getString("created_date"));
            invoiceItemsBEAN.setUpdatedDate(rs.getString("updated_date"));
            invoiceItemsBEAN.setBranch(rs.getString("branch"));
            return invoiceItemsBEAN;
        }

    }

    public class InvoiceItemMapper implements RowMapper<InvoiceItemsBEAN> {

        /**
         *
         * @param rs
         * @param rowNum
         * @return
         * @throws SQLException
         */
        @Override
        public InvoiceItemsBEAN mapRow(ResultSet rs, int rowNum) throws SQLException {

            InvoiceItemsBEAN invoiceItemsBEAN = new InvoiceItemsBEAN();
            invoiceItemsBEAN.setLineId(rs.getString("line_id"));
            invoiceItemsBEAN.setInvoiceId(rs.getString("invoice_id"));
            invoiceItemsBEAN.setItemId(rs.getString("item_id"));
            invoiceItemsBEAN.setTimeEntryIds(rs.getString("time_entry_ids"));
            invoiceItemsBEAN.setExpenseId(rs.getString("expense_id"));
            invoiceItemsBEAN.setExpenseRecieptName(rs.getString("expense_reciept_name"));
            invoiceItemsBEAN.setItemName(rs.getString("item_name"));
            invoiceItemsBEAN.setDescription(rs.getString("description"));
            invoiceItemsBEAN.setItemCount(rs.getString("item_count"));
            invoiceItemsBEAN.setBcyRate(rs.getString("bcy_rate"));
            invoiceItemsBEAN.setRate(rs.getString("rate"));
            invoiceItemsBEAN.setQuantity(rs.getString("quantity"));
            invoiceItemsBEAN.setUnit(rs.getString("unit"));
            invoiceItemsBEAN.setDiscountAmount(rs.getString("discount_amount"));
            invoiceItemsBEAN.setDiscountType(rs.getString("discount_type"));
            invoiceItemsBEAN.setTaxId(rs.getString("tax_id"));
            invoiceItemsBEAN.setTaxName(rs.getString("tax_name"));
            invoiceItemsBEAN.setTaxType(rs.getString("tax_type"));
            invoiceItemsBEAN.setTaxPercentage(rs.getString("tax_percentage"));
            invoiceItemsBEAN.setItemTotal(rs.getString("item_total"));
            return invoiceItemsBEAN;
        }

    }
}
