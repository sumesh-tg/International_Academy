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
package com.zs.ina.admin.master.sources;

import com.zs.ina.admin.master.sources.dao.SourceBEAN;
import com.zs.ina.admin.master.sources.dao.SourceDAO;
import com.zs.ina.admin.master.sources.dao.SourceIMPL;
import com.zs.ina.admin.master.sources.dao.SourcePOJO;
import com.zs.ina.admin.master.sources.dao.SourceSERVICE;
import com.zs.ina.agency.dao.AgencyDAO;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import javafx.util.Duration;
import org.apache.log4j.Logger;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import org.springframework.beans.BeanUtils;

/**
 * FXML Controller class
 *
 * @author 100035
 */
public class FXMLSourcesController implements Initializable {

    @FXML
    private TextField txtSourceName;
    @FXML
    private ComboBox<SourcePOJO> cmbSetDefaultSource;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnSetDefault;
    @FXML
    private TextField txtSearch;
    @FXML
    private TableColumn<?, ?> colSourceName;
    @FXML
    private TableColumn<?, ?> colSourceType;
    @FXML
    private TableView<SourceBEAN> tblSources;
    @FXML
    private Button btnClear;

    SourceBEAN sourceBEAN = new SourceBEAN();
    SourceSERVICE sourceSERVICE = new SourceSERVICE(new SourceIMPL());
    Logger logger = Logger.getLogger(FXMLSourcesController.class);
    ObservableList<SourceBEAN> sourceTableList = FXCollections.observableArrayList();
    ObservableList<SourcePOJO> sourcesList = FXCollections.observableArrayList();
    ShowPopupMessages showPopupMessages = new ShowPopupMessages();
    @FXML
    private HBox hboxSourceName;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        autoCompletion();
        loadcmbSource();
        bindAllData();
        viewSourceTable();
        btnSetDefault.disableProperty().bind(Bindings.equal(cmbSetDefaultSource.getSelectionModel().selectedIndexProperty(), -1));

