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

import com.zs.ina.enquiry.appointment.dao.UserPOJO;
import javafx.collections.ObservableList;

/**
 *
 * @author 100035
 */
public interface PaymentDAO {

    
    /* ============== Add Payment Head ============ */

    /**
     *
     * @param paymentBEAN
     * @return
     */

    public int insertPaymentHead(PaymentBEAN paymentBEAN);

    /**
     *
     * @param paymentBEAN
     * @return
     */
    public int disablePaymentHead(PaymentBEAN paymentBEAN);

    /**
     *
     * @return
     */
    public ObservableList<PaymentPOJO> retrieveEnabledPayments();
    
    /**
     *
     * @return
     */
    public ObservableList<PaymentPOJO> retrieveDisabledPayments();
    /* ============== Add Payment Amount ============ */
    
    /**
     *
     * @param paymentHeadsIdList
     * @param programReqd
     * @return
     */
    public int insertPaymentHeadAmount(ObservableList<PaymentBEAN> paymentHeadsIdList,String programReqd);

    /**
     *
     * @param paymentHeadsIdList
     * @param programReqd
     * @return
     */
    public int updatePaymentHeadAmount(ObservableList<PaymentBEAN> paymentHeadsIdList,String programReqd);

   // public int updateMethodType(MethodBEAN methodBEAN);

    /**
     *
     * @return
     */
    public ObservableList<PaymentBEAN> retrievePaymentHeads();

    /**
     *
     * @param programReqd
     * @return
     */
    public ObservableList<PaymentBEAN> retrievePaymentHeadsAmount(String programReqd);

    /**
     *
     * @param programReqd
     * @return
     */
    public int deletePaymentHeadsAmount(String programReqd);
    
  //  public ObservableList<PaymentBEAN> retrievePayAmounts();

   // public ObservableList<MethodPOJO> retrieveMethodsOnly();
}
