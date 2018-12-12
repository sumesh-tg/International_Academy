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
package com.zs.ina.accounts.constants;

/**
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class InvoiceConstants {

    public enum Status {
        ALL("All"),
        DRAFT("Draft"),
        PARTIALLY_PAID("Partially Paid"),
        UNPAID("Unpaid"),
        OVERDUE("Over Due"),
        PAID("Paid");

        private String label;

        Status(String label) {
            this.label = label;
        }

        public String toString() {
            return label;
        }
    }

    public enum Discounts {
        PERCENTAGE("%"),
        RUPEESE("RS");

        private String discounts;

        Discounts(String discounts) {
            this.discounts = discounts;
        }

        public String toString() {
            return discounts;
        }
    }

    public enum SortType {
        CREATED_TIME("Created Time"),
        LAST_MODIFY_TIME("Last Modified Time"),
        DATE("Date"),
        INVOICE("Invoice"),
        ORDER_NUMBER("Order Number"),
        DUE_DATE("Due Date"),
        AMOUNT("Amount"),
        STATUS("Status"),
        BALANCE_DUE("Balance Due"),
        CUSTOMER_NAME("Customer Name"),
        REFRESH_LIST("Customer Name"),
        EXPORT_INVOICES("Customer Name");
        private String name;

        SortType(String name) {
            this.name = name;
        }

        public String toString() {
            return name;
        }
    }

    public enum MasterForms {
        INVOICE_ITEM("Add/Update Invoice Items"),
        TAX("Add/Update Tax"),
        PAYMENT_TERMS("Add/Update Payment Terms"),
        INVOICE("Invoice"),
        ORDER_NUMBER("Order Number"),
        DUE_DATE("Due Date"),
        AMOUNT("Amount"),
        STATUS("Status"),
        BALANCE_DUE("Balance Due"),
        CUSTOMER_NAME("Customer Name"),
        REFRESH_LIST("Customer Name"),
        EXPORT_INVOICES("Customer Name");
        private String master;

        MasterForms(String master) {
            this.master = master;
        }

        public String toString() {
            return master;
        }
    }
}
