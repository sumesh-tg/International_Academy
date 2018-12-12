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
package com.zs.ina.enquiry.appointment;

import com.zs.ina.enquiry.appointment.dao.AppointmentScheduleBEAN;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 100035
 */
public class FXMLEventFullViewController implements Initializable {

//    AppointmentScheduleSERVICE appointmentScheduleSERVICE = new AppointmentScheduleSERVICE(new AppointmentScheduleIMPL());
//    ObservableList<AppointmentScheduleBEAN> singleEventDetailsList = FXCollections.observableArrayList();
    
    
    @FXML
    private Label lblMeetingName;
    @FXML
    private Label lblMeetingDescription;
    @FXML
    private Label lblStartDateTime;
    @FXML
    private Label lblEndDateTime;
    @FXML
    private Label lblLocation;
    @FXML
    private Label lblAnchor;
    @FXML
    private Button btnClose;
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnClose.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
              Stage stage = (Stage) btnClose.getScene().getWindow();
              stage.close();
            }
        });
    }

    /**
     *
     * @param appointmentScheduleBEAN
     */
    public void viewEventDetails(AppointmentScheduleBEAN appointmentScheduleBEAN) {
       lblMeetingName.setText(appointmentScheduleBEAN.getMeetingName());
       lblMeetingDescription.setText(appointmentScheduleBEAN.getMeetingDescription());
       lblStartDateTime.setText(appointmentScheduleBEAN.getFormattedStartDate());
       lblEndDateTime.setText(appointmentScheduleBEAN.getFormattedEndDate());
       lblLocation.setText(appointmentScheduleBEAN.getLocation());
       lblAnchor.setText(appointmentScheduleBEAN.getAnchor());
    }

}
