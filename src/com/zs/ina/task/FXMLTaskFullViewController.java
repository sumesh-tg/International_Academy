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
package com.zs.ina.task;

import com.zs.ina.admin.master.retrieve.CounselorsListPOJO;
import com.zs.ina.admin.master.retrieve.RetrieveStaticMasterDAO;
import com.zs.ina.common.error.ShowPopupMessages;
import com.zs.ina.context.Context;
import com.zs.ina.counselor.dao.model.CounselorDetailsBEAN;
import com.zs.ina.enquiry.dao.EnquiryDetailsDAO;
import com.zs.ina.task.dao.TasksBEAN;
import com.zs.ina.task.dao.TasksDAO;
import com.zs.ina.task.dao.TasksIMPL;
import com.zs.ina.task.log.FXMLTasksLogController;
import com.zs.traynotification.notification.Notification;
import com.zs.traynotification.notification.Notifications;
import com.zs.traynotification.notification.TrayNotification;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import org.apache.log4j.Logger;
import org.controlsfx.control.PopOver;
import org.springframework.beans.BeanUtils;

/**
 * FXML Controller class
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class FXMLTaskFullViewController implements Initializable {

    static Logger logger = Logger.getLogger(FXMLTaskFullViewController.class);

    @FXML
    private TableView<TasksBEAN> tblTaskList;
    @FXML
    private TextField txtSearch;
    @FXML
    private TextField txtTicketNo;
    @FXML
    private TextField txtTaskName;
    @FXML
    private TextArea txttaskDescription;
    @FXML
    private Button btnAddTask;
    TasksBEAN tasksBEAN = new TasksBEAN();
    @FXML
    private ComboBox<String> cmbTaskStatus;
    @FXML
    private ComboBox<CounselorsListPOJO> cmbAssignTo;
    TasksDAO tasksDAO = new TasksIMPL();
    ShowPopupMessages showPopupMessages = new ShowPopupMessages();
    private String CUR_USERNAME = null;
    private String CUR_BRANCH = null;
    @FXML
    private TableColumn<?, ?> clmTicketNo;
    @FXML
    private TableColumn<?, ?> clmTaskName;
    @FXML
    private TableColumn<?, ?> clmTaskDesciption;
    @FXML
    private TableColumn<?, ?> clmAssignTo;
    @FXML
    private TableColumn<?, ?> clmTaskStatus;
    ObservableList<TasksBEAN> masterSearchData = FXCollections.observableArrayList();
    @FXML
    private Hyperlink hyperLinkOpen;
    @FXML
    private Hyperlink hyperLinkHold;
    @FXML
    private Hyperlink hyperLinkclosed;
    List<TasksBEAN> listCreatedTasks = new ArrayList<>();
    @FXML
    private ImageView imgViewClose;
    @FXML
    private Button cmbViewLog;
    PopOver popOver = new PopOver();
    @FXML
    private Hyperlink hyperLinkCreated;
    @FXML
    private Hyperlink hyperLinkIncoming;
    @FXML
    private Hyperlink hyperLinkAll;
    @FXML
    private Button btnClear;
    @FXML
    private Label lblAssignTo;
    String KEYWORD = null;
    String CUR_ASSIGN_TO = null;
    @FXML
    private Label lblTaskInfo;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CUR_USERNAME = Context.getInstance().currentProfile().getProfilePOJO().getUsername();
        CUR_BRANCH = Context.getInstance().currentProfile().getProfilePOJO().getBranch();
        /* ======================== Load Enquiry Data If the Task related to particular enquiry ==================== */
        CounselorDetailsBEAN counselorDetailsBEAN = new CounselorDetailsBEAN();
        counselorDetailsBEAN = Context.getInstance().currentProfile().getCounselorDetailsTaskBEAN();
        lblTaskInfo.setText("");
        if (counselorDetailsBEAN != null) {
            if (counselorDetailsBEAN.getEnquiryID() != null) {
                System.out.println("This task relates to enquiry" + counselorDetailsBEAN.toString());
                KEYWORD = counselorDetailsBEAN.getEnquiryID();
                lblTaskInfo.setText("This task related to enquiry of " + counselorDetailsBEAN.getContactName() + "(" + counselorDetailsBEAN.getContactNumber1() + ")");
                /* ======================== Load All Created Tasks ==================== */
                listCreatedTasks = tasksDAO.retrieveAllTasks(CUR_USERNAME, CUR_USERNAME, CUR_BRANCH, KEYWORD);
                loadCreatedTasks(listCreatedTasks);
            } else {
                /* ======================== Load All Created Tasks ==================== */
                listCreatedTasks = tasksDAO.retrieveAllTasks(CUR_USERNAME, CUR_USERNAME, CUR_BRANCH);
                loadCreatedTasks(listCreatedTasks);
            }
        } else {
            /* ======================== Load All Created Tasks ==================== */
            listCreatedTasks = tasksDAO.retrieveAllTasks(CUR_USERNAME, CUR_USERNAME, CUR_BRANCH);
            loadCreatedTasks(listCreatedTasks);
        }

        bindAddNewTaskControls();
        tasksBEAN.setTaskStatus("Open");
        cmbTaskStatus.setDisable(true);
