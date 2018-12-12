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
package com.zs.ina.admin.master.stdisdcodes;

import static com.zs.ina.admin.master.stdisdcodes.FXMLStdCodesController.logger;
import com.zs.ina.admin.master.stdisdcodes.dao.StdCodesBEAN;
import com.zs.ina.admin.master.stdisdcodes.dao.StdCodesService;
import com.zs.ina.common.error.ShowPopupMessages;
import java.lang.reflect.InvocationTargetException;
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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

/**
 * FXML Controller class
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class FXMLIsdCodesController implements Initializable {

    @FXML
    private TextField txtPlace;
    @FXML
    private TextField txtISD;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnSave;
    @FXML
    private TextField txtSearch;
    @FXML
    private TableView<StdCodesBEAN> tblISD;
    @FXML
    private TableColumn<?, ?> colPlace;
    @FXML
    private TableColumn<?, ?> colISD;

    ShowPopupMessages popupMessages = new ShowPopupMessages();
    StdCodesBEAN codesBEAN = new StdCodesBEAN();
    static Logger logger = Logger.getLogger(FXMLStdCodesController.class);
    ObservableList<StdCodesBEAN> masterSearchData = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bindAllData();

        loadTableData();
        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (validateIsdCode(codesBEAN)) {
                    if (codesBEAN.getId() == null) {
                        StdCodesService.insertIsdCodes(codesBEAN);
                        btnClear.fire();
                        loadTableData();
                        popupMessages.showSuccess("ISD Saved Successfully !", "", btnSave);
                    } else {
                        StdCodesService.updateIsdCodes(codesBEAN);
                        btnClear.fire();
                        loadTableData();
                        popupMessages.showSuccess("ISD Updated Successfully !", "", btnSave);
                    }
                }

            }
        });
        /* ======================== Click Event On TableView ==================== */
        tblISD.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    if (tblISD.getSelectionModel().getSelectedItem() != null) {
                        codesBEAN = new StdCodesBEAN();
                        BeanUtils.copyProperties(codesBEAN, tblISD.getSelectionModel().getSelectedItem());
                        bindAllData();
                        btnSave.setText("Update");

                    }
                } catch (IllegalAccessException ex) {
                    logger.error(ex);
                } catch (InvocationTargetException ex) {
                    logger.error(ex);
                }
            }
        });

        btnDelete.disableProperty().bind(Bindings.equal(tblISD.getSelectionModel().selectedIndexProperty(), -1));
        btnDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (codesBEAN.getId() != null) {
                    StdCodesService.deleteIsdCodes(codesBEAN);
                    loadTableData();
                    btnClear.fire();
                    popupMessages.showSuccess("ISD Deleted Successfully !", "", btnSave);
                }
            }
        });
        btnClear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                codesBEAN = new StdCodesBEAN();
                Bindings.bindBidirectional(txtPlace.textProperty(), codesBEAN.placeProperty());
                Bindings.bindBidirectional(txtISD.textProperty(), codesBEAN.codeProperty());
                tblISD.getSelectionModel().clearSelection();
                btnSave.setText("Save");
            }
        });

        /* ======================== Change events ==================== */
        txtPlace.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    if (!(newValue.matches("[a-zA-Z,\\s]*"))) {
                        Bindings.unbindBidirectional(codesBEAN.placeProperty(), txtPlace.textProperty());
                        txtPlace.setText(oldValue);
                        Bindings.bindBidirectional(codesBEAN.placeProperty(), txtPlace.textProperty());
                    } else {
                        Bindings.unbindBidirectional(codesBEAN.placeProperty(), txtPlace.textProperty());
                        txtPlace.setText(newValue);
                        Bindings.bindBidirectional(codesBEAN.placeProperty(), txtPlace.textProperty());
                    }

                }
            }
        });
        txtISD.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    if (!(newValue.matches("[0-9]*")) || newValue.length() > 10) {
                        Bindings.unbindBidirectional(codesBEAN.codeProperty(), txtISD.textProperty());
                        txtISD.setText(oldValue);
                        Bindings.bindBidirectional(codesBEAN.codeProperty(), txtISD.textProperty());
                    } else {
                        Bindings.unbindBidirectional(codesBEAN.codeProperty(), txtISD.textProperty());
                        txtISD.setText(newValue);
                        Bindings.bindBidirectional(codesBEAN.codeProperty(), txtISD.textProperty());
                    }

                }
            }
        });
    }

    /**
     *
     */
    public void loadTableData() {
        /* ======================== Load Table Data ==================== */
        ObservableList<StdCodesBEAN> stdCodesBEANList = StdCodesService.retrieveIsdCodes();
        masterSearchData = stdCodesBEANList;
        colISD.setCellValueFactory(new PropertyValueFactory<>("code"));
        colPlace.setCellValueFactory(new PropertyValueFactory<>("place"));
        tblISD.setItems(stdCodesBEANList);

        /* ======================== Add Searching  ==================== */
        FilteredList<StdCodesBEAN> filteredData = new FilteredList<>(masterSearchData, p -> true);
        txtSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                filteredData.setPredicate(enquiry -> {
                    btnClear.fire();
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();

                    if (enquiry.getPlace().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (enquiry.getCode().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    return false;
                });
                SortedList<StdCodesBEAN> sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(tblISD.comparatorProperty());
                tblISD.setItems(sortedData);
            }
        });
    }

    /**
     *
     * @param codesBEAN
     * @return
     */
    public boolean validateIsdCode(StdCodesBEAN codesBEAN) {
        boolean flag = true;
        if (codesBEAN.getPlace() == null || codesBEAN.getPlace().equalsIgnoreCase("")) {
            flag = false;
            popupMessages.showError("Enter ISD Code", txtISD);
        } else if (codesBEAN.getPlace() == null || codesBEAN.getPlace().equalsIgnoreCase("")) {
            flag = false;
            popupMessages.showError("Enter Place", txtPlace);
        }
        return flag;
    }

    /**
     *
     */
    public void bindAllData() {
        Bindings.bindBidirectional(txtPlace.textProperty(), codesBEAN.placeProperty());
        Bindings.bindBidirectional(txtISD.textProperty(), codesBEAN.codeProperty());
    }
}
