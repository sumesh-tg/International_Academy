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
package com.zs.ina.accounts.master.tax;

import com.zs.ina.accounts.master.dao.MasterTaxBEAN;
import com.zs.ina.accounts.master.dao.MasterTaxDAO;
import com.zs.ina.common.UiiDGenerator;
import com.zs.ina.common.error.ShowPopupMessages;
import com.zs.ina.context.Context;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
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
import javafx.scene.control.CheckBox;
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
public class FXMLMasterTaxController implements Initializable {

    @FXML
    private TextField txtTaxName;
    @FXML
    private TextField txtRate;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnSave;
    @FXML
    private CheckBox chkCompoundTax;
    @FXML
    private TextField txtSearch;
    @FXML
    private TableView<MasterTaxBEAN> tbllTaxes;
    @FXML
    private TableColumn<MasterTaxBEAN, String> colName;
    @FXML
    private TableColumn<MasterTaxBEAN, String> colIsCompound;
    @FXML
    private TableColumn<MasterTaxBEAN, String> colRate;
    /* ======================== Variebles ==================== */
    ValidationSupport validationSupport = new ValidationSupport();
    ShowPopupMessages popupMessages = new ShowPopupMessages();
    ApplicationContext springAppContext = Context.getInstance().currentProfile().getSpringContext();
    /* ======================== Beans ==================== */
    MasterTaxBEAN masterTaxBEAN = new MasterTaxBEAN();
    MasterTaxDAO masterTaxDAO = springAppContext.getBean(MasterTaxDAO.class);
    /* ======================== Master Data For Search ==================== */
    ObservableList<MasterTaxBEAN> masterDataTaxes = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bindWithBeans();
        loadTableData();
        buttonActions();
        validationSupport();
        validationListners();
        btnDelete.disableProperty().bind(Bindings.equal(tbllTaxes.getSelectionModel().selectedIndexProperty(), -1));
    }

    public void validationListners() {
        txtRate.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue == null || newValue.equalsIgnoreCase("")) {

                } else if (!(newValue.matches("[0-9,.]*"))) {
                    Bindings.unbindBidirectional(masterTaxBEAN.rateProperty(), txtRate.textProperty());
                    txtRate.setText(oldValue);
                    Bindings.bindBidirectional(masterTaxBEAN.rateProperty(), txtRate.textProperty());
                } else {
                    /* ======================== Parse to float ==================== */
                    try {
                        Float.parseFloat(newValue);
                        // System.out.println("Parse Value :: " + Float.parseFloat(newValue));
                        Bindings.unbindBidirectional(masterTaxBEAN.rateProperty(), txtRate.textProperty());
                        txtRate.setText(newValue);
                        Bindings.bindBidirectional(masterTaxBEAN.rateProperty(), txtRate.textProperty());

                    } catch (NumberFormatException exception) {
                        Bindings.unbindBidirectional(masterTaxBEAN.rateProperty(), txtRate.textProperty());
                        txtRate.setText(oldValue);
                        Bindings.bindBidirectional(masterTaxBEAN.rateProperty(), txtRate.textProperty());
                        //  exception.printStackTrace();
                    }
                }
            }
        });
        txtRate.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    if (txtRate.getText() == null || txtRate.getText().equalsIgnoreCase("")) {

                    } else {
                        txtRate.setText("" + Float.parseFloat(txtRate.getText()));
                    }
                }
            }
        });
    }

    public void bindWithBeans() {
        Bindings.bindBidirectional(txtRate.textProperty(), masterTaxBEAN.rateProperty());
        Bindings.bindBidirectional(txtTaxName.textProperty(), masterTaxBEAN.taxNameProperty());
    }

    public void validationSupport() {
        validationSupport.registerValidator(txtTaxName, Validator.createEmptyValidator("Tax name required"));
        validationSupport.registerValidator(txtRate, Validator.createEmptyValidator("Tax rate required"));
    }

    public void buttonActions() {
        chkCompoundTax.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    masterTaxBEAN.setIsCompoundTax("1");
                } else {
                    masterTaxBEAN.setIsCompoundTax("0");
                }
            }
        });
        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("test check box selection :: " + chkCompoundTax.selectedProperty().get());
                if (chkCompoundTax.selectedProperty().get()) {
                    masterTaxBEAN.setIsCompoundTax("1");
                } else {
                    masterTaxBEAN.setIsCompoundTax("0");
                }
                ValidationResult result = validationSupport.getValidationResult();
                for (ValidationMessage msg : result.getMessages()) {
                    popupMessages.showError(msg.getText(), msg.getTarget());
                    break;
                }
                if (!validationSupport.isInvalid()) {
                    if (masterTaxBEAN.getTaxId() == null || masterTaxBEAN.getTaxId().equalsIgnoreCase("")) {
                        String itemId = "tx_" + UiiDGenerator.getUIID8();
                        masterTaxBEAN.setTaxId(itemId);
                        boolean done = masterTaxDAO.insertTax(masterTaxBEAN);
                        if (done) {
                            Notifications.create()
                                    .title("New tax has been created successfully!")
                                    .text("New tax has been created successfully!")
                                    .showInformation();
                            btnClear.fire();
                        } else {
                            Notifications.create()
                                    .title("Tax creation failed!")
                                    .text("Tax creation failed!")
                                    .showError();
                            masterTaxBEAN.setTaxId(null);
                        }
                    } else {
                        boolean done = masterTaxDAO.updateTax(masterTaxBEAN);
                        if (done) {
                            Notifications.create()
                                    .title("New tax has been updated successfully!")
                                    .text("New tax has been updated successfully!")
                                    .showInformation();
                            btnClear.fire();
                        } else {
                            Notifications.create()
                                    .title("Tax updation failed!")
                                    .text("Tax updation failed!")
                                    .showError();
                        }
                    }

                }

            }
        });
        /* ======================== Clear Forn ==================== */
        btnClear.setOnAction(new EventHandler<ActionEvent>() {
            @Override

            public void handle(ActionEvent event) {
                loadTableData();
                masterTaxBEAN = new MasterTaxBEAN();
                bindWithBeans();
            }
        });
        /* ======================== Delete Tax ==================== */
        btnDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (masterTaxBEAN.getTaxId() != null) {
                    boolean done = masterTaxDAO.deleteTax(masterTaxBEAN.getTaxId());
                    if (done) {
                        Notifications.create()
                                .title("Deleted successfully!")
                                .text("Deleted successfully!")
                                .showInformation();
                        loadTableData();
                        masterTaxBEAN = new MasterTaxBEAN();
                        bindWithBeans();
                    } else {
                        Notifications.create()
                                .title("Deletion failed!")
                                .text("Deletion failed!")
                                .showError();
                    }
                }
            }
        });
    }

    public void loadTableData() {
        txtSearch.clear();
        tbllTaxes.getItems().clear();
        ObservableList<MasterTaxBEAN> listTaxes = masterTaxDAO.retrieveTax();
        colName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<MasterTaxBEAN, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<MasterTaxBEAN, String> param) {
                return param.getValue().taxNameProperty();
            }
        });
        colIsCompound.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<MasterTaxBEAN, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<MasterTaxBEAN, String> param) {
                if (param.getValue().getIsCompoundTax() != null) {
                    if (param.getValue().getIsCompoundTax().equalsIgnoreCase("0")) {
                        return new SimpleStringProperty("No");
                    } else {
                        return new SimpleStringProperty("Yes");
                    }
                }
                return param.getValue().isCompoundTaxProperty();
            }
        });
        colRate.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<MasterTaxBEAN, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<MasterTaxBEAN, String> param) {
                return param.getValue().rateProperty();
            }
        });
        tbllTaxes.getItems().addAll(listTaxes);
        /* ======================== Set RowFactory ==================== */
        masterDataTaxes.removeAll(masterDataTaxes);
        masterDataTaxes.addAll(listTaxes);
        /* ======================== Sort Details ==================== */
        FilteredList<MasterTaxBEAN> filteredData = new FilteredList<>(masterDataTaxes, p -> true);
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(enquiry -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (enquiry.getTaxName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches contact name.
                }
                return false; // Does not match.
            });

            SortedList<MasterTaxBEAN> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(tbllTaxes.comparatorProperty());
            tbllTaxes.setItems(sortedData);
        });
        /* ======================== Set Row Factory ==================== */
        tbllTaxes.setRowFactory(new Callback<TableView<MasterTaxBEAN>, TableRow<MasterTaxBEAN>>() {
            @Override
            public TableRow<MasterTaxBEAN> call(TableView<MasterTaxBEAN> param) {
                final TableRow<MasterTaxBEAN> row = new TableRow<MasterTaxBEAN>() {
                    @Override
                    protected void updateItem(MasterTaxBEAN tbean, boolean empty) {
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
                                BeanUtils.copyProperties(row.getItem(), masterTaxBEAN);
                                bindWithBeans();
                            }

                        }
                    }
                });
                return row;
            }
        });
    }
}
