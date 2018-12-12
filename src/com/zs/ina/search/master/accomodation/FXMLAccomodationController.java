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
package com.zs.ina.search.master.accomodation;

import com.zs.ina.search.master.accomodation.dao.AccomodationBEAN;
import com.zs.ina.search.master.accomodation.dao.AccomodationDAO;
import com.zs.ina.search.master.accomodation.dao.AccomodationIMPL;
import com.zs.ina.common.error.ShowPopupMessages;
import com.zs.ina.context.Context;
import com.zs.traynotification.notification.Notification;
import com.zs.traynotification.notification.Notifications;
import com.zs.traynotification.notification.TrayNotification;
import java.net.URL;
import java.util.ArrayList;
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
import org.springframework.context.ApplicationContext;

/**
 * FXML Controller class
 *
 * @author 100035
 */
public class FXMLAccomodationController implements Initializable {

    @FXML
    private TableView<AccomodationBEAN> tblAccomodation;
    @FXML
    private HBox hboxAccomodation;
    @FXML
    private TextField txtAccomodation;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnClear;
    @FXML
    private TableColumn<?, ?> colAccomodation;
    @FXML
    private TextField txtSearch;
    @FXML
    private Button btnDelete;

    ApplicationContext springAppContext = Context.getInstance().currentProfile().getSpringContext();
    AccomodationDAO accomodationDAO = springAppContext.getBean(AccomodationDAO.class);
    AccomodationBEAN accomodationBEAN = new AccomodationBEAN();
    Logger logger = Logger.getLogger(FXMLAccomodationController.class);
    ShowPopupMessages showErrors = new ShowPopupMessages();
    List<AccomodationBEAN> listAccomodationsTable = new ArrayList<>();

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        autoCompletion();
        bindAllData();
        viewAccomodationTable();
        btnDelete.disableProperty().bind(Bindings.equal(tblAccomodation.getSelectionModel().selectedIndexProperty(), -1));

