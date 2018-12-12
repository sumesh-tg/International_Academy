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
package com.zs.ina.admin.master.enquirystatus;

import com.zs.ina.admin.master.enquirystatus.dao.EnquiryStatusBEAN;
import com.zs.ina.admin.master.enquirystatus.dao.EnquiryStatusIMPL;
import com.zs.ina.admin.master.enquirystatus.dao.EnquiryStatusSERVICE;
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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.HTMLEditor;
import javafx.util.Callback;
import javafx.util.Duration;
import org.springframework.beans.BeanUtils;

/**
 * FXML Controller class
 *
 * @author 100035
 */
public class FXMLEnquiryStatusController implements Initializable {
    
    EnquiryStatusBEAN enquiryStatusBEAN = new EnquiryStatusBEAN();
    EnquiryStatusSERVICE enquiryStatusSERVICE = new EnquiryStatusSERVICE(new EnquiryStatusIMPL());
    
    @FXML
    private ToggleGroup grpDateAndReason;
    @FXML
    private ToggleGroup grpEnable;
    @FXML
    private HTMLEditor htmlEmailTemplate;
    @FXML
    private TextField txtStatus;
    @FXML
    private TableView<EnquiryStatusBEAN> tblStatus;
    @FXML
    private TextField txtSearch;
    @FXML
    private Button btnSave;
    @FXML
    private TextField txtSubject;
    @FXML
    private TableColumn<?, ?> colStatus;
    @FXML
    private TableColumn<?, ?> colEnable;
    @FXML
    private RadioButton rdDateReasonYes;
    @FXML
    private RadioButton rdDateReasonNo;
    @FXML
    private RadioButton rdEnableYes;
    @FXML
    private RadioButton rdEnableNo;
    @FXML
    private Button btnClear;
    @FXML
    private Hyperlink hypName;
    @FXML
    private Hyperlink hypPhone;
    @FXML
    private Hyperlink hypEmail;
    @FXML
    private Hyperlink hypProgram;
    @FXML
    private TextField txtFromMail;
    
    ObservableList<EnquiryStatusBEAN> statusTableList = FXCollections.observableArrayList();
    ShowPopupMessages showPopupMessages = new ShowPopupMessages();
    @FXML
    private ComboBox<EnquiryStatusBEAN> cmbEnquiryStatus;
    @FXML
    private Button btnSetProComplete;
    
    /**
     *
     */
    public void viewStatusTable() {
        statusTableList.clear();
        statusTableList = enquiryStatusSERVICE.retrieveStatusValues();
        addStatusIntoTable(statusTableList);
        
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bindAllData();
        viewStatusTable();
        cmbEnquiryStatus.setItems(statusTableList);
        for (EnquiryStatusBEAN esbean : cmbEnquiryStatus.getItems()) {
            if (esbean.getProcessCompletion() != null) {
                if (esbean.getProcessCompletion().equalsIgnoreCase("1")) {
                    cmbEnquiryStatus.getSelectionModel().select(esbean);
                }
            }
        }
        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                enquiryStatusBEAN.setEmailBody(htmlEmailTemplate.getHtmlText());
                if (validateAllFields()) {
                    
                    if (btnSave.getText().equals("Save")) {
                        System.out.println("app_status in contrlr : " + enquiryStatusBEAN.getAppStatus());
                        int result = enquiryStatusSERVICE.insertEnquiryStatus(enquiryStatusBEAN);
                        if (result > 0) {
                            viewStatusTable();
                            Notification notification = Notifications.SUCCESS;
                            TrayNotification trayNotification = new TrayNotification("New status added", "New status added successfully", notification);
                            trayNotification.showAndDismiss(Duration.seconds(3), txtStatus);
                            clearAllFields();
                        } else {
                            Notification noticication = Notifications.ERROR;
                            TrayNotification trayNotification = new TrayNotification("New status not added", "New status not added successfully", noticication);
                            trayNotification.showAndDismiss(Duration.seconds(3), txtStatus);
                        }
                        
                    }
                    
                    if (btnSave.getText().equals("Update")) {
                        
                        int result = enquiryStatusSERVICE.updateStatusDetails(enquiryStatusBEAN);
                        if (result > 0) {
                            viewStatusTable();
                            Notification notification = Notifications.SUCCESS;
                            TrayNotification trayNotification = new TrayNotification("Status updated", "Status updated successfully", notification);
                            trayNotification.showAndDismiss(Duration.seconds(3), txtStatus);
                            clearAllFields();
                            btnSave.setText("Save");
                        } else {
                            Notification noticication = Notifications.ERROR;
                            TrayNotification trayNotification = new TrayNotification("Status not updated", "Status updated unsuccessfully", noticication);
                            trayNotification.showAndDismiss(Duration.seconds(3), txtStatus);
                        }
                        
                    }
                    bindAllData();
                    enquiryStatusBEAN = new EnquiryStatusBEAN();
                    
                }
            }
            
