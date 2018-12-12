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
package com.zs.ina.enquiry.appointment.invitations;

import com.zs.ina.common.error.ShowPopupMessages;
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
import org.controlsfx.control.PopOver;

/**
 * FXML Controller class
 *
 * @author 100035
 */
public class FXMLEventRejectionReasonController implements Initializable {

    AppointmentScheduleBEAN appointmentScheduleBEAN = new AppointmentScheduleBEAN();
    AppointmentScheduleSERVICE appointmentScheduleSERVICE = new AppointmentScheduleSERVICE(new AppointmentScheduleIMPL());
    ShowPopupMessages showPopupMessages = new ShowPopupMessages();
    PopOver popOver = new PopOver();

    @FXML
    private TextArea txtarReasonForRejection;
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
        btnSubmit.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (validateReason()) {
                    appointmentScheduleBEAN.setRemarks(txtarReasonForRejection.getText());
                    int result = appointmentScheduleSERVICE.updateInvitationStatus(appointmentScheduleBEAN);
                    int results = appointmentScheduleSERVICE.updateRemarks(appointmentScheduleBEAN);

                    if (result > 0 && results > 0) {
                        appointmentScheduleBEAN.setEventReject("rejected");
                        Notification notification = Notifications.SUCCESS;
                        TrayNotification trayNotification = new TrayNotification("Event Rejected", "Event rejected successfully", notification);
                        trayNotification.showAndDismiss(Duration.seconds(3));

                    } else {
                        Notification notification = Notifications.ERROR;
                        TrayNotification trayNotification = new TrayNotification("Event Not Rejected", "Event not rejected correctly", notification);
                        trayNotification.showAndDismiss(Duration.seconds(3));

                    }
                    popOver.hide();
                }
             
            }

            private boolean validateReason() {
                boolean flag = true;
                if (txtarReasonForRejection.getText() == null || txtarReasonForRejection.getText().equals("")) {
                    showPopupMessages.showError("Enter reason for rejection", txtarReasonForRejection);
                    flag = false;
                }
                return flag;
            }
        }
        );
        btnClose.setOnAction(new EventHandler<ActionEvent>() {

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