        btnSave.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (validateAllFields()) {
                    if (sourceBEAN.getEnquirySourceId() != null) {
                        int result_update = sourceSERVICE.updateSource(sourceBEAN);
                        if (result_update > 0) {

                            viewSourceTable();
                            clearAllFields();
                            loadcmbSource();
                            Notification notification = Notifications.SUCCESS;
                            TrayNotification trayNotification = new TrayNotification("Source Details are updated", "Source Details updated successfully", notification);
                            trayNotification.showAndDismiss(Duration.seconds(3),txtSourceName);

                        } else {
                            Notification notification = Notifications.ERROR;
                            TrayNotification trayNotification = new TrayNotification("Source Details are not updated", "Source Details are not updated correctly", notification);
                            trayNotification.showAndDismiss(Duration.seconds(3),txtSourceName);

                        }
                    } else {
                        int result_insert = sourceSERVICE.insertSource(sourceBEAN);
                        if (result_insert > 0) {
                            viewSourceTable();
                            clearAllFields();
                            loadcmbSource();
                            Notification notification = Notifications.SUCCESS;
                            TrayNotification trayNotification = new TrayNotification("Source details saved", "Source details saved successfully", notification);
                            trayNotification.showAndDismiss(Duration.seconds(3),txtSourceName);

                        } else {
                            Notification notification = Notifications.ERROR;
                            TrayNotification trayNotification = new TrayNotification("Source details not saved", "Source details not saved correctly", notification);
                            trayNotification.showAndDismiss(Duration.seconds(3),txtSourceName);

                        }
                    }

                }
            }

        });

        cmbSetDefaultSource.valueProperty().addListener(new ChangeListener<SourcePOJO>() {

            @Override
            public void changed(ObservableValue<? extends SourcePOJO> observable, SourcePOJO oldValue, SourcePOJO newValue) {
                if (newValue != null) {
                    sourceBEAN.setEnquirySourceId(newValue.getSourceId());
                    sourceBEAN.setSourceType("default");
                }
            }
        });

        btnSetDefault.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                int result_setsource = sourceSERVICE.updateSourceType(sourceBEAN);
                if (result_setsource > 0) {
                    viewSourceTable();
                    clearAllFields();
                    loadcmbSource();
                    Notification notification = Notifications.SUCCESS;
                    TrayNotification trayNotification = new TrayNotification("Set default source", "Set default source successfully", notification);
                    trayNotification.showAndDismiss(Duration.seconds(3),txtSourceName);

                } else {
                    Notification notification = Notifications.ERROR;
                    TrayNotification trayNotification = new TrayNotification("Cannot set deafault source", "Cannot set default source correctly", notification);
                    trayNotification.showAndDismiss(Duration.seconds(3),txtSourceName);

                }

            }
        });

        tblSources.setRowFactory(new Callback<TableView<SourceBEAN>, TableRow<SourceBEAN>>() {

            @Override
            public TableRow<SourceBEAN> call(TableView<SourceBEAN> param) {
                final TableRow<SourceBEAN> row = new TableRow<SourceBEAN>() {
                    @Override
                    protected void updateItem(SourceBEAN sourceBEAN, boolean empty) {
                        super.updateItem(sourceBEAN, empty);
                        if (sourceBEAN != null) {

                        }
                    }

                };
                row.setOnMouseClicked(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        if (event.getButton() == MouseButton.PRIMARY) {
                            if (row.getItem() != null) {
                                sourceBEAN = new SourceBEAN();
                                sourceBEAN.enquirySourceIdProperty().addListener(new ChangeListener<String>() {

                                    @Override
                                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                                        if (newValue != null) {
                                            btnSave.setText("Update");
                                        } else {
                                            btnSave.setText("Save");
                                        }
                                    }
                                });

                                BeanUtils.copyProperties(row.getItem(), sourceBEAN);
                                bindAllData();

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
        txtSourceName.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    if (!(newValue.matches("[a-zA-Z,\\s]*"))) {
                        Bindings.unbindBidirectional(sourceBEAN.sourceNameProperty(), txtSourceName.textProperty());
                        txtSourceName.setText(oldValue);
                        Bindings.bindBidirectional(sourceBEAN.sourceNameProperty(), txtSourceName.textProperty());
                    } else {
                        Bindings.unbindBidirectional(sourceBEAN.sourceNameProperty(), txtSourceName.textProperty());
                        txtSourceName.setText(newValue);
                        Bindings.bindBidirectional(sourceBEAN.sourceNameProperty(), txtSourceName.textProperty());
                    }

                }
            }

        });

    }

    /**
     *
     */
    public void bindAllData() {

        Bindings.bindBidirectional(txtSourceName.textProperty(), sourceBEAN.sourceNameProperty());

    }

    /**
     *
     */
    public void viewSourceTable() {
        sourceTableList.clear();
        sourceTableList = sourceSERVICE.retrieveSources();
        addSourcesIntoTable(sourceTableList);
    }

    /**
     *
     * @param loginTableList
     */
    public void addSourcesIntoTable(ObservableList<SourceBEAN> loginTableList) {
        colSourceName.setCellValueFactory(new PropertyValueFactory<>("sourceName"));
        colSourceType.setCellValueFactory(new PropertyValueFactory<>("sourceType"));

        ObservableList<SourceBEAN> sourceBEANs = FXCollections.observableList(loginTableList);
        FilteredList<SourceBEAN> filteredData = new FilteredList<SourceBEAN>(sourceBEANs, source -> true);
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(source -> {
                // If filter text is empty, display all events.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare all records with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (source.getSourceName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (source.getSourceType().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else {
                    return false;
                } // Does not match.
            });
        });

        SortedList<SourceBEAN> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tblSources.comparatorProperty());
        tblSources.setItems(sortedData);
    }

    /**
     *
     */
    public void clearAllFields() {
        btnSave.setText("Save");
        cmbSetDefaultSource.getSelectionModel().clearSelection();
        sourceBEAN = new SourceBEAN();
        bindAllData();
        sourceBEAN.enquirySourceIdProperty().addListener(new ChangeListener<String>() {

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

    }

    /**
     *
     * @return
     */
    public boolean validateAllFields() {
        boolean flag = true;
        if (sourceBEAN.getSourceName() == null || sourceBEAN.getSourceName().equals("")) {
            showPopupMessages.showError("Enter source", txtSourceName);
            flag = false;
        }
        return flag;
    }

    /**
     *
     */
    public void loadcmbSource() {
        cmbSetDefaultSource.getItems().clear();
        sourcesList = sourceSERVICE.retrieveSourcesOnly();
        cmbSetDefaultSource.setItems(sourcesList);
    }

    private void autoCompletion() {
        List<String> allSourceNames = SourceIMPL.getAllSources();
        txtSourceName = TextFields.createClearableTextField();
        AutoCompletionBinding<String> autoCompletionBindingNumber = TextFields.bindAutoCompletion(txtSourceName, allSourceNames);
        txtSourceName.setPrefWidth(218);
        hboxSourceName.getChildren().remove(1);
        hboxSourceName.getChildren().add(txtSourceName);
    }

}