//        txtTaskremarks.setDisable(true);
        /* ======================== Load next ticket no ==================== */
        loadNextTicketNo();
        txtTicketNo.setDisable(true);
        initData();

        /* ======================== Save New Task Details ==================== */
        btnAddTask.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Task Data :: " + tasksBEAN.toString());
                int row = 0;
                if (validateTask(tasksBEAN)) {
                    tasksBEAN.setCreatedUser(CUR_USERNAME);
                    tasksBEAN.setTicketNo("0");
                    tasksBEAN.setKeyword(KEYWORD);
                    System.out.println("Test Curr AssignTo ::" + CUR_ASSIGN_TO);
                    System.out.println("Test Curr AssignTo From ::" + tasksBEAN.getAssignedTo());

                    if (CUR_ASSIGN_TO != null) {
                        if (!CUR_ASSIGN_TO.equalsIgnoreCase(tasksBEAN.getAssignedTo())) {
                            row = tasksDAO.insertTask(tasksBEAN);
                        }
                    } else {
                        row = tasksDAO.insertTask(tasksBEAN);
                    }

                    if (row > 0) {
                        Notification notification = Notifications.SUCCESS;
                        TrayNotification tray = new TrayNotification("New Task Created", "New task has been created successfully!", notification);
                        if (btnAddTask.getText().equalsIgnoreCase("Forward")) {
                            tray = new TrayNotification("Task Forwarded", "This task has been forwarded successfully!", notification);
                        }
                        tray.showAndDismiss(Duration.seconds(3), btnAddTask);
                    } else {
                        Notification notification = Notifications.ERROR;
                        TrayNotification tray = new TrayNotification("Task Creation Failed", "Please make sure you entered valid data!", notification);
                        if (btnAddTask.getText().equalsIgnoreCase("Forward")) {
                            tray = new TrayNotification("Forward Failed", "This task couldn't forward!", notification);
                        }
                        tray.showAndDismiss(Duration.seconds(3), btnAddTask);
                    }
                    tasksBEAN = new TasksBEAN();
                    lblTaskInfo.setText("");
                    lblAssignTo.setText("Assign To");
                    btnAddTask.setText("Add Task");
                    cmbAssignTo.getSelectionModel().clearSelection();
                    CUR_ASSIGN_TO = null;
                    bindAddNewTaskControls();
                    tasksBEAN.setTaskStatus("Open");
                    listCreatedTasks.clear();
                    if (KEYWORD != null) {
                        if (KEYWORD.equalsIgnoreCase("")) {
                            listCreatedTasks = tasksDAO.retrieveAllTasks(CUR_USERNAME, CUR_USERNAME, CUR_BRANCH);
                        } else {
                            listCreatedTasks = tasksDAO.retrieveAllTasks(CUR_USERNAME, CUR_USERNAME, CUR_BRANCH, KEYWORD);
                        }
                    } else {
                        listCreatedTasks = tasksDAO.retrieveAllTasks(CUR_USERNAME, CUR_USERNAME, CUR_BRANCH);
                    }
                    loadCreatedTasks(listCreatedTasks);
                    loadNextTicketNo();

                }
            }
        });
        /* ======================== Add Events For hyperlinks ==================== */
        addHyperLinkEvents();
        /* ======================== Add Close Icon ==================== */
        imgViewClose.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Stage stage = (Stage) btnAddTask.getScene().getWindow();
                stage.close();
            }
        });
        addLogFeature();
        /* ======================== Clear Form ==================== */
        btnClear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tasksBEAN = new TasksBEAN();
