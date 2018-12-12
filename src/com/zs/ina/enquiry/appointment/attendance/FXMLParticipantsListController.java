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

import com.zs.ina.enquiry.appointment.dao.AppointmentScheduleIMPL;
import com.zs.ina.enquiry.appointment.dao.AppointmentScheduleSERVICE;
import com.zs.ina.enquiry.appointment.dao.UserPOJO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author 100035
 */
public class FXMLParticipantsListController implements Initializable {

    AppointmentScheduleSERVICE appointmentScheduleSERVICE = new AppointmentScheduleSERVICE(new AppointmentScheduleIMPL());

    String CUR_USERNAME = "";
    String CUR_BRANCH = "";

    @FXML
    private Label lblParticipantName;
    @FXML
    private CheckBox chkAttendance;
    @FXML
    private Label lblReason;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /**
     *
     * @param userPOJO
     * @param masterParticipantsIdList
     */
    public void loadParticipantsList(UserPOJO userPOJO, ObservableList<UserPOJO> masterParticipantsIdList) {

        lblParticipantName.setText(userPOJO.getFirstName() + " " + userPOJO.getLastName());
        if(userPOJO.getRemarks()!= null){
        lblReason.setText(userPOJO.getRemarks());
        chkAttendance.setDisable(true);
        lblReason.setStyle("-fx-text-fill:red");
        chkAttendance.setStyle("-fx-body-color:darkred");
        }
        System.out.println("Check Status :: " + userPOJO.getParticipationStatus());
        chkAttendance.selectedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    masterParticipantsIdList.add(userPOJO);
                    userPOJO.setParticipationStatus("present");
                } else {
                    masterParticipantsIdList.remove(userPOJO);
                    userPOJO.setParticipationStatus("absent");
                }
            }
        });
        if (userPOJO.getParticipationStatus() != null) {
            if (userPOJO.getParticipationStatus().equalsIgnoreCase("present")) {
                chkAttendance.setSelected(true);
            } else {
                chkAttendance.setSelected(false);
            }

        }
       

    }

}
