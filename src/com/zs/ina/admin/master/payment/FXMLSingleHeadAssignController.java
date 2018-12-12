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
package com.zs.ina.admin.master.payment;

import com.zs.ina.admin.master.payment.dao.PaymentBEAN;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author 100035
 */
public class FXMLSingleHeadAssignController implements Initializable {

    @FXML
    private CheckBox chkPaymentHead;
    @FXML
    private TextField txtPaymentAmount;

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
     * @param paymentBEAN
     * @param masterPaymentHeadsList
     */
    public void passPaymentHeadDetails(PaymentBEAN paymentBEAN, ObservableList<PaymentBEAN> masterPaymentHeadsList) {

        //chkPaymentHead.setDisable(true);
        chkPaymentHead.setText(paymentBEAN.getHead());
        txtPaymentAmount.setDisable(true);
        chkPaymentHead.selectedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    chkPaymentHead.setSelected(true);
                    txtPaymentAmount.setDisable(false);
                    if (!masterPaymentHeadsList.contains(paymentBEAN)) {
                        masterPaymentHeadsList.add(paymentBEAN);
                    }
                } else {

                    chkPaymentHead.setSelected(false);
                    txtPaymentAmount.setDisable(true);
                    masterPaymentHeadsList.remove(paymentBEAN);
                    txtPaymentAmount.setText("");
                    Bindings.bindBidirectional(txtPaymentAmount.textProperty(), paymentBEAN.amountProperty());
                }
            }
        });
        if (paymentBEAN.getHead_id() != null && paymentBEAN.getHeadsId() != null) {
            if (paymentBEAN.getHead_id().equalsIgnoreCase(paymentBEAN.getHeadsId())) {
                chkPaymentHead.setSelected(true);
//                masterPaymentHeadsIdList.add(paymentBEAN);
                txtPaymentAmount.setDisable(false);
                txtPaymentAmount.setText(paymentBEAN.getAmount());
                Bindings.bindBidirectional(txtPaymentAmount.textProperty(), paymentBEAN.amountProperty());
            }
        }
        txtPaymentAmount.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    if (!(newValue.matches("[0-9]*"))) {
                        Bindings.unbindBidirectional(paymentBEAN.amountProperty(), txtPaymentAmount.textProperty());
                        txtPaymentAmount.setText(oldValue);
                        Bindings.bindBidirectional(paymentBEAN.amountProperty(), txtPaymentAmount.textProperty());
                    } else {
                        Bindings.unbindBidirectional(paymentBEAN.amountProperty(), txtPaymentAmount.textProperty());
                        txtPaymentAmount.setText(newValue);
                        Bindings.bindBidirectional(paymentBEAN.amountProperty(), txtPaymentAmount.textProperty());
                    }

                }
            }

        });

    }

}
