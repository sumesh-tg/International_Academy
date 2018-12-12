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
package com.ina.application.config;

import com.zs.ina.accounts.invoice.dao.InvoiceDAO;
import com.zs.ina.accounts.invoice.dao.InvoiceIMPL;
import com.zs.ina.accounts.master.dao.InvoiceItemsMasterDAO;
import com.zs.ina.accounts.master.dao.InvoiceItemsMasterIMPL;
import com.zs.ina.accounts.master.dao.MasterTaxDAO;
import com.zs.ina.accounts.master.dao.MasterTaxIMPL;
import com.zs.ina.accounts.master.dao.PaymentTermsDAO;
import com.zs.ina.accounts.master.dao.PaymentTermsIMPL;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
@Configuration
public class AccountsConfig {

    @Bean
    InvoiceDAO invoiceDAO() {
        return new InvoiceIMPL();
    }
    @Bean
    InvoiceItemsMasterDAO invoiceItemsMasterDAO() {
        return new InvoiceItemsMasterIMPL();
    }
    @Bean
    MasterTaxDAO masterTaxDAO() {
        return new MasterTaxIMPL();
    }
    @Bean
    PaymentTermsDAO paymentTermsDAO() {
        return new PaymentTermsIMPL();
    }
}
