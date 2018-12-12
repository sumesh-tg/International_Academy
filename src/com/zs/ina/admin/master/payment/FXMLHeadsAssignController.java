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

import com.zs.ina.admin.master.payment.dao.PaymentBEAN;
import com.zs.ina.admin.master.payment.dao.PaymentIMPL;
import com.zs.ina.admin.master.payment.dao.PaymentPOJO;
import com.zs.ina.admin.master.payment.dao.PaymentSERVICE;
import com.zs.ina.common.error.ShowPopupMessages;
import com.zs.ina.enquiry.appointment.attendance.FXMLMeetingListController;
import com.zs.ina.enquiry.appointment.dao.AppointmentScheduleBEAN;
import com.zs.traynotification.notification.Notification;
import com.zs.traynotification.notification.Notifications;
import com.zs.traynotification.notification.TrayNotification;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import javafx.util.Duration;
import org.apache.log4j.Logger;

/**
 * FXML Controller class
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class FXMLHeadsAssignController implements Initializable {

    @FXML
    private ComboBox<String> cmbProgramReqd;
    @FXML
    private ListView<PaymentBEAN> listViewPayAmount;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnClear;

    PaymentBEAN paymentBEAN = new PaymentBEAN();
    PaymentSERVICE paymentSERVICE = new PaymentSERVICE(new PaymentIMPL());
    Logger logger = Logger.getLogger(FXMLHeadsAssignController.class);
    ObservableList<String> programList = FXCollections.observableArrayList();
    ObservableList<PaymentBEAN> masterPaymentHeadsList = FXCollections.observableArrayList();
    ObservableList<PaymentBEAN> paymentsAssignedList = FXCollections.observableArrayList();
    ShowPopupMessages showPopupMessages = new ShowPopupMessages();

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clearAllFields();
         btnClear.disableProperty().bind(Bindings.equal(cmbProgramReqd.getSelectionModel().selectedIndexProperty(), -1));

//        paymentHeadsList = paymentSERVICE.retrievePaymentHeads();
//        System.out.println("Test List PAYMENT HEADS:: " + paymentHeadsList.toString());

        /* ============ Display Events List of the Anchor(Current User) =============== */
        //     addPaymentHeadsIntoListView(paymentHeadsList);
        cmbProgramReqd.valueProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    masterPaymentHeadsList.clear();
                    paymentsAssignedList.clear();
                    paymentBEAN.setProgramReqd(newValue);

                    paymentsAssignedList = paymentSERVICE.retrievePaymentHeadsAmount(newValue);
                    addPaymentHeadsIntoListView(paymentsAssignedList);

                }

            }
        });
        btnSave.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Test All paymentHeadsIdList" + masterPaymentHeadsList.toString());
                int result_insert;
                int result_delete;
                if (validatePaymentHeadsIdList()) {
                    String programReqd = cmbProgramReqd.getSelectionModel().getSelectedItem();
                    result_delete = paymentSERVICE.deletePaymentHeadsAmount(programReqd);
                    result_insert = paymentSERVICE.insertPaymentHeadAmount(masterPaymentHeadsList, programReqd);
                    if (result_insert > 0) {
                        masterPaymentHeadsList.clear();
                        paymentsAssignedList.clear();
                        Notification noticication = Notifications.SUCCESS;
                        TrayNotification trayNotification = new TrayNotification("Amount Assigned", "Payment Amount assigned successfully", noticication);
                        trayNotification.showAndDismiss(Duration.seconds(5), btnSave);

                    } else {
                        Notification noticication = Notifications.ERROR;
                        TrayNotification trayNotification = new TrayNotification("Amount Not Assigned", "Payment Amount not assigned successfully", noticication);
                        trayNotification.showAndDismiss(Duration.seconds(5), btnSave);

                    }

                }
            }

        });
        btnClear.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                clearAllFields();
            }
        });

    }

    /**
     *
     * @return
     */
    public boolean validatePaymentHeadsIdList() {
        boolean flag = true;
        for (PaymentBEAN paymentBEAN : masterPaymentHeadsList) {
            if (paymentBEAN.getAmount() == null || paymentBEAN.getAmount().equalsIgnoreCase("")) {
                showPopupMessages.showError("Enter amount to all selected payments", btnSave);
                flag = false;
                break;
            }
        }
        return flag;

    }

    /**
     *
     * @param paymentsAssignedList
     */
    public void addPaymentHeadsIntoListView(ObservableList<PaymentBEAN> paymentsAssignedList) {
        listViewPayAmount.getItems().clear();

        listViewPayAmount.setCellFactory(new Callback<ListView<PaymentBEAN>, ListCell<PaymentBEAN>>() {

            @Override
            public ListCell<PaymentBEAN> call(ListView<PaymentBEAN> param) {

                final ListCell<PaymentBEAN> cell = new ListCell<PaymentBEAN>() {
                    @Override
                    protected void updateItem(PaymentBEAN paymentBEAN, boolean empty) {
                        super.updateItem(paymentBEAN, empty);
                        if (paymentBEAN != null) {
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zs/ina/admin/master/payment/FXMLSingleHeadAssign.fxml"));
                                Parent root = (Parent) loader.load();
                                FXMLSingleHeadAssignController controller = (FXMLSingleHeadAssignController) loader.getController();
                                controller.passPaymentHeadDetails(paymentBEAN, masterPaymentHeadsList);
                                setGraphic(root);
                            } catch (IOException sqle) {
                                logger.error(sqle.toString());
                                sqle.printStackTrace();
                            }

                        } else {
                            setGraphic(null);
                        }
                    }
                };
                return cell;
            }

        });
        listViewPayAmount.getItems().addAll(paymentsAssignedList);

    }

    /**
     *
     */
    public void clearAllFields() {
        programList.clear();
        cmbProgramReqd.getItems().clear();
        masterPaymentHeadsList.clear();
        paymentsAssignedList.clear();
        listViewPayAmount.getItems().clear();
        cmbProgramReqd.setPromptText("Select");
        listViewPayAmount.setPlaceholder(new Label("Select program first"));
        programList.addAll("Study", "Work", "Migrate", "Training");
        cmbProgramReqd.setItems(programList);
    }

}
