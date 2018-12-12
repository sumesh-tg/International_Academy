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
package com.zs.ina.admin.master.locations.country;

import com.zs.ina.admin.master.locations.country.dao.CountryBEAN;
import com.zs.ina.admin.master.locations.country.dao.CountryIMPL;
import com.zs.ina.admin.master.locations.country.dao.CountrySERVICE;
import com.zs.ina.common.ComboBoxJumpToChar;
import com.zs.ina.common.error.ShowPopupMessages;
import com.zs.traynotification.notification.Notification;
import com.zs.traynotification.notification.Notifications;
import com.zs.traynotification.notification.TrayNotification;
import java.net.URL;
import java.util.List;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import javafx.util.Duration;
import org.apache.log4j.Logger;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author 100035
 */
public class FXMLCountryController implements Initializable {

    @FXML
    private Button btnCountrySave;
    @FXML
    private Button btnCountryDelete;
    @FXML
    private Button btnStateSave;
    @FXML
    private Button btnStateDelete;
    @FXML
    private Button btnDistrictSave;
    @FXML
    private TextField txtCountry;
    @FXML
    private ComboBox<String> cmbCountryDel;
    @FXML
    private ComboBox<String> cmbCountryChoose1;
    @FXML
    private TextField txtState;
    @FXML
    private ComboBox<String> cmbStateDel;
    @FXML
    private ComboBox<String> cmbCountryChoose2;
    @FXML
    private ComboBox<String> cmbStateChoose;
    @FXML
    private TextField txtDistrict;
    @FXML
    private TableView<CountryBEAN> tblLocations;
    @FXML
    private TableColumn<?, ?> colCountry;
    @FXML
    private TableColumn<?, ?> colState;
    @FXML
    private TableColumn<?, ?> colDistrict;
    @FXML
    private TextField txtSearch;
    @FXML
    private HBox hboxCountry;
    @FXML
    private HBox hboxState;

    CountryBEAN countryBEAN = new CountryBEAN();
    CountrySERVICE countrySERVICE = new CountrySERVICE(new CountryIMPL());
    Logger logger = Logger.getLogger(FXMLCountryController.class);
    ObservableList<CountryBEAN> locationTableList = FXCollections.observableArrayList();
    ObservableList<String> countryList = FXCollections.observableArrayList();
    ObservableList<String> stateList = FXCollections.observableArrayList();
    ObservableList<String> districtList = FXCollections.observableArrayList();
    ShowPopupMessages showPopupMessages = new ShowPopupMessages();

    /**
     *
     *
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        autoCompletionCountry();
        viewLocationTable();
        loadcmbCountryChoose1();
        loadcmbCountryChoose2();
        loadcmbCountryDel();
        loadcmbStateDel();
      

        /* =========combobox display based on keypress =========== */
        ComboBoxJumpToChar.jumpToChar(cmbStateDel);
        ComboBoxJumpToChar.jumpToChar(cmbCountryDel);
        ComboBoxJumpToChar.jumpToChar(cmbCountryChoose1);
        ComboBoxJumpToChar.jumpToChar(cmbCountryChoose2);
        ComboBoxJumpToChar.jumpToChar(cmbStateChoose);

        btnCountryDelete.disableProperty().bind(Bindings.equal(cmbCountryDel.getSelectionModel().selectedIndexProperty(), -1));
        btnStateDelete.disableProperty().bind(Bindings.equal(cmbStateDel.getSelectionModel().selectedIndexProperty(), -1));
        btnStateSave.disableProperty().bind(Bindings.equal(cmbCountryChoose1.getSelectionModel().selectedIndexProperty(), -1));
        cmbStateChoose.disableProperty().bind(Bindings.equal(cmbCountryChoose2.getSelectionModel().selectedIndexProperty(), -1));
        btnDistrictSave.disableProperty().bind(Bindings.equal(cmbStateChoose.getSelectionModel().selectedIndexProperty(), -1));

        bindAllData();

