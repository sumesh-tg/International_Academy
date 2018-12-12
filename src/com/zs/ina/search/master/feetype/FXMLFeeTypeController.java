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
package com.zs.ina.search.master.feetype;

import com.zs.ina.common.error.ShowPopupMessages;
import com.zs.ina.context.Context;
import com.zs.ina.search.master.feetype.dao.FeeTypeBEAN;
import com.zs.ina.search.master.feetype.dao.FeeTypeDAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
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
public class FXMLFeeTypeController implements Initializable {

    @FXML
    private HBox timingHbox;
    @FXML
    private TextField txtFeeType;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnSave;
    @FXML
    private TextField txtSearch;
    @FXML
    private TableView<FeeTypeBEAN> tblFeeType;
    @FXML
    private TableColumn<FeeTypeBEAN, FeeTypeBEAN> colJobTitle;
    @FXML
    private TableColumn<FeeTypeBEAN, FeeTypeBEAN> colId;
    ApplicationContext springAppContext = Context.getInstance().currentProfile().getSpringContext();
    FeeTypeDAO feeTypeDAO = springAppContext.getBean(FeeTypeDAO.class);
    FeeTypeBEAN feeTypeBEAN = new FeeTypeBEAN();
    ValidationSupport validationSupport = new ValidationSupport();
    ShowPopupMessages popupMessages = new ShowPopupMessages();

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadTableDataAutoCompletion();
        Bindings.bindBidirectional(txtFeeType.textProperty(), feeTypeBEAN.feeTypeProperty());

