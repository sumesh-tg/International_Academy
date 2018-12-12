/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.enquiry.appointment.newevent;

import com.zs.ina.common.error.ShowPopupMessages;
import com.zs.ina.context.Context;
import com.zs.ina.enquiry.appointment.dao.AppointmentScheduleBEAN;
import com.zs.ina.enquiry.appointment.dao.AppointmentScheduleIMPL;
import com.zs.ina.enquiry.appointment.dao.AppointmentScheduleSERVICE;
import com.zs.ina.enquiry.appointment.dao.UserPOJO;
import com.zs.traynotification.notification.Notification;
import com.zs.traynotification.notification.Notifications;
import com.zs.traynotification.notification.TrayNotification;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.util.Duration;
import jfxtras.scene.control.LocalDateTimeTextField;
import org.controlsfx.control.PopOver;

/**
 * FXML Controller class
 *
 * @author 100035
 */
public class FXMLNewEventController implements Initializable {

    @FXML
    private ComboBox<UserPOJO> cmbAnchor;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnClose;

    @FXML
    private ListView<UserPOJO> listAllEmployees;
    @FXML
    private ListView<UserPOJO> listParticipants;

    @FXML
    private TextField txtLocation;

    PopOver popOver = new PopOver();
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button btnRightDoubleArrow;
    @FXML
    private Button btnRightSingleArrow;
    @FXML
    private Button btnLeftDoubleArrow;
    @FXML
    private Button btnLeftSingleArrow;
    @FXML
    private HBox hboxStartDateTime;
    @FXML
    private HBox hboxEndDateTime;
    @FXML
    private HBox hboxNewEventTitle;
    @FXML
    private TextField txtMeetingName;
    @FXML
    private TextArea txtMeetingDescription;

    AppointmentScheduleBEAN appointmentScheduleBEAN = new AppointmentScheduleBEAN();
    AppointmentScheduleSERVICE appointmentScheduleSERVICE = new AppointmentScheduleSERVICE(new AppointmentScheduleIMPL());
    LocalDateTimeTextField startLocalDateTimeTextField = new LocalDateTimeTextField();
    LocalDateTimeTextField endLocalDateTimeTextField = new LocalDateTimeTextField();
    ObservableList<UserPOJO> leftParticipantsList = FXCollections.observableArrayList();
    ObservableList<UserPOJO> rightParticipantsList = FXCollections.observableArrayList();
    String CUR_USERNAME = null;
    String CUR_BRANCH = null;
    ShowPopupMessages showPopupMessages = new ShowPopupMessages();
    LocalDateTime startDate = null;
    LocalDateTime endDate = null;
    int flag = 0;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CUR_USERNAME = Context.getInstance().currentProfile().getProfilePOJO().getUsername();
        CUR_BRANCH = Context.getInstance().currentProfile().getProfilePOJO().getBranch();

        /* ========== bindAllData ====== */
        bindAllData();
        /*===============  Start Date and end Date Display and Alignment  ===========*/
        hboxStartDateTime.getChildren().add(startLocalDateTimeTextField);
        HBox.setHgrow(startLocalDateTimeTextField, Priority.ALWAYS);
        hboxEndDateTime.getChildren().add(endLocalDateTimeTextField);
        HBox.setHgrow(endLocalDateTimeTextField, Priority.ALWAYS);

        startLocalDateTimeTextField.localDateTimeProperty().addListener(new ChangeListener<LocalDateTime>() {

            @Override
            public void changed(ObservableValue<? extends LocalDateTime> observable, LocalDateTime oldValue, LocalDateTime newValue) {
                if (newValue != null) {
                    System.out.println("New Value :" + newValue);
                    startDate = newValue;
                    Timestamp timestamp = Timestamp.valueOf(newValue);
//                    java.util.Date today = new java.util.Date();
//                    Timestamp ts1 = new java.sql.Timestamp(today.getTime());
//                    System.out.println("today.getTime():::"+today.getTime());
                    System.out.println("TME:" + timestamp.toString().substring(0, timestamp.toString().length() - 2));
                    appointmentScheduleBEAN.setStartDateTime(timestamp.toString().substring(0, timestamp.toString().length() - 2));

                }
            }
        });
        
