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
package com.zs.ina.enquiry.appointment.cancelorpostpond;

import com.zs.ina.common.error.ShowPopupMessages;
import com.zs.ina.context.Context;
import com.zs.ina.enquiry.appointment.dao.AppointmentScheduleBEAN;
import com.zs.ina.enquiry.appointment.dao.AppointmentScheduleIMPL;
import com.zs.ina.enquiry.appointment.dao.AppointmentScheduleSERVICE;
import com.zs.traynotification.notification.Notification;
import com.zs.traynotification.notification.Notifications;
import com.zs.traynotification.notification.TrayNotification;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.util.Duration;
import org.apache.commons.lang.RandomStringUtils;
import org.controlsfx.control.PopOver;

/**
 * FXML Controller class
 *
 * @author 100035
 */
public class FXMLEventCancelReasonController implements Initializable {

    AppointmentScheduleBEAN appointmentScheduleBEAN = new AppointmentScheduleBEAN();
    AppointmentScheduleSERVICE appointmentScheduleSERVICE = new AppointmentScheduleSERVICE(new AppointmentScheduleIMPL());
    ShowPopupMessages showPopupMessages = new ShowPopupMessages();
    PopOver popOver = new PopOver();
    String CUR_USERNAME = "";
    String CUR_BRANCH = "";

    @FXML
    private TextArea txtarReasonforCancel;
    @FXML
    private Button btnSubmit;
    @FXML
    private Button btnCancel;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        CUR_USERNAME = Context.getInstance().currentProfile().getProfilePOJO().getUsername();
        CUR_BRANCH = Context.getInstance().currentProfile().getProfilePOJO().getBranch();

        btnSubmit.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (validateReason()) {
                    appointmentScheduleBEAN.setMeetingStatus("cacelled");
                    int resultUpdateMeetingstatus = appointmentScheduleSERVICE.updateMeetingStatus(appointmentScheduleBEAN, CUR_USERNAME, CUR_BRANCH);
                    int resultUpdateAllInvitationStatus = appointmentScheduleSERVICE.updateAllInvitationStatus(appointmentScheduleBEAN, CUR_USERNAME, CUR_BRANCH);

                    if (resultUpdateMeetingstatus > 0 && resultUpdateAllInvitationStatus > 0) {

                        /* ====== set setEventCancel property of AppointmentScheduleBEAN to invoke its changeListener ====== */
                        appointmentScheduleBEAN.setEventCancel("cancelled");

                        /* ====== set setEventAccept property of AppointmentScheduleBEAN to invoke its changeListener in FXMLAgendaCalendarController ====== */
                        Context.getInstance().currentProfile().setEventCancels(RandomStringUtils.randomAlphabetic(5));

                        Notification notification = Notifications.SUCCESS;
                        TrayNotification trayNotification = new TrayNotification("Event Cancelled", "Event cancelled Successfully", notification);
                        trayNotification.showAndDismiss(Duration.seconds(3));

                    } else {
                        Notification notification = Notifications.ERROR;
                        TrayNotification trayNotification = new TrayNotification("Event not Cancelled", "Event Not Cancelled correctly", notification);
                        trayNotification.showAndDismiss(Duration.seconds(3));

                    }

                    popOver.hide();
                }

            }

            private boolean validateReason() {
                boolean flag = true;
                if (txtarReasonforCancel.getText() == null || txtarReasonforCancel.getText().equals("")) {
                    showPopupMessages.showError("Enter reason for cancel", txtarReasonforCancel);
                    flag = false;
                }
                return flag;
            }
        }
        );
        btnCancel.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                popOver.hide();
            }
        }
        );
    }

    /**
     *
     * @param popOver
     * @param appointmentScheduleBEAN
     */
    public void passPopOverObject(PopOver popOver, AppointmentScheduleBEAN appointmentScheduleBEAN) {
        this.popOver = popOver;
        this.appointmentScheduleBEAN = appointmentScheduleBEAN;
    }

}
