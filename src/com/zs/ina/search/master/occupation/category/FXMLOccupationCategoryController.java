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
package com.zs.ina.search.master.occupation.category;

import com.zs.ina.common.error.ShowPopupMessages;
import com.zs.ina.context.Context;
import com.zs.ina.search.master.occupation.category.dao.OccupationCategoryBEAN;
import com.zs.ina.search.master.occupation.category.dao.OccupationCategoryDAO;
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
public class FXMLOccupationCategoryController implements Initializable {

    @FXML
    private HBox hboxOccCat;
    @FXML
    private TextField txtOccCat;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnSave;
    @FXML
    private TextField searchTxt;
    @FXML
    private TableView<OccupationCategoryBEAN> tblOccCat;
    @FXML
    private TableColumn<OccupationCategoryBEAN, OccupationCategoryBEAN> colOccCat;
    @FXML
    private TableColumn<OccupationCategoryBEAN, OccupationCategoryBEAN> colId;
    ApplicationContext springAppContext = Context.getInstance().currentProfile().getSpringContext();
    OccupationCategoryDAO occupationCategoryDAO = springAppContext.getBean(OccupationCategoryDAO.class);
    OccupationCategoryBEAN occupationCategoryBEAN = new OccupationCategoryBEAN();
    ShowPopupMessages popupMessages = new ShowPopupMessages();
    ValidationSupport validationSupport = new ValidationSupport();

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadTableData();
        Bindings.bindBidirectional(txtOccCat.textProperty(), occupationCategoryBEAN.OccupationCatProperty());
        btnDelete.disableProperty().bind(Bindings.equal(tblOccCat.getSelectionModel().selectedIndexProperty(), -1));

