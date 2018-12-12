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
package com.zs.ina.admin.master.relationship;

import com.zs.ina.admin.master.relationship.dao.RelationBEAN;
import com.zs.ina.admin.master.relationship.dao.RelationIMPL;
import com.zs.ina.admin.master.relationship.dao.RelationSERVICE;
import com.zs.ina.admin.master.retrieve.RetrieveStaticMasterDAO;
import com.zs.ina.admin.master.stdisdcodes.dao.StdCodesService;
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
public class FXMLRelationshipController implements Initializable {

    RelationBEAN relationBEAN = new RelationBEAN();
    RelationSERVICE relationSERVICE = new RelationSERVICE(new RelationIMPL());
    Logger logger = Logger.getLogger(FXMLRelationshipController.class);
    ObservableList<RelationBEAN> relationTableList = FXCollections.observableArrayList();
    ShowPopupMessages showPopupMessages = new ShowPopupMessages();
    ShowPopupMessages popupMessages = new ShowPopupMessages();
    @FXML
    private TextField txtRelationship;
    @FXML
    private TextField txtSearch;
    @FXML
    private TableView<RelationBEAN> tblRelationship;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnClear;
    @FXML
    private HBox hboxRelationship;
    @FXML
    private TableColumn<?, ?> colRelationship;
    @FXML
    private Button btnDelete;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        viewRelationshipTable();
        bindAllData();
        // autoCompletion();
        btnSave.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                if (validateAllFields()) {
                    if (relationBEAN.getRelationshipId() != null) {

                        int result_update = relationSERVICE.updateRelationship(relationBEAN);
                        if (result_update > 0) {
                            clearAllFields();
                            viewRelationshipTable();
                            Notification notification = Notifications.SUCCESS;
                            TrayNotification trayNotification = new TrayNotification("Relationship updated", "Relationship updated successfully", notification);
                            trayNotification.showAndDismiss(Duration.seconds(3), txtRelationship);

                        } else {
                            Notification notification = Notifications.ERROR;
                            TrayNotification trayNotification = new TrayNotification("Relationship not updated", "Relationship not updated correctly", notification);
                            trayNotification.showAndDismiss(Duration.seconds(3), txtRelationship);

                        }

                    } else {

                        int result_insert = relationSERVICE.insertRelationship(relationBEAN);
                        if (result_insert > 0) {
                            clearAllFields();
                            viewRelationshipTable();

                            Notification notification = Notifications.SUCCESS;
                            TrayNotification trayNotification = new TrayNotification("Relationship saved", "Relationship saved successfully", notification);
                            trayNotification.showAndDismiss(Duration.seconds(3), txtRelationship);

                        } else {
                            Notification notification = Notifications.ERROR;
                            TrayNotification trayNotification = new TrayNotification("Relationship not saved", "Relationship saved correctly", notification);
                            trayNotification.showAndDismiss(Duration.seconds(3), txtRelationship);

                        }

                    }

                }

            }

            private boolean validateAllFields() {
                boolean flag = true;
                if (relationBEAN.getRelationship() == null || relationBEAN.getRelationship().equals("")) {
                    showPopupMessages.showError("Enter Relationship", txtRelationship);
                    flag = false;
                }
                return flag;

            }

        });

        txtRelationship.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    if (!(newValue.matches("[a-zA-Z,\\s]*"))) {
                        Bindings.unbindBidirectional(relationBEAN.relationshipProperty(), txtRelationship.textProperty());
                        txtRelationship.setText(oldValue);
                        Bindings.bindBidirectional(relationBEAN.relationshipProperty(), txtRelationship.textProperty());
                    } else {
                        Bindings.unbindBidirectional(relationBEAN.relationshipProperty(), txtRelationship.textProperty());
                        txtRelationship.setText(newValue);
                        Bindings.bindBidirectional(relationBEAN.relationshipProperty(), txtRelationship.textProperty());
                    }

                }
            }

        });
        tblRelationship.setRowFactory(new Callback<TableView<RelationBEAN>, TableRow<RelationBEAN>>() {

            @Override
            public TableRow<RelationBEAN> call(TableView<RelationBEAN> param) {
                final TableRow<RelationBEAN> row = new TableRow<RelationBEAN>() {
                    @Override
                    protected void updateItem(RelationBEAN relationBEAN, boolean empty) {
                        super.updateItem(relationBEAN, empty);
                        if (relationBEAN != null) {

                        }
                    }

                };
                row.setOnMouseClicked(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        if (event.getButton() == MouseButton.PRIMARY) {
                            if (row.getItem() != null) {
                                relationBEAN = new RelationBEAN();
                                relationBEAN.relationshipIdProperty().addListener(new ChangeListener<String>() {

                                    @Override
                                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                                        if (newValue != null) {
                                            btnSave.setText("Update");
                                        } else {
                                            btnSave.setText("Save");
                                        }
                                    }
                                });

                                BeanUtils.copyProperties(row.getItem(), relationBEAN);
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
        
         btnDelete.disableProperty().bind(Bindings.equal(tblRelationship.getSelectionModel().selectedIndexProperty(), -1));
        btnDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (relationBEAN.getRelationshipId()!= null) {
                    int result_delete=relationSERVICE.deleteRelationship(relationBEAN);
                    if(result_delete > 0){
                    viewRelationshipTable();
                    btnClear.fire();
                    popupMessages.showSuccess("Deleted Successfully !", "", btnDelete);
                    }
                }
            }
        });

    }

    private void bindAllData() {
        Bindings.bindBidirectional(txtRelationship.textProperty(), relationBEAN.relationshipProperty());
    }

    private void viewRelationshipTable() {
        relationTableList.clear();
        relationTableList = relationSERVICE.retrieveRelationships();
        addRelationsIntoTable(relationTableList);
    }

    private void addRelationsIntoTable(ObservableList<RelationBEAN> levelTableList) {

        colRelationship.setCellValueFactory(new PropertyValueFactory<>("relationship"));
        ObservableList<RelationBEAN> relationBEANs = FXCollections.observableList(levelTableList);
        FilteredList<RelationBEAN> filteredData = new FilteredList<RelationBEAN>(relationBEANs, relation -> true);
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(relation -> {
                // If filter text is empty, display all events.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare all records with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (relation.getRelationship().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else {
                    return false;
                } // Does not match.
            });
        });

        SortedList<RelationBEAN> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tblRelationship.comparatorProperty());
        tblRelationship.setItems(sortedData);
    }

    private void clearAllFields() {

        btnSave.setText("Save");
        relationBEAN = new RelationBEAN();
        bindAllData();
        relationBEAN.relationshipIdProperty().addListener(new ChangeListener<String>() {

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

    private void autoCompletion() {

        List<String> allRelations = RetrieveStaticMasterDAO.getAllRelations();
        txtRelationship = TextFields.createClearableTextField();
        AutoCompletionBinding<String> autoCompletionBindingNumber = TextFields.bindAutoCompletion(txtRelationship, allRelations);
        txtRelationship.setPrefWidth(218);
        hboxRelationship.getChildren().remove(1);
        hboxRelationship.getChildren().add(txtRelationship);
    }
}
