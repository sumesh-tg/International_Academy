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

import com.zs.ina.context.Context;
import com.zs.ina.enquiry.appointment.dao.AppointmentScheduleBEAN;
import com.zs.ina.enquiry.appointment.dao.AppointmentScheduleIMPL;
import com.zs.ina.enquiry.appointment.dao.AppointmentScheduleSERVICE;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import org.apache.log4j.Logger;

/**
 * FXML Controller class
 *
 * @author 100035
 */
public class FXMLTodayEventsController implements Initializable {

    Logger logger = Logger.getLogger(FXMLTodayEventsController.class);
    AppointmentScheduleSERVICE appointmentScheduleSERVICE = new AppointmentScheduleSERVICE(new AppointmentScheduleIMPL());

    @FXML
    private ListView<AppointmentScheduleBEAN> listViewTodayEvents;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String CUR_USERNAME = Context.getInstance().currentProfile().getProfilePOJO().getUsername();
        String CUR_BRANCH = Context.getInstance().currentProfile().getProfilePOJO().getBranch();
        ObservableList<AppointmentScheduleBEAN> todayEventsList = FXCollections.observableArrayList();
        todayEventsList = appointmentScheduleSERVICE.viewTodayEvents(CUR_USERNAME, CUR_BRANCH);
        System.out.println("todayEventsList Test List :: " + todayEventsList.toString());
        addInvitationsIntoListViewTodayEvents(todayEventsList);
       
      
    }

    private void addInvitationsIntoListViewTodayEvents(ObservableList<AppointmentScheduleBEAN> todayEventsList) {
        listViewTodayEvents.setCellFactory(new Callback<ListView<AppointmentScheduleBEAN>, ListCell<AppointmentScheduleBEAN>>() {

            @Override
            public ListCell<AppointmentScheduleBEAN> call(ListView<AppointmentScheduleBEAN> param) {

                final ListCell<AppointmentScheduleBEAN> cell = new ListCell<AppointmentScheduleBEAN>() {
                    @Override
                    protected void updateItem(AppointmentScheduleBEAN appointmentScheduleBEAN, boolean empty) {
                        super.updateItem(appointmentScheduleBEAN, empty);
                        if (appointmentScheduleBEAN != null) {
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zs/ina/enquiry/appointment/FXMLTodaySingleEventView.fxml"));
                                Parent root = (Parent) loader.load();
                                FXMLTodaySingleEventViewController controller = (FXMLTodaySingleEventViewController) loader.getController();
                                controller.passEventDetails(appointmentScheduleBEAN);
                                AnchorPane anchorPane = new AnchorPane();
                                anchorPane.getChildren().add(root);

                                setGraphic(anchorPane);

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
        listViewTodayEvents.getItems().addAll(todayEventsList);
    }

}