        /* ======================== Save Details ==================== */
        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (validateJobTitle()) {
                    if (occupationCategoryBEAN.getId() != null) {
                        int done = occupationCategoryDAO.updateOccupationCategory(occupationCategoryBEAN);
                        if (done > 0) {
                            Notifications.create()
                                    .title("Occupation Category Updated!")
                                    .text("Occupation Category Updated Successfully!")
                                    .showInformation();
                        } else {
                            Notifications.create()
                                    .title("Fatal Error!")
                                    .text("Occupation Category Updation Failed !")
                                    .showError();
                        }
                    } else {
                        int done = occupationCategoryDAO.insertOccupationCategory(occupationCategoryBEAN);
                        if (done > 0) {
                            Notifications.create()
                                    .title("Occupation Category Saved!")
                                    .text("Occupation Category Saved Successfully!")
                                    .showInformation();
                        } else {
                            Notifications.create()
                                    .title("Fatal Error!")
                                    .text("Occupation Category Saving Failed !")
                                    .showError();
                        }
                    }
                    txtOccCat.clear();
                    occupationCategoryBEAN = new OccupationCategoryBEAN();
                    loadTableData();
                    Bindings.bindBidirectional(txtOccCat.textProperty(), occupationCategoryBEAN.OccupationCatProperty());
                    btnClear.fire();
                } else {
                    popupMessages.showError("All fields required!", btnSave);

                }
            }

        });
        btnDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (occupationCategoryBEAN.getId() != null) {
                    int done = occupationCategoryDAO.deleteOccupationCategory(occupationCategoryBEAN.getId());
                    if (done > 0) {
                        Notifications.create()
                                .title("Occupation Category Deleted!")
                                .text("Occupation Category Deleted Successfully!")
                                //    .owner(btnSave)
                                .showInformation();
                    }
                    occupationCategoryBEAN = new OccupationCategoryBEAN();
                    loadTableData();
                    Bindings.bindBidirectional(txtOccCat.textProperty(), occupationCategoryBEAN.OccupationCatProperty());

                }
            }
        });
        btnClear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                txtOccCat.clear();
                btnSave.setText("Save");
                occupationCategoryBEAN = new OccupationCategoryBEAN();
                Bindings.bindBidirectional(txtOccCat.textProperty(), occupationCategoryBEAN.OccupationCatProperty());
            }
        });
        /* ======================== Table Row Factory ==================== */
        tblOccCat.setRowFactory(new Callback<TableView<OccupationCategoryBEAN>, TableRow<OccupationCategoryBEAN>>() {
            @Override
            public TableRow<OccupationCategoryBEAN> call(TableView<OccupationCategoryBEAN> param) {
                final TableRow<OccupationCategoryBEAN> row = new TableRow<OccupationCategoryBEAN>() {
                    @Override
                    protected void updateItem(OccupationCategoryBEAN jobTitleBean, boolean empty) {
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
                                occupationCategoryBEAN = new OccupationCategoryBEAN();
                                occupationCategoryBEAN.idProperty().addListener(new ChangeListener<String>() {

                                    @Override
                                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                                        if (newValue != null) {
                                            btnSave.setText("Update");
                                        } else {
                                            btnSave.setText("Save");
                                        }
                                    }

                                });
                                BeanUtils.copyProperties(row.getItem(), occupationCategoryBEAN);
                                Bindings.bindBidirectional(txtOccCat.textProperty(), occupationCategoryBEAN.OccupationCatProperty());

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
    public boolean validateJobTitle() {
        boolean flag = false;
        if (occupationCategoryBEAN.getOccupationCat() != null && !occupationCategoryBEAN.getOccupationCat().equalsIgnoreCase("")) {
            flag = true;
        }
        return flag;
    }

    /**
     *
     */
    public void loadTableData() {
        ObservableList<OccupationCategoryBEAN> listJobTitles = occupationCategoryDAO.retrieveOccupationCategory();
        colId.setCellValueFactory(
                new PropertyValueFactory<>("id"));
        colOccCat.setCellValueFactory(
                new PropertyValueFactory<>("OccupationCat"));
        FilteredList<OccupationCategoryBEAN> filteredData = new FilteredList<OccupationCategoryBEAN>(listJobTitles, jobTitles -> true);
        searchTxt.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(accomodations -> {
                // If filter text is empty, display all events.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Compare all records with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (accomodations.getOccupationCat().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else {
                    return false;
                } // Does not match.
            });
        });
        SortedList<OccupationCategoryBEAN> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tblOccCat.comparatorProperty());
        tblOccCat.setItems(sortedData);
        //   tblOccCat.setItems(listJobTitles);
        /* ======================== Auto Completion For Occupation Categorys ==================== */

        hboxOccCat.getChildren().remove(txtOccCat);
        txtOccCat = TextFields.createClearableTextField();
        hboxOccCat.getChildren().add(txtOccCat);
        if (listJobTitles.size() > 0) {
            AutoCompletionBinding<OccupationCategoryBEAN> autoCompletionBindingNumber = TextFields.bindAutoCompletion(txtOccCat, listJobTitles);
            autoCompletionBindingNumber.setOnAutoCompleted(new EventHandler<AutoCompletionBinding.AutoCompletionEvent<OccupationCategoryBEAN>>() {

                @Override
                public void handle(AutoCompletionBinding.AutoCompletionEvent<OccupationCategoryBEAN> event) {
                    BeanUtils.copyProperties(event.getCompletion(), occupationCategoryBEAN);
                    occupationCategoryBEAN.idProperty().addListener(new ChangeListener<String>() {

                        @Override
                        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                            if (newValue != null) {
                                btnSave.setText("Update");
                            } else {
                                btnSave.setText("Save");
                            }
                        }

                    });
                    Bindings.bindBidirectional(txtOccCat.textProperty(), occupationCategoryBEAN.OccupationCatProperty());
                    System.out.println("Test Values :: " + occupationCategoryBEAN.getId() + occupationCategoryBEAN.getOccupationCat());
                }
            });
        }
        /* ========================  Validation Support ==================== */
        validationSupport.registerValidator(txtOccCat, Validator.createEmptyValidator("Occupation Category is required"));
        validationSupport.validationResultProperty().addListener(new ChangeListener<ValidationResult>() {
            @Override
            public void changed(ObservableValue<? extends ValidationResult> observable, ValidationResult oldValue, ValidationResult newValue) {
                System.out.println("Validate Listner :: " + newValue.getMessages());
            }
        });
    }
}
