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
package com.zs.ina.admin.master.configpool;

import com.zs.ina.admin.master.configpool.dao.ConfigPoolBEAN;
import com.zs.ina.admin.master.configpool.dao.ConfigPoolSERVICE;
import com.zs.ina.admin.master.configpool.dao.NamePOJO;
import com.zs.ina.common.error.ShowPopupMessages;
import com.zs.traynotification.notification.Notification;
import com.zs.traynotification.notification.Notifications;
import com.zs.traynotification.notification.TrayNotification;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javafx.util.Duration;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;

/**
 * FXML Controller class
 *
 * @author 100035
 */
public class FXMLConfigPoolController implements Initializable {

    @FXML
    private Button btnSave;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnClear;
    @FXML
    private TableView<ConfigPoolBEAN> tblEnquiryLimit;

    @FXML
    private ComboBox<String> cmbBranch;
    @FXML
    private ComboBox<NamePOJO> cmbCounselor;
    @FXML
    private TextField txtEnquiryLimit;
    // private ComboBox<String> cmbTimeUnit;

    ConfigPoolBEAN configPoolBEAN = new ConfigPoolBEAN();
    // ObservableList<String> TimeUnitList = FXCollections.observableArrayList();
    ConfigPoolSERVICE configPoolSERVICE = new ConfigPoolSERVICE();
    Logger logger = Logger.getLogger(FXMLConfigPoolController.class);
    ObservableList<String> branchList = FXCollections.observableArrayList();
    ObservableList<NamePOJO> fullNameList = FXCollections.observableArrayList();
    ObservableList<ConfigPoolBEAN> configPoolTableList = FXCollections.observableArrayList();
    ObservableList<ConfigPoolBEAN> configPoolCheckList = FXCollections.observableArrayList();
    ShowPopupMessages showPopupMessages = new ShowPopupMessages();
    int timeLimit, duration;
    String durations;
    @FXML
    private TextField txtFlagLimit;
    @FXML
    private TableColumn<?, ?> colBranch;
    @FXML
    private TableColumn<?, ?> colCounselor;
    @FXML
    private TableColumn<?, ?> colEnquiryLimit;
    @FXML
    private TextField txtSearch;
    @FXML
    private TextField txtMinutes;
    @FXML
    private TextField txtDays;
    @FXML
    private TextField txtHours;
    @FXML
    private TableColumn<?, ?> colFlagLimit;
    @FXML
    private TableColumn<?, ?> colTimeLimit;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        viewConfigTable();
        bindAllData();
        clearAllFields();

        branchList = configPoolSERVICE.retrieveBranches();
        cmbBranch.setItems(branchList);
        cmbCounselor.disableProperty().bind(Bindings.equal(cmbBranch.getSelectionModel().selectedIndexProperty(), -1));
        btnDelete.disableProperty().bind(Bindings.equal(tblEnquiryLimit.getSelectionModel().selectedIndexProperty(), -1));

