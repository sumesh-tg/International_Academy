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
package com.zs.ina.admin.master.payment.dao;

import com.zs.ina.admin.master.documents.dao.DocumentBEAN;
import javafx.collections.ObservableList;

/**
 *
 * @author 100035
 */
public class PaymentSERVICE {

    PaymentDAO paymentDAO = new PaymentIMPL();

    /**
     *
     * @param paymentDAO
     */
    public PaymentSERVICE(PaymentIMPL paymentDAO) {
        this.paymentDAO = paymentDAO;
    }

    /**
     *
     * @param paymentBEAN
     * @return
     */
    public int insertPaymentHead(PaymentBEAN paymentBEAN) {
        return paymentDAO.insertPaymentHead(paymentBEAN);
    }

    /**
     *
     * @param paymentBEAN
     * @return
     */
    public int disablePaymentHead(PaymentBEAN paymentBEAN) {
        return paymentDAO.disablePaymentHead(paymentBEAN);
    }

    /**
     *
     * @return
     */
    public ObservableList<PaymentPOJO> retrieveEnabledPayments() {
        return paymentDAO.retrieveEnabledPayments();
    }

    /**
     *
     * @return
     */
    public ObservableList<PaymentPOJO> retrieveDisabledPayments() {
        return paymentDAO.retrieveDisabledPayments();
    }

    /**
     *
     * @return
     */
    public ObservableList<PaymentBEAN> retrievePaymentHeads() {
        return paymentDAO.retrievePaymentHeads();
    }

    /**
     *
     * @param paymentHeadsIdList
     * @param programReqd
     * @return
     */
    public int insertPaymentHeadAmount(ObservableList<PaymentBEAN> paymentHeadsIdList, String programReqd) {
        return paymentDAO.insertPaymentHeadAmount(paymentHeadsIdList, programReqd);
    }

    /**
     *
     * @param programReqd
     * @return
     */
    public ObservableList<PaymentBEAN> retrievePaymentHeadsAmount(String programReqd) {
        return paymentDAO.retrievePaymentHeadsAmount(programReqd);
    }

    /**
     *
     * @param programReqd
     * @return
     */
    public int deletePaymentHeadsAmount(String programReqd) {
        return paymentDAO.deletePaymentHeadsAmount(programReqd);
    }

    
}
