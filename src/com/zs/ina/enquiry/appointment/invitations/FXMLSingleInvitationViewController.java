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

import com.zs.ina.context.Context;
import com.zs.ina.enquiry.appointment.dao.AppointmentScheduleBEAN;
import com.zs.ina.enquiry.appointment.dao.AppointmentScheduleIMPL;
import com.zs.ina.enquiry.appointment.dao.AppointmentScheduleSERVICE;
import com.zs.traynotification.notification.Notification;
import com.zs.traynotification.notification.Notifications;
import com.zs.traynotification.notification.TrayNotification;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.PopupWindow;
import javafx.util.Duration;
import org.apache.commons.lang.RandomStringUtils;
import org.controlsfx.control.PopOver;
import org.controlsfx.control.PopOver.ArrowLocation;
import org.apache.log4j.Logger;

/**
 * FXML Controller class
 *
 * @author 100035
 */
public class FXMLSingleInvitationViewController implements Initializable {

    @FXML
    private Button btnReject;
    @FXML
    private Button btnAccept;
    @FXML
    private Label lblMeetingName;
    @FXML
    private Label lblStartDate;
    @FXML
    private Label lblEndDate;
    @FXML
    private Label lblLocation;
    Logger logger = Logger.getLogger(FXMLSingleInvitationViewController.class);
    AppointmentScheduleSERVICE appointmentScheduleSERVICE = new AppointmentScheduleSERVICE(new AppointmentScheduleIMPL());
    PopOver popOver = new PopOver();

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    /* =============== Display Invitation Details ===================== */

    /**
     *
     * @param appointmentScheduleBEAN
     */

    public void passEventDetails(AppointmentScheduleBEAN appointmentScheduleBEAN) {
        // this.appointmentScheduleBEAN=appointmentScheduleBEAN;
        System.out.println("test beann ::" + appointmentScheduleBEAN.toString());
        lblMeetingName.setText(appointmentScheduleBEAN.getMeetingName());
        lblLocation.setText(appointmentScheduleBEAN.getLocation());
        lblStartDate.setText(appointmentScheduleBEAN.getFormattedStartDate());
        lblEndDate.setText(appointmentScheduleBEAN.getFormattedEndDate());
        /* =============== Accept Button ===================== */
        btnAccept.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                appointmentScheduleBEAN.setInvitationStatus("accepted");
                int result = appointmentScheduleSERVICE.updateInvitationStatus(appointmentScheduleBEAN);
                if (result > 0) {

                    /* ====== set setEventAccept property of AppointmentScheduleBEAN to invoke its changeListener ====== */
                    appointmentScheduleBEAN.setEventAccept("accepted");

                    /* ====== set setEventAccept property of AppointmentScheduleBEAN to invoke its changeListener in FXMLAgendaCalendarController ====== */
                    Context.getInstance().currentProfile().setInvitationAcceptance(RandomStringUtils.randomAlphabetic(5));

                    Notification noticication = Notifications.SUCCESS;
                    TrayNotification trayNotification = new TrayNotification("Event Accepted", "Event Accepted Successfully", noticication);
                    trayNotification.showAndDismiss(Duration.seconds(3));

                } else {
                    Notification noticication = Notifications.ERROR;
                    TrayNotification trayNotification = new TrayNotification("Event Not Accepted", "Event Not Accepted correctly", noticication);
                    trayNotification.showAndDismiss(Duration.seconds(3));

                }
            }
        });
        /* =============== Reject Button ===================== */
        btnReject.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                try {
                    popOver = new PopOver();
                    appointmentScheduleBEAN.setInvitationStatus("rejected");
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zs/ina/enquiry/appointment/invitations/FXMLEventRejectionReason.fxml"));
                    Parent root = (Parent) loader.load();
                    FXMLEventRejectionReasonController controller = (FXMLEventRejectionReasonController) loader.getController();
                    AnchorPane anchorPane = new AnchorPane();
                    anchorPane.getChildren().add(root);
                    popOver.setContentNode(anchorPane);
                    popOver.setAutoHide(false);
                    popOver.setDetachable(false);
                    popOver.setTitle("Reason");
                    popOver.setAnchorLocation(PopupWindow.AnchorLocation.CONTENT_BOTTOM_RIGHT);
                    popOver.setArrowLocation(ArrowLocation.RIGHT_BOTTOM);

                    popOver.show(btnReject.getScene().getWindow(), btnReject.localToScreen(0, 0).getX() + 20, btnReject.localToScreen(0, 0).getY() + 30);
                    //  popOver.detach();
                    controller.passPopOverObject(popOver, appointmentScheduleBEAN);

                } catch (IOException sqle) {
                    logger.error(sqle.toString());
                    sqle.printStackTrace();
                }
            }
        });
    }
}
