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
package com.zs.ina.enquiry.appointment.attendance;

import com.zs.ina.context.Context;
import com.zs.ina.enquiry.appointment.dao.AppointmentScheduleBEAN;
import com.zs.ina.enquiry.appointment.dao.AppointmentScheduleIMPL;
import com.zs.ina.enquiry.appointment.dao.AppointmentScheduleSERVICE;
import com.zs.ina.enquiry.appointment.dao.UserPOJO;
import com.zs.traynotification.notification.Notification;
import com.zs.traynotification.notification.Notifications;
import com.zs.traynotification.notification.TrayNotification;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import javafx.util.Duration;
import org.apache.log4j.Logger;
import org.controlsfx.control.PopOver;

/**
 * FXML Controller class
 *
 * @author 100035
 */
public class FXMLEventsAttendanceController implements Initializable {

    AppointmentScheduleSERVICE appointmentScheduleSERVICE = new AppointmentScheduleSERVICE(new AppointmentScheduleIMPL());
    ObservableList<UserPOJO> participantsList = FXCollections.observableArrayList();
    ObservableList<AppointmentScheduleBEAN> eventsList = FXCollections.observableArrayList();
    ObservableList<UserPOJO> checkedParticipantsList = FXCollections.observableArrayList();
    ObservableList<UserPOJO> participantsIdList = FXCollections.observableArrayList();
    /* ====================== List For Storing Attendance Id's ====================== */
    ObservableList<UserPOJO> masterParticipantsIdList = FXCollections.observableArrayList();
    PopOver popOver = new PopOver();
    Logger logger = Logger.getLogger(FXMLEventsAttendanceController.class);
    String CUR_USERNAME = "";
    String CUR_BRANCH = "";

    @FXML
    private ListView<AppointmentScheduleBEAN> listViewEvents;
    @FXML
    private ListView<UserPOJO> listViewParticipants;
    @FXML
    private Button btnSubmit;
    @FXML
    private Button btnClose;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CUR_USERNAME = Context.getInstance().currentProfile().getProfilePOJO().getUsername();
        CUR_BRANCH = Context.getInstance().currentProfile().getProfilePOJO().getBranch();
        eventsList = appointmentScheduleSERVICE.viewEventsForAnchor(CUR_USERNAME, CUR_BRANCH);
        System.out.println("Test List EventsForAnchor:: " + eventsList.toString());

        /* ============ Display Events List of the Anchor(Current User) =============== */
        addEventsIntoListViewEvents(eventsList);

        /* ============ Submit Button =============== */
        btnSubmit.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Test All Attendance" + masterParticipantsIdList.toString());
                int result;
                result = appointmentScheduleSERVICE.updateParticipationStatus(masterParticipantsIdList, listViewEvents.getSelectionModel().getSelectedItem().getMeetingId());
                if (result > 0) {
                    Notification noticication = Notifications.SUCCESS;
                    TrayNotification trayNotification = new TrayNotification("Attendance Marked", "Attendance Marked Successfully", noticication);
                    trayNotification.showAndDismiss(Duration.seconds(5), btnSubmit);
                    // popOver.hide();
                } else {
                    Notification noticication = Notifications.ERROR;
                    TrayNotification trayNotification = new TrayNotification("Attendance Not Marked", "Attendance Save Unsuccessfully", noticication);
                    trayNotification.showAndDismiss(Duration.seconds(5), btnSubmit);

                }

            }
        });
        btnClose.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                popOver.hide();
            }
        });

    }

    /* ============ popOver =============== */

    /**
     *
     * @param popOver
     */

    public void passPopOverObject(PopOver popOver) {
        this.popOver = popOver;
    }

    private void addEventsIntoListViewEvents(ObservableList<AppointmentScheduleBEAN> eventsList) {
        listViewEvents.setCellFactory(new Callback<ListView<AppointmentScheduleBEAN>, ListCell<AppointmentScheduleBEAN>>() {

            @Override
            public ListCell<AppointmentScheduleBEAN> call(ListView<AppointmentScheduleBEAN> param) {

                final ListCell<AppointmentScheduleBEAN> cell = new ListCell<AppointmentScheduleBEAN>() {
                    @Override
                    protected void updateItem(AppointmentScheduleBEAN appointmentScheduleBEAN, boolean empty) {
                        super.updateItem(appointmentScheduleBEAN, empty);
                        if (appointmentScheduleBEAN != null) {
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zs/ina/enquiry/appointment/attendance/FXMLMeetingList.fxml"));
                                Parent root = (Parent) loader.load();
                                FXMLMeetingListController controller = (FXMLMeetingListController) loader.getController();
                                controller.passEventDetails(appointmentScheduleBEAN);
                                setGraphic(root);
                            } catch (IOException sqle) {
                                logger.error(sqle.toString());
                                sqle.printStackTrace();
                            }

                        } else {
                            setGraphic(null);
                        }
                    }
                };
                return cell;
            }

        });
        listViewEvents.getItems().addAll(eventsList);
        listViewEvents.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<AppointmentScheduleBEAN>() {

            @Override
            public void changed(ObservableValue<? extends AppointmentScheduleBEAN> observable, AppointmentScheduleBEAN oldValue, AppointmentScheduleBEAN newValue) {
                if (newValue != null) {
                    System.out.println("You selected :: " + newValue.toString());
                    masterParticipantsIdList.clear();
                    participantsList = appointmentScheduleSERVICE.viewParticipants(CUR_USERNAME, CUR_BRANCH, newValue);
                    addParticipantsIntoListViewParticipants(participantsList);
                }
            }

            /* ============ Display Participants List of the selected event =============== */
            private void addParticipantsIntoListViewParticipants(ObservableList<UserPOJO> participantsList) {
                listViewParticipants.getItems().clear();
                listViewParticipants.setCellFactory(new Callback<ListView<UserPOJO>, ListCell<UserPOJO>>() {

                    @Override
                    public ListCell<UserPOJO> call(ListView<UserPOJO> param) {

                        final ListCell<UserPOJO> cell = new ListCell<UserPOJO>() {
                            @Override
                            protected void updateItem(UserPOJO userPOJO, boolean empty) {
                                super.updateItem(userPOJO, empty);
                                if (userPOJO != null) {
                                    try {
                                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zs/ina/enquiry/appointment/attendance/FXMLParticipantsList.fxml"));
                                        Parent root = (Parent) loader.load();
                                        FXMLParticipantsListController controller = (FXMLParticipantsListController) loader.getController();
                                        controller.loadParticipantsList(userPOJO, masterParticipantsIdList);
                                        setGraphic(root);
                                    } catch (IOException sqle) {
                                        logger.error(sqle.toString());
                                        sqle.printStackTrace();
                                    }

                                } else {
                                    setGraphic(null);
                                }
                            }
                        };
                        return cell;
                    }

                });
                listViewParticipants.getItems().addAll(participantsList);
            }
        });

    }
}
