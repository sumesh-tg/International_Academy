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
package com.zs.ina.accounts.master.items;

import com.zs.ina.accounts.invoice.dao.InvoiceItemsBEAN;
import com.zs.ina.accounts.invoice.dao.InvoiceItemsDAO;
import com.zs.ina.accounts.master.dao.InvoiceItemsMasterDAO;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
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
public class FXMLInvoiceItemsController implements Initializable {

    @FXML
    private TextField txtItemName;
    @FXML
    private TextField txtUnit;
    @FXML
    private TextField txtIdentificationCode;
    @FXML
    private TextField txtRate;
    @FXML
    private TextArea txtDescription;
    @FXML
    private ComboBox<String> cmbTax;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnSave;
    @FXML
    private TableView<InvoiceItemsBEAN> tblInvoiceItems;
    @FXML
    private TableColumn<InvoiceItemsBEAN, String> colName;
    @FXML
    private TableColumn<InvoiceItemsBEAN, String> colUnit;
    @FXML
    private TableColumn<InvoiceItemsBEAN, String> colIdentificationCode;
    @FXML
    private TableColumn<InvoiceItemsBEAN, String> colRate;
    @FXML
    private TableColumn<InvoiceItemsBEAN, String> colDescription;
    @FXML
    private TableColumn<InvoiceItemsBEAN, String> colTax;
    @FXML
    private TextField txtSearch;
    /* ======================== Variebles ==================== */
    ValidationSupport validationSupport = new ValidationSupport();
    InvoiceItemsBEAN invoiceItemsBEAN = new InvoiceItemsBEAN();
    ShowPopupMessages popupMessages = new ShowPopupMessages();
    ApplicationContext springAppContext = Context.getInstance().currentProfile().getSpringContext();
    /* ======================== Beans from spring context ==================== */
    InvoiceItemsDAO invoiceItemsDAO = springAppContext.getBean(InvoiceItemsDAO.class);
    InvoiceItemsMasterDAO invoiceItemsMasterDAO = springAppContext.getBean(InvoiceItemsMasterDAO.class);
    /* ======================== Search List ==================== */
    ObservableList<InvoiceItemsBEAN> masterDataInvoiceItems = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bindAllControls();
        buttonActions();
        loadInvoiceItemsTableData();
        validationSupport();
        /* ======================== Disable Button ==================== */
        btnDelete.disableProperty().bind(Bindings.equal(tblInvoiceItems.getSelectionModel().selectedIndexProperty(), -1));

    }

    public void loadInvoiceItemsTableData() {
        txtSearch.clear();

        tblInvoiceItems.getItems().clear();

        ObservableList<InvoiceItemsBEAN> listInvoiceItems = invoiceItemsMasterDAO.retrieveInvoiceMasterItems();
        colName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<InvoiceItemsBEAN, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<InvoiceItemsBEAN, String> param) {
                return param.getValue().itemNameProperty();
            }
        });
        colDescription.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<InvoiceItemsBEAN, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<InvoiceItemsBEAN, String> param) {
                return param.getValue().descriptionProperty();
            }
        });
        colIdentificationCode.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<InvoiceItemsBEAN, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<InvoiceItemsBEAN, String> param) {
                return param.getValue().identificationCodeProperty();
            }
        });
        colRate.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<InvoiceItemsBEAN, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<InvoiceItemsBEAN, String> param) {
                return param.getValue().rateProperty();
            }
        });
        colTax.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<InvoiceItemsBEAN, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<InvoiceItemsBEAN, String> param) {
                return param.getValue().taxIdProperty();
            }
        });
        colUnit.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<InvoiceItemsBEAN, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<InvoiceItemsBEAN, String> param) {
                return param.getValue().unitProperty();
            }
        });
        tblInvoiceItems.getItems().addAll(listInvoiceItems);
        /* ======================== Add.Remove Master Data ==================== */
        masterDataInvoiceItems.removeAll(masterDataInvoiceItems);
        masterDataInvoiceItems.addAll(listInvoiceItems);
        /* ======================== Apply Sorting To Table ==================== */
        FilteredList<InvoiceItemsBEAN> filteredData = new FilteredList<>(masterDataInvoiceItems, p -> true);
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(enquiry -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                // NoticePOJO noticePOJO=new NoticePOJO();
                if (enquiry.getItemName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches contact name.
                }
//                else if (enquiry.getAddress().toLowerCase().indexOf(lowerCaseFilter) != -1) {
//                    return true; // Filter phone.
//                }
                return false; // Does not match.
            });

            SortedList<InvoiceItemsBEAN> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(tblInvoiceItems.comparatorProperty());
            tblInvoiceItems.setItems(sortedData);

        });
        /* ======================== Set Row Factory ==================== */
        tblInvoiceItems.setRowFactory(new Callback<TableView<InvoiceItemsBEAN>, TableRow<InvoiceItemsBEAN>>() {
            @Override
            public TableRow<InvoiceItemsBEAN> call(TableView<InvoiceItemsBEAN> param) {
                final TableRow<InvoiceItemsBEAN> row = new TableRow<InvoiceItemsBEAN>() {
                    @Override
                    protected void updateItem(InvoiceItemsBEAN tbean, boolean empty) {
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
//                                try {
//                                } catch (IOException ex) {
//                                    ex.printStackTrace();
////                                    logger.error(ex.getMessage());
//                                }

                            }
                            /* ======================== On SIngle Click Load Data to form ==================== */
                            if (event.getClickCount() == 1) {
                                BeanUtils.copyProperties(row.getItem(), invoiceItemsBEAN);
                                bindAllControls();
                            }

                        }
                    }
                });
                return row;
            }
        });
    }

    public void buttonActions() {
        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ValidationResult result = validationSupport.getValidationResult();
                for (ValidationMessage msg : result.getMessages()) {
                    popupMessages.showError(msg.getText(), msg.getTarget());
                    break;
                }
                if (!validationSupport.isInvalid()) {
                    if (invoiceItemsBEAN.getItemId() == null || invoiceItemsBEAN.getItemId().equalsIgnoreCase("")) {
                        String itemId = "it_" + UiiDGenerator.getUIID8();
                        invoiceItemsBEAN.setItemId(itemId);
                        boolean done = invoiceItemsMasterDAO.insertInvoiceMasterItems(invoiceItemsBEAN);
                        if (done) {
                            Notifications.create()
                                    .title("New item has been created successfully!")
                                    .text("New item has been created successfully!")
                                    .showInformation();
                            invoiceItemsBEAN = new InvoiceItemsBEAN();
                            bindAllControls();
                            loadInvoiceItemsTableData();
                        } else {
                            Notifications.create()
                                    .title("Item creation failed!")
                                    .text("Item creation failed!")
                                    .showError();
                            invoiceItemsBEAN.setItemId(null);
                        }
                    } else {
                        boolean done = invoiceItemsMasterDAO.updateInvoiceMasterItems(invoiceItemsBEAN);
                        if (done) {
                            Notifications.create()
                                    .title("Updated successfully!")
                                    .text("The item has been updated successfully!")
                                    .showInformation();
                            invoiceItemsBEAN = new InvoiceItemsBEAN();
                            bindAllControls();
                            loadInvoiceItemsTableData();
                        } else {
                            Notifications.create()
                                    .title("Updation failed!")
                                    .text("Item Updation failed!")
                                    .showError();
                            invoiceItemsBEAN.setItemId(null);
                        }
                    }
                }
            }
        });
        /* ======================== Clear Controls ==================== */
        btnClear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                invoiceItemsBEAN = new InvoiceItemsBEAN();
                bindAllControls();
            }
        });
        /* ======================== Delete Button Actions ==================== */
        btnDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Deleteion required here!");
                if (invoiceItemsBEAN.getItemId() == null || invoiceItemsBEAN.getItemId().equalsIgnoreCase("")) {

                } else {
                    boolean done = invoiceItemsMasterDAO.deleteInvoiceMasterItems(invoiceItemsBEAN.getItemId());
                    if (done) {
                        invoiceItemsBEAN = new InvoiceItemsBEAN();
                        bindAllControls();
                        loadInvoiceItemsTableData();

                    }
                }
            }
        });
    }

    public void bindAllControls() {
        Bindings.bindBidirectional(txtItemName.textProperty(), invoiceItemsBEAN.itemNameProperty());
        Bindings.bindBidirectional(txtDescription.textProperty(), invoiceItemsBEAN.descriptionProperty());
        Bindings.bindBidirectional(txtIdentificationCode.textProperty(), invoiceItemsBEAN.identificationCodeProperty());
        Bindings.bindBidirectional(txtRate.textProperty(), invoiceItemsBEAN.rateProperty());
        Bindings.bindBidirectional(txtUnit.textProperty(), invoiceItemsBEAN.unitProperty());
        Bindings.bindBidirectional(cmbTax.valueProperty(), invoiceItemsBEAN.taxPercentageProperty());
    }

    public void validationSupport() {
        validationSupport.registerValidator(txtItemName, Validator.createEmptyValidator("Item name required"));
        validationSupport.registerValidator(txtDescription, Validator.createEmptyValidator("Item description required"));
        validationSupport.registerValidator(txtIdentificationCode, Validator.createEmptyValidator("Identification code required"));
        validationSupport.registerValidator(txtRate, Validator.createEmptyValidator("Rate required"));
        validationSupport.registerValidator(txtUnit, Validator.createEmptyValidator("Unit required"));
    }
}
