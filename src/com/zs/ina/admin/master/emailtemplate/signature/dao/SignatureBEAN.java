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
package com.zs.ina.admin.master.emailtemplate.signature.dao;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author 100035
 */
public class SignatureBEAN {

    private final StringProperty signatureId = new SimpleStringProperty();
    private final StringProperty emailHead = new SimpleStringProperty();
    private final StringProperty signature = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getSignature() {
        return signature.get();
    }

    /**
     *
     * @param value
     */
    public void setSignature(String value) {
        signature.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty signatureProperty() {
        return signature;
    }

    /**
     *
     * @return
     */
    public String getEmailHead() {
        return emailHead.get();
    }

    /**
     *
     * @param value
     */
    public void setEmailHead(String value) {
        emailHead.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty emailHeadProperty() {
        return emailHead;
    }

    /**
     *
     * @return
     */
    public String getSignatureId() {
        return signatureId.get();
    }

    /**
     *
     * @param value
     */
    public void setSignatureId(String value) {
        signatureId.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty signatureIdProperty() {
        return signatureId;
    }
    
}