        cmbBranch.valueProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {

                    System.out.println("branch :::" + newValue);
                    cmbCounselor.getItems().clear();
                    fullNameList = configPoolSERVICE.retrieveFullNames(newValue);
                    cmbCounselor.setItems(fullNameList);
                    txtDays.setText("0");
                    txtHours.setText("0");
                    txtMinutes.setText("0");
                    txtEnquiryLimit.setText("");
                    txtFlagLimit.setText("");
                    bindAllData();
                    

                }

            }
        });

        cmbCounselor.valueProperty().addListener(new ChangeListener<NamePOJO>() {

            @Override
            public void changed(ObservableValue<? extends NamePOJO> observable, NamePOJO oldValue, NamePOJO newValue) {
                if (newValue != null) {
                    for (ConfigPoolBEAN configPoolBeans : configPoolTableList) {
                        if (configPoolBeans.getUsername().equalsIgnoreCase(newValue.getUsername())) {
                            BeanUtils.copyProperties(configPoolBeans, configPoolBEAN);
                            bindAllData();
                        }
                    }
                    System.out.println("userame :::" + newValue.getUsername());

                    configPoolBEAN.setUsername(newValue.getUsername());

                }

            }
        });

        txtEnquiryLimit.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {

                    if ((newValue.matches("[0-9]*") && newValue.length() <= 4)) {
                        Bindings.unbindBidirectional(configPoolBEAN.enquiryLimitProperty(), txtEnquiryLimit.textProperty());
                        txtEnquiryLimit.setText(newValue);
                        Bindings.bindBidirectional(configPoolBEAN.enquiryLimitProperty(), txtEnquiryLimit.textProperty());
                    } else {
                        Bindings.unbindBidirectional(configPoolBEAN.enquiryLimitProperty(), txtEnquiryLimit.textProperty());
                        txtEnquiryLimit.setText(oldValue);
                        Bindings.bindBidirectional(configPoolBEAN.enquiryLimitProperty(), txtEnquiryLimit.textProperty());
                    }

                }
            }
        });
        txtFlagLimit.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {

                    if ((newValue.matches("[0-9]*") && newValue.length() <= 4)) {
                        Bindings.unbindBidirectional(configPoolBEAN.flagLimitProperty(), txtFlagLimit.textProperty());
                        txtFlagLimit.setText(newValue);
                        Bindings.bindBidirectional(configPoolBEAN.flagLimitProperty(), txtFlagLimit.textProperty());
                    } else {
                        Bindings.unbindBidirectional(configPoolBEAN.flagLimitProperty(), txtFlagLimit.textProperty());
                        txtFlagLimit.setText(oldValue);
                        Bindings.bindBidirectional(configPoolBEAN.flagLimitProperty(), txtFlagLimit.textProperty());
                    }
                }
            }
        });
        txtDays.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {

                    if ((newValue.matches("[0-9]*") && newValue.length() <= 3)) {
                        Bindings.unbindBidirectional(configPoolBEAN.daysProperty(), txtDays.textProperty());
                        txtDays.setText(newValue);
                        Bindings.bindBidirectional(configPoolBEAN.daysProperty(), txtDays.textProperty());
                    } else {
                        Bindings.unbindBidirectional(configPoolBEAN.daysProperty(), txtDays.textProperty());
                        txtDays.setText(oldValue);
                        Bindings.bindBidirectional(configPoolBEAN.daysProperty(), txtDays.textProperty());
                    }
                }
            }
        });
        txtHours.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {

                    if ((newValue.matches("[0-9]*") && newValue.length() <= 2)) {
                        Bindings.unbindBidirectional(configPoolBEAN.hoursProperty(), txtHours.textProperty());
                        txtHours.setText(newValue);
                        Bindings.bindBidirectional(configPoolBEAN.hoursProperty(), txtHours.textProperty());
                    } else {
                        Bindings.unbindBidirectional(configPoolBEAN.hoursProperty(), txtHours.textProperty());
                        txtHours.setText(oldValue);
                        Bindings.bindBidirectional(configPoolBEAN.hoursProperty(), txtHours.textProperty());
                    }

                }
            }
        });
        txtMinutes.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {

                    if ((newValue.matches("[0-9]*") && newValue.length() <= 2)) {
                        Bindings.unbindBidirectional(configPoolBEAN.minutesProperty(), txtMinutes.textProperty());
                        txtMinutes.setText(newValue);
                        Bindings.bindBidirectional(configPoolBEAN.minutesProperty(), txtMinutes.textProperty());
                    } else {
                        Bindings.unbindBidirectional(configPoolBEAN.minutesProperty(), txtMinutes.textProperty());
                        txtMinutes.setText(oldValue);
                        Bindings.bindBidirectional(configPoolBEAN.minutesProperty(), txtMinutes.textProperty());
                    }
                }
            }
        });
        btnSave.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                if (validateAllFields()) {
                    int days = Integer.parseInt(configPoolBEAN.getDays());
                    int daysInSeconds = days * 24 * 60 * 60;
                    int hours = Integer.parseInt(configPoolBEAN.getHours());
                    int hoursInSeconds = hours * 60 * 60;
                    int minutes = Integer.parseInt(configPoolBEAN.getMinutes());
                    int minutesInSeconds = minutes * 60;
                    int duration = daysInSeconds + hoursInSeconds + minutesInSeconds;
                    String durationInString = Integer.toString(duration);
                    configPoolBEAN.setEnquiryDuration(durationInString);
                    if (configPoolBEAN.getId() != null) {
                        int result_update = configPoolSERVICE.updateConfigPool(configPoolBEAN);
                        if (result_update > 0) {
                            viewConfigTable();
                            clearAllFields();

                            Notification notification = Notifications.SUCCESS;
                            TrayNotification trayNotification = new TrayNotification("Enquiry Limit details are updated", "Enquiry Limit details updated successfully", notification);
                            trayNotification.showAndDismiss(Duration.seconds(3),txtEnquiryLimit);

                        } else {
                            Notification notification = Notifications.ERROR;
                            TrayNotification trayNotification = new TrayNotification("Enquiry Limit details is not updated", "Enquiry Limit is not updated correctly", notification);
                            trayNotification.showAndDismiss(Duration.seconds(3),txtEnquiryLimit);

                        }

                    } else {

                        int result_insert = configPoolSERVICE.insertConfigPool(configPoolBEAN);
                        if (result_insert > 0) {
                            viewConfigTable();
                            clearAllFields();

                            Notification notification = Notifications.SUCCESS;
                            TrayNotification trayNotification = new TrayNotification("Enquiry Limit details are saved", "Enquiry Limit details saved successfully", notification);
                            trayNotification.showAndDismiss(Duration.seconds(3),txtEnquiryLimit);

                        } else {
                            Notification notification = Notifications.ERROR;
                            TrayNotification trayNotification = new TrayNotification("Enquiry Limit details are not saved", "Enquiry Limit details are not saved correctly", notification);
                            trayNotification.showAndDismiss(Duration.seconds(3),txtEnquiryLimit);

                        }

                    }

                }

            }

            private boolean validateAllFields() {
                boolean flag = true;
                if (cmbBranch.getSelectionModel().getSelectedIndex() == -1) {
                    showPopupMessages.showError("Select Branch", cmbBranch);
                    flag = false;
                } else if (cmbCounselor.getSelectionModel().getSelectedIndex() == -1) {
                    showPopupMessages.showError("Select Counselor", cmbCounselor);
                    flag = false;
                } else if (configPoolBEAN.getEnquiryLimit() == null || configPoolBEAN.getEnquiryLimit().equals("")) {
                    showPopupMessages.showError("Enter enquiry limit", txtEnquiryLimit);
                    flag = false;
                } else if (configPoolBEAN.getFlagLimit() == null || configPoolBEAN.getFlagLimit().equals("")) {
                    showPopupMessages.showError("Enter flag limit", txtFlagLimit);
                    flag = false;
                } else if (configPoolBEAN.getDays() == null || configPoolBEAN.getDays().equals("")) {
                    showPopupMessages.showError("Enter days", txtDays);
                    flag = false;
                } else if (configPoolBEAN.getHours() == null || configPoolBEAN.getHours().equals("")) {
                    showPopupMessages.showError("Enter hours", txtHours);
                    flag = false;
                } else if (configPoolBEAN.getMinutes() == null || configPoolBEAN.getMinutes().equals("")) {
                    showPopupMessages.showError("Enter minutes", txtMinutes);
                    flag = false;
                } else if (configPoolBEAN.getDays().equals("0") && configPoolBEAN.getHours().equals("0") && configPoolBEAN.getMinutes().equals("0")) {
                    showPopupMessages.showError("Enter valid time limit", txtDays);
                    flag = false;
                }
                return flag;

            }

        });

        btnDelete.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                int result_delete = configPoolSERVICE.deleteConfigPool(configPoolBEAN.getId());
                if (result_delete > 0) {
                    clearAllFields();
                    viewConfigTable();
                    Notification notification = Notifications.SUCCESS;
                    TrayNotification trayNotification = new TrayNotification("Counselor enquiry limit details deleted", "Counselor enquiry limit details deleted successfully", notification);
                    trayNotification.showAndDismiss(Duration.seconds(3));

                } else {
                    Notification notification = Notifications.ERROR;
                    TrayNotification trayNotification = new TrayNotification("Counselor enquiry limit details not deleted", "Counselor enquiry limit details not deleted successfully", notification);
                    trayNotification.showAndDismiss(Duration.seconds(3));

                }
            }
        });
        tblEnquiryLimit.setRowFactory(new Callback<TableView<ConfigPoolBEAN>, TableRow<ConfigPoolBEAN>>() {

            @Override
            public TableRow<ConfigPoolBEAN> call(TableView<ConfigPoolBEAN> param) {
                final TableRow<ConfigPoolBEAN> row = new TableRow<ConfigPoolBEAN>() {
                    @Override
                    protected void updateItem(ConfigPoolBEAN configPoolBEAN, boolean empty) {
                        super.updateItem(configPoolBEAN, empty);
                        if (!empty) {
                            if (configPoolBEAN != null) {

                            }
                        }
                    }

                };
                row.setOnMouseClicked(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        if (event.getButton() == MouseButton.PRIMARY) {
                            if (row.getItem() != null) {
                                configPoolBEAN = new ConfigPoolBEAN();
                                configPoolBEAN.idProperty().addListener(new ChangeListener<String>() {

                                    @Override
                                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                                        if (newValue != null) {
                                            btnSave.setText("Update");
                                        } else {
                                            btnSave.setText("Save");
                                        }
                                    }
                                });
                             //   configPoolBEAN=row.getItem();
                                BeanUtils.copyProperties(row.getItem(), configPoolBEAN);
                                bindAllData();
                                cmbBranch.getSelectionModel().select(configPoolBEAN.getBranch());

                                for (NamePOJO namePOJO : cmbCounselor.getItems()) {
                                    if (namePOJO.getUsername().equalsIgnoreCase(row.getItem().getUsername())) {
                                        cmbCounselor.getSelectionModel().select(namePOJO);
                                    }
                                }

                            }

                        }
                    }

                });
                return row;
            }

        });
        btnClear.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                clearAllFields();
            }
        });

    }

    private void bindAllData() {
        Bindings.bindBidirectional(txtEnquiryLimit.textProperty(), configPoolBEAN.enquiryLimitProperty());
        Bindings.bindBidirectional(txtFlagLimit.textProperty(), configPoolBEAN.flagLimitProperty());
        Bindings.bindBidirectional(txtDays.textProperty(), configPoolBEAN.daysProperty());
        Bindings.bindBidirectional(txtHours.textProperty(), configPoolBEAN.hoursProperty());
        Bindings.bindBidirectional(txtMinutes.textProperty(), configPoolBEAN.minutesProperty());

    }

    private void viewConfigTable() {
        configPoolTableList.clear();
        configPoolTableList = configPoolSERVICE.retrieveConfigPool();
        addConfigPoolIntoTable(configPoolTableList);
    }

    private void addConfigPoolIntoTable(ObservableList<ConfigPoolBEAN> configPoolTableList) {
        colBranch.setCellValueFactory(new PropertyValueFactory<>("branch"));
        colCounselor.setCellValueFactory(new PropertyValueFactory<>("fullname"));
        colEnquiryLimit.setCellValueFactory(new PropertyValueFactory<>("enquiryLimit"));
        colFlagLimit.setCellValueFactory(new PropertyValueFactory<>("flagLimit"));
        colTimeLimit.setCellValueFactory(new PropertyValueFactory<>("tblTimeLimit"));
        ObservableList<ConfigPoolBEAN> configPoolBEANs = FXCollections.observableList(configPoolTableList);
        FilteredList<ConfigPoolBEAN> filteredData = new FilteredList<ConfigPoolBEAN>(configPoolBEANs, config -> true);
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(config -> {
                // If filter text is empty, display all events.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Compare all records with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (config.getBranch().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (config.getFullname().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (config.getEnquiryLimit().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (config.getFlagLimit().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;

                } else if (config.getTblTimeLimit().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;

                } else {
                    return false;
                } // Does not match.
            });
        });

        SortedList<ConfigPoolBEAN> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tblEnquiryLimit.comparatorProperty());
        tblEnquiryLimit.setItems(sortedData);

    }

    private void clearAllFields() {
        btnSave.setText("Save");
        tblEnquiryLimit.getSelectionModel().clearSelection();
        cmbBranch.getSelectionModel().clearSelection();
        cmbCounselor.getSelectionModel().clearSelection();
        configPoolBEAN = new ConfigPoolBEAN();
        bindAllData();
        configPoolBEAN.idProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    System.out.println("id addListener:::" + newValue);
                    btnSave.setText("Update");
                } else {
                    btnSave.setText("Save");
                }
            }
        });

        txtSearch.setText("");
        txtDays.setText("0");
        txtHours.setText("0");
        txtMinutes.setText("0");
    }

}
