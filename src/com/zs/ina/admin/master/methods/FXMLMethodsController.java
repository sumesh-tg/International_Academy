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
package com.zs.ina.admin.master.methods;

import com.zs.ina.admin.master.methods.dao.MethodBEAN;
import com.zs.ina.admin.master.methods.dao.MethodIMPL;
import com.zs.ina.admin.master.methods.dao.MethodPOJO;
import com.zs.ina.admin.master.methods.dao.MethodSERVICE;
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
public class FXMLMethodsController implements Initializable {

    @FXML
    private TextField txtSearch;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnDefault;
    @FXML
    private TableView<MethodBEAN> tblMethods;
    @FXML
    private TextField txtEnquiryMethod;
    @FXML
    private ComboBox<MethodPOJO> cmbDefaultMethod;
    @FXML
    private Button btnClear;
    @FXML
    private TableColumn<?, ?> colMethod;
    @FXML
    private TableColumn<?, ?> colMethodType;

    MethodBEAN methodBEAN = new MethodBEAN();
    MethodSERVICE methodSERVICE = new MethodSERVICE(new MethodIMPL());
    Logger logger = Logger.getLogger(FXMLMethodsController.class);
    ObservableList<MethodBEAN> methodTableList = FXCollections.observableArrayList();
    ObservableList<MethodPOJO> methodsList = FXCollections.observableArrayList();
    ShowPopupMessages showPopupMessages = new ShowPopupMessages();
    @FXML
    private HBox hboxEnquiryMethod;

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
        viewMethodTable();
        btnDefault.disableProperty().bind(Bindings.equal(cmbDefaultMethod.getSelectionModel().selectedIndexProperty(), -1));

