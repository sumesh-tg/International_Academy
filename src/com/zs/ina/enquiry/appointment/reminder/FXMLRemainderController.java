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
package com.zs.ina.enquiry.appointment.reminder;

import com.zs.ina.common.error.ShowPopupMessages;
import com.zs.ina.context.Context;
import com.zs.ina.enquiry.appointment.dao.AppointmentScheduleBEAN;
import com.zs.ina.enquiry.appointment.dao.AppointmentScheduleIMPL;
import com.zs.ina.enquiry.appointment.dao.AppointmentScheduleSERVICE;
import com.zs.ina.enquiry.appointment.reminder.dao.ReminderBEAN;
import com.zs.ina.enquiry.appointment.reminder.dao.ReminderDAO;
import com.zs.ina.enquiry.appointment.reminder.dao.ReminderIMPL;
import com.zs.ina.login.INALoginForm;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;

/**
 * FXML Controller class
 *
 * @author 100035
 */
public class FXMLRemainderController implements Initializable {

    Logger logger = Logger.getLogger(FXMLRemainderController.class);
    AppointmentScheduleSERVICE appointmentScheduleSERVICE = new AppointmentScheduleSERVICE(new AppointmentScheduleIMPL());
    ObservableList<SnoozePOJO> searchTextList = FXCollections.observableArrayList();
    ObservableList<AppointmentScheduleBEAN> reminderTodayEventsLists = FXCollections.observableArrayList();
    ObservableList<AppointmentScheduleBEAN> reminderEventsSnoozedList = FXCollections.observableArrayList();
    AppointmentScheduleBEAN appointmentScheduleBEAN = new AppointmentScheduleBEAN();
    AppointmentScheduleBEAN appointmentScheduleBEANS = new AppointmentScheduleBEAN();
    String milliseconds;
    String meetingId;

    @FXML
    private TableView<ReminderBEAN> tblTodayRemainingEvents;
    @FXML
    private TableColumn<?, ?> colEventName;
    @FXML
    private TableColumn<ReminderBEAN, ReminderBEAN> colTimeRemaining;
    @FXML
    private Button btnDismiss;
    @FXML
    private Button btnDismissAll;
    @FXML
    private ComboBox<SnoozePOJO> cmbSnoozeMinutes;
    @FXML
    private Button btnSnoooze;
    String CUR_USERNAME = "";
    String CUR_BRANCH = "";
    String CUR_ROLE = "";
    ReminderDAO reminderDAO = new ReminderIMPL();
    ShowPopupMessages showErrors = new ShowPopupMessages();

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        CUR_USERNAME = Context.getInstance().currentProfile().getProfilePOJO().getUsername();
        CUR_BRANCH = Context.getInstance().currentProfile().getProfilePOJO().getBranch();
        CUR_ROLE = Context.getInstance().currentProfile().getProfilePOJO().getRole();
        searchTextList.addAll(new SnoozePOJO("1", "1 minutes before start"), new SnoozePOJO("2", "2 minutes before start"), new SnoozePOJO("5", "5 minutes before start"), new SnoozePOJO("7", "7 minutes before start"));
        cmbSnoozeMinutes.setItems(searchTextList);
        btnDismiss.disableProperty().bind(Bindings.equal(tblTodayRemainingEvents.getSelectionModel().selectedIndexProperty(), -1));
        btnSnoooze.disableProperty().bind(Bindings.equal(cmbSnoozeMinutes.getSelectionModel().selectedIndexProperty(), -1));
//        btnSnoooze.disableProperty().bind(Bindings.and(
//                tblTodayRemainingEvents.getSelectionModel().selectedIndexProperty().isEqualTo(-1),
//                cmbSnoozeMinutes.getSelectionModel().selectedIndexProperty().isEqualTo(-1)));