            private boolean validateAllFields() {
                boolean flag = true;
                if (enquiryStatusBEAN.getAppStatus() == null || enquiryStatusBEAN.getAppStatus().equals("")) {
                    showPopupMessages.showError("Enter status", txtStatus);
                    flag = false;
                } else if (enquiryStatusBEAN.getFromMail() == null || enquiryStatusBEAN.getFromMail().equals("")) {
                    showPopupMessages.showError("Enter sender mail address", txtFromMail);
                    flag = false;
                } else if (enquiryStatusBEAN.getEmailSubject() == null || enquiryStatusBEAN.getEmailSubject().equals("")) {
                    showPopupMessages.showError("Enter mail subject", txtSubject);
                    flag = false;
                }
//                else if (enquiryStatusBEAN.getEmailBody() == null || enquiryStatusBEAN.getEmailBody().equals("")) {
//                    showPopupMessages.showError("Enter details to send mail", htmlEmailTemplate);
//                    flag = false;
//                }

                return flag;
                
            }
            
        });
        grpDateAndReason.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                
                if (newValue != null) {
                    RadioButton checkedOption = (RadioButton) newValue.getToggleGroup().getSelectedToggle(); // Cast object to radio button
                    if (checkedOption.getText().equals("YES")) {
                        enquiryStatusBEAN.setDateReasonEnable("1");
                    }
                    if (checkedOption.getText().equals("NO")) {
                        enquiryStatusBEAN.setDateReasonEnable("0");
                    }
                    
                }
            }
        });
        grpEnable.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                
                if (newValue != null) {
                    RadioButton checkedOption = (RadioButton) newValue.getToggleGroup().getSelectedToggle(); // Cast object to radio button
                    if (checkedOption.getText().equals("YES")) {
                        enquiryStatusBEAN.setEnable("1");
                    }
                    if (checkedOption.getText().equals("NO")) {
                        enquiryStatusBEAN.setEnable("0");
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
        /* ============= hyperlink click events =========== */

//        System.out.println("htmlEmailTemplate : " + htmlEmailTemplate.getChildrenUnmodifiable().toString());
//        ObservableList<Node> node = htmlEmailTemplate.getChildrenUnmodifiable();
//        for (Node n : node) {
//            GridPane p = (GridPane) n;
//            System.out.println("test ::" + p.getChildren().toString());
//        }
        hypName.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                htmlEmailTemplate.setHtmlText(htmlEmailTemplate.getHtmlText() + hypName.getText());
            }
        });
        hypPhone.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                htmlEmailTemplate.setHtmlText(htmlEmailTemplate.getHtmlText() + hypPhone.getText());
            }
        });
        hypEmail.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                htmlEmailTemplate.setHtmlText(htmlEmailTemplate.getHtmlText() + hypEmail.getText());
            }
        });
        hypProgram.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                htmlEmailTemplate.setHtmlText(htmlEmailTemplate.getHtmlText() + hypProgram.getText());
            }
        });
        tblStatus.setRowFactory(new Callback<TableView<EnquiryStatusBEAN>, TableRow<EnquiryStatusBEAN>>() {
            
            @Override
            public TableRow<EnquiryStatusBEAN> call(TableView<EnquiryStatusBEAN> param) {
                final TableRow<EnquiryStatusBEAN> row = new TableRow<EnquiryStatusBEAN>() {
                    @Override
                    protected void updateItem(EnquiryStatusBEAN enquiryStatusBEAN, boolean empty) {
                        super.updateItem(enquiryStatusBEAN, empty);
                        
                        if (enquiryStatusBEAN != null) {
//                        
                            System.out.println("inside row factory");
                            
                        }
                    }
                    
                };
                row.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    
                    @Override
                    public void handle(MouseEvent event) {
                        if (event.getButton() == MouseButton.PRIMARY) {
                            btnSave.setText("Update");
                            viewStatusDetails(row.getItem());
                            
                        }
                    }
                    
                    private void viewStatusDetails(EnquiryStatusBEAN enquiryStatusBEANS) {
                        
                        BeanUtils.copyProperties(enquiryStatusBEANS, enquiryStatusBEAN);
                        if (enquiryStatusBEAN.getEnable().equals("YES")) {
                            rdEnableYes.setSelected(true);
                            enquiryStatusBEAN.setEnable("1");
                        }
                        if (enquiryStatusBEAN.getEnable().equals("NO")) {
                            rdEnableNo.setSelected(true);
                            enquiryStatusBEAN.setEnable("0");
                        }
                        if (enquiryStatusBEAN.getDateReasonEnable().equals("YES")) {
                            rdDateReasonYes.setSelected(true);
                            enquiryStatusBEAN.setDateReasonEnable("1");
                        }
                        if (enquiryStatusBEAN.getDateReasonEnable().equals("NO")) {
                            rdDateReasonNo.setSelected(true);
                            enquiryStatusBEAN.setDateReasonEnable("0");
                        }
                        txtStatus.setText(enquiryStatusBEAN.getAppStatus());
                        txtFromMail.setText(enquiryStatusBEAN.getFromMail());
                        txtSubject.setText(enquiryStatusBEAN.getEmailSubject());
                        htmlEmailTemplate.setHtmlText(enquiryStatusBEAN.getEmailBody());
                        bindAllData();
                        
                    }
                });
                return row;
            }
            
        });

        /* ======================== Set Process Completion Status ==================== */
        btnSetProComplete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (cmbEnquiryStatus.getSelectionModel().getSelectedIndex() > -1) {
                    enquiryStatusSERVICE.setProcessCompletionStatus(cmbEnquiryStatus.getSelectionModel().getSelectedItem());
                }
            }
        });
        
    }
    
    private void addStatusIntoTable(ObservableList<EnquiryStatusBEAN> statusTableList) {
        colStatus.setCellValueFactory(new PropertyValueFactory<>("appStatus"));
        colEnable.setCellValueFactory(new PropertyValueFactory<>("enable"));
        ObservableList<EnquiryStatusBEAN> enquiryStatusBEANs = FXCollections.observableList(statusTableList);
        FilteredList<EnquiryStatusBEAN> filteredData = new FilteredList<EnquiryStatusBEAN>(enquiryStatusBEANs, status -> true);
        
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(status -> {
                // If filter text is empty, display all events.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare all records with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (status.getAppStatus().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else {
                    return false;
                } // Does not match.
            });
        });
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<EnquiryStatusBEAN> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tblStatus.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tblStatus.setItems(sortedData);
    }
    
    /**
     *
     */
    public void bindAllData() {
        Bindings.bindBidirectional(txtStatus.textProperty(), enquiryStatusBEAN.appStatusProperty());
        Bindings.bindBidirectional(txtSubject.textProperty(), enquiryStatusBEAN.emailSubjectProperty());
        Bindings.bindBidirectional(txtFromMail.textProperty(), enquiryStatusBEAN.fromMailProperty());
        //  Bindings.bindBidirectional(htmlEmailTemplate.accessibleTextProperty(), enquiryStatusBEAN.emailBodyProperty());
    }
    
    /**
     *
     */
    public void clearAllFields() {
        System.out.println("clear all fields");
        tblStatus.getSelectionModel().clearSelection();
        htmlEmailTemplate.setHtmlText("");
        if (enquiryStatusBEAN.dateReasonEnableProperty().getValue().equals("1")) {
            rdDateReasonYes.setSelected(false);
        }
        if (enquiryStatusBEAN.dateReasonEnableProperty().getValue().equals("0")) {
            rdDateReasonNo.setSelected(false);
        }
        if (enquiryStatusBEAN.enableProperty().getValue().equals("1")) {
            rdEnableYes.setSelected(false);
        }
        if (enquiryStatusBEAN.enableProperty().getValue().equals("0")) {
            rdEnableNo.setSelected(false);
        }
        enquiryStatusBEAN = new EnquiryStatusBEAN();
        bindAllData();
        
    }
    
}
