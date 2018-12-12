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
package com.zs.ina.admin.master.payment;

import com.zs.ina.admin.master.payment.dao.MandatoryBEAN;
import com.zs.ina.admin.master.payment.dao.PaymentBEAN;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;

/**
 * FXML Controller class
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class FXMLSingleMandatoryHeadController implements Initializable {

    @FXML
    private CheckBox checkBoxHead;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /**
     *
     * @param mandatoryBEAN
     * @param masterMandatoryPaymentHead
     * @param curAppStatusId
     */
    public void loadPaymentHeads(MandatoryBEAN mandatoryBEAN, ObservableList<MandatoryBEAN> masterMandatoryPaymentHead, String curAppStatusId) {
        checkBoxHead.setText(mandatoryBEAN.getHeadName());
        checkBoxHead.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    mandatoryBEAN.setAppStatusId(curAppStatusId);
                    if (!masterMandatoryPaymentHead.contains(mandatoryBEAN)) {
                        masterMandatoryPaymentHead.add(mandatoryBEAN);
                    }
                } else {
                    masterMandatoryPaymentHead.remove(mandatoryBEAN);
                }
            }
        });

        if (mandatoryBEAN.getHeadId() != null && mandatoryBEAN.getIsMandatoryHeadId() != null) {
            if (mandatoryBEAN.getHeadId().equalsIgnoreCase(mandatoryBEAN.getIsMandatoryHeadId())) {
                checkBoxHead.setSelected(true);
//                masterMandatoryPaymentHead.add(mandatoryBEAN);
            }
        }
    }
}