        btnSave.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (validateAllFields()) {
                    if (methodBEAN.getMethodId() != null) {
                        int result_update = methodSERVICE.updateMethod(methodBEAN);
                        if (result_update > 0) {
                            clearAllFields();
                            viewMethodTable();

                            loadcmbSource();
                            Notification notification = Notifications.SUCCESS;
                            TrayNotification trayNotification = new TrayNotification("Method Details are updated", "Method Details updated successfully", notification);
                            trayNotification.showAndDismiss(Duration.seconds(3),txtEnquiryMethod);

                        } else {
                            Notification notification = Notifications.ERROR;
                            TrayNotification trayNotification = new TrayNotification("Method Details are not updated", "Method Details are not updated correctly", notification);
                            trayNotification.showAndDismiss(Duration.seconds(3),txtEnquiryMethod);

                        }
                    } else {
                        int result_insert = methodSERVICE.insertMethod(methodBEAN);
                        if (result_insert > 0) {
                            viewMethodTable();
                            clearAllFields();
                            loadcmbSource();
                            Notification notification = Notifications.SUCCESS;
                            TrayNotification trayNotification = new TrayNotification("Method details saved", "Method details saved successfully", notification);
                            trayNotification.showAndDismiss(Duration.seconds(3),txtEnquiryMethod);

                        } else {
                            Notification notification = Notifications.ERROR;
                            TrayNotification trayNotification = new TrayNotification("Method details not saved", "Method details not saved correctly", notification);
                            trayNotification.showAndDismiss(Duration.seconds(3),txtEnquiryMethod);

                        }
                    }

                }
            }

        });

        cmbDefaultMethod.valueProperty().addListener(new ChangeListener<MethodPOJO>() {

            @Override
            public void changed(ObservableValue<? extends MethodPOJO> observable, MethodPOJO oldValue, MethodPOJO newValue) {
                if (newValue != null) {
                    methodBEAN.setMethodId(newValue.getMethodId());
                    methodBEAN.setMethodType("default");
                }
            }
        });
        btnClear.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                clearAllFields();

            }
        });

        btnDefault.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                int result_setsource = methodSERVICE.updateMethodType(methodBEAN);
                if (result_setsource > 0) {
                    viewMethodTable();
                    clearAllFields();
                    loadcmbSource();
                    Notification notification = Notifications.SUCCESS;
                    TrayNotification trayNotification = new TrayNotification("Set default method", "Set default method successfully", notification);
                    trayNotification.showAndDismiss(Duration.seconds(3),txtEnquiryMethod);

                } else {
                    Notification notification = Notifications.ERROR;
                    TrayNotification trayNotification = new TrayNotification("Cannot set deafault method", "Cannot set default method correctly", notification);
                    trayNotification.showAndDismiss(Duration.seconds(3),txtEnquiryMethod);

                }

            }
        });

        tblMethods.setRowFactory(new Callback<TableView<MethodBEAN>, TableRow<MethodBEAN>>() {

            @Override
            public TableRow<MethodBEAN> call(TableView<MethodBEAN> param) {
                final TableRow<MethodBEAN> row = new TableRow<MethodBEAN>() {
                    @Override
                    protected void updateItem(MethodBEAN methodBEAN, boolean empty) {
                        super.updateItem(methodBEAN, empty);
                        if (methodBEAN != null) {

                        }
                    }

                };
                row.setOnMouseClicked(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        if (event.getButton() == MouseButton.PRIMARY) {
                            if (row.getItem() != null) {
                                methodBEAN = new MethodBEAN();
                                methodBEAN.methodIdProperty().addListener(new ChangeListener<String>() {

                                    @Override
                                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                                        if (newValue != null) {
                                            btnSave.setText("Update");
                                        } else {
                                            btnSave.setText("Save");
                                        }
                                    }
                                });

                                BeanUtils.copyProperties(row.getItem(), methodBEAN);
                                bindAllData();
                            }

                        }
                    }

                });
                return row;
            }

        });
        txtEnquiryMethod.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    if (!(newValue.matches("[a-zA-Z,\\s]*"))) {
                        Bindings.unbindBidirectional(methodBEAN.methodNameProperty(), txtEnquiryMethod.textProperty());
                        txtEnquiryMethod.setText(oldValue);
                        Bindings.bindBidirectional(methodBEAN.methodNameProperty(), txtEnquiryMethod.textProperty());
                    } else {
                        Bindings.unbindBidirectional(methodBEAN.methodNameProperty(), txtEnquiryMethod.textProperty());
                        txtEnquiryMethod.setText(newValue);
                        Bindings.bindBidirectional(methodBEAN.methodNameProperty(), txtEnquiryMethod.textProperty());
                    }

                }
            }

        });
    }

    /**
     *
     */
    public void bindAllData() {

        Bindings.bindBidirectional(txtEnquiryMethod.textProperty(), methodBEAN.methodNameProperty());

    }

    /**
     *
     */
    public void viewMethodTable() {
        methodTableList.clear();
        methodTableList = methodSERVICE.retrieveMethods();
        addMethodsIntoTable(methodTableList);
    }

    /**
     *
     * @param methodTableList
     */
    public void addMethodsIntoTable(ObservableList<MethodBEAN> methodTableList) {
        colMethod.setCellValueFactory(new PropertyValueFactory<>("methodName"));
        colMethodType.setCellValueFactory(new PropertyValueFactory<>("methodType"));

        ObservableList<MethodBEAN> methodBEANs = FXCollections.observableList(methodTableList);
        FilteredList<MethodBEAN> filteredData = new FilteredList<MethodBEAN>(methodBEANs, method -> true);
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(method -> {
                // If filter text is empty, display all events.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare all records with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (method.getMethodName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (method.getMethodType().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else {
                    return false;
                } // Does not match.
            });
        });

        SortedList<MethodBEAN> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tblMethods.comparatorProperty());
        tblMethods.setItems(sortedData);
    }

    /**
     *
     */
    public void clearAllFields() {
        btnSave.setText("Save");
        cmbDefaultMethod.getSelectionModel().clearSelection();
        methodBEAN = new MethodBEAN();
        bindAllData();
        methodBEAN.methodIdProperty().addListener(new ChangeListener<String>() {

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
        if (methodBEAN.getMethodName() == null || methodBEAN.getMethodName().equals("")) {
            showPopupMessages.showError("Enter enquiry method", txtEnquiryMethod);
            flag = false;
        }
        return flag;
    }

    /**
     *
     */
    public void loadcmbSource() {
        cmbDefaultMethod.getItems().clear();
        methodsList = methodSERVICE.retrieveMethodsOnly();
        cmbDefaultMethod.setItems(methodsList);
    }

    /**
     *
     */
    public void autoCompletion() {
        List<String> allMethodsNames = MethodIMPL.getAllEnquiryMethods();
        txtEnquiryMethod = TextFields.createClearableTextField();
        AutoCompletionBinding<String> autoCompletionBindingNumber = TextFields.bindAutoCompletion(txtEnquiryMethod, allMethodsNames);
        txtEnquiryMethod.setPrefWidth(218);
        hboxEnquiryMethod.getChildren().remove(1);
        hboxEnquiryMethod.getChildren().add(txtEnquiryMethod);
    }

}