        btnSave.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (validateAllFields()) {
                    if (accomodationBEAN.getAccomodationId() != null) {
                        int result_update = accomodationDAO.updateAcccomodation(accomodationBEAN);
                        if (result_update > 0) {

                            viewAccomodationTable();
                            clearAllFields();
                            Notification notification = Notifications.SUCCESS;
                            TrayNotification trayNotification = new TrayNotification("Accomodation Details are updated", "Accomodation Details updated successfully", notification);
                            trayNotification.showAndDismiss(Duration.seconds(3), txtAccomodation);

                        } else {
                            Notification notification = Notifications.ERROR;
                            TrayNotification trayNotification = new TrayNotification("Accomodation Details are not updated", "Accomodation Details are not updated correctly", notification);
                            trayNotification.showAndDismiss(Duration.seconds(3), txtAccomodation);

                        }
                    } else {
                        int result_insert = accomodationDAO.insertAcccomodation(accomodationBEAN);
                        if (result_insert > 0) {
                            viewAccomodationTable();
                            clearAllFields();
                            Notification notification = Notifications.SUCCESS;
                            TrayNotification trayNotification = new TrayNotification("Accomodation details saved", "Accomodation details saved successfully", notification);
                            trayNotification.showAndDismiss(Duration.seconds(3), txtAccomodation);

                        } else {
                            Notification notification = Notifications.ERROR;
                            TrayNotification trayNotification = new TrayNotification("Accomodation details not saved", "Accomodation details not saved correctly", notification);
                            trayNotification.showAndDismiss(Duration.seconds(3), txtAccomodation);

                        }
                    }

                }
            }

        });
         btnDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (accomodationBEAN.getAccomodationId()!= null) {
                    int result_delete = accomodationDAO.deleteAccomodation(accomodationBEAN);
                    if (result_delete > 0) {
                        viewAccomodationTable();
                        clearAllFields();
                        showErrors.showSuccess("Deleted Successfully !", "Accomodation Deleted Successfully !", btnSave);
                    } else {
                        showErrors.showError("Deletion Failed ! Try again !", btnSave);
                    }

                }
            }
        });
        tblAccomodation.setRowFactory(new Callback<TableView<AccomodationBEAN>, TableRow<AccomodationBEAN>>() {

            @Override
            public TableRow<AccomodationBEAN> call(TableView<AccomodationBEAN> param) {
                final TableRow<AccomodationBEAN> row = new TableRow<AccomodationBEAN>() {
                    @Override
                    protected void updateItem(AccomodationBEAN accomodationBEAN, boolean empty) {
                        super.updateItem(accomodationBEAN, empty);
                        if (accomodationBEAN != null) {

                        }
                    }

                };
                row.setOnMouseClicked(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        if (event.getButton() == MouseButton.PRIMARY) {
                            if (row.getItem() != null) {
                                accomodationBEAN = new AccomodationBEAN();
                                accomodationBEAN.accomodationIdProperty().addListener(new ChangeListener<String>() {

                                    @Override
                                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                                        if (newValue != null) {
                                            btnSave.setText("Update");
                                        } else {
                                            btnSave.setText("Save");
                                        }
                                    }

                                });
                                BeanUtils.copyProperties(row.getItem(), accomodationBEAN);
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
        txtAccomodation.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    if (!(newValue.matches("[a-zA-Z,\\s]*"))) {
                        Bindings.unbindBidirectional(accomodationBEAN.accomodationProperty(), txtAccomodation.textProperty());
                        txtAccomodation.setText(oldValue);
                        Bindings.bindBidirectional(accomodationBEAN.accomodationProperty(), txtAccomodation.textProperty());
                    } else {
                        Bindings.unbindBidirectional(accomodationBEAN.accomodationProperty(), txtAccomodation.textProperty());
                        txtAccomodation.setText(newValue);
                        Bindings.bindBidirectional(accomodationBEAN.accomodationProperty(), txtAccomodation.textProperty());
                    }

                }
            }

        });

    }

    /**
     *
     */
    public void bindAllData() {

        Bindings.bindBidirectional(txtAccomodation.textProperty(), accomodationBEAN.accomodationProperty());

    }

    /**
     *
     */
    public void viewAccomodationTable() {
        listAccomodationsTable.clear();
        listAccomodationsTable = accomodationDAO.retrieveAcccomodation();
        addAccomodationsIntoTable(listAccomodationsTable);
    }

    /**
     *
     * @param listAccomodationsTable
     */
    public void addAccomodationsIntoTable(List<AccomodationBEAN> listAccomodationsTable) {
        colAccomodation.setCellValueFactory(new PropertyValueFactory<>("accomodation"));

        ObservableList<AccomodationBEAN> accomodationBEANs = FXCollections.observableList(listAccomodationsTable);
        FilteredList<AccomodationBEAN> filteredData = new FilteredList<AccomodationBEAN>(accomodationBEANs, accomodations -> true);
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(accomodations -> {
                // If filter text is empty, display all events.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Compare all records with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (accomodations.getAccomodation().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else {
                    return false;
                } // Does not match.
            });
        });
        SortedList<AccomodationBEAN> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tblAccomodation.comparatorProperty());
        tblAccomodation.setItems(sortedData);
    }

    /**
     *
     */
    public void clearAllFields() {
        btnSave.setText("Save");
        accomodationBEAN = new AccomodationBEAN();
        bindAllData();
        accomodationBEAN.accomodationIdProperty().addListener(new ChangeListener<String>() {

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
        if (accomodationBEAN.getAccomodation() == null || accomodationBEAN.getAccomodation().equals("")) {
            showErrors.showError("Enter accomodation", txtAccomodation);
            flag = false;
        }
        return flag;
    }

    /**
     *
     */
    public void autoCompletion() {
        List<String> allSourceNames = AccomodationIMPL.getAllAccomodations();
        txtAccomodation = TextFields.createClearableTextField();
        AutoCompletionBinding<String> autoCompletionBindingNumber = TextFields.bindAutoCompletion(txtAccomodation, allSourceNames);
        txtAccomodation.setPrefWidth(218);
        hboxAccomodation.getChildren().remove(1);
        hboxAccomodation.getChildren().add(txtAccomodation);
    }
}
