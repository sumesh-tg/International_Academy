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
import org.controlsfx.control.PopOver;
import org.apache.log4j.Logger;
/**
 * FXML Controller class
 *
 * @author 100035
 */
public class FXMLSingleEventCancelViewController implements Initializable {

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
    Logger logger = Logger.getLogger(FXMLSingleEventCancelViewController.class);

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
        // this.appointmentScheduleBEAN=appointmentScheduleBEAN;

        if (appointmentScheduleBEAN != null) {
            System.out.println("test beann cancel ::" + appointmentScheduleBEAN.toString());
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
                    controller.passPopOverObject(popOver, appointmentScheduleBEAN);
                } catch (IOException sqle) {
                    logger.error(sqle.toString());
                    sqle.printStackTrace();

                }
            }
        });
        /* =============== Postpone Button ===================== */

    }

}
