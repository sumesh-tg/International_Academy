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
import com.zs.ina.admin.master.payment.dao.MandatoryDAO;
import com.zs.ina.admin.master.payment.dao.PaymentBEAN;
import com.zs.ina.admin.master.payment.dao.PaymentDAO;
import com.zs.ina.admin.master.payment.dao.PaymentIMPL;
import com.zs.ina.admin.master.retrieve.EnquirySatusPOJO;
import com.zs.ina.admin.master.retrieve.RetrieveStaticMasterDAO;
import com.zs.ina.common.error.ShowPopupMessages;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleBooleanProperty;
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
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class FXMLMakeMandatoryController implements Initializable {

    @FXML
    private ComboBox<EnquirySatusPOJO> cmbEnquiryStatus;
    @FXML
    private ListView<MandatoryBEAN> listViewMandatoryHeads;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnClear;
    ObservableList<EnquirySatusPOJO> obsEnquiryStatus = FXCollections.observableArrayList();
    PaymentDAO paymentDAO = new PaymentIMPL();
    MandatoryDAO mandatoryDAO = new PaymentIMPL();
    ObservableList<MandatoryBEAN> masterMandatoryPaymentHead = FXCollections.observableArrayList();
    String CURR_APP_STATUS = null;
    ShowPopupMessages popupMessages = new ShowPopupMessages();
    SimpleBooleanProperty clearProperty = new SimpleBooleanProperty();

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /* ======================== Load Enquiry Status Master Data ==================== */
        obsEnquiryStatus = RetrieveStaticMasterDAO.retrieveEnquiryAppStatus();
        cmbEnquiryStatus.setItems(obsEnquiryStatus);
        /* ======================== Load Payment Heads Data ==================== */
        listViewMandatoryHeads.setPlaceholder(new Label("Choose Enquiry Status ..."));
        cmbEnquiryStatus.valueProperty().addListener(new ChangeListener<EnquirySatusPOJO>() {
            @Override
            public void changed(ObservableValue<? extends EnquirySatusPOJO> observable, EnquirySatusPOJO oldValue, EnquirySatusPOJO newValue) {
                if (newValue != null) {
                    CURR_APP_STATUS = newValue.getId();
                    ObservableList<MandatoryBEAN> listMandatory = mandatoryDAO.getMandatoryHeadsByStatus(CURR_APP_STATUS);
                    listViewMandatoryHeads.getItems().clear();
                    masterMandatoryPaymentHead.clear();
                    listViewMandatoryHeads.setCellFactory(new Callback<ListView<MandatoryBEAN>, ListCell<MandatoryBEAN>>() {
                        @Override
                        public ListCell<MandatoryBEAN> call(ListView<MandatoryBEAN> param) {

                            final ListCell<MandatoryBEAN> cell = new ListCell<MandatoryBEAN>() {
                                @Override
                                protected void updateItem(MandatoryBEAN mandatoryBEAN, boolean empty) {
                                    super.updateItem(mandatoryBEAN, empty);
                                    if (mandatoryBEAN != null) {
                                        try {
                                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zs/ina/admin/master/payment/FXMLSingleMandatoryHead.fxml"));
                                            Parent root = (Parent) loader.load();
                                            FXMLSingleMandatoryHeadController controller = (FXMLSingleMandatoryHeadController) loader.getController();
                                            controller.loadPaymentHeads(mandatoryBEAN, masterMandatoryPaymentHead, CURR_APP_STATUS);
                                            setGraphic(root);
                                        } catch (IOException sqle) {
//                                        logger.error(sqle.toString());
                                            sqle.printStackTrace();
                                        }

                                    } else {
                                        setGraphic(null);
                                        setText(null);
                                    }
                                }
                            };
                            return cell;
                        }
                    });

                    listViewMandatoryHeads.getItems().addAll(listMandatory);
                    System.out.println("Test Master List Cell Factory :: " + masterMandatoryPaymentHead.size());
                }
            }
        });
        /* ======================== Save , Update , Delete Actions  ==================== */
        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (CURR_APP_STATUS != null) {
                    mandatoryDAO.deleteMandatoryHeads(CURR_APP_STATUS);
                    System.out.println("Test Master List Save :: " + masterMandatoryPaymentHead.toString());
                    if (mandatoryDAO.insertMandatoryHeads(masterMandatoryPaymentHead) > 0) {
                        popupMessages.showSuccess("Saved Successfully !", "Saved Successfully !", cmbEnquiryStatus);
                    } else {
                        popupMessages.showError("Save Failed ! Try again !", cmbEnquiryStatus);
                    }
                }
            }
        });
        btnClear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cmbEnquiryStatus.getSelectionModel().clearSelection();
                listViewMandatoryHeads.getItems().clear();
            }
        });
        /* ======================== Disable Clear Button ==================== */
        btnClear.disableProperty().bind(Bindings.equal(cmbEnquiryStatus.getSelectionModel().selectedIndexProperty(), -1));

    }

}
