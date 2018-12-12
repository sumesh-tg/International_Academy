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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author 100035
 */
public class FXMLTodaySingleEventViewController implements Initializable {
    @FXML
    private Label lblMeetingName;
    @FXML
    private Label lblStartTime;
    @FXML
    private Label lblEndTime;
    @FXML
    private Label lblLocation;

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
     * @param appointmentScheduleBEAN
     */
    public void passEventDetails(AppointmentScheduleBEAN appointmentScheduleBEAN) {
    
        lblMeetingName.setText(appointmentScheduleBEAN.getMeetingName());
        lblLocation.setText(appointmentScheduleBEAN.getLocation());
        lblStartTime.setText(appointmentScheduleBEAN.getFormattedStartDate());
        lblEndTime.setText(appointmentScheduleBEAN.getFormattedEndDate()); 
    }
    
}
