/*
 * Copyright ZoftSolutions(C) 2016 SUMESH T.G <ZoftSolutions>
 The regular expression classes are not available.
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 The regular expression classes are not available.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 The regular expression classes are not available.
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 *  Developed by ZoftSolutions (2015) Company.
 */
package com.zs.ina.admin.master.payment;

import com.zs.ina.admin.master.payment.dao.PaymentBEAN;
import com.zs.ina.admin.master.payment.dao.PaymentIMPL;
import com.zs.ina.admin.master.payment.dao.PaymentPOJO;
import com.zs.ina.admin.master.payment.dao.PaymentSERVICE;
import com.zs.ina.common.error.ShowPopupMessages;
import com.zs.traynotification.notification.Notification;
import com.zs.traynotification.notification.Notifications;
import com.zs.traynotification.notification.TrayNotification;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import org.apache.log4j.Logger;

/**
 * FXML Controller class
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class FXMLPaymentHeadsController implements Initializable {

    @FXML
    private TextField txtPaymentHead;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnSave;
    @FXML
    private ComboBox<PaymentPOJO> cmbPaymentEnable;
    @FXML
    private Button btnDisable;
    @FXML
    private ComboBox<PaymentPOJO> cmbPaymentDisable;
    @FXML
    private Button btnEnable;

    PaymentBEAN paymentBEAN = new PaymentBEAN();
    PaymentSERVICE paymentSERVICE = new PaymentSERVICE(new PaymentIMPL());
    Logger logger = Logger.getLogger(FXMLPaymentHeadsController.class);
    ObservableList<PaymentPOJO> enabledPaymentsList = FXCollections.observableArrayList();
    ObservableList<PaymentPOJO> disabledPaymentsList = FXCollections.observableArrayList();
    ShowPopupMessages showPopupMessages = new ShowPopupMessages();

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //      autoCompletion();
        loadcmbPayments();
        bindAllData();
        btnDisable.disableProperty().bind(Bindings.equal(cmbPaymentEnable.getSelectionModel().selectedIndexProperty(), -1));
        btnEnable.disableProperty().bind(Bindings.equal(cmbPaymentDisable.getSelectionModel().selectedIndexProperty(), -1));

        btnSave.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (validatePaymentHead()) {
                    int result_insert = paymentSERVICE.insertPaymentHead(paymentBEAN);
                    if (result_insert > 0) {
                        clearAllFields();
                        loadcmbPayments();
                        Notification notification = Notifications.SUCCESS;
                        TrayNotification trayNotification = new TrayNotification("Payment Head saved", "Payment Head saved successfully", notification);
                        trayNotification.showAndDismiss(Duration.seconds(3), txtPaymentHead);

                    } else {
                        Notification notification = Notifications.ERROR;
                        TrayNotification trayNotification = new TrayNotification("Payment Head not saved", "Payment Head not saved correctly", notification);
                        trayNotification.showAndDismiss(Duration.seconds(3), txtPaymentHead);

                    }
                }

            }

        });
        cmbPaymentEnable.valueProperty().addListener(new ChangeListener<PaymentPOJO>() {

            @Override
            public void changed(ObservableValue<? extends PaymentPOJO> observable, PaymentPOJO oldValue, PaymentPOJO newValue) {
                if (newValue != null) {
                    paymentBEAN.setHead_id(newValue.getHead_id());
                }
            }
        });
        cmbPaymentDisable.valueProperty().addListener(new ChangeListener<PaymentPOJO>() {

            @Override
            public void changed(ObservableValue<? extends PaymentPOJO> observable, PaymentPOJO oldValue, PaymentPOJO newValue) {
                if (newValue != null) {
                    paymentBEAN.setHead_id(newValue.getHead_id());
                }
            }
        });

        btnClear.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                clearAllFields();

            }
        });

        btnDisable.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                paymentBEAN.setEnable("n");
                int result_update = paymentSERVICE.disablePaymentHead(paymentBEAN);
                if (result_update > 0) {
                    clearAllFields();
                    loadcmbPayments();
                    showPopupMessages.showSuccess("Payment Head Disabled", "Payment Head Disabled successfully", cmbPaymentEnable);
                } else {
                    showPopupMessages.showError("Payment Head Disabling Failed ! Try again !", cmbPaymentEnable);
                }
            }
        });
        btnEnable.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                paymentBEAN.setEnable("y");
                int result_update = paymentSERVICE.disablePaymentHead(paymentBEAN);
                if (result_update > 0) {
                    clearAllFields();
                    loadcmbPayments();
                    showPopupMessages.showSuccess("Payment Head Enabled", "Payment Head Enabled successfully", cmbPaymentEnable);
                } else {
                    showPopupMessages.showError("Payment Head Enabling Failed ! Try again !", cmbPaymentEnable);
                }
            }
        });
        txtPaymentHead.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    if (!(newValue.matches("[a-zA-Z,\\s]*"))) {
                        Bindings.unbindBidirectional(paymentBEAN.headProperty(), txtPaymentHead.textProperty());
                        txtPaymentHead.setText(oldValue);
                        Bindings.bindBidirectional(paymentBEAN.headProperty(), txtPaymentHead.textProperty());
                    } else {
                        Bindings.unbindBidirectional(paymentBEAN.headProperty(), txtPaymentHead.textProperty());
                        txtPaymentHead.setText(newValue);
                        Bindings.bindBidirectional(paymentBEAN.headProperty(), txtPaymentHead.textProperty());
                    }

                }
            }

        });
    }

    /**
     *
     */
    public void bindAllData() {

        Bindings.bindBidirectional(txtPaymentHead.textProperty(), paymentBEAN.headProperty());

    }

    /**
     *
     * @return
     */
    public boolean validatePaymentHead() {
        boolean flag = true;
        if (paymentBEAN.getHead() == null || paymentBEAN.getHead().equalsIgnoreCase("")) {
            showPopupMessages.showError("Enter Payment Head", txtPaymentHead);
            flag = false;
        }
        return flag;
    }

    /**
     *
     */
    public void clearAllFields() {

        cmbPaymentDisable.getSelectionModel().clearSelection();
        cmbPaymentEnable.getSelectionModel().clearSelection();
        paymentBEAN = new PaymentBEAN();
        bindAllData();

    }

    /**
     *
     * @return
     */
    public boolean validateAllFields() {
        boolean flag = true;
        if (paymentBEAN.getHead() == null || paymentBEAN.getHead().equals("")) {
            showPopupMessages.showError("Enter Payment Head", txtPaymentHead);
            flag = false;
        }
        return flag;
    }

    /**
     *
     */
    public void loadcmbPayments() {

        cmbPaymentEnable.getItems().clear();
        enabledPaymentsList.clear();
        enabledPaymentsList = paymentSERVICE.retrieveEnabledPayments();
        cmbPaymentEnable.setItems(enabledPaymentsList);

        cmbPaymentDisable.getItems().clear();
        disabledPaymentsList.clear();
        disabledPaymentsList = paymentSERVICE.retrieveDisabledPayments();
        cmbPaymentDisable.setItems(disabledPaymentsList);

    }

//    public void autoCompletion() {
//        List<String> allMethodsNames = MethodIMPL.getAllEnquiryMethods();
//        txtEnquiryMethod = TextFields.createSearchField();
//        AutoCompletionBinding<String> autoCompletionBindingNumber = TextFields.bindAutoCompletion(txtEnquiryMethod, allMethodsNames);
//        txtEnquiryMethod.setPrefWidth(218);
//        hboxEnquiryMethod.getChildren().remove(1);
//        hboxEnquiryMethod.getChildren().add(txtEnquiryMethod);
//    }
}
