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
package com.zs.ina.task.log;

import com.zs.ina.common.error.ShowPopupMessages;
import com.zs.ina.context.Context;
import com.zs.ina.counselor.dao.model.CounselorDetailsBEAN;
import com.zs.ina.enquiry.dao.EnquiryDetailsDAO;
import com.zs.ina.login.INALoginForm;
import com.zs.ina.task.dao.TasksBEAN;
import com.zs.ina.task.dao.TasksDAO;
import com.zs.ina.task.dao.TasksIMPL;
import com.zs.traynotification.notification.Notification;
import com.zs.traynotification.notification.Notifications;
import com.zs.traynotification.notification.TrayNotification;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import org.controlsfx.control.PopOver;

/**
 * FXML Controller class
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class FXMLTasksLogController implements Initializable {

    @FXML
    private ListView<TasksBEAN> listViewLog;
    TasksDAO tasksDAO = new TasksIMPL();
    private String CUR_USERNAME = null;
    private String CUR_BRANCH = null;
    @FXML
    private Label lblTaskName;
    @FXML
    private Label lblTaskNo;
    @FXML
    private Label lblTaskCreatedDate;
    @FXML
    private TextField txtSearch;
    @FXML
    private Label lblTaskDescription;
    @FXML
    private ComboBox<String> cmbTaskStatus;
    @FXML
    private TextField txtRemarks;
    @FXML
    private Button btnAddRemark;
    @FXML
    private Label lblTaskCreatedBy;
    @FXML
    private ImageView imgViewClose;
    @FXML
    private Label lblEnquiryInfo;
    ShowPopupMessages showPopupMessages = new ShowPopupMessages();

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    /**
     *
     * @param tasksBEAN
     */
    public void initData(TasksBEAN tasksBEAN) {
        CUR_USERNAME = Context.getInstance().currentProfile().getProfilePOJO().getUsername();
        CUR_BRANCH = Context.getInstance().currentProfile().getProfilePOJO().getBranch();
        /* ======================== Load All Log ==================== */
        ObservableList<TasksBEAN> listTaskLogs = tasksDAO.retrieveTaskLogById(CUR_USERNAME, CUR_BRANCH, tasksBEAN.getTaskId());
        loadAllLogData(listTaskLogs);
        System.out.println("Data :: " + listTaskLogs.toString());
        lblEnquiryInfo.setVisible(false);
        lblEnquiryInfo.setManaged(false);
        lblTaskName.setText(lblTaskName.getText() + " " + tasksBEAN.getTaskName());
        lblTaskNo.setText(lblTaskNo.getText() + " " + tasksBEAN.getTicketNo());
        lblTaskCreatedDate.setText(lblTaskCreatedDate.getText() + " " + tasksBEAN.getCreatedDate());
        if (tasksBEAN.getTaskDescription() != null) {
            if (tasksBEAN.getTaskDescription().length() > 300) {
                lblTaskDescription.setText(lblTaskDescription.getText() + " " + tasksBEAN.getTaskDescription().substring(0, 299));
            } else {
                lblTaskDescription.setText(lblTaskDescription.getText() + " " + tasksBEAN.getTaskDescription());
            }
        } else {
            lblTaskDescription.setText(lblTaskDescription.getText() + " " + tasksBEAN.getTaskDescription());
        }

        lblTaskCreatedBy.setText(lblTaskCreatedBy.getText() + " " + tasksBEAN.getCreatedUser());
        lblTaskDescription.setWrapText(true);
        lblTaskName.setWrapText(true);
        /* ======================== Show enquiry related data if the enquiry relates to current task ==================== */
        if (tasksBEAN.getKeyword() != null) {
            if (!tasksBEAN.getKeyword().equalsIgnoreCase("")) {
                if (!tasksBEAN.getKeyword().equalsIgnoreCase("generic")) {
                    CounselorDetailsBEAN counselorDetailsBEAN = new CounselorDetailsBEAN();
                    EnquiryDetailsDAO.getEnquiryById(tasksBEAN.getKeyword(), counselorDetailsBEAN);
                    System.out.println("test enquiry :: " + counselorDetailsBEAN.toString());
                    lblEnquiryInfo.setVisible(true);
                    lblEnquiryInfo.setManaged(true);
                    lblEnquiryInfo.setText("This task related to enquiry of " + counselorDetailsBEAN.getContactName() + " (" + counselorDetailsBEAN.getContactNumber1() + ")");
                }
            }
        }
        /* ======================== Add Bindings ==================== */
//        cmbAssesmentSubStatus.valueProperty().addListener(new ChangeListener<SubStatusPOJO>() {
//            @Override
//            public void changed(ObservableValue<? extends SubStatusPOJO> observable, SubStatusPOJO oldValue, SubStatusPOJO newValue) {
//                if (newValue != null) {
//                    tasksBEAN.setAssessmentSubStatus(newValue.getId());
//                    System.out.println("Id Selected :: " + newValue.getId());
//                }
//            }
//        });
        //  Bindings.bindBidirectional(cmbAssesmentSubStatus.valueProperty(), tasksBEAN.assessmentSubStatusProperty());
        Bindings.bindBidirectional(txtRemarks.textProperty(), tasksBEAN.remarksProperty());
        Bindings.bindBidirectional(cmbTaskStatus.valueProperty(), tasksBEAN.taskStatusProperty());
        /* ======================== Load master data ==================== */
        ObservableList observableListtaskStatus = FXCollections.observableArrayList("Open", "On Hold", "Closed");
        cmbTaskStatus.setItems(observableListtaskStatus);

        /* ======================== Save Remarks ==================== */
        btnAddRemark.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Task Bean :: " + tasksBEAN.toString());
                tasksBEAN.setUpdatedUser(CUR_USERNAME);
                tasksBEAN.setCreatedUser(CUR_USERNAME);
                if (tasksBEAN.getKeyword() != null) {
                    if (!tasksBEAN.getKeyword().equalsIgnoreCase("")) {
                        if (!tasksBEAN.getKeyword().equalsIgnoreCase("generic")) {
                            System.out.println("It is enquiry related enquiry ....");
                            if (tasksBEAN.getRemarks() != null) {
                                int row = tasksDAO.insertTaskDetailsLog(tasksBEAN);
                                if (row > 0) {
                                    /* ======================== Load All Log ==================== */
                                    ObservableList<TasksBEAN> listTaskLogs = tasksDAO.retrieveTaskLogById(CUR_USERNAME, CUR_BRANCH, tasksBEAN.getTaskId());
                                    loadAllLogData(listTaskLogs);
                                    txtRemarks.clear();
                                }
                            } else {
                                showPopupMessages.showError("Please Add Remarks & Task Status", txtRemarks);
                            }

                        } else if (tasksBEAN.getRemarks() != null) {
                            if (!tasksBEAN.getRemarks().equalsIgnoreCase("")) {
                                int row = tasksDAO.insertTaskDetailsLog(tasksBEAN);
                                if (row > 0) {
                                    /* ======================== Load All Log ==================== */
                                    ObservableList<TasksBEAN> listTaskLogs = tasksDAO.retrieveTaskLogById(CUR_USERNAME, CUR_BRANCH, tasksBEAN.getTaskId());
                                    loadAllLogData(listTaskLogs);
                                    txtRemarks.clear();
                                }
                            } else {
                                showPopupMessages.showError("Please add remarks", txtRemarks);
                            }
                        } else {
                            showPopupMessages.showError("Please add remarks", txtRemarks);
                        }
                    }
                }

            }
        }
        );
        /* ======================== Close Button ==================== */
        imgViewClose.setOnMouseClicked(
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event
            ) {
                PopOver stage = (PopOver) btnAddRemark.getScene().getWindow();
                stage.hide();
            }
        }
        );
        /* ======================== Change Task Status ==================== */
        cmbTaskStatus.valueProperty()
                .addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue
                    ) {
                        if (newValue != null) {
                            switch (newValue) {
                                case "Open":
                                    System.out.println("Open");
                                    tasksBEAN.setTaskStatus(newValue);
                                    int row2 = tasksDAO.updateTask(tasksBEAN);
                                    if (row2 > 0) {
                                        Notification notification = Notifications.SUCCESS;
                                        TrayNotification tray = new TrayNotification("Task Status Changed", "This task status has been closed successfully!", notification);
                                        tray.showAndDismiss(Duration.seconds(2), btnAddRemark);
                                    } else {
                                        Notification notification = Notifications.ERROR;
                                        TrayNotification tray = new TrayNotification("Task Status Change Failed", "Failed to change task status! Try again!", notification);
                                        tray.showAndDismiss(Duration.seconds(2), btnAddRemark);
                                    }
                                    break;
                                case "Closed":
                                    System.out.println("Closed");
                                    Bindings.unbindBidirectional(tasksBEAN.taskStatusProperty(), cmbTaskStatus.valueProperty());
                                    PopOver stage2 = (PopOver) btnAddRemark.getScene().getWindow();
                                    stage2.setDetached(true);
                                    stage2.setAutoHide(false);
                                    stage2.detach();
                                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure want close this task!", ButtonType.YES, ButtonType.CANCEL);
                                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                                    stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                                    stage.initOwner(stage2);
                                    alert.showAndWait();
                                    if (alert.getResult() == ButtonType.YES) {
                                        tasksBEAN.setTaskStatus(newValue);
                                        int row = tasksDAO.updateTask(tasksBEAN);
                                        if (row > 0) {
                                            Notification notification = Notifications.SUCCESS;
                                            TrayNotification tray = new TrayNotification("Task Closed", "This task has been closed successfully!", notification);
                                            tray.showAndDismiss(Duration.seconds(2), btnAddRemark);
                                        } else {
                                            Notification notification = Notifications.ERROR;
                                            TrayNotification tray = new TrayNotification("Task Close Failed", "Failed to close! Try again!", notification);
                                            tray.showAndDismiss(Duration.seconds(2), btnAddRemark);
                                        }
                                    } else if (alert.getResult() == ButtonType.CANCEL) {
                                        tasksBEAN.setTaskStatus(oldValue);
                                    }
                                    Bindings.bindBidirectional(cmbTaskStatus.valueProperty(), tasksBEAN.taskStatusProperty());
                                    break;
                                case "On Hold":
                                    System.out.println("On hold");
                                    tasksBEAN.setTaskStatus(newValue);
                                    int row = tasksDAO.updateTask(tasksBEAN);
                                    if (row > 0) {
                                        Notification notification = Notifications.SUCCESS;
                                        TrayNotification tray = new TrayNotification("Task Status Changed", "This task status has been closed successfully!", notification);
                                        tray.showAndDismiss(Duration.seconds(2), btnAddRemark);
                                    } else {
                                        Notification notification = Notifications.ERROR;
                                        TrayNotification tray = new TrayNotification("Task Status Change Failed", "Failed to change task status! Try again!", notification);
                                        tray.showAndDismiss(Duration.seconds(2), btnAddRemark);
                                    }
                                    break;
                                default:
                                    System.out.println("No Operation");
                            }

                        }
                    }
                }
                );
        /* ======================== Disable remark button for closed task ==================== */
        btnAddRemark.disableProperty()
                .bind(cmbTaskStatus.valueProperty().isEqualTo("Closed"));
        txtRemarks.disableProperty()
                .bind(cmbTaskStatus.valueProperty().isEqualTo("Closed"));

        txtRemarks.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    btnAddRemark.fire();
                }
            }
        });
    }

    /**
     *
     * @param tasksBEANs
     */
    public void loadAllLogData(ObservableList<TasksBEAN> tasksBEANs) {
        //  listViewLog.setItems(listTaskLogs);
        listViewLog.setCellFactory(new Callback<ListView<TasksBEAN>, ListCell<TasksBEAN>>() {
            @Override
            public ListCell<TasksBEAN> call(ListView<TasksBEAN> param) {
                return new LogCell();
            }
        });

        FilteredList<TasksBEAN> filteredData = new FilteredList<>(tasksBEANs, s -> true);
        txtSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                filteredData.setPredicate(enquiry -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (enquiry.getUpdatedDate().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    } else if (enquiry.getUpdatedUser().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    } else if (enquiry.getRemarks().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    }
                    return false;
                });
            }
        });
        listViewLog.setItems(filteredData);
    }

    static class LogCell extends ListCell<TasksBEAN> {

        @Override
        public void updateItem(TasksBEAN item, boolean empty) {
            super.updateItem(item, empty);
            Label lbltest = new Label();
            if (item != null) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zs/ina/task/log/FXMLSingleLogView.fxml"));
                    Parent root = (Parent) loader.load();
                    FXMLSingleLogViewController controller = (FXMLSingleLogViewController) loader.getController();
                    controller.initData(item);
                    lbltest.setText(item.getCreatedDate());
                    setGraphic(root);
                } catch (IOException ex) {
                    Logger.getLogger(FXMLTasksLogController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                setText(null);
                setGraphic(null);
            }
        }
    }
}