        /* ======================== Save Details ==================== */
        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                /* ======================== Test Validation  ==================== */
                ValidationResult result = validationSupport.getValidationResult();
                System.out.println("Enter Valid Data" + result.getMessages());
                System.out.println("Test Validation Valid :: " + validationSupport.isInvalid());
                if (validateFeeType()) {
                    if (feeTypeBEAN.getId() != null) {
                        int done = feeTypeDAO.updateFeeType(feeTypeBEAN);
                        if (done > 0) {
                            Notifications.create()
                                    .title("Fee Type Updated!")
                                    .text("Fee Type Updated Successfully!")
                                    .showInformation();
                        } else {
                            Notifications.create()
                                    .title("Fatal Error!")
                                    .text("Fee Type Updation Failed !")
                                    .showError();
                        }
                    } else {
                        int done = feeTypeDAO.insertFeeType(feeTypeBEAN);
                        if (done > 0) {
                            Notifications.create()
                                    .title("Fee Type Saved!")
                                    .text("Fee Type Saved Successfully!")
                                    .showInformation();
                        } else {
                            Notifications.create()
                                    .title("Fatal Error!")
                                    .text("Fee Type Saving Failed !")
                                    .showError();
                        }
                    }
                    txtFeeType.clear();
                    feeTypeBEAN = new FeeTypeBEAN();
                    loadTableDataAutoCompletion();
                    Bindings.bindBidirectional(txtFeeType.textProperty(), feeTypeBEAN.feeTypeProperty());
                    btnClear.fire();
                } else {
                    popupMessages.showError("All fields required!", btnSave);

                }
            }
        });
        /* ======================== Delete Data ==================== */
        btnDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (feeTypeBEAN.getId() != null) {
                    int done = feeTypeDAO.deleteFeeType(feeTypeBEAN.getId());
                    if (done > 0) {
                        Notifications.create()
                                .title("Fee Type Deleted!")
                                .text("Fee Type Deleted Successfully!")
                                //    .owner(btnSave)
                                .showInformation();
                    }
                    feeTypeBEAN = new FeeTypeBEAN();
                    loadTableDataAutoCompletion();
                    Bindings.bindBidirectional(txtFeeType.textProperty(), feeTypeBEAN.feeTypeProperty());
                    btnClear.fire();
                }
            }
        });
        /* ======================== Clear Form ==================== */
        btnClear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                txtFeeType.clear();
                btnSave.setText("Save");
                feeTypeBEAN = new FeeTypeBEAN();
                Bindings.bindBidirectional(txtFeeType.textProperty(), feeTypeBEAN.feeTypeProperty());
            }
        });
        /* ======================== Table Row Factory ==================== */
        tblFeeType.setRowFactory(new Callback<TableView<FeeTypeBEAN>, TableRow<FeeTypeBEAN>>() {
            @Override
            public TableRow<FeeTypeBEAN> call(TableView<FeeTypeBEAN> param) {
                final TableRow<FeeTypeBEAN> row = new TableRow<FeeTypeBEAN>() {
                    @Override
                    protected void updateItem(FeeTypeBEAN jobTitleBean, boolean empty) {
                        super.updateItem(jobTitleBean, empty);
                        if (jobTitleBean != null) {

                        }
                    }

                };
                row.setOnMouseClicked(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        if (event.getButton() == MouseButton.PRIMARY) {
                            if (row.getItem() != null) {
                                feeTypeBEAN = new FeeTypeBEAN();
                                feeTypeBEAN.idProperty().addListener(new ChangeListener<String>() {

                                    @Override
                                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                                        if (newValue != null) {
                                            btnSave.setText("Update");
                                        } else {
                                            btnSave.setText("Save");
                                        }
                                    }

                                });
                                BeanUtils.copyProperties(row.getItem(), feeTypeBEAN);
                                Bindings.bindBidirectional(txtFeeType.textProperty(), feeTypeBEAN.feeTypeProperty());

                            }

                        }
                    }

                });
                return row;
            }
        });

    }

    /**
     *
     * @return
     */
    public boolean validateFeeType() {
        boolean flag = false;
        if (feeTypeBEAN.getFeeType() != null && !feeTypeBEAN.getFeeType().equalsIgnoreCase("")) {
            flag = true;
        }
        return flag;
    }

    /**
     *
     */
    public void loadTableDataAutoCompletion() {
        ObservableList<FeeTypeBEAN> listFeeTypes = feeTypeDAO.retrieveFeeType();
        colId.setCellValueFactory(
                new PropertyValueFactory<>("id"));
        colJobTitle.setCellValueFactory(
                new PropertyValueFactory<>("feeType"));
        FilteredList<FeeTypeBEAN> filteredData = new FilteredList<FeeTypeBEAN>(listFeeTypes, feeType -> true);
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(feeTypess -> {
                // If filter text is empty, display all events.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Compare all records with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                if (feeTypess.getFeeType() == null) {
                    return false;
                }
                if (feeTypess.getFeeType().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else {
                    return false;
                } // Does not match.

            });
        });
        SortedList<FeeTypeBEAN> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tblFeeType.comparatorProperty());
        tblFeeType.setItems(sortedData);
        //   tblJobTitle.setItems(listJobTitles);
        /* ======================== Auto Completion For Fee Types ==================== */

        timingHbox.getChildren().remove(txtFeeType);
        txtFeeType = TextFields.createClearableTextField();
        timingHbox.getChildren().add(txtFeeType);
        AutoCompletionBinding<FeeTypeBEAN> autoCompletionBindingNumber = TextFields.bindAutoCompletion(txtFeeType, listFeeTypes);
        autoCompletionBindingNumber.setOnAutoCompleted(new EventHandler<AutoCompletionBinding.AutoCompletionEvent<FeeTypeBEAN>>() {

            @Override
            public void handle(AutoCompletionBinding.AutoCompletionEvent<FeeTypeBEAN> event) {
                BeanUtils.copyProperties(event.getCompletion(), feeTypeBEAN);
                feeTypeBEAN.idProperty().addListener(new ChangeListener<String>() {

                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                        if (newValue != null) {
                            btnSave.setText("Update");
                        } else {
                            btnSave.setText("Save");
                        }
                    }

                });
                Bindings.bindBidirectional(txtFeeType.textProperty(), feeTypeBEAN.feeTypeProperty());
                System.out.println("Test Values :: " + feeTypeBEAN.getId() + feeTypeBEAN.getFeeType());
            }
        });
        /* ========================  Validation Support ==================== */

        validationSupport.registerValidator(txtFeeType, Validator.createEmptyValidator("Fee Type is required"));
        validationSupport.validationResultProperty().addListener(new ChangeListener<ValidationResult>() {
            @Override
            public void changed(ObservableValue<? extends ValidationResult> observable, ValidationResult oldValue, ValidationResult newValue) {
                System.out.println("Validate Listner :: " + newValue.getMessages());
            }
        });
    }

}