        endLocalDateTimeTextField.localDateTimeProperty().addListener(new ChangeListener<LocalDateTime>() {

            @Override
            public void changed(ObservableValue<? extends LocalDateTime> observable, LocalDateTime oldValue, LocalDateTime newValue) {
                if (newValue != null) {
                    endDate = newValue;
                    Timestamp timestamp = Timestamp.valueOf(newValue);
                    appointmentScheduleBEAN.setEndDateTime(timestamp.toString().substring(0, timestamp.toString().length() - 2));
                }
            }
        });

        leftParticipantsList = AppointmentScheduleIMPL.retrieveUsers();
        ObservableList<UserPOJO> cmbAnchorList = FXCollections.observableArrayList(leftParticipantsList);
        cmbAnchor.setItems(cmbAnchorList);
        listAllEmployees.setItems(leftParticipantsList);
        listParticipants.setItems(rightParticipantsList);

        /* ========== controls settings ====== */
        btnRightSingleArrow.disableProperty().bind(Bindings.equal(listAllEmployees.getSelectionModel().selectedIndexProperty(), -1));
        listAllEmployees.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        btnLeftSingleArrow.disableProperty().bind(Bindings.equal(listParticipants.getSelectionModel().selectedIndexProperty(), -1));
        listParticipants.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        btnLeftDoubleArrow.disableProperty().bind(Bindings.isEmpty(rightParticipantsList));