        btnSnoooze.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                if (cmbSnoozeMinutes.getSelectionModel().getSelectedIndex() > -1) {
                    ReminderBEAN rbean = tblTodayRemainingEvents.getSelectionModel().getSelectedItem();
                    rbean.setUpdatedUser(CUR_USERNAME);
                    rbean.setInterval(cmbSnoozeMinutes.getSelectionModel().getSelectedItem().getMinutes());
                    reminderDAO.handleSnoozeReminder(rbean);
                    ObservableList<ReminderBEAN> listRem = reminderDAO.retrieveReminder(CUR_USERNAME, CUR_BRANCH, CUR_ROLE);
                    if (listRem.size() > 0) {
                        addRemainingEventsIntoTable(listRem);
                    } else {
                        Stage stage = (Stage) btnSnoooze.getScene().getWindow();
                        stage.close();
                    }
                } else {
                    showErrors.showError("Please Choose minutes !", cmbSnoozeMinutes);
                }
            }

        });

        btnDismiss.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure want to dismiss this reminder !", ButtonType.YES, ButtonType.CANCEL);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                stage.initOwner(btnDismiss.getScene().getWindow());
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES) {
                    ReminderBEAN rbean = tblTodayRemainingEvents.getSelectionModel().getSelectedItem();
                    rbean.setUpdatedUser(CUR_USERNAME);
                    reminderDAO.deleteReminder(rbean.getRemId());
                    ObservableList<ReminderBEAN> listRem = reminderDAO.retrieveReminder(CUR_USERNAME, CUR_BRANCH, CUR_ROLE);
                    if (listRem.size() > 0) {
                        addRemainingEventsIntoTable(listRem);
                    } else {
                        Stage stage3 = (Stage) btnSnoooze.getScene().getWindow();
                        stage3.close();
                    }
                }
            }
        });
        btnDismissAll.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure want to dismiss all this reminders !", ButtonType.YES, ButtonType.CANCEL);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image(INALoginForm.class.getResourceAsStream("images/ia_logo.png")));
                stage.initOwner(btnDismiss.getScene().getWindow());
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES) {
                    ObservableList<ReminderBEAN> listRem = tblTodayRemainingEvents.getItems();
                    for (ReminderBEAN bEAN : listRem) {
                        reminderDAO.deleteReminder(bEAN.getRemId());
                    }
                    Stage stage2 = (Stage) btnSnoooze.getScene().getWindow();
                    stage2.close();
                }
            }
        });

    }

    private void addRemainingEventsIntoTable(ObservableList<ReminderBEAN> reminderEventsList) {
        System.out.println("reminderEventsList inside table cell :: " + reminderEventsList.toString());

        tblTodayRemainingEvents.getItems().clear();

        colEventName.setCellValueFactory(new PropertyValueFactory<>("meetingName"));
        colTimeRemaining.setCellValueFactory(new PropertyValueFactory<>("timeRemaining"));
        tblTodayRemainingEvents.getItems().addAll(reminderEventsList);

        tblTodayRemainingEvents.setRowFactory(new Callback<TableView<ReminderBEAN>, TableRow<ReminderBEAN>>() {

            @Override
            public TableRow<ReminderBEAN> call(TableView<ReminderBEAN> param) {
                final TableRow<ReminderBEAN> row = new TableRow<ReminderBEAN>() {
                    @Override
                    protected void updateItem(ReminderBEAN reminderBEAN, boolean empty) {
                        super.updateItem(reminderBEAN, empty);
                        if (!empty) {
                            String message = "Meeting Name : " + reminderBEAN.getMeetingName()
                                    + " \n Location : " + reminderBEAN.getLocation()
                                    + " \n Start Time : " + reminderBEAN.getStartDate()
                                    + " \n End Time : " + reminderBEAN.getEndDate();
                            setTooltip(new Tooltip(message));
                        } else {
                            setGraphic(null);
                            setText(null);
                        }

                    }

                };
                row.setOnMouseClicked(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        if (event.getButton() == MouseButton.PRIMARY) {
                            BeanUtils.copyProperties(appointmentScheduleBEANS, appointmentScheduleBEAN);
                            meetingId = appointmentScheduleBEAN.getMeetingId();
                            System.out.println("inside mouse click id::" + appointmentScheduleBEANS.getMeetingId());
                            System.out.println("inside mouse click appointmentScheduleBEANS :: " + appointmentScheduleBEANS.toString());
                        }
                    }

                });
                return row;
            }

        });
    }

    /**
     *
     * @param reminderTodayEventsList
     */
    public void passReminderTodayEvents(ObservableList<ReminderBEAN> reminderTodayEventsList) {
        reminderTodayEventsList.addListener(new ListChangeListener<Object>() {

            @Override
            public void onChanged(ListChangeListener.Change<? extends Object> c) {
            //    addRemainingEventsIntoTable(reminderTodayEventsList);
            }
        });
        addRemainingEventsIntoTable(reminderTodayEventsList);
    }

    /**
     *
     */
    public class SnoozePOJO {

        String minutes;
        String snoozeText;

        private SnoozePOJO() {

        }

        /**
         *
         * @return
         */
        public String getMinutes() {
            return minutes;
        }

        /**
         *
         * @param milliseconds
         */
        public void setMinutes(String milliseconds) {
            this.minutes = milliseconds;
        }

        /**
         *
         * @return
         */
        public String getSnoozeText() {
            return snoozeText;
        }

        /**
         *
         * @param snoozeText
         */
        public void setSnoozeText(String snoozeText) {
            this.snoozeText = snoozeText;
        }

        @Override
        public String toString() {
            return snoozeText;
        }

        /**
         *
         * @param milliseconds
         * @param snoozeText
         */
        public SnoozePOJO(String milliseconds, String snoozeText) {
            this.minutes = milliseconds;
            this.snoozeText = snoozeText;
        }

    }

}
