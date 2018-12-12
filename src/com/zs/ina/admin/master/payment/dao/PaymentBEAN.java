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

import java.util.Objects;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author 100035
 */
public class PaymentBEAN {

    private final StringProperty head_id = new SimpleStringProperty();
    private final StringProperty head = new SimpleStringProperty();
    private final StringProperty enable = new SimpleStringProperty();

    /* =============== Add Payment Head Amount ================== */
    private final StringProperty assignId = new SimpleStringProperty();
    private final StringProperty programReqd = new SimpleStringProperty();
    private final StringProperty amount = new SimpleStringProperty();
    private final StringProperty headsId = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getEnable() {
        return enable.get();
    }

    /**
     *
     * @param value
     */
    public void setEnable(String value) {
        enable.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty enableProperty() {
        return enable;
    }

    /**
     *
     * @return
     */
    public String getHeadsId() {
        return headsId.get();
    }

    /**
     *
     * @param value
     */
    public void setHeadsId(String value) {
        headsId.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty headsIdProperty() {
        return headsId;
    }

    /**
     *
     * @return
     */
    public String getAssignId() {
        return assignId.get();
    }

    /**
     *
     * @param value
     */
    public void setAssignId(String value) {
        assignId.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty assignIdProperty() {
        return assignId;
    }

    /**
     *
     * @return
     */
    public String getAmount() {
        return amount.get();
    }

    /**
     *
     * @param value
     */
    public void setAmount(String value) {
        amount.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty amountProperty() {
        return amount;
    }

    /**
     *
     * @return
     */
    public String getProgramReqd() {
        return programReqd.get();
    }

    /**
     *
     * @param value
     */
    public void setProgramReqd(String value) {
        programReqd.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty programReqdProperty() {
        return programReqd;
    }

    /**
     *
     * @return
     */
    public String getHead() {
        return head.get();
    }

    /**
     *
     * @param value
     */
    public void setHead(String value) {
        head.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty headProperty() {
        return head;
    }

    /**
     *
     * @return
     */
    public String getHead_id() {
        return head_id.get();
    }

    /**
     *
     * @param value
     */
    public void setHead_id(String value) {
        head_id.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty head_idProperty() {
        return head_id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.head_id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PaymentBEAN other = (PaymentBEAN) obj;
        if (!Objects.equals(this.head_id, other.head_id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PaymentBEAN{" + "head_id=" + head_id + ", head=" + head + ", assignId=" + assignId + ", programReqd=" + programReqd + ", amount=" + amount + ", headsId=" + headsId + '}';
    }

}