        /*===========  Close Button  ===========*/
        btnClose.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                popOver.hide();
            }
        });
        /*===========  " > " Button  ===========*/
        btnRightSingleArrow.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (listAllEmployees.getSelectionModel().getSelectedIndex() >= 0) {
                    rightParticipantsList.addAll(listAllEmployees.getSelectionModel().getSelectedItems());
                    leftParticipantsList.removeAll(listAllEmployees.getSelectionModel().getSelectedItems());
                }
            }
        }
        );
        /*===========  " >> " Button  ===========*/
        btnRightDoubleArrow.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                rightParticipantsList.addAll(listAllEmployees.getItems());
                leftParticipantsList.removeAll(listAllEmployees.getItems());
            }
        });

        /*===========  " << " Button  ===========*/
        btnLeftDoubleArrow.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                leftParticipantsList.addAll(listParticipants.getItems());
                rightParticipantsList.removeAll(listParticipants.getItems());
            }
        });

        /*===========  " < " Button  ===========*/
        btnLeftSingleArrow.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (listParticipants.getSelectionModel().getSelectedIndex() >= 0) {
                    leftParticipantsList.addAll(listParticipants.getSelectionModel().getSelectedItems());
                    rightParticipantsList.removeAll(listParticipants.getSelectionModel().getSelectedItems());
                }
            }
        });
        cmbAnchor.valueProperty().addListener(new ChangeListener<UserPOJO>() {

            @Override
            public void changed(ObservableValue<? extends UserPOJO> observable, UserPOJO oldValue, UserPOJO newValue) {
                if (newValue != null) {
                    appointmentScheduleBEAN.setAnchor(newValue.getUsername());
                }
            }
        });

        /*================== Save Button =============*/
        btnSave.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (validateAllFields()) {

                    UserPOJO userPOJO = new UserPOJO();
                    userPOJO.setUsername(CUR_USERNAME);

                    for (UserPOJO upojo : rightParticipantsList) {
                        if (upojo.getUsername().equals(userPOJO.getUsername())) {
                            flag = 1;
                            break;
                        }
                    }
                    if (flag == 0) {
                        rightParticipantsList.add(userPOJO);
                    }
                    appointmentScheduleBEAN.setParticipantsIdList(rightParticipantsList);
                    appointmentScheduleBEAN.setCreatedUser(CUR_USERNAME);
                    System.out.println("Save :" + rightParticipantsList);
                    appointmentScheduleSERVICE = new AppointmentScheduleSERVICE(new AppointmentScheduleIMPL());
                    int result = appointmentScheduleSERVICE.insertAppointment(appointmentScheduleBEAN);
                    if (result > 0) {
                        clearAllFields();
                        Notification noticication = Notifications.SUCCESS;
                        TrayNotification trayNotification = new TrayNotification("Meeting Scheduled", "Scheduled Successfully", noticication);
                        trayNotification.showAndDismiss(Duration.seconds(3),btnSave);
                        trayNotification.setOnDismiss(new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent event) {
                                Context.getInstance().currentProfile().getPopOver().hide();
                            }
                        });
                    } else {
                        Notification noticication = Notifications.ERROR;
                        TrayNotification trayNotification = new TrayNotification("Meeting Not Scheduled", "Meeting not scheduled successfully", noticication);
                        trayNotification.showAndDismiss(Duration.seconds(3), btnSave);

                    }
                }

            }

            private boolean validateAllFields() {
                boolean flag = true;
                if (rightParticipantsList.isEmpty()) {
                    showPopupMessages.showError("Select Participants for meeting", listAllEmployees);
                    flag = false;
                } else if (appointmentScheduleBEAN.getMeetingName() == null || appointmentScheduleBEAN.getMeetingName().equals("")) {
                    showPopupMessages.showError("Enter meeting name", txtMeetingName);
                    flag = false;
                } else if (appointmentScheduleBEAN.getMeetingDescription() == null || appointmentScheduleBEAN.getMeetingDescription().equals("")) {
                    showPopupMessages.showError("Enter meeting description", txtMeetingDescription);
                    flag = false;
                } else if (appointmentScheduleBEAN.getStartDateTime() == null || appointmentScheduleBEAN.getStartDateTime().equals("")) {
                    showPopupMessages.showError("Select start date and time", startLocalDateTimeTextField);
                    flag = false;
                } else if (appointmentScheduleBEAN.getEndDateTime() == null || appointmentScheduleBEAN.getEndDateTime().equals("")) {
                    showPopupMessages.showError("Select end date and time", endLocalDateTimeTextField);
                    flag = false;
                } else if (endDate.isBefore(startDate) || endDate.isEqual(startDate)) {
                    showPopupMessages.showError("End date must be after start date", endLocalDateTimeTextField);
                    flag = false;
                } else if (appointmentScheduleBEAN.getLocation() == null || appointmentScheduleBEAN.getLocation().equals("")) {
                    showPopupMessages.showError("Enter location", txtLocation);
                    flag = false;
                } else if (appointmentScheduleBEAN.getAnchor() == null || appointmentScheduleBEAN.getAnchor().equals("")) {
                    showPopupMessages.showError("Select anchor", cmbAnchor);
                    flag = false;
                }
//                 else if (appointmentScheduleBEAN.getStartDateTime()> ) {
//                    showPopupMessages.showError("Select start date and time", startLocalDateTimeTextField);
//                    flag = false;
//                }

                return flag;
            }

            private void clearAllFields() {
                appointmentScheduleBEAN.setMeetingName("");
                appointmentScheduleBEAN.setMeetingDescription("");
                appointmentScheduleBEAN.setStartDateTime("");
                appointmentScheduleBEAN.setEndDateTime("");
                appointmentScheduleBEAN.setLocation("");
                appointmentScheduleBEAN.setAnchor("");
                cmbAnchor.getSelectionModel().clearSelection();
                listParticipants.getItems().clear();

                bindAllData();

            }
        });
    }

    /**
     *
     * @param popOver
     */
    public void passPopOverObject(PopOver popOver) {
        this.popOver = popOver;
    }

    private void bindAllData() {
        Bindings.bindBidirectional(txtMeetingName.textProperty(), appointmentScheduleBEAN.meetingNameProperty());
        Bindings.bindBidirectional(txtMeetingDescription.textProperty(), appointmentScheduleBEAN.meetingDescriptionProperty());
        Bindings.bindBidirectional(txtLocation.textProperty(), appointmentScheduleBEAN.locationProperty());

    }

}
