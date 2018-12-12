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
package com.zs.ina.search.master.subclass;

import com.zs.ina.common.error.ShowPopupMessages;
import com.zs.ina.context.Context;
import com.zs.ina.search.master.subclass.dao.SubClassBEAN;
import com.zs.ina.search.master.subclass.dao.SubClassDAO;
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
public class FXMLSubClassController implements Initializable {

    @FXML
    private HBox hboxSubClass;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnSave;
    @FXML
    private TextField searchTxt;
    @FXML
    private TableView<SubClassBEAN> tblSubClass;
    @FXML
    private TableColumn<SubClassBEAN, SubClassBEAN> colSubClass;
    @FXML
    private TableColumn<SubClassBEAN, SubClassBEAN> colId;
    ApplicationContext springAppContext = Context.getInstance().currentProfile().getSpringContext();
    SubClassDAO subClassDAO = springAppContext.getBean(SubClassDAO.class);
    SubClassBEAN subClassBEAN = new SubClassBEAN();
    @FXML
    private TextField txtSubClass;
    ValidationSupport validationSupport = new ValidationSupport();
    ShowPopupMessages popupMessages = new ShowPopupMessages();

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadTableData();
        Bindings.bindBidirectional(txtSubClass.textProperty(), subClassBEAN.subClassProperty());
        btnDelete.disableProperty().bind(Bindings.equal(tblSubClass.getSelectionModel().selectedIndexProperty(), -1));

        /* ======================== Save Details ==================== */
        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (validateSubClass()) {
                    if (subClassBEAN.getId() != null) {
                        int done = subClassDAO.updateSubClass(subClassBEAN);
                        if (done > 0) {
                            Notifications.create()
                                    .title("Sub Class Updated!")
                                    .text("Sub Class Updated Successfully!")
                                    .showInformation();
                        } else {
                            Notifications.create()
                                    .title("Fatal Error!")
                                    .text("Sub Class Updation Failed !")
                                    .showError();
                        }
                    } else {
                        int done = subClassDAO.insertSubClass(subClassBEAN);
                        if (done > 0) {
                            Notifications.create()
                                    .title("Sub Class Saved!")
                                    .text("Sub Class Saved Successfully!")
                                    .showInformation();
                        } else {
                            Notifications.create()
                                    .title("Fatal Error!")
                                    .text("Sub Class Saving Failed !")
                                    .showError();
                        }
                    }
                    txtSubClass.clear();
                    subClassBEAN = new SubClassBEAN();
                    loadTableData();
                    Bindings.bindBidirectional(txtSubClass.textProperty(), subClassBEAN.subClassProperty());
                } else {
                    popupMessages.showError("All fields required!", btnSave);
                }
            }
        });
        btnDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (subClassBEAN.getId() != null) {
                    int done = subClassDAO.deleteSubClass(subClassBEAN.getId());
                    if (done > 0) {
                        Notifications.create()
                                .title("Sub Class Deleted!")
                                .text("Sub Class Deleted Successfully!")
                                //    .owner(btnSave)
                                .showInformation();
                    }
                    subClassBEAN = new SubClassBEAN();
                    loadTableData();
                    Bindings.bindBidirectional(txtSubClass.textProperty(), subClassBEAN.subClassProperty());

                }
            }
        });
        btnClear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                txtSubClass.clear();
                btnSave.setText("Save");
                subClassBEAN = new SubClassBEAN();
                Bindings.bindBidirectional(txtSubClass.textProperty(), subClassBEAN.subClassProperty());
            }
        });
        /* ======================== Table Row Factory ==================== */
        tblSubClass.setRowFactory(new Callback<TableView<SubClassBEAN>, TableRow<SubClassBEAN>>() {
            @Override
            public TableRow<SubClassBEAN> call(TableView<SubClassBEAN> param) {
                final TableRow<SubClassBEAN> row = new TableRow<SubClassBEAN>() {
                    @Override
                    protected void updateItem(SubClassBEAN subClassBEAN, boolean empty) {
                        super.updateItem(subClassBEAN, empty);
                        if (subClassBEAN != null) {

                        }
                    }

                };
                row.setOnMouseClicked(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        if (event.getButton() == MouseButton.PRIMARY) {
                            if (row.getItem() != null) {
                                subClassBEAN = new SubClassBEAN();
                                subClassBEAN.idProperty().addListener(new ChangeListener<String>() {

                                    @Override
                                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                                        if (newValue != null) {
                                            btnSave.setText("Update");
                                        } else {
                                            btnSave.setText("Save");
                                        }
                                    }

                                });
                                BeanUtils.copyProperties(row.getItem(), subClassBEAN);
                                Bindings.bindBidirectional(txtSubClass.textProperty(), subClassBEAN.subClassProperty());

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
    public boolean validateSubClass() {
        boolean flag = false;
        if (subClassBEAN.getSubClass() != null && !subClassBEAN.getSubClass().equalsIgnoreCase("")) {
            flag = true;
        }
        return flag;
    }

    /**
     *
     */
    public void loadTableData() {
        ObservableList<SubClassBEAN> listSubClasses = subClassDAO.retrieveSubClass();
        colId.setCellValueFactory(
                new PropertyValueFactory<>("id"));
        colSubClass.setCellValueFactory(
                new PropertyValueFactory<>("subClass"));
        FilteredList<SubClassBEAN> filteredData = new FilteredList<SubClassBEAN>(listSubClasses, subClasses -> true);
        searchTxt.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(subClass -> {
                // If filter text is empty, display all events.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Compare all records with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (subClass.getSubClass().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else {
                    return false;
                } // Does not match.
            });
        });
        SortedList<SubClassBEAN> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tblSubClass.comparatorProperty());
        tblSubClass.setItems(sortedData);
        //   tblJobTitle.setItems(listJobTitles);
        /* ======================== Auto Completion For Sub Classs ==================== */

        hboxSubClass.getChildren().remove(txtSubClass);
        txtSubClass = TextFields.createClearableTextField();
        hboxSubClass.getChildren().add(txtSubClass);
        if (listSubClasses.size() > 0) {
            AutoCompletionBinding<SubClassBEAN> autoCompletionBindingNumber = TextFields.bindAutoCompletion(txtSubClass, listSubClasses);
            autoCompletionBindingNumber.setOnAutoCompleted(new EventHandler<AutoCompletionBinding.AutoCompletionEvent<SubClassBEAN>>() {

                @Override
                public void handle(AutoCompletionBinding.AutoCompletionEvent<SubClassBEAN> event) {
                    BeanUtils.copyProperties(event.getCompletion(), subClassBEAN);
                    subClassBEAN.idProperty().addListener(new ChangeListener<String>() {

                        @Override
                        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                            if (newValue != null) {
                                btnSave.setText("Update");
                            } else {
                                btnSave.setText("Save");
                            }
                        }

                    });
                    Bindings.bindBidirectional(txtSubClass.textProperty(), subClassBEAN.subClassProperty());
                    System.out.println("Test Values :: " + subClassBEAN.getId() + subClassBEAN.getSubClass());
                }
            });
        }
        /* ========================  Validation Support ==================== */

        validationSupport.registerValidator(txtSubClass, Validator.createEmptyValidator("Sub Class is required"));
        validationSupport.validationResultProperty().addListener(new ChangeListener<ValidationResult>() {
            @Override
            public void changed(ObservableValue<? extends ValidationResult> observable, ValidationResult oldValue, ValidationResult newValue) {
                System.out.println("Validate Listner :: " + newValue.getMessages());
            }
        });
    }
}