        /* =========== Save and Delete button for Country ======== */
        btnCountrySave.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                if (validateCountryField()) {
//                    String CountryInCapitalize = txtCountry.getText().substring(0, 1).toUpperCase() + txtCountry.getText().substring(1);
//                    countryBEAN.setCountry(CountryInCapitalize);
//                    int length=CountryInCapitalize.length();
                    
                    int result_insert_country = countrySERVICE.insertCountry(countryBEAN);
                    if (result_insert_country > 0) {
                        txtCountry.setText("");
                        loadcmbCountryDel();
                        loadcmbCountryChoose1();
                        loadcmbCountryChoose2();
                        Notification notification = Notifications.SUCCESS;
                        TrayNotification trayNotification = new TrayNotification("Country Saved", "Country saved successfully", notification);
                        trayNotification.showAndDismiss(Duration.seconds(3),txtCountry);

                    } else {
                        Notification notification = Notifications.ERROR;
                        TrayNotification trayNotification = new TrayNotification("Country not saved", "Country not saved correctly", notification);
                        trayNotification.showAndDismiss(Duration.seconds(3),txtCountry);

                    }

                }

            }

            private boolean validateCountryField() {
                boolean flag = true;
                if (countryBEAN.getCountry() == null || countryBEAN.getCountry().equals("")) {
                    showPopupMessages.showError("Enter country", txtCountry);
                    flag = false;
                }

                return flag;
            }
        });

        btnCountryDelete.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                countryBEAN.setCountry(cmbCountryDel.getSelectionModel().getSelectedItem());
                int result_delete_country = countrySERVICE.deleteCountry(countryBEAN);
                System.out.println("result_delete_country int :::" + result_delete_country);
                if (result_delete_country > 0) {
                    txtCountry.setText("");
                    cmbCountryDel.getSelectionModel().clearSelection();
                    loadcmbCountryDel();
                    Notification notification = Notifications.SUCCESS;
                    TrayNotification trayNotification = new TrayNotification("Country Deleted", "Country deleted successfully", notification);
                    trayNotification.showAndDismiss(Duration.seconds(3),txtCountry);

                } else {
                    Notification notification = Notifications.ERROR;
                    TrayNotification trayNotification = new TrayNotification("Country not deleted", "Country not deleted correctly", notification);
                    trayNotification.showAndDismiss(Duration.seconds(3),txtCountry);

                }

            }

        });

        /* =========== Save and Delete button for State ======== */
        btnStateSave.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                if (validateStateField()) {
                    countryBEAN.setCountry(cmbCountryChoose1.getSelectionModel().getSelectedItem());
                    int result_insert_state = countrySERVICE.insertState(countryBEAN);
                    if (result_insert_state > 0) {
                        txtCountry.setText("");
                        txtState.setText("");
                        loadcmbStateDel();
                        Notification notification = Notifications.SUCCESS;
                        TrayNotification trayNotification = new TrayNotification("State Saved", "State saved successfully", notification);
                        trayNotification.showAndDismiss(Duration.seconds(3),txtState);

                    } else {
                        Notification notification = Notifications.ERROR;
                        TrayNotification trayNotification = new TrayNotification("State not saved", "State not saved correctly", notification);
                        trayNotification.showAndDismiss(Duration.seconds(3),txtState);

                    }

                }

            }

            private boolean validateStateField() {
                boolean flag = true;
                if (countryBEAN.getState() == null || countryBEAN.getState().equals("")) {
                    showPopupMessages.showError("Enter state", txtState);
                    flag = false;
                }

                return flag;
            }
        });

        btnStateDelete.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                countryBEAN.setState(cmbStateDel.getSelectionModel().getSelectedItem());
                int result_delete_state = countrySERVICE.deleteState(countryBEAN);
                System.out.println("result_delete_country int :::" + result_delete_state);
                if (result_delete_state > 0) {
                    txtState.setText("");
                    cmbStateDel.getSelectionModel().clearSelection();
                    loadcmbStateDel();
                    Notification notification = Notifications.SUCCESS;
                    TrayNotification trayNotification = new TrayNotification("State Deleted", "State deleted successfully", notification);
                    trayNotification.showAndDismiss(Duration.seconds(3),txtState);

                } else {
                    Notification notification = Notifications.ERROR;
                    TrayNotification trayNotification = new TrayNotification("State not deleted", "State not deleted correctly", notification);
                    trayNotification.showAndDismiss(Duration.seconds(3),txtState);

                }

            }

        });

        /* =========== Save and Delete button for District ======== */
        /*======1.load states based on the selected country ======*/
        cmbCountryChoose2.valueProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    System.out.println("newValue inside ChangeListener:::" + newValue);
                    loadcmbStateChoose(newValue);
                }
            }
        });
        btnDistrictSave.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                if (validateDistrictField()) {
                    countryBEAN.setCountry(cmbCountryChoose2.getSelectionModel().getSelectedItem());
                    countryBEAN.setState(cmbStateChoose.getSelectionModel().getSelectedItem());
                    int result_insert_district = countrySERVICE.insertDistrict(countryBEAN);
                    if (result_insert_district > 0) {
                        txtCountry.setText("");
                        txtState.setText("");
                        txtDistrict.setText("");
                       
                        viewLocationTable();
                        Notification notification = Notifications.SUCCESS;
                        TrayNotification trayNotification = new TrayNotification("District Saved", "District saved successfully", notification);
                        trayNotification.showAndDismiss(Duration.seconds(3),txtDistrict);

                    } else {
                        Notification notification = Notifications.ERROR;
                        TrayNotification trayNotification = new TrayNotification("District not saved", "District not saved correctly", notification);
                        trayNotification.showAndDismiss(Duration.seconds(3),txtDistrict);

                    }

                }

            }

            private boolean validateDistrictField() {
                boolean flag = true;
                if (countryBEAN.getDistrict() == null || countryBEAN.getDistrict().equals("")) {
                    showPopupMessages.showError("Enter district", txtDistrict);
                    flag = false;
                }
                return flag;
            }
        });

        txtCountry.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    if (!(newValue.matches("[a-zA-Z,\\s]*"))) {
                        Bindings.unbindBidirectional(countryBEAN.countryProperty(), txtCountry.textProperty());
                        txtCountry.setText(oldValue);
                        Bindings.bindBidirectional(countryBEAN.countryProperty(), txtCountry.textProperty());
                    } else {
                        Bindings.unbindBidirectional(countryBEAN.countryProperty(), txtCountry.textProperty());
                        txtCountry.setText(newValue);
                        Bindings.bindBidirectional(countryBEAN.countryProperty(), txtCountry.textProperty());
                    }

                }
            }

        });
        txtState.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    if (!(newValue.matches("[a-zA-Z,\\s]*"))) {
                        Bindings.unbindBidirectional(countryBEAN.stateProperty(), txtState.textProperty());
                        txtState.setText(oldValue);
                        Bindings.bindBidirectional(countryBEAN.stateProperty(), txtState.textProperty());
                    } else {
                        Bindings.unbindBidirectional(countryBEAN.stateProperty(), txtState.textProperty());
                        txtState.setText(newValue);
                        Bindings.bindBidirectional(countryBEAN.stateProperty(), txtState.textProperty());
                    }

                }
            }

        });
        txtDistrict.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    if (!(newValue.matches("[a-zA-Z,\\s]*"))) {
                        Bindings.unbindBidirectional(countryBEAN.districtProperty(), txtDistrict.textProperty());
                        txtDistrict.setText(oldValue);
                        Bindings.bindBidirectional(countryBEAN.districtProperty(), txtDistrict.textProperty());
                    } else {
                        Bindings.unbindBidirectional(countryBEAN.districtProperty(), txtDistrict.textProperty());
                        txtDistrict.setText(newValue);
                        Bindings.bindBidirectional(countryBEAN.districtProperty(), txtDistrict.textProperty());
                    }

                }
            }

        });
        tblLocations.setRowFactory(new Callback<TableView<CountryBEAN>, TableRow<CountryBEAN>>() {

            @Override
            public TableRow<CountryBEAN> call(TableView<CountryBEAN> param) {
                final TableRow<CountryBEAN> row = new TableRow<CountryBEAN>() {
                    @Override
                    protected void updateItem(CountryBEAN countryBEAN, boolean empty) {
                        super.updateItem(countryBEAN, empty);
                        final Tooltip tooltip = new Tooltip();

                        if (countryBEAN != null) {
                            tooltip.setText("Right Click to delete district");
                            setTooltip(tooltip);
                        }
                    }

                };
                row.setOnMouseClicked(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        if (event.getButton() == MouseButton.SECONDARY) {
                            System.out.println("inside SECONDARY");
                            final ContextMenu contextMenu = new ContextMenu();
                            contextMenu.setOnShowing(new EventHandler<WindowEvent>() {
                                public void handle(WindowEvent e) {
                                    System.out.println("showing");
                                }
                            });
                            contextMenu.setOnShown(new EventHandler<WindowEvent>() {
                                public void handle(WindowEvent e) {
                                    System.out.println("shown");
                                }
                            });

                            MenuItem itemDelete = new MenuItem("Delete");
                            itemDelete.setOnAction(new EventHandler<ActionEvent>() {
                                public void handle(ActionEvent e) {
                                    countrySERVICE.deleteDistrict(row.getItem());
                                    locationTableList.remove(row.getItem());
                                    viewLocationTable();
                                }
                            });

                            contextMenu.getItems().addAll(itemDelete);
                            row.contextMenuProperty().bind(
                                    Bindings.when(row.emptyProperty())
                                    .then((ContextMenu) null)
                                    .otherwise(contextMenu));
                        }

                    }
                });

                return row;

            }
        });

    }

    private void bindAllData() {
        Bindings.bindBidirectional(txtCountry.textProperty(), countryBEAN.countryProperty());
        Bindings.bindBidirectional(txtState.textProperty(), countryBEAN.stateProperty());
        Bindings.bindBidirectional(txtDistrict.textProperty(), countryBEAN.districtProperty());
    }

    private void loadcmbCountryDel() {
        cmbCountryDel.getItems().clear();
        countryList.clear();
        countryList = countrySERVICE.loadcmbCountryCommon();
        cmbCountryDel.setItems(countryList);
    }

    private void loadcmbCountryChoose1() {
        cmbCountryChoose1.getItems().clear();
        countryList.clear();
        countryList = countrySERVICE.loadcmbCountryCommon();
        cmbCountryChoose1.setItems(countryList);
    }

    private void loadcmbCountryChoose2() {
        cmbCountryChoose2.getItems().clear();
        countryList.clear();
        countryList = countrySERVICE.loadcmbCountryCommon();
        cmbCountryChoose2.setItems(countryList);
    }

    private void loadcmbStateDel() {
        cmbStateDel.getItems().clear();
        stateList.clear();
        stateList = countrySERVICE.loadcmbStateDel();
        cmbStateDel.setItems(stateList);
    }

    /**
     *
     * @param country
     */
    public void loadcmbStateChoose(String country) {
        cmbStateChoose.getItems().clear();
        stateList.clear();
        System.out.println("newValue inside loadcmbStateChoose:::" + country);
        stateList = countrySERVICE.loadcmbStateChoose(country);
        cmbStateChoose.setItems(stateList);
    }


    private void viewLocationTable() {
        locationTableList.clear();
        locationTableList = countrySERVICE.retrieveLocations();
        addLocationsIntoTable(locationTableList);
    }

    private void addLocationsIntoTable(ObservableList<CountryBEAN> locationTableList) {
        colCountry.setCellValueFactory(new PropertyValueFactory<>("country"));
        colState.setCellValueFactory(new PropertyValueFactory<>("state"));
        colDistrict.setCellValueFactory(new PropertyValueFactory<>("district"));

        ObservableList<CountryBEAN> CountryBEANs = FXCollections.observableList(locationTableList);
        FilteredList<CountryBEAN> filteredData = new FilteredList<CountryBEAN>(CountryBEANs, locations -> true);
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(locations -> {
                // If filter text is empty, display all events.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Compare all records with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (locations.getCountry().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (locations.getState().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (locations.getDistrict().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else {
                    return false;
                } // Does not match.
            });
        });

        SortedList<CountryBEAN> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tblLocations.comparatorProperty());
        tblLocations.setItems(sortedData);
    }

    /**
     *
     */
    public void autoCompletionCountry() {
        List<String> allCountriesList = CountryIMPL.getAllCountries();
        txtCountry = TextFields.createClearableTextField();
        AutoCompletionBinding<String> autoCompletionBindingNumber = TextFields.bindAutoCompletion(txtCountry, allCountriesList);
        txtCountry.setPrefWidth(218);
        hboxCountry.getChildren().remove(1);
        hboxCountry.getChildren().add(txtCountry);
    }

}
