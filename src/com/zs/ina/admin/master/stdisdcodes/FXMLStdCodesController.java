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

import com.zs.ina.admin.dao.SourcePOJO;
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
public class FXMLStdCodesController implements Initializable {

    @FXML
    private Button btnDelete;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnSave;
    @FXML
    private TextField searchTxt;
    @FXML
    private TableView<StdCodesBEAN> tblStdCodes;
    @FXML
    private TableColumn<StdCodesBEAN, StdCodesBEAN> clmPlace;
    @FXML
    private TableColumn<StdCodesBEAN, StdCodesBEAN> clmCode;
    @FXML
    private TextField txtPlace;
    @FXML
    private TextField txtCode;
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
        Bindings.bindBidirectional(txtPlace.textProperty(), codesBEAN.placeProperty());
        Bindings.bindBidirectional(txtCode.textProperty(), codesBEAN.codeProperty());
        loadTableData();
        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (validateStdCode(codesBEAN)) {
                    if (codesBEAN.getId() == null) {
                        StdCodesService.insertStdCodes(codesBEAN);
                        btnClear.fire();
                        loadTableData();
                        popupMessages.showSuccess("Saved Successfully !", "", btnSave);
                    } else {
                        StdCodesService.updateStdCodes(codesBEAN);
                        btnClear.fire();
                        loadTableData();
                        popupMessages.showSuccess("Updated Successfully !", "", btnSave);
                    }
                }

            }
        });
        /* ======================== Click Event On TableView ==================== */
        tblStdCodes.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    if (tblStdCodes.getSelectionModel().getSelectedItem() != null) {
                        codesBEAN = new StdCodesBEAN();
                        BeanUtils.copyProperties(codesBEAN, tblStdCodes.getSelectionModel().getSelectedItem());
                        Bindings.bindBidirectional(txtPlace.textProperty(), codesBEAN.placeProperty());
                        Bindings.bindBidirectional(txtCode.textProperty(), codesBEAN.codeProperty());
                        btnSave.setText("Update");

                    }
                } catch (IllegalAccessException ex) {
                    logger.error(ex);
                } catch (InvocationTargetException ex) {
                    logger.error(ex);
                }
            }
        });

        btnDelete.disableProperty().bind(Bindings.equal(tblStdCodes.getSelectionModel().selectedIndexProperty(), -1));
        btnDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (codesBEAN.getId() != null) {
                    StdCodesService.deleteStdCodes(codesBEAN);
                    loadTableData();
                    btnClear.fire();
                    popupMessages.showSuccess("Deleted Successfully !", "", btnSave);
                }
            }
        });
        btnClear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                codesBEAN = new StdCodesBEAN();
                Bindings.bindBidirectional(txtPlace.textProperty(), codesBEAN.placeProperty());
                Bindings.bindBidirectional(txtCode.textProperty(), codesBEAN.codeProperty());
                tblStdCodes.getSelectionModel().clearSelection();
                btnSave.setText("Save");
                searchTxt.clear();
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
        txtCode.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    if (!(newValue.matches("[0-9]*")) || newValue.length() > 10) {
                        Bindings.unbindBidirectional(codesBEAN.codeProperty(), txtCode.textProperty());
                        txtCode.setText(oldValue);
                        Bindings.bindBidirectional(codesBEAN.codeProperty(), txtCode.textProperty());
                    } else {
                        Bindings.unbindBidirectional(codesBEAN.codeProperty(), txtCode.textProperty());
                        txtCode.setText(newValue);
                        Bindings.bindBidirectional(codesBEAN.codeProperty(), txtCode.textProperty());
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
        masterSearchData.clear();
        ObservableList<StdCodesBEAN> stdCodesBEANList = StdCodesService.retrieveStdCodes();
        masterSearchData = stdCodesBEANList;
        clmCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        clmPlace.setCellValueFactory(new PropertyValueFactory<>("place"));
        tblStdCodes.setItems(stdCodesBEANList);

        /* ======================== Add Searching  ==================== */
        FilteredList<StdCodesBEAN> filteredData = new FilteredList<>(masterSearchData, p -> true);
        searchTxt.textProperty().addListener(new ChangeListener<String>() {
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
                sortedData.comparatorProperty().bind(tblStdCodes.comparatorProperty());
                tblStdCodes.setItems(sortedData);
            }
        });
    }

    /**
     *
     * @param codesBEAN
     * @return
     */
    public boolean validateStdCode(StdCodesBEAN codesBEAN) {
        boolean flag = true;
        if (codesBEAN.getPlace() == null || codesBEAN.getPlace().equalsIgnoreCase("")) {
            flag = false;
            popupMessages.showError("Enter STD Code", txtCode);
        } else if (codesBEAN.getPlace() == null || codesBEAN.getPlace().equalsIgnoreCase("")) {
            flag = false;
            popupMessages.showError("Enter Place", txtPlace);
        }
        return flag;
    }
}
