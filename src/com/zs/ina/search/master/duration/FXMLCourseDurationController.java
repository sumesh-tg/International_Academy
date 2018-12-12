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
package com.zs.ina.search.master.duration;

import com.zs.ina.common.error.ShowPopupMessages;
import com.zs.ina.context.Context;
import com.zs.ina.search.master.duration.dao.DurationBEAN;
import com.zs.ina.search.master.duration.dao.DurationDAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;

/**
 * FXML Controller class
 *
 * @author 100035
 */
public class FXMLCourseDurationController implements Initializable {

    @FXML
    private TableView<DurationBEAN> tblCourseDuration;
    @FXML
    private TableColumn<?, ?> colDuration;
    @FXML
    private TableColumn<?, ?> colDays;
    @FXML
    private TextField txtDuration;
    @FXML
    private TextField txtSearch;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnSave;
    @FXML
    private TextField txtUnit;
    @FXML
    private Button btnDelete;

    ApplicationContext springAppContext = Context.getInstance().currentProfile().getSpringContext();
    DurationDAO durationDAO = springAppContext.getBean(DurationDAO.class);
    DurationBEAN durationBEAN = new DurationBEAN();
    Logger logger = Logger.getLogger(FXMLCourseDurationController.class);
    ShowPopupMessages popupMessages = new ShowPopupMessages();
    ObservableList<DurationBEAN> listDurationTable = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        bindAllData();
        viewDurationTable();
        btnDelete.disableProperty().bind(Bindings.equal(tblCourseDuration.getSelectionModel().selectedIndexProperty(), -1));

        btnSave.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (validateAllFields()) {
                    if (durationBEAN.getDurationId() != null) {
                        int result_update = durationDAO.updateDuration(durationBEAN);
                        if (result_update > 0) {
                            viewDurationTable();
                            clearAllFields();
                            popupMessages.showSuccess("Updated Successfully !", "Course Duration Updated Successfully !", btnSave);

                        } else {
                            popupMessages.showError("Updation Failed ! Try again !", btnSave);
                        }

                    } else {
                        int result_insert = durationDAO.insertDuration(durationBEAN);
                        if (result_insert > 0) {
                            viewDurationTable();
                            clearAllFields();
                            popupMessages.showSuccess("Saved Successfully !", "Course Duration Saved Successfully !", btnSave);

                        } else {
                            popupMessages.showError("Save Operation Failed ! Try again !", btnSave);
                        }

                    }

                }
            }
        });
        tblCourseDuration.setRowFactory(new Callback<TableView<DurationBEAN>, TableRow<DurationBEAN>>() {

            @Override
            public TableRow<DurationBEAN> call(TableView<DurationBEAN> param) {
                final TableRow<DurationBEAN> row = new TableRow<DurationBEAN>() {
                    @Override
                    protected void updateItem(DurationBEAN durationBEAN, boolean empty) {
                        super.updateItem(durationBEAN, empty);
                        if (durationBEAN != null) {

                        }
                    }

                };
                row.setOnMouseClicked(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        if (event.getButton() == MouseButton.PRIMARY) {
                            if (row.getItem() != null) {
                                durationBEAN = new DurationBEAN();
                                durationBEAN.durationIdProperty().addListener(new ChangeListener<String>() {

                                    @Override
                                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                                        if (newValue != null) {
                                            btnSave.setText("Update");
                                        } else {
                                            btnSave.setText("Save");
                                        }
                                    }

                                });
                                BeanUtils.copyProperties(row.getItem(), durationBEAN);
                                bindAllData();

                            }

                        }
                    }

                });
                return row;
            }

        });
        btnDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (durationBEAN.getDurationId() != null) {
                    int result_delete = durationDAO.deleteDuration(durationBEAN);
                    if (result_delete > 0) {
                        viewDurationTable();
                        clearAllFields();
                        popupMessages.showSuccess("Deleted Successfully !", "Course Duration Deleted Successfully !", btnSave);
                    } else {
                        popupMessages.showError("Deletion Failed ! Try again !", btnSave);
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
        txtDuration.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    if (!(newValue.matches("[a-zA-Z,0-9,-,\\s]*"))) {
                        Bindings.unbindBidirectional(durationBEAN.durationProperty(), txtDuration.textProperty());
                        txtDuration.setText(oldValue);
                        Bindings.bindBidirectional(durationBEAN.durationProperty(), txtDuration.textProperty());
                    } else {
                        Bindings.unbindBidirectional(durationBEAN.durationProperty(), txtDuration.textProperty());
                        txtDuration.setText(newValue);
                        Bindings.bindBidirectional(durationBEAN.durationProperty(), txtDuration.textProperty());
                    }

                }
            }

        });
        txtUnit.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    if (!(newValue.matches("[0-9]*"))) {
                        Bindings.unbindBidirectional(durationBEAN.durationDaysProperty(), txtUnit.textProperty());
                        txtUnit.setText(oldValue);
                        Bindings.bindBidirectional(durationBEAN.durationDaysProperty(), txtUnit.textProperty());
                    } else {
                        Bindings.unbindBidirectional(durationBEAN.durationDaysProperty(), txtUnit.textProperty());
                        txtUnit.setText(newValue);
                        Bindings.bindBidirectional(durationBEAN.durationDaysProperty(), txtUnit.textProperty());
                    }

                }
            }

        });
    }

    /**
     *
     */
    public void bindAllData() {

        Bindings.bindBidirectional(txtDuration.textProperty(), durationBEAN.durationProperty());
        Bindings.bindBidirectional(txtUnit.textProperty(), durationBEAN.durationDaysProperty());

    }

    /**
     *
     */
    public void viewDurationTable() {
        listDurationTable.clear();
        listDurationTable = durationDAO.retrieveDuration();
        addDurationIntoTable(listDurationTable);
    }

    /**
     *
     */
    public void clearAllFields() {
        btnSave.setText("Save");
        durationBEAN = new DurationBEAN();
        bindAllData();
        durationBEAN.durationIdProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    btnSave.setText("Update");
                } else {
                    btnSave.setText("Save");
                }
            }

        });
        txtSearch.setText("");
        tblCourseDuration.getSelectionModel().clearSelection();
    }

    /**
     *
     * @return
     */
    public boolean validateAllFields() {
        boolean flag = true;
        if (durationBEAN.getDuration() == null || durationBEAN.getDuration().equals("")) {
            popupMessages.showError("Enter trainig course duration", txtDuration);
            flag = false;
        } else if (durationBEAN.getDurationDays() == null || durationBEAN.getDurationDays().equals("")) {
            popupMessages.showError("Enter trainig course duration in days ( in Digits)", txtUnit);
            flag = false;
        }
        return flag;
    }

    private void addDurationIntoTable(ObservableList<DurationBEAN> listIntakesTable) {

        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colDays.setCellValueFactory(new PropertyValueFactory<>("durationDays"));
        ObservableList<DurationBEAN> durationBEANs = FXCollections.observableList(listDurationTable);
        FilteredList<DurationBEAN> filteredData = new FilteredList<DurationBEAN>(durationBEANs, durations -> true);
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(durations -> {
                // If filter text is empty, display all events.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Compare all records with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (durations.getDuration().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (durations.getDurationDays().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else {
                    return false;
                } // Does not match.
            });
        });
        SortedList<DurationBEAN> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tblCourseDuration.comparatorProperty());
        tblCourseDuration.setItems(sortedData);
    }

}
