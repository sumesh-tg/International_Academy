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
package com.zs.ina.admin.master.locations;

import com.zs.ina.admin.master.locations.dao.CountryIMPL;
import com.zs.ina.login.INALoginForm;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import com.zs.ina.admin.master.locations.dao.CountryDAO;
import com.zs.ina.admin.master.locations.dao.LocationsDAO;
import com.zs.ina.admin.master.locations.dao.LocationsIMPL;
import com.zs.ina.admin.master.locations.dao.LocationsPOJO;
import javafx.beans.binding.Bindings;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class FXMLLocationsController implements Initializable {

    @FXML
    private TextField txtCountry;
    @FXML
    private Button btnCountrySave;
    @FXML
    private ComboBox<String> cmbDelCountry;
    @FXML
    private Button btnDelCountry;
    @FXML
    private ComboBox<String> cmbAddCountry;
    @FXML
    private TextField txtLocation;
    @FXML
    private Button btnSaveLocation;
    @FXML
    private TextField txtSearch;
    @FXML
    private TableView<LocationsPOJO> tblCountryLocation;
    CountryDAO countryDAO = new CountryIMPL();
    LocationsDAO locationsDAO = new LocationsIMPL();
    @FXML
    private TableColumn<LocationsPOJO, String> clmCountry;
    @FXML
    private TableColumn<LocationsPOJO, String> clmLocation;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addCountryOperations();
        addLocationOperations();
        addTableData();

        /* ======================== Table Events ==================== */
        tblCountryLocation.setRowFactory(new Callback<TableView<LocationsPOJO>, TableRow<LocationsPOJO>>() {
            @Override
            public TableRow<LocationsPOJO> call(TableView<LocationsPOJO> param) {
                final TableRow<LocationsPOJO> row = new TableRow<>();
                final ContextMenu contextMenu = new ContextMenu();
                final MenuItem deleteLocation = new MenuItem("Delete");
                final MenuItem updateLocation = new MenuItem("Update");
                deleteLocation.setOnAction((ActionEvent event) -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure want to delete " + row.getItem().getLocation() + " !", ButtonType.YES, ButtonType.CANCEL);
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                    stage.initOwner(btnDelCountry.getScene().getWindow());
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.YES) {
                        locationsDAO.deleteLocation(row.getItem().getId());
                        addTableData();
                    }
                });
                contextMenu.getItems().addAll(deleteLocation, updateLocation);
                row.contextMenuProperty().bind(
                        Bindings.when(row.emptyProperty())
                        .then((ContextMenu) null)
                        .otherwise(contextMenu));
                return row;
            }
        });
    }

    /**
     *
     */
    public void addTableData() {
        clmLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        clmCountry.setCellValueFactory(new PropertyValueFactory<>("country"));
        tblCountryLocation.setItems(locationsDAO.retrieveAllLocations());;
    }

    /**
     *
     */
    public void addLocationOperations() {

        btnSaveLocation.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (cmbAddCountry.getSelectionModel().getSelectedIndex() >= 0 & txtLocation.getText() != null) {
                    if (!txtLocation.getText().equalsIgnoreCase("")) {
                        locationsDAO.insertLocation(cmbAddCountry.getSelectionModel().getSelectedItem(), txtLocation.getText());
                        cmbAddCountry.getSelectionModel().clearSelection();
                        txtLocation.setText("");
                        addTableData();
                    }
                }

            }
        });
    }

    /**
     *
     */
    public void addCountryOperations() {

        txtCountry.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null & newValue.length() > 0) {
                    String firstLetterCapitalized
                            = newValue.toLowerCase().substring(0, 1).toUpperCase(Locale.getDefault()) + newValue.substring(1);
                    txtCountry.setText(firstLetterCapitalized);
                }
            }
        });

        btnCountrySave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (txtCountry.getText() != null) {
                    countryDAO.insertCountry(txtCountry.getText());
                    cmbAddCountry.getItems().clear();
                    cmbDelCountry.getItems().clear();
                    cmbAddCountry.setItems(countryDAO.retrieveMasterAllCountries());
                    cmbDelCountry.setItems(countryDAO.retrieveMasterAllCountries());
                    txtCountry.setText("");
                }
            }
        });
        btnDelCountry.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure want to delete !", ButtonType.YES, ButtonType.CANCEL);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                stage.initOwner(btnDelCountry.getScene().getWindow());
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES) {
                    if (cmbDelCountry.getSelectionModel().getSelectedIndex() >= 0) {
                        countryDAO.deleteCountry(cmbDelCountry.getSelectionModel().getSelectedItem());
                        cmbAddCountry.getItems().clear();
                        cmbDelCountry.getItems().clear();
                        cmbAddCountry.setItems(countryDAO.retrieveMasterAllCountries());
                        cmbDelCountry.setItems(countryDAO.retrieveMasterAllCountries());
                    }
                }
            }
        }
        );

        cmbAddCountry.getItems().clear();
        cmbDelCountry.getItems().clear();
        cmbAddCountry.setItems(countryDAO.retrieveMasterAllCountries());
        cmbDelCountry.setItems(countryDAO.retrieveMasterAllCountries());
        
    

    }

}
