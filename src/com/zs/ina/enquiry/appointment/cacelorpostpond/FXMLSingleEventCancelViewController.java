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
package com.zs.ina.enquiry.appointment.cacelorpostpond;

import com.zs.ina.context.Context;
import com.zs.ina.enquiry.appointment.dao.AppointmentScheduleBEAN;
import com.zs.ina.enquiry.appointment.dao.AppointmentScheduleIMPL;
import com.zs.ina.enquiry.appointment.dao.AppointmentScheduleSERVICE;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import org.apache.log4j.Logger;
import org.controlsfx.control.PopOver;

/**
 * FXML Controller class
 *
 * @author 100035
 */
public class FXMLSingleEventCancelViewController implements Initializable {
    static Logger logger = Logger.getLogger(FXMLSingleEventCancelViewController.class);

    @FXML
    private Label lblMeetingName;
    @FXML
    private Label lblLocation;
    @FXML
    private Label lblStartDate;
    @FXML
    private Label lblEndDate;
    @FXML
    private Button btnCancel;

    AppointmentScheduleSERVICE appointmentScheduleSERVICE = new AppointmentScheduleSERVICE(new AppointmentScheduleIMPL());
    PopOver popOver = new PopOver();
    String CUR_USERNAME = "";
    String CUR_BRANCH = "";

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CUR_USERNAME = Context.getInstance().currentProfile().getProfilePOJO().getUsername();
        CUR_BRANCH = Context.getInstance().currentProfile().getProfilePOJO().getBranch();
    }

    /**
     *
     * @param appointmentScheduleBEAN
     */
    public void passEventDetails(AppointmentScheduleBEAN appointmentScheduleBEAN) {
        
        if(appointmentScheduleBEAN!=null){
        lblMeetingName.setText(appointmentScheduleBEAN.getMeetingName());
        lblLocation.setText(appointmentScheduleBEAN.getLocation());
        lblStartDate.setText(appointmentScheduleBEAN.getFormattedStartDate());
        lblEndDate.setText(appointmentScheduleBEAN.getFormattedEndDate());
        }
        /* =============== Accept Button ===================== */
        btnCancel.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
               try {
                    popOver = new PopOver();
                    appointmentScheduleBEAN.setInvitationStatus("rejected");
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zs/ina/enquiry/appointment/cancelorpostpond/FXMLEventCancelReason.fxml"));
                    Parent root = (Parent) loader.load();
                    FXMLEventCancelReasonController controller = (FXMLEventCancelReasonController) loader.getController();
                    AnchorPane anchorPane = new AnchorPane();
                    anchorPane.getChildren().add(root);
                    popOver.setContentNode(anchorPane);
                    popOver.setAutoHide(false);
                    popOver.setDetachable(false);
                    popOver.setTitle("Reason for Cancel");
                    popOver.setAnchorLocation(PopupWindow.AnchorLocation.CONTENT_BOTTOM_RIGHT);
                    popOver.setArrowLocation(PopOver.ArrowLocation.RIGHT_BOTTOM);
                    popOver.show(btnCancel.getScene().getWindow(), btnCancel.localToScreen(0, 0).getX() + 20, btnCancel.localToScreen(0, 0).getY() + 30);
                    controller.passPopOverObject(popOver,appointmentScheduleBEAN);
                } catch (IOException ex) {
                  logger.debug(ex);
                  ex.printStackTrace();
                }
                
                
                
//                appointmentScheduleBEAN.setMeetingStatus("cacelled");
//                int result_cancel = appointmentScheduleSERVICE.updateMeetingStatus(appointmentScheduleBEAN,CUR_USERNAME,CUR_BRANCH);
//                if (result_cancel > 0) {
//
//                    /* ====== set setEventCancel property of AppointmentScheduleBEAN to invoke its changeListener ====== */
//                    appointmentScheduleBEAN.setEventCancel("cancelled");
//
//                    /* ====== set setEventAccept property of AppointmentScheduleBEAN to invoke its changeListener in FXMLAgendaCalendarController ====== */
//                    Context.getInstance().currentProfile().setEventCancels(RandomStringUtils.randomAlphabetic(5));
//
//                    Notification notification = Notifications.SUCCESS;
//                    TrayNotification trayNotification = new TrayNotification("Event Cancelled", "Event cancelled Successfully", notification);
//                    trayNotification.showAndDismiss(Duration.seconds(3));
//
//                } else {
//                    Notification notification = Notifications.ERROR;
//                    TrayNotification trayNotification = new TrayNotification("Event not Cancelled", "Event Not Cancelled correctly", notification);
//                    trayNotification.showAndDismiss(Duration.seconds(3));
//
//                }
            }
        });
        /* =============== Postpone Button ===================== */

    }

}