//                txtKeyWord.clear();
//                txtKeyWord.setDisable(true);
//                KEYWORD = null;
                lblTaskInfo.setText("");
                bindAddNewTaskControls();
                tasksBEAN.setTaskStatus("Open");
                listCreatedTasks.clear();
                if (KEYWORD != null) {
                    if (KEYWORD.equalsIgnoreCase("")) {
                        listCreatedTasks = tasksDAO.retrieveAllTasks(CUR_USERNAME, CUR_USERNAME, CUR_BRANCH);
                    } else {
                        listCreatedTasks = tasksDAO.retrieveAllTasks(CUR_USERNAME, CUR_USERNAME, CUR_BRANCH, KEYWORD);
                    }
                } else {
                    listCreatedTasks = tasksDAO.retrieveAllTasks(CUR_USERNAME, CUR_USERNAME, CUR_BRANCH);
                }
                loadCreatedTasks(listCreatedTasks);
                loadNextTicketNo();
                lblAssignTo.setText("Assign To");
                btnAddTask.setText("Add Task");
                cmbAssignTo.getSelectionModel().clearSelection();
                CUR_ASSIGN_TO = null;

            }
        });
    }

    private void addHyperLinkEvents() {
        hyperLinkOpen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                listCreatedTasks.clear();
                txtSearch.clear();
                if (KEYWORD != null) {
                    if (KEYWORD.isEmpty()) {
                        listCreatedTasks = tasksDAO.retrieveByStatus(CUR_USERNAME, CUR_BRANCH, hyperLinkOpen.getText());
                    } else {
                        listCreatedTasks = tasksDAO.retrieveByStatus(CUR_USERNAME, CUR_BRANCH, hyperLinkOpen.getText(), KEYWORD);
                    }
                } else {
                    listCreatedTasks = tasksDAO.retrieveByStatus(CUR_USERNAME, CUR_BRANCH, hyperLinkOpen.getText());
                }
                System.out.println("Clicked" + KEYWORD);
                loadCreatedTasks(listCreatedTasks);
            }
        });
        hyperLinkHold.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                listCreatedTasks.clear();
                txtSearch.clear();
                if (KEYWORD != null) {
                    if (KEYWORD.equalsIgnoreCase("")) {
                        System.out.println("test empty string " + KEYWORD);
                        listCreatedTasks = tasksDAO.retrieveByStatus(CUR_USERNAME, CUR_BRANCH, hyperLinkHold.getText());
                    } else {
                        System.out.println("test  kere " + KEYWORD);

                        listCreatedTasks = tasksDAO.retrieveByStatus(CUR_USERNAME, CUR_BRANCH, hyperLinkHold.getText(), KEYWORD);
                    }
                } else {
                    System.out.println("test  null  " + KEYWORD);

                    listCreatedTasks = tasksDAO.retrieveByStatus(CUR_USERNAME, CUR_BRANCH, hyperLinkHold.getText());
                }
                System.out.println("Clicked" + KEYWORD);
                loadCreatedTasks(listCreatedTasks);
            }
        });
        hyperLinkclosed.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                listCreatedTasks.clear();
                txtSearch.clear();
                if (KEYWORD != null) {
                    if (KEYWORD.isEmpty()) {
                        listCreatedTasks = tasksDAO.retrieveByStatus(CUR_USERNAME, CUR_BRANCH, hyperLinkclosed.getText());
                    } else {
                        listCreatedTasks = tasksDAO.retrieveByStatus(CUR_USERNAME, CUR_BRANCH, hyperLinkclosed.getText(), KEYWORD);
                    }
                } else {
                    listCreatedTasks = tasksDAO.retrieveByStatus(CUR_USERNAME, CUR_BRANCH, hyperLinkclosed.getText());
                }
                System.out.println("Clicked " + KEYWORD);
                loadCreatedTasks(listCreatedTasks);
            }
        });
        hyperLinkCreated.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                listCreatedTasks.clear();
                txtSearch.clear();
                listCreatedTasks = tasksDAO.retrieveTaskCreated(CUR_USERNAME, CUR_BRANCH, KEYWORD);
                loadCreatedTasks(listCreatedTasks);
            }
        });
        hyperLinkIncoming.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                listCreatedTasks.clear();
                txtSearch.clear();
                listCreatedTasks = tasksDAO.retrieveAllIncoming(CUR_USERNAME, CUR_BRANCH, KEYWORD);
                loadCreatedTasks(listCreatedTasks);
            }
        });
        hyperLinkAll.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                listCreatedTasks.clear();
                txtSearch.clear();
                if (KEYWORD != null) {
                    if (KEYWORD.equalsIgnoreCase("")) {
                        listCreatedTasks = tasksDAO.retrieveAllTasks(CUR_USERNAME, CUR_BRANCH, hyperLinkAll.getText());
                    } else {
                        listCreatedTasks = tasksDAO.retrieveAllTasks(CUR_USERNAME, CUR_BRANCH, hyperLinkAll.getText(), KEYWORD);
                    }
                } else {
                    listCreatedTasks = tasksDAO.retrieveAllTasks(CUR_USERNAME, CUR_BRANCH, hyperLinkAll.getText());
                }
                loadCreatedTasks(listCreatedTasks);
            }
        });
    }

    private void loadCreatedTasks(List<TasksBEAN> listCreatedTasks) {
        masterSearchData.clear();
        tblTaskList.getItems().clear();
        masterSearchData.addAll(listCreatedTasks);
        System.out.println("Test " + listCreatedTasks.size());
        clmTicketNo.setCellValueFactory(
                new PropertyValueFactory<>("ticketNo"));
        clmTaskName.setCellValueFactory(
                new PropertyValueFactory<>("taskName"));
        clmTaskDesciption.setCellValueFactory(
                new PropertyValueFactory<>("taskDescription"));
        clmAssignTo.setCellValueFactory(
                new PropertyValueFactory<>("assignedTo"));
        clmTaskStatus.setCellValueFactory(
                new PropertyValueFactory<>("taskStatus"));
//        tblTaskList.getItems().addAll(listCreatedTasks);
        FilteredList<TasksBEAN> filteredData = new FilteredList<>(masterSearchData, p -> true);
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(enquiry -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (enquiry.getTaskName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (enquiry.getAssignedTo().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (enquiry.getTicketNo().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (enquiry.getTaskStatus().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (enquiry.getTaskDescription().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                return false;
            });
        });
        SortedList<TasksBEAN> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tblTaskList.comparatorProperty());
        tblTaskList.setItems(sortedData);
        /* ======================== Add Row factory for double click row ==================== */
        tblTaskList.setRowFactory(new Callback<TableView<TasksBEAN>, TableRow<TasksBEAN>>() {
            @Override
            public TableRow<TasksBEAN> call(TableView<TasksBEAN> param) {
                final TableRow<TasksBEAN> row = new TableRow<TasksBEAN>() {
                    @Override
                    protected void updateItem(TasksBEAN tbean, boolean empty) {
                        super.updateItem(tbean, empty);
                        if (!empty) {

                        }
                    }
                };
                row.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (event.getButton().equals(MouseButton.PRIMARY)) {
                            if (event.getClickCount() == 2) {
                                try {
                                    popOver = new PopOver();
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zs/ina/task/log/FXMLTasksLog.fxml"));
                                    Parent root = (Parent) loader.load();
                                    FXMLTasksLogController controller = (FXMLTasksLogController) loader.getController();
                                    controller.initData(row.getItem());
                                    popOver.setArrowLocation(PopOver.ArrowLocation.RIGHT_BOTTOM);
                                    popOver.setAnchorLocation(PopupWindow.AnchorLocation.WINDOW_TOP_LEFT);
                                    popOver.setArrowSize(12.0);
                                    popOver.setHideOnEscape(true);
                                    popOver.setAutoFix(true);
                                    popOver.setAutoHide(true);
                                    popOver.setTitle("Log details of task " + row.getItem().getTaskName());
                                    popOver.setDetached(true);
                                    popOver.detach();
                                    popOver.setContentNode(root);
                                    popOver.show(cmbViewLog, cmbViewLog.localToScreen(0, 0).getX() - 60, cmbViewLog.localToScreen(0, 0).getY() + 30);
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                    logger.error(ex.getMessage());
                                }

                            }
                            /* ======================== On SIngle Click Load Data to form ==================== */
                            if (event.getClickCount() == 1) {
                                BeanUtils.copyProperties(row.getItem(), tasksBEAN);
                                CUR_ASSIGN_TO = row.getItem().getAssignedTo();
                                tasksBEAN.setAssignedTo(null);
                                /* ======================== Disabe Forwarding  ==================== */
                                btnAddTask.disableProperty().bind(row.getItem().taskStatusProperty().isEqualTo("Closed"));
                                if (tasksBEAN.getKeyword() != null) {
                                    if (!tasksBEAN.getKeyword().equalsIgnoreCase("")) {
                                        if (!tasksBEAN.getKeyword().equalsIgnoreCase("generic")) {
                                            CounselorDetailsBEAN counselorDetailsBEAN = new CounselorDetailsBEAN();
                                            EnquiryDetailsDAO.getEnquiryById(tasksBEAN.getKeyword(), counselorDetailsBEAN);
                                            System.out.println("test enquiry :: " + counselorDetailsBEAN.toString());
                                            lblTaskInfo.setVisible(true);
                                            lblTaskInfo.setManaged(true);
                                            lblTaskInfo.setText("This task related to enquiry of " + counselorDetailsBEAN.getContactName() + " (" + counselorDetailsBEAN.getContactNumber1() + ")");
                                        } else {
                                            lblTaskInfo.setText("");
                                            lblTaskInfo.setVisible(false);
                                        }
                                    } else {
                                        lblTaskInfo.setText("");
                                        lblTaskInfo.setVisible(false);
                                    }
                                } else {
                                    lblTaskInfo.setText("");
                                    lblTaskInfo.setVisible(false);
                                }
                                bindAddNewTaskControls();
                                lblAssignTo.setText("Forward To");
                                btnAddTask.setText("Forward");

                            }

                        }
                    }
                });
                return row;
            }
        }
        );
    }

    private void initData() {
        ObservableList observableListtaskStatus = FXCollections.observableArrayList("Open", "On Hold", "Closed");
        cmbTaskStatus.setItems(observableListtaskStatus);
        ObservableList<CounselorsListPOJO> listCounselors = RetrieveStaticMasterDAO.getAllUsersByBranch(null);
        for (CounselorsListPOJO clpojo : listCounselors) {
            if (clpojo.getUsername().equalsIgnoreCase(CUR_USERNAME)) {
                listCounselors.remove(clpojo);
                break;
            }
        }
        cmbAssignTo.setItems(listCounselors);
    }

    private void bindAddNewTaskControls() {
        /* ======================== Bind Add Task Controls ==================== */

        Bindings.bindBidirectional(txtTicketNo.textProperty(), tasksBEAN.ticketNoProperty());
//        Bindings.bindBidirectional(tasksBEAN.keywordProperty(), txtKeyWord.textProperty());
        Bindings.bindBidirectional(txtTaskName.textProperty(), tasksBEAN.taskNameProperty());
        Bindings.bindBidirectional(cmbTaskStatus.valueProperty(), tasksBEAN.taskStatusProperty());
        Bindings.bindBidirectional(txttaskDescription.textProperty(), tasksBEAN.taskDescriptionProperty());
//        Bindings.bindBidirectional(txtTaskremarks.textProperty(), tasksBEAN.remarksProperty());
//        Bindings.bindBidirectional(cmbAssignTo.valueProperty(), tasksBEAN.assignedToProperty());

        cmbAssignTo.valueProperty().addListener(new ChangeListener<CounselorsListPOJO>() {

            @Override
            public void changed(ObservableValue<? extends CounselorsListPOJO> observable, CounselorsListPOJO oldValue, CounselorsListPOJO newValue) {
                if (newValue != null) {
                    tasksBEAN.setAssignedTo(newValue.getUsername());
                }
            }
        });
        tasksBEAN.assignedToProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    cmbAssignTo.getSelectionModel().clearSelection();
                    for (CounselorsListPOJO clpojo : cmbAssignTo.getItems()) {
                        if (clpojo.getUsername().equalsIgnoreCase(newValue)) {
                            cmbAssignTo.getSelectionModel().select(clpojo);
                        }
                    }
                }
            }
        });

    }

    /**
     *
     * @param tasksBEAN
     * @return
     */
    public boolean validateTask(TasksBEAN tasksBEAN) {
        boolean flag = true;
        if (tasksBEAN.getTaskName() == null || tasksBEAN.getTaskName().equalsIgnoreCase("")) {
            showPopupMessages.showError("Please Enter Task Name!", txtTaskName);
            flag = false;
        } else if (tasksBEAN.getTaskStatus() == null || tasksBEAN.getTaskStatus().equalsIgnoreCase("")) {
            showPopupMessages.showError("Please Choose Task Status!", cmbTaskStatus);
            flag = false;
        } else if (tasksBEAN.getTaskDescription() == null || tasksBEAN.getTaskDescription().equalsIgnoreCase("")) {
            showPopupMessages.showError("Please Enter Task Description!", txttaskDescription);
            flag = false;
        } else if (tasksBEAN.getAssignedTo() == null || tasksBEAN.getAssignedTo().equalsIgnoreCase("")) {
            showPopupMessages.showError("Please assign task to a person!", cmbAssignTo);
            flag = false;
        }
        return flag;
    }

    private void loadNextTicketNo() {
        String _nextTicketNo = tasksDAO.getNextTicketNo();
        if (_nextTicketNo != null) {
            txtTicketNo.setText(_nextTicketNo);
        }
    }

    private void addLogFeature() {
        /* ======================== Log button visibility ==================== */
        cmbViewLog.disableProperty().bind(Bindings.equal(tblTaskList.getSelectionModel().selectedIndexProperty(), -1));
        cmbViewLog.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    popOver = new PopOver();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zs/ina/task/log/FXMLTasksLog.fxml"));
                    Parent root = (Parent) loader.load();
                    FXMLTasksLogController controller = (FXMLTasksLogController) loader.getController();
                    controller.initData(tblTaskList.getSelectionModel().getSelectedItem());
                    popOver.setArrowLocation(PopOver.ArrowLocation.RIGHT_BOTTOM);
                    popOver.setAnchorLocation(PopupWindow.AnchorLocation.WINDOW_TOP_LEFT);
                    popOver.setArrowSize(12.0);
                    popOver.setHideOnEscape(true);
                    popOver.setAutoFix(true);
                    popOver.setAutoHide(true);
                    popOver.setTitle("List of users");
                    popOver.setContentNode(root);
                    popOver.show(cmbViewLog, cmbViewLog.localToScreen(0, 0).getX() - 60, cmbViewLog.localToScreen(0, 0).getY() + 30);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    logger.error(ex.toString());
                }

            }
        });
    }

}
