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
package com.zs.ina.accounts.master.paymentterms;

import com.zs.ina.accounts.master.dao.PaymentTermsBEAN;
import com.zs.ina.accounts.master.dao.PaymentTermsDAO;
import com.zs.ina.common.UiiDGenerator;
import com.zs.ina.common.error.ShowPopupMessages;
import com.zs.ina.context.Context;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import org.controlsfx.control.Notifications;
import org.controlsfx.validation.ValidationMessage;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;

/**
 * FXML Controller class
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class FXMLPaymentTermsController implements Initializable {

    @FXML
    private TextField txtShowItAs;
    @FXML
    private TextField txtNoDays;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnSave;
    @FXML
    private TextField txtSearch;
    @FXML
    private TableView<PaymentTermsBEAN> tbllTerms;
    @FXML
    private TableColumn<PaymentTermsBEAN, String> colShowItAs;
    @FXML
    private TableColumn<PaymentTermsBEAN, String> colNoDays;
    /* ======================== Variebles ==================== */
    ValidationSupport validationSupport = new ValidationSupport();
    ShowPopupMessages popupMessages = new ShowPopupMessages();
    ApplicationContext springAppContext = Context.getInstance().currentProfile().getSpringContext();
    /* ======================== Beans ==================== */
    PaymentTermsBEAN paymentTermsBEAN = new PaymentTermsBEAN();
    PaymentTermsDAO paymentTermsDAO = springAppContext.getBean(PaymentTermsDAO.class);
    /* ======================== Master Data For Search ==================== */
    ObservableList<PaymentTermsBEAN> masterPaymentTerms = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadTableData();
        loadButtonActions();
        bindWithBean();
        validationSupport();
    }

    public void validationSupport() {
        validationSupport.registerValidator(txtShowItAs, Validator.createEmptyValidator("Enter value"));
        validationSupport.registerValidator(txtNoDays, Validator.createEmptyValidator("Enter number of days"));
    }

    public void bindWithBean() {
        Bindings.bindBidirectional(txtNoDays.textProperty(), paymentTermsBEAN.numberOfDaysProperty());
        Bindings.bindBidirectional(txtShowItAs.textProperty(), paymentTermsBEAN.showItAsProperty());
    }

    public void loadButtonActions() {
        btnDelete.disableProperty().bind(Bindings.equal(tbllTerms.getSelectionModel().selectedIndexProperty(), -1));
        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ValidationResult result = validationSupport.getValidationResult();
                for (ValidationMessage msg : result.getMessages()) {
                    popupMessages.showError(msg.getText(), msg.getTarget());
                    break;
                }
                if (!validationSupport.isInvalid()) {
                    if (paymentTermsBEAN.getTermsId() == null || paymentTermsBEAN.getTermsId().equalsIgnoreCase("")) {
                        if (!paymentTermsDAO.checkExistsPaymentTerms(paymentTermsBEAN, null)) {
                            paymentTermsBEAN.setTermsId("pt_" + UiiDGenerator.getUIID8());
                            boolean done = paymentTermsDAO.insertPaymentTerms(paymentTermsBEAN);
                            if (done) {
                                Notifications.create()
                                        .title("Payment term has been created successfully!")
                                        .text("Payment term  has been created successfully!")
                                        .showInformation();
                                btnClear.fire();
                            } else {
                                Notifications.create()
                                        .title("Payment term creation failed!")
                                        .text("Payment term creation failed!")
                                        .showError();
                                paymentTermsBEAN.setTermsId(null);
                            }
                        } else {
                            Notifications.create()
                                    .title("Payment term or number of days exists !")
                                    .text("Payment term or number of days exists !")
                                    .showWarning();
                        }
                    } else {
                        if (!paymentTermsDAO.checkExistsPaymentTerms(paymentTermsBEAN, paymentTermsBEAN.getTermsId())) {
                            boolean done = paymentTermsDAO.updatePaymentTerms(paymentTermsBEAN);
                            if (done) {
                                Notifications.create()
                                        .title("Payment term has been updated successfully!")
                                        .text("Payment term  has been updated successfully!")
                                        .showInformation();
                                btnClear.fire();
                            } else {
                                Notifications.create()
                                        .title("Payment term updation failed!")
                                        .text("Payment term updation failed!")
                                        .showError();
                            }
                        } else {
                            Notifications.create()
                                    .title("Payment term or number of days exists !")
                                    .text("Payment term or number of days exists !")
                                    .showWarning();
                        }
                    }
                }
            }
        });
        btnDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (paymentTermsBEAN.getTermsId() != null || !paymentTermsBEAN.getTermsId().equalsIgnoreCase("")) {
                    boolean done = paymentTermsDAO.deletePaymentTerms(paymentTermsBEAN.getTermsId());
                    if (done) {
                        Notifications.create()
                                .title("Payment term deleted successfully!")
                                .text("Payment term deleted successfully!")
                                .showInformation();
                        btnClear.fire();
                    } else {
                        Notifications.create()
                                .title("Payment term deletion failed!")
                                .text("Payment term deletion failed!")
                                .showError();
                    }
                }
            }
        });
        btnClear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                paymentTermsBEAN = new PaymentTermsBEAN();
                bindWithBean();
                txtSearch.clear();
                tbllTerms.getItems().clear();
                tbllTerms.getSelectionModel().clearSelection();
                loadTableData();
            }
        });
    }

    public void loadTableData() {
        ObservableList<PaymentTermsBEAN> listPaymentTerms = paymentTermsDAO.retrievePaymentTerms();
        colShowItAs.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PaymentTermsBEAN, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<PaymentTermsBEAN, String> param) {
                return param.getValue().showItAsProperty();
            }
        });
        colNoDays.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PaymentTermsBEAN, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<PaymentTermsBEAN, String> param) {
                return param.getValue().numberOfDaysProperty();
            }
        });
        tbllTerms.getItems().addAll(listPaymentTerms);
        /* ======================== Set RowFactory ==================== */
        masterPaymentTerms.removeAll(masterPaymentTerms);
        masterPaymentTerms.addAll(listPaymentTerms);
        /* ======================== Sort Details ==================== */
        FilteredList<PaymentTermsBEAN> filteredData = new FilteredList<>(masterPaymentTerms, p -> true);
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(enquiry -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (enquiry.getShowItAs().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches contact name.
                }
                return false; // Does not match.
            });

            SortedList<PaymentTermsBEAN> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(tbllTerms.comparatorProperty());
            tbllTerms.setItems(sortedData);
        });
        /* ======================== Set Row Factory ==================== */
        tbllTerms.setRowFactory(new Callback<TableView<PaymentTermsBEAN>, TableRow<PaymentTermsBEAN>>() {
            @Override
            public TableRow<PaymentTermsBEAN> call(TableView<PaymentTermsBEAN> param) {
                final TableRow<PaymentTermsBEAN> row = new TableRow<PaymentTermsBEAN>() {
                    @Override
                    protected void updateItem(PaymentTermsBEAN tbean, boolean empty) {
                        super.updateItem(tbean, empty);
                        if (!empty) {

                        }
                    }
                };
                row.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (event.getButton().equals(MouseButton.PRIMARY)) {
                            if (event.getClickCount() == 2) {

                            }
                            /* ======================== On SIngle Click Load Data to form ==================== */
                            if (event.getClickCount() == 1) {
                                BeanUtils.copyProperties(row.getItem(), paymentTermsBEAN);
                                bindWithBean();
                            }

                        }
                    }
                });
                return row;
            }
        });
    }
}
